package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Medicion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medicion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer medicion_id;

    @Column(name = "fecha_medicion", nullable = false)
    private LocalDate fecha_medicion;
    @ManyToOne
    @JoinColumn(name = "piscina_id")
    private Piscina piscina_id;
}
