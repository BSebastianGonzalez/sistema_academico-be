package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.EstudianteDTO;
import co.ufps.edu.backend.dto.EstudianteLoginResponseDTO;
import co.ufps.edu.backend.model.Carrera;
import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.CarreraRepository;
import co.ufps.edu.backend.repository.EstudianteRepository;
import co.ufps.edu.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    @Autowired
    private final EstudianteRepository estudianteRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final CarreraRepository carreraRepository;

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> getEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    public Estudiante createEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();

        Usuario usuario = usuarioRepository.findById(estudianteDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Carrera carrera = carreraRepository.findById(estudianteDTO.getIdCarrera())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        Long codCarrera = carrera.getId();
        Long cantidadEstudiantes = estudianteRepository.countByCarrera(carrera);
        Long id = (codCarrera * 10000) + (cantidadEstudiantes + 1);

        String apellido1 = usuario.getApellido1();
        String apellido2 = usuario.getApellido2();
        String nombre1 = usuario.getNombre1().toLowerCase();
        String nombre2 = usuario.getNombre2().toLowerCase();

        String correo = nombre1 + nombre2 + Character.toLowerCase(apellido1.charAt(0)) +
                Character.toLowerCase(apellido2.charAt(0)) + "@ufps.edu.co";

        estudiante.setId(id);
        estudiante.setSemestre(estudianteDTO.getSemestre());
        estudiante.setCreditos(0);
        estudiante.setActivo(true);
        estudiante.setCorreo(correo);
        estudiante.setContrasenia(estudianteDTO.getContrasenia());
        estudiante.setUsuario(usuario);
        estudiante.setCarrera(carrera);

        return estudianteRepository.save(estudiante);
    }


    public Estudiante updateEstudiante(Long id, Estudiante estudianteDetails) {
        return estudianteRepository.findById(id).map(estudiante -> {
            estudiante.setSemestre(estudianteDetails.getSemestre());
            estudiante.setUsuario(estudianteDetails.getUsuario());
            return estudianteRepository.save(estudiante);
        }).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
    }

    public void deleteEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    public Estudiante desactivarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        estudiante.setActivo(false);
        return estudianteRepository.save(estudiante);
    }

    public EstudianteLoginResponseDTO loginEstudiante(Long id, String correo, String contrasenia) {
        Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);

        if (estudianteOptional.isEmpty()) {
            return new EstudianteLoginResponseDTO(false, "Código de estudiante incorrecto", null);
        }

        Estudiante estudiante = estudianteOptional.get();

        if (!estudiante.getCorreo().equals(correo)) {
            return new EstudianteLoginResponseDTO(false, "Correo electrónico incorrecto", null);
        }

        if (!estudiante.getContrasenia().equals(contrasenia)) {
            return new EstudianteLoginResponseDTO(false, "Contraseña incorrecta", null);
        }

        estudiante.setUltimoAcceso(new Date());
        estudianteRepository.save(estudiante);

        String horaAcceso = "Hora de inicio de sesión: " + estudiante.getUltimoAcceso();
        return new EstudianteLoginResponseDTO(true, "Inicio de sesión exitoso", horaAcceso);
    }
}
