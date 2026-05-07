package pe.upc.smartpoolguardian.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.upc.smartpoolguardian.entities.Notificacion;
import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.schema.request.NotificacionRequestDTO;
import pe.upc.smartpoolguardian.schema.response.NotificacionResponseDTO;
import pe.upc.smartpoolguardian.schema.response.NotificacionResponseDTO2;
import pe.upc.smartpoolguardian.servicesimplements.NotificacionServiceImplement;
import pe.upc.smartpoolguardian.servicesimplements.UsuarioServiceImplement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    @Autowired
    private NotificacionServiceImplement notificacionService;
    @Autowired
    private UsuarioServiceImplement usuarioService;

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crearNotificacion(@RequestBody NotificacionRequestDTO dto) {
        //DTO A ENTITY
        Notificacion crear =  new Notificacion();
        crear.setTipoNotificacion(dto.getTipoNotificacion());
        crear.setMensaje(dto.getMensaje());
        crear.setLeido(false);
        crear.setFechaCreacion(dto.getFechaCreacion());
        crear.setHoraCreacion(dto.getHoraCreacion());

        Usuario user = usuarioService.buscarUsuarioPorId(dto.getUsuarioId());
        crear.setUsuario(user);

        //CREAR
        Notificacion creado = notificacionService.crearNotificacion(crear);

        //ENTITY A DTO
        NotificacionResponseDTO response = new NotificacionResponseDTO();
        response.setIdNotificacion(creado.getNotificacionId());
        response.setTipoNotificacion(creado.getTipoNotificacion());
        response.setMensaje(creado.getMensaje());
        response.setLeido(creado.isLeido());
        response.setFechaCreacion(creado.getFechaCreacion());
        response.setHoraCreacion(creado.getHoraCreacion());
        response.setUsuarioId(user.getUsuarioId());

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO2>> listarPorUsuarioId(@RequestParam int usuarioId){
        return ResponseEntity.ok(notificacionService.listarPorUsuarioId(usuarioId));
    }
    @PutMapping("/actualizar-leido/{idUsuario}/{idNotificacion}")
    public ResponseEntity<String>actualizarLeido(@PathVariable int idUsuario, @PathVariable int idNotificacion) {
        Usuario user = usuarioService.buscarUsuarioPorId(idUsuario);

        boolean encontrada = false;

        for (Notificacion n : user.getNotificaciones()) {
            if (n.getNotificacionId().equals(idNotificacion)) {
                Notificacion notificacion = notificacionService.findById(n.getNotificacionId());
                notificacionService.actualizarLeido(notificacion);
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Notificacion no encontrada");
        }
        else{
            return ResponseEntity.ok("Marcado como leído");
        }
    }

    @GetMapping("/leidos/")
    public ResponseEntity<List<NotificacionResponseDTO2>> listarLeidosPorUsuario(@RequestParam int usuarioId){
        return ResponseEntity.ok(notificacionService.listarLeidosPorUsuario(usuarioId));
    }

    @GetMapping("/no-leidos/")
    public ResponseEntity<List<NotificacionResponseDTO2>> listarNoLeidosPorUsuario(@RequestParam int usuarioId){
        return ResponseEntity.ok(notificacionService.listarNoLeidosPorUsuario(usuarioId));
    }

    @GetMapping("/buscar-por-fecha-y-hora/")
    public ResponseEntity<List<NotificacionResponseDTO2>> listarPorFechayHora(@RequestParam int usuarioId,
                                                                              @RequestParam LocalDate fecha,
                                                                              @RequestParam LocalTime hora){
        return ResponseEntity.ok(notificacionService.listarPorFechayHora(usuarioId, fecha, hora));
    }
}
