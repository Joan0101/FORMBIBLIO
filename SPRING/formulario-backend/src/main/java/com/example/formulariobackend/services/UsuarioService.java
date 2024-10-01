package com.example.formulariobackend.services;

import com.example.formulariobackend.models.Usuario;

public interface UsuarioService { 
    Usuario nuevoUsuario(Usuario nuevoUsuario); // crear
    Iterable<Usuario> getAll(); // consultar (todos)
    Usuario modificarUsuario (Usuario usuario); // modificar
    Boolean eliminarUsuario (String dni); // Cambiado a String
    boolean verificarDni(String dni);
}
