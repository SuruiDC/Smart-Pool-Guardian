package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Medicion;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

public interface IMedicionService {

    public Medicion crearMedicion(Medicion medicion);
    public List<Medicion> listarMedicionesPorPiscina(int idPiscina);
    public Optional<Medicion> buscarMedicioPorId(int id);
}
