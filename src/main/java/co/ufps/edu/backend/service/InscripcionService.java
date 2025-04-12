package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.model.Inscripcion;
import co.ufps.edu.backend.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionService {
    @Autowired
    private final InscripcionRepository inscripcionRepository;

    public List<Inscripcion> getAllInscripciones {
        return inscripcionRepository.findAll();
    }
    public Inscripcion createInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }
    public Inscripcion updateInscripcion(Long id, Inscripcion inscripcionDetails) {
        return inscripcionRepository.findById(id).map(inscripcion -> {
            inscripcion.setFechaInscripcion(inscripcionDetails.getFechaInscripcion());
            inscripcion.setEstado(inscripcionDetails.getEstado());
            inscripcion.setEstudiante(inscripcionDetails.getEstudiante());
            inscripcion.setCurso(inscripcionDetails.getCurso());
            return inscripcionRepository.save(inscripcion);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public void deleteInscripcion(Long id) {inscripcionRepository.deleteById(id);}
}
