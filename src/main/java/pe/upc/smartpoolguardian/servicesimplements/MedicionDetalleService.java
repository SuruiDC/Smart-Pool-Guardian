package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.DetalleMedicion;
import pe.upc.smartpoolguardian.repositories.IMedicionDetalleRepository;
import pe.upc.smartpoolguardian.servicesinterfaces.IMedicionDetalleService;

import java.util.List;
import java.util.Optional;

@Service
public class MedicionDetalleService implements IMedicionDetalleService {

    @Autowired
    private IMedicionDetalleRepository mdR;

    @Override
    public DetalleMedicion crearDetalle(DetalleMedicion detalle) {
        return mdR.save(detalle);
    }

    @Override
    public Optional<DetalleMedicion> buscarDetallePorId(int id) {
        return mdR.findById(id);
    }

    @Override
    public List<Object[]> promedioPhPiscina() {
        return mdR.promedioPhPiscina();
    }

    @Override
    public List<Object[]> temperaturaMasAltaPiscina() {
        return mdR.temperaturaMasAltaPiscina();
    }
}
