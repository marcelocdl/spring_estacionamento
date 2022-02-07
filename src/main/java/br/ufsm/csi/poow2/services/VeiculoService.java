package br.ufsm.csi.poow2.services;

import br.ufsm.csi.poow2.dao.VeiculoDAO;
import br.ufsm.csi.poow2.model.Veiculo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VeiculoService {

    private VeiculoDAO veiculoDAO;

    public VeiculoService() {
        this.veiculoDAO = new VeiculoDAO();
    }

    public ArrayList<Veiculo> getVeiculos() {
        return this.veiculoDAO.getVeiculos();
    }

    public Veiculo getVeiculoById(int id) { return this.veiculoDAO.getVeiculosById(id); }

    public boolean cadastrarVeiculo(Veiculo v){
        return this.veiculoDAO.cadastrarVeiculo(v);
    }

    public boolean atualizarVeiculo(Veiculo v){
        return this.veiculoDAO.atualizarVeiculo(v);
    }

    public boolean deletarVeiculo(int id){
        return this.veiculoDAO.excluirVeiculo(id);
    }

}
