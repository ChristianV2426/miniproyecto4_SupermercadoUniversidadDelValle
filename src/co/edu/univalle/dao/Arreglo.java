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
import java.util.*;

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

    public static String[] actualizarArreglo(String[] arregloOriginal){
        int tamañoArregloOriginal = arregloOriginal.length;
        String[]arregloActualizado = new String[tamañoArregloOriginal+1];
        arregloActualizado[0] = "Seleccionar";

        if(tamañoArregloOriginal == 0){
            return arregloActualizado;
        }

        int contador = 1;
        for(String cadena : arregloOriginal){
            arregloActualizado[contador] = cadena;
            contador++;
        }

        return arregloActualizado;
    }
}