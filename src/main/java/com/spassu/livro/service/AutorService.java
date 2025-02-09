package com.spassu.livro.service;

import com.spassu.exception.RecursoNaoEncontradoException;
import com.spassu.livro.dto.AutorDTO;
import com.spassu.livro.model.Autor;
import com.spassu.livro.repository.AutorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDTO> listarTodos() {
        return autorRepository.findAll()
                .stream()
                .map(autor -> new AutorDTO(autor.getId(), autor.getNome()))
                .toList();
    }

    public AutorDTO buscarPorId(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Autor não encontrado"));
        return new AutorDTO(autor.getId(), autor.getNome());
    }

    public AutorDTO criar(@Valid AutorDTO dto) {
        Autor autor = new Autor();
        autor.setNome(dto.nome());
        autor = autorRepository.save(autor);
        return new AutorDTO(autor.getId(), autor.getNome());
    }

    public AutorDTO atualizar(Long id, @Valid AutorDTO dto) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Autor não encontrado"));
        autor.setNome(dto.nome());
        autor = autorRepository.save(autor);
        return new AutorDTO(autor.getId(), autor.getNome());
    }

    public void excluir(Long id) {
        if (!autorRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Autor não encontrado");
        }
        autorRepository.deleteById(id);
    }
}
