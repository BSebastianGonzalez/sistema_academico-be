package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Calificacion;
import co.ufps.edu.backend.repository.CalificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CalificacionService {

    @Autowired
    private final CalificacionRepository calificacionRepository;

    public List<Calificacion> getAllCalificaciones() {
        return calificacionRepository.findAll();
    }

    public Optional<Calificacion> getCalificacionById(Long id) {
        return calificacionRepository.findById(id);
    }

    public Calificacion createCalificacion(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    public Calificacion updateCalificacion(Long id, Calificacion calificacionDetails) {
        return calificacionRepository.findById(id).map(calificacion -> {
            calificacion.setNota(calificacionDetails.getNota());
            calificacion.setFecha(calificacionDetails.getFecha());
            calificacion.setEstudiante(calificacionDetails.getEstudiante());
            calificacion.setEvaluacion(calificacionDetails.getEvaluacion());
            return calificacionRepository.save(calificacion);
        }).orElseThrow(() -> new RuntimeException("Calificacion not found"));
    }

    public void deleteCalificacion(Long id) {
        calificacionRepository.deleteById(id);
    }

    public List<Calificacion> getCalificacionesByEstudiante(Long estudianteId) {
        return calificacionRepository.findByEstudianteId(estudianteId);
    }
}

