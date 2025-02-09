package com.spassu.livro.controller;

import com.spassu.livro.dto.LivroRelatorioDTO;
import com.spassu.livro.service.LivroService;
import com.spassu.livro.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class RelatorioController {
    private final RelatorioService relatorioService;
    private final LivroService livroService;

    @GetMapping("/livros")
    public ResponseEntity<byte[]> gerarRelatorioLivros() {
        String nomeArquivo = relatorioService.gerarNomeArquivo();

        try {
            List<LivroRelatorioDTO> livros = livroService.listarTodos().stream()
                    .map(LivroRelatorioDTO::new)
                    .toList();

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("TITULO_RELATORIO", "Relatório de Livros");

            byte[] pdf = relatorioService.gerarRelatorioLivros(livros, parametros, nomeArquivo);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + nomeArquivo)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
