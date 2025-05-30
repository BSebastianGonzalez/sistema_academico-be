<%--
  Created by IntelliJ IDEA.
  User: diego
  Date: 29/05/2025
  Time: 6:30 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Asignación de Cursos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .card-header {
            background: linear-gradient(135deg, #3498db 0%, #2980b9 100%);
            color: white;
        }
        .teacher-card {
            transition: all 0.3s;
            border: 2px solid transparent;
        }
        .teacher-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
            border-color: #3498db;
        }
        .badge-available {
            background-color: #28a745;
        }
        .badge-full {
            background-color: #dc3545;
        }
        .course-item {
            transition: all 0.2s;
        }
        .course-item:hover {
            background-color: #f8f9fa;
        }
        .assigned-course {
            background-color: #e8f4ff;
            border-left: 4px solid #3498db;
        }
        .time-conflict {
            background-color: #ffeef0;
            border-left: 4px solid #dc3545;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <jsp:include page="../partials/sidebar.jsp">
            <jsp:param name="active" value="asignacion"/>
        </jsp:include>

        <!-- Main Content -->
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4 py-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Asignación de Cursos a Docentes</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#asignarModal">
                        <i class="fas fa-plus me-1"></i> Nueva Asignación
                    </button>
                </div>
            </div>

            <!-- Filtros -->
            <div class="card mb-4">
                <div class="card-body">
                    <div class="row g-3">
                        <div class="col-md-4">
                            <label class="form-label">Docente</label>
                            <select id="filterDocente" class="form-select">
                                <option value="">Todos los docentes</option>
                                <!-- JS populate -->
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label class="form-label">Semestre</label>
                            <select id="filterSemestre" class="form-select">
                                <option value="2023-1">2023-1</option>
                                <option value="2023-2" selected>2023-2</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <label class="form-label">Estado</label>
                            <select id="filterEstado" class="form-select">
                                <option value="">Todos</option>
                                <option value="disponible">Disponible</option>
                                <option value="completo">Carga Completa</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Lista de Docentes -->
            <div class="row" id="docentesContainer">
                <!-- JS populate -->
            </div>
        </main>
    </div>
</div>

<!-- Modal Asignar Curso -->
<div class="modal fade" id="asignarModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title">Asignar Nuevo Curso</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="asignacionForm">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label">Docente</label>
                            <select id="docenteSelect" class="form-select" required>
                                <option value="">Seleccionar docente...</option>
                                <!-- JS populate -->
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Curso</label>
                            <select id="cursoSelect" class="form-select" required>
                                <option value="">Seleccionar curso...</option>
                                <!-- JS populate -->
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Grupo</label>
                            <select id="grupoSelect" class="form-select" required>
                                <option value="">Seleccionar grupo...</option>
                                <!-- JS populate -->
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Horas Semanales</label>
                            <input type="number" id="horasInput" class="form-control" min="1" max="20" value="4" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Día</label>
                            <select id="diaSelect" class="form-select" required>
                                <option value="LUNES">Lunes</option>
                                <option value="MARTES">Martes</option>
                                <option value="MIÉRCOLES">Miércoles</option>
                                <option value="JUEVES">Jueves</option>
                                <option value="VIERNES">Viernes</option>
                                <option value="SÁBADO">Sábado</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Hora Inicio</label>
                            <input type="time" id="horaInicio" class="form-control" value="08:00" required>
                        </div>
                        <div class="col-12">
                            <div id="conflictAlert" class="alert alert-danger d-none">
                                <i class="fas fa-exclamation-triangle me-2"></i>
                                <span id="conflictMessage"></span>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" id="btnGuardarAsignacion" class="btn btn-primary">Guardar Asignación</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Horario -->
<div class="modal fade" id="horarioModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title">Horario del Docente</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="horarioTable">
                        <thead class="table-light">
                        <tr>
                            <th>Hora</th>
                            <th>Lunes</th>
                            <th>Martes</th>
                            <th>Miércoles</th>
                            <th>Jueves</th>
                            <th>Viernes</th>
                            <th>Sábado</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- JS populate -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    // Constantes y variables globales
    const API_BASE = '${pageContext.request.contextPath}/api';
    let docentes = [];
    let cursos = [];
    let grupos = [];
    let horarios = {};

    // Elementos DOM
    const docentesContainer = document.getElementById('docentesContainer');
    const filterDocente = document.getElementById('filterDocente');
    const filterEstado = document.getElementById('filterEstado');
    const docenteSelect = document.getElementById('docenteSelect');
    const cursoSelect = document.getElementById('cursoSelect');
    const grupoSelect = document.getElementById('grupoSelect');
    const diaSelect = document.getElementById('diaSelect');
    const horasInput = document.getElementById('horasInput');
    const horaInicio = document.getElementById('horaInicio');
    const conflictAlert = document.getElementById('conflictAlert');
    const conflictMessage = document.getElementById('conflictMessage');
    const btnGuardarAsignacion = document.getElementById('btnGuardarAsignacion');

    // Inicialización
    document.addEventListener('DOMContentLoaded', () => {
        cargarDocentes();
        cargarCursos();
        cargarGrupos();
    });

    // Cargar docentes desde API
    async function cargarDocentes() {
        try {
            const response = await axios.get(`${API_BASE}/docentes`);
            docentes = response.data;
            renderizarDocentes();
            poblarSelectDocentes();
        } catch (error) {
            console.error('Error al cargar docentes:', error);
            Swal.fire('Error', 'No se pudo cargar la lista de docentes', 'error');
        }
    }

    // Cargar cursos desde API
    async function cargarCursos() {
        try {
            const response = await axios.get(`${API_BASE}/cursos`);
            cursos = response.data;
            poblarSelectCursos();
        } catch (error) {
            console.error('Error al cargar cursos:', error);
        }
    }

    // Cargar grupos desde API
    async function cargarGrupos() {
        try {
            const response = await axios.get(`${API_BASE}/grupos`);
            grupos = response.data;
            poblarSelectGrupos();
        } catch (error) {
            console.error('Error al cargar grupos:', error);
        }
    }

    // Renderizar tarjetas de docentes
    function renderizarDocentes() {
        docentesContainer.innerHTML = '';

        const docenteFiltro = filterDocente.value;
        const estadoFiltro = filterEstado.value;

        docentes.forEach(docente => {
            if (docenteFiltro && docente.id != docenteFiltro) return;
            if (estadoFiltro === 'disponible' && docente.cargaActual >= docente.cargaMaxima) return;
            if (estadoFiltro === 'completo' && docente.cargaActual < docente.cargaMaxima) return;

            const porcentajeCarga = Math.min(100, Math.round((docente.cargaActual / docente.cargaMaxima) * 100));
            const horasRestantes = docente.cargaMaxima - docente.cargaActual;

            let badgeClass = 'badge-available';
            let badgeText = `${horasRestantes}h disponibles`;

            if (horasRestantes <= 0) {
                badgeClass = 'badge-full';
                badgeText = 'Carga completa';
            }

            const docenteHTML = `
                    <div class="col-md-6 mb-4">
                        <div class="card teacher-card h-100">
                            <div class="card-header d-flex justify-content-between align-items-center">
                                <h5 class="mb-0">${docente.nombre} ${docente.apellido}</h5>
                                <span class="badge ${badgeClass}">${badgeText}</span>
                            </div>
                            <div class="card-body">
                                <div class="d-flex justify-content-between mb-3">
                                    <div>
                                        <strong>Carga actual:</strong> ${docente.cargaActual}h / ${docente.cargaMaxima}h
                                    </div>
                                    <button class="btn btn-sm btn-outline-info"
                                            data-bs-toggle="modal"
                                            data-bs-target="#horarioModal"
                                            data-docente-id="${docente.id}">
                                        <i class="fas fa-calendar-alt me-1"></i> Ver horario
                                    </button>
                                </div>
                                <div class="progress mb-3">
                                    <div class="progress-bar" role="progressbar"
                                         style="width: ${porcentajeCarga}%"
                                         aria-valuenow="${porcentajeCarga}"
                                         aria-valuemin="0"
                                         aria-valuemax="100"></div>
                                </div>
                                <h6>Cursos asignados:</h6>
                                <div id="cursos-${docente.id}" class="list-group">
                                    ${docente.cursosAsignados.map(curso => `
                                        <div class="list-group-item course-item assigned-course d-flex justify-content-between align-items-center">
                                            <div>
                                                <strong>${curso.nombre}</strong> - Grupo ${curso.grupo}<br>
                                                <small>${curso.horario}</small>
                                            </div>
                                            <button class="btn btn-sm btn-danger btn-eliminar-asignacion"
                                                    data-asignacion-id="${curso.id}">
                                                <i class="fas fa-trash-alt"></i>
                                            </button>
                                        </div>
                                    `).join('')}
                                </div>
                            </div>
                        </div>
                    </div>
                `;

            docentesContainer.innerHTML += docenteHTML;
        });

        // Agregar eventos a los botones de eliminar
        document.querySelectorAll('.btn-eliminar-asignacion').forEach(btn => {
            btn.addEventListener('click', eliminarAsignacion);
        });

        // Agregar eventos a los botones de horario
        document.querySelectorAll('[data-bs-target="#horarioModal"]').forEach(btn => {
            btn.addEventListener('click', cargarHorarioDocente);
        });
    }

    // Poblar select de docentes en filtro y modal
    function poblarSelectDocentes() {
        filterDocente.innerHTML = '<option value="">Todos los docentes</option>';
        docenteSelect.innerHTML = '<option value="">Seleccionar docente...</option>';

        docentes.forEach(docente => {
            const option = document.createElement('option');
            option.value = docente.id;
            option.textContent = `${docente.nombre} ${docente.apellido}`;

            filterDocente.appendChild(option.cloneNode(true));
            docenteSelect.appendChild(option);
        });
    }

    // Poblar select de cursos
    function poblarSelectCursos() {
        cursoSelect.innerHTML = '<option value="">Seleccionar curso...</option>';

        cursos.forEach(curso => {
            const option = document.createElement('option');
            option.value = curso.id;
            option.textContent = curso.nombre;
            cursoSelect.appendChild(option);
        });
    }

    // Poblar select de grupos
    function poblarSelectGrupos() {
        grupoSelect.innerHTML = '<option value="">Seleccionar grupo...</option>';

        grupos.forEach(grupo => {
            const option = document.createElement('option');
            option.value = grupo.id;
            option.textContent = grupo.nombre;
            grupoSelect.appendChild(option);
        });
    }

    // Cargar horario de docente para modal
    async function cargarHorarioDocente(event) {
        const docenteId = event.currentTarget.getAttribute('data-docente-id');
        const horarioTable = document.querySelector('#horarioTable tbody');
        horarioTable.innerHTML = '';

        try {
            const response = await axios.get(`${API_BASE}/docentes/${docenteId}/horario`);
            horarios[docenteId] = response.data;

            // Generar filas de horas
            for (let hora = 7; hora <= 21; hora++) {
                const row = document.createElement('tr');
                row.innerHTML = `<td>${hora}:00 - ${hora+1}:00</td>
                                     <td></td><td></td><td></td><td></td><td></td><td></td>`;
                horarioTable.appendChild(row);
            }

            // Llenar horarios
            horarios[docenteId].forEach(asignacion => {
                const horaInicio = parseInt(asignacion.horaInicio.split(':')[0]);
                const horas = asignacion.horasSemanales;
                const diaIndex = ['LUNES', 'MARTES', 'MIÉRCOLES', 'JUEVES', 'VIERNES', 'SÁBADO']
                    .indexOf(asignacion.dia) + 1;

                for (let h = 0; h < horas; h++) {
                    const horaFila = horaInicio + h - 7;
                    if (horaFila >= 0 && horaFila <= 14) {
                        const cell = horarioTable.rows[horaFila].cells[diaIndex];
                        cell.innerHTML = `
                                <div class="bg-primary text-white p-1 rounded">
                                    <strong>${asignacion.curso}</strong><br>
                                    <small>Grupo ${asignacion.grupo}</small>
                                </div>
                            `;
                    }
                }
            });
        } catch (error) {
            console.error('Error al cargar horario:', error);
            horarioTable.innerHTML = '<tr><td colspan="7" class="text-center">Error al cargar el horario</td></tr>';
        }
    }

    // Verificar conflictos de horario
    async function verificarConflictoHorario() {
        const docenteId = docenteSelect.value;
        const dia = diaSelect.value;
        const hora = horaInicio.value;
        const horas = parseInt(horasInput.value);

        if (!docenteId || !dia || !hora || !horas) {
            conflictAlert.classList.add('d-none');
            return false;
        }

        try {
            const response = await axios.post(`${API_BASE}/asignaciones/verificar-conflicto`, {
                docenteId,
                dia,
                horaInicio: hora,
                horas
            });

            if (response.data.conflicto) {
                conflictMessage.textContent = response.data.mensaje;
                conflictAlert.classList.remove('d-none');
                return true;
            } else {
                conflictAlert.classList.add('d-none');
                return false;
            }
        } catch (error) {
            console.error('Error al verificar conflicto:', error);
            conflictAlert.classList.add('d-none');
            return false;
        }
    }

    // Guardar nueva asignación
    async function guardarAsignacion() {
        const conflicto = await verificarConflictoHorario();
        if (conflicto) return;

        const asignacion = {
            docenteId: docenteSelect.value,
            cursoId: cursoSelect.value,
            grupoId: grupoSelect.value,
            horasSemanales: parseInt(horasInput.value),
            dia: diaSelect.value,
            horaInicio: horaInicio.value
        };

        try {
            const response = await axios.post(`${API_BASE}/asignaciones`, asignacion);
            Swal.fire('Éxito', 'Asignación creada correctamente', 'success');

            // Actualizar datos
            cargarDocentes();

            // Cerrar modal y resetear formulario
            const modal = bootstrap.Modal.getInstance(document.getElementById('asignarModal'));
            modal.hide();
            document.getElementById('asignacionForm').reset();
        } catch (error) {
            console.error('Error al guardar asignación:', error);
            Swal.fire('Error', 'No se pudo crear la asignación', 'error');
        }
    }

    // Eliminar asignación
    async function eliminarAsignacion(event) {
        const asignacionId = event.currentTarget.getAttribute('data-asignacion-id');

        try {
            const result = await Swal.fire({
                title: '¿Eliminar asignación?',
                text: 'Esta acción no se puede deshacer',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: 'Sí, eliminar'
            });

            if (result.isConfirmed) {
                await axios.delete(`${API_BASE}/asignaciones/${asignacionId}`);
                Swal.fire('Eliminada', 'La asignación ha sido eliminada', 'success');
                cargarDocentes();
            }
        } catch (error) {
            console.error('Error al eliminar asignación:', error);
            Swal.fire('Error', 'No se pudo eliminar la asignación', 'error');
        }
    }

    // Event Listeners
    filterDocente.addEventListener('change', renderizarDocentes);
    filterEstado.addEventListener('change', renderizarDocentes);
    btnGuardarAsignacion.addEventListener('click', guardarAsignacion);

    // Verificar conflicto cuando cambian los campos relevantes
    [docenteSelect, diaSelect, horaInicio, horasInput].forEach(element => {
        element.addEventListener('change', verificarConflictoHorario);
    });
</script>
</body>
</html>