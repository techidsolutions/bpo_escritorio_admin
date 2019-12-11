/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import modelo.ComponenteFormulario;
import modelo.Finca;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class ManipularXML {
    Document documento;
    
    /**
     * 
     * @throws ParserConfigurationException 
     */
    public ManipularXML() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = docFactory.newDocumentBuilder();
        documento = constructor.newDocument();
    }
    
    /**
     * 
     * @return
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public String convertirCadena() throws TransformerConfigurationException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();        
        Transformer transformer = tf.newTransformer();
        StringWriter writer = new StringWriter();
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString();
        return output;
    }
    
    /**
     * 
     * @param nombreArchivoXML
     * @param guardarConvertir
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void escribirArchivo(String nombreArchivoXML, String guardarConvertir) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String ruta = "";
        switch (guardarConvertir){
            case "Guardar":ruta = Utiles.rutaGuardadosNotaSimple;break;
            case "Convertir":ruta = Utiles.rutaProcesadosNotaSimple;break;
        }
        File archivo = new File(ruta + nombreArchivoXML + ".xml");
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(archivo);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"xhtml1-strict.dtd");
        transformer.transform(source, result);
    }
    
    /**
     * 
     * @param nombreArchivoXML
     * @param guardarConvertir
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void escribirArchivoCargas(String nombreArchivoXML, String guardarConvertir) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String ruta = "";
        switch (guardarConvertir){
            case "Guardar":ruta = Utiles.rutaGuardadosNotaSimple;break;
            case "Convertir":ruta = Utiles.rutaProcesadosNotaSimple;break;
        }
        File archivo = new File(ruta + nombreArchivoXML + "_cargas" + ".xml");
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(archivo);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);
    }
    
    /**
     * 
     * @param nombreArchivoXML
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void escribirArchivoIRPF(String nombreArchivoXML) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String ruta = Utiles.rutaProcesadosIRPF;
        File archivo = new File(ruta + nombreArchivoXML.split("\\.")[0] + ".xml");
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(archivo);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"xhtml1-strict.dtd");
        transformer.transform(source, result);
    }
    
    /**
     * 
     * @param nombreArchivoXML
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void escribirArchivoVidaLaboral(String nombreArchivoXML) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String ruta = Utiles.rutaProcesadosVidaLaboral;
        File archivo = new File(ruta + nombreArchivoXML.split("\\.")[0] + ".xml");
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(archivo);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"xhtml1-strict.dtd");
        transformer.transform(source, result);
    }
    
    /**
     * 
     * @param nombreArchivoXML
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void escribirArchivoDocumentoKO(String nombreArchivoXML) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String ruta = Utiles.rutaDocumentoKO;
        
        File archivo = new File(ruta + nombreArchivoXML.split("\\.")[0] + ".xml");
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(archivo);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"xhtml1-strict.dtd");
        transformer.transform(source, result);
    }
    
    /**
     * 
     * @param nombreArchivoXML
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void escribirArchivoNomina(String nombreArchivoXML) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String ruta = Utiles.rutaProcesadosNomina;
        File archivo = new File(ruta + nombreArchivoXML.split("\\.")[0] + ".xml");
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(archivo);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"xhtml1-strict.dtd");
        transformer.transform(source, result);
    }

    /**
     * 
     * @param nombreArchivoXML
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void escribirArchivoRecibo(String nombreArchivoXML) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String ruta = Utiles.rutaProcesadosRecibo;
        File archivo = new File(ruta + nombreArchivoXML.split("\\.")[0] + ".xml");
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(archivo);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"xhtml1-strict.dtd");
        transformer.transform(source, result);
    }
    
    /**
     * 
     * @param nombreArchivoXML
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void escribirArchivoTasacion(String nombreArchivoXML) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String ruta = Utiles.rutaProcesadosTasacion;
        File archivo = new File(ruta + nombreArchivoXML.split("\\.")[0] + ".xml");
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(archivo);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"xhtml1-strict.dtd");
        transformer.transform(source, result);
    }
    
    /**
     * 
     * @param nombreArchivoXML
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void escribirArchivoNotaSimpleCaixa(String nombreArchivoXML) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String ruta = Utiles.rutaProcesadosNotaSimpleCaixa;
        
        File archivo = new File(ruta + nombreArchivoXML.split("\\.")[0] + ".xml");
        DOMSource source = new DOMSource(documento);
        StreamResult result = new StreamResult(archivo);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"xhtml1-strict.dtd");
        transformer.transform(source, result);
    }
    
    /**
     * 
     * @param listaComponentesXML
     * @param listaTitulares
     * @param listaAnejos 
     * @param listaCargas 
     */
    public void crearDocumento(ArrayList<ComponenteFormulario> listaComponentesXML, ArrayList<ArrayList<ComponenteFormulario>> listaTitulares, 
                               ArrayList<ArrayList<ComponenteFormulario>> listaAnejos,
                               ArrayList<ArrayList<ComponenteFormulario>> listaCargas) {
        Element raiz;
        Element hijoRegistral;
        Element hijoTitular = null;
        Element hijoTitulo = null;
        Element elementoTemporal;
        int indice = 0;
        
        raiz = documento.createElement("OcrResultRegistral");
        //raiz.setAttribute("Reference", listaComponentesXML.get(0).getValor());
        documento.appendChild(raiz); 
        hijoRegistral = documento.createElement("Registral");
        raiz.appendChild(hijoRegistral);
        
        for (ComponenteFormulario componenteFormulario: listaComponentesXML){
            if ((indice <= 26)){
                elementoTemporal = documento.createElement(listaComponentesXML.get(indice).getNombre());
                elementoTemporal.setTextContent(listaComponentesXML.get(indice).getValor());
                hijoRegistral.appendChild(elementoTemporal);
            }else if ((indice > 26) && (indice <=31)){
                if  (indice == 27) {
                    hijoTitular = documento.createElement("Titular");
                    raiz.appendChild(hijoTitular);
                }
                elementoTemporal = documento.createElement(listaComponentesXML.get(indice).getNombre());
                elementoTemporal.setTextContent(listaComponentesXML.get(indice).getValor());
                hijoTitular.appendChild(elementoTemporal);
            }else {
                if  (indice == 32) { 
                    hijoTitulo = documento.createElement("Titulo");
                    hijoTitular.appendChild(hijoTitulo);
                } 
                elementoTemporal = documento.createElement(listaComponentesXML.get(indice).getNombre());
                elementoTemporal.setTextContent(listaComponentesXML.get(indice).getValor());
                hijoTitulo.appendChild(elementoTemporal);
            } 
            indice++;
        }
        
        //Si existen titulares
        if (!listaTitulares.isEmpty()){
            for (ArrayList<ComponenteFormulario> titular : listaTitulares) {
                hijoTitular = documento.createElement("Titular");
                raiz.appendChild(hijoTitular);
                indice = 0;
                for (ComponenteFormulario componenteFormulario : titular) {
                    if (indice <= 4){
                        elementoTemporal = documento.createElement(titular.get(indice).getNombre());
                        elementoTemporal.setTextContent(titular.get(indice).getValor());
                        hijoTitular.appendChild(elementoTemporal);
                    } else if (indice >= 5){
                        if (indice == 5){
                            hijoTitulo = documento.createElement("Titulo");
                            hijoTitular.appendChild(hijoTitulo);
                        }
                        elementoTemporal = documento.createElement(titular.get(indice).getNombre());
                        elementoTemporal.setTextContent(titular.get(indice).getValor());
                        hijoTitulo.appendChild(elementoTemporal);
                    }
                    indice++;
                }
            }
        }
        
        //Si existen anejos
        if (!listaAnejos.isEmpty()){
            for (ArrayList<ComponenteFormulario> anejo : listaAnejos) {
                hijoTitular = documento.createElement("Anejo");
                raiz.appendChild(hijoTitular);
                indice = 0;
                for (ComponenteFormulario componenteFormulario : anejo) {
                        elementoTemporal = documento.createElement(anejo.get(indice).getNombre());
                        elementoTemporal.setTextContent(anejo.get(indice).getValor());
                        hijoTitular.appendChild(elementoTemporal);
                    indice++;
                }
            }
        }
        
        //si existen cargas
        if (!listaCargas.isEmpty()){
            for (ArrayList<ComponenteFormulario> carga : listaCargas) {
                hijoTitular = documento.createElement("Carga");
                raiz.appendChild(hijoTitular);
                indice = 0;
                for (ComponenteFormulario componenteFormulario : carga) {
                        elementoTemporal = documento.createElement(carga.get(indice).getNombre());
                        elementoTemporal.setTextContent(carga.get(indice).getValor());
                        hijoTitular.appendChild(elementoTemporal);
                    indice++;
                }
            }
        }
    }
    
    public void crearDocumentoTasacion(ArrayList<ComponenteFormulario> listaComponentesXML, ArrayList<Finca> listaFincas) {
        Element raiz;
        Element hijoTasacion;
        Element hijoTitular;
        Element hijoFinca;
        Element hijoTitulo = null;
        Element elementoTemporal;
        int indice = 0;
        
        raiz = documento.createElement("OcrResultRegistral");
        //raiz.setAttribute("Reference", listaComponentesXML.get(0).getValor());
        documento.appendChild(raiz); 
        hijoTasacion = documento.createElement("TASACION");
        raiz.appendChild(hijoTasacion);
        for (ComponenteFormulario componenteFormulario: listaComponentesXML){
            elementoTemporal = documento.createElement(componenteFormulario.getNombre());
            elementoTemporal.setTextContent(componenteFormulario.getValor());
            hijoTasacion.appendChild(elementoTemporal);
        }
        
        if (!listaFincas.isEmpty()){
            for (Finca finca : listaFincas) {
                hijoFinca = documento.createElement("FINCA");
                hijoTasacion.appendChild(hijoFinca);
                for (ComponenteFormulario componenteFormulario : finca.getListaComponentesXML()) {
                    elementoTemporal = documento.createElement(componenteFormulario.getNombre());
                    elementoTemporal.setTextContent(componenteFormulario.getValor());
                    hijoFinca.appendChild(elementoTemporal);
                }
                
                //Si existen titulares
                if (!finca.getListaTitulares().isEmpty()){
                    for (ArrayList<ComponenteFormulario> titular : finca.getListaTitulares()) {
                        hijoTitular = documento.createElement("Titular");
                        hijoFinca.appendChild(hijoTitular);
                        indice = 0;
                        for (ComponenteFormulario componenteFormulario : titular) {
                            if (indice <= 4){
                                elementoTemporal = documento.createElement(titular.get(indice).getNombre());
                                elementoTemporal.setTextContent(titular.get(indice).getValor());
                                hijoTitular.appendChild(elementoTemporal);
                            } else if (indice >= 5){
                                if (indice == 5){
                                    hijoTitulo = documento.createElement("Titulo");
                                    hijoTitular.appendChild(hijoTitulo);
                                }
                                elementoTemporal = documento.createElement(titular.get(indice).getNombre());
                                elementoTemporal.setTextContent(titular.get(indice).getValor());
                                hijoTitulo.appendChild(elementoTemporal);
                            }
                            indice++;
                        }
                    }
                }
                
                 //Si existen anejos
                if (!finca.getListaAnejos().isEmpty()){
                    for (ArrayList<ComponenteFormulario> anejo : finca.getListaAnejos()) {
                        hijoTitular = documento.createElement("Anejo");
                        hijoFinca.appendChild(hijoTitular);
                        indice = 0;
                        for (ComponenteFormulario componenteFormulario : anejo) {
                                elementoTemporal = documento.createElement(anejo.get(indice).getNombre());
                                elementoTemporal.setTextContent(anejo.get(indice).getValor());
                                hijoTitular.appendChild(elementoTemporal);
                            indice++;
                        }
                    }
                }
            }
        }
        
        /*
        //Si existen titulares
        if (!listaTitulares.isEmpty()){
            for (ArrayList<ComponenteFormulario> titular : listaTitulares) {
                hijoTitular = documento.createElement("Titular");
                raiz.appendChild(hijoTitular);
                indice = 0;
                for (ComponenteFormulario componenteFormulario : titular) {
                    if (indice <= 4){
                        elementoTemporal = documento.createElement(titular.get(indice).getNombre());
                        elementoTemporal.setTextContent(titular.get(indice).getValor());
                        hijoTitular.appendChild(elementoTemporal);
                    } else if (indice >= 5){
                        if (indice == 5){
                            hijoTitulo = documento.createElement("Titulo");
                            hijoTitular.appendChild(hijoTitulo);
                        }
                        elementoTemporal = documento.createElement(titular.get(indice).getNombre());
                        elementoTemporal.setTextContent(titular.get(indice).getValor());
                        hijoTitulo.appendChild(elementoTemporal);
                    }
                    indice++;
                }
            }
        }
        
        //Si existen anejos
        if (!listaAnejos.isEmpty()){
            for (ArrayList<ComponenteFormulario> anejo : listaAnejos) {
                hijoTitular = documento.createElement("Anejo");
                raiz.appendChild(hijoTitular);
                indice = 0;
                for (ComponenteFormulario componenteFormulario : anejo) {
                        elementoTemporal = documento.createElement(anejo.get(indice).getNombre());
                        elementoTemporal.setTextContent(anejo.get(indice).getValor());
                        hijoTitular.appendChild(elementoTemporal);
                    indice++;
                }
            }
        }
        
        //si existen cargas
        if (!listaCargas.isEmpty()){
            for (ArrayList<ComponenteFormulario> carga : listaCargas) {
                hijoTitular = documento.createElement("Carga");
                raiz.appendChild(hijoTitular);
                indice = 0;
                for (ComponenteFormulario componenteFormulario : carga) {
                        elementoTemporal = documento.createElement(carga.get(indice).getNombre());
                        elementoTemporal.setTextContent(carga.get(indice).getValor());
                        hijoTitular.appendChild(elementoTemporal);
                    indice++;
                }
            }
        }
        */
    }
    
    /**
     * 
     * @param listaComponentesXML
     * @param referencia
     * @param estadoSolicitud
     * @param descripcionError 
     */
    public void crearDocumentoCargas(ArrayList<ArrayList<ComponenteFormulario>> listaComponentesXML, String referencia, String estadoSolicitud, String descripcionError) {
        Element hijoCarga;
        Element elementoTemporal;
        Element raiz = documento.createElement("OcrResultCargas");
        raiz.setAttribute("Reference", referencia);
        documento.appendChild(raiz);
        elementoTemporal = documento.createElement("ESTADO_SOLICITUD");
        elementoTemporal.setTextContent(estadoSolicitud);
        raiz.appendChild(elementoTemporal);
        elementoTemporal = documento.createElement("DESCRIPCION_ERROR");
        elementoTemporal.setTextContent(descripcionError);
        raiz.appendChild(elementoTemporal);
        for (ArrayList<ComponenteFormulario> lista : listaComponentesXML) {
            hijoCarga = documento.createElement("Carga");
            raiz.appendChild(hijoCarga);
            for (ComponenteFormulario componenteFormulario: lista){
                elementoTemporal = documento.createElement(componenteFormulario.getNombre());
                elementoTemporal.setTextContent(componenteFormulario.getValor());
                hijoCarga.appendChild(elementoTemporal);
            }
        }
        
    } 
     
    /**
     * 
     * @param listaComponentesXML 
     */
    public void crearDocumentoIRPF(ArrayList<ComponenteFormulario> listaComponentesXML) {
        Element hijoIRPF;
        Element elementoTemporal;
        Element raiz = documento.createElement("OcrResultIRPF");
        documento.appendChild(raiz);
        hijoIRPF = documento.createElement("IRPF");
        raiz.appendChild(hijoIRPF);
        for (ComponenteFormulario componenteFormulario: listaComponentesXML){
            elementoTemporal = documento.createElement(componenteFormulario.getNombre());
            elementoTemporal.setTextContent(componenteFormulario.getValor());
            hijoIRPF.appendChild(elementoTemporal);
        }
    } 
    
    /**
     * 
     * @param listaComponentesXML 
     */
    public void crearDocumentoKO(ArrayList<ComponenteFormulario> listaComponentesXML) {
        Element hijoIRPF;
        Element elementoTemporal;
        Element raiz = documento.createElement("OcrResultDocumentoKO");
        documento.appendChild(raiz);
        hijoIRPF = documento.createElement("DOCUMENTO_KO");
        raiz.appendChild(hijoIRPF);
        for (ComponenteFormulario componenteFormulario: listaComponentesXML){
            elementoTemporal = documento.createElement(componenteFormulario.getNombre());
            elementoTemporal.setTextContent(componenteFormulario.getValor());
            hijoIRPF.appendChild(elementoTemporal);
        }
    } 
    
    /**
     * 
     * @param listaComponentesXML 
     */
    public void crearDocumentoVidaLaboral(ArrayList<ComponenteFormulario> listaComponentesXML) {
        Element hijoIRPF;
        Element elementoTemporal;
        Element raiz = documento.createElement("OcrResultVidaLaboral");
        documento.appendChild(raiz);
        hijoIRPF = documento.createElement("VIDA_LABORAL");
        raiz.appendChild(hijoIRPF);
        for (ComponenteFormulario componenteFormulario: listaComponentesXML){
            elementoTemporal = documento.createElement(componenteFormulario.getNombre());
            elementoTemporal.setTextContent(componenteFormulario.getValor());
            hijoIRPF.appendChild(elementoTemporal);
        }
    }  
    
    /**
     * 
     * @param listaComponentesXML 
     */
    public void crearDocumentoNomina(ArrayList<ComponenteFormulario> listaComponentesXML) {
        Element hijoIRPF;
        Element elementoTemporal;
        Element raiz = documento.createElement("OcrResultNomina");
        documento.appendChild(raiz);
        hijoIRPF = documento.createElement("NOMINA");
        raiz.appendChild(hijoIRPF);
        for (ComponenteFormulario componenteFormulario: listaComponentesXML){
            elementoTemporal = documento.createElement(componenteFormulario.getNombre());
            elementoTemporal.setTextContent(componenteFormulario.getValor());
            hijoIRPF.appendChild(elementoTemporal);
        }
    }  

    /**
     * 
     * @param listaComponentesXML 
     */
    public void crearDocumentoRecibo(ArrayList<ComponenteFormulario> listaComponentesXML) {
        Element hijoIRPF;
        Element elementoTemporal;
        Element raiz = documento.createElement("OcrResultRecibo");
        documento.appendChild(raiz);
        hijoIRPF = documento.createElement("RECIBO");
        raiz.appendChild(hijoIRPF);
        for (ComponenteFormulario componenteFormulario: listaComponentesXML){
            elementoTemporal = documento.createElement(componenteFormulario.getNombre());
            elementoTemporal.setTextContent(componenteFormulario.getValor());
            hijoIRPF.appendChild(elementoTemporal);
        }
    }  
    
    /**
     * 
     * @param listaComponentesXML 
     */
    public void crearDocumentoTasacion(ArrayList<ComponenteFormulario> listaComponentesXML) {
        Element hijoIRPF;
        Element elementoTemporal;
        Element raiz = documento.createElement("OcrResultTasacion");
        documento.appendChild(raiz);
        hijoIRPF = documento.createElement("TASACION");
        raiz.appendChild(hijoIRPF);
        for (ComponenteFormulario componenteFormulario: listaComponentesXML){
            elementoTemporal = documento.createElement(componenteFormulario.getNombre());
            elementoTemporal.setTextContent(componenteFormulario.getValor());
            hijoIRPF.appendChild(elementoTemporal);
        }
    }  
    
    /**
     * 
     * @param listaComponentesXML 
     */
    public void crearDocumentoNotaSimpleCaixa(ArrayList<ComponenteFormulario> listaComponentesXML) {
        Element elementoTemporal;
        Element raiz = documento.createElement("OcrResultNotaSimple");
        documento.appendChild(raiz);
        for (ComponenteFormulario componenteFormulario: listaComponentesXML){
            elementoTemporal = documento.createElement(componenteFormulario.getNombre());
            elementoTemporal.setTextContent(componenteFormulario.getValor());
            raiz.appendChild(elementoTemporal);
        }
    }  
}
