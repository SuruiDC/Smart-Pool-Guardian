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
    private Integer evaluacion_id;

    @Column(name = "estado_general", nullable = false)
    private String estado_general;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fecha_creacion;

    @Column(name = "diagnostico", nullable = false)
    private String diagnostico;

    @OneToOne
    @JoinColumn(name = "medicion_id")
    private Medicion medicion_id;

}
