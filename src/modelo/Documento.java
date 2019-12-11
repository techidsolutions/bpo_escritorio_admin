/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase para manipular la ionformación de los documentos.
 * @author TECH ID SOLUTIONS
 */
public class Documento {
    private String nombre;
    private String directorioRaiz;
    private String estado;
    private String fecha;
    private String usuario;
    private String tipoDocumento; //Registral(R), Carga(C) o Registral y Carga (RC)
    public Documento(String nombre,String tipoDocumento, String estado, String usuario, String fecha,  String directorioRaiz) {
        this.nombre = nombre;
        this.directorioRaiz = directorioRaiz;
        this.estado = estado;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipoDocumento = tipoDocumento;
    }
    public String getDirectorioRaiz() {
        return directorioRaiz;
    }
    public String getEstado() {
        return estado;
    }
    public String getFecha() {
        return fecha;
    }
    public String getNombre() {
        return nombre;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setDirectorioRaiz(String directorioRaiz) {
        this.directorioRaiz = directorioRaiz;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
