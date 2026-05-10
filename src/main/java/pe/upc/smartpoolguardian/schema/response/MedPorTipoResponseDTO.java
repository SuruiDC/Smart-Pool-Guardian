package pe.upc.smartpoolguardian.schema.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MedPorTipoResponseDTO {

    private int piscina_id;
    private String nombre_piscina;
    private LocalDate fecha_meicion;
    private String tipo_medicion;

}
