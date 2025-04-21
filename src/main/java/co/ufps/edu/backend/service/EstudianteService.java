package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.EstudianteDTO;
import co.ufps.edu.backend.model.Carrera;
import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.CarreraRepository;
import co.ufps.edu.backend.repository.EstudianteRepository;
import co.ufps.edu.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteService {
    @Autowired
    private final EstudianteRepository estudianteRepository;
    private final UsuarioRepository usuarioRepository;
    private final CarreraRepository carreraRepository;

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> getEstudianteById(Long id) {
        return estudianteRepository.findById(id);
    }

    public Estudiante createEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = new Estudiante();

        //Creando codigo de estudiante
        Usuario usuario = usuarioRepository.findById(estudianteDTO.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Carrera carrera = carreraRepository.findById(estudianteDTO.getIdCarrera())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));
        Long codCarrera = carrera.getId();
        Long cantidadEstudiantes = estudianteRepository.countByCarrera(carrera);
        Long id = (codCarrera * 10000) + (cantidadEstudiantes + 1);

        //Creando correo
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
}
