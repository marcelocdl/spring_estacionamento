package br.ufsm.csi.poow2.model;

public class Tipo {

    private int cod_tipo;
    private String descricao;

    public Tipo() {
    }

    public Tipo(int cod_tipo){
        this.cod_tipo = cod_tipo;
    }

    public Tipo(int cod_tipo, String descricao) {
        this.cod_tipo = cod_tipo;
        this.descricao = descricao;
    }

    public int getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(int cod_tipo) {
        this.cod_tipo = cod_tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
