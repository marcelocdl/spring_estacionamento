package br.ufsm.csi.poow2.model;

public class Veiculo {

    private int id;
    private String placa;
    private String modelo;
    private String cor;
    private Tipo tipo;
    private Cliente cliente;

    public Veiculo() {
    }

    public Veiculo(int id){
        this.id = id;
    }

    public Veiculo(int id, String placa) {
        this.id = id;
        this.placa = placa;
    }

    public Veiculo(String placa, String cor, String modelo, Tipo tipo, Cliente cliente) {
        this.placa = placa;
        this.cor = cor;
        this.modelo = modelo;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    public Veiculo(int id, String placa, String cor, String modelo, Tipo tipo, Cliente cliente) {
        this.id = id;
        this.placa = placa;
        this.cor = cor;
        this.modelo = modelo;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
