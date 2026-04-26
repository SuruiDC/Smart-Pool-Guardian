package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Piscina;

import java.util.List;
import java.util.Optional;

public interface IPiscinaService {
    public void delete(int id);
    public Piscina insert(Piscina p);
    public Optional<Piscina> listId(int id);
    List<Piscina>listarPiscinasPorUsuarios(int idUsuario);
    public void update(Piscina p);
}
