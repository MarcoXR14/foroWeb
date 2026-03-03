package com.example.foro.controller;

import com.example.foro.domain.usuarios.DatosAutenticacion;
import com.example.foro.domain.usuarios.Usuarios;
import com.example.foro.infra.segurity.DatosTokenJWT;
import com.example.foro.infra.segurity.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loguearse")
public class LoginControler {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@Valid @RequestBody DatosAutenticacion datos){
        var autenticacionToken = new UsernamePasswordAuthenticationToken(datos.usuario(), datos.pass());
        var autenticacion = manager.authenticate(autenticacionToken);

        var tokenKWT = tokenService.generarToken((Usuarios) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenKWT));
    }
}
