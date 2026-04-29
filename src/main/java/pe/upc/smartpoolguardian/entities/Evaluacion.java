package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Evaluacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer evaluacionId;

    @Column(name = "estado_general", nullable = false)
    private String estadoGeneral;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "diagnostico", length = 255 ,nullable = false)
    private String diagnostico;

    @OneToOne
    @JoinColumn(name = "medicion_id")
    private Medicion medicion;

}
