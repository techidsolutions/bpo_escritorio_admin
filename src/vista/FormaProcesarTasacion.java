/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Atxy2k.CustomTextField.RestrictedTextField;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ComponenteFormulario;
import modelo.Documento;
import modelo.Finca;
import modelo.ModelCombo;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.internationalization.java.Resources;
import util.ComboboxToolTipRenderer;
import util.ModelTooltips;
import util.TareaSegundoPlano;
import util.Utiles;
import static vista.FormaPrincipal.cargarListaDocumentos;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaProcesarTasacion extends javax.swing.JDialog {
    Vector modelOcupacion;
    Vector modelTipoIdentificacion;
    Vector modelProvincia;
    Vector modelRegimen;
    Vector modelAnnoConstruccion;
    Vector modelEstadoConservacion;
    Vector modelAdvertencias;
    Vector modelCondicionantes;
    Vector modelFuente;
    Vector modelFinalidad;
    Vector modelLeyMercado;
    Vector modelMetodoValoracion;
    Vector modelMetodoValoracionEstadistico;
    Vector modelVisitaInmueble;
    Vector modelEmpresaTasadora;
    DefaultListModel listaAvertencias = new DefaultListModel();
    DefaultListModel listaCondicionantes = new DefaultListModel();
    private List<String> listaAdvertenciasSeleccionada;
    private List<String> listaCondicionantesSeleccionada;
    final int filas = 20;
    Documento documentoSeleccionado;
    ArrayList<Documento> listaDocumentos;
    int elementoSeleccionadoTabla = -1;
    
    private ArrayList<ArrayList<ComponenteFormulario>> listaTitulares; 
    private ArrayList<ArrayList<ComponenteFormulario>> listaCargas; 
    private ArrayList<ArrayList<ComponenteFormulario>> listaAnejos; 
    //private ArrayList<ArrayList<ComponenteFormulario>> listaFincas;
    private ArrayList<Finca> listaFincas;
    private ArrayList<ModelTooltips> listaModelTooltips;
    private ArrayList<ModelTooltips> listaModelTooltipsCondicionantes;

    private ArrayList<ComponenteFormulario> datosEmpresaTasadora = new ArrayList<ComponenteFormulario>();

    public ArrayList<ArrayList<ComponenteFormulario>> getListaTitulares() {
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

    public ArrayList<Finca> getListaFincas() {
        return listaFincas;
    }

    public void setListaFincas(ArrayList<Finca> listaFincas) {
        this.listaFincas = listaFincas;
    }

    public List<String> getListaAdvertenciasSeleccionada() {
        return listaAdvertenciasSeleccionada;
    }

    public void setListaAdvertenciasSeleccionada(List<String> listaAdvertenciasSeleccionada) {
        this.listaAdvertenciasSeleccionada = listaAdvertenciasSeleccionada;
    }

    public List<String> getListaCondicionantesSeleccionada() {
        return listaCondicionantesSeleccionada;
    }

    public void setListaCondicionantesSeleccionada(List<String> listaCondicionantesSeleccionada) {
        this.listaCondicionantesSeleccionada = listaCondicionantesSeleccionada;
    }
    
    /**
     * Creates new form FormaProcesarIRPF
     * @param parent
     * @param modal
     */
    public FormaProcesarTasacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        /*
        RestrictedTextField restriccionSeccion = new RestrictedTextField(eSeccion);
        restriccionSeccion.setLimit(5);
        restriccionSeccion.setOnlyNums(true);
        */
        
        eFechaCaducidad.setFormat(Resources.DMY);
        eFechaCaducidad.setName("FECHA_CADUCIDAD");
        eFechaCaducidad.setNextFocusableComponent(eFechaTasacion);
        
        eFechaTasacion.setFormat(Resources.DMY);
        eFechaTasacion.setName("FECHA_TASACION");
        eFechaTasacion.setNextFocusableComponent(eFechaValoracionEstadistico);
        
        eFechaValoracionEstadistico.setFormat(Resources.DMY);
        eFechaValoracionEstadistico.setName("FECHA_VALORACION_ESTADISTICO.");
        eFechaValoracionEstadistico.setNextFocusableComponent(eFechaVisita);
        
        eFechaVisita.setFormat(Resources.DMY);
        eFechaVisita.setName("FECHA_VISITA");
        eFechaVisita.setNextFocusableComponent(eFinalidadTasacion);
        listaFincas = new ArrayList<>();
        
        listaAdvertenciasSeleccionada = new ArrayList<>();
        listaCondicionantesSeleccionada = new ArrayList<>();
        
        //AutoCompleteDecorator.decorate(eCapturarAvertencias);
    }
    
    private void limpiarComponentes(){
        eCodigoTasacion.setText("");
        eCodigoTasacionEstadistica.setText("");
        eNombreDocumento.setText("");
        eNombreSolicitante.setText("");
        eNombreTasador.setText("");
        eObservaciones.setText("");
        eValorHipotecario.setText("");
        eValorTasacion.setText("");
        eValorTasacionEstadistico.setText("");
        
        //Combo
        eFinalidadTasacion.setSelectedIndex(3);
        eFuenteDatos.setSelectedIndex(0);
        eLeyMercado.setSelectedIndex(0);
        eMetodoValoracion.setSelectedIndex(2);
        eMetodoValoracionEstadistico.setSelectedIndex(0);
        eVisitaInmueble.setSelectedIndex(0);
        eEmpresaTasadora.setSelectedIndex(0);

        //Fechas
        Date fecha = null;
        eFechaCaducidad.setDate(fecha);
        eFechaTasacion.setDate(fecha);
        eFechaValoracionEstadistico.setDate(fecha);
        eFechaVisita.setDate(fecha);
        
        //List
        eCondicionantes.setModel(new DefaultListModel<String>());
        eAdvertencias.setModel(new DefaultListModel<String>());
        
        listaAdvertenciasSeleccionada.clear();
        listaCondicionantesSeleccionada.clear();
        listaFincas.clear();
        
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        modelo.setRowCount(0);

        SelectEmpresaTasadoraInfo(0);
    }
    
    private void limpiarComponentesFormulario(){
        eNombreDocumento.setText("");
        listaFincas.clear();
        DefaultTableModel modelo = (DefaultTableModel)jTable1.getModel();
        modelo.setRowCount(0);
    }
    
    /**
     * 
     * @param TipoCombo 
     */
    private void llenarCombo(String TipoCombo){
        switch(TipoCombo){
            /* 
            case "Provincia":modelProvincia = new Vector();
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
                                break; */
            /*case "Advertencias":
             listaModelTooltips = new ArrayList<>();
             modelAdvertencias = new Vector();
             modelAdvertencias.addElement( new ModelCombo("Inmueblesujetoaexpropiacionforzosaporloqueelvalordetasacioninformadopodriaversealterado", "Inmueblesujetoaexpropiacionforzosaporloqueelvalordetasacioninformadopodriaversealterado" ) );listaModelTooltips.add( new ModelTooltips("Inmueblesujetoaexpropiacionforzosaporloqueelvalordetasacioninformadopodriaversealterado", "Inmueblesujetoaexpropiacionforzosaporloqueelvalordetasacioninformadopodriaversealterado" ) );
             modelAdvertencias.addElement( new ModelCombo("LaDenominacionDeCalleYoNumeroDePoliciaNoCoincideConLaQueFiguraEnLaDocumentacionRegistralNoExistiendoDudasDeLaIdentificacionDelElementoValorado", "LaDenominacionDeCalleYoNumeroDePoliciaNoCoincideConLaQueFiguraEnLaDocumentacionRegistralNoExistiendoDudasDeLaIdentificacionDelElementoValorado" ) );listaModelTooltips.add( new ModelTooltips("LaDenominacionDeCalleYoNumeroDePoliciaNoCoincideConLaQueFiguraEnLaDocumentacionRegistralNoExistiendoDudasDeLaIdentificacionDelElementoValorado", "LaDenominacionDeCalleYoNumeroDePoliciaNoCoincideConLaQueFiguraEnLaDocumentacionRegistralNoExistiendoDudasDeLaIdentificacionDelElementoValorado" ) );
             modelAdvertencias.addElement( new ModelCombo("ExistenDiscrepanciasPocoSignificativasEntreLaDescripcionRegistralYLaRealidadFisicaActualSiendoElElementoValoradoAcordeConLaNormativaUrbanisticaVigente", "ExistenDiscrepanciasPocoSignificativasEntreLaDescripcionRegistralYLaRealidadFisicaActualSiendoElElementoValoradoAcordeConLaNormativaUrbanisticaVigente" ) );listaModelTooltips.add( new ModelTooltips("ExistenDiscrepanciasPocoSignificativasEntreLaDescripcionRegistralYLaRealidadFisicaActualSiendoElElementoValoradoAcordeConLaNormativaUrbanisticaVigente", "ExistenDiscrepanciasPocoSignificativasEntreLaDescripcionRegistralYLaRealidadFisicaActualSiendoElElementoValoradoAcordeConLaNormativaUrbanisticaVigente" ) );
             modelAdvertencias.addElement( new ModelCombo("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeDocumentacionAcreditativaDeLaExpedicionDelSeguroDeDañosDecenal", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeDocumentacionAcreditativaDeLaExpedicionDelSeguroDeDañosDecenal" ) );listaModelTooltips.add( new ModelTooltips("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeDocumentacionAcreditativaDeLaExpedicionDelSeguroDeDañosDecenal", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeDocumentacionAcreditativaDeLaExpedicionDelSeguroDeDañosDecenal" ) );
             modelAdvertencias.addElement( new ModelCombo("NoSeHaDispuestoParaSuAnalisisPorElTasadorDelContratoDeEjecucionDeObraYDelPresupuestoDeContrata", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDelContratoDeEjecucionDeObraYDelPresupuestoDeContrata" ) );listaModelTooltips.add( new ModelTooltips("NoSeHaDispuestoParaSuAnalisisPorElTasadorDelContratoDeEjecucionDeObraYDelPresupuestoDeContrata", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDelContratoDeEjecucionDeObraYDelPresupuestoDeContrata" ) );
             modelAdvertencias.addElement( new ModelCombo("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaUltimaCertificacionDeObraDeLaDireccionFacultativa", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaUltimaCertificacionDeObraDeLaDireccionFacultativa" ) );listaModelTooltips.add( new ModelTooltips("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaUltimaCertificacionDeObraDeLaDireccionFacultativa", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaUltimaCertificacionDeObraDeLaDireccionFacultativa" ) );
             modelAdvertencias.addElement( new ModelCombo("DeNoCumplirseLasClausulasDeLaLicenciaDeObrasElValorDeTasacionInformadoPodriaVerseAlterado", "DeNoCumplirseLasClausulasDeLaLicenciaDeObrasElValorDeTasacionInformadoPodriaVerseAlterado" ) );listaModelTooltips.add( new ModelTooltips("DeNoCumplirseLasClausulasDeLaLicenciaDeObrasElValorDeTasacionInformadoPodriaVerseAlterado", "DeNoCumplirseLasClausulasDeLaLicenciaDeObrasElValorDeTasacionInformadoPodriaVerseAlterado" ) );
             modelAdvertencias.addElement( new ModelCombo("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaLicenciaDeAperturaYoActividad", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaLicenciaDeAperturaYoActividad" ) );listaModelTooltips.add( new ModelTooltips("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaLicenciaDeAperturaYoActividad", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaLicenciaDeAperturaYoActividad" ) );
             modelAdvertencias.addElement( new ModelCombo("DeNoCumplirseLasClausulasDeLaLicenciaDeObrasElValorDeTasacionInformadoPodriaVer", "DeNoCumplirseLasClausulasDeLaLicenciaDeObrasElValorDeTasacionInformadoPodriaVer" ) );listaModelTooltips.add( new ModelTooltips("DeNoCumplirseLasClausulasDeLaLicenciaDeObrasElValorDeTasacionInformadoPodriaVer", "DeNoCumplirseLasClausulasDeLaLicenciaDeObrasElValorDeTasacionInformadoPodriaVer" ) );
             modelAdvertencias.addElement( new ModelCombo("seAlterado", "seAlterado" ) );listaModelTooltips.add( new ModelTooltips("seAlterado", "seAlterado" ) );
             modelAdvertencias.addElement( new ModelCombo("ElValorDeTasacionUnicamenteSeraValidadoSiElFuturoTitularRegistralEsElActualInquilinoDelElementoValoradoPorLoQueNoSeHaDeterminadoElValorDeMercadoPorCapitalizacionDeLasRentas", "ElValorDeTasacionUnicamenteSeraValidadoSiElFuturoTitularRegistralEsElActualInquilinoDelElementoValoradoPorLoQueNoSeHaDeterminadoElValorDeMercadoPorCapitalizacionDeLasRentas" ) );listaModelTooltips.add( new ModelTooltips("ElValorDeTasacionUnicamenteSeraValidadoSiElFuturoTitularRegistralEsElActualInquilinoDelElementoValoradoPorLoQueNoSeHaDeterminadoElValorDeMercadoPorCapitalizacionDeLasRentas", "ElValorDeTasacionUnicamenteSeraValidadoSiElFuturoTitularRegistralEsElActualInquilinoDelElementoValoradoPorLoQueNoSeHaDeterminadoElValorDeMercadoPorCapitalizacionDeLasRentas" ) );
             modelAdvertencias.addElement( new ModelCombo("NoSeHaDispuestoParaSuAnalisisPorElTasadorLosEstatutosDeLaComunidadOCertificadoDelAdministradorParaComprobarLasLimitacionesDeUso", "NoSeHaDispuestoParaSuAnalisisPorElTasadorLosEstatutosDeLaComunidadOCertificadoDelAdministradorParaComprobarLasLimitacionesDeUso" ) );listaModelTooltips.add( new ModelTooltips("NoSeHaDispuestoParaSuAnalisisPorElTasadorLosEstatutosDeLaComunidadOCertificadoDelAdministradorParaComprobarLasLimitacionesDeUso", "NoSeHaDispuestoParaSuAnalisisPorElTasadorLosEstatutosDeLaComunidadOCertificadoDelAdministradorParaComprobarLasLimitacionesDeUso" ) );
             modelAdvertencias.addElement( new ModelCombo("SeHanDeterminadoLosFlujosDeCajaAPartirDeRatiosMediasDelSectorAlNoHaberDispuestoElBalanceYCuentaDeResultadosDeLosTresUltimosAños", "SeHanDeterminadoLosFlujosDeCajaAPartirDeRatiosMediasDelSectorAlNoHaberDispuestoElBalanceYCuentaDeResultadosDeLosTresUltimosAños" ) );listaModelTooltips.add( new ModelTooltips("SeHanDeterminadoLosFlujosDeCajaAPartirDeRatiosMediasDelSectorAlNoHaberDispuestoElBalanceYCuentaDeResultadosDeLosTresUltimosAños", "SeHanDeterminadoLosFlujosDeCajaAPartirDeRatiosMediasDelSectorAlNoHaberDispuestoElBalanceYCuentaDeResultadosDeLosTresUltimosAños" ) );
             modelAdvertencias.addElement( new ModelCombo("DocumentacionNecesariaParaCalcularFlujosDeCaja", "DocumentacionNecesariaParaCalcularFlujosDeCaja" ) );listaModelTooltips.add( new ModelTooltips("DocumentacionNecesariaParaCalcularFlujosDeCaja", "DocumentacionNecesariaParaCalcularFlujosDeCaja" ) );
             modelAdvertencias.addElement( new ModelCombo("AlTratarseDeUnDerechoDeUsufructoDeUnaConcesionAdministrativaODeUnDerechoDeSuperficieLaValoracionQuedaSujetaALasLimitacionesDelArt31DelRd6851982DelMercadoHipotecario", "AlTratarseDeUnDerechoDeUsufructoDeUnaConcesionAdministrativaODeUnDerechoDeSuperficieLaValoracionQuedaSujetaALasLimitacionesDelArt31DelRd6851982DelMercadoHipotecario" ) );listaModelTooltips.add( new ModelTooltips("AlTratarseDeUnDerechoDeUsufructoDeUnaConcesionAdministrativaODeUnDerechoDeSuperficieLaValoracionQuedaSujetaALasLimitacionesDelArt31DelRd6851982DelMercadoHipotecario", "AlTratarseDeUnDerechoDeUsufructoDeUnaConcesionAdministrativaODeUnDerechoDeSuperficieLaValoracionQuedaSujetaALasLimitacionesDelArt31DelRd6851982DelMercadoHipotecario" ) );
             modelAdvertencias.addElement( new ModelCombo("SeHaDeterminadoElValorMaximoLegalAPartirDeLaSuperficieUtilQueFiguraEnLaDocumentacionRegistralAlNoHaberDispuestoDeLaCedulaDeCalificacionDefinitiva", "SeHaDeterminadoElValorMaximoLegalAPartirDeLaSuperficieUtilQueFiguraEnLaDocumentacionRegistralAlNoHaberDispuestoDeLaCedulaDeCalificacionDefinitiva" ) );listaModelTooltips.add( new ModelTooltips("SeHaDeterminadoElValorMaximoLegalAPartirDeLaSuperficieUtilQueFiguraEnLaDocumentacionRegistralAlNoHaberDispuestoDeLaCedulaDeCalificacionDefinitiva", "SeHaDeterminadoElValorMaximoLegalAPartirDeLaSuperficieUtilQueFiguraEnLaDocumentacionRegistralAlNoHaberDispuestoDeLaCedulaDeCalificacionDefinitiva" ) );
             modelAdvertencias.addElement( new ModelCombo("NoSeHaPodidoComprobarElRegimenDeProteccionDelPatrimonioArquitectonico", "NoSeHaPodidoComprobarElRegimenDeProteccionDelPatrimonioArquitectonico" ) );listaModelTooltips.add( new ModelTooltips("NoSeHaPodidoComprobarElRegimenDeProteccionDelPatrimonioArquitectonico", "NoSeHaPodidoComprobarElRegimenDeProteccionDelPatrimonioArquitectonico" ) );
             modelAdvertencias.addElement( new ModelCombo("DocumentacionCatastralRelevanteRegimenDeExplotacionORendimientoEconomico", "DocumentacionCatastralRelevanteRegimenDeExplotacionORendimientoEconomico" ) );listaModelTooltips.add( new ModelTooltips("DocumentacionCatastralRelevanteRegimenDeExplotacionORendimientoEconomico", "DocumentacionCatastralRelevanteRegimenDeExplotacionORendimientoEconomico" ) );
             modelAdvertencias.addElement( new ModelCombo("DocumentacionCatastralRelevante", "DocumentacionCatastralRelevante" ) );listaModelTooltips.add( new ModelTooltips("DocumentacionCatastralRelevante", "DocumentacionCatastralRelevante" ) );
             modelAdvertencias.addElement( new ModelCombo("RegimenDeExplotacionDerechoDeRiegoORendimientoEconomico", "RegimenDeExplotacionDerechoDeRiegoORendimientoEconomico" ) );listaModelTooltips.add( new ModelTooltips("RegimenDeExplotacionDerechoDeRiegoORendimientoEconomico", "RegimenDeExplotacionDerechoDeRiegoORendimientoEconomico" ) );
             modelAdvertencias.addElement( new ModelCombo("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaReferenciaCatastralDeAlgunInmueble", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaReferenciaCatastralDeAlgunInmueble" ) );listaModelTooltips.add( new ModelTooltips("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaReferenciaCatastralDeAlgunInmueble", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaReferenciaCatastralDeAlgunInmueble" ) );
             modelAdvertencias.addElement( new ModelCombo("SeAdvierteQueLaDocumentacionCatastralAportadaTieneUnaAntiguEdadSuperiorATresMeses", "SeAdvierteQueLaDocumentacionCatastralAportadaTieneUnaAntiguEdadSuperiorATresMeses" ) );listaModelTooltips.add( new ModelTooltips("SeAdvierteQueLaDocumentacionCatastralAportadaTieneUnaAntiguEdadSuperiorATresMeses", "SeAdvierteQueLaDocumentacionCatastralAportadaTieneUnaAntiguEdadSuperiorATresMeses" ) );
             modelAdvertencias.addElement( new ModelCombo("SeAdvierteQueSeHaIniciadoElProcedimientoDeExpropiacionDeLaTotalidadOParteDelInmuebleTasado", "SeAdvierteQueSeHaIniciadoElProcedimientoDeExpropiacionDeLaTotalidadOParteDelInmuebleTasado" ) );listaModelTooltips.add( new ModelTooltips("SeAdvierteQueSeHaIniciadoElProcedimientoDeExpropiacionDeLaTotalidadOParteDelInmuebleTasado", "SeAdvierteQueSeHaIniciadoElProcedimientoDeExpropiacionDeLaTotalidadOParteDelInmuebleTasado" ) );
             modelAdvertencias.addElement( new ModelCombo("SeAdvierteQueSeHaAprobadoUnInstrumentoDeOrdenacionTerritorialOUrbanisticaPlanOProyectoQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado", "SeAdvierteQueSeHaAprobadoUnInstrumentoDeOrdenacionTerritorialOUrbanisticaPlanOProyectoQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado" ) );listaModelTooltips.add( new ModelTooltips("SeAdvierteQueSeHaAprobadoUnInstrumentoDeOrdenacionTerritorialOUrbanisticaPlanOProyectoQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado", "SeAdvierteQueSeHaAprobadoUnInstrumentoDeOrdenacionTerritorialOUrbanisticaPlanOProyectoQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado" ) );
             modelAdvertencias.addElement( new ModelCombo("SeAdvierteQueSeHaDeclaradoPorLaAdministracionCompetenteUnaResolucionAdministrativaConAudienciaDeLosInteresadosQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado", "SeAdvierteQueSeHaDeclaradoPorLaAdministracionCompetenteUnaResolucionAdministrativaConAudienciaDeLosInteresadosQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado" ) );listaModelTooltips.add( new ModelTooltips("SeAdvierteQueSeHaDeclaradoPorLaAdministracionCompetenteUnaResolucionAdministrativaConAudienciaDeLosInteresadosQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado", "SeAdvierteQueSeHaDeclaradoPorLaAdministracionCompetenteUnaResolucionAdministrativaConAudienciaDeLosInteresadosQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado" ) );
             modelAdvertencias.addElement( new ModelCombo("SeAdvierteQueSeHaIncoadoExpedienteDeIncumplimientoDeLosPlazosODemasDeberesInherentesAlProcesoDeUrbanizacionODeEdificacionQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado", "SeAdvierteQueSeHaIncoadoExpedienteDeIncumplimientoDeLosPlazosODemasDeberesInherentesAlProcesoDeUrbanizacionODeEdificacionQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado" ) );listaModelTooltips.add( new ModelTooltips("SeAdvierteQueSeHaIncoadoExpedienteDeIncumplimientoDeLosPlazosODemasDeberesInherentesAlProcesoDeUrbanizacionODeEdificacionQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado", "SeAdvierteQueSeHaIncoadoExpedienteDeIncumplimientoDeLosPlazosODemasDeberesInherentesAlProcesoDeUrbanizacionODeEdificacionQuePuedeProvocarLaExpropiacionDeLaTotalidadOParteDelInmuebleTasado" ) );
             modelAdvertencias.addElement( new ModelCombo("EnCasoDeDeclaracionAdministrativaDeIncumplimientoDePlazosDeLosDeberesDeUrbanizacionODeEdificacionDelSueloYPosteriorExpropiacionDeLosTerrenosEstosSeJustipreciaranDeConformidadConLosCriteriosEstablecidosEnLaLey82007", "EnCasoDeDeclaracionAdministrativaDeIncumplimientoDePlazosDeLosDeberesDeUrbanizacionODeEdificacionDelSueloYPosteriorExpropiacionDeLosTerrenosEstosSeJustipreciaranDeConformidadConLosCriteriosEstablecidosEnLaLey82007" ) );listaModelTooltips.add( new ModelTooltips("EnCasoDeDeclaracionAdministrativaDeIncumplimientoDePlazosDeLosDeberesDeUrbanizacionODeEdificacionDelSueloYPosteriorExpropiacionDeLosTerrenosEstosSeJustipreciaranDeConformidadConLosCriteriosEstablecidosEnLaLey82007", "EnCasoDeDeclaracionAdministrativaDeIncumplimientoDePlazosDeLosDeberesDeUrbanizacionODeEdificacionDelSueloYPosteriorExpropiacionDeLosTerrenosEstosSeJustipreciaranDeConformidadConLosCriteriosEstablecidosEnLaLey82007" ) );
             modelAdvertencias.addElement( new ModelCombo("LaDocumentacionCatastralAportadaPerteneceALaFincaMatrizDeLaQueProcede", "LaDocumentacionCatastralAportadaPerteneceALaFincaMatrizDeLaQueProcede" ) );listaModelTooltips.add( new ModelTooltips("LaDocumentacionCatastralAportadaPerteneceALaFincaMatrizDeLaQueProcede", "LaDocumentacionCatastralAportadaPerteneceALaFincaMatrizDeLaQueProcede" ) );
             modelAdvertencias.addElement( new ModelCombo("NoExistenAdvertenciasParaElCasoConcretoDeEsteInforme", "NoExistenAdvertenciasParaElCasoConcretoDeEsteInforme" ) );listaModelTooltips.add( new ModelTooltips("NoExistenAdvertenciasParaElCasoConcretoDeEsteInforme", "NoExistenAdvertenciasParaElCasoConcretoDeEsteInforme" ) );
             modelAdvertencias.addElement( new ModelCombo("OtrasAdvertencias", "OtrasAdvertencias" ) );listaModelTooltips.add( new ModelTooltips("OtrasAdvertencias", "OtrasAdvertencias" ) );
             modelAdvertencias.addElement( new ModelCombo("A11ExisteElementoOInmuebleConstruidoSobreLaFincaNoInscritoRegistralmenteQueSeHaConsideradoEnLaValoracionAlSerAcordeConLaNormativaUrbanisticaYLegalizableSiendoRecomendableSuInscripcionEnElRegistroDeLaPropiedadDe", "A11ExisteElementoOInmuebleConstruidoSobreLaFincaNoInscritoRegistralmenteQueSeHaConsideradoEnLaValoracionAlSerAcordeConLaNormativaUrbanisticaYLegalizableSiendoRecomendableSuInscripcionEnElRegistroDeLaPropiedadDe" ) );listaModelTooltips.add( new ModelTooltips("A11ExisteElementoOInmuebleConstruidoSobreLaFincaNoInscritoRegistralmenteQueSeHaConsideradoEnLaValoracionAlSerAcordeConLaNormativaUrbanisticaYLegalizableSiendoRecomendableSuInscripcionEnElRegistroDeLaPropiedadDe", "A11ExisteElementoOInmuebleConstruidoSobreLaFincaNoInscritoRegistralmenteQueSeHaConsideradoEnLaValoracionAlSerAcordeConLaNormativaUrbanisticaYLegalizableSiendoRecomendableSuInscripcionEnElRegistroDeLaPropiedadDe" ) );
             modelAdvertencias.addElement( new ModelCombo("A12LaDenominacionDelRegistroDeLaPropiedadYDePoliciaNoCoincidenNoExistiendoDudasDeSuCorrectaIdentificacionSeRecomiendaSuCorrectaInscripcion", "A12LaDenominacionDelRegistroDeLaPropiedadYDePoliciaNoCoincidenNoExistiendoDudasDeSuCorrectaIdentificacionSeRecomiendaSuCorrectaInscripcion" ) );listaModelTooltips.add( new ModelTooltips("A12LaDenominacionDelRegistroDeLaPropiedadYDePoliciaNoCoincidenNoExistiendoDudasDeSuCorrectaIdentificacionSeRecomiendaSuCorrectaInscripcion", "A12LaDenominacionDelRegistroDeLaPropiedadYDePoliciaNoCoincidenNoExistiendoDudasDeSuCorrectaIdentificacionSeRecomiendaSuCorrectaInscripcion" ) );
             modelAdvertencias.addElement( new ModelCombo("A13AlEstarUbicadoElElementoValoradoEnUnEdificioDeRecienteConstruccionNoExisteTodaviaFichaCatastralDeLaMismaSeAportanLosDatosCatastralesDelTerrenoEnElQueSeHaConstruidoElEdificio", "A13AlEstarUbicadoElElementoValoradoEnUnEdificioDeRecienteConstruccionNoExisteTodaviaFichaCatastralDeLaMismaSeAportanLosDatosCatastralesDelTerrenoEnElQueSeHaConstruidoElEdificio" ) );listaModelTooltips.add( new ModelTooltips("A13AlEstarUbicadoElElementoValoradoEnUnEdificioDeRecienteConstruccionNoExisteTodaviaFichaCatastralDeLaMismaSeAportanLosDatosCatastralesDelTerrenoEnElQueSeHaConstruidoElEdificio", "A13AlEstarUbicadoElElementoValoradoEnUnEdificioDeRecienteConstruccionNoExisteTodaviaFichaCatastralDeLaMismaSeAportanLosDatosCatastralesDelTerrenoEnElQueSeHaConstruidoElEdificio" ) );
             modelAdvertencias.addElement( new ModelCombo("A14ElSolicitanteNoEsTitularDeLaPlenaPropiedadRegistralTasacionUnicamenteValidaSiEllosSolicitantesEssonTitularesDeLaPlenaTitularidadRegistral", "A14ElSolicitanteNoEsTitularDeLaPlenaPropiedadRegistralTasacionUnicamenteValidaSiEllosSolicitantesEssonTitularesDeLaPlenaTitularidadRegistral" ) );listaModelTooltips.add( new ModelTooltips("A14ElSolicitanteNoEsTitularDeLaPlenaPropiedadRegistralTasacionUnicamenteValidaSiEllosSolicitantesEssonTitularesDeLaPlenaTitularidadRegistral", "A14ElSolicitanteNoEsTitularDeLaPlenaPropiedadRegistralTasacionUnicamenteValidaSiEllosSolicitantesEssonTitularesDeLaPlenaTitularidadRegistral" ) );
             modelAdvertencias.addElement( new ModelCombo("A15ElSolicitanteNoEsTitularDeLaPlenaPropiedadRegistralTasacionUnicamenteValidaSiEllosSolicitantesEssonTitularesDeLaPlenaTitularidadRegistral", "A15ElSolicitanteNoEsTitularDeLaPlenaPropiedadRegistralTasacionUnicamenteValidaSiEllosSolicitantesEssonTitularesDeLaPlenaTitularidadRegistral" ) );listaModelTooltips.add( new ModelTooltips("A15ElSolicitanteNoEsTitularDeLaPlenaPropiedadRegistralTasacionUnicamenteValidaSiEllosSolicitantesEssonTitularesDeLaPlenaTitularidadRegistral", "A15ElSolicitanteNoEsTitularDeLaPlenaPropiedadRegistralTasacionUnicamenteValidaSiEllosSolicitantesEssonTitularesDeLaPlenaTitularidadRegistral" ) );
             modelAdvertencias.addElement( new ModelCombo("A16TasacionSolicitadaConjuntamentePorNudoPropietarioYUsufructuarioTasacionEnLaHipotesisDePlenaPropiedadAlSerLosSolicitantesConjuntamenteNudoPropietarioOUsufructuarioOPorExtincionDelUsufructo", "A16TasacionSolicitadaConjuntamentePorNudoPropietarioYUsufructuarioTasacionEnLaHipotesisDePlenaPropiedadAlSerLosSolicitantesConjuntamenteNudoPropietarioOUsufructuarioOPorExtincionDelUsufructo" ) );listaModelTooltips.add( new ModelTooltips("A16TasacionSolicitadaConjuntamentePorNudoPropietarioYUsufructuarioTasacionEnLaHipotesisDePlenaPropiedadAlSerLosSolicitantesConjuntamenteNudoPropietarioOUsufructuarioOPorExtincionDelUsufructo", "A16TasacionSolicitadaConjuntamentePorNudoPropietarioYUsufructuarioTasacionEnLaHipotesisDePlenaPropiedadAlSerLosSolicitantesConjuntamenteNudoPropietarioOUsufructuarioOPorExtincionDelUsufructo" ) );
             modelAdvertencias.addElement( new ModelCombo("A31NoSeHaDispuestoParaSuAnalisisPorElTasadorDeDocumentacionAcreditativaDeLaExpedicionDelSeguroDeDañOsDecenal", "A31NoSeHaDispuestoParaSuAnalisisPorElTasadorDeDocumentacionAcreditativaDeLaExpedicionDelSeguroDeDañOsDecenal" ) );listaModelTooltips.add( new ModelTooltips("A31NoSeHaDispuestoParaSuAnalisisPorElTasadorDeDocumentacionAcreditativaDeLaExpedicionDelSeguroDeDañOsDecenal", "A31NoSeHaDispuestoParaSuAnalisisPorElTasadorDeDocumentacionAcreditativaDeLaExpedicionDelSeguroDeDañOsDecenal" ) );
             modelAdvertencias.addElement( new ModelCombo("A32NoSeHaDispuestoParaSuAnalisisPorElTasadorDelContratoDeEjecucionDeObraYDelPresupuestoDeContrata", "A32NoSeHaDispuestoParaSuAnalisisPorElTasadorDelContratoDeEjecucionDeObraYDelPresupuestoDeContrata" ) );listaModelTooltips.add( new ModelTooltips("A32NoSeHaDispuestoParaSuAnalisisPorElTasadorDelContratoDeEjecucionDeObraYDelPresupuestoDeContrata", "A32NoSeHaDispuestoParaSuAnalisisPorElTasadorDelContratoDeEjecucionDeObraYDelPresupuestoDeContrata" ) );
             modelAdvertencias.addElement( new ModelCombo("A33NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaUltimaCertificacionDeObraDeLaDireccionFacultativa", "A33NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaUltimaCertificacionDeObraDeLaDireccionFacultativa" ) );listaModelTooltips.add( new ModelTooltips("A33NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaUltimaCertificacionDeObraDeLaDireccionFacultativa", "A33NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaUltimaCertificacionDeObraDeLaDireccionFacultativa" ) );
             modelAdvertencias.addElement( new ModelCombo("A41NoSeAportaLaLicenciaDeAperturaYoActividadAlEstarElLocalDesocupado", "A41NoSeAportaLaLicenciaDeAperturaYoActividadAlEstarElLocalDesocupado" ) );listaModelTooltips.add( new ModelTooltips("A41NoSeAportaLaLicenciaDeAperturaYoActividadAlEstarElLocalDesocupado", "A41NoSeAportaLaLicenciaDeAperturaYoActividadAlEstarElLocalDesocupado" ) );
             modelAdvertencias.addElement( new ModelCombo("A42NoSeHaDispuestoDeLaLicenciaDeAperturaYoActividadParaSuAnalisisPorElTasadorALosEfectosDeConfirmarQueElUsoAQueSeDestinaActualmenteEstaAutorizadoYQueElActualOcupanteEsTitularDeLaLicencia", "A42NoSeHaDispuestoDeLaLicenciaDeAperturaYoActividadParaSuAnalisisPorElTasadorALosEfectosDeConfirmarQueElUsoAQueSeDestinaActualmenteEstaAutorizadoYQueElActualOcupanteEsTitularDeLaLicencia" ) );listaModelTooltips.add( new ModelTooltips("A42NoSeHaDispuestoDeLaLicenciaDeAperturaYoActividadParaSuAnalisisPorElTasadorALosEfectosDeConfirmarQueElUsoAQueSeDestinaActualmenteEstaAutorizadoYQueElActualOcupanteEsTitularDeLaLicencia", "A42NoSeHaDispuestoDeLaLicenciaDeAperturaYoActividadParaSuAnalisisPorElTasadorALosEfectosDeConfirmarQueElUsoAQueSeDestinaActualmenteEstaAutorizadoYQueElActualOcupanteEsTitularDeLaLicencia" ) );
             modelAdvertencias.addElement( new ModelCombo("A43ElValorDeTasacionInformadoUnicamenteSeraValidoSiElFuturoTitularRegistralEsElActualInquilinoDelElementoValoradoPorLoQueNoSeHaDeterminadoElValorDeMercadoPorCapitalizacionDeLasRentas", "A43ElValorDeTasacionInformadoUnicamenteSeraValidoSiElFuturoTitularRegistralEsElActualInquilinoDelElementoValoradoPorLoQueNoSeHaDeterminadoElValorDeMercadoPorCapitalizacionDeLasRentas" ) );listaModelTooltips.add( new ModelTooltips("A43ElValorDeTasacionInformadoUnicamenteSeraValidoSiElFuturoTitularRegistralEsElActualInquilinoDelElementoValoradoPorLoQueNoSeHaDeterminadoElValorDeMercadoPorCapitalizacionDeLasRentas", "A43ElValorDeTasacionInformadoUnicamenteSeraValidoSiElFuturoTitularRegistralEsElActualInquilinoDelElementoValoradoPorLoQueNoSeHaDeterminadoElValorDeMercadoPorCapitalizacionDeLasRentas" ) );
             modelAdvertencias.addElement( new ModelCombo("A44NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLosEstatutosDeLaComunidadOCertificadoDelAdministradorParaConfirmarLasLimitacionesDeUso", "A44NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLosEstatutosDeLaComunidadOCertificadoDelAdministradorParaConfirmarLasLimitacionesDeUso" ) );listaModelTooltips.add( new ModelTooltips("A44NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLosEstatutosDeLaComunidadOCertificadoDelAdministradorParaConfirmarLasLimitacionesDeUso", "A44NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLosEstatutosDeLaComunidadOCertificadoDelAdministradorParaConfirmarLasLimitacionesDeUso" ) );
             modelAdvertencias.addElement( new ModelCombo("A45SeHanDeterminadoLosFlujosDeCajaAPartirDeRatiosMediasDelSectorAlNoHaberDispuestoDelBalanceYCuentaDeResultadosDeLosTresUltimosAños", "A45SeHanDeterminadoLosFlujosDeCajaAPartirDeRatiosMediasDelSectorAlNoHaberDispuestoDelBalanceYCuentaDeResultadosDeLosTresUltimosAños" ) );listaModelTooltips.add( new ModelTooltips("A45SeHanDeterminadoLosFlujosDeCajaAPartirDeRatiosMediasDelSectorAlNoHaberDispuestoDelBalanceYCuentaDeResultadosDeLosTresUltimosAños", "A45SeHanDeterminadoLosFlujosDeCajaAPartirDeRatiosMediasDelSectorAlNoHaberDispuestoDelBalanceYCuentaDeResultadosDeLosTresUltimosAños" ) );
             modelAdvertencias.addElement( new ModelCombo("A46AlTratarseDeUnDerechoDeUsufructoDeUnaConcesionAdministrativaODeUnDerechoDeSuperficieLaValoracionQuedaSujetaALasLimitacionesDelArt31DelRd6851982DelMercadoHipotecario", "A46AlTratarseDeUnDerechoDeUsufructoDeUnaConcesionAdministrativaODeUnDerechoDeSuperficieLaValoracionQuedaSujetaALasLimitacionesDelArt31DelRd6851982DelMercadoHipotecario" ) );listaModelTooltips.add( new ModelTooltips("A46AlTratarseDeUnDerechoDeUsufructoDeUnaConcesionAdministrativaODeUnDerechoDeSuperficieLaValoracionQuedaSujetaALasLimitacionesDelArt31DelRd6851982DelMercadoHipotecario", "A46AlTratarseDeUnDerechoDeUsufructoDeUnaConcesionAdministrativaODeUnDerechoDeSuperficieLaValoracionQuedaSujetaALasLimitacionesDelArt31DelRd6851982DelMercadoHipotecario" ) );
             modelAdvertencias.addElement( new ModelCombo("A5NoSeHaDispuestoDeLaCedulaDeProteccionPorLoQueElValorMaximoLegalInformadoEsUnaEstimacionBasadaEnLaSuperficieUtilRegistralQueDeberaSerCorroboradaMedianteConsultaEnElDepartamentoDeLaAdministracionCompetente", "A5NoSeHaDispuestoDeLaCedulaDeProteccionPorLoQueElValorMaximoLegalInformadoEsUnaEstimacionBasadaEnLaSuperficieUtilRegistralQueDeberaSerCorroboradaMedianteConsultaEnElDepartamentoDeLaAdministracionCompetente" ) );listaModelTooltips.add( new ModelTooltips("A5NoSeHaDispuestoDeLaCedulaDeProteccionPorLoQueElValorMaximoLegalInformadoEsUnaEstimacionBasadaEnLaSuperficieUtilRegistralQueDeberaSerCorroboradaMedianteConsultaEnElDepartamentoDeLaAdministracionCompetente", "A5NoSeHaDispuestoDeLaCedulaDeProteccionPorLoQueElValorMaximoLegalInformadoEsUnaEstimacionBasadaEnLaSuperficieUtilRegistralQueDeberaSerCorroboradaMedianteConsultaEnElDepartamentoDeLaAdministracionCompetente" ) );
             modelAdvertencias.addElement( new ModelCombo("A6NoSeHaDispuestoDeLaCedulaUrbanisticaDelTerrenoHabiendoseContrastadoLosParametrosUrbanisticosManejadosEnElInformeEnConsultaALosServiciosUrbanisticosMunicipales", "A6NoSeHaDispuestoDeLaCedulaUrbanisticaDelTerrenoHabiendoseContrastadoLosParametrosUrbanisticosManejadosEnElInformeEnConsultaALosServiciosUrbanisticosMunicipales" ) );listaModelTooltips.add( new ModelTooltips("A6NoSeHaDispuestoDeLaCedulaUrbanisticaDelTerrenoHabiendoseContrastadoLosParametrosUrbanisticosManejadosEnElInformeEnConsultaALosServiciosUrbanisticosMunicipales", "A6NoSeHaDispuestoDeLaCedulaUrbanisticaDelTerrenoHabiendoseContrastadoLosParametrosUrbanisticosManejadosEnElInformeEnConsultaALosServiciosUrbanisticosMunicipales" ) );
             modelAdvertencias.addElement( new ModelCombo("A7Advertenciasotros", "A7Advertenciasotros" ) );listaModelTooltips.add( new ModelTooltips("A7Advertenciasotros", "A7Advertenciasotros" ) );
             eCapturarAvertencias.setModel(new DefaultComboBoxModel(modelAdvertencias));
             break;      */

            /*case "Condicionantes":
             listaModelTooltipsCondicionantes = new ArrayList<>();
             modelCondicionantes = new Vector();
             modelCondicionantes.addElement( new ModelCombo("InscripcionDelElementoValoradoComoFincaRegistralIndependiente", "InscripcionDelElementoValoradoComoFincaRegistralIndependiente" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("InscripcionDelElementoValoradoComoFincaRegistralIndependiente", "InscripcionDelElementoValoradoComoFincaRegistralIndependiente" ) );
             modelCondicionantes.addElement( new ModelCombo("AclaracionDiferenciasSignificativasEntreLaSuperficieRegistralCatastralyLaComprobada", "AclaracionDiferenciasSignificativasEntreLaSuperficieRegistralCatastralyLaComprobada" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AclaracionDiferenciasSignificativasEntreLaSuperficieRegistralCatastralyLaComprobada", "AclaracionDiferenciasSignificativasEntreLaSuperficieRegistralCatastralyLaComprobada" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDeNotaSimpleoCertificacionEmitidaFueraDeLos3MesesAnterioresaLaFechaDeTasacion", "AportacionDeNotaSimpleoCertificacionEmitidaFueraDeLos3MesesAnterioresaLaFechaDeTasacion" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDeNotaSimpleoCertificacionEmitidaFueraDeLos3MesesAnterioresaLaFechaDeTasacion", "AportacionDeNotaSimpleoCertificacionEmitidaFueraDeLos3MesesAnterioresaLaFechaDeTasacion" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDeProyectoVisado", "AportacionDeProyectoVisado" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDeProyectoVisado", "AportacionDeProyectoVisado" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDeLicenciaDeObras", "AportacionDeLicenciaDeObras" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDeLicenciaDeObras", "AportacionDeLicenciaDeObras" ) );
             modelCondicionantes.addElement( new ModelCombo("JustificacionDeQueElElementoValoradoEstaLibreDeInquilinosuOcupantes", "JustificacionDeQueElElementoValoradoEstaLibreDeInquilinosuOcupantes" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("JustificacionDeQueElElementoValoradoEstaLibreDeInquilinosuOcupantes", "JustificacionDeQueElElementoValoradoEstaLibreDeInquilinosuOcupantes" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDeContratoDeArrendamientoyoUltimoRecibo", "AportacionDeContratoDeArrendamientoyoUltimoRecibo" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDeContratoDeArrendamientoyoUltimoRecibo", "AportacionDeContratoDeArrendamientoyoUltimoRecibo" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDeUltimosRecibosDeRentaAbonados", "AportacionDeUltimosRecibosDeRentaAbonados" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDeUltimosRecibosDeRentaAbonados", "AportacionDeUltimosRecibosDeRentaAbonados" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDeLaCedulaDeCalificacionProvisionaloDefinitiva", "AportacionDeLaCedulaDeCalificacionProvisionaloDefinitiva" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDeLaCedulaDeCalificacionProvisionaloDefinitiva", "AportacionDeLaCedulaDeCalificacionProvisionaloDefinitiva" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDeDocumentacionUrbanisticayoDatosDeAprovechamientoUrbanistico", "AportacionDeDocumentacionUrbanisticayoDatosDeAprovechamientoUrbanistico" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDeDocumentacionUrbanisticayoDatosDeAprovechamientoUrbanistico", "AportacionDeDocumentacionUrbanisticayoDatosDeAprovechamientoUrbanistico" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDeCertificacionRegistral", "AportacionDeCertificacionRegistral" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDeCertificacionRegistral", "AportacionDeCertificacionRegistral" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDePropietarioDeCertificadoDatosDeAlquiler", "AportacionDePropietarioDeCertificadoDatosDeAlquiler" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDePropietarioDeCertificadoDatosDeAlquiler", "AportacionDePropietarioDeCertificadoDatosDeAlquiler" ) );
             modelCondicionantes.addElement( new ModelCombo("DerechoDeRiego", "DerechoDeRiego" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("DerechoDeRiego", "DerechoDeRiego" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionCedulaUrbanisticaCertificadoUrbanisticoMunicipaloDocumentacionparadeterminarlaClasedeSueloyAprovechamientoUrbanisticodelaPropiedad", "AportacionCedulaUrbanisticaCertificadoUrbanisticoMunicipaloDocumentacionparadeterminarlaClasedeSueloyAprovechamientoUrbanisticodelaPropiedad" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionCedulaUrbanisticaCertificadoUrbanisticoMunicipaloDocumentacionparadeterminarlaClasedeSueloyAprovechamientoUrbanisticodelaPropiedad", "AportacionCedulaUrbanisticaCertificadoUrbanisticoMunicipaloDocumentacionparadeterminarlaClasedeSueloyAprovechamientoUrbanisticodelaPropiedad" ) );
             modelCondicionantes.addElement( new ModelCombo("EstadodeConservacionAparente", "EstadodeConservacionAparente" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("EstadodeConservacionAparente", "EstadodeConservacionAparente" ) );
             modelCondicionantes.addElement( new ModelCombo("ServidumbresVisibles", "ServidumbresVisibles" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("ServidumbresVisibles", "ServidumbresVisibles" ) );
             modelCondicionantes.addElement( new ModelCombo("AportacionDocumentoRegimendeProteccionDelPatrimonioArquitectonico", "AportacionDocumentoRegimendeProteccionDelPatrimonioArquitectonico" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("AportacionDocumentoRegimendeProteccionDelPatrimonioArquitectonico", "AportacionDocumentoRegimendeProteccionDelPatrimonioArquitectonico" ) );
             modelCondicionantes.addElement( new ModelCombo("NoExistenCondicionantesParaElCasoConcretoDeEsteInforme", "NoExistenCondicionantesParaElCasoConcretoDeEsteInforme" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("NoExistenCondicionantesParaElCasoConcretoDeEsteInforme", "NoExistenCondicionantesParaElCasoConcretoDeEsteInforme" ) );
             modelCondicionantes.addElement( new ModelCombo("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaDocumentacionCatastral", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaDocumentacionCatastral" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaDocumentacionCatastral", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaDocumentacionCatastral" ) );
             modelCondicionantes.addElement( new ModelCombo("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaReferenciaCatastralDeAlgunInmueble", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaReferenciaCatastralDeAlgunInmueble" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaReferenciaCatastralDeAlgunInmueble", "NoSeHaDispuestoParaSuAnalisisPorElTasadorDeLaReferenciaCatastralDeAlgunInmueble" ) );
             modelCondicionantes.addElement( new ModelCombo("NoSeHaPodidoComprobarLaExistenciaDeProcedimientoDeDeclaracionDeIncumplimientoPlazosDeLosDeberesDeUrbanizacionoDeEdificacionDelSuelo", "NoSeHaPodidoComprobarLaExistenciaDeProcedimientoDeDeclaracionDeIncumplimientoPlazosDeLosDeberesDeUrbanizacionoDeEdificacionDelSuelo" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("NoSeHaPodidoComprobarLaExistenciaDeProcedimientoDeDeclaracionDeIncumplimientoPlazosDeLosDeberesDeUrbanizacionoDeEdificacionDelSuelo", "NoSeHaPodidoComprobarLaExistenciaDeProcedimientoDeDeclaracionDeIncumplimientoPlazosDeLosDeberesDeUrbanizacionoDeEdificacionDelSuelo" ) );
             modelCondicionantes.addElement( new ModelCombo("OtrosCondicionantes", "OtrosCondicionantes" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("OtrosCondicionantes", "OtrosCondicionantes" ) );
             modelCondicionantes.addElement( new ModelCombo("C111_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimpleEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion", "C111_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimpleEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C111_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimpleEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion", "C111_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimpleEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion" ) );
             modelCondicionantes.addElement( new ModelCombo("C112_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion", "C112_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C112_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion", "C112_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEmitidaDuranteLosTresMesesAnterioresaLaFechaDeLaTasacion" ) );
             modelCondicionantes.addElement( new ModelCombo("C121_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimple", "C121_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimple" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C121_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimple", "C121_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiaDeNotaSimple" ) );
             modelCondicionantes.addElement( new ModelCombo("C122_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEnLaQueLaDescripcionCatastralSeAdecueaLaRealidadFisicaActualySeaAcordeConLaNormativaUrbanistica", "C122_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEnLaQueLaDescripcionCatastralSeAdecueaLaRealidadFisicaActualySeaAcordeConLaNormativaUrbanistica" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C122_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEnLaQueLaDescripcionCatastralSeAdecueaLaRealidadFisicaActualySeaAcordeConLaNormativaUrbanistica", "C122_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeFichaCatastralEnLaQueLaDescripcionCatastralSeAdecueaLaRealidadFisicaActualySeaAcordeConLaNormativaUrbanistica" ) );
             modelCondicionantes.addElement( new ModelCombo("C13_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiadeNotaSimpleEnLaQueElElementoValoradoFigure", "C13_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiadeNotaSimpleEnLaQueElElementoValoradoFigure" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C13_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiadeNotaSimpleEnLaQueElElementoValoradoFigure", "C13_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeDocumentacionRegistralSegunNormativaEscrituraDeLaPropiedadoCertificacionRegistralyoCopiadeNotaSimpleEnLaQueElElementoValoradoFigure" ) );
             modelCondicionantes.addElement( new ModelCombo("C14_ElelementoValoradoEstaFormadoPorDosoMasFincasRegistralesQueFormanUnaUnicaUnidadFuncionalNoSiendoPosibleDeterminarLaConfiguracionoLinderosDeCadaUnaDeEllasPorLoQueElValorInformadoSeCorrespondeConLaAgrupacion", "C14_ElelementoValoradoEstaFormadoPorDosoMasFincasRegistralesQueFormanUnaUnicaUnidadFuncionalNoSiendoPosibleDeterminarLaConfiguracionoLinderosDeCadaUnaDeEllasPorLoQueElValorInformadoSeCorrespondeConLaAgrupacion" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C14_ElelementoValoradoEstaFormadoPorDosoMasFincasRegistralesQueFormanUnaUnicaUnidadFuncionalNoSiendoPosibleDeterminarLaConfiguracionoLinderosDeCadaUnaDeEllasPorLoQueElValorInformadoSeCorrespondeConLaAgrupacion", "C14_ElelementoValoradoEstaFormadoPorDosoMasFincasRegistralesQueFormanUnaUnicaUnidadFuncionalNoSiendoPosibleDeterminarLaConfiguracionoLinderosDeCadaUnaDeEllasPorLoQueElValorInformadoSeCorrespondeConLaAgrupacion" ) );
             modelCondicionantes.addElement( new ModelCombo("C15_LaFincaValoradaDaAccesoaOtraFincaRegistralQueSoloTieneAccesoAtravesDeEllayNoConstaInscritaServidumbreDePasoElValorDeTasacionSoloSeraValidoSiSeInscribeLasServidumbreDePasoDeacuerdoaLosParametrosManejados", "C15_LaFincaValoradaDaAccesoaOtraFincaRegistralQueSoloTieneAccesoAtravesDeEllayNoConstaInscritaServidumbreDePasoElValorDeTasacionSoloSeraValidoSiSeInscribeLasServidumbreDePasoDeacuerdoaLosParametrosManejados" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C15_LaFincaValoradaDaAccesoaOtraFincaRegistralQueSoloTieneAccesoAtravesDeEllayNoConstaInscritaServidumbreDePasoElValorDeTasacionSoloSeraValidoSiSeInscribeLasServidumbreDePasoDeacuerdoaLosParametrosManejados", "C15_LaFincaValoradaDaAccesoaOtraFincaRegistralQueSoloTieneAccesoAtravesDeEllayNoConstaInscritaServidumbreDePasoElValorDeTasacionSoloSeraValidoSiSeInscribeLasServidumbreDePasoDeacuerdoaLosParametrosManejados" ) );
             modelCondicionantes.addElement( new ModelCombo("C31_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeproyectovisado", "C31_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeproyectovisado" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C31_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeproyectovisado", "C31_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeproyectovisado" ) );
             modelCondicionantes.addElement( new ModelCombo("C32_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeLicenciaDeObrasConSusClausulasyCartaDePago", "C32_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeLicenciaDeObrasConSusClausulasyCartaDePago" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C32_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeLicenciaDeObrasConSusClausulasyCartaDePago", "C32_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDeLicenciaDeObrasConSusClausulasyCartaDePago" ) );
             modelCondicionantes.addElement( new ModelCombo("C41_AportacionAlTasadorParaLaVerificacionDeDocumentacionQueJustifiqueQueElElementoValoradoEstaLibreDeArrendamientoInquilinosuOcupantesDiferentesDelTitularRegistralAlNoHaberSidoAportadasLasCondicionesDeOcupacion", "C41_AportacionAlTasadorParaLaVerificacionDeDocumentacionQueJustifiqueQueElElementoValoradoEstaLibreDeArrendamientoInquilinosuOcupantesDiferentesDelTitularRegistralAlNoHaberSidoAportadasLasCondicionesDeOcupacion" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C41_AportacionAlTasadorParaLaVerificacionDeDocumentacionQueJustifiqueQueElElementoValoradoEstaLibreDeArrendamientoInquilinosuOcupantesDiferentesDelTitularRegistralAlNoHaberSidoAportadasLasCondicionesDeOcupacion", "C41_AportacionAlTasadorParaLaVerificacionDeDocumentacionQueJustifiqueQueElElementoValoradoEstaLibreDeArrendamientoInquilinosuOcupantesDiferentesDelTitularRegistralAlNoHaberSidoAportadasLasCondicionesDeOcupacion" ) );
             modelCondicionantes.addElement( new ModelCombo("C42_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelContratoDeArrendamiento", "C42_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelContratoDeArrendamiento" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C42_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelContratoDeArrendamiento", "C42_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelContratoDeArrendamiento" ) );
             modelCondicionantes.addElement( new ModelCombo("C43_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelUltimoReciboDeRentaAbonado", "C43_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelUltimoReciboDeRentaAbonado" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C43_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelUltimoReciboDeRentaAbonado", "C43_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDelUltimoReciboDeRentaAbonado" ) );
             modelCondicionantes.addElement( new ModelCombo("C5_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDecedulaurbanisticacertificadomunicipaluotradocumentacionquepermitadeterminaryconfirmarintegramentelaclasedesueloelaprovechamientoysuscondicion", "C5_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDecedulaurbanisticacertificadomunicipaluotradocumentacionquepermitadeterminaryconfirmarintegramentelaclasedesueloelaprovechamientoysuscondicion" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C5_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDecedulaurbanisticacertificadomunicipaluotradocumentacionquepermitadeterminaryconfirmarintegramentelaclasedesueloelaprovechamientoysuscondicion", "C5_AportacionAlTasadorParaLaVerificacionDeLosDatosEmpleadosEnElInformeDecedulaurbanisticacertificadomunicipaluotradocumentacionquepermitadeterminaryconfirmarintegramentelaclasedesueloelaprovechamientoysuscondicion" ) );
             modelCondicionantes.addElement( new ModelCombo("C7_CondicionantesOtros", "C7_CondicionantesOtros" ) );listaModelTooltipsCondicionantes.add( new ModelTooltips("C7_CondicionantesOtros", "C7_CondicionantesOtros" ) );
             eCapturarCondicionantes.setModel(new DefaultComboBoxModel(modelCondicionantes));
             break; */
            case "Finalidad":
                modelFinalidad = new Vector();
                modelFinalidad.addElement(new ModelCombo("CompraPrimeraVivienda", "CompraPrimeraVivienda"));
                modelFinalidad.addElement(new ModelCombo("CambiarlaHipotecaActual", "CambiarlaHipotecaActual"));
                modelFinalidad.addElement(new ModelCombo("CompraSegundaVivienda", "CompraSegundaVivienda"));
                modelFinalidad.addElement(new ModelCombo("OtraFinalidad", "OtraFinalidad"));
                modelFinalidad.addElement(new ModelCombo("Reformas_Mejoras", "Reformas_Mejoras"));
                modelFinalidad.addElement(new ModelCombo("FinanciacionNegocios", "FinanciacionNegocios"));
                modelFinalidad.addElement(new ModelCombo("Refinanciacion_CancelacionDeudas", "Refinanciacion_CancelacionDeudas"));
                modelFinalidad.addElement(new ModelCombo("Resto", "Resto"));
                modelFinalidad.addElement(new ModelCombo("NoBuscoFinanciacionAdicional", "NoBuscoFinanciacionAdicional"));
                modelFinalidad.addElement(new ModelCombo("EliminarInterviniente", "EliminarInterviniente"));
                modelFinalidad.addElement(new ModelCombo("CompraPrimeraViviendaConMudanza", "CompraPrimeraViviendaConMudanza"));
                modelFinalidad.addElement(new ModelCombo("CompraSegundaViviendaConMudanza", "CompraSegundaViviendaConMudanza"));
                eFinalidadTasacion.setModel(new DefaultComboBoxModel(modelFinalidad));
                eFinalidadTasacion.setSelectedIndex(3);
                break;
            case "Fuente":
                modelFuente = new Vector();
                modelFuente.addElement(new ModelCombo("DatoNoDisponible", "DatoNoDisponible"));
                modelFuente.addElement(new ModelCombo("TransaccionesRecientes", "TransaccionesRecientes"));
                modelFuente.addElement(new ModelCombo("DatosDeOferta", "DatosDeOferta"));
                eFuenteDatos.setModel(new DefaultComboBoxModel(modelFuente));
                break;
            case "Ley mercado":
                modelLeyMercado = new Vector();
                modelLeyMercado.addElement(new ModelCombo("Si", "Si"));
                modelLeyMercado.addElement(new ModelCombo("No", "No"));
                modelLeyMercado.addElement(new ModelCombo("DatoNoDisponible", "DatoNoDisponible"));
                eLeyMercado.setModel(new DefaultComboBoxModel(modelLeyMercado));
                break;
            case "Método valoración":
                modelMetodoValoracion = new Vector();
                modelMetodoValoracion.addElement(new ModelCombo("MetodoDelCoste", "MetodoDelCoste"));
                modelMetodoValoracion.addElement(new ModelCombo("MetodoResidual", "MetodoResidual"));
                modelMetodoValoracion.addElement(new ModelCombo("MetodoDeComparacion", "MetodoDeComparacion"));
                modelMetodoValoracion.addElement(new ModelCombo("ActualizacionDeInmueblesArrendados", "ActualizacionDeInmueblesArrendados"));
                modelMetodoValoracion.addElement(new ModelCombo("OtrosMetodos", "OtrosMetodos"));
                modelMetodoValoracion.addElement(new ModelCombo("ValorMaximoLegal", "ValorMaximoLegal"));
                modelMetodoValoracion.addElement(new ModelCombo("ExplotacionEconomica", "ExplotacionEconomica"));
                modelMetodoValoracion.addElement(new ModelCombo("ActualizacionDeMercadoDeAlquileres", "ActualizacionDeMercadoDeAlquileres"));
                modelMetodoValoracion.addElement(new ModelCombo("ResidualEstatico", "ResidualEstatico"));
                modelMetodoValoracion.addElement(new ModelCombo("ResidualDinamico", "ResidualDinamico"));
                modelMetodoValoracion.addElement(new ModelCombo("OtroMetodo", "OtroMetodo"));
                modelMetodoValoracion.addElement(new ModelCombo("DerechoReal", "DerechoReal"));
                eMetodoValoracion.setModel(new DefaultComboBoxModel(modelMetodoValoracion));
                eMetodoValoracion.setSelectedIndex(2);
                break;
            case "Método valoración estadístico":
                modelMetodoValoracionEstadistico = new Vector();
                modelMetodoValoracionEstadistico.addElement(new ModelCombo("DatoNoDisponible", "DatoNoDisponible"));
                modelMetodoValoracionEstadistico.addElement(new ModelCombo("ModelosAutomaticosDeValoracion", "ModelosAutomaticosDeValoracion"));
                modelMetodoValoracionEstadistico.addElement(new ModelCombo("ProcedimientoMuestral", "ProcedimientoMuestral"));
                eMetodoValoracionEstadistico.setModel(new DefaultComboBoxModel(modelMetodoValoracionEstadistico));
                break;
            case "Visita inmueble":
                modelVisitaInmueble = new Vector();
                modelVisitaInmueble.addElement(new ModelCombo("Si", "Si"));
                modelVisitaInmueble.addElement(new ModelCombo("No", "No"));
                modelVisitaInmueble.addElement(new ModelCombo("DatoNoDisponible", "DatoNoDisponible"));
                eVisitaInmueble.setModel(new DefaultComboBoxModel(modelVisitaInmueble));
                break;
            case "Empresa tasadora":
                modelEmpresaTasadora = new Vector();
                BuildEmpresaTasadora(modelEmpresaTasadora);
                eEmpresaTasadora.setModel(new DefaultComboBoxModel(modelEmpresaTasadora));
                break;

        }
    }
    
    private void anadirHints(){
        /*
        ComboboxToolTipRenderer renderer = new ComboboxToolTipRenderer();
        List<String> tooltips = new ArrayList<>();
        Collections.sort(listaModelTooltips);
        eCapturarAvertencias.setRenderer(renderer);
        modelAdvertencias = new Vector();
        for (ModelTooltips modelTooltips : listaModelTooltips) {
            modelAdvertencias.addElement( new ModelCombo(modelTooltips.getCodigo(), modelTooltips.getNombre()) );
            tooltips.add(modelTooltips.getNombre());
        }
        eCapturarAvertencias.setModel(new DefaultComboBoxModel(modelAdvertencias));
        
        renderer.setTooltips(tooltips);
        
        ComboboxToolTipRenderer rendererCond = new ComboboxToolTipRenderer();
        List<String> tooltipsCond = new ArrayList<>();
        eCapturarCondicionantes.setRenderer(rendererCond);
        modelCondicionantes = new Vector();
        for (ModelTooltips modelTooltips : listaModelTooltipsCondicionantes) {
            modelCondicionantes.addElement( new ModelCombo(modelTooltips.getCodigo(), modelTooltips.getNombre()) );
            tooltipsCond.add(modelTooltips.getNombre());
        }
        eCapturarCondicionantes.setModel(new DefaultComboBoxModel(modelCondicionantes));
        rendererCond.setTooltips(tooltipsCond);
        */
    }
    
    public static ArrayList<Documento> cargarListaDocumentos(){
        ArrayList<Documento> listaDocumentos = new ArrayList<>();
        String caminoDirectorioRaiz = Utiles.rutaEnviadosTasacion;
	File dirRaiz = new File(caminoDirectorioRaiz);
        String archivos[] = dirRaiz.list();
        File dirTemp;
        Documento documento;
        for (String archivo : archivos) {
            dirTemp = new File(caminoDirectorioRaiz.concat(archivo));
            documento = new Documento(dirTemp.getName(), "Tasación", "Pendiente de procesar", "", "", "");
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

        jLabel27 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        bConvertir = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        eFechaCaducidad = new org.openswing.swing.client.DateControl();
        jLabel13 = new javax.swing.JLabel();
        eNombreDocumento = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        eAdvertencias = new javax.swing.JList<String>();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        eCondicionantes = new javax.swing.JList<String>();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        eFechaValoracionEstadistico = new org.openswing.swing.client.DateControl();
        eFechaVisita = new org.openswing.swing.client.DateControl();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        eNombreTasador = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        eObservaciones = new javax.swing.JTextArea();
        jLabel29 = new javax.swing.JLabel();
        eValorTasacion = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        eFechaTasacion = new org.openswing.swing.client.DateControl();
        jLabel1 = new javax.swing.JLabel();
        eCodigoTasacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        eCodigoTasacionEstadistica = new javax.swing.JTextField();
        eFinalidadTasacion = new javax.swing.JComboBox<String>();
        jLabel40 = new javax.swing.JLabel();
        eFuenteDatos = new javax.swing.JComboBox<String>();
        jLabel41 = new javax.swing.JLabel();
        eLeyMercado = new javax.swing.JComboBox<String>();
        jLabel42 = new javax.swing.JLabel();
        eMetodoValoracion = new javax.swing.JComboBox<String>();
        jLabel43 = new javax.swing.JLabel();
        eMetodoValoracionEstadistico = new javax.swing.JComboBox<String>();
        jLabel44 = new javax.swing.JLabel();
        eVisitaInmueble = new javax.swing.JComboBox<String>();
        jLabel45 = new javax.swing.JLabel();
        eNombreSolicitante = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        eValorHipotecario = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        eValorTasacionEstadistico = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        eEmpresaTasadora = new javax.swing.JComboBox<String>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jLabel27.setText("Nombre tasador:");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Extracción datos Tasación");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bConvertir.setText("Convertir");
        bConvertir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bConvertirMousePressed(evt);
            }
        });
        bConvertir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConvertirActionPerformed(evt);
            }
        });

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información Tasación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel6.setText("Fecha caducidad:");

        jLabel13.setText("Nombre documento:");

        eNombreDocumento.setEnabled(false);
        eNombreDocumento.setName("NOMBRE_DOCUMENTO"); // NOI18N
        eNombreDocumento.setNextFocusableComponent(eCodigoTasacion);

        jScrollPane2.setName("ScrollAdvertencias"); // NOI18N

        eAdvertencias.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eAdvertencias.setName("ADVERTENCIAS."); // NOI18N
        eAdvertencias.setNextFocusableComponent(eCondicionantes);
        jScrollPane2.setViewportView(eAdvertencias);

        jLabel14.setText("Advertencias:");

        jButton1.setText("+");
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

        jButton2.setText("-");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        jLabel15.setText("Condicionantes:");

        jButton3.setText("+");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton3MousePressed(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("-");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton4MousePressed(evt);
            }
        });

        jScrollPane3.setName("ScrollCondicionantes"); // NOI18N

        eCondicionantes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        eCondicionantes.setName("CONDICIONANTES."); // NOI18N
        eCondicionantes.setNextFocusableComponent(eEmpresaTasadora);
        jScrollPane3.setViewportView(eCondicionantes);

        jLabel16.setText("Empresa tasadora:");

        jLabel7.setText("Fecha valoracion estadistica:");

        jLabel8.setText("Fecha visita:");

        jLabel17.setText("Finalidad de tasación:");

        jLabel26.setText("Nombre tasador:");

        eNombreTasador.setName("NOMBRE_TASADOR."); // NOI18N
        eNombreTasador.setNextFocusableComponent(eNombreSolicitante);

        jLabel28.setText("Observaciones:");

        eObservaciones.setColumns(20);
        eObservaciones.setRows(5);
        eObservaciones.setName("OBSERVACIONES."); // NOI18N
        jScrollPane4.setViewportView(eObservaciones);

        jLabel29.setText("Valor tasación:");

        eValorTasacion.setName("VALOR_TASACION"); // NOI18N
        eValorTasacion.setNextFocusableComponent(eValorTasacionEstadistico);
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

        jLabel39.setText("Fecha tasación:");

        jLabel1.setText("Código tasación (Expediente):");

        eCodigoTasacion.setName("CODIGO_TASACION."); // NOI18N
        eCodigoTasacion.setNextFocusableComponent(eCodigoTasacionEstadistica);

        jLabel5.setText("Código tasación sociedad estadístico :");

        eCodigoTasacionEstadistica.setName("CODIGO_TASACION_ESTADISTICO."); // NOI18N
        eCodigoTasacionEstadistica.setNextFocusableComponent(eAdvertencias);

        eFinalidadTasacion.setName("FINALIDAD_TASACION"); // NOI18N
        eFinalidadTasacion.setNextFocusableComponent(eLeyMercado);

        jLabel40.setText("Fuente datos:");

        eFuenteDatos.setName("FUENTE_DATOS"); // NOI18N
        eFuenteDatos.setNextFocusableComponent(eMetodoValoracion);
        eFuenteDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eFuenteDatosActionPerformed(evt);
            }
        });

        jLabel41.setText("Ley de mercado:");

        eLeyMercado.setName("LEY_MERCADO"); // NOI18N
        eLeyMercado.setNextFocusableComponent(eMetodoValoracionEstadistico);

        jLabel42.setText("Método de valoración:");

        eMetodoValoracion.setName("METODO_VALORACION"); // NOI18N
        eMetodoValoracion.setNextFocusableComponent(eVisitaInmueble);

        jLabel43.setText("Método valoración estad:");

        eMetodoValoracionEstadistico.setName("METODO_VALORACION_ESTADISTICO"); // NOI18N
        eMetodoValoracionEstadistico.setNextFocusableComponent(eFuenteDatos);

        jLabel44.setText("Visita inmueble:");

        eVisitaInmueble.setName("VISITA_INMUEBLE"); // NOI18N
        eVisitaInmueble.setNextFocusableComponent(eNombreTasador);

        jLabel45.setText("Nombre solicitante:");

        eNombreSolicitante.setName("NOMBRE_SOLICITANTE."); // NOI18N
        eNombreSolicitante.setNextFocusableComponent(eValorHipotecario);

        jLabel46.setText("Valor hipotecario:");

        eValorHipotecario.setName("VALOR_HIPOTECARIO"); // NOI18N
        eValorHipotecario.setNextFocusableComponent(eValorTasacion);
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

        jLabel47.setText("Valor tasación estadístico:");

        eValorTasacionEstadistico.setName("VALOR_TASACION_ESTADISTICO."); // NOI18N
        eValorTasacionEstadistico.setNextFocusableComponent(eObservaciones);
        eValorTasacionEstadistico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                eValorTasacionEstadisticoFocusLost(evt);
            }
        });
        eValorTasacionEstadistico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eValorTasacionEstadisticoKeyTyped(evt);
            }
        });

        jButton9.setText("Añadir datos finca");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton9MousePressed(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDUFIR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable1);

        jButton10.setText("Eliminar finca");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton10MousePressed(evt);
            }
        });

        jButton11.setText("Limpiar formulario");
        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton11MousePressed(evt);
            }
        });

        jButton5.setText("Modificar finca");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton5MousePressed(evt);
            }
        });

        eEmpresaTasadora.setName("EMPRESA_TASADORA."); // NOI18N
        eEmpresaTasadora.setNextFocusableComponent(eFechaCaducidad);
        eEmpresaTasadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eEmpresaTasadoraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel44)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eVisitaInmueble, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel41)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(eLeyMercado, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel42))
                                        .addComponent(jLabel40))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(eFuenteDatos, 0, 138, Short.MAX_VALUE)
                                        .addComponent(eMetodoValoracion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel47)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eValorTasacionEstadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eValorTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel46)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(eValorHipotecario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(141, 141, 141)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton5))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addComponent(eFechaValoracionEstadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eFechaVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eMetodoValoracionEstadistico, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel26))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eNombreTasador, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(eNombreSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eFinalidadTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel13))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(eNombreDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton11))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(eCodigoTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(eCodigoTasacionEstadistica, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eEmpresaTasadora, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel39)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(eFechaTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton2))
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton4))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(eNombreDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(eCodigoTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5)
                    .addComponent(eCodigoTasacionEstadistica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel16)
                        .addComponent(eEmpresaTasadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(eFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(eFechaTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eFechaValoracionEstadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7))
                    .addComponent(eFechaVisita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(eFinalidadTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(eFuenteDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(eLeyMercado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(eMetodoValoracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(eMetodoValoracionEstadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(eVisitaInmueble, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(eNombreTasador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel45)
                                    .addComponent(eNombreSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(eValorHipotecario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9)
                            .addComponent(jButton10)
                            .addComponent(jButton5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(eValorTasacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(eValorTasacionEstadistico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125))
        );

        jScrollPane6.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(654, 654, 654)
                        .addComponent(bConvertir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(246, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bConvertir)
                .addContainerGap(433, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

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
        jXTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jXTable1PropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(jXTable1);

        jButton6.setText("Convertir a Doc KO");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton6MousePressed(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Actualizar");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton7MousePressed(evt);
            }
        });

        jButton8.setText("Cargar documento");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton8MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton6)
                            .addComponent(jButton8))
                        .addGap(0, 66, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bConvertirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConvertirMousePressed

        if (listaCondicionantes.isEmpty()) {
            listaCondicionantes.add(0, "NoExistenCondicionantesParaElCasoConcretoDeEsteInforme");
            eCondicionantes.setModel(listaCondicionantes);
        }

        if (listaAvertencias.isEmpty()) {
            listaAvertencias.add(0, "NoExistenAdvertenciasParaElCasoConcretoDeEsteInforme");
            eAdvertencias.setModel(listaAvertencias);
        }

        int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea Convertir?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (respuesta == 0) {
            JComponent comp = Utiles.ValidaControles(jPanel2);
            if (comp == null) {
                new TareaSegundoPlano(this, Utiles.msgTareaRealizandoConversion) {
                    @Override
                    protected void tareaHaRealizar() {
                        File archivo = new File(Utiles.rutaEnviadosTasacion.concat(eNombreDocumento.getText()));
                        Boolean movido = archivo.renameTo(new File(Utiles.rutaProcesadosTasacionPDF.concat(eNombreDocumento.getText())));
                        if (movido) {
                            Utiles.generarXMLTasacion(eNombreDocumento, listaFincas);
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

                if (comp instanceof DateControl && ((DateControl) comp).getValue() != null) {
                    JOptionPane.showMessageDialog(null, Utiles.msgFechaIncorrecta + comp.getName());
                    comp.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, Utiles.msgDebeIntroducir + comp.getName());
                    comp.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_bConvertirMousePressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        actualizarInfoDocumentos(Utiles.msgTareaCargandoDocumentos);
        llenarCombo("Ocupacion");
        llenarCombo("Tipo de identificacion");
        llenarCombo("Provincia");
        llenarCombo("Régimen protección");
        llenarCombo("Año construcción");
        llenarCombo("Estado conservación");
        llenarCombo("Advertencias");
        llenarCombo("Condicionantes");
        llenarCombo("Finalidad");
        llenarCombo("Fuente");
        llenarCombo("Ley mercado");
        llenarCombo("Método valoración");
        llenarCombo("Método valoración estadístico");
        llenarCombo("Visita inmueble");
        llenarCombo("Empresa tasadora");

        anadirHints();
        
        SelectEmpresaTasadoraInfo(0);
    }//GEN-LAST:event_formWindowOpened

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        /*
        listaAvertencias.addElement(listaModelTooltips.get(eCapturarAvertencias.getSelectedIndex()).getNombre() );
        eAdvertencias.setModel(listaAvertencias);
        */
        FormaAdvertencia formaAdvertencia = new FormaAdvertencia(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaAdvertencia.setLocation(GEnv.getCenterPoint().x-formaAdvertencia.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaAdvertencia.getHeight()/2 + 25);

        formaAdvertencia.setResizable(true);
        formaAdvertencia.setVisible(true);
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        if (eAdvertencias.getSelectedIndex() != -1) {
            int index = eAdvertencias.getSelectedIndex();
            listaAvertencias.removeElementAt(index);
            listaAdvertenciasSeleccionada.remove(index);
            eAdvertencias.setModel(listaAvertencias);
        }else{
            JOptionPane.showMessageDialog(null,Utiles.msgDebeSeleccionarAdvertenciaEliminar);
        }
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MousePressed
        /*
        listaCondicionantes.addElement(listaModelTooltipsCondicionantes.get(eCapturarCondicionantes.getSelectedIndex()).getNombre() );
        eCondicionantes.setModel(listaCondicionantes);
        */
        
        FormaCondicionantes formaCondicionantes = new FormaCondicionantes(this, false);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaCondicionantes.setLocation(GEnv.getCenterPoint().x-formaCondicionantes.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaCondicionantes.getHeight()/2 + 25);

        formaCondicionantes.setResizable(true);
        formaCondicionantes.setVisible(true);
          
    }//GEN-LAST:event_jButton3MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MousePressed
        if (eCondicionantes.getSelectedIndex() != -1) {

            int index = eCondicionantes.getSelectedIndex();
            listaCondicionantes.removeElementAt(index);
            listaCondicionantesSeleccionada.remove(index);
            eCondicionantes.setModel(listaCondicionantes);
        }else{
            JOptionPane.showMessageDialog(null,Utiles.msgDebeSeleccionarCondicionanteEliminar);
        }
    }//GEN-LAST:event_jButton4MousePressed

    private void eValorTasacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eValorTasacionKeyTyped
        Utiles.validarNumeroReal(evt, eValorTasacion, 9, 2);
    }//GEN-LAST:event_eValorTasacionKeyTyped

    private void jXTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jXTable1PropertyChange

    }//GEN-LAST:event_jXTable1PropertyChange

    private void jButton6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MousePressed
        if (jXTable1.getSelectedRow() != -1){
            new TareaSegundoPlano(this, Utiles.msgTareaProcesandoDocumento) {
                @Override
                protected void tareaHaRealizar() {
                    elementoSeleccionadoTabla = jXTable1.getSelectedRow();
                    documentoSeleccionado = listaDocumentos.get(elementoSeleccionadoTabla);
                    File archivo = new File(Utiles.rutaEnviadosTasacion.concat(documentoSeleccionado.getNombre()));
                    archivo.renameTo(new File(Utiles.rutaEnviadosDocumentosKO.concat(documentoSeleccionado.getNombre())));
                    listaDocumentos.remove(elementoSeleccionadoTabla);
                    DefaultTableModel modelo = (DefaultTableModel)jXTable1.getModel();
                    modelo.removeRow(elementoSeleccionadoTabla);
                    jXTable1.setModel(modelo);
                    limpiarComponentesFormulario();
                }
            }.ejecutarTarea();
        }else JOptionPane.showMessageDialog(null,"Debe seleccionar un documento de la lista.");
    }//GEN-LAST:event_jButton6MousePressed

    private void jButton7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MousePressed
        actualizarInfoDocumentos(Utiles.msgTareaCargandoDocumentos);
    }//GEN-LAST:event_jButton7MousePressed

    private void jButton8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MousePressed
        if (jXTable1.getSelectedRow() != -1){
            new TareaSegundoPlano(this, Utiles.msgTareaProcesandoDocumento) {
                @Override
                protected void tareaHaRealizar() {
                    elementoSeleccionadoTabla = jXTable1.getSelectedRow();
                    documentoSeleccionado = listaDocumentos.get(elementoSeleccionadoTabla);
                    limpiarComponentesFormulario();
                    eNombreDocumento.setText(documentoSeleccionado.getNombre());
                    //eReferencia.setText(documentoSeleccionado.getNombre().substring(0, documentoSeleccionado.getNombre().length()-4).split("_")[0]);
                    jXTable1.clearSelection();
                    File archivo = new File(Utiles.rutaEnviadosTasacion.concat("\\").concat(documentoSeleccionado.getNombre()));
                        try {
                            Desktop.getDesktop().open(archivo);
                        }catch (IOException ex) {
                        }
                    }
                }.ejecutarTarea();
            }else JOptionPane.showMessageDialog(null,"Debe seleccionar un documento de la lista.");
    }//GEN-LAST:event_jButton8MousePressed

    private void eValorHipotecarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eValorHipotecarioKeyTyped
        Utiles.validarNumeroReal(evt, eValorHipotecario, 9, 2);

        eValorTasacion.setText(eValorHipotecario.getText() + (evt.getKeyChar() != '\b' ? evt.getKeyChar() : ""));
        eValorTasacionEstadistico.setText(eValorHipotecario.getText() + (evt.getKeyChar() != '\b' ? evt.getKeyChar() : ""));
    }//GEN-LAST:event_eValorHipotecarioKeyTyped

    private void eValorTasacionEstadisticoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eValorTasacionEstadisticoKeyTyped
        Utiles.validarNumeroReal(evt, eValorTasacionEstadistico, 9, 2);
    }//GEN-LAST:event_eValorTasacionEstadisticoKeyTyped

    private void jButton9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MousePressed
        FormaProcesarFinca formaProcesarFinca = new FormaProcesarFinca(null, true, this, -1, null);
        GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        formaProcesarFinca.setLocation(GEnv.getCenterPoint().x-formaProcesarFinca.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaProcesarFinca.getHeight()/2 + 25);

        formaProcesarFinca.setResizable(false);
        formaProcesarFinca.setVisible(true);

        Utiles.llenarTabla(jTable1,listaFincas, "Fincas");
    }//GEN-LAST:event_jButton9MousePressed

    private void eFuenteDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eFuenteDatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eFuenteDatosActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1){
            DefaultTableModel tabla = (DefaultTableModel)jTable1.getModel();
            tabla.removeRow(indiceSeleccionado);
            jTable1.setModel(tabla);
            listaFincas.remove(indiceSeleccionado);
        } else
        JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneFincaEliminar);
    }//GEN-LAST:event_jButton10MousePressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        listaAvertencias.removeAllElements();
        for (String advertencia : listaAdvertenciasSeleccionada) {
            listaAvertencias.addElement(advertencia);
        }
        eAdvertencias.setModel(listaAvertencias);
        
        listaCondicionantes.removeAllElements();
        for (String condicionante : listaCondicionantesSeleccionada) {
            listaCondicionantes.addElement(condicionante);
        }
        eCondicionantes.setModel(listaCondicionantes);
    }//GEN-LAST:event_formWindowActivated

    private void jButton11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MousePressed
        limpiarComponentes();
    }//GEN-LAST:event_jButton11MousePressed

    private void jButton5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MousePressed
        int indiceSeleccionado = jTable1.getSelectedRow();
        if (indiceSeleccionado != -1){
            FormaProcesarFinca formaProcesarFinca = new FormaProcesarFinca(null, true, this, indiceSeleccionado, listaFincas.get(indiceSeleccionado));
            GraphicsEnvironment GEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            formaProcesarFinca.setLocation(GEnv.getCenterPoint().x-formaProcesarFinca.getWidth()/2 - 20,GEnv.getCenterPoint().y-formaProcesarFinca.getHeight()/2 + 25);
            formaProcesarFinca.setResizable(false);
            formaProcesarFinca.setVisible(true);
            Utiles.llenarTabla(jTable1,listaFincas, "Fincas");
        } else
            JOptionPane.showMessageDialog(null, Utiles.msgSeleccioneFincaModificar);
    }//GEN-LAST:event_jButton5MousePressed

    private void bConvertirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConvertirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bConvertirActionPerformed

    private void eValorHipotecarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eValorHipotecarioFocusLost
        if (!eValorHipotecario.getText().equals(""))
            if (eValorHipotecario.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eValorHipotecario.requestFocus();
            }
                
        
    }//GEN-LAST:event_eValorHipotecarioFocusLost

    private void eValorTasacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eValorTasacionFocusLost
        if (!eValorTasacion.getText().equals(""))
            if (eValorTasacion.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eValorTasacion.requestFocus();
            }
    }//GEN-LAST:event_eValorTasacionFocusLost

    private void eValorTasacionEstadisticoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_eValorTasacionEstadisticoFocusLost
        if (!eValorTasacionEstadistico.getText().equals(""))
            if (eValorTasacionEstadistico.getText().contains(".")){
                JOptionPane.showMessageDialog(null, "No se permite el caracter \".\" debe utilizar \",\" ");
                eValorTasacionEstadistico.requestFocus();
            }
    }//GEN-LAST:event_eValorTasacionEstadisticoFocusLost

    private void eEmpresaTasadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eEmpresaTasadoraActionPerformed

        SelectEmpresaTasadoraInfo(eEmpresaTasadora.getSelectedIndex());
    }//GEN-LAST:event_eEmpresaTasadoraActionPerformed

    private void SelectEmpresaTasadoraInfo(int index) {

        if (index != -1) {
            ComponenteFormulario aux = datosEmpresaTasadora.get(index);
            eCodigoTasacionEstadistica.setText(aux.getNombre());
            if (aux.getValor() != "") {
                eFechaValoracionEstadistico.setDate(BuildDate(aux.getValor()));
            } else {
                eFechaValoracionEstadistico.setDate(null);
            }
        }
    }

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
            java.util.logging.Logger.getLogger(FormaProcesarTasacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaProcesarTasacion dialog = new FormaProcesarTasacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JList<String> eAdvertencias;
    private javax.swing.JTextField eCodigoTasacion;
    private javax.swing.JTextField eCodigoTasacionEstadistica;
    private javax.swing.JList<String> eCondicionantes;
    private javax.swing.JComboBox<String> eEmpresaTasadora;
    private org.openswing.swing.client.DateControl eFechaCaducidad;
    private org.openswing.swing.client.DateControl eFechaTasacion;
    private org.openswing.swing.client.DateControl eFechaValoracionEstadistico;
    private org.openswing.swing.client.DateControl eFechaVisita;
    private javax.swing.JComboBox<String> eFinalidadTasacion;
    private javax.swing.JComboBox<String> eFuenteDatos;
    private javax.swing.JComboBox<String> eLeyMercado;
    private javax.swing.JComboBox<String> eMetodoValoracion;
    private javax.swing.JComboBox<String> eMetodoValoracionEstadistico;
    private javax.swing.JTextField eNombreDocumento;
    private javax.swing.JTextField eNombreSolicitante;
    private javax.swing.JTextField eNombreTasador;
    private javax.swing.JTextArea eObservaciones;
    private javax.swing.JTextField eValorHipotecario;
    private javax.swing.JTextField eValorTasacion;
    private javax.swing.JTextField eValorTasacionEstadistico;
    private javax.swing.JComboBox<String> eVisitaInmueble;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXTable jXTable1;
    // End of variables declaration//GEN-END:variables

    private void BuildEmpresaTasadora(Vector modelEmpresaTasadora) {
        modelEmpresaTasadora.addElement(new ModelCombo("AESVAL, LOGICA DE VALORACIONES, S.A.", "AESVAL, LOGICA DE VALORACIONES, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4499", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("Agrupación Técnica del Valor, S.A.", "Agrupación Técnica del Valor, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4633", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("ALIA TASACIONES", "ALIA TASACIONES"));
        datosEmpresaTasadora.add(new ComponenteFormulario("4406", "23/03/1992"));
        modelEmpresaTasadora.addElement(new ModelCombo("ARCO VALORACIONES", "ARCO VALORACIONES"));
        datosEmpresaTasadora.add(new ComponenteFormulario("4416", "20/12/1985"));
        modelEmpresaTasadora.addElement(new ModelCombo("ARQUITASA SOCIEDA DE TASACION, S.A.", "ARQUITASA SOCIEDA DE TASACION, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4404", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("BALKIDE BALORAZIOAK, S.A.", "BALKIDE BALORAZIOAK, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4444", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("BARNA-TASA, S.A.", "BARNA-TASA, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4470", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("CBRE VALUATION ADVISORY, S.A.", "CBRE VALUATION ADVISORY, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4630", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("COLLECTIU ARQUITECTES TAXADORS.S.A.", "COLLECTIU ARQUITECTES TAXADORS.S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4462", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("COMPAÑIA HISPANA DE TASACIONES Y VALORACIONES, S.A.", "COMPAÑIA HISPANA DE TASACIONES Y VALORACIONES, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4368", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("EUROVALORACIONES S.A.", "EUROVALORACIONES S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4388", "21/11/1990"));
        modelEmpresaTasadora.addElement(new ModelCombo("GENERAL DE VALORACIONES, S.A.", "GENERAL DE VALORACIONES, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4363", "17/05/1989"));
        modelEmpresaTasadora.addElement(new ModelCombo("GESVALT SOCIEDAD DE TASACION SA", "GESVALT SOCIEDAD DE TASACION SA"));
        datosEmpresaTasadora.add(new ComponenteFormulario("4455", "27/05/1994"));
        modelEmpresaTasadora.addElement(new ModelCombo("GLOVAL VALUATION, S.A.U", "GLOVAL VALUATION, S.A.U"));
        datosEmpresaTasadora.add(new ComponenteFormulario("4311", "07/03/1985"));
        modelEmpresaTasadora.addElement(new ModelCombo("GRUPO TASVALOR S.A.", "GRUPO TASVALOR S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4448", "13/12/1993"));
        modelEmpresaTasadora.addElement(new ModelCombo("INSTITUTO DE VALORACIONES, S.A.", "INSTITUTO DE VALORACIONES, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4498", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("KRATA, S.A.", "KRATA, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4323", "02/01/1987"));
        modelEmpresaTasadora.addElement(new ModelCombo("RISC VALOR, S.A.", "RISC VALOR, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4456", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("SAVILLS AGUIRRE NEWMAN VALORAC. Y TASAC., S.A", "SAVILLS AGUIRRE NEWMAN VALORAC. Y TASAC., S.A"));
        datosEmpresaTasadora.add(new ComponenteFormulario("4632", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("SOCIEDAD DE TASACION S.A.", "SOCIEDAD DE TASACION S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4301", "17/12/1982"));
        modelEmpresaTasadora.addElement(new ModelCombo("TASACIONES ANDALUZAS S.A.", "TASACIONES ANDALUZAS S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4358", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("TASACIONES HIPOTECARIAS RENTA S.A.", "TASACIONES HIPOTECARIAS RENTA S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4459", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("TASACIONES HIPOTECARIAS S.A.U.", "TASACIONES HIPOTECARIAS S.A.U."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4302", "23/11/1982"));
        modelEmpresaTasadora.addElement(new ModelCombo("TASALIA SOCIEDAD DE TASACION, S.A.", "TASALIA SOCIEDAD DE TASACION, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4383", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("TASASUR SOCIEDAD DE TASACIONES, S.A.", "TASASUR SOCIEDAD DE TASACIONES, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4494", "11/10/2005"));
        modelEmpresaTasadora.addElement(new ModelCombo("TASIBERICA, S.A.", "TASIBERICA, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4304", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("TECNITASA S.A.", "TECNITASA S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4315", "20/12/1985"));
        modelEmpresaTasadora.addElement(new ModelCombo("TINSA, S.A.", "TINSA, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4313", "07/05/1985"));
        modelEmpresaTasadora.addElement(new ModelCombo("UVE VALORACIONES, S.A.", "UVE VALORACIONES, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4631", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("Valoraciones Mediterráneo, S.A.,", "Valoraciones Mediterráneo, S.A.,"));
        datosEmpresaTasadora.add(new ComponenteFormulario("4350", "31/10/1988"));
        modelEmpresaTasadora.addElement(new ModelCombo("ZEHAZKI, S.A.", "ZEHAZKI, S.A."));
        datosEmpresaTasadora.add(new ComponenteFormulario("4349", ""));
        modelEmpresaTasadora.addElement(new ModelCombo("SERVATAS", "SERVATAS"));
        datosEmpresaTasadora.add(new ComponenteFormulario("4307", "17/09/1984"));
    }

    private Date BuildDate(String fecha) {
        String newDate = fecha.substring(6, 10);
        newDate += "/";
        newDate += fecha.substring(3, 5);
        newDate += "/" + fecha.substring(0, 2);

        Date date = new Date(newDate);
        return date;
    }
}
