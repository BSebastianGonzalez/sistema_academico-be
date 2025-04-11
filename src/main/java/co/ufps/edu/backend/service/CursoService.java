package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.*;
import co.ufps.edu.backend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    @Autowired
    private final CursoRepository cursoRepository;

    @Autowired
    private final AsignaturaRepository asignaturaRepository;

    @Autowired
    private final PeriodoAcademicoRepository periodoAcademicoRepository;

    @Autowired
    private final ProfesorRepository profesorRepository;

    //Listar Cursos
    public List<Curso> listarTodosCursos() {
        return cursoRepository.findAll();
    }

    //Obtener Curso por Id
    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    /*

    public Curso crearCurso(Curso curso, Long asignaturaId, Long periodoId) {
        Optional<Asignatura> asignaturaOpt = asignaturaRepository.findById(asignaturaId);
        Optional<PeriodoAcademico> periodoOpt = periodoAcademicoRepository.findById(periodoId);

        if (asignaturaOpt.isPresent() && periodoOpt.isPresent()) {
            curso.setAsignatura(asignaturaOpt.get());
            curso.setPeriodoAcademico(periodoOpt.get());
            return Optional.of(cursoRepository.save(curso));
        }
        return Optional.empty();
    }
     */

    public Curso crearCurso(Curso curso) {
        // 1. Asignatura por código

        String codigo = curso.getAsignatura().getCodigo();

        Asignatura asignatura = asignaturaRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Asignatura " + codigo + " no encontrada"));

        // 2. PeriodoAcademico por nombre
        String nombrePeriodo = curso.getPeriodoAcademico().getNombre();

        PeriodoAcademico periodo = periodoAcademicoRepository.findByNombre(nombrePeriodo)
                .orElseThrow(() -> new EntityNotFoundException(
                        "PeriodoAcademico " + nombrePeriodo + " no encontrado"));

        // 3. Profesor por nombre (usamos el primero de la colección)
        if (curso.getProfesores().isEmpty()) {
            throw new IllegalArgumentException("Debe especificar al menos un profesor");
        }
        String nombreProf = curso.getProfesores().iterator().next().getNombre();
        Profesor profesor = profesorRepository.findByNombre(nombreProf)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Profesor " + nombreProf + " no encontrado"));

        // 4. Setear relaciones y valores
        curso.setAsignatura(asignatura);
        curso.setPeriodoAcademico(periodo);
        curso.setCupoActual((short) 0);

        curso.getProfesores().clear();
        curso.getProfesores().add(profesor);

        // 5. Guardar
        return cursoRepository.save(curso);
    }

    /*
    public Curso crearCurso(Curso curso) {
        // Validar relaciones antes de guardar (ejemplo básico)
        if (curso.getAsignatura() == null || curso.getPeriodoAcademico() == null) {
            throw new IllegalArgumentException("Datos incompletos para crear el curso");
        }
        return cursoRepository.save(curso);
    }
     */

    /*
    public Curso crearCurso(Curso curso) {
        Asignatura asignatura = asignaturaService.obtenerAsignaturaPorId(curso.getAsignatura().getCodigo());
        curso.setAsignatura(asignatura);
        return cursoRepository.save(curso);
    }
    */

    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setCupoMaximo(cursoActualizado.getCupoMaximo());
            curso.setCupoActual(cursoActualizado.getCupoActual());
            curso.setAsignatura(cursoActualizado.getAsignatura());
            curso.setClase(cursoActualizado.getClase());
            curso.setProfesores(cursoActualizado.getProfesores());
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public boolean eliminarCurso(Long id) {
        return cursoRepository.findById(id)
                .map(curso -> {
                    cursoRepository.delete(curso);
                    return true;
                })
                .orElse(false);
    }

    /*
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }
     */

    /*
    public boolean asignarProfesor(Long cursoId, Long profesorId) {
        Optional<Curso> cursoOpt = cursoRepository.findById(cursoId);
        Optional<Profesor> profesorOpt = profesorRepository.findById(profesorId);

        if (cursoOpt.isPresent() && profesorOpt.isPresent()) {
            Curso curso = cursoOpt.get();
            Profesor profesor = profesorOpt.get();

            if (!curso.getProfesores().contains(profesor)) {
                curso.getProfesores().add(profesor);
                profesor.getCursosAsignados().add(curso);
                cursoRepository.save(curso);
                return true;
            }
        }
        return false;
    }
     */






}

