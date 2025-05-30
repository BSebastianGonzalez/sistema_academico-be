document.addEventListener('DOMContentLoaded', function() {
    console.log('Script de estudiante cargado correctamente');

    // 1. Configurar menú
    setupMenu();

    // 2. Configurar eventos
    setupEvents();

    // 3. Cargar datos del estudiante
    loadStudentData();
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

    // Botón de matrícula
    document.getElementById('matricula-btn').addEventListener('click', function(e) {
        console.log('Accediendo a módulo de matrícula');
        // Aquí podrías cargar contenido dinámico
    });

    // Botón de historial
    document.getElementById('historial-btn').addEventListener('click', function(e) {
        console.log('Accediendo a módulo de historial académico');
        // Aquí podrías cargar contenido dinámico
    });
}

function loadStudentData() {
    // Simulando datos del backend
    const studentData = {
        nombre: "Diego Pérez",
        codigo: "202310234",
        carrera: "Ingeniería de Sistemas",
        promedio: 4.7
    };

    // Actualizar UI con datos del estudiante
    document.querySelector('.user-name').textContent = studentData.nombre;
    document.querySelector('.user-role').textContent = `Estudiante - ${studentData.carrera}`;
    document.querySelector('.page-title').textContent = `Bienvenido, ${studentData.nombre}`;
}

function logout() {
    console.log('Cerrando sesión de estudiante...');

    // Aquí iría la lógica real de logout
    // Ejemplo: 
    // fetch('/api/logout', { method: 'POST' })
    //   .then(response => window.location.href = '/index.jsp')

    // Por ahora solo redirigimos
    window.location.href = "${pageContext.request.contextPath}/index.jsp";
}