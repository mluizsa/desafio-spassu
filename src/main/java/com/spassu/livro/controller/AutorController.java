package com.spassu.livro.controller;

import com.spassu.livro.dto.AutorDTO;
import com.spassu.livro.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public List<AutorDTO> listarTodos() {
        return autorService.listarTodos();
    }

    @GetMapping("/{id}")
    public AutorDTO buscarPorId(@PathVariable Long id) {
        return autorService.buscarPorId(id);
    }

    @PostMapping
    public AutorDTO criar(@Valid @RequestBody AutorDTO dto) {
        return autorService.criar(dto);
    }

    @PutMapping("/{id}")
    public AutorDTO atualizar(@PathVariable Long id, @Valid @RequestBody AutorDTO dto) {
        return autorService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        autorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
