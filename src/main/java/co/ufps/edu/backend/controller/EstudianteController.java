package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.EstudianteDTO;
import co.ufps.edu.backend.dto.EstudianteLoginRequestDTO;
import co.ufps.edu.backend.dto.EstudianteLoginResponseDTO;
import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.service.EstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping("/index_estudiantes")
    public String mostrarVistaEstudiantes() {
        return "index_estudiantes"; // No necesita .jsp
    }

    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estudiante createEstudiante(@RequestBody EstudianteDTO estudiante) {
        return estudianteService.createEstudiante(estudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetails) {
        try {
            Estudiante updatedEstudiante = estudianteService.updateEstudiante(id, estudianteDetails);
            return ResponseEntity.ok(updatedEstudiante);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/desactivar/{id}")
    public ResponseEntity<Estudiante> desactivarEstudiante(@PathVariable Long id) {
        try {
            Estudiante updatedEstudiante = estudianteService.desactivarEstudiante(id);
            return ResponseEntity.ok(updatedEstudiante);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<EstudianteLoginResponseDTO> login(@RequestBody EstudianteLoginRequestDTO request) {
        EstudianteLoginResponseDTO response = estudianteService.loginEstudiante(
                request.getId(),
                request.getCorreo(),
                request.getContrasenia()
        );
        return ResponseEntity.ok(response);
    }
}
