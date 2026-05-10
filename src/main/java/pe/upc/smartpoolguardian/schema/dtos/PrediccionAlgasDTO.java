package pe.upc.smartpoolguardian.schema.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrediccionAlgasDTO {

    private String nombrePiscina;
    private Double temperaturaActual;
    private Double nivelCloroActual;
    private String mensajeRiesgo;

    public String getNombrePiscina() {
        return nombrePiscina;
    }

    public void setNombrePiscina(String nombrePiscina) {
        this.nombrePiscina = nombrePiscina;
    }

    public String getMensajeRiesgo() {
        return mensajeRiesgo;
    }

    public void setMensajeRiesgo(String mensajeRiesgo) {
        this.mensajeRiesgo = mensajeRiesgo;
    }

    public Double getNivelCloroActual() {
        return nivelCloroActual;
    }

    public void setNivelCloroActual(Double nivelCloroActual) {
        this.nivelCloroActual = nivelCloroActual;
    }

    public Double getTemperaturaActual() {
        return temperaturaActual;
    }

    public void setTemperaturaActual(Double temperaturaActual) {
        this.temperaturaActual = temperaturaActual;
    }
}
