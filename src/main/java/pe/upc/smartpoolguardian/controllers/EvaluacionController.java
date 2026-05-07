package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.entities.Evaluacion;
import pe.upc.smartpoolguardian.entities.Medicion;
import pe.upc.smartpoolguardian.schema.dtos.EvaluacionPorFiltroDTO;
import pe.upc.smartpoolguardian.servicesinterfaces.IEvaluacionService;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionService;
import pe.upc.smartpoolguardian.servicesinterfaces.IPiscinaService;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluacion")
public class EvaluacionController {
    @Autowired
    private IEvaluacionService eS;
    ModelMapper m = new ModelMapper();
    @GetMapping("/evaluacion-filtro/{estadoG}/{diagnostico}/{fecha}")
    public ResponseEntity<?>evaluacionFiltro(@PathVariable @Valid String estadoG, String diagnostico, LocalDate fecha){
        Optional<Evaluacion> evaluacion = eS.filtrarEvaluacion(estadoG,diagnostico,fecha);
        EvaluacionPorFiltroDTO dto = m.map(evaluacion.get(),EvaluacionPorFiltroDTO.class);
        return ResponseEntity.ok(dto);


    }


}
