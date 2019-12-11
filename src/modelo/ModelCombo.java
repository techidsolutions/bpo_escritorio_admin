/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase para definir el modelos de los componentes tipo listas desplegables.
 * @author TECH ID SOLUTIONS
 */
public class ModelCombo {
    private String clave;
    private String valor;
    public ModelCombo(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }
    public String getClave() {
        return clave;
    }
    public String getValor() {
        return valor;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return valor; 
    }
}
