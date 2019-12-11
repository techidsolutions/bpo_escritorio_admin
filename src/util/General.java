/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import modelo.ComponenteFormulario;
import modelo.InfoExcel;
import modelo.ModelCombo;
import org.openswing.swing.client.DateControl;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class General {
    
    /**
     * 
     * @param eReferencia
     * @return 
     */
    private static ArrayList<ComponenteFormulario> generarLista(JTextField eReferencia){
        JComponent compTemp = (JComponent)eReferencia;
        String atributo;
        ArrayList<ComponenteFormulario> listaComponentes = new ArrayList<>();
        while (compTemp != null){
            atributo = compTemp.getName();
            if (atributo.endsWith("."))
                atributo = atributo.substring(0, atributo.length() - 1);
            if (compTemp instanceof JFormattedTextField) {
                    if (((JFormattedTextField)compTemp).getText().equals("   .  ")) 
                        listaComponentes.add(new ComponenteFormulario(atributo, ""));
                    else listaComponentes.add(new ComponenteFormulario(atributo, ((JFormattedTextField)compTemp).getText()));
            }else if (compTemp instanceof JTextField){
                listaComponentes.add(new ComponenteFormulario(atributo, ((JTextField)compTemp).getText()));
            } else if (compTemp instanceof JComboBox) {
                   listaComponentes.add(new ComponenteFormulario(atributo, ((ModelCombo)((JComboBox)compTemp).getSelectedItem()).getClave()));
            } else if (compTemp instanceof DateControl) {
                if (((DateControl)compTemp).getDate() != null)
                    listaComponentes.add(new ComponenteFormulario(atributo,  Utiles.convertirFechaYYYYMMDD( ((DateControl)compTemp).getDate())));
                else listaComponentes.add(new ComponenteFormulario(atributo,  ""));
            } else if (compTemp instanceof JTextArea){
                listaComponentes.add(new ComponenteFormulario(atributo, ((JTextArea)compTemp).getText()));
            }
                compTemp = (JComponent)compTemp.getNextFocusableComponent();
        }
       return listaComponentes;
    }
    
    /**
     * 
     * @return 
     */
    private static Integer indiceParaEscribir(){
        int fila = 4;
        try {
            Workbook workbook = Workbook.getWorkbook(new File("docs\\BPO__data entry.xls"));
            Sheet sheet = workbook.getSheet("Data Entry");
            Cell cell = sheet.getCell(0, fila); //columna,fila
            String cellData = cell.getContents();
            while (!cellData.equals("")){
                fila++;
                cell = sheet.getCell(1, fila); //columna,fila
                cellData = cell.getContents();
            }
            workbook.close();
        } catch (IOException | BiffException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return fila;
    }
    
    /**
     * 
     * @param eReferencia
     * @param listaTitulares
     * @param listaCargas
     * @return 
     */
    public static File writeExcel(JTextField eReferencia, ArrayList<ArrayList<ComponenteFormulario>> listaTitulares, ArrayList<ArrayList<ComponenteFormulario>> listaCargas) {
        ArrayList<ComponenteFormulario> listaComponentes = generarLista(eReferencia);
        File ficheroExcel = new File("docs\\BPO__data entry.xls");
        try {
            int fila = indiceParaEscribir();
            Workbook target_workbook = Workbook.getWorkbook(ficheroExcel);
            WritableWorkbook workbook = Workbook.createWorkbook(ficheroExcel, target_workbook);
            target_workbook.close();
            WritableSheet sheet;
            jxl.write.Label label;
            sheet = workbook.getSheet("Data Entry");
            int columna = 0;
            for (ComponenteFormulario componente : listaComponentes) {
                label = new jxl.write.Label(columna , fila, componente.getValor());
                sheet.addCell(label);
                columna++;
            }
            //Si tiene más de un titular
            if (listaTitulares.size() > 0){
                for (ArrayList<ComponenteFormulario> titular  : listaTitulares) {
                    for (ComponenteFormulario componenteFormulario : titular) {
                        label = new jxl.write.Label(columna , fila, componenteFormulario.getValor());
                        sheet.addCell(label);
                        columna++;
                    }
                }
            }
            
            //Se añaden las cargas
            columna = 89;
            if (listaCargas.size() > 0){
                ArrayList<ComponenteFormulario> carga = listaCargas.get(0);
                for (ComponenteFormulario componenteFormulario : carga) {
                    label = new jxl.write.Label(columna , fila, componenteFormulario.getValor());
                    sheet.addCell(label);
                    columna++;
                }
                if (listaCargas.size() > 1){ //Tiene más de una carga
                    for (int i = 1; i < listaCargas.size(); i++) {
                        carga = listaCargas.get(i);
                        fila++;
                        columna = 0;
                        for (ComponenteFormulario componente : listaComponentes) {
                            label = new jxl.write.Label(columna , fila, componente.getValor());
                            sheet.addCell(label);
                            columna++;
                        }
                        //Si tiene más de un titular
                        if (listaTitulares.size() > 0){
                            for (ArrayList<ComponenteFormulario> titular  : listaTitulares) {
                                for (ComponenteFormulario componenteFormulario : titular) {
                                    label = new jxl.write.Label(columna , fila, componenteFormulario.getValor());
                                    sheet.addCell(label);
                                    columna++;
                                }
                            }
                        }
                        columna = 89;
                        for (ComponenteFormulario componenteFormulario : carga) {
                            label = new jxl.write.Label(columna , fila, componenteFormulario.getValor());
                            sheet.addCell(label);
                            columna++;
                        }
                    }
                }
            }
            workbook.write();
            workbook.close();
        } catch (IOException | WriteException | BiffException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return ficheroExcel;
    }
    
    /**
     * 
     * @return 
     */
    public static File limpiarExcel() {
        File ficheroExcel = new File("docs\\BPO__data entry.xls");
        try {
            Workbook target_workbook = Workbook.getWorkbook(ficheroExcel);
            WritableWorkbook workbook = Workbook.createWorkbook(ficheroExcel, target_workbook);
            target_workbook.close();
            WritableSheet sheet;
            jxl.write.Label label;
            sheet = workbook.getSheet("Data Entry");
            int fila = 4;
            Cell cell = sheet.getCell(0, fila); //columna,fila
            String cellData = cell.getContents();
            String estado = "";
            while (!cellData.equals("")){
                for (int i = 0; i < 122; i++) {
                     label = new jxl.write.Label(i , fila, estado);
                    sheet.addCell(label);
                }
                fila++;
                cell = sheet.getCell(0, fila); //columna,fila
                cellData = cell.getContents();
            }
            workbook.write();
            workbook.close();
            
        } catch (IOException | WriteException | BiffException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return ficheroExcel;
    }
    
    /**
     * 
     * @return 
     */
    public static File writeExcelReferencia() {
        File ficheroExcel = new File("docs\\Listado 9651 asignados BC.xls");
        try {
            ArrayList<InfoExcel> listaDocErroneos = devolverDocumentosErronesReferencia();
            ArrayList<InfoExcel> listaDocHechos = devolverDocumentosHechosReferencia();
            Workbook target_workbook = Workbook.getWorkbook(ficheroExcel);
            WritableWorkbook workbook = Workbook.createWorkbook(ficheroExcel, target_workbook);
            target_workbook.close();
            WritableSheet sheet;
            jxl.write.Label label;
            sheet = workbook.getSheet("Archivos");
            int fila = 4;
            Cell cell = sheet.getCell(0, fila); //columna,fila
            String cellData = cell.getContents();
            String estado = "";
            while (!cellData.equals("")){
                Boolean esHecho = existeDoc(listaDocHechos, cellData.split("\\.")[0]);
                Boolean esErroneo = existeDoc(listaDocErroneos, cellData);
                if (esHecho && esErroneo){
                    estado = "Hecho y Erróneo";
                }else if (esHecho){
                    estado = "Hecho";
                }else if (esErroneo){ 
                    estado = "Erróneo"; 
                } else {
                }   
                label = new jxl.write.Label(2 , fila, estado);
                sheet.addCell(label);
                
                estado = "";
                fila++;
                cell = sheet.getCell(0, fila); //columna,fila
                cellData = cell.getContents();
            }
            workbook.write();
            workbook.close();
            
        } catch (IOException | WriteException | BiffException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return ficheroExcel;
    }
    
    /**
     * 
     * @return 
     */
    public static ArrayList<InfoExcel> devolverDocumentosErrones(){
        ArrayList<InfoExcel> listaDocErroneos = new ArrayList<>();
        File fichero = new File("docs\\eliminar.txt");
        try {
            Scanner lectura = new Scanner(fichero);
            while (lectura.hasNext()){
                String info = lectura.nextLine();
                listaDocErroneos.add(new InfoExcel(info));
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
        return listaDocErroneos;
    }
    
    /**
     * 
     * @return 
     */
    public static ArrayList<InfoExcel> devolverDocumentosErronesReferencia(){
        ArrayList<InfoExcel> listaDocErroneos = new ArrayList<>();
        File fichero = new File("docs\\eliminar.txt");
        try {
            Scanner lectura = new Scanner(fichero);
            while (lectura.hasNext()){
                String info = lectura.nextLine();
                listaDocErroneos.add(new InfoExcel(info.split("-")[0]));
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
        return listaDocErroneos;
    }
    
    /**
     * 
     * @param listaDocHechos
     * @param valor
     * @return 
     */
    private static Boolean existeDoc(ArrayList<InfoExcel> listaDocHechos, String valor){
        Boolean existe = false;
        for (InfoExcel listaDocHecho : listaDocHechos) {
            if (listaDocHecho.getNombre().equalsIgnoreCase(valor))
                return true;
        }
        return existe;
    }
    
    /**
     * 
     * @return 
     */
    public static ArrayList<InfoExcel> devolverDocumentosHechos(){
        ArrayList<InfoExcel> listaDocHechos = new ArrayList<>();
        String cellData;
        try{
            Workbook workbook = Workbook.getWorkbook(new File("docs\\TECH_Reporte_OCR_20180202v2.xls"));
            Sheet sheet = workbook.getSheet("Data Entry");
            int fila = 4;
            Cell cell = sheet.getCell(1, fila); 
            cellData = cell.getContents();
            while (!cellData.equals("")){
                if (!existeDoc(listaDocHechos, cellData)){
                    listaDocHechos.add(new InfoExcel(cellData));
                }    
                fila++;
                cell = sheet.getCell(1, fila); //columna,fila
                cellData = cell.getContents();
            }
            workbook.close();
        }
        catch(IOException | BiffException ex){
        }
        return listaDocHechos;
    }
    
    public static ArrayList<InfoExcel> devolverDocumentosHechosReferencia(){
        ArrayList<InfoExcel> listaDocHechos = new ArrayList<>();
        String cellData;
        try{
            Workbook workbook = Workbook.getWorkbook(new File("docs\\TECH_Reporte_OCR_20180202v2.xls"));
            Sheet sheet = workbook.getSheet("Data Entry");
            int fila = 4;
            Cell cell = sheet.getCell(0, fila); //columna,fila
            cellData = cell.getContents();
            while (!cellData.equals("")){
                if (!existeDoc(listaDocHechos, cellData)){
                    listaDocHechos.add(new InfoExcel(cellData));
                }    
                fila++;
                cell = sheet.getCell(0, fila); //columna,fila
                cellData = cell.getContents();
            }
            workbook.close();
        }
        catch(IOException | BiffException ex){
        }
        return listaDocHechos;
    }
}
