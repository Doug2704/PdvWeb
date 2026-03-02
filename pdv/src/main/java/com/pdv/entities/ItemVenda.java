package com.pdv.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "item_venda") // Resolve o erro: Cannot resolve table

//removido preço unitário, o preço já consta em produto
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "item_venda_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venda_id") // Resolve o erro: Cannot resolve column 'venda_id'
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id") // Resolve o erro: Cannot resolve column 'produto_id'
    private Produto produto;

    @Column(nullable = false, name = "quantidade")
    private Double quantidade;

    @Column(nullable = false, name = "subtotal")
    private Double subTotal;

    public ItemVenda() {
    }

    public ItemVenda(Venda venda, Produto produto, Double quantidade) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.subTotal = quantidade * produto.getPreco();
    }

    public Long getId() {
        return id;
    }

    public Venda getVenda() {
        return venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getSubTotal() {
        return subTotal;
    }
}