/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Atxy2k.CustomTextField.RestrictedTextField;
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
public class FormaTitularTasacion extends javax.swing.JDialog {

    /**
     * 1: Adicionar
     * 2:Modificar
     */
    private final Integer tipoLLamada;
    private final FormaProcesarFinca fp;
    private final int indiceElementoModificar;
    private Vector modelTipoPropiedad;
    private Vector modelTipoTitulo;
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
            /*
            case "Regimen":model.addElement( new ModelCombo("Gananciales", "Gananciales" ) );
                                model.addElement( new ModelCombo("SeparacionBienes", "SeparacionBienes" ) );
                                model.addElement( new ModelCombo("Participacion", "Participacion" ) );
                                eRegimen.setModel(new DefaultComboBoxModel(model));
                                break;
            */
            
            case "Tipo de participacion":
                                model = new Vector();
                                model.addElement( new ModelCombo("Titular", "Titular" ) );
                                model.addElement( new ModelCombo("SujetoPasivo", "SujetoPasivo" ) );
                                model.addElement( new ModelCombo("Vendedor", "Vendedor" ) );
                                model.addElement( new ModelCombo("Avalista", "Avalista" ) );
                                model.addElement( new ModelCombo("Otros", "Otros" ) );
                                model.addElement( new ModelCombo("Demandante", "Demandante" ) );
                                model.addElement( new ModelCombo("Demandado", "Demandado" ) );
                                model.addElement( new ModelCombo("PersonaContacto", "PersonaContacto" ) );
                                model.addElement( new ModelCombo("HipotecanteNoDeudor", "HipotecanteNoDeudor" ) );
                                eTipoParticipacion.setModel(new DefaultComboBoxModel(model));
                                break;  
            case "Tipo de propiedad":modelTipoPropiedad = new Vector();
                                modelTipoPropiedad.addElement( new ModelCombo("Propietario", "Propietario" ) );
                                modelTipoPropiedad.addElement( new ModelCombo("Nudo_propietario", "Nudo_propietario" ) );
                                modelTipoPropiedad.addElement( new ModelCombo("Usufructuario", "Usufructuario" ) );
                                modelTipoPropiedad.addElement( new ModelCombo("Arrendatario_Ocupante", "Arrendatario_Ocupante" ) );
                                eTipoPropiedad.setModel(new DefaultComboBoxModel(modelTipoPropiedad));
                                break;
             case "Tipo de titulo":modelTipoTitulo = new Vector();
                                modelTipoTitulo.addElement( new ModelCombo("Otros", "Otros" ) );
                                modelTipoTitulo.addElement( new ModelCombo("Adjudicacion", "Adjudicacion" ) );
                                modelTipoTitulo.addElement( new ModelCombo("Aportacion", "Aportacion" ) );
                                modelTipoTitulo.addElement( new ModelCombo("CompraVenta", "CompraVenta" ) );
                                modelTipoTitulo.addElement( new ModelCombo("DacionEnPago", "DacionEnPago" ) );
                                modelTipoTitulo.addElement( new ModelCombo("Donacion", "Donacion" ) );
                                modelTipoTitulo.addElement( new ModelCombo("Herencia", "Herencia" ) );
                                modelTipoTitulo.addElement( new ModelCombo("LiquidacionSociedad", "LiquidacionSociedad" ) );
                                modelTipoTitulo.addElement( new ModelCombo("ObraNueva", "ObraNueva" ) );
                                modelTipoTitulo.addElement( new ModelCombo("Permuta", "Permuta" ) );
                                modelTipoTitulo.addElement( new ModelCombo("PropiedadDivisionHorizontal", "PropiedadDivisionHorizontal" ) );
                                modelTipoTitulo.addElement( new ModelCombo("TransmisionOnerosa", "TransmisionOnerosa" ) );
                                modelTipoTitulo.addElement( new ModelCombo("CompensacionUrbanistica", "CompensacionUrbanistica" ) );
                                eTipoTitulo.setModel(new DefaultComboBoxModel(modelTipoTitulo));
                                break;
                                
            case "Tipo de documento":model = new Vector();
                                model.addElement( new ModelCombo("Unknwon", "Unknwon" ) );
                                model.addElement( new ModelCombo("Nif", "Nif" ) );
                                model.addElement( new ModelCombo("Cif", "Cif" ) );
                                model.addElement( new ModelCombo("Passport", "Passport" ) );
                                model.addElement( new ModelCombo("TarjetaResidente", "TarjetaResidente" ) );
                                model.addElement( new ModelCombo("IdentificacionFiscalMenor", "IdentificacionFiscalMenor" ) );
                                eTipoDocumento.setModel(new DefaultComboBoxModel(model));
                                break;  
            case "Regimen economico":model = new Vector();
                                    model.addElement( new ModelCombo("Otros", "Otros" ) );
                                    model.addElement( new ModelCombo("Gananciales", "Gananciales" ) );
                                    model.addElement( new ModelCombo("SeparacionBienes", "SeparacionBienes" ) );
                                    model.addElement( new ModelCombo("Participacion", "Participacion" ) );
                                    eRegimenEconomico.setModel(new DefaultComboBoxModel(model));
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
        ArrayList<ComponenteFormulario> listaComponentesXML = fp.getListaTitulares().get(indiceElementoModificar);
        eNombreRazon.setText(listaComponentesXML.get(0).getValor());
        ePrimerApellido.setText(listaComponentesXML.get(1).getValor());
        eSegundoApellido.setText(listaComponentesXML.get(2).getValor());
        eDocumento.setText(listaComponentesXML.get(4).getValor());
        
        
        switch(listaComponentesXML.get(3).getValor()){
            case "Unknwon":eTipoDocumento.setSelectedIndex(0);
                    break;
            case "Nif":eTipoDocumento.setSelectedIndex(1);
                    break;
            case "Cif":eTipoDocumento.setSelectedIndex(2);
                    break; 
            case "Passport":eTipoDocumento.setSelectedIndex(3);
                    break; 
            case "TarjetaResidente":eTipoDocumento.setSelectedIndex(4);
                    break; 
            case "IdentificacionFiscalMenor":eTipoDocumento.setSelectedIndex(5);
                    break;         
        }
        
        switch(listaComponentesXML.get(14).getValor()){
            case "Gananciales":eRegimenEconomico.setSelectedIndex(1);
                    break;
            case "SeparacionBienes":eRegimenEconomico.setSelectedIndex(2);
                    break;
            case "Participacion":eRegimenEconomico.setSelectedIndex(3);
                    break; 
            default:eRegimenEconomico.setSelectedIndex(0);
        }
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha;
            if (!listaComponentesXML.get(5).getValor().equals("")){
                fecha = formato.parse(listaComponentesXML.get(5).getValor());
                eFechaEscritura.setDate(fecha);
            }
            if (!listaComponentesXML.get(9).getValor().equals("")){
                fecha = formato.parse(listaComponentesXML.get(9).getValor());
                eFechaInscripcionTitular.setDate(fecha);
            }
        } catch (ParseException ex) {
            Logger.getLogger(FormaTitularTasacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        eNumeroProtocolo.setText(listaComponentesXML.get(6).getValor());
        eNotario.setText(listaComponentesXML.get(7).getValor());
        eNumeroInscripcion.setText(listaComponentesXML.get(8).getValor());
        
        switch(listaComponentesXML.get(10).getValor()){
            case "Propietario":eTipoParticipacion.setSelectedIndex(0);
                    break;
            case "Nudo_propietario":eTipoParticipacion.setSelectedIndex(1);
                    break;
            case "Usufructuario":eTipoParticipacion.setSelectedIndex(2);
                    break;        
            case "Arrendatario_Ocupante":eTipoParticipacion.setSelectedIndex(3);
                    break;        
        }
        ePorcientoParticipacion.setText(listaComponentesXML.get(11).getValor());
        
        seleccionarElementoCombo(modelTipoTitulo, eTipoTitulo, listaComponentesXML.get(12).getValor());
        seleccionarElementoCombo(modelTipoPropiedad, eTipoPropiedad, listaComponentesXML.get(13).getValor());
    }
    
    /**
     * 
     * @param parent
     * @param modal
     * @param tipoLLamada
     * @param indiceElementoModificar 
     */
    public FormaTitularTasacion(java.awt.Dialog parent, boolean modal, Integer tipoLLamada, Integer indiceElementoModificar) {
        super(parent, modal);
        initComponents();
        this.tipoLLamada = tipoLLamada;
        this.fp = (FormaProcesarFinca)parent;
        this.indiceElementoModificar = indiceElementoModificar;
        
        /*
        RestrictedTextField restriccionNumeroInscripcion = new RestrictedTextField(eNumeroInscripcion);
        restriccionNumeroInscripcion.setOnlyNums(true);
        restriccionNumeroInscripcion.setLimit(10);
        */
        
        RestrictedTextField restriccionNif = new RestrictedTextField(eDocumento);
        restriccionNif.setLimit(10);
        
        RestrictedTextField restriccionTipoProtocolo = new RestrictedTextField(eNumeroProtocolo);
        restriccionTipoProtocolo.setLimit(20);
        
        RestrictedTextField restriccionNotario = new RestrictedTextField(eNotario);
        restriccionNotario.setLimit(50);  
        
        eFechaEscritura.setFormat(Resources.DMY);
        eFechaEscritura.setName("TLO_FECHA_ESCRITURA.");
        eFechaEscritura.setNextFocusableComponent(eNumeroProtocolo);
        eFechaInscripcionTitular.setFormat(Resources.DMY);
        eFechaInscripcionTitular.setName("TLO_FECHA_INSCRIPCION.");
        eFechaInscripcionTitular.setNextFocusableComponent(eTipoParticipacion);
        
        llenarComboTipoInmueble("Regimen");
        llenarComboTipoInmueble("Tipo de participacion");
        llenarComboTipoInmueble("Tipo de propiedad");
        llenarComboTipoInmueble("Tipo de documento");
        llenarComboTipoInmueble("Regimen economico");
        llenarComboTipoInmueble("Tipo de titulo");
        
        if (tipoLLamada == 2)
            llenarFormulario();
        else if (tipoLLamada == 3){
            llenarFormulario();
            eNombreRazon.setEnabled(false);
            ePrimerApellido.setEnabled(false);
            eSegundoApellido.setEnabled(false);
            eTipoDocumento.setEnabled(false);
            eDocumento.setEnabled(false);
            eRegimenEconomico.setEnabled(false);
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

        jPanel6 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        eNombreRazon = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        ePrimerApellido = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        eSegundoApellido = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        eDocumento = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        eFechaEscritura = new org.openswing.swing.client.DateControl();
        jLabel41 = new javax.swing.JLabel();
        eNumeroProtocolo = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        eNotario = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        eNumeroInscripcion = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        eFechaInscripcionTitular = new org.openswing.swing.client.DateControl();
        jLabel45 = new javax.swing.JLabel();
        eTipoParticipacion = new javax.swing.JComboBox<String>();
        jLabel46 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        ePorcientoParticipacion = new javax.swing.JTextField();
        eTipoDocumento = new javax.swing.JComboBox<String>();
        jLabel50 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        eTipoPropiedad = new javax.swing.JComboBox<String>();
        jLabel51 = new javax.swing.JLabel();
        eRegimenEconomico = new javax.swing.JComboBox<String>();
        jLabel52 = new javax.swing.JLabel();
        eTipoTitulo = new javax.swing.JComboBox<String>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Titular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel34.setText("Nomb/Razón Social:");

        eNombreRazon.setName("TIT_NOMBRE"); // NOI18N
        eNombreRazon.setNextFocusableComponent(ePrimerApellido);

        jLabel35.setText("Primer apellido:");

        ePrimerApellido.setName("TIT_APELLIDO1."); // NOI18N
        ePrimerApellido.setNextFocusableComponent(eSegundoApellido);

        jLabel36.setText("Segundo apellido:");

        eSegundoApellido.setName("TIT_APELLIDO2."); // NOI18N
        eSegundoApellido.setNextFocusableComponent(eTipoDocumento);

        jLabel37.setText("NIF/CIF titular:");

        eDocumento.setName("TIT_DOC_IDENTIDAD."); // NOI18N
        eDocumento.setNextFocusableComponent(eFechaEscritura);

        jLabel40.setText("Fecha escritura:");

        jLabel41.setText("Número protocolo:");

        eNumeroProtocolo.setName("TLO_PROTOCOLO."); // NOI18N
        eNumeroProtocolo.setNextFocusableComponent(eNotario);

        jLabel42.setText("Notario:");

        eNotario.setName("TLO_NOTARIA."); // NOI18N
        eNotario.setNextFocusableComponent(eNumeroInscripcion);

        jLabel43.setText("Número inscripción:");

        eNumeroInscripcion.setName("TLO_NUM_INSCRIPCION."); // NOI18N
        eNumeroInscripcion.setNextFocusableComponent(eFechaInscripcionTitular);
        eNumeroInscripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eNumeroInscripcionKeyTyped(evt);
            }
        });

        jLabel44.setText("Fecha inscripción:");

        jLabel45.setText("Tipo de participación:");

        eTipoParticipacion.setName("TLO_TIPO_PARTICIPACION."); // NOI18N
        eTipoParticipacion.setNextFocusableComponent(ePorcientoParticipacion);

        jLabel46.setText("% de Partic. titular:");

        jButton1.setText("Aceptar");
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

        ePorcientoParticipacion.setName("TLO_PORC_PARTICIPACION."); // NOI18N
        ePorcientoParticipacion.setNextFocusableComponent(eTipoTitulo);
        ePorcientoParticipacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ePorcientoParticipacionKeyTyped(evt);
            }
        });

        eTipoDocumento.setName("TIPO_DOCUMENTO"); // NOI18N
        eTipoDocumento.setNextFocusableComponent(eDocumento);

        jLabel50.setText("Tipo de documento:");

        jLabel47.setText("Tipo de propiedad:");

        eTipoPropiedad.setName("TLO_TIPO_PROPIEDAD"); // NOI18N
        eTipoPropiedad.setNextFocusableComponent(eRegimenEconomico);

        jLabel51.setText("Régimen económico:");

        eRegimenEconomico.setName("REGIMEN_ECONOMICO"); // NOI18N

        jLabel52.setText("Tipo de título:");

        eTipoTitulo.setName("TLO_TIPO_TITULO."); // NOI18N
        eTipoTitulo.setNextFocusableComponent(eTipoPropiedad);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ePrimerApellido)
                            .addComponent(eSegundoApellido, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eNombreRazon)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eTipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eDocumento)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel40)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44)
                            .addComponent(jLabel45)
                            .addComponent(jLabel46)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eTipoTitulo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eTipoParticipacion, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eFechaInscripcionTitular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(eNumeroInscripcion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eNotario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eNumeroProtocolo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaEscritura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ePorcientoParticipacion)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel51))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eRegimenEconomico, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eTipoPropiedad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(eNombreRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(ePrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(eFechaEscritura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNumeroProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eNotario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eNumeroInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(eFechaInscripcionTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eTipoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(ePorcientoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eTipoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eTipoPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eRegimenEconomico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
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
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        JComponent comp = Utiles.ValidaControles(jPanel6);
        if (comp == null){
          if (eDocumento.getText().length() <= 10){
            if (Utiles.validarFecha(eFechaEscritura) && Utiles.validarFecha(eFechaInscripcionTitular)) {    
                JComponent compTemp = (JComponent)eNombreRazon;
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
                if ((tipoLLamada == 1) || (tipoLLamada == 3))
                    fp.getListaTitulares().add(listaComponentesXML);
                else fp.getListaTitulares().set(indiceElementoModificar, listaComponentesXML);
                
                JOptionPane.showMessageDialog(null, Utiles.msgOperacionRealizada);
            } else {
                    JOptionPane.showMessageDialog(null,"Ha definido alguna fecha incorrectamente.");
            }     
          }else {
            JOptionPane.showMessageDialog(null,"Documento incorrecto, a lo sumo debe tener 10 caracteres.");
            eDocumento.requestFocus();
          }   
        }else {
             if (comp instanceof DateControl && ((DateControl) comp).getValue() != null) 
                {
                    JOptionPane.showMessageDialog(null,Utiles.msgFechaIncorrecta + comp.getName());
                    comp.requestFocus();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,Utiles.msgDebeIntroducir + comp.getName());
                    comp.requestFocus();
                } 
        }
        
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        this.dispose();
    }//GEN-LAST:event_jButton2MousePressed

    private void ePorcientoParticipacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ePorcientoParticipacionKeyTyped
        Utiles.validarNumeroReal(evt, ePorcientoParticipacion, 3, 2);
    }//GEN-LAST:event_ePorcientoParticipacionKeyTyped

    private void eNumeroInscripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eNumeroInscripcionKeyTyped
         Utiles.validarNumeroEntero(evt, eNumeroInscripcion, 10);
    }//GEN-LAST:event_eNumeroInscripcionKeyTyped

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
            java.util.logging.Logger.getLogger(FormaTitularTasacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaTitularTasacion dialog = new FormaTitularTasacion(new javax.swing.JDialog(), true, null, null);
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
    private javax.swing.JComboBox<String> eRegimenEconomico;
    private javax.swing.JTextField eSegundoApellido;
    private javax.swing.JComboBox<String> eTipoDocumento;
    private javax.swing.JComboBox<String> eTipoParticipacion;
    private javax.swing.JComboBox<String> eTipoPropiedad;
    private javax.swing.JComboBox<String> eTipoTitulo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
