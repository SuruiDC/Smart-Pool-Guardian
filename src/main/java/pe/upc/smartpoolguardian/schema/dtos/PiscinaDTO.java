package pe.upc.smartpoolguardian.schema.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PiscinaDTO {
    //dante cambia el nombre del dto y agrega otro dto si falta y aghrega sus atributos osea
    // lo que quieres mostrar , guiate de usuario si quieres

    private int piscina_id;
    private String nombre_piscina;
    private double volumen;
    private boolean eliminado;
    private int usuario_id;
}
