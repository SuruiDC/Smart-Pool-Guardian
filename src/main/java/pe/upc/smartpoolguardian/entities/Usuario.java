package pe.upc.smartpoolguardian.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;

    @NotBlank
    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombreUsuario;
    @NotBlank
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    @NotBlank
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @NotBlank
    @Column(name = "numero_celular", length = 15, nullable = false)
    private String numeroCelular;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;

    //RELACION BIDIRECCIONAL A PISCINA
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Piscina> piscinas;

    //RELACION BIDIRECCIONAL A NOTIFICACIONES
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Notificacion> notificaciones;

}
