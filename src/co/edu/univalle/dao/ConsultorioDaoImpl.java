/*
    Archivo: ConsultorioDaoImpl.java
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
    Clase: ConsultorioDaoImpl

    INTENCIÓN: Esta es la clase que define la lógica del Data Access Object para los objetos de tipo onsultorio.

    RELACIONES:
    -Implementa la interfaz ConsultorioDao
*/


package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.io.Serializable;
import java.util.*;

public class ConsultorioDaoImpl implements ConsultorioDao, Serializable{
    public static final long serialVersionUID = 1L;
    private HashMap<Integer, Consultorio> consultorios;

    public ConsultorioDaoImpl(){
        this.consultorios = new HashMap<>();
    }

    /** 
     * Añade un consultorio al HashMap de consultorios.
     * Unicamente añade al consultorio y retorna true, si el consultorio no se encuentra registrado. 
     */
    @Override
    public boolean añadir(Consultorio consultorio) {
        if(consultorios.containsKey(consultorio.getIdConsultorio()))
            return false;

        consultorios.put(consultorio.getIdConsultorio(), consultorio);
        return true;
    }

    @Override
    public boolean elementoPresente(Integer id){
        if(consultorios.containsKey(id))
            return true;
        return false;
    }

    /**
     * Devuelve el consultorio con el número de id ingresado como parámetro.
     * Si no hay un consultorio registrado con ese número de id, entonces devuelve null.
     */
    @Override
    public Consultorio obtenerElemento(Integer id) {
        if (consultorios.containsKey(id)) 
            return consultorios.get(id);

        return null;
    }

    /**
     * Devuelve una lista con objetos de tipo consultorio, correspondiente a todos los consultorios del sistema. 
     * Si no hay consultorios registrados devuelve una lista vacía. 
     */
    @Override
    public List<Consultorio> obtenerLista() {
        List<Consultorio> listaConsultorios = new ArrayList<>();

        if(!consultorios.isEmpty()){
            for(Map.Entry<Integer, Consultorio> pareja : consultorios.entrySet())
                listaConsultorios.add(pareja.getValue());
        }

        return listaConsultorios;
    }

    /**
     * Actualiza todos los datos de un consultorio. Hay que ingresar como argumento el id del consultorio a actualizar, y un objeto de tipo consultorio con los nuevos datos.  
     * Devuelve true cuando se realiza la actualización de datos. 
     * Devuelve false cuando los números de id no coinciden, y por tanto no es posible realizar la actualización. 
     */
    @Override
    public boolean actualizar(Integer id, Consultorio consultorio) {
        if(id.equals(consultorio.getIdConsultorio())){
            consultorios.replace(id, consultorio);
            return true;
        }
        return false;
    }

    /**
     * Elimina el consultorio cuyo número de documento es el id pasado como argumento de la función.
     * Retorna true cuando el consultorio se encuentra registrado y es eliminado satisfactoriamente. 
     * Retorna false cuando el consultorio no se encuentra en los registros y por tanto no puede ser eliminado.
     */
    @Override
    public boolean eliminar(Integer id) 
    {
        if(consultorios.containsKey(id))
        {
            consultorios.remove(id);
            return true;
        }

        return false;
    }

    /**
     * Devuelve una lista con todos los consultorios que tengan citas asignadas. 
     * Si no hay consultorios registrados, o no hay consultorios con citas registradas, devuelve una lista vacía. 
     */
    @Override
    public List<Consultorio> obtenerConsultoriosConCita() {
        List<Consultorio> listaConsultoriosConCita = new ArrayList<>();

        if(!consultorios.isEmpty()){
            for(Map.Entry<Integer, Consultorio> pareja : consultorios.entrySet()){
                if(!pareja.getValue().getCitasAsignadas().isEmpty())
                    listaConsultoriosConCita.add(pareja.getValue());
            }   
        }
        return listaConsultoriosConCita;
    }

    /**
     * Devuelve una lista con todos los médicos de cierta especialidad que se pasa como argumento. 
     * Si no hay médicos registrados, o médicos con la especialidad buscada, devuelve una lista vacía. 
     */
    @Override
    public List<Consultorio> obtenerConsultoriosConEspecialidad(ServicioMedico servicioMedico){
        List<Consultorio> listaconsultoriosConEspecialidad = new ArrayList<>();
        String nombreEspecialidad = servicioMedico.getNombreServicio();
        
        if(!consultorios.isEmpty()){
            for(Map.Entry<Integer, Consultorio> pareja : consultorios.entrySet())
                if(pareja.getValue().getEspecialidadMedica().equals(nombreEspecialidad))
                    listaconsultoriosConEspecialidad.add(pareja.getValue());
        }
        return listaconsultoriosConEspecialidad;
    }

    public String[] arregloConsultoriosConEspecialidad(ServicioMedico servicioMedico){
        List<Consultorio> listaConsultorios = obtenerConsultoriosConEspecialidad(servicioMedico);
        int tamanoArreglo = listaConsultorios.size();
        String[] arregloConsultorios = new String[tamanoArreglo];

        if(!listaConsultorios.isEmpty()){
            int contador = 0;
            for(Consultorio consultorio : listaConsultorios){
                arregloConsultorios[contador] = String.valueOf(consultorio);
                contador++;
            }
        }
        return arregloConsultorios;
    }

}
