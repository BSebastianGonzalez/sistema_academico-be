package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.CursoDTO;
import co.ufps.edu.backend.model.*;
import co.ufps.edu.backend.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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


    public Curso crearCurso(CursoDTO cursoDTO) {

        Curso curso = new Curso();

        Asignatura asignatura = asignaturaRepository.findById(cursoDTO.getAsignaturaId())
                .orElseThrow(() -> new RuntimeException("Asignatura no existe"));

        PeriodoAcademico periodo = periodoAcademicoRepository.findById(cursoDTO.getPeriodoAcademicoId())
                .orElseThrow(() -> new RuntimeException("Periodo no existe"));

        curso.setAsignatura(asignatura);
        curso.setPeriodoAcademico(periodo);
        curso.setCupoMaximo(cursoDTO.getCupoMaximo());
        curso.setCupoActual((short) 0);

        Set<Profesor> profesores = new HashSet<>(profesorRepository.findAllById(cursoDTO.getProfesoresIds()));

        curso.setProfesores(profesores);

        // 5. Guardar
        return cursoRepository.save(curso);
    }

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


    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

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

