package com.pdv.entities;

import com.pdv.exception.ValorNegationException;
import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "produto_id")
    private Long id;

    @Column(nullable = false, name = "nome")
    private String nome;

    @Column(nullable = false, name = "preco")
    private Double preco;

    // PADRONIZADO: Nome 'estoque' e tipo 'Integer' para evitar erro de Double
    @Column(nullable = false, name = "estoque")
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
        if (preco < 0) {
            throw new ValorNegationException("preço", preco);
        }
        this.preco = preco;
    }

    // Getters e Setters corrigidos para 'estoque'
    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        if (estoque < 0) {
            throw new ValorNegationException("Estoque", estoque.doubleValue());
        }
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