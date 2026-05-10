package pe.upc.smartpoolguardian.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.upc.smartpoolguardian.entities.Usuario;
import pe.upc.smartpoolguardian.repositories.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repo.findOneByNombreUsuario(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("El usuario no existe", username));
        }

        //Creacion de la lista de roles en un usuario, en nuestro caso solo pasaremos 1 rol
        List<GrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(user.getRol().getTipoRol()));

<<<<<<< HEAD
        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getNombreUsuario(), user.getPassword(), true, true, true, true, roles);
=======
        //Se devuelve el detalle de usuario
        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getNombreUsuario(), user.getPassword(), user.getActivo(), true, true, true, roles);
>>>>>>> 4577b41c06bec77f064e708bab4b4aac95dda829

        return ud;
    }
}
