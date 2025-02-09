package com.spassu.livro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record LivroDTO(
       Long id,

       @NotBlank(message = "O título é obrigatório")
       @Size(max = 40, message = "O título deve ter no máximo 40 caracteres")
       String titulo,

       @NotBlank(message = "A editora é obrigatória")
       @Size(max = 40, message = "A editora deve ter no máximo 40 caracteres")
       String editora,

       @NotNull(message = "A edição é obrigatória")
       Integer edicao,

       @NotBlank(message = "O ano de publicação é obrigatório")
       @Size(min = 4, max = 4, message = "O ano de publicação deve ter 4 dígitos")
       String anoPublicacao,

       @NotNull(message = "O valor do livro é obrigatório")
       Double valor,

       List<Long> autoresIds,
       List<Long> assuntosIds
) {}
