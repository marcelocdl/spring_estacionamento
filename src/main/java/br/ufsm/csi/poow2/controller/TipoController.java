package br.ufsm.csi.poow2.controller;

import br.ufsm.csi.poow2.dao.TipoDAO;
import br.ufsm.csi.poow2.model.Tipo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/tipo")
public class TipoController {

    @GetMapping("/tipos")
    public ArrayList<Tipo> getTipos(){
        return new TipoDAO().getTodosTipos();
    }

}
