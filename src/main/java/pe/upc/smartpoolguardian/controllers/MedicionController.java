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
        //ENCONTRAR PISCINA
        var piscinaOpt = pS.buscarPiscinaPorId(dto.getIdPiscina());

        if (piscinaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay una piscina creada con esa id.");
        }
        //DE DTO A ENTITY
        Medicion medicion = new Medicion();
        medicion.setFechaMedicion(dto.getFechaMedicion());
        medicion.setPiscina(piscinaOpt.get());

        //CREAR MEDICION
        Medicion registro = mS.crearMedicion(medicion);

        //DE ENTITY A DTO
        MedicionDTO response = new MedicionDTO();
        response.setFechaMedicion(registro.getFechaMedicion());
        response.setIdPiscina(registro.getPiscina().getPiscinaId());

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
