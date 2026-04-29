package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private Integer usuarioId;

    @NotBlank
    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombreUsuario;
    @NotBlank
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    @NotBlank
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @NotBlank
    @Column(name = "numero_celular", length = 15, nullable = false)
    private String numeroCelular;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    //RELACION BIDIRECCIONAL A PISCINA
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Piscina> piscinas;

    //RELACION BIDIRECCIONAL A NOTIFICACIONES
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Notificacion> notificaciones;

}
