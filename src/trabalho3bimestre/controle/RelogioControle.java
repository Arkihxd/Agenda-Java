package trabalho3bimestre.controle;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabalho3bimestre.visao.Principal;


public class RelogioControle implements Runnable{
    PrincipalControle pControl;
    Principal p;
    
    RelogioControle(PrincipalControle pControl){
        this.pControl = pControl;
        p = new Principal();
    }

    @Override
    public void run() {
            try {
                for(;;){
                    Long agora = System.currentTimeMillis();
                    SimpleDateFormat zap = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
                    pControl.atualizaRelogio(zap.format(agora));
                    
                    Thread.sleep(1000);
                }
            }catch(InterruptedException ex){
                Logger.getLogger(RelogioControle.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
