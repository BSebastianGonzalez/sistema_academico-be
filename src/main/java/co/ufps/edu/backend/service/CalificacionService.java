package co.ufps.edu.backend.service;


import co.ufps.edu.backend.model.Calificacion;
import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.model.Evaluacion;
import co.ufps.edu.backend.repository.CalificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalificacionService {
    @Autowired
    private final CalificacionRepository calificacionRepository;
    public List<Calificacion> getAllCalificaciones() {return calificacionRepository.findAll();}
    public Optional<Calificacion> getCalificacionByEstudiante(Estudiante estudiante) {
        return CalificacionRepository.findByEstudiante(estudiante);
    }
    public Calificacion createCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }
    public Calificacion updateCalificacion(Calificacion calificacionDetails) {
        return calificacionRepository.findById(id).map(user -> {
            user.setNota(calificacionDetails.getNota());
            user.setFecha(calificacionDetails.getFecha());
            user.setEstudiante(calificacionDetails.getEstudiante());
            user.setEvaluacion(calificacionDetails.getEvaluacion());
            return calificacionRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Nota not found"));
    }
}
