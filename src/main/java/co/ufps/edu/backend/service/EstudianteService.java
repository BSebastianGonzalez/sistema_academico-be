package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    @Autowired
    private final EstudianteRepository estudianteRepository;

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> getEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    public Estudiante createEstudiante(Estudiante estudiante) {
        int codigoCarrera = estudiante.getCodigoCarrera();
        Integer ultimoId = estudianteRepository.findUltimoIdByCarrera(codigoCarrera);
        Integer nuevoId = (ultimoId == null) ? codigoCarrera * 10000 + 1 : ultimoId + 1;
        estudiante.setId(nuevoId);
        return estudianteRepository.save(estudiante);
    }


    public Estudiante updateEstudiante(Long id, Estudiante estudianteDetails) {
        return estudianteRepository.findById(id).map(estudiante -> {
            estudiante.setCodigoCarrera(estudianteDetails.getCodigoCarrera());
            estudiante.setSemestre(estudianteDetails.getSemestre());
            estudiante.setEstado(estudianteDetails.isEstado());
            estudiante.setUsuario(estudianteDetails.getUsuario());
            return estudianteRepository.save(estudiante);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }
}
