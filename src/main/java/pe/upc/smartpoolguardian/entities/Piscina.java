package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
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
    private Integer piscina_id;

    @Column(name = "nombre_piscina",length = 50,nullable = false)
    private String nombre_piscina;
    @Column(name = "volumen",nullable = false)
    private double volumen;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario_id;
}
