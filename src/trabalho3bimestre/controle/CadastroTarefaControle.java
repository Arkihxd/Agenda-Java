package trabalho3bimestre.controle;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JDialog;
import trabalho3bimestre.modelo.Tarefa;
import trabalho3bimestre.modelo.JDBCTarefaDAO;
import trabalho3bimestre.modelo.JDBCContatoDAO;
import trabalho3bimestre.visao.CadastroTarefas;


public class CadastroTarefaControle implements ActionListener{
    public CadastroTarefas cadastroTarefa;
    public JDBCTarefaDAO bancoTarefa;
    public JDBCContatoDAO bancoContato;
    
    public CadastroTarefaControle(){
        bancoTarefa = new JDBCTarefaDAO();
        bancoContato = new JDBCContatoDAO();
        cadastroTarefa = new CadastroTarefas();
        cadastroTarefa.btCadastro.addActionListener(this);
        cadastroTarefa.btCancela.addActionListener(this);
    }
    
    private void criaTarefa(){
        try{
            if(cadastroTarefa.tfNome.getText().isEmpty() || cadastroTarefa.tfDescricao.getText().isEmpty() || cadastroTarefa.tfData.getText().isEmpty()){
                throw new Exception("Preencha todos os campos!!");
            }    
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm:ss");
            bancoTarefa.insere(new Tarefa( cadastroTarefa.tfNome.getText(), new java.sql.Date(dataFormat.parse(cadastroTarefa.tfData.getText()).getTime()), new java.sql.Time(horaFormat.parse(cadastroTarefa.tfHora.getText()).getTime()), cadastroTarefa.tfDescricao.getText()));
            cadastroTarefa.mostraMensagem(cadastroTarefa.tfNome.getText()+" cadastrado com sucesso");
            limpar();
        }catch(ParseException e){
            cadastroTarefa.mostraMensagem("Você digitou uma data inválida. Por favor use o formado dd/mm/aaaa. Datas digitadas em formato dd/mm/aa serão interpretadas erroneamente.");
        }catch(SQLException e1){
            cadastroTarefa.mostraMensagem("Problema com o banco. Aguarde uma atualização.");
        }catch(Exception e){
            cadastroTarefa.mostraMensagem(e.getMessage());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cadastroTarefa.btCadastro){
            criaTarefa();
        }
        else if(e.getSource()==cadastroTarefa.btCancela){
            cadastroTarefa.janela.dispose();
        }
    }
    
    public void mostraJanela(){
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        dialog.setContentPane(cadastroTarefa);
        dialog.setSize(cadastroTarefa.getSize());
        cadastroTarefa.setDialog(dialog);
        dialog.setVisible(true);
    }
    
    public void limpar(){
        cadastroTarefa.tfNome.setText("");
        cadastroTarefa.tfData.setText("");
        cadastroTarefa.tfHora.setText("");
        cadastroTarefa.tfDescricao.setText("");
        cadastroTarefa.janela.dispose();
    }
    
}
