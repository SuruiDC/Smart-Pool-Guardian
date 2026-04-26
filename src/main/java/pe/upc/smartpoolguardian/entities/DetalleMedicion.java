package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Detalle_Medicion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleMedicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detalle_medicion_id;

    @Column(name = "nivel_cloro",nullable = false)
    private double nivel_cloro;

    @Column(name = "nivel_ph",nullable = false)
    private double nivel_ph;

    @Column(name = "temperatura",nullable = false)
    private double temperatura;

    @Column(name = "nivel_turbidez", nullable = false)
    private double nivel_turbidez;

    @Column(name = "alcalinidad", nullable = false)
    private double alcalinidad;

    @Column(name = "dureza_calcio", nullable = false)
    private double dureza_calcio;

    @Column(name = "tiene_algas", nullable = false)
    private boolean tiene_algas;

    @Column(name = "color_piscina", nullable = false)
    private String color_piscina;

    @Column(name = "olor", nullable = false)
    private String olor;

    @Column(name = "tipo_medicion", nullable = false)
    private String tipo_medicion;

    @OneToOne
    @JoinColumn(name = "medicion_id")
    private Medicion medicion_id;

}
