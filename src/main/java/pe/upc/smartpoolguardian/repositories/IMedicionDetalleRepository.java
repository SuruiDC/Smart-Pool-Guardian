package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.DetalleMedicion;
import java.util.List;

@Repository
public interface IMedicionDetalleRepository extends JpaRepository<DetalleMedicion,Integer> {

    @Query(value = "SELECT m.piscina_id,\n" +
            "       CAST(\n" +
            "           ROUND(AVG(d.nivel_ph)::numeric, 2)\n" +
            "           AS double precision\n" +
            "       ) AS promedio_ph\n" +
            " FROM detalle_medicion d\n" +
            " JOIN medicion m \n" +
            " ON d.medicion_id = m.medicion_id\n" +
            " GROUP BY m.piscina_id;", nativeQuery = true)
    List<Object[]> promedioPhPiscina();

    @Query(value = " SELECT m.piscina_id,\n" +
            "       CAST(ROUND(AVG(d.temperatura)::numeric, 2) AS double precision)\n" +
            "       AS temperatura_promedio\n" +
            " FROM detalle_medicion d\n" +
            " JOIN medicion m \n" +
            " ON d.medicion_id = m.medicion_id\n" +
            " GROUP BY m.piscina_id\n" +
            "ORDER BY temperatura_promedio DESC;", nativeQuery = true)
    List<Object[]> temperaturaMasAltaPiscina();
}