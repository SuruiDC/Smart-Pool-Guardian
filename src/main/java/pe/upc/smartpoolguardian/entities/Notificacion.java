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
    private Integer notificacionId;

    @Column(name = "tipo_notificacion", nullable = false)
    private String tipoNotificacion;

    @Column(name = "mensaje", length = 255 ,nullable = false)
    private String mensaje;

    @Column(name = "leido", nullable = false)
    private boolean leido;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
