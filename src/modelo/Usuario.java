/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 * Clase que manipula la información de los usuarios.
 * @author TECH ID SOLUTIONS
 */
public class Usuario {
    private String nombreCompleto;
    private String nombre;
    private String clave;
    public Usuario(String nombre, String clave, String nombreCompleto) {
        this.nombre = nombre;
        this.clave = clave;
        this.nombreCompleto = nombreCompleto;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
