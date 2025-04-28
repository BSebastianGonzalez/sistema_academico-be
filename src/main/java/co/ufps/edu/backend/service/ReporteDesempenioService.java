package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.ReporteDesempenioDTO;
import co.ufps.edu.backend.model.Calificacion;
import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.model.Usuario;
import co.ufps.edu.backend.repository.CalificacionRepository;
import co.ufps.edu.backend.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteDesempenioService {

    private final EstudianteRepository estudianteRepository;
    private final CalificacionRepository calificacionRepository;

    public ReporteDesempenioDTO generarReporte(Long estudianteId) {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Usuario usuario = estudiante.getUsuario();
        List<Calificacion> calificaciones = calificacionRepository.findByEstudianteId(estudianteId);

        if (calificaciones.isEmpty()) {
            return new ReporteDesempenioDTO(
                    estudiante.getId(),
                    usuario.getNombre1() + " " + usuario.getNombre2() + " " + usuario.getApellido1() + " " + usuario.getApellido2(),
                    0,
                    0,
                    0
            );
        }

        float sumaNotas = 0;
        for (Calificacion calificacion : calificaciones) {
            sumaNotas += calificacion.getNota();
        }

        float promedio = sumaNotas / calificaciones.size();

        float proyeccion = promedio;

        return new ReporteDesempenioDTO(
                estudiante.getId(),
                usuario.getNombre1() + " " + usuario.getNombre2() + " " + usuario.getApellido1() + " " + usuario.getApellido2(),
                promedio,
                proyeccion,
                calificaciones.size()
        );
    }
}
