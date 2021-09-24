/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.visao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

/**
 *
 * @author aluno
 */
public class CadastroContatos extends JPanel{
    public JButton btCadastro, btCancela;
    public JLabel lbNome, lbEmail, lbTelefone, lbAno;
    public JTextField tfNome, tfEmail, tfTelefone, tfAno;
    public JDialog janela;
    private JPanel painelCentral, painelNorte, painelSul;
    
    public CadastroContatos(){
        this.setSize(740, 400);
        this.setLayout(new BorderLayout(2,2));
        
        lbNome = new JLabel("Nome:");
        tfNome = new JTextField("", 20);
        lbEmail = new JLabel("E-mail:");
        tfEmail = new JTextField("", 20);
        lbTelefone = new JLabel("Telefone:");
        tfTelefone = new JTextField("", 20);
        lbAno = new JLabel("Ano de nascimento:");
        tfAno = new JTextField("", 20);
        btCadastro = new JButton("Cadastrar");
        btCancela = new JButton("Cancelar");
        painelCentral = new JPanel();
        painelSul = new JPanel();
        painelNorte = new JPanel();
        painelNorte.setLayout(new GridLayout(1,2));
        painelSul.setLayout(new GridLayout(4,2));
        painelNorte.add(btCadastro);
        painelNorte.add(btCancela);
        painelSul.add(lbNome);
        painelSul.add(tfNome);
        painelSul.add(lbEmail);
        painelSul.add(tfEmail);
        painelSul.add(lbTelefone);
        painelSul.add(tfTelefone);
        painelSul.add(lbAno);
        painelSul.add(tfAno);       
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
