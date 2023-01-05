/*
    Archivo: ServicioMedico.java
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
    CLASE: ServicioMedico

    INTENCIÓN: Esta clase definirá los servicios médicos que se prestarán en la entidad de salud. 
    En un servicio médico están involucrados el número de identificación único del servicio, 
    nombre del servicio médico y una breve descripción del mismo. 

    RELACIONES:
    -Implementa la interfaz Listable, pues los datos de los objetos servicio médico se desplegarán en la tabla dispuesta para ello en la interfaz gráfica del programa.
    -Implementa la interfaz Serializable para permitir la persistencia del sistema en archivos de tipo binario.
*/

package co.edu.univalle.modelo;

import java.io.Serializable;

public class ServicioMedico implements Listable, Serializable{
    public static final long serialVersionUID = 1L;
    private Integer idServicio;
    private String nombreServicio;
    private String descripcionServicio;
    private static final int totalDatos = 3;
    
    public ServicioMedico(
        Integer idServicio, String nombreServicio, String descripcionServicio
        ){
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.descripcionServicio = descripcionServicio;
    }

    public Integer getIdServicio(){
        return idServicio;
    }

    // La idea es que no se pueda modificar el id del servicio, este el número de identificación único de cada servicio en el sistema
    /*
    public void setIdServicio(Integer idServicio){
        this.idServicio = idServicio;
    }
    */

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    /**
     * Devuelve el número total de datos que se mostrarán por servicio médico en la tabla de la interfaz gráfica del programa.
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
                return String.valueOf(idServicio);
            case 1:
                return String.valueOf(nombreServicio);
            case 2:
                return String.valueOf(descripcionServicio);
            default:
                return "";
        }
    }
    
    @Override
    public String toString() {
        String cadena = idServicio + " " + nombreServicio;
        return cadena;
    }
}
