/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class Poblacion implements Comparable<Poblacion>{
    private String codigo;
    private String nombre;
    private String codigoProvincia;
    public Poblacion(String codigo, String nombre, String codigoProvincia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codigoProvincia = codigoProvincia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigoProvincia() {
        return codigoProvincia;
    }
    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }
    @Override
    public String toString() {
        return this.nombre;
    }
    @Override
    public int compareTo(Poblacion o) {
         if(o.getNombre().compareTo(nombre) > 0){
            return -1;
        }else if(o.getNombre().compareTo(nombre) > 0){
            return 0;
        }else{
            return 1;
        }
    }
}
