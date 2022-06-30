/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.jcraft.jsch.ChannelSftp;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import static util.Utiles.direcion;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class TimerTaskSchedule {

    private static final String SERVIDOR = "31.47.76.216";
    private static final int PUERTO = 22;
    private static final String USUARIO = "bpo";
    private static final String PASSWORD = "Tech1D01";
    ChannelSftp channelSftp = null;

    /**
     *
     */
    public void setTimerTaskSchedule(/*final ChannelSftp channelSftp, final ChannelSftp channelSftpTech*/) {

        final Timer timer;
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                DocumentosDescargados documentosenviadosws = null;

                try {
                    SFTPConnector sshConnector = new SFTPConnector();
                    File file = new File(direcion.concat("\\conf\\configFtp.properties"));
                    FileInputStream fileInputStream = new FileInputStream(file);
                    Properties mainProperties = new Properties();
                    mainProperties.load(fileInputStream);
                    //buscando en el fichero de conf la llave "ipFtp"
                    String ipFtp = mainProperties.getProperty("ipFtp");
                    //buscando en el fichero de conf la llave "userFtp"
                    String usuarioFtp = mainProperties.getProperty("userFtp");
                    //buscando en el fichero de conf la llave "passWdFtp"
                    String claveFtp = mainProperties.getProperty("passWdFtp");
                    //buscando en el fichero de conf la llave "portFtp"
                    String portFtp = mainProperties.getProperty("portFtp");
                    //Cerrando el fichero
                    fileInputStream.close();
                    documentosenviadosws= sshConnector.connectsftp(USUARIO, PASSWORD, SERVIDOR, PUERTO);                    
                    //documentosenviadosws = sshConnector.connectsftp(usuarioFtp, claveFtp, ipFtp, new Integer(portFtp));
                    JOptionPane.showMessageDialog(null, "Documentos disponibles\n"
                            + "Nota Simple:" + documentosenviadosws.getDescargdaNotas() + "\n"
                            + "IRPF:" + documentosenviadosws.getDescargdaIRPF() + "\n"
                            + "Nómina:" + documentosenviadosws.getDescargdaNomina() + "\n"
                            + "Vida laboral:" + documentosenviadosws.getDescargdaVidaLaboral() + "\n"
                            + "Recibo:" + documentosenviadosws.getDescargdaRecibo() + "\n"
                            + "Tasación:" + documentosenviadosws.getDescargdaTasacion() + "\n"
                            + "Nota Simple OCR:" + documentosenviadosws.getDescargdaNotasNodulos());
                    sshConnector.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }

            }
        };
        //Comienza dentro de 0ms y luego lanzamos la tarea cada 1000ms   60000
        //timer.schedule(task, 1000, 3600000);
        //timer.schedule(task, 1000, 300000); cada 5 minutos
        timer.schedule(task, 1000, 300000);

    }
}
