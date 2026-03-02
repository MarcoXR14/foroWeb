package com.example.foro.domain.topicos;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        String curso,
        Estado estado,
        LocalDateTime fechaDeCrecaion
) {
    public DatosListaTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getEstado(),
                topico.getFechaDeCreacion());
    }
}
