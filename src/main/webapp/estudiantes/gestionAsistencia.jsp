<%--
  Created by IntelliJ IDEA.
  User: diego
  Date: 29/05/2025
  Time: 6:29 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Asistencia</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/asistencia.css">
</head>
<body>
<jsp:include page="../partials/estudianteHeader.jsp" />

<main class="container mt-4">
    <div class="row mb-4">
        <div class="col-md-12">
            <h2>Registro de Asistencia</h2>
            <nav>
                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                    <button class="nav-link active" id="nav-resumen-tab" data-bs-toggle="tab"
                            data-bs-target="#nav-resumen" type="button" role="tab">
                        Resumen
                    </button>
                    <button class="nav-link" id="nav-detalle-tab" data-bs-toggle="tab"
                            data-bs-target="#nav-detalle" type="button" role="tab">
                        Detalle por Curso
                    </button>
                </div>
            </nav>
        </div>
    </div>

    <div class="tab-content" id="nav-tabContent">
        <!-- Resumen de asistencia -->
        <div class="tab-pane fade show active" id="nav-resumen" role="tabpanel">
            <div class="row">
                <c:forEach items="${resumenAsistencia}" var="curso">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100">
                            <div class="card-header bg-primary text-white">
                                <h5>${curso.nombre}</h5>
                            </div>
                            <div class="card-body text-center">
                                <div class="row">
                                    <div class="col-6">
                                        <div class="asistencia-indicator asistencia-presente">
                                            <h3 class="mb-0">${curso.asistencias}</h3>
                                            <small>Asistencias</small>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="asistencia-indicator asistencia-ausente">
                                            <h3 class="mb-0">${curso.ausencias}</h3>
                                            <small>Ausencias</small>
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-3">
                                    <div class="progress" style="height: 20px;">
                                        <div class="progress-bar bg-success" role="progressbar"
                                             style="width: ${curso.porcentajeAsistencia}%"
                                             aria-valuenow="${curso.porcentajeAsistencia}"
                                             aria-valuemin="0" aria-valuemax="100">
                                                ${curso.porcentajeAsistencia}%
                                        </div>
                                    </div>
                                    <small class="text-muted">Porcentaje de asistencia</small>
                                </div>
                            </div>
                            <div class="card-footer bg-transparent">
                                <button class="btn btn-sm btn-outline-primary w-100 btn-detalle-curso"
                                        data-curso-id="${curso.id}">
                                    Ver Detalle
                                </button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Detalle por curso -->
        <div class="tab-pane fade" id="nav-detalle" role="tabpanel">
            <div class="row mb-3">
                <div class="col-md-4">
                    <select class="form-select" id="selectCurso">
                        <option value="">Seleccione un curso</option>
                        <c:forEach items="${resumenAsistencia}" var="curso">
                            <option value="${curso.id}">${curso.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="fechaInicio" placeholder="Fecha inicio">
                </div>
                <div class="col-md-4">
                    <input type="text" class="form-control" id="fechaFin" placeholder="Fecha fin">
                </div>
            </div>

            <div class="card">
                <div class="card-header">
                    <h5 id="detalleCursoTitulo">Seleccione un curso para ver el detalle</h5>
                </div>
                <div class="card-body">
                    <table id="detalleAsistenciaTable" class="table table-striped" style="width:100%">
                        <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Estado</th>
                            <th>Justificación</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Se llena dinámicamente -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</main>

<!-- Modal para justificar ausencia -->
<div class="modal fade" id="justificarModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Justificar Ausencia</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="justificacionForm">
                    <input type="hidden" id="registroAsistenciaId">
                    <div class="mb-3">
                        <label for="tipoJustificacion" class="form-label">Tipo de justificación</label>
                        <select class="form-select" id="tipoJustificacion" required>
                            <option value="">Seleccione...</option>
                            <option value="MEDICA">Médica</option>
                            <option value="PERSONAL">Personal</option>
                            <option value="ACADEMICA">Académica</option>
                            <option value="OTRA">Otra</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="comentario" class="form-label">Comentario</label>
                        <textarea class="form-control" id="comentario" rows="3" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="archivoJustificacion" class="form-label">Documento soporte (opcional)</label>
                        <input class="form-control" type="file" id="archivoJustificacion">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary" id="btnEnviarJustificacion">Enviar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/es.js"></script>
<script src="${pageContext.request.contextPath}/js/gestionAsistencia.js"></script>
</body>
</html>