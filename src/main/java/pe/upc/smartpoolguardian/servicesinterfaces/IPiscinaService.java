package pe.upc.smartpoolguardian.servicesinterfaces;

<<<<<<< HEAD
import org.springframework.data.repository.query.Param;
import pe.upc.smartpoolguardian.schema.dtos.PiscinasPorUsuarioDTO;
import pe.upc.smartpoolguardian.entities.Piscina;

import java.util.List;

public interface IPiscinaService {
    public Piscina crearPiscinaPorUsuario(Piscina piscina);
    public List<PiscinasPorUsuarioDTO> mostrarPiscinasPorUsuario(int id);
    public Piscina actualizarPiscina(Piscina piscina);
    public void eliminarPiscina(Piscina piscina);
=======
import pe.upc.smartpoolguardian.entities.Piscina;

import java.util.List;
import java.util.Optional;

public interface IPiscinaService {
    public void delete(int id);
    public Piscina insert(Piscina p);
    public Optional<Piscina> listId(int id);
    List<Piscina>listarPiscinasPorUsuarios(int idUsuario);
    public void update(Piscina p);
>>>>>>> efc206b (Se crearon los endpoints de piscinas : buscar Piscinas por id, eliminar de piscinas, actualizar piscinas,listar piscinas por idusuario,registrar piscinas)
}
