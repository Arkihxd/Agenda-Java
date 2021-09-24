/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.visao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author aluno
 */
public class Principal extends JFrame {
    private JMenuBar menu;
    public JLabel imagem, relogio;
    private JMenu menuCompromisso, menuContato, menuTarefa;
    public JMenuItem cadCompromisso, cadContato, cadTarefa;
    public JButton verContato, verTarefa, verCompromisso;
    private JPanel painelSul;
    
    public Principal(){
        setSize(740,430);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Agenda");
        this.setLayout(new BorderLayout(2,2));
        this.setLocationRelativeTo(null);   
        menu = new JMenuBar();
        this.setJMenuBar(menu);
        
        menuCompromisso = new JMenu("Compromisso");
        menu.add(menuCompromisso);
        cadCompromisso = new JMenuItem("Cadastrar Compromisso");
        menuCompromisso.add(cadCompromisso);
        
        menuTarefa = new JMenu("Tarefa");
        menu.add(menuTarefa);
        cadTarefa = new JMenuItem("Cadastrar Tarefa");
        menuTarefa.add(cadTarefa);
        
        menuContato = new JMenu("Contato");
        menu.add(menuContato);
        cadContato = new JMenuItem("Cadastrar Contato");
        menuContato.add(cadContato);
        
        imagem = new JLabel();
        Icon icone = new ImageIcon("fundo.jpg");
        this.imagem = new JLabel("",icone,SwingConstants.LEFT);
        add(imagem);
        
        relogio = new JLabel("Relogio");
        menu.add(relogio);
       
        
        verContato = new JButton("Ver Contatos");
        verTarefa = new JButton("Ver Tarefas");
        verCompromisso = new JButton("Ver Compromissos");
        painelSul = new JPanel();
        painelSul.setLayout(new GridLayout(1,3));
        painelSul.add(verContato);
        painelSul.add(verTarefa);
        painelSul.add(verCompromisso);
        this.add(painelSul,BorderLayout.SOUTH);

    }
    
    public void mostraMensagem(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }
   

}
