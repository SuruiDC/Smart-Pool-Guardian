package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.schema.dtos.UsuariosInactivosDTO;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findOneByNombreUsuario(String username);

    //BUSCAR POR NOMBRE
    @Query("select count(u.nombreUsuario) from Usuario u where u.nombreUsuario =:username")
    public int buscarUsername(@Param("username") String nombre);

    //INSERTAR ROLES
    @Transactional
    @Modifying
    @Query(value = "insert into rol (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
    public void insRol(@Param("rol") String authority, @Param("user_id") Long user_id);

    //USUARIOS INACTIVOS
    @Query("SELECT u.nombreUsuario, u.email, u.numeroCelular, p.nombrePiscina, MAX(m.fechaMedicion) " +
            "FROM Usuario u " +
            "JOIN u.piscinas p " +
            "JOIN p.mediciones m " +
            "GROUP BY u.nombreUsuario, u.email, u.numeroCelular, p.nombrePiscina " +
            "HAVING MAX(m.fechaMedicion) < :fechaLimite")
    public List<UsuariosInactivosDTO> listarUsuariosInactivos(@Param("fechaLimite") LocalDateTime fechaLimite);
}
