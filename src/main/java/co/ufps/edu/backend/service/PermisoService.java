package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Permiso;
import co.ufps.edu.backend.repository.PermisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermisoService {
    @Autowired
    private final PermisoRepository permisoRepository;

    public List<Permiso> getAllPermisos() {return permisoRepository.findAll();}
    public Optional<Permiso> getPermisoById(Long id) {
        return permisoRepository.findById(id);
    }
    public Permiso createPermiso(Permiso permiso) {
        return permisoRepository.save(permiso);
    }
    public Permiso updatePermiso (Long id, Permiso permisoDetails) {
        return permisoRepository.findById(id).map(user -> {
            user.setNombre(permisoDetails.getNombre());
            user.setDescripcion(permisoDetails.getDescripcion());
            return permisoRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("Permiso not found"));
    }
    public void deletePermiso(Long id) {
        permisoRepository.deleteById(id);
    }
}
