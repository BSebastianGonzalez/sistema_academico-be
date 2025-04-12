package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.RecursoAcademico;
import co.ufps.edu.backend.repository.RecursoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecursoAcademicoService {
    @Autowired
    private final RecursoAcademicoRepository recursoAcademicoRepository;
    public List<RecursoAcademico> getAllRecursosAcademicos() {
        return recursoAcademicoRepository.findAll();
    }
    public Optional<RecursoAcademico> getRecursoAcademicoById(Long id) {
        return recursoAcademicoRepository.findById(id);
    }
    public RecursoAcademico createRecursoAcademico(RecursoAcademico recursoAcademico) {
        return recursoAcademicoRepository.save(recursoAcademico);
    }
    public RecursoAcademico updateRecurso(Long id, RecursoAcademico recursoAcademicoDetails) {
        return recursoAcademicoRepository.findById(id).map(recursoAcademico -> {
            recursoAcademico.setNombre(recursoAcademicoDetails.getNombre());
            recursoAcademico.setTipo(recursoAcademicoDetails.getTipo());
            recursoAcademico.setUbicacion(recursoAcademicoDetails.getUbicacion());
            recursoAcademico.setDisponible(recursoAcademicoDetails.isDisponible());
            recursoAcademico.setCursoAsignado(recursoAcademicoDetails.getCursoAsignado());
            return recursoAcademicoRepository.save(recursoAcademico);
        }).orElseThrow(() -> new RuntimeException("Recurso not found"));
    }

    public void deleteRecurso(Long id) {
        recursoAcademicoRepository.deleteById(id);
    }
}
