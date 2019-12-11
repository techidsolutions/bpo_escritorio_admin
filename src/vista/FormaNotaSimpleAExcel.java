/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ComponenteFormulario;
import modelo.ModelCombo;
import org.openswing.swing.internationalization.java.Resources;
import util.General;
import util.TareaSegundoPlano;
import util.Utiles;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaNotaSimpleAExcel extends javax.swing.JDialog {
    private ArrayList<ArrayList<ComponenteFormulario>> listaTitulares; 
    private final ArrayList<ArrayList<ComponenteFormulario>> listaCargas; 
    Vector modelTipoFinca;
    Vector modelTipoVia;
    Vector modelEstadoSolicitud;
    Vector modelVPO;
    Vector modelGaraje;
    Vector modelTrastero;
    Vector modelTerraza;
    Vector modelRegimen;
    Vector modelTipoParticipacion;
    Vector modelAnnoConstruccion;
    Vector modelProvincia;
    java.awt.Frame parentF; 
    Vector modelTipoDocumento;

    public  ArrayList<ArrayList<ComponenteFormulario>> getListaTitulares() {
        return listaTitulares;
    }

    public void setListaTitulares(ArrayList<ArrayList<ComponenteFormulario>> listaTitulares) {
        this.listaTitulares = listaTitulares;
    }

    public ArrayList<ArrayList<ComponenteFormulario>> getListaCargas() {
        return listaCargas;
    }
    
    /**
     * Creates new form FormaNotaSimpleAExcel
     * @param parent
     * @param modal
     */
    public FormaNotaSimpleAExcel(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.parentF = parent;
        eFechaVerificación.setFormat(Resources.DMY);
        eFechaVerificación.setName("FECHA_VERRIF_REG.");
        eFechaVerificación.setNextFocusableComponent(eIdufir);
        eFechaCalificacion.setFormat(Resources.DMY);
        eFechaCalificacion.setName("FECHA_CALIFICACION.");
        eFechaCalificacion.setNextFocusableComponent(ePrimerApellido);
        eFechaEscritura.setFormat(Resources.DMY);
        eFechaEscritura.setName("TLO_FECHA_ESCRITURA.");
        eFechaEscritura.setNextFocusableComponent(eNumeroInscripcion);
        eFechaInscripcionTitular.setFormat(Resources.DMY);
        eFechaInscripcionTitular.setName("TLO_FECHA_INSCRIPCION.");
        eFechaInscripcionTitular.setNextFocusableComponent(null);
        llenarComboTipoInmueble("Tipo finca");
        llenarComboTipoInmueble("Provincia");
        llenarComboTipoInmueble("Tipo via");
        llenarComboTipoInmueble("VPO");
        llenarComboTipoInmueble("Garaje");
        llenarComboTipoInmueble("Trastero"); 
        llenarComboTipoInmueble("Terraza");
        llenarComboTipoInmueble("Regimen");
        llenarComboTipoInmueble("Tipo de participacion");
        listaTitulares = new ArrayList<>();
        listaCargas = new ArrayList<>();
    }
    
    /**
     * 
     * @param TipoCombo 
     */
    private void llenarComboTipoInmueble(String TipoCombo){
        switch(TipoCombo){
            case "VPO":         modelVPO = new Vector();
                                modelVPO.addElement( new ModelCombo("N", "N" ) );
                                modelVPO.addElement( new ModelCombo("S", "S" ) );
                                eVPO.setModel(new DefaultComboBoxModel(modelVPO));
                                break;
            case "Garaje":      modelGaraje = new Vector();
                                modelGaraje.addElement( new ModelCombo("N", "N" ) );
                                modelGaraje.addElement( new ModelCombo("S", "S" ) );
                                eGaraje.setModel(new DefaultComboBoxModel(modelGaraje));
                                break;
            case "Trastero":    modelTrastero = new Vector();
                                modelTrastero.addElement( new ModelCombo("N", "N" ) );
                                modelTrastero.addElement( new ModelCombo("S", "S" ) );
                                eTrastero.setModel(new DefaultComboBoxModel(modelTrastero));
                                break;
            case "Terraza":     modelTerraza = new Vector();
                                modelTerraza.addElement( new ModelCombo("N", "N" ) );
                                modelTerraza.addElement( new ModelCombo("S", "S" ) );
                                eTerraza.setModel(new DefaultComboBoxModel(modelTerraza));
                                break;
            case "Regimen":     modelRegimen = new Vector();
                                modelRegimen.addElement( new ModelCombo("Privativo", "Privativo" ) );
                                modelRegimen.addElement( new ModelCombo("Ganancial", "Ganancial" ) );
                                modelRegimen.addElement( new ModelCombo("Participación en gananciales", "Participación en gananciales" ) );
                                eRegimen.setModel(new DefaultComboBoxModel(modelRegimen));
                                break;
            case "Tipo de participacion":modelTipoParticipacion = new Vector();
                                modelTipoParticipacion.addElement( new ModelCombo("Pleno dominio", "Pleno dominio" ) );
                                modelTipoParticipacion.addElement( new ModelCombo("Uso o usufructo", "Uso o usufructo" ) );
                                modelTipoParticipacion.addElement( new ModelCombo("Propiedad", "Propiedad" ) );
                                eTipoParticipacion.setModel(new DefaultComboBoxModel(modelTipoParticipacion));
                                break; 
            case "Provincia":modelProvincia = new Vector();
                                modelProvincia.addElement( new ModelCombo("NO INFORMADO", "NO INFORMADO" ) );
                                modelProvincia.addElement( new ModelCombo("ALAVA", "ALAVA" ) );
                                modelProvincia.addElement( new ModelCombo("ALBACETE", "ALBACETE" ) );
                                modelProvincia.addElement( new ModelCombo("ALICANTE", "ALICANTE" ) );
                                modelProvincia.addElement( new ModelCombo("ALMERIA", "ALMERIA" ) );
                                modelProvincia.addElement( new ModelCombo("AVILA", "AVILA" ) );
                                modelProvincia.addElement( new ModelCombo("BADAJOZ", "BADAJOZ" ) );
                                modelProvincia.addElement( new ModelCombo("BALEARES", "BALEARES" ) );
                                modelProvincia.addElement( new ModelCombo("BARCELONA", "BARCELONA" ) );
                                modelProvincia.addElement( new ModelCombo("BURGOS", "BURGOS" ) );
                                modelProvincia.addElement( new ModelCombo("CACERES", "CACERES" ) );
                                modelProvincia.addElement( new ModelCombo("CADIZ", "CADIZ" ) );
                                modelProvincia.addElement( new ModelCombo("CASTELLON", "CASTELLON" ) );
                                modelProvincia.addElement( new ModelCombo("CIUDAD_REAL", "CIUDAD_REAL" ) );
                                modelProvincia.addElement( new ModelCombo("CORDOBA", "CORDOBA" ) );
                                modelProvincia.addElement( new ModelCombo("A_CORUNA", "A_CORUNA" ) );
                                modelProvincia.addElement( new ModelCombo("CUENCA", "CUENCA" ) );
                                modelProvincia.addElement( new ModelCombo("GIRONA", "GIRONA" ) );
                                modelProvincia.addElement( new ModelCombo("GRANADA", "GRANADA" ) );
                                modelProvincia.addElement( new ModelCombo("GUADALAJARA", "GUADALAJARA" ) );
                                modelProvincia.addElement( new ModelCombo("GUIPUZCOA", "GUIPUZCOA" ) );
                                modelProvincia.addElement( new ModelCombo("HUELVA", "HUELVA" ) );
                                modelProvincia.addElement( new ModelCombo("HUESCA", "HUESCA" ) );
                                modelProvincia.addElement( new ModelCombo("JAEN", "JAEN" ) );
                                modelProvincia.addElement( new ModelCombo("LEON", "LEON" ) );
                                modelProvincia.addElement( new ModelCombo("LLEIDA", "LLEIDA" ) );
                                modelProvincia.addElement( new ModelCombo("LA_RIOJA", "LA_RIOJA" ) );
                                modelProvincia.addElement( new ModelCombo("LUGO", "LUGO" ) );
                                modelProvincia.addElement( new ModelCombo("MADRID", "MADRID" ) );
                                modelProvincia.addElement( new ModelCombo("MALAGA", "MALAGA" ) );
                                modelProvincia.addElement( new ModelCombo("MURCIA", "MURCIA" ) );
                                modelProvincia.addElement( new ModelCombo("NAVARRA", "NAVARRA" ) );
                                modelProvincia.addElement( new ModelCombo("OURENSE", "OURENSE" ) );
                                modelProvincia.addElement( new ModelCombo("ASTURIAS", "ASTURIAS" ) );
                                modelProvincia.addElement( new ModelCombo("PALENCIA", "PALENCIA" ) );
                                modelProvincia.addElement( new ModelCombo("LAS_PALMAS", "LAS_PALMAS" ) );
                                modelProvincia.addElement( new ModelCombo("PONTEVEDRA", "PONTEVEDRA" ) );
                                modelProvincia.addElement( new ModelCombo("SALAMANCA", "SALAMANCA" ) );
                                modelProvincia.addElement( new ModelCombo("SC_TENERIFE", "SC_TENERIFE" ) );
                                modelProvincia.addElement( new ModelCombo("CANTABRIA", "CANTABRIA" ) );
                                modelProvincia.addElement( new ModelCombo("SEGOVIA", "SEGOVIA" ) );
                                modelProvincia.addElement( new ModelCombo("SEVILLA", "SEVILLA" ) );
                                modelProvincia.addElement( new ModelCombo("SORIA", "SORIA" ) );
                                modelProvincia.addElement( new ModelCombo("TARRAGONA", "TARRAGONA" ) );
                                modelProvincia.addElement( new ModelCombo("TERUEL", "TERUEL" ) );
                                modelProvincia.addElement( new ModelCombo("TOLEDO", "TOLEDO" ) );
                                modelProvincia.addElement( new ModelCombo("VALENCIA", "VALENCIA" ) );
                                modelProvincia.addElement( new ModelCombo("VALLADOLID", "VALLADOLID" ) );
                                modelProvincia.addElement( new ModelCombo("VIZCAYA", "VIZCAYA" ) );
                                modelProvincia.addElement( new ModelCombo("ZAMORA", "ZAMORA" ) );
                                modelProvincia.addElement( new ModelCombo("ZARAGOZA", "ZARAGOZA" ) );
                                modelProvincia.addElement( new ModelCombo("CEUTA", "CEUTA" ) );
                                modelProvincia.addElement( new ModelCombo("MELILLA", "MELILLA" ) );
                                eProvincia.setModel(new DefaultComboBoxModel(modelProvincia));
                                break;  
        }
    }
    
    /**
     * 
     */
    private void limpiarComponentesFormulario(){
        eReferencia.setText("");
        eIdAsesor.setText("");
        eIdufir.setText("");
        eReferenciaCatastral.setText("");
        ePlazaRegistro.setText("");
        eMunicipio.setText("");
        eNumeroVia.setText("");
        eBloque.setText("");
        eCalle.setText("");
        eKm.setText("");
        ePuerta.setText("");
        eEscalera.setText("");
        ePiso.setText("");
        eCodigoPostal.setText("");
        eNumeroRegistro.setText("");
        eSeccion.setText("");
        eNumeroFinca.setText("");
        eSubfinca.setText("");
        eTomo.setText("");
        eLibro.setText("");
        eFolio.setText("");
        eSuperficieConstruida.setText("");
        eSuperficieUtil.setText("");
        eSuperficieTerreno.setText("");
        eSuperficieGaraje.setText("");
        eSuperficieTrastero.setText("");
        eSuperficieTerraza.setText("");
        eNombreRazon.setText("");
        ePrimerApellido.setText("");
        eSegundoApellido.setText("");
        eDocumento.setText("");
        eTituloPropiedad.setText("");
        eNumeroProtocolo.setText("");
        eNotario.setText("");
        eNumeroInscripcion.setText("");
        ePorcientoParticipacion.setText("");
        eCuota.setText("");
        eTipoFinca.setText("");
        eTipoVia.setText("");
        
        //Combo
        eProvincia.setSelectedIndex(0); 
        eVPO.setSelectedIndex(0); 
        eRegimen.setSelectedIndex(0); 
        eTipoParticipacion.setSelectedIndex(0); 
        eGaraje.setSelectedIndex(0); 
        eTrastero.setSelectedIndex(0); 
        eTerraza.setSelectedIndex(0); 

        //Fechas
        Date fecha = null;
        eFechaVerificación.setDate(fecha);
        eFechaCalificacion.setDate(fecha);
        eFechaInscripcionTitular.setDate(fecha);
        eFechaEscritura.setDate(fecha);
        listaTitulares.clear();
        listaCargas.clear();
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        modelo.setRowCount(0);
    }
    
    /**
     * 
     */
    private void limpiarComponentes(){
        eReferencia.setText("");
        eIdAsesor.setText("");
        listaTitulares.clear();
        listaCargas.clear();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        eFechaVerificación = new org.openswing.swing.client.DateControl();
        jLabel8 = new javax.swing.JLabel();
        eIdufir = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        eReferenciaCatastral = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        eNumeroRegistro = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        eSeccion = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        eFolio = new javax.swing.JTextField();
        eNumeroFinca = new javax.swing.JTextField();
        eSubfinca = new javax.swing.JTextField();
        eTomo = new javax.swing.JTextField();
        eLibro = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        ePlazaRegistro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        eReferencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        eIdAsesor = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        eNombreRazon = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        ePrimerApellido = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        eSegundoApellido = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        eDocumento = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        eRegimen = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        eTituloPropiedad = new javax.swing.JTextField();
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
        eTipoParticipacion = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonAdicionarTitular = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCargas = new javax.swing.JButton();
        jButtonEliminarTitular = new javax.swing.JButton();
        jButtonModificarTitular = new javax.swing.JButton();
        ePorcientoParticipacion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        eCodigoPostal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        eVPO = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        eFechaCalificacion = new org.openswing.swing.client.DateControl();
        jLabel30 = new javax.swing.JLabel();
        eSuperficieConstruida = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        eSuperficieUtil = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        eSuperficieTerreno = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        eProvincia = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        eMunicipio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        eNumeroVia = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        eBloque = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        eCalle = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ePuerta = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        eEscalera = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        eKm = new javax.swing.JTextField();
        ePiso = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        eCuota = new javax.swing.JTextField();
        eGaraje = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        eSuperficieGaraje = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        eTrastero = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        eSuperficieTrastero = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        eTerraza = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        eSuperficieTerraza = new javax.swing.JTextField();
        eTipoFinca = new javax.swing.JTextField();
        eTipoVia = new javax.swing.JTextField();
        jButtonLimpiar = new javax.swing.JButton();
        jButtonLimpiar1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nota Simple a Excel");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos registrales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel6.setText("Fecha verificación:");

        jLabel8.setText("IDUFIR:");

        eIdufir.setName("IDUFIR."); // NOI18N
        eIdufir.setNextFocusableComponent(eReferenciaCatastral);

        jLabel9.setText("Referencia catastral:");

        eReferenciaCatastral.setName("REF_CATASTRAL."); // NOI18N
        eReferenciaCatastral.setNextFocusableComponent(ePlazaRegistro);

        jLabel21.setText("Número registro:");

        eNumeroRegistro.setName("REGISTRO_PROPIEDAD."); // NOI18N
        eNumeroRegistro.setNextFocusableComponent(eNumeroFinca);

        jLabel22.setText("Sección:");

        eSeccion.setName("RG_SECCION."); // NOI18N
        eSeccion.setNextFocusableComponent(eTipoFinca);

        jLabel23.setText("Número finca:");

        jLabel24.setText("Subfinca:");

        jLabel25.setText("Tomo:");

        jLabel26.setText("Libro:");

        jLabel27.setText("Folio:");

        eFolio.setName("RG_FOLIO."); // NOI18N
        eFolio.setNextFocusableComponent(eSeccion);

        eNumeroFinca.setName("RG_FINCA."); // NOI18N
        eNumeroFinca.setNextFocusableComponent(eSubfinca);

        eSubfinca.setName("RG_SUBFINCA."); // NOI18N
        eSubfinca.setNextFocusableComponent(eTomo);

        eTomo.setName("RG_TOMO."); // NOI18N
        eTomo.setNextFocusableComponent(eLibro);
        eTomo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eTomoKeyTyped(evt);
            }
        });

        eLibro.setName("RG_LIBRO."); // NOI18N
        eLibro.setNextFocusableComponent(eFolio);

        jLabel33.setText("Plaza registro:");

        ePlazaRegistro.setName("DIRECCION"); // NOI18N
        ePlazaRegistro.setNextFocusableComponent(eNumeroRegistro);

        jLabel2.setText("Referencia:");

        eReferencia.setFocusCycleRoot(true);
        eReferencia.setName("NOMBRE_DOCUMENTO"); // NOI18N
        eReferencia.setNextFocusableComponent(eIdAsesor);

        jLabel3.setText("Id asesor:");

        eIdAsesor.setName("REFERENCE"); // NOI18N
        eIdAsesor.setNextFocusableComponent(eFechaVerificación);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(eReferenciaCatastral, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eIdufir, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eFechaVerificación, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ePlazaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eNumeroRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eNumeroFinca, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(1, 1, 1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel26)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel27))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(eSubfinca)
                                    .addComponent(eTomo)
                                    .addComponent(eLibro)
                                    .addComponent(eFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eReferencia, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(eIdAsesor))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(eReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(eIdAsesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaVerificación, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(eIdufir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(eReferenciaCatastral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel33)
                            .addComponent(ePlazaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNumeroRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNumeroFinca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(eSubfinca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eTomo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(eLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)))))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del titular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel34.setText("Nomb/Razón Social:");

        eNombreRazon.setName("TIT_NOMBRE."); // NOI18N
        eNombreRazon.setNextFocusableComponent(eDocumento);

        jLabel35.setText("Primer apellido:");

        ePrimerApellido.setName("TIT_APELLIDO1."); // NOI18N
        ePrimerApellido.setNextFocusableComponent(eSegundoApellido);

        jLabel36.setText("Segundo apellido:");

        eSegundoApellido.setName("TIT_APELLIDO2."); // NOI18N
        eSegundoApellido.setNextFocusableComponent(eNombreRazon);

        jLabel37.setText("NIF/CIF titular:");

        eDocumento.setName("TIT_DOC_IDENTIDAD."); // NOI18N
        eDocumento.setNextFocusableComponent(eRegimen);

        jLabel38.setText("Régimen:");

        eRegimen.setName("TIT_REGIMEN."); // NOI18N
        eRegimen.setNextFocusableComponent(ePorcientoParticipacion);

        jLabel39.setText("Título propiedad:");

        eTituloPropiedad.setName("TLO_PROPIEDAD."); // NOI18N
        eTituloPropiedad.setNextFocusableComponent(eNotario);

        jLabel40.setText("Fecha escritura:");

        jLabel41.setText("Número protocolo:");

        eNumeroProtocolo.setName("TLO_PROTOCOLO."); // NOI18N
        eNumeroProtocolo.setNextFocusableComponent(eFechaEscritura);

        jLabel42.setText("Notario:");

        eNotario.setName("TLO_NOTARIA."); // NOI18N
        eNotario.setNextFocusableComponent(eNumeroProtocolo);

        jLabel43.setText("Número inscripción:");

        eNumeroInscripcion.setName("TLO_NUM_INSCRIPCION."); // NOI18N
        eNumeroInscripcion.setNextFocusableComponent(eFechaInscripcionTitular);

        jLabel44.setText("Fecha inscripción:");

        jLabel45.setText("Tipo de participación:");

        eTipoParticipacion.setName("TLO_TIPO_PARTICIPACION."); // NOI18N
        eTipoParticipacion.setNextFocusableComponent(eTituloPropiedad);

        jLabel46.setText("% de Partic. titular:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "NIF/CIF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButtonAdicionarTitular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButtonAdicionarTitular.setText(" Titular");
        jButtonAdicionarTitular.setToolTipText("Adicionar titular");
        jButtonAdicionarTitular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAdicionarTitularMousePressed(evt);
            }
        });

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar1.png"))); // NOI18N
        jButtonGuardar.setText("Guardar");
        jButtonGuardar.setToolTipText("Guardar datos formulario");
        jButtonGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonGuardarMousePressed(evt);
            }
        });

        jButtonCargas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report_fac_g.png"))); // NOI18N
        jButtonCargas.setText("Cargas");
        jButtonCargas.setToolTipText("Gestionar cargas");
        jButtonCargas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonCargasMousePressed(evt);
            }
        });

        jButtonEliminarTitular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButtonEliminarTitular.setText(" Titular");
        jButtonEliminarTitular.setToolTipText("Eliminar titular");
        jButtonEliminarTitular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonEliminarTitularMousePressed(evt);
            }
        });

        jButtonModificarTitular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        jButtonModificarTitular.setText("Titular");
        jButtonModificarTitular.setToolTipText("Modificar titular");
        jButtonModificarTitular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonModificarTitularMousePressed(evt);
            }
        });

        ePorcientoParticipacion.setName("TLO_PORC_PARTICIPACION."); // NOI18N
        ePorcientoParticipacion.setNextFocusableComponent(eTipoParticipacion);
        ePorcientoParticipacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ePorcientoParticipacionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ePorcientoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel34)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(eNombreRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel35)
                                .addComponent(jLabel36))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eSegundoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ePrimerApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(27, 27, 27)
                                .addComponent(eDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eRegimen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eTipoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jButtonGuardar))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eFechaInscripcionTitular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eNumeroInscripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eFechaEscritura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eNotario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eNumeroProtocolo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eTituloPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButtonModificarTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEliminarTitular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonCargas)
                            .addComponent(jButtonAdicionarTitular)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(199, 199, 199))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                            .addComponent(jLabel34))
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
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eTipoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eTituloPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eNotario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel42))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eNumeroProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel41))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                    .addComponent(jLabel44)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonModificarTitular, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonAdicionarTitular)
                                        .addComponent(jButtonEliminarTitular)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCargas)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jButtonGuardar)))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de la finca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        eCodigoPostal.setName("FINCA_CP."); // NOI18N
        eCodigoPostal.setNextFocusableComponent(eProvincia);

        jLabel19.setText("Código postal:");

        jLabel28.setText("VPO:");

        eVPO.setName("REGIMEN_PROTECCION"); // NOI18N
        eVPO.setNextFocusableComponent(eFechaCalificacion);

        jLabel29.setText("Fecha calificación:");

        jLabel30.setText("Superficie construida:");

        eSuperficieConstruida.setName("SUPERF_CONSTRUIDA."); // NOI18N
        eSuperficieConstruida.setNextFocusableComponent(eSuperficieUtil);
        eSuperficieConstruida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieConstruidaKeyTyped(evt);
            }
        });

        jLabel31.setText("Superficie útil:");

        eSuperficieUtil.setName("SUPERF_UTIL."); // NOI18N
        eSuperficieUtil.setNextFocusableComponent(eSuperficieTerreno);
        eSuperficieUtil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieUtilKeyTyped(evt);
            }
        });

        jLabel32.setText("Superficie terreno:");

        eSuperficieTerreno.setName("SUPERF_TERRENO."); // NOI18N
        eSuperficieTerreno.setNextFocusableComponent(eCuota);
        eSuperficieTerreno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieTerrenoKeyTyped(evt);
            }
        });

        jLabel48.setText("Provincia:");

        eProvincia.setName("PROVINCIA."); // NOI18N
        eProvincia.setNextFocusableComponent(eSuperficieConstruida);
        eProvincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eProvinciaActionPerformed(evt);
            }
        });

        jLabel49.setText("Tipo de finca:");

        jLabel12.setText("Tipo de vía:");

        jLabel13.setText("Municipio:");

        eMunicipio.setName("FINCA_NOMBRE."); // NOI18N
        eMunicipio.setNextFocusableComponent(eCodigoPostal);

        jLabel14.setText("Número:");

        eNumeroVia.setName("FINCA_NUMERO."); // NOI18N
        eNumeroVia.setNextFocusableComponent(eKm);

        jLabel15.setText("Bloque:");

        eBloque.setName("FINCA_PORTAL."); // NOI18N
        eBloque.setNextFocusableComponent(eEscalera);

        jLabel16.setText("Calle:");

        eCalle.setName("FINCA_PLANTA."); // NOI18N
        eCalle.setNextFocusableComponent(eNumeroVia);

        jLabel17.setText("Puerta:");

        ePuerta.setName("FINCA_PUERTA."); // NOI18N
        ePuerta.setNextFocusableComponent(eMunicipio);

        jLabel18.setText("Escalera:");

        eEscalera.setName("FINCA_ESCALERA."); // NOI18N
        eEscalera.setNextFocusableComponent(ePiso);

        jLabel20.setText("Km:");

        eKm.setName("FINCA_NUMERO."); // NOI18N
        eKm.setNextFocusableComponent(eBloque);

        ePiso.setName("FINCA_ESCALERA."); // NOI18N
        ePiso.setNextFocusableComponent(ePuerta);

        jLabel47.setText("Piso:");

        jLabel51.setText("Cuota(%):");

        eCuota.setName("SUPERF_TERRENO."); // NOI18N
        eCuota.setNextFocusableComponent(eGaraje);
        eCuota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eCuotaKeyTyped(evt);
            }
        });

        eGaraje.setName("REGIMEN_PROTECCION"); // NOI18N
        eGaraje.setNextFocusableComponent(eSuperficieGaraje);

        jLabel52.setText("Garaje:");

        jLabel53.setText("Superficie garaje:");

        eSuperficieGaraje.setName("SUPERF_UTIL."); // NOI18N
        eSuperficieGaraje.setNextFocusableComponent(eTrastero);
        eSuperficieGaraje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieGarajeKeyTyped(evt);
            }
        });

        jLabel54.setText("Trastero:");

        eTrastero.setName("REGIMEN_PROTECCION"); // NOI18N
        eTrastero.setNextFocusableComponent(eSuperficieTrastero);

        jLabel55.setText("Superficie trastero:");

        eSuperficieTrastero.setName("SUPERF_UTIL."); // NOI18N
        eSuperficieTrastero.setNextFocusableComponent(eTerraza);
        eSuperficieTrastero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieTrasteroKeyTyped(evt);
            }
        });

        jLabel56.setText("Terraza:");

        eTerraza.setName("REGIMEN_PROTECCION"); // NOI18N
        eTerraza.setNextFocusableComponent(eSuperficieTerraza);

        jLabel57.setText("Superficie terraza:");

        eSuperficieTerraza.setName("SUPERF_UTIL."); // NOI18N
        eSuperficieTerraza.setNextFocusableComponent(eVPO);
        eSuperficieTerraza.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieTerrazaKeyTyped(evt);
            }
        });

        eTipoFinca.setName("TIPO_FINCA"); // NOI18N
        eTipoFinca.setNextFocusableComponent(eTipoVia);

        eTipoVia.setName("TIPO_VIA"); // NOI18N
        eTipoVia.setNextFocusableComponent(eCalle);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eKm, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eNumeroVia, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eEscalera, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel47)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ePiso, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ePuerta, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel48)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(eCodigoPostal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel32)
                                .addGap(174, 174, 174))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eTipoVia))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel49)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eTipoFinca, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(eSuperficieUtil, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(eSuperficieConstruida)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(eSuperficieTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel51)
                                            .addComponent(jLabel52)
                                            .addComponent(jLabel53))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eGaraje, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eSuperficieGaraje, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel54)
                                            .addComponent(jLabel55))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eTrastero, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eSuperficieTrastero, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eFechaCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eVPO, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eTerraza, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eSuperficieTerraza, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(43, 43, 43))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel30)
                    .addComponent(eSuperficieConstruida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eTipoFinca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(eSuperficieUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(eTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(eCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(eSuperficieTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eNumeroVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel51)
                    .addComponent(eCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel52)
                    .addComponent(eGaraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eBloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(eSuperficieGaraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(eEscalera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(ePiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(eTrastero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eSuperficieTrastero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ePuerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(eMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(eTerraza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eSuperficieTerraza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(eVPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eFechaCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(eProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel48)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 131, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(736, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel8);

        jButtonLimpiar.setText("Limpiar formulario");
        jButtonLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonLimpiarMousePressed(evt);
            }
        });

        jButtonLimpiar1.setText("Borrar excel");
        jButtonLimpiar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonLimpiar1MousePressed(evt);
            }
        });

        jButton1.setText("Cargar nota simple");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonLimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLimpiar))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLimpiar)
                    .addComponent(jButtonLimpiar1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdicionarTitularMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAdicionarTitularMousePressed
        FormaTitularExcel formaTitular = new FormaTitularExcel(null, true, 1, -1, this);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaTitular.setLocation(GEnv.getCenterPoint().x-formaTitular.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaTitular.getHeight()/2 + 25);
        formaTitular.setResizable(false);
        formaTitular.setVisible(true);
        Utiles.llenarTabla(jTable1,listaTitulares, "Titulares");
    }//GEN-LAST:event_jButtonAdicionarTitularMousePressed

    private void jButtonGuardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonGuardarMousePressed
        int option = JOptionPane.showConfirmDialog(null, "Realmente desea guardar la nota simple?", "Guardar nota simple", JOptionPane.YES_NO_OPTION);
        if (option == 0)
            new TareaSegundoPlano(this, "Guardando nota simple...") {
                        @Override
                        protected void tareaHaRealizar() {
                            General.writeExcel(eReferencia, listaTitulares, listaCargas);
                            limpiarComponentes();
                        }
                    }.ejecutarTarea();
    }//GEN-LAST:event_jButtonGuardarMousePressed

    private void jButtonCargasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCargasMousePressed
        FormaCargasExcel formaCargas = new FormaCargasExcel(parentF, true, this);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaCargas.setLocation(GEnv.getCenterPoint().x-formaCargas.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaCargas.getHeight()/2 + 25);
        formaCargas.setResizable(true);
        formaCargas.setVisible(true);
    }//GEN-LAST:event_jButtonCargasMousePressed

    private void jButtonEliminarTitularMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEliminarTitularMousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1){
            DefaultTableModel tabla = (DefaultTableModel)jTable1.getModel();
            tabla.removeRow(indiceSeleccionado);
            jTable1.setModel(tabla);
            listaTitulares.remove(indiceSeleccionado);
        } else
        JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneTitularEliminar);
    }//GEN-LAST:event_jButtonEliminarTitularMousePressed

    private void jButtonModificarTitularMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModificarTitularMousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1){
            FormaTitularExcel formaTitular = new FormaTitularExcel(null, true, 2,indiceSeleccionado, this);
            GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            formaTitular.setLocation(GEnv.getCenterPoint().x-formaTitular.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaTitular.getHeight()/2 + 25);
            formaTitular.setResizable(false);
            formaTitular.setVisible(true);
            Utiles.llenarTabla(jTable1,listaTitulares, "Titulares");
        } else JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneTitularModificar);
    }//GEN-LAST:event_jButtonModificarTitularMousePressed

    private void ePorcientoParticipacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ePorcientoParticipacionKeyTyped
        Utiles.validarNumeroReal(evt, ePorcientoParticipacion, 3, 2);
    }//GEN-LAST:event_ePorcientoParticipacionKeyTyped

    private void eTomoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eTomoKeyTyped

    }//GEN-LAST:event_eTomoKeyTyped

    private void eSuperficieConstruidaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eSuperficieConstruidaKeyTyped
        Utiles.validarNumeroReal(evt, eSuperficieConstruida, 9, 2);
    }//GEN-LAST:event_eSuperficieConstruidaKeyTyped

    private void eSuperficieUtilKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eSuperficieUtilKeyTyped
        Utiles.validarNumeroReal(evt, eSuperficieUtil, 9, 2);
    }//GEN-LAST:event_eSuperficieUtilKeyTyped

    private void eSuperficieTerrenoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eSuperficieTerrenoKeyTyped
        Utiles.validarNumeroReal(evt, eSuperficieTerreno, 9, 2);
    }//GEN-LAST:event_eSuperficieTerrenoKeyTyped

    private void eProvinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eProvinciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eProvinciaActionPerformed

    private void jButtonLimpiarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLimpiarMousePressed
        int option = JOptionPane.showConfirmDialog(null, "Realmente desea limpiar el formulario?", "Limpiar formulario", JOptionPane.YES_NO_OPTION);
        if (option == 0){
            limpiarComponentesFormulario();
        }
        
    }//GEN-LAST:event_jButtonLimpiarMousePressed

    private void eCuotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eCuotaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_eCuotaKeyTyped

    private void eSuperficieGarajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eSuperficieGarajeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_eSuperficieGarajeKeyTyped

    private void eSuperficieTrasteroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eSuperficieTrasteroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_eSuperficieTrasteroKeyTyped

    private void eSuperficieTerrazaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eSuperficieTerrazaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_eSuperficieTerrazaKeyTyped

    private void jButtonLimpiar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLimpiar1MousePressed
        int option = JOptionPane.showConfirmDialog(null, "Realmente desea borrar el documento Excel?", "Borrar documento Excel", JOptionPane.YES_NO_OPTION);
        if (option == 0)
            new TareaSegundoPlano(this, "Borrando documento excel...") {
                        @Override
                        protected void tareaHaRealizar() {
                            General.limpiarExcel();
                            limpiarComponentes();
                        }
                    }.ejecutarTarea();
    }//GEN-LAST:event_jButtonLimpiar1MousePressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            eReferencia.setText(file.getName().split("-{1}")[0]);
            eIdAsesor.setText(file.getName().split("\\.")[0]);
            try {
                Desktop.getDesktop().open(file);
            }catch (IOException ex) {
            }
        }
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
            java.util.logging.Logger.getLogger(FormaNotaSimpleAExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaNotaSimpleAExcel dialog = new FormaNotaSimpleAExcel(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField eBloque;
    private javax.swing.JTextField eCalle;
    private javax.swing.JTextField eCodigoPostal;
    private javax.swing.JTextField eCuota;
    private javax.swing.JTextField eDocumento;
    private javax.swing.JTextField eEscalera;
    private org.openswing.swing.client.DateControl eFechaCalificacion;
    private org.openswing.swing.client.DateControl eFechaEscritura;
    private org.openswing.swing.client.DateControl eFechaInscripcionTitular;
    private org.openswing.swing.client.DateControl eFechaVerificación;
    private javax.swing.JTextField eFolio;
    private javax.swing.JComboBox<String> eGaraje;
    private javax.swing.JTextField eIdAsesor;
    private javax.swing.JTextField eIdufir;
    private javax.swing.JTextField eKm;
    private javax.swing.JTextField eLibro;
    private javax.swing.JTextField eMunicipio;
    private javax.swing.JTextField eNombreRazon;
    private javax.swing.JTextField eNotario;
    private javax.swing.JTextField eNumeroFinca;
    private javax.swing.JTextField eNumeroInscripcion;
    private javax.swing.JTextField eNumeroProtocolo;
    private javax.swing.JTextField eNumeroRegistro;
    private javax.swing.JTextField eNumeroVia;
    private javax.swing.JTextField ePiso;
    private javax.swing.JTextField ePlazaRegistro;
    private javax.swing.JTextField ePorcientoParticipacion;
    private javax.swing.JTextField ePrimerApellido;
    private javax.swing.JComboBox<String> eProvincia;
    private javax.swing.JTextField ePuerta;
    private javax.swing.JTextField eReferencia;
    private javax.swing.JTextField eReferenciaCatastral;
    private javax.swing.JComboBox<String> eRegimen;
    private javax.swing.JTextField eSeccion;
    private javax.swing.JTextField eSegundoApellido;
    private javax.swing.JTextField eSubfinca;
    private javax.swing.JTextField eSuperficieConstruida;
    private javax.swing.JTextField eSuperficieGaraje;
    private javax.swing.JTextField eSuperficieTerraza;
    private javax.swing.JTextField eSuperficieTerreno;
    private javax.swing.JTextField eSuperficieTrastero;
    private javax.swing.JTextField eSuperficieUtil;
    private javax.swing.JComboBox<String> eTerraza;
    private javax.swing.JTextField eTipoFinca;
    private javax.swing.JComboBox<String> eTipoParticipacion;
    private javax.swing.JTextField eTipoVia;
    private javax.swing.JTextField eTituloPropiedad;
    private javax.swing.JTextField eTomo;
    private javax.swing.JComboBox<String> eTrastero;
    private javax.swing.JComboBox<String> eVPO;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdicionarTitular;
    private javax.swing.JButton jButtonCargas;
    private javax.swing.JButton jButtonEliminarTitular;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonLimpiar1;
    private javax.swing.JButton jButtonModificarTitular;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
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
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
