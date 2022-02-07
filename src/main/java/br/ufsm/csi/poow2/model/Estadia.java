package br.ufsm.csi.poow2.model;

import java.sql.Date;

public class Estadia {

    private int id;
    private String data;
    private String hr_entrada;
    private String hr_saida;
    private Vaga vaga;
    private Veiculo veiculo;

    public Estadia() {
    }

    public Estadia(int id, String hr_saida){
        this.id = id;
        this.hr_saida = hr_saida;
    }

    public Estadia(int id, String hr_entrada, String hr_saida, Vaga vaga, Veiculo veiculo) {
        this.id = id;
        this.hr_entrada = hr_entrada;
        this.hr_saida = hr_saida;
        this.vaga = vaga;
        this.veiculo = veiculo;
    }

    public Estadia(String hr_entrada, Vaga vaga, Veiculo veiculo) {
        this.hr_entrada = hr_entrada;
        this.vaga = vaga;
        this.veiculo = veiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHr_entrada() {
        return hr_entrada;
    }

    public void setHr_entrada(String hr_entrada) {
        this.hr_entrada = hr_entrada;
    }

    public String getHr_saida() {
        return hr_saida;
    }

    public void setHr_saida(String hr_saida) {
        this.hr_saida = hr_saida;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
