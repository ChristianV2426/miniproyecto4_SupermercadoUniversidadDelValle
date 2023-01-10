/*
    Archivo: ProductoDaoImpl.java
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
    Clase: ProductoDaoImpl

    INTENCIÓN: Esta es la clase que define la lógica del Data Access Object para los objetos de tipo producto.

    RELACIONES:
    -Implementa la interfaz ProductoDao
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.io.Serializable;
import java.util.*;

public class ProductoDaoImpl implements ProductoDao, Serializable{
    public static final long serialVersionUID = 1L;
    private HashMap<Integer, Producto> mapa;

    public ProductoDaoImpl() {
        this.mapa = new HashMap<>();
    }

    @Override
    public boolean añadir(Producto t) {
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
    public Producto getElemento(Integer identificacion) {
        if(mapa.containsKey(identificacion))
            return mapa.get(identificacion);
        return null;
    }

    @Override
    public String[][] getListables() {
        ArrayList<Producto> lista = new ArrayList<>();

        if(!mapa.isEmpty())
            for(Map.Entry<Integer, Producto> pareja : mapa.entrySet())
                lista.add(pareja.getValue());

        return Arreglo.getArreglo(lista);
    }

    @Override
    public boolean actualizar(Integer identificacion, Producto t) {
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
    public String[][] getListablesConCategoria(String categoria) {
        ArrayList<Producto> lista = new ArrayList<>();

        if(!mapa.isEmpty())
            for(Map.Entry<Integer, Producto> pareja : mapa.entrySet())
                if(pareja.getValue().getCategoriaProducto().equals(categoria))
                    lista.add(pareja.getValue());

        return Arreglo.getArreglo(lista);
    }

}
