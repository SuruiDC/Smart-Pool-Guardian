package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Evaluacion;
import pe.upc.smartpoolguardian.repositories.IEvaluacionRepository;
import pe.upc.smartpoolguardian.servicesinterfaces.IEvaluacionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionServiceImplement implements IEvaluacionService {
    @Autowired
    private IEvaluacionRepository eR;

    @Override
    public List<Evaluacion> list() {
        return eR.findAll();
    }

    @Override
    public Optional<Evaluacion> filtrarEvaluacion(String estadoG, String diagnostico, LocalDate fecha) {
        return eR.filtrarEvaluacion(estadoG,diagnostico,fecha);
    }
}
