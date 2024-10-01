package com.example.formulariobackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column
    @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener 8 dígitos y solo números")
    private String dni; // Cambiado a String

    @Column
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column
    @Email(message = "El formato de correo es inválido")
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$", message = "El correo no debe tener espacios")
    private String email;

    @Column
    @NotBlank(message = "El tipo de usuario es obligatorio")
    private String tipousuario;

    @Column
    @Pattern(regexp = "^\\d{4}$", message = "El legajo debe tener 4 dígitos")
    private String legajo;

    @Column
    @Pattern(regexp = "^\\d{8,15}$", message = "El teléfono debe tener entre 8 y 15 dígitos")
    private String telefono;

    @Column
    @NotBlank(message = "El domicilio es obligatorio")
    private String domicilio;

    @Column
    @NotBlank(message = "La localidad es obligatoria")
    private String localidad;

    public Usuario(String dni, String nombre, String apellido, String email, String tipousuario, String legajo,
                   String telefono, String domicilio, String localidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.tipousuario = tipousuario;
        this.legajo = legajo;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.localidad = localidad;
    }
}
