package com.spassu.livro.dto;

import com.spassu.livro.model.Livro;

public class LivroRelatorioDTO {
    private Long id;
    private String titulo;
    private String editora;
    private Integer edicao;
    private String anoPublicacao;
    private Double valor;

    public LivroRelatorioDTO(LivroDTO livro) {
        this.id = livro.id();
        this.titulo = livro.titulo();
        this.editora = livro.editora();
        this.edicao = livro.edicao();
        this.anoPublicacao = livro.anoPublicacao();
        this.valor = livro.valor();
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getEditora() { return editora; }
    public Integer getEdicao() { return edicao; }
    public String getAnoPublicacao() { return anoPublicacao; }
    public Double getValor() { return valor; }
}
