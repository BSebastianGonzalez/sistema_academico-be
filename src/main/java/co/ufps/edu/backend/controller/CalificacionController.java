package co.ufps.edu.backend.controller;


import co.ufps.edu.backend.model.Calificacion;
import co.ufps.edu.backend.service.CalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
@RequiredArgsConstructor
public class CalificacionController {

    private final CalificacionService calificacionService;

    @GetMapping
    public List<Calificacion> getAllCalificaciones() {
        return calificacionService.getAllCalificaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> getCalificacionById(@PathVariable Long id) {
        return calificacionService.getCalificacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Calificacion createCalificacion(@RequestBody Calificacion calificacion) {
        return calificacionService.createCalificacion(calificacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calificacion> updateCalificacion(@PathVariable Long id, @RequestBody Calificacion calificacionDetails) {
        try {
            Calificacion updatedCalificacion = calificacionService.updateCalificacion(id, calificacionDetails);
            return ResponseEntity.ok(updatedCalificacion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalificacion(@PathVariable Long id) {
        calificacionService.deleteCalificacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estudiante/{id}")
    public List<Calificacion> getCalificacionesByEstudiante(@PathVariable Long id) {
        return calificacionService.getCalificacionesByEstudiante(id);
    }
}

