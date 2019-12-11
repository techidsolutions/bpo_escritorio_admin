/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.ComponenteFormulario;
import modelo.ModelCombo;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.internationalization.java.Resources;
import util.Utiles;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaTitularExcel extends javax.swing.JDialog {
    /**
     * 1: Adicionar
     * 2:Modificar
     */
    private final Integer tipoLLamada;
    private final FormaNotaSimpleAExcel fp;
    private final int indiceElementoModificar;
    /**
     * Creates new form FormaTitular
     */
    
    /**
     * 
     * @param TipoCombo 
     */
    private void llenarComboTipoInmueble(String TipoCombo){
        Vector model = new Vector();
        switch(TipoCombo){
            case "Regimen":     model.addElement( new ModelCombo("Privativo", "Privativo" ) );
                                model.addElement( new ModelCombo("Ganancial", "Ganancial" ) );
                                model.addElement( new ModelCombo("Participación en gananciales", "Participación en gananciales" ) );
                                eRegimen.setModel(new DefaultComboBoxModel(model));
                                break;
            case "Tipo de participacion":
                                model.addElement( new ModelCombo("Pleno dominio", "Pleno dominio" ) );
                                model.addElement( new ModelCombo("Uso o usufructo", "Uso o usufructo" ) );
                                model.addElement( new ModelCombo("Propiedad", "Propiedad" ) );
                                eTipoParticipacion.setModel(new DefaultComboBoxModel(model));
                                break;                    
        }
    }
    
    /**
     * 
     */
    private void llenarFormulario(){
        ArrayList<ComponenteFormulario> listaComponentesXML = fp.getListaTitulares().get(indiceElementoModificar);
        eNombreRazon.setText(listaComponentesXML.get(2).getValor());
        ePrimerApellido.setText(listaComponentesXML.get(0).getValor());
        eSegundoApellido.setText(listaComponentesXML.get(1).getValor());
        eDocumento.setText(listaComponentesXML.get(3).getValor());
        switch(listaComponentesXML.get(4).getValor()){
            case "Privativo":eRegimen.setSelectedIndex(0);
                    break;
            case "Ganancial":eRegimen.setSelectedIndex(1);
                    break;
            case "Participación en gananciales":eRegimen.setSelectedIndex(2);
                    break;        
        }
        eTituloPropiedad.setText(listaComponentesXML.get(7).getValor());
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha;
            if (!listaComponentesXML.get(10).getValor().equals("")){
                fecha = formato.parse(listaComponentesXML.get(10).getValor());
                eFechaEscritura.setDate(fecha);
            }
            if (!listaComponentesXML.get(12).getValor().equals("")){
                fecha = formato.parse(listaComponentesXML.get(12).getValor());
                eFechaInscripcionTitular.setDate(fecha);
            }
        } catch (ParseException ex) {
            Logger.getLogger(FormaTitularExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        eNumeroProtocolo.setText(listaComponentesXML.get(9).getValor());
        eNotario.setText(listaComponentesXML.get(8).getValor());
        eNumeroInscripcion.setText(listaComponentesXML.get(11).getValor());
        switch(listaComponentesXML.get(6).getValor()){
            case "Pleno dominio":eTipoParticipacion.setSelectedIndex(0);
                    break;
            case "Uso o usufructo":eTipoParticipacion.setSelectedIndex(1);
                    break;
            case "Propiedad":eTipoParticipacion.setSelectedIndex(2);
                    break;        
        }
        ePorcientoParticipacion.setText(listaComponentesXML.get(5).getValor());
    }
    
    /**
     * 
     * @param parent
     * @param modal
     * @param tipoLLamada
     * @param indiceElementoModificar
     * @param fp 
     */
    public FormaTitularExcel(java.awt.Frame parent, boolean modal, Integer tipoLLamada, Integer indiceElementoModificar, FormaNotaSimpleAExcel fp) {
        super(parent, modal);
        initComponents();
        this.tipoLLamada = tipoLLamada;
        this.fp = fp;
        this.indiceElementoModificar = indiceElementoModificar;
        
        eFechaEscritura.setFormat(Resources.DMY);
        eFechaEscritura.setName("TLO_FECHA_ESCRITURA.");
        eFechaEscritura.setNextFocusableComponent(eNumeroInscripcion);
        eFechaInscripcionTitular.setFormat(Resources.DMY);
        eFechaInscripcionTitular.setName("TLO_FECHA_INSCRIPCION.");
        eFechaInscripcionTitular.setNextFocusableComponent(null);
        
        llenarComboTipoInmueble("Regimen");
        llenarComboTipoInmueble("Tipo de participacion");
        
        if (tipoLLamada == 2)
            llenarFormulario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        ePrimerApellido = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        eSegundoApellido = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        eNombreRazon = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        eDocumento = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        eRegimen = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        ePorcientoParticipacion = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        eTipoParticipacion = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        eTituloPropiedad = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        eNotario = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        eNumeroProtocolo = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        eFechaEscritura = new org.openswing.swing.client.DateControl();
        jLabel43 = new javax.swing.JLabel();
        eNumeroInscripcion = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        eFechaInscripcionTitular = new org.openswing.swing.client.DateControl();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del titular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jButton1.setText("Añadir");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        jLabel35.setText("Primer apellido:");

        ePrimerApellido.setName("TIT_APELLIDO1."); // NOI18N
        ePrimerApellido.setNextFocusableComponent(eSegundoApellido);

        jLabel36.setText("Segundo apellido:");

        eSegundoApellido.setName("TIT_APELLIDO2."); // NOI18N
        eSegundoApellido.setNextFocusableComponent(eNombreRazon);

        jLabel34.setText("Nomb/Razón Social:");

        eNombreRazon.setName("TIT_NOMBRE."); // NOI18N
        eNombreRazon.setNextFocusableComponent(eDocumento);

        jLabel37.setText("NIF/CIF titular:");

        eDocumento.setName("TIT_DOC_IDENTIDAD."); // NOI18N
        eDocumento.setNextFocusableComponent(eRegimen);

        jLabel38.setText("Régimen:");

        eRegimen.setName("TIT_REGIMEN."); // NOI18N
        eRegimen.setNextFocusableComponent(ePorcientoParticipacion);

        jLabel46.setText("% de Partic. titular:");

        ePorcientoParticipacion.setName("TLO_PORC_PARTICIPACION."); // NOI18N
        ePorcientoParticipacion.setNextFocusableComponent(eTipoParticipacion);
        ePorcientoParticipacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ePorcientoParticipacionKeyTyped(evt);
            }
        });

        jLabel45.setText("Tipo de participación:");

        eTipoParticipacion.setName("TLO_TIPO_PARTICIPACION."); // NOI18N
        eTipoParticipacion.setNextFocusableComponent(eTituloPropiedad);

        jLabel39.setText("Título propiedad:");

        eTituloPropiedad.setName("TLO_PROPIEDAD."); // NOI18N
        eTituloPropiedad.setNextFocusableComponent(eNotario);

        jLabel42.setText("Notario:");

        eNotario.setName("TLO_NOTARIA."); // NOI18N
        eNotario.setNextFocusableComponent(eNumeroProtocolo);

        jLabel41.setText("Número protocolo:");

        eNumeroProtocolo.setName("TLO_PROTOCOLO."); // NOI18N
        eNumeroProtocolo.setNextFocusableComponent(eFechaEscritura);

        jLabel40.setText("Fecha escritura:");

        jLabel43.setText("Número inscripción:");

        eNumeroInscripcion.setName("TLO_NUM_INSCRIPCION."); // NOI18N
        eNumeroInscripcion.setNextFocusableComponent(eFechaInscripcionTitular);

        jLabel44.setText("Fecha inscripción:");

        jButton3.setText("Finalizar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ePorcientoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel34)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(eNombreRazon))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel35)
                                                .addComponent(jLabel36))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(eSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(ePrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel38)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eTipoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(eFechaInscripcionTitular, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(eNumeroInscripcion)
                                        .addComponent(eFechaEscritura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel39)
                                        .addComponent(jLabel42))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(eNotario)
                                        .addComponent(eTituloPropiedad, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                                .addComponent(jLabel41))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(eNumeroProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(78, 78, 78)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ePrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNombreRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(jLabel41))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(eDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ePorcientoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eTituloPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNotario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eNumeroProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel40)
                            .addComponent(eFechaEscritura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNumeroInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaInscripcionTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eTipoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
            JComponent compTemp = (JComponent)ePrimerApellido;
            String atributo;
            ArrayList<ComponenteFormulario> listaComponentesXML = new ArrayList<>();
            while (compTemp != null){
                atributo = compTemp.getName();
                if (atributo.endsWith("."))
                    atributo = atributo.substring(0, atributo.length() - 1);
                if (compTemp instanceof JTextField){
                    listaComponentesXML.add(new ComponenteFormulario(atributo, ((JTextField)compTemp).getText()));
                } else if (compTemp instanceof JComboBox) {
                        listaComponentesXML.add(new ComponenteFormulario(atributo, ((ModelCombo)((JComboBox)compTemp).getSelectedItem()).getClave()));
                } else if (compTemp instanceof JFormattedTextField) {
                         listaComponentesXML.add(new ComponenteFormulario(atributo, ((JFormattedTextField)compTemp).getText()));
                } else if (compTemp instanceof DateControl) {
                    if (((DateControl)compTemp).getDate() != null)
                        listaComponentesXML.add(new ComponenteFormulario(atributo,  Utiles.convertirFechaYYYYMMDD(((DateControl)compTemp).getDate())));
                    else listaComponentesXML.add(new ComponenteFormulario(atributo,  ""));
                }else if (compTemp instanceof JCheckBox){
                    listaComponentesXML.add(new ComponenteFormulario(atributo, ((JCheckBox)compTemp).isSelected()?"True":"False"));
                } 
                compTemp = (JComponent)compTemp.getNextFocusableComponent();
            }
            if (tipoLLamada == 1)
                fp.getListaTitulares().add(listaComponentesXML);
            else fp.getListaTitulares().set(indiceElementoModificar, listaComponentesXML);
            JOptionPane.showMessageDialog(null, Utiles.msgOperacionRealizada);
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        this.dispose();
    }//GEN-LAST:event_jButton2MousePressed

    private void ePorcientoParticipacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ePorcientoParticipacionKeyTyped
        Utiles.validarNumeroReal(evt, ePorcientoParticipacion, 3, 2);
    }//GEN-LAST:event_ePorcientoParticipacionKeyTyped

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        dispose();
    }//GEN-LAST:event_jButton3MousePressed

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
            java.util.logging.Logger.getLogger(FormaTitularExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaTitularExcel dialog = new FormaTitularExcel(new javax.swing.JFrame(), true, null, null, null);
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
    private javax.swing.JTextField eDocumento;
    private org.openswing.swing.client.DateControl eFechaEscritura;
    private org.openswing.swing.client.DateControl eFechaInscripcionTitular;
    private javax.swing.JTextField eNombreRazon;
    private javax.swing.JTextField eNotario;
    private javax.swing.JTextField eNumeroInscripcion;
    private javax.swing.JTextField eNumeroProtocolo;
    private javax.swing.JTextField ePorcientoParticipacion;
    private javax.swing.JTextField ePrimerApellido;
    private javax.swing.JComboBox<String> eRegimen;
    private javax.swing.JTextField eSegundoApellido;
    private javax.swing.JComboBox<String> eTipoParticipacion;
    private javax.swing.JTextField eTituloPropiedad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
