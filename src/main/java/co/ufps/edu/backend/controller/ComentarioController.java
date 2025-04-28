package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.dto.ComentarioDTO;
import co.ufps.edu.backend.model.Comentario;
import co.ufps.edu.backend.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<Comentario> enviarComentario(@RequestBody ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioService.enviarComentario(comentarioDTO);
        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/evaluacion/{evaluacionId}")
    public ResponseEntity<List<Comentario>> listarComentariosPorEvaluacion(@PathVariable Long evaluacionId) {
        List<Comentario> comentarios = comentarioService.listarComentariosPorEvaluacion(evaluacionId);
        return ResponseEntity.ok(comentarios);
    }
}
