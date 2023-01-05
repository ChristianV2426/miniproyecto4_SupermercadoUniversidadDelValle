/*
    Archivo: AfiliadoDaoImpl.java
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
    Clase: AfiliadoDaoImpl

    INTENCIÓN: Esta es la clase que define la lógica del Data Access Object para los objetos de tipo afiliado.

    RELACIONES:
    -Implementa la interfaz AfiliadoDao
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.io.Serializable;
import java.util.*;

public class AfiliadoDaoImpl implements AfiliadoDao, Serializable{
    public static final long serialVersionUID = 1L;
    private HashMap<Integer, Afiliado> afiliados;

    public AfiliadoDaoImpl(){
        this.afiliados = new HashMap<>();
    }

    /** 
     * Añade un afiliado al HashMap de afiliados.
     * Unicamente añade al afiliado y retorna true, si el afiliado no se encuentra registrado. 
     */
    @Override
    public boolean añadir(Afiliado afiliado) {
        if(afiliados.containsKey(afiliado.getDocumento()))
            return false;

        afiliados.put(afiliado.getDocumento(), afiliado);
        return true;
    }

    @Override
    public boolean elementoPresente(Integer id){
        if(afiliados.containsKey(id))
            return true;
        return false;
    }

    /**
     * Devuelve el afiliado con el número de id ingresado como parámetro.
     * Si no hay un afiliado registrado con ese número de id, entonces devuelve null.
     */
    @Override
    public Afiliado obtenerElemento(Integer id) {
        if (afiliados.containsKey(id)) 
            return afiliados.get(id);

        return null;
    }

    /**
     * Devuelve una lista con objetos de tipo afiliado, correspondiente a todos los afiliados del sistema. 
     * Si no hay afiliados registrados devuelve una lista vacía. 
     */
    @Override
    public List<Afiliado> obtenerLista() {
        List<Afiliado> listaAfiliados = new ArrayList<>();

        if(!afiliados.isEmpty()){
            for(Map.Entry<Integer, Afiliado> pareja : afiliados.entrySet())
                listaAfiliados.add(pareja.getValue());
        }

        return listaAfiliados;
    }

    /**
     * Actualiza todos los datos de un afiliado. Hay que ingresar como argumento el id del afiliado a actualizar, y un objeto de tipo afiliado con los nuevos datos.  
     * Devuelve true cuando se realiza la actualización de datos. 
     * Devuelve false cuando los números de documento id no coinciden, y por tanto no es posible realizar la actualización. 
     */
    @Override
    public boolean actualizar(Integer id, Afiliado afiliado) {
        if(id.equals(afiliado.getDocumento())){
            afiliados.replace(id, afiliado);
            return true;
        }
        return false;
    }

    /**
     * Elimina el afiliado cuyo número de documento es el id pasado como argumento de la función.
     * Retorna true cuando el afiliado se encuentra registrado y es eliminado satisfactoriamente. 
     * Retorna false cuando el afiliado no se encuentra en los registros y por tanto no puede ser eliminado.
     */
    @Override
    public boolean eliminar(Integer id) 
    {
        if(afiliados.containsKey(id))
        {
            afiliados.remove(id);
            return true;
        }

        return false;
    }

    /**
     * Devuelve una lista con todos los afiliados que tengan citas asignadas. 
     * Si no hay afiliados registrados, o no hay afiliados con citas registradas, devuelve una lista vacía. 
     */
    @Override
    public List<Afiliado> obtenerAfiliadosConCita() {
        List<Afiliado> listaAfiliadosConCita = new ArrayList<>();

        if(!afiliados.isEmpty()){
            for(Map.Entry<Integer, Afiliado> pareja : afiliados.entrySet()){
                if(!pareja.getValue().getCitasAsignadas().isEmpty())
                    listaAfiliadosConCita.add(pareja.getValue());
            }   
        }
        return listaAfiliadosConCita;
    }
}
