package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.entities.Medicion;
import pe.upc.smartpoolguardian.schema.dtos.MedicionDTO;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionService;
import pe.upc.smartpoolguardian.servicesinterfaces.IPiscinaService;

import java.util.List;

@RestController
@RequestMapping("/api/mediciones")
public class MedicionController {

    @Autowired
    private IMedicionService mS;

    @Autowired
    private IPiscinaService pS;

    ModelMapper m = new ModelMapper();

    @PostMapping("/registrar/")
    public ResponseEntity<?> crearMedicion(@RequestBody @Valid MedicionDTO dto) {

        if (pS.buscarPiscinaPorId(dto.getIdPiscina()).isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay una piscina creada con esa id.");

        Medicion medicion = m.map(dto, Medicion.class);
        Medicion registro = mS.crearMedicion(medicion);
        MedicionDTO response = m.map(registro, MedicionDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/listar/{idPiscina}")
    public ResponseEntity<?> listarPorPiscina(@PathVariable int idPiscina) {

        List<MedicionDTO> mediciones = mS.listarMedicionesPorPiscina(idPiscina).stream()
                .map( x -> m.map(x, MedicionDTO.class)).toList();

        if (mediciones.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron mediciones de esa piscina o no existe esa piscina.");

        } else {

            return ResponseEntity.ok(mediciones);
        }
    }
}
