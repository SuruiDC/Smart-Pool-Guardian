package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Usuario;

import java.util.List;

public interface IUsuarioService {
    public Usuario registrarUsuario(Usuario usuario);
    public List<Usuario> mostrarUsuarios();
}
