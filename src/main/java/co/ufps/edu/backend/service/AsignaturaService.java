package co.ufps.edu.backend.service;

import co.ufps.edu.backend.dto.AsignaturaDTO;
import co.ufps.edu.backend.model.Asignatura;
import co.ufps.edu.backend.model.Carrera;
import co.ufps.edu.backend.repository.AsignaturaRepository;
import co.ufps.edu.backend.repository.CarreraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AsignaturaService {

    @Autowired
    private final AsignaturaRepository asignaturaRepository;

    @Autowired
    private final CarreraRepository carreraRepository;


    //Listar Asignaturas
    public List<Asignatura> obtenerTodasLasAsignaturas() {
        return asignaturaRepository.findAll();
    }

    //Obtener Asignatura por Id
    public Optional<Asignatura> obtenerAsignaturaPorId(Long id) {
        return asignaturaRepository.findById(id);
    }

    //Obtener Asignatura por Codigo
    public Optional<Asignatura> obtenerAsignaturaPorCodigo(String codigo) {
        return asignaturaRepository.findByCodigo(codigo);
    }

    //Crear Asignatura
    public Asignatura crearAsignatura(AsignaturaDTO asignaturaDTO) {
        Carrera carrera = carreraRepository.findByCodigo(asignaturaDTO.getCodigoCarrera())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));


        Set<Asignatura> prerequisitos = new HashSet<>();
        if (asignaturaDTO.getCodigosPrerrequisitos() != null && !asignaturaDTO.getCodigosPrerrequisitos().isEmpty()) {
            for (String codigo : asignaturaDTO.getCodigosPrerrequisitos()) {
                Asignatura prerequisito = asignaturaRepository.findByCodigo(codigo)
                        .orElseThrow(() -> new RuntimeException("Prerrequisito no encontrado: " + codigo));
                prerequisitos.add(prerequisito);
            }

            int maxSemestre = prerequisitos.stream()
                    .mapToInt(Asignatura::getSemestre)
                    .max()
                    .orElse(0);

            if (asignaturaDTO.getSemestre() <= maxSemestre) {
                throw new RuntimeException("El semestre debe ser mayor al máximo de los prerrequisitos");
            }
        }

        if (asignaturaDTO.getSecuenciaAsignatura() == -1) {
            Long count = asignaturaRepository.countByCarreraAndPensumAndSemestre(
                    carrera,
                    asignaturaDTO.getPensum(),
                    asignaturaDTO.getSemestre()
            );

            asignaturaDTO.setSecuenciaAsignatura(count.intValue() + 1);
        }

        String codigo = generarCodigoAsignatura(
                asignaturaDTO.getCodigoCarrera(),
                asignaturaDTO.getPensum(),
                asignaturaDTO.getSemestre(),
                asignaturaDTO.getSecuenciaAsignatura()
        );

        Asignatura asignatura = new Asignatura();
        asignatura.setCodigoCarrera(asignaturaDTO.getCodigoCarrera());
        asignatura.setPensum(asignaturaDTO.getPensum());
        asignatura.setSemestre(asignaturaDTO.getSemestre());
        asignatura.setSecuenciaAsignatura(asignaturaDTO.getSecuenciaAsignatura());
        asignatura.setNombre(asignaturaDTO.getNombre());
        asignatura.setDescripcion(asignaturaDTO.getDescripcion());
        asignatura.setCreditos(asignaturaDTO.getCreditos());
        asignatura.setCarrera(carrera);
        asignatura.setPrerequisitos(prerequisitos);
        asignatura.setCreditosRequeridos(asignaturaDTO.getCreditosRequeridos());

        return asignaturaRepository.save(asignatura);
    }

    //Actualizar Asignatura
    public Asignatura actualizarAsignatura(String codigo, Asignatura asignaturaDetails) {
        return asignaturaRepository.findByCodigo(codigo).map(asignatura -> {
            asignatura.setNombre(asignaturaDetails.getNombre());
            asignatura.setDescripcion(asignaturaDetails.getDescripcion());
            asignatura.setCreditos(asignaturaDetails.getCreditos());
            return asignaturaRepository.save(asignatura);
        }).orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    //Eliminar Asignatura
    public void eliminarAsignatura(String codigo) {
        asignaturaRepository.deleteByCodigo(codigo);
    }

    //Generar Codigo Asignatura
    private String generarCodigoAsignatura(String codigoCarrera, int pensum, int semestre, int secuenciaAsignatura) {
        return codigoCarrera
                + pensum
                + String.format("%02d", semestre)
                + String.format("%02d", secuenciaAsignatura);
    }

    public boolean validarPrerrequisitos(
            String codigoAsignatura,
            Set<String> asignaturasAprobadas,
            int creditosAcumulados
    ) {
        // 1. Obtener la asignatura
        Asignatura asignatura = asignaturaRepository.findByCodigo(codigoAsignatura)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada: " + codigoAsignatura));

        // 2. Validar créditos requeridos (si aplica)
        if (asignatura.getCreditosRequeridos() != -1) {
            if (creditosAcumulados < asignatura.getCreditosRequeridos()) {
                return false;
            }
        }

        // 3. Validar códigos de prerrequisitos (si aplica)
        if (!asignatura.getCodigosPrerrequisitos().isEmpty()) {
            // Verificar que todas las asignaturas prerrequisito estén aprobadas
            boolean cumpleCodigos = asignaturasAprobadas.containsAll(asignatura.getCodigosPrerrequisitos());
            if (!cumpleCodigos) {
                return false;
            }
        }

        // 4. Si pasa todas las validaciones
        return true;
    }






}

