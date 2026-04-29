package pe.upc.smartpoolguardian.schema.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EvaluacionResponseDTO {
    private String estadoGeneral;
    private LocalDate fechaCreacion;
    private String diagnostico;
}
