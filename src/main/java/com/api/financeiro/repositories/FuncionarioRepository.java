package com.api.financeiro.repositories;


import java.util.Optional;

import com.api.financeiro.domain.Funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
   Optional<Funcionario> findByEmail(String email);
}
