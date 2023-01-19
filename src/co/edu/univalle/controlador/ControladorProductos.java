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


public class ControladorProductos {
    private static String[] labelProductos = {"ID del producto", "Nombre del producto", "Categoría", "Cantidad en Stock", "Precio de venta"};
    private static String[] opcionesCategoriaProducto = {"Seleccionar", "Categoría 1", "Categoría 2", "Categoría 3"};
    private static JComboBox<String> dropCategoriaProducto = new JComboBox<>(opcionesCategoriaProducto);
    private static String encabezadoProductos[] = {"ID del producto", "Nombre producto", "Categoría", "Cantidad en Stock", "Precio de venta"};
    
    
    public static void pintar(VentanaInicio ventanaInicio) {
        ventanaInicio.getLabelTexto()[0].setText(labelProductos[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdProductos());

        ventanaInicio.getLabelTexto()[1].setText(labelProductos[1]);
        ventanaInicio.getContenedorTexto()[1].add(ventanaInicio.getFieldNombresProductos());

        ventanaInicio.getContenedorTexto()[2].setVisible(true);
        ventanaInicio.getLabelTexto()[2].setText(labelProductos[2]);
        ventanaInicio.getContenedorTexto()[2].add(dropCategoriaProducto);
            
        ventanaInicio.getLabelTexto()[3].setText(labelProductos[3]);
        ventanaInicio.getContenedorTexto()[3].add(ventanaInicio.getFieldCantidadProductos());

        ventanaInicio.getLabelTexto()[4].setText(labelProductos[4]);
        ventanaInicio.getContenedorTexto()[4].add(ventanaInicio.getFieldPrecioProductos());
    }

    public static void limpiar(VentanaInicio ventanaInicio) {

        // ventanaInicio.getContenedorTexto()[2].setVisible(false); // Se hace para solucionar bugs de swing

        ventanaInicio.getFieldIdProductos().setText("");
        ventanaInicio.getFieldNombresProductos().setText("");
        dropCategoriaProducto.setSelectedItem("Seleccionar");
        ventanaInicio.getFieldCantidadProductos().setText("");
        ventanaInicio.getFieldPrecioProductos().setText("");
    }

    public static String[] getEncabezadoProductos() {
        return encabezadoProductos;
    }

    public static boolean revisarFieldsProductos(VentanaInicio ventanaInicio){

        return true;
    }

    public static Producto crearProducto(VentanaInicio ventanaInicio){
        
        return null;
    }

    public static boolean revisarIDProducto(VentanaInicio ventanaInicio){
        
        return true;
    }
    
}
