package co.ufps.edu.backend.controller;


import co.ufps.edu.backend.dto.AsignaturaDTO;
import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.service.AsignaturaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/asignaturas")
@RequiredArgsConstructor
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    //Listar Asignaturas
    @GetMapping
    public List<Asignatura> obtenerTodasLasAsignaturas() {
        return asignaturaService.obtenerTodasLasAsignaturas();
    }

    /*
    //Obtener Asignatura por Id
    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> obtenerAsignaturaPorId(@PathVariable Long id) {
        return asignaturaService.obtenerAsignaturaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
     */
    
    //Obtener Asignatura por Codigo
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Asignatura> obtenerAsignaturaPorCodigo(@PathVariable String codigo) {
        return asignaturaService.obtenerAsignaturaPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /*
    @PostMapping
    public ResponseEntity<Asignatura> crearAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura nuevaAsignatura = asignaturaService.crearAsignatura(asignatura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsignatura);
    }
     */

    // Crear Asignatura
    @PostMapping
    public Asignatura crearAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
        return asignaturaService.crearAsignatura(asignaturaDTO);
    }

    // Actualizar Asignatura
    @PutMapping("/{codigo}")
    public ResponseEntity<Asignatura> actualizarAsignatura(@PathVariable String codigo, @RequestBody Asignatura asignaturaActualizada) {
        try {
            Asignatura actualizarAsignatura = asignaturaService.actualizarAsignatura(codigo, asignaturaActualizada);
            return ResponseEntity.ok(actualizarAsignatura);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar Asignatura
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminarAsignatura(@PathVariable String codigo) {
        asignaturaService.eliminarAsignatura(codigo);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{codigo}/validar-prerrequisitos")
    public ResponseEntity<?> validarPrerrequisitos(
            @PathVariable String codigo,
            @RequestParam(required = false) Set<String> asignaturasAprobadas,
            @RequestParam(required = false) Integer creditosAcumulados
    ) {
        try {
            boolean cumple = asignaturaService.validarPrerrequisitos(
                    codigo,
                    asignaturasAprobadas != null ? asignaturasAprobadas : Collections.emptySet(),
                    creditosAcumulados != null ? creditosAcumulados : 0
            );

            return ResponseEntity.ok(Map.of(
                    "cumplePrerrequisitos", cumple,
                    "asignatura", codigo
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Error en validación",
                    "mensaje", e.getMessage()
            ));
        }
    }

}
