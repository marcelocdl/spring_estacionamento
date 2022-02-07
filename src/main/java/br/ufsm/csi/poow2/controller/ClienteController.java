package br.ufsm.csi.poow2.controller;

import br.ufsm.csi.poow2.dao.ClienteDAO;
import br.ufsm.csi.poow2.model.Cliente;
import br.ufsm.csi.poow2.services.ClienteService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController() {
        this.clienteService = new ClienteService();
    }

    @GetMapping("/clientes")
    public ArrayList<Cliente> getClientes(){
        return this.clienteService.getClientes();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getCliente(@PathVariable("id") int id){
        return this.clienteService.getClienteById(id);
    }

    /*
    public String cadastrarCliente(@RequestParam(value = "nome") String nome,
                                   @RequestParam(value = "cpf") String cpf){
     */

    @PostMapping("/cadastrar")
    public String cadastrarCliente(@RequestBody Cliente cliente){
        //Cliente c = new Cliente(nome, cpf);

        if(this.clienteService.cadastrarCliente(cliente)){
            return "Cadastrado com sucesso";
        }else {
            return "Erro ao cadastrar";
        }

        //return new ClienteDAO().cadastrarCliente(new Cliente(nome, cpf));
    }

    @PutMapping("/editar/{id}")
    public String atualizarCliente(@RequestBody Cliente cliente){

        //Cliente c = new Cliente(id, nome, cpf);
        if (this.clienteService.atualizarCliente(cliente)) {
            return "Atualizado com sucesso";
        } else {
            return "Erro ao atualizar";
        }

        //return new ClienteDAO().atualizarCliente(new Cliente(id, nome, cpf));
    }

    @DeleteMapping("deletar/{id}")
    public String excluirCliente(@PathVariable("id") int id){

        if (this.clienteService.deletarCliente(id)){
            return "Deletado com sucesso";
        }else{
            return "Erro ao deletar";
        }
    }

}
