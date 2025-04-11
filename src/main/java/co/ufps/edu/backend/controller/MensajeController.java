package co.ufps.edu.backend.controller;

import co.ufps.edu.backend.model.Mensaje;
import co.ufps.edu.backend.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensajes")
@RequiredArgsConstructor
public class MensajeController {

    private MensajeService mensajeService;

    @PostMapping("/enviar")
    public ResponseEntity<Mensaje> enviarMensaje(@RequestBody Mensaje mensaje,
                                                 @RequestParam Long idEmisor,
                                                 @RequestParam Long idReceptor) {
        Mensaje enviado = mensajeService.enviarMensaje(mensaje, idEmisor, idReceptor);
        return ResponseEntity.status(HttpStatus.CREATED).body(enviado);
    }

    @GetMapping("/recibidos")
    public ResponseEntity<List<Mensaje>> verMensajesRecibidos(@RequestParam Long receptorId) {
        return ResponseEntity.ok(mensajeService.obtenerMensajesRecibidos(receptorId));
    }

    @GetMapping("/enviados")
    public ResponseEntity<List<Mensaje>> verMensajesEnviados(@RequestParam Long emisorId) {
        return ResponseEntity.ok(mensajeService.obtenerMensajesEnviados(emisorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensaje> verMensaje(@PathVariable Long id) {
        return mensajeService.obtenerMensajePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Long id) {
        mensajeService.eliminarMensaje(id);
        return ResponseEntity.noContent().build();
    }















}
