package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.entities.Evaluacion;
import pe.upc.smartpoolguardian.entities.Medicion;
import pe.upc.smartpoolguardian.schema.dtos.EvaluacionPorFiltroDTO;
import pe.upc.smartpoolguardian.schema.request.EvaluacionRequestDTO;
import pe.upc.smartpoolguardian.servicesinterfaces.IEvaluacionService;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionService;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluacion")
public class EvaluacionController {

    @Autowired
    private IEvaluacionService eS;

    @Autowired
    private IMedicionService mS;

    ModelMapper m = new ModelMapper();

    @PostMapping("/registrar/{idMedicion}")
    public ResponseEntity<?> registrarEvaluacion(
            @Valid @RequestBody EvaluacionRequestDTO dto,
            @PathVariable("idMedicion") int idMedicion
    ) {

        Optional<Medicion> medicion = mS.buscarMedicioPorId(idMedicion);

        if (medicion.isEmpty()) return ResponseEntity.notFound().build();

        if (medicion.get().getPiscina().isEliminado()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La piscina relacionada a esta medicion esta eliminada");

        Evaluacion eva = new Evaluacion();
        eva.setDiagnostico(dto.getDiagnostico());
        eva.setEstadoGeneral(dto.getEstadoGeneral());
        eva.setFechaCreacion(dto.getFechaCreacion());
        eva.setMedicion(medicion.get());

        eS.RegistrarEvaluacion(eva);

        return ResponseEntity.status(HttpStatus.CREATED).body("Se creo correctamente la evaluacion");
    }

    @GetMapping("/evaluacion-filtro/{estadoG}/{diagnostico}/{fecha}")
    public ResponseEntity<?>evaluacionFiltro(
            @PathVariable @Valid String estadoG,
            String diagnostico,
            LocalDate fecha
    ){

        Optional<Evaluacion> evaluacion = eS.filtrarEvaluacion(estadoG,diagnostico,fecha);
        EvaluacionPorFiltroDTO dto = m.map(evaluacion.get(),EvaluacionPorFiltroDTO.class);
        return ResponseEntity.ok(dto);


    }

}
