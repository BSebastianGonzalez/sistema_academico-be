package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.CursoDisponibleDTO;
import co.ufps.edu.backend.dto.InscripcionRequest;
import co.ufps.edu.backend.model.Inscripcion;
import co.ufps.edu.backend.service.InscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @PostMapping
    public ResponseEntity<String> inscribirEstudiante(@RequestBody InscripcionRequest request) {
        inscripcionService.realizarInscripcion(request);
        return ResponseEntity.ok("Inscripción exitosa");
    }

    @GetMapping("/cursos-disponibles")
    public ResponseEntity<List<CursoDisponibleDTO>> obtenerCursosDisponibles() {
        return ResponseEntity.ok(inscripcionService.obtenerCursosDisponibles());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleErrors(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }



}
