package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.model.Aula;
import co.ufps.edu.backend.model.SesionClase;
import co.ufps.edu.backend.repository.AulaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AulaService {
    @Autowired
    private final AulaRepository aulaRepository;

    public List<Aula> getAllAulas() {
        return aulaRepository.findAll();
    }
    public Optional<Aula> getAulaById(Long id) {
        return aulaRepository.findById(id);
    }
    public Aula createAula(Aula aula) {
        return aulaRepository.save(aula);
    }
    public Aula updateUser(Aula aulaDetails) {
        return aulaRepository.findById(id).map(aula -> {
            aula.setCodigo(aulaDetails.getCodigo());
            aula.setEdificio(aulaDetails.getEdificio());
            aula.setCapacidad(aulaDetails.getCapacidad());
            aula.setTipo(aulaDetails.getTipo());
            aula.setDisponible(aula.isDisponible());
            aula.setSesion(aula.getSesion());
            return aulaRepository.save(aula);
        }).orElseThrow(() -> new RuntimeException("Aula not found"));
    }
    public void deleteAula(Long id) {
        aulaRepository.deleteById(id);
    }
}
