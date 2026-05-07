package pe.upc.smartpoolguardian.schema.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuariosInactivosDTO {
    private String nombreUsuario;
    private String email;
    private String numeroCelular;
    private String nombrePiscina;
    private LocalDateTime fechaUltimaMedicion;
}
