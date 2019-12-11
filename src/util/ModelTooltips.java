/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author TECH ID SOLUTIONS
 */
public class ModelTooltips implements Comparable<ModelTooltips>{
    private String codigo;
    private String nombre;
    public ModelTooltips(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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
    @Override
    public String toString() {
        return this.nombre;
    }
    @Override
    public int compareTo(ModelTooltips o) {
         if(o.getNombre().compareTo(nombre) > 0){
            return -1;
        }else if(o.getNombre().compareTo(nombre) > 0){
            return 0;
        }else{
            return 1;
        }
    }
}
