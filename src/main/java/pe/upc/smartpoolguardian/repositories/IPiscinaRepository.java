package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.schema.dtos.PiscinasPorUsuarioDTO;
import pe.upc.smartpoolguardian.entities.Piscina;

import java.util.List;

@Repository
public interface IPiscinaRepository extends JpaRepository<Piscina,Integer> {
    @Query(" SELECT " +
            " p.piscinaId AS piscinaId, " +
            " p.nombrePiscina AS nombrePiscina, " +
            " p.volumen AS volumen " +
            " FROM Piscina p WHERE p.eliminado=false " +
            " AND p.usuario.usuarioId = :usuarioIngresado")
    public List<PiscinasPorUsuarioDTO> mostrarPiscinasPorUsuario(
            @Param("usuarioIngresado")int id);

}
