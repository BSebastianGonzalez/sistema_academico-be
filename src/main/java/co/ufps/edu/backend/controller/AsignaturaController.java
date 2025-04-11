package co.ufps.edu.backend.controller;


import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.repository.AsignaturaRepository;
import co.ufps.edu.backend.service.AsignaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Obtener Asignatura por Id
    @GetMapping("/{id}")
    public ResponseEntity<Asignatura> obtenerAsignaturaPorId(@PathVariable Long id) {
        return asignaturaService.obtenerAsignaturaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

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
    public Asignatura crearAsignatura(@RequestBody Asignatura asignatura) {
        return asignaturaService.crearAsignatura(asignatura);
    }


    /*
    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura nuevaAsignatura = asignaturaService.crearAsignatura(asignatura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaAsignatura);
    }
     */

    // Actualizar Asignatura
    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> actualizarAsignatura(@PathVariable Long id, @RequestBody Asignatura asignaturaActualizada) {
        try {
            Asignatura actualizarAsignatura = asignaturaService.actualizarAsignatura(id, asignaturaActualizada);
            return ResponseEntity.ok(actualizarAsignatura);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar Asignatura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignatura(@PathVariable Long id) {
        asignaturaService.eliminarAsignatura(id);
        return ResponseEntity.notFound().build();
    }

}
