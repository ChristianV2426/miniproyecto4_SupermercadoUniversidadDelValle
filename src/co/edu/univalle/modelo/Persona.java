/*
    Archivo: Persona.java
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
    CLASE: Persona

    INTENCIÓN: Esta clase definirá los valores y métodos básicos de las personas involucradas en el sistema.
    Inicialmente está pensada para que afiliados y médicos hereden de esta clase, pero pensando en extensibilidad futura
    otras personas como funcionarios / trabajadores, auditores, etc. también podrían hacer uso de la herencia con esta clase. 

    RELACIONES:
    -Contiene una lista de citas asignada.
    -Implementa la interfaz Listable, pues los datos de los objetos personas se desplegarán en la tabla dispuesta para ello en la interfaz gráfica del programa.
    -Implementa la interfaz Serializable para permitir la persistencia del sistema en archivos de tipo binario.
*/

package co.edu.univalle.modelo;

import java.io.Serializable;
import java.time.*;
import java.util.*;

public abstract class Persona implements Listable, Serializable{
    public static final long serialVersionUID = 1L;
    protected String tipoDocumento; // C.C., T.I., C.E.
    protected Integer documento;
    protected String nombre;
    protected String apellidos;
    protected LocalDate fechaNacimiento;
    protected Period edad;
    protected String sexo;
    protected String telefono;
    protected HashMap<Integer, Cita> citasAsignadas;

    public Persona(
        String tipoDocumento, Integer documento, String nombre, String apellidos,
        LocalDate fechaNacimiento, String sexo, String telefono
        ){
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.telefono = telefono;
        this.citasAsignadas = new HashMap<Integer, Cita>();

        calcularEdad();
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    public Integer getDocumento() {
        return documento;
    }

    // La idea es que no se pueda modificar el número de documento de la persona, este el número de identificación único de cada persona en el sistema.
    /*
    public void setDocumento(Integer documento) {
        this.documento = documento;
    }
    */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Period getEdad(){
        return edad;
    }

    public int getEdadEnAños(){
        return edad.getYears();
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public HashMap<Integer, Cita> getCitasAsignadas() {
        return citasAsignadas;
    }

    /**
     * Retorna una lista con todas las citas asignadas a la persona. 
     * Si la persona no tiene citas asignadas, entonces retorna una lista vacía.
     */
    public List<Cita> getListaCitasAsignadas(){
        List<Cita> listaCitasAsignadas = new ArrayList<>();
        
        if(!citasAsignadas.isEmpty()){
            for(Map.Entry<Integer, Cita> pareja : citasAsignadas.entrySet())
                listaCitasAsignadas.add(pareja.getValue());
        }
        return listaCitasAsignadas;
    }

    // En principio no se debería poder asignar directamente un HashMap de citas asignadas a la persona
    // Se deben usar los métodos propuestos para agregar/eliminar/verificar citas, etc.
    /*
    public void setCitasAsignadas(HashMap<Integer, Cita> citasAsignadas) {
        this.citasAsignadas = citasAsignadas;
    }
    */

    /**
     * Calcula la edad de la persona y la guarda en la variable edad de tipo Period.
     */
    public void calcularEdad(){
        this.edad = Period.between(fechaNacimiento, LocalDate.now());
    }

    @Override
    public String toString(){
        String cadena = String.valueOf(documento);
        return cadena;
    }

    /**
     * Agrega una cita en la agenda de la persona.
     * Agrega la cita utilizando el id de la cita como llave, y el objeto de tipo Cita como valor.
     * Antes de usar este método verificar que la persona tenga disponibilidad en la fecha y hora de la cita y que no tenga ya agendada la misma cita, con los métodos tieneDisponibilidad(LocalDateTime fechaYhoraCitaAVerificar) y tieneEstaCitaAgendada(Cita cita)
     */
    public void agregarCita(Cita cita){
        citasAsignadas.put((Integer)cita.getIdCita(), cita);
    }

    /**
     * Elimina una cita de la agenda de la persona.
     * Antes de usar este método verificar que la persona efectivamente tenga la cita agendada, utilizando el método tieneEstaCitaAgendada(Cita cita).
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
     * Retorna true si la persona está disponible en la fecha y hora a verificar (es decir, si no tiene una cita ya agendada en esa fecha y hora).
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