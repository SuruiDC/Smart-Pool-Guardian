package pe.upc.smartpoolguardian.schema.dtos;

import lombok.Data;

@Data
public class PrediccionAlgasDTO {

    private String nombrePiscina;
    private Double temperaturaActual;
    private Double nivelCloroActual;
    private String mensajeRiesgo;
}
