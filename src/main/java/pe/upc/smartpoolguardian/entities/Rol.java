package pe.upc.smartpoolguardian.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Rol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rolId;

    @NotBlank
    @Column(name = "tipo_rol", length = 30, nullable = false)
    private String tipoRol;
/*
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

 */
}
