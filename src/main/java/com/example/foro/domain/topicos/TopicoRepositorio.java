package com.example.foro.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepositorio extends JpaRepository<Topico, Long> {

    //Entidad que va a servir para verificar si existe un registro dentro de la DB con el mismo titulo y mensaje
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    //Entidad que va a servir para buscar dentro de ls DB un topico con un ID definido
    boolean existsById(Long id);

    //Entidad para buscar todos los topicos y paginarlos
    Page<Topico> findAllByActivoTrue(Pageable paginacion);
}
