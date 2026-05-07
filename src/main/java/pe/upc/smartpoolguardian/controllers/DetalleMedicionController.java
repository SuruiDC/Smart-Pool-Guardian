package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.entities.DetalleMedicion;
import pe.upc.smartpoolguardian.entities.Medicion;
import pe.upc.smartpoolguardian.schema.dtos.DetalleMedicionDTO;
import pe.upc.smartpoolguardian.servicesinterfaces.IDetalleMedicionService;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionService;

import java.util.Optional;

@RestController
@RequestMapping("/api/detalles-medicion")
public class DetalleMedicionController {

    @Autowired
    private IMedicionService mS;

    @Autowired
    private IDetalleMedicionService mdS;

    ModelMapper m = new ModelMapper();

    @PostMapping("/registrar/")
    public ResponseEntity<?> crearDetalleMedicion(@RequestBody @Valid DetalleMedicionDTO dto) {
        //BUSCAR MEDICION
        var medicionOpt = mS.buscarMedicioPorId(dto.getMedicionId());

        if (medicionOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay una medicion creada con esa id.");
        }

        Medicion medicion = medicionOpt.get();

        // DE DTO A ENTITY
        DetalleMedicion detalle = new DetalleMedicion();

        detalle.setNivelCloro(dto.getNivelCloro());
        detalle.setNivelPh(dto.getNivelPh());
        detalle.setTemperatura(dto.getTemperatura());
        detalle.setNivelTurbidez(dto.getNivelTurbidez());
        detalle.setAlcalinidad(dto.getAlcalinidad());
        detalle.setDurezaCalcio(dto.getDurezaCalcio());
        detalle.setTieneAlgas(dto.isTieneAlgas());
        detalle.setColorPiscina(dto.getColorPiscina());
        detalle.setOlor(dto.getOlor());
        detalle.setTipoMedicion(dto.getTipoMedicion());

        detalle.setMedicion(medicion);

        //CREAR DETALLEMEDICION
        DetalleMedicion registro = mdS.crearDetalle(detalle);

        //DE ENTITY A DTO
        DetalleMedicionDTO response = new DetalleMedicionDTO();
        response.setNivelCloro(registro.getNivelCloro());
        response.setNivelPh(registro.getNivelPh());
        response.setTemperatura(registro.getTemperatura());
        response.setNivelTurbidez(registro.getNivelTurbidez());
        response.setAlcalinidad(registro.getAlcalinidad());
        response.setDurezaCalcio(registro.getDurezaCalcio());
        response.setTieneAlgas(registro.isTieneAlgas());
        response.setColorPiscina(registro.getColorPiscina());
        response.setOlor(registro.getOlor());
        response.setTipoMedicion(registro.getTipoMedicion());
        response.setMedicionId(registro.getMedicion().getMedicionId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {

        Optional<DetalleMedicion> detalle = mdS.buscarDetallePorId(id);

        if (detalle.isPresent()) {
            DetalleMedicionDTO dto = m.map(detalle.get(), DetalleMedicionDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle no encontrado");
        }
    }

}
