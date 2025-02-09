package com.spassu.livro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutorDTO(
       Long id,

       @NotBlank(message = "O nome do autor é obrigatório")
       @Size(max = 40, message = "O nome deve ter no máximo 40 caracteres")
       String nome) {
}
