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
    public void RegistrarEvaluacion(Evaluacion evaluacion) {
        eR.save(evaluacion);
    }

    @Override
    public List<Evaluacion> filtrarEvaluacion(String estadoG, LocalDate fecha) {
        return eR.filtrarEvaluacion(estadoG,fecha);
    }

    @Override
    public Evaluacion buscarEvaluacionPorId(int id) {
        return eR.findById(id).orElseThrow(()->new RuntimeException("No se encontro el id"));
    }
}
