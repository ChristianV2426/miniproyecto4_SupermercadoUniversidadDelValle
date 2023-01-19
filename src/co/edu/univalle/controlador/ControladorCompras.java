/*
    Archivo: ControladorCompras.java
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
    CLASE: ControladorCompras

    INTENCIÓN: Esta es la clase controladora de la categoría compras.
    Esta clase se encarga orientar los eventos relacionados con las compras del supermercado.
    
    RELACIONES:
*/

package co.edu.univalle.controlador;

import co.edu.univalle.modelo.*;
import co.edu.univalle.vista.*;

public class ControladorCompras {
    
    private static String[] labelCompra = {"ID de la compra ", "Fecha de la compra ", "NIT del proveedor ", "Nombre del proveedor ", "Valor de la factura ", "Lista de productos "};
    private static String[] encabezadoCompra = {"ID de la compra", "Fecha de la compra", "NIT del proveedor", "Nombre del proveedor", "Número de productos", "Valor de la factura"};
    
    public static void pintar(VentanaInicio ventanaInicio) {
        ventanaInicio.getLabelTexto()[0].setText(labelCompra[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdCompra());

        ventanaInicio.getLabelTexto()[1].setText(labelCompra[1]);
        ventanaInicio.getContenedorTexto()[1].add(ventanaInicio.getFieldFechaCompra());
        
        ventanaInicio.getLabelTexto()[2].setText(labelCompra[2]);
        ventanaInicio.getContenedorTexto()[2].add(ventanaInicio.getFieldNitProveedorCompra());

        ventanaInicio.getLabelTexto()[3].setText(labelCompra[3]);
        ventanaInicio.getContenedorTexto()[3].add(ventanaInicio.getFieldNombreProveedorCompra());
        
        ventanaInicio.getLabelTexto()[4].setText(labelCompra[5]);
        ventanaInicio.getContenedorTexto()[4].add(ventanaInicio.getbuttonListaProductosCompra());

        ventanaInicio.getLabelTexto()[5].setText(labelCompra[4]);
        ventanaInicio.getContenedorTexto()[5].add(ventanaInicio.getFieldCostoCompra());

    }

    public static void limpiar(VentanaInicio ventanaInicio) {
        ventanaInicio.getFieldIdCompra().setText("");
        ventanaInicio.getFieldFechaCompra().setText("");
        ventanaInicio.getFieldNitProveedorCompra().setText("");
        ventanaInicio.getFieldNombreProveedorCompra().setText("");
        ventanaInicio.getFieldCostoCompra().setText("");
    }

    public static String[] getEncabezadoCompra() {
        return encabezadoCompra;
    }

    public static boolean revisarFieldsCompras(VentanaInicio ventanaInicio){

        return true;
    }
    
    public static Compra crearCompra(VentanaInicio ventanaInicio){

        return null;
    }

    public static boolean revisarIDCompra(VentanaInicio ventanaInicio){

        return true;
    }
}
