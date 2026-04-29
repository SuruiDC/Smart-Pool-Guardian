package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Notificacion;
import pe.upc.smartpoolguardian.schema.response.NotificacionResponseDTO2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface INotificacionService {
    public Notificacion crearNotificacion(Notificacion notificacion);
    public Notificacion findById(int id);
    public List<NotificacionResponseDTO2> listarPorUsuarioId(int usuarioId);
    public Notificacion actualizarLeido (Notificacion  notificacion);
    public List<NotificacionResponseDTO2> listarLeidosPorUsuario(int usuarioId);
    public List<NotificacionResponseDTO2> listarNoLeidosPorUsuario(int usuarioId);
    public List<NotificacionResponseDTO2> listarPorFechayHora(int usuarioId, LocalDate fecha, LocalTime hora);
}
