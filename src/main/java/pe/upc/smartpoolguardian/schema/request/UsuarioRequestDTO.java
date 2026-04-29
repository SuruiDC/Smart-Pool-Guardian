package pe.upc.smartpoolguardian.schema.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String numeroCelular;

    private int rolId;
}
