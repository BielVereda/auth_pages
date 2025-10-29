package com.login.login.controller;

import com.login.login.entity.Usuario;
import com.login.login.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario/cadastro")
    public ResponseEntity<String> cadastrar(@RequestBody Usuario usuario) {
        String resposta = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        String resposta = usuarioService.login(usuario);
        return ResponseEntity.ok(resposta);
    }
}