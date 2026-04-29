package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.Notificacion;
import pe.upc.smartpoolguardian.schema.response.NotificacionResponseDTO2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface NotificacionRepository extends CrudRepository<Notificacion, Integer> {
    @Query("SELECT " +
            " n.notificacionId AS notificacionId, " +
            " n.tipoNotificacion AS tipoNotificacion, " +
            " n.mensaje AS mensaje, " +
            " n.leido AS leido, " +
            " n.fechaCreacion AS fechaCreacion, " +
            " n.horaCreacion AS horaCreacion, " +
            " n.usuario.usuarioId AS usuarioId " +
            " FROM Notificacion n " +
            " WHERE n.usuario.usuarioId = :usuarioId ")
    public List<NotificacionResponseDTO2> listarPorUsuarioId(int usuarioId);

    @Query("SELECT " +
            " n.notificacionId AS notificacionId, " +
            " n.tipoNotificacion AS tipoNotificacion, " +
            " n.mensaje AS mensaje, " +
            " n.leido AS leido, " +
            " n.fechaCreacion AS fechaCreacion, " +
            " n.horaCreacion AS horaCreacion, " +
            " n.usuario.usuarioId AS usuarioId " +
            " FROM Notificacion n WHERE n.leido = true " +
            " AND n.usuario.usuarioId = :usuarioId ")
    public List<NotificacionResponseDTO2> listarLeidosPorUsuario(@Param("usuarioId") int usuarioId);

    @Query("SELECT " +
            " n.notificacionId AS notificacionId, " +
            " n.tipoNotificacion AS tipoNotificacion, " +
            " n.mensaje AS mensaje, " +
            " n.leido AS leido, " +
            " n.fechaCreacion AS fechaCreacion, " +
            " n.horaCreacion AS horaCreacion, " +
            " n.usuario.usuarioId AS usuarioId " +
            " FROM Notificacion n WHERE n.leido = false " +
            " AND n.usuario.usuarioId = :usuarioId ")
    public List<NotificacionResponseDTO2> listarNoLeidosPorUsuario(@Param("usuarioId") int usuarioId);

    @Query("SELECT " +
            " n.notificacionId AS notificacionId, " +
            " n.tipoNotificacion AS tipoNotificacion, " +
            " n.mensaje AS mensaje, " +
            " n.leido AS leido, " +
            " n.fechaCreacion AS fechaCreacion," +
            " n.horaCreacion AS horaCreacion, " +
            " n.usuario.usuarioId AS usuarioId " +
            " FROM Notificacion n " +
            " WHERE n.fechaCreacion = :fecha " +
            " AND (:hora IS NULL or n.horaCreacion = :hora) " +
            " AND n.usuario.usuarioId = :usuarioId ")
    public List<NotificacionResponseDTO2> listarPorFechayHora(
            @Param("usuarioId") int usuarioId,
            @Param("fecha") LocalDate fecha,
            @Param("hora")LocalTime hora);
}
