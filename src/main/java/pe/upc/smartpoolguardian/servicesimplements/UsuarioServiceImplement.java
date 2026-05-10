package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.repositories.IUsuarioRepository;
import pe.upc.smartpoolguardian.schema.dtos.UsuariosInactivosDTO;
import pe.upc.smartpoolguardian.servicesinterfaces.IUsuarioService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImplement implements IUsuarioService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Usuario registrarUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> mostrarUsuarios() {

        return usuarioRepository.findAll();
    }

    @Override
    public Usuario editarUsuario(Usuario usuario) {
        Usuario existe = usuarioRepository.findById(usuario.getUsuarioId()).
                orElseThrow(()->new RuntimeException("Usuario no encontrado") );

        existe.setNombreUsuario(usuario.getNombreUsuario());
        existe.setPassword(usuario.getPassword());
        existe.setEmail(usuario.getEmail());
        existe.setNumeroCelular(usuario.getNumeroCelular());
        existe.setRol(usuario.getRol());
        return usuarioRepository.save(existe);
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Usuario no encontrado") );
    }

    @Override
    public List<UsuariosInactivosDTO> obtenerInactivos(int dias) {
        LocalDate fechaLimite = LocalDate.now().minusDays(dias);
        List<Object[]> resultados = usuarioRepository.listarUsuariosInactivos(fechaLimite);

        List<UsuariosInactivosDTO> dtos = new ArrayList<>();

        for (Object[] fila : resultados) {
            UsuariosInactivosDTO dto = new UsuariosInactivosDTO();
            dto.setNombreUsuario((String) fila[0]);
            dto.setEmail((String) fila[1]);
            dto.setNumeroCelular((String) fila[2]);
            dto.setNombrePiscina((String) fila[3]);

            // Mapeo directo: Si Postgres manda LocalDate, Java lo recibe como LocalDate
            dto.setFechaUltimaMedicion((java.time.LocalDate) fila[4]);

            dtos.add(dto);
        }
        return dtos;
    }

    /*
    @Override
    public void borrarUsuarioPorId(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        else {
            usuarioRepository.deleteById(id);
        }


    }
    */
}
