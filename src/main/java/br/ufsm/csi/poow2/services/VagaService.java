package br.ufsm.csi.poow2.services;

import br.ufsm.csi.poow2.dao.VagaDAO;
import br.ufsm.csi.poow2.model.Vaga;

import java.util.ArrayList;

public class VagaService {
    private VagaDAO vagaDAO;

    public VagaService() {
        this.vagaDAO = new VagaDAO();
    }

    public ArrayList<Vaga> getVagas() {
        return this.vagaDAO.getTodasVagas();
    }

    public Vaga getVagaById(int id) {
        return this.vagaDAO.getVagaById(id);
    }

    public boolean ocuparVaga(int id) {
        return this.vagaDAO.ocuparVaga(id);
    }

    public boolean desocuparVaga(int id) {
        return this.vagaDAO.desocuparVaga(id);
    }

}
