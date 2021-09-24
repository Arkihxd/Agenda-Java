/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3bimestre.visao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
/**
 *
 * @author haperlin
 */
public class VerContato extends JPanel{
    public JTextField tfFiltro;
    public JButton btFechar, btId, btNome, btDesativar;
    public JDialog janela;
    public JButton btTudo;
    private JPanel painelNorte, painelSubNorte;
    
    public JTable tbDetalhes;
    
    public VerContato(){
        this.setSize(640,480);
        this.setLayout(new BorderLayout());
        
        painelNorte = new JPanel();
        painelSubNorte = new JPanel();
        painelNorte.setLayout(new GridLayout(2,1));
        painelSubNorte.setLayout(new GridLayout(1,5));
        
        btFechar = new JButton("Fechar");
        btId = new JButton("Id");
        btTudo = new JButton("Tudo");
        btNome = new JButton("Nome");
        btDesativar = new JButton("Desativar");
        
        painelSubNorte.add(btFechar);
        painelSubNorte.add(btId);
        painelSubNorte.add(btNome);
        painelSubNorte.add(btTudo);
        painelSubNorte.add(btDesativar);
        painelNorte.add(painelSubNorte);
        tfFiltro = new JTextField();
        painelNorte.add(tfFiltro);
        this.add(painelNorte,BorderLayout.NORTH);
        
        tbDetalhes = new JTable();
        this.add(new JScrollPane(tbDetalhes),BorderLayout.CENTER);
        
        
    }
    
    public void setDialog(JDialog janela){
        this.janela = janela;
    }
    
    public void mostraMensagem(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }  
    
    
    
}
