/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class JDBCTarefaDAO  implements TarefaDAO{

    @Override
    public void insere(Tarefa t) throws SQLException {
       Connection con = Conexao.getConnection();
        
        String sql = "insert into Tarefa(nome,data, hora,descricao, realizacao, atividade) values(?, ?, ?, ?,?, ?);";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setString(1, t.getNome());
        pStmt.setDate(2, t.getData());
        pStmt.setTime(3, t.getHora());
        pStmt.setString(4, t.getDescricao());
        pStmt.setBoolean(5, t.isRealizacao());
        pStmt.setBoolean(6, t.isAtividade());
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close(); 
    }

    @Override
    public Tarefa busca(int pid) throws SQLException {
        Tarefa resultado = null;
        
        Connection con = Conexao.getConnection();
        
        PreparedStatement pStmt = con.prepareStatement("select * from Tarefa where id = ?;");
        
        pStmt.setInt(1, pid);
        
        ResultSet rs = pStmt.executeQuery();
        
        if(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                resultado = new Tarefa(id, nome, data, hora, descricao, realizacao, atividade);
            }
            rs.close();
        
        pStmt.close();
        con.close();
        return resultado;
      
    }

    @Override
    public void altera(Tarefa t) throws SQLException {
        Connection con = Conexao.getConnection();
        
        
        String sql = "UPDATE Tarefa SET nome = ?, data = ?, descricao = ? where id = ?";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setString(1, t.getNome());
        pStmt.setDate(2, t.getData());
        pStmt.setString(3, t.getDescricao());
        pStmt.setInt(4, t.getId());
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close();
    }

    @Override
    public void deleta(Tarefa t) throws SQLException {
          Connection con = Conexao.getConnection();
        
        
        String sql = "UPDATE Tarefa set atividade = 0 where id = ?";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1, t.getId());
        
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close();
        
      
    }
    
    public void recupera(Tarefa t) throws SQLException {
          Connection con = Conexao.getConnection();
        
        
        String sql = "UPDATE Tarefa set atividade = 1 where id = ?";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1, t.getId());
        
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close();
        
      
    }
 
    public void arquiva(Long agora) throws SQLException {
          Connection con = Conexao.getConnection();
        
        
        String sql = "UPDATE Tarefa set realizacao = 1 where data+hora-10800000 < ?";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setLong(1, agora);
        
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close();
      
    }
    
    public ArrayList<Tarefa> buscaPeriodo(Long agora, int horas) throws SQLException {
       ArrayList<Tarefa> resultado = new ArrayList<Tarefa>();

        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Tarefa where data+hora-10800000 between ? and ?;");
        
        pStmt.setLong(1, agora);
        pStmt.setLong(2, agora+(3600000*horas));
        
        ResultSet rs = pStmt.executeQuery();;
        
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Tarefa t = new Tarefa(id, nome, data, hora, descricao, realizacao, atividade);
                resultado.add(t);
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;
    }


    @Override
    public ArrayList<Tarefa> lista() throws SQLException {
          ArrayList<Tarefa> resultado = new ArrayList<Tarefa>();

        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Tarefa;");
        
        ResultSet rs = pStmt.executeQuery();;
        
            while(rs.next()){
               int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Tarefa t = new Tarefa(id, nome, data, hora, descricao, realizacao, atividade);
                resultado.add(t);
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;
      
    }

    public ArrayList<Tarefa> buscaHorario(Time xtime) throws SQLException {
       ArrayList<Tarefa> resultado = new ArrayList<Tarefa>();

        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Tarefa where horario = ?;");
        
        pStmt.setLong(1, xtime.getTime());
        
        ResultSet rs = pStmt.executeQuery();;
        
            while(rs.next()){
               int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Tarefa t = new Tarefa(id, nome, data, hora, descricao, realizacao, atividade);
                resultado.add(t);
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;
    }

    public ArrayList<Tarefa> buscaNome(String bnome) throws SQLException {
        ArrayList<Tarefa> resultado = new ArrayList<Tarefa>();

        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Tarefa where nome like ?;");
        
        pStmt.setString(1, bnome);
        
        ResultSet rs = pStmt.executeQuery();;
        
       
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Tarefa t = new Tarefa(id, nome, data, hora, descricao, realizacao, atividade);
                resultado.add(t);
                
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;

    }

   public ArrayList<Tarefa> buscaData(Date xdata) throws SQLException {
       ArrayList<Tarefa> resultado = new ArrayList<Tarefa>();

        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Tarefa where data = ?;");
        
        pStmt.setLong(1, xdata.getTime());
        
        ResultSet rs = pStmt.executeQuery();;
        
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Tarefa t = new Tarefa(id, nome, data, hora, descricao, realizacao, atividade);
                resultado.add(t);
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;
      
    }
    
    
}
