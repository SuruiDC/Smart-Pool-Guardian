package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.upc.smartpoolguardian.schema.request.PiscinaRequestDTO;
import pe.upc.smartpoolguardian.schema.response.PiscinaResponseDTO;
import pe.upc.smartpoolguardian.schema.dtos.PiscinasPorUsuarioDTO;
import pe.upc.smartpoolguardian.entities.Piscina;
import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.servicesimplements.PiscinaServiceImplement;
import pe.upc.smartpoolguardian.servicesimplements.UsuarioServiceImplement;
import java.util.List;

@RestController
@RequestMapping("/api/piscinas")
public class PiscinaController {
    @Autowired
    private PiscinaServiceImplement piscinaService;
    @Autowired
    private UsuarioServiceImplement usuarioService;

    @PostMapping("/registrar/{idUsuario}")
    public ResponseEntity<PiscinaResponseDTO> crearPiscina(@PathVariable int idUsuario,
                                                           @RequestBody @Valid PiscinaRequestDTO dto) {
        //Verificar si existe usuario
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        //DTO a Entity
        Piscina crear = new Piscina();
        crear.setNombrePiscina(dto.getNombrePiscina());
        crear.setVolumen(dto.getVolumen());
        crear.setUsuario(usuario);

        //crear
        Piscina creado = piscinaService.crearPiscinaPorUsuario(crear);

        //Entity a DTO
        PiscinaResponseDTO response = new PiscinaResponseDTO();
        response.setPiscinaId(creado.getPiscinaId());
        response.setNombrePiscina(creado.getNombrePiscina());
        response.setVolumen(creado.getVolumen());
        response.setUsuarioId(creado.getUsuario().getUsuarioId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PiscinasPorUsuarioDTO>> mostrarPiscinasporUsuario(@RequestParam int idUsuario) {
        List<PiscinasPorUsuarioDTO> lista = piscinaService.mostrarPiscinasPorUsuario(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PutMapping("/{idUsuario}/{idPiscina}")
    public ResponseEntity<PiscinaResponseDTO> actualizarPiscina(@PathVariable int idUsuario,
                                                                @PathVariable int idPiscina,
                                                                @RequestBody @Valid PiscinaRequestDTO dto) {
        //Verificar si existe usuario
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        //DTO a Entity
        Piscina actualizar = new Piscina();
        actualizar.setPiscinaId(idPiscina);
        actualizar.setNombrePiscina(dto.getNombrePiscina());
        actualizar.setVolumen(dto.getVolumen());
        actualizar.setUsuario(usuario);

        for (Piscina p: usuario.getPiscinas()) {
            if (p.getPiscinaId() == idPiscina) {
                //actualizar
                Piscina actualizada =  piscinaService.actualizarPiscina(actualizar);

                //Entity a DTO
                PiscinaResponseDTO response = new PiscinaResponseDTO();
                response.setPiscinaId(actualizada.getPiscinaId());
                response.setNombrePiscina(actualizada.getNombrePiscina());
                response.setVolumen(actualizada.getVolumen());
                response.setUsuarioId(actualizada.getUsuario().getUsuarioId());

                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Piscina no existe");
            }
        }
        return ResponseEntity.ok().body(null);

    }

    @DeleteMapping("/{idUsuario}/{idPiscina}")
    public ResponseEntity<Void> eliminarPiscina(@PathVariable int idUsuario,
                                                @PathVariable int idPiscina) {
        //Verificar si existe usuario
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        for (Piscina p: usuario.getPiscinas()) {
            if (p.getPiscinaId() == idPiscina) {
                piscinaService.eliminarPiscina(p);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Piscina no existe");
    }
}