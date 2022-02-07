package br.ufsm.csi.poow2.dao;

import br.ufsm.csi.poow2.model.Cliente;
import br.ufsm.csi.poow2.util.ConectaBD;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    public boolean cadastrarCliente(Cliente cliente){

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "INSERT INTO cliente (nome, cpf) VALUES" +
                    " (?, ?);";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setString(1, cliente.getNome());
            this.preparedStatement.setString(2, cliente.getCpf());

            this.preparedStatement.execute();

            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM cliente ORDER BY cod_cliente;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(this.rs.getInt("cod_cliente"));
                cliente.setNome(this.rs.getString("nome"));
                cliente.setCpf(this.rs.getString("cpf"));

                clientes.add(cliente);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return clientes;
    }

    public boolean excluirCliente(int id){

        try(Connection connection = new ConectaBD().getConexao()){
            this.sql = "DELETE FROM cliente AS c WHERE c.cod_cliente = "+id+";";
            this.preparedStatement = connection.prepareStatement(this.sql);

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

    public boolean atualizarCliente(Cliente cliente){

        try (Connection connection = new ConectaBD().getConexao()) {

            this.sql = "UPDATE cliente SET nome = ?, cpf = ? WHERE cod_cliente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setString(1, cliente.getNome());
            this.preparedStatement.setString(2, cliente.getCpf());
            this.preparedStatement.setInt(3, cliente.getId());

            if(this.preparedStatement.executeUpdate() == 1){
                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public Cliente getClienteById(int id){
        Cliente cliente = null;

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM cliente WHERE cod_cliente = "+id+";";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                String nome = this.rs.getString("nome");
                String cpf = this.rs.getString("cpf");

                cliente = new Cliente(id, nome, cpf);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return cliente;
    }

}
