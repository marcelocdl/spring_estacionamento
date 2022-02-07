package br.ufsm.csi.poow2.controller;

import br.ufsm.csi.poow2.model.Usuario;
import br.ufsm.csi.poow2.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> autenticacao(@RequestBody Usuario usuario){

        System.out.println("Login: "+ usuario.getLogin());

        try{
            final Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha()));

            if(authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = new JWTUtil().geraToken(usuario);
                usuario.setToken(token);
                usuario.setSenha("");

                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Usuário ou senha incorretos!", HttpStatus.BAD_REQUEST);
    }

}
