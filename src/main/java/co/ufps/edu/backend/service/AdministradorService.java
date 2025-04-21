package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.AdminLoginResponseDTO;
import co.ufps.edu.backend.dto.AdministradorDTO;
import co.ufps.edu.backend.model.Administrador;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.AdministradorRepository;
import co.ufps.edu.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorService {
    @Autowired
    private final AdministradorRepository administradorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Administrador> getAllAdministradores() {
        return administradorRepository.findAll();
    }

    public Optional<Administrador> getAdministradorById(Long id) {
        return administradorRepository.findById(id);
    }

    public Administrador createAdministrador(AdministradorDTO administradorDTO) {
        Administrador administrador = new Administrador();
        Usuario usuario = usuarioRepository.findById(administradorDTO.getIdUsuario())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String nombre1 = usuario.getNombre1().toLowerCase();
        String nombre2 = usuario.getNombre2().toLowerCase();
        String apellido1 = usuario.getApellido1();
        String apellido2 = usuario.getApellido2();

        String correo = nombre1 + nombre2 + Character.toLowerCase(apellido1.charAt(0)) +
                Character.toLowerCase(apellido2.charAt(0)) + "@ufps.edu.co";
        administrador.setCorreo(correo);
        administrador.setDepartamento(administradorDTO.getDepartamento());
        administrador.setNivelAcceso(administradorDTO.getNivelAcceso());
        administrador.setUsuario(usuario);
        administrador.setActivo(true);
        return administradorRepository.save(administrador);
    }

    public Administrador updateAdministrador(Long id, Administrador administradorDetails) {
        return administradorRepository.findById(id).map(administrador -> {
            administrador.setDepartamento(administradorDetails.getDepartamento());
            administrador.setNivelAcceso(administradorDetails.getNivelAcceso());
            administrador.setCorreo(administradorDetails.getCorreo());
            administrador.setContrasenia(administradorDetails.getContrasenia());
            administrador.setUltimoAcceso(administradorDetails.getUltimoAcceso());
            administrador.setUsuario(administradorDetails.getUsuario());
            administrador.setActivo(administradorDetails.isActivo());
            return administradorRepository.save(administrador);
        }).orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
    }

    public void deleteAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }

    public Administrador desactivarAdministrador(Long id) {
        Administrador administrador = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));
        administrador.setActivo(false);
        return administradorRepository.save(administrador);
    }

    public AdminLoginResponseDTO login(String correo, String contrasenia) {
        Optional<Administrador> adminOptional = administradorRepository.findByCorreo(correo);

        if (adminOptional.isPresent()) {
            Administrador admin = adminOptional.get();
            if (admin.getContrasenia().equals(contrasenia)) {
                admin.setUltimoAcceso(new Date());
                String horaAcceso = "Hora de inicio de sesion: " + admin.getUltimoAcceso();
                return new AdminLoginResponseDTO(true, "Inicio de sesión exitoso", horaAcceso);
            } else {
                return new AdminLoginResponseDTO(false, "Contraseña incorrecta", null);
            }
        } else {
            return new AdminLoginResponseDTO(false, "Correo no registrado", null);
        }
    }
}
