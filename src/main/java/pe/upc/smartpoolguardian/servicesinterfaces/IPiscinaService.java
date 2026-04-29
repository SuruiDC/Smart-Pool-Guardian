package pe.upc.smartpoolguardian.servicesinterfaces;
import pe.upc.smartpoolguardian.entities.Piscina;
import pe.upc.smartpoolguardian.schema.dtos.PiscinasPorUsuarioDTO;
import java.util.List;
import java.util.Optional;

public interface IPiscinaService {
    public Piscina crearPiscinaPorUsuario(Piscina piscina);

    public List<PiscinasPorUsuarioDTO> mostrarPiscinasPorUsuario(int id);

    public Optional<Piscina> buscarPiscinaPorId(int id);

    public Piscina actualizarPiscina(Piscina piscina);

    public void eliminarPiscina(Piscina piscina);
}
