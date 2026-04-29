package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Evaluacion;

import java.util.List;

public interface IEvaluacionService {
    public List<Evaluacion> list();
    List<Evaluacion>filtrarEvaluacion(String estadoG, String diagnostico);
}
