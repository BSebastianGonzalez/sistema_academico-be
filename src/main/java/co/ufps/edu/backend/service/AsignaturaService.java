package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.repository.AsignaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignaturaService {
    @Autowired
    private final AsignaturaRepository asignaturaRepository;

    public List<Asignatura> getAllAsignaturas() {return asignaturaRepository.findAll();}

    public Optional<Asignatura> getAsignaturaById(Long id) {return asignaturaRepository.findById(id);}

    public Optional<Asignatura> getAsignaturaByCodigo(String codigo) {return asignaturaRepository.findByCodigo(codigo);}

    public Asignatura createAsignatura(Asignatura asignatura) {
        // Verificar que no exista otra asignatura con el mismo código
        if (asignatura.getCodigo() != null && asignaturaRepository.findByCodigo(asignatura.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una asignatura con el código " + asignatura.getCodigo());
        }

        return asignaturaRepository.save(asignatura);
    }
    public Asignatura updateAsignatura(String codigo, Asignatura asignaturaDetails) {
        return asignaturaRepository.findByCodigo(codigo).map(asignatura -> {
            asignatura.setCodigo(asignaturaDetails.getCodigo());
            asignatura.setNombre(asignaturaDetails.getNombre());
            asignatura.setDescripcion(asignaturaDetails.getDescripcion());
            asignatura.setCreditos(asignaturaDetails.getCreditos());
            return asignaturaRepository.save(asignatura);
        }).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    public void deleteAsignatura(String codigo) {
        asignaturaRepository.deleteByCodigo(codigo);
    }

}

