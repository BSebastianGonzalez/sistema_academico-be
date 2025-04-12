package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Notificacion;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificacionService {
    @Autowired
    private final NotificacionRepository notificacionRepository;
    public List<Notificacion> getAllNotificaciones() {
        return notificacionRepository.findAll();
    }
    public Optional<Notificacion> getNotificacionById(Long id) {
        return notificacionRepository.findById(id);
    }
    public Notificacion createNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }
    public Notificacion updateNotificacion(Long id, Notificacion notificacionDetails) {
        return notificacionRepository.findById(id).map(notificacion -> {
            notificacion.setTitulo(notificacion.getTitulo());
            notificacion.setContenido(notificacion.getContenido());
            notificacion.setFechaCreacion(notificacion.getFechaCreacion());
            notificacion.setDescripcion(notificacion.getDescripcion());
            notificacion.setEnviado(notificacion.isEnviado());
            notificacion.setDestinatario(notificacion.getDestinatario());
            return notificacionRepository.save(notificacion);
        }).orElseThrow(() -> new RuntimeException("Notificacion not found"));
    }
    public void deleteNotificacion(Long id) {
        notificacionRepository.deleteById(id);
    }
}
