package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.repositories.UsuarioRepository;
import pe.upc.smartpoolguardian.servicesinterfaces.IUsuarioService;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario registrarUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> mostrarUsuarios() {

        return usuarioRepository.findAll();
    }

    @Override
    public Usuario editarUsuario(Usuario usuario) {
        Usuario existe = usuarioRepository.findById(usuario.getUsuarioId()).
                orElseThrow(()->new RuntimeException("Usuario no encontrado") );

        existe.setNombreUsuario(usuario.getNombreUsuario());
        existe.setPassword(usuario.getPassword());
        existe.setEmail(usuario.getEmail());
        existe.setNumeroCelular(usuario.getNumeroCelular());
        existe.setRol(usuario.getRol());
        return usuarioRepository.save(existe);
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Usuario no encontrado") );
    }
/*
    @Override
    public void borrarUsuarioPorId(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        else {
            usuarioRepository.deleteById(id);
        }


    }
*/
}
