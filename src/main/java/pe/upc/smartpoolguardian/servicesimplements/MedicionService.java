package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Medicion;
import pe.upc.smartpoolguardian.repositories.IMedicionRepository;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionService;

import java.util.List;

@Service
public class MedicionService implements IMedicionService {

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
}
