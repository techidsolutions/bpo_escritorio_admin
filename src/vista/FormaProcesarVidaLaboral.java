/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import modelo.Documento;
import modelo.ModelCombo;
import org.jdesktop.swingx.JXTable;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.internationalization.java.Resources;
import org.oxbow.swingbits.table.filter.TableRowFilterSupport;
import util.TareaSegundoPlano;
import util.Utiles;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaProcesarVidaLaboral extends javax.swing.JDialog {
    Vector modelTipoRegimen;
    Vector modelTipoContrato;
    final int filas = 20;
    ArrayList<Documento> listaDocumentos;
    int elementoSeleccionadoTabla = -1;
    Documento documentoSeleccionado;
    /**
     * Creates new form FormaProcesarIRPF
     * @param parent
     * @param modal
     */
    public FormaProcesarVidaLaboral(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        RestrictedTextField restriccionNumeroAnnosTotal = new RestrictedTextField(eNumeroAnnosTotal);
        restriccionNumeroAnnosTotal.setLimit(15);
        restriccionNumeroAnnosTotal.setOnlyNums(true);
        
        RestrictedTextField restriccionNif = new RestrictedTextField(eNif);
        restriccionNif.setLimit(10);
        
        eFechaAltaSS.setFormat(Resources.DMY);
        eFechaAltaSS.setName("FECHA_ALTA_SEGURIDAD_SOCIAL");
        eFechaAltaSS.setNextFocusableComponent(eFechaCEA);
        
        eFechaCEA.setFormat(Resources.DMY);
        eFechaCEA.setName("FECHA_CEA");
        eFechaCEA.setNextFocusableComponent(eFechaAltaUltimaEmpresa);
        
        eFechaAltaUltimaEmpresa.setFormat(Resources.DMY);
        eFechaAltaUltimaEmpresa.setName("FECHA_ALTA_ULTIMA_EMPRESA");
        eFechaAltaUltimaEmpresa.setNextFocusableComponent(eFechaDocumento);
        
        eFechaDocumento.setFormat(Resources.DMY);
        eFechaDocumento.setName("FECHA_DOCUMENTO");
        eFechaDocumento.setNextFocusableComponent(eIdCEA);
        
    }
    
    /**
     * 
     * @param TipoCombo 
     */
    private void llenarCombo(String TipoCombo){
        switch(TipoCombo){
            case "Tipo Regimen":modelTipoRegimen = new Vector();
                                modelTipoRegimen.addElement( new ModelCombo("General", "General" ) );
                                modelTipoRegimen.addElement( new ModelCombo("Autonomos", "Autonomos" ) );
                                modelTipoRegimen.addElement( new ModelCombo("Mar", "Mar" ) );
                                modelTipoRegimen.addElement( new ModelCombo("Carbion", "Carbion" ) );
                                modelTipoRegimen.addElement( new ModelCombo("Hogar", "Hogar" ) );
                                modelTipoRegimen.addElement( new ModelCombo("Gral_RepresentantesComercio", "Gral_RepresentantesComercio" ) );
                                modelTipoRegimen.addElement( new ModelCombo("Gral_SistemasEspecialesFrutasHorrtalizasConservasVegetales", "Gral_SistemasEspecialesFrutasHorrtalizasConservasVegetales" ) );
                                
                                eTipoRegimen.setModel(new DefaultComboBoxModel(modelTipoRegimen));
                                break;
            
            case "Tipo Contrato":modelTipoContrato = new Vector();
                                modelTipoContrato.addElement( new ModelCombo("SinAsignar", "SinAsignar" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_completoOrdinario", "100" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_completoFomento_contratacion_indefinidaTransformacion_contrato_temporal", "109" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_completoPersonas_con_discapacidad", "130" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_completoPersonas_con_discapacidadTransformacion_contrato_temporal", "139" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_completoFomento_contratacion_indefinidaInicial", "150" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_completoTransformacion_contrato_temporal", "189" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_parcialOrdinario", "200" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_parcialFomento_contratacion_indefinidaTransformacion_contrato_temporal", "209" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_parcialPersonas_con_discapacidad", "230" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_parcialPersonas_con_discapacidadTransformacion_contrato_temporal", "239" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_parcialFomento_contratacion_indefinidaInicial", "250" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_parcialTransformacion_contrato_temporal", "289" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoFijo_discontinuo", "300" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoFijo_discontinuoFomento_contratacion_indefinidaTransformacion_contrato_temporal", "309" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoFijo_discontinuoPersonas_con_discapacidad", "330" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoTiempo_parcialPersonas_con_discapacidadTransformacion_contrato_temporal_", "339" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoFijo_discontinuoFomento_contratacion_indefinidaInicial", "350" ) );
                                modelTipoContrato.addElement( new ModelCombo("IndefinidoFijo_discontinuoTransformacion_contrato_temporal", "389" ) );
                                modelTipoContrato.addElement( new ModelCombo("Duracion_determinadaTiempo_completoobra_o_servicio_determinado", "401" ) );
                                modelTipoContrato.addElement( new ModelCombo("Duracion_determinadaTiempo_completoEventual_circunstancias_de_la_produccion", "402" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_completoCaracter_administrativo", "408" ) );
                                modelTipoContrato.addElement( new ModelCombo("Duracion_determinadaTiempo_completoInterinidad", "410" ) );
                                modelTipoContrato.addElement( new ModelCombo("Duracion_determinadaTiempo_completoInterinidadCaracter_administrativo", "418" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_completoPracticas", "420" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_completoformacion_y_aprendizaje", "430" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_completoRelevo", "441" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_completoFomento_contratacion_indefinida", "450" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_completoEmpresas_de_insercion", "452" ) );
                                modelTipoContrato.addElement( new ModelCombo("Duracion_determinadaTiempo_parcialobra_o_servicio_determinado", "501" ) );
                                modelTipoContrato.addElement( new ModelCombo("Duracion_determinadaTiempo_parcialEventual_circustancias_de_la_produccion", "502" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_parcialCaracter_administrativo", "508" ) );
                                modelTipoContrato.addElement( new ModelCombo("Duracion_determinadaTiempo_parcialInterinidad", "510" ) );
                                modelTipoContrato.addElement( new ModelCombo("Duracion_determinadaTiempo_parcialInterinidadCaracter_administrativo", "518" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_parcialPracticas", "520" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_parcialPersonas_con_discapacidad", "530" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_parcialJubilado_parcial", "540" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_parcialRelevo", "541" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_parcialFomento_contratacion_indefinida", "550" ) );
                                modelTipoContrato.addElement( new ModelCombo("TemporalTiempo_parcialEmpresas_de_insercion", "552" ) );
                                eTipoContrato.setModel(new DefaultComboBoxModel(modelTipoContrato));
                                break;
        }
    }
    
    /**
     * 
     */
    private void limpiarComponentesFormulario(){
        eCodCEA.setText("");
        eIdCEA.setText("");
        eNumeroAnnosTotal.setText("");
        eNif.setText("");
        eNombreDocumento.setText("");
        eNumeroSeguridadSocial.setText("");
        eNombreTitular.setText("");
                
        //Combo
        eTipoContrato.setSelectedIndex(0); 
        eTipoRegimen.setSelectedIndex(0); 

        //Fechas
        Date fecha = null;
        eFechaAltaSS.setDate(fecha);
        eFechaAltaUltimaEmpresa.setDate(fecha);
        eFechaCEA.setDate(fecha);
        eFechaDocumento.setDate(fecha);
    }
    
    /**
     * 
     * @return 
     */
    public static ArrayList<Documento> cargarListaDocumentos(){
        ArrayList<Documento> listaDocumentos = new ArrayList<>();
        String caminoDirectorioRaiz = Utiles.rutaEnviadosVidaLaboral;
	File dirRaiz = new File(caminoDirectorioRaiz);
        String archivos[] = dirRaiz.list();
        File dirTemp;
        Documento documento;
        for (String archivo : archivos) {
            dirTemp = new File(caminoDirectorioRaiz.concat(archivo));
            documento = new Documento(dirTemp.getName(), "Vida Laboral", "Pendiente de procesar", "", "", "");
            listaDocumentos.add(documento);
        }
        return listaDocumentos;
    }
    
    /**
     * 
     * @param mensaje 
     */
    private void actualizarInfoDocumentos(String mensaje){
            new TareaSegundoPlano(this, mensaje) {
                @Override
                    protected void tareaHaRealizar() {
                        listaDocumentos = cargarListaDocumentos();
                        Utiles.llenarTabla(jXTable1, listaDocumentos, "Documentos IRPF");
                        Dimension dimension = jXTable1.getPreferredSize();
                        jScrollPane1.setPreferredSize(new Dimension(dimension.width,jXTable1.getRowHeight()*filas));
                    }
            }.ejecutarTarea();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        eNumeroAnnosTotal = new javax.swing.JTextField();
        eFechaCEA = new org.openswing.swing.client.DateControl();
        bConvertir = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        eNombreDocumento = new javax.swing.JTextField();
        eCodCEA = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        eFechaAltaUltimaEmpresa = new org.openswing.swing.client.DateControl();
        eNumeroSeguridadSocial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        eFechaAltaSS = new org.openswing.swing.client.DateControl();
        jLabel5 = new javax.swing.JLabel();
        eIdCEA = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        eTipoRegimen = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        eFechaDocumento = new org.openswing.swing.client.DateControl();
        eTipoContrato = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        eNif = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        eNombreTitular = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jButton2 = new javax.swing.JButton();
        jXFindBar1 = new org.jdesktop.swingx.JXFindBar();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extracción datos Vida Laboral");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información Vida Laboral", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Código CEA:");

        jLabel4.setText("Números de años total:");

        jLabel6.setText("Fecha CEA:");

        eNumeroAnnosTotal.setName("NUMERO_ANNO_TOTAL"); // NOI18N
        eNumeroAnnosTotal.setNextFocusableComponent(eNumeroSeguridadSocial);

        bConvertir.setText("Convertir");
        bConvertir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bConvertirMousePressed(evt);
            }
        });

        jLabel13.setText("Nombre documento:");

        eNombreDocumento.setEnabled(false);
        eNombreDocumento.setName("NOMBRE_DOCUMENTO"); // NOI18N
        eNombreDocumento.setNextFocusableComponent(eNombreTitular);

        eCodCEA.setName("COD_CEA"); // NOI18N
        eCodCEA.setNextFocusableComponent(eFechaAltaSS);

        jLabel14.setText("Fecha alta última empresa:");

        eNumeroSeguridadSocial.setName("NUMERO_SEGURIDAD_SOCIAL"); // NOI18N
        eNumeroSeguridadSocial.setNextFocusableComponent(eTipoContrato);

        jLabel2.setText("Número de seguridad social:");

        jLabel3.setText("Tipo régimen:");

        jLabel7.setText("Fecha 1ra Alta SS:");

        jLabel5.setText("ID CEA:");

        eIdCEA.setName("ID_CEA"); // NOI18N
        eIdCEA.setNextFocusableComponent(eNif);

        jLabel8.setText("Tipo Contrato");

        eTipoRegimen.setName("TIPO_REGIMEN"); // NOI18N

        jLabel15.setText("Fecha documento:");

        eTipoContrato.setName("TIPO_CONTRATO"); // NOI18N
        eTipoContrato.setNextFocusableComponent(eTipoRegimen);

        jLabel16.setText("NIF:");

        eNif.setName("NIF"); // NOI18N
        eNif.setNextFocusableComponent(eNumeroAnnosTotal);

        jLabel9.setText("Nombre titular:");

        eNombreTitular.setName("NOMBRE_TITULAR"); // NOI18N
        eNombreTitular.setNextFocusableComponent(eCodCEA);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel13)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eNombreDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eTipoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bConvertir)
                        .addComponent(eTipoRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 41, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eNif, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(eNumeroAnnosTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(eNumeroSeguridadSocial))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eNombreTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eIdCEA, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eCodCEA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(eFechaAltaUltimaEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(eFechaCEA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(eFechaAltaSS, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(eFechaDocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(eNombreDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(eNombreTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(eCodCEA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(eFechaAltaSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(eFechaCEA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(eFechaAltaUltimaEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(eFechaDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(eIdCEA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(eNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(eNumeroAnnosTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(eNumeroSeguridadSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(eTipoContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(eTipoRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bConvertir))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jXTable1.setEditable(false);
        jScrollPane2.setViewportView(jXTable1);

        jButton2.setText("Cargar documento");
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

        jButton1.setText("Actualizar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jButton3.setText("Convertir a Documento KO");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bConvertirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConvertirMousePressed
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea Convertir?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0){
            JComponent comp = Utiles.ValidaControles(jPanel2);
            if (comp == null){
                if (Utiles.validarFecha(eFechaAltaSS) && Utiles.validarFecha(eFechaAltaUltimaEmpresa) && Utiles.validarFecha(eFechaCEA) && Utiles.validarFecha(eFechaDocumento)) {
                    if (eNif.getText().length() <= 10){
                        new TareaSegundoPlano(this, Utiles.msgTareaRealizandoConversion) {
                            @Override
                            protected void tareaHaRealizar() {
                                File archivo = new File(Utiles.rutaEnviadosVidaLaboral.concat(eNombreDocumento.getText()));
                                Boolean movido = archivo.renameTo(new File(Utiles.rutaProcesadosVidaLaboralPDF.concat(eNombreDocumento.getText())));
                                if (movido){
                                    Utiles.generarXMLVidaLaboral(eNombreDocumento);
                                    listaDocumentos.remove(elementoSeleccionadoTabla);
                                    DefaultTableModel modelo = (DefaultTableModel)jXTable1.getModel();
                                    modelo.removeRow(elementoSeleccionadoTabla);
                                    jXTable1.setModel(modelo);
                                    limpiarComponentesFormulario();
                                }else JOptionPane.showMessageDialog(null,"Debe cerrar el documento PDF antes de convertir.");
                            }
                            }.ejecutarTarea();
                    }else {
                            JOptionPane.showMessageDialog(null,"NIF incorrecto, a lo sumo debe tener 10 caracteres.");
                            eNif.requestFocus();
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Fecha incorrecta.");
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
        }
    }//GEN-LAST:event_bConvertirMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        llenarCombo("Tipo Regimen");
        llenarCombo("Tipo Contrato");
        
        JTableHeader th;
        th = jXTable1.getTableHeader();
        Font fuente = new Font(th.getFont().getName(), Font.BOLD, 11);
        th.setFont(fuente);

        jXTable1.setAutoResizeMode(JXTable.AUTO_RESIZE_OFF);
        TableColumn columna = jXTable1.getColumn("Nombre");
        columna.setPreferredWidth(200);
        columna = jXTable1.getColumn("Estado");
        columna.setPreferredWidth(200);
        
        jXTable1.setAutoCreateRowSorter(true);
        jXTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jXTable1.setColumnControlVisible(true); 
        jXFindBar1.setSearchable(jXTable1.getSearchable());
        jXTable1.setColumnControlVisible(true);
        TableRowFilterSupport.forTable(jXTable1).searchable(true).apply();
        
        jXTable1.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
           elementoSeleccionadoTabla = jXTable1.getSelectedRow();
           jXTable1.setRowSelectionInterval(jXTable1.rowAtPoint(e.getPoint()), jXTable1.rowAtPoint(e.getPoint()));
        }
        });
        actualizarInfoDocumentos(Utiles.msgTareaCargandoDocumentos);
    }//GEN-LAST:event_formWindowOpened

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        if (jXTable1.getSelectedRow() != -1){
            new TareaSegundoPlano(this, Utiles.msgTareaProcesandoDocumento) {
                @Override
                protected void tareaHaRealizar() {
                    elementoSeleccionadoTabla = jXTable1.getSelectedRow();
                    documentoSeleccionado = listaDocumentos.get(elementoSeleccionadoTabla);
                    limpiarComponentesFormulario();
                    eNombreDocumento.setText(documentoSeleccionado.getNombre());
                    jXTable1.clearSelection();
                    File archivo = new File(Utiles.rutaEnviadosVidaLaboral.concat("\\").concat(documentoSeleccionado.getNombre()));
                        try {
                            Desktop.getDesktop().open(archivo);
                        }catch (IOException ex) {
                        }
                    }
                }.ejecutarTarea();
            }else JOptionPane.showMessageDialog(null,"Debe seleccionar un documento de la lista.");
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
         actualizarInfoDocumentos(Utiles.msgTareaCargandoDocumentos);
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        if (jXTable1.getSelectedRow() != -1){
            new TareaSegundoPlano(this, Utiles.msgTareaProcesandoDocumento) {
                @Override
                protected void tareaHaRealizar() {
                    elementoSeleccionadoTabla = jXTable1.getSelectedRow();
                    documentoSeleccionado = listaDocumentos.get(elementoSeleccionadoTabla);
                    File archivo = new File(Utiles.rutaEnviadosVidaLaboral.concat(documentoSeleccionado.getNombre()));
                    archivo.renameTo(new File(Utiles.rutaEnviadosDocumentosKO.concat(documentoSeleccionado.getNombre())));
                    listaDocumentos.remove(elementoSeleccionadoTabla);
                    DefaultTableModel modelo = (DefaultTableModel)jXTable1.getModel();
                    modelo.removeRow(elementoSeleccionadoTabla);
                    jXTable1.setModel(modelo);
                    limpiarComponentesFormulario();
                }
            }.ejecutarTarea();
        }else JOptionPane.showMessageDialog(null,"Debe seleccionar un documento de la lista.");
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
            java.util.logging.Logger.getLogger(FormaProcesarVidaLaboral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaProcesarVidaLaboral dialog = new FormaProcesarVidaLaboral(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bConvertir;
    private javax.swing.JTextField eCodCEA;
    private org.openswing.swing.client.DateControl eFechaAltaSS;
    private org.openswing.swing.client.DateControl eFechaAltaUltimaEmpresa;
    private org.openswing.swing.client.DateControl eFechaCEA;
    private org.openswing.swing.client.DateControl eFechaDocumento;
    private javax.swing.JTextField eIdCEA;
    private javax.swing.JTextField eNif;
    private javax.swing.JTextField eNombreDocumento;
    private javax.swing.JTextField eNombreTitular;
    private javax.swing.JTextField eNumeroAnnosTotal;
    private javax.swing.JTextField eNumeroSeguridadSocial;
    private javax.swing.JComboBox<String> eTipoContrato;
    private javax.swing.JComboBox<String> eTipoRegimen;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXFindBar jXFindBar1;
    private org.jdesktop.swingx.JXTable jXTable1;
    // End of variables declaration//GEN-END:variables
}
