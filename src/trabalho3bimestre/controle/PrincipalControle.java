package trabalho3bimestre.controle;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import trabalho3bimestre.modelo.Compromisso;
import trabalho3bimestre.modelo.Tarefa;
import trabalho3bimestre.visao.Principal;

public class PrincipalControle implements ActionListener {

    private Principal janela;
    private CadastroContatosControle cdContato;
    private CadastroCompromissosControle cdCompromisso;
    private CadastroTarefaControle cdTarefa;
    private VerCompromissoControle vrCompromisso;
    private VerTarefaControle vrTarefa;
    private VerContatoControle vrContato;
    private LembreteControle taskTime = new LembreteControle(this);
    private Thread threadTime = new Thread(taskTime);
    private RelogioControle rControle = new RelogioControle(this);
    private Thread relogio = new Thread(rControle);
    
    public PrincipalControle() throws SQLException{
        janela = new Principal();
        vrCompromisso = new VerCompromissoControle();
        vrTarefa = new VerTarefaControle();
        vrContato = new VerContatoControle();
        cdContato = new CadastroContatosControle();
        cdCompromisso = new CadastroCompromissosControle();
        cdTarefa = new CadastroTarefaControle();
        janela.cadContato.addActionListener(this);
        janela.cadCompromisso.addActionListener(this);
        janela.cadTarefa.addActionListener(this);
        janela.verContato.addActionListener(this);
        janela.verTarefa.addActionListener(this);
        janela.verCompromisso.addActionListener(this);
        relogio.start();
        threadTime.start();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==janela.cadContato){
            cdContato.mostraJanela();
        }
        else if(e.getSource()==janela.cadCompromisso){
            try {
                cdCompromisso.mostraJanela();
            } catch (SQLException ex) {
                Logger.getLogger(PrincipalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource()==janela.cadTarefa){
           cdTarefa.mostraJanela();
        }
        else if(e.getSource()==janela.verContato){
            try {
                vrContato.abrir();
            } catch (SQLException ex) {
                Logger.getLogger(PrincipalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource()==janela.verTarefa){
            try {
                vrTarefa.abrir();
            } catch (SQLException ex) {
                Logger.getLogger(PrincipalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(e.getSource()==janela.verCompromisso){
            try {
                vrCompromisso.abrir();
            } catch (SQLException ex) {
                Logger.getLogger(PrincipalControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void atualizaCompromisso(ArrayList<Compromisso> p) throws MalformedURLException{
        if(p.size()>0){
            janela.verCompromisso.setBackground(Color.red);
            janela.verCompromisso.setText("Ver Compromissos("+p.size()+" agora)");
            String str = "Você tem compromissos agora! São eles:\n\n";
            for(Compromisso c:p){
                str+="- "+c.getNome()+"\n";
            }
            tocarAudio();
            janela.mostraMensagem(str+"\nnas próximos 2 horas"); 
        }
        
        else{
            janela.verCompromisso.setBackground(null);
            janela.verCompromisso.setText("Ver Compromissos");
        }
        
    }
    
    public void atualizaTarefa(ArrayList<Tarefa> p) throws MalformedURLException{
        if(p.size()>0){
            janela.verTarefa.setBackground(Color.red);
            janela.verTarefa.setText("Ver Tarefas("+p.size()+" agora)");
            String str = "Você tem tarefas agora! São elas:\n\n";
            for(Tarefa c:p){
                str+="- "+c.getNome()+"\n";
            }
            tocarAudio();
            janela.mostraMensagem(str+"\nnas próximas 2 horas");
        }
        
        else{
            janela.verTarefa.setBackground(null);
            janela.verTarefa.setText("Ver Tarefas");
            
        }
        
    }
    
    public void atualizaRelogio(String r){
        janela.relogio.setText("                                                                                                                                      "+r);
    }
    
    public void mostraJanela(){
        janela.setVisible(true);
    }
    public void tocarAudio() throws MalformedURLException{
        File file = new File("som.wav");
        AudioClip clip = Applet.newAudioClip(file.toURI().toURL());
        clip.play();
    }
    
}
