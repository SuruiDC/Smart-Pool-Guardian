package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.schema.request.RolRequestDTO;
import pe.upc.smartpoolguardian.entities.Rol;
import pe.upc.smartpoolguardian.servicesimplements.RolServiceImplement;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class
RolController {
    @Autowired
    private RolServiceImplement rolService;

    private ModelMapper m = new ModelMapper();

    @PostMapping("/registrar")
    public ResponseEntity<Rol> crearRol(@RequestBody @Valid RolRequestDTO dto) {
        Rol nuevo = new Rol();
        nuevo.setRolId(null);
        nuevo.setTipoRol(dto.getTipoRol());

        Rol creado = rolService.crearRol(nuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarRoles() {
        List<RolRequestDTO> response = rolService.mostrarRoles().stream().map(x -> m.map(x, RolRequestDTO.class)).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable int id, @RequestBody @Valid RolRequestDTO dto) {
        Rol nuevo = new Rol();
        nuevo.setRolId(id);
        nuevo.setTipoRol(dto.getTipoRol());
        Rol creado = rolService.actualizarRol(nuevo);
        return ResponseEntity.ok(creado);
    }


}
