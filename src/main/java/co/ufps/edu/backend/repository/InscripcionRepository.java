package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.Estudiante;
import co.ufps.edu.backend.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByEstudianteAndCursoAsignaturaAndEstado(Estudiante estudiante, Asignatura asignatura, String estado);
}
