package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Calificacion;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.Evaluacion;
import co.ufps.edu.backend.model.Profesor;
import co.ufps.edu.backend.repository.EvaluacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluacionService {
    @Autowired
    private final EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> getAllEvaluaciones() {
        return evaluacionRepository.findAll();
    }
    public Evaluacion getEvaluacionById(Long id) {
        return evaluacionRepository.findById(id).orElseThrow(() -> new RuntimeException("Evaluacion not found"));
    }
    public Evaluacion createEvaluacion(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }
    public Evaluacion updateEvaluacion(Long id, Evaluacion evaluacionDetails) {
        return evaluacionRepository.findById(id).map(evaluacion -> {
            evaluacion.setTitulo(evaluacionDetails.getTitulo());
            evaluacion.setDescripcion(evaluacionDetails.getDescripcion());
            evaluacion.setFechaAplicacion(evaluacionDetails.getFechaAplicacion());
            evaluacion.setPorcentaje(evaluacionDetails.getPorcentaje());
            evaluacion.setTipo(evaluacionDetails.getTipo());
            evaluacion.setCreador(evaluacionDetails.getCreador());
            evaluacion.setCurso(evaluacionDetails.getCurso());
            evaluacion.setCalificacion(evaluacionDetails.getCalificacion());
            return evaluacionRepository.save(evaluacion);
        }).orElseThrow(() -> new RuntimeException("Evaluacion not found"));
    }
    public void deleteEvaluacion(Long id) {
        evaluacionRepository.deleteById(id);
    }



}
