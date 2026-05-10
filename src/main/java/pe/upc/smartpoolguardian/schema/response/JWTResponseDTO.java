package pe.upc.smartpoolguardian.schema.response;

import java.io.Serializable;

public class JWTResponseDTO implements Serializable {

    private final String jwttoken;

    public String getJwttoken() {
        return jwttoken;
    }

    public JWTResponseDTO(String jwttoken) {
        super();
        this.jwttoken = jwttoken;
    }

}
