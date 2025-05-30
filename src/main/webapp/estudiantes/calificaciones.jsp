<%--
  Created by IntelliJ IDEA.
  User: diego
  Date: 29/05/2025
  Time: 9:50 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%-- calificaciones.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Calificaciones</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <style>
    /* ESTILOS GENERALES */
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f8f9fa;
      color: #343a40;
      margin: 0;
      padding: 0;
      min-height: 100vh;
    }

    /* HEADER */
    .student-header {
      background: linear-gradient(90deg, #3498db 0%, #2980b9 100%);
      color: white;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
      position: fixed;
      top: 0;
      width: 100%;
      z-index: 1000;
      height: 60px;
    }

    .header-content {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 0 1.5rem;
      height: 100%;
    }

    .logo {
      display: flex;
      align-items: center;
      font-weight: 700;
      font-size: 1.5rem;
      text-decoration: none;
      color: white;
    }

    .logo i {
      margin-right: 10px;
      font-size: 1.8rem;
    }

    .user-menu {
      display: flex;
      align-items: center;
      gap: 15px;
    }

    .user-info {
      color: white;
      text-align: right;
    }

    .user-name {
      font-weight: 600;
      font-size: 1rem;
      margin: 0;
    }

    .user-role {
      font-size: 0.85rem;
      opacity: 0.8;
      margin: 0;
    }

    .dropdown-menu {
      min-width: 200px;
    }

    /* SIDEBAR */
    .sidebar {
      width: 250px;
      background-color: white;
      position: fixed;
      left: 0;
      top: 60px;
      bottom: 0;
      box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
      overflow-y: auto;
      z-index: 900;
      padding-top: 20px;
    }

    .sidebar-menu {
      list-style: none;
      padding: 0;
      margin: 0;
    }

    .menu-title {
      padding: 15px 20px 5px;
      font-size: 0.9rem;
      text-transform: uppercase;
      color: #6c757d;
      font-weight: 600;
    }

    .menu-item {
      padding: 0;
    }

    .menu-link {
      display: flex;
      align-items: center;
      padding: 12px 20px;
      color: #495057;
      text-decoration: none;
      transition: all 0.2s;
      border-left: 4px solid transparent;
    }

    .menu-link:hover {
      background-color: #e9ecef;
      color: #3498db;
    }

    .menu-link.active {
      background-color: #e3f2fd;
      color: #3498db;
      border-left: 4px solid #3498db;
      font-weight: 600;
    }

    .menu-link i {
      width: 24px;
      text-align: center;
      margin-right: 12px;
      font-size: 1.1rem;
    }

    /* MAIN CONTENT */
    .main-content {
      margin-left: 250px;
      padding: 20px;
      transition: all 0.3s ease;
      margin-top: 60px;
    }

    .page-header {
      margin-bottom: 25px;
      padding-bottom: 15px;
      border-bottom: 1px solid #eaeaea;
    }

    .page-title {
      font-size: 1.8rem;
      font-weight: 600;
      color: #2c3e50;
      margin: 0;
    }

    .breadcrumb {
      background: none;
      padding: 0;
      margin: 10px 0 0;
      font-size: 0.9rem;
    }

    .breadcrumb-item a {
      color: #6c757d;
      text-decoration: none;
    }

    /* MODULE CARDS */
    .module-cards {
      display: grid;
      grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
      gap: 20px;
      margin-top: 20px;
    }

    .module-card {
      background: white;
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
      transition: all 0.3s ease;
      overflow: hidden;
      border: 1px solid #eaeaea;
    }

    .module-card:hover {
      transform: translateY(-5px);
      box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
    }

    .card-header {
      background: linear-gradient(135deg, #3498db 0%, #2980b9 100%);
      color: white;
      padding: 15px 20px;
      position: relative;
    }

    .card-icon {
      position: absolute;
      top: -20px;
      right: 20px;
      width: 60px;
      height: 60px;
      background: rgba(255, 255, 255, 0.2);
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 1.8rem;
    }

    .card-title {
      font-size: 1.3rem;
      font-weight: 600;
      margin: 0;
      padding-right: 70px;
    }

    .card-body {
      padding: 20px;
    }

    .module-features {
      list-style: none;
      padding: 0;
      margin: 0;
    }

    .module-features li {
      padding: 8px 0;
      border-bottom: 1px solid #f1f2f6;
      display: flex;
      align-items: center;
    }

    .module-features li:last-child {
      border-bottom: none;
    }

    .module-features li i {
      color: #28a745;
      margin-right: 10px;
      font-size: 0.9rem;
    }

    .card-footer {
      background-color: #f8f9fa;
      padding: 15px 20px;
      text-align: center;
      border-top: 1px solid #eaeaea;
    }

    .btn-module {
      background-color: #3498db;
      color: white;
      border: none;
      padding: 8px 20px;
      border-radius: 5px;
      font-weight: 500;
      transition: all 0.3s;
      text-decoration: none;
      display: inline-block;
    }

    .btn-module:hover {
      background-color: #2980b9;
      color: white;
      transform: scale(1.05);
    }

    /* RESPONSIVE */
    @media (max-width: 992px) {
      .sidebar {
        width: 70px;
      }

      .menu-title, .menu-text {
        display: none;
      }

      .menu-link i {
        margin-right: 0;
        font-size: 1.3rem;
      }

      .menu-link {
        justify-content: center;
        padding: 15px;
      }

      .main-content {
        margin-left: 70px;
      }
    }

    @media (max-width: 768px) {
      .module-cards {
        grid-template-columns: 1fr;
      }

      .header-content {
        flex-direction: column;
        padding: 10px;
        height: auto;
      }

      .user-menu {
        margin-top: 10px;
      }

      .sidebar {
        width: 100%;
        height: auto;
        position: relative;
        top: 0;
      }

      .main-content {
        margin-left: 0;
        margin-top: 20px;
      }
    }

    /* Estilos adicionales para calificaciones */
    .materia-header {
      background-color: #e0e5ff;
      font-weight: bold;
    }
    .nota-final {
      font-weight: bold;
      background-color: #d1e7dd;
    }
    .card-calificaciones {
      border-radius: 10px;
      box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
    }
  </style>
</head>
<body>
<jsp:include page="../partials/estudianteHeader.jsp" />

<!-- Contenido principal -->
<main class="main-content">

  <div class="card card-calificaciones mb-4">
    <div class="card-header py-3 bg-primary text-white">
      <h6 class="m-0 font-weight-bold">Calificaciones del Semestre</h6>
    </div>
    <div class="card-body">
      <div class="form-group mb-4">
        <label for="selectSemestre">Seleccionar Semestre:</label>
        <select class="form-select" id="selectSemestre">
          <option value="">Cargando semestres...</option>
        </select>
      </div>

      <div class="table-responsive">
        <table class="table table-bordered" id="tablaCalificaciones">
          <thead class="table-light">
          <tr>
            <th>Materia</th>
            <th>Evaluación</th>
            <th>Porcentaje</th>
            <th>Nota</th>
            <th>Fecha</th>
          </tr>
          </thead>
          <tbody id="cuerpoTabla">
          <tr>
            <td colspan="5" class="text-center">Seleccione un semestre</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
  window.ESTUDIANTE_ID = ${sessionScope.estudiante.id};
</script>
<script src="${pageContext.request.contextPath}/js/estudiantes/calificaciones.js"></script>
</body>
</html>

