/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.ComponenteFormulario;
import modelo.ModelCombo;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.internationalization.java.Resources;
import util.LimitarNumeros;
import util.Utiles;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaCargasExcel extends javax.swing.JDialog {

    private Vector modelAmpliacion;
    private Vector modelModificacion;
    private Vector modelSubrogacion;
    private Vector modelArrendamiento;
    
    private FormaNotaSimpleAExcel fp;
    private int indiceCargaSeleccionada;
    
    /**
     * Creates new form FormaCargas
     * @param parent
     * @param modal
     * @param fp
     */
    public FormaCargasExcel(java.awt.Frame parent, boolean modal, FormaNotaSimpleAExcel fp) {
        super(parent, modal);
        initComponents();
        this.fp = fp;
        llenarComboTipoInmueble("Tipo carga");
        llenarComboTipoInmueble("Ampliación");
        llenarComboTipoInmueble("Modificación");
        llenarComboTipoInmueble("Subrogación");
        llenarComboTipoInmueble("Arrendamiento");
        RestrictedTextField restriccionPlazoDeCarga = new RestrictedTextField(ePlazoDeCarga);
        restriccionPlazoDeCarga.setLimit(6);
        ePlazoDeCarga.setDocument(new LimitarNumeros());
        eFechaVencimiento.setFormat(Resources.DMY);
        eFechaVencimiento.setName("FECHA_VENCIMIENTO.");
        eFechaVencimiento.setNextFocusableComponent(ePlazoDeCarga);
        eFechaEscritura.setFormat(Resources.DMY);
        eFechaEscritura.setName("FECHA_ESCRITURA.");
        eFechaEscritura.setNextFocusableComponent(eNotarioCesion);
        eFechaAnotacion.setFormat(Resources.DMY);
        eFechaAnotacion.setName("FECHA_ANOTACION.");
        eFechaAnotacion.setNextFocusableComponent(eCocneptoAsiento);
        eFechaMandamientoEmbargo.setFormat(Resources.DMY);
        eFechaMandamientoEmbargo.setName("FECHA_MANDAMIENTO.");
        eFechaMandamientoEmbargo.setNextFocusableComponent(eLetraAnotacion);
        eFechaAsiento.setFormat(Resources.DMY);
        eFechaAsiento.setName("FECHA_MANDAMIENTO.");
        eFechaAsiento.setNextFocusableComponent(eArrendamientoFinanciero);
    }
    
    /**
     * 
     * @param TipoCombo 
     */
    private void llenarComboTipoInmueble(String TipoCombo){
        switch(TipoCombo){
            case "Ampliación":  modelAmpliacion  =  new Vector();
                                modelAmpliacion.addElement( new ModelCombo("N", "N" ) );
                                modelAmpliacion.addElement( new ModelCombo("S", "S" ) );
                                eAmpliacion.setModel(new DefaultComboBoxModel(modelAmpliacion));
                                break;
            case "Modificación":modelModificacion  =  new Vector();
                                modelModificacion.addElement( new ModelCombo("N", "N" ) );
                                modelModificacion.addElement( new ModelCombo("S", "S" ) );
                                eModificacionNovacion.setModel(new DefaultComboBoxModel(modelModificacion));
                                break;
            case "Subrogación": modelSubrogacion  =  new Vector();
                                modelSubrogacion.addElement( new ModelCombo("N", "N" ) );
                                modelSubrogacion.addElement( new ModelCombo("S", "S" ) );
                                eSubrogacion.setModel(new DefaultComboBoxModel(modelSubrogacion));
                                break;
            case "Arrendamiento":modelArrendamiento  =  new Vector();
                                modelArrendamiento.addElement( new ModelCombo("N", "N" ) );
                                modelArrendamiento.addElement( new ModelCombo("S", "S" ) );
                                eArrendamientoFinanciero.setModel(new DefaultComboBoxModel(modelArrendamiento));
                                break;
        }
     }
     
    /**
     * 
     * @param modelo
     * @param combo
     * @param valor 
     */
    private void seleccionarElementoCombo(Vector modelo, JComboBox combo, String valor){
        ModelCombo modeloCombo;
        for(int i=0; i<modelo.size(); i++){
            modeloCombo = (ModelCombo)modelo.elementAt(i);
            if (modeloCombo.getClave().equals(valor)){
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
    
    /**
     * 
     */ 
    private void llenarFormulario(){
        seleccionarElementoCombo(modelAmpliacion, eAmpliacion, fp.getListaCargas().get(indiceCargaSeleccionada).get(15).getValor());
        seleccionarElementoCombo(modelModificacion, eModificacionNovacion, fp.getListaCargas().get(indiceCargaSeleccionada).get(17).getValor());
        seleccionarElementoCombo(modelSubrogacion, eSubrogacion, fp.getListaCargas().get(indiceCargaSeleccionada).get(18).getValor());
        seleccionarElementoCombo(modelArrendamiento, eArrendamientoFinanciero, fp.getListaCargas().get(indiceCargaSeleccionada).get(31).getValor());
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = null;
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(10).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(10).getValor());
                eFechaVencimiento.setDate(fecha);
            }else {
                eFechaVencimiento.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(12).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(12).getValor());
                eFechaEscritura.setDate(fecha);
            }else {
                eFechaEscritura.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(27).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(27).getValor());
                eFechaAnotacion.setDate(fecha);
            }else {
                eFechaAnotacion.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(25).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(25).getValor());
                eFechaMandamientoEmbargo.setDate(fecha);
            }else {
                eFechaMandamientoEmbargo.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(30).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(30).getValor());
                eFechaAsiento.setDate(fecha);
            }else {
                eFechaAsiento.setDate(fecha);
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(FormaCargasExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
        eTipoCarga.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(0).getValor());
        eRango.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(1).getValor());
        ePorcentajeResponsabilidadHipoteca.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(2).getValor());
        eBeneficiario.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(3).getValor());
        eImporteHipoteca.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(4).getValor());
        eInteresesHipoteca.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(5).getValor());
        eInteresesDemoraHipoteca.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(6).getValor());
        eCostasGastosHipoteca.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(7).getValor());
        eOtrosGastosHipoteca.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(8).getValor());
        eResponsabilidadHipoteca1.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(9).getValor());
        ePlazoDeCarga.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(11).getValor());
        eNotarioCesion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(13).getValor());
        eProtocoloCesion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(14).getValor());
        eImporteAmpliacion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(16).getValor());
        eInteresesCostasEmbargo.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(19).getValor());
        eInteresesDemoraEmbargo.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(20).getValor());
        eGastosEmbargo.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(21).getValor());
        eTipoProcedimientoEmbargo.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(22).getValor());
        eNumeroProcedimientoEmbargo1.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(23).getValor());
        eJusgadoAyuntamiento.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(24).getValor());
        eLetraAnotacion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(26).getValor());
        eCocneptoAsiento.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(28).getValor());
        eNumeroAsiento.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(29).getValor());
        eNumeroFilaSolicitud.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(32).getValor());
     }
    
    /**
     * 
     */
    private void limpiarComponentesFormulario(){
        eRango.setText("");
        ePorcentajeResponsabilidadHipoteca.setText("");
        eBeneficiario.setText("");
        eImporteHipoteca.setText("");
        eInteresesHipoteca.setText("");
        eInteresesDemoraHipoteca.setText("");
        eCostasGastosHipoteca.setText("");
        eOtrosGastosHipoteca.setText("");
        eResponsabilidadHipoteca1.setText("");
        ePlazoDeCarga.setText("");
        eNotarioCesion.setText("");
        eProtocoloCesion.setText("");
        eImporteAmpliacion.setText("");
        eInteresesCostasEmbargo.setText("");
        eInteresesDemoraEmbargo.setText("");
        eGastosEmbargo.setText("");
        eTipoProcedimientoEmbargo.setText("");
        eNumeroProcedimientoEmbargo1.setText("");
        eJusgadoAyuntamiento.setText("");
        eLetraAnotacion.setText("");
        eCocneptoAsiento.setText("");
        eNumeroAsiento.setText("");
        eNumeroFilaSolicitud.setText("");
        eTipoCarga.setText("");
        
        //Combo
        eAmpliacion.setSelectedIndex(0); 
        eModificacionNovacion.setSelectedIndex(0); 
        eSubrogacion.setSelectedIndex(0); 
        eArrendamientoFinanciero.setSelectedIndex(0); 

        //Fechas
        Date fecha = null;
        eFechaAnotacion.setDate(fecha);
        eFechaAsiento.setDate(fecha);
        eFechaEscritura.setDate(fecha);
        eFechaMandamientoEmbargo.setDate(fecha);
        eFechaVencimiento.setDate(fecha);
    }
    
    /**
     * 
     */
    private void quitarPuntoNombre(){
        JComponent compTemp = (JComponent)eTipoCarga;
        String atributo;
        while (compTemp != null){
            atributo = compTemp.getName();
            if (compTemp.getName().endsWith("."))
                compTemp.setName(compTemp.getName().substring(0, atributo.length() - 1));
            compTemp = (JComponent)compTemp.getNextFocusableComponent();
        }
    } 
    
    /**
     * 
     */
    private void deshabilitarComponentes(){
        JComponent compTemp = (JComponent)eTipoCarga;
        while (compTemp != null){
            if (!compTemp.getName().endsWith("."))
                compTemp.setEnabled(false);
            compTemp = (JComponent)compTemp.getNextFocusableComponent();
        }
    } 
    
    /**
     * 
     */
    private void habilitarComponentes(){
        JComponent compTemp = (JComponent)eTipoCarga;
        while (compTemp != null){
            compTemp.setEnabled(true);
            compTemp = (JComponent)compTemp.getNextFocusableComponent();
        }
    } 
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        eBeneficiario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ePlazoDeCarga = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        eFechaVencimiento = new org.openswing.swing.client.DateControl();
        eAdicionar = new javax.swing.JButton();
        eModificar = new javax.swing.JButton();
        eEliminar = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        eRango = new javax.swing.JTextField();
        eImporteHipoteca = new javax.swing.JTextField();
        eInteresesHipoteca = new javax.swing.JTextField();
        eInteresesDemoraHipoteca = new javax.swing.JTextField();
        eCostasGastosHipoteca = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        eOtrosGastosHipoteca = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        ePorcentajeResponsabilidadHipoteca = new javax.swing.JTextField();
        eResponsabilidadHipoteca1 = new javax.swing.JTextField();
        eNotarioCesion = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        eProtocoloCesion = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        eAmpliacion = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        eImporteAmpliacion = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        eModificacionNovacion = new javax.swing.JComboBox<>();
        eSubrogacion = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        eInteresesCostasEmbargo = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        eInteresesDemoraEmbargo = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        eGastosEmbargo = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        eTipoProcedimientoEmbargo = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        eNumeroProcedimientoEmbargo1 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        eJusgadoAyuntamiento = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        eFechaMandamientoEmbargo = new org.openswing.swing.client.DateControl();
        jLabel51 = new javax.swing.JLabel();
        eLetraAnotacion = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        eFechaAnotacion = new org.openswing.swing.client.DateControl();
        jLabel53 = new javax.swing.JLabel();
        eCocneptoAsiento = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        eNumeroAsiento = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        eFechaAsiento = new org.openswing.swing.client.DateControl();
        jLabel56 = new javax.swing.JLabel();
        eArrendamientoFinanciero = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        eNumeroFilaSolicitud = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        eFechaEscritura = new org.openswing.swing.client.DateControl();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        eTipoCarga = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cargas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Beneficiario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setText("Tipo de carga:");

        jLabel3.setText("Beneficiario:");

        eBeneficiario.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eBeneficiario.setNextFocusableComponent(eImporteHipoteca);

        jLabel4.setText("Porcentaje responsabilidad:");

        jLabel6.setText("Importe:");

        jLabel8.setText("Intereses hipoteca:");

        jLabel9.setText("Intereses de demora:");
        jLabel9.setName("INTERESES_DEMORA."); // NOI18N

        jLabel10.setText("Costas y gastos hipoteca:");

        jLabel15.setText("Plazo (meses) :");

        ePlazoDeCarga.setName("PLAZO_CARGA"); // NOI18N
        ePlazoDeCarga.setNextFocusableComponent(eFechaEscritura);

        jLabel16.setText("Fecha venc. hipoteca:");

        eAdicionar.setText("Añadir");
        eAdicionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eAdicionarMousePressed(evt);
            }
        });

        eModificar.setText("Modificar");
        eModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eModificarMousePressed(evt);
            }
        });

        eEliminar.setText("Eliminar");
        eEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                eEliminarMousePressed(evt);
            }
        });
        eEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eEliminarActionPerformed(evt);
            }
        });

        jLabel39.setText("Rango:");

        eRango.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eRango.setNextFocusableComponent(ePorcentajeResponsabilidadHipoteca);

        eImporteHipoteca.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eImporteHipoteca.setNextFocusableComponent(eInteresesHipoteca);

        eInteresesHipoteca.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eInteresesHipoteca.setNextFocusableComponent(eInteresesDemoraHipoteca);

        eInteresesDemoraHipoteca.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eInteresesDemoraHipoteca.setNextFocusableComponent(eCostasGastosHipoteca);

        eCostasGastosHipoteca.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eCostasGastosHipoteca.setNextFocusableComponent(eOtrosGastosHipoteca);

        jLabel40.setText("Otros gastos hipoteca:");

        eOtrosGastosHipoteca.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eOtrosGastosHipoteca.setNextFocusableComponent(eResponsabilidadHipoteca1);

        jLabel41.setText("Responsabilidad hipoteca:");

        ePorcentajeResponsabilidadHipoteca.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        ePorcentajeResponsabilidadHipoteca.setNextFocusableComponent(eBeneficiario);

        eResponsabilidadHipoteca1.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eResponsabilidadHipoteca1.setNextFocusableComponent(eFechaVencimiento);

        eNotarioCesion.setName("NOTARIO_CESION"); // NOI18N
        eNotarioCesion.setNextFocusableComponent(eProtocoloCesion);

        jLabel30.setText("Notario:");

        jLabel31.setText("Nro. Protocolo:");

        eProtocoloCesion.setName("PROTOCOLO_CESION"); // NOI18N
        eProtocoloCesion.setNextFocusableComponent(eAmpliacion);

        jLabel42.setText("Ampliación:");

        eAmpliacion.setFocusCycleRoot(true);
        eAmpliacion.setName("TIPO_CARGA"); // NOI18N
        eAmpliacion.setNextFocusableComponent(eImporteAmpliacion);

        jLabel11.setText("Importe ampliación:");

        eImporteAmpliacion.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eImporteAmpliacion.setNextFocusableComponent(eModificacionNovacion);

        jLabel43.setText("Modificación/Novación:");

        eModificacionNovacion.setFocusCycleRoot(true);
        eModificacionNovacion.setName("TIPO_CARGA"); // NOI18N
        eModificacionNovacion.setNextFocusableComponent(eSubrogacion);

        eSubrogacion.setFocusCycleRoot(true);
        eSubrogacion.setName("TIPO_CARGA"); // NOI18N
        eSubrogacion.setNextFocusableComponent(eInteresesCostasEmbargo);

        jLabel44.setText("Subrogación:");

        jLabel12.setText("Intereses y costas de embargo:");

        eInteresesCostasEmbargo.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eInteresesCostasEmbargo.setNextFocusableComponent(eInteresesDemoraEmbargo);

        jLabel45.setText("Intereses demora embargo:");

        eInteresesDemoraEmbargo.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eInteresesDemoraEmbargo.setNextFocusableComponent(eGastosEmbargo);

        jLabel46.setText("Gastos embargo:");

        eGastosEmbargo.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eGastosEmbargo.setNextFocusableComponent(eTipoProcedimientoEmbargo);

        jLabel47.setText("Tipo de procedimiento o embargo:");

        eTipoProcedimientoEmbargo.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eTipoProcedimientoEmbargo.setNextFocusableComponent(eNumeroProcedimientoEmbargo1);

        jLabel48.setText("Nro. de procedimiento o embargo:");

        eNumeroProcedimientoEmbargo1.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eNumeroProcedimientoEmbargo1.setNextFocusableComponent(eJusgadoAyuntamiento);

        jLabel49.setText("Jusgado o ayuntamiento:");

        eJusgadoAyuntamiento.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eJusgadoAyuntamiento.setNextFocusableComponent(eFechaMandamientoEmbargo);

        jLabel50.setText("Fecha mandamiento o embargo:");

        jLabel51.setText("Letra de anotación:");

        eLetraAnotacion.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eLetraAnotacion.setNextFocusableComponent(eFechaAnotacion);

        jLabel52.setText("Fecha anotación:");

        jLabel53.setText("Concepto del asiento:");

        eCocneptoAsiento.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eCocneptoAsiento.setNextFocusableComponent(eNumeroAsiento);

        jLabel54.setText("Nro. asiento:");

        eNumeroAsiento.setName("BENEFICIARIO_HIPOTECA"); // NOI18N
        eNumeroAsiento.setNextFocusableComponent(eFechaAsiento);

        jLabel55.setText("Fecha asiento:");

        jLabel56.setText("Arrendamiento o financiero:");

        eArrendamientoFinanciero.setFocusCycleRoot(true);
        eArrendamientoFinanciero.setName("TIPO_CARGA"); // NOI18N
        eArrendamientoFinanciero.setNextFocusableComponent(eNumeroFilaSolicitud);

        jLabel57.setText("Nro. fila/solicitud :");

        eNumeroFilaSolicitud.setName("BENEFICIARIO_HIPOTECA"); // NOI18N

        jLabel17.setText("Fecha escritura:");

        jButton1.setText("Limpiar formulario");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton2.setText("Finalizar");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        eTipoCarga.setName("TIPO_CARGA"); // NOI18N
        eTipoCarga.setNextFocusableComponent(eRango);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OTRA", "HIPOTECA", "EMBARGO", "CERTIFICADO DE CARGAS", "CONCURSO" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo de carga:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel39))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(eRango, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                                        .addComponent(eBeneficiario)
                                                        .addComponent(eImporteHipoteca)
                                                        .addComponent(ePorcentajeResponsabilidadHipoteca))
                                                    .addComponent(eInteresesHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(eInteresesDemoraHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(eCostasGastosHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(eTipoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel41)
                                            .addComponent(jLabel40)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eOtrosGastosHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eResponsabilidadHipoteca1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ePlazoDeCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eFechaEscritura, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel46)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(eGastosEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel47)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(eTipoProcedimientoEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel43)
                                                    .addComponent(jLabel45)
                                                    .addComponent(jLabel11))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(eInteresesDemoraEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel48)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(eNumeroProcedimientoEmbargo1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel49)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(eJusgadoAyuntamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(eEliminar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(eModificar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(eAdicionar)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel44)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eSubrogacion, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eInteresesCostasEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addGap(149, 149, 149))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(eAmpliacion, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(eImporteAmpliacion, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(eModificacionNovacion, javax.swing.GroupLayout.Alignment.TRAILING, 0, 147, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eNotarioCesion, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eProtocoloCesion, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eLetraAnotacion, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eFechaMandamientoEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eFechaAnotacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eCocneptoAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eNumeroAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel56)
                                    .addComponent(jLabel55))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(eFechaAsiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(eArrendamientoFinanciero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eNumeroFilaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2))))
                .addContainerGap(343, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(eTipoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel39)
                                            .addComponent(eRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(ePorcentajeResponsabilidadHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel31)
                                            .addComponent(eProtocoloCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(eNotarioCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel30)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42)
                                    .addComponent(eAmpliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(eBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eImporteAmpliacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(eImporteHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(eModificacionNovacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel43)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eSubrogacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel44))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(eInteresesCostasEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel45)
                                    .addComponent(eInteresesDemoraEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel46)
                                    .addComponent(eGastosEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel47)
                                    .addComponent(eTipoProcedimientoEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel48)
                                    .addComponent(eNumeroProcedimientoEmbargo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel49)
                                    .addComponent(eJusgadoAyuntamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(eInteresesHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(eInteresesDemoraHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(eCostasGastosHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel40)
                                    .addComponent(eOtrosGastosHipoteca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel41)
                                    .addComponent(eResponsabilidadHipoteca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(eFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ePlazoDeCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(eFechaEscritura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaMandamientoEmbargo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(eLetraAnotacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaAnotacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(eCocneptoAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(eNumeroAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaAsiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(eArrendamientoFinanciero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(eNumeroFilaSolicitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eAdicionar)
                    .addComponent(eModificar)
                    .addComponent(eEliminar)
                    .addComponent(jButton2))
                .addContainerGap(764, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eEliminarActionPerformed

    private void eAdicionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eAdicionarMousePressed
        if (!eNumeroFilaSolicitud.getText().equals("")){  
            JComponent compTemp = (JComponent)eTipoCarga;
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
                        listaComponentesXML.add(new ComponenteFormulario(atributo,  Utiles.convertirFechaYYYYMMDD( ((DateControl)compTemp).getDate())));
                    else listaComponentesXML.add(new ComponenteFormulario(atributo,  ""));
                } 
                compTemp = (JComponent)compTemp.getNextFocusableComponent();
            }
            fp.getListaCargas().add(listaComponentesXML);
            Utiles.llenarTabla(jTable1,fp.getListaCargas(), "Cargas");
            eNumeroFilaSolicitud.setText("");
            JOptionPane.showMessageDialog(null, Utiles.msgOperacionRealizada);
        }else {
            JOptionPane.showMessageDialog(null, "Debe introducir el Nro. fila/solicitud");
            eNumeroFilaSolicitud.requestFocus();
        }
    }//GEN-LAST:event_eAdicionarMousePressed

    private void eEliminarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eEliminarMousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1){
            DefaultTableModel tabla = (DefaultTableModel)jTable1.getModel();
            tabla.removeRow(indiceSeleccionado);
            jTable1.setModel(tabla);
            fp.getListaCargas().remove(indiceSeleccionado);
        } else
             JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneCargaEliminar);
    }//GEN-LAST:event_eEliminarMousePressed

    private void eModificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eModificarMousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1){
            JComponent compTemp = (JComponent)eTipoCarga;
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
                        listaComponentesXML.add(new ComponenteFormulario(atributo,  Utiles.convertirFechaYYYYMMDD( ((DateControl)compTemp).getDate())));
                    else listaComponentesXML.add(new ComponenteFormulario(atributo,  ""));
                } 
                compTemp = (JComponent)compTemp.getNextFocusableComponent();
            }
            fp.getListaCargas().set(indiceSeleccionado, listaComponentesXML);
            llenarFormulario();
            Utiles.llenarTabla(jTable1,fp.getListaCargas(), "Cargas");
            JOptionPane.showMessageDialog(null, Utiles.msgOperacionRealizada);
        } else
             JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneCargaModificar);
        
    }//GEN-LAST:event_eModificarMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jTable1.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
           indiceCargaSeleccionada = jTable1.getSelectedRow();
           llenarFormulario();
        }
        });
        Utiles.llenarTabla(jTable1,fp.getListaCargas(), "Cargas");
    }//GEN-LAST:event_formWindowOpened

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        int option = JOptionPane.showConfirmDialog(null, "Realmente desea limpiar el formulario?", "Limpiar formulario", JOptionPane.YES_NO_OPTION);
        if (option == 0)
            limpiarComponentesFormulario();
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        dispose();
    }//GEN-LAST:event_jButton2MousePressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        int indice = jComboBox1.getSelectedIndex();
        if (jComboBox1.getSelectedIndex() != -1){
            switch (indice){
                case 0: limpiarComponentesFormulario();
                        quitarPuntoNombre();
                        habilitarComponentes();
                        break;
                case 1: limpiarComponentesFormulario();
                        quitarPuntoNombre();
                        habilitarComponentes();
                        eTipoCarga.setName(eTipoCarga.getName().concat("\\."));
                        eTipoCarga.setText("HIPOTECA");
                        eRango.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eFechaAnotacion.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        ePorcentajeResponsabilidadHipoteca.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eBeneficiario.setName(eJusgadoAyuntamiento.getName().concat("\\."));
                        eImporteHipoteca.setName(eFechaMandamientoEmbargo.getName().concat("\\."));
                        eInteresesHipoteca.setName(eFechaAnotacion.getName().concat("\\."));
                        eInteresesDemoraHipoteca.setName(eFechaAnotacion.getName().concat("\\."));
                        eCostasGastosHipoteca.setName(eFechaAnotacion.getName().concat("\\."));
                        eOtrosGastosHipoteca.setName(eFechaAnotacion.getName().concat("\\."));
                        eResponsabilidadHipoteca1.setName(eFechaAnotacion.getName().concat("\\."));
                        eFechaVencimiento.setName(eFechaAnotacion.getName().concat("\\."));
                        ePlazoDeCarga.setName(eFechaAnotacion.getName().concat("\\."));
                        eFechaEscritura.setName(eFechaAnotacion.getName().concat("\\."));
                        eNotarioCesion.setName(eFechaAnotacion.getName().concat("\\."));
                        eProtocoloCesion.setName(eFechaAnotacion.getName().concat("\\."));
                        eAmpliacion.setName(eFechaAnotacion.getName().concat("\\."));
                        eImporteAmpliacion.setName(eFechaAnotacion.getName().concat("\\."));
                        eModificacionNovacion.setName(eFechaAnotacion.getName().concat("\\."));
                        eSubrogacion.setName(eFechaAnotacion.getName().concat("\\."));
                        eNumeroFilaSolicitud.setName(eFechaAnotacion.getName().concat("\\."));
                        deshabilitarComponentes();
                        break;
                case 2: limpiarComponentesFormulario();
                        quitarPuntoNombre();
                        habilitarComponentes();
                        eTipoCarga.setName(eTipoCarga.getName().concat("\\."));
                        eTipoCarga.setText("EMBARGO");
                        eRango.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eFechaAnotacion.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eImporteHipoteca.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eInteresesCostasEmbargo.setName(eJusgadoAyuntamiento.getName().concat("\\."));
                        eInteresesDemoraEmbargo.setName(eFechaMandamientoEmbargo.getName().concat("\\."));
                        eGastosEmbargo.setName(eFechaAnotacion.getName().concat("\\."));
                        eTipoProcedimientoEmbargo.setName(eFechaAnotacion.getName().concat("\\."));
                        eNumeroProcedimientoEmbargo1.setName(eFechaAnotacion.getName().concat("\\."));
                        eJusgadoAyuntamiento.setName(eFechaAnotacion.getName().concat("\\."));
                        eFechaMandamientoEmbargo.setName(eFechaAnotacion.getName().concat("\\."));
                        eLetraAnotacion.setName(eFechaAnotacion.getName().concat("\\."));
                        eFechaAnotacion.setName(eFechaAnotacion.getName().concat("\\."));
                        eNumeroFilaSolicitud.setName(eFechaAnotacion.getName().concat("\\."));
                        deshabilitarComponentes();
                        break;
                case 3: limpiarComponentesFormulario();
                        quitarPuntoNombre();
                        habilitarComponentes();
                        eTipoCarga.setName(eTipoCarga.getName().concat("\\."));
                        eTipoCarga.setText("CERTIFICADO DE CARGAS");
                        eRango.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eFechaAnotacion.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eNumeroProcedimientoEmbargo1.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eJusgadoAyuntamiento.setName(eJusgadoAyuntamiento.getName().concat("\\."));
                        eFechaMandamientoEmbargo.setName(eFechaMandamientoEmbargo.getName().concat("\\."));
                        eFechaAnotacion.setName(eFechaAnotacion.getName().concat("\\."));
                        eNumeroFilaSolicitud.setName(eFechaAnotacion.getName().concat("\\."));
                        deshabilitarComponentes();
                       break;
                case 4: limpiarComponentesFormulario();
                        quitarPuntoNombre();
                        habilitarComponentes();
                        eTipoCarga.setName(eTipoCarga.getName().concat("\\."));
                        eTipoCarga.setText("CONCURSO");
                        eRango.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eFechaAnotacion.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eNumeroProcedimientoEmbargo1.setName(eNumeroProcedimientoEmbargo1.getName().concat("\\."));
                        eJusgadoAyuntamiento.setName(eJusgadoAyuntamiento.getName().concat("\\."));
                        eFechaMandamientoEmbargo.setName(eFechaMandamientoEmbargo.getName().concat("\\."));
                        eFechaAnotacion.setName(eFechaAnotacion.getName().concat("\\."));
                        eNumeroFilaSolicitud.setName(eFechaAnotacion.getName().concat("\\."));
                        deshabilitarComponentes();
                    break;
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormaCargasExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaCargasExcel dialog = new FormaCargasExcel(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton eAdicionar;
    private javax.swing.JComboBox<String> eAmpliacion;
    private javax.swing.JComboBox<String> eArrendamientoFinanciero;
    private javax.swing.JTextField eBeneficiario;
    private javax.swing.JTextField eCocneptoAsiento;
    private javax.swing.JTextField eCostasGastosHipoteca;
    private javax.swing.JButton eEliminar;
    private org.openswing.swing.client.DateControl eFechaAnotacion;
    private org.openswing.swing.client.DateControl eFechaAsiento;
    private org.openswing.swing.client.DateControl eFechaEscritura;
    private org.openswing.swing.client.DateControl eFechaMandamientoEmbargo;
    private org.openswing.swing.client.DateControl eFechaVencimiento;
    private javax.swing.JTextField eGastosEmbargo;
    private javax.swing.JTextField eImporteAmpliacion;
    private javax.swing.JTextField eImporteHipoteca;
    private javax.swing.JTextField eInteresesCostasEmbargo;
    private javax.swing.JTextField eInteresesDemoraEmbargo;
    private javax.swing.JTextField eInteresesDemoraHipoteca;
    private javax.swing.JTextField eInteresesHipoteca;
    private javax.swing.JTextField eJusgadoAyuntamiento;
    private javax.swing.JTextField eLetraAnotacion;
    private javax.swing.JComboBox<String> eModificacionNovacion;
    private javax.swing.JButton eModificar;
    private javax.swing.JTextField eNotarioCesion;
    private javax.swing.JTextField eNumeroAsiento;
    private javax.swing.JTextField eNumeroFilaSolicitud;
    private javax.swing.JTextField eNumeroProcedimientoEmbargo1;
    private javax.swing.JTextField eOtrosGastosHipoteca;
    private javax.swing.JTextField ePlazoDeCarga;
    private javax.swing.JTextField ePorcentajeResponsabilidadHipoteca;
    private javax.swing.JTextField eProtocoloCesion;
    private javax.swing.JTextField eRango;
    private javax.swing.JTextField eResponsabilidadHipoteca1;
    private javax.swing.JComboBox<String> eSubrogacion;
    private javax.swing.JTextField eTipoCarga;
    private javax.swing.JTextField eTipoProcedimientoEmbargo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
