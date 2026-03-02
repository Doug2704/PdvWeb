package com.pdv.repository;

import com.pdv.model.Caixa;
import com.pdv.model.Sangria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SangriaRepository extends JpaRepository<Sangria, Long> {
    List<Sangria> findByCaixa(Caixa caixa);
}