package pe.upc.smartpoolguardian.schema.response;

import java.io.Serializable;

public class JWTResponDTO implements Serializable {

    private final String jwttoken;

    public String getJwttoken() {
        return jwttoken;
    }

    public JwtResponseDTO(String jwttoken) {
        super();
        this.jwttoken = jwttoken;
    }

}
