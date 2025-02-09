package com.spassu.livro.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "livro")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String editora;

    private Integer edicao;

    @Column(name="ano_publicacao")
    private String anoPublicacao;

    private Double valor;

    @ManyToMany
    @JoinTable(name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "livro_assunto",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "assunto_id"))
    private Set<Assunto> assuntos = new HashSet<>();
}
