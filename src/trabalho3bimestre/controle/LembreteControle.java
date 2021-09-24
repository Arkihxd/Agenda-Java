/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.controle;
import java.sql.*;
import java.text.SimpleDateFormat; 
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabalho3bimestre.modelo.Compromisso;
import trabalho3bimestre.modelo.JDBCCompromissoDAO;
import trabalho3bimestre.modelo.JDBCTarefaDAO;
import trabalho3bimestre.modelo.Tarefa;
 

public class LembreteControle implements Runnable{
    private JDBCCompromissoDAO bancoCompromisso;
    private JDBCTarefaDAO bancoTarefa;
    PreparedStatement stmt;
    ResultSet rs;
    private ArrayList<Compromisso> compromissosPendentes = new ArrayList<Compromisso>();
    private ArrayList<Tarefa> tarefasPendentes = new ArrayList<Tarefa>();
    PrincipalControle pControle;
    
    public LembreteControle(PrincipalControle pControle) throws SQLException{
            bancoCompromisso = new JDBCCompromissoDAO();
            bancoTarefa = new JDBCTarefaDAO();
            this.pControle=pControle;
    }

    @Override
    public void run() {
        try{
            for(;;){
                compromissosPendentes.clear();
                tarefasPendentes.clear();
                        compromissosPendentes = bancoCompromisso.buscaPeriodo(System.currentTimeMillis(), 2);
                        bancoCompromisso.arquiva(System.currentTimeMillis());
                        tarefasPendentes = bancoTarefa.buscaPeriodo(System.currentTimeMillis(), 2);
                        bancoTarefa.arquiva(System.currentTimeMillis());
                        
                        pControle.atualizaCompromisso(compromissosPendentes);
                        pControle.atualizaTarefa(tarefasPendentes);
                        Thread.sleep(30000);
                } 
            }catch(SQLException ex){
                Logger.getLogger(LembreteControle.class.getName()).log(Level.SEVERE, null, ex);
            }catch(Exception e){
                e.printStackTrace();
            }         
    }  
    
}
