document.addEventListener('DOMContentLoaded', function() {
    const API_DOCENTES = '/docentes';
    const tabla = document.querySelector('#tablaDocentes tbody');
    const btnNuevo = document.getElementById('btnNuevoDocente');
    const modal = new bootstrap.Modal('#modalDocente');
    const form = document.getElementById('formDocente');
    const btnGuardar = document.getElementById('btnGuardarDocente');

    // Cargar docentes
    function cargarDocentes() {
        fetch(API_DOCENTES)
            .then(response => response.json())
            .then(data => {
                tabla.innerHTML = '';
                data.forEach(docente => {
                    const tr = document.createElement('tr');
                    tr.innerHTML = `
                        <td>${docente.id}</td>
                        <td>${docente.nombre}</td>
                        <td>${docente.especialidad}</td>
                        <td>${docente.email}</td>
                        <td>
                            <button class="btn btn-sm btn-warning" onclick="editarDocente(${docente.id})">Editar</button>
                            <button class="btn btn-sm btn-danger" onclick="eliminarDocente(${docente.id})">Eliminar</button>
                        </td>
                    `;
                    tabla.appendChild(tr);
                });
            });
    }

    // Nuevo docente
    btnNuevo.addEventListener('click', () => {
        form.reset();
        document.getElementById('docenteId').value = '';
        modal.show();
    });

    // Guardar docente
    btnGuardar.addEventListener('click', () => {
        const docente = {
            id: document.getElementById('docenteId').value || null,
            nombre: document.getElementById('nombre').value,
            especialidad: document.getElementById('especialidad').value,
            email: document.getElementById('email').value,
            telefono: document.getElementById('telefono').value
        };

        const metodo = docente.id ? 'PUT' : 'POST';
        const url = docente.id ? `${API_DOCENTES}/${docente.id}` : API_DOCENTES;

        fetch(url, {
            method: metodo,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(docente)
        })
            .then(response => {
                if (response.ok) {
                    modal.hide();
                    cargarDocentes();
                } else {
                    throw new Error('Error al guardar docente');
                }
            })
            .catch(error => console.error('Error:', error));
    });

    // Funciones globales
    window.editarDocente = function(id) {
        fetch(`${API_DOCENTES}/${id}`)
            .then(response => response.json())
            .then(docente => {
                document.getElementById('docenteId').value = docente.id;
                document.getElementById('nombre').value = docente.nombre;
                document.getElementById('especialidad').value = docente.especialidad;
                document.getElementById('email').value = docente.email;
                document.getElementById('telefono').value = docente.telefono;
                modal.show();
            });
    };

    window.eliminarDocente = function(id) {
        if (confirm('¿Está seguro de eliminar este docente?')) {
            fetch(`${API_DOCENTES}/${id}`, { method: 'DELETE' })
                .then(() => cargarDocentes())
                .catch(error => console.error('Error:', error));
        }
    };

    // Inicializar
    cargarDocentes();
});