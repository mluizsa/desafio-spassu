package com.spassu.livro.service;

import com.spassu.livro.dto.LivroDTO;
import com.spassu.livro.model.Assunto;
import com.spassu.livro.model.Autor;
import com.spassu.livro.model.Livro;
import com.spassu.livro.repository.AssuntoRepository;
import com.spassu.livro.repository.AutorRepository;
import com.spassu.livro.repository.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AssuntoRepository assuntoRepository;

    public List<LivroDTO> listarTodos() {
        return livroRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<LivroDTO> buscarPorId(Long id) {
        return livroRepository.findById(id).map(this::convertToDTO);
    }

    public LivroDTO salvar(@Valid LivroDTO dto) {
        Livro livro = convertToEntity(dto);
        return convertToDTO(livroRepository.save(livro));
    }

    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }



    private LivroDTO convertToDTO(Livro livro) {
        return new LivroDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getEditora(),
                livro.getEdicao(),
                livro.getAnoPublicacao(),
                livro.getValor(),
                livro.getAutores().stream().map(a -> a.getId()).collect(Collectors.toList()),
                livro.getAssuntos().stream().map(a -> a.getId()).collect(Collectors.toList())
        );
    }

    private Livro convertToEntity(LivroDTO dto) {
        Livro livro = new Livro();
        livro.setId(dto.id());
        livro.setTitulo(dto.titulo());
        livro.setEditora(dto.editora());
        livro.setEdicao(dto.edicao());
        livro.setAnoPublicacao(dto.anoPublicacao());
        livro.setValor(dto.valor());

        List<Autor> autores = autorRepository.findAllById(dto.autoresIds());
        List<Assunto> assuntos = assuntoRepository.findAllById(dto.assuntosIds());

        livro.setAutores(new HashSet<>(autores));
        livro.setAssuntos(new HashSet<>(assuntos));
        return livro;
    }
}
