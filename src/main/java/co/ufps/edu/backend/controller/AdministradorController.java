package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.AdminLoginRequestDTO;
import co.ufps.edu.backend.dto.AdminLoginResponseDTO;
import co.ufps.edu.backend.dto.AdministradorDTO;
import co.ufps.edu.backend.model.Administrador;
import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.service.AdministradorService;
import co.ufps.edu.backend.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AdministradorController {
    private final AdministradorService administradorService;

    @GetMapping
    public List<Administrador> getAllAdministradores() {
        return administradorService.getAllAdministradores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Long id) {
        return administradorService.getAdministradorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Administrador createAdministrador(@RequestBody AdministradorDTO administrador) {
        return administradorService.createAdministrador(administrador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable Long id, @RequestBody Administrador administradorDetails) {
        try {
            Administrador updatedAdministrador = administradorService.updateAdministrador(id, administradorDetails);
            return ResponseEntity.ok(updatedAdministrador);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Long id) {
        administradorService.deleteAdministrador(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/desactivar/{id}")
    public ResponseEntity<Administrador> desactivarAdministrador(@PathVariable Long id) {
        try {
            Administrador updatedAdministrador = administradorService.desactivarAdministrador(id);
            return ResponseEntity.ok(updatedAdministrador);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponseDTO> login(@RequestBody AdminLoginRequestDTO request) {
        AdminLoginResponseDTO response = administradorService.login(request.getCorreo(), request.getContrasenia());
        return ResponseEntity.ok(response);
    }

}
