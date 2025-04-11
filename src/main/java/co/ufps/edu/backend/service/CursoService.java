package co.ufps.edu.backend.service;

import co.ufps.edu.backend.model.*;
import co.ufps.edu.backend.repository.*;
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
    private final AsignaturaService asignaturaService;

    public List<Curso> listarTodosCursos() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso crearCurso(Curso curso) {
        // Validar relaciones antes de guardar (ejemplo básico)
        if (curso.getAsignatura() == null || curso.getPeriodoAcademico() == null) {
            throw new IllegalArgumentException("Datos incompletos para crear el curso");
        }
        return cursoRepository.save(curso);
    }

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
            curso.setClase(cursoActualizado.getClase());
            curso.setProfesor(cursoActualizado.getProfesor());
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }







}

