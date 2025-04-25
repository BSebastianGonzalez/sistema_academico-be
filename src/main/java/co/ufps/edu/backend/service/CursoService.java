package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.CursoDTO;
import co.ufps.edu.backend.dto.CursoResponseDTO;
import co.ufps.edu.backend.dto.ProfesorInfoDTO;
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
import java.util.stream.Collectors;

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
        // Validar asignatura
        Asignatura asignatura = asignaturaRepository.findById(cursoDTO.getAsignaturaId())
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        // Validar periodo académico
        PeriodoAcademico periodo = periodoAcademicoRepository.findById(cursoDTO.getPeriodoAcademicoId())
                .orElseThrow(() -> new RuntimeException("Periodo académico no encontrado"));

        // Buscar profesor por nombre completo o ID
        Profesor profesor;
        if(cursoDTO.getProfesorId() != null) {
            profesor = profesorRepository.findById(cursoDTO.getProfesorId())
                    .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        } else {
            profesor = profesorRepository.findByNombreCompleto(cursoDTO.getNombreCompletoProfesor())
                    .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        }

        // Crear y guardar el curso
        Curso curso = new Curso();
        curso.setAsignatura(asignatura);
        curso.setPeriodoAcademico(periodo);
        curso.setCupoMaximo(cursoDTO.getCupoMaximo());
        curso.setCupoActual((short) 0);

        Set<Profesor> profesores = new HashSet<>();
        profesores.add(profesor);
        curso.setProfesores(profesores);

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

    public List<Curso> getCursosConCupoDisponible(int cupoMaximo) {
        return cursoRepository.findByCupoActualLessThan(cupoMaximo);
    }

    public CursoResponseDTO crearCursoConRespuesta(CursoDTO cursoDTO) {
        Curso cursoCreado = crearCurso(cursoDTO); // Reutiliza el método original
        return convertirACursoResponseDTO(cursoCreado);
    }

    public CursoResponseDTO convertirACursoResponseDTO(Curso curso) {
        CursoResponseDTO responseDTO = new CursoResponseDTO();
        responseDTO.setId(curso.getId());
        responseDTO.setNombreAsignatura(curso.getAsignatura().getNombre());
        responseDTO.setCupoMaximo(curso.getCupoMaximo());
        responseDTO.setCupoActual(curso.getCupoActual());

        // Mapear profesores con sus nombres completos
        List<ProfesorInfoDTO> profesoresDTO = curso.getProfesores().stream()
                .map(this::convertirAProfesorInfoDTO)
                .collect(Collectors.toList());
        responseDTO.setProfesores(profesoresDTO);

        return responseDTO;
    }

    private ProfesorInfoDTO convertirAProfesorInfoDTO(Profesor profesor) {
        ProfesorInfoDTO dto = new ProfesorInfoDTO();
        dto.setId(profesor.getId());

        // Construir nombre completo desde la relación con Usuario
        Usuario usuario = profesor.getUsuario();
        dto.setNombreCompleto(
                usuario.getNombre1() + " " +
                        (usuario.getNombre2() != null ? usuario.getNombre2() + " " : "") +
                        usuario.getApellido1() + " " +
                        usuario.getApellido2()
        );

        return dto;
    }

    @Transactional
    public Curso asignarProfesor(Long cursoId, Long profesorId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        // Lógica de asignación
        if (!curso.getProfesores().contains(profesor)) {
            curso.getProfesores().add(profesor);
            profesor.getCursos().add(curso);
        }

        return cursoRepository.save(curso);
    }

    @Transactional
    public void removerProfesor(Long cursoId, Long profesorId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        // Lógica de remoción
        if (curso.getProfesores().contains(profesor)) {
            curso.getProfesores().remove(profesor);
            profesor.getCursos().remove(curso);
        }
    }






}

