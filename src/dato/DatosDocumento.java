/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dato;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class DatosDocumento {
    
    public static void Insertar(Connection conexion, String nombre, String estado, String directorioPadre, String usuario){
        try {
            (conexion.createStatement()).executeUpdate("INSERT INTO bpo_documento (nombre,estado,directorio_padre,usuario)  VALUE ("+ nombre + "," + estado + "," + directorioPadre + "," + usuario + ")");
        } catch (SQLException ex) {
            Logger.getLogger(DatosDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
