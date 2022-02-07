package br.ufsm.csi.poow2.dao;

import br.ufsm.csi.poow2.model.Tipo;
import br.ufsm.csi.poow2.util.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TipoDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    public String cadastrarTipo(Tipo tipo){

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "INSERT INTO tipo (descricao) VALUES (?);";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setString(1, tipo.getDescricao());

            this.preparedStatement.execute();
            this.status = "OK";

        }catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }

    public ArrayList<Tipo> getTodosTipos(){
        ArrayList<Tipo> tipos = new ArrayList<Tipo>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM tipo";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Tipo tipo = new Tipo();
                tipo.setCod_tipo(this.rs.getInt("cod_tipo"));
                tipo.setDescricao(this.rs.getString("descricao"));

                tipos.add(tipo);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return tipos;
    }
}
