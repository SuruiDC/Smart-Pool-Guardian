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
    public List<Recomendacion> findByEvaluacionId(@Param("evaluacionId") Integer evaluacionId);

    @Query(value = "SELECT r.descripcion, e.diagnostico, e.estado_general, e.fecha_creacion FROM recomendacion AS r\n" +
            "INNER JOIN evaluacion AS e\n" +
            "ON r.evaluacion_id = e.evaluacion_id\n" +
            "WHERE e.estado_general = 'CRITICO'", nativeQuery = true)
    public List<Object[]> ListarRecomendacionParaCritico();
}
