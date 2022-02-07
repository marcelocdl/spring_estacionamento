package br.ufsm.csi.poow2.controller;

import br.ufsm.csi.poow2.model.Vaga;
import br.ufsm.csi.poow2.services.VagaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    private VagaService vagaService;

    public VagaController() { this.vagaService = new VagaService(); }

    @GetMapping("/vagas")
    public ArrayList<Vaga> getVagas(){
        return this.vagaService.getVagas();
    }

    @GetMapping("/vaga/{id}")
    public Vaga getVaga(@PathVariable("id") int id){
        return this.vagaService.getVagaById(id);
    }

}
