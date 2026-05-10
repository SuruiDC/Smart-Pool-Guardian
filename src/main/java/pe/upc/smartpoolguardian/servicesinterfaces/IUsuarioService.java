package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.schema.dtos.UsuariosInactivosDTO;

import java.util.List;

public interface IUsuarioService {
    public Usuario guardarUsuario(Usuario usuario);
    public List<Usuario> mostrarUsuarios();
    public Usuario editarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    public Usuario buscarUsuarioPorId(int id);
    public List<UsuariosInactivosDTO> obtenerInactivos(int dias);

}
