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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.ComponenteFormulario;
import modelo.ModelCombo;
import modelo.RegistroPropiedad;
import org.openswing.swing.client.DateControl;
import org.openswing.swing.internationalization.java.Resources;
import util.ComboboxToolTipRenderer;
import util.LimitarNumeros;
import util.Utiles;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class FormaCargas extends javax.swing.JDialog {

    private Vector modelTipoCarga;
    private Vector modelMoneda;
    private FormaPrincipal fp;
    private int indiceCargaSeleccionada;
    private List<RegistroPropiedad> listaBeneficiario;
    Vector modelBeneficiario;
    
    /**
     * Creates new form FormaCargas
     * @param parent
     * @param modal
     */
    public FormaCargas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.fp = (FormaPrincipal)parent;
        
        RestrictedTextField restriccionLetra = new RestrictedTextField(eLetraEmbargo);
        restriccionLetra.setLimit(2);
        
        RestrictedTextField restriccionProtocolo = new RestrictedTextField(eProtocolo);
        restriccionProtocolo.setLimit(20);
        
        RestrictedTextField restriccionProtocoloCesion = new RestrictedTextField(eProtocoloCesion);
        restriccionProtocoloCesion.setLimit(20);
        
        RestrictedTextField restriccionNovacion = new RestrictedTextField(eDescripcionNovacion);
        restriccionNovacion.setLimit(100);
        
        RestrictedTextField restriccionResponsabilidadHipotecaria = new RestrictedTextField(eRespHipotecaria);
        restriccionResponsabilidadHipotecaria.setLimit(50);
        
        RestrictedTextField restriccionNotario = new RestrictedTextField(eNotario);
        restriccionNotario.setLimit(100);
        
        /*
        RestrictedTextField restriccionNumeroInscripcion = new RestrictedTextField(eNumeroInscripcion);
        restriccionNumeroInscripcion.setOnlyNums(true);
        restriccionNumeroInscripcion.setLimit(15);
        */
        RestrictedTextField restriccionRelacionCarga = new RestrictedTextField(eRelacionCarga);
        restriccionRelacionCarga.setLimit(50);
        
        RestrictedTextField restriccionCertificacionDominio = new RestrictedTextField(eCertificacionDominio);
        restriccionCertificacionDominio.setLimit(30);
        
        RestrictedTextField restriccionJusgado = new RestrictedTextField(eJusgado);
        restriccionJusgado.setLimit(50);
        
        RestrictedTextField restriccionAuto = new RestrictedTextField(eAutoProcedimiento);
        restriccionAuto.setLimit(50);
        
        RestrictedTextField restriccionCesion = new RestrictedTextField(eCesion);
        restriccionCesion.setLimit(50);
        
        RestrictedTextField restriccionNotarioCesion = new RestrictedTextField(eNotarioCesion);
        restriccionNotarioCesion.setLimit(50);
        
        RestrictedTextField restriccionBeneficiarioCesion = new RestrictedTextField(eBeneficiarioCesion);
        restriccionBeneficiarioCesion.setLimit(100);
        
        eRelacionCarga.setDocument(new LimitarNumeros());
        
        llenarComboTipoInmueble("Tipo carga");
        llenarComboTipoInmueble("Moneda");
        
        /*
        RestrictedTextField restriccionPlazoDeCarga = new RestrictedTextField(ePlazoDeCarga);
        restriccionPlazoDeCarga.setOnlyNums(true);
        restriccionPlazoDeCarga.setLimit(6);
        */
//ePlazoDeCarga.setDocument(new LimitarNumeros());
         /*
        RestrictedTextField restriccionTomo = new RestrictedTextField(eTomoCesion);
        restriccionTomo.setOnlyNums(true);
        restriccionTomo.setLimit(4);
        
        RestrictedTextField restriccionLibro = new RestrictedTextField(eLibroCesion);
        restriccionLibro.setOnlyNums(true);
        restriccionLibro.setLimit(4);
        
       
        RestrictedTextField restriccionFolio = new RestrictedTextField(eFolioCesion);
        restriccionFolio.setOnlyNums(true);
        restriccionFolio.setLimit(6);
        */
        
        eFechaVencimiento.setFormat(Resources.DMY);
        eFechaVencimiento.setName("FECHA_VENCIMIENTO.");
        eFechaVencimiento.setNextFocusableComponent(eFechaEscritura);
        
        eFechaEscritura.setFormat(Resources.DMY);
        eFechaEscritura.setName("FECHA_ESCRITURA.");
        eFechaEscritura.setNextFocusableComponent(eProtocolo);
        
        eFechaInscripcion.setFormat(Resources.DMY);
        eFechaInscripcion.setName("FECHA_INSCRIPCION_CARGA.");
        eFechaInscripcion.setNextFocusableComponent(eCertificacionDominio);
        
        eFechaCertificacion.setFormat(Resources.DMY);
        eFechaCertificacion.setName("FECHA_CERTIFICACION.");
        eFechaCertificacion.setNextFocusableComponent(eJusgado);
        
        eFechaAuto.setFormat(Resources.DMY);
        eFechaAuto.setName("FECHA_AUTO.");
        eFechaAuto.setNextFocusableComponent(eTomoCesion);
        
        eFechaCesion.setFormat(Resources.DMY);
        eFechaCesion.setName("FECHA_CESION.");
        eFechaCesion.setNextFocusableComponent(eFechaInscripcionCesion);
        
        eFechaInscripcionCesion.setFormat(Resources.DMY);
        eFechaInscripcionCesion.setName("FECHA_INSCRIPCION_CESION.");
        eFechaInscripcionCesion.setNextFocusableComponent(eNotarioCesion);
        
    }
    
    private void llenarPoblaciones(){
        ComboboxToolTipRenderer renderer = new ComboboxToolTipRenderer();
        eBeneficiarioBanco.setRenderer(renderer);
        List<String> tooltips = new ArrayList<>();
        
        listaBeneficiario = Utiles.leerBeneficiarios();
        modelBeneficiario = new Vector();
        Collections.sort(listaBeneficiario);
        modelBeneficiario.addElement( new ModelCombo("", "NO_DEFINIDO") );
        tooltips.add("NO_DEFINIDO");
        for (RegistroPropiedad registroPropiedad : listaBeneficiario) {
            modelBeneficiario.addElement( new ModelCombo(registroPropiedad.getCodigo(), registroPropiedad.getNombre()) );
            tooltips.add(registroPropiedad.getNombre());
        }
        eBeneficiarioBanco.setModel(new DefaultComboBoxModel(modelBeneficiario));
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
            case "Tipo carga":  modelTipoCarga = new Vector();
                                ComboboxToolTipRenderer renderer = new ComboboxToolTipRenderer();
                                eTipoCarga.setRenderer(renderer);
                                List<String> tooltips = new ArrayList<>();
                                modelTipoCarga.addElement( new ModelCombo("HIPOTECA", "HIPOTECA" ) );tooltips.add("HIPOTECA");
                                modelTipoCarga.addElement( new ModelCombo("EMBARGO", "EMBARGO" ) );tooltips.add("EMBARGO");
                                modelTipoCarga.addElement( new ModelCombo("NOVACIÓN", "NOVACIÓN" ) );tooltips.add("NOVACIÓN");
                                modelTipoCarga.addElement( new ModelCombo("DERECHO_SUPERFICIE", "DERECHO_SUPERFICIE" ) );tooltips.add("DERECHO_SUPERFICIE");
                                modelTipoCarga.addElement( new ModelCombo("CONCESION_ADVA", "CONCESION_ADVA" ) );tooltips.add("CONCESION_ADVA");
                                modelTipoCarga.addElement( new ModelCombo("USUFRUCTO", "USUFRUCTO" ) ); tooltips.add("USUFRUCTO");
                                modelTipoCarga.addElement( new ModelCombo("DERECHO_USO", "DERECHO_USO" ) ); tooltips.add("DERECHO_USO");
                                modelTipoCarga.addElement( new ModelCombo("DERECHO_HABITACION", "DERECHO_HABITACION" ) ); tooltips.add("DERECHO_HABITACION");
                                modelTipoCarga.addElement( new ModelCombo("APTO_TURISTICO", "APTO_TURISTICO" ) ); tooltips.add("APTO_TURISTICO");
                                modelTipoCarga.addElement( new ModelCombo("CONCURSO_ACREEDORES", "CONCURSO_ACREEDORES" ) ); tooltips.add("CONCURSO_ACREEDORES");
                                modelTipoCarga.addElement( new ModelCombo("ART_207", "ART_207" ) ); tooltips.add("ART_207");
                                modelTipoCarga.addElement( new ModelCombo("ART_28", "ART_28" ) ); tooltips.add("ART_28");
                                modelTipoCarga.addElement( new ModelCombo("CERTIFICADO_CARGAS", "CERTIFICADO_CARGAS" ) ); tooltips.add("CERTIFICADO_CARGAS");
                                modelTipoCarga.addElement( new ModelCombo("OTROS", "OTROS" ) ); tooltips.add("OTROS");
                                modelTipoCarga.addElement( new ModelCombo("ASIENTO_PENDIENTE_DESPACHO", "ASIENTO_PENDIENTE_DESPACHO" ) ); tooltips.add("ASIENTO_PENDIENTE_DESPACHO");
                                modelTipoCarga.addElement( new ModelCombo("OTROS_PRESUPUESTO", "OTROS_PRESUPUESTO" ) ); tooltips.add("OTROS_PRESUPUESTO");
                                modelTipoCarga.addElement( new ModelCombo("EMBARGO_OOPP", "EMBARGO_OOPP" ) ); tooltips.add("EMBARGO_OOPP");
                                modelTipoCarga.addElement( new ModelCombo("ART_ICULO_205", "ART_ICULO_205" ) ); tooltips.add("ART_ICULO_205");
                                modelTipoCarga.addElement( new ModelCombo("CENSO", "CENSO" ) ); tooltips.add("CENSO");
                                modelTipoCarga.addElement( new ModelCombo("CONDICION_RESOLUTORIA", "CONDICION_RESOLUTORIA" ) ); tooltips.add("CONDICION_RESOLUTORIA");
                                modelTipoCarga.addElement( new ModelCombo("CERTIFICADO_CARGAS_OOPP", "CERTIFICADO_CARGAS_OOPP" ) ); tooltips.add("CERTIFICADO_CARGAS_OOPP");
                                modelTipoCarga.addElement( new ModelCombo("SEGURO_DECENAL", "SEGURO_DECENAL" ) ); tooltips.add("SEGURO_DECENAL");
                                modelTipoCarga.addElement( new ModelCombo("ASIENTO_PENDIENTE_CAMBIO_TITULARIDAD", "ASIENTO_PENDIENTE_CAMBIO_TITULARIDAD" ) ); tooltips.add("ASIENTO_PENDIENTE_CAMBIO_TITULARIDAD");
                                modelTipoCarga.addElement( new ModelCombo("ASIENTO_PENDIENTE_HIPOTECA", "ASIENTO_PENDIENTE_HIPOTECA" ) ); tooltips.add("ASIENTO_PENDIENTE_HIPOTECA");
                                modelTipoCarga.addElement( new ModelCombo("ASIENTO_PENDIENTE_AGRUPACION_SEGREGACION_FINCAS", "ASIENTO_PENDIENTE_AGRUPACION_SEGREGACION_FINCAS" ) ); tooltips.add("ASIENTO_PENDIENTE_AGRUPACION_SEGREGACION_FINCAS");
                                modelTipoCarga.addElement( new ModelCombo("ASIENTO_PENDIENTE_EMBARGO_RESTO", "ASIENTO_PENDIENTE_EMBARGO_RESTO" ) ); tooltips.add("ASIENTO_PENDIENTE_EMBARGO_RESTO");
                                modelTipoCarga.addElement( new ModelCombo("ASIENTO_PENDIENTE_DESCALIFICACION", "ASIENTO_PENDIENTE_DESCALIFICACION" ) ); tooltips.add("ASIENTO_PENDIENTE_DESCALIFICACION");
                                modelTipoCarga.addElement( new ModelCombo("OPCION_COMPRA_FAVOR_TERCEROS", "OPCION_COMPRA_FAVOR_TERCEROS" ) ); tooltips.add("DERECHOS_SOBRE_BIENES_DEMANIALES");
                                modelTipoCarga.addElement( new ModelCombo("DERECHOS_SOBRE_BIENES_DEMANIALES", "DERECHOS_SOBRE_BIENES_DEMANIALES" ) ); tooltips.add("DERECHOS_SOBRE_BIENES_DEMANIALES");
                                modelTipoCarga.addElement( new ModelCombo("FINCAS_INDIVISAS", "FINCAS_INDIVISAS" ) ); tooltips.add("FINCAS_INDIVISAS");
                                modelTipoCarga.addElement( new ModelCombo("CARGAS_URBANISTICAS_VIGOR", "CARGAS_URBANISTICAS_VIGOR" ) ); tooltips.add("CARGAS_URBANISTICAS_VIGOR");
                                modelTipoCarga.addElement( new ModelCombo("ASIENTO_PENDIENTE_EMBARGO_OOPP", "ASIENTO_PENDIENTE_EMBARGO_OOPP" ) ); tooltips.add("ASIENTO_PENDIENTE_EMBARGO_OOPP");
                                modelTipoCarga.addElement( new ModelCombo("DERECHO_VUELO", "DERECHO_VUELO" ) ); tooltips.add("DERECHO_VUELO");
                                modelTipoCarga.addElement( new ModelCombo("FUERA_ORDENACION_CAMBIO_PGOU", "FUERA_ORDENACION_CAMBIO_PGOU " ) ); tooltips.add("FUERA_ORDENACION_CAMBIO_PGOU");
                                modelTipoCarga.addElement( new ModelCombo("TITULAR_REGISTRAL_INCAPACITADO", "TITULAR_REGISTRAL_INCAPACITADO" ) ); tooltips.add("TITULAR_REGISTRAL_INCAPACITADO");
                                modelTipoCarga.addElement( new ModelCombo("TITULAR_MENOR_EDAD", "TITULAR_MENOR_EDAD" ) ); tooltips.add("TITULAR_MENOR_EDAD");
                                modelTipoCarga.addElement( new ModelCombo("ALODIO_LAUDEMIO", "ALODIO_LAUDEMIO" ) ); tooltips.add("ALODIO_LAUDEMIO");
                                modelTipoCarga.addElement( new ModelCombo("AFECCION_FISCAL", "AFECCION_FISCAL" ) ); tooltips.add("AFECCION_FISCAL");
                                modelTipoCarga.addElement( new ModelCombo("DERECHO_RESERVA", "DERECHO_RESERVA" ) ); tooltips.add("DERECHO_RESERVA");
                                modelTipoCarga.addElement( new ModelCombo("LIMITACION_DOMINIO", "LIMITACION_DOMINIO" ) ); tooltips.add("LIMITACION_DOMINIO");
                                modelTipoCarga.addElement( new ModelCombo("SERVIDUMBRE", "SERVIDUMBRE" ) ); tooltips.add("SERVIDUMBRE");
                                modelTipoCarga.addElement( new ModelCombo("SUSTITUCION_FIDEICOMISA", "SUSTITUCION_FIDEICOMISA" ) ); tooltips.add("SUSTITUCION_FIDEICOMISA");
                                modelTipoCarga.addElement( new ModelCombo("OTROS_GRAVE", "OTROS_GRAVE" ) ); tooltips.add("OTROS_GRAVE");
                                renderer.setTooltips(tooltips);
                                eTipoCarga.setModel(new DefaultComboBoxModel(modelTipoCarga));
                                break;
            case "Moneda":modelMoneda = new Vector();
                                modelMoneda.addElement( new ModelCombo("Euro", "Euro" ) );
                                modelMoneda.addElement( new ModelCombo("Dólar Estadounidense", "Dólar Estadounidense" ) );
                                modelMoneda.addElement( new ModelCombo("Libra Esterlina", "Libra Esterlina" ) );
                                modelMoneda.addElement( new ModelCombo("Yen", "Yen" ) );
                                eMoneda.setModel(new DefaultComboBoxModel(modelMoneda));
                                break;
        }
     }
     
     private void limpiarComponentesFormulario(){
        eLetraEmbargo.setText("");
        eBeneficiarioCesion.setText("");
        ePlazoDeCarga.setText("");
        eNotarioCesion.setText("");
        eProtocoloCesion.setText("");
        eRespHipotecaria.setText("");
        ePorcentajeParticipacion.setText("");
        eDescripcionNovacion.setText("");
        eImporteCarga.setText("");
        eInteresesCarga.setText("");
        eInteresesDemora.setText("");
        eCostas.setText("");
        eGastosProcesales.setText("");
        eOtrosGastos.setText("");
        eRecargoApremio.setText("");
        ePlazoDeCarga.setText("");
        eProtocolo.setText("");
        eNotario.setText("");
        eCertificacionDominio.setText("");
        eJusgado.setText("");
        eAutoProcedimiento.setText("");
        eCesion.setText("");        
        eNotario.setText("");
        eProtocolo.setText("");
        eTomoCesion.setText("");
        eLibroCesion.setText("");
        eFolioCesion.setText("");
        eNumeroInscripcion.setText("");
        eRelacionCarga.setText("");
        eIgualdadRango.setText("");
        eBeneficiario.setText("");
        
        //Combo
        eTipoCarga.setSelectedIndex(0); 
        eMoneda.setSelectedIndex(0); 
        eBeneficiarioBanco.setSelectedIndex(0); 

        //Fechas
        Date fecha = null;
        eFechaEscritura.setDate(fecha);
        eFechaVencimiento.setDate(fecha);
        eFechaAuto.setDate(fecha);
        eFechaCertificacion.setDate(fecha);
        eFechaCesion.setDate(fecha);
        eFechaInscripcion.setDate(fecha);
        eFechaInscripcionCesion.setDate(fecha);
        eFechaVencimiento.setDate(fecha);
                
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
        seleccionarElementoCombo(modelTipoCarga, eTipoCarga, fp.getListaCargas().get(indiceCargaSeleccionada).get(0).getValor());
        seleccionarElementoCombo(modelMoneda, eMoneda, fp.getListaCargas().get(indiceCargaSeleccionada).get(7).getValor());
        seleccionarElementoCombo(modelBeneficiario, eBeneficiarioBanco, fp.getListaCargas().get(indiceCargaSeleccionada).get(2).getValor());
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha;
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(16).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(16).getValor());
                eFechaVencimiento.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(17).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(17).getValor());
                eFechaEscritura.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(20).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(20).getValor());
                eFechaInscripcion.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(22).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(22).getValor());
                eFechaCertificacion.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(25).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(25).getValor());
                eFechaAuto.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(34).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(34).getValor());
                eFechaCesion.setDate(fecha);
            }
            if (!fp.getListaCargas().get(indiceCargaSeleccionada).get(35).getValor().equals("")){
                fecha = formato.parse(fp.getListaCargas().get(indiceCargaSeleccionada).get(35).getValor());
                eFechaInscripcionCesion.setDate(fecha);
            }
        } catch (ParseException ex) {
            Logger.getLogger(FormaCargas.class.getName()).log(Level.SEVERE, null, ex);
        }
        eLetraEmbargo.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(1).getValor());
        eBeneficiario.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(3).getValor());
        ePorcentajeParticipacion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(4).getValor());
        eDescripcionNovacion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(5).getValor());
        eImporteCarga.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(6).getValor());
        eInteresesCarga.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(8).getValor());
        eInteresesDemora.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(9).getValor());
        eCostas.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(10).getValor());
        eGastosProcesales.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(11).getValor());
        eOtrosGastos.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(12).getValor());
        eRespHipotecaria.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(13).getValor());
        eRecargoApremio.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(14).getValor());
        ePlazoDeCarga.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(15).getValor());
        eProtocolo.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(18).getValor());
        eNotario.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(19).getValor());
        eCertificacionDominio.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(21).getValor());
        eJusgado.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(23).getValor());
        eAutoProcedimiento.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(24).getValor());
        eTomoCesion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(26).getValor());
        eLibroCesion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(27).getValor());
        String folio = fp.getListaCargas().get(indiceCargaSeleccionada).get(28).getValor();
        eFolioCesion.setText(folio);
        eNumeroInscripcion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(29).getValor());
        eRelacionCarga.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(30).getValor());
        eIgualdadRango.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(31).getValor());
        eCesion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(32).getValor());
        eBeneficiarioCesion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(33).getValor());
        eNotarioCesion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(36).getValor());
        eProtocoloCesion.setText(fp.getListaCargas().get(indiceCargaSeleccionada).get(37).getValor());
        
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        eTipoCarga = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        eLetraEmbargo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ePorcentajeParticipacion = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        eDescripcionNovacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        eImporteCarga = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        eMoneda = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        eInteresesCarga = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        eInteresesDemora = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        eCostas = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        eGastosProcesales = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        eOtrosGastos = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        eRespHipotecaria = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        eRecargoApremio = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        ePlazoDeCarga = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        eFechaVencimiento = new org.openswing.swing.client.DateControl();
        jLabel17 = new javax.swing.JLabel();
        eFechaEscritura = new org.openswing.swing.client.DateControl();
        jLabel18 = new javax.swing.JLabel();
        eProtocolo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        eNotario = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        eFechaInscripcion = new org.openswing.swing.client.DateControl();
        jLabel21 = new javax.swing.JLabel();
        eCertificacionDominio = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        eFechaCertificacion = new org.openswing.swing.client.DateControl();
        jLabel23 = new javax.swing.JLabel();
        eJusgado = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        eAutoProcedimiento = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        eFechaAuto = new org.openswing.swing.client.DateControl();
        eAdicionar = new javax.swing.JButton();
        eModificar = new javax.swing.JButton();
        eEliminar = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        eTomoCesion = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        eLibroCesion = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        eFolioCesion = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        eNumeroInscripcion = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        eRelacionCarga = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        eIgualdadRango = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        eCesion = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        eBeneficiarioCesion = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        eFechaCesion = new org.openswing.swing.client.DateControl();
        jLabel29 = new javax.swing.JLabel();
        eFechaInscripcionCesion = new org.openswing.swing.client.DateControl();
        jLabel30 = new javax.swing.JLabel();
        eNotarioCesion = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        eProtocoloCesion = new javax.swing.JTextField();
        eBeneficiarioBanco = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        eBeneficiario = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cargas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Letra", "Beneficiario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setText("Tipo carga:");

        eTipoCarga.setFocusCycleRoot(true);
        eTipoCarga.setName("TIPO_CARGA"); // NOI18N
        eTipoCarga.setNextFocusableComponent(eLetraEmbargo);

        jLabel2.setText("Letra Ident. embargo:");
        jLabel2.setName(""); // NOI18N

        eLetraEmbargo.setName("LETRA."); // NOI18N
        eLetraEmbargo.setNextFocusableComponent(eBeneficiarioBanco);

        jLabel3.setText("BeneficiarioB:");

        jLabel4.setText("Porcentaje participación:");

        ePorcentajeParticipacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.##"))));
        ePorcentajeParticipacion.setName("BENEFICIARIO_PARTICIPACION."); // NOI18N
        ePorcentajeParticipacion.setNextFocusableComponent(eDescripcionNovacion);

        jLabel5.setText("Descripción novación:");

        eDescripcionNovacion.setName("DESCRIPCION_NOVACION."); // NOI18N
        eDescripcionNovacion.setNextFocusableComponent(eImporteCarga);

        jLabel6.setText("Importe carga:");

        eImporteCarga.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("############.##"))));
        eImporteCarga.setName("IMPORTE_HIPOTECA."); // NOI18N
        eImporteCarga.setNextFocusableComponent(eMoneda);
        eImporteCarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eImporteCargaKeyTyped(evt);
            }
        });

        jLabel7.setText("Moneda:");

        eMoneda.setName("MONEDA."); // NOI18N
        eMoneda.setNextFocusableComponent(eInteresesCarga);

        jLabel8.setText("Intereses de carga:");

        eInteresesCarga.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########.###"))));
        eInteresesCarga.setName("INTERESES_HIPOTECA."); // NOI18N
        eInteresesCarga.setNextFocusableComponent(eInteresesDemora);
        eInteresesCarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eInteresesCargaKeyTyped(evt);
            }
        });

        jLabel9.setText("Intereses de demora:");
        jLabel9.setName("INTERESES_DEMORA."); // NOI18N

        eInteresesDemora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########.###"))));
        eInteresesDemora.setName("INTERESES_DEMORA."); // NOI18N
        eInteresesDemora.setNextFocusableComponent(eCostas);
        eInteresesDemora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eInteresesDemoraKeyTyped(evt);
            }
        });

        jLabel10.setText("Costas:");

        eCostas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########.###"))));
        eCostas.setName("COSTAS_GASTOS."); // NOI18N
        eCostas.setNextFocusableComponent(eGastosProcesales);
        eCostas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eCostasKeyTyped(evt);
            }
        });

        jLabel11.setText("Gastos procesales:");

        eGastosProcesales.setName("GASTOS_PROCESALES."); // NOI18N
        eGastosProcesales.setNextFocusableComponent(eOtrosGastos);
        eGastosProcesales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eGastosProcesalesKeyTyped(evt);
            }
        });

        jLabel12.setText("Otros gastos:");

        eOtrosGastos.setName("OTROS_GASTOS."); // NOI18N
        eOtrosGastos.setNextFocusableComponent(eRespHipotecaria);
        eOtrosGastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eOtrosGastosKeyTyped(evt);
            }
        });

        jLabel13.setText("Resp. hipotecaria:");

        eRespHipotecaria.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########.###"))));
        eRespHipotecaria.setName("RESPONSABILIDAD_HIPOTECA."); // NOI18N
        eRespHipotecaria.setNextFocusableComponent(eRecargoApremio);

        jLabel14.setText("Recargo de apremio:");

        eRecargoApremio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########.###"))));
        eRecargoApremio.setName("RECARGO_APREMIO."); // NOI18N
        eRecargoApremio.setNextFocusableComponent(ePlazoDeCarga);
        eRecargoApremio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eRecargoApremioKeyTyped(evt);
            }
        });

        jLabel15.setText("Plazo de carga:");

        ePlazoDeCarga.setName("PLAZO_CARGA."); // NOI18N
        ePlazoDeCarga.setNextFocusableComponent(eFechaVencimiento);
        ePlazoDeCarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ePlazoDeCargaKeyTyped(evt);
            }
        });

        jLabel16.setText("Fecha venc. hipotecario:");

        jLabel17.setText("Fecha de escritura:");

        jLabel18.setText("Nº Protocolo/Auto:");

        eProtocolo.setName("PROTOCOLO."); // NOI18N
        eProtocolo.setNextFocusableComponent(eNotario);

        jLabel19.setText("Notario:");

        eNotario.setName("NOTARIO."); // NOI18N
        eNotario.setNextFocusableComponent(eFechaInscripcion);

        jLabel20.setText("Fecha inscripción:");

        jLabel21.setText("Certificación dominio:");

        eCertificacionDominio.setName("CERTIFICACION_DOMINIO."); // NOI18N
        eCertificacionDominio.setNextFocusableComponent(eFechaCertificacion);
        eCertificacionDominio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eCertificacionDominioKeyReleased(evt);
            }
        });

        jLabel22.setText("Fecha Certificación:");

        jLabel23.setText("Juzgado:");

        eJusgado.setName("JUZGADO."); // NOI18N
        eJusgado.setNextFocusableComponent(eAutoProcedimiento);

        jLabel24.setText("Auto/Procedimiento:");

        eAutoProcedimiento.setName("AUTO."); // NOI18N
        eAutoProcedimiento.setNextFocusableComponent(eFechaAuto);

        jLabel25.setText("Fecha auto:");

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

        jLabel32.setText("Tomo:");

        eTomoCesion.setName("TOMO."); // NOI18N
        eTomoCesion.setNextFocusableComponent(eLibroCesion);
        eTomoCesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eTomoCesionKeyTyped(evt);
            }
        });

        jLabel33.setText("Libro:");

        eLibroCesion.setName("LIBRO."); // NOI18N
        eLibroCesion.setNextFocusableComponent(eFolioCesion);
        eLibroCesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eLibroCesionKeyTyped(evt);
            }
        });

        jLabel34.setText("Folio:");

        eFolioCesion.setName("FOLIO."); // NOI18N
        eFolioCesion.setNextFocusableComponent(eNumeroInscripcion);
        eFolioCesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eFolioCesionKeyTyped(evt);
            }
        });

        jLabel36.setText("Nº Inscripción:");

        eNumeroInscripcion.setName("NUM_INSCRIPCION."); // NOI18N
        eNumeroInscripcion.setNextFocusableComponent(eRelacionCarga);
        eNumeroInscripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                eNumeroInscripcionKeyTyped(evt);
            }
        });

        jLabel37.setText("Relación con carga:");

        eRelacionCarga.setName("RELACION_CARGA."); // NOI18N
        eRelacionCarga.setNextFocusableComponent(eIgualdadRango);

        jLabel38.setText("Igualdad de Rango:");

        eIgualdadRango.setName("IGUALDAD_RANGO."); // NOI18N
        eIgualdadRango.setNextFocusableComponent(eCesion);

        jLabel26.setText("Cesión:");

        eCesion.setName("CESION."); // NOI18N
        eCesion.setNextFocusableComponent(eBeneficiarioCesion);
        eCesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eCesionKeyReleased(evt);
            }
        });

        jLabel27.setText("Beneficiario cesión:");

        eBeneficiarioCesion.setName("BENEFICIARIO_CESION."); // NOI18N
        eBeneficiarioCesion.setNextFocusableComponent(eFechaCesion);

        jLabel28.setText("Fecha cesión:");

        jLabel29.setText("Fecha Inscripción cesión:");

        jLabel30.setText("Notario cesión:");

        eNotarioCesion.setName("NOTARIO_CESION."); // NOI18N
        eNotarioCesion.setNextFocusableComponent(eProtocoloCesion);

        jLabel31.setText("Protocolo cesión:");

        eProtocoloCesion.setName("PROTOCOLO_CESION."); // NOI18N

        eBeneficiarioBanco.setToolTipText("");
        eBeneficiarioBanco.setName("BENEFICIARIO_HIPOTECA_BANCO"); // NOI18N
        eBeneficiarioBanco.setNextFocusableComponent(eBeneficiario);
        eBeneficiarioBanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eBeneficiarioBancoActionPerformed(evt);
            }
        });

        jLabel35.setText("Beneficiario:");

        eBeneficiario.setName("BENEFICIARIO_HIPOTECA."); // NOI18N
        eBeneficiario.setNextFocusableComponent(ePorcentajeParticipacion);

        jButton1.setText("Limpiar formulario");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel35))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(ePorcentajeParticipacion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eBeneficiario, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(eDescripcionNovacion, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                        .addGap(1, 1, 1))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(eMoneda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(eInteresesCarga)
                                    .addComponent(eInteresesDemora)
                                    .addComponent(eCostas)
                                    .addComponent(eGastosProcesales)
                                    .addComponent(eOtrosGastos)
                                    .addComponent(eImporteCarga)
                                    .addComponent(eRespHipotecaria, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eTipoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eBeneficiarioBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(eLetraEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel19)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel22)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel24))
                                    .addGap(15, 15, 15))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel25)
                                    .addGap(18, 18, 18)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(eFechaInscripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eRecargoApremio)
                            .addComponent(ePlazoDeCarga)
                            .addComponent(eFechaVencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(eFechaEscritura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eProtocolo)
                            .addComponent(eNotario)
                            .addComponent(eCertificacionDominio)
                            .addComponent(eFechaCertificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(eJusgado)
                            .addComponent(eAutoProcedimiento)
                            .addComponent(eFechaAuto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(eIgualdadRango)
                                        .addComponent(eNotarioCesion, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(eFechaInscripcionCesion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(eFechaCesion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(eBeneficiarioCesion, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(eCesion, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(eProtocoloCesion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(eNumeroInscripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                        .addComponent(eRelacionCarga, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(eLibroCesion)
                                    .addComponent(eTomoCesion, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eFolioCesion, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))))
                        .addGap(294, 294, 294))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(eEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eAdicionar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(eRecargoApremio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(ePlazoDeCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel17)
                                        .addComponent(eFechaEscritura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(eProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel18))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel20)
                                    .addGap(118, 118, 118))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel36)
                                        .addComponent(eNumeroInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel37)
                                        .addComponent(eRelacionCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel38)
                                        .addComponent(eIgualdadRango, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel26)
                                        .addComponent(eCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel27)
                                        .addComponent(eBeneficiarioCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel28)
                                        .addComponent(eFechaCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(eFechaInscripcionCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel29))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(eNotarioCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel30))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel31)
                                        .addComponent(eProtocoloCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(eBeneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(ePorcentajeParticipacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(eDescripcionNovacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(eImporteCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(eMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(eInteresesCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(eInteresesDemora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(eCostas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(eGastosProcesales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(eOtrosGastos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(eRespHipotecaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(eTomoCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eLibroCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(eFolioCesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(eTipoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(eLetraEmbargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(eBeneficiarioBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(eNotario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(eFechaInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eCertificacionDominio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(eFechaCertificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(eJusgado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(eAutoProcedimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(eFechaAuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eAdicionar)
                    .addComponent(eModificar)
                    .addComponent(eEliminar))
                .addContainerGap(718, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eEliminarActionPerformed

    private void eCesionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eCesionKeyReleased
        /*
        String str = eCesion.getText();
        if ((str.equals("s") ) || (str.equals("S") ) || (str.equals("n") ) || (str.equals("N") )){
            eCesion.setText(str.toUpperCase());

        }else
        eCesion.setText("");
        */
    }//GEN-LAST:event_eCesionKeyReleased

    private void eCertificacionDominioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eCertificacionDominioKeyReleased
        /*
        String str = eCertificacionDominio.getText();
        if ((str.equals("s") ) || (str.equals("S") ) || (str.equals("n") ) || (str.equals("N") ))
        eCertificacionDominio.setText(str.toUpperCase());
        else
        eCertificacionDominio.setText("");
        */
    }//GEN-LAST:event_eCertificacionDominioKeyReleased

    private void eAdicionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eAdicionarMousePressed
        JComponent comp = Utiles.ValidaControles(jPanel1);
        if (comp == null){
            if (Utiles.validarFecha(eFechaAuto) && Utiles.validarFecha(eFechaCertificacion) && Utiles.validarFecha(eFechaCesion) && 
                Utiles.validarFecha(eFechaEscritura) && Utiles.validarFecha(eFechaInscripcion) && Utiles.validarFecha(eFechaInscripcionCesion) && Utiles.validarFechaCotaInferior(eFechaVencimiento)) {
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
                JOptionPane.showMessageDialog(null, Utiles.msgOperacionRealizada);
                limpiarComponentesFormulario();
            } else {
                    JOptionPane.showMessageDialog(null,"Ha definido alguna fecha incorrectamente.");
            } 
            
        }else {
            JOptionPane.showMessageDialog(null, Utiles.msgDebeIntroducir + comp.getName());
            comp.requestFocus();
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
            //limpiarComponentesFormulario();
            Utiles.llenarTabla(jTable1,fp.getListaCargas(), "Cargas");
            JOptionPane.showMessageDialog(null, Utiles.msgOperacionRealizada);
            limpiarComponentesFormulario();
            jTable1.clearSelection();
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
        llenarPoblaciones();
    }//GEN-LAST:event_formWindowOpened

    private void eCostasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eCostasKeyTyped
        Utiles.validarNumeroReal(evt, eCostas, 9, 2);
    }//GEN-LAST:event_eCostasKeyTyped

    private void eGastosProcesalesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eGastosProcesalesKeyTyped
        Utiles.validarNumeroReal(evt, eGastosProcesales, 9, 2);
    }//GEN-LAST:event_eGastosProcesalesKeyTyped

    private void eImporteCargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eImporteCargaKeyTyped
        Utiles.validarNumeroReal(evt, eImporteCarga, 9, 2);
    }//GEN-LAST:event_eImporteCargaKeyTyped

    private void eInteresesCargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eInteresesCargaKeyTyped
        Utiles.validarNumeroReal(evt, eInteresesCarga, 9, 2);
    }//GEN-LAST:event_eInteresesCargaKeyTyped

    private void eInteresesDemoraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eInteresesDemoraKeyTyped
        Utiles.validarNumeroReal(evt, eInteresesDemora, 9, 2);
    }//GEN-LAST:event_eInteresesDemoraKeyTyped

    private void eOtrosGastosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eOtrosGastosKeyTyped
        Utiles.validarNumeroReal(evt, eOtrosGastos, 9, 2);
    }//GEN-LAST:event_eOtrosGastosKeyTyped

    private void eRecargoApremioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eRecargoApremioKeyTyped
        Utiles.validarNumeroReal(evt, eRecargoApremio, 9, 2);
    }//GEN-LAST:event_eRecargoApremioKeyTyped

    private void eBeneficiarioBancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eBeneficiarioBancoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eBeneficiarioBancoActionPerformed

    private void eFolioCesionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eFolioCesionKeyTyped
        Utiles.validarNumeroEntero(evt, eFolioCesion, 6);
    }//GEN-LAST:event_eFolioCesionKeyTyped

    private void eLibroCesionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eLibroCesionKeyTyped
        Utiles.validarNumeroEntero(evt, eLibroCesion, 4);
    }//GEN-LAST:event_eLibroCesionKeyTyped

    private void eTomoCesionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eTomoCesionKeyTyped
        Utiles.validarNumeroEntero(evt, eTomoCesion, 4);
    }//GEN-LAST:event_eTomoCesionKeyTyped

    private void eNumeroInscripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_eNumeroInscripcionKeyTyped
        Utiles.validarNumeroEntero(evt, eNumeroInscripcion, 15);
    }//GEN-LAST:event_eNumeroInscripcionKeyTyped

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        limpiarComponentesFormulario();
        jTable1.clearSelection();
    }//GEN-LAST:event_jButton1MousePressed

    private void ePlazoDeCargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ePlazoDeCargaKeyTyped
        Utiles.validarNumeroEntero(evt, ePlazoDeCarga, 6);
    }//GEN-LAST:event_ePlazoDeCargaKeyTyped

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
            java.util.logging.Logger.getLogger(FormaCargas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormaCargas dialog = new FormaCargas(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField eAutoProcedimiento;
    private javax.swing.JTextField eBeneficiario;
    private javax.swing.JComboBox<String> eBeneficiarioBanco;
    private javax.swing.JTextField eBeneficiarioCesion;
    private javax.swing.JTextField eCertificacionDominio;
    private javax.swing.JTextField eCesion;
    private javax.swing.JFormattedTextField eCostas;
    private javax.swing.JTextField eDescripcionNovacion;
    private javax.swing.JButton eEliminar;
    private org.openswing.swing.client.DateControl eFechaAuto;
    private org.openswing.swing.client.DateControl eFechaCertificacion;
    private org.openswing.swing.client.DateControl eFechaCesion;
    private org.openswing.swing.client.DateControl eFechaEscritura;
    private org.openswing.swing.client.DateControl eFechaInscripcion;
    private org.openswing.swing.client.DateControl eFechaInscripcionCesion;
    private org.openswing.swing.client.DateControl eFechaVencimiento;
    private javax.swing.JTextField eFolioCesion;
    private javax.swing.JTextField eGastosProcesales;
    private javax.swing.JTextField eIgualdadRango;
    private javax.swing.JFormattedTextField eImporteCarga;
    private javax.swing.JFormattedTextField eInteresesCarga;
    private javax.swing.JFormattedTextField eInteresesDemora;
    private javax.swing.JTextField eJusgado;
    private javax.swing.JTextField eLetraEmbargo;
    private javax.swing.JTextField eLibroCesion;
    private javax.swing.JButton eModificar;
    private javax.swing.JComboBox<String> eMoneda;
    private javax.swing.JTextField eNotario;
    private javax.swing.JTextField eNotarioCesion;
    private javax.swing.JTextField eNumeroInscripcion;
    private javax.swing.JTextField eOtrosGastos;
    private javax.swing.JTextField ePlazoDeCarga;
    private javax.swing.JFormattedTextField ePorcentajeParticipacion;
    private javax.swing.JTextField eProtocolo;
    private javax.swing.JTextField eProtocoloCesion;
    private javax.swing.JFormattedTextField eRecargoApremio;
    private javax.swing.JTextField eRelacionCarga;
    private javax.swing.JFormattedTextField eRespHipotecaria;
    private javax.swing.JComboBox<String> eTipoCarga;
    private javax.swing.JTextField eTomoCesion;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
