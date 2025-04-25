package co.ufps.edu.backend.repository;

import co.ufps.edu.backend.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    Optional<Profesor> findByCorreo(String correo);

    @Query("SELECT p FROM Profesor p WHERE " +
            "CONCAT(p.usuario.nombre1, ' ', " +
            "COALESCE(p.usuario.nombre2, ''), ' ', " +
            "p.usuario.apellido1, ' ', " +
            "p.usuario.apellido2) = :nombreCompleto")
    Optional<Profesor> findByNombreCompleto(@Param("nombreCompleto") String nombreCompleto);

    // Método alternativo para buscar por ID de usuario
    @Query("SELECT p FROM Profesor p WHERE p.usuario.id = :usuarioId")
    Optional<Profesor> findByUsuarioId(@Param("usuarioId") Long usuarioId);

}
