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
public class RecomendacionRequestDTO {

    @NotBlank
    @NotNull
    private String mensaje;

    @NotNull
    private Integer evaluacionId;
}
