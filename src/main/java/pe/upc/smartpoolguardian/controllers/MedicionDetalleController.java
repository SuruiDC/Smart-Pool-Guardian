package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.entities.DetalleMedicion;
import pe.upc.smartpoolguardian.schema.dtos.MedicionDetalleDTO;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionDetalleService;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalles-medicion")
public class MedicionDetalleController {

    @Autowired
    private IMedicionService mS;

    @Autowired
    private IMedicionDetalleService mdS;

    ModelMapper m = new ModelMapper();

    @PostMapping("/registrar/")
    public ResponseEntity<?> crearMedicionDetalle(@RequestBody @Valid MedicionDetalleDTO dto) {

        if (mS.buscarMedicioPorId(dto.getMedicionId()).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay una medicion creada con esa id.");

        DetalleMedicion detalle = m.map(dto, DetalleMedicion.class);
        DetalleMedicion registro = mdS.crearDetalle(detalle);
        MedicionDetalleDTO response = m.map(registro, MedicionDetalleDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {

        Optional<DetalleMedicion> detalle = mdS.buscarDetallePorId(id);

        if (detalle.isPresent()) {
            MedicionDetalleDTO dto = m.map(detalle.get(), MedicionDetalleDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle no encontrado");
        }
    }

}
