package com.login.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO {

	@NotNull(message = "O usuario não pode ficar sem nome, sua anta!")
	private String name; 
	
	@NotBlank(message = "O seu burro, não tá vendo que o preço tá errado?")
	private String preco; 
	
	@Size(message = "Coloca a quantidade certa cabeção")
	private String quantidade;

	public ProdutoRequestDTO(String name, String preco, String quantidade) {
		this.name = name;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public ProdutoRequestDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
}