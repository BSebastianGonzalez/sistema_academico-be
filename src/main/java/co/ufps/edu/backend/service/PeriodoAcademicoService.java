package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.PeriodoAcademico;
import co.ufps.edu.backend.repository.PeriodoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeriodoAcademicoService {

    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    public Optional<PeriodoAcademico> findById(Long id) {
        return periodoAcademicoRepository.findById(id);
    }
}
