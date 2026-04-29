package pe.upc.smartpoolguardian.schema.dtos;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicionDTO {

    @NotNull
    private LocalDate fechaMedicion;

    @NotNull
    private int idPiscina;

}