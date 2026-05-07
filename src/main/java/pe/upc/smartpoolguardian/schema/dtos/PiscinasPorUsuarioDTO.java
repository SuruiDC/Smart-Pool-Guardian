package pe.upc.smartpoolguardian.schema.dtos;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


public interface PiscinasPorUsuarioDTO {
    int getPiscinaId();
    String getNombrePiscina();
    double getVolumen();
}
