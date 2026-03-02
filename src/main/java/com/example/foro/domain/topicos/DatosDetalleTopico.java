package com.example.foro.domain.topicos;

import java.time.LocalDateTime;
import java.util.Date;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        LocalDateTime fechaDeCreacion,
        Estado estado
) {
    public DatosDetalleTopico (Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getFechaDeCreacion(),
                topico.getEstado()
        );
    }
}
