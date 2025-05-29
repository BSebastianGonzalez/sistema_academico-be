<%--
  Created by IntelliJ IDEA.
  User: diego
  Date: 29/05/2025
  Time: 2:18 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Gestión de Asignaturas</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
  <a class="navbar-brand" href="index.jsp">ESTUDIANTES</a>
  <ul class="navbar-nav ml-auto">
    <li class="nav-item"><a class="nav-link" href="cursos.jsp">Cursos</a></li>
    <li class="nav-item active"><a class="nav-link" href="asignaturas.jsp">Asignaturas</a></li>
  </ul>
</nav>

<div class="container mt-4">
  <h2>Asignaturas</h2>
  <button class="btn btn-primary mb-3" onclick="openForm()">Nueva Asignatura</button>

  <table class="table table-striped" id="tablaAsign">
    <thead class="thead-light">
    <tr>
      <th>ID</th>
      <th>Nombre</th>
      <th>Contenido</th>
      <th>Objetivos</th>
      <th>Competencias</th>
      <th>Acciones</th>
    </tr>
    </thead>
    <tbody></tbody>
  </table>
</div>

<!-- Modal de alta/edición -->
<div class="modal fade" id="asignModal" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <form id="formAsign" onsubmit="saveAsign(event)">
        <div class="modal-header">
          <h5 class="modal-title" id="modalTitle">Nueva Asignatura</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <input type="hidden" id="asignId"/>
          <div class="form-row">
            <div class="form-group col-md-6">
              <label>Nombre</label>
              <input type="text" class="form-control" id="nombre" required/>
            </div>
            <div class="form-group col-md-6">
              <label>Contenido</label>
              <input type="text" class="form-control" id="contenido" required/>
            </div>
          </div>
          <div class="form-group">
            <label>Objetivos</label>
            <textarea class="form-control" id="objetivos" rows="2" required></textarea>
          </div>
          <div class="form-group">
            <label>Competencias</label>
            <textarea class="form-control" id="competencias" rows="2" required></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-success">Guardar</button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXd89h..." crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script src="js/asign.js"></script>
</body>
</html>

