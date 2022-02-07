package br.ufsm.csi.poow2.dao;

import br.ufsm.csi.poow2.model.Estadia;
import br.ufsm.csi.poow2.model.Vaga;
import br.ufsm.csi.poow2.model.Veiculo;
import br.ufsm.csi.poow2.util.ConectaBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EstadiaDAO {

    private String sql;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private String status;

    private Vaga vaga = new Vaga();

    public boolean cadastrarEstadia(Estadia estadia){

        try(Connection connection = new ConectaBD().getConexao()) {

            vaga = new VagaDAO().getVagaById(estadia.getVaga().getNumVaga());

            if(vaga.isOcupado()){
                return false;
            }else{
                this.sql = "INSERT INTO estadia (data, hrentrada, hrsaida, numvaga, codveiculo) VALUES"+
                        " (CURRENT_DATE, ?, ?, ?, ?);";
                this.preparedStatement = connection.prepareStatement(this.sql);

                this.preparedStatement.setString(1, estadia.getHr_entrada());
                this.preparedStatement.setString(2, estadia.getHr_saida());
                this.preparedStatement.setInt(3, estadia.getVaga().getNumVaga());
                this.preparedStatement.setInt(4, estadia.getVeiculo().getId());

                this.preparedStatement.execute();
                return true;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Estadia> getEstadias(){
        ArrayList<Estadia> estadias = new ArrayList<>();

        try(Connection connection = new ConectaBD().getConexao()) {

            this.sql = "SELECT *, to_char(data,'DD-MM-YYYY') as data_form FROM estadia e, veiculo ve, vaga va WHERE "+
                    "e.codveiculo = ve.codveiculo AND "+
                    "e.numvaga = va.numvaga ORDER BY e.hrsaida;";
            this.stmt = connection.createStatement();
            this.rs = this.stmt.executeQuery(sql);

            while (this.rs.next()){
                Estadia estadia = new Estadia();

                estadia.setId(this.rs.getInt("codestadia"));
                estadia.setData(this.rs.getString("data_form"));
                estadia.setHr_entrada(this.rs.getString("hrentrada"));
                estadia.setHr_saida(this.rs.getString("hrsaida"));

                Vaga vaga = new Vaga();
                vaga.setNumVaga(this.rs.getInt("numvaga"));

                Veiculo veiculo = new Veiculo();
                veiculo.setId(this.rs.getInt("codveiculo"));
                veiculo.setPlaca(this.rs.getString("placa"));

                estadia.setVaga(vaga);
                estadia.setVeiculo(veiculo);
                estadias.add(estadia);

            }

        }catch (Exception e){
            e.printStackTrace();
            this.status = "ERRO";
        }

        return estadias;
    }

    public Estadia getEstadia(int id){
        Estadia estadia = null;

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM estadia e, veiculo ve, vaga va WHERE " +
                    "e.codveiculo = ve.codveiculo AND " +
                    "e.numvaga = va.numvaga AND e.codestadia = ?;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.rs = this.preparedStatement.executeQuery();

            while(this.rs.next()){
                int codestadia = (this.rs.getInt("codestadia"));
                String hr_entrada = (this.rs.getString("hrentrada"));
                String hr_saida = (this.rs.getString("hrsaida"));

                Vaga vaga = new Vaga();
                vaga.setNumVaga(this.rs.getInt("numvaga"));

                Veiculo veiculo = new Veiculo();
                veiculo.setId(this.rs.getInt("codveiculo"));
                veiculo.setPlaca(this.rs.getString("placa"));

                estadia = new Estadia(codestadia, hr_entrada, hr_saida, vaga, veiculo);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return estadia;
    }

    public boolean saida(Estadia estadia){
        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "UPDATE estadia SET " +
                    "hrsaida = ? WHERE estadia.codestadia = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);

            this.preparedStatement.setString(1, estadia.getHr_saida());
            this.preparedStatement.setInt(2, estadia.getId());

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

    public Estadia getEstadiaByVeiculo(int id){
        Estadia estadia = null;

        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "SELECT * FROM estadia e, veiculo ve, vaga va WHERE e.codveiculo = ? AND " +
                    "e.codveiculo = ve.codveiculo AND " +
                    "e.numvaga = va.numvaga;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.rs = this.preparedStatement.executeQuery();

            while(this.rs.next()){
                int codestadia = (this.rs.getInt("codestadia"));
                String hr_entrada = (this.rs.getString("hrentrada"));
                String hr_saida = (this.rs.getString("hrsaida"));

                Vaga vaga = new Vaga();
                vaga.setNumVaga(this.rs.getInt("numvaga"));

                Veiculo veiculo = new Veiculo();
                veiculo.setId(this.rs.getInt("codveiculo"));
                veiculo.setPlaca(this.rs.getString("placa"));

                estadia = new Estadia(codestadia, hr_entrada, hr_saida, vaga, veiculo);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return estadia;
    }

    public boolean excluirVeiculoEstadia(int id){

        try(Connection connection = new ConectaBD().getConexao()){

            boolean retorno;

            this.sql = "DELETE FROM estadia WHERE estadia.codveiculo = ? ;";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            retorno = this.preparedStatement.execute();

            if(retorno == true){
                return true;
            }else{
                return false;
            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
