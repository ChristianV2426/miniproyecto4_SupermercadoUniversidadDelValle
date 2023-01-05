/*
    Archivo: CitaDaoImpl.java
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
    Clase: CitaDaoImpl

    INTENCIÓN: Esta es la clase que define la lógica del Data Access Object para los objetos de tipo cita.

    RELACIONES:
    -Implementa la interfaz CitaDao
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.io.Serializable;
import java.util.*;

public class CitaDaoImpl implements CitaDao, Serializable{
    public static final long serialVersionUID = 1L;
    private HashMap<Integer, Cita> citas;

    public CitaDaoImpl(){
        this.citas = new HashMap<>();
    }

    /** 
     * Añade una cita al HashMap de citas.
     * Unicamente añade al cita y retorna true, si la cita no se encuentra registrada. 
     */
    @Override
    public boolean añadir(Cita cita) {
        if(citas.containsKey(cita.getIdCita()))
            return false;

        citas.put(cita.getIdCita(), cita);
        return true;
    }

    @Override
    public boolean elementoPresente(Integer id){
        if(citas.containsKey(id))
            return true;
        return false;
    }

    /**
     * Devuelve la cita con el número de id ingresado como parámetro.
     * Si no hay una cita registrada con ese número de id, entonces devuelve null.
     */
    @Override
    public Cita obtenerElemento(Integer id) {
        if (citas.containsKey(id)) 
            return citas.get(id);

        return null;
    }

    /**
     * Devuelve una lista con objetos de tipo cita, correspondiente a todas las citas del sistema. 
     * Si no hay citas registradas devuelve una lista vacía. 
     */
    @Override
    public List<Cita> obtenerLista() {
        List<Cita> listaCitas = new ArrayList<>();

        if(!citas.isEmpty()){
            for(Map.Entry<Integer, Cita> pareja : citas.entrySet())
                listaCitas.add(pareja.getValue());
        }

        return listaCitas;
    }

    /**
     * Actualiza todos los datos de una cita. Hay que ingresar como argumento el id de la cita a actualizar, y un objeto de tipo cita con los nuevos datos.  
     * Devuelve true cuando se realiza la actualización de datos. 
     * Devuelve false cuando los números de documentos no coinciden, y por tanto no es posible realizar la actualización. 
     */
    @Override
    public boolean actualizar(Integer id, Cita cita) {
        if(id.equals(cita.getIdCita())){
            citas.replace(id, cita);
            return true;
        }
        return false;
    }

    /**
     * Elimina la cita cuyo número de documento es el id pasado como argumento de la función.
     * Retorna true cuando la cita se encuentra registrada y es eliminada satisfactoriamente. 
     * Retorna false cuando la cita no se encuentra en los registros y por tanto no puede ser eliminada.
     */
    @Override
    public boolean eliminar(Integer id) 
    {
        if(citas.containsKey(id))
        {
            citas.remove(id);
            return true;
        }

        return false;
    }

    /**
     * Devuelve una lista con todos las citas de cierta especialidad que se pasa como argumento. 
     * Si no hay citas registradas, o citas con la especialidad buscada, devuelve una lista vacía. 
     */
    @Override
    public List<Cita> obtenerCitasConEspecialidad(ServicioMedico servicioMedico){
        List<Cita> listaCitasConEspecialidad = new ArrayList<>();
        String nombreEspecialidad = servicioMedico.getNombreServicio();
        
        if(!citas.isEmpty()){
            for(Map.Entry<Integer, Cita> pareja : citas.entrySet())
                if(pareja.getValue().getServicioMedico().getNombreServicio().equals(nombreEspecialidad))
                    listaCitasConEspecialidad.add(pareja.getValue());
        }
        return listaCitasConEspecialidad;
    }

    @Override
    public List<Cita> obtenerCitasConMedico(Medico medico){
        List<Cita> listaCitasConMedico = new ArrayList<>();

        if(!citas.isEmpty()){
            for(Map.Entry<Integer, Cita> pareja : citas.entrySet())
                if(pareja.getValue().getMedico().getDocumento().equals(medico.getDocumento()))
                    listaCitasConMedico.add(pareja.getValue());
        }
        return listaCitasConMedico;
    }
}