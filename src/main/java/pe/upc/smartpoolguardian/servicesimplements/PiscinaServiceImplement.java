package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Piscina;
import pe.upc.smartpoolguardian.repositories.IPiscinaRepositorie;
import pe.upc.smartpoolguardian.servicesinterfaces.IPiscinaService;

import java.util.List;
import java.util.Optional;

@Service
public class PiscinaServiceImplement implements IPiscinaService {
    @Autowired
    private IPiscinaRepositorie pR;

    @Override
    public void delete(int id) {
        pR.deleteById(id);
    }

    @Override
    public Piscina insert(Piscina p) {
        return pR.save(p);
    }

    @Override
    public Optional<Piscina> listId(int id) {
        return pR.findById(id);
    }

    @Override
    public List<Piscina> listarPiscinasPorUsuarios(int idUsuario) {
        return pR.listarPiscinasPorUsuarios(idUsuario);
    }


    @Override
    public void update(Piscina p) {
        pR.save(p);
    }
}
