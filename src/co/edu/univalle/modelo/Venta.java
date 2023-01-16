/*
    Archivo: Venta.java
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
    CLASE: Venta

    INTENCIÓN: Esta clase modela las ventas del Supermercado. 
    Las ventas son transacciones donde está involucrado un tercero de tipo cliente. El Supermercado le vende productos de su stock al cliente a cambio de dinero.
    Aunque la mayor parte de los atributos y métodos de las ventas están definidos en la clase padre Transaccion.java,
    se define una clase exclusiva para las ventas pensando en la extensibilidad del programa (futuros nuevos atributos/funcionalidades de las ventas que se necesiten a medida que el sistema crezca).
    Además, la clase padre es una clase abstracta. 

    RELACIONES:
    -Es una transacción por lo tanto contiene un HashMap de transacciones realizadas. También por herencia implementa las interfaces Listable y Serializable.
*/

package co.edu.univalle.modelo;

import java.util.*;
import java.math.*;
import java.time.*;

public class Venta extends Transaccion{

    public Venta(Integer idTransaccion, LocalDate fechaTransaccion, Integer documentoDelTercero, String nombreDelTercero, HashMap<Integer, Integer> listaProductos, BigDecimal costoTransaccion){
        super(idTransaccion, fechaTransaccion, documentoDelTercero, nombreDelTercero, listaProductos, costoTransaccion);
    }

}
