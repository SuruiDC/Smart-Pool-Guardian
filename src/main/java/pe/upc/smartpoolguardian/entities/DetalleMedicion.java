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
    private Integer detalleMedicionId;

    @Column(name = "nivel_cloro",nullable = false)
    private double nivelCloro;

    @Column(name = "nivel_ph",nullable = false)
    private double nivelPh;

    @Column(name = "temperatura",nullable = false)
    private double temperatura;

    @Column(name = "nivel_turbidez", nullable = false)
    private double nivelTurbidez;

    @Column(name = "alcalinidad", nullable = false)
    private double alcalinidad;

    @Column(name = "dureza_calcio", nullable = false)
    private double durezaCalcio;

    @Column(name = "tiene_algas", nullable = false)
    private boolean tieneAlgas;

    @Column(name = "color_piscina", nullable = false)
    private String colorPiscina;

    @Column(name = "olor", nullable = false)
    private String olor;

    @Column(name = "tipo_medicion", nullable = false)
    private String tipoMedicion;

    @OneToOne
    @JoinColumn(name = "medicion_id")
    private Medicion medicion;

}
