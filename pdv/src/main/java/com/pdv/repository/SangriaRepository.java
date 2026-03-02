package com.pdv.repository;

import com.pdv.entities.Caixa;
import com.pdv.entities.Sangria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SangriaRepository extends JpaRepository<Sangria, Long> {
    List<Sangria> findByCaixa(Caixa caixa);
}