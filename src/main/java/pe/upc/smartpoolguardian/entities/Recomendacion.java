package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Recomendacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recomendacionId;

    @Column(name = "descripcion", length = 255 ,nullable = false)
    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id")
    private Evaluacion evaluacion;

}
