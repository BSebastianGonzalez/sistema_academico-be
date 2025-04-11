package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Mensaje;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.MensajeRepository;
import co.ufps.edu.backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MensajeService {

    @Autowired
    private final MensajeRepository mensajeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Mensaje enviarMensaje(Mensaje mensaje, Long idEmisor, Long idReceptor) {
        Usuario emisor = usuarioRepository.findById(idEmisor)
                .orElseThrow(() -> new EntityNotFoundException("Emisor no encontrado"));

        Usuario receptor = usuarioRepository.findById(idReceptor)
                .orElseThrow(() -> new EntityNotFoundException("Receptor no encontrado"));

        mensaje.setEmisor(emisor);
        mensaje.setReceptor(receptor);
        mensaje.setFechaEnvio(LocalDateTime.now());

        return mensajeRepository.save(mensaje);
    }

    public List<Mensaje> obtenerMensajesEnviados(Long idEmisor) {
        return mensajeRepository.findByEmisorId(idEmisor);
    }

    public List<Mensaje> obtenerMensajesRecibidos(Long idReceptor) {
        return mensajeRepository.findByReceptorId(idReceptor);
    }

    public Optional<Mensaje> obtenerMensajePorId(Long id) {
        return mensajeRepository.findById(id);
    }

    public void eliminarMensaje(Long id) {
        mensajeRepository.deleteById(id);
    }



}
