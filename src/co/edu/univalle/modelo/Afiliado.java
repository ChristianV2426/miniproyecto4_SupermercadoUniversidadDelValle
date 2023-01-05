/*
    Archivo: Afiliado.java
    Fundamentos de Programación Orientada a Eventos - 750014C Grupo 01
    Proyecto 3 - Servicio de Salud Universidad del Valle

    Autores: 
    Juan Camilo Narvaez Tascon - juan.narvaez.tascon@correounivalle.edu.co - 2140112-3743
    Christian David Vargas Gutiérrez - vargas.christian@correounivalle.edu.co - 2179172-3743

    Profesor:
    Ing. M.Sc. Luis Yovany Romo Portilla

    Licencia: GNU-GPL
*/

/**
    CLASE: Afiliado

    INTENCIÓN: Esta clase modela los afiliados al sistema. Hereda los datos y métodos básicos de la clase Persona,
    y define los datos y métodos propios relacionados con los afiliados.
    RELACIONES:
    -Es una persona y por lo tanto contiene una lista de citas asignadas. También por herencia implementa las interfaces Listable y Serializable.
*/

package co.edu.univalle.modelo;

import java.time.*;

public class Afiliado extends Persona{
    private String ciudadNacimiento;
    private String paisNacimiento;
    private String grupoSanguineo;
    private String rh;
    private String estadoCivil;
    private String direccion;
    private String ciudadResidencia;
    private String tipoRegimenSalud;
    private String eps;
    private static final int totalDatos = 17;
    
    public Afiliado(
        String tipoDocumento, Integer documento, String nombre, String apellidos,
        LocalDate fechaNacimiento, String ciudaNacimiento, String paisNacimiento, String sexo, 
        String grupoSanguineo, String rh, String estadoCivil, String direccion, 
        String ciudadResidencia, String telefono, String tipoRegimenSalud, String eps
        ){
        super(tipoDocumento, documento, nombre, apellidos, fechaNacimiento, sexo, telefono);
        this.ciudadNacimiento = ciudaNacimiento;
        this.paisNacimiento = paisNacimiento;
        this.grupoSanguineo = grupoSanguineo;
        this.rh = rh;
        this.estadoCivil = estadoCivil;
        this.direccion = direccion;
        this.ciudadResidencia = ciudadResidencia;
        this.tipoRegimenSalud = tipoRegimenSalud;
        this.eps = eps;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public void setCiudadResidencia(String ciudadResidencia) {
        this.ciudadResidencia = ciudadResidencia;
    }

    public String getTipoRegimenSalud() {
        return tipoRegimenSalud;
    }

    public void setTipoRegimenSalud(String tipoRegimenSalud) {
        this.tipoRegimenSalud = tipoRegimenSalud;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    /**
     * Devuelve el número total de datos que se mostrarán por afiliado en la tabla de la interfaz gráfica del programa.
     */
    public int getTotalDatos(){
        return totalDatos;
    }

    /**
     * Devuelve un string con el valor de uno de los datos que se muestran en la tabla de la interfaz gráfica del programa.
     * Especificamente devuelve el string del dato i-ésimo, que se ingresa como parámetro del método.
     */
    public String getDato(int idDato){
        switch(idDato){
            case 0:
                return String.valueOf(tipoDocumento);
            case 1:
                return String.valueOf(documento);
            case 2:
                return String.valueOf(nombre);
            case 3:
                return String.valueOf(apellidos);
            case 4:
                return String.valueOf(fechaNacimiento);
            case 5:
                return String.valueOf(ciudadNacimiento);
            case 6:
                return String.valueOf(paisNacimiento);
            case 7:
                return String.valueOf(edad.getYears());
            case 8:
                return String.valueOf(sexo);
            case 9:
                return String.valueOf(grupoSanguineo);
            case 10:
                return String.valueOf(rh);
            case 11:
                return String.valueOf(estadoCivil);
            case 12:
                return String.valueOf(direccion);
            case 13:
                return String.valueOf(ciudadResidencia);
            case 14:
                return String.valueOf(telefono);
            case 15:
                return String.valueOf(tipoRegimenSalud);
            case 16:
                return String.valueOf(eps);
            default:
                return "";
        }
    }
}
