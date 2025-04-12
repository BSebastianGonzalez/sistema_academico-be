package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Asistencia;
import co.ufps.edu.backend.repository.AsistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AsistenciaService {
    @Autowired
    private final AsistenciaRepository asistenciaRepository;
    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }
    public Optional<Asistencia> getAsistenciaByEstudiante(boolean presente) {
        return asistenciaRepository.findByPresente(presente);
    }
    public Asistencia createAsistencia(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }
    public Asistencia updateAsistencia(Asistencia asistenciaDetails) {
        return asistenciaRepository.findById(asistenciaDetails.getId()).map(asistencia -> {
            asistencia.setPresente(asistenciaDetails.isPresente());
            asistencia.setSesion(asistenciaDetails.getSesion());
            return asistenciaRepository.save(asistencia);
        }).orElseThrow(() -> new RuntimeException("Asistencia not found"));
    }
    public void deleteAsistencia(Long id) {
        asistenciaRepository.deleteById(id);
    }

}
