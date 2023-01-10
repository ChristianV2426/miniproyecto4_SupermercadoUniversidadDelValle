/*
    Archivo: ClienteDaoImpl.java
    Fundamentos de Programación Orientada a Eventos - 750014C Grupo 01
    Proyecto 4 - Supermercado Universidad del Valle

    Autores: 
    Juan Camilo Narvaez Tascon - juan.narvaez.tascon@correounivalle.edu.co - 2140112-3743
    Christian David Vargas Gutiérrez - vargas.christian@correounivalle.edu.co - 2179172-3743

    Profesor:
    Ing. M.Sc. Luis Yovany Romo Portilla

    Licencia: GNU-GPL
*/

/**
    Clase: ClienteDaoImpl

    INTENCIÓN: Esta es la clase que define la lógica del Data Access Object para los objetos de tipo cliente.

    RELACIONES:
    -Implementa la interfaz ClienteDao
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.io.Serializable;
import java.util.*;

public class ClienteDaoImpl implements ClienteDao, Serializable{
    public static final long serialVersionUID = 1L;
    private HashMap<Integer, Cliente> mapa;

    public ClienteDaoImpl() {
        this.mapa = new HashMap<>();
    }

    @Override
    public boolean añadir(Cliente t) {
        if(mapa.containsKey(t.getIdentificacion()))
            return false;
        
        mapa.put(t.getIdentificacion(), t);
        return true;
    }

    @Override
    public boolean elementoPresente(Integer identificacion) {
        if(mapa.containsKey(identificacion))
            return true;
        return false;
    }

    @Override
    public Cliente getElemento(Integer identificacion) {
        if(mapa.containsKey(identificacion))
            return mapa.get(identificacion);
        return null;
    }

    @Override
    public String[][] getListables() {
        ArrayList<Cliente> lista = new ArrayList<>();

        if(!mapa.isEmpty())
            for(Map.Entry<Integer, Cliente> pareja : mapa.entrySet())
                lista.add(pareja.getValue());

        return Arreglo.getArreglo(lista);
    }

    @Override
    public boolean actualizar(Integer identificacion, Cliente t) {
        if(identificacion.equals(t.getIdentificacion())){
            mapa.replace(identificacion, t);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminar(Integer identificacion) {
        if(mapa.containsKey(identificacion)){
            mapa.remove(identificacion);
            return true;
        }
        return false;
    }

    @Override
    public String[][] getListablesConTransacciones() {
        ArrayList<Cliente> lista = new ArrayList<>();

        if(!mapa.isEmpty())
            for(Map.Entry<Integer, Cliente> pareja : mapa.entrySet())
                if(!pareja.getValue().getTransaccionesRealizadas().isEmpty())
                    lista.add(pareja.getValue());

        return Arreglo.getArreglo(lista);
    }

}
