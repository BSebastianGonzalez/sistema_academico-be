package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.model.Inscripcion;
import co.ufps.edu.backend.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/inscripciones")
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
