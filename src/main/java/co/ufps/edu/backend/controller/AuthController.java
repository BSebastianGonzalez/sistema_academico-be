package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.LoginRequestDTO;
import co.ufps.edu.backend.dto.LoginResponseDTO;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) {
        Usuario usuario = usuarioService.validarCredenciales(loginDTO.getCorreo(), loginDTO.getContrasenia());

        if (usuario != null) {
            LoginResponseDTO response = new LoginResponseDTO(
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getRol().getNombre(),
                    "Inicio de sesión exitoso"
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas o usuario inactivo");
        }
    }
}
