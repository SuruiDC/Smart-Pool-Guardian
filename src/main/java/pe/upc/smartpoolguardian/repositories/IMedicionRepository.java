package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.Medicion;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMedicionRepository extends JpaRepository<Medicion,Integer> {

    @Query("SELECT m FROM Medicion AS m WHERE m.piscina.piscinaId = :idPiscina")
    public List<Medicion> listarMedicionesPorPiscina(
            @Param("idPiscina") Integer idPiscina
    );

}
