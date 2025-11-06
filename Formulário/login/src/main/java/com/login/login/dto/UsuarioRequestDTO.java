package com.login.login.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

	@NotNull(message = "O nome não pode ser nulo, sua anta!")
	private String name; 
	
	@NotBlank(message = "'Vai dá merda isso...' - Capitão Nascimento")
	private String email; 
	
	@Size(min = 6, max = 20, message = "Cabaço... A senha tem que ter entre 6-20 caracteres.")
	private String password;

	public UsuarioRequestDTO(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public UsuarioRequestDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	
}