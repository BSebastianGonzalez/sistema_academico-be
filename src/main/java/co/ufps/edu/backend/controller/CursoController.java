package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.AdministradorDTO;
import co.ufps.edu.backend.dto.CursoDTO;
import co.ufps.edu.backend.dto.CursoResponseDTO;
import co.ufps.edu.backend.model.Administrador;
import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.service.CursoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    //
    @GetMapping
    public List<Curso> obtenerTodosLosCursos() {
        return cursoService.listarTodosCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        return cursoService.obtenerCursoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> crearCurso(@RequestBody CursoDTO cursoDTO) {
        try {
            Curso nuevoCurso = cursoService.crearCurso(cursoDTO);
            return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @Valid @RequestBody Curso cursoDetails) {
        try {
            Curso actualizarCurso = cursoService.actualizarCurso(id, cursoDetails);
            return ResponseEntity.ok(actualizarCurso);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cursoId}/profesores/{profesorId}")
    public ResponseEntity<Curso> asignarProfesor(
            @PathVariable Long cursoId,
            @PathVariable Long profesorId
    ) {
        return ResponseEntity.ok(cursoService.asignarProfesor(cursoId, profesorId));
    }

    @DeleteMapping("/{cursoId}/profesores/{profesorId}")
    public ResponseEntity<Void> removerProfesor(
            @PathVariable Long cursoId,
            @PathVariable Long profesorId
    ) {
        cursoService.removerProfesor(cursoId, profesorId);
        return ResponseEntity.noContent().build();
    }


}
