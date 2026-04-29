package pe.upc.smartpoolguardian.schema.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PiscinaResponseDTO {
    @NotNull
    private int piscinaId;
    @NotNull
    @NotBlank
    private String nombrePiscina;
    @NotNull
    private double volumen;
    @NotNull
    private int usuarioId;
}
