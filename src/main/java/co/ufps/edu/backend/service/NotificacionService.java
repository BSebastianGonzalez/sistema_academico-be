package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Notificacion;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.NotificacionRepository;
import co.ufps.edu.backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Notificacion crearNotificacion(Notificacion notificacion, Long destinatarioId) {
        Usuario usuario = usuarioRepository.findById(destinatarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        notificacion.setDestinatario(usuario);
        notificacion.setFechaCreacion(new Date());
        notificacion.setEnviado(true); // o false si aún no ha sido leída

        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> obtenerNotificacionPorUsuario(Long destinatarioId) {
        return notificacionRepository.findByDestinatario_Id(destinatarioId);
    }

    public void eliminarNotificacion(Long id) {
        notificacionRepository.deleteById(id);
    }
}
