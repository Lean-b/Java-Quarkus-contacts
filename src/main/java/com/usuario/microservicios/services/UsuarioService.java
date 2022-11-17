package com.usuario.microservicios.services;

import com.usuario.microservicios.entidades.Usuario;
import com.usuario.microservicios.modelos.Avion;
import com.usuario.microservicios.modelos.Motos;
import com.usuario.microservicios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;
    final String ROOT_URI = "https://localhost:8002/avion/usuario";
    final String ROOT_URI2 = "https://localhost:8003/motos/usuario";

    public List<Avion> getAvion(int usuarioId) {
        List<Avion> avion =  restTemplate.getForObject(ROOT_URI + usuarioId, List.class);
        return  avion;
    }

    public List<Motos> getMotos(int usuarioId) {
        List<Motos> motos =  restTemplate.getForObject(ROOT_URI2 + usuarioId, List.class);
        return  motos;
    }

    @Autowired
    private UsuarioRepository usuarioRepository;
    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }



}
