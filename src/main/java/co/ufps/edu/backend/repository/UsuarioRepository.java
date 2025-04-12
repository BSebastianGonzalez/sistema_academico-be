package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoAndActivoTrue(String correo);
}
