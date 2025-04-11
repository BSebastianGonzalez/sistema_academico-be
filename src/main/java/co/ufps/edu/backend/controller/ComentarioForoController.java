package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.model.ComentarioForo;
import co.ufps.edu.backend.service.ComentarioForoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentariosForo")
@RequiredArgsConstructor
public class ComentarioForoController {

    private ComentarioForoService comentarioService;

    @PostMapping("/publicar")
    public ResponseEntity<ComentarioForo> comentar(@RequestBody ComentarioForo comentario,
                                                   @RequestParam Long foroId,
                                                   @RequestParam Long autorId) {
        ComentarioForo creado = comentarioService.comentar(foroId, autorId, comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/{idForo}")
    public List<ComentarioForo> listarComentariosForo(@PathVariable Long idForo) {
        return comentarioService.listarComentariosForo(idForo);
    }
}
