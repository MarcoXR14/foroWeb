package com.example.foro.domain.topicos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String autor,
        @NotBlank String curso,
        LocalDateTime fechaDeCreacion,
        Estado estado
) {
}
