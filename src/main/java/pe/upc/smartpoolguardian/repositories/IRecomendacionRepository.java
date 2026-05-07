package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.Recomendacion;

import java.util.List;

@Repository
public interface IRecomendacionRepository extends JpaRepository<Recomendacion, Integer> {

    @Query("SELECT r FROM Recomendacion r WHERE r.evaluacion.evaluacionId = :evaluacionId")
    List<Recomendacion> findByEvaluacionId(@Param("evaluacionId") Integer evaluacionId);
}
