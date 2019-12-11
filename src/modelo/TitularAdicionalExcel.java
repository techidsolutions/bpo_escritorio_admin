/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 * Clase que manipula la información de los titulares.
 * @author TECH ID SOLUTIONS
 */
public class TitularAdicionalExcel {
    private String nombreTitular;
    private String primerApellido;
    private String segundoApellido;
    private String documento;
    private String regimen;
    private String tituloPropiedad;
    private String fechaEscritura;
    private String tituloProtocolo;
    private String notario;
    private String numeroInscripcion;
    private String fechaInscripcion;
    private String tipoParticipacion;
    private String porcientoParticipacion;
    private String enPropiedad;
    private String tipoDocumento;
    public TitularAdicionalExcel(String nombreTitular, String primerApellido, String segundoApellido, String documento, String regimen, String tituloPropiedad, String fechaEscritura, String tituloProtocolo, String notario, String numeroInscripcion, String fechaInscripcion, String tipoParticipacion, String porcientoParticipacion, String enPropiedad, String tipoDocumento) {
        this.nombreTitular = nombreTitular;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.documento = documento;
        this.regimen = regimen;
        this.tituloPropiedad = tituloPropiedad;
        this.fechaEscritura = fechaEscritura;
        this.tituloProtocolo = tituloProtocolo;
        this.notario = notario;
        this.numeroInscripcion = numeroInscripcion;
        this.fechaInscripcion = fechaInscripcion;
        this.tipoParticipacion = tipoParticipacion;
        this.porcientoParticipacion = porcientoParticipacion;
        this.enPropiedad = enPropiedad;
        this.tipoDocumento = tipoDocumento;
    }
    public String getDocumento() {
        return documento;
    }
    public String getFechaEscritura() {
        return fechaEscritura;
    }
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }
    public String getNombreTitular() {
        return nombreTitular;
    }
    public String getNotario() {
        return notario;
    }
    public String getNumeroInscripcion() {
        return numeroInscripcion;
    }
    public String getPorcientoParticipacion() {
        return porcientoParticipacion;
    }
    public String getPrimerApellido() {
        return primerApellido;
    }
    public String getRegimen() {
        return regimen;
    }
    public String getSegundoApellido() {
        return segundoApellido;
    }
    public String getTipoParticipacion() {
        return tipoParticipacion;
    }
    public String getTituloPropiedad() {
        return tituloPropiedad;
    }
    public String getTituloProtocolo() {
        return tituloProtocolo;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public void setFechaEscritura(String fechaEscritura) {
        this.fechaEscritura = fechaEscritura;
    }
    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }
    public void setNotario(String notario) {
        this.notario = notario;
    }
    public void setNumeroInscripcion(String numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }
    public void setPorcientoParticipacion(String porcientoParticipacion) {
        this.porcientoParticipacion = porcientoParticipacion;
    }
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    public void setTipoParticipacion(String tipoParticipacion) {
        this.tipoParticipacion = tipoParticipacion;
    }
    public void setTituloPropiedad(String tituloPropiedad) {
        this.tituloPropiedad = tituloPropiedad;
    }
    public void setTituloProtocolo(String tituloProtocolo) {
        this.tituloProtocolo = tituloProtocolo;
    }
    public String getEnPropiedad() {
        return enPropiedad;
    }
    public void setEnPropiedad(String enPropiedad) {
        this.enPropiedad = enPropiedad;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
