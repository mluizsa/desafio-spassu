package com.spassu.livro.service;

import com.spassu.exception.RecursoNaoEncontradoException;
import com.spassu.livro.dto.AssuntoDTO;
import com.spassu.livro.model.Assunto;
import com.spassu.livro.repository.AssuntoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssuntoService {

    @Autowired
    private AssuntoRepository assuntoRepository;

    public List<AssuntoDTO> listarTodos() {
        return assuntoRepository.findAll()
                .stream()
                .map(assunto -> new AssuntoDTO(assunto.getId(), assunto.getDescricao()))
                .toList();
    }

    public AssuntoDTO buscarPorId(Long id) {
        Assunto assunto = assuntoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assunto não encontrado"));
        return new AssuntoDTO(assunto.getId(), assunto.getDescricao());
    }

    public AssuntoDTO criar(@Valid AssuntoDTO dto) {
        Assunto assunto = new Assunto();
        assunto.setDescricao(dto.descricao());
        assunto = assuntoRepository.save(assunto);
        return new AssuntoDTO(assunto.getId(), assunto.getDescricao());
    }

    public AssuntoDTO atualizar(Long id, @Valid AssuntoDTO dto) {
        Assunto assunto = assuntoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Assunto não encontrado"));
        assunto.setDescricao(dto.descricao());
        assunto = assuntoRepository.save(assunto);
        return new AssuntoDTO(assunto.getId(), assunto.getDescricao());
    }

    public void excluir(Long id) {
        if (!assuntoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Assunto não encontrado");
        }
        assuntoRepository.deleteById(id);
    }
}
