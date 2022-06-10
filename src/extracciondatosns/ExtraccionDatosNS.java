/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extracciondatosns;

import java.awt.GraphicsEnvironment;
import util.TimerTaskSchedule;
import util.Utiles;
import vista.FormaInicio;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class ExtraccionDatosNS {

    private static GraphicsEnvironment CentroPantalla(){
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return gEnv;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            System.out.println(System.getProperty("user.dir"));
            FormaInicio formaInicial = new FormaInicio(null, true);
            formaInicial.setLocation(CentroPantalla().getCenterPoint().x - formaInicial.getWidth()/2,
                    CentroPantalla().getCenterPoint().y - formaInicial.getHeight()/2);
            formaInicial.setVisible(true);
            
            TimerTaskSchedule  timerTaskSchedule = new TimerTaskSchedule();
            timerTaskSchedule.setTimerTaskSchedule();
           
    }
    
}
