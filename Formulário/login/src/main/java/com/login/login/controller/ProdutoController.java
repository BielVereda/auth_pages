package com.login.login.controller;

import com.login.login.dto.ProdutoRequestDTO;
import com.login.login.dto.ProdutoResponseDTO;
import com.login.login.entity.Produto;
import com.login.login.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/cadastro")
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        Produto novoProduto = new Produto(produto.getName(), produto.getPreco(), produto.getQuantidade());
        novoProduto = produtoRepository.save(novoProduto);
        return ResponseEntity.ok(novoProduto);
    }

    @PostMapping("/cadastro-dto")
    public ResponseEntity<ProdutoResponseDTO> cadastrarProdutoComDTO(@RequestBody ProdutoRequestDTO dto) {
        Produto produto = new Produto(
            dto.getName(),
            Double.parseDouble(dto.getPreco()),
            Integer.parseInt(dto.getQuantidade())
        );
        Produto salvo = produtoRepository.save(produto);
        ProdutoResponseDTO response = new ProdutoResponseDTO(
            salvo.getId(), salvo.getName(), salvo.getPreco(), salvo.getQuantidade()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar")
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/listar-dto")
    public List<ProdutoResponseDTO> listarProdutosDTO() {
        return produtoRepository.findAll().stream()
            .map(p -> new ProdutoResponseDTO(p.getId(), p.getName(), p.getPreco(), p.getQuantidade()))
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.isPresent()
            ? ResponseEntity.ok(produto.get())
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
    }

    @GetMapping("/dto/{id}")
public ResponseEntity<ProdutoResponseDTO> buscarPorIdDTO(@PathVariable Long id) {
    Optional<Produto> produtoOptional = produtoRepository.findById(id);

    if (produtoOptional.isPresent()) {
        Produto produto = produtoOptional.get();
        ProdutoResponseDTO response = new ProdutoResponseDTO(
            produto.getId(),
            produto.getName(),
            produto.getPreco(),
            produto.getQuantidade()
        );
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody Produto novo) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setName(novo.getName());
            produto.setPreco(novo.getPreco());
            produto.setQuantidade(novo.getQuantidade());
            produtoRepository.save(produto);
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @PutMapping("/dto/{id}")
    public ResponseEntity<?> atualizarProdutoDTO(@PathVariable Long id, @RequestBody ProdutoRequestDTO dto) {
        Optional<Produto> existente = produtoRepository.findById(id);
        if (existente.isPresent()) {
            Produto produto = existente.get();
            produto.setName(dto.getName());
            produto.setPreco(Double.parseDouble(dto.getPreco()));
            produto.setQuantidade(Integer.parseInt(dto.getQuantidade()));
            Produto atualizado = produtoRepository.save(produto);
            ProdutoResponseDTO response = new ProdutoResponseDTO(
                atualizado.getId(), atualizado.getName(), atualizado.getPreco(), atualizado.getQuantidade()
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok("Produto excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @GetMapping("/buscar/{name}")
    public ResponseEntity<?> buscarPorNome(@PathVariable String name) {
        Produto produto = produtoRepository.findByName(name);
        return produto != null
            ? ResponseEntity.ok(produto)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto com nome '" + name + "' não encontrado");
    }

    @GetMapping("/buscar-dto/{name}")
    public ResponseEntity<?> buscarPorNomeDTO(@PathVariable String name) {
        Produto produto = produtoRepository.findByName(name);
        if (produto != null) {
            ProdutoResponseDTO response = new ProdutoResponseDTO(
                produto.getId(), produto.getName(), produto.getPreco(), produto.getQuantidade()
            );
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto com nome '" + name + "' não encontrado");
        }
    }
}
