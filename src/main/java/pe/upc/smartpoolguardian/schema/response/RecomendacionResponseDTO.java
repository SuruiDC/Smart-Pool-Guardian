package pe.upc.smartpoolguardian.schema.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecomendacionResponseDTO {

    private Integer recomendacionId;
    private String mensaje;
    private Integer evaluacionId;
}
