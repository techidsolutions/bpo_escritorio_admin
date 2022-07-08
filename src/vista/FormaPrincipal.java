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
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
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
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.ComponenteFormulario;
import modelo.Documento;
import modelo.ModelCombo;
import modelo.Poblacion;
import modelo.Provincia;
import modelo.RegistroPropiedad;
import modelo.Usuario;
import org.jdesktop.swingx.JXTable;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.internationalization.java.Resources;
import org.oxbow.swingbits.table.filter.TableRowFilterSupport;
import util.ComboboxToolTipRenderer;
import util.LimitarNumeros;
import util.TareaSegundoPlano;
import util.Utiles;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaPrincipal extends javax.swing.JFrame {
    private static Usuario usuario = null;
    int alto;
    final int filas = 20;
    JScrollBar barra;
    TableRowSorter<TableModel> ordenar;
    Documento documentoSeleccionado;
    ArrayList<Documento> listaDocumentos;
    int elementoSeleccionadoTabla = -1;
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
     
    private ArrayList<ArrayList<ComponenteFormulario>> listaTitulares; 
    private ArrayList<ArrayList<ComponenteFormulario>> listaCargas; 
    private ArrayList<ArrayList<ComponenteFormulario>> listaAnejos; 
    private List<Poblacion> listaPoblaciones;
    private ArrayList<Provincia> listaProvincias;
    private List<RegistroPropiedad> listaRegistros;

    private ArrayList<ComponenteFormulario> listaTitularData;

    public  static void setUsuario(Usuario usuario) {
        FormaPrincipal.usuario = usuario;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public  ArrayList<ArrayList<ComponenteFormulario>> getListaTitulares() {
        return listaTitulares;
    }

    public void setListaTitulares(ArrayList<ArrayList<ComponenteFormulario>> listaTitulares) {
        this.listaTitulares = listaTitulares;
    }

    public ArrayList<ArrayList<ComponenteFormulario>> getListaCargas() {
        return listaCargas;
    }

    public ArrayList<ArrayList<ComponenteFormulario>> getListaAnejos() {
        return listaAnejos;
    }

    public void setListaAnejos(ArrayList<ArrayList<ComponenteFormulario>> listaAnejos) {
        this.listaAnejos = listaAnejos;
    }
    
    
    /**
     * 
     */
    private void llenarPoblaciones(){
        ComboboxToolTipRenderer renderer = new ComboboxToolTipRenderer();
        eRegistroPropiedad.setRenderer(renderer);
        List<String> tooltips = new ArrayList<>();
        listaPoblaciones = Utiles.leerPoblaciones();
        listaRegistros = Utiles.leerRegistros();
        //listaProvincias = Utiles.leerProvincias();
        modelPoblacion = new Vector();
        Collections.sort(listaPoblaciones);
        for (Poblacion poblacion : listaPoblaciones) {
            modelPoblacion.addElement( new ModelCombo(poblacion.getCodigo(), poblacion.getNombre()) );
        }
        ePoblacion.setModel(new DefaultComboBoxModel(modelPoblacion));
        
        modelRegistroPropiedad = new Vector();
        Collections.sort(listaRegistros);
        modelRegistroPropiedad.addElement( new ModelCombo("0", "NO_INFORMADO") );
        tooltips.add("NO_INFORMADO");
        for (RegistroPropiedad registroPropiedad : listaRegistros) {
            modelRegistroPropiedad.addElement( new ModelCombo(registroPropiedad.getCodigo(), registroPropiedad.getNombre()) );
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
    
    private void llenarComboTipoInmueble(String TipoCombo){
        switch(TipoCombo){
                case "Subtipo inmueble":modelSubTipoInmueble = new Vector();
                                    modelSubTipoInmueble.addElement( new ModelCombo("TerrenoSecano", "TerrenoSecano" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("ViviendaPiso", "ViviendaPiso" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("ViviendaUnifamiliar", "ViviendaUnifamiliar" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("PlazaGaraje", "PlazaGaraje" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("CuartoTrastero", "CuartoTrastero" ) ); 
                                    modelSubTipoInmueble.addElement( new ModelCombo("ObraNueva", "ObraNueva" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("DivisionHorizontal", "DivisionHorizontal" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("LocalComercial", "LocalComercial" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("EdificioIndustrial", "EdificioIndustrial" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("LocalIndustrial", "LocalIndustrial" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("LocalOficinas", "LocalOficinas" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("Suelo", "Suelo" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("OtrosInmuebles", "OtrosInmuebles" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("BienesInmuebles", "BienesInmuebles" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("EdificioCompleto", "EdificioCompleto" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("Pareado", "Pareado" ) );
                                    modelSubTipoInmueble.addElement( new ModelCombo("Adosado", "Adosado" ) );
                                    eSubtipoInmueble.setModel(new DefaultComboBoxModel(modelSubTipoInmueble));
                                    break;    
                case "Tipo via":    modelTipoVia = new Vector();
                                    modelTipoVia.addElement( new ModelCombo("Calle", "Calle" ) );
                                    modelTipoVia.addElement( new ModelCombo("Plaza", "Plaza" ) );
                                    modelTipoVia.addElement( new ModelCombo("Paseo", "Paseo" ) );
                                    modelTipoVia.addElement( new ModelCombo("Avenida", "Avenida" ) );
                                    modelTipoVia.addElement( new ModelCombo("Ronda", "Ronda" ) );
                                    modelTipoVia.addElement( new ModelCombo("Carretera", "Carretera" ) );
                                    modelTipoVia.addElement( new ModelCombo("Camino", "Camino" ) );
                                    modelTipoVia.addElement( new ModelCombo("Cuesta", "Cuesta" ) );
                                    modelTipoVia.addElement( new ModelCombo("Edificio", "Edificio" ) );
                                    modelTipoVia.addElement( new ModelCombo("Urbanizacion", "Urbanizacion" ) );
                                    modelTipoVia.addElement( new ModelCombo("Carrera", "Carrera" ) );
                                    modelTipoVia.addElement( new ModelCombo("Callejon", "Callejon" ) );
                                    modelTipoVia.addElement( new ModelCombo("Poligono", "Poligono" ) );
                                    modelTipoVia.addElement( new ModelCombo("Travesia", "Travesia" ) );
                                    modelTipoVia.addElement( new ModelCombo("Glorieta", "Glorieta" ) );
                                    modelTipoVia.addElement( new ModelCombo("Colonia", "Colonia" ) );
                                    modelTipoVia.addElement( new ModelCombo("Chalet", "Chalet" ) );
                                    modelTipoVia.addElement( new ModelCombo("Agrupacion", "Agrupacion" ) );
                                    modelTipoVia.addElement( new ModelCombo("Alameda", "Alameda" ) );
                                    modelTipoVia.addElement( new ModelCombo("Apartado", "Apartado" ) );
                                    modelTipoVia.addElement( new ModelCombo("Bajada", "Bajada" ) );
                                    modelTipoVia.addElement( new ModelCombo("Barranco", "Barranco" ) );
                                    modelTipoVia.addElement( new ModelCombo("Barriada", "Barriada" ) );
                                    modelTipoVia.addElement( new ModelCombo("Barrio", "Barrio" ) );
                                    modelTipoVia.addElement( new ModelCombo("Bloque", "Bloque" ) );
                                    modelTipoVia.addElement( new ModelCombo("Bulevar", "Bulevar" ) );
                                    modelTipoVia.addElement( new ModelCombo("Caserio", "Caserio" ) );
                                    modelTipoVia.addElement( new ModelCombo("Diseminado", "Diseminado" ) );
                                    modelTipoVia.addElement( new ModelCombo("Grupo", "Grupo" ) );
                                    modelTipoVia.addElement( new ModelCombo("Lugar", "Lugar" ) );
                                    modelTipoVia.addElement( new ModelCombo("Mercado", "Mercado" ) );
                                    modelTipoVia.addElement( new ModelCombo("Parque", "Parque" ) );
                                    modelTipoVia.addElement( new ModelCombo("Partida", "Partida" ) );
                                    modelTipoVia.addElement( new ModelCombo("Pasaje", "Pasaje" ) );
                                    modelTipoVia.addElement( new ModelCombo("Poblado", "Poblado" ) );
                                    modelTipoVia.addElement( new ModelCombo("Rambla", "Rambla" ) );
                                    modelTipoVia.addElement( new ModelCombo("Residencial", "Residencial" ) );
                                    modelTipoVia.addElement( new ModelCombo("Rua", "Rua" ) );
                                    modelTipoVia.addElement( new ModelCombo("Sector", "Sector" ) );
                                    modelTipoVia.addElement( new ModelCombo("Senda", "Senda" ) );
                                    modelTipoVia.addElement( new ModelCombo("Subida", "Subida" ) );
                                    modelTipoVia.addElement( new ModelCombo("Torrente", "Torrente" ) );
                                    modelTipoVia.addElement( new ModelCombo("Travesera", "Travesera" ) );
                                    modelTipoVia.addElement( new ModelCombo("Via", "Via" ) );
                                    modelTipoVia.addElement( new ModelCombo("Carrer", "Carrer" ) );
                                    eTipoVia.setModel(new DefaultComboBoxModel(modelTipoVia));
                                    break;
                case "Regimen":     modelRegimen = new Vector();
                                    modelRegimen.addElement( new ModelCombo("VentaLibre", "VentaLibre   " ) );
                                    modelRegimen.addElement( new ModelCombo("VPO", "VPO" ) );
                                    modelRegimen.addElement( new ModelCombo("VPOAnt78", "VPOAnt78" ) );
                                    modelRegimen.addElement( new ModelCombo("VPOPrivada", "VPOPrivada" ) );
                                    modelRegimen.addElement( new ModelCombo("VPOPublica", "VPOPublica" ) );
                                    modelRegimen.addElement( new ModelCombo("VPT", "VPT" ) );
                                    modelRegimen.addElement( new ModelCombo("Subvencionada", "Subvencionada" ) );
                                    eRegimenProteccion.setModel(new DefaultComboBoxModel(modelRegimen));
                                    break;
                case "Regimen economico":     modelRegimenEconomico = new Vector();
                                    modelRegimenEconomico.addElement( new ModelCombo("Otros", "Otros" ) );
                                    modelRegimenEconomico.addElement( new ModelCombo("Gananciales", "Gananciales" ) );
                                    modelRegimenEconomico.addElement( new ModelCombo("SeparacionBienes", "SeparacionBienes" ) );
                                    modelRegimenEconomico.addElement( new ModelCombo("Participacion", "Participacion" ) );
                                    eRegimenEconomico.setModel(new DefaultComboBoxModel(modelRegimenEconomico));
                                    break;                    
                case "Tipo de participacion":modelTipoParticipacion = new Vector();
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
                                break;                 
                case "Provincia":modelProvincia = new Vector();
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
        int test = 9;
        for(int i=0; i<modelo.size(); i++){
            modeloCombo = (ModelCombo)modelo.elementAt(i);
            if (modeloCombo.getClave().equals(valor)){
                combo.setSelectedIndex(i);
                break;
            }
        }
    }
    
    private void llenarCamposDocumentoGuardado(ArrayList<ComponenteFormulario> listaComponentesCargados){
        eIdufir.setText(listaComponentesCargados.get(4).getValor());
        eReferenciaCatastral.setText(listaComponentesCargados.get(5).getValor());
        eNombreVia.setText(listaComponentesCargados.get(9).getValor());
        eBloqueOPortal.setText(listaComponentesCargados.get(11).getValor());
        ePlanta.setText(listaComponentesCargados.get(12).getValor());
        ePuerta.setText(listaComponentesCargados.get(13).getValor());
        eEscalera.setText(listaComponentesCargados.get(14).getValor());
        eCodigoPostal.setText(listaComponentesCargados.get(15).getValor());
        eSeccion.setText(listaComponentesCargados.get(18).getValor());
        eNumeroFinca.setText("");
        eNumeroFinca.setText(listaComponentesCargados.get(19).getValor());
        eSubfinca.setText(listaComponentesCargados.get(20).getValor());
        eTomo.setText(listaComponentesCargados.get(21).getValor());
        eLibro.setText(listaComponentesCargados.get(22).getValor());
        eFolio.setText(listaComponentesCargados.get(23).getValor());
        eSuperficieConstruida.setText(listaComponentesCargados.get(26).getValor());
        eSuperficieUtil.setText(listaComponentesCargados.get(27).getValor());
        eSuperficieTerreno.setText(listaComponentesCargados.get(28).getValor());
        eCoeficiente.setText(listaComponentesCargados.get(31).getValor());
        eNombreRazon.setText(listaComponentesCargados.get(32).getValor());
        ePrimerApellido.setText(listaComponentesCargados.get(33).getValor());
        eSegundoApellido.setText(listaComponentesCargados.get(34).getValor());
        eDocumento.setText(listaComponentesCargados.get(35).getValor());
        eNumeroProtocolo.setText(listaComponentesCargados.get(39).getValor());
        eNotario.setText(listaComponentesCargados.get(40).getValor());
        eNumeroInscripcion.setText(listaComponentesCargados.get(41).getValor());
        ePorcientoParticipacion.setText(listaComponentesCargados.get(44).getValor());
        
        /*if (listaComponentesCargados.get(41).getValor().equals(""))
            ePorcientoParticipacion.setText("   .  ");
        else ePorcientoParticipacion.setText(Utiles.formaCadenaParaJFormatter(listaComponentesCargados.get(41).getValor(), 3) );*/
        
        //Combo
        seleccionarElementoCombo(modelSubTipoInmueble, eSubtipoInmueble, listaComponentesCargados.get(7).getValor());
        seleccionarElementoCombo(modelTipoVia, eTipoVia, listaComponentesCargados.get(8).getValor());
        seleccionarElementoCombo(modelRegimen, eRegimenProteccion, listaComponentesCargados.get(24).getValor());
        seleccionarElementoCombo(modelTipoParticipacion, eTipoParticipacion, listaComponentesCargados.get(43).getValor());
        seleccionarElementoCombo(modelPoblacion, ePoblacion, listaComponentesCargados.get(29).getValor());
        seleccionarElementoCombo(modelProvincia, eProvincia, listaComponentesCargados.get(30).getValor());
        
        //Fechas
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = null;
            if (!listaComponentesCargados.get(3).getValor().equals("")){
                fecha = formato.parse(listaComponentesCargados.get(3).getValor());
                eFechaVerificación.setDate(fecha);
            }
            if (!listaComponentesCargados.get(25).getValor().equals("")){
                fecha = formato.parse(listaComponentesCargados.get(25).getValor());
                eFechaCalificacion.setDate(fecha);
            }
            if (!listaComponentesCargados.get(39).getValor().equals("")){
                fecha = formato.parse(listaComponentesCargados.get(42).getValor());
                eFechaInscripcionTitular.setDate(fecha);
            }
            if (!listaComponentesCargados.get(35).getValor().equals("")){
                fecha = formato.parse(listaComponentesCargados.get(38).getValor());
                eFechaEscritura.setDate(fecha);
            }
        } catch (ParseException ex) {
            Logger.getLogger(FormaTitular.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (listaComponentesCargados.size() > 45){
            listaTitulares.clear();
            ArrayList<ComponenteFormulario> listaParaTitulares = new ArrayList<>();
            for (int i = 42; i < listaComponentesCargados.size(); i++) {
                listaParaTitulares.add(listaComponentesCargados.get(i));
            }
            int cantidadOtrosTitulares = listaParaTitulares.size()/13;
            ArrayList<ComponenteFormulario> listaParaTitularesTemp = null;
            for (int i = 0; i < cantidadOtrosTitulares; i++) {
                listaParaTitularesTemp = new ArrayList<>();
                for (int indice = i*13; indice < (i*13 + 13); indice++) {
                    listaParaTitularesTemp.add(listaParaTitulares.get(indice));
                }
                listaTitulares.add(listaParaTitularesTemp);
            }
            Utiles.llenarTabla(jTable1,listaTitulares, "Titulares");
        }
    }
    
    private void limpiarComponentesFormulario(){
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
        eNombreRazon.setText("");
        ePrimerApellido.setText("");
        eSegundoApellido.setText("");
        eDocumento.setText("");
        eNumeroProtocolo.setText("");
        eNotario.setText("");
        eNumeroInscripcion.setText("");
        ePorcientoParticipacion.setText("");
        eCoeficiente.setText("");
        
        
        //Combo
        eSubtipoInmueble.setSelectedIndex(0); 
        eTipoVia.setSelectedIndex(0); 
        eRegimenProteccion.setSelectedIndex(0); 
        eRegimenEconomico.setSelectedIndex(0); 
        eTipoParticipacion.setSelectedIndex(0); 
        ePoblacion.setSelectedIndex(0); 
        eProvincia.setSelectedIndex(0); 
        eRegistroPropiedad.setSelectedIndex(0); 

        //Fechas
        Date fecha = null;
        eFechaVerificación.setDate(fecha);
        eFechaCalificacion.setDate(fecha);
        eFechaInscripcionTitular.setDate(fecha);
        eFechaEscritura.setDate(fecha);
        
        listaTitulares.clear();
        listaAnejos.clear();
        listaCargas.clear();
        
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        modelo.setRowCount(0);
    }
    
    private void limpiarComponentes(){
        eNombreXML.setText("");
        listaTitulares.clear();
        listaAnejos.clear();
        listaCargas.clear();
    }
    
    /*
    private Boolean esErronea(){
        
        ModelCombo modeloEstadoSolicitud = (ModelCombo)eEstadoSolicitud.getSelectedItem();
        if (modeloEstadoSolicitud.getClave().equals("E")){
            String referencia = eReferencia.getText();
            String descripcion = eDescripcionError.getText();
            limpiarComponentesFormulario();
            eEstadoSolicitud.setSelectedIndex(1);
            eReferencia.setText(referencia);
            eDescripcionError.setText(descripcion);
            return true;
        }
        return false;    
    } 
    */
    
    
     
    /**
     * Creates new form FormaPrincipal
     */
    public FormaPrincipal() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/images/icono.png")).getImage());
        //setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
        //setLocationRelativeTo(null);
        //jPanel1.setVisible(false);
        
        RestrictedTextField restriccionIdufir = new RestrictedTextField(eIdufir);
        restriccionIdufir.setLimit(15);
        
        RestrictedTextField restriccionReferenciaCatastral = new RestrictedTextField(eReferenciaCatastral);
        restriccionReferenciaCatastral.setLimit(55);
        
        RestrictedTextField restriccionNombreVia = new RestrictedTextField(eNombreVia);
        restriccionNombreVia.setLimit(40);
        
        RestrictedTextField restriccionBloquePortal = new RestrictedTextField(eBloqueOPortal);
        restriccionBloquePortal.setLimit(5);
        
        RestrictedTextField restriccionPlanta = new RestrictedTextField(ePlanta);
        restriccionPlanta.setLimit(5);
        
        RestrictedTextField restriccionPuerta = new RestrictedTextField(ePuerta);
        restriccionPuerta.setLimit(5);
        
        RestrictedTextField restriccionEscalera = new RestrictedTextField(eEscalera);
        restriccionEscalera.setLimit(5);
        
        RestrictedTextField restriccionCodigoPostal = new RestrictedTextField(eCodigoPostal);
        //restriccionCodigoPostal.setOnlyNums(true);
        restriccionCodigoPostal.setLimit(5);
        
        RestrictedTextField restriccionSeccion = new RestrictedTextField(eSeccion);
        restriccionSeccion.setOnlyNums(true);
        restriccionSeccion.setLimit(3);
        
        RestrictedTextField restriccionNombreRazon = new RestrictedTextField(eNombreRazon);
        restriccionNombreRazon.setLimit(137);
        
        RestrictedTextField restriccionPrimerApellido = new RestrictedTextField(ePrimerApellido);
        restriccionPrimerApellido.setLimit(45);
        
        RestrictedTextField restriccionSegundoApellido = new RestrictedTextField(eSegundoApellido);
        restriccionSegundoApellido.setLimit(45);
        
        RestrictedTextField restriccionDocumento = new RestrictedTextField(eDocumento);
        restriccionDocumento.setLimit(10);
        
        RestrictedTextField restriccionNumeroProtocolo = new RestrictedTextField(eNumeroProtocolo);
        restriccionNumeroProtocolo.setLimit(30);
        
        RestrictedTextField restriccionNotario = new RestrictedTextField(eNotario);
        restriccionNotario.setLimit(50);
        
        RestrictedTextField restriccionNumeroInscripcion = new RestrictedTextField(eNumeroInscripcion);
        restriccionNumeroInscripcion.setOnlyNums(true);
        restriccionNumeroInscripcion.setLimit(10);
        
        RestrictedTextField restriccionNumeroFinca = new RestrictedTextField(eNumeroFinca);
        restriccionNumeroFinca.setLimit(6);
        
        RestrictedTextField restriccionNumeroSubFinca = new RestrictedTextField(eSubfinca);
        restriccionNumeroSubFinca.setLimit(5);
        
        RestrictedTextField restriccionTomo = new RestrictedTextField(eTomo);
        restriccionTomo.setOnlyNums(true);
        restriccionTomo.setLimit(4);
        
        RestrictedTextField restriccionLibro = new RestrictedTextField(eLibro);
        restriccionLibro.setOnlyNums(true);
        restriccionLibro.setLimit(4);
        
        RestrictedTextField restriccionFolio = new RestrictedTextField(eFolio);
        restriccionFolio.setOnlyNums(true);
        restriccionFolio.setLimit(6);
        
        RestrictedTextField restriccionRazonSocial = new RestrictedTextField(eNombreRazon);
        restriccionRazonSocial.setLimit(137);
        
        RestrictedTextField restriccionNif = new RestrictedTextField(eDocumento);
        restriccionNif.setLimit(10);
        
        RestrictedTextField restriccionTipoProtocolo = new RestrictedTextField(eNumeroProtocolo);
        restriccionTipoProtocolo.setLimit(20);
        
        eFechaVerificación.setFormat(Resources.DMY);
        eFechaVerificación.setName("FECHA_VERRIF_REG.");
        eFechaVerificación.setNextFocusableComponent(eIdufir);
        eFechaCalificacion.setFormat(Resources.DMY);
        eFechaCalificacion.setName("FECHA_CALIFICACION.");
        eFechaCalificacion.setNextFocusableComponent(eSuperficieConstruida);
        eFechaEscritura.setFormat(Resources.DMY);
        eFechaEscritura.setName("TLO_FECHA_ESCRITURA.");
        eFechaEscritura.setNextFocusableComponent(eNumeroProtocolo);
        eFechaInscripcionTitular.setFormat(Resources.DMY);
        eFechaInscripcionTitular.setName("TLO_FECHA_INSCRIPCION.");
        eFechaInscripcionTitular.setNextFocusableComponent(eTipoParticipacion);
        
        llenarComboTipoInmueble("Tipo inmueble");
        llenarComboTipoInmueble("Subtipo inmueble");
        llenarComboTipoInmueble("Tipo finca");
        llenarComboTipoInmueble("Provincia");
        llenarComboTipoInmueble("Tipo via");
        llenarComboTipoInmueble("Regimen");
        llenarComboTipoInmueble("Tipo de participacion");
        llenarComboTipoInmueble("Estado solicitud");
        llenarComboTipoInmueble("Tipo documento");
        llenarComboTipoInmueble("Tipo de propiedad");
        llenarComboTipoInmueble("Tipo de documento");
        llenarComboTipoInmueble("Provincia");
        llenarComboTipoInmueble("Regimen economico");
        llenarComboTipoInmueble("Tipo de titulo");        
                
        llenarPoblaciones();

        listaTitulares = new ArrayList<>();
        listaCargas = new ArrayList<>();
        listaAnejos = new ArrayList<>();
        
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        //Solo notas
        /*
        jMenuItem1.setVisible(false);
        jMenuItem2.setVisible(false);
        jMenuItem3.setVisible(false);
        jMenuItem4.setVisible(false);
        jMenuItem5.setVisible(false);
        jMenuItem7.setVisible(false);
        jMenuItem8.setVisible(false);
        jMenuItem9.setVisible(false);
        jMenuItem11.setVisible(false);
        jMenuItem12.setVisible(false);
        jMenuItem13.setVisible(false);
        */
        
        //Sin notas
        /*
        jMenuItem11.setVisible(false);
        jMenuItem12.setVisible(false);
        jMenuItem13.setVisible(false);
        jPanel1.setVisible(false);
        */
        
    }
    
    public static ArrayList<Documento> cargarListaDocumentos(){
        ArrayList<Documento> listaDocumentos = new ArrayList<>();
        String caminoDirectorioRaiz = Utiles.rutaEnviadosNotaSimple;
	File dirRaiz = new File(caminoDirectorioRaiz);
        String archivos[] = dirRaiz.list();
        File dirTemp;
        Documento documento;
        for (String archivo : archivos) {
            dirTemp = new File(caminoDirectorioRaiz.concat(archivo));
            documento = new Documento(dirTemp.getName(), "Nota Simple", "Pendiente de procesar", "", "", "");
            listaDocumentos.add(documento);
        }
        return listaDocumentos;
    }
    
    private void actualizarInfoDocumentos(String mensaje){
            new TareaSegundoPlano(this, mensaje) {
                @Override
                    protected void tareaHaRealizar() {
                        listaDocumentos = cargarListaDocumentos();
                        Utiles.llenarTabla(jXTable1, listaDocumentos, "Documentos IRPF");
                        Dimension dimension = jXTable1.getPreferredSize();
                        jScrollPane4.setPreferredSize(new Dimension(dimension.width,jXTable1.getRowHeight()*filas));
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

        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelNombreUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jXFindBar1 = new org.jdesktop.swingx.JXFindBar();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        eNombreXML = new javax.swing.JTextField();
        jButtonLimpiar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        eFechaVerificación = new org.openswing.swing.client.DateControl();
        jLabel8 = new javax.swing.JLabel();
        eIdufir = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        eReferenciaCatastral = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        eSubtipoInmueble = new javax.swing.JComboBox<String>();
        jLabel12 = new javax.swing.JLabel();
        eTipoVia = new javax.swing.JComboBox<String>();
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
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonAdicionarTitular = new javax.swing.JButton();
        jButtonCargas = new javax.swing.JButton();
        jButtonConvertir = new javax.swing.JButton();
        jButtonEliminarTitular = new javax.swing.JButton();
        jButtonModificarTitular = new javax.swing.JButton();
        ePorcientoParticipacion = new javax.swing.JTextField();
        jButtonCargas1 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        eTipoPropiedad = new javax.swing.JComboBox<String>();
        jLabel50 = new javax.swing.JLabel();
        eTipoDocumento = new javax.swing.JComboBox<String>();
        jLabel51 = new javax.swing.JLabel();
        eRegimenEconomico = new javax.swing.JComboBox<String>();
        jButtonAdicionarTitular1 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        eTipoTitulo = new javax.swing.JComboBox<String>();
        eNumeroFinca = new javax.swing.JTextField();
        eSubfinca = new javax.swing.JTextField();
        eTomo = new javax.swing.JTextField();
        eLibro = new javax.swing.JTextField();
        eSuperficieConstruida = new javax.swing.JTextField();
        eSuperficieUtil = new javax.swing.JTextField();
        eSuperficieTerreno = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        ePoblacion = new javax.swing.JComboBox<String>();
        jLabel47 = new javax.swing.JLabel();
        eProvincia = new javax.swing.JComboBox<String>();
        jLabel48 = new javax.swing.JLabel();
        eCoeficiente = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        eRegimenProteccion = new javax.swing.JComboBox<String>();
        jLabel20 = new javax.swing.JLabel();
        eRegistroPropiedad = new javax.swing.JComboBox<String>();
        jLabel30 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TECH ID Solutions: Sistema de extracción notas simples");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(18, 121, 205));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelNombreUsuario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jLabelNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabelNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable1.setEditable(false);
        jXTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXTable1MouseClicked(evt);
            }
        });
        jXTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jXTable1PropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(jXTable1);

        javax.swing.GroupLayout jXFindBar1Layout = new javax.swing.GroupLayout(jXFindBar1);
        jXFindBar1.setLayout(jXFindBar1Layout);
        jXFindBar1Layout.setHorizontalGroup(
            jXFindBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );
        jXFindBar1Layout.setVerticalGroup(
            jXFindBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jButton3.setText("Convertir a Documento KO");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });

        jButton1.setText("Actualizar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXFindBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Nombre XML:");

        eNombreXML.setEditable(false);
        eNombreXML.setFocusCycleRoot(true);
        eNombreXML.setName("NOMBRE_DOCUMENTO"); // NOI18N
        eNombreXML.setNextFocusableComponent(eFechaVerificación);

        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonLimpiarMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Extracción de datos Nota Simple");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información registral", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

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

        jLabel22.setText("Sección:");

        eSeccion.setName("RG_SECCION."); // NOI18N
        eSeccion.setNextFocusableComponent(eNumeroFinca);

        jLabel23.setText("Número finca:");

        jLabel24.setText("Subfinca:");

        jLabel25.setText("Tomo:");

        jLabel26.setText("Libro:");

        jLabel27.setText("Folio:");

        eFolio.setName("RG_FOLIO."); // NOI18N
        eFolio.setNextFocusableComponent(eRegimenProteccion);

        jLabel29.setText("Fecha calificación:");

        jLabel31.setText("Superficie útil:");

        jLabel32.setText("Superficie terreno:");

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

        jLabel44.setText("Fecha inscripción:");

        jLabel45.setText("Tipo de participación:");

        eTipoParticipacion.setName("TLO_TIPO_PARTICIPACION."); // NOI18N
        eTipoParticipacion.setNextFocusableComponent(ePorcientoParticipacion);

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

        jButtonCargas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report_fac_g.png"))); // NOI18N
        jButtonCargas.setText("Cargas");
        jButtonCargas.setToolTipText("Gestionar cargas");
        jButtonCargas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonCargasMousePressed(evt);
            }
        });

        jButtonConvertir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/restaura_bd_g.png"))); // NOI18N
        jButtonConvertir.setText("Salvar");
        jButtonConvertir.setToolTipText("Convertir a XML");
        jButtonConvertir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonConvertirMousePressed(evt);
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

        ePorcientoParticipacion.setName("TLO_PORC_PARTICIPACION"); // NOI18N
        ePorcientoParticipacion.setNextFocusableComponent(eTipoTitulo);
        ePorcientoParticipacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ePorcientoParticipacionKeyTyped(evt);
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

        jLabel49.setText("Tipo de propiedad:");

        eTipoPropiedad.setName("TLO_TIPO_PROPIEDAD."); // NOI18N
        eTipoPropiedad.setNextFocusableComponent(eRegimenEconomico);

        jLabel50.setText("Tipo de documento:");

        eTipoDocumento.setName("TIPO_DOCUMENTO"); // NOI18N
        eTipoDocumento.setNextFocusableComponent(eDocumento);

        jLabel51.setText("Régimen económico:");

        eRegimenEconomico.setName("REGIMEN_ECONOMICO"); // NOI18N

        jButtonAdicionarTitular1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        jButtonAdicionarTitular1.setText("Título");
        jButtonAdicionarTitular1.setToolTipText("Adicionar titular");
        jButtonAdicionarTitular1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAdicionarTitular1MousePressed(evt);
            }
        });

        jLabel52.setText("Tipo de título:");

        eTipoTitulo.setName("TLO_TIPO_TITULO."); // NOI18N
        eTipoTitulo.setNextFocusableComponent(eTipoPropiedad);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel35)
                                .addComponent(jLabel34)
                                .addComponent(jLabel36))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ePrimerApellido)
                                .addComponent(eSegundoApellido)
                                .addComponent(eNombreRazon, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGap(13, 13, 13)
                                    .addComponent(jLabel50))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel37)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eDocumento)
                                .addComponent(eTipoDocumento, 0, 134, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel46)
                            .addComponent(jLabel45)
                            .addComponent(jLabel49)
                            .addComponent(jLabel52)
                            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAdicionarTitular1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonModificarTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonCargas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonEliminarTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonCargas1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonAdicionarTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonConvertir, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(eTipoParticipacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ePorcientoParticipacion)
                                .addComponent(eTipoPropiedad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eRegimenEconomico, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eTipoTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabel44))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eNotario)
                            .addComponent(eNumeroProtocolo)
                            .addComponent(eNumeroInscripcion)
                            .addComponent(eFechaInscripcionTitular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eFechaEscritura, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eTipoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(ePorcientoParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eTipoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eTipoPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eRegimenEconomico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(eFechaEscritura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eNumeroProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel41))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(eNotario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNumeroInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44)
                            .addComponent(eFechaInscripcionTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdicionarTitular1)
                    .addComponent(jButtonModificarTitular)
                    .addComponent(jButtonEliminarTitular)
                    .addComponent(jButtonAdicionarTitular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCargas)
                    .addComponent(jButtonCargas1)
                    .addComponent(jButtonConvertir)))
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
        eCoeficiente.setNextFocusableComponent(eNombreRazon);
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

        jLabel30.setText("Superficie construida:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addComponent(jLabel23)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eFechaVerificación, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(eNumeroFinca, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                .addComponent(eIdufir, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eReferenciaCatastral, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eSuperficieConstruida, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eSuperficieUtil, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eNombreVia, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eSuperficieTerreno, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel48))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eTomo, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(eLibro)
                            .addComponent(eFolio, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eCoeficiente)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel24)
                        .addGap(2, 2, 2)
                        .addComponent(eSubfinca, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel22)
                                .addComponent(jLabel15)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17)
                                .addComponent(jLabel18)
                                .addComponent(jLabel19))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(ePlanta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                                .addComponent(eCodigoPostal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eEscalera, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ePuerta, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(eSeccion)
                                .addComponent(eBloqueOPortal, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eFechaCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel47)
                                        .addComponent(jLabel33)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel12))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(ePoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(eProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(eRegistroPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(eSubtipoInmueble, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(eTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(67, 67, 67)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eRegimenProteccion, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eSuperficieConstruida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eSuperficieUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(eSuperficieTerreno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eNombreVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(eNumeroFinca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(eSubfinca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(eTomo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(eFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48)
                            .addComponent(eCoeficiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eBloqueOPortal)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(ePlanta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ePuerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(eEscalera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(eSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eRegimenProteccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ePoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(eRegistroPropiedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(eSubtipoInmueble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(eTipoVia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        eIdufir.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eNombreXML, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel7)))
                .addGap(127, 127, 127))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(681, 681, 681))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(eNombreXML, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(631, 631, 631))
        );

        jMenu1.setText("Tipo de Documento");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenu1MouseReleased(evt);
            }
        });

        jMenuItem1.setText("IRPF");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Vida laboral");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Nómina");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Recibo");
        jMenuItem5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem5MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem4.setText("Tasación");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem6.setText("Documento KO");
        jMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem6MouseReleased(evt);
            }
        });
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);
        jMenu1.add(jSeparator1);

        jMenuItem7.setText("Notas Simples a Excel (3000)");
        jMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem7MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuItem8.setText("Notas Simples OCR CAIXA");
        jMenuItem8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem8MouseReleased(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Descargar y subir documentos");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenu2MouseReleased(evt);
            }
        });

        jMenuItem16.setText("Descargar Vida laboral");
        jMenuItem16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem16MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem16);

        jMenuItem17.setText("Descargar IRPF");
        jMenuItem17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem17MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenuItem18.setText("Descargar Nóminas");
        jMenuItem18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem18MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuItem19.setText("Descargar Recibos");
        jMenuItem19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem19MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem19);

        jMenuItem9.setText("Descargar (Vida laboral, IRPF, Nóminas, Recibos)");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem9MouseReleased(evt);
            }
        });
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem14.setText("Descargar Notas Simples");
        jMenuItem14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem14MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuItem15.setText("Descargar Tasaciones");
        jMenuItem15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem15MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem15);
        jMenu2.add(jSeparator4);

        jMenuItem10.setText("Subir documentos (Vida laboral, IRPF, Nóminas, Recibos, Notas Simples, Tasaciones)");
        jMenuItem10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem10MouseReleased(evt);
            }
        });
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);
        jMenu2.add(jSeparator2);

        jMenuItem11.setText("Descargar Notas Simples OCR Pendientes de Revisión");
        jMenuItem11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem11MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem12.setText("Subir Notas Simples OCR Revisadas");
        jMenuItem12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem12MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem12);
        jMenu2.add(jSeparator3);

        jMenuItem13.setText("Descargar Documentos KO OCR");
        jMenuItem13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem13MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem13);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        FormaLogin formaLogin = new FormaLogin(this, true);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaLogin.setLocation(GEnv.getCenterPoint().x-formaLogin.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaLogin.getHeight()/2 + 25);
        formaLogin.setResizable(false);
        formaLogin.setVisible(true);
        
        if ( usuario != null)
            jLabelNombreUsuario.setText(usuario.getNombre());
        
        JTableHeader th = jXTable1.getTableHeader();
        Font fuente = new Font(th.getFont().getName(), Font.BOLD, 11);
        th.setFont(fuente);

//        jXTable1.setAutoResizeMode(JXTable.AUTO_RESIZE_OFF);
//        TableColumn columna = jXTable1.getColumn("Nombre");
//        columna.setPreferredWidth(660);
//        columna = jXTable1.getColumn("Estado");
//        columna.setPreferredWidth(360);
        
        jXTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jXTable1.setColumnControlVisible(true); 
        jXFindBar1.setSearchable(jXTable1.getSearchable());
        jXTable1.setColumnControlVisible(true);
        TableRowFilterSupport.forTable(jXTable1).searchable(true).apply();
        
        jXTable1.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e){
           if (jXTable1.getSelectedRow() != -1){ 
            elementoSeleccionadoTabla = jXTable1.getSelectedRow();
            jXTable1.setRowSelectionInterval(jXTable1.rowAtPoint(e.getPoint()), jXTable1.rowAtPoint(e.getPoint()));
           }
          
        }
        });
        
        eNumeroFinca.setDocument(new LimitarNumeros());
        eSubfinca.setDocument(new LimitarNumeros());
        eTomo.setDocument(new LimitarNumeros());
        eLibro.setDocument(new LimitarNumeros());
        eFolio.setDocument(new LimitarNumeros());
        
        actualizarInfoDocumentos(Utiles.msgTareaCargandoDocumentos);
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //Utiles.guardarListaDocumentos(listaDocumentos);
    }//GEN-LAST:event_formWindowClosing

    private void jButtonLimpiarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLimpiarMousePressed
        limpiarComponentesFormulario();
    }//GEN-LAST:event_jButtonLimpiarMousePressed

    private void jXTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jXTable1PropertyChange
        
    }//GEN-LAST:event_jXTable1PropertyChange

    private void jMenuItem1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseReleased
        FormaProcesarIRPF formaProcesarIRPF = new FormaProcesarIRPF(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaProcesarIRPF.setLocation(GEnv.getCenterPoint().x-formaProcesarIRPF.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaProcesarIRPF.getHeight()/2 + 25);

        formaProcesarIRPF.setResizable(true);
        formaProcesarIRPF.setVisible(true);
    }//GEN-LAST:event_jMenuItem1MouseReleased

    private void jMenuItem2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseReleased
        FormaProcesarVidaLaboral formaProcesarVidaLaboral = new FormaProcesarVidaLaboral(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaProcesarVidaLaboral.setLocation(GEnv.getCenterPoint().x-formaProcesarVidaLaboral.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaProcesarVidaLaboral.getHeight()/2 + 25);

        formaProcesarVidaLaboral.setResizable(true);
        formaProcesarVidaLaboral.setVisible(true);
    }//GEN-LAST:event_jMenuItem2MouseReleased

    private void jMenuItem3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseReleased
        FormaProcesarNomina formaProcesarNomina = new FormaProcesarNomina(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaProcesarNomina.setLocation(GEnv.getCenterPoint().x-formaProcesarNomina.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaProcesarNomina.getHeight()/2 + 25);

        formaProcesarNomina.setResizable(true);
        formaProcesarNomina.setVisible(true);
    }//GEN-LAST:event_jMenuItem3MouseReleased

    private void jMenuItem4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseReleased
        if (jMenuItem4.isEnabled()){
            FormaProcesarTasacion formaProcesarTasacion = new FormaProcesarTasacion(this, false);
            GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            formaProcesarTasacion.setLocation(GEnv.getCenterPoint().x-formaProcesarTasacion.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaProcesarTasacion.getHeight()/2 + 25);

            formaProcesarTasacion.setResizable(true);
            formaProcesarTasacion.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem4MouseReleased

    private void jMenuItem5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem5MouseReleased
        if (jMenuItem5.isEnabled()){
        FormaProcesarRecibo formaProcesarRecibo = new FormaProcesarRecibo(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaProcesarRecibo.setLocation(GEnv.getCenterPoint().x-formaProcesarRecibo.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaProcesarRecibo.getHeight()/2 + 25);

        formaProcesarRecibo.setResizable(true);
        formaProcesarRecibo.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem5MouseReleased

    private void jMenuItem6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem6MouseReleased
        FormaProcesarDocumentosKO formaProcesarDocumentosKO = new FormaProcesarDocumentosKO(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaProcesarDocumentosKO.setLocation(GEnv.getCenterPoint().x-formaProcesarDocumentosKO.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaProcesarDocumentosKO.getHeight()/2 + 25);

        formaProcesarDocumentosKO.setResizable(true);
        formaProcesarDocumentosKO.setVisible(true);
    }//GEN-LAST:event_jMenuItem6MouseReleased

    private void jMenu1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseReleased
        FormaNotaSimpleAExcel formaNotaSimpleAExcel = new FormaNotaSimpleAExcel(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaNotaSimpleAExcel.setLocation(GEnv.getCenterPoint().x-formaNotaSimpleAExcel.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaNotaSimpleAExcel.getHeight()/2 + 25);

        formaNotaSimpleAExcel.setResizable(true);
        formaNotaSimpleAExcel.setVisible(true);
    }//GEN-LAST:event_jMenu1MouseReleased

    private void jMenuItem7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem7MouseReleased
        FormaNotaSimpleAExcel formaNotaSimpleAExcel = new FormaNotaSimpleAExcel(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaNotaSimpleAExcel.setLocation(GEnv.getCenterPoint().x-formaNotaSimpleAExcel.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaNotaSimpleAExcel.getHeight()/2 + 25);

        formaNotaSimpleAExcel.setResizable(true);
        formaNotaSimpleAExcel.setVisible(true);
    }//GEN-LAST:event_jMenuItem7MouseReleased

    private void jMenuItem8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem8MouseReleased
        FormaProcesarNotaSimpleCaixa formaProcesarNotaSimpleCaixa = new FormaProcesarNotaSimpleCaixa(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaProcesarNotaSimpleCaixa.setLocation(GEnv.getCenterPoint().x-formaProcesarNotaSimpleCaixa.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaProcesarNotaSimpleCaixa.getHeight()/2 + 25);

        formaProcesarNotaSimpleCaixa.setResizable(true);
        formaProcesarNotaSimpleCaixa.setVisible(true);
        
        
    }//GEN-LAST:event_jMenuItem8MouseReleased

    private void jButtonAdicionarTitularMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAdicionarTitularMousePressed
        BuildTitularData();
        FormaTitular formaTitular = new FormaTitular(this, true, 1, -1);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaTitular.setLocation(GEnv.getCenterPoint().x-formaTitular.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaTitular.getHeight()/2 + 25);

        formaTitular.setResizable(false);
        formaTitular.setVisible(true);

        Utiles.llenarTabla(jTable1,listaTitulares, "Titulares");
    }//GEN-LAST:event_jButtonAdicionarTitularMousePressed

    private void jButtonCargasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCargasMousePressed
        FormaCargas formaCargas = new FormaCargas(this, true);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaCargas.setLocation(GEnv.getCenterPoint().x-formaCargas.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaCargas.getHeight()/2 + 25);

        //formaCargas.setResizable(false);
        formaCargas.setVisible(true);
    }//GEN-LAST:event_jButtonCargasMousePressed

    private void jButtonConvertirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonConvertirMousePressed
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea Convertir?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0){
            JComponent comp = Utiles.ValidaControles(jPanel5);
            JComponent comp1 = Utiles.ValidaControles(jPanel1);
            JComponent comp2 = Utiles.ValidaControles(jPanel6);
            if (comp == null){
                if (comp1 == null){
                    if (comp2 == null){
                      if (Utiles.validarFecha(eFechaVerificación) && Utiles.validarFecha(eFechaCalificacion) && Utiles.validarFecha(eFechaEscritura) && Utiles.validarFecha(eFechaInscripcionTitular)) {  
                //esErronea();
                        new TareaSegundoPlano(this, Utiles.msgTareaRealizandoConversion) {
                            @Override
                            protected void tareaHaRealizar() {
                                File archivo = new File(Utiles.rutaEnviadosNotaSimple.concat(eNombreXML.getText()));
                                Boolean movido = archivo.renameTo(new File(Utiles.rutaProcesadosNotaSimplePDF.concat(eNombreXML.getText())));
                                if (movido){
                                    Utiles.generarXML(eNombreXML, listaTitulares, "Convertir", listaAnejos, listaCargas);
                                    listaDocumentos.remove(elementoSeleccionadoTabla);
                                    DefaultTableModel modelo = (DefaultTableModel)jXTable1.getModel();
                                    modelo.removeRow(elementoSeleccionadoTabla);
                                    jXTable1.setModel(modelo);
                                    limpiarComponentes();
                                }else JOptionPane.showMessageDialog(null,"Debe cerrar el documento PDF antes de convertir.");

                            }
                        }.ejecutarTarea();

                      } else {
                        JOptionPane.showMessageDialog(null,"Ha definido alguna fecha incorrectamente.");
                      }   

                    } else {
                        if (comp2 instanceof DateControl && ((DateControl) comp2).getValue() != null) {
                            JOptionPane.showMessageDialog(null, Utiles.msgFechaIncorrecta + comp2.getName());
                            comp2.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(null, Utiles.msgDebeIntroducir + comp2.getName());
                            comp.requestFocus();
                        }
                    }
                } else {
                    if (comp1 instanceof DateControl && ((DateControl) comp1).getValue() != null) {
                        JOptionPane.showMessageDialog(null, Utiles.msgFechaIncorrecta + comp1.getName());
                        comp1.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(null, Utiles.msgDebeIntroducir + comp1.getName());
                        comp1.requestFocus();
                    }
                }
            } else {
                if (comp instanceof DateControl && ((DateControl) comp).getValue() != null) {
                    JOptionPane.showMessageDialog(null, Utiles.msgFechaIncorrecta + comp.getName());
                    comp.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, Utiles.msgDebeIntroducir + comp.getName());
                    comp.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_jButtonConvertirMousePressed

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
            FormaTitular formaTitular = new FormaTitular(this, true, 2,indiceSeleccionado);
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

    private void jButtonCargas1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCargas1MousePressed
        FormaAnejo formaAnejo = new FormaAnejo(this, true);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaAnejo.setLocation(GEnv.getCenterPoint().x-formaAnejo.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaAnejo.getHeight()/2 + 25);
        formaAnejo.setResizable(false);
        formaAnejo.setVisible(true);
    }//GEN-LAST:event_jButtonCargas1MousePressed

    private void jButtonCargas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCargas1ActionPerformed

    private void eTomoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eTomoKeyTyped
        //System.out.println(evt.getKeyChar());
        //System.out.println(evt.getKeyCode());

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
        
        ModelCombo modelo = (ModelCombo)ePoblacion.getSelectedItem();
        Boolean indiceAsignado = false;
        for (Poblacion poblacion : listaPoblaciones) {
            if (modelo.getClave().equals(poblacion.getCodigo())){
                int indice = 0;
                for (Provincia provincia : listaProvincias) {
                    if (poblacion.getCodigoProvincia().equals(provincia.getCodigo())){
                        eProvincia.setSelectedIndex(indice);
                        indiceAsignado = true;
                        break;
                    }
                    indice++;
                }
            }
            if (indiceAsignado)
            break;
        }
        
    }//GEN-LAST:event_ePoblacionActionPerformed

    private void eCoeficienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eCoeficienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_eCoeficienteKeyTyped

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        if (jXTable1.getSelectedRow() != -1){
            new TareaSegundoPlano(this, Utiles.msgTareaProcesandoDocumento) {
                @Override
                protected void tareaHaRealizar() {
                    elementoSeleccionadoTabla = jXTable1.getSelectedRow();
                    documentoSeleccionado = listaDocumentos.get(elementoSeleccionadoTabla);
                    File archivo = new File(Utiles.rutaEnviadosNotaSimple.concat(documentoSeleccionado.getNombre()));
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

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        actualizarInfoDocumentos(Utiles.msgTareaCargandoDocumentos);
    }//GEN-LAST:event_jButton1MousePressed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea descargar documentos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaDescargarArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.copiarFichPdfFromFTPbySSH(this, listaDocumentos, usuario.getNombreCompleto());
                jButton1MousePressed(null);
            }
        }.ejecutarTarea();
    }//GEN-LAST:event_jMenuItem9MouseReleased

    private void jMenuItem10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem10MouseReleased
       int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea subir documentos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaSubiendoArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.copiarFichXMLToFTPbySSH(usuario.getNombreCompleto());
            }
        }.ejecutarTarea();
    }//GEN-LAST:event_jMenuItem10MouseReleased

    private void jMenuItem13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem13MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea descargar documentos KO OCR?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaDescargarArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.descargarKOOCR(this, listaDocumentos, usuario.getNombreCompleto());
                jButton1MousePressed(null);
            }
        }.ejecutarTarea();
    }//GEN-LAST:event_jMenuItem13MouseReleased

    private void jMenuItem11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem11MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea descargar documentos pendientes OCR?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
            new TareaSegundoPlano(this, Utiles.msgTareaDescargarArchivos) {
                @Override
                protected void tareaHaRealizar() {
                    Utiles.descargarXmlPendientesOCR();
                    //jButton4MousePressed(null);
                }
            }.ejecutarTarea();
    }//GEN-LAST:event_jMenuItem11MouseReleased

    private void jMenuItem12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem12MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea subir documentos pendientes OCR?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaSubiendoArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.subirXMLPendientesOCR(usuario.getNombreCompleto());
            }
        }.ejecutarTarea();
    }//GEN-LAST:event_jMenuItem12MouseReleased

    private void jButtonAdicionarTitular1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAdicionarTitular1MousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1){
            FormaTitular formaTitular = new FormaTitular(this, true, 3,indiceSeleccionado);
            GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            formaTitular.setLocation(GEnv.getCenterPoint().x-formaTitular.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaTitular.getHeight()/2 + 25);

            formaTitular.setResizable(false);
            formaTitular.setVisible(true);

            Utiles.llenarTabla(jTable1,listaTitulares, "Titulares");
        } else JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneTitularModificar);
    }//GEN-LAST:event_jButtonAdicionarTitular1MousePressed

    private void jMenuItem14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem14MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea descargar documentos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaDescargarArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.copiarNotasSimplesFromFTP(this, listaDocumentos, usuario.getNombreCompleto());
                jButton1MousePressed(null);
            }
        }.ejecutarTarea();
    }//GEN-LAST:event_jMenuItem14MouseReleased

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem15MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea descargar documentos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaDescargarArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.copiarTasacionesFromFTP(this, listaDocumentos, usuario.getNombreCompleto());
                jButton1MousePressed(null);
            } 
        }.ejecutarTarea();
    }//GEN-LAST:event_jMenuItem15MouseReleased

    private void jMenu2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2MouseReleased

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void eSuperficieConstruidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eSuperficieConstruidaFocusLost
         if (!eSuperficieConstruida.getText().equals(""))
            if (eSuperficieConstruida.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eSuperficieConstruida.requestFocus();
            }
    }//GEN-LAST:event_eSuperficieConstruidaFocusLost

    private void eSuperficieUtilFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eSuperficieUtilFocusLost
        if (!eSuperficieUtil.getText().equals(""))
            if (eSuperficieUtil.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eSuperficieUtil.requestFocus();
            }
    }//GEN-LAST:event_eSuperficieUtilFocusLost

    private void eSuperficieTerrenoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eSuperficieTerrenoFocusLost
        if (!eSuperficieTerreno.getText().equals(""))
            if (eSuperficieTerreno.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eSuperficieTerreno.requestFocus();
            }
    }//GEN-LAST:event_eSuperficieTerrenoFocusLost

    private void jMenuItem16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem16MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea descargar documentos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaDescargarArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.copiarVidaLaboralFromFTP(this, listaDocumentos, usuario.getNombreCompleto());
                jButton1MousePressed(null);
            }
        }.ejecutarTarea();
//        
    }//GEN-LAST:event_jMenuItem16MouseReleased

    private void jMenuItem17MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem17MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea descargar documentos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaDescargarArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.copiarIRPFFromFTP(this, listaDocumentos, usuario.getNombreCompleto());
                jButton1MousePressed(null);
            }
        }.ejecutarTarea();
    }//GEN-LAST:event_jMenuItem17MouseReleased

    private void jMenuItem18MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem18MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea descargar documentos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaDescargarArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.copiarNominasFromFTP(this, listaDocumentos, usuario.getNombreCompleto());
                jButton1MousePressed(null);
            }
        }.ejecutarTarea();        
    }//GEN-LAST:event_jMenuItem18MouseReleased

    private void jMenuItem19MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem19MouseReleased
        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea descargar documentos?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0)
        new TareaSegundoPlano(this, Utiles.msgTareaDescargarArchivos) {
            @Override
            protected void tareaHaRealizar() {
                Utiles.copiarRecibosFromFTP(this, listaDocumentos, usuario.getNombreCompleto());
                jButton1MousePressed(null);
            }
        }.ejecutarTarea();          
    }//GEN-LAST:event_jMenuItem19MouseReleased

    private void jXTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTable1MouseClicked
        // TODO add your handling code here:
        
        if(evt.getClickCount() > 1)
        {
            if (jXTable1.getSelectedRow() != -1){
            new TareaSegundoPlano(this, Utiles.msgTareaProcesandoDocumento) {
                @Override
                protected void tareaHaRealizar() {
                    elementoSeleccionadoTabla = jXTable1.getSelectedRow();
                    documentoSeleccionado = listaDocumentos.get(elementoSeleccionadoTabla);
                    limpiarComponentesFormulario();
                    eNombreXML.setText(documentoSeleccionado.getNombre());
                    //eReferencia.setText(documentoSeleccionado.getNombre().substring(0, documentoSeleccionado.getNombre().length()-4).split("_")[0]);
                    jXTable1.clearSelection();
                    File archivo = new File(Utiles.rutaEnviadosNotaSimple.concat("\\").concat(documentoSeleccionado.getNombre()));

                        System.out.print(Utiles.rutaEnviadosNotaSimple);
                        try {
                            Desktop.getDesktop().open(archivo);
                        }catch (IOException ex) {
                        }
                    }
                }.ejecutarTarea();
            }else JOptionPane.showMessageDialog(null,"Debe seleccionar un documento de la lista.");
        }
    }//GEN-LAST:event_jXTable1MouseClicked

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
            java.util.logging.Logger.getLogger(FormaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField eBloqueOPortal;
    private javax.swing.JTextField eCodigoPostal;
    private javax.swing.JTextField eCoeficiente;
    private javax.swing.JTextField eDocumento;
    private javax.swing.JTextField eEscalera;
    private org.openswing.swing.client.DateControl eFechaCalificacion;
    private org.openswing.swing.client.DateControl eFechaEscritura;
    private org.openswing.swing.client.DateControl eFechaInscripcionTitular;
    private org.openswing.swing.client.DateControl eFechaVerificación;
    private javax.swing.JTextField eFolio;
    private javax.swing.JTextField eIdufir;
    private javax.swing.JTextField eLibro;
    private javax.swing.JTextField eNombreRazon;
    private javax.swing.JTextField eNombreVia;
    private javax.swing.JTextField eNombreXML;
    private javax.swing.JTextField eNotario;
    private javax.swing.JTextField eNumeroFinca;
    private javax.swing.JTextField eNumeroInscripcion;
    private javax.swing.JTextField eNumeroProtocolo;
    private javax.swing.JTextField ePlanta;
    private javax.swing.JComboBox<String> ePoblacion;
    private javax.swing.JTextField ePorcientoParticipacion;
    private javax.swing.JTextField ePrimerApellido;
    private javax.swing.JComboBox<String> eProvincia;
    private javax.swing.JTextField ePuerta;
    private javax.swing.JTextField eReferenciaCatastral;
    private javax.swing.JComboBox<String> eRegimenEconomico;
    private javax.swing.JComboBox<String> eRegimenProteccion;
    private javax.swing.JComboBox<String> eRegistroPropiedad;
    private javax.swing.JTextField eSeccion;
    private javax.swing.JTextField eSegundoApellido;
    private javax.swing.JTextField eSubfinca;
    private javax.swing.JComboBox<String> eSubtipoInmueble;
    private javax.swing.JTextField eSuperficieConstruida;
    private javax.swing.JTextField eSuperficieTerreno;
    private javax.swing.JTextField eSuperficieUtil;
    private javax.swing.JComboBox<String> eTipoDocumento;
    private javax.swing.JComboBox<String> eTipoParticipacion;
    private javax.swing.JComboBox<String> eTipoPropiedad;
    private javax.swing.JComboBox<String> eTipoTitulo;
    private javax.swing.JComboBox<String> eTipoVia;
    private javax.swing.JTextField eTomo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAdicionarTitular;
    private javax.swing.JButton jButtonAdicionarTitular1;
    private javax.swing.JButton jButtonCargas;
    private javax.swing.JButton jButtonCargas1;
    private javax.swing.JButton jButtonConvertir;
    private javax.swing.JButton jButtonEliminarTitular;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonModificarTitular;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNombreUsuario;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXFindBar jXFindBar1;
    private org.jdesktop.swingx.JXTable jXTable1;
    // End of variables declaration//GEN-END:variables

    private void BuildTitularData() {
        JComponent compTemp = (JComponent) eNombreRazon;
        String atributo;
        if (listaTitularData == null) {
            listaTitularData = new ArrayList<ComponenteFormulario>();
        } else {
            listaTitularData.clear();
        }

        while (compTemp != null) {
            atributo = compTemp.getName();
            if (atributo.endsWith(".")) {
                atributo = atributo.substring(0, atributo.length() - 1);
            }
            if (compTemp instanceof JTextField) {
                listaTitularData.add(new ComponenteFormulario(atributo, ((JTextField) compTemp).getText()));
            } else if (compTemp instanceof JComboBox) {
                listaTitularData.add(new ComponenteFormulario(atributo, ((ModelCombo) ((JComboBox) compTemp).getSelectedItem()).getClave()));
            } else if (compTemp instanceof JFormattedTextField) {
                listaTitularData.add(new ComponenteFormulario(atributo, ((JFormattedTextField) compTemp).getText()));
            } else if (compTemp instanceof DateControl) {
                if (((DateControl) compTemp).getDate() != null) {
                    listaTitularData.add(new ComponenteFormulario(atributo, Utiles.convertirFechaYYYYMMDD(((DateControl) compTemp).getDate())));
                } else {
                    listaTitularData.add(new ComponenteFormulario(atributo, ""));
                }
            }
            compTemp = (JComponent) compTemp.getNextFocusableComponent();
        }
    }

    /**
     * @return the listaTitularData
     */
    public ArrayList<ComponenteFormulario> getListaTitularData() {
        return listaTitularData;
    }

    /**
     * @param listaTitularData the listaTitularData to set
     */
    public void setListaTitularData(ArrayList<ComponenteFormulario> listaTitularData) {
        this.listaTitularData = listaTitularData;
    }
}
