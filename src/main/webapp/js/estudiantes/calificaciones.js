document.addEventListener('DOMContentLoaded', function() {
    // Obtener ID del estudiante desde la sesión
    const ESTUDIANTE_ID = ${sessionScope.estudiante.id}; // Ajustar según tu estructura
    const API_BASE_URL = '${pageContext.request.contextPath}/api'; // URL de tu API

    // Elementos DOM
    const selectSemestre = document.getElementById('selectSemestre');
    const cuerpoTabla = document.getElementById('cuerpoTabla');

    // Cargar semestres del estudiante
    const cargarSemestres = async () => {
    try {
    const response = await axios.get(`${API_BASE_URL}/estudiantes/${ESTUDIANTE_ID}/semestres`);
    selectSemestre.innerHTML = response.data.map(semestre =>
    `<option value="${semestre.id}">${semestre.nombre}</option>`
    ).join('');

    if (response.data.length > 0) {
    cargarCalificaciones(response.data[0].id);
}
} catch (error) {
    console.error('Error al cargar semestres:', error);
    cuerpoTabla.innerHTML = `<tr><td colspan="5" class="text-center text-danger">Error al cargar los semestres</td></tr>`;
}
};

    // Cargar calificaciones por semestre
    const cargarCalificaciones = async (semestreId) => {
    try {
    cuerpoTabla.innerHTML = `<tr><td colspan="5" class="text-center">Cargando calificaciones...</td></tr>`;

    const response = await axios.get(
    `${API_BASE_URL}/calificaciones?estudianteId=${ESTUDIANTE_ID}&semestreId=${semestreId}`
    );

    renderizarCalificaciones(response.data);
} catch (error) {
    console.error('Error al cargar calificaciones:', error);
    cuerpoTabla.innerHTML = `<tr><td colspan="5" class="text-center text-danger">Error al cargar las calificaciones</td></tr>`;
}
};

    // Renderizar datos en la tabla
    const renderizarCalificaciones = (calificaciones) => {
    if (calificaciones.length === 0) {
    cuerpoTabla.innerHTML = `<tr><td colspan="5" class="text-center">No hay calificaciones registradas</td></tr>`;
    return;
}

    let htmlContent = '';
    const materias = {};

    // Agrupar por materia
    calificaciones.forEach(calif => {
    if (!materias[calif.materiaId]) {
    materias[calif.materiaId] = {
    nombre: calif.materiaNombre,
    calificaciones: [],
    notaFinal: 0
};
}
    materias[calif.materiaId].calificaciones.push(calif);
});

    // Calcular nota final por materia
    Object.keys(materias).forEach(materiaId => {
    const materia = materias[materiaId];
    materia.notaFinal = materia.calificaciones.reduce(
    (total, calif) => total + (calif.nota * calif.porcentaje / 100), 0
    );

    // Cabecera de materia
    htmlContent += `
          <tr class="materia-header">
            <td colspan="5">${materia.nombre}</td>
          </tr>
        `;

    // Filas de calificaciones
    materia.calificaciones.forEach(calif => {
    htmlContent += `
            <tr>
              <td></td>
              <td>${calif.evaluacionNombre}</td>
              <td>${calif.porcentaje}%</td>
              <td>${calif.nota}</td>
              <td>${new Date(calif.fecha).toLocaleDateString()}</td>
            </tr>
          `;
});

    // Fila de nota final
    htmlContent += `
          <tr class="nota-final">
            <td colspan="3"></td>
            <td><strong>${materia.notaFinal.toFixed(2)}</strong></td>
            <td>NOTA FINAL</td>
          </tr>
        `;
});

    cuerpoTabla.innerHTML = htmlContent;
};

    // Event Listeners
    selectSemestre.addEventListener('change', (e) => {
    if (e.target.value) {
    cargarCalificaciones(e.target.value);
}
});

    // Iniciar carga
    cargarSemestres();
});
