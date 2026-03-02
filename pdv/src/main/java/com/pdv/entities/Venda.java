package com.pdv.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "venda_id")
    private Long id;

    // Valor final (já com desconto subtraído)
    @Column(nullable = false, name = "total")
    private Double total;

    // Valor do desconto aplicado
    @Column(nullable = false, name = "desconto")
    private Double desconto;

    // NOVO: Armazena DINHEIRO, PIX, CARTAO, etc.
    @Column(nullable = false, name = "forma_pagto")
    private String formaPagamento;

    @Column(nullable = false, name = "data_hora")
    private LocalDateTime dataHora;

    // Executa automaticamente antes de salvar no banco
    @PrePersist
    protected void prePersist() {
        this.dataHora = LocalDateTime.now();
    }

    public Venda() {
    }

    //inicia a venda com valor 0,0
    public Venda(String formaPagamento) {
        this.formaPagamento = formaPagamento;
        this.total = 0.0;
    }


    public void adicionarItem(ItemVenda itemVenda) {
        this.total += itemVenda.getSubTotal();
    }

    //getters e setters (apenas os necessários)
    public Long getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

}