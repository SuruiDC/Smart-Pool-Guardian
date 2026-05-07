package pe.upc.smartpoolguardian.schema.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EvaluacionPorFiltroDTO {
    private String estadoGeneral;
    private LocalDate fechaCreacion;
    private String diagnostico;
}
