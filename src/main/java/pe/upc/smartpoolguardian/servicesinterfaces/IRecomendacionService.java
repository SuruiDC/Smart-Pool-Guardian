package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Recomendacion;

import java.util.List;

public interface IRecomendacionService {

    public Recomendacion crearRecomendacion(Recomendacion recomendacion);

    public List<Recomendacion> listarRecomendaciones();

    public List<Recomendacion> listarPorEvaluacion(Integer evaluacionId);

    public Recomendacion buscarRecomendacionPorId(Integer id);

    public Recomendacion actualizarRecomendacion(Recomendacion recomendacion);

    public void eliminarRecomendacion(Integer id);
}
