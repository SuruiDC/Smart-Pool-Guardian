package pe.upc.smartpoolguardian.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


@Configuration
@EnableWebSecurity//Activa la seguridad web en la apliacion
@EnableMethodSecurity//Activa anotaciones que comprueba por roles como @PreAuthorize("hasRole('ADMIN')"), @Secured("ROLE_ADMIN")
public class WebSecurityConfig {

    //Manejador de errores
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    //Cargador de usuarios desde la DB
    @Autowired
    private UserDetailsService jwtUserDetailsService;

    //Decodifica el token
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    //Manejador de errores
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver exceptionResolver;

    //Autentica usuario y contraseña
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Encriptador de contraseñas
    @Bean
    public static PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    //Indica como se autenticaran los usuarios
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService)
                .passwordEncoder(passwordEncoder());//Usa BCrypt para validar passwords
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //Desde Spring Boot 3.1+
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)//Desactiva el CSRF ya que no lo usaremos
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/login").permitAll()//Aqui excluye el endpoint login de los endpoints privados
                        .anyRequest().authenticated()//Aca indica que cualquier otro endpoint es por autenticacion
                )
                .httpBasic(Customizer.withDefaults())//Segun gpt esto no sirve xd
                .formLogin(AbstractHttpConfigurer::disable)//Desactiva la pag de login automatica
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(Customizer.withDefaults());//Queda en revision esto
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
