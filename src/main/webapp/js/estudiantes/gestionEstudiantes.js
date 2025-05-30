// Inicialización del panel de estudiante
document.addEventListener('DOMContentLoaded', function() {
    // Cargar datos del estudiante
    cargarDatosEstudiante();

    // Configurar eventos
    configurarEventosEstudiante();

    // Verificar sesión
    verificarSesion();
});

function cargarDatosEstudiante() {
    // Obtener datos de sesión (simulado)
    const estudiante = {
        nombre: "Diego Pérez",
        carrera: "Ingeniería de Sistemas",
        codigo: "202310234",
        promedio: 4.7,
        cursosInscritos: 6
    };

    // Actualizar elementos del DOM
    document.querySelectorAll('.user-name').forEach(el => {
        el.textContent = estudiante.nombre;
    });

    document.querySelector('.user-role').textContent = `Estudiante - ${estudiante.carrera}`;
    document.querySelector('.page-title').textContent = `Bienvenido, ${estudiante.nombre}`;
    document.querySelector('.stat-value:nth-child(1)').textContent = estudiante.cursosInscritos;
    document.querySelector('.stat-value:nth-child(2)').textContent = estudiante.promedio;
}

function configurarEventosEstudiante() {
    // Evento para el menú desplegable
    document.querySelectorAll('.dropdown-toggle').forEach(toggle => {
        toggle.addEventListener('click', function(e) {
            e.preventDefault();
            const menu = this.nextElementSibling;
            menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
        });
    });

    // Cerrar menú al hacer clic fuera
    document.addEventListener('click', function(e) {
        if (!e.target.matches('.dropdown-toggle')) {
            document.querySelectorAll('.dropdown-menu').forEach(menu => {
                menu.style.display = 'none';
            });
        }
    });

    // Navegación entre módulos
    document.querySelectorAll('.menu-link').forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();

            // Remover clase activa de todos los enlaces
            document.querySelectorAll('.menu-link').forEach(el => {
                el.classList.remove('active');
            });

            // Agregar clase activa al enlace seleccionado
            this.classList.add('active');

            // Mostrar el módulo correspondiente (simulado)
            const target = this.getAttribute('href');
            alert(`Cargando módulo: ${target.substring(1)}`);
        });
    });
}

function verificarSesion() {
    // Verificar si hay una sesión activa (simulado)
    const sessionActive = localStorage.getItem('studentSession') || true;

    if (!sessionActive) {
        alert('Debe iniciar sesión primero');
        window.location.href = `${getContextPath()}/index.jsp`;
    }
}

function getContextPath() {
    return window.location.pathname.split('/')[1];
}

// Función para cerrar sesión
window.logout = function() {
    localStorage.removeItem('studentSession');
    window.location.href = `${getContextPath()}/index.jsp`;
};