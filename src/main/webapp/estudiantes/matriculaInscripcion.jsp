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
    <title>Matrícula Académica</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/matricula.css">
</head>
<body>
<jsp:include page="../partials/estudianteHeader.jsp" />

<main class="container mt-4">
    <div class="row">
        <div class="col-md-12">
            <h2 class="mb-4">Proceso de Matrícula</h2>
            <div class="alert alert-info">
                Período activo: <strong>${periodoAcademico.nombre}</strong> |
                Fecha límite: <strong>${periodoAcademico.fechaLimite}</strong>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header bg-primary text-white">
                    <h4>Cursos Disponibles</h4>
                </div>
                <div class="card-body">
                    <table class="table table-hover" id="cursosTable">
                        <thead>
                        <tr>
                            <th>Código</th>
                            <th>Nombre</th>
                            <th>Créditos</th>
                            <th>Horario</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${cursosDisponibles}" var="curso">
                            <tr data-curso-id="${curso.id}">
                                <td>${curso.codigo}</td>
                                <td>${curso.nombre}</td>
                                <td>${curso.creditos}</td>
                                <td>${curso.horario}</td>
                                <td>
                                    <button class="btn btn-sm btn-success btn-inscribir"
                                        ${matriculados.contains(curso.id) ? 'disabled' : ''}>
                                            ${matriculados.contains(curso.id) ? 'Inscrito' : 'Inscribir'}
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card">
                <div class="card-header bg-success text-white">
                    <h4>Mi Matrícula</h4>
                </div>
                <div class="card-body">
                    <ul class="list-group" id="cursosMatriculados">
                        <c:forEach items="${cursosMatriculados}" var="curso">
                            <li class="list-group-item d-flex justify-content-between align-items-center">
                                    ${curso.nombre}
                                <button class="btn btn-sm btn-danger btn-eliminar" data-curso-id="${curso.id}">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="mt-3">
                        <p class="text-end"><strong>Total créditos: <span id="totalCreditos">${totalCreditos}</span></strong></p>
                        <button class="btn btn-primary w-100" id="confirmarMatricula"
                        ${cursosMatriculados.isEmpty() ? 'disabled' : ''}>
                            Confirmar Matrícula
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="${pageContext.request.contextPath}/js/matricula.js"></script>
</body>
</html>