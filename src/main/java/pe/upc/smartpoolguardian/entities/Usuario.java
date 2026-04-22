package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
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
    private int usuario_id;

    @Column(name = "nombre_usuario", length = 50, nullable = false)
    private String nombre_usuario;
    @Column(name = "contraseña", length = 100, nullable = false)
    private String contraseña;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "numero_celular", length = 11, nullable = false)
    private String numero_celular;

}
