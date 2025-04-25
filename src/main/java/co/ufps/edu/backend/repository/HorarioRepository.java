package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Aula;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.DiaSemana;
import co.ufps.edu.backend.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {


    // 1) Conflictos por aula
    @Query("SELECT h FROM Horario h " +
            "WHERE h.aula.id = :aulaId " +
            "AND h.dia = :dia " +
            "AND h.horaInicio < :horaFin " +
            "AND h.horaFin > :horaInicio")
    List<Horario> findConflictsByAula(
            @Param("aulaId") Long aulaId,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin
    );

    // 2) Conflictos por profesor
    @Query("SELECT h FROM Horario h " +
            "WHERE h.profesor.id = :profesorId " +
            "AND h.dia = :dia " +
            "AND h.horaInicio < :horaFin " +
            "AND h.horaFin > :horaInicio")
    List<Horario> findConflictsByProfesor(
            @Param("profesorId") Long profesorId,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") LocalTime horaInicio,
            @Param("horaFin") LocalTime horaFin
    );

    // 3) Horarios de un curso en un día
    @Query("SELECT h FROM Horario h " +
            "WHERE h.curso.id = :cursoId " +
            "AND h.dia = :dia")
    List<Horario> findByCursoIdAndDia(
            @Param("cursoId") Long cursoId,
            @Param("dia") DiaSemana dia
    );

    // 4) Exámenes en un aula y día (usa el enum TipoHorario)
    @Query("SELECT h FROM Horario h " +
            "WHERE h.aula = :aula " +
            "AND h.dia = :dia " +
            "AND h.tipo = co.ufps.edu.backend.model.TipoHorario.EXAMEN")
    List<Horario> findExamenesByAulaAndDia(
            @Param("aula") Aula aula,
            @Param("dia") DiaSemana dia
    );

    // 5) Métodos derivados adicionales
    List<Horario> findByCursoId(Long cursoId);
    List<Horario> findByAulaAndDiaOrderByHoraInicioAsc(Aula aula, DiaSemana dia);
    List<Horario> findByCurso(Curso curso);
}
