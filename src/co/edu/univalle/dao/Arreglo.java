/*
    Archivo: Arreglo.java
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
    CLASE: Arreglo

    INTENCIÓN: Esta clase tiene la única intención de definir un método estático que convierta las listas
    de objetos del sistema en arreglos bidimensionales de tipo String, que contengan los datos de cada objeto que 
    se desplegarán en la interfaz gráfica del programa. Esta clase trabaja con objetos genéricos que extiendan de la clase Listable.
    
    RELACIONES:
    -Implementa la interfaz Serializable para permitir la persistencia del sistema en archivos de tipo binario.
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.io.Serializable;
import java.math.*;
import java.util.*;
import java.text.*;


public class Arreglo implements Serializable{
    public static final long serialVersionUID = 1L;

    public static <T extends Listable> String[][] getArreglo(ArrayList<T> lista){
        if(lista.isEmpty())
            return null;

        int numeroFilas = lista.size();
        int numeroColumnas = lista.get(0).getTotalDatos();
        String[][] arreglo = new String[numeroFilas][numeroColumnas];

        for(int fila=0; fila < numeroFilas; fila++)
            for(int columna=0; columna < numeroColumnas; columna++)
                arreglo[fila][columna] = lista.get(fila).getDato(columna);

        return arreglo;
    }

    public static String[][] getArregloProductosVenta(Supermercado supermercado, HashMap<Integer, Integer> listaProductos){
        if(listaProductos.isEmpty())
            return null;

        int numeroFilas = listaProductos.size();
        int numeroColumnas = 3;
        String[][] arreglo = new String[numeroFilas][numeroColumnas];

        int contadorFila = 0;
        for(Map.Entry<Integer, Integer> pareja : listaProductos.entrySet()){
            Integer idProducto = pareja.getKey();
            arreglo[contadorFila][0] = String.valueOf(idProducto);
            arreglo[contadorFila][1] = String.valueOf(supermercado.getProductos().getElemento(idProducto).getNombreProducto());
            arreglo[contadorFila][2] = String.valueOf(pareja.getValue());
            contadorFila++;
        }
        return arreglo;
    }

    public static String[][] getArregloProductosCompra(Supermercado supermercado, HashMap<Integer, Integer> listaProductos, HashMap<Integer, BigDecimal> listaPrecios){
        if(listaProductos.isEmpty())
            return null;
        
        int numeroFilas = listaProductos.size();
        int numeroColumnas = 4;
        String[][] arreglo = new String[numeroFilas][numeroColumnas];
        
        DecimalFormatSymbols puntoDecimal = new DecimalFormatSymbols();
        puntoDecimal.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("###.00", puntoDecimal);
        int contadorFila = 0;
        for(Map.Entry<Integer, Integer> pareja : listaProductos.entrySet()){
            Integer idProducto = pareja.getKey();
            arreglo[contadorFila][0] = String.valueOf(idProducto);
            arreglo[contadorFila][1] = String.valueOf(supermercado.getProductos().getElemento(idProducto).getNombreProducto());
            arreglo[contadorFila][2] = String.valueOf(pareja.getValue());
            arreglo[contadorFila][3] = String.valueOf(formato.format(listaPrecios.get(idProducto)));
            contadorFila++;
        }

        return arreglo;
    }
}