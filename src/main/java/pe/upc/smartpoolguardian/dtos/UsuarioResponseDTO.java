package pe.upc.smartpoolguardian.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioResponseDTO {
    private String nombre_usuario;
    private String email;
    private String numero_celular;
}
