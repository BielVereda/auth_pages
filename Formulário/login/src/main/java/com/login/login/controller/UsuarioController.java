package com.login.login.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.login.login.dto.UsuarioResponseDTO;
import com.login.login.entity.Usuario;
import com.login.login.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user) {
        Usuario usuario = new Usuario(user.getName(), user.getEmail(), user.getPassword());
        usuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario user) {
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());

        if (findUser == null || !findUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Usuário ou senha inválidos");
        }

        return ResponseEntity.ok("Logado com sucesso!");
    }

    @GetMapping("/listar-dto")
    public List<UsuarioResponseDTO> listarUsuariosDTO() {
        return usuarioRepository.findAll().stream().map(UsuarioResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> usuariosPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok("Excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario novo) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setName(novo.getName());
            usuario.setPassword(novo.getPassword());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}