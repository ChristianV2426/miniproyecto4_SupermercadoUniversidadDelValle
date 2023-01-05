/*
    Archivo: Medico.java
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
    CLASE: Medico

    INTENCIÓN: Esta clase modela los médicos del sistema. Hereda los datos y métodos básicos de la clase Persona,
    y define los datos y métodos propios relacionados con los médicos.
    RELACIONES:
    -Es una persona y por lo tanto contiene una lista de citas asignadas. También por herencia implementa la interfaz Serializable.
*/


package co.edu.univalle.modelo;

import java.time.*;

public class Medico extends Persona{
    ServicioMedico servicioMedico;
    String especialidadMedica;
    String universidadEstudios;
    private static final int totalDatos = 10;

    public Medico(
        String tipoDocumento, Integer documento, String nombre, String apellidos,
        LocalDate fechaNacimiento, String sexo, ServicioMedico servicioMedico,
        String universidadEstudios, String telefono
        ){
        super(tipoDocumento, documento, nombre, apellidos, fechaNacimiento, sexo, telefono);
        this.servicioMedico = servicioMedico;
        this.especialidadMedica = servicioMedico.getNombreServicio();
        this.universidadEstudios = universidadEstudios;
    }

    public ServicioMedico getServicioMedico() {
        return servicioMedico;
    }

    public void setServicioMedico(ServicioMedico servicioMedico) {
        this.especialidadMedica = servicioMedico.getNombreServicio();
        this.servicioMedico = servicioMedico;
    }

    public String getEspecialidadMedica() {
        return especialidadMedica;
    }

    // La especialidad médica (que solo es el String) debe setearse unicamente vía setServicioMedico(ServicioMedico servicioMedico)
    /*
    public void setEspecialidadMedica(String especialidadMedica) {
        this.especialidadMedica = especialidadMedica;
    }
    */

    public String getUniversidadEstudios() {
        return universidadEstudios;
    }

    public void setUniversidadEstudios(String universidadEstudios) {
        this.universidadEstudios = universidadEstudios;
    }

    @Override
    public String toString(){
        String cadena = super.toString() + " " + nombre + " " + apellidos + " ";
        return cadena;
    }

    /**
     * Devuelve el número total de datos que se mostrarán por médico en la tabla de la interfaz gráfica del programa.
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
                return String.valueOf(edad.getYears());
            case 6:
                return String.valueOf(sexo);
            case 7:
                return String.valueOf(servicioMedico);
            case 8:
                return String.valueOf(universidadEstudios);
            case 9:
                return String.valueOf(telefono);
            default:
                return "";
        }
    }
}
