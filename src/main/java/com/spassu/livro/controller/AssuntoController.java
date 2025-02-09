package com.spassu.livro.controller;

import com.spassu.livro.dto.AssuntoDTO;
import com.spassu.livro.service.AssuntoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assuntos")
public class AssuntoController {

    private final AssuntoService assuntoService;

    @Autowired
    public AssuntoController(AssuntoService assuntoService) {
        this.assuntoService = assuntoService;
    }


    @GetMapping
    public List<AssuntoDTO> listarTodos() {
        return assuntoService.listarTodos();
    }

    @GetMapping("/{id}")
    public AssuntoDTO buscarPorId(@PathVariable Long id) {
        return assuntoService.buscarPorId(id);
    }

    @PostMapping
    public AssuntoDTO criar(@Valid @RequestBody AssuntoDTO dto) {
        return assuntoService.criar(dto);
    }

    @PutMapping("/{id}")
    public AssuntoDTO atualizar(@PathVariable Long id, @Valid @RequestBody AssuntoDTO dto) {
        return assuntoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        assuntoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
