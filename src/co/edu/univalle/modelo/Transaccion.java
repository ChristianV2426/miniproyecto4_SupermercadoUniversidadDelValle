/*
    Archivo: Transaccion.java
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
    CLASE ABSTRACTA: Transacción

    INTENCIÓN: Esta clase definirá los valores y métodos básicos de las transacciones del sistema.
    Una transacción podrá ser una venta a un cliente o una compra a un proveedor.
    La idea es que las clases Venta.java y Compra.java hereden de esta clase.
    En una transacción están involucrados un tercero y una lista de productos que se intercambian con el tercero.

    RELACIONES:
    -Contiene un HashMap donde guarda la lista de productos involucrados en la transacción. 
    -Implementa la interfaz Listable, pues los datos de los objetos de tipo transacción se desplegarán en la tabla dispuesta para ello en la interfaz gráfica del programa.
    -Implementa la interfaz Serializable para permitir la persistencia del sistema en archivos de tipo binario.
*/

package co.edu.univalle.modelo;

import java.io.Serializable;
import java.util.*;
import java.time.*;
import java.math.*;

public abstract class Transaccion implements Listable, Serializable{
    public static final long serialVersionUID = 1L;
    private static final int totalDatos = 6;
    private Integer idTransaccion;
    private LocalDate fechaTransaccion;
    private Integer documentoDelTercero;
    private String nombreDelTercero;
    private HashMap<Integer, Integer> listaProductos;
    private BigDecimal costoTransaccion; 

    public Transaccion(Integer idTransaccion, LocalDate fechaTransaccion, Integer documentoDelTercero, String nombreDelTercero, HashMap<Integer, Integer> listaProductos, BigDecimal costoTransaccion){
        this.idTransaccion = idTransaccion;
        this.fechaTransaccion = fechaTransaccion;
        this.documentoDelTercero = documentoDelTercero;
        this.nombreDelTercero = nombreDelTercero;
        this.listaProductos = listaProductos;
        this.costoTransaccion = costoTransaccion;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public LocalDate getFechaTransaccion() {
        return fechaTransaccion;
    }

    public Integer getDocumentoDelTercero() {
        return documentoDelTercero;
    }

    public String getNombreDelTercero() {
        return nombreDelTercero;
    }

    public HashMap<Integer, Integer> getListaProductos() {
        return listaProductos;
    }

    public BigDecimal getCostoTransaccion() {
        return costoTransaccion;
    }

    public int getTotalDatos(){
        return totalDatos;
    }

    public String getDato(int idDato){
        switch(idDato){
            case 0:
                return String.valueOf(idTransaccion);
            case 1:
                return String.valueOf(fechaTransaccion);
            case 2:
                return String.valueOf(documentoDelTercero);
            case 3:
                return String.valueOf(nombreDelTercero);
            case 4:
                return String.valueOf(listaProductos.size());
            case 5:
                return String.valueOf(costoTransaccion);
            default:
                return "";
        }
    }

    @Override
    public String toString(){
        String cadena = String.valueOf(idTransaccion) + " " + String.valueOf(fechaTransaccion) + " total de productos: " + String.valueOf(listaProductos.size());
        return cadena;
    }
    
}
