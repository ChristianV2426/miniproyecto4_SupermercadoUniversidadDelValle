/*
    Archivo: ServicioMedicoDaoImpl.java
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
    Clase: ServicioMedicoDaoImpl

    INTENCIÓN: Esta es la clase que define la lógica del Data Access Object para los objetos de tipo servicio médico.

    RELACIONES:
    -Implementa la interfaz ServicioMedicoDao
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.io.Serializable;
import java.util.*;

public class ServicioMedicoDaoImpl implements ServicioMedicoDao, Serializable{
    public static final long serialVersionUID = 1L;
    private HashMap<Integer, ServicioMedico> serviciosMedicos;

    public ServicioMedicoDaoImpl(){
        this.serviciosMedicos = new HashMap<>();
    }

    /** 
     * Añade un servicio médico al HashMap de serviciosMedicos.
     * Unicamente añade al servicio médico y retorna true, si el servicio médico no se encuentra registrado. 
     */
    @Override
    public boolean añadir(ServicioMedico servicioMedico) {
        if(serviciosMedicos.containsKey(servicioMedico.getIdServicio()))
            return false;

        serviciosMedicos.put(servicioMedico.getIdServicio(), servicioMedico);
        return true;
    }

    @Override
    public boolean elementoPresente(Integer id){
        if(serviciosMedicos.containsKey(id))
            return true;
        return false;
    }

    /**
     * Devuelve el servicio médico con el número de id ingresado como parámetro.
     * Si no hay un servicio médico registrado con ese número de id, entonces devuelve null.
     */
    @Override
    public ServicioMedico obtenerElemento(Integer id) {
        if (serviciosMedicos.containsKey(id)) 
            return serviciosMedicos.get(id);

        return null;
    }

    /**
     * Devuelve una lista con objetos de tipo servicio médico, correspondiente a todos los servicios médicos del sistema. 
     * Si no hay servicios médicos registrados devuelve una lista vacía. 
     */
    @Override
    public List<ServicioMedico> obtenerLista() {
        List<ServicioMedico> listaServiciosMedicos = new ArrayList<>();
        
        if(!serviciosMedicos.isEmpty()){
            for(Map.Entry<Integer, ServicioMedico> pareja : serviciosMedicos.entrySet())
                listaServiciosMedicos.add(pareja.getValue());
        }

        return listaServiciosMedicos;
    }
    
    @Override
    public String[] obtenerArreglo(){
        int tamanoArreglo = serviciosMedicos.size();
        String[] arregloServiciosMedicos = new String[tamanoArreglo];
        
        if(!serviciosMedicos.isEmpty()){
            int contador = 0;
            for(Map.Entry<Integer, ServicioMedico> pareja : serviciosMedicos.entrySet()){
                arregloServiciosMedicos[contador] = String.valueOf(pareja.getValue());
                contador++;
            }
        }
        return arregloServiciosMedicos;
    }

    /**
     * Actualiza todos los datos de un servicio médico. Hay que ingresar como argumento el id del servicio médico a actualizar, y un objeto de tipo servicio médico con los nuevos datos.  
     * Devuelve true cuando se realiza la actualización de datos. 
     * Devuelve false cuando los id no coinciden, y por tanto no es posible realizar la actualización. 
     */
    @Override
    public boolean actualizar(Integer id, ServicioMedico servicioMedico) {
        if(id.equals(servicioMedico.getIdServicio())){
            serviciosMedicos.replace(id, servicioMedico);
            return true;
        }
        return false;
    }

    /**
     * Elimina el servicio médico cuyo número de documento es el id pasado como argumento de la función.
     * Retorna true cuando el servicio médico se encuentra registrado y es eliminado satisfactoriamente. 
     * Retorna false cuando el servicio médico no se encuentra en los registros y por tanto no puede ser eliminado.
     */
    @Override
    public boolean eliminar(Integer id) 
    {
        if(serviciosMedicos.containsKey(id))
        {
            serviciosMedicos.remove(id);
            return true;
        }

        return false;
    }
}
