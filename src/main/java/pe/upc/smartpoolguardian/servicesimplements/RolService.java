package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Rol;
import pe.upc.smartpoolguardian.repositories.RolRepository;
import pe.upc.smartpoolguardian.servicesinterfaces.IRolService;

import java.util.List;

@Service
public class RolService implements IRolService {
    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol crearRol(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public List<Rol> mostrarRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol actualizarRol(Rol rol) {
        Rol existe = rolRepository.findById(rol.getRolId()).
                orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        existe.setTipoRol(rol.getTipoRol());
        return rolRepository.save(existe);
    }

    @Override
    public void eliminarRolPorId(int id) {
        if (rolRepository.existsById(id)) {
            rolRepository.deleteById(id);
        }
        else  {
            throw new RuntimeException("Rol no encontrado");
        }
    }

    @Override
    public Rol findByRolId(int rolId) {
        return rolRepository.findById(rolId)
                .orElseThrow(()-> new RuntimeException("Rol no encontrado"));
    }
}
