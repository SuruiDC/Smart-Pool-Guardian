package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Recomendacion;
import pe.upc.smartpoolguardian.repositories.IRecomendacionRepository;
import pe.upc.smartpoolguardian.servicesinterfaces.IRecomendacionService;

import java.util.List;

@Service
public class RecomendacionServiceImplement implements IRecomendacionService {

    @Autowired
    private IRecomendacionRepository recomendacionRepository;

    @Override
    public Recomendacion crearRecomendacion(Recomendacion recomendacion) {
        return recomendacionRepository.save(recomendacion);
    }

    @Override
    public List<Recomendacion> listarRecomendaciones() {
        return recomendacionRepository.findAll();
    }

    @Override
    public List<Recomendacion> listarPorEvaluacion(Integer evaluacionId) {
        return recomendacionRepository.findByEvaluacionId(evaluacionId);
    }

    @Override
    public Recomendacion buscarRecomendacionPorId(Integer id) {
        return recomendacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recomendacion no encontrada con id: " + id));
    }

    @Override
    public Recomendacion actualizarRecomendacion(Recomendacion recomendacion) {
        Recomendacion existe = recomendacionRepository.findById(recomendacion.getRecomendacionId())
                .orElseThrow(() -> new RuntimeException("Recomendacion no encontrada con id: " + recomendacion.getRecomendacionId()));

        existe.setMensaje(recomendacion.getMensaje());
        existe.setEvaluacion(recomendacion.getEvaluacion());

        return recomendacionRepository.save(existe);
    }

    @Override
    public void eliminarRecomendacion(Integer id) {
        Recomendacion existe = recomendacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recomendacion no encontrada con id: " + id));

        recomendacionRepository.delete(existe);
    }
}
