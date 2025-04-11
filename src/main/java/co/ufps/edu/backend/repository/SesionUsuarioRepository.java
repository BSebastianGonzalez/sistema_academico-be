package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.SesionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionUsuarioRepository extends JpaRepository<SesionUsuario, Long> {
}
