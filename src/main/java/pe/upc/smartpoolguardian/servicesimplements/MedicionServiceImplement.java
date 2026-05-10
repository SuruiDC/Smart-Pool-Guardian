package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Medicion;
import pe.upc.smartpoolguardian.repositories.IMedicionRepository;
import pe.upc.smartpoolguardian.schema.dtos.PrediccionAlgasDTO;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicionServiceImplement implements IMedicionService {

    @Autowired
    private IMedicionRepository mR;

    @Override
    public Medicion crearMedicion(Medicion medicion) {
        return mR.save(medicion);
    }

    @Override
    public List<Medicion> listarMedicionesPorPiscina(int idPiscina) {
        return mR.listarMedicionesPorPiscina(idPiscina);
    }

    @Override
    public Optional<Medicion> buscarMedicioPorId(int id) {
        return mR.findById(id);
    }

    @Override
    public List<PrediccionAlgasDTO> obtenerPrediccionesAlgas(Integer idUsuario) {
        List<Object[]> resultados = mR.predecirAlgas(idUsuario);
        List<PrediccionAlgasDTO> dtos = new ArrayList<>();

        for (Object[] fila : resultados) {
            PrediccionAlgasDTO dto = new PrediccionAlgasDTO();
            dto.setNombrePiscina((String) fila[0]);

            Double temp = ((Number) fila[1]).doubleValue();
            Double cloro = ((Number) fila[2]).doubleValue();

            dto.setTemperaturaActual(temp);
            dto.setNivelCloroActual(cloro);

            String severidad = (temp > 31) ? "MUY ALTO" : "MODERADO";
            dto.setMensajeRiesgo("Riesgo " + severidad + ": La temperatura de " + temp +
                    "°C facilita el crecimiento de algas con tan poco cloro (" + cloro + "ppm).");

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<Object[]> ObtenerMedicionesDeUnTipoPorPiscina(Integer idPiscina, String tipo) {
        return mR.ObtenerMedicionesDeUnTipoPorPiscina(idPiscina, tipo);
    }
}
