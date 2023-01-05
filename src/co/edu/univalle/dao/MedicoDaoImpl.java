/*
    Archivo: MedicoDaoImpl.java
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
    Clase: MedicoDaoImpl

    INTENCIÓN: Esta es la clase que define la lógica del Data Access Object para los objetos de tipo médico.

    RELACIONES:
    -Implementa la interfaz MedicoDao
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.io.Serializable;
import java.util.*;

public class MedicoDaoImpl implements MedicoDao, Serializable{
    public static final long serialVersionUID = 1L;
    private HashMap<Integer, Medico> medicos;

    public MedicoDaoImpl(){
        this.medicos = new HashMap<>();
    }

    /** 
     * Añade un médico al HashMap de medicos.
     * Unicamente añade al médico y retorna true, si el medico no se encuentra registrado. 
     */
    @Override
    public boolean añadir(Medico medico) {
        if(medicos.containsKey(medico.getDocumento()))
            return false;

        medicos.put(medico.getDocumento(), medico);
        return true;
    }

    @Override
    public boolean elementoPresente(Integer id){
        if(medicos.containsKey(id))
            return true;
        return false;
    }

    /**
     * Devuelve el médico con el número de id ingresado como parámetro.
     * Si no hay un medico registrado con ese número de id, entonces devuelve null.
     */
    @Override
    public Medico obtenerElemento(Integer id) {
        if (medicos.containsKey(id)) 
            return medicos.get(id);

        return null;
    }

    /**
     * Devuelve una lista con objetos de tipo médico, correspondiente a todos los médicos del sistema. 
     * Si no hay médicos registrados devuelve una lista vacía. 
     */
    @Override
    public List<Medico> obtenerLista() {
        List<Medico> listamedicos = new ArrayList<>();

        if(!medicos.isEmpty()){
            for(Map.Entry<Integer, Medico> pareja : medicos.entrySet())
                listamedicos.add(pareja.getValue());
        }

        return listamedicos;
    }

    /**
     * Actualiza todos los datos de un médico. Hay que ingresar como argumento el id del médico a actualizar, y un objeto de tipo médico con los nuevos datos.  
     * Devuelve true cuando se realiza la actualización de datos. 
     * Devuelve false cuando los números de documentos no coinciden, y por tanto no es posible realizar la actualización. 
     */
    @Override
    public boolean actualizar(Integer id, Medico medico) {
        if(id.equals(medico.getDocumento())){
            medicos.replace(id, medico);
            return true;
        }
        return false;
    }

    /**
     * Elimina el médico cuyo número de documento es el id pasado como argumento de la función.
     * Retorna true cuando el medico se encuentra registrado y es eliminado satisfactoriamente. 
     * Retorna false cuando el médico no se encuentra en los registros y por tanto no puede ser eliminado.
     */
    @Override
    public boolean eliminar(Integer id) 
    {
        if(medicos.containsKey(id))
        {
            medicos.remove(id);
            return true;
        }

        return false;
    }

    /**
     * Devuelve una lista con todos los médicos que tengan citas asignadas. 
     * Si no hay médicos registrados, o no hay médicos con citas registradas, devuelve una lista vacía. 
     */
    @Override
    public List<Medico> obtenerMedicosConCita() {
        List<Medico> listaMedicosConCita = new ArrayList<>();

        if(!medicos.isEmpty()){
            for(Map.Entry<Integer, Medico> pareja : medicos.entrySet()){
                if(!pareja.getValue().getCitasAsignadas().isEmpty())
                    listaMedicosConCita.add(pareja.getValue());
            }   
        }
        return listaMedicosConCita;
    }

    /**
     * Devuelve una lista con todos los médicos de cierta especialidad que se pasa como argumento. 
     * Si no hay médicos registrados, o médicos con la especialidad buscada, devuelve una lista vacía. 
     */
    @Override
    public List<Medico> obtenerMedicosConEspecialidad(ServicioMedico servicioMedico){
        List<Medico> listaMedicosConEspecialidad = new ArrayList<>();
        String nombreEspecialidad = servicioMedico.getNombreServicio();
        
        if(!medicos.isEmpty()){
            for(Map.Entry<Integer, Medico> pareja : medicos.entrySet())
                if(pareja.getValue().getEspecialidadMedica().equals(nombreEspecialidad))
                    listaMedicosConEspecialidad.add(pareja.getValue());
        }
        return listaMedicosConEspecialidad;
    }

    public String[] arregloMedicosConEspecialidad(ServicioMedico servicioMedico){
        List<Medico> listaMedicos = obtenerMedicosConEspecialidad(servicioMedico);
        int tamanoArreglo = listaMedicos.size();
        String[] arregloMedicos = new String[tamanoArreglo];

        if(!listaMedicos.isEmpty()){
            int contador = 0;
            for(Medico medico : listaMedicos){
                arregloMedicos[contador] = String.valueOf(medico);
                contador++;
            }
        }
        return arregloMedicos;
    }
}
