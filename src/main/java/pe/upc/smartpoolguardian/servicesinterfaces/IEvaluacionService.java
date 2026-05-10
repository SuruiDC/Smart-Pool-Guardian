package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Evaluacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IEvaluacionService {
    public List<Evaluacion> list();
    public void RegistrarEvaluacion(Evaluacion evaluacion);
    List<Evaluacion> filtrarEvaluacion(String estadoG, LocalDate fecha);
    public Evaluacion buscarEvaluacionPorId(int id);
}
