package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Foro;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.ForoRepository;
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
public class ForoService {

    @Autowired
    private ForoRepository foroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Foro crearForo(Foro foro, Long idCreador) {
        Usuario creador = usuarioRepository.findById(idCreador)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        foro.setCreador(creador);
        foro.setFechaCreacion(LocalDateTime.now());

        return foroRepository.save(foro);
    }

    public List<Foro> listarForos() {
        return foroRepository.findAll();
    }

    public Optional<Foro> obtenerForos(Long id) {
        return foroRepository.findById(id);
    }

    public void eliminarForos(Long id) {
        foroRepository.deleteById(id);
    }

}
