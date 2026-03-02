package com.pdv.entities;

import com.pdv.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "caixa")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "data_abertura")
    private LocalDateTime dataAbertura;

    @Column(nullable = false, name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @Column(nullable = false, name = "valor_abertura")
    private Double valorAbertura;

    @Column(nullable = false, name = "valor_fechamento")
    private Double valorFechamento;

    @Column(nullable = false, name = "total_vendas")
    private Double totalVendas;

    @Column(nullable = false, name = "total_sangria")
    private Double totalSangria;

    @Column(nullable = false, name = "status")
    private Status status; // "ABERTO" ou "FECHADO"

    @Column(columnDefinition = "TEXT") // Para armazenar JSON das sangrias
    private String sangriasJson;

    // --- Getters e Setters ---
    public Long getId() {
        return id;
    }

    // --- NOVOS MÉTODOS PARA SANGRIA ---
    public Double getTotalSangria() {
        return totalSangria != null ? totalSangria : 0.0;
    }

    public void setTotalSangria(Double totalSangria) {
        this.totalSangria = totalSangria;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public Double getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(Double valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public Double getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(Double valorFechamento) {
        this.valorFechamento = valorFechamento;
    }

    public Double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(Double totalVendas) {
        this.totalVendas = totalVendas;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getSangriasJson() {
        return sangriasJson;
    }

    public void setSangriasJson(String sangriasJson) {
        this.sangriasJson = sangriasJson;
    }

    //Seta valores antes de salvar (abertura de caixa)
    @PrePersist
    protected void abrirCaixa() {
        this.dataAbertura = LocalDateTime.now();
        this.status = Status.ABERTO;
    }

    //Seta valores antes de atualizar caixa aberto (para o fechamento)
    @PreUpdate
    protected void onClose() {
        if (status == Status.FECHADO) {
            this.dataFechamento = LocalDateTime.now();
        }
    }
}