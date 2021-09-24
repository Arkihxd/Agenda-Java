package trabalho3bimestre.controle;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JDialog;
import trabalho3bimestre.modelo.Compromisso;
import trabalho3bimestre.modelo.Contato;
import trabalho3bimestre.modelo.JDBCCompromissoDAO;
import trabalho3bimestre.modelo.JDBCContatoDAO;
import trabalho3bimestre.visao.CadastroCompromissos;


public class CadastroCompromissosControle implements ActionListener{
    public CadastroCompromissos cadastroCompromisso;
    public JDBCCompromissoDAO bancoCompromisso;
    public JDBCContatoDAO bancoContato;
    
    public CadastroCompromissosControle() throws SQLException{
        bancoCompromisso = new JDBCCompromissoDAO();
        bancoContato = new JDBCContatoDAO();
        cadastroCompromisso = new CadastroCompromissos();
        cadastroCompromisso.btCadastro.addActionListener(this);
        cadastroCompromisso.btCancela.addActionListener(this);
        
        
    }
    
    private void criaCompromisso(){
        try{
            if(cadastroCompromisso.tfNome.getText().isEmpty() || cadastroCompromisso.tfDescricao.getText().isEmpty() || cadastroCompromisso.tfData.getText().isEmpty() ){
                throw new Exception("Preencha todos os campos!!");
            }    
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
            Compromisso cc = new Compromisso(cadastroCompromisso.tfNome.getText(), new java.sql.Date(dataFormat.parse(cadastroCompromisso.tfData.getText()).getTime()), new java.sql.Time(horaFormat.parse(cadastroCompromisso.tfHora.getText()).getTime()), cadastroCompromisso.tfDescricao.getText(), (Contato)cadastroCompromisso.jbxMandante.getSelectedItem());
            bancoCompromisso.insere(cc);
            cadastroCompromisso.mostraMensagem(cadastroCompromisso.tfNome.getText()+" cadastrado com sucesso");
            limpar();
        }catch(ParseException e){
            cadastroCompromisso.mostraMensagem("Você digitou uma data inválida. Por favor use o formado dd/mm/aaaa. Datas digitadas em formato dd/mm/aa serão interpretadas erroneamente.");
        }catch(SQLException e1){
            cadastroCompromisso.mostraMensagem("Problema com o banco. Aguarde uma atualização.");
        }catch(ClassCastException e1){
            cadastroCompromisso.mostraMensagem("Por favor, selecione um mandante. Se você quer cadastrar uma atividade sem mandante, tente cadastrar uma tarefa!");
        }catch(Exception e){
            cadastroCompromisso.mostraMensagem(e.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cadastroCompromisso.btCadastro){
            criaCompromisso();
        }
        else if(e.getSource()==cadastroCompromisso.btCancela){
            cadastroCompromisso.janela.dispose();
        }
    }
    
    public void mostraJanela() throws SQLException{
        cadastroCompromisso.jbxMandante.removeAllItems();
        montaCombo();
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        dialog.setContentPane(cadastroCompromisso);
        dialog.setSize(cadastroCompromisso.getSize());
        cadastroCompromisso.setDialog(dialog);
        dialog.setVisible(true);
    }
    
    private void montaCombo() throws SQLException{
        cadastroCompromisso.jbxMandante.addItem("");
         for(Contato c:bancoContato.lista()){
            cadastroCompromisso.jbxMandante.addItem(c);	
         }	
    }

     
    public void limpar(){
        cadastroCompromisso.tfNome.setText("");
        cadastroCompromisso.tfData.setText("");
        cadastroCompromisso.tfHora.setText("");
        cadastroCompromisso.tfDescricao.setText("");
        cadastroCompromisso.janela.dispose();
    }
    
}
