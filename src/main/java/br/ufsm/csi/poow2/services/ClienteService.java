package br.ufsm.csi.poow2.services;

import br.ufsm.csi.poow2.dao.ClienteDAO;
import br.ufsm.csi.poow2.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClienteService {

    private ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public ArrayList<Cliente> getClientes() {
        return this.clienteDAO.getClientes();
    }

    public Cliente getClienteById(int id) { return this.clienteDAO.getClienteById(id); }

    public boolean cadastrarCliente(Cliente c){
        return this.clienteDAO.cadastrarCliente(c);
    }

    public boolean atualizarCliente(Cliente c){
        return this.clienteDAO.atualizarCliente(c);
    }

    public boolean deletarCliente(int id){
        return this.clienteDAO.excluirCliente(id);
    }

}
