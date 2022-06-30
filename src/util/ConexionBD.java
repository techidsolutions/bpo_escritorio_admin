/*
 *  To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Clase que registra el driver y realiza la conexión.
 * @version 1.0
 * @author TECH ID SOLUTIONS
 */

public class ConexionBD {
    /**
     * nombreHost: Variable que almacena el nombre del Host a donde se hace la conexión
     * usuario: Variable que almacena el nombre de usuario.
     * clave: Variable que almacena la contraseña
     * cadenaConexion: Variable que almacena la cadena de conexión.
     */
    private String nombreHost; 
    private String usuario; 
    private String clave;
    private String cadenaConexion;

    ConexionBD(String ec234255134200euwest1computeamazonawscom, String lvanmvjveklsei, String a9f2638f12a6efd4024fd25c971084d46d4a152be) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Método que devuelve una Conexión.
     * @return Conexión (Connection)
     * @throws java.sql.SQLException Si existe algún error
     */
    public Connection Conexion()throws java.sql.SQLException {
        try {
           Class.forName("com.mysql.jdbc.Driver").newInstance(); // Se registra el driver
         }
         catch (ClassNotFoundException | InstantiationException | IllegalAccessException e ){ 
              JOptionPane.showMessageDialog(null, "Acción fallida: " + e.getMessage()); 
         } 
       cadenaConexion = "jdbc:mysql://" + this.nombreHost + "/liferay"; // Se crea la cadena de conexión
       return DriverManager.getConnection(cadenaConexion, this.usuario, this.clave); 
    }
    
    /**
     * 
     * @return
     * @throws java.sql.SQLException 
     */
    public Connection conexionAccess()throws java.sql.SQLException {
        try {
           Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         }
         catch (Exception e ){ 
              JOptionPane.showMessageDialog(null, "Acción fallida: " + e.getMessage()); 
         } 
       Connection conexion = DriverManager.getConnection("jdbc:odbc:bd_bpo");
       return conexion;
    }
//    public Connection Conexion_postgresql()throws java.sql.SQLException {
//        try {
//           Class.forName("org.postgresql.Driver"); // Se registra el driver
//         }
//         catch (ClassNotFoundException e ){ 
//             
//         } 
//       cadenaConexion = "jdbc:postgresql://" + this.nombreHost + ":5432/d2j83ml2qjqnu0" + "?sslmode=require"; // Se crea la cadena de conexiÃ³n
//       return DriverManager.getConnection(cadenaConexion, this.usuario, this.clave); 
//    }
}
