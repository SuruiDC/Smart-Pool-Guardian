package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Integer usuarioId;
=======
    private int usuario_id;
>>>>>>> efc206b (Se crearon los endpoints de piscinas : buscar Piscinas por id, eliminar de piscinas, actualizar piscinas,listar piscinas por idusuario,registrar piscinas)

    @NotBlank
    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombreUsuario;
    @NotBlank
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    @NotBlank
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @Column(name = "eliminado", nullable = false)
    private boolean eliminado;
    @NotBlank
    @Column(name = "numero_celular", length = 15, nullable = false)
    private String numeroCelular;

    //RELACION BIDIRECCIONAL A PISCINA
    @OneToMany(mappedBy = "usuario")
    private List<Piscina> piscinas;
}
