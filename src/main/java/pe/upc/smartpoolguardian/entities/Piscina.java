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
<<<<<<< HEAD
    private Integer piscinaId;
=======
    private int piscina_id;
>>>>>>> efc206b (Se crearon los endpoints de piscinas : buscar Piscinas por id, eliminar de piscinas, actualizar piscinas,listar piscinas por idusuario,registrar piscinas)

    @NotBlank
    @Column(name = "nombre_piscina",length = 50,nullable = false)
    private String nombrePiscina;
    @Column(name = "volumen",nullable = false)
    private double volumen;
    @Column(name = "eliminado", nullable = false)
<<<<<<< HEAD
    private boolean eliminado = false;

=======
    private boolean eliminado;
>>>>>>> efc206b (Se crearon los endpoints de piscinas : buscar Piscinas por id, eliminar de piscinas, actualizar piscinas,listar piscinas por idusuario,registrar piscinas)
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
