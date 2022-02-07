package br.ufsm.csi.poow2.security;

import br.ufsm.csi.poow2.dao.UsuarioDAO;
import br.ufsm.csi.poow2.model.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = new UsuarioDAO().getUsuario(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário ou senha inválidos");
        }else{
            UserDetails user = User.withUsername(usuario.getLogin())
                    .password(usuario.getSenha())
                    .authorities(usuario.getPermissao()).build();

            return user;
        }
    }


}
