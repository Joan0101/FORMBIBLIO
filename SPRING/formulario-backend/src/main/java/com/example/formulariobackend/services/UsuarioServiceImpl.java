package com.example.formulariobackend.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.formulariobackend.models.Usuario;
import com.example.formulariobackend.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario nuevoUsuario(Usuario nuevoUsuario) {
        try {
            if (nuevoUsuario.getDni() == null || nuevoUsuario.getDni().isEmpty()) {
                throw new IllegalArgumentException("DNI no puede estar vacío.");
            }

            nuevoUsuario.setEmail(nuevoUsuario.getEmail().toLowerCase());
            nuevoUsuario.setNombre(nuevoUsuario.getNombre().toUpperCase());
            nuevoUsuario.setApellido(nuevoUsuario.getApellido().toUpperCase());
            nuevoUsuario.setDomicilio(nuevoUsuario.getDomicilio().toUpperCase());
            nuevoUsuario.setLocalidad(nuevoUsuario.getLocalidad().toUpperCase());

            return usuarioRepository.save(nuevoUsuario);
        } catch (Exception e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Iterable<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario modificarUsuario(Usuario usuario) {
        // Suponiendo que tienes un repositorio para manejar los datos
        Usuario usuarioExistente = usuarioRepository.findById(usuario.getDni()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setTipousuario(usuario.getTipousuario());
        usuarioExistente.setLegajo(usuario.getLegajo());
        usuarioExistente.setTelefono(usuario.getTelefono());
        usuarioExistente.setDomicilio(usuario.getDomicilio());
        usuarioExistente.setLocalidad(usuario.getLocalidad());
        return usuarioRepository.save(usuarioExistente);
    }
    

    @Override
    public boolean verificarDni(String dni) {
        return usuarioRepository.existsById(dni);
    }

    @Override
    public Boolean eliminarUsuario(String dni) {
        try {
            usuarioRepository.deleteById(dni);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
