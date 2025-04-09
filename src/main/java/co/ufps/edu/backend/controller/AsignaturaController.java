package co.ufps.edu.backend.controller;


import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.repository.AsignaturaRepository;
import co.ufps.edu.backend.service.AsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignaturas")
@RequiredArgsConstructor
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    @GetMapping
    public List<Asignatura> obtenerTodasLasAsignaturas() {
        return asignaturaService.obtenerTodasLasAsignaturas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Asignatura> obtenerAsignaturaPorCodigo(@PathVariable int codigo) {
        return asignaturaService.obtenerAsignaturaPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Asignatura crearAsignatura(@RequestBody Asignatura asignatura) {
        return asignaturaService.crearAsignatura(asignatura);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Asignatura> actualizarAsignatura(@PathVariable int codigo, @RequestBody Asignatura asignaturaActualizada) {
        try {
            Asignatura actualizarAsignatura = asignaturaService.actualizarAsignatura(codigo, asignaturaActualizada);
            return ResponseEntity.ok(actualizarAsignatura);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminarAsignatura(@PathVariable int codigo) {
        asignaturaService.eliminarAsignatura(codigo);
        return ResponseEntity.noContent().build();
    }
}
