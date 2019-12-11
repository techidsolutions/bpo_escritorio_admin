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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import modelo.Documento;
import modelo.ModelCombo;
import org.jdesktop.swingx.JXTable;
import org.openswing.swing.internationalization.java.Resources;
import org.oxbow.swingbits.table.filter.TableRowFilterSupport;
import util.TareaSegundoPlano;
import util.Utiles;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaProcesarNomina extends javax.swing.JDialog {
    Vector modelGrupoCotizacion;
    final int filas = 20;
    ArrayList<Documento> listaDocumentos;
    int elementoSeleccionadoTabla = -1;
    Documento documentoSeleccionado;
    
    /**
     * Creates new form FormaProcesarIRPF
     * @param parent
     * @param modal
     */
    public FormaProcesarNomina(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        RestrictedTextField restriccionNif = new RestrictedTextField(eNIF);
        restriccionNif.setLimit(10);
        
        eFecha.setFormat(Resources.DMY);
        eFecha.setName("FECHA");
        eFecha.setNextFocusableComponent(eFechaAntiguedad);
        
        eFechaAntiguedad.setFormat(Resources.DMY);
        eFechaAntiguedad.setName("FECHA_ANTIGUEDAD.");
        eFechaAntiguedad.setNextFocusableComponent(eGrupoCotizacion);
    }
    
    /**
     * 
     * @param TipoCombo 
     */
    private void llenarCombo(String TipoCombo){
        switch(TipoCombo){
            case "Grupo Cotizacion":modelGrupoCotizacion = new Vector();
                                modelGrupoCotizacion.addElement( new ModelCombo("SinGrupo", "SinGrupo" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo1_Ingenieros_Licenciados", "Grupo1_Ingenieros_Licenciados" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo2_Ingenieros_Tecnicos_Peritos_AyudantesTitulados", "Grupo2_Ingenieros_Tecnicos_Peritos_AyudantesTitulados" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo3_Jefes_Administrativos_deTaller", "Grupo3_Jefes_Administrativos_deTaller" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo4_AyudantesNoTitulados", "Grupo4_AyudantesNoTitulados" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo5_Oficiales_Administrativos", "Grupo5_Oficiales_Administrativos" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo6_Subalternos", "Grupo6_Subalternos" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo7_AuxiliaresAdministrativos", "Grupo7_AuxiliaresAdministrativos" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo8_OficialesPrimera_Segunda", "Grupo8_OficialesPrimera_Segunda" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo9_OficialesTercera_Especialistas", "Grupo9_OficialesTercera_Especialistas" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo10_Peones", "Grupo10_Peones" ) );
                                modelGrupoCotizacion.addElement( new ModelCombo("Grupo11_Menores18", "Grupo11_Menores18" ) );
                                eGrupoCotizacion.setModel(new DefaultComboBoxModel(modelGrupoCotizacion));
                                break; 
        }
    }
    
    /**
     * 
     */
    private void limpiarComponentesFormulario(){
        eBaseImponible.setText("");
        eCNAE.setText("");
        eContingenciasComunes.setText("");
        eEmpresa.setText("");
        eNombreDocumento.setText("");
        eImporteRecibir.setText("");
        eNIF.setText("");
        eNumeroSeguridadSocial.setText("");
        eRetencion.setText("");
        eRetencionSeguridadSocial.setText("");
        eTipoIRPF.setText("");
        eNombreTitular.setText("");
                
        //Combo
        eGrupoCotizacion.setSelectedIndex(0); 

        //Fechas
        Date fecha = null;
        eFecha.setDate(fecha);
        eFechaAntiguedad.setDate(fecha);
    }
    
    /**
     * 
     * @return 
     */
    public static ArrayList<Documento> cargarListaDocumentos(){
        ArrayList<Documento> listaDocumentos = new ArrayList<>();
        String caminoDirectorioRaiz = Utiles.rutaEnviadosNomina;
	File dirRaiz = new File(caminoDirectorioRaiz);
        String archivos[] = dirRaiz.list();
        File dirTemp;
        Documento documento;
        for (String archivo : archivos) {
            dirTemp = new File(caminoDirectorioRaiz.concat(archivo));
            documento = new Documento(dirTemp.getName(), "Nómina", "Pendiente de procesar", "", "", "");
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
        jLabel6 = new javax.swing.JLabel();
        eBaseImponible = new javax.swing.JTextField();
        eFecha = new org.openswing.swing.client.DateControl();
        bConvertir = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        eNombreDocumento = new javax.swing.JTextField();
        eCNAE = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        eContingenciasComunes = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        eEmpresa = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        eFechaAntiguedad = new org.openswing.swing.client.DateControl();
        jLabel3 = new javax.swing.JLabel();
        eImporteRecibir = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        eNumeroSeguridadSocial = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        eRetencion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        eRetencionSeguridadSocial = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        eTipoIRPF = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        eGrupoCotizacion = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        eNIF = new javax.swing.JTextField();
        eNombreTitular = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jXFindBar1 = new org.jdesktop.swingx.JXFindBar();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extracción datos Nómina");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información Nómina", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Base imponible:");

        jLabel6.setText("Fecha:");

        eBaseImponible.setName("BASE_IMPONIBLE"); // NOI18N
        eBaseImponible.setNextFocusableComponent(eCNAE);
        eBaseImponible.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eBaseImponibleFocusLost(evt);
            }
        });
        eBaseImponible.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eBaseImponibleKeyTyped(evt);
            }
        });

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

        eCNAE.setName("CNAE."); // NOI18N
        eCNAE.setNextFocusableComponent(eContingenciasComunes);

        jLabel14.setText("CNAE:");

        jLabel2.setText("Contingencias comunes:");

        eContingenciasComunes.setName("CONTINGENCIAS_COMUNES."); // NOI18N
        eContingenciasComunes.setNextFocusableComponent(eEmpresa);
        eContingenciasComunes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eContingenciasComunesFocusLost(evt);
            }
        });
        eContingenciasComunes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eContingenciasComunesKeyTyped(evt);
            }
        });

        jLabel16.setText("Empresa:");

        eEmpresa.setName("EMPRESA"); // NOI18N
        eEmpresa.setNextFocusableComponent(eFecha);

        jLabel7.setText("Fecha antiguedad:");

        jLabel3.setText("Importe a recibir:");

        eImporteRecibir.setName("IMPORTE_RECIBIR"); // NOI18N
        eImporteRecibir.setNextFocusableComponent(eNIF);
        eImporteRecibir.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eImporteRecibirFocusLost(evt);
            }
        });
        eImporteRecibir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eImporteRecibirKeyTyped(evt);
            }
        });

        jLabel17.setText("Número de seguridad social:");

        eNumeroSeguridadSocial.setName("NUMERO_SEGURIDAD_SOCIAL."); // NOI18N
        eNumeroSeguridadSocial.setNextFocusableComponent(eRetencion);

        jLabel4.setText("Retención:");

        eRetencion.setName("RETENCION"); // NOI18N
        eRetencion.setNextFocusableComponent(eRetencionSeguridadSocial);
        eRetencion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eRetencionFocusLost(evt);
            }
        });
        eRetencion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eRetencionKeyTyped(evt);
            }
        });

        jLabel5.setText("Retención seguridad social:");

        eRetencionSeguridadSocial.setName("RETENCION_SEGURIDAD_SOCIAL."); // NOI18N
        eRetencionSeguridadSocial.setNextFocusableComponent(eTipoIRPF);
        eRetencionSeguridadSocial.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eRetencionSeguridadSocialFocusLost(evt);
            }
        });
        eRetencionSeguridadSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eRetencionSeguridadSocialKeyTyped(evt);
            }
        });

        jLabel8.setText("Tipo IRPF:");

        eTipoIRPF.setName("TIPO_IRPF"); // NOI18N
        eTipoIRPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eTipoIRPFFocusLost(evt);
            }
        });
        eTipoIRPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eTipoIRPFKeyTyped(evt);
            }
        });

        jLabel9.setText("Grupo cotización:");

        eGrupoCotizacion.setName("GRUPO_COTIZACION"); // NOI18N
        eGrupoCotizacion.setNextFocusableComponent(eImporteRecibir);

        jLabel15.setText("NIF:");

        eNIF.setName("NIF"); // NOI18N
        eNIF.setNextFocusableComponent(eNumeroSeguridadSocial);

        eNombreTitular.setName("NOMBRE_TITULAR"); // NOI18N
        eNombreTitular.setNextFocusableComponent(eBaseImponible);

        jLabel18.setText("Nombre titular:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eGrupoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(eBaseImponible, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(eCNAE, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(eContingenciasComunes, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(eEmpresa, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(eFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                        .addComponent(eFechaAntiguedad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eNombreDocumento)
                        .addComponent(eNombreTitular))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bConvertir)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(eTipoIRPF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(eRetencionSeguridadSocial, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eRetencion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eNumeroSeguridadSocial, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eNIF, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eImporteRecibir, javax.swing.GroupLayout.Alignment.LEADING)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(eNombreDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(eNombreTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(eBaseImponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(eCNAE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(eContingenciasComunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(eEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(eFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(eFechaAntiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eGrupoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(eImporteRecibir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(eNIF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(eNumeroSeguridadSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(eRetencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(eRetencionSeguridadSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(eTipoIRPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bConvertir))
        );

        eBaseImponible.getAccessibleContext().setAccessibleName("Real");
        eContingenciasComunes.getAccessibleContext().setAccessibleName("Real");
        eImporteRecibir.getAccessibleContext().setAccessibleName("Real");
        eRetencion.getAccessibleContext().setAccessibleName("Real");
        eRetencionSeguridadSocial.getAccessibleContext().setAccessibleName("Real");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap(616, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3))))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eBaseImponibleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eBaseImponibleKeyTyped
        Utiles.validarNumeroReal(evt, eBaseImponible, 9, 2);
    }//GEN-LAST:event_eBaseImponibleKeyTyped

    private void bConvertirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConvertirMousePressed
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea Convertir?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0){
            JComponent comp = Utiles.ValidaControles(jPanel2);
            if (comp == null){
                if (Utiles.validarFecha(eFecha) && Utiles.validarFecha(eFechaAntiguedad)){
                    if (eNIF.getText().length() <= 10){
                        new TareaSegundoPlano(this, Utiles.msgTareaRealizandoConversion) {
                            @Override
                            protected void tareaHaRealizar() {
                                File archivo = new File(Utiles.rutaEnviadosNomina.concat(eNombreDocumento.getText()));
                                Boolean movido = archivo.renameTo(new File(Utiles.rutaProcesadosNominaPDF.concat(eNombreDocumento.getText())));
                                if (movido){
                                    Utiles.generarXMLNomina(eNombreDocumento);
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
                            eNIF.requestFocus();
                    } 
                }else {
                    JOptionPane.showMessageDialog(null,"Fecha incorrecta.");
            
                }    
            }else {
                 String valorReal = "";
                if(comp instanceof JTextField){
                    if (((JTextField)comp).getAccessibleContext().getAccessibleName() != null)
                        valorReal = " o no se acepta el caracter \".\""; 

                } 
                JOptionPane.showMessageDialog(null,Utiles.msgDebeIntroducir + comp.getName() + valorReal);
                comp.requestFocus();
            }
        }
    }//GEN-LAST:event_bConvertirMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        llenarCombo("Grupo Cotizacion");
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

    private void eContingenciasComunesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eContingenciasComunesKeyTyped
        Utiles.validarNumeroReal(evt, eContingenciasComunes, 9, 2);
    }//GEN-LAST:event_eContingenciasComunesKeyTyped

    private void eImporteRecibirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eImporteRecibirKeyTyped
        Utiles.validarNumeroReal(evt, eImporteRecibir, 9, 2);
    }//GEN-LAST:event_eImporteRecibirKeyTyped

    private void eRetencionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eRetencionKeyTyped
        Utiles.validarNumeroReal(evt, eRetencion, 9, 2);
    }//GEN-LAST:event_eRetencionKeyTyped

    private void eRetencionSeguridadSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eRetencionSeguridadSocialKeyTyped
       Utiles.validarNumeroReal(evt, eRetencionSeguridadSocial, 9, 2);
    }//GEN-LAST:event_eRetencionSeguridadSocialKeyTyped

    private void eTipoIRPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eTipoIRPFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_eTipoIRPFKeyTyped

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
                    File archivo = new File(Utiles.rutaEnviadosNomina.concat("\\").concat(documentoSeleccionado.getNombre()));
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
                    File archivo = new File(Utiles.rutaEnviadosNomina.concat(documentoSeleccionado.getNombre()));
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

    private void eBaseImponibleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eBaseImponibleFocusLost
        if (!eBaseImponible.getText().equals(""))
            if (eBaseImponible.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eBaseImponible.requestFocus();
            }
    }//GEN-LAST:event_eBaseImponibleFocusLost

    private void eContingenciasComunesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eContingenciasComunesFocusLost
        if (!eContingenciasComunes.getText().equals(""))
            if (eContingenciasComunes.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eContingenciasComunes.requestFocus();
            }
    }//GEN-LAST:event_eContingenciasComunesFocusLost

    private void eImporteRecibirFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eImporteRecibirFocusLost
        if (!eImporteRecibir.getText().equals(""))
            if (eImporteRecibir.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eImporteRecibir.requestFocus();
            }
    }//GEN-LAST:event_eImporteRecibirFocusLost

    private void eRetencionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eRetencionFocusLost
        if (!eRetencion.getText().equals(""))
            if (eRetencion.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eRetencion.requestFocus();
            }
    }//GEN-LAST:event_eRetencionFocusLost

    private void eRetencionSeguridadSocialFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eRetencionSeguridadSocialFocusLost
        if (!eRetencionSeguridadSocial.getText().equals(""))
            if (eRetencionSeguridadSocial.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eRetencionSeguridadSocial.requestFocus();
            }
    }//GEN-LAST:event_eRetencionSeguridadSocialFocusLost

    private void eTipoIRPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eTipoIRPFFocusLost
        if (!eTipoIRPF.getText().equals(""))
            if (eTipoIRPF.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eTipoIRPF.requestFocus();
            }
    }//GEN-LAST:event_eTipoIRPFFocusLost

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
            java.util.logging.Logger.getLogger(FormaProcesarNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaProcesarNomina dialog = new FormaProcesarNomina(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField eBaseImponible;
    private javax.swing.JTextField eCNAE;
    private javax.swing.JTextField eContingenciasComunes;
    private javax.swing.JTextField eEmpresa;
    private org.openswing.swing.client.DateControl eFecha;
    private org.openswing.swing.client.DateControl eFechaAntiguedad;
    private javax.swing.JComboBox<String> eGrupoCotizacion;
    private javax.swing.JTextField eImporteRecibir;
    private javax.swing.JTextField eNIF;
    private javax.swing.JTextField eNombreDocumento;
    private javax.swing.JTextField eNombreTitular;
    private javax.swing.JTextField eNumeroSeguridadSocial;
    private javax.swing.JTextField eRetencion;
    private javax.swing.JTextField eRetencionSeguridadSocial;
    private javax.swing.JTextField eTipoIRPF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
