package com.attornatus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long> {
	//public Optional<Endereco> findByEndereco (String logradouro,String cep,String numero, String cidade ); 
}
