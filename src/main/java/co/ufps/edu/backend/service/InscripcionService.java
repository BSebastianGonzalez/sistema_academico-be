package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.CursoDisponibleDTO;
import co.ufps.edu.backend.dto.InscripcionRequest;
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
import java.util.Set;
import java.util.stream.Collectors;

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


    public void realizarInscripcion(InscripcionRequest request) {
        Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Curso curso = cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Validar cupos
        if (curso.getCupoActual() >= curso.getCupoMaximo()) {
            throw new RuntimeException("Cupo lleno para este curso");
        }

        // Validar prerrequisitos
        validarPrerrequisitos(estudiante, curso.getAsignatura());

        // Crear inscripción
        Inscripcion nuevaInscripcion = new Inscripcion();
        nuevaInscripcion.setFechaInscripcion(new Date());
        nuevaInscripcion.setEstado("ACTIVA");
        nuevaInscripcion.setEstudiante(estudiante);
        nuevaInscripcion.setCurso(curso);

        // Actualizar cupo
        curso.setCupoActual(curso.getCupoActual() + 1);
        cursoRepository.save(curso);

        inscripcionRepository.save(nuevaInscripcion);
    }

    private void validarPrerrequisitos(Estudiante estudiante, Asignatura asignatura) {
        Set<Asignatura> asignaturasAprobadas = estudiante.getCalificaciones().stream()
                .filter(c -> c.getNota() >= 3.0)
                .map(c -> c.getEvaluacion().getCurso().getAsignatura())
                .collect(Collectors.toSet());

        if (!asignaturasAprobadas.containsAll(asignatura.getPrerequisitos())) {
            throw new RuntimeException("No cumple con los prerrequisitos");
        }
    }

    /*
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

     */

    public List<CursoDisponibleDTO> obtenerCursosDisponibles() {
        return cursoRepository.findAll().stream()
                .map(curso -> new CursoDisponibleDTO(
                        curso.getId(),
                        curso.getAsignatura().getNombre(),
                        curso.getAsignatura().getCodigo(),
                        curso.getCupoMaximo() - curso.getCupoActual(),
                        curso.getAsignatura().getPrerequisitos().stream()
                                .map(Asignatura::getCodigo)
                                .collect(Collectors.toSet())
                ))
                .toList();
    }


















}
