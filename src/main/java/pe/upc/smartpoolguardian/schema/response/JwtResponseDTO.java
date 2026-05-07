package pe.upc.smartpoolguardian.schema.response;

public class JwtResponseDTO {
    private final String jwttoken;

    public String getJwttoken() {
        return jwttoken;
    }

    public JwtResponseDTO(String jwttoken) {
        super();
        this.jwttoken = jwttoken;
    }
}
