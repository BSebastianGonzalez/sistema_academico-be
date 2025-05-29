const API = 'http://localhost:8080/cursos';

document.addEventListener('DOMContentLoaded', fetchCursos);

function fetchCursos() {
    fetch(API)
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#tablaCursos tbody');
            tbody.innerHTML = '';
            data.forEach(c => {
                tbody.innerHTML += `
          <tr>
            <td>${c.id}</td>
            <td>${c.nombre}</td>
            <td>${c.descripcion}</td>
            <td>
              <button class="btn btn-sm btn-warning" onclick="editCurso(${c.id})">✏️</button>
              <button class="btn btn-sm btn-danger" onclick="deleteCurso(${c.id})">🗑️</button>
            </td>
          </tr>`;
            });
        });
}

function openForm() {
    $('#cursoModal').modal('show');
    document.getElementById('modalTitle').innerText = 'Nuevo Curso';
    document.getElementById('formCurso').reset();
    document.getElementById('cursoId').value = '';
}

function saveCurso(e) {
    e.preventDefault();
    const id = document.getElementById('cursoId').value;
    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API}/${id}` : API;
    const body = {
        nombre: document.getElementById('nombre').value,
        descripcion: document.getElementById('descripcion').value
    };

    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
    })
        .then(() => {
            $('#cursoModal').modal('hide');
            fetchCursos();
        });
}

function editCurso(id) {
    fetch(`${API}/${id}`)
        .then(res => res.json())
        .then(c => {
            document.getElementById('cursoId').value = c.id;
            document.getElementById('nombre').value = c.nombre;
            document.getElementById('descripcion').value = c.descripcion;
            document.getElementById('modalTitle').innerText = 'Editar Curso';
            $('#cursoModal').modal('show');
        });
}

function deleteCurso(id) {
    if (!confirm('¿Eliminar curso?')) return;
    fetch(`${API}/${id}`, { method: 'DELETE' })
        .then(() => fetchCursos());
}
