package pe.upc.smartpoolguardian.schema.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioResponseDTO {
    private String nombreUsuario;
    private String email;
    private String numeroCelular;
    private int rolId;
}
