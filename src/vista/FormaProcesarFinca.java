/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.ComponenteFormulario;
import modelo.Finca;
import modelo.ModelCombo;
import modelo.Poblacion;
import modelo.Provincia;
import modelo.RegistroPropiedad;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.internationalization.java.Resources;
import util.ComboboxToolTipRenderer;
import util.TareaSegundoPlano;
import util.Utiles;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaProcesarFinca extends javax.swing.JDialog {

    FormaProcesarTasacion formaTasacion;
    private ArrayList<ArrayList<ComponenteFormulario>> listaTitulares;
    private ArrayList<ArrayList<ComponenteFormulario>> listaAnejos;
    private List<Poblacion> listaPoblaciones;
    private ArrayList<Provincia> listaProvincias;
    private List<RegistroPropiedad> listaRegistros;
    Vector modelTipoFinca;
    Vector modelTipoVia;
    Vector modelEstadoSolicitud;
    Vector modelVPO;
    Vector modelRegimen;
    Vector modelRegimenEconomico;
    Vector modelTipoParticipacion;
    Vector modelAnnoConstruccion;
    Vector modelProvincia;
    Vector modelTipoDocumento;
    Vector modelTipoInmueble;
    Vector modelSubTipoInmueble;
    Vector modelPoblacion;
    Vector modelTipoPropiedad;
    Vector modelRegistroPropiedad;
    Vector modelTipoTitulo;
    Vector modelOcupacion;
    Vector modelConservacion;

    private final Integer tipoLLamada;
    private Finca fincaModificar;

    /**
     * Creates new form FormaProcesarFinca
     *
     * @param parent
     * @param modal
     */
    public FormaProcesarFinca(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.tipoLLamada = null;
        RestrictedTextField restriccionCodigoPostal = new RestrictedTextField(eCodigoPostal);
        restriccionCodigoPostal.setLimit(5);
    }

    private void seleccionarElementoCombo(Vector modelo, JComboBox combo, String valor) {
        ModelCombo modeloCombo;
        int test = 9;
        for (int i = 0; i < modelo.size(); i++) {
            modeloCombo = (ModelCombo) modelo.elementAt(i);
            if (modeloCombo.getClave().equals(valor)) {
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void llenarCamposDocumentoGuardado(ArrayList<ComponenteFormulario> listaComponentesCargados) {
        eIdufir.setText(listaComponentesCargados.get(1).getValor());
        eReferenciaCatastral.setText(listaComponentesCargados.get(2).getValor());
        eNombreVia.setText(listaComponentesCargados.get(6).getValor());
        eBloqueOPortal.setText(listaComponentesCargados.get(7).getValor());
        ePlanta.setText(listaComponentesCargados.get(8).getValor());
        ePuerta.setText(listaComponentesCargados.get(9).getValor());
        eEscalera.setText(listaComponentesCargados.get(10).getValor());
        eCodigoPostal.setText(listaComponentesCargados.get(11).getValor());
        eSeccion.setText(listaComponentesCargados.get(12).getValor());
        eNumeroFinca.setText("");
        eNumeroFinca.setText(listaComponentesCargados.get(13).getValor());
        eSubfinca.setText(listaComponentesCargados.get(14).getValor());
        eTomo.setText(listaComponentesCargados.get(15).getValor());
        eLibro.setText(listaComponentesCargados.get(16).getValor());
        eFolio.setText(listaComponentesCargados.get(17).getValor());
        eSuperficieConstruida.setText(listaComponentesCargados.get(20).getValor());
        eSuperficieUtil.setText(listaComponentesCargados.get(21).getValor());
        eSuperficieTerreno.setText(listaComponentesCargados.get(22).getValor());
        eCoeficiente.setText(listaComponentesCargados.get(25).getValor());
        eValorSeguro.setText(listaComponentesCargados.get(28).getValor());
        eSuperficieComprobada.setText(listaComponentesCargados.get(30).getValor());
        eValorSuelo.setText(listaComponentesCargados.get(31).getValor());
        eValorTasacion.setText(listaComponentesCargados.get(32).getValor());
        eValoracion.setText(listaComponentesCargados.get(33).getValor());
        eValorHipotecario.setText(listaComponentesCargados.get(34).getValor());

        //Combo
        seleccionarElementoCombo(modelRegistroPropiedad, eRegistroPropiedad, listaComponentesCargados.get(3).getValor());
        seleccionarElementoCombo(modelSubTipoInmueble, eSubtipoInmueble, listaComponentesCargados.get(4).getValor());
        seleccionarElementoCombo(modelTipoVia, eTipoVia, listaComponentesCargados.get(5).getValor());
        seleccionarElementoCombo(modelRegimen, eRegimenProteccion, listaComponentesCargados.get(18).getValor());
        seleccionarElementoCombo(modelPoblacion, ePoblacion, listaComponentesCargados.get(23).getValor());
        seleccionarElementoCombo(modelProvincia, eProvincia, listaComponentesCargados.get(24).getValor());
        seleccionarElementoCombo(modelOcupacion, eOcupacion, listaComponentesCargados.get(26).getValor());
        seleccionarElementoCombo(modelConservacion, eEstadoConservacion, listaComponentesCargados.get(27).getValor());
        seleccionarElementoCombo(modelAnnoConstruccion, eAnnoConstruccion, listaComponentesCargados.get(29).getValor());

        //Fechas
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = null;
            if (!listaComponentesCargados.get(0).getValor().equals("")) {
                fecha = formato.parse(listaComponentesCargados.get(0).getValor());
                eFechaVerificación.setDate(fecha);
            }
            if (!listaComponentesCargados.get(19).getValor().equals("")) {
                fecha = formato.parse(listaComponentesCargados.get(19).getValor());
                eFechaCalificacion.setDate(fecha);
            }
        } catch (ParseException ex) {
            Logger.getLogger(FormaTitular.class.getName()).log(Level.SEVERE, null, ex);
        }

        Utiles.llenarTabla(jTable1, fincaModificar.getListaTitulares(), "Titulares");
        listaTitulares = fincaModificar.getListaTitulares();
        listaAnejos = fincaModificar.getListaAnejos();

    }

    public FormaProcesarFinca(java.awt.Frame parent, boolean modal, FormaProcesarTasacion formaTasacion, Integer tipoLLamada, Finca fincaModificar) {
        super(parent, modal);
        initComponents();
        this.formaTasacion = formaTasacion;
        this.tipoLLamada = tipoLLamada;
        this.fincaModificar = fincaModificar;

        RestrictedTextField restriccionIdufir = new RestrictedTextField(eIdufir);
        restriccionIdufir.setLimit(15);

        RestrictedTextField restriccionReferenciaCatastral = new RestrictedTextField(eReferenciaCatastral);
        restriccionReferenciaCatastral.setLimit(55);

        RestrictedTextField restriccionNombreVia = new RestrictedTextField(eNombreVia);
        restriccionNombreVia.setLimit(40);

        RestrictedTextField restriccionBloquePortal = new RestrictedTextField(eBloqueOPortal);
        restriccionBloquePortal.setLimit(5);

        RestrictedTextField restriccionCodigoPostal = new RestrictedTextField(eCodigoPostal);
        restriccionCodigoPostal.setLimit(5);

        RestrictedTextField restriccionPlanta = new RestrictedTextField(ePlanta);
        restriccionPlanta.setLimit(5);

        RestrictedTextField restriccionPuerta = new RestrictedTextField(ePuerta);
        restriccionPuerta.setLimit(5);

        RestrictedTextField restriccionEscalera = new RestrictedTextField(eEscalera);
        restriccionEscalera.setLimit(5);

        RestrictedTextField restriccionNumeroFinca = new RestrictedTextField(eNumeroFinca);
        restriccionNumeroFinca.setLimit(6);

        RestrictedTextField restriccionNumeroSubFinca = new RestrictedTextField(eSubfinca);
        restriccionNumeroSubFinca.setLimit(5);

        /*
         RestrictedTextField restriccionCodigoPostal = new RestrictedTextField(eCodigoPostal);
         restriccionCodigoPostal.setOnlyNums(true);
         restriccionCodigoPostal.setLimit(5);
        
         RestrictedTextField restriccionSeccion = new RestrictedTextField(eSeccion);
         restriccionSeccion.setOnlyNums(true);
         restriccionSeccion.setLimit(3);
        
         RestrictedTextField restriccionTomo = new RestrictedTextField(eTomo);
         restriccionTomo.setOnlyNums(true);
         restriccionTomo.setLimit(4);
        
         RestrictedTextField restriccionLibro = new RestrictedTextField(eLibro);
         restriccionLibro.setOnlyNums(true);
         restriccionLibro.setLimit(4);
        
         RestrictedTextField restriccionFolio = new RestrictedTextField(eFolio);
         restriccionFolio.setOnlyNums(true);
         restriccionFolio.setLimit(6);
         */
        eFechaVerificación.setFormat(Resources.DMY);
        eFechaVerificación.setName("FECHA_VERRIF_REG.");
        eFechaVerificación.setNextFocusableComponent(eIdufir);
        eFechaCalificacion.setFormat(Resources.DMY);
        eFechaCalificacion.setName("FECHA_CALIFICACION.");
        eFechaCalificacion.setNextFocusableComponent(eSuperficieConstruida);

        llenarCombo("Tipo inmueble");
        llenarCombo("Subtipo inmueble");
        llenarCombo("Tipo finca");
        llenarCombo("Provincia");
        llenarCombo("Tipo via");
        llenarCombo("Regimen");
        llenarCombo("Tipo de participacion");
        llenarCombo("Estado solicitud");
        llenarCombo("Tipo documento");
        llenarCombo("Tipo de propiedad");
        llenarCombo("Tipo de documento");
        llenarCombo("Provincia");
        llenarCombo("Poblacion");
        llenarCombo("Regimen economico");
        llenarCombo("Tipo de titulo");
        llenarCombo("Ocupación");
        llenarCombo("Conservación");
        llenarCombo("Año construcción");
        listaTitulares = new ArrayList<>();
        listaAnejos = new ArrayList<>();

    }

    public ArrayList<ArrayList<ComponenteFormulario>> getListaTitulares() {
        return listaTitulares;
    }

    public void setListaTitulares(ArrayList<ArrayList<ComponenteFormulario>> listaTitulares) {
        this.listaTitulares = listaTitulares;
    }

    public ArrayList<ArrayList<ComponenteFormulario>> getListaAnejos() {
        return listaAnejos;
    }

    public void setListaAnejos(ArrayList<ArrayList<ComponenteFormulario>> listaAnejos) {
        this.listaAnejos = listaAnejos;
    }

    private void llenarCombo(String TipoCombo) {
        switch (TipoCombo) {
            case "Subtipo inmueble":
                modelSubTipoInmueble = new Vector();
                modelSubTipoInmueble.addElement(new ModelCombo("TerrenoSecano", "TerrenoSecano"));
                modelSubTipoInmueble.addElement(new ModelCombo("ViviendaPiso", "ViviendaPiso"));
                modelSubTipoInmueble.addElement(new ModelCombo("ViviendaUnifamiliar", "ViviendaUnifamiliar"));
                modelSubTipoInmueble.addElement(new ModelCombo("PlazaGaraje", "PlazaGaraje"));
                modelSubTipoInmueble.addElement(new ModelCombo("CuartoTrastero", "CuartoTrastero"));
                modelSubTipoInmueble.addElement(new ModelCombo("ObraNueva", "ObraNueva"));
                modelSubTipoInmueble.addElement(new ModelCombo("DivisionHorizontal", "DivisionHorizontal"));
                modelSubTipoInmueble.addElement(new ModelCombo("LocalComercial", "LocalComercial"));
                modelSubTipoInmueble.addElement(new ModelCombo("EdificioIndustrial", "EdificioIndustrial"));
                modelSubTipoInmueble.addElement(new ModelCombo("LocalIndustrial", "LocalIndustrial"));
                modelSubTipoInmueble.addElement(new ModelCombo("LocalOficinas", "LocalOficinas"));
                modelSubTipoInmueble.addElement(new ModelCombo("Suelo", "Suelo"));
                modelSubTipoInmueble.addElement(new ModelCombo("OtrosInmuebles", "OtrosInmuebles"));
                modelSubTipoInmueble.addElement(new ModelCombo("BienesInmuebles", "BienesInmuebles"));
                modelSubTipoInmueble.addElement(new ModelCombo("EdificioCompleto", "EdificioCompleto"));
                modelSubTipoInmueble.addElement(new ModelCombo("Pareado", "Pareado"));
                modelSubTipoInmueble.addElement(new ModelCombo("Adosado", "Adosado"));
                eSubtipoInmueble.setModel(new DefaultComboBoxModel(modelSubTipoInmueble));
                break;
            case "Tipo via":
                modelTipoVia = new Vector();
                modelTipoVia.addElement(new ModelCombo("Calle", "Calle"));
                modelTipoVia.addElement(new ModelCombo("Plaza", "Plaza"));
                modelTipoVia.addElement(new ModelCombo("Paseo", "Paseo"));
                modelTipoVia.addElement(new ModelCombo("Avenida", "Avenida"));
                modelTipoVia.addElement(new ModelCombo("Ronda", "Ronda"));
                modelTipoVia.addElement(new ModelCombo("Carretera", "Carretera"));
                modelTipoVia.addElement(new ModelCombo("Camino", "Camino"));
                modelTipoVia.addElement(new ModelCombo("Cuesta", "Cuesta"));
                modelTipoVia.addElement(new ModelCombo("Edificio", "Edificio"));
                modelTipoVia.addElement(new ModelCombo("Urbanizacion", "Urbanizacion"));
                modelTipoVia.addElement(new ModelCombo("Carrera", "Carrera"));
                modelTipoVia.addElement(new ModelCombo("Callejon", "Callejon"));
                modelTipoVia.addElement(new ModelCombo("Poligono", "Poligono"));
                modelTipoVia.addElement(new ModelCombo("Travesia", "Travesia"));
                modelTipoVia.addElement(new ModelCombo("Glorieta", "Glorieta"));
                modelTipoVia.addElement(new ModelCombo("Colonia", "Colonia"));
                modelTipoVia.addElement(new ModelCombo("Chalet", "Chalet"));
                modelTipoVia.addElement(new ModelCombo("Agrupacion", "Agrupacion"));
                modelTipoVia.addElement(new ModelCombo("Alameda", "Alameda"));
                modelTipoVia.addElement(new ModelCombo("Apartado", "Apartado"));
                modelTipoVia.addElement(new ModelCombo("Bajada", "Bajada"));
                modelTipoVia.addElement(new ModelCombo("Barranco", "Barranco"));
                modelTipoVia.addElement(new ModelCombo("Barriada", "Barriada"));
                modelTipoVia.addElement(new ModelCombo("Barrio", "Barrio"));
                modelTipoVia.addElement(new ModelCombo("Bloque", "Bloque"));
                modelTipoVia.addElement(new ModelCombo("Bulevar", "Bulevar"));
                modelTipoVia.addElement(new ModelCombo("Caserio", "Caserio"));
                modelTipoVia.addElement(new ModelCombo("Diseminado", "Diseminado"));
                modelTipoVia.addElement(new ModelCombo("Grupo", "Grupo"));
                modelTipoVia.addElement(new ModelCombo("Lugar", "Lugar"));
                modelTipoVia.addElement(new ModelCombo("Mercado", "Mercado"));
                modelTipoVia.addElement(new ModelCombo("Parque", "Parque"));
                modelTipoVia.addElement(new ModelCombo("Partida", "Partida"));
                modelTipoVia.addElement(new ModelCombo("Pasaje", "Pasaje"));
                modelTipoVia.addElement(new ModelCombo("Poblado", "Poblado"));
                modelTipoVia.addElement(new ModelCombo("Rambla", "Rambla"));
                modelTipoVia.addElement(new ModelCombo("Residencial", "Residencial"));
                modelTipoVia.addElement(new ModelCombo("Rua", "Rua"));
                modelTipoVia.addElement(new ModelCombo("Sector", "Sector"));
                modelTipoVia.addElement(new ModelCombo("Senda", "Senda"));
                modelTipoVia.addElement(new ModelCombo("Subida", "Subida"));
                modelTipoVia.addElement(new ModelCombo("Torrente", "Torrente"));
                modelTipoVia.addElement(new ModelCombo("Travesera", "Travesera"));
                modelTipoVia.addElement(new ModelCombo("Via", "Via"));
                modelTipoVia.addElement(new ModelCombo("Carrer", "Carrer"));
                eTipoVia.setModel(new DefaultComboBoxModel(modelTipoVia));
                break;
            case "Regimen":
                modelRegimen = new Vector();
                modelRegimen.addElement(new ModelCombo("NoInformado", "NoInformado"));
                modelRegimen.addElement(new ModelCombo("VentaLibre", "VentaLibre"));
                modelRegimen.addElement(new ModelCombo("VPO", "VPO"));
                modelRegimen.addElement(new ModelCombo("VPOAnt78", "VPOAnt78"));
                modelRegimen.addElement(new ModelCombo("VPOPrivada", "VPOPrivada"));
                modelRegimen.addElement(new ModelCombo("VPOPublica", "VPOPublica"));
                modelRegimen.addElement(new ModelCombo("VPT", "VPT"));
                modelRegimen.addElement(new ModelCombo("Subvencionada", "Subvencionada"));
                eRegimenProteccion.setModel(new DefaultComboBoxModel(modelRegimen));
                break;
            /*case "Regimen economico":     modelRegimenEconomico = new Vector();
             modelRegimenEconomico.addElement( new ModelCombo("Otros", "Otros" ) );
             modelRegimenEconomico.addElement( new ModelCombo("Gananciales", "Gananciales" ) );
             modelRegimenEconomico.addElement( new ModelCombo("SeparacionBienes", "SeparacionBienes" ) );
             modelRegimenEconomico.addElement( new ModelCombo("Participacion", "Participacion" ) );
             eRegimenEconomico.setModel(new DefaultComboBoxModel(modelRegimenEconomico));
             break;                    
             */
            /*case "Tipo de participacion":modelTipoParticipacion = new Vector();
             modelTipoParticipacion.addElement( new ModelCombo("Titular", "Titular" ) );
             modelTipoParticipacion.addElement( new ModelCombo("SujetoPasivo", "SujetoPasivo" ) );
             modelTipoParticipacion.addElement( new ModelCombo("Vendedor", "Vendedor" ) );
             modelTipoParticipacion.addElement( new ModelCombo("Avalista", "Avalista" ) );
             modelTipoParticipacion.addElement( new ModelCombo("Otros", "Otros" ) );
             modelTipoParticipacion.addElement( new ModelCombo("Demandante", "Demandante" ) );
             modelTipoParticipacion.addElement( new ModelCombo("Demandado", "Demandado" ) );
             modelTipoParticipacion.addElement( new ModelCombo("PersonaContacto", "PersonaContacto" ) );
             modelTipoParticipacion.addElement( new ModelCombo("HipotecanteNoDeudor", "HipotecanteNoDeudor" ) );
             eTipoParticipacion.setModel(new DefaultComboBoxModel(modelTipoParticipacion));
             break; 
             case "Tipo de propiedad":modelTipoPropiedad = new Vector();
             modelTipoPropiedad.addElement( new ModelCombo("Propietario", "Propietario" ) );
             modelTipoPropiedad.addElement( new ModelCombo("Nudo_propietario", "Nudo_propietario" ) );
             modelTipoPropiedad.addElement( new ModelCombo("Usufructuario", "Usufructuario" ) );
             modelTipoPropiedad.addElement( new ModelCombo("Arrendatario_Ocupante", "Arrendatario_Ocupante" ) );
             eTipoPropiedad.setModel(new DefaultComboBoxModel(modelTipoPropiedad));
             break;
             case "Tipo de titulo":modelTipoTitulo = new Vector();
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
             modelTipoTitulo.addElement( new ModelCombo("Otros", "Otros" ) );
                                
             eTipoTitulo.setModel(new DefaultComboBoxModel(modelTipoTitulo));
             break;
                                
                
             case "Tipo de documento":modelTipoDocumento = new Vector();
             modelTipoDocumento.addElement( new ModelCombo("Unknwon", "Unknwon" ) );
             modelTipoDocumento.addElement( new ModelCombo("Nif", "Nif" ) );
             modelTipoDocumento.addElement( new ModelCombo("Cif", "Cif" ) );
             modelTipoDocumento.addElement( new ModelCombo("Passport", "Passport" ) );
             modelTipoDocumento.addElement( new ModelCombo("TarjetaResidente", "TarjetaResidente" ) );
             modelTipoDocumento.addElement( new ModelCombo("IdentificacionFiscalMenor", "IdentificacionFiscalMenor" ) );
             eTipoDocumento.setModel(new DefaultComboBoxModel(modelTipoDocumento));
             break;    */
            case "Provincia":
                modelProvincia = new Vector();
                listaProvincias = new ArrayList<>();
                //modelProvincia.addElement( new ModelCombo("SIN_ASIGNAR", "SIN_ASIGNAR" ) );
                modelProvincia.addElement(new ModelCombo("ALAVA", "ALAVA"));
                listaProvincias.add(new Provincia("1", "ALAVA"));
                modelProvincia.addElement(new ModelCombo("ALBACETE", "ALBACETE"));
                listaProvincias.add(new Provincia("2", "ALBACETE"));
                modelProvincia.addElement(new ModelCombo("ALICANTE", "ALICANTE"));
                listaProvincias.add(new Provincia("3", "ALICANTE"));
                modelProvincia.addElement(new ModelCombo("ALMERIA", "ALMERIA"));
                listaProvincias.add(new Provincia("4", "ALMERIA"));
                modelProvincia.addElement(new ModelCombo("AVILA", "AVILA"));
                listaProvincias.add(new Provincia("5", "AVILA"));
                modelProvincia.addElement(new ModelCombo("BADAJOZ", "BADAJOZ"));
                listaProvincias.add(new Provincia("6", "BADAJOZ"));
                modelProvincia.addElement(new ModelCombo("BALEARES", "BALEARES"));
                listaProvincias.add(new Provincia("7", "BALEARES"));
                modelProvincia.addElement(new ModelCombo("BARCELONA", "BARCELONA"));
                listaProvincias.add(new Provincia("8", "BARCELONA"));
                modelProvincia.addElement(new ModelCombo("BURGOS", "BURGOS"));
                listaProvincias.add(new Provincia("9", "BURGOS"));
                modelProvincia.addElement(new ModelCombo("CACERES", "CACERES"));
                listaProvincias.add(new Provincia("10", "CACERES"));
                modelProvincia.addElement(new ModelCombo("CADIZ", "CADIZ"));
                listaProvincias.add(new Provincia("11", "CADIZ"));
                modelProvincia.addElement(new ModelCombo("CASTELLON", "CASTELLON"));
                listaProvincias.add(new Provincia("12", "CASTELLON"));
                modelProvincia.addElement(new ModelCombo("CIUDAD_REAL", "CIUDAD_REAL"));
                listaProvincias.add(new Provincia("13", "CIUDAD_REAL"));
                modelProvincia.addElement(new ModelCombo("CORDOBA", "CORDOBA"));
                listaProvincias.add(new Provincia("14", "CORDOBA"));
                modelProvincia.addElement(new ModelCombo("A_CORUÑA", "A_CORUÑA"));
                listaProvincias.add(new Provincia("15", "A_CORUÑA"));
                modelProvincia.addElement(new ModelCombo("CUENCA", "CUENCA"));
                listaProvincias.add(new Provincia("16", "CUENCA"));
                modelProvincia.addElement(new ModelCombo("GIRONA", "GIRONA"));
                listaProvincias.add(new Provincia("17", "GIRONA"));
                modelProvincia.addElement(new ModelCombo("GRANADA", "GRANADA"));
                listaProvincias.add(new Provincia("18", "GRANADA"));
                modelProvincia.addElement(new ModelCombo("GUADALAJARA", "GUADALAJARA"));
                listaProvincias.add(new Provincia("19", "GUADALAJARA"));
                modelProvincia.addElement(new ModelCombo("GUIPUZCOA", "GUIPUZCOA"));
                listaProvincias.add(new Provincia("20", "GUIPUZCOA"));
                modelProvincia.addElement(new ModelCombo("HUELVA", "HUELVA"));
                listaProvincias.add(new Provincia("21", "HUELVA"));
                modelProvincia.addElement(new ModelCombo("HUESCA", "HUESCA"));
                listaProvincias.add(new Provincia("22", "HUESCA"));
                modelProvincia.addElement(new ModelCombo("JAEN", "JAEN"));
                listaProvincias.add(new Provincia("23", "JAEN"));
                modelProvincia.addElement(new ModelCombo("LEON", "LEON"));
                listaProvincias.add(new Provincia("24", "LEON"));
                modelProvincia.addElement(new ModelCombo("LLEIDA", "LLEIDA"));
                listaProvincias.add(new Provincia("25", "LLEIDA"));
                modelProvincia.addElement(new ModelCombo("LA_RIOJA", "LA_RIOJA"));
                listaProvincias.add(new Provincia("26", "LA_RIOJA"));
                modelProvincia.addElement(new ModelCombo("LUGO", "LUGO"));
                listaProvincias.add(new Provincia("27", "LUGO"));
                modelProvincia.addElement(new ModelCombo("MADRID", "MADRID"));
                listaProvincias.add(new Provincia("28", "MADRID"));
                modelProvincia.addElement(new ModelCombo("MALAGA", "MALAGA"));
                listaProvincias.add(new Provincia("29", "MALAGA"));
                modelProvincia.addElement(new ModelCombo("MURCIA", "MURCIA"));
                listaProvincias.add(new Provincia("30", "MURCIA"));
                modelProvincia.addElement(new ModelCombo("NAVARRA", "NAVARRA"));
                listaProvincias.add(new Provincia("31", "NAVARRA"));
                modelProvincia.addElement(new ModelCombo("OURENSE", "OURENSE"));
                listaProvincias.add(new Provincia("32", "OURENSE"));
                modelProvincia.addElement(new ModelCombo("ASTURIAS", "ASTURIAS"));
                listaProvincias.add(new Provincia("33", "ASTURIAS"));
                modelProvincia.addElement(new ModelCombo("PALENCIA", "PALENCIA"));
                listaProvincias.add(new Provincia("34", "PALENCIA"));
                modelProvincia.addElement(new ModelCombo("LAS_PALMAS", "LAS_PALMAS"));
                listaProvincias.add(new Provincia("35", "LAS_PALMAS"));
                modelProvincia.addElement(new ModelCombo("PONTEVEDRA", "PONTEVEDRA"));
                listaProvincias.add(new Provincia("36", "PONTEVEDRA"));
                modelProvincia.addElement(new ModelCombo("SALAMANCA", "SALAMANCA"));
                listaProvincias.add(new Provincia("37", "SALAMANCA"));
                modelProvincia.addElement(new ModelCombo("SC_TENERIFE", "SC_TENERIFE"));
                listaProvincias.add(new Provincia("38", "SC_TENERIFE"));
                modelProvincia.addElement(new ModelCombo("CANTABRIA", "CANTABRIA"));
                listaProvincias.add(new Provincia("39", "CANTABRIA"));
                modelProvincia.addElement(new ModelCombo("SEGOVIA", "SEGOVIA"));
                listaProvincias.add(new Provincia("40", "SEGOVIA"));
                modelProvincia.addElement(new ModelCombo("SEVILLA", "SEVILLA"));
                listaProvincias.add(new Provincia("41", "SEVILLA"));
                modelProvincia.addElement(new ModelCombo("SORIA", "SORIA"));
                listaProvincias.add(new Provincia("42", "SORIA"));
                modelProvincia.addElement(new ModelCombo("TARRAGONA", "TARRAGONA"));
                listaProvincias.add(new Provincia("43", "TARRAGONA"));
                modelProvincia.addElement(new ModelCombo("TERUEL", "TERUEL"));
                listaProvincias.add(new Provincia("44", "TERUEL"));
                modelProvincia.addElement(new ModelCombo("TOLEDO", "TOLEDO"));
                listaProvincias.add(new Provincia("45", "TOLEDO"));
                modelProvincia.addElement(new ModelCombo("VALENCIA", "VALENCIA"));
                listaProvincias.add(new Provincia("46", "VALENCIA"));
                modelProvincia.addElement(new ModelCombo("VALLADOLID", "VALLADOLID"));
                listaProvincias.add(new Provincia("47", "VALLADOLID"));
                modelProvincia.addElement(new ModelCombo("VIZCAYA", "VIZCAYA"));
                listaProvincias.add(new Provincia("48", "VIZCAYA"));
                modelProvincia.addElement(new ModelCombo("ZAMORA", "ZAMORA"));
                listaProvincias.add(new Provincia("49", "ZAMORA"));
                modelProvincia.addElement(new ModelCombo("ZARAGOZA", "ZARAGOZA"));
                listaProvincias.add(new Provincia("50", "ZARAGOZA"));
                modelProvincia.addElement(new ModelCombo("CEUTA", "CEUTA"));
                listaProvincias.add(new Provincia("51", "CEUTA"));
                modelProvincia.addElement(new ModelCombo("MELILLA", "MELILLA"));
                listaProvincias.add(new Provincia("52", "MELILLA"));
                eProvincia.setModel(new DefaultComboBoxModel(modelProvincia));
                break;
            case "Poblacion":
                modelPoblacion = new Vector();
                modelPoblacion.addElement(new ModelCombo("222", "222"));
                modelPoblacion.addElement(new ModelCombo("333", "333"));
                ePoblacion.setModel(new DefaultComboBoxModel(modelPoblacion));
                break;
            case "Ocupación":
                modelOcupacion = new Vector();
                modelOcupacion.addElement(new ModelCombo("SinDatos", "SinDatos"));
                modelOcupacion.addElement(new ModelCombo("Usufructuario", "Usufructuario"));
                modelOcupacion.addElement(new ModelCombo("Precarista", "Precarista"));
                modelOcupacion.addElement(new ModelCombo("Inquilino", "Inquilino"));
                modelOcupacion.addElement(new ModelCombo("Propietario_Usuario", "Propietario_Usuario"));
                modelOcupacion.addElement(new ModelCombo("Desocupada", "Desocupada"));
                modelOcupacion.addElement(new ModelCombo("Otros", "Otros"));
                eOcupacion.setModel(new DefaultComboBoxModel(modelOcupacion));
                break;
            case "Conservación":
                modelConservacion = new Vector();
                modelConservacion.addElement(new ModelCombo("NoInformado", "NoInformado"));
                modelConservacion.addElement(new ModelCombo("Nuevo_1Anio", "Nuevo_1Anio"));
                modelConservacion.addElement(new ModelCombo("Seminuevo_5Anios", "Seminuevo_5Anios"));
                modelConservacion.addElement(new ModelCombo("Usada_Mas5Anios", "Usada_Mas5Anios"));
                modelConservacion.addElement(new ModelCombo("AReformar", "AReformar"));
                modelConservacion.addElement(new ModelCombo("Usada_Mas55Anios", "Usada_Mas55Anios"));
                modelConservacion.addElement(new ModelCombo("EnConstruccion", "EnConstruccion"));
                modelConservacion.addElement(new ModelCombo("EnRuinas", "EnRuinas"));
                modelConservacion.addElement(new ModelCombo("EnProyecto", "EnProyecto"));
                eEstadoConservacion.setModel(new DefaultComboBoxModel(modelConservacion));
                break;
            case "Año construcción":
                modelAnnoConstruccion = new Vector();
                modelAnnoConstruccion.addElement(new ModelCombo("NoInformado", "NoInformado"));
                modelAnnoConstruccion.addElement(new ModelCombo("Antes1981", "Antes1981"));
                modelAnnoConstruccion.addElement(new ModelCombo("De1981a1999", "De1981a1999"));
                modelAnnoConstruccion.addElement(new ModelCombo("De1999a2003", "De1999a2003"));
                modelAnnoConstruccion.addElement(new ModelCombo("De2003a2008", "De2003a2008"));
                modelAnnoConstruccion.addElement(new ModelCombo("De2009a2013", "De2009a2013"));
                modelAnnoConstruccion.addElement(new ModelCombo("Despues2013", "Despues2013"));
                eAnnoConstruccion.setModel(new DefaultComboBoxModel(modelAnnoConstruccion));
                break;
        }
    }

    private void limpiarComponentesFormulario() {
        eIdufir.setText("");
        eReferenciaCatastral.setText("");
        eNombreVia.setText("");
        eBloqueOPortal.setText("");
        ePlanta.setText("");
        ePuerta.setText("");
        eEscalera.setText("");
        eCodigoPostal.setText("");
        eSeccion.setText("");
        eNumeroFinca.setText("");
        eNumeroFinca.setText("");
        eSubfinca.setText("");
        eTomo.setText("");
        eLibro.setText("");
        eFolio.setText("");
        eSuperficieConstruida.setText("");
        eSuperficieUtil.setText("");
        eSuperficieTerreno.setText("");
        eCoeficiente.setText("");
        eSuperficieComprobada.setText("");
        eValorSeguro.setText("");
        eValorSuelo.setText("");
        eValorTasacion.setText("");
        eValoracion.setText("");

        //Combo
        eSubtipoInmueble.setSelectedIndex(0);
        eTipoVia.setSelectedIndex(0);
        eRegimenProteccion.setSelectedIndex(0);
        ePoblacion.setSelectedIndex(0);
        eProvincia.setSelectedIndex(0);
        eRegistroPropiedad.setSelectedIndex(0);
        eAnnoConstruccion.setSelectedIndex(0);
        eOcupacion.setSelectedIndex(0);
        eEstadoConservacion.setSelectedIndex(0);

        //Fechas
        Date fecha = null;
        eFechaVerificación.setDate(fecha);
        eFechaCalificacion.setDate(fecha);

        //listaTitulares.clear();
        listaAnejos.clear();

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
    }

    private void limpiarComponentes() {
        //listaTitulares.clear();
        listaAnejos.clear();
    }

    private void llenarPoblaciones() {
        ComboboxToolTipRenderer renderer = new ComboboxToolTipRenderer();
        eRegistroPropiedad.setRenderer(renderer);
        List<String> tooltips = new ArrayList<>();
        listaPoblaciones = Utiles.leerPoblaciones();
        listaRegistros = Utiles.leerRegistros();
        //listaProvincias = Utiles.leerProvincias();
        modelPoblacion = new Vector();
        Collections.sort(listaPoblaciones);
        for (Poblacion poblacion : listaPoblaciones) {
            modelPoblacion.addElement(new ModelCombo(poblacion.getCodigo(), poblacion.getNombre()));
        }
        ePoblacion.setModel(new DefaultComboBoxModel(modelPoblacion));

        modelRegistroPropiedad = new Vector();
        Collections.sort(listaRegistros);
        modelRegistroPropiedad.addElement(new ModelCombo("0", "NO_INFORMADO"));
        tooltips.add("NO_INFORMADO");
        for (RegistroPropiedad registroPropiedad : listaRegistros) {
            modelRegistroPropiedad.addElement(new ModelCombo(registroPropiedad.getCodigo(), registroPropiedad.getNombre()));
            tooltips.add(registroPropiedad.getNombre());
        }
        eRegistroPropiedad.setModel(new DefaultComboBoxModel(modelRegistroPropiedad));
        renderer.setTooltips(tooltips);
        /*
         modelProvincia = new Vector();
         for (Provincia provincia : listaProvincias) {
         modelProvincia.addElement( new ModelCombo(provincia.getCodigo(), provincia.getNombre()) );
         }
         eProvincia.setModel(new DefaultComboBoxModel(modelProvincia));
         */
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
        jLabel6 = new javax.swing.JLabel();
        eFechaVerificación = new org.openswing.swing.client.DateControl();
        jLabel8 = new javax.swing.JLabel();
        eIdufir = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        eReferenciaCatastral = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        eSubtipoInmueble = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        eTipoVia = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        eNombreVia = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        eBloqueOPortal = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        ePlanta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        ePuerta = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        eEscalera = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        eCodigoPostal = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        eSeccion = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        eFolio = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        eFechaCalificacion = new org.openswing.swing.client.DateControl();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonAdicionarTitular = new javax.swing.JButton();
        jButtonConvertir = new javax.swing.JButton();
        jButtonEliminarTitular = new javax.swing.JButton();
        jButtonModificarTitular = new javax.swing.JButton();
        jButtonCargas1 = new javax.swing.JButton();
        jButtonAdicionarTitular1 = new javax.swing.JButton();
        eNumeroFinca = new javax.swing.JTextField();
        eSubfinca = new javax.swing.JTextField();
        eTomo = new javax.swing.JTextField();
        eLibro = new javax.swing.JTextField();
        eSuperficieConstruida = new javax.swing.JTextField();
        eSuperficieUtil = new javax.swing.JTextField();
        eSuperficieTerreno = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        ePoblacion = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        eProvincia = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        eCoeficiente = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        eRegimenProteccion = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        eRegistroPropiedad = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        eOcupacion = new javax.swing.JComboBox<>();
        eEstadoConservacion = new javax.swing.JComboBox<>();
        eValorSeguro = new javax.swing.JTextField();
        eSuperficieComprobada = new javax.swing.JTextField();
        eAnnoConstruccion = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        eValorSuelo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        eValorTasacion = new javax.swing.JTextField();
        eValoracion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        eValorHipotecario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Añadir datos finca tasación");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información finca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel6.setText("Fecha verificación:");

        jLabel8.setText("IDUFIR:");

        eIdufir.setName("IDUFIR"); // NOI18N
        eIdufir.setNextFocusableComponent(eReferenciaCatastral);

        jLabel9.setText("Referencia catastral:");

        eReferenciaCatastral.setName("REF_CATASTRAL."); // NOI18N
        eReferenciaCatastral.setNextFocusableComponent(eRegistroPropiedad);

        jLabel11.setText("Tipo de finca:");

        eSubtipoInmueble.setName("FINCA_TIPO"); // NOI18N
        eSubtipoInmueble.setNextFocusableComponent(eTipoVia);

        jLabel12.setText("Tipo de vía:");

        eTipoVia.setName("TIPO_VIA"); // NOI18N
        eTipoVia.setNextFocusableComponent(eNombreVia);

        jLabel13.setText("Nombre de la vía:");

        eNombreVia.setName("FINCA_NOMBRE."); // NOI18N
        eNombreVia.setNextFocusableComponent(eBloqueOPortal);

        jLabel15.setText("Bloque o portal:");

        eBloqueOPortal.setName("FINCA_PORTAL."); // NOI18N
        eBloqueOPortal.setNextFocusableComponent(ePlanta);

        jLabel16.setText("Planta:");

        ePlanta.setName("FINCA_PLANTA."); // NOI18N
        ePlanta.setNextFocusableComponent(ePuerta);

        jLabel17.setText("Puerta:");

        ePuerta.setName("FINCA_PUERTA."); // NOI18N
        ePuerta.setNextFocusableComponent(eEscalera);

        jLabel18.setText("Escalera:");

        eEscalera.setName("FINCA_ESCALERA."); // NOI18N
        eEscalera.setNextFocusableComponent(eCodigoPostal);

        jLabel19.setText("Código postal:");

        eCodigoPostal.setName("FINCA_CP."); // NOI18N
        eCodigoPostal.setNextFocusableComponent(eSeccion);
        eCodigoPostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eCodigoPostalKeyTyped(evt);
            }
        });

        jLabel22.setText("Sección:");

        eSeccion.setName("RG_SECCION."); // NOI18N
        eSeccion.setNextFocusableComponent(eNumeroFinca);
        eSeccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSeccionKeyTyped(evt);
            }
        });

        jLabel23.setText("Número finca:");

        jLabel24.setText("Subfinca:");

        jLabel25.setText("Tomo:");

        jLabel26.setText("Libro:");

        jLabel27.setText("Folio:");

        eFolio.setName("RG_FOLIO."); // NOI18N
        eFolio.setNextFocusableComponent(eRegimenProteccion);
        eFolio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eFolioKeyTyped(evt);
            }
        });

        jLabel29.setText("Fecha calificación:");

        jLabel30.setText("Superficie construida:");

        jLabel31.setText("Superficie útil:");

        jLabel32.setText("Superficie terreno:");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Titular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        jButtonConvertir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/restaura_bd_g.png"))); // NOI18N
        jButtonConvertir.setText("Salvar finca");
        jButtonConvertir.setToolTipText("Convertir a XML");
        jButtonConvertir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonConvertirMousePressed(evt);
            }
        });
        jButtonConvertir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvertirActionPerformed(evt);
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

        jButtonCargas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report_fac_g.png"))); // NOI18N
        jButtonCargas1.setText("Anejos");
        jButtonCargas1.setToolTipText("Gestionar cargas");
        jButtonCargas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonCargas1MousePressed(evt);
            }
        });
        jButtonCargas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargas1ActionPerformed(evt);
            }
        });

        jButtonAdicionarTitular1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButtonAdicionarTitular1.setText("Título");
        jButtonAdicionarTitular1.setToolTipText("Adicionar titular");
        jButtonAdicionarTitular1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAdicionarTitular1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAdicionarTitular1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdicionarTitular, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonModificarTitular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCargas1, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonConvertir)
                    .addComponent(jButtonEliminarTitular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdicionarTitular1)
                    .addComponent(jButtonEliminarTitular)
                    .addComponent(jButtonModificarTitular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdicionarTitular)
                    .addComponent(jButtonCargas1)
                    .addComponent(jButtonConvertir))
                .addGap(57, 57, 57))
        );

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
        eLibro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eLibroKeyTyped(evt);
            }
        });

        eSuperficieConstruida.setName("SUPERF_CONSTRUIDA."); // NOI18N
        eSuperficieConstruida.setNextFocusableComponent(eSuperficieUtil);
        eSuperficieConstruida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eSuperficieConstruidaFocusLost(evt);
            }
        });
        eSuperficieConstruida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieConstruidaKeyTyped(evt);
            }
        });

        eSuperficieUtil.setName("SUPERF_UTIL."); // NOI18N
        eSuperficieUtil.setNextFocusableComponent(eSuperficieTerreno);
        eSuperficieUtil.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eSuperficieUtilFocusLost(evt);
            }
        });
        eSuperficieUtil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eSuperficieUtilActionPerformed(evt);
            }
        });
        eSuperficieUtil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieUtilKeyTyped(evt);
            }
        });

        eSuperficieTerreno.setName("SUPERF_TERRENO."); // NOI18N
        eSuperficieTerreno.setNextFocusableComponent(ePoblacion);
        eSuperficieTerreno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eSuperficieTerrenoFocusLost(evt);
            }
        });
        eSuperficieTerreno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieTerrenoKeyTyped(evt);
            }
        });

        jLabel33.setText("Población:");

        ePoblacion.setMaximumRowCount(25);
        ePoblacion.setName("FINCA_POBLACION"); // NOI18N
        ePoblacion.setNextFocusableComponent(eProvincia);
        ePoblacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ePoblacionActionPerformed(evt);
            }
        });

        jLabel47.setText("Provincia:");

        eProvincia.setName("FINCA_PROVINCIA"); // NOI18N
        eProvincia.setNextFocusableComponent(eCoeficiente);

        jLabel48.setText("Coeficiente:");

        eCoeficiente.setName("COEFICIENTE."); // NOI18N
        eCoeficiente.setNextFocusableComponent(eOcupacion);
        eCoeficiente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eCoeficienteKeyTyped(evt);
            }
        });

        jLabel38.setText("Régimen protección:");

        eRegimenProteccion.setName("REGIMEN_PROTECCION"); // NOI18N
        eRegimenProteccion.setNextFocusableComponent(eFechaCalificacion);

        jLabel20.setText("Registro Propiedad:");

        eRegistroPropiedad.setName("REGISTRO_PROPIEDAD"); // NOI18N
        eRegistroPropiedad.setNextFocusableComponent(eSubtipoInmueble);

        jLabel1.setText("Ocupación:");

        jLabel2.setText("Estado conservación:");

        jLabel3.setText("Valor seguro:");

        jLabel4.setText("Año de construcción:");

        jLabel5.setText("Superficie comprobada:");

        eOcupacion.setName("OCUPACION."); // NOI18N
        eOcupacion.setNextFocusableComponent(eEstadoConservacion);

        eEstadoConservacion.setName("ESTADO_CONSERVACION."); // NOI18N
        eEstadoConservacion.setNextFocusableComponent(eValorSeguro);

        eValorSeguro.setName("VALOR_SEGURO."); // NOI18N
        eValorSeguro.setNextFocusableComponent(eAnnoConstruccion);
        eValorSeguro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eValorSeguroFocusLost(evt);
            }
        });
        eValorSeguro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eValorSeguroActionPerformed(evt);
            }
        });
        eValorSeguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eValorSeguroKeyTyped(evt);
            }
        });

        eSuperficieComprobada.setName("SUPERFICIE_COMPROBADA."); // NOI18N
        eSuperficieComprobada.setNextFocusableComponent(eValorSuelo);
        eSuperficieComprobada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eSuperficieComprobadaFocusLost(evt);
            }
        });
        eSuperficieComprobada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eSuperficieComprobadaKeyTyped(evt);
            }
        });

        eAnnoConstruccion.setName("ANNO_CONSTRUCCION."); // NOI18N
        eAnnoConstruccion.setNextFocusableComponent(eSuperficieComprobada);

        jLabel7.setText("Valor suelo:");

        eValorSuelo.setName("VALOR_SUELO."); // NOI18N
        eValorSuelo.setNextFocusableComponent(eValorTasacion);
        eValorSuelo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eValorSueloFocusLost(evt);
            }
        });
        eValorSuelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eValorSueloKeyTyped(evt);
            }
        });

        jLabel10.setText("Valor tasación:");

        eValorTasacion.setName("VALOR_TASACION."); // NOI18N
        eValorTasacion.setNextFocusableComponent(eValoracion);
        eValorTasacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eValorTasacionFocusLost(evt);
            }
        });
        eValorTasacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eValorTasacionKeyTyped(evt);
            }
        });

        eValoracion.setName("VALORACION."); // NOI18N
        eValoracion.setNextFocusableComponent(eValorHipotecario);
        eValoracion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eValoracionFocusLost(evt);
            }
        });
        eValoracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eValoracionKeyTyped(evt);
            }
        });

        jLabel14.setText("Valoración:");

        jLabel21.setText("Valor hipotecario:");

        eValorHipotecario.setName("VALOR_HIPOTECARIO."); // NOI18N
        eValorHipotecario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eValorHipotecarioFocusLost(evt);
            }
        });
        eValorHipotecario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eValorHipotecarioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eRegistroPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(eSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(eNombreVia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eTipoVia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eSubtipoInmueble, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eReferenciaCatastral, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eIdufir, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eFechaVerificación, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eBloqueOPortal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ePlanta, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ePuerta, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eEscalera, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(eTomo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                        .addComponent(eSubfinca, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(eNumeroFinca, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(eLibro, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(eFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eRegimenProteccion, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel47)
                                    .addComponent(jLabel48)
                                    .addComponent(jLabel1)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(19, 19, 19))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(23, 23, 23)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel31)
                                    .addComponent(jLabel32))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(59, 59, 59)
                                    .addComponent(jLabel33))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eSuperficieComprobada, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ePoblacion, 0, 191, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(eFechaCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(eSuperficieConstruida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(eSuperficieUtil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(eSuperficieTerreno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(eProvincia, 0, 191, Short.MAX_VALUE)
                                .addComponent(eCoeficiente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(eOcupacion, 0, 191, Short.MAX_VALUE)
                                .addComponent(eEstadoConservacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eValorSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(eAnnoConstruccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(eValorSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eValorTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(eValoracion, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eValorHipotecario, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(728, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
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
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(eRegistroPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(eSubtipoInmueble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNombreVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eBloqueOPortal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(ePlanta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ePuerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(eEscalera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(eSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNumeroFinca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eSubfinca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eTomo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eRegimenProteccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(eSuperficieConstruida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eSuperficieUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(eSuperficieTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(ePoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(eProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(eCoeficiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(eOcupacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eEstadoConservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(eValorSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(eAnnoConstruccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(eSuperficieComprobada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(eValorSuelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(eValorTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21)
                                .addComponent(eValorHipotecario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(eValoracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(838, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jButton1.setText("Limpiar formulario");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdicionarTitularMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAdicionarTitularMousePressed
        FormaTitularTasacion formaTitularTasacion = new FormaTitularTasacion(this, true, 1, -1);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaTitularTasacion.setLocation(GEnv.getCenterPoint().x - formaTitularTasacion.getWidth() / 2 - 20, GEnv.getCenterPoint().y - formaTitularTasacion.getHeight() / 2 + 25);

        formaTitularTasacion.setResizable(false);
        formaTitularTasacion.setVisible(true);

        Utiles.llenarTabla(jTable1, listaTitulares, "Titulares");
    }//GEN-LAST:event_jButtonAdicionarTitularMousePressed

    private void jButtonConvertirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonConvertirMousePressed
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea Salvar?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0) {
            JComponent comp1 = Utiles.ValidaControles(jPanel1);
            JComponent comp2 = Utiles.ValidaControles(jPanel6);
            if (comp1 == null) {
                if (comp2 == null) {
                    if (Utiles.validarFecha(eFechaVerificación) && Utiles.validarFecha(eFechaCalificacion)) {
                        //esErronea();
                        new TareaSegundoPlano(this, Utiles.msgTareaAnadiendoFinca) {
                            @Override
                            protected void tareaHaRealizar() {
                                JComponent compTemp = (JComponent) eFechaVerificación;
                                String atributo;
                                ArrayList<ComponenteFormulario> listaComponentesXML = new ArrayList<>();
                                while (compTemp != null) {
                                    atributo = compTemp.getName();
                                    if (atributo.endsWith(".")) {
                                        atributo = atributo.substring(0, atributo.length() - 1);
                                    }
                                    if (compTemp instanceof JFormattedTextField) {
                                        System.out.println(((JFormattedTextField) compTemp).getText());
                                        if (((JFormattedTextField) compTemp).getText().equals("   .  ")) {
                                            listaComponentesXML.add(new ComponenteFormulario(atributo, ""));
                                        } else {
                                            listaComponentesXML.add(new ComponenteFormulario(atributo, ((JFormattedTextField) compTemp).getText()));
                                        }
                                    } else if (compTemp instanceof JTextField) {
                                        listaComponentesXML.add(new ComponenteFormulario(atributo, ((JTextField) compTemp).getText()));
                                    } else if (compTemp instanceof JComboBox) {
                                        /*if ((true) && (!atributo.equals("ESTADO_SOLICITUD")))
                                         listaComponentesXML.add(new ComponenteFormulario(atributo, ""));
                                         else */
                                        listaComponentesXML.add(new ComponenteFormulario(atributo, ((ModelCombo) ((JComboBox) compTemp).getSelectedItem()).getClave()));
                                    } else if (compTemp instanceof DateControl) {
                                        if (((DateControl) compTemp).getDate() != null) {
                                            listaComponentesXML.add(new ComponenteFormulario(atributo, Utiles.convertirFechaYYYYMMDD(((DateControl) compTemp).getDate())));
                                        } else {
                                            listaComponentesXML.add(new ComponenteFormulario(atributo, ""));
                                        }
                                    } else if (compTemp instanceof JTextArea) {
                                        listaComponentesXML.add(new ComponenteFormulario(atributo, ((JTextArea) compTemp).getText()));
                                    }
                                    compTemp = (JComponent) compTemp.getNextFocusableComponent();
                                }
                                ArrayList<ArrayList<ComponenteFormulario>> listaAnejosTemp = new ArrayList<>();
                                for (ArrayList<ComponenteFormulario> anejo : listaAnejos) {
                                    listaAnejosTemp.add(anejo);
                                }
                                if (tipoLLamada == -1) {
                                    formaTasacion.getListaFincas().add(new Finca(listaComponentesXML, listaTitulares, listaAnejosTemp));
                                } else {
                                    formaTasacion.getListaFincas().set(tipoLLamada, new Finca(listaComponentesXML, listaTitulares, listaAnejosTemp));
                                }
                                //formaTasacion.setListaTitulares(listaTitulares);
                                //formaTasacion.setListaAnejos(listaAnejos);
                                //Utiles.generarXML(eNombreXML, listaTitulares, "Convertir", listaAnejos, listaCargas);
                                /*
                                 listaDocumentos.remove(elementoSeleccionadoTabla);
                                 DefaultTableModel modelo = (DefaultTableModel)jXTable1.getModel();
                                 modelo.removeRow(elementoSeleccionadoTabla);
                                 jXTable1.setModel(modelo);
                                 */
                                limpiarComponentes();
                            }
                        }.ejecutarTarea();

                    } else {
                        JOptionPane.showMessageDialog(null, "Ha definido alguna fecha incorrectamente.");
                    }

                } 
                else 
                {
                    if (comp2 instanceof DateControl && ((DateControl) comp2).getValue() != null) 
                    {
                        JOptionPane.showMessageDialog(null, Utiles.msgFechaIncorrecta + comp2.getName());
                        comp2.requestFocus();
                    }
                    else 
                    {
                        JOptionPane.showMessageDialog(null, Utiles.msgDebeIntroducir + comp2.getName());
                        comp2.requestFocus();
                    }
                }
            } else 
            {
                if (comp1 instanceof DateControl && ((DateControl) comp1).getValue() != null) 
                {
                    JOptionPane.showMessageDialog(null, Utiles.msgFechaIncorrecta + comp1.getName());
                    comp1.requestFocus();
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, Utiles.msgDebeIntroducir + comp1.getName());
                    comp1.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_jButtonConvertirMousePressed

    private void jButtonEliminarTitularMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEliminarTitularMousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1) {
            DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
            tabla.removeRow(indiceSeleccionado);
            jTable1.setModel(tabla);
            listaTitulares.remove(indiceSeleccionado);
        } else {
            JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneTitularEliminar);
        }
    }//GEN-LAST:event_jButtonEliminarTitularMousePressed

    private void jButtonModificarTitularMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModificarTitularMousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1) {
            FormaTitularTasacion formaTitularTasacion = new FormaTitularTasacion(this, true, 2, indiceSeleccionado);
            GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            formaTitularTasacion.setLocation(GEnv.getCenterPoint().x - formaTitularTasacion.getWidth() / 2 - 20, GEnv.getCenterPoint().y - formaTitularTasacion.getHeight() / 2 + 25);

            formaTitularTasacion.setResizable(false);
            formaTitularTasacion.setVisible(true);

            Utiles.llenarTabla(jTable1, listaTitulares, "Titulares");
        } else {
            JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneTitularModificar);
        }
    }//GEN-LAST:event_jButtonModificarTitularMousePressed

    private void jButtonCargas1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCargas1MousePressed
        FormaAnejoTasacion formaAnejoTasacion = new FormaAnejoTasacion(this, true);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaAnejoTasacion.setLocation(GEnv.getCenterPoint().x - formaAnejoTasacion.getWidth() / 2 - 20, GEnv.getCenterPoint().y - formaAnejoTasacion.getHeight() / 2 + 25);
        formaAnejoTasacion.setResizable(false);
        formaAnejoTasacion.setVisible(true);
    }//GEN-LAST:event_jButtonCargas1MousePressed

    private void jButtonCargas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCargas1ActionPerformed

    private void jButtonAdicionarTitular1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAdicionarTitular1MousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1) {
            FormaTitularTasacion formaTitular = new FormaTitularTasacion(this, true, 3, indiceSeleccionado);
            GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            formaTitular.setLocation(GEnv.getCenterPoint().x - formaTitular.getWidth() / 2 - 20, GEnv.getCenterPoint().y - formaTitular.getHeight() / 2 + 25);

            formaTitular.setResizable(false);
            formaTitular.setVisible(true);

            Utiles.llenarTabla(jTable1, listaTitulares, "Titulares");
        } else {
            JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneTitularModificar);
        }
    }//GEN-LAST:event_jButtonAdicionarTitular1MousePressed

    private void eTomoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eTomoKeyTyped
        Utiles.validarNumeroEntero(evt, eTomo, 4);
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

    private void ePoblacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ePoblacionActionPerformed
        ModelCombo modelo = (ModelCombo) ePoblacion.getSelectedItem();
        Boolean indiceAsignado = false;
        for (Poblacion poblacion : listaPoblaciones) {
            if (modelo.getClave().equals(poblacion.getCodigo())) {
                int indice = 0;
                for (Provincia provincia : listaProvincias) {
                    if (poblacion.getCodigoProvincia().equals(provincia.getCodigo())) {
                        eProvincia.setSelectedIndex(indice);
                        indiceAsignado = true;
                        break;
                    }
                    indice++;
                }
            }
            if (indiceAsignado) {
                break;
            }
        }

    }//GEN-LAST:event_ePoblacionActionPerformed

    private void eCoeficienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eCoeficienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_eCoeficienteKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        llenarPoblaciones();
        listaAnejos.clear();
        listaTitulares.clear();
        if (tipoLLamada != -1) {
            llenarCamposDocumentoGuardado(this.fincaModificar.getListaComponentesXML());
        }
    }//GEN-LAST:event_formWindowOpened

    private void eValorSeguroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eValorSeguroKeyTyped
        Utiles.validarNumeroReal(evt, eValorSeguro, 9, 2);
    }//GEN-LAST:event_eValorSeguroKeyTyped

    private void eSuperficieComprobadaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eSuperficieComprobadaKeyTyped
        Utiles.validarNumeroReal(evt, eSuperficieComprobada, 9, 2);
    }//GEN-LAST:event_eSuperficieComprobadaKeyTyped

    private void eValorSueloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eValorSueloKeyTyped
        Utiles.validarNumeroReal(evt, eValorSuelo, 9, 2);
    }//GEN-LAST:event_eValorSueloKeyTyped

    private void eValorTasacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eValorTasacionKeyTyped
        Utiles.validarNumeroReal(evt, eValorTasacion, 9, 2);

        eValoracion.setText(eValorTasacion.getText() + (evt.getKeyChar() != '\b' ? evt.getKeyChar() : ""));
        eValorHipotecario.setText(eValorTasacion.getText() + (evt.getKeyChar() != '\b' ? evt.getKeyChar() : ""));
    }//GEN-LAST:event_eValorTasacionKeyTyped

    private void eValoracionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eValoracionKeyTyped
        Utiles.validarNumeroReal(evt, eValoracion, 9, 2);
    }//GEN-LAST:event_eValoracionKeyTyped

    private void eValorSeguroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eValorSeguroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eValorSeguroActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        limpiarComponentesFormulario();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1MousePressed

    private void jButtonConvertirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvertirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonConvertirActionPerformed

    private void eValorHipotecarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eValorHipotecarioKeyTyped
        Utiles.validarNumeroReal(evt, eValorHipotecario, 9, 2);
    }//GEN-LAST:event_eValorHipotecarioKeyTyped

    private void eFolioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eFolioKeyTyped
        Utiles.validarNumeroEntero(evt, eFolio, 6);
    }//GEN-LAST:event_eFolioKeyTyped

    private void eCodigoPostalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eCodigoPostalKeyTyped
        //Utiles.validarNumeroEntero(evt, eCodigoPostal, 5);
    }//GEN-LAST:event_eCodigoPostalKeyTyped

    private void eSeccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eSeccionKeyTyped
        Utiles.validarNumeroEntero(evt, eSeccion, 3);
    }//GEN-LAST:event_eSeccionKeyTyped

    private void eLibroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eLibroKeyTyped
        Utiles.validarNumeroEntero(evt, eLibro, 4);
    }//GEN-LAST:event_eLibroKeyTyped

    private void eSuperficieUtilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eSuperficieUtilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eSuperficieUtilActionPerformed

    private void eSuperficieConstruidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eSuperficieConstruidaFocusLost
        if (!eSuperficieConstruida.getText().equals("")) {
            if (eSuperficieConstruida.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eSuperficieConstruida.requestFocus();
            }
        }
    }//GEN-LAST:event_eSuperficieConstruidaFocusLost

    private void eSuperficieUtilFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eSuperficieUtilFocusLost
        if (!eSuperficieUtil.getText().equals("")) {
            if (eSuperficieUtil.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eSuperficieUtil.requestFocus();
            }
        }
    }//GEN-LAST:event_eSuperficieUtilFocusLost

    private void eSuperficieTerrenoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eSuperficieTerrenoFocusLost
        if (!eSuperficieTerreno.getText().equals("")) {
            if (eSuperficieTerreno.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eSuperficieTerreno.requestFocus();
            }
        }
    }//GEN-LAST:event_eSuperficieTerrenoFocusLost

    private void eValorSeguroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eValorSeguroFocusLost
        if (!eValorSeguro.getText().equals("")) {
            if (eValorSeguro.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eValorSeguro.requestFocus();
            }
        }
    }//GEN-LAST:event_eValorSeguroFocusLost

    private void eSuperficieComprobadaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eSuperficieComprobadaFocusLost
        if (!eSuperficieComprobada.getText().equals("")) {
            if (eSuperficieComprobada.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eSuperficieComprobada.requestFocus();
            }
        }
    }//GEN-LAST:event_eSuperficieComprobadaFocusLost

    private void eValorSueloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eValorSueloFocusLost
        if (!eValorSuelo.getText().equals("")) {
            if (eValorSuelo.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eValorSuelo.requestFocus();
            }
        }
    }//GEN-LAST:event_eValorSueloFocusLost

    private void eValorTasacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eValorTasacionFocusLost
        if (!eValorTasacion.getText().equals("")) {
            if (eValorTasacion.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eValorTasacion.requestFocus();
            }
        }
    }//GEN-LAST:event_eValorTasacionFocusLost

    private void eValoracionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eValoracionFocusLost
        if (!eValoracion.getText().equals("")) {
            if (eValoracion.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eValoracion.requestFocus();
            }
        }
    }//GEN-LAST:event_eValoracionFocusLost

    private void eValorHipotecarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eValorHipotecarioFocusLost
        if (!eValorHipotecario.getText().equals("")) {
            if (eValorHipotecario.getText().contains(".")) {
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eValorHipotecario.requestFocus();
            }
        }
    }//GEN-LAST:event_eValorHipotecarioFocusLost

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
            java.util.logging.Logger.getLogger(FormaProcesarFinca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormaProcesarFinca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormaProcesarFinca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormaProcesarFinca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormaProcesarFinca dialog = new FormaProcesarFinca(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> eAnnoConstruccion;
    private javax.swing.JTextField eBloqueOPortal;
    private javax.swing.JTextField eCodigoPostal;
    private javax.swing.JTextField eCoeficiente;
    private javax.swing.JTextField eEscalera;
    private javax.swing.JComboBox<String> eEstadoConservacion;
    private org.openswing.swing.client.DateControl eFechaCalificacion;
    private org.openswing.swing.client.DateControl eFechaVerificación;
    private javax.swing.JTextField eFolio;
    private javax.swing.JTextField eIdufir;
    private javax.swing.JTextField eLibro;
    private javax.swing.JTextField eNombreVia;
    private javax.swing.JTextField eNumeroFinca;
    private javax.swing.JComboBox<String> eOcupacion;
    private javax.swing.JTextField ePlanta;
    private javax.swing.JComboBox<String> ePoblacion;
    private javax.swing.JComboBox<String> eProvincia;
    private javax.swing.JTextField ePuerta;
    private javax.swing.JTextField eReferenciaCatastral;
    private javax.swing.JComboBox<String> eRegimenProteccion;
    private javax.swing.JComboBox<String> eRegistroPropiedad;
    private javax.swing.JTextField eSeccion;
    private javax.swing.JTextField eSubfinca;
    private javax.swing.JComboBox<String> eSubtipoInmueble;
    private javax.swing.JTextField eSuperficieComprobada;
    private javax.swing.JTextField eSuperficieConstruida;
    private javax.swing.JTextField eSuperficieTerreno;
    private javax.swing.JTextField eSuperficieUtil;
    private javax.swing.JComboBox<String> eTipoVia;
    private javax.swing.JTextField eTomo;
    private javax.swing.JTextField eValorHipotecario;
    private javax.swing.JTextField eValorSeguro;
    private javax.swing.JTextField eValorSuelo;
    private javax.swing.JTextField eValorTasacion;
    private javax.swing.JTextField eValoracion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdicionarTitular;
    private javax.swing.JButton jButtonAdicionarTitular1;
    private javax.swing.JButton jButtonCargas1;
    private javax.swing.JButton jButtonConvertir;
    private javax.swing.JButton jButtonEliminarTitular;
    private javax.swing.JButton jButtonModificarTitular;
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
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
