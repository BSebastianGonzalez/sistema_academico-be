package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.RecursoAcademico;
import co.ufps.edu.backend.repository.RecursoAcademicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecursoAcademicoService {

    private final RecursoAcademicoRepository recursoAcademicoRepository;

    public Optional<RecursoAcademico> findById(Long id) {
        return recursoAcademicoRepository.findById(id);
    }
}