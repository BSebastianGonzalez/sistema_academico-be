package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.ProfesorDTO;
import co.ufps.edu.backend.dto.ProfesorLoginRequestDTO;
import co.ufps.edu.backend.dto.ProfesorLoginResponseDTO;
import co.ufps.edu.backend.model.Profesor;
import co.ufps.edu.backend.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping("/index_docentes")
    public String mostrarVistaDocentes() {
        return "index_docentes"; // No necesita .jsp
    }

    @GetMapping("/list")
    public List<Profesor> getAllProfesores() {
        return profesorService.getAllProfesores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Long id) {
        return profesorService.getProfesorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Profesor createProfesor(@RequestBody ProfesorDTO profesor) {
        return profesorService.createProfesor(profesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable Long id, @RequestBody Profesor profesorDetails) {
        try {
            Profesor updatedProfesor = profesorService.updateProfesor(id, profesorDetails);
            return ResponseEntity.ok(updatedProfesor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/desactivar/{id}")
    public ResponseEntity<Profesor> desactivarProfesor(@PathVariable Long id) {
        try {
            Profesor updatedProfesor = profesorService.desactivarProfesor(id);
            return ResponseEntity.ok(updatedProfesor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ProfesorLoginResponseDTO> login(@RequestBody ProfesorLoginRequestDTO request) {
        ProfesorLoginResponseDTO response = profesorService.login(request.getCorreo(), request.getContrasenia());
        return ResponseEntity.ok(response);
    }
}

