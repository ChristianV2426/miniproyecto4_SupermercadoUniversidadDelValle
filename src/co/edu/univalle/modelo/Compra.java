/*
    Archivo: Compra.java
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
    CLASE: Compra

    INTENCIÓN: Esta clase modela las compras del Supermercado. 
    Las compras son transacciones donde está involucrado un tercero de tipo proveedor. El Supermercado le compra productos a los proveedores para tener stock que vender.
    Aunque la mayor parte de los atributos y métodos de las compras están definidos en la clase padre Transaccion.java,
    se define una clase exclusiva para las compras pensando en la extensibilidad del programa (futuros nuevos atributos/funcionalidades de las compras que se necesiten a medida que el sistema crezca).
    Además, la clase padre es una clase abstracta. 

    RELACIONES:
    -Es una transacción por lo tanto contiene un HashMap de transacciones realizadas. También por herencia implementa las interfaces Listable y Serializable.
*/

package co.edu.univalle.modelo;

import java.util.*;
import java.math.*;
import java.time.*;

public class Compra extends Transaccion{
    HashMap<Integer, BigDecimal> listaPrecios;

    public Compra(Integer idTransaccion, LocalDate fechaTransaccion, Integer documentoDelTercero, String nombreDelTercero, HashMap<Integer, Integer> listaProductos, BigDecimal costoTransaccion, HashMap<Integer, BigDecimal> listaPrecios){
        super(idTransaccion, fechaTransaccion, documentoDelTercero, nombreDelTercero, listaProductos, costoTransaccion);
        this.listaPrecios = listaPrecios;
    }

    public HashMap<Integer, BigDecimal> getListaPrecios() {
        return listaPrecios;
    }  

}
