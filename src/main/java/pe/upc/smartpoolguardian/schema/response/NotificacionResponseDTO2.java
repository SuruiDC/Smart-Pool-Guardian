package pe.upc.smartpoolguardian.schema.response;



import java.time.LocalDate;
import java.time.LocalTime;

public interface NotificacionResponseDTO2 {
    Integer getNotificacionId();
    String getTipoNotificacion();
    String getMensaje();
    boolean isLeido();
    LocalDate getFechaCreacion();
    LocalTime getHoraCreacion();
    int getUsuarioId();
}
