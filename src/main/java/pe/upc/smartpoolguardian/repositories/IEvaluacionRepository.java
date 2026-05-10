package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.Evaluacion;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IEvaluacionRepository extends JpaRepository<Evaluacion,Integer> {

    @Query("SELECT e FROM Evaluacion e WHERE e.estadoGeneral = :estadoG AND e.diagnostico = :diagnostico AND e.fechaCreacion = :fecha" )
    Optional<Evaluacion> filtrarEvaluacion(
            @Param("estadoG") String estadoG,
            @Param("diagnostico") String diagnostico,
            @Param("fecha")LocalDate fecha
            );
}
