package com.attornatus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.exception.PessoaException;
import com.attornatus.model.Pessoa;
import com.attornatus.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/pessoas")
@CrossOrigin("*")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	public void validarDados(Pessoa pessoa) throws PessoaException {
		Optional<Pessoa> pessoaNome = pessoaRepository.findByNome(pessoa.getNome());

		if (pessoaNome.isPresent()) {
			throw new PessoaException();
		}
	}

	@GetMapping
	public ResponseEntity<List<Pessoa>> GetAll() {
		return ResponseEntity.ok(pessoaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> GetById(@PathVariable Long id) {
		return pessoaRepository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/criar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> post(@RequestBody Pessoa cliente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(cliente));
	}

	@PutMapping("/editar")
	public ResponseEntity<Pessoa> Put(@RequestBody Pessoa pessoa) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.save(pessoa));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
	}

	@ExceptionHandler({ PessoaException.class })
	public ResponseEntity<String> handleEmailInvalidaException(PessoaException e) {
		String exception = e.getMessage();
		return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
	}

}
