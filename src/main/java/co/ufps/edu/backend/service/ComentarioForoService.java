package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.ComentarioForo;
import co.ufps.edu.backend.model.Foro;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.ComentarioForoRepository;
import co.ufps.edu.backend.repository.ForoRepository;
import co.ufps.edu.backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioForoService {

    @Autowired
    private ComentarioForoRepository comentarioRepository;

    @Autowired
    private ForoRepository foroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ComentarioForo comentar(Long idForo, Long idAutor, ComentarioForo comentario) {
        Foro foro = foroRepository.findById(idForo)
                .orElseThrow(() -> new EntityNotFoundException("Foro no encontrado"));

        Usuario autor = usuarioRepository.findById(idAutor)
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));

        comentario.setForo(foro);
        comentario.setAutor(autor);
        comentario.setFechaPublicacion(LocalDateTime.now());

        return comentarioRepository.save(comentario);
    }

    public List<ComentarioForo> listarComentariosForo(Long idForo) {
        return comentarioRepository.findByForoId(idForo);
    }
}
