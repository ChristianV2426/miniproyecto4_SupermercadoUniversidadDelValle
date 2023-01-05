/*
    Archivo: Consultorio.java
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
    CLASE: Consultorio

    INTENCIÓN: Esta clase definirá los consultorios donde se prestan los servicios médicos en la entidad de salud.
    Un consultorio tiene información relacionada con su número de identificación único, la sede de la entidad de salud 
    en la que se encuentra ubidado el consultorio, el número del consultorio y el servicio médico que se puede prestar en el consultorio.

    RELACIONES:
    -Contiene un servicio médico asociado.
    -Implementa la interfaz Listable, pues los datos de los objetos cita se desplegarán en la tabla dispuesta para ello en la interfaz gráfica del programa.
    -Implementa la interfaz Serializable para permitir la persistencia del sistema en archivos de tipo binario.
*/


package co.edu.univalle.modelo;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public class Consultorio implements Listable, Serializable{
    public static final long serialVersionUID = 1L;
    private Integer idConsultorio;
    private String sede;
    private Integer numeroConsultorio;
    private ServicioMedico servicioMedico;
    private String especialidadMedica;
    private HashMap<Integer, Cita> citasAsignadas;
    private static final int totalDatos = 4;
    
    public Consultorio(
        Integer idConsultorio, String sede, Integer numeroConsultorio, ServicioMedico servicioMedico
        ){
            this.idConsultorio = idConsultorio;
            this.sede = sede;
            this.numeroConsultorio = numeroConsultorio;
            this.servicioMedico = servicioMedico;
            this.especialidadMedica = servicioMedico.getNombreServicio();
            this.citasAsignadas = new HashMap<Integer, Cita>();
        }

    public Integer getIdConsultorio() {
        return idConsultorio;
    }

    // La idea es que no se pueda modificar el id del consultorio, este el número de identificación único de cada consultorio en el sistema
    /*
    public void setIdConsultorio(Integer idConsultorio) {
        this.idConsultorio = idConsultorio;
    }
    */

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Integer getNumeroConsultorio() {
        return numeroConsultorio;
    }

    public void setNumeroConsultorio(Integer numeroConsultorio) {
        this.numeroConsultorio = numeroConsultorio;
    }

    public ServicioMedico getServicioMedico() {
        return servicioMedico;
    }

    public void setServicioMedico(ServicioMedico servicioMedico) {
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

    public HashMap<Integer, Cita> getCitasAsignadas() {
        return citasAsignadas;
    }

    // En principio no se debería poder asignar directamente un HashMap de citas asignadas al consultorio
    // Se deben usar los métodos propuestos para agregar/eliminar/verificar citas, etc.
    /*
    public void setCitasAsignadas(HashMap<Integer, Cita> citasAsignadas) {
        this.citasAsignadas = citasAsignadas;
    }
    */

    /**
     * Devuelve el número total de datos que se mostrarán por consultorio en la tabla de la interfaz gráfica del programa.
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
                return String.valueOf(idConsultorio);
            case 1:
                return String.valueOf(numeroConsultorio);
            case 2:
                return String.valueOf(sede);
            case 3:
                return String.valueOf(servicioMedico);
            default:
                return "";
        }
    }

    @Override
    public String toString(){
        String cadena =
        idConsultorio + 
        " consultorio " + numeroConsultorio +
        " sede " + sede;
        
        return cadena;
    }

    /**
     * Agrega una cita en la agenda del consultorio.
     * Agrega la cita utilizando el id de la cita como llave, y el objeto de tipo Cita como valor.
     * Antes de usar este método verificar que el consultorio tenga disponibilidad en la fecha y hora de la cita y que no tenga ya agendada la misma cita, con los métodos tieneDisponibilidad(LocalDateTime fechaYhoraCitaAVerificar) y tieneEstaCitaAgendada(Cita cita)
     */
    public void agregarCita(Cita cita){
        citasAsignadas.put((Integer)cita.getIdCita(), cita);
    }

    /**
     * Elimina una cita de la agenda del consultorio.
     * Antes de usar este método verificar que el consultorio efectivamente tenga la cita agendada, utilizando el método tieneEstaCitaAgendada(Cita cita).
     */
    public void eliminarCita(Cita cita){
        citasAsignadas.remove((Integer)cita.getIdCita());
    }

    /**
     * Retorna true si la cita pasada como argumento se encuentra en la agenda de la persona.
     */
    public boolean tieneEstaCitaAgendada(Cita cita){
        if(citasAsignadas.containsKey((Integer)cita.getIdCita()))
            return true;
        else
            return false;
    }

    /**
     * Retorna true si el consultorio está disponible en la fecha y hora a verificar (es decir, si no tiene una cita ya agendada en esa fecha y hora).
     */
    public boolean tieneDisponibilidadParaCita(LocalDateTime fechaYhoraCitaAVerificar){
        if(citasAsignadas == null || citasAsignadas.isEmpty())
            return true;

        for(Map.Entry<Integer, Cita> cita : citasAsignadas.entrySet())
            if(cita.getValue().getFechaYHora().equals(fechaYhoraCitaAVerificar))
                return false;
                
        return true;
    }
}
