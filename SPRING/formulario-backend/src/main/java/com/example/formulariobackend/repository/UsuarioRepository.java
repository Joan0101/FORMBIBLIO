package com.example.formulariobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.formulariobackend.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    // No se requieren métodos adicionales aquí
}
