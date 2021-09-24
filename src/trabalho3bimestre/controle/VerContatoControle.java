package trabalho3bimestre.controle;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import trabalho3bimestre.modelo.Contato;
import trabalho3bimestre.modelo.ContatoDAO;
import trabalho3bimestre.modelo.Contato;
import trabalho3bimestre.modelo.JDBCContatoDAO;
import trabalho3bimestre.visao.VerContato;


public class VerContatoControle implements ActionListener{
    private VerContato janela;
    private JDBCContatoDAO ContatoDAO;
    
    public VerContatoControle(){
        ContatoDAO = new JDBCContatoDAO();
        this.janela = new VerContato();
        janela.btTudo.addActionListener(this);
        janela.btId.addActionListener(this);
        janela.btNome.addActionListener(this);
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
                Logger.getLogger(VerContatoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btId){
            try {
                listaId();
            } catch (SQLException ex) {
                Logger.getLogger(VerContatoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btNome){
            try {
                listaNome();
            } catch (SQLException ex) {
                Logger.getLogger(VerContatoControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource() == janela.btDesativar){
            try {
                desativa();
                listaTudo();
            } catch (SQLException ex) {
                Logger.getLogger(VerContatoControle.class.getName()).log(Level.SEVERE, null, ex);
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
         modelo.addColumn("Email");
         modelo.addColumn("Telefone");
         modelo.addColumn("Atividade");
         
         for(Contato l : ContatoDAO.lista()){
            Object[] linha = new Object[5];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getEmail();
            linha[3] = l.getTelefone();
            linha[4] = l.isAtividade();
            modelo.addRow(linha);
            
        }
         janela.tbDetalhes.setModel(modelo);
    }
    
    private void listaNome() throws SQLException{
        try{
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.addColumn("Id");
         modelo.addColumn("Nome");
         modelo.addColumn("Email");
         modelo.addColumn("Telefone");
         modelo.addColumn("Atividade");
         
         for(Contato l : ContatoDAO.buscaNome(janela.tfFiltro.getText())){
            Object[] linha = new Object[5];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getEmail();
            linha[3] = l.getTelefone();
            linha[4] = l.isAtividade();
            modelo.addRow(linha);
            
        }
         janela.tbDetalhes.setModel(modelo);
         }catch(NullPointerException e){
            janela.mostraMensagem("Sua pesquisa não retornou nada.");
        }catch(SQLException e2){
            janela.mostraMensagem("Problemas com o banco. Aguarde uma atualização.");
        }
    }
    
    private void listaId() throws SQLException{
        try{
        DefaultTableModel modelo = new DefaultTableModel();
         modelo.addColumn("Id");
         modelo.addColumn("Nome");
         modelo.addColumn("Email");
         modelo.addColumn("Telefone");
         modelo.addColumn("Atividade");
         
         Contato l = ContatoDAO.busca(Integer.valueOf(janela.tfFiltro.getText()));
            Object[] linha = new Object[5];
            linha[0] = l.getId();
            linha[1] = l.getNome();
            linha[2] = l.getEmail();
            linha[3] = l.getTelefone();
            linha[4] = l.isAtividade();
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
       
    private void desativa() throws SQLException{
        try{
        if(ContatoDAO.busca(Integer.valueOf(janela.tfFiltro.getText())).isAtividade()==true){
            ContatoDAO.deleta(ContatoDAO.busca(Integer.valueOf(janela.tfFiltro.getText())));
        }else{
            ContatoDAO.recupera(ContatoDAO.busca(Integer.valueOf(janela.tfFiltro.getText())));
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
