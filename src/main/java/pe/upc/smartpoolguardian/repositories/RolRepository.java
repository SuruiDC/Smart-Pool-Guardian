package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
