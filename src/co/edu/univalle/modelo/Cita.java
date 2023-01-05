/*
    Archivo: Cita.java
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
    CLASE: Cita

    INTENCIÓN: Esta clase definirá las citas médicas del sistema. En una cita médica están involucrados
    un número único de identificación de la cita, un afiliado, un servicio médico a prestarse, un médico, un consultorio donde se prestará el servicio médico, 
    y una fecha y hora. 

    RELACIONES:
    -Implementa la interfaz Listable, pues los datos de los objetos cita se desplegarán en la tabla dispuesta para ello en la interfaz gráfica del programa.
    -Implementa la interfaz Serializable para permitir la persistencia del sistema en archivos de tipo binario.
*/


package co.edu.univalle.modelo;

import java.io.Serializable;
import java.time.*;

public class Cita implements Listable, Serializable{
    public static final long serialVersionUID = 1L;
    private Integer idCita;
    private Afiliado afiliado;
    private ServicioMedico servicioMedico;
    private Medico medico;
    private Consultorio consultorio;
    private LocalDateTime fechaYHora;
    private static final int totalDatos = 7;

    public Cita(
        Integer idCita, Afiliado afiliado, ServicioMedico servicioMedico, Medico medico,
        Consultorio consultorio, LocalDateTime fechaYHora
        ){
        this.idCita = idCita;
        this.afiliado = afiliado;
        this.servicioMedico = servicioMedico;
        this.medico = medico;
        this.consultorio = consultorio;
        this.fechaYHora = fechaYHora;
    }

    public Integer getIdCita() {
        return idCita;
    }

    // La idea es que no se pueda modificar el id de la cita, este el número de identificación único de cada cita en el sistema
    /*
    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }
    */

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }

    public ServicioMedico getServicioMedico() {
        return servicioMedico;
    }

    public void setServicioMedico(ServicioMedico servicioMedico) {
        this.servicioMedico = servicioMedico;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    /**
     * Devuelve el número total de datos que se mostrarán por cita en la tabla de la interfaz gráfica del programa.
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
                return String.valueOf(idCita);
            case 1:
                return String.valueOf(afiliado);
            case 2:
                return String.valueOf(servicioMedico);
            case 3:
                return String.valueOf(medico);
            case 4:
                return String.valueOf(consultorio.getIdConsultorio() + " consultorio " +  consultorio.getNumeroConsultorio() + " sede " + consultorio.getSede());
            case 5:
                return String.valueOf(fechaYHora.getYear() + "-" + fechaYHora.getMonthValue() + "-" + fechaYHora.getDayOfMonth());
            case 6:
                return String.valueOf(fechaYHora.getHour() + ":" + fechaYHora.getMinute());
            default:
                return "";
        }
    }

    @Override
    public String toString(){
        String cadena = 
        "\nPaciente: " + afiliado.getTipoDocumento() + " " + afiliado.getDocumento() + " " + afiliado.getNombre() + " " + afiliado.getApellidos() +
        "\nMédico: " + medico.getTipoDocumento() + " " + medico.getDocumento() + " " + medico.getNombre() + " " + medico.getApellidos() + " " +
        "\nServicio Médico: " + servicioMedico.getNombreServicio() + 
        "\nConsultorio: " + consultorio.getNumeroConsultorio() + " en sede " + consultorio.getSede() + 
        "\nFecha y hora: " + fechaYHora;
        return cadena;
    }
}
