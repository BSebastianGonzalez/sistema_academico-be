package co.ufps.edu.backend.service;


import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setCedula(usuarioDetails.getCedula());
            usuario.setNombre(usuarioDetails.getNombre());
            usuario.setApellido(usuarioDetails.getApellido());
            usuario.setCorreo(usuarioDetails.getCorreo());
            usuario.setContrasenia(usuarioDetails.getContrasenia());
            usuario.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
            usuario.setDireccion(usuarioDetails.getDireccion());
            usuario.setTelefono(usuarioDetails.getTelefono());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}

