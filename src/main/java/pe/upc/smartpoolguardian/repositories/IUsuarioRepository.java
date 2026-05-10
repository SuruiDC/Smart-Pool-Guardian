package pe.upc.smartpoolguardian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.upc.smartpoolguardian.entities.Usuario;

import java.time.LocalDate;
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

    @Query(value = "SELECT u.nombre_usuario as nombreUsuario, u.email, u.numero_celular as numeroCelular, " +
            "p.nombre_piscina as nombrePiscina, MAX(m.fecha_medicion) as fechaUltimaMedicion " +
            "FROM usuario u " +
            "JOIN piscina p ON u.usuario_id = p.usuario_id " +
            "JOIN medicion m ON p.piscina_id = m.piscina_id " +
            "GROUP BY u.nombre_usuario, u.email, u.numero_celular, p.nombre_piscina " +
            "HAVING MAX(m.fecha_medicion) < :fechaLimite",
            nativeQuery = true)
    public List<Object[]> listarUsuariosInactivos(@Param("fechaLimite") LocalDate fechaLimite);

}
