package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.DetalleMedicion;

@Repository
public interface IDetalleMedicionRepository extends JpaRepository<DetalleMedicion,Integer> {
}