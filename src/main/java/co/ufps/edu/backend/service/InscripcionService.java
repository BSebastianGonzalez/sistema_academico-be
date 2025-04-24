package co.ufps.edu.backend.service;

import co.ufps.edu.backend.exception.ConflictException;
import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.model.Inscripcion;
import co.ufps.edu.backend.repository.AsignaturaRepository;
import co.ufps.edu.backend.repository.CursoRepository;
import co.ufps.edu.backend.repository.EstudianteRepository;
import co.ufps.edu.backend.repository.InscripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    @Autowired
    private final InscripcionRepository inscripcionRepository;

    @Autowired
    private final CursoRepository cursoRepository;

    @Autowired
    private final EstudianteRepository estudianteRepository;

    @Autowired
    private final AsignaturaRepository asignaturaRepository;


    public Inscripcion inscribirEstudiante(Long estudianteId, Long cursoId) {
        // Validar existencia
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Validar cupo
        if (curso.getCupoActual() >= curso.getCupoMaximo()) {
            throw new ConflictException("El curso no tiene cupos disponibles");
        }

        // Validar prerrequisitos
        Asignatura asignatura = curso.getAsignatura();
        for (Asignatura prerrequisito : asignatura.getPrerequisitos()) {
            List<Inscripcion> aprobaciones = inscripcionRepository.findByEstudianteAndCursoAsignaturaAndEstado(
                    estudiante,
                    prerrequisito,
                    "APROBADO"
            );
            if (aprobaciones.isEmpty()) {
                throw new ConflictException("Prerrequisito no cumplido: " + prerrequisito.getNombre());
            }
        }

        // Crear inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setCurso(curso);
        inscripcion.setEstado("INSCRITO");
        inscripcion.setFechaInscripcion(new Date());

        // Actualizar cupo
        curso.setCupoActual(curso.getCupoActual() + 1);
        cursoRepository.save(curso);

        return inscripcionRepository.save(inscripcion);
    }

    public void cancelarInscripcion(Long inscripcionId) {
        Inscripcion inscripcion = inscripcionRepository.findById(inscripcionId)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        if (!"INSCRITO".equals(inscripcion.getEstado())) {
            throw new ConflictException("La inscripción no puede cancelarse");
        }

        // Actualizar cupo
        Curso curso = inscripcion.getCurso();
        curso.setCupoActual(curso.getCupoActual() - 1);
        cursoRepository.save(curso);

        // Cancelar
        inscripcion.setEstado("CANCELADO");
        inscripcionRepository.save(inscripcion);
    }


















}
