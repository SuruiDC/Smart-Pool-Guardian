package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.Medicion;

import java.util.List;

@Repository
public interface IMedicionRepository extends JpaRepository<Medicion,Integer> {

    @Query("SELECT m FROM Medicion AS m WHERE m.piscina.piscinaId = :idPiscina")
    public List<Medicion> listarMedicionesPorPiscina(
            @Param("idPiscina") Integer idPiscina
    );

    @Query(value = "SELECT p.nombre_piscina, dm.temperatura, dm.nivel_cloro " +
            "FROM medicion m " +
            "JOIN piscina p ON m.piscina_id = p.piscina_id " +
            "JOIN detalle_medicion dm ON m.medicion_id = dm.medicion_id " +
            "WHERE dm.temperatura > 28.0 " +
            "AND dm.nivel_cloro < 1.0 " +
            "AND p.usuario_id = :idUsuario " +
            "AND m.fecha_medicion >= (CURRENT_TIMESTAMP - INTERVAL '1 month')", // Aumentamos a 1 mes para la prueba
            nativeQuery = true)
    List<Object[]> predecirAlgas(@Param("idUsuario") Integer idUsuario);

}
