package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.repository.AsignaturaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignaturaService {

    @Autowired
    private final AsignaturaRepository asignaturaRepository;

    //Listar Asignaturas
    public List<Asignatura> obtenerTodasLasAsignaturas() {
        return asignaturaRepository.findAll();
    }

    //Obtener Asignatura
    public Optional<Asignatura> obtenerAsignaturaPorCodigo(int codigo) {
        return asignaturaRepository.findById(codigo);
    }

    //Opcional
    public List<Asignatura> buscarAsignaturasPorNombre(String nombre) {
        return asignaturaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    //Opcional
    public List<Asignatura> buscarAsignaturasPorCreditos(byte creditos) {
        return asignaturaRepository.findByCreditos(creditos);
    }

    //Crear Asignatura
    public Asignatura crearAsignatura(Asignatura asignatura) {
        // Validar que no exista otra asignatura con el mismo nombre
        if (asignaturaRepository.existsByNombreIgnoreCase(asignatura.getNombre())) {
            throw new IllegalArgumentException("Ya existe una asignatura con el nombre: " + asignatura.getNombre());
        }
        return asignaturaRepository.save(asignatura);
    }


    //Actualizar Asignatura
    public Asignatura actualizarAsignatura(int codigo, Asignatura asignaturaDetails) {
        return asignaturaRepository.findById(codigo).map(asignatura -> {
            asignatura.setNombre(asignaturaDetails.getNombre());
            asignatura.setDescripcion(asignaturaDetails.getDescripcion());
            asignatura.setCreditos(asignaturaDetails.getCreditos());
            return asignaturaRepository.save(asignatura);
        }).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    //Eliminar Asignatura
    public void eliminarAsignatura(int codigo) {
        asignaturaRepository.deleteById(codigo);
    }
}

