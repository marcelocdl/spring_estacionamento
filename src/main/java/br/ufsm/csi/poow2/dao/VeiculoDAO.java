package br.ufsm.csi.poow2.dao;

import br.ufsm.csi.poow2.model.Cliente;
import br.ufsm.csi.poow2.model.Estadia;
import br.ufsm.csi.poow2.model.Tipo;
import br.ufsm.csi.poow2.model.Veiculo;
import br.ufsm.csi.poow2.util.ConectaBD;

import java.sql.*;
import java.util.ArrayList;

public class VeiculoDAO {

    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    public boolean cadastrarVeiculo(Veiculo veiculo){

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "INSERT INTO veiculo (placa, cor, modelo, codtipo, codcliente) VALUES" +
                    " (?, ?, ?, ?, ?);";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setString(1, veiculo.getPlaca());
            this.preparedStatement.setString(2, veiculo.getCor());
            this.preparedStatement.setString(3, veiculo.getModelo());
            this.preparedStatement.setInt(4, veiculo.getTipo().getCod_tipo());
            this.preparedStatement.setInt(5, veiculo.getCliente().getId());

            this.preparedStatement.executeUpdate();

            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Veiculo> getVeiculos(){
        ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM veiculo ve, cliente cli, tipo ti WHERE" +
                    " ve.codcliente = cli.cod_cliente AND" +
                    " ve.codtipo = ti.cod_tipo ORDER BY codveiculo;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Veiculo veiculo = new Veiculo();
                veiculo.setId(this.rs.getInt("codveiculo"));
                veiculo.setPlaca(this.rs.getString("placa"));
                veiculo.setCor(this.rs.getString("cor"));
                veiculo.setModelo(this.rs.getString("modelo"));

                Cliente cliente = new Cliente();
                cliente.setId(this.rs.getInt("codcliente"));
                cliente.setNome(this.rs.getString("nome"));
                cliente.setCpf(this.rs.getString("cpf"));

                Tipo tipo = new Tipo();
                tipo.setCod_tipo(this.rs.getInt("cod_tipo"));
                tipo.setDescricao(this.rs.getString("descricao"));

                veiculo.setCliente(cliente);
                veiculo.setTipo(tipo);
                veiculos.add(veiculo);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return veiculos;
    }

    public boolean excluirVeiculo(int id){

        boolean retorno;

        try(Connection connection = new ConectaBD().getConexao()){
            System.out.println("chegou no método deletar");

            connection.setAutoCommit(false);

            Estadia e = new EstadiaDAO().getEstadiaByVeiculo(id);

            if(e == null) {
                System.out.println("não existe estadia");
                this.sql = "DELETE FROM veiculo WHERE veiculo.codveiculo = "+id+";";
                this.preparedStatement = connection.prepareStatement(this.sql);

                if (this.preparedStatement.executeUpdate() == 1) {
                    System.out.println("nao existe estadia e chegou no if do veiculo");
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            }
            else if(e.getHr_saida() != null) {
                System.out.println("Cod estadia = " + e.getId());
                System.out.println("passou na condicao");
                this.sql = "DELETE FROM estadia WHERE estadia.codveiculo = "+id+";";
                this.preparedStatement = connection.prepareStatement(this.sql);

                if (this.preparedStatement.executeUpdate() != 0) {
                    System.out.println("deletou a estadia e chegou antes de deletar o veiculo");

                    this.sql = "DELETE FROM veiculo AS v WHERE v.codveiculo = "+id+";";
                    this.preparedStatement = connection.prepareStatement(this.sql);

                    if (this.preparedStatement.executeUpdate() == 1) {
                        System.out.println("deletou a estadia e chegou no if do veiculo");
                        connection.commit();
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                }
            }
            return false;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean atualizarVeiculo(Veiculo veiculo){
        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "UPDATE veiculo SET " +
                    "placa = ?, cor = ?, modelo = ?, codtipo = ?, codcliente = ? " +
                    " WHERE veiculo.codveiculo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setString(1, veiculo.getPlaca());
            this.preparedStatement.setString(2, veiculo.getCor());
            this.preparedStatement.setString(3, veiculo.getModelo());
            this.preparedStatement.setInt(4, veiculo.getTipo().getCod_tipo());
            this.preparedStatement.setInt(5, veiculo.getCliente().getId());
            this.preparedStatement.setInt(6, veiculo.getId());

            if(this.preparedStatement.executeUpdate() == 1){
                return true;
            }else{
                return false;
            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Veiculo getVeiculosById(int id){
        Veiculo veiculo = null;

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM veiculo ve, cliente cli, tipo ti WHERE" +
                    " ve.codcliente = cli.cod_cliente AND" +
                    " ve.codtipo = ti.cod_tipo AND ve.codveiculo ="+id+";";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                String placa = this.rs.getString("placa");
                String cor = this.rs.getString("cor");
                String modelo = this.rs.getString("modelo");

                Cliente cliente = new Cliente();
                cliente.setId(this.rs.getInt("codcliente"));
                cliente.setNome(this.rs.getString("nome"));

                Tipo tipo = new Tipo();
                tipo.setCod_tipo(this.rs.getInt("cod_tipo"));
                tipo.setDescricao(this.rs.getString("descricao"));

                veiculo = new Veiculo(id, placa, cor, modelo, tipo, cliente);
            }

        }catch (SQLException e){
            e.printStackTrace();
            this.status = "ERRO";
        }

        return veiculo;
    }

}
