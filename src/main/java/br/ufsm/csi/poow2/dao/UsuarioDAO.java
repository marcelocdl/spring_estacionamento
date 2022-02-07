package br.ufsm.csi.poow2.dao;

import br.ufsm.csi.poow2.model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioDAO {

    public Usuario getUsuario(String login) {

        if(login.equals("marcelo@admin")){

            return new Usuario("marcelo@admin",
                    new BCryptPasswordEncoder().encode("123"), "ADMIN");

        }else{
            return null;
        }
    }

}
