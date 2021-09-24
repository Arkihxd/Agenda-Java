package trabalho3bimestre.controle;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JDialog;
import trabalho3bimestre.modelo.Contato;
import trabalho3bimestre.modelo.JDBCContatoDAO;
import trabalho3bimestre.visao.CadastroContatos;

/**
 *
 * @author aluno
 */
public class CadastroContatosControle implements ActionListener{
    public CadastroContatos cadastroContato;
    public JDBCContatoDAO bancoContato;
    
    public CadastroContatosControle(){
        bancoContato = new JDBCContatoDAO();
        cadastroContato = new CadastroContatos();
        cadastroContato.btCadastro.addActionListener(this);
        cadastroContato.btCancela.addActionListener(this);
    }
    
    private void criaContato(){
        try{
            if(cadastroContato.tfNome.getText().isEmpty() || cadastroContato.tfEmail.getText().isEmpty() || cadastroContato.tfTelefone.getText().isEmpty() || cadastroContato.tfAno.getText().isEmpty()){
                 throw new Exception("Preencha todos os campos!!");
            }    
            bancoContato.insere(new Contato(cadastroContato.tfNome.getText(), cadastroContato.tfEmail.getText(), cadastroContato.tfTelefone.getText(), Integer.valueOf(cadastroContato.tfAno.getText())));
            cadastroContato.mostraMensagem(cadastroContato.tfNome.getText()+" cadastrado com sucesso");
            limpar();
        }catch(NumberFormatException e1){
            cadastroContato.mostraMensagem("O ano de nascimento deve ser digitado em formato numérico.");
        }catch (SQLException ex) {
            cadastroContato.mostraMensagem("Problema com o banco. Agurde uma atualização.");
        }catch(Exception e3){
            cadastroContato.mostraMensagem(e3.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cadastroContato.btCadastro){
            criaContato();
        }else if(e.getSource()==cadastroContato.btCancela){
            cadastroContato.janela.dispose();
        }
    }
    
    public void mostraJanela(){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        dialog.setContentPane(cadastroContato);
        dialog.setSize(cadastroContato.getSize());
        cadastroContato.setDialog(dialog);
        dialog.setVisible(true);
    }
    
    public void limpar(){
        cadastroContato.tfNome.setText("");
        cadastroContato.tfAno.setText("");
        cadastroContato.tfEmail.setText("");
        cadastroContato.tfTelefone.setText("");
        cadastroContato.janela.dispose();
    }
    
}
