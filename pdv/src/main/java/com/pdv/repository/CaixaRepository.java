package com.pdv.repository;

import com.pdv.model.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    // Retorna o caixa pelo status (ABERTO ou FECHADO)
    Optional<Caixa> findByStatus(String status);

    // Retorna todos os caixas pelo status
    List<Caixa> findAllByStatus(String status);

    List<Caixa> findAllByStatusOrderByDataFechamentoDesc(String status);

    // Retorna o último caixa aberto (caso queira histórico futuramente)
    Optional<Caixa> findTopByStatusOrderByDataAberturaDesc(String status);
}