<%--
  Created by IntelliJ IDEA.
  User: diego
  Date: 29/05/2025
  Time: 8:25 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Estudiante</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estudiantes/login.css">
</head>
<body>
<div class="login-container">
    <div class="login-card">
        <div class="card-body">
            <div class="text-center mb-4">
                <i class="fas fa-user-graduate fa-3x text-primary"></i>
                <h2 class="mt-3">Acceso Estudiantes</h2>
            </div>

            <form id="login-form">
                <div class="mb-3">
                    <label for="codigo" class="form-label">Código de Estudiante</label>
                    <input type="text" class="form-control" id="codigo" placeholder="Ingrese su código" required>
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" placeholder="Ingrese su contraseña" required>
                </div>

                <button type="submit" class="btn btn-primary w-100">Iniciar Sesión</button>
            </form>

            <div class="mt-3 text-center">
                <a href="${pageContext.request.contextPath}/index.jsp" class="text-primary">
                    <i class="fas fa-arrow-left"></i> Volver al inicio
                </a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/estudiantes/login.js"></script>
</body>
</html>
