package pe.upc.smartpoolguardian.servicesinterfaces;

import org.springframework.data.repository.query.Param;
import pe.upc.smartpoolguardian.schema.dtos.PiscinasPorUsuarioDTO;
import pe.upc.smartpoolguardian.entities.Piscina;

import java.util.List;

public interface IPiscinaService {
    public Piscina crearPiscinaPorUsuario(Piscina piscina);
    public List<PiscinasPorUsuarioDTO> mostrarPiscinasPorUsuario(int id);
    public Piscina actualizarPiscina(Piscina piscina);
    public void eliminarPiscina(Piscina piscina);
}
