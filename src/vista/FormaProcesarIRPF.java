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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
public class FormaProcesarIRPF extends javax.swing.JDialog {

    Vector modelEstadoCivil;
    Vector modelProvincia;
    Vector modelViviendaHabitual;
    Vector modelOpcionTributaria;
    final int filas = 20;
    ArrayList<Documento> listaDocumentos;
    int elementoSeleccionadoTabla = -1;
    Documento documentoSeleccionado;

    /**
     * Creates new form FormaProcesarIRPF
     *
     * @param parent
     * @param modal
     */
    public FormaProcesarIRPF(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        RestrictedTextField restriccionCodigoPostal = new RestrictedTextField(eCodigoPostal);
        restriccionCodigoPostal.setLimit(5);
        //restriccionCodigoPostal.setOnlyNums(true);

        RestrictedTextField restriccionPersonasCargo = new RestrictedTextField(ePersonasCargo);
        restriccionPersonasCargo.setLimit(15);
        restriccionPersonasCargo.setOnlyNums(true);

        RestrictedTextField restriccionNif = new RestrictedTextField(eNif);
        restriccionNif.setLimit(10);

        eFecha.setFormat(Resources.DMY);
        eFecha.setName("FECHA");
        eFecha.setNextFocusableComponent(eNif);
    }

    /**
     *
     * @param TipoCombo
     */
    private void llenarCombo(String TipoCombo) {
        switch (TipoCombo) {
            case "Estado Civil":
                modelEstadoCivil = new Vector();
                modelEstadoCivil.addElement(new ModelCombo("Soltera", "Soltera"));
                modelEstadoCivil.addElement(new ModelCombo("Casada", "Casada"));
                modelEstadoCivil.addElement(new ModelCombo("Viuda", "Viuda"));
                modelEstadoCivil.addElement(new ModelCombo("Divorciada", "Divorciada"));
                eEstadoCivil.setModel(new DefaultComboBoxModel(modelEstadoCivil));
                break;
            case "Provincia":
                modelProvincia = new Vector();
                modelProvincia.addElement(new ModelCombo("SIN_ASIGNAR", "SIN_ASIGNAR"));
                modelProvincia.addElement(new ModelCombo("ALAVA", "ALAVA"));
                modelProvincia.addElement(new ModelCombo("ALBACETE", "ALBACETE"));
                modelProvincia.addElement(new ModelCombo("ALICANTE", "ALICANTE"));
                modelProvincia.addElement(new ModelCombo("ALMERIA", "ALMERIA"));
                modelProvincia.addElement(new ModelCombo("AVILA", "AVILA"));
                modelProvincia.addElement(new ModelCombo("BADAJOZ", "BADAJOZ"));
                modelProvincia.addElement(new ModelCombo("BALEARES", "BALEARES"));
                modelProvincia.addElement(new ModelCombo("BARCELONA", "BARCELONA"));
                modelProvincia.addElement(new ModelCombo("BURGOS", "BURGOS"));
                modelProvincia.addElement(new ModelCombo("CACERES", "CACERES"));
                modelProvincia.addElement(new ModelCombo("CADIZ", "CADIZ"));
                modelProvincia.addElement(new ModelCombo("CASTELLON", "CASTELLON"));
                modelProvincia.addElement(new ModelCombo("CIUDAD_REAL", "CIUDAD_REAL"));
                modelProvincia.addElement(new ModelCombo("CORDOBA", "CORDOBA"));
                modelProvincia.addElement(new ModelCombo("A_CORUÑA", "A_CORUÑA"));
                modelProvincia.addElement(new ModelCombo("CUENCA", "CUENCA"));
                modelProvincia.addElement(new ModelCombo("GIRONA", "GIRONA"));
                modelProvincia.addElement(new ModelCombo("GRANADA", "GRANADA"));
                modelProvincia.addElement(new ModelCombo("GUADALAJARA", "GUADALAJARA"));
                modelProvincia.addElement(new ModelCombo("GUIPUZCOA", "GUIPUZCOA"));
                modelProvincia.addElement(new ModelCombo("HUELVA", "HUELVA"));
                modelProvincia.addElement(new ModelCombo("HUESCA", "HUESCA"));
                modelProvincia.addElement(new ModelCombo("JAEN", "JAEN"));
                modelProvincia.addElement(new ModelCombo("LEON", "LEON"));
                modelProvincia.addElement(new ModelCombo("LLEIDA", "LLEIDA"));
                modelProvincia.addElement(new ModelCombo("LA_RIOJA", "LA_RIOJA"));
                modelProvincia.addElement(new ModelCombo("LUGO", "LUGO"));
                modelProvincia.addElement(new ModelCombo("MADRID", "MADRID"));
                modelProvincia.addElement(new ModelCombo("MALAGA", "MALAGA"));
                modelProvincia.addElement(new ModelCombo("MURCIA", "MURCIA"));
                modelProvincia.addElement(new ModelCombo("NAVARRA", "NAVARRA"));
                modelProvincia.addElement(new ModelCombo("OURENSE", "OURENSE"));
                modelProvincia.addElement(new ModelCombo("ASTURIAS", "ASTURIAS"));
                modelProvincia.addElement(new ModelCombo("PALENCIA", "PALENCIA"));
                modelProvincia.addElement(new ModelCombo("LAS_PALMAS", "LAS_PALMAS"));
                modelProvincia.addElement(new ModelCombo("PONTEVEDRA", "PONTEVEDRA"));
                modelProvincia.addElement(new ModelCombo("SALAMANCA", "SALAMANCA"));
                modelProvincia.addElement(new ModelCombo("SC_TENERIFE", "SC_TENERIFE"));
                modelProvincia.addElement(new ModelCombo("CANTABRIA", "CANTABRIA"));
                modelProvincia.addElement(new ModelCombo("SEGOVIA", "SEGOVIA"));
                modelProvincia.addElement(new ModelCombo("SEVILLA", "SEVILLA"));
                modelProvincia.addElement(new ModelCombo("SORIA", "SORIA"));
                modelProvincia.addElement(new ModelCombo("TARRAGONA", "TARRAGONA"));
                modelProvincia.addElement(new ModelCombo("TERUEL", "TERUEL"));
                modelProvincia.addElement(new ModelCombo("TOLEDO", "TOLEDO"));
                modelProvincia.addElement(new ModelCombo("VALENCIA", "VALENCIA"));
                modelProvincia.addElement(new ModelCombo("VALLADOLID", "VALLADOLID"));
                modelProvincia.addElement(new ModelCombo("VIZCAYA", "VIZCAYA"));
                modelProvincia.addElement(new ModelCombo("ZAMORA", "ZAMORA"));
                modelProvincia.addElement(new ModelCombo("ZARAGOZA", "ZARAGOZA"));
                modelProvincia.addElement(new ModelCombo("CEUTA", "CEUTA"));
                modelProvincia.addElement(new ModelCombo("MELILLA", "MELILLA"));

                eProvincia.setModel(new DefaultComboBoxModel(modelProvincia));
                break;
            case "Vivienda habitual":
                modelViviendaHabitual = new Vector();
                modelViviendaHabitual.addElement(new ModelCombo("Propietario", "1:Propietario"));
                modelViviendaHabitual.addElement(new ModelCombo("Usufructuario", "2:Usufructuario"));
                modelViviendaHabitual.addElement(new ModelCombo("Arrendatario", "3:Arrendatario"));
                modelViviendaHabitual.addElement(new ModelCombo("OtraSituacion", "4:OtraSituacion"));
                eViviendaHabitual.setModel(new DefaultComboBoxModel(modelViviendaHabitual));
                break;
            case "Opción tributaria":
                modelOpcionTributaria = new Vector();
                modelOpcionTributaria.addElement(new ModelCombo("Individual", "Individual"));
                modelOpcionTributaria.addElement(new ModelCombo("Conjunta", "Conjunta"));
                eOpcionTributacion.setModel(new DefaultComboBoxModel(modelOpcionTributaria));
                break;
        }
    }

    /**
     *
     */
    private void limpiarComponentesFormulario() {
        eAportaciones.setText("");
        eBaseImponibleAhorro.setText("");
        eBaseImponibleGeneral.setText("");
        eNif.setText("");
        eNombreDocumento.setText("");
        ePersonasCargo.setText("");
        eRendimientoNeto.setText("");
        eResultadoDeclaracion.setText("");
        eRetenciones.setText("");
        eCodigoPostal.setText("");
        eNombreTitular.setText("");

        //Combo
        eEstadoCivil.setSelectedIndex(0);
        eProvincia.setSelectedIndex(0);
        eOpcionTributacion.setSelectedIndex(0);
        eViviendaHabitual.setSelectedIndex(0);

        //Fechas
        Date fecha = null;
        eFecha.setDate(fecha);
    }

    /**
     *
     * @return
     */
    public static ArrayList<Documento> cargarListaDocumentos() {
        ArrayList<Documento> listaDocumentos = new ArrayList<>();
        String caminoDirectorioRaiz = Utiles.rutaEnviadosIRPF;
        File dirRaiz = new File(caminoDirectorioRaiz);
        String archivos[] = dirRaiz.list();
        File dirTemp;
        Documento documento;
        for (String archivo : archivos) {
            dirTemp = new File(caminoDirectorioRaiz.concat(archivo));
            documento = new Documento(dirTemp.getName(), "IRPF", "Pendiente de procesar", "", "", "");
            listaDocumentos.add(documento);
        }
        return listaDocumentos;
    }

    /**
     *
     * @param mensaje
     */
    private void actualizarInfoDocumentos(String mensaje) {
        new TareaSegundoPlano(this, mensaje) {
            @Override
            protected void tareaHaRealizar() {
                listaDocumentos = cargarListaDocumentos();
                Utiles.llenarTabla(jXTable1, listaDocumentos, "Documentos IRPF");
                Dimension dimension = jXTable1.getPreferredSize();
                jScrollPane1.setPreferredSize(new Dimension(dimension.width, jXTable1.getRowHeight() * filas));
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

        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        eAportaciones = new javax.swing.JTextField();
        eBaseImponibleGeneral = new javax.swing.JTextField();
        eBaseImponibleAhorro = new javax.swing.JTextField();
        eCodigoPostal = new javax.swing.JTextField();
        eEstadoCivil = new javax.swing.JComboBox<>();
        eFecha = new org.openswing.swing.client.DateControl();
        eOpcionTributacion = new javax.swing.JComboBox<>();
        ePersonasCargo = new javax.swing.JTextField();
        eProvincia = new javax.swing.JComboBox<>();
        eViviendaHabitual = new javax.swing.JComboBox<>();
        eRendimientoNeto = new javax.swing.JTextField();
        eRetenciones = new javax.swing.JTextField();
        bConvertir = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        eNombreDocumento = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        eNif = new javax.swing.JTextField();
        eResultadoDeclaracion = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        eNombreTitular = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jXFindBar1 = new org.jdesktop.swingx.JXFindBar();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jLabel23.setText("600");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extracción datos IRPF");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información IRPF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Aportaciones:");

        jLabel2.setText("Base imponible ahorro:");

        jLabel3.setText("Base imponible general:");

        jLabel4.setText("Código postal:");

        jLabel5.setText("Estado civil:");

        jLabel6.setText("Fecha:");

        jLabel7.setText("Opción tributación:");

        jLabel8.setText("Personas a cargo:");

        jLabel9.setText("Provincia:");

        jLabel10.setText("Relación vivienda habitual:");

        jLabel11.setText("Rendimiento neto inmueble arrendado: ");

        jLabel12.setText("Retenciones por rendimiento trabajo: ");

        eAportaciones.setName("APORTACIONES."); // NOI18N
        eAportaciones.setNextFocusableComponent(eBaseImponibleAhorro);
        eAportaciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eAportacionesFocusLost(evt);
            }
        });
        eAportaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eAportacionesKeyTyped(evt);
            }
        });

        eBaseImponibleGeneral.setToolTipText("");
        eBaseImponibleGeneral.setName("BASE_IMPONIBLE_GENERAL"); // NOI18N
        eBaseImponibleGeneral.setNextFocusableComponent(eCodigoPostal);
        eBaseImponibleGeneral.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eBaseImponibleGeneralFocusLost(evt);
            }
        });
        eBaseImponibleGeneral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eBaseImponibleGeneralKeyTyped(evt);
            }
        });

        eBaseImponibleAhorro.setName("BASE_IMPONIBLE_AHORRO"); // NOI18N
        eBaseImponibleAhorro.setNextFocusableComponent(eBaseImponibleGeneral);
        eBaseImponibleAhorro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eBaseImponibleAhorroFocusLost(evt);
            }
        });
        eBaseImponibleAhorro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eBaseImponibleAhorroKeyTyped(evt);
            }
        });

        eCodigoPostal.setName("CP"); // NOI18N
        eCodigoPostal.setNextFocusableComponent(eEstadoCivil);

        eEstadoCivil.setName("ESTADO_CIVIL"); // NOI18N
        eEstadoCivil.setNextFocusableComponent(eFecha);

        eOpcionTributacion.setName("OPCION_TRIBUTACION"); // NOI18N
        eOpcionTributacion.setNextFocusableComponent(ePersonasCargo);

        ePersonasCargo.setName("PERSONAS_CARGO."); // NOI18N
        ePersonasCargo.setNextFocusableComponent(eProvincia);

        eProvincia.setName("PROVINCIA"); // NOI18N
        eProvincia.setNextFocusableComponent(eViviendaHabitual);

        eViviendaHabitual.setName("VIVIENDA_HABITUA"); // NOI18N
        eViviendaHabitual.setNextFocusableComponent(eRendimientoNeto);

        eRendimientoNeto.setName("RENDIMIENTO_NETO."); // NOI18N
        eRendimientoNeto.setNextFocusableComponent(eResultadoDeclaracion);
        eRendimientoNeto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eRendimientoNetoFocusLost(evt);
            }
        });
        eRendimientoNeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eRendimientoNetoActionPerformed(evt);
            }
        });
        eRendimientoNeto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eRendimientoNetoKeyTyped(evt);
            }
        });

        eRetenciones.setName("RENDIMIENTO_TRABAJO."); // NOI18N
        eRetenciones.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eRetencionesFocusLost(evt);
            }
        });
        eRetenciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eRetencionesKeyTyped(evt);
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

        jLabel14.setText("413");

        jLabel15.setText("405");

        jLabel16.setText("392");

        jLabel17.setText("27");

        jLabel18.setText("074");

        jLabel19.setText("538");

        jLabel20.setText("NIF:");

        eNif.setName("NIF"); // NOI18N
        eNif.setNextFocusableComponent(eOpcionTributacion);

        eResultadoDeclaracion.setName("RESULTADO_DECLARACION"); // NOI18N
        eResultadoDeclaracion.setNextFocusableComponent(eRetenciones);
        eResultadoDeclaracion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eResultadoDeclaracionFocusLost(evt);
            }
        });
        eResultadoDeclaracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eResultadoDeclaracionKeyTyped(evt);
            }
        });

        jLabel21.setText("Resultado declaración:");

        jLabel22.setText("600");

        jLabel24.setText("01");

        jLabel25.setText("76");

        jLabel26.setText("50");

        jLabel27.setText("68 ó 69");

        jLabel28.setText("06 al 09");

        jLabel29.setText("29");

        jLabel30.setText("Nombre titular:");

        eNombreTitular.setName("NOMBRE_TITULAR"); // NOI18N
        eNombreTitular.setNextFocusableComponent(eAportaciones);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(112, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eNombreDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel12)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(eRetenciones)
                            .addComponent(eNif)
                            .addComponent(eAportaciones, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eBaseImponibleGeneral, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eBaseImponibleAhorro, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eEstadoCivil, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                            .addComponent(eOpcionTributacion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ePersonasCargo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eProvincia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eViviendaHabitual, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eRendimientoNeto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eCodigoPostal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eResultadoDeclaracion)
                            .addComponent(eNombreTitular)))
                    .addComponent(bConvertir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel22)
                    .addComponent(jLabel19)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(eNombreDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(eNombreTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(eAportaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(eBaseImponibleAhorro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(eBaseImponibleGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(eCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(eEstadoCivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(eFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(eNif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(eOpcionTributacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ePersonasCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(eProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(eViviendaHabitual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(eRendimientoNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(eResultadoDeclaracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eRetenciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bConvertir)
                .addContainerGap())
        );

        eAportaciones.getAccessibleContext().setAccessibleName("Real");
        eBaseImponibleGeneral.getAccessibleContext().setAccessibleName("Real");
        eBaseImponibleAhorro.getAccessibleContext().setAccessibleName("Real");
        eRendimientoNeto.getAccessibleContext().setAccessibleName("Real");
        eRetenciones.getAccessibleContext().setAccessibleName("Real");
        eResultadoDeclaracion.getAccessibleContext().setAccessibleName("Real");

        jButton2.setText("Cargar documento");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(8, 8, 8)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap(507, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(156, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eAportacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eAportacionesKeyTyped
        Utiles.validarNumeroReal(evt, eAportaciones, 9, 2);
    }//GEN-LAST:event_eAportacionesKeyTyped

    private void eBaseImponibleGeneralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eBaseImponibleGeneralKeyTyped
        Utiles.validarNumeroReal(evt, eBaseImponibleGeneral, 9, 2);
    }//GEN-LAST:event_eBaseImponibleGeneralKeyTyped

    private void eBaseImponibleAhorroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eBaseImponibleAhorroKeyTyped
        Utiles.validarNumeroReal(evt, eBaseImponibleAhorro, 9, 2);
    }//GEN-LAST:event_eBaseImponibleAhorroKeyTyped

    private void eRendimientoNetoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eRendimientoNetoKeyTyped
        Utiles.validarNumeroReal(evt, eRendimientoNeto, 9, 2);
    }//GEN-LAST:event_eRendimientoNetoKeyTyped

    private void eRetencionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eRetencionesKeyTyped
        Utiles.validarNumeroReal(evt, eRetenciones, 9, 2);
    }//GEN-LAST:event_eRetencionesKeyTyped

    private void bConvertirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConvertirMousePressed
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea Convertir?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0) {
            JComponent comp = Utiles.ValidaControles(jPanel2);
            if (comp == null) {
                if (Utiles.validarFecha(eFecha)) {
                    if (eNif.getText().length() <= 10) {
                        new TareaSegundoPlano(this, Utiles.msgTareaRealizandoConversion) {
                            @Override
                            protected void tareaHaRealizar() {
                                File archivo = new File(Utiles.rutaEnviadosIRPF.concat(eNombreDocumento.getText()));
                                Boolean movido = archivo.renameTo(new File(Utiles.rutaProcesadosIRPFPDF.concat(eNombreDocumento.getText())));
                                if (movido) {
                                    Utiles.generarXMLOtroTipoDocumento(eNombreDocumento);
                                    listaDocumentos.remove(elementoSeleccionadoTabla);
                                    DefaultTableModel modelo = (DefaultTableModel) jXTable1.getModel();
                                    modelo.removeRow(elementoSeleccionadoTabla);
                                    jXTable1.setModel(modelo);
                                    limpiarComponentesFormulario();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Debe cerrar el documento PDF antes de convertir.");
                                }
                            }
                        }.ejecutarTarea();
                    } else {
                        JOptionPane.showMessageDialog(null, "NIF incorrecto, a lo sumo debe tener 10 caracteres.");
                        eNif.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Fecha incorrecta.");
                    eFecha.requestFocus();
                }
            } else {

                if (comp instanceof JTextField) 
                {
                    String valorReal = "";
                    if (((JTextField) comp).getAccessibleContext().getAccessibleName() != null) {
                        valorReal = " o no se acepta el caracter \".\"";
                    }

                    JOptionPane.showMessageDialog(null, Utiles.msgDebeIntroducir + comp.getName() + valorReal);
                    comp.requestFocus();
                }
                else if (comp instanceof DateControl && ((DateControl) comp).getValue() != null) 
                {
                    JOptionPane.showMessageDialog(null, Utiles.msgFechaIncorrecta + comp.getName());
                    comp.requestFocus();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, Utiles.msgDebeIntroducir + comp.getName());
                    comp.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_bConvertirMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        llenarCombo("Estado Civil");
        llenarCombo("Provincia");
        llenarCombo("Vivienda habitual");
        llenarCombo("Opción tributaria");

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

        jXTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                elementoSeleccionadoTabla = jXTable1.getSelectedRow();
                jXTable1.setRowSelectionInterval(jXTable1.rowAtPoint(e.getPoint()), jXTable1.rowAtPoint(e.getPoint()));

            }
        });

        actualizarInfoDocumentos(Utiles.msgTareaCargandoDocumentos);
    }//GEN-LAST:event_formWindowOpened

    private void eResultadoDeclaracionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eResultadoDeclaracionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_eResultadoDeclaracionKeyTyped

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        if (jXTable1.getSelectedRow() != -1) {
            new TareaSegundoPlano(this, Utiles.msgTareaProcesandoDocumento) {
                @Override
                protected void tareaHaRealizar() {
                    elementoSeleccionadoTabla = jXTable1.getSelectedRow();
                    documentoSeleccionado = listaDocumentos.get(elementoSeleccionadoTabla);
                    limpiarComponentesFormulario();
                    eNombreDocumento.setText(documentoSeleccionado.getNombre());
                    jXTable1.clearSelection();
                    File archivo = new File(Utiles.rutaEnviadosIRPF.concat("\\").concat(documentoSeleccionado.getNombre()));
                    try {
                        Desktop.getDesktop().open(archivo);
                    } catch (IOException ex) {
                    }
                }
            }.ejecutarTarea();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un documento de la lista.");
        }
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        actualizarInfoDocumentos(Utiles.msgTareaCargandoDocumentos);
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        if (jXTable1.getSelectedRow() != -1) {
            new TareaSegundoPlano(this, Utiles.msgTareaProcesandoDocumento) {
                @Override
                protected void tareaHaRealizar() {
                    elementoSeleccionadoTabla = jXTable1.getSelectedRow();
                    documentoSeleccionado = listaDocumentos.get(elementoSeleccionadoTabla);
                    File archivo = new File(Utiles.rutaEnviadosIRPF.concat(documentoSeleccionado.getNombre()));
                    archivo.renameTo(new File(Utiles.rutaEnviadosDocumentosKO.concat(documentoSeleccionado.getNombre())));
                    listaDocumentos.remove(elementoSeleccionadoTabla);
                    DefaultTableModel modelo = (DefaultTableModel) jXTable1.getModel();
                    modelo.removeRow(elementoSeleccionadoTabla);
                    jXTable1.setModel(modelo);
                    limpiarComponentesFormulario();
                }
            }.ejecutarTarea();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un documento de la lista.");
        }
    }//GEN-LAST:event_jButton3MousePressed

    private void eAportacionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eAportacionesFocusLost
        if (!eAportaciones.getText().equals("")) {
            if (eAportaciones.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eAportaciones.requestFocus();
            }
        }
    }//GEN-LAST:event_eAportacionesFocusLost

    private void eBaseImponibleAhorroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eBaseImponibleAhorroFocusLost
        if (!eBaseImponibleAhorro.getText().equals("")) {
            if (eBaseImponibleAhorro.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eBaseImponibleAhorro.requestFocus();
            }
        }
    }//GEN-LAST:event_eBaseImponibleAhorroFocusLost

    private void eBaseImponibleGeneralFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eBaseImponibleGeneralFocusLost
        if (!eBaseImponibleGeneral.getText().equals("")) {
            if (eBaseImponibleGeneral.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eBaseImponibleGeneral.requestFocus();
            }
        }
    }//GEN-LAST:event_eBaseImponibleGeneralFocusLost

    private void eRendimientoNetoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eRendimientoNetoFocusLost
        if (!eRendimientoNeto.getText().equals("")) {
            if (eRendimientoNeto.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eRendimientoNeto.requestFocus();
            }
        }
    }//GEN-LAST:event_eRendimientoNetoFocusLost

    private void eRendimientoNetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eRendimientoNetoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eRendimientoNetoActionPerformed

    private void eResultadoDeclaracionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eResultadoDeclaracionFocusLost
        if (!eResultadoDeclaracion.getText().equals("")) {
            if (eResultadoDeclaracion.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eResultadoDeclaracion.requestFocus();
            }
        }
    }//GEN-LAST:event_eResultadoDeclaracionFocusLost

    private void eRetencionesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eRetencionesFocusLost
        if (!eRetenciones.getText().equals("")) {
            if (eRetenciones.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eRetenciones.requestFocus();
            }
        }
    }//GEN-LAST:event_eRetencionesFocusLost

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
            java.util.logging.Logger.getLogger(FormaProcesarIRPF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaProcesarIRPF dialog = new FormaProcesarIRPF(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField eAportaciones;
    private javax.swing.JTextField eBaseImponibleAhorro;
    private javax.swing.JTextField eBaseImponibleGeneral;
    private javax.swing.JTextField eCodigoPostal;
    private javax.swing.JComboBox<String> eEstadoCivil;
    private org.openswing.swing.client.DateControl eFecha;
    private javax.swing.JTextField eNif;
    private javax.swing.JTextField eNombreDocumento;
    private javax.swing.JTextField eNombreTitular;
    private javax.swing.JComboBox<String> eOpcionTributacion;
    private javax.swing.JTextField ePersonasCargo;
    private javax.swing.JComboBox<String> eProvincia;
    private javax.swing.JTextField eRendimientoNeto;
    private javax.swing.JTextField eResultadoDeclaracion;
    private javax.swing.JTextField eRetenciones;
    private javax.swing.JComboBox<String> eViviendaHabitual;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
