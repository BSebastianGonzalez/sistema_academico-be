document.addEventListener('DOMContentLoaded', () => {
    // Inicializar flatpickr para rangos de fechas
    flatpickr('#fechaInicio', { locale: 'es', dateFormat: 'Y-m-d' });
    flatpickr('#fechaFin', { locale: 'es', dateFormat: 'Y-m-d' });

    // Inicializar DataTable
    window.detalleTable = $('#detalleAsistenciaTable').DataTable({
        columns: [
            { data: 'fecha' },
            { data: 'hora' },
            { data: 'estado' },
            { data: 'justificacion' }
        ],
        language: {
            url: 'https://cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
        }
    });

    // Botones de Ver Detalle en resumen
    document.querySelectorAll('.btn-detalle-curso').forEach(btn => {
        btn.addEventListener('click', () => {
            const cursoId = btn.getAttribute('data-curso-id');
            cargarDetalle(cursoId);
            // Cambiar a pestaña Detalle por Curso
            const detalleTab = new bootstrap.Tab(document.querySelector('#nav-detalle-tab'));
            detalleTab.show();
        });
    });

    // Select en Detalle por Curso
    document.getElementById('selectCurso').addEventListener('change', e => cargarDetalle(e.target.value));

    // Filtrar rango de fechas
    document.getElementById('fechaFin').addEventListener('change', () => {
        const cursoId = document.getElementById('selectCurso').value;
        if (cursoId) cargarDetalle(cursoId);
    });

    // Envío de justificación
    document.getElementById('btnEnviarJustificacion').addEventListener('click', enviarJustificacion);
});

function cargarDetalle(cursoId) {
    const fechaInicio = document.getElementById('fechaInicio').value;
    const fechaFin = document.getElementById('fechaFin').value;
    // Actualizar título
    document.getElementById('detalleCursoTitulo').textContent = 'Detalle de Asistencia - Curso ' + cursoId;
    // Cargar datos vía AJAX
    fetch(`api/asistencia/detalle?curso=${cursoId}&inicio=${fechaInicio}&fin=${fechaFin}`)
        .then(res => res.json())
        .then(data => {
            detalleTable.clear().rows.add(data).draw();
            // Añadir botones de justificar en ausencias
            $('#detalleAsistenciaTable tbody').off('click', '.btn-justificar').on('click', '.btn-justificar', function() {
                const registro = $(this).data('registro-id');
                abrirModalJustificacion(registro);
            });
        })
        .catch(err => console.error(err));
}

function abrirModalJustificacion(registroId) {
    $('#justificarModal').modal('show');
    document.getElementById('registroAsistenciaId').value = registroId;
}

function enviarJustificacion() {
    const form = document.getElementById('justificacionForm');
    const formData = new FormData(form);
    formData.append('registroId', document.getElementById('registroAsistenciaId').value);

    fetch('api/asistencia/justificar', {
        method: 'POST',
        body: formData
    })
        .then(res => res.json())
        .then(r => {
            alert(r.mensaje);
            $('#justificarModal').modal('hide');
            // Recargar detalle actual
            const cursoId = document.getElementById('selectCurso').value;
            if (cursoId) cargarDetalle(cursoId);
        })
        .catch(err => alert('Error al enviar justificación'));
}
