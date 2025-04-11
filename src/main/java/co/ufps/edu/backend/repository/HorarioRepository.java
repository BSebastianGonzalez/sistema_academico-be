package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Aula;
import co.ufps.edu.backend.model.Curso;
import co.ufps.edu.backend.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByAulaAndDia(Aula aula, String dia);
    List<Horario> findByCurso(Curso curso);
}
