package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Notificacion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificacion_id;

    @Column(name = "tipo_notificacion", nullable = false)
    private String tipo_notificacion;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "leido", nullable = false)
    private boolean leido;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fecha_creacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario_id;
}
