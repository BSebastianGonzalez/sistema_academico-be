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
    <title>Historial Académico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/historial.css">
</head>
<body>
<jsp:include page="../partials/estudianteHeader.jsp" />

<main class="container mt-4">
    <div class="row mb-4">
        <div class="col-md-12">
            <h2>Historial Académico</h2>
            <nav>
                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                    <button class="nav-link active" id="nav-cursos-tab" data-bs-toggle="tab"
                            data-bs-target="#nav-cursos" type="button" role="tab">
                        Cursos
                    </button>
                    <button class="nav-link" id="nav-promedios-tab" data-bs-toggle="tab"
                            data-bs-target="#nav-promedios" type="button" role="tab">
                        Promedios
                    </button>
                    <button class="nav-link" id="nav-constancias-tab" data-bs-toggle="tab"
                            data-bs-target="#nav-constancias" type="button" role="tab">
                        Constancias
                    </button>
                </div>
            </nav>
        </div>
    </div>

    <div class="tab-content" id="nav-tabContent">
        <!-- Tabla de cursos -->
        <div class="tab-pane fade show active" id="nav-cursos" role="tabpanel">
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col-md-6">
                            <h5>Mis Cursos</h5>
                        </div>
                        <div class="col-md-6 text-end">
                            <button class="btn btn-sm btn-primary" id="btnExportarPDF">
                                <i class="fas fa-file-pdf"></i> Exportar PDF
                            </button>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <table id="cursosTable" class="table table-striped" style="width:100%">
                        <thead>
                        <tr>
                            <th>Periodo</th>
                            <th>Código</th>
                            <th>Curso</th>
                            <th>Créditos</th>
                            <th>Nota</th>
                            <th>Estado</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${historial}" var="curso">
                            <tr>
                                <td>${curso.periodo}</td>
                                <td>${curso.codigo}</td>
                                <td>${curso.nombre}</td>
                                <td>${curso.creditos}</td>
                                <td class="${curso.nota >= 3.0 ? 'text-success' : 'text-danger'}">
                                    <strong>${curso.nota}</strong>
                                </td>
                                <td>
                                            <span class="badge bg-${curso.aprobado ? 'success' : 'danger'}">
                                                    ${curso.aprobado ? 'Aprobado' : 'Reprobado'}
                                            </span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Promedios -->
        <div class="tab-pane fade" id="nav-promedios" role="tabpanel">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h5>Promedio Ponderado</h5>
                        </div>
                        <div class="card-body text-center">
                            <h1 class="display-1 text-primary">${promedioPonderado}</h1>
                            <p class="text-muted">Promedio acumulado</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header bg-success text-white">
                            <h5>Créditos Aprobados</h5>
                        </div>
                        <div class="card-body text-center">
                            <h1 class="display-1 text-success">${creditosAprobados}</h1>
                            <p class="text-muted">de ${creditosTotales} créditos</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mt-4">
                <div class="card-header">
                    <h5>Evolución de Promedio</h5>
                </div>
                <div class="card-body">
                    <canvas id="promedioChart" height="300"></canvas>
                </div>
            </div>
        </div>

        <!-- Constancias -->
        <div class="tab-pane fade" id="nav-constancias" role="tabpanel">
            <div class="card">
                <div class="card-header">
                    <h5>Constancias Disponibles</h5>
                </div>
                <div class="card-body">
                    <div class="row">
                        <c:forEach items="${constancias}" var="constancia">
                            <div class="col-md-4 mb-3">
                                <div class="card h-100">
                                    <div class="card-body text-center">
                                        <i class="fas fa-file-alt fa-3x mb-3 text-primary"></i>
                                        <h5>${constancia.nombre}</h5>
                                        <p class="text-muted">${constancia.descripcion}</p>
                                    </div>
                                    <div class="card-footer bg-transparent">
                                        <button class="btn btn-primary w-100 btn-descargar"
                                                data-tipo="${constancia.tipo}">
                                            <i class="fas fa-download"></i> Descargar
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf-autotable/3.5.25/jspdf.plugin.autotable.min.js"></script>
<script src="${pageContext.request.contextPath}/js/historial.js"></script>
</body>
</html>
