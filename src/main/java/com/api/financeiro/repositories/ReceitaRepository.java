package com.api.financeiro.repositories;

import com.api.financeiro.domain.Receita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
}