package com.login.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.login.entity.Usuario;
import com.login.login.repository.UsuarioRepository;

@CrossOrigin(origins = "http://127.0.0.1:5500/assets/pages/signin.html")
@RestController

public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping(value = "usuario/cadastro")
	public ResponseEntity<?> saveUser (@RequestBody Usuario user){
		Usuario usuario = new Usuario(user.getName(), user.getEmail(), user.getPassword());
		usuarioRepository.save(usuario);
		return ResponseEntity.ok("O usuário foi cadastrado com sucesso!");
	}
	
	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody Usuario user){
		
		Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
		if (findUser == null) {
			return ResponseEntity.ok("Usuário não encontrado");
		}else {
			if (findUser.getPassword().equals(user.getPassword())) {
				return ResponseEntity.ok("Usuário Logado!");
				}else {
					return ResponseEntity.ok("Senha incorrreta");
				}
		}
	}
}