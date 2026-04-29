package pe.upc.smartpoolguardian.schema.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionRequestDTO {
    private String tipoNotificacion;
    private String mensaje;
    private boolean leido;
    private LocalDate fechaCreacion;
    private LocalTime horaCreacion;
    private int usuarioId;
}
