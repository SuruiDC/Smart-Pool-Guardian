package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.entities.Rol;
import pe.upc.smartpoolguardian.schema.request.UsuarioRequestDTO;
import pe.upc.smartpoolguardian.schema.response.UsuarioResponseDTO;
import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.servicesimplements.RolServiceImplement;
import pe.upc.smartpoolguardian.servicesimplements.UsuarioServiceImplement;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServiceImplement usuarioService;
    @Autowired
    private RolServiceImplement rolService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@RequestBody @Valid UsuarioRequestDTO dto) {
        //De DTORequest a Entity
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setNumeroCelular(dto.getNumeroCelular());

        Rol encontrarRol = rolService.findByRolId(dto.getRolId());
        usuario.setRol(encontrarRol);

        //CREAR USUARIO
        Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);

        // De Entity a DTOResponse
        UsuarioResponseDTO response = new UsuarioResponseDTO(
                nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(),
                nuevoUsuario.getNumeroCelular(),
                nuevoUsuario.getRol().getRolId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> mostrarTodosLosUsuarios() {
        List<Usuario> listaUsuarios = usuarioService.mostrarUsuarios();
        return ResponseEntity.ok(listaUsuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario (@PathVariable int id,
                                                                 @RequestBody @Valid UsuarioRequestDTO dto){
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(id);
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setNumeroCelular(dto.getNumeroCelular());

        Rol encontrarRol = rolService.findByRolId(dto.getRolId());
        usuario.setRol(encontrarRol);

        Usuario actualizado = usuarioService.editarUsuario(usuario);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                actualizado.getNombreUsuario(),
                actualizado.getEmail(),
                actualizado.getNumeroCelular(),
                actualizado.getRol().getRolId()
        );
        return ResponseEntity.ok(response);
    }
/*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId (@PathVariable int id){
        usuarioService.borrarUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }

 */
}
