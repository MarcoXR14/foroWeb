package com.example.foro.domain.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepositorio extends JpaRepository <Usuarios, Long>{
    UserDetails findByUsuario(String usuario);
}
