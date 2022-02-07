package br.ufsm.csi.poow2.dao;

import br.ufsm.csi.poow2.model.Vaga;
import br.ufsm.csi.poow2.util.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VagaDAO {
    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    /*public String cadastrarVaga(Vaga vaga){

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "INSERT INTO vaga VALUES (?,?);";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setInt(1, vaga.getNumVaga());
            this.preparedStatement.setBoolean(2, false);

            this.preparedStatement.execute();
            this.status = "OK";

        }catch(Exception e){
            e.printStackTrace();
        }

        return status;
    } */

    public ArrayList<Vaga> getTodasVagas(){
        ArrayList<Vaga> vagas = new ArrayList<Vaga>();

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM vaga ORDER BY numvaga;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while(this.rs.next()){
                Vaga vaga = new Vaga();
                vaga.setNumVaga(this.rs.getInt("numvaga"));
                vaga.setOcupado(this.rs.getBoolean("ocupado"));

                vagas.add(vaga);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return vagas;
    }

    public Vaga getVagaById(int id){
        Vaga v = null;

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM vaga WHERE numvaga = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.rs = this.preparedStatement.executeQuery();

            while(this.rs.next()){
                int vaga = (this.rs.getInt("numVaga"));
                boolean ocupado = (this.rs.getBoolean("ocupado"));

                v = new Vaga(vaga, ocupado);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return v;
    }

    public boolean ocuparVaga(int id){
        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "UPDATE vaga SET ocupado = true WHERE numvaga = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setInt(1, id);

            return this.preparedStatement.execute();

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean desocuparVaga(int id){
        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "UPDATE vaga SET ocupado = false WHERE numvaga = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setInt(1, id);

            return this.preparedStatement.execute();

        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
