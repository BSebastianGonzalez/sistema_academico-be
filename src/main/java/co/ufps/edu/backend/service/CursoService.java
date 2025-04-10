package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.*;
import co.ufps.edu.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private final CursoRepository cursoRepository;

    @Autowired
    private final AsignaturaRepository asignaturaRepository;

    @Autowired
    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    @Autowired
    private final SesionClaseRepository sesionClaseRepository;

    @Autowired
    private final ProfesorRepository profesorRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository, AsignaturaRepository asignaturaRepository, PeriodoAcademicoRepository periodoAcademicoRepository, SesionClaseRepository sesionClaseRepository, ProfesorRepository profesorRepository) {
        this.cursoRepository = cursoRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.periodoAcademicoRepository = periodoAcademicoRepository;
        this.sesionClaseRepository = sesionClaseRepository;
        this.profesorRepository = profesorRepository;
    }

    @Transactional(readOnly = true)
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Curso> obtenerCursosPorAsignatura(Asignatura asignatura) {
        return cursoRepository.findByAsignatura(asignatura);
    }

    @Transactional(readOnly = true)
    public List<Curso> obtenerCursosPorPeriodoAcademico(PeriodoAcademico periodoAcademico) {
        return cursoRepository.findByPeriodoAcademico(periodoAcademico);
    }

    @Transactional(readOnly = true)
    public List<Curso> obtenerCursosPorProfesor(Profesor profesor) {
        return cursoRepository.findByProfesor(profesor);
    }

    @Transactional(readOnly = true)
    public List<Curso> obtenerCursosConCupoDisponible() {
        return cursoRepository.findCursosConCupoDisponible();
    }

    @Transactional(readOnly = true)
    public List<Curso> obtenerCursosEnPeriodoActivo() {
        return cursoRepository.findCursosEnPeriodoActivo();
    }

    public Curso crearCurso(Curso curso) {
        // Verificar que la asignatura existe
        Asignatura asignatura = asignaturaRepository.findById(curso.getAsignatura().getCodigo())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        // Verificar que el periodo académico existe
        PeriodoAcademico periodo = periodoAcademicoRepository.findById(curso.getPeriodoAcademico().getId())
                .orElseThrow(() -> new RuntimeException("Periodo académico no encontrado"));

        // Verificar que la sesión de clase existe
        SesionClase clase = sesionClaseRepository.findById(curso.getClase().getId())
                .orElseThrow(() -> new RuntimeException("Sesión de clase no encontrada"));

        // Verificar que el profesor existe si se proporciona
        if (curso.getProfesor() != null && curso.getProfesor().getId() != null) {
            Profesor profesor = profesorRepository.findById(curso.getProfesor().getId())
                    .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
            curso.setProfesor(profesor);
        }

        // Inicializar cupo actual
        curso.setCupoActual((short) 0);

        // Establecer relaciones
        curso.setAsignatura(asignatura);
        curso.setPeriodoAcademico(periodo);
        curso.setClase(clase);

        return cursoRepository.save(curso);
    }

    public Curso actualizarCurso(Long id, Curso cursoDetails) {
        return cursoRepository.findById(id).map(curso -> {
            // Actualizar solo los campos que no son relaciones
            curso.setCupoMaximo(cursoDetails.getCupoMaximo());
            curso.setCupoActual(cursoDetails.getCupoActual());

            // Actualizar relaciones si se proporcionan
            if (cursoDetails.getAsignatura() != null && cursoDetails.getAsignatura().getCodigo() != 0) {
                Asignatura asignatura = asignaturaRepository.findById(cursoDetails.getAsignatura().getCodigo())
                        .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
                curso.setAsignatura(asignatura);
            }

            if (cursoDetails.getPeriodoAcademico() != null && cursoDetails.getPeriodoAcademico().getId() != null) {
                PeriodoAcademico periodo = periodoAcademicoRepository.findById(cursoDetails.getPeriodoAcademico().getId())
                        .orElseThrow(() -> new RuntimeException("Periodo académico no encontrado"));
                curso.setPeriodoAcademico(periodo);
            }

            if (cursoDetails.getClase() != null && cursoDetails.getClase().getId() != null) {
                SesionClase clase = sesionClaseRepository.findById(cursoDetails.getClase().getId())
                        .orElseThrow(() -> new RuntimeException("Sesión de clase no encontrada"));
                curso.setClase(clase);
            }

            if (cursoDetails.getProfesor() != null && cursoDetails.getProfesor().getId() != null) {
                Profesor profesor = profesorRepository.findById(cursoDetails.getProfesor().getId())
                        .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
                curso.setProfesor(profesor);
            }

            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso inscribirEstudiante(Long cursoId, Inscripcion inscripcion) {
        return cursoRepository.findById(cursoId).map(curso -> {

            // Verificar si hay cupo disponible
            if (curso.getCupoActual() >= curso.getCupoMaximo()) {
                throw new RuntimeException("No hay cupos disponibles en este curso");
            }

            // Incrementar el cupo actual
            curso.setCupoActual((short) (curso.getCupoActual() + 1));

            // Asignar la inscripción al curso
            curso.setInscripcion(inscripcion);

            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public Curso asignarEvaluacion(Long cursoId, Evaluacion evaluacion) {
        return cursoRepository.findById(cursoId).map(curso -> {
            curso.setEvaluacion(evaluacion);
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public Curso asignarRecurso(Long cursoId, RecursoAcademico recurso) {
        return cursoRepository.findById(cursoId).map(curso -> {
            curso.setRecurso(recurso);
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

}

