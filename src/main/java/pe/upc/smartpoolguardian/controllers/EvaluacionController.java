package pe.upc.smartpoolguardian.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.upc.smartpoolguardian.servicesinterfaces.IEvaluacionService;
import pe.upc.smartpoolguardian.servicesinterfaces.IPiscinaService;

@RestController
@RequestMapping("/api/evaluacion")
public class EvaluacionController {
    @Autowired
    private IEvaluacionService eS;
  
}
