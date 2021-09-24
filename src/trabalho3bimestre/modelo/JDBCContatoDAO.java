
package trabalho3bimestre.modelo;

import trabalho3bimestre.modelo.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCContatoDAO implements ContatoDAO{

    @Override
    public void insere(Contato c) throws SQLException {
      Connection con = Conexao.getConnection();
        
        String sql = "insert into Contato(nome,ano,email,telefone, atividade) values(?, ?, ?, ?, ?);";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setString(1, c.getNome());
        pStmt.setInt(2, c.getAno());
        pStmt.setString(3, c.getEmail());
        pStmt.setString(4, c.getTelefone());
        pStmt.setBoolean(5, c.isAtividade());
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close(); 
    }

    @Override
    public Contato busca(int id) throws SQLException {
        Contato resultado = null;
        
        Connection con = Conexao.getConnection();
        
        PreparedStatement pStmt = con.prepareStatement("select * from Contato where id = ?");
        
        pStmt.setInt(1, id);
        
        ResultSet rs = pStmt.executeQuery();
        
        if(rs.next()){
            
                int sid = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                boolean atividade = rs.getBoolean("atividade");
                int ano = rs.getInt("ano");

                resultado = new Contato(sid, nome, email, telefone,atividade, ano);
            
            rs.close();
        }
        pStmt.close();
        con.close();
        return resultado;           
        
    }

    @Override
    public void altera(Contato c) throws SQLException {
           Connection con = Conexao.getConnection();
        
        
        String sql = "UPDATE Contato SET nome = ?, ano = ?, email = ?, telefone = ? where id = ?;";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setString(1, c.getNome());
        pStmt.setInt(2, c.getAno());
        pStmt.setString(3, c.getEmail());
        pStmt.setString(4, c.getTelefone());
        pStmt.setInt(5, c.getId());
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close(); 
        
    }

    @Override
    public void deleta(Contato c) throws SQLException {
     Connection con = Conexao.getConnection();
        
        
        String sql = "UPDATE Contato SET atividade=0 where id = ?;";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1, c.getId());
        
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close();
    }
    
    public void recupera(Contato c) throws SQLException {
     Connection con = Conexao.getConnection();
        
        
        String sql = "UPDATE Contato SET atividade=1 where id = ?;";
        
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setInt(1, c.getId());
        
        pStmt.executeUpdate(); 
                
        pStmt.close();
        con.close();
    }

    @Override
    public ArrayList<Contato> lista() throws SQLException {
        ArrayList<Contato> resultado = new ArrayList<Contato>();
        
        Connection con = Conexao.getConnection();
        
        PreparedStatement pStmt = con.prepareStatement("select * from Contato");
        
        ResultSet rs = pStmt.executeQuery();
        
        while(rs.next()){
            
                int sid = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                boolean atividade = rs.getBoolean("atividade");
                int ano = rs.getInt("ano");

                Contato c = new Contato(sid, nome, email, telefone,atividade, ano);
            resultado.add(c);
            
        }
        rs.close();
        pStmt.close();
        con.close();
        return resultado; 
       
    }

    public ArrayList<Contato> buscaNome(String bnome) throws SQLException {
        ArrayList<Contato> resultado = new ArrayList<Contato>();
        
        Connection con = Conexao.getConnection();
        
        PreparedStatement pStmt = con.prepareStatement("select * from Contato where nome = ?");
        
        pStmt.setString(1, bnome);
        
        ResultSet rs = pStmt.executeQuery();
        
        while(rs.next()){
            
                int sid = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                boolean atividade = rs.getBoolean("atividade");
                int ano = rs.getInt("ano");

                Contato c = new Contato(sid, nome, email, telefone,atividade, ano);
            resultado.add(c);
            
            
        }
        rs.close();
        pStmt.close();
        con.close();
        return resultado; 
       
    }
	
    
}
