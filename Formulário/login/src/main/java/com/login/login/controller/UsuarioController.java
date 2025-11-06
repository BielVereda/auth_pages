package com.login.login.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.login.entity.Usuario;
import com.login.login.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
@RestController
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping(value = "usuario/cadastro")
	public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user) {
		Usuario usuario = new Usuario(user.getName(), user.getEmail(), user.getPassword());
		usuario = usuarioRepository.save(usuario);
		return ResponseEntity.ok(usuario);
	}

	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody Usuario user) {
		Usuario findUser = usuarioRepository.findByEmail(user.getEmail());

		if (findUser == null) {
			return ResponseEntity.ok("Usuário não encontrado");
		} else {
			if (findUser.getPassword().equals(user.getPassword())) {
				return ResponseEntity.ok("Logado com sucesso!");
			}
			return ResponseEntity.ok("Essa senha pertence ao usuário com o email " + findUser.getEmail());
		}
	}

	@GetMapping(value = "fiama")
	public List<Usuario> listarUsuarios1() {
		List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
		return listaDeUsuarios;
	}

	@GetMapping(value = "vitinho")
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@GetMapping(value = "{id}")
	public Optional<Usuario> usuariosPorId(@PathVariable long id) {
		return usuarioRepository.findById(id);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		if (usuarioRepository.existsById(id)) {
			usuarioRepository.deleteById(id); // 204
			return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso!");
		} else { // 404
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse id não existe");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario novo) {
		Optional<Usuario> UsuarioExistente = usuarioRepository.findById(id);

		if (UsuarioExistente.isPresent()) {
			Usuario Usuario = UsuarioExistente.get();
			Usuario.setName(novo.getName());
			Usuario.setPassword(novo.getPassword());
			usuarioRepository.save(Usuario);
			return ResponseEntity.ok(Usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}