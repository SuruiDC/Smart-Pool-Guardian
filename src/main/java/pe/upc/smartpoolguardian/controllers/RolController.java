package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.schema.request.RolRequestDTO;
import pe.upc.smartpoolguardian.entities.Rol;
import pe.upc.smartpoolguardian.servicesimplements.RolService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody @Valid RolRequestDTO dto) {
        Rol nuevo = new Rol();
        nuevo.setTipoRol(dto.getTipoRol());

        Rol creado = rolService.crearRol(nuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {
        return ResponseEntity.ok(rolService.mostrarRoles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable int id, @RequestBody @Valid RolRequestDTO dto) {
        Rol nuevo = new Rol();
        nuevo.setRolId(id);
        nuevo.setTipoRol(dto.getTipoRol());
        Rol creado = rolService.actualizarRol(nuevo);
        return ResponseEntity.ok(creado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rol> eliminarRol(@PathVariable int id) {
        rolService.eliminarRolPorId(id);
        return ResponseEntity.noContent().build();
    }

}
