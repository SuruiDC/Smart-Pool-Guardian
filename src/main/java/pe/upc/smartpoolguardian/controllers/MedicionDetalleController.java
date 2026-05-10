package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.entities.DetalleMedicion;
import pe.upc.smartpoolguardian.schema.dtos.MedicionDetalleDTO;
import pe.upc.smartpoolguardian.schema.response.PhResponseDTO;
import pe.upc.smartpoolguardian.schema.response.TemperaturaMasAltaResponseDTO;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionDetalleService;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionService;

import java.util.ArrayList;
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

    @GetMapping("/buscar-id/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {

        Optional<DetalleMedicion> detalle = mdS.buscarDetallePorId(id);

        if (detalle.isPresent()) {
            MedicionDetalleDTO dto = m.map(detalle.get(), MedicionDetalleDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle no encontrado");
        }
    }

    @GetMapping("/promedio-nivel-ph-piscina")
    public ResponseEntity<?>promedioPhPorPiscina(){
        List<Object[]> lista = mdS.promedioPhPiscina();
        if(lista.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay mediciones existentes");
        }
        List<PhResponseDTO> response =new ArrayList<>();
        for(Object[] fila : lista){
            PhResponseDTO dto = new PhResponseDTO();
            dto.setPiscina_id(((Number)fila[0]).intValue());
            dto.setPromedioPh(((Double)fila[1]));
            response.add(dto);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/piscina-con-temperaturas-mas-altas")
    public ResponseEntity<?>temperaturasMasAlta(){
        List<Object[]> lista = mdS.temperaturaMasAltaPiscina();
        if(lista.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay mediciones existentes");
        }
        List<TemperaturaMasAltaResponseDTO> response = new ArrayList<>();
        for(Object[] fila : lista){
            TemperaturaMasAltaResponseDTO dto = new TemperaturaMasAltaResponseDTO();
            dto.setPiscina_id(((Number)fila[0]).intValue());
            dto.setTemperaturaPiscina(((Double)fila[1]));
            response.add(dto);
        }
        return ResponseEntity.ok(response);
    }

}
