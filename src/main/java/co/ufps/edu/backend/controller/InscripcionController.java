package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.AdministradorDTO;
import co.ufps.edu.backend.exception.ConflictException;
import co.ufps.edu.backend.model.Administrador;
import co.ufps.edu.backend.model.Inscripcion;
import co.ufps.edu.backend.repository.InscripcionRepository;
import co.ufps.edu.backend.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping
    public Inscripcion inscribir(@RequestParam Long estudianteId, @RequestParam Long cursoId) {
        return inscripcionService.inscribirEstudiante(estudianteId,cursoId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        inscripcionService.cancelarInscripcion(id);
        return ResponseEntity.noContent().build();
    }



}
