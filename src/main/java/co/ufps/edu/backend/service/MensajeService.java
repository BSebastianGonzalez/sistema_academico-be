package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Mensaje;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.MensajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MensajeService {
    @Autowired
    private final MensajeRepository mensajeRepository;
    public List<Mensaje> getAllMensajes() {
        return mensajeRepository.findAll();
    }
    public Optional<Mensaje> getMensajeById(Long id) {
        return mensajeRepository.findById(id);
    }
    public Mensaje createMensaje(Mensaje mensaje) {
        return mensajeRepository.save(mensaje);
    }
    public Mensaje updateUser(Long id, Mensaje mensajeDetails) {
        return mensajeRepository.findById(id).map(mensaje -> {
            mensaje.setAsunto(mensajeDetails.getAsunto());
            mensaje.setContenido(mensajeDetails.getContenido());
            mensaje.setFechaEnvio(mensajeDetails.getFechaEnvio());
            mensaje.setContenido(mensajeDetails.getContenido());
            mensaje.setLeido(mensajeDetails.isLeido());
            mensaje.setRemitente(mensajeDetails.getRemitente());
            mensaje.setDestinatario(mensajeDetails.getDestinatario());
            return mensajeRepository.save(mensaje);
        }).orElseThrow(() -> new RuntimeException("Mensaje not found"));
    }

    public void deleteMensaje(Long id) {
        mensajeRepository.deleteById(id);
    }
}
