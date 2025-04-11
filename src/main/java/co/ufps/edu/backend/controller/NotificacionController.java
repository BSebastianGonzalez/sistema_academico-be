package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.model.Notificacion;
import co.ufps.edu.backend.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {


    private final NotificacionService notificacionService;

    @PostMapping("/crear")
    public ResponseEntity<Notificacion> crearNotificacion(@RequestBody Notificacion notificacion,
                                                          @RequestParam Long destinatarioId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notificacionService.crearNotificacion(notificacion, destinatarioId));
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<Notificacion>> obtenerNotificaciones(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(notificacionService.obtenerNotificacionPorUsuario(idUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNotificaciones(@PathVariable Long id) {
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }


}
