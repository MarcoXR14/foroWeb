package com.example.foro.infra.segurity;

import com.example.foro.domain.usuarios.UsuarioRepositorio;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//OncePerRequestFilter es el encargado de ejecutar los filtros por cada request que recibe
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null){
            var subjet = tokenService.getSubject(tokenJWT);
            var usuario = usuarioRepositorio.findByUsuario(subjet);

            // Con el siguiente código, forzamos a Spring a autenticar al usuario para que no tengamos que pasar el token en cada HttpRequest
            var authenticacion = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticacion);
        }

        filterChain.doFilter(request, response); //Este permite seguir con la cadena de filtros, si no se ecnuentra, se bloquea
    }

    /*
        Obtenemos el encabezado (header):
        Si el encabezado tiene algun dato (un token), lo obtiene y eliminamos Bearer (dato que viene antes del JWT)
        Si no tiene contenido, regresa un null y una exepcion de que no se encontró el token
     */
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
