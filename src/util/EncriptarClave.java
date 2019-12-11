/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Clase para encriptar claves con el método MD5
 * @author TECH ID SOLUTIONS.
 */
public class EncriptarClave {
    
    /**
     * Método que encripta una cadena con el método MD5
     * @param password Contraseña
     * @return Contraseña encriptada
     * @throws NoSuchAlgorithmException 
     */
    public static String encriptarMD5(String password) throws NoSuchAlgorithmException {
           char[] HEXADECIMAL = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                                  '9', 'a', 'b', 'c', 'd', 'e', 'f' };
           MessageDigest md = MessageDigest.getInstance("MD5");
           byte[] bytes = md.digest(password.getBytes());
           StringBuilder sb = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++){
              int low = (int)(bytes[i] & 0x0f);
              int high = (int)((bytes[i] & 0xf0) >> 4);
              sb.append(HEXADECIMAL[high]);
              sb.append(HEXADECIMAL[low]);
           }
           password=sb.toString();
           return password;
        }
}
