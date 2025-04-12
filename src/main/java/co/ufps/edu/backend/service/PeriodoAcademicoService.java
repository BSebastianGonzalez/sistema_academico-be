package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.PeriodoAcademico;
import co.ufps.edu.backend.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeriodoAcademicoService {
    @Autowired
    private final PeriodoAcademicoRepository periodoAcademicoRepository;
    public List<PeriodoAcademico> getAllPeriodosAcademicos() {
        return periodoAcademicoRepository.findAll();
    }
    public Optional<PeriodoAcademico> getPeriodoAcademicoById(Long id) {
        return periodoAcademicoRepository.findById(id);
    }
    public PeriodoAcademico createPeriodoAcademico(PeriodoAcademico periodoAcademico) {
        return periodoAcademicoRepository.save(periodoAcademico);
    }
    public PeriodoAcademico updatePeriodoAcademico(PeriodoAcademico periodoAcademicoDetails) {
        return periodoAcademicoRepository.findById(periodoAcademicoDetails.getId()).map(periodoAcademico -> {
            periodoAcademico.setNombre(periodoAcademicoDetails.getNombre());
            periodoAcademico.setFechaInicio(periodoAcademicoDetails.getFechaInicio());
            periodoAcademico.setFechaFin(periodoAcademicoDetails.getFechaFin());
            periodoAcademico.setCurso(periodoAcademicoDetails.getCurso());
            return periodoAcademicoRepository.save(periodoAcademico);
        }).orElseThrow(() -> new RuntimeException("Periodo not found"));
    }
    public void deletePeriodoAcademico(Long id) {
        periodoAcademicoRepository.deleteById(id);
    }
}
