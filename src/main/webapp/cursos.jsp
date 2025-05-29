<%--
  Created by IntelliJ IDEA.
  User: diego
  Date: 29/05/2025
  Time: 2:17 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Gestión de Cursos</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-4">
  <h2>Cursos</h2>
  <button class="btn btn-primary mb-2" onclick="openForm()">Nuevo Curso</button>

  <table class="table table-bordered" id="tablaCursos">
    <thead>
    <tr>
      <th>ID</th><th>Nombre</th><th>Descripción</th><th>Acciones</th>
    </tr>
    </thead>
    <tbody></tbody>
  </table>
</div>

<!-- Modal formulario -->
<div class="modal" tabindex="-1" role="dialog" id="cursoModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <form id="formCurso" onsubmit="saveCurso(event)">
        <div class="modal-header">
          <h5 class="modal-title" id="modalTitle">Nuevo Curso</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body">
          <input type="hidden" id="cursoId"/>
          <div class="form-group">
            <label>Nombre</label>
            <input type="text" class="form-control" id="nombre" required/>
          </div>
          <div class="form-group">
            <label>Descripción</label>
            <textarea class="form-control" id="descripcion" required></textarea>
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

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script src="js/cursos.js"></script>
</body>
</html>
