package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.Piscina;

import java.util.List;

@Repository
public interface IPiscinaRepositorie extends JpaRepository<Piscina,Integer> {

    @Query(value = "SELECT * FROM piscina p WHERE p.usuario_id = :idUsuario",nativeQuery = true)
    List<Piscina>listarPiscinasPorUsuarios(
            @Param("idUsuario") int idUsuario
    );
}
