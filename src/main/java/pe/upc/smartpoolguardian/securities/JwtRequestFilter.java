package pe.upc.smartpoolguardian.securities;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.upc.smartpoolguardian.servicesimplements.JwtUserDetailsService;

import java.io.IOException;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;//Los detalles de usuario del token
    @Autowired
    private JwtTokenUtil jwtTokenUtil;//Es el que maneja todo el token

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");//Se obtiene el header con el token
        String username = null;
        String jwtToken = null;

        System.out.println(requestTokenHeader);

        //Se valida si se ha recibido un token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            //Se come 7 caracteres que al parecer no pertenecen al token
            jwtToken = requestTokenHeader.substring(7);
            try {

                //Se obtiene el usuario con el token, Les aconsejo probar: https://www.jwt.io
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException e) {

                //Log de que no se encontro el token
                System.out.println("No se puede encontrar el token JWT");

            } catch (ExpiredJwtException e) {

                //Log de token expirado
                System.out.println("Token JWT ha expirado");

            }
        } else {

            //Bearer es parte del header seria lo equivalente a decir
            //Authorization: Bearer(Estoy que les envio este token:) TOKEN
            logger.warn("JWT Token no inicia con la palabra Bearer");
            System.out.println(requestTokenHeader);

        }

        // Comprueba si existe autenticacion
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //Trae los detalles del usuario
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // Valida el token
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                //Aca se cree una instancia donde creamos un usuario ya autenticado, es decir que ya paso los filtros de seguridad
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //Aqui añadimos la metadata al request, metadata: inforamcion relevante para el request
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Aqui guardamos la autenticacion del usuario
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        //Aqui solo se sigue procesado el request
        chain.doFilter(request, response);
    }
}
