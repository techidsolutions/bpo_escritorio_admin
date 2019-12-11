/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Clase con la funcionalidad de exportar los datos a documentos a Excel.
 * @author TECH ID SOLUTIONS
 */
public class ExportarExcel {
    private final File fichero;
    private final List<JTable> tabla;
    private final List<String> nombreFicheros;

    public ExportarExcel(File file, List<JTable> tabla, List<String> nombreFicheros) throws Exception {
        this.fichero = file;
        this.tabla = tabla;
        this.nombreFicheros = nombreFicheros;
        if (nombreFicheros.size() != tabla.size()) {
            throw new Exception ("Error");
        }
    }
    
    /**
     * Método que exporta los datos a documento Excel.  
     * @return Verdadero o falso.
     */
    public boolean export() {
        try {
            DataOutputStream out=new DataOutputStream (new FileOutputStream(fichero));
            WritableWorkbook w = Workbook.createWorkbook(out);
            for (int index = 0; index < tabla.size(); index++){
                JTable table = tabla.get(index);
                WritableSheet s = w.createSheet(nombreFicheros.get(index),0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                      for (int j = 0; j < table.getRowCount(); j++) {
                            Object titulo = table.getColumnName(i);
                            s.addCell(new Label(i+1, j+1, String.valueOf(titulo)));
                       }
                }
                for (int i = 0; i < table.getColumnCount(); i++) {
                      for (int j = 0; j < table.getRowCount(); j++) {
                            Object object = table.getValueAt(j, i);
                            s.addCell(new Label(i+1, j+2, String.valueOf(object)));
                      }
                }
            }
            w.write();
            w.close();
            return true;
        } 
        catch (IOException | WriteException e) {
            return false;
        }
    }
}
