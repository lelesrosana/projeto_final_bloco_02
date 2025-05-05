package com.generation.final02.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.generation.final02.model.Produto;
import com.generation.final02.repository.CategoriaRepository;
import com.generation.final02.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

@RestController // Indica que esta classe é um Controller
@RequestMapping("/produto") // Define o caminho base para as requisições a este controller
@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite requisições de diferentes domínios
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/produto/{nome}") 
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) { 
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome)); 
    }

    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) { 
        if (produto.getCategoria() == null || !categoriaRepository.existsById(produto.getCategoria().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) { 
        if (produtoRepository.existsById(produto.getId())) {
            if (produto.getCategoria() == null || !categoriaRepository.existsById(produto.getCategoria().getId())) {
                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
            }
            return ResponseEntity.ok(produtoRepository.save(produto));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id); 
        if (produto.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        produtoRepository.deleteById(id); 
    }
}

