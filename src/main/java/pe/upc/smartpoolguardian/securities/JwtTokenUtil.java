package pe.upc.smartpoolguardian.securities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil{


    private static final long TOKEN_VALIDITY = 2 * 60 * 60 * 1000; // 2 horas

    @Value("${jwt.secret}")
    private String secret;

    //Decode al token para obtener el username
    public String getUsernameFromToken(String token) {

        return getClaim(token, Claims::getSubject);

    }

    //Se obtiene la fecha de expiracion del token o mejor dicho la hora
    public Date getExpirationDate(String token) {

        return getClaim(token, Claims::getExpiration);

    }

    //Permite traer algo en especifico, ejemplo: Queremos traer la contraseña sin necesidad
    // de crear un metodo para descifrar solo la contraseña en el token
    public <T> T getClaim(String token, Function<Claims, T> resolver) {

        return resolver.apply(getAllClaims(token));

    }

    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Validador de existencia del token
    private boolean isExpired(String token) {

        return getExpirationDate(token).before(new Date());

    }

    //Aquí se genera el token con los userDetails, es decir sus roles, si esta activo(eliminado) y nombre
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        claims.put(
                "roles",
                userDetails.getAuthorities()
                        .stream()
                        .map(auth -> auth.getAuthority())
                        .collect(Collectors.joining(","))
        );

        return createToken(
                claims,
                userDetails.getUsername()
        );
    }

    private String createToken(Map<String, Object> claims, String username) {

        Date now = new Date();
        Date expiration = new Date(now.getTime() + TOKEN_VALIDITY);

        return Jwts.builder()//Se arma el token
                .setClaims(claims)//Roles
                .setSubject(username)//Usuario
                .setIssuedAt(now)//Fecha de emision
                .setExpiration(expiration)//Fecha de expiracion
                .signWith(//Se firma el token es decir se codifica o se encripta como sea
                        new SecretKeySpec(
                                Base64.getDecoder().decode(secret),
                                SignatureAlgorithm.HS512.getJcaName()
                        )
                )
                .compact();//Convierte todo en un string
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return getUsernameFromToken(token)
                .equals(userDetails.getUsername()) && !isExpired(token);
    }
}