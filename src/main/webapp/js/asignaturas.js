const API_ASIGN = 'http://localhost:8080/api/asignaturas';

document.addEventListener('DOMContentLoaded', fetchAsign);

function fetchAsign() {
    fetch(API_ASIGN)
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#tablaAsign tbody');
            tbody.innerHTML = '';
            data.forEach(a => {
                tbody.innerHTML += `
          <tr>
            <td>${a.id}</td>
            <td>${a.nombre}</td>
            <td>${a.contenido}</td>
            <td>${a.objetivos}</td>
            <td>${a.competencias}</td>
            <td>
              <button class="btn btn-sm btn-warning" onclick="editAsign(${a.id})">✏️</button>
              <button class="btn btn-sm btn-danger" onclick="deleteAsign(${a.id})">🗑️</button>
            </td>
          </tr>`;
            });
        })
        .catch(err => console.error('Error cargando asignaturas:', err));
}

function openForm() {
    $('#asignModal').modal('show');
    document.getElementById('modalTitle').innerText = 'Nueva Asignatura';
    document.getElementById('formAsign').reset();
    document.getElementById('asignId').value = '';
}

function saveAsign(e) {
    e.preventDefault();
    const id = document.getElementById('asignId').value;
    const method = id ? 'PUT' : 'POST';
    const url = id ? `${API_ASIGN}/${id}` : API_ASIGN;
    const body = {
        nombre: document.getElementById('nombre').value,
        contenido: document.getElementById('contenido').value,
        objetivos: document.getElementById('objetivos').value,
        competencias: document.getElementById('competencias').value
    };

    fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
    })
        .then(res => {
            if (!res.ok) throw new Error('Error guardando');
            $('#asignModal').modal('hide');
            fetchAsign();
        })
        .catch(err => alert(err));
}

function editAsign(id) {
    fetch(`${API_ASIGN}/${id}`)
        .then(res => res.json())
        .then(a => {
            document.getElementById('asignId').value = a.id;
            document.getElementById('nombre').value = a.nombre;
            document.getElementById('contenido').value = a.contenido;
            document.getElementById('objetivos').value = a.objetivos;
            document.getElementById('competencias').value = a.competencias;
            document.getElementById('modalTitle').innerText = 'Editar Asignatura';
            $('#asignModal').modal('show');
        })
        .catch(err => console.error('Error al editar:', err));
}

function deleteAsign(id) {
    if (!confirm('¿Eliminar esta asignatura?')) return;
    fetch(`${API_ASIGN}/${id}`, { method: 'DELETE' })
        .then(res => {
            if (!res.ok) throw new Error('Error eliminando');
            fetchAsign();
        })
        .catch(err => alert(err));
}
