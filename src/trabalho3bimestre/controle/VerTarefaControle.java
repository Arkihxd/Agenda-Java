/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.controle;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import trabalho3bimestre.modelo.Tarefa;
import trabalho3bimestre.modelo.TarefaDAO;
import trabalho3bimestre.modelo.Tarefa;
import trabalho3bimestre.modelo.JDBCTarefaDAO;
import trabalho3bimestre.visao.VerTarefa;

/**
 *
 * @author haperlin
 */
public class VerTarefaControle implements ActionListener{
    private VerTarefa janela;
    private JDBCTarefaDAO TarefaDAO;
    
    public VerTarefaControle(){
        TarefaDAO = new JDBCTarefaDAO();
        this.janela = new VerTarefa();
        janela.btTudo.addActionListener(this);
        janela.btId.addActionListener(this);
        janela.btNome.addActionListener(this);
        janela.btData.addActionListener(this);
        janela.btFechar.addActionListener(this);
        janela.btDesativar.addActionListener(this);
        
    }
    
    public void abrir() throws SQLException{
        listaTudo();
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        dialog.setContentPane(janela);
        dialog.setSize(janela.getSize());
        janela.setDialog(dialog);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == janela.btTudo){
            try {
                listaTudo();
            } catch (SQLException ex) {
                Logger.getLogger(VerTarefaControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btId){
            try {
                listaId();
            } catch (SQLException ex) {
                Logger.getLogger(VerTarefaControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btNome){
            try {
                listaNome();
            } catch (SQLException ex) {
                Logger.getLogger(VerTarefaControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btData){
            try {
                listaData();
            } catch (SQLException ex) {
                Logger.getLogger(VerTarefaControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(VerTarefaControle.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        else if(e.getSource() == janela.btDesativar){
            try {
                desativa();
                listaTudo();
            } catch (SQLException ex) {
                Logger.getLogger(VerTarefaControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btFechar){
            janela.janela.dispose();
        }
        
        
    }
    
    private void listaTudo() throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.addColumn("Id");
         modelo.addColumn("Nome");
         modelo.addColumn("Data");
         modelo.addColumn("Hora");
         modelo.addColumn("Descrição");
         modelo.addColumn("Realização");
         modelo.addColumn("Atividade");
         
         for(Tarefa l : TarefaDAO.lista()){
            Object[] linha = new Object[7];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getData();
            linha[3] = l.getHora();
            linha[4] = l.getDescricao();
            linha[5] = l.isRealizacao();
            linha[6] = l.isAtividade();
            modelo.addRow(linha);
            
        }
         janela.tbDetalhes.setModel(modelo);
    }
    
    private void listaId() throws SQLException{
        try{
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.addColumn("Id");
         modelo.addColumn("Nome");
         modelo.addColumn("Data");
         modelo.addColumn("Hora");
         modelo.addColumn("Descrição");
         modelo.addColumn("Realização");
         modelo.addColumn("Atividade");
         
         Tarefa l =TarefaDAO.busca(Integer.valueOf(janela.tfFiltro.getText()));
            Object[] linha = new Object[7];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getData();
            linha[3] = l.getHora();
            linha[4] = l.getDescricao();
            linha[5] = l.isRealizacao();
            linha[6] = l.isAtividade();
            modelo.addRow(linha);
            
        
         janela.tbDetalhes.setModel(modelo);
         }catch(NullPointerException e){
            janela.mostraMensagem("Sua pesquisa não retornou nada.");
        }catch(NumberFormatException e1){
            janela.mostraMensagem("Para pesquisar por ID você precisa digitar um número.");
        }catch(SQLException e2){
            janela.mostraMensagem("Problemas com o banco. Aguarde uma atualização.");
        }
    }
    
    private void listaNome() throws SQLException{
        try{
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.addColumn("Id");
         modelo.addColumn("Nome");
         modelo.addColumn("Data");
         modelo.addColumn("Hora");
         modelo.addColumn("Descrição");
         modelo.addColumn("Realização");
         modelo.addColumn("Atividade");
         
         for(Tarefa l : TarefaDAO.buscaNome(janela.tfFiltro.getText())){
            Object[] linha = new Object[7];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getData();
            linha[3] = l.getHora();
            linha[4] = l.getDescricao();
            linha[5] = l.isRealizacao();
            linha[6] = l.isAtividade();
            modelo.addRow(linha);
            
        }
         janela.tbDetalhes.setModel(modelo);
         }catch(NullPointerException e){
            janela.mostraMensagem("Sua pesquisa não retornou nada.");
        }catch(SQLException e2){
            janela.mostraMensagem("Problemas com o banco. Aguarde uma atualização.");
        }
    }
    
    private void listaData() throws SQLException, ParseException{
        try{
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.addColumn("Id");
         modelo.addColumn("Nome");
         modelo.addColumn("Data");
         modelo.addColumn("Hora");
         modelo.addColumn("Descrição");
         modelo.addColumn("Realização");
         modelo.addColumn("Atividade");
         
         SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
         for(Tarefa l : TarefaDAO.buscaData(new java.sql.Date(dataFormat.parse(janela.tfFiltro.getText()).getTime()))){
            Object[] linha = new Object[7];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getData();
            linha[3] = l.getHora();
            linha[4] = l.getDescricao();
            linha[5] = l.isRealizacao();
            linha[6] = l.isAtividade();
            modelo.addRow(linha);
            
        }
         janela.tbDetalhes.setModel(modelo);
         }catch(NullPointerException e){
            janela.mostraMensagem("Sua pesquisa não retornou nada.");
        }catch(ParseException e1){
            janela.mostraMensagem("Você digitou uma data inválida. Por favor use o formado dd/mm/aaaa. Datas digitadas em formato dd/mm/aa serão interpretadas erroneamente.");
        }catch(SQLException e2){
            janela.mostraMensagem("Problemas com o banco. Aguarde uma atualização.");
        }
    }
    
    private void desativa() throws SQLException{
        try{
        if(TarefaDAO.busca(Integer.valueOf(janela.tfFiltro.getText())).isAtividade()==true){
            TarefaDAO.deleta(TarefaDAO.busca(Integer.valueOf(janela.tfFiltro.getText())));
        }else{
            TarefaDAO.recupera(TarefaDAO.busca(Integer.valueOf(janela.tfFiltro.getText())));
        }
        }catch(NullPointerException e){
            janela.mostraMensagem("Não foi possível desativar. Confira se foi digitado o ID correto.");
        }catch(NumberFormatException e1){
            janela.mostraMensagem("Para realizar a deleção você deve digitar o ID..");
        }catch(SQLException e2){
            janela.mostraMensagem("Problemas com o banco. Aguarde uma atualização.");
        }
    }
    

    
}
