package com.spassu.livro.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AssuntoDTO(
        Long id,

        @NotBlank(message = "A descrição do assunto é obrigatória")
        @Size(max = 20, message = "A descrição deve ter no máximo 20 caracteres")
        String descricao
) {
}
