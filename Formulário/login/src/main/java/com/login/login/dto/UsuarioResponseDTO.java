package com.login.login.dto;

import com.login.login.entity.Usuario;

public class UsuarioResponseDTO {

    private Long id;
    private String name;
    private String email;

    public UsuarioResponseDTO() {
    }

    // ✅ Construtor que aceita um objeto Usuario
    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}