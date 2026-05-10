package pe.upc.smartpoolguardian.schema.request;

import lombok.Getter;
import lombok.Setter;
import pe.upc.smartpoolguardian.entities.Medicion;

import java.time.LocalDate;

@Getter
@Setter
public class EvaluacionRequestDTO {
    private LocalDate fechaCreacion;
    private String estadoGeneral;
    private String diagnostico;
}
