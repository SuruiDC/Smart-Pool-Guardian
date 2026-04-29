package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.upc.smartpoolguardian.entities.Usuario;
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
