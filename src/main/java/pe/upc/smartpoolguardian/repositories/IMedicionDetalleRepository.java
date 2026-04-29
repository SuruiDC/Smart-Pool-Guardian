package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.DetalleMedicion;

import java.util.List;

@Repository
public interface IMedicionDetalleRepository extends JpaRepository<DetalleMedicion,Integer> {
}