package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuario_id;

    @NotBlank
    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombre_usuario;
    @NotBlank
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    @NotBlank
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @NotBlank
    @Column(name = "numero_celular", length = 15, nullable = false)
    private String numero_celular;

}
