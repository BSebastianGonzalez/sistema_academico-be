package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoAndActivoTrue(String correo);

    @Query("SELECT u FROM Usuario u WHERE u.correo = :correo AND u.activo = true")
    Optional<Usuario> buscarPorCorreoActivo(@Param("correo") String correo);
}
