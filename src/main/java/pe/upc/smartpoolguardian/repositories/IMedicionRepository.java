package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.Medicion;
import pe.upc.smartpoolguardian.schema.dtos.PrediccionAlgasDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMedicionRepository extends JpaRepository<Medicion,Integer> {

    @Query("SELECT m FROM Medicion AS m WHERE m.piscina.piscinaId = :idPiscina")
    public List<Medicion> listarMedicionesPorPiscina(
            @Param("idPiscina") Integer idPiscina
    );

    @Query("SELECT m.piscina.nombrePiscina, dm.temperatura, dm.nivelCloro " +
            "FROM Medicion m JOIN m.detalleMedicion dm " +
            "WHERE dm.temperatura > 28.0 AND dm.nivelCloro < 1.0 " +
            "AND m.piscina.usuario.usuarioId = :idUsuario")
    public List<PrediccionAlgasDTO> predecirAlgasPorUsuario(@Param("idUsuario") Integer idUsuario);
}
