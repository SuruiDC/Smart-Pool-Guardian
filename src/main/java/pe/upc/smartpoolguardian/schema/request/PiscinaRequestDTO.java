package pe.upc.smartpoolguardian.schema.request;

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
public class PiscinaRequestDTO {
    @NotBlank
    @NotNull
    private String nombrePiscina;
    @NotNull
    private double volumen;
}
