package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

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
    @NotBlank
    @Column(name = "tipo_notificacion", nullable = false)
    private String tipoNotificacion;
    @NotBlank
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "leido", nullable = false)
    private boolean leido = false;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "hora_creacion", nullable = false)
    private LocalTime horaCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
