package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.schema.dtos.PiscinasPorUsuarioDTO;
import pe.upc.smartpoolguardian.entities.Piscina;
import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.repositories.PiscinaRepository;
import pe.upc.smartpoolguardian.repositories.UsuarioRepository;
import pe.upc.smartpoolguardian.servicesinterfaces.IPiscinaService;

import java.util.List;
import java.util.Optional;

@Service
public class PiscinaService implements IPiscinaService {
    @Autowired
    private PiscinaRepository piscinaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Piscina crearPiscinaPorUsuario(Piscina piscina) {
        return piscinaRepository.save(piscina);
    }

    @Override
    public List<PiscinasPorUsuarioDTO> mostrarPiscinasPorUsuario(int id) {
        Usuario usuario = usuarioRepository.findById(id).
                orElseThrow(()->new RuntimeException("Usuario no encontrado"));

        return piscinaRepository.mostrarPiscinasPorUsuario(id);
    }

    @Override
    public Optional<Piscina> buscarPiscinaPorId(int id) {
        return piscinaRepository.findById(id);
    }

    @Override
    public Piscina actualizarPiscina(Piscina piscina) {
        Piscina existe = piscinaRepository.findById(piscina.getPiscinaId()).
                orElseThrow(()-> new RuntimeException("Piscina no encontrada"));

        if (existe.isEliminado()){
            throw new RuntimeException("Piscina no encontrada");
        }
        else {
            existe.setNombrePiscina(piscina.getNombrePiscina());
            existe.setVolumen(piscina.getVolumen());
            return piscinaRepository.save(existe);
        }

    }

    @Override
    public void eliminarPiscina(Piscina piscina) {

        if (piscina.isEliminado()){
            throw new RuntimeException("Piscina no encontrada");
        }
        else {
            piscina.setEliminado(true);
            piscinaRepository.save(piscina);
        }

    }

}
