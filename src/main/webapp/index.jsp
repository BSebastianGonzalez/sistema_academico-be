<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sistema de Gestión Académica</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .welcome-container {
            height: 100vh;
            background: linear-gradient(135deg, #2c3e50 0%, #3498db 100%);
            display: flex;
            align-items: center;
        }

        .card-option {
            transition: all 0.3s;
            height: 100%;
            border-radius: 15px;
            overflow: hidden;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            border: none;
        }

        .card-option:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.3);
        }

        .student-card {
            background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);
        }

        .teacher-card {
            background: linear-gradient(135deg, #fa709a 0%, #fee140 100%);
        }

        .system-logo {
            font-size: 3.5rem;
            margin-bottom: 20px;
            color: white;
        }

        .welcome-text {
            text-shadow: 0 2px 4px rgba(0,0,0,0.3);
        }
    </style>
</head>
<body>
<div class="welcome-container">
    <div class="container">
        <div class="row justify-content-center mb-5">
            <div class="col-md-8 text-center text-white">
                <div class="system-logo">
                    <i class="fas fa-graduation-cap"></i>
                </div>
                <h1 class="display-4 fw-bold welcome-text">Sistema de Gestión Académica</h1>
                <p class="lead welcome-text">Plataforma integral para la administración de instituciones educativas</p>
            </div>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-5 mb-4">
                <div class="card-option student-card">
                    <div class="card-body text-center p-5">
                        <i class="fas fa-user-graduate fa-5x text-white mb-4"></i>
                        <h2 class="text-white">Acceso Estudiantes</h2>
                        <p class="text-white">Gestión de matrículas, historial académico y asistencia</p>
                        <a href="${pageContext.request.contextPath}/estudiantes/index_estudiantes.jsp"
                           class="btn btn-light btn-lg mt-3">
                            Entrar como Estudiante
                        </a>
                    </div>
                </div>
            </div>

            <div class="col-md-5 mb-4">
                <div class="card-option teacher-card">
                    <div class="card-body text-center p-5">
                        <i class="fas fa-chalkboard-teacher fa-5x text-white mb-4"></i>
                        <h2 class="text-white">Acceso Docentes</h2>
                        <p class="text-white">Gestión de cursos, evaluaciones y calificaciones</p>
                        <a href="${pageContext.request.contextPath}/profesores/index_profesores.jsp"
                           class="btn btn-light btn-lg mt-3">
                            Entrar como Docente
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <div class="row justify-content-center mt-4">
            <div class="col-md-10 text-center text-white">
                <p class="mt-4">¿Eres administrador?
                    <a href="${pageContext.request.contextPath}/admin/autenticacion.jsp" class="text-warning">Accede al panel de administración</a>
                </p>
                <p class="small">Sistema Académico &copy; 2023 - Universidad/p>
            </div>
        </div>
    </div
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
