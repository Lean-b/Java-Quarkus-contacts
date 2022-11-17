package com.usuario.microservicios.controller;

import com.usuario.microservicios.entidades.Usuario;
import com.usuario.microservicios.modelos.Avion;
import com.usuario.microservicios.modelos.Motos;
import com.usuario.microservicios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        List<Usuario> usuario = usuarioService.getAll();
        if (usuario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuario);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
            return  ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario newUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(newUsuario);
    }



    @GetMapping("/avion/{usuarioId}")
    public ResponseEntity<List<Avion>> listarAvion(@PathVariable("usuarioId") int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Avion> avion = usuarioService.getAvion(id);
        return  ResponseEntity.ok(avion);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Motos>> listarMotos(@PathVariable("usuarioId") int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Motos> motos = usuarioService.getMotos(id);
        return  ResponseEntity.ok(motos);
    }



}
