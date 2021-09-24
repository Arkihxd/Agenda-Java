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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import trabalho3bimestre.modelo.Compromisso;
import trabalho3bimestre.modelo.CompromissoDAO;
import trabalho3bimestre.modelo.Compromisso;
import trabalho3bimestre.modelo.JDBCCompromissoDAO;
import trabalho3bimestre.visao.VerCompromisso;


public class VerCompromissoControle implements ActionListener{
    private VerCompromisso janela;
    private JDBCCompromissoDAO CompromissoDAO;
    
    public VerCompromissoControle(){
        CompromissoDAO = new JDBCCompromissoDAO();
        this.janela = new VerCompromisso();
        janela.btTudo.addActionListener(this);
        janela.btId.addActionListener(this);
        janela.btNome.addActionListener(this);
        janela.btData.addActionListener(this);
        janela.btMandante.addActionListener(this);
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
                Logger.getLogger(VerCompromissoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btId){
            try {
                listaId();
            } catch (SQLException ex) {
                Logger.getLogger(VerCompromissoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btNome){
            try {
                listaNome();
            } catch (SQLException ex) {
                Logger.getLogger(VerCompromissoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btData){
            try {
                listaData();
            } catch (SQLException ex) {
                Logger.getLogger(VerCompromissoControle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(VerCompromissoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btMandante){
            try {
                listaMandante();
            } catch (SQLException ex) {
                Logger.getLogger(VerCompromissoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btFechar){
            janela.janela.dispose();
        }
        else if(e.getSource() == janela.btDesativar){
            try {
                desativa();
                listaTudo();
            } catch (SQLException ex) {
                Logger.getLogger(VerCompromissoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    private void listaTudo() throws SQLException{
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.addColumn("Id");
         modelo.addColumn("Nome");
         modelo.addColumn("Data");
         modelo.addColumn("Hora");
         modelo.addColumn("Mandante");
         modelo.addColumn("Descrição");
         modelo.addColumn("Realização");
         modelo.addColumn("Atividade");
         
         for(Compromisso l : CompromissoDAO.lista()){
            Object[] linha = new Object[8];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getData();
            linha[3] = l.getHora();
            linha[4] = l.getMandante();
            linha[5] = l.getDescricao();
            linha[6] = l.isRealizacao();
            linha[7] = l.isAtividade();
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
         modelo.addColumn("Mandante");
         modelo.addColumn("Descrição");
         modelo.addColumn("Realização");
         modelo.addColumn("Atividade");
         
         Compromisso l =CompromissoDAO.busca(Integer.valueOf(janela.tfFiltro.getText()));
            Object[] linha = new Object[8];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getData();
            linha[3] = l.getHora();
            linha[4] = l.getMandante();
            linha[5] = l.getDescricao();
            linha[6] = l.isRealizacao();
            linha[7] = l.isAtividade();
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
         modelo.addColumn("Mandante");
         modelo.addColumn("Descrição");
         modelo.addColumn("Realização");
         modelo.addColumn("Atividade");
         
         for(Compromisso l : CompromissoDAO.buscaNome(janela.tfFiltro.getText())){
            Object[] linha = new Object[8];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getData();
            linha[3] = l.getHora();
            linha[4] = l.getMandante();
            linha[5] = l.getDescricao();
            linha[6] = l.isRealizacao();
            linha[7] = l.isAtividade();
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
         modelo.addColumn("Mandante");
         modelo.addColumn("Descrição");
         modelo.addColumn("Realização");
         modelo.addColumn("Atividade");
         
         SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
         for(Compromisso l : CompromissoDAO.buscaData(new java.sql.Date(dataFormat.parse(janela.tfFiltro.getText()).getTime()))){
            Object[] linha = new Object[8];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getData();
            linha[3] = l.getHora();
            linha[4] = l.getMandante();
            linha[5] = l.getDescricao();
            linha[6] = l.isRealizacao();
            linha[7] = l.isAtividade();
            modelo.addRow(linha);
            
        }
         janela.tbDetalhes.setModel(modelo);
         }catch(ParseException e){
            janela.mostraMensagem("Você digitou uma data inválida. Por favor use o formado dd/mm/aaaa. Datas digitadas em formato dd/mm/aa serão interpretadas erroneamente.");
        }catch(SQLException e2){
            janela.mostraMensagem("Problemas com o banco. Aguarde uma atualização.");
        }
    }
    
    private void listaMandante() throws SQLException{
        try{
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.addColumn("Id");
         modelo.addColumn("Nome");
         modelo.addColumn("Data");
         modelo.addColumn("Hora");
         modelo.addColumn("Mandante");
         modelo.addColumn("Descrição");
         modelo.addColumn("Realização");
         modelo.addColumn("Atividade");
         
         for(Compromisso l : CompromissoDAO.buscaMandante(Integer.valueOf(janela.tfFiltro.getText()))){
            Object[] linha = new Object[8];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getData();
            linha[3] = l.getHora();
            linha[4] = l.getMandante();
            linha[5] = l.getDescricao();
            linha[6] = l.isRealizacao();
            linha[7] = l.isAtividade();
            modelo.addRow(linha);
            
        }
         janela.tbDetalhes.setModel(modelo);
          }catch(NumberFormatException e1){
            janela.mostraMensagem("Existem muitas pessoas com nome igual não é? Para pesquisar pelo mandante você precisa digitar seu Id, que pode ser conferido na janela de Contatos.");
        }catch(SQLException e2){
            janela.mostraMensagem("Problemas com o banco. Aguarde uma atualização.");
        }
    }
    
    private void desativa() throws SQLException{
        try{
        if(CompromissoDAO.busca(Integer.valueOf(janela.tfFiltro.getText())).isAtividade()==true){
            CompromissoDAO.deleta(CompromissoDAO.busca(Integer.valueOf(janela.tfFiltro.getText())));
        }else{
            CompromissoDAO.recupera(CompromissoDAO.busca(Integer.valueOf(janela.tfFiltro.getText())));
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
