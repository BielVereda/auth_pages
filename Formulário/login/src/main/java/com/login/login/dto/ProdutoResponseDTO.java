package com.login.login.dto;

public class ProdutoResponseDTO {

    private Long id;
    private String name;
    private double preco;
    private int quantidade;
    private double subTotal;

    public ProdutoResponseDTO(Long id, String name, double preco, int quantidade) {
        this.id = id;
        this.name = name;
        this.preco = preco;
        this.quantidade = quantidade;
        this.subTotal = preco * quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreco(double preco) {
        this.preco = preco;
        this.subTotal = preco * this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.subTotal = this.preco * quantidade;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}