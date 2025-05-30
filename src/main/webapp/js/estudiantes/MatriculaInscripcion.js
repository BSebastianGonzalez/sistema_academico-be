document.addEventListener('DOMContentLoaded', function() {
    const API_CURSOS = '/api/cursos';
    const API_MATRICULA = '/api/matricula';
    const tablaCursos = document.querySelector('#tablaCursos tbody');
    const listaCursos = document.getElementById('cursosSeleccionados');
    const btnConfirmar = document.getElementById('btnConfirmarMatricula');

    let cursosDisponibles = [];
    let cursosSeleccionados = [];

    // Cargar cursos
    function cargarCursos() {
        fetch(API_CURSOS)
            .then(response => response.json())
            .then(data => {
                cursosDisponibles = data;
                mostrarCursos(data);
            });
    }

    // Mostrar cursos
    function mostrarCursos(cursos) {
        tablaCursos.innerHTML = '';
        cursos.forEach(curso => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${curso.id}</td>
                <td>${curso.nombre}</td>
                <td>${curso.creditos}</td>
                <td>${curso.horario}</td>
                <td>
                    <button class="btn btn-sm btn-primary" onclick="seleccionarCurso(${curso.id})">Seleccionar</button>
                </td>
            `;
            tablaCursos.appendChild(tr);
        });
    }

    // Seleccionar curso
    window.seleccionarCurso = function(id) {
        const curso = cursosDisponibles.find(c => c.id === id);
        if (curso && !cursosSeleccionados.some(c => c.id === id)) {
            cursosSeleccionados.push(curso);
            actualizarListaSeleccionados();
        }
    }

    // Actualizar lista
    function actualizarListaSeleccionados() {
        listaCursos.innerHTML = '';
        cursosSeleccionados.forEach(curso => {
            const li = document.createElement('li');
            li.className = 'list-group-item d-flex justify-content-between align-items-center';
            li.innerHTML = `
                ${curso.nombre} (${curso.creditos} créditos)
                <button class="btn btn-sm btn-danger" onclick="eliminarCurso(${curso.id})">
                    <i class="fas fa-times"></i>
                </button>
            `;
            listaCursos.appendChild(li);
        });
    }

    // Eliminar curso
    window.eliminarCurso = function(id) {
        cursosSeleccionados = cursosSeleccionados.filter(c => c.id !== id);
        actualizarListaSeleccionados();
    }

    // Confirmar matrícula
    btnConfirmar.addEventListener('click', function() {
        const cursosIds = cursosSeleccionados.map(c => c.id);

        fetch(API_MATRICULA, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ cursos: cursosIds })
        })
            .then(response => {
                if (response.ok) {
                    alert('Matrícula confirmada con éxito');
                    cursosSeleccionados = [];
                    actualizarListaSeleccionados();
                } else {
                    throw new Error('Error al confirmar matrícula');
                }
            })
            .catch(error => console.error('Error:', error));
    });

    // Inicializar
    cargarCursos();
});