package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Medicion;

import java.util.List;

public interface IMedicionService {

    public Medicion crearMedicion(Medicion medicion);
    public List<Medicion> listarMedicionesPorPiscina(int idPiscina);

}
