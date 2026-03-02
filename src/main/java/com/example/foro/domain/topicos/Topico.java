package com.example.foro.domain.topicos;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String autor;
    private String curso;
    private LocalDateTime fechaDeCreacion = LocalDateTime.now();
    private Boolean activo = true;

    @Enumerated(EnumType.STRING)
    private Estado estado;

public Topico(DatosRegistroTopico datos){
    this.id = null;
    this.titulo = datos.titulo();
    this.mensaje = datos.mensaje();
    this.autor = datos.autor();
    this.curso = datos.curso();
    this.fechaDeCreacion = LocalDateTime.now();
    this.estado = Estado.POR_RESOLVER;
    this.activo = true;
}

    public void actualizarinformacion(@Valid DatosActualizarTopicos datos) {
    if (datos.titulo() != null) {
        this.titulo = datos.titulo();;
    }
    if (datos.mensaje() != null){
        this.mensaje = datos.mensaje();
    }
    if (datos.estado() != null){
        this.estado = datos.estado();
    }
    }
}
