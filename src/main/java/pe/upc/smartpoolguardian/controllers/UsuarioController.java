package pe.upc.smartpoolguardian.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.upc.smartpoolguardian.dtos.UsuarioRequestDTO;
import pe.upc.smartpoolguardian.dtos.UsuarioResponseDTO;
import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.servicesimplements.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@RequestBody @Valid UsuarioRequestDTO dto) {
        //De DTORequest a Entity
        Usuario usuario = new Usuario();
        usuario.setNombre_usuario(dto.getNombre_usuario());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setNumero_celular(dto.getNumero_celular());

        Usuario nuevo_usuario = usuarioService.registrarUsuario(usuario);

        // De Entity a DTOResponse
        UsuarioResponseDTO response = new UsuarioResponseDTO(
                nuevo_usuario.getNombre_usuario(),
                nuevo_usuario.getEmail(),
                nuevo_usuario.getNumero_celular()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> mostrarTodosLosUsuarios() {
        List<Usuario> listaUsuarios = usuarioService.mostrarUsuarios();
        return ResponseEntity.ok(listaUsuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario (@PathVariable int id, @RequestBody @Valid UsuarioRequestDTO dto){
        Usuario usuario = new Usuario();
        usuario.setUsuario_id(id);
        usuario.setNombre_usuario(dto.getNombre_usuario());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());
        usuario.setNumero_celular(dto.getNumero_celular());

        Usuario actualizado = usuarioService.editarUsuario(usuario);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                actualizado.getNombre_usuario(),
                actualizado.getEmail(),
                actualizado.getNumero_celular()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId (@PathVariable int id){
        usuarioService.borrarUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }
}
