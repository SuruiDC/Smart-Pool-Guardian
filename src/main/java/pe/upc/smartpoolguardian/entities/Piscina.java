package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Piscina")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Piscina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer piscinaId;

    @NotBlank
    @Column(name = "nombre_piscina",length = 50,nullable = false)
    private String nombrePiscina;
    @Column(name = "volumen",nullable = false)
    private double volumen;
    @Column(name = "eliminado", nullable = false)
    private boolean eliminado = false;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
