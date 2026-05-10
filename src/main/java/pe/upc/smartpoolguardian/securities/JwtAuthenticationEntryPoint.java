package pe.upc.smartpoolguardian.securities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;//Interfaz de spring q se ejecuta cuando falla un aut
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        //En resumen es un manejador de errores para cuando no esta autorizado el usuario
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }
}
