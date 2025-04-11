package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.Administrador;
import co.ufps.edu.backend.repository.AdministradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorService {
    @Autowired
    private final AdministradorRepository administradorRepository;

    public List<Administrador> getAllAdministradores() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> getAdministradorById(Long id) {
        return administradorRepository.findById(id);
    }

    public Administrador createAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public Administrador updateAdministrador(Long id, Administrador administradorDetails) {
        return administradorRepository.findById(id).map(administrador -> {
            administrador.setDepartamento(administradorDetails.getDepartamento());
            administrador.setNivelAcceso(administradorDetails.getNivelAcceso());
            administrador.setUsuario(administradorDetails.getUsuario());
            return administradorRepository.save(administrador);
        }).orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }

    public void deleteAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }
}
