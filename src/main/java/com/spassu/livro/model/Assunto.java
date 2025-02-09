package com.spassu.livro.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assunto")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="descricao")
    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    private Set<Livro> livros = new HashSet<>();
}
