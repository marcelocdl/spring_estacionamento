package br.ufsm.csi.poow2.services;

import br.ufsm.csi.poow2.dao.EstadiaDAO;
import br.ufsm.csi.poow2.dao.VagaDAO;
import br.ufsm.csi.poow2.model.Estadia;

import java.util.ArrayList;

public class EstadiaService {

    private EstadiaDAO estadiaDAO;
    private VagaDAO vagaDAO;

    public EstadiaService() {
        this.estadiaDAO = new EstadiaDAO();
        this.vagaDAO = new VagaDAO();
    }

    public ArrayList<Estadia> getEstadias() {
        return this.estadiaDAO.getEstadias();
    }

    public Estadia getEstadiaById(int id){
        return this.estadiaDAO.getEstadia(id);
    }

    public boolean cadastrarEstadia(Estadia e) {
        if(this.estadiaDAO.cadastrarEstadia(e)){
            this.vagaDAO.ocuparVaga(e.getVaga().getNumVaga());
            return true;
        }else{
            return false;
        }
    }

    public boolean registrarSaida(Estadia e) {
        Estadia rs = this.estadiaDAO.getEstadia(e.getId());

        if(this.estadiaDAO.saida(e) == true){
            this.vagaDAO.desocuparVaga(rs.getVaga().getNumVaga());
            return true;
        }else{
            return false;
        }
    }

}
