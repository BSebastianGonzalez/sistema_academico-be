document.addEventListener('DOMContentLoaded', function() {
    console.log('Script de docente cargado correctamente');

    // 1. Configurar menú
    setupMenu();

    // 2. Configurar eventos
    setupEvents();

    // 3. Cargar datos del docente
    loadTeacherData();
});

function setupMenu() {
    const menuLinks = document.querySelectorAll('.menu-link');

    menuLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();

            // Remover activo de todos
            menuLinks.forEach(item => {
                item.classList.remove('active');
            });

            // Agregar activo al seleccionado
            this.classList.add('active');

            // Obtener el módulo objetivo
            const targetModule = this.getAttribute('data-target');
            console.log('Cambiando a módulo:', targetModule);

            // Aquí puedes cargar contenido dinámico si es necesario
        });
    });
}

function setupEvents() {
    // Botón de logout
    document.getElementById('logout-btn').addEventListener('click', function(e) {
        e.preventDefault();
        logout();
    });

    // Botón de cursos
    document.getElementById('cursos-btn').addEventListener('click', function(e) {
        console.log('Accediendo a módulo de cursos');
        // Aquí podrías cargar contenido dinámico
    });

    // Botón de evaluaciones
    document.getElementById('evaluaciones-btn').addEventListener('click', function(e) {
        console.log('Accediendo a módulo de evaluaciones');
        // Aquí podrías cargar contenido dinámico
    });
}

function loadTeacherData() {
    // Simulando datos del backend
    const teacherData = {
        nombre: "Prof. María Rodríguez",
        departamento: "Departamento de Matemáticas",
        codigo: "PRO-12345"
    };

    // Actualizar UI con datos del docente
    document.querySelector('.user-name').textContent = teacherData.nombre;
    document.querySelector('.user-role').textContent = teacherData.departamento;
    document.querySelector('.page-title').textContent = `Bienvenido, ${teacherData.nombre}`;
}

function logout() {
    console.log('Cerrando sesión de docente...');

    // Aquí iría la lógica real de logout
    // Por ahora solo redirigimos
    window.location.href = "${pageContext.request.contextPath}/index.jsp";
}