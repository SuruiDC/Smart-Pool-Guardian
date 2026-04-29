package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.DetalleMedicion;
import pe.upc.smartpoolguardian.repositories.IDetalleMedicionRepository;
import pe.upc.smartpoolguardian.servicesinterfaces.IDetalleMedicionService;

import java.util.Optional;

@Service
public class DetalleMedicionServiceImplement implements IDetalleMedicionService {

    @Autowired
    private IDetalleMedicionRepository mdR;

    @Override
    public DetalleMedicion crearDetalle(DetalleMedicion detalle) {
        return mdR.save(detalle);
    }

    @Override
    public Optional<DetalleMedicion> buscarDetallePorId(int id) {
        return mdR.findById(id);
    }
}
