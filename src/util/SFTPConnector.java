/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import java.util.Vector;
/**
 *
 * @author naylencuaje
 */
public class SFTPConnector {
 /**
     * Sesion SFTP establecida.
     */
    String DIRtasacion = "/home/BPO/EnviadosWS/Tasacion";
    String DIRIRPF = "/home/BPO/EnviadosWS/IRPF";
    String DIRNomina = "/home/BPO/EnviadosWS/Nomina";
    String DIRNotaSimple = "/home/BPO/EnviadosWS/Nota Simple";
    String DIRNotaSimpleOCR = "/home/BPO/EnviadosWS/NotaSimpleOCR";
    String DIRRecibo = "/home/BPO/EnviadosWS/Recibo";
    String DIRVidaLaboral = "/home/BPO/EnviadosWS/Vida Laboral";
    private Session session;
    Channel     channel     = null;
    ChannelSftp channelSftp = null;
    /**
     * Establece una conexion SFTP.
     *
     * @param username Nombre de usuario.
     * @param password Contrasena.
     * @param host     Host a conectar.
     * @param port     Puerto del Host.
     *
     * @throws JSchException          Cualquier error al establecer
     *                                conexión SFTP.
     * @throws IllegalAccessException Indica que ya existe una conexion
     *                                SFTP establecida.
     */
    public DocumentosDescargados connectsftp(String username, String password, String host, int port)
        throws JSchException, IllegalAccessException, SftpException {
        if (this.session == null || !this.session.isConnected()) {
            JSch jsch = new JSch();
 
            this.session = jsch.getSession(username, host, port);
            this.session.setPassword(password);
 
            // Parametro para no validar key de conexion.
            this.session.setConfig("StrictHostKeyChecking", "no");
 
            this.session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp)channel;

            channelSftp.cd(DIRtasacion);
            Vector filelisttasacion = channelSftp.ls(DIRtasacion);
            int listatasacion = filelisttasacion.size()-2;
            
            channelSftp.cd(DIRIRPF);
            Vector filelistirpf = channelSftp.ls(DIRIRPF);
            int listairpf = filelistirpf.size()-2;
            
            channelSftp.cd(DIRNomina);
            Vector filelistnomina = channelSftp.ls(DIRNomina);
            int listanomina = filelistnomina.size()-2;
            
            channelSftp.cd(DIRNotaSimple);            
            Vector filelistnotasimple = channelSftp.ls(DIRNotaSimple);
            int listanotasimple = filelistnotasimple.size()-2;

            channelSftp.cd(DIRNotaSimpleOCR);
            Vector filelistnotasimpleocr = channelSftp.ls(DIRNotaSimpleOCR);
            int listanotasimpleocr = filelistnotasimpleocr.size()-2;
            
            channelSftp.cd(DIRRecibo);
            Vector filelistrecibo = channelSftp.ls(DIRRecibo);
            int listarecibo = filelistrecibo.size()-2;

            channelSftp.cd(DIRVidaLaboral);
            Vector filelistvidalaboral = channelSftp.ls(DIRVidaLaboral);
            int listavidalaboral = filelistvidalaboral.size()-2;

//            for(int i=0; i<filelist.size();i++){
//                LsEntry entry = (LsEntry) filelist.get(i);
//                System.out.println(entry.getFilename());
//            }            
//            int carpetas = filelist.size();
//            System.out.println(carpetas);
            DocumentosDescargados documentosDescargados = new DocumentosDescargados(listatasacion + listairpf + listanomina + listanotasimple + listanotasimpleocr + listarecibo + listavidalaboral,
                listanotasimple, listairpf, listanomina, listarecibo, listatasacion, listavidalaboral, listanotasimpleocr);
            return documentosDescargados;
            }else {
            throw new IllegalAccessException("Sesion SFTP ya iniciada.");
        }
    }
    
    
    /**
     * Cierra la sesion SFTP.
     */
    public final void disconnect() {
        this.session.disconnect();
    }
 }
