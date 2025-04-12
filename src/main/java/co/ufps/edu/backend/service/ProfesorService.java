package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Profesor;
import co.ufps.edu.backend.repository.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProfesorService {
    @Autowired
    private final ProfesorRepository profesorRepository;
    public List<Profesor> getAllProfesores() {return profesorRepository.findAll();}
    public Optional<Profesor> getProfesorById(Long id) {
        return profesorRepository.findById(id);
    }
    public Profesor createProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }
}
