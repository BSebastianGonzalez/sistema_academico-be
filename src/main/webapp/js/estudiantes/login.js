document.addEventListener('DOMContentLoaded', function() {
    const loginForm = document.getElementById('login-form');

    loginForm.addEventListener('submit', async function(e) {
        e.preventDefault();

        const codigo = document.getElementById('codigo').value;
        const password = document.getElementById('password').value;

        try {
            const response = await fetch('${pageContext.request.contextPath}/api/auth/estudiante', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    codigo: codigo,
                    password: password
                })
            });

            const data = await response.json();

            if (response.ok) {
                // Guardar token en localStorage
                localStorage.setItem('studentToken', data.token);

                // Redirigir al panel de estudiante
                window.location.href = 'index.jsp';
            } else {
                alert(`Error: ${data.message || 'Credenciales inválidas'}`);
            }
        } catch (error) {
            console.error('Error en la autenticación:', error);
            alert('Error de conexión con el servidor');
        }
    });
});