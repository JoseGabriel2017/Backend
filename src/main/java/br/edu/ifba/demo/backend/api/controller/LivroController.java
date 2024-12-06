package br.edu.ifba.demo.backend.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.demo.backend.api.repository.LivroRepository;
import br.edu.ifba.demo.backend.api.dto.LivroDTO;
import br.edu.ifba.demo.backend.api.model.LivroModel;

@RestController
@RequestMapping("/livro")
public class LivroController {

    private LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        super();
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public String teste() {
        return "Testando Rota Livro";
    }

    @GetMapping("/listall")
    public List<LivroDTO> listAll() {
        var livros = livroRepository.findAll();
        return LivroDTO.converter(livros);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable("id") Long id) {
        Optional<LivroModel> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return new ResponseEntity<>(LivroDTO.converter(livro.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByTitle/{titulo}")
    public ResponseEntity<List<LivroDTO>> getByTitle(@PathVariable("titulo") String titulo) {
        List<LivroModel> livros = livroRepository.findByTituloContainingIgnoreCase(titulo);
        if (!livros.isEmpty()) {
            return new ResponseEntity<>(LivroDTO.converter(livros), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByISBN/{isbn}")
    public LivroModel getByisbn(@PathVariable("isbn") String isbn) {
        Optional<LivroModel> livro = livroRepository.findByIsbn(isbn);
        if (livro.isPresent())
            return livro.get();
        return null;
    }

    @PostMapping
    public ResponseEntity<LivroModel> addLivro(@RequestBody LivroModel livro) {
        LivroModel savedLivro = livroRepository.save(livro);
        return new ResponseEntity<>(savedLivro, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroModel> deleteLivro(@PathVariable("id") Long id) {
        Optional<LivroModel> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            livroRepository.delete(livro.get());
            return new ResponseEntity<>(livro.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
