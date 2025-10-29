package com.login.login.service;

import com.login.login.entity.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private static final String FILE_PATH = "src/main/resources/usuarios.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Lê todos os usuários do arquivo
    public List<Usuario> listarUsuarios() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Salva lista de usuários no arquivo
    private void salvarUsuarios(List<Usuario> usuarios) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cadastrar novo usuário
    public String cadastrarUsuario(Usuario novo) {
        List<Usuario> usuarios = listarUsuarios();

        // Evita emails duplicados
        boolean existe = usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(novo.getEmail()));
        if (existe) return "Email já cadastrado!";

        usuarios.add(novo);
        salvarUsuarios(usuarios);
        return "Usuário cadastrado com sucesso!";
    }

    // Login
    public String login(Usuario tentativa) {
        List<Usuario> usuarios = listarUsuarios();

        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(tentativa.getEmail())) {
                if (u.getPassword().equals(tentativa.getPassword())) {
                    return "Usuário logado!";
                } else {
                    return "Senha incorreta!";
                }
            }
        }
        return "Usuário não encontrado!";
    }
}