package com.pdv.model;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "produto_id")
    private Long id;

    private String nome;
    private Double preco;

    // PADRONIZADO: Nome 'estoque' e tipo 'Integer' para evitar erro de Double
    private Integer estoque;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    // Getters e Setters corrigidos para 'estoque'
    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Produto() {
    }

    public Produto(String nome, Double preco, Integer estoque) {

        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }
}