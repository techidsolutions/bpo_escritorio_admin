/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Usuario;
import util.EncriptarClave;
import util.Utiles;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaLogin extends javax.swing.JDialog {

    
    /**
     * Creates new form FormaLogin
     * @param parent
     * @param modal
     */
    public FormaLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * 
     * @param nombre
     * @param clave
     * @return 
     */
    public Usuario loguearUsuario(String nombre, String clave) {
        File archivo = new File(Utiles.rutaUsuarios);
        Usuario usuario = null;
        try {
            Scanner scanner = new Scanner(archivo);
            String nombreTemp;
            String claveTemp;
            while (scanner.hasNextLine()){
                nombreTemp = scanner.nextLine();
                try {
                    nombreTemp = Utiles.desencriptar(nombreTemp);
                } catch (Exception ex) {
                    Logger.getLogger(FormaLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
                claveTemp = scanner.nextLine();
                if ((nombreTemp.equals(nombre)) && (claveTemp.equals(clave))){
                    usuario = new Usuario(nombre, clave, Utiles.desencriptar(scanner.nextLine()));
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormaLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FormaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    /**
     * 
     * @return 
     */
    private Boolean existeAlmenoUnaCuenta(){
        File archivo = new File(Utiles.rutaUsuarios);
        try {
            Scanner scanner = new Scanner(archivo);
            while (scanner.hasNextLine()){
                return true;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jPasswordFieldClave = new javax.swing.JPasswordField();
        jButtonCrearCuenta = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Iniciar sesión");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Usuario:");

        jLabel2.setText("Clave:");

        jTextFieldUsuario.setName(""); // NOI18N
        jTextFieldUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldUsuarioKeyPressed(evt);
            }
        });

        jPasswordFieldClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordFieldClaveKeyPressed(evt);
            }
        });

        jButtonCrearCuenta.setText("Crear");
        jButtonCrearCuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonCrearCuentaMousePressed(evt);
            }
        });

        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAceptarMousePressed(evt);
            }
        });

        jButton1.setText("Cancelar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCrearCuenta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldUsuario)
                    .addComponent(jPasswordFieldClave, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordFieldClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCrearCuenta)
                    .addComponent(jButtonAceptar)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAceptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAceptarMousePressed
        String claveEncriptada = "";
        try {
            claveEncriptada = EncriptarClave.encriptarMD5(jPasswordFieldClave.getText().trim());
        }
        catch(NoSuchAlgorithmException e){

        }
        Usuario usuario = loguearUsuario(jTextFieldUsuario.getText(), claveEncriptada);
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, Utiles.msgNombreClaveIncorrectos);
            jTextFieldUsuario.requestFocus();
        }else {
            FormaPrincipal.setUsuario(usuario);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonAceptarMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jTextFieldUsuario.requestFocus();        // TODO add your handling code here:
        if (existeAlmenoUnaCuenta())
            jButtonCrearCuenta.setVisible(false);
            
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      
    }//GEN-LAST:event_formWindowClosed

    private void jButtonCrearCuentaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCrearCuentaMousePressed
        FormaCrearUsuario formaCrearUsuario = new FormaCrearUsuario(null, true);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaCrearUsuario.setLocation(GEnv.getCenterPoint().x-formaCrearUsuario.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaCrearUsuario.getHeight()/2 + 25);

        formaCrearUsuario.setResizable(false);
        formaCrearUsuario.setVisible(true);
    }//GEN-LAST:event_jButtonCrearCuentaMousePressed

    private void jTextFieldUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioKeyPressed
        char caracter = evt.getKeyChar();
        if (caracter == java.awt.event.KeyEvent.VK_ENTER) {
            jButtonAceptarMousePressed(null);
        }
    }//GEN-LAST:event_jTextFieldUsuarioKeyPressed

    private void jPasswordFieldClaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldClaveKeyPressed
        char caracter = evt.getKeyChar();
        if (caracter == java.awt.event.KeyEvent.VK_ENTER) {
            jButtonAceptarMousePressed(null);
        }
    }//GEN-LAST:event_jPasswordFieldClaveKeyPressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        Window Padre;
        Padre = (Window) this.getParent();
        if (Padre.getOwnedWindows().length > 0 ){
            for(Window Elem :Padre.getOwnedWindows()){
                Elem.dispose();
            }
        }
        Padre.dispose();    
    }//GEN-LAST:event_jButton1MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaLogin dialog = new FormaLogin(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCrearCuenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldClave;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
