package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        Usuario existe = usuarioRepository.findById(usuario.getUsuario_id()).
                orElseThrow(()->new RuntimeException("Usuario no encontrado") );

        existe.setNombre_usuario(usuario.getNombre_usuario());
        existe.setPassword(usuario.getPassword());
        existe.setEmail(usuario.getEmail());
        existe.setNumero_celular(usuario.getNumero_celular());
        return usuarioRepository.save(existe);
    }
/*
    @Override
    public void borrarUsuarioPorId(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);


    }
*/
}
