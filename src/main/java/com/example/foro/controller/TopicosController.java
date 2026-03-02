package com.example.foro.controller;

import com.example.foro.domain.topicos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepositorio repositorio;


    @Transactional
    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        if (repositorio.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            return ResponseEntity.badRequest().body("Error: este tópico ya existe dentro del foro");
        }

        var topico = new Topico(datos);
        repositorio.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));

    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> obtenerTopicos(
            @PageableDefault(size = 10, sort = {"fechaDeCreacion"}) Pageable paginacion){
        var page = repositorio.findAllByActivoTrue(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    //Obtener un topico en especifico
    @GetMapping("/{id}")
    public ResponseEntity topicoEspecifico(@PathVariable Long id){
        if (!repositorio.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        var topico = repositorio.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopicos datos){
        if (!repositorio.existsById(datos.id())){
            return ResponseEntity.notFound().build();
        }

        var topico = repositorio.getReferenceById(datos.id());
        topico.actualizarinformacion(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Optional<Topico> topicoOpcional = repositorio.findById(id);
        if (topicoOpcional.isPresent()) {
            repositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
