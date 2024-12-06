package br.edu.ifba.demo.backend.api.model;

import lombok.Data;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "livro")
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLivro")
    private Long id_livro;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "autor", nullable = false)
    private String autor;

    @Column(name = "editora", nullable = true)
    private String editora;

    @Column(name = "anoPublicacao", nullable = true)
    private Integer ano_publicacao;

    @Column(name = "genero", nullable = true)
    private String genero;

    @Column(name = "isbn", nullable = true)
    private String isbn;

    @Column(name = "numPaginas", nullable = true)
    private Integer num_paginas;

    @Column(name = "sinopse", nullable = true)
    private String sinopse;

    @Column(name = "idioma", nullable = true)
    private String idioma;

    @Column(name = "dataCadastro", nullable = true)
    private Timestamp data_cadastro;

    @Column(name = "preco", nullable = true)
    private Double preco;

    public LivroModel(Long id_livro, String titulo, String autor, String editora, Integer ano_publicacao, String genero,
            String isbn, Integer num_paginas, String sinopse, String idioma, Timestamp data_cadastro, Double preco) {
        this.id_livro = id_livro;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano_publicacao = ano_publicacao;
        this.genero = genero;
        this.isbn = isbn;
        this.num_paginas = num_paginas;
        this.sinopse = sinopse;
        this.idioma = idioma;
        this.data_cadastro = data_cadastro;
        this.preco = preco;
    }
}
