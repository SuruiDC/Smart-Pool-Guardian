package pe.upc.smartpoolguardian.schema.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DetalleMedicionDTO {

    @NotNull
    private double nivelCloro;

    @NotNull
    private double nivelPh;

    @NotNull
    private double temperatura;

    @NotNull
    private double nivelTurbidez;

    @NotNull
    private double alcalinidad;

    @NotNull
    private double durezaCalcio;

    @NotNull
    private boolean tieneAlgas;

    @NotNull
    private String colorPiscina;

    @NotNull
    private String olor;

    @NotNull
    private String tipoMedicion;

    @NotNull
    private int medicionId;

}
