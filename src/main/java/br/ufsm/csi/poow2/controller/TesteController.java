package br.ufsm.csi.poow2.controller;

import br.ufsm.csi.poow2.dao.VagaDAO;
import br.ufsm.csi.poow2.model.Vaga;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TesteController {

    @CrossOrigin(origins = "*")
    @GetMapping("/teste")
    public ArrayList<Vaga> getVagas(){
        return new VagaDAO().getTodasVagas();
    }

    /*public String testeJwt(){
        return "Feitoo";
    } */

}
