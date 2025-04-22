package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.ProfesorDTO;
import co.ufps.edu.backend.dto.ProfesorLoginResponseDTO;
import co.ufps.edu.backend.model.Profesor;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.ProfesorRepository;
import co.ufps.edu.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfesorService {
    @Autowired
    private final ProfesorRepository profesorRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;

    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> getProfesorById(Long id) {
        return profesorRepository.findById(id);
    }

    public Profesor createProfesor(ProfesorDTO profesorDTO) {
        Profesor profesor = new Profesor();

        Usuario usuario = usuarioRepository.findById(profesorDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String nombre1 = usuario.getNombre1().toLowerCase();
        String apellido1 = usuario.getApellido1();
        String nombre2 = usuario.getNombre2().toLowerCase();
        String apellido2 = usuario.getApellido2();

        String correo = nombre1 + nombre2 +
                Character.toLowerCase(apellido1.charAt(0)) +
                Character.toLowerCase(apellido2.charAt(0)) + "@ufps.edu.co";

        profesor.setCorreo(correo);
        profesor.setContrasenia(profesorDTO.getContrasenia());
        profesor.setActivo(true);
        profesor.setUsuario(usuario);

        return profesorRepository.save(profesor);
    }


    public Profesor updateProfesor(Long id, Profesor profesorDetails) {
        return profesorRepository.findById(id).map(profesor -> {
            profesor.setCorreo(profesorDetails.getCorreo());
            profesor.setContrasenia(profesorDetails.getContrasenia());
            profesor.setUltimoAcceso(profesorDetails.getUltimoAcceso());
            profesor.setActivo(profesorDetails.isActivo());
            profesor.setUsuario(profesorDetails.getUsuario());
            profesor.setCarrera(profesorDetails.getCarrera());
            return profesorRepository.save(profesor);
        }).orElseThrow(() -> new RuntimeException("Profesor not found"));
    }

    public void deleteProfesor(Long id) {
        profesorRepository.deleteById(id);
    }

    public Profesor desactivarProfesor(Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        profesor.setActivo(false);
        return profesorRepository.save(profesor);
    }

    public ProfesorLoginResponseDTO login(String correo, String contrasenia) {
        Optional<Profesor> profesorOptional = profesorRepository.findByCorreo(correo);

        if (profesorOptional.isPresent()) {
            Profesor profesor = profesorOptional.get();

            if (profesor.getContrasenia().equals(contrasenia)) {
                profesor.setUltimoAcceso(new Date());
                profesorRepository.save(profesor);

                String horaAcceso = "Hora de inicio de sesión: " + profesor.getUltimoAcceso();
                return new ProfesorLoginResponseDTO(true, "Inicio de sesión exitoso", horaAcceso);
            } else {
                return new ProfesorLoginResponseDTO(false, "Contraseña incorrecta", null);
            }
        } else {
            return new ProfesorLoginResponseDTO(false, "Correo no registrado", null);
        }
    }

}

