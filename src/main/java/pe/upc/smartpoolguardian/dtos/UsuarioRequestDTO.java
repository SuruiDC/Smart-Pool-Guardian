package pe.upc.smartpoolguardian.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {
    @NotBlank
    private String nombre_usuario;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String numero_celular;
}
