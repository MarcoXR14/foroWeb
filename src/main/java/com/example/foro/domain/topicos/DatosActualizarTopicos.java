package com.example.foro.domain.topicos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosActualizarTopicos(
        @NotNull Long id,
        String titulo,
        String mensaje,
        Estado estado
) {
}
