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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author aluno
 */
public class JDBCCompromissoDAO  implements CompromissoDAO{
    private JDBCContatoDAO gerenciaMandante = new JDBCContatoDAO();

    @Override
    public void insere(Compromisso t) throws SQLException {
       Connection con = Conexao.getConnection();
        
        String sql = "insert into Compromisso(nome,data,hora,mandante,descricao, realizacao, atividade) values(?, ?, ?, ?, ?, ?, ?);";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setString(1, t.getNome());
        pStmt.setLong(2, t.getData().getTime());
        pStmt.setLong(3, t.getHora().getTime());
        pStmt.setInt(4, t.getMandante().getId());
        pStmt.setString(5, t.getDescricao());
        pStmt.setBoolean(6, t.isRealizacao());
        pStmt.setBoolean(7, t.isAtividade());
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close(); 
    }

    @Override
    public Compromisso busca(int pid) throws SQLException {
        Compromisso resultado = null;
        
        Connection con = Conexao.getConnection();
        
        PreparedStatement pStmt = con.prepareStatement("select * from Compromisso where id = ?");
        
        pStmt.setInt(1, pid);
        
        ResultSet rs = pStmt.executeQuery();
        
         while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                Contato mandante = gerenciaMandante.busca(rs.getInt("mandante"));
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                resultado = new Compromisso(id, nome, data, hora, descricao, mandante, realizacao, atividade);
            rs.close();
        }
        pStmt.close();
        con.close();
        return resultado;
      
    }

    @Override
    public void altera(Compromisso t) throws SQLException {
        Connection con = Conexao.getConnection();
        
        String sql = "UPDATE Compromisso SET nome = ?, data = ?, descricao = ?";
        
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
    public void deleta(Compromisso t) throws SQLException {
        Connection con = Conexao.getConnection();
       
        String sql = "UPDATE Compromisso set atividade = 0 where id = ?";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1, t.getId());
        
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close();
      
    }
    
    public void recupera(Compromisso t) throws SQLException {
        Connection con = Conexao.getConnection();
       
        String sql = "UPDATE Compromisso set atividade = 1 where id = ?";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1, t.getId());
        
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close();
      
    }
 
    public void arquiva(Long agora) throws SQLException {
          Connection con = Conexao.getConnection();
        
        
        String sql = "UPDATE Compromisso set realizacao = 1 where data+hora-10800000 < ?";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setLong(1, agora);
        
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close();
      
    }

    @Override
    public ArrayList<Compromisso> lista() throws SQLException {
          ArrayList<Compromisso> resultado = new ArrayList<Compromisso>();

        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Compromisso");
        
        ResultSet rs = pStmt.executeQuery();;
        
       
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                Contato mandante = gerenciaMandante.busca(rs.getInt("mandante"));
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Compromisso cont = new Compromisso(id, nome, data, hora, descricao, mandante, realizacao, atividade);
                resultado.add(cont);
                
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;
      
    }
    
    public ArrayList<Compromisso> buscaPeriodo(Long agora, int horas) throws SQLException {
        ArrayList<Compromisso> resultado = new ArrayList<Compromisso>();
        Long periodo=agora+(3600000*horas);
        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Compromisso where atividade=1 and data+hora-10800000 between ? and ?");
        
        pStmt.setLong(1, agora);
        pStmt.setLong(2, periodo);
        
        ResultSet rs = pStmt.executeQuery();
        
       
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                Contato mandante = gerenciaMandante.busca(rs.getInt("mandante"));
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Compromisso cont = new Compromisso(id, nome, data, hora, descricao, mandante, realizacao, atividade);
                resultado.add(cont);
                
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;

    }

    
    public ArrayList<Compromisso> buscaNome(String bnome) throws SQLException {
        ArrayList<Compromisso> resultado = new ArrayList<Compromisso>();

        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Compromisso where nome like ?");
        
        pStmt.setString(1, bnome);
        
        ResultSet rs = pStmt.executeQuery();;
        
       
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                Contato mandante = gerenciaMandante.busca(rs.getInt("mandante"));
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Compromisso cont = new Compromisso(id, nome, data, hora, descricao, mandante, realizacao, atividade);
                resultado.add(cont);
                
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;

    }


    
    public ArrayList<Compromisso> buscaMandante(int mid) throws SQLException {
        ArrayList<Compromisso> resultado = new ArrayList<Compromisso>();
        
        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Compromisso where mandante = ?;");
        
        pStmt.setInt(1, mid);
        
        ResultSet rs = pStmt.executeQuery();;
        
       
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                Contato mandante = gerenciaMandante.busca(rs.getInt("mandante"));
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Compromisso cont = new Compromisso(id, nome, data, hora, descricao, mandante, realizacao, atividade);
                resultado.add(cont);
                
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;
      
    }

    
    public ArrayList<Compromisso> buscaData(Date xdata) throws SQLException {
        ArrayList<Compromisso> resultado = new ArrayList<Compromisso>();
        System.out.println(xdata.getTime());
        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Compromisso where data = ?;");
        
        pStmt.setLong(1, xdata.getTime());
        
        ResultSet rs = pStmt.executeQuery();;
        
       while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                Contato mandante = gerenciaMandante.busca(rs.getInt("mandante"));
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Compromisso cont = new Compromisso(id, nome, data, hora, descricao, mandante, realizacao, atividade);
                resultado.add(cont);
                
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;
    }

    
    public ArrayList<Compromisso> buscaHorario(Time xtime) throws SQLException {
         ArrayList<Compromisso> resultado = new ArrayList<Compromisso>();

        Connection c = Conexao.getConnection();
        Statement stmt = c.createStatement();
        
        PreparedStatement pStmt = c.prepareStatement("select * from Compromisso where horario = ?;");
        
        pStmt.setLong(1, xtime.getTime());
        
        ResultSet rs = pStmt.executeQuery();;
        
       
            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                String descricao = rs.getString("descricao");
                Contato mandante = gerenciaMandante.busca(rs.getInt("mandante"));
                boolean realizacao = rs.getBoolean("realizacao");
                boolean atividade = rs.getBoolean("atividade");
                Compromisso cont = new Compromisso(id, nome, data, hora, descricao, mandante, realizacao, atividade);
                resultado.add(cont);
                
            }
            rs.close();
        
        stmt.close();
        c.close();
        return resultado;
    }
    
}
