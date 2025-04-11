package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.model.Foro;
import co.ufps.edu.backend.service.ForoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foros")
@RequiredArgsConstructor
public class ForoController {

    private ForoService foroService;

    @PostMapping("/crear")
    public ResponseEntity<Foro> crearForo(@RequestBody Foro foro,
                                          @RequestParam Long idCreador) {
        Foro creado = foroService.crearForo(foro, idCreador);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public List<Foro> listarForos() {
        return foroService.listarForos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Foro> obtenerForos(@PathVariable Long id) {
        return foroService.obtenerForos(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarForos(@PathVariable Long id) {
        foroService.eliminarForos(id);
        return ResponseEntity.noContent().build();
    }
}
