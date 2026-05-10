package pe.upc.smartpoolguardian.schema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuariosInactivosDTO {
    private String nombreUsuario;
    private String email;
    private String numeroCelular;
    private String nombrePiscina;
    private LocalDate fechaUltimaMedicion;
}
