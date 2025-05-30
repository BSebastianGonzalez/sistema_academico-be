<%--
  Created by IntelliJ IDEA.
  User: diego
  Date: 29/05/2025
  Time: 6:30 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Docentes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/docentes/gestionDocentes.js" defer></script>
</head>
<body>
<jsp:include page="../partials/navbar.jsp" />

<div class="container mt-4">
    <h2>Gestión de Docentes</h2>
    <button class="btn btn-primary mb-3" id="btnNuevoDocente">Nuevo Docente</button>

    <table class="table table-striped" id="tablaDocentes">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Especialidad</th>
            <th>Email</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>

<!-- Modal Docente -->
<div class="modal fade" id="modalDocente" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Datos del Docente</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                <form id="formDocente">
                    <input type="hidden" id="docenteId">
                    <div class="mb-3">
                        <label class="form-label">Nombre Completo</label>
                        <input type="text" class="form-control" id="nombre" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Especialidad</label>
                        <input type="text" class="form-control" id="especialidad" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Teléfono</label>
                        <input type="tel" class="form-control" id="telefono">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-primary" id="btnGuardarDocente">Guardar</button>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>