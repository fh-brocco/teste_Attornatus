package com.attornatus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository <Pessoa, Long>{
	public Optional<Pessoa> findByNome(String nome);
}
