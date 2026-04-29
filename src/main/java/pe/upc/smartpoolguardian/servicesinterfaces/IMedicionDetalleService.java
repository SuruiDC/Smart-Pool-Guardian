package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.DetalleMedicion;

import java.util.Optional;

public interface IMedicionDetalleService {

    public DetalleMedicion crearDetalle(DetalleMedicion detalle);

    public Optional<DetalleMedicion> buscarDetallePorId(int id);

}
