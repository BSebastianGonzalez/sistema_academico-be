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

    //Obtener Asignatura por Id
    public Optional<Asignatura> obtenerAsignaturaPorId(Long id) {
        return asignaturaRepository.findById(id);
    }

    //Obtener Asignatura por Codigo
    public Optional<Asignatura> obtenerAsignaturaByCodigo(String codigo) {
        return asignaturaRepository.findByCodigo(codigo);
    }

    //Crear Asignatura
    public Asignatura createAsignatura(Asignatura asignatura) {
        // Verificar que no exista otra asignatura con el mismo código
        if (asignatura.getCodigo() != null && asignaturaRepository.findByCodigo(asignatura.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una asignatura con el código " + asignatura.getCodigo());
        }

        return asignaturaRepository.save(asignatura);
    }

    /*
    public Asignatura crearAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }
    */

    //Actualizar Asignatura
    public Asignatura actualizarAsignatura(Long id, Asignatura asignaturaDetails) {
        return asignaturaRepository.findById(id).map(asignatura -> {
            asignatura.setCodigo(asignaturaDetails.getCodigo());
            asignatura.setNombre(asignaturaDetails.getNombre());
            asignatura.setDescripcion(asignaturaDetails.getDescripcion());
            asignatura.setCreditos(asignaturaDetails.getCreditos());
            return asignaturaRepository.save(asignatura);
        }).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    //Eliminar Asignatura

    /*
    public void eliminarAsignatura(Long id) {
         asignaturaRepository.deleteById(id);
    }
     */

    public boolean eliminarAsignatura(Long id) {
        return asignaturaRepository.findById(id)
                .map(asignatura -> {
                    asignaturaRepository.delete(asignatura);
                    return true;
                })
                .orElse(false);
    }


}

