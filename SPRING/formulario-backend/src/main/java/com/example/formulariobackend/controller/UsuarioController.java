package com.example.formulariobackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.formulariobackend.models.Usuario;
import com.example.formulariobackend.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{dni}")
    public ResponseEntity<Boolean> verificarDni(@PathVariable String dni) {
        try {
            boolean exists = usuarioService.verificarDni(dni);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            System.out.println("Error al verificar DNI: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PostMapping(value = "/nuevo", consumes = "application/json")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario nuevoUsuario) { 
        try {
            Usuario usuarioCreado = this.usuarioService.nuevoUsuario(nuevoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
        } catch (Exception e) {
            System.out.println("Error al crear el usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/eliminar/{dni}")
    public ResponseEntity<Boolean> eliminarUsuario(@PathVariable(value = "dni") String dni) {
        try {
            boolean eliminado = this.usuarioService.eliminarUsuario(dni);
            return ResponseEntity.ok(eliminado);
        } catch (Exception e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/todos")
    public Iterable<Usuario> obtenerTodos() {
        return usuarioService.getAll();
    }

    @PutMapping("/actualizar") // Cambia POST a PUT
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario) {
    try {
        Usuario usuarioActualizado = this.usuarioService.modificarUsuario(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    } catch (Exception e) {
        System.out.println("Error al actualizar el usuario: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
}
