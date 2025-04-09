package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    //
    @GetMapping
    public List<Curso> obtenerTodosLosCursos() {
        return cursoService.obtenerTodosLosCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        return cursoService.obtenerCursoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Curso createCourse(@RequestBody Curso curso) {
        return cursoService.crearCurso(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Curso>> actualizarCurso(@PathVariable Long id, @RequestBody Curso cursoActualizado) {
        try {
            Optional<Curso> actualizarCurso = cursoService.actualizarCurso(id, cursoActualizado);
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



}
