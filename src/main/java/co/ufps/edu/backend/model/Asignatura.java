package co.ufps.edu.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Entity
@Table(name = "asignaturas")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Lob
    private String descripcion;

    @Positive(message = "Los créditos deben ser un valor positivo")
    private byte creditos;

    public Asignatura() {
    }

    public Asignatura(int codigo, String nombre, String descripcion, byte creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte getCreditos() {
        return creditos;
    }

    public void setCreditos(byte creditos) {
        this.creditos = creditos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return codigo == that.codigo && creditos == that.creditos && Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, descripcion, creditos);
    }

    @Override
    public String toString() {
        return "Asignatura{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", creditos=" + creditos +
                '}';
    }
}
