/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


/**
 *
 * @author aluno
 */
public class CadastroCompromissos extends JPanel{
    public JButton btCadastro, btCancela;
    public JLabel lbNome, lbData, lbDescricao, lbMandante, lbHora;
    public JTextField tfNome, tfData, tfDescricao, tfMandante, tfHora;
    public JDialog janela;
    public JComboBox jbxMandante;
    private JPanel painelCentral, painelSul, painelNorte;
    
    public CadastroCompromissos(){
        this.setSize(640, 480);
        this.setLayout(new FlowLayout());
        
        this.setSize(740, 400);
        this.setLayout(new BorderLayout(2,2));
        
        lbNome = new JLabel("Nome:");
        tfNome = new JTextField("", 20);
        lbMandante = new JLabel("Mandante:");
        jbxMandante = new JComboBox();
        lbData = new JLabel("Data (dd/mm/aaaa):");
        tfData = new JTextField("", 20);
        lbDescricao = new JLabel("Descricao:");
        tfDescricao = new JTextField("", 20);
        lbHora = new JLabel("Hora (HH:mm:ss):");
        tfHora = new JTextField("", 20);
        btCadastro = new JButton("Cadastrar");
        btCancela = new JButton("Cancelar");
        painelCentral = new JPanel();
        painelSul = new JPanel();
        painelNorte = new JPanel();
        painelNorte.setLayout(new GridLayout(1,2));
        painelSul.setLayout(new GridLayout(5,2));
        painelNorte.add(btCadastro);
        painelNorte.add(btCancela);
        painelSul.add(lbNome);
        painelSul.add(tfNome);
        painelSul.add(lbData);
        painelSul.add(tfData);
        painelSul.add(lbMandante);
        painelSul.add(jbxMandante);
        painelSul.add(lbDescricao);
        painelSul.add(tfDescricao);
        painelSul.add(lbHora);
        painelSul.add(tfHora);
        this.add(painelNorte,BorderLayout.NORTH);
        this.add(painelCentral,BorderLayout.CENTER);
        this.add(painelSul,BorderLayout.SOUTH);
       
    }
    
    
    
    public void setDialog(JDialog janela){
        this.janela = janela;
    }
    
    public void mostraMensagem(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }  
}
