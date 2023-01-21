/*
    Archivo: ControladorProductos.java
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
    CLASE: ControladorProductos

    INTENCIÓN: Esta es la clase controladora de la categoría productos.
    Esta clase se encarga orientar los eventos relacionados con los productos del supermercado.
    
    RELACIONES:
*/

package co.edu.univalle.controlador;

import co.edu.univalle.modelo.*;
import co.edu.univalle.vista.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ControladorListar {
    private static String encabezadoListaVenta[] = {"ID del producto", "Cantidad"};
    private static String encabezadoListaCompra[] = {"ID del producto", "Cantidad", "Costo"};
    
    public static void limpiar(VentanaListar ventanaListar, String categoria) {

        if(categoria == "Ventas (a clientes)"){
            ventanaListar.getId().setText("");
            ventanaListar.getCantidad().setText("");


        } else if(categoria == "Compras (a proveedores)"){
            ventanaListar.getId().setText("");
            ventanaListar.getCantidad().setText("");
            ventanaListar.getCosto().setText("");
        }
    }

    public static String[] getEncabezadoListaVenta() {
        return encabezadoListaVenta;
    }

    public static String[] getEncabezadoListaCompra() {
        return encabezadoListaCompra;
    }

    // public static boolean revisarFieldsProductos(VentanaInicio ventanaInicio){

    //     return true;
    // }

    // public static Producto crearProducto(VentanaInicio ventanaInicio){
        
    //     return null;
    // }

    // public static boolean revisarIDProducto(VentanaInicio ventanaInicio){
        
    //     return true;
    // }

    public static void asignarTabla(DefaultTableModel modeloTabla, VentanaListar ventanaListar, String categoria) {

        if(categoria == "Ventas (a clientes)"){
            String tablaIdVentasListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 0).toString();
            String tablaCantidadListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 1).toString();

            ventanaListar.getId().setText(tablaIdVentasListado);
            ventanaListar.getCantidad().setText(tablaCantidadListado);

        } else if(categoria == "Compras (a proveedores)"){
            String tablaIdVentasListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 0).toString();
            String tablaCantidadListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 1).toString();
            String tablaCostoListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 2).toString();

            ventanaListar.getId().setText(tablaIdVentasListado);
            ventanaListar.getCantidad().setText(tablaCantidadListado);
            ventanaListar.getCosto().setText(tablaCostoListado);
        }
    }
}