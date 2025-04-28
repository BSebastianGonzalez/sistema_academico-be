package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.ComentarioDTO;
import co.ufps.edu.backend.model.Comentario;
import co.ufps.edu.backend.model.Evaluacion;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.ComentarioRepository;
import co.ufps.edu.backend.repository.EvaluacionRepository;
import co.ufps.edu.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {
    @Autowired
    private final ComentarioRepository comentarioRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final EvaluacionRepository evaluacionRepository;

    public Comentario enviarComentario(ComentarioDTO comentarioDTO) {
        Usuario remitente = usuarioRepository.findById(comentarioDTO.getRemitenteId())
                .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));
        Usuario destinatario = usuarioRepository.findById(comentarioDTO.getDestinatarioId())
                .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));
        Evaluacion evaluacion = evaluacionRepository.findById(comentarioDTO.getEvaluacionId())
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));

        Comentario comentario = new Comentario();
        comentario.setContenido(comentarioDTO.getContenido());
        comentario.setFecha(new Date());
        comentario.setRemitente(remitente);
        comentario.setDestinatario(destinatario);
        comentario.setEvaluacion(evaluacion);

        return comentarioRepository.save(comentario);
    }

    public List<Comentario> listarComentariosPorEvaluacion(Long evaluacionId) {
        return comentarioRepository.findByEvaluacionId(evaluacionId);
    }
}

