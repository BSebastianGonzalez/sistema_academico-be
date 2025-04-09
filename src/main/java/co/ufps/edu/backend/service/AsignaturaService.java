package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaService {

    @Autowired
    private final AsignaturaRepository asignaturaRepository;

    @Autowired
    public AsignaturaService(AsignaturaRepository asignaturaRepository) {
        this.asignaturaRepository = asignaturaRepository;
    }

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
    public Asignatura actualizarAsignatura(int codigo, Asignatura asignaturaActualizada) {
        return asignaturaRepository.findById(codigo)
                .map(asignaturaExistente -> {
                    // Verificar si el nombre ha cambiado y si el nuevo nombre ya existe
                    if (!asignaturaExistente.getNombre().equalsIgnoreCase(asignaturaActualizada.getNombre()) &&
                            asignaturaRepository.existsByNombreIgnoreCase(asignaturaActualizada.getNombre())) {
                        throw new IllegalArgumentException("Ya existe una asignatura con el nombre: " + asignaturaActualizada.getNombre());
                    }

                    asignaturaExistente.setNombre(asignaturaActualizada.getNombre());
                    asignaturaExistente.setDescripcion(asignaturaActualizada.getDescripcion());
                    asignaturaExistente.setCreditos(asignaturaActualizada.getCreditos());
                    return asignaturaRepository.save(asignaturaExistente);
                }).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    //Eliminar Asignatura
    public void eliminarAsignatura(int codigo) {
        asignaturaRepository.deleteById(codigo);
    }
}

