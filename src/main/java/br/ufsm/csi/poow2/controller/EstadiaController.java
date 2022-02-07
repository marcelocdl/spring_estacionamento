package br.ufsm.csi.poow2.controller;

import br.ufsm.csi.poow2.dao.ClienteDAO;
import br.ufsm.csi.poow2.dao.EstadiaDAO;
import br.ufsm.csi.poow2.dao.VagaDAO;
import br.ufsm.csi.poow2.model.Cliente;
import br.ufsm.csi.poow2.model.Estadia;
import br.ufsm.csi.poow2.model.Vaga;
import br.ufsm.csi.poow2.model.Veiculo;
import br.ufsm.csi.poow2.services.EstadiaService;
import br.ufsm.csi.poow2.services.VagaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;

@RestController
@RequestMapping("estadia")
public class EstadiaController {

    private EstadiaService estadiaService;
    private VagaService vagaService;

    public EstadiaController() {
        this.estadiaService = new EstadiaService();
        this.vagaService = new VagaService();
    }

    @GetMapping("/estadias")
    public ArrayList<Estadia> getEstadias() { return this.estadiaService.getEstadias(); }

    @GetMapping("/estadia/{id}")
    public Estadia getEstadia(@PathVariable("id") int id){
        return this.estadiaService.getEstadiaById(id);
    }

    @PostMapping("/cadastrar")
    /*public ResponseEntity<Object> cadastrarEstadia(@RequestParam(value = "hrentrada") String hrEntrada,
                                                   @RequestParam(value = "numvaga") int numvaga,
                                                   @RequestParam(value = "codveiculo") int codveiculo){
    */
    public String cadastrarEstadia(@RequestBody Estadia estadia){
        //Estadia e = new Estadia(hrEntrada, new Vaga(numvaga), new Veiculo(codveiculo));

        if(this.estadiaService.cadastrarEstadia(estadia)){
            return "Cadastrado com sucesso";
        }else{
            return "Erro ao cadastrar (verifique se a vaga já não está OCUPADA!)";
        }
    }

    @PutMapping("saida/{id}")
    /*public ResponseEntity<Object> registrarSaida(@PathVariable("id") int id,
                                 @RequestParam(value = "hrsaida") String hrSaida){
    */
    public String registrarEstadia(@RequestBody Estadia estadia){
        //Estadia e = new Estadia(id, hrSaida);

        if(this.estadiaService.registrarSaida(estadia)){
            return "Saida registrada com sucesso";
        }else{
            return "Erro ao registrar saida";
        }
    }
}
