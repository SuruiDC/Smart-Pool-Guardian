package pe.upc.smartpoolguardian.servicesinterfaces;

import pe.upc.smartpoolguardian.entities.Rol;

import java.util.List;

public interface IRolService {
    public Rol crearRol(Rol rol);
    public List<Rol> mostrarRoles();
    public Rol actualizarRol(Rol rol);
    public void eliminarRolPorId(int id);
}
