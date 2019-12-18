/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import difflib.DiffUtils;
import difflib.Patch;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import modelo.ComponenteFormulario;
import modelo.Documento;
import modelo.Finca;
import modelo.ModelCombo;
import modelo.NotaSimpleCaixa;
import modelo.Poblacion;
import modelo.Provincia;
import modelo.RegistroPropiedad;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.util.Base64;
import org.jdesktop.swingx.JXTable;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.openswing.swing.client.DateControl;
import static util.Utiles.rutaEnviados;
import vista.FormaCrearUsuario;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class Utiles {
    public static String direcion=System.getProperty("user.dir");
    public static String rutaEnviados = System.getProperty("user.dir").concat("\\docs\\Enviados\\");
    public static String rutaPendientes = System.getProperty("user.dir").concat("\\docs\\");

    public static String rutaPendientesOCR = System.getProperty("user.dir").concat("\\docs\\Pendientes\\NotaSimpleOCR\\");
    public static String rutaProcesadosNotaSimpleOCR = System.getProperty("user.dir").concat("\\docs\\ProcesadosPDF\\NotaSimpleOCR\\");
    public static String rutaSubidos = System.getProperty("user.dir").concat("\\docs\\Subidos\\NotaSimpleOCR\\");

    public static String rutaProcesadosNotaSimple = System.getProperty("user.dir").concat("\\docs\\Procesados\\Nota Simple\\");
    public static String rutaGuardadosNotaSimple = System.getProperty("user.dir").concat("\\docs\\Guardados\\Nota Simple\\");
    public static String rutaDocumentos = System.getProperty("user.dir").concat("\\conf\\documentos.dat");
    public static String rutaUsuarios = System.getProperty("user.dir").concat("\\conf\\usuarios.dat");

    public static String rutaProcesadosIRPF = System.getProperty("user.dir").concat("\\docs\\Procesados\\IRPF\\");
    public static String rutaProcesadosVidaLaboral = System.getProperty("user.dir").concat("\\docs\\Procesados\\Vida Laboral\\");
    public static String rutaProcesadosNomina = System.getProperty("user.dir").concat("\\docs\\Procesados\\Nomina\\");
    public static String rutaProcesadosTasacion = System.getProperty("user.dir").concat("\\docs\\Procesados\\Tasacion\\");
    public static String rutaProcesadosRecibo = System.getProperty("user.dir").concat("\\docs\\Procesados\\Recibo\\");
    public static String rutaDocumentoKO = System.getProperty("user.dir").concat("\\docs\\Procesados\\DocumentosKO\\");
    public static String rutaProcesadosNotaSimpleCaixa = System.getProperty("user.dir").concat("\\docs\\Procesados\\Nota Simple Caixa\\");

    public static String rutaTrazas = System.getProperty("user.dir").concat("\\docs\\");

    public static String rutaEnviadosIRPF = System.getProperty("user.dir").concat("\\docs\\Enviados\\IRPF\\ocr\\");
    public static String rutaEnviadosNomina = System.getProperty("user.dir").concat("\\docs\\Enviados\\Nomina\\ocr\\");
    public static String rutaEnviadosVidaLaboral = System.getProperty("user.dir").concat("\\docs\\Enviados\\Vida Laboral\\ocr\\");
    public static String rutaEnviadosDocumentosKO = System.getProperty("user.dir").concat("\\docs\\Enviados\\DocumentosKO\\ocr\\");
    public static String rutaEnviadosNotaSimpleCaixa = System.getProperty("user.dir").concat("\\docs\\Enviados\\Nota Simple Caixa\\ocr\\");
    public static String rutaEnviadosNotaSimple = System.getProperty("user.dir").concat("\\docs\\Enviados\\Nota Simple\\ocr\\");
    public static String rutaEnviadosRecibo = System.getProperty("user.dir").concat("\\docs\\Enviados\\Recibo\\ocr\\");
    public static String rutaEnviadosTasacion = System.getProperty("user.dir").concat("\\docs\\Enviados\\Tasacion\\ocr\\");

    public static String rutaProcesadosNotaSimplePDF = System.getProperty("user.dir").concat("\\docs\\ProcesadosPDF\\Nota Simple\\");
    public static String rutaProcesadosIRPFPDF = System.getProperty("user.dir").concat("\\docs\\ProcesadosPDF\\IRPF\\");
    public static String rutaProcesadosNominaPDF = System.getProperty("user.dir").concat("\\docs\\ProcesadosPDF\\Nomina\\");
    public static String rutaProcesadosVidaLaboralPDF = System.getProperty("user.dir").concat("\\docs\\ProcesadosPDF\\Vida Laboral\\");
    public static String rutaProcesadosReciboPDF = System.getProperty("user.dir").concat("\\docs\\ProcesadosPDF\\Recibo\\");
    public static String rutaProcesadosDocumentosKOPDF = System.getProperty("user.dir").concat("\\docs\\ProcesadosPDF\\DocumentosKO\\");
    public static String rutaProcesadosNotaSimpleCaixaPDF = System.getProperty("user.dir").concat("\\docs\\ProcesadosPDF\\Nota Simple Caixa\\");
    public static String rutaProcesadosTasacionPDF = System.getProperty("user.dir").concat("\\docs\\ProcesadosPDF\\Tasacion\\");

    public static String rutaTextoEliminar = System.getProperty("user.dir").concat("\\conf\\texto_eliminar.dat");

    /**
     * Mensajes en la aplicación
     */
    public static String msgTareaExportando = "Exportando datos a documento PDF...";
    public static String msgTareaExportandoExcel = "Exportando datos a documento Excel...";
    public static String msgTareaProcesandoDocumento = "Procesando documento...";
    public static String msgTareaSalvandoDocumento = "Salvando documento...";
    public static String msgTareaRealizandoConversion = "Realizando conversión...";
    public static String msgTareaCargandoDocumentos = "Cargando documentos...";
    public static String msgTareaActualizandoDocumentos = "Actualizando información de documentos...";
    public static String msgTareaDescargarArchivos = "Descargando archivos...";
    public static String msgTareaSubiendoArchivos = "Subiendo archivos...";
    public static String msgTareaAnadiendoFinca = "Añadiendo finca...";

    public static String msgDefinirCargas = "Debe definir las cargas para generar el XML correspondiente.";
    public static String msgTipoXmlGenerar = "Seleccione el tipo de XML a generar.";
    public static String msgDebeIntroducir = "Debe introducir: ";
    public static String msgDocumentoProcesar = "Seleccione el documento a procesar en la tabla.";
    public static String msgDefinirNombreArchivo = "Debe definir el nombre del archivo.";
    public static String msgSeleccioneDocumento = "Seleccione el documento en la tabla.";
    public static String msgSeleccioneTitularEliminar = "Seleccione el titular a eliminar.";
    public static String msgSeleccioneFincaEliminar = "Seleccione la finca a eliminar.";
    public static String msgSeleccioneFincaModificar = "Seleccione la finca a modificar.";
    public static String msgSeleccioneTitularModificar = "Seleccione el titular a modificar.";
    public static String msgTipoXmlGuardar = "Seleccione el tipo de XML a guardar.";
    public static String msgOperacionRealizada = "Operación realizada correctamente.";
    public static String msgSeleccioneCargaEliminar = "Seleccione la carga a eliminar.";
    public static String msgSeleccioneCargaModificar = "Seleccione la carga a modificar.";
    public static String msgRectifiqueClave = "Rectifique la contraseña.";
    public static String msgCuentaCreada = "Cuenta creada satisfactoriamente.";
    public static String msgNombreClaveIncorrectos = "Nombre o contraseña incorrectos.";
    public static String msgDebeIntroducirAdvertencia = "Debe introducir advertencia.";
    public static String msgDebeSeleccionarAdvertenciaEliminar = "Debe seleccionar la advertencia a eliminar.";
    public static String msgDebeSeleccionarAdvertenciaModificar = "Debe seleccionar la advertencia a modificar.";

    public static String msgDebeIntroducirCondicionante = "Debe introducir condicionante.";
    public static String msgDebeSeleccionarCondicionanteEliminar = "Debe seleccionar la condicionante a eliminar.";
    public static String msgDebeSeleccionarCondicionanteModificar = "Debe seleccionar la condicionante a modificar.";

    public static String secretKey = "bpotechid1234";

    /**
     *
     * @param container
     * @return
     */
    public static JComponent ValidaControles(JPanel container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JComboBox) {
                //  if(((JComboBox)comp).getSelectedIndex()==-1){return(JComponent) comp;}
                if (((JComboBox) comp).getModel().getSelectedItem() == "") {
                    return (JComponent) comp;
                }//if(((JComboBox)comp).getModel().getSelectedItem()== ""){return(JComponent) comp;}
            }
            if (comp instanceof JTextField) {
                String nombreAccesible = ((JTextField) comp).getAccessibleContext().getAccessibleName();
                Boolean existePunto = false;
                if (nombreAccesible != null) {
                    existePunto = ((JTextField) comp).getText().contains(".");
                }
                if ((((JTextField) comp).getName().substring(((JTextField) comp).getName().length() - 1)).compareTo(".") != 0) {//esta validacion es para los campos no requeridos, hay que nombrarlos con un punto al final.
                    if ((((JTextField) comp).getText().length() == 0) || (existePunto)) {
                        return (JComponent) comp;
                    }
                } else {
                    if (existePunto) {
                        return (JComponent) comp;
                    }
                }
            }
            if (comp instanceof JPasswordField) {
                if ((((JPasswordField) comp).getName().substring(((JPasswordField) comp).getName().length() - 1)).compareTo(".") != 0) {//esta validacion es para los campos no requeridos, hay que nombrarlos con un punto al final.
                    if (((JPasswordField) comp).getText().length() == 0) {
                        return (JComponent) comp;
                    }
                }
            }

            if (comp instanceof JFormattedTextField) {
                if ((((JFormattedTextField) comp).getName().substring(((JFormattedTextField) comp).getName().length() - 1)).compareTo(".") != 0) {//esta validacion es para los campos no requeridos, hay que nombrarlos con un punto al final.
                    if (((JFormattedTextField) comp).getText().length() == 0) {
                        return (JComponent) comp;
                    }
                }
            }

            if (comp instanceof JTextArea) {
                if ((((JTextArea) comp).getName().substring(((JTextArea) comp).getName().length() - 1)).compareTo(".") != 0) {//esta validacion es para los campos no requeridos, hay que nombrarlos con un punto al final.
                    if (((JTextArea) comp).getText().length() == 0) {
                        return (JComponent) comp;
                    }
                }
            }

            if (comp instanceof DateControl) {
                if ((((DateControl) comp).getName().substring(((DateControl) comp).getName().length() - 1)).compareTo(".") != 0) {//esta validacion es para los campos no requeridos, hay que nombrarlos con un punto al final.
                    if (((DateControl) comp).getValue() == null) {
                        return (JComponent) comp;
                    }
                }
            }

            /*if(comp instanceof JScrollPane){
             if (((JScrollPane)comp).getName() != null){
             //if (((JScrollPane)comp).getName().equals("ScrollAdvertencias") || ((JScrollPane)comp).getName().equals("ScrollCondicionantes")){
             Component listaComp[] = ((JScrollPane)comp).getComponents();
                     
             if  ((((JList)listaComp[0]).getName().substring(((JList)listaComp[0]).getName().length()-1)).compareTo(".")!=0){//esta validacion es para los campos no requeridos, hay que nombrarlos con un punto al final.
             DefaultListModel lista = (DefaultListModel)((JList)listaComp[0]).getModel();
             if(lista.getSize() == 0){return(JComponent) listaComp[0];}
             }
                    
             }
             }*/
        }
        return null;
    }

    /**
     * Método que convierte un dato tipo Date a una cadena con un formato
     * determinado.
     *
     * @param fecha Fecha tipo Date
     * @return String con un formato determinado.
     */
    public static String convertirFechaDDMMYYYY(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
        return formateador.format(fecha);
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static String convertirFechaYYYYMMDD(Date fecha) {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(fecha);
    }

    /**
     * Método que genera un archivo XML con la información de los documentos.
     *
     * @param eNombreXML Nombre del XML.
     * @param listaTitulares Lista de titulares
     * @param convertirGuardar String donde se indica si el XML se convertirá
     * como procesado o es para guardar.
     * @param listaAnejos
     * @param listaCargas
     */
    public static void generarXML(JTextField eNombreXML, ArrayList<ArrayList<ComponenteFormulario>> listaTitulares, String convertirGuardar,
            ArrayList<ArrayList<ComponenteFormulario>> listaAnejos,
            ArrayList<ArrayList<ComponenteFormulario>> listaCargas) {
        //JComponent compTemp = (JComponent)eNombreXML.getNextFocusableComponent();
        JComponent compTemp = (JComponent) eNombreXML;
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
        /*for (ComponenteFormulario componenteFormulario : listaComponentesXML) {
         System.out.println(componenteFormulario.getNombre() + ":" + componenteFormulario.getValor());
         }*/

        ManipularXML creador;
        try {
            creador = new ManipularXML();
            creador.crearDocumento(listaComponentesXML, listaTitulares, listaAnejos, listaCargas);
            creador.escribirArchivo(eNombreXML.getText().split("\\.")[0], convertirGuardar);
        } catch (ParserConfigurationException | TransformerException ex) {
        }
    }

    /**
     *
     * @param eNombreXML
     */
    public static void generarXMLOtroTipoDocumento(JTextField eNombreXML) {
        JComponent compTemp = (JComponent) eNombreXML;
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
        ManipularXML creador;
        try {
            creador = new ManipularXML();
            creador.crearDocumentoIRPF(listaComponentesXML);
            creador.escribirArchivoIRPF(eNombreXML.getText());
        } catch (ParserConfigurationException | TransformerException ex) {
        }
    }

    /**
     *
     * @param eNombreXML
     */
    public static void generarXMLDocumentoKO(JTextField eNombreXML) {
        JComponent compTemp = (JComponent) eNombreXML;
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

        ManipularXML creador;
        try {
            creador = new ManipularXML();
            creador.crearDocumentoKO(listaComponentesXML);
            creador.escribirArchivoDocumentoKO(eNombreXML.getText());
        } catch (ParserConfigurationException | TransformerException ex) {
        }
    }

    /**
     *
     * @param eNombreXML
     */
    public static void generarXMLVidaLaboral(JTextField eNombreXML) {
        JComponent compTemp = (JComponent) eNombreXML;
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

        ManipularXML creador;
        try {
            creador = new ManipularXML();
            creador.crearDocumentoVidaLaboral(listaComponentesXML);
            creador.escribirArchivoVidaLaboral(eNombreXML.getText());
        } catch (ParserConfigurationException | TransformerException ex) {
        }
    }

    /**
     *
     * @param eNombreXML
     */
    public static void generarXMLNomina(JTextField eNombreXML) {
        JComponent compTemp = (JComponent) eNombreXML;
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
        ManipularXML creador;
        try {
            creador = new ManipularXML();
            creador.crearDocumentoNomina(listaComponentesXML);
            creador.escribirArchivoNomina(eNombreXML.getText());
        } catch (ParserConfigurationException | TransformerException ex) {
        }
    }

    /**
     *
     * @param eNombreXML
     */
    public static void generarXMLRecibo(JTextField eNombreXML) {
        JComponent compTemp = (JComponent) eNombreXML;
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
        ManipularXML creador;
        try {
            creador = new ManipularXML();
            creador.crearDocumentoRecibo(listaComponentesXML);
            creador.escribirArchivoRecibo(eNombreXML.getText());
        } catch (ParserConfigurationException | TransformerException ex) {
        }
    }

    public static void generarXMLTasacion(JTextField eNombreXML, ArrayList<Finca> listaFincas) {
        JComponent compTemp = (JComponent) eNombreXML;
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
                listaComponentesXML.add(new ComponenteFormulario(atributo, ((ModelCombo) ((JComboBox) compTemp).getSelectedItem()).getClave()));
            } else if (compTemp instanceof DateControl) {
                if (((DateControl) compTemp).getDate() != null) {
                    listaComponentesXML.add(new ComponenteFormulario(atributo, Utiles.convertirFechaYYYYMMDD(((DateControl) compTemp).getDate())));
                } else {
                    listaComponentesXML.add(new ComponenteFormulario(atributo, ""));
                }
            } else if (compTemp instanceof JTextArea) {
                listaComponentesXML.add(new ComponenteFormulario(atributo, ((JTextArea) compTemp).getText()));
            } else if (compTemp instanceof JCheckBox) {
                listaComponentesXML.add(new ComponenteFormulario(atributo, ((JCheckBox) compTemp).isSelected() ? "True" : "False"));
            } else if (compTemp instanceof JList) {
                ListModel lista = ((JList) compTemp).getModel();
                String valor = "";
                if (lista.getSize() > 0) {
                    for (int i = 0; i < lista.getSize(); i++) {
                        String elemento = (String) lista.getElementAt(i);
                        valor = valor.concat(elemento);
                        valor = valor.concat("#");
                    }
                    valor = valor.substring(0, valor.length() - 1);
                }
                listaComponentesXML.add(new ComponenteFormulario(atributo, valor));
            }
            compTemp = (JComponent) compTemp.getNextFocusableComponent();
        }
        ManipularXML creador;
        try {
            creador = new ManipularXML();
            creador.crearDocumentoTasacion(listaComponentesXML, listaFincas);
            //creador.crearDocumentoTasacion(listaComponentesXML);
            creador.escribirArchivoTasacion(eNombreXML.getText());
        } catch (ParserConfigurationException | TransformerException ex) {
        }
    }

    /**
     *
     * @param texto
     * @return
     */
    public static String limpiarTexto(String texto) {
        try {
            FileInputStream fis = new FileInputStream(rutaTextoEliminar);
            int valor = fis.read();
            String linea = "";
            while (valor != -1) {
                Character caracter = (char) valor;
                if (caracter != '\n') {
                    if (caracter.equals('\u0093')) {
                        linea = linea.concat("“");
                    } else {
                        linea = linea.concat(String.valueOf(caracter));
                    }
                } else {
                    if (texto.contains(linea.trim())) {
                        texto = texto.replace(linea.trim(), "");
                    }
                    //texto = texto.replaceAll("^" + linea.trim() + "$", "");
                    linea = "";
                }
                valor = fis.read();
            }
        } catch (Exception ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texto;
    }

    /**
     *
     * @param listaNotaSimpleCaixa
     * @return
     */
    public static String generarXMLNotaSimpleCaixa(ArrayList<NotaSimpleCaixa> listaNotaSimpleCaixa) {
        String result = "Documentos no procesados:\n";
        ArrayList<ComponenteFormulario> listaComponentesXML;
        for (NotaSimpleCaixa notaSimpleCaixa : listaNotaSimpleCaixa) {
            try {
                listaComponentesXML = new ArrayList<>();
                listaComponentesXML.add(new ComponenteFormulario("ID_DOCUMENTO", notaSimpleCaixa.getNombre().split("\\.")[0]));
                String descripcion = "DESCRIPCION";
                String datosResgistrales[] = notaSimpleCaixa.getTexto().split(descripcion, 2);
                if (datosResgistrales.length < 2) {
                    descripcion = "DESCRIPCIÓN";
                    datosResgistrales = notaSimpleCaixa.getTexto().split(descripcion, 2);
                }
                if (datosResgistrales.length < 2) {
                    descripcion = "ESCRIPCION";
                    datosResgistrales = notaSimpleCaixa.getTexto().split(descripcion, 2);
                }
                if (datosResgistrales.length < 2) {
                    descripcion = "ESCRIPCIÓN";
                    datosResgistrales = notaSimpleCaixa.getTexto().split(descripcion, 2);
                }
                if (datosResgistrales.length < 2) {
                    descripcion = "Descripción";
                    datosResgistrales = notaSimpleCaixa.getTexto().split(descripcion, 2);
                }
                if (datosResgistrales.length < 2) {
                    descripcion = "URBANA";
                    datosResgistrales = notaSimpleCaixa.getTexto().split(descripcion, 2);
                }
                if (datosResgistrales.length < 2) {
                    descripcion = "DESCRIPCION DE LA FINCA";
                    datosResgistrales = notaSimpleCaixa.getTexto().split(descripcion, 2);
                }
                listaComponentesXML.add(new ComponenteFormulario("DATOS_REGISTRALES", datosResgistrales[0]));

                String titular = "TITULARIDADES";
                String datosDescripcion[] = datosResgistrales[1].split(titular, 2);
                if (datosDescripcion.length < 2) {
                    titular = "TITULARES";
                    datosDescripcion = datosResgistrales[1].split(titular, 2);
                }
                if (datosDescripcion.length < 2) {
                    titular = "ITULARES";
                    datosDescripcion = datosResgistrales[1].split(titular, 2);
                }
                if (datosDescripcion.length < 2) {
                    titular = "TITULARIDAD";
                    datosDescripcion = datosResgistrales[1].split(titular, 2);
                }
                if (datosDescripcion.length < 2) {
                    titular = "TITULAR";
                    datosDescripcion = datosResgistrales[1].split(titular, 2);
                }
                if (datosDescripcion.length < 2) {
                    titular = "Titular";
                    datosDescripcion = datosResgistrales[1].split(titular, 2);
                }
                if (datosDescripcion.length < 2) {
                    titular = "TITULO";
                    datosDescripcion = datosResgistrales[1].split(titular, 2);
                }
                if (datosDescripcion.length < 2) {
                    titular = "RESUMEN DE TITULARES";
                    datosDescripcion = datosResgistrales[1].split(titular, 2);
                }
                listaComponentesXML.add(new ComponenteFormulario("DESCRIPCION_FINCA", descripcion + "\n" + datosDescripcion[0]));

                String cargas = "CARGAS";
                String datosTitulares[] = datosDescripcion[1].split(cargas);
                if (datosTitulares.length < 2) {
                    cargas = "Cargas";
                    datosTitulares = datosDescripcion[1].split(cargas);
                }
                if (datosTitulares.length < 2) {
                    cargas = "Libre de cargas";
                    datosTitulares = datosDescripcion[1].split(cargas);
                }
                if (datosTitulares.length < 2) {
                    cargas = "RESUMEN DE CARGAS";
                    datosTitulares = datosDescripcion[1].split(cargas);
                }
                if (datosTitulares.length < 2) {
                    cargas = "HIPOTECA";
                    datosTitulares = datosDescripcion[1].split(cargas);
                }
                if (datosTitulares.length > 2) {
                    for (int i = 2; i < datosTitulares.length; i++) {
                        datosTitulares[1] = datosTitulares[1].concat(datosTitulares[i]);
                    }
                }
                listaComponentesXML.add(new ComponenteFormulario("TITULARES", titular + "\n" + datosTitulares[0]));

                String asiento = "PENDIENTES DE DESPACHO";
                String datosCargas[] = datosTitulares[1].split(asiento);
                if (datosCargas.length < 2) {
                    asiento = "Pendientes de Despacho";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "ASIENTOS LIBRO DIARIO";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "SIN ASIENTOS PENDIENTES";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "Sin asientos pendientes";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "Documentos relativos a la finca presentados y pendientes de despacho";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "DOCUMENTOS PENDIENTES";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "Asientos pendientes de despacho:";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "DOCUMENTOS RELATIVOS A LA FINCA PRESENTADOS Y PENDIENTES DE DESPACHO";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "PRESENTACION";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "ASIENTOS DEL DIARIO";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                if (datosCargas.length < 2) {
                    asiento = "ASIENTO/S";
                    datosCargas = datosTitulares[1].split(asiento);
                }
                //listaComponentesXML.add(new ComponenteFormulario("CARGAS", cargas + "\n" + datosCargas[0]));

                if (datosCargas.length < 2) {
                    String advertencia = "Para información de consumidores";
                    String datosAdvertencia[] = datosCargas[0].split(advertencia, 2);
                    if (datosAdvertencia.length < 2) {
                        advertencia = "1.- A los efectos de";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "INFORMACIÓN AL CONSUMIDOR:";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "INFORMACION AL CONSUMIDOR:";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "A los efectos de";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "Conforme a lo";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "MUY IMPORTANTE,";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "MUY IMPORTANTE";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "MUY IMPORTANTE :";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "MUY IMPORTANTE:";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "ADVERTENCIA:";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "ADVERTENCIAS";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "ADVERTENCIA";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "Queda prohibida la incorporación de los datos de esta nota a ficheros o bases";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "Queda prohibida";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    listaComponentesXML.add(new ComponenteFormulario("CARGAS", cargas + "\n" + datosAdvertencia[0]));
                    listaComponentesXML.add(new ComponenteFormulario("ASIENTOS_PENDIENTES", "Sin asientos pendientes"));
                } else {
                    listaComponentesXML.add(new ComponenteFormulario("CARGAS", cargas + "\n" + datosCargas[0]));
                    //Separar las advertencias
                    String advertencia = "Para información de consumidores";
                    String datosAdvertencia[] = datosCargas[1].split(advertencia, 2);
                    if (datosAdvertencia.length < 2) {
                        advertencia = "1.- A los efectos de";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "INFORMACIÓN AL CONSUMIDOR:";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "INFORMACION AL CONSUMIDOR:";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "A los efectos de";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "Conforme a lo";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "MUY IMPORTANTE,";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "MUY IMPORTANTE";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "MUY IMPORTANTE :";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "MUY IMPORTANTE:";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "ADVERTENCIA:";
                        datosAdvertencia = datosCargas[0].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "ADVERTENCIAS";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "ADVERTENCIA";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "Queda prohibida la incorporación de los datos de esta nota a ficheros o bases";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    if (datosAdvertencia.length < 2) {
                        advertencia = "Queda prohibida";
                        datosAdvertencia = datosCargas[1].split(advertencia, 2);
                    }
                    listaComponentesXML.add(new ComponenteFormulario("ASIENTOS_PENDIENTES", asiento + datosAdvertencia[0].replace(advertencia, "")));
                }

                /*
                 if (datosCargas.length > 1)
                 listaComponentesXML.add(new ComponenteFormulario("ASIENTOS_PENDIENTES", asiento + datosCargas[1]));
                 else 
                 listaComponentesXML.add(new ComponenteFormulario("ASIENTOS_PENDIENTES", "NO EXISTEN ASIENTOS PENDIENTES"));
                 */
                ManipularXML creador;
                try {
                    creador = new ManipularXML();
                    creador.crearDocumentoNotaSimpleCaixa(listaComponentesXML);
                    creador.escribirArchivoNotaSimpleCaixa(notaSimpleCaixa.getNombre());
                } catch (ParserConfigurationException ex) {

                } catch (TransformerException ex) {
                    Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                result = result.concat(notaSimpleCaixa.getNombre() + "\n");
                System.out.println("Documento no procesado:" + notaSimpleCaixa.getNombre());
            }

        }
        return result;
    }

    /**
     * Método que genera un archivo XML con la información de los documentos
     * tipo Carga.
     *
     * @param eNombreXML Nombre del XML
     * @param referencia Referencia del documento.
     * @param estadoSolicitud Estado de la solicitud.
     * @param descripcionError Descripción del error.
     * @param listaComponentesXML Lista de componentes XML.
     * @param convertirGuardar String donde se indica si el XML se convertirá
     * como procesado o es para guardar.
     */
    public static void generarXMLCargas(JTextField eNombreXML, String referencia, String estadoSolicitud, String descripcionError, ArrayList<ArrayList<ComponenteFormulario>> listaComponentesXML, String convertirGuardar) {
        ManipularXML creador;
        try {
            creador = new ManipularXML();
            creador.crearDocumentoCargas(listaComponentesXML, referencia, estadoSolicitud, descripcionError);
            creador.escribirArchivoCargas(eNombreXML.getText(), convertirGuardar);
        } catch (ParserConfigurationException | TransformerException ex) {
        }
    }

    /**
     * Método que llena una tabla con la información basada en una lista de
     * objetos.
     *
     * @param tabla Tabla a llenar.
     * @param listaObjetos Lista de objetos.
     * @param tipoObjeto Tipo de entidad
     */
    public static void llenarTabla(JTable tabla, ArrayList listaObjetos, String tipoObjeto) {
        DefaultTableModel data = (DefaultTableModel) tabla.getModel();
        data.setRowCount(listaObjetos.size());
        Object objAux;
        int tLista = listaObjetos.size();
        int nFila = 0;
        ArrayList<ComponenteFormulario> datosTitulares;

        if (tipoObjeto.compareTo("Titulares") == 0) {
            while (nFila < tLista) {
                objAux = listaObjetos.get(nFila);
                datosTitulares = (ArrayList<ComponenteFormulario>) objAux;
                data.setValueAt(datosTitulares.get(0).getValor(), nFila, 0);
                data.setValueAt(datosTitulares.get(1).getValor(), nFila, 1);
                nFila++;
            }
            nFila++;
        } else if (tipoObjeto.compareTo("Documentos") == 0) {
            Documento documento;
            while (nFila < tLista) {
                objAux = listaObjetos.get(nFila);
                documento = (Documento) objAux;
                data.setValueAt(documento.getNombre(), nFila, 0);
                data.setValueAt(documento.getEstado(), nFila, 1);
                data.setValueAt(documento.getUsuario(), nFila, 2);
                data.setValueAt(documento.getFecha(), nFila, 3);
                nFila++;
            }
            nFila++;
        } else if (tipoObjeto.compareTo("Cargas") == 0) {
            ArrayList<ComponenteFormulario> datosCarga;
            while (nFila < tLista) {
                objAux = listaObjetos.get(nFila);
                datosCarga = (ArrayList<ComponenteFormulario>) objAux;
                data.setValueAt(datosCarga.get(0).getValor(), nFila, 0);
                data.setValueAt(datosCarga.get(3).getValor(), nFila, 1);
                nFila++;
            }
            nFila++;
        } else if (tipoObjeto.compareTo("Anejos") == 0) {
            ArrayList<ComponenteFormulario> datosCarga;
            while (nFila < tLista) {
                objAux = listaObjetos.get(nFila);
                datosCarga = (ArrayList<ComponenteFormulario>) objAux;
                data.setValueAt(datosCarga.get(0).getValor(), nFila, 0);
                nFila++;
            }
            nFila++;
        } else if (tipoObjeto.compareTo("Fincas") == 0) {
            Finca datosFinca;
            while (nFila < tLista) {
                objAux = listaObjetos.get(nFila);
                datosFinca = (Finca) objAux;
                data.setValueAt(datosFinca.getListaComponentesXML().get(1).getValor(), nFila, 0);
                nFila++;
            }
            nFila++;
        }
        tabla.setModel(data);
    }

    /**
     *
     * @param tabla
     * @param listaObjetos
     * @param tipoObjeto
     */
    public static void llenarTabla(JXTable tabla, ArrayList listaObjetos, String tipoObjeto) {
        DefaultTableModel data = (DefaultTableModel) tabla.getModel();
        data.setRowCount(listaObjetos.size());
        Object objAux;
        int tLista = listaObjetos.size();
        int nFila = 0;
        ArrayList<ComponenteFormulario> datosTitulares;
        if (tipoObjeto.compareTo("Titulares") == 0) {
            while (nFila < tLista) {
                objAux = listaObjetos.get(nFila);
                datosTitulares = (ArrayList<ComponenteFormulario>) objAux;
                data.setValueAt(datosTitulares.get(0).getValor(), nFila, 0);
                data.setValueAt(datosTitulares.get(3).getValor(), nFila, 1);
                nFila++;
            }
            nFila++;
        } else if (tipoObjeto.compareTo("Documentos") == 0) {
            Documento documento;
            String fechaDetalles[];
            while (nFila < tLista) {
                objAux = listaObjetos.get(nFila);
                documento = (Documento) objAux;
                data.setValueAt(documento.getNombre(), nFila, 0);
                data.setValueAt(documento.getDirectorioRaiz(), nFila, 1);
                data.setValueAt(documento.getTipoDocumento(), nFila, 2);
                data.setValueAt(documento.getEstado(), nFila, 3);
                data.setValueAt(documento.getUsuario(), nFila, 4);
                data.setValueAt(documento.getFecha(), nFila, 5);
                if (!documento.getFecha().isEmpty()) {
                    fechaDetalles = documento.getFecha().split("-");
                    data.setValueAt(fechaDetalles[1].concat("-").concat(fechaDetalles[2]), nFila, 6);
                } else {
                    data.setValueAt("", nFila, 6);
                }
                nFila++;
            }
            nFila++;

        } else if (tipoObjeto.compareTo("Documentos IRPF") == 0) {
            Documento documento;
            while (nFila < tLista) {
                objAux = listaObjetos.get(nFila);
                documento = (Documento) objAux;
                data.setValueAt(documento.getNombre(), nFila, 0);
                data.setValueAt(documento.getEstado(), nFila, 1);
                nFila++;
            }
            nFila++;
        }
        tabla.setModel(data);
    }

    /**
     * Método que carga la información de los nuevos documentos y los ya
     * registrados en el archivo de "Documentos"
     *
     * @param nombreUsuario Nombre del usuario que ha iniciado sesión.
     * @return ArrayList Lista de documentos.
     */
    public static ArrayList<Documento> cargarListaDocumentos(String nombreUsuario) {
        ArrayList<Documento> listaDocumentos = new ArrayList<>();
        String caminoDirectorioRaiz = rutaEnviados;
        File dirRaiz = new File(caminoDirectorioRaiz);
        String directorios[] = dirRaiz.list();
        File dirTemp;
        String subDirectorios[];
        File archivoDocumentos = new File(rutaDocumentos);
        Scanner scanner = null;
        String lineaArchivo;
        String datosDocumento[];
        try {
            scanner = new Scanner(archivoDocumentos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se chequea si existen documentos sin registrar en el archivo que contiene los datos de los mismo
        for (String directorio : directorios) {
            dirTemp = new File(caminoDirectorioRaiz.concat(directorio));
            subDirectorios = dirTemp.list();
            for (String subDirectorio : subDirectorios) {
                Boolean existeDocumento = false;
                while (scanner.hasNextLine()) {
                    try {
                        lineaArchivo = scanner.nextLine();
                        lineaArchivo = desencriptar(lineaArchivo);
                        datosDocumento = lineaArchivo.split(":");
                        if (datosDocumento[0].equals(subDirectorio)) {
                            existeDocumento = true;
                            listaDocumentos.add(new Documento(datosDocumento[0], datosDocumento[1], datosDocumento[2], datosDocumento[3], datosDocumento[4], datosDocumento[5]));
                            break;
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (!existeDocumento) {
                    listaDocumentos.add(new Documento(subDirectorio, "", "Pendiente", nombreUsuario, "", directorio));
                }
            }
        }
        return listaDocumentos;
    }

    /**
     * Método que carga la cantidad de documentos terminados por meses, para ser
     * utilizado en la generación del gráfico.
     *
     * @param listaDocumentos Lista de documentos.
     * @return Lista de cantidades (Integer) por meses.
     */
    public static ArrayList<Integer> cargarListaDocumentosTerminados(ArrayList<Documento> listaDocumentos) {
        String fechaActual = convertirFechaDDMMYYYY(new Date());
        ArrayList<Integer> listaCantidades = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            listaCantidades.add(0);
        }
        for (Documento documento : listaDocumentos) {
            try {
                if ((documento.getEstado().equals("Terminado")) && (documento.getFecha().split("-")[2].equals(fechaActual.split("-")[2]))) {
                    switch (documento.getFecha().split("-")[1]) {
                        case "01":
                            listaCantidades.set(0, listaCantidades.get(0) + 1);
                            break;
                        case "02":
                            listaCantidades.set(1, listaCantidades.get(1) + 1);
                            break;
                        case "03":
                            listaCantidades.set(2, listaCantidades.get(2) + 1);
                            break;
                        case "04":
                            listaCantidades.set(3, listaCantidades.get(3) + 1);
                            break;
                        case "05":
                            listaCantidades.set(4, listaCantidades.get(4) + 1);
                            break;
                        case "06":
                            listaCantidades.set(5, listaCantidades.get(5) + 1);
                            break;
                        case "07":
                            listaCantidades.set(6, listaCantidades.get(6) + 1);
                            break;
                        case "08":
                            listaCantidades.set(7, listaCantidades.get(7) + 1);
                            break;
                        case "09":
                            listaCantidades.set(8, listaCantidades.get(8) + 1);
                            break;
                        case "10":
                            listaCantidades.set(9, listaCantidades.get(9) + 1);
                            break;
                        case "11":
                            listaCantidades.set(10, listaCantidades.get(10) + 1);
                            break;
                        case "12":
                            listaCantidades.set(11, listaCantidades.get(11) + 1);
                            break;
                    }
                    //listaDocumentos.add(new Documento(datosDocumento[0], datosDocumento[1], datosDocumento[2],datosDocumento[3], datosDocumento[4], datosDocumento[5]));
                }
            } catch (Exception ex) {
                Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaCantidades;
    }
    /*
     public static ArrayList<ComponenteFormulario> cargarXML(String nombreDocumento){
     SAXParserFactory factory = SAXParserFactory.newInstance();
     SAXParser saxParser;
     ArrayList<ComponenteFormulario> listaComponentes = null;
     try {
     saxParser = factory.newSAXParser();
     File archivoXML = new File(nombreDocumento);
     LeerXML leerXML =  new LeerXML();
     saxParser.parse(archivoXML, leerXML);
     listaComponentes =  leerXML.getListaComponentes();
     ArrayList<ComponenteFormulario> listaComponentesRepetidos = new ArrayList<ComponenteFormulario>();
     int indice = 0;
     for (ComponenteFormulario componenteFormulario : listaComponentes) {
     if ( (listaComponentes.get(indice).getValor().equals("\n")) || (listaComponentes.get(indice).getValor().equals("\n    ")) || (listaComponentes.get(indice).getValor().equals("\n        ")) || (listaComponentes.get(indice).getValor().equals("\n            ")))
     listaComponentes.get(indice).setValor("");
     if ( (listaComponentes.get(indice).getNombre().equals("Registral")) || (listaComponentes.get(indice).getNombre().equals("Titular")) || (listaComponentes.get(indice).getNombre().equals("Titulo")))
     listaComponentesRepetidos.add(listaComponentes.get(indice));
     if (indice <= (listaComponentes.size() - 2))
     if (listaComponentes.get(indice).getNombre().equals(listaComponentes.get(indice + 1).getNombre()))
     listaComponentesRepetidos.add(listaComponentes.get(indice + 1));
     indice++;
     }
     listaComponentes.removeAll(listaComponentesRepetidos);
            
     } catch (ParserConfigurationException ex) {
     Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SAXException ex) {
     Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
     Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
     }
     return listaComponentes;
     }*/

    /**
     * Método que parsea y lee un documento XML para devolver una lista con la
     * información de cada componente del formulario.
     *
     * @param rutaArchivo Ruta del archivo.
     * @return ArrayList (ComponenteFormulario) Lista con la información de los
     * componentes del formulario.
     */
    public static ArrayList<ComponenteFormulario> leerXML(String rutaArchivo) {
        ArrayList<ComponenteFormulario> listaComponentes = new ArrayList<>();
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(rutaArchivo);
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            listaComponentes.add(new ComponenteFormulario("REFERENCE", rootNode.getAttributeValue("Reference")));
            java.util.List list = rootNode.getChildren();
            Element elementoTemp;
            Element hijoDeRegistral;
            Element hijoDeTitular;
            Element hijoDeTitulo;
            for (int i = 0; i < list.size(); i++) {
                elementoTemp = (Element) list.get(i);
                switch (elementoTemp.getName()) {
                    case "Registral":
                        java.util.List listaHijosRegistral = elementoTemp.getChildren();
                        for (int j = 0; j < listaHijosRegistral.size(); j++) {
                            hijoDeRegistral = (Element) listaHijosRegistral.get(j);
                            listaComponentes.add(new ComponenteFormulario(hijoDeRegistral.getName(), hijoDeRegistral.getText()));
                        }
                        break;
                    case "Titular":
                        java.util.List listaHijosTitular = elementoTemp.getChildren();
                        for (int k = 0; k < listaHijosTitular.size(); k++) {
                            hijoDeTitular = (Element) listaHijosTitular.get(k);
                            if (hijoDeTitular.getName().equals("Titulo")) {
                                java.util.List listaHijosTitulo = hijoDeTitular.getChildren();
                                for (int m = 0; m < listaHijosTitulo.size(); m++) {
                                    hijoDeTitulo = (Element) listaHijosTitulo.get(m);
                                    listaComponentes.add(new ComponenteFormulario(hijoDeTitulo.getName(), hijoDeTitulo.getText()));
                                }
                            } else {
                                listaComponentes.add(new ComponenteFormulario(hijoDeTitular.getName(), hijoDeTitular.getText()));
                            }
                        }
                        break;
                    default:
                        listaComponentes.add(new ComponenteFormulario(elementoTemp.getName(), elementoTemp.getText()));
                        break;
                }
            }
        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
        return listaComponentes;
    }

    /**
     * Método que parsea y lee un documento XML (Cargas) para devolver una lista
     * con la información de cada componente del formulario.
     *
     * @param rutaArchivo Ruta del archivo
     * @return Matriz con la información de los componentes del formulario de
     * cargas, donde cada fila representa la información de una carga
     * determinada.
     */
    public static ArrayList<ArrayList<ComponenteFormulario>> leerXMLCargas(String rutaArchivo) {
        ArrayList<ArrayList<ComponenteFormulario>> listaCargas = new ArrayList<>();
        ArrayList<ComponenteFormulario> listaComponentes = new ArrayList<>();
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(rutaArchivo);
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            //listaComponentes.add(new ComponenteFormulario("REFERENCE", rootNode.getAttributeValue("Reference")));
            java.util.List list = rootNode.getChildren();
            Element elementoTemp;
            Element hijoDeCarga;
            for (int i = 0; i < list.size(); i++) {
                elementoTemp = (Element) list.get(i);
                if (elementoTemp.getName().equals("Carga")) {
                    java.util.List listaHijosCarga = elementoTemp.getChildren();
                    for (int j = 0; j < listaHijosCarga.size(); j++) {
                        hijoDeCarga = (Element) listaHijosCarga.get(j);
                        listaComponentes.add(new ComponenteFormulario(hijoDeCarga.getName(), hijoDeCarga.getText()));
                    }
                    listaCargas.add(listaComponentes);
                    listaComponentes = new ArrayList<>();
                }
            }
        } catch (IOException | JDOMException io) {
            System.out.println(io.getMessage());
        }
        return listaCargas;
    }

    /**
     * Método que guarda la información de los documentos en un fichero usando
     * un método de encriptación.
     *
     * @param listaDocumentos Lista de documentos.
     */
    public static void guardarListaDocumentos(ArrayList<Documento> listaDocumentos) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(rutaDocumentos, false));
            String datosDocumento = "";
            for (Documento documento : listaDocumentos) {
                datosDocumento = datosDocumento.concat(documento.getNombre()).concat(":").concat(documento.getTipoDocumento()).concat(":").concat(documento.getEstado()).concat(":").concat(documento.getUsuario()).concat(":").concat(documento.getFecha()).concat(":").concat(documento.getDirectorioRaiz());
                bw.write(encriptar(datosDocumento) + "\n");
                datosDocumento = "";
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FormaCrearUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public static void guardarListaDocumentosArchivoBinario(ArrayList<Documento> listaDocumentos){
     try{
     DataOutputStream salida =new DataOutputStream(new FileOutputStream(rutaDocumentosB, false));
     String datosDocumento = "";
     for (Documento documento : listaDocumentos) {
     datosDocumento = datosDocumento.concat(documento.getNombre()).concat(":").concat(documento.getTipoDocumento()).concat(":").concat(documento.getEstado()).concat(":").concat(documento.getUsuario()).concat(":").concat(documento.getFecha()).concat(":").concat(documento.getDirectorioRaiz());
     salida.writeChars(datosDocumento);
     datosDocumento = "";
     }  
     salida.close();
     }catch(IOException e){
     System.out.println("Error E/S");
     }
     }*/
    /**
     *
     */
    public static void copiarArchivosXmlHaciaServidorFTP() {
        FTPClient ftpClient = new FTPClient();
        try {
            String nombreHost = "hm6744.neodigit.net";
            //InetAddress ip = InetAddress.getByName(nombreHost);
            ftpClient.connect(InetAddress.getByName(nombreHost));
            ftpClient.login("centroingentis", "0l30nt1d");
            int reply = ftpClient.getReplyCode();
            System.out.println("Respuesta recibida de conexión FTP:" + reply);

            if (FTPReply.isPositiveCompletion(reply)) {
                System.out.println("Conectado");
                ftpClient.changeWorkingDirectory("/");//Cambiar directorio de trabajo
                System.out.println("Se cambió satisfactoriamente el directorio");
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                BufferedInputStream buffIn;
                buffIn = new BufferedInputStream(new FileInputStream(""));//Ruta del archivo para enviar
                ftpClient.enterLocalPassiveMode();
                ftpClient.storeFile("", buffIn);//Ruta completa de alojamiento en el FTP
                buffIn.close(); //Cerrar envio de arcivos al FTP
                ftpClient.logout(); //Cerrar sesión
                ftpClient.disconnect();//Desconectarse del servidor
            } else {
                System.out.println("No Conectado");
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param nombreArchivo
     * @return
     */
   
    public static File copiarArchivosPdfDesdeServidorFTP(String nombreArchivo) {
        File archivoRetorno = null;
        FTPClient ftp = new FTPClient();
        int respuesta;

        String archivo = nombreArchivo;

        try {
             File file = new File(direcion.concat("\\conf\\configFtp.properties"));
            FileInputStream fileInputStream = new FileInputStream(file);
            Properties mainProperties = new Properties();
            mainProperties.load(fileInputStream);
            //buscando en el fichero de conf la llave "ipFtp"
            String ipFtp = mainProperties.getProperty("ipFtp");
            //buscando en el fichero de conf la llave "userFtp"
            String usuarioFtp = mainProperties.getProperty("usuarioFtp");
            //buscando en el fichero de conf la llave "passWdFtp"
            String claveFtp = mainProperties.getProperty("claveFtp");
            //Cerrando el fichero
            fileInputStream.close();
            ftp.connect(ipFtp);
            ftp.login(usuarioFtp, claveFtp);
            respuesta = ftp.getReplyCode();
            if (respuesta == 230) {
                ftp.setFileType(FTP.BINARY_FILE_TYPE);
                ftp.changeWorkingDirectory("/home/BPO");
                respuesta = ftp.getReplyCode();
                if (FTPReply.isPositiveCompletion(respuesta)) {
                    FTPFile archivosFtp[] = ftp.listFiles();
                    respuesta = ftp.getReplyCode();
                    if (FTPReply.isPositiveCompletion(respuesta)) {
                        if (archivosFtp.length > 0) {
                            for (FTPFile archivosFtp1 : archivosFtp) {
                                String nombre = archivosFtp1.getName();
                                if (nombre.equals(nombreArchivo)) {
                                    String archivoSalida = rutaEnviados
                                            + File.separator + archivosFtp1.getName();
                                    boolean retorno_download = ftp.retrieveFile(archivosFtp1.getName(), new FileOutputStream(archivoSalida));
                                    if (retorno_download) {
                                        archivoRetorno = new File(archivoSalida);
                                        if (!(archivoRetorno.length() > 0)) {
                                            System.out.println("Advertencia: Archivo con longitud 0");
                                            archivoRetorno = null;
                                        }
                                    } else {
                                        System.out.println("No se pudo descargar en: " + archivoSalida);
                                    }
                                }
                                if (archivoRetorno != null) {
                                    break;
                                }
                            }
                            if (archivoRetorno == null) {
                                System.out.println("No se pudo descargar el archivo: " + nombreArchivo);
                            }
                        } else {
                            System.out.println("Listado de archivos de longitud invalida -" + archivosFtp.length);
                        }
                    } else {
                        System.out.println("No se pudo listar el directorio -");
                    }
                } else {
                    System.out.println("No se pudo cambiar de directorio -");
                }
            } else {
                System.out.println("No se pudo autenticar -");
            }
        } catch (SocketException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                ftp.disconnect();
            } catch (IOException ex) {
            }
        }
        return archivoRetorno;
    }

    /**
     *
     * @return
     */
    public static Session connectFTPbySSH() {

        JSch jsch = new JSch();
        Session session = null;
        try {
            File file = new File(direcion.concat("\\conf\\configFtp.properties"));
            FileInputStream fileInputStream = new FileInputStream(file);
            Properties mainProperties = new Properties();
            mainProperties.load(fileInputStream);
            //buscando en el fichero de conf la llave "ipFtp"
            String ipFtp = mainProperties.getProperty("ipFtp");

            //buscando en el fichero de conf la llave "userFtp"
            String userFtp = mainProperties.getProperty("userFtp");
            //buscando en el fichero de conf la llave "passWdFtp"
            String passWdFtp = mainProperties.getProperty("passWdFtp");

            //Cerrando el fichero
            fileInputStream.close();
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            }

            session = jsch.getSession(userFtp, ipFtp);
            session.setPassword(passWdFtp);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return session;
    }

    /**
     *
     * @param file
     * @return
     */
    private static List<String> fileToLines(File file) {
        BufferedReader in = null;
        final List<String> lines = new ArrayList<>();
        try {

            String line;
            in = new BufferedReader(new FileReader(file));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lines;
    }

    /**
     *
     * @param nombreUsuario
     */
    public static void copiarFichXMLToFTPbySSH(String nombreUsuario) {
        ArrayList<String> listaDirectorio = new ArrayList<>();
        listaDirectorio.add("Procesados/IRPF/");
        listaDirectorio.add("Procesados/Nomina/");
        listaDirectorio.add("Procesados/Recibo/");
        listaDirectorio.add("Procesados/Tasacion/");
        listaDirectorio.add("Procesados/Vida Laboral/");
        listaDirectorio.add("Procesados/DocumentosKO/");
        listaDirectorio.add("Procesados/Nota Simple/");

        Session session = connectFTPbySSH();
        try {
            if (session != null && session.isConnected()) {
                ChannelSftp channelSftp;
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                for (String fileName : listaDirectorio) {
                    String filePath = System.getProperty("user.dir").concat("\\docs\\").concat(fileName.replaceAll("/", "\\\\"));
                    File dirRaiz = new File(filePath);
                    String filesReady[] = dirRaiz.list();
                    for (String fileNameTemp : filesReady) {
                        if (!esDirectorio(fileNameTemp)) {
                            channelSftp.put(filePath.concat("\\").concat(fileNameTemp), fileName.concat(fileNameTemp));
                            File archivo = new File(filePath.concat("\\").concat(fileNameTemp));
                            archivo.renameTo(new File(filePath.concat("\\").concat(fileNameTemp).replaceAll("Procesados", "Subidos")));
                            generarTraza(fileNameTemp, "Procesado\t", nombreUsuario);
                        }
                    }
                }
                channelSftp.exit();
                channelSftp.disconnect();
                session.disconnect();
            }
        } catch (JSchException | SftpException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param usuario
     */
    public static void copiarFichTrzasToFTP(String usuario) {
        Session session = connectFTPbySSH();
        String fuente = rutaTrazas.concat("trazas_").concat(usuario).concat(".dat");
        File ficheroFuente = new File(fuente);

        try {
            if (session != null && session.isConnected()) {
                ficheroFuente.createNewFile();
                ChannelSftp channelSftp;
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                channelSftp.put(fuente, "/home/BPO/Historico/".concat("trazas_").concat(usuario).concat(".dat"));
                //channelSftp.put(fuente, "/home/BPO/Historico/".concat("trazas_").concat(usuario).concat("_0").concat(".dat"));
                //channelSftp.mkdir("/home/BPO/Enviados_Por_Usuario/".concat("Enviados_").concat(usuario));
                ficheroFuente.delete();

                channelSftp.exit();
                channelSftp.disconnect();
                session.disconnect();
            } else {

            }
        } catch (JSchException | SftpException | IOException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param nombreArchivo
     * @param estado
     * @param nombreUsuario
     */
    private static void generarTraza(String nombreArchivo, String estado, String nombreUsuario) {
        Session session = connectFTPbySSH();
        if (session != null && session.isConnected()) {
            ChannelSftp channelSftp;
            try {
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                String fuente = "/home/BPO/Historico/".concat("trazas_").concat(nombreUsuario).concat(".dat");
                String destino = rutaTrazas.concat("trazas_").concat(nombreUsuario).concat(".dat");
                channelSftp.get(fuente, destino);
                BufferedWriter bw;
                try {
                    bw = new BufferedWriter(new FileWriter(destino, true));
                    String fechaAccion = new Date().toString();
                    bw.write(nombreArchivo.concat("\t\t").concat(estado).concat("\t\t").concat(fechaAccion).concat("\n"));
                    bw.close();
                } catch (IOException ex) {
                    Logger.getLogger(FormaCrearUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
                channelSftp.put(destino, fuente);
                channelSftp.exit();
                channelSftp.disconnect();
                session.disconnect();
                File fichero = new File(destino);
                fichero.delete();
            } catch (JSchException | SftpException ex) {
                Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @param listaDocumentos
     * @return
     */
    private static Integer catidadDocumentosPendientes(ArrayList<Documento> listaDocumentos) {
        Integer cantidadDoc = 0;
        for (Documento documento : listaDocumentos) {
            if (documento.getEstado().equals("Pendiente")) {
                cantidadDoc++;
            }
        }
        return cantidadDoc;
    }

    /**
     *
     * @param channelSftp
     * @return
     */
    private static Boolean noExisteDescarga(ChannelSftp channelSftp) {
        try {
            Vector listaArchivos = channelSftp.ls("/home/BPO/Historico/");
            for (int i = 0; i < listaArchivos.size(); i++) {
                ChannelSftp.LsEntry archivo = (ChannelSftp.LsEntry) listaArchivos.elementAt(i);
                if (!archivo.getFilename().equals(".") && !archivo.getFilename().equals("..")) {
                    String partesNombreExt[] = archivo.getFilename().split("\\.");
                    String partesNombre[] = partesNombreExt[0].split("_");
                    if (partesNombre[2].equals("1")) {
                        return false;
                    }
                }
            }
        } catch (SftpException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public static List<ChannelSftp.LsEntry> ordenar(List<ChannelSftp.LsEntry> listaArchivos) {
        for (int k = 0; k < (listaArchivos.size() - 1); k++) {
            for (int f = 0; f < (listaArchivos.size() - 1) - k; f++) {
                System.out.println(listaArchivos.get(f).getAttrs().getFlags());
                if (listaArchivos.get(f).getAttrs().getMTime() > listaArchivos.get(f + 1).getAttrs().getMTime()) {
                    ChannelSftp.LsEntry aux;
                    aux = listaArchivos.get(f);
                    listaArchivos.set(f, listaArchivos.get(f + 1));
                    listaArchivos.set(f + 1, aux);
                }
            }
        }
        return listaArchivos;
    }

    /**
     *
     * @param tarea
     * @param listaDocumentos
     * @param nombreUsuario
     * @return
     */
    public static int copiarFichPdfFromFTPbySSH(TareaSegundoPlano tarea, ArrayList<Documento> listaDocumentos, String nombreUsuario) {
        ArrayList<String> listaDirectorio = new ArrayList<>();
        listaDirectorio.add("EnviadosWS/IRPF/");
        listaDirectorio.add("EnviadosWS/Nomina/");
        listaDirectorio.add("EnviadosWS/Recibo/");
        listaDirectorio.add("EnviadosWS/Vida Laboral/");

        int cantidadDocDescargados = 0;
        Session session = connectFTPbySSH();
        Integer cantidadDocumentos = 0;
        try {
            if (session != null && session.isConnected()) {
                ChannelSftp channelSftp;
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                for (String fileName : listaDirectorio) {
                    Vector<ChannelSftp.LsEntry> listaArchivos = channelSftp.ls("/home/BPO/".concat(fileName));
                    /**
                     * ** Ordenar por la fecha de modificación **
                     */
                    List<ChannelSftp.LsEntry> listaEntry = new ArrayList<>();
                    for (Object archivo : listaArchivos) {
                        if (!((ChannelSftp.LsEntry) archivo).getFilename().equals(".") && !((ChannelSftp.LsEntry) archivo).getFilename().equals("..")) {
                            listaEntry.add((ChannelSftp.LsEntry) archivo);
                        }
                    }
                    listaEntry = ordenar(listaEntry);
                    /**
                     * ******************************************
                     */
                    if (listaArchivos.size() >= 12) {
                        cantidadDocumentos = 9;
                    } else {
                        cantidadDocumentos = listaArchivos.size() - 2;
                    }
                    for (int j = 0; j < cantidadDocumentos; j++) {
                        //ChannelSftp.LsEntry archivo = (ChannelSftp.LsEntry)listaArchivos.elementAt(j);
                        ChannelSftp.LsEntry archivo = listaEntry.get(j);
                        //if (!archivo.getFilename().equals(".") && !archivo.getFilename().equals("..")){
                        String directorioAdecuado[] = fileName.split("/");
                        String destino = rutaEnviados.concat(directorioAdecuado[1]).concat("\\").concat("ocr").concat("\\").concat(archivo.getFilename());
                        channelSftp.get("/home/BPO/".concat(fileName).concat("/").concat(archivo.getFilename()), destino);
                        String rutaEliminar = "/home/BPO/".concat(fileName).concat("/").concat(archivo.getFilename());
                        channelSftp.rm(rutaEliminar);
                        channelSftp.put(destino, "/home/BPO/ConvirtiendoWS/".concat(directorioAdecuado[1]).concat("/").concat(archivo.getFilename()));
                        generarTraza(archivo.getFilename(), "Convirtiendo", nombreUsuario);
                        cantidadDocDescargados++;
                        tarea.setCantidad(cantidadDocDescargados);
                        //}    
                    }
                }
                channelSftp.exit();
                channelSftp.disconnect();
                session.disconnect();
            }
        } catch (JSchException | SftpException ex) {
            System.out.println(ex.getMessage());
        }
        return 1;
    }

    /**
     *
     * @param listaArchivos
     * @return
     */
    private static Integer getNumberOfDocumentsToDownload() {
        Integer numberOfDocuments = -1;
        Session session = connectFTPbySSH();
        if (session != null && session.isConnected()) {
            ChannelSftp channelSftp;
            try {
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                String destino = rutaTrazas.concat("number_of_documents_to_download.dat");
                channelSftp.get("/home/BPO/config/number_of_documents_to_download.dat", destino);
                File archivo = new File(destino);
                ArrayList<String> numberOfDocumentsList = new ArrayList<>();
                try {
                    Scanner scanner = new Scanner(archivo);
                    while (scanner.hasNextLine()) {
                        numberOfDocumentsList.add(scanner.nextLine());
                    }
                    scanner.close();
                    numberOfDocuments = Integer.valueOf(numberOfDocumentsList.get(0).split(":")[1]);
                } catch (FileNotFoundException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
                }
                channelSftp.exit();
                channelSftp.disconnect();
                session.disconnect();
                if (archivo.delete()) {
                    System.out.println("Archivo eliminado correctamente");
                }
            } catch (JSchException | SftpException ex) {
                Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return numberOfDocuments;
    }

    public static int copiarNotasSimplesFromFTP(TareaSegundoPlano tarea, ArrayList<Documento> listaDocumentos, String nombreUsuario) {
        ArrayList<String> listaDirectorio = new ArrayList<>();
        listaDirectorio.add("EnviadosWS/Nota Simple/");
        //listaDirectorio.add("Test/");

        int cantidadDocDescargados = 0;
        Session session = connectFTPbySSH();
        Integer cantidadDocumentos;
        Integer numberOfNotesToDownload = getNumberOfDocumentsToDownload();
        if (numberOfNotesToDownload != -1) {
            try {
                if (session != null && session.isConnected()) {
                    ChannelSftp channelSftp;
                    channelSftp = (ChannelSftp) session.openChannel("sftp");
                    channelSftp.connect();
                    for (String fileName : listaDirectorio) {
                        Vector listaArchivos = channelSftp.ls("/home/BPO/".concat(fileName));

                        /**
                         * ** Ordenar por la fecha de modificación **
                         */
                        List<ChannelSftp.LsEntry> listaEntry = new ArrayList<>();
                        for (Object archivo : listaArchivos) {
                            if (!((ChannelSftp.LsEntry) archivo).getFilename().equals(".") && !((ChannelSftp.LsEntry) archivo).getFilename().equals("..")) {
                                listaEntry.add((ChannelSftp.LsEntry) archivo);
                            }
                        }
                        listaEntry = ordenar(listaEntry);
                        /**
                         * **********************************************
                         */

                        if (listaArchivos.size() >= (numberOfNotesToDownload + 3)) {
                            cantidadDocumentos = numberOfNotesToDownload;
                        } else {
                            cantidadDocumentos = listaArchivos.size() - 2;
                        }
                        for (int j = 0; j < cantidadDocumentos; j++) {
                            ChannelSftp.LsEntry archivo = listaEntry.get(j);
                            String directorioAdecuado[] = fileName.split("/");
                            //PARA PRUEBAS SE HA COMENTADO LA SIGUIENTE LINEA
                            //String destino = rutaEnviados.concat(directorioAdecuado[0]).concat("\\").concat("ocr").concat("\\").concat(archivo.getFilename()); 
                            String destino = rutaEnviados.concat(directorioAdecuado[1]).concat("\\").concat("ocr").concat("\\").concat(archivo.getFilename());
                            channelSftp.get("/home/BPO/".concat(fileName).concat("/").concat(archivo.getFilename()), destino);
                            String rutaEliminar = "/home/BPO/".concat(fileName).concat("/").concat(archivo.getFilename());
                            channelSftp.rm(rutaEliminar);

                            channelSftp.put(destino, "/home/BPO/ConvirtiendoWS/".concat(directorioAdecuado[1]).concat("/").concat(archivo.getFilename()));
                            generarTraza(archivo.getFilename(), "Convirtiendo", nombreUsuario);
                            cantidadDocDescargados++;
                            tarea.setCantidad(cantidadDocDescargados);
                        }
                    }
                    channelSftp.exit();
                    channelSftp.disconnect();
                    session.disconnect();
                }
            } catch (JSchException | SftpException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error en la lectura del número de Notas Simples a descargar.");
            return 0;
        }
        return 1;
    }

    public static int copiarTasacionesFromFTP(TareaSegundoPlano tarea, ArrayList<Documento> listaDocumentos, String nombreUsuario) {
        ArrayList<String> listaDirectorio = new ArrayList<>();
        listaDirectorio.add("EnviadosWS/Tasacion/");
        //listaDirectorio.add("Test");
        int cantidadDocDescargados = 0;
        Session session = connectFTPbySSH();
        Integer cantidadDocumentos = 0;
        try {
            if (session != null && session.isConnected()) {
                ChannelSftp channelSftp;
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                for (String fileName : listaDirectorio) {
                    Vector listaArchivos = channelSftp.ls("/home/BPO/".concat(fileName));
                    /**
                     * ** Ordenar por la fecha de modificación **
                     */
                    List<ChannelSftp.LsEntry> listaEntry = new ArrayList<>();
                    for (Object archivo : listaArchivos) {
                        if (!((ChannelSftp.LsEntry) archivo).getFilename().equals(".") && !((ChannelSftp.LsEntry) archivo).getFilename().equals("..")) {
                            listaEntry.add((ChannelSftp.LsEntry) archivo);
                        }
                    }
                    listaEntry = ordenar(listaEntry);
                    if (listaArchivos.size() >= 12) {
                        cantidadDocumentos = 9;
                    } else {
                        cantidadDocumentos = listaArchivos.size() - 2;
                    }
                    for (int j = 0; j < cantidadDocumentos; j++) {
                        //ChannelSftp.LsEntry archivo = (ChannelSftp.LsEntry)listaArchivos.elementAt(j);
                        ChannelSftp.LsEntry archivo = listaEntry.get(j);
                        //if (!archivo.getFilename().equals(".") && !archivo.getFilename().equals("..")){
                        String directorioAdecuado[] = fileName.split("/");
                        String destino = rutaEnviados.concat(directorioAdecuado[1]).concat("\\").concat("ocr").concat("\\").concat(archivo.getFilename());
                        channelSftp.get("/home/BPO/".concat(fileName).concat("/").concat(archivo.getFilename()), destino);
                        String rutaEliminar = "/home/BPO/".concat(fileName).concat("/").concat(archivo.getFilename());
                        channelSftp.rm(rutaEliminar);
                        channelSftp.put(destino, "/home/BPO/ConvirtiendoWS/".concat(directorioAdecuado[1]).concat("/").concat(archivo.getFilename()));
                        generarTraza(archivo.getFilename(), "Convirtiendo", nombreUsuario);
                        cantidadDocDescargados++;
                        tarea.setCantidad(cantidadDocDescargados);
                        //}    
                    }
                }
                channelSftp.exit();
                channelSftp.disconnect();
                session.disconnect();
            }
        } catch (JSchException | SftpException ex) {
            System.out.println(ex.getMessage());
        }
        return 1;
    }

    public static int descargarKOOCR(TareaSegundoPlano tarea, ArrayList<Documento> listaDocumentos, String nombreUsuario) {
        ArrayList<String> listaDirectorio = new ArrayList<>();
        listaDirectorio.add("ConvirtiendoWS/DocumentosKO/");

        int cantidadDocDescargados = 0;
        Session session = connectFTPbySSH();
        try {
            if (session != null && session.isConnected()) {
                ChannelSftp channelSftp;
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                for (String fileName : listaDirectorio) {
                    Vector listaArchivos = channelSftp.ls("/home/BPO/".concat(fileName));
                    for (int j = 0; j < listaArchivos.size(); j++) {
                        ChannelSftp.LsEntry archivo = (ChannelSftp.LsEntry) listaArchivos.elementAt(j);
                        if (!archivo.getFilename().equals(".") && !archivo.getFilename().equals("..")) {
                            //String fuente = "/home/BPO/".concat(fileName).concat("/").concat(archivo.getFilename());
                            String directorioAdecuado[] = fileName.split("/");
                            String destino = rutaEnviados.concat(directorioAdecuado[1]).concat("\\").concat("ocr").concat("\\").concat(archivo.getFilename());
                            //channelSftp.get(fuente, destino);
                            channelSftp.get("/home/BPO/".concat(fileName).concat("/").concat(archivo.getFilename()), destino);
                            //String rutaEliminar = "/home/BPO/".concat(fileName).concat("/").concat(archivo.getFilename());
                            //channelSftp.rm(rutaEliminar);
                            //channelSftp.put(destino, "/home/BPO/ConvirtiendoWS/".concat(directorioAdecuado[1]).concat("/").concat(archivo.getFilename()));
                            //generarTraza(archivo.getFilename(), "Convirtiendo", nombreUsuario);
                            cantidadDocDescargados++;
                            tarea.setCantidad(cantidadDocDescargados);
                        }
                    }
                }
                channelSftp.exit();
                channelSftp.disconnect();
                session.disconnect();
            }
        } catch (JSchException | SftpException ex) {
            System.out.println(ex.getMessage());
        }
        return 1;
    }

    /**
     *
     * @param elemento
     * @return
     */
    private static Boolean esDirectorio(String elemento) {
        String partes[] = elemento.split("\\.");
        return partes.length == 1;
    }

    /**
     *
     * @return
     */
    public static int descargarXmlPendientes() {
        ArrayList<String> listaDirectorio = new ArrayList<>();
        listaDirectorio.add("Pendientes/");
        listaDirectorio.add("Pendientes/IRPF/");
        listaDirectorio.add("Pendientes/Nomina/");
        listaDirectorio.add("Pendientes/Recibo/");
        listaDirectorio.add("Pendientes/Tasacion/");
        listaDirectorio.add("Pendientes/Vida Laboral/");
        for (String fileName : listaDirectorio) {
            Session session = connectFTPbySSH();
            try {
                if (session != null && session.isConnected()) {
                    ChannelSftp channelSftp;
                    channelSftp = (ChannelSftp) session.openChannel("sftp");
                    channelSftp.connect();
                    Vector listaArchivosXml = channelSftp.ls(fileName);
                    for (int i = 0; i < listaArchivosXml.size(); i++) {
                        ChannelSftp.LsEntry archivoXml = (ChannelSftp.LsEntry) listaArchivosXml.elementAt(i);
                        if (!archivoXml.getFilename().equals(".") && !archivoXml.getFilename().equals("..") && !esDirectorio(archivoXml.getFilename())) {
                            String fuente = "/home/BPO/".concat(fileName).concat(archivoXml.getFilename());
                            String rutaPendienteExacta = rutaPendientes.concat(fileName.replaceAll("/", "\\\\"));
                            String destino = rutaPendienteExacta.concat("\\").concat(archivoXml.getFilename());
                            //File ficheroADescargar = new File(destino);
                            //if (!ficheroADescargar.exists()){
                            channelSftp.get(fuente, destino);
                            channelSftp.rm("/home/BPO/".concat(fileName).concat("/").concat(archivoXml.getFilename()));
                            //}
                        }
                    }
                    channelSftp.exit();
                    channelSftp.disconnect();
                    session.disconnect();
                } else {
                }
            } catch (JSchException | SftpException ex) {
                Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 1;
    }

    public static int descargarXmlPendientesOCR() {
        Session session = connectFTPbySSH();
        String fileName = "PendientesOCR/";
        try {
            if (session != null && session.isConnected()) {
                ChannelSftp channelSftp;
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                Vector listaArchivosXml = channelSftp.ls(fileName);
                String destino;
                for (int i = 0; i < listaArchivosXml.size(); i++) {
                    ChannelSftp.LsEntry archivoXml = (ChannelSftp.LsEntry) listaArchivosXml.elementAt(i);
                    if (!archivoXml.getFilename().equals(".") && !archivoXml.getFilename().equals("..")) {
                        String fuente = "/home/BPO/PendientesOCR/".concat(archivoXml.getFilename());
                        if (archivoXml.getFilename().split("\\.")[1].equals("xml")) {
                            destino = rutaPendientesOCR.concat("\\").concat(archivoXml.getFilename());
                        } else {
                            destino = rutaProcesadosNotaSimpleOCR.concat("\\").concat(archivoXml.getFilename());
                        }
                        channelSftp.get(fuente, destino);
                        channelSftp.rm("/home/BPO/PendientesOCR/".concat("/").concat(archivoXml.getFilename()));
                    }
                }
                channelSftp.exit();
                channelSftp.disconnect();
                session.disconnect();
            } else {

            }
        } catch (JSchException | SftpException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    /**
     *
     * @param nombreUsuario
     */
    public static void subirXMLPendientes(String nombreUsuario) {
        ArrayList<String> listaDirectorio = new ArrayList<>();
        listaDirectorio.add("Procesados/");
        listaDirectorio.add("Procesados/IRPF/");
        listaDirectorio.add("Procesados/Nomina/");
        listaDirectorio.add("Procesados/Recibo/");
        listaDirectorio.add("Procesados/Tasacion/");
        listaDirectorio.add("Procesados/Vida Laboral/");
        for (String fileName : listaDirectorio) {
            Session session = connectFTPbySSH();
            //String fileName = "Procesados/";
            String filePath = rutaPendientes.concat(fileName.replaceAll("Procesados/", "Pendientes\\\\"));
            File dirRaiz = new File(filePath);
            String filesReady[] = dirRaiz.list();
            try {
                if (session != null && session.isConnected()) {
                    ChannelSftp channelSftp;
                    channelSftp = (ChannelSftp) session.openChannel("sftp");
                    channelSftp.connect();
                    String fuente = "/home/BPO/".concat(fileName);
                    String destino = rutaTrazas;
                    for (String fileNameTemp : filesReady) {
                        if (!esDirectorio(fileNameTemp)) {
                            try {
                                try {
                                    //Se busca en Procesados
                                    channelSftp.get(fuente.concat(fileNameTemp), destino.concat(fileNameTemp));
                                } catch (SftpException ex) {
                                    //Se busca en Subidos al no estar ya en Procesados
                                    channelSftp.get("/home/BPO/Subidos/".concat(fileNameTemp), destino.concat(fileNameTemp));
                                }
                                File ficheroProcesado = new File(filePath.concat("\\").concat(fileNameTemp));
                                File ficheroTemp = new File(destino.concat("\\").concat(fileNameTemp));

                                List<String> originalFileLines = fileToLines(ficheroProcesado);
                                List<String> revisedFileLines = fileToLines(ficheroTemp);
                                Patch patch = DiffUtils.diff(originalFileLines, revisedFileLines);

                                if (patch.getDeltas().size() > 0) {
                                    channelSftp.put(filePath.concat("\\").concat(fileNameTemp), fileName.concat(fileNameTemp));
                                    generarTraza(fileNameTemp, "Procesado\t", nombreUsuario);
                                }
                                ficheroTemp.delete();
                            } catch (SftpException ex) {
                                channelSftp.put(filePath.concat("\\").concat(fileNameTemp), fileName.concat(fileNameTemp));
                                generarTraza(fileNameTemp, "Procesado\t", nombreUsuario);
                            }
                        }
                    }
                    channelSftp.exit();
                    channelSftp.disconnect();
                    session.disconnect();
                } else {

                }
            } catch (JSchException | SftpException ex) {
                Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void subirXMLPendientesOCR(String nombreUsuario) {
        Session session = connectFTPbySSH();
        String fileName = "Procesados/NotaSimpleOCR/";
        String filePath = rutaPendientesOCR;
        File dirRaiz = new File(rutaPendientesOCR);
        String filesReady[] = dirRaiz.list();
        try {
            if (session != null && session.isConnected()) {
                ChannelSftp channelSftp;
                channelSftp = (ChannelSftp) session.openChannel("sftp");
                channelSftp.connect();
                String fuente = "/home/BPO/Procesados/NotaSimpleOCR/";
                String destino = rutaTrazas;
                for (String fileNameTemp : filesReady) {
                    try {
                        try {
                            //Se busca en Procesados
                            channelSftp.get(fuente.concat(fileNameTemp), destino.concat(fileNameTemp));
                        } catch (SftpException ex) {
                            //Se busca en Subidos al no estar ya en Procesados
                            channelSftp.get("/home/BPO/SubidosWS/NotaSimpleOCR/".concat(fileNameTemp), destino.concat(fileNameTemp));
                        }
                        File ficheroProcesado = new File(filePath.concat("\\").concat(fileNameTemp));
                        File ficheroTemp = new File(destino.concat("\\").concat(fileNameTemp));

                        List<String> originalFileLines = fileToLines(ficheroProcesado);
                        List<String> revisedFileLines = fileToLines(ficheroTemp);
                        Patch patch = DiffUtils.diff(originalFileLines, revisedFileLines);

                        if (patch.getDeltas().size() > 0) {
                            channelSftp.put(filePath.concat("\\").concat(fileNameTemp), fileName.concat(fileNameTemp));
                            //generarTraza(fileNameTemp, "Procesado\t", nombreUsuario);
                        }
                        ficheroTemp.delete();
                        if (!ficheroProcesado.renameTo(new File(rutaSubidos.concat(ficheroProcesado.getName())))) {
                            JOptionPane.showMessageDialog(null, "Debe cerrar el archivo XML para que se mueva al directorio adecuado.");
                        }

                    } catch (SftpException ex) {
                        channelSftp.put(filePath.concat("\\").concat(fileNameTemp), fileName.concat(fileNameTemp));
                        //generarTraza(fileNameTemp, "Procesado\t", nombreUsuario);
                    }
                }
                channelSftp.exit();
                channelSftp.disconnect();
                session.disconnect();
            }
        } catch (JSchException | SftpException ex) {
            Logger.getLogger(Utiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param cadena
     * @param CantCarAntesPunto
     * @return
     */
    public static String formaCadenaParaJFormatter(String cadena, int CantCarAntesPunto) {
        int indice = cadena.indexOf('.');
        String cadenaAntesPunto = cadena.substring(0, indice);
        String cadenaDespuesPunto = cadena.substring(indice, cadena.length());
        int cantCeros = CantCarAntesPunto - cadenaAntesPunto.length();
        for (int i = 0; i < cantCeros; i++) {
            cadenaAntesPunto = "0".concat(cadenaAntesPunto);
        }
        return cadenaAntesPunto.concat(cadenaDespuesPunto);
    }

    /**
     *
     * @param evt
     * @param campoTexto
     * @param longitudParteEntera
     * @param longitudParteDecimal
     */
    public static void validarNumeroReal(java.awt.event.KeyEvent evt, JTextField campoTexto, int longitudParteEntera, int longitudParteDecimal) {
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
                && (c != ',')) {
            evt.consume();
        }
        if (c == ',' && campoTexto.getText().contains(",")) {
            evt.consume();
        }
        if (campoTexto.getText().contains(",")) {
            String valores[] = campoTexto.getText().split(",");
            if ((valores[0].length() > longitudParteEntera)) {
                evt.consume();
            }
            if (valores.length > 1) {
                if ((campoTexto.getText().length() + 1) >= (longitudParteEntera + longitudParteDecimal + 1)) {
                    if ((((valores[0].length() + 1) > longitudParteEntera) && ((valores[1].length() + 1) > longitudParteDecimal))) /*|| ( (valores[1].length() + 1) > 2)*/ //if (!((valores[0].length() + 1) <= 3) || ((valores[1].length() + 1) <= 2))
                    {
                        evt.consume();
                    }
                }
            }
        } else if (campoTexto.getText().length() >= longitudParteEntera) {
            if (c != ',') {
                evt.consume();
            }
        }
    }

    public static void validarNumeroEntero(java.awt.event.KeyEvent evt, JTextField campoTexto, int longitudParteEntera) {
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
            evt.consume();
        }
        if ((campoTexto.getText().length() > (longitudParteEntera - 1))) {
            evt.consume();
        }
    }

    /**
     *
     * @param tabla
     */
    public static void establecerColorColumnasClases(JXTable tabla) {
        tabla.getColumn("Estado").setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent((JXTable) table, value, false, hasFocus, row, column);
                JLabel label = (JLabel) comp;
                String valorCampo = (String) value;
                switch (valorCampo) {
                    case "Pendiente":
                        comp.setForeground(Color.BLACK);
                        break;
                    case "Terminado":
                        comp.setForeground(Color.GREEN);
                        break;
                    case "Guardado":
                        comp.setForeground(Color.RED);
                        break;
                    default:
                        break;
                }
                comp.setBackground(new Color(223, 223, 223));
                comp.setFont(new Font(comp.getName(), Font.BOLD, 11));
                return comp;
            }
        });
    }

    /**
     * Método que exporta la información actual de los documentos en la tabla a
     * PDF.
     *
     * @param tabla Tabla con los datos del documento.
     * @param archivoPdf Archivo PDF.
     * @param titulo Título.
     */
    public static void exportarPDF(JXTable tabla, File archivoPdf, String titulo) {
        try {
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(archivoPdf));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("No se ha encontrado el fichero para generar el pdf.)" + fileNotFoundException);
            }
            document.open();

            document.addTitle("Reporte sobre documentos.");
            document.addSubject("Usando iText");
            document.addKeywords("Java, PDF, iText");
            document.addAuthor("TechID");
            document.addCreator("TechID");

            //Primera página
            Anchor anchor = new Anchor("Reporte sobre documentos.", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER));
            anchor.setName("Reporte sobre documentos.");

            //El segundo parámetro es el número del capítulo.
            Chapter catPart = new Chapter(new Paragraph(anchor), 1);

            Paragraph subPara = new Paragraph("Realizado por TechID", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER));
            Section subCatPart = catPart.addSection(subPara);
            subCatPart.add(new Paragraph("Documentos.\n\n"));

            // Se crea la tabla)
            PdfPTable table = new PdfPTable(tabla.getColumnCount());

            //Se llenan las filas de PdfPTable
            PdfPCell columnHeader;

            //Se rellenan las cabeceras de las columnas de la tabla.                
            for (int column = 0; column < tabla.getColumnCount(); column++) {
                columnHeader = new PdfPCell(new Phrase(tabla.getColumnName(column)));
                columnHeader.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table.addCell(columnHeader);
            }
            table.setHeaderRows(1);
            //Se rellenan las filas de la tabla.                
            for (int row = 0; row < tabla.getRowCount(); row++) {
                for (int column = 0; column < tabla.getColumnCount(); column++) {
                    table.addCell(tabla.getValueAt(row, column).toString());
                }
            }
            subCatPart.add(table);
            document.add(catPart);
            document.close();
        } catch (DocumentException documentException) {
            System.out.println("El fichero no existe. Se ha producido un error al generar un documento: " + documentException);
        }
    }

    /**
     * Método que encripta una cadena de texto utilizando el algoritmo DESede
     * para la clave secreta.
     *
     * @param texto Texto a encriptar.
     * @return Texto encriptado.
     */
    public static String encriptar(String texto) {
        //String secretKey = "bpotechid1234"; //llave para encriptar datos
        String base64EncryptedString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
        }
        return base64EncryptedString;
    }

    /**
     * Método que desencripta un texto determinado.
     *
     * @param textoEncriptado Texto Encriptado Texto encriptado
     * @return Texto desencriptado.
     * @throws Exception
     */
    public static String desencriptar(String textoEncriptado) throws Exception {
        //String secretKey = "bpotechid1234"; //llave para desenciptar datos
        String base64EncryptedString = "";
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decipher.doFinal(message);
            base64EncryptedString = new String(plainText, "UTF-8");
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
        }
        return base64EncryptedString;
    }

    /**
     *
     * @param fecha
     * @return
     */
    public static Boolean validarFecha(DateControl fecha) {
        if (fecha.getValue() != null) {
            Date menorFecha = new Date(1900 - 1900, 0, 1);
            Date mayorFecha = new Date();
            try {
                if (fecha.getDate().before(menorFecha) || fecha.getDate().after(mayorFecha)) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public static Boolean validarFechaCotaInferior(DateControl fecha) {
        if (fecha.getDate() != null) {
            Date menorFecha = new Date(1900 - 1900, 0, 1);
            try {
                if (fecha.getDate().before(menorFecha)) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param texto
     * @return
     */
    public static BigInteger convertirStringToASCII(String texto) {
        StringBuilder sb = new StringBuilder();
        for (char c : texto.toCharArray()) {
            sb.append((int) c);
        }
        BigInteger mInt = new BigInteger(sb.toString());
        return mInt;
    }

    /**
     *
     * @param texto
     * @return
     */
    public static ArrayList<Integer> convertirStringToListaASCII(String texto) {
        ArrayList<Integer> listaASCII = new ArrayList<>();
        for (int i = 0; i < texto.length(); ++i) {
            char c = texto.charAt(i);
            listaASCII.add((int) c);
        }
        return listaASCII;
    }

    public static String convertirASCIIToString(ArrayList<Integer> listaASCII) {
        String textoOriginal = "";
        for (Integer elemento : listaASCII) {
            textoOriginal = textoOriginal.concat(Character.toString((char) elemento.intValue()));
        }
        return textoOriginal;
    }

    /**
     *
     * @return
     */
    public static ArrayList<Poblacion> leerPoblaciones() {
        ArrayList<Poblacion> listaPoblaciones = new ArrayList<>();
        String cellData;
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setEncoding("ISO-8859-1");
            Workbook workbook = Workbook.getWorkbook(new File("conf\\Poblaciones.xls"), ws);
            Sheet sheet = workbook.getSheet("Hoja1");
            int fila = 0;
            Cell cell = sheet.getCell(0, fila); //columna,fila
            cellData = cell.getContents();
            while (!cellData.equals("FIN")) {
                // byte[] ptext = (sheet.getCell(1, fila)).getContents().getBytes(Charset.forName("UTF-8")); //ISO-8859-1
                // String value = new String(ptext, "UTF-8"); 
                listaPoblaciones.add(new Poblacion((sheet.getCell(0, fila)).getContents(), (sheet.getCell(1, fila)).getContents(), (sheet.getCell(2, fila)).getContents()));
                fila++;
                cell = sheet.getCell(0, fila); //columna,fila
                cellData = cell.getContents();
            }
            workbook.close();
        } catch (IOException | BiffException ex) {
            System.out.println(ex.getMessage());
        }
        return listaPoblaciones;
    }

    public static ArrayList<Provincia> leerProvincias() {
        ArrayList<Provincia> listaProvincia = new ArrayList<>();
        String cellData;
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setEncoding("ISO-8859-1");
            Workbook workbook = Workbook.getWorkbook(new File("conf\\Provincias.xls"), ws);
            Sheet sheet = workbook.getSheet("Hoja1");
            int fila = 0;
            Cell cell = sheet.getCell(0, fila); //columna,fila
            cellData = cell.getContents();
            while (!cellData.equals("FIN")) {
                // byte[] ptext = (sheet.getCell(1, fila)).getContents().getBytes(Charset.forName("UTF-8")); //ISO-8859-1
                // String value = new String(ptext, "UTF-8"); 
                listaProvincia.add(new Provincia((sheet.getCell(0, fila)).getContents(), (sheet.getCell(1, fila)).getContents()));
                fila++;
                cell = sheet.getCell(0, fila); //columna,fila
                cellData = cell.getContents();
            }
            workbook.close();
        } catch (IOException | BiffException ex) {
            System.out.println(ex.getMessage());
        }
        return listaProvincia;
    }

    public static ArrayList<RegistroPropiedad> leerRegistros() {
        ArrayList<RegistroPropiedad> listaRegistroPropiedad = new ArrayList<>();
        String cellData;
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setEncoding("ISO-8859-1");
            Workbook workbook = Workbook.getWorkbook(new File("conf\\Registros.xls"), ws);
            Sheet sheet = workbook.getSheet("Hoja1");
            int fila = 0;
            Cell cell = sheet.getCell(0, fila); //columna,fila
            cellData = cell.getContents();
            while (!cellData.equals("FIN")) {
                // byte[] ptext = (sheet.getCell(1, fila)).getContents().getBytes(Charset.forName("UTF-8")); //ISO-8859-1
                // String value = new String(ptext, "UTF-8"); 
                listaRegistroPropiedad.add(new RegistroPropiedad((sheet.getCell(0, fila)).getContents(), (sheet.getCell(1, fila)).getContents()));
                fila++;
                cell = sheet.getCell(0, fila); //columna,fila
                cellData = cell.getContents();
            }
            workbook.close();
        } catch (IOException | BiffException ex) {
            System.out.println(ex.getMessage());
        }
        return listaRegistroPropiedad;
    }

    public static ArrayList<RegistroPropiedad> leerBeneficiarios() {
        ArrayList<RegistroPropiedad> listaRegistroPropiedad = new ArrayList<>();
        String cellData;
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setEncoding("ISO-8859-1");
            Workbook workbook = Workbook.getWorkbook(new File("conf\\Beneficiarios.xls"), ws);
            Sheet sheet = workbook.getSheet("Hoja1");
            int fila = 0;
            Cell cell = sheet.getCell(0, fila); //columna,fila
            cellData = cell.getContents();
            while (!cellData.equals("FIN")) {
                // byte[] ptext = (sheet.getCell(1, fila)).getContents().getBytes(Charset.forName("UTF-8")); //ISO-8859-1
                // String value = new String(ptext, "UTF-8"); 
                listaRegistroPropiedad.add(new RegistroPropiedad((sheet.getCell(0, fila)).getContents(), (sheet.getCell(1, fila)).getContents()));
                fila++;
                cell = sheet.getCell(0, fila); //columna,fila
                cellData = cell.getContents();
            }
            workbook.close();
        } catch (IOException | BiffException ex) {
            System.out.println(ex.getMessage());
        }
        return listaRegistroPropiedad;
    }

    /*
     String str = eEstadoSolicitud.getText();
     if ((str.equals("c") ) || (str.equals("C") ) || (str.equals("e") ) || (str.equals("E") ))
     eEstadoSolicitud.setText(str.toUpperCase());
     else 
     eEstadoSolicitud.setText("");
        
     eFechaVerificación.setName("FECHA_VERRIF_REG.");
     
     */
}
