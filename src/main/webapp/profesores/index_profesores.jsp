<%--
  Created by IntelliJ IDEA.
  User: diego
  Date: 29/05/2025
  Time: 7:42 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Panel de Docente</title>
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
    .teacher-header {
      background: linear-gradient(90deg, #27ae60 0%, #219653 100%);
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
      color: #27ae60;
    }

    .menu-link.active {
      background-color: #e8f5e9;
      color: #27ae60;
      border-left: 4px solid #27ae60;
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
      background: linear-gradient(135deg, #27ae60 0%, #219653 100%);
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
      background-color: #27ae60;
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
      background-color: #219653;
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
  </style>
</head>
<body>
<!-- Header -->
<header class="teacher-header">
  <div class="header-content">
    <a href="#" class="logo">
      <i class="fas fa-chalkboard-teacher"></i>
      <span>Panel de Docente</span>
    </a>
    <div class="user-menu">
      <div class="user-info">
        <div class="user-name">Prof. María Rodríguez</div>
        <div class="user-role">Departamento de Matemáticas</div>
      </div>
      <div class="dropdown">
        <button class="btn btn-light btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown">
          <i class="fas fa-user-circle"></i>
        </button>
        <ul class="dropdown-menu dropdown-menu-end">
          <li><a class="dropdown-item" href="#"><i class="fas fa-user me-2"></i>Mi Perfil</a></li>
          <li><a class="dropdown-item" href="#"><i class="fas fa-cog me-2"></i>Configuración</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="#" id="logout-btn"><i class="fas fa-sign-out-alt me-2"></i>Cerrar sesión</a></li>
        </ul>
      </div>
    </div>
  </div>
</header>

<!-- Menú lateral -->
<nav class="sidebar">
  <ul class="sidebar-menu">
    <li class="menu-title">Menú Principal</li>
    <li class="menu-item">
      <a href="#" class="menu-link active" data-target="dashboard">
        <i class="fas fa-home"></i>
        <span>Inicio</span>
      </a>
    </li>
    <li class="menu-item">
      <a href="#" class="menu-link" data-target="cursos">
        <i class="fas fa-book"></i>
        <span>Asignación de Cursos</span>
      </a>
    </li>
    <li class="menu-item">
      <a href="#" class="menu-link" data-target="evaluaciones">
        <i class="fas fa-clipboard-list"></i>
        <span>Evaluaciones</span>
      </a>
    </li>
    <li class="menu-item">
      <a href="#" class="menu-link" data-target="calificaciones">
        <i class="fas fa-chart-line"></i>
        <span>Calificaciones</span>
      </a>
    </li>
  </ul>
</nav>

<!-- Contenido principal -->
<main class="main-content" id="main-content">
  <div class="page-header">
    <h1 class="page-title">Bienvenido, Prof. María Rodríguez</h1>
    <nav aria-label="breadcrumb">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="#"><i class="fas fa-home"></i> Inicio</a></li>
      </ol>
    </nav>
  </div>

  <!-- Módulos del sistema -->
  <div class="module-cards">
    <div class="module-card">
      <div class="card-header">
        <div class="card-icon">
          <i class="fas fa-book"></i>
        </div>
        <h3 class="card-title">Gestión de Cursos</h3>
      </div>
      <div class="card-body">
        <ul class="module-features">
          <li><i class="fas fa-check-circle text-success"></i> Asignación de cursos</li>
          <li><i class="fas fa-check-circle text-success"></i> Planificación de contenidos</li>
        </ul>
      </div>
      <div class="card-footer">
        <a href="${pageContext.request.contextPath}/docentes/asignacionCursos.jsp"
           class="btn btn-module" id="cursos-btn">
          Acceder
        </a>
      </div>
    </div>

    <div class="module-card">
      <div class="card-header">
        <div class="card-icon">
          <i class="fas fa-clipboard-list"></i>
        </div>
        <h3 class="card-title">Gestión de Evaluaciones</h3>
      </div>
      <div class="card-body">
        <ul class="module-features">
          <li><i class="fas fa-check-circle text-success"></i> Crear evaluaciones</li>
          <li><i class="fas fa-check-circle text-success"></i> Programar exámenes</li>
        </ul>
      </div>
      <div class="card-footer">
        <a href="${pageContext.request.contextPath}/docentes/gestionEvaluaciones.jsp"
           class="btn btn-module" id="evaluaciones-btn">
          Acceder
        </a>
      </div>
    </div>
  </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script></script>
</body>
</html>