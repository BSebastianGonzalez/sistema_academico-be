<%--
  Created by IntelliJ IDEA.
  User: diego
  Date: 29/05/2025
  Time: 9:19 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<script>
  window.contextPath = '${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/estudianteHeader.js"></script>
<style>
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
</style>
<header class="student-header">
  <div class="header-content">
    <a href="#" class="logo">
      <i class="fas fa-user-graduate"></i>
      <span>Panel de Estudiante</span>
    </a>
    <div class="user-menu">
      <div class="user-info">
        <div class="user-name">${estudiante.nombre}</div>
        <div class="user-role">Estudiante - ${estudiante.programa}</div>
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

