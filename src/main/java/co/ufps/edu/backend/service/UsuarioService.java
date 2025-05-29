package co.ufps.edu.backend.service;


import co.ufps.edu.backend.dto.UsuarioDTO;
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

    public Usuario createUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioCreate = new Usuario();
        usuarioCreate.setId(usuarioDTO.getDocumento());
        usuarioCreate.setNombre1(usuarioDTO.getNombre1());
        usuarioCreate.setNombre2(usuarioDTO.getNombre2());
        usuarioCreate.setApellido1(usuarioDTO.getApellido1());
        usuarioCreate.setApellido2(usuarioDTO.getApellido2());
        usuarioCreate.setCorreo(usuarioDTO.getCorreo());
        usuarioCreate.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuarioCreate.setDireccion(usuarioDTO.getDireccion());
        usuarioCreate.setTelefono(usuarioDTO.getTelefono());
        usuarioCreate.setTipoDocumento(usuarioDTO.getTipoDocumento());
        return usuarioRepository.save(usuarioCreate);
    }

    public Usuario updateUsuario(Long id, Usuario usuarioDetails) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setCorreo(usuarioDetails.getCorreo());
            usuario.setFechaNacimiento(usuarioDetails.getFechaNacimiento());
            usuario.setDireccion(usuarioDetails.getDireccion());
            usuario.setTelefono(usuarioDetails.getTelefono());
            usuario.setNombre1(usuarioDetails.getNombre1());
            usuario.setTipoDocumento(usuarioDetails.getTipoDocumento());
            usuario.setNombre2(usuarioDetails.getNombre2());
            usuario.setApellido1(usuarioDetails.getApellido1());
            usuario.setApellido2(usuarioDetails.getApellido2());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}

