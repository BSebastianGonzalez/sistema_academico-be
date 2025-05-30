document.addEventListener('DOMContentLoaded', async function() {
    // Verificar autenticación
    const token = localStorage.getItem('studentToken');
    if (!token) {
        window.location.href = 'login.jsp';
        return;
    }

    try {
        // Cargar datos del estudiante
        const response = await fetch('${pageContext.request.contextPath}/api/estudiantes/perfil', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (!response.ok) {
            throw new Error('Error al cargar datos del estudiante');
        }

        const estudiante = await response.json();
        actualizarUI(estudiante);
        configurarEventos();

    } catch (error) {
        console.error('Error:', error);
        alert('Error al cargar datos. Será redirigido al login.');
        localStorage.removeItem('studentToken');
        window.location.href = 'login.jsp';
    }
});

function actualizarUI(estudiante) {
    document.querySelectorAll('.user-name').forEach(el => {
        el.textContent = estudiante.nombreCompleto;
    });

    document.querySelector('.user-role').textContent = `Estudiante - ${estudiante.carrera}`;
    document.querySelector('.page-title').textContent = `Bienvenido, ${estudiante.nombreCompleto}`;

    // Actualizar estadísticas
    document.querySelector('#cursos-count').textContent = estudiante.cursosInscritos || 0;
    document.querySelector('#promedio').textContent = estudiante.promedioAcademico || 'N/A';
}

function configurarEventos() {
    // Evento para logout
    document.getElementById('logout-btn').addEventListener('click', function() {
        localStorage.removeItem('studentToken');
        window.location.href = 'login.jsp';
    });

    // Otros eventos del dashboard...
}