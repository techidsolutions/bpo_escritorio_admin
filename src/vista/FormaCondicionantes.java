/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.ListSelectionModel;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaCondicionantes extends javax.swing.JDialog {

    private FormaProcesarTasacion formaTasacion;

    /**
     * Creates new form FormaAdvertencia
     *
     * @param parent
     * @param modal
     */
    public FormaCondicionantes(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.formaTasacion = (FormaProcesarTasacion) parent;
        jXList2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jXFindBar1 = new org.jdesktop.swingx.JXFindBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        jXList2 = new org.jdesktop.swingx.JXList();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Condicionantes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton1.setText("Añadir");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton2.setText("Limpiar selección");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jXFindBar1Layout = new javax.swing.GroupLayout(jXFindBar1);
        jXFindBar1.setLayout(jXFindBar1Layout);
        jXFindBar1Layout.setHorizontalGroup(
            jXFindBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 421, Short.MAX_VALUE)
        );
        jXFindBar1Layout.setVerticalGroup(
            jXFindBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jXList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "InscripcionDelElementoValoradoComoFincaRegistralIndependiente", "AclaracionDiferenciasSignificativasEntreLaSuperficieRegistralCatastralyLaComprobada", "AportacionDeNotaSimpleoCertificacionEmitidaFueraDeLos3MesesAnterioresaLaFechaDeTasacion", "AportacionDeProyectoVisado", "AportacionDeLicenciaDeObras", "JustificacionDeQueElElementoValoradoEstaLibreDeInquilinosuOcupantes", "AportacionDeContratoDeArrendamientoyoUltimoRecibo", "AportacionDeUltimosRecibosDeRentaAbonados", "AportacionDeLaCedulaDeCalificacionProvisionaloDefinitiva", "AportacionDeDocumentacionUrbanisticayoDatosDeAprovechamientoUrbanistico", "AportacionDeCertificacionRegistral", "AportacionDePropietarioDeCertificadoDatosDeAlquiler", "DerechoDeRiego", "AportacionCedulaUrbanisticaCertificadoUrbanisticoMunicipaloDocumentacionparadeterminarlaClasedeSueloyAprovechamientoUrbanisticodelaPropiedad", "EstadodeConservacionAparente", "ServidumbresVisibles", "AportacionDocumentoRegimendeProteccionDelPatrimonioArquitectonico", "NoExistenCondicionantesParaElCasoConcretoDeEsteInforme", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaDocumentacionCatastral", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaReferenciaCatastralDeAlgunInmueble", "NoSeHaPodidoComprobarLaExistenciaDeProcedimientoDeDeclaracionDeIncumplimientoPlazosDeLosDeberesDeUrbanizacionoDeEdificacionDelSuelo", "OtrosCondicionantes", "C111_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimpleEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion", "C112_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion", "C121_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimple", "C122_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEnLaQueLaDescripcionCatastralSeAdecueaLaRealidadFisicaActualySeaAcordeConLaNormativaUrbanistica", "C13_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiadeNotaSimpleEnLaQueElElementoValoradoFigure", "C14_ElelementoValoradoEstaFormadoPorDosoMasFincasRegistralesQueFormanUnaUnicaUnidadFuncionalNoSiendoPosibleDeterminarLaConfiguracionoLinderosDeCadaUnaDeEllasPorLoQueElValorInformadoSeCorrespondeConLaAgrupacion", "C15_LaFincaValoradaDaAccesoaOtraFincaRegistralQueSoloTieneAccesoAtravesDeEllayNoConstaInscritaServidumbreDePasoElValorDeTasacionSoloSeraValidoSiSeInscribeLasServidumbreDePasoDeacuerdoaLosParametrosManejados", "C31_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeproyectovisado", "C32_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeLicenciaDeObrasConSusClausulasyCartaDePago", "C41_AportacionAlTasadorParaLaVerificacionDeDocumentacionQueJustifiqueQueElElementoValoradoEstaLibreDeArrendamientoInquilinosuOcupantesDiferentesDelTitularRegistralAlNoHaberSidoAportadasLasCondicionesDeOcupacion", "C42_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelContratoDeArrendamiento", "C43_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelUltimoReciboDeRentaAbonado", "C5_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDecedulaurbanisticacertificadomunicipaluotradocumentacionquepermitadeterminaryconfirmarintegramentelaclasedesueloelaprovechamientoysuscondicion", "C7_CondicionantesOtros" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jXList2);

        jButton3.setText("Cerrar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        if (jXList2.getSelectedValuesList().size() > 0) {
            for (Object selected : jXList2.getSelectedValuesList()) {

                if (!formaTasacion.getListaCondicionantesSeleccionada().contains((String) selected)) {
                    formaTasacion.getListaCondicionantesSeleccionada().add((String) selected);
                }
            }
        } else {
            if (formaTasacion.getListaCondicionantesSeleccionada().size() == 0) {
                formaTasacion.getListaCondicionantesSeleccionada().add("NoExistenCondicionantesParaElCasoConcretoDeEsteInforme");
            }
        }
        jXList2.clearSelection();
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed

        jXList2.clearSelection();
    }//GEN-LAST:event_jButton2MousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jXFindBar1.setSearchable(jXList2.getSearchable());
    }//GEN-LAST:event_formWindowOpened

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        if (formaTasacion.getListaCondicionantesSeleccionada().size() == 0) {
            formaTasacion.getListaCondicionantesSeleccionada().add("NoExistenCondicionantesParaElCasoConcretoDeEsteInforme");
        }
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormaCondicionantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormaCondicionantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormaCondicionantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormaCondicionantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormaCondicionantes dialog = new FormaCondicionantes(new javax.swing.JDialog(), true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXFindBar jXFindBar1;
    private org.jdesktop.swingx.JXList jXList2;
    // End of variables declaration//GEN-END:variables
}
