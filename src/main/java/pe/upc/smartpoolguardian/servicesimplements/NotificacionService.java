package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Notificacion;
import pe.upc.smartpoolguardian.repositories.NotificacionRepository;
import pe.upc.smartpoolguardian.schema.response.NotificacionResponseDTO2;
import pe.upc.smartpoolguardian.servicesinterfaces.INotificacionService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class NotificacionService implements INotificacionService {
    @Autowired
    private NotificacionRepository notificacionRepository;

    @Override
    public Notificacion crearNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public Notificacion findById(int id) {
        return notificacionRepository.findById(id).
                orElseThrow(()-> new RuntimeException("Notificacion no encontrada"));
    }

    @Override
    public List<NotificacionResponseDTO2> listarPorUsuarioId(int usuarioId) {
        return notificacionRepository.listarPorUsuarioId(usuarioId);
    }

    @Override
    public Notificacion actualizarLeido(Notificacion notificacion) {
        Notificacion existe = notificacionRepository.findById(notificacion.getNotificacionId()).
                orElseThrow(()-> new RuntimeException("Notificacion no encontrada"));

        existe.setLeido(true);
        return notificacionRepository.save(existe);
    }

    @Override
    public List<NotificacionResponseDTO2> listarLeidosPorUsuario(int usuarioId) {
        return notificacionRepository.listarLeidosPorUsuario(usuarioId);
    }

    @Override
    public List<NotificacionResponseDTO2> listarNoLeidosPorUsuario(int usuarioId) {
        return notificacionRepository.listarNoLeidosPorUsuario(usuarioId);
    }

    @Override
    public List<NotificacionResponseDTO2> listarPorFechayHora(int usuarioId, LocalDate fecha, LocalTime hora) {
        return notificacionRepository.listarPorFechayHora(usuarioId, fecha, hora);
    }


}
