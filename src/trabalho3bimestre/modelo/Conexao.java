package trabalho3bimestre.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author haperlin
 */
public class Conexao {
    private static final String URL_BANCO = "jdbc:sqlite:Banco3Bim.sqlite";
    private static Connection[] pool = new Connection[5];
    
    public static Connection getConnection() throws SQLException{
        
        for(int i=0;i<pool.length;i++){
            if(pool[i]==null || pool[i].isClosed()){
                pool[i] = DriverManager.getConnection(URL_BANCO); 
                return pool[i];
            }
        }
        throw new SQLException("Sem conexão disponíveis!!!");
    }
    
     public void limparConnection() throws SQLException {
        
        for(int i=0;i<pool.length;i++){
            if(pool[i]!= null ){
                pool[i].isClosed();
                pool[i]= null;
            }
        }

    }
    
    
}

