package br.ufsm.csi.poow2.controller;

import br.ufsm.csi.poow2.model.Veiculo;
import br.ufsm.csi.poow2.services.VeiculoService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("veiculo")
public class VeiculoController {

    private VeiculoService veiculoService;

    public VeiculoController() {
        this.veiculoService = new VeiculoService();
    }

    @GetMapping("/veiculos")
    public ArrayList<Veiculo> getVeiculos(){ return veiculoService.getVeiculos(); }

    @GetMapping("/veiculo/{id}")
    public Veiculo getVeiculo(@PathVariable("id") int id){
        return this.veiculoService.getVeiculoById(id);
    }

    @PostMapping("/cadastrar")
    public String cadastrarVeiculo(@RequestBody Veiculo veiculo) {

        //Veiculo v = new Veiculo(placa, cor, modelo, new Tipo(codtipo), new Cliente(codcliente));

        if(this.veiculoService.cadastrarVeiculo(veiculo)){
            return "Cadastrado com sucesso";
        }else {
            return "Erro ao cadastrar";
        }
    }

    @PutMapping("/editar/{id}")
    public String atualizarVeiculo(@RequestBody Veiculo veiculo){

        //Veiculo v = new Veiculo(id, placa, cor, modelo, new Tipo(codtipo), new Cliente(codcliente));

        if (this.veiculoService.atualizarVeiculo(veiculo)) {
            return "Atualizado com sucesso";
        } else {
            return "Erro ao atualizar";
        }
    }

    @DeleteMapping("/deletar/{id}")
    public String excluirVeiculo(@PathVariable("id") int id){
        if (this.veiculoService.deletarVeiculo(id)){
            return "Deletado com sucesso";
        }else{
            return "Erro ao deletar";
        }

    }

}
