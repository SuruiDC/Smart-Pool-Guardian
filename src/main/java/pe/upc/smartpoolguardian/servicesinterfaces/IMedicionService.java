package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Medicion;
import pe.upc.smartpoolguardian.schema.dtos.PrediccionAlgasDTO;

import java.util.Optional;
import java.util.List;

public interface IMedicionService {

    public Medicion crearMedicion(Medicion medicion);
    public List<Medicion> listarMedicionesPorPiscina(int idPiscina);
    public Optional<Medicion> buscarMedicioPorId(int id);
    public List<PrediccionAlgasDTO> obtenerPrediccionesAlgas(Integer idUsuario);
}
