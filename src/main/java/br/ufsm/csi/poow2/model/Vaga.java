package br.ufsm.csi.poow2.model;

public class Vaga {

    private int numVaga;
    private boolean ocupado;

    public Vaga() {
    }

    public Vaga(int numVaga){
        this.numVaga = numVaga;
    }

    public Vaga(int numVaga, boolean ocupado) {
        this.numVaga = numVaga;
        this.ocupado = ocupado;
    }

    public int getNumVaga() {
        return numVaga;
    }

    public void setNumVaga(int numVaga) {
        this.numVaga = numVaga;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

}
