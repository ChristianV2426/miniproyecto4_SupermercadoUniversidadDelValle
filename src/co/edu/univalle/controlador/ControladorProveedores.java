/*
    Archivo: ControladorProveedores.java
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
    CLASE: ControladorProveedores

    INTENCIÓN: Esta es la clase controladora de la categoría proveedores.
    Esta clase se encarga orientar los eventos relacionados con los proveedores del supermercado.
    
    RELACIONES:
*/



package co.edu.univalle.controlador;

import co.edu.univalle.vista.*;
import co.edu.univalle.modelo.*;
import javax.swing.*;


public class ControladorProveedores {
    private static String[] labelProveedores = {"NIT ", "Nombre del proveedor ", "Correo ", "Teléfono "};
    private static String encabezadoProveedores[] = {"NIT", "Nombre del proveedor", "Correo", "Teléfono", "Transacciones registradas"};
    
    public static void pintar(VentanaInicio ventanaInicio) {
        ventanaInicio.getLabelTexto()[0].setText(labelProveedores[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdProveedor());

        ventanaInicio.getLabelTexto()[1].setText(labelProveedores[1]);
        ventanaInicio.getContenedorTexto()[1].add(ventanaInicio.getFieldNombreProveedor());

        ventanaInicio.getLabelTexto()[2].setText(labelProveedores[2]);
        ventanaInicio.getContenedorTexto()[2].add(ventanaInicio.getFieldCorreoProveedor());

        ventanaInicio.getLabelTexto()[3].setText(labelProveedores[3]);
        ventanaInicio.getContenedorTexto()[3].add(ventanaInicio.getFieldTelefonoProveedor());

    }

    public static void limpiar(VentanaInicio ventanaInicio) {
        ventanaInicio.getFieldIdProveedor().setText("");
        ventanaInicio.getFieldIdProveedor().setEditable(true);
        ventanaInicio.getFieldNombreProveedor().setText("");
        ventanaInicio.getFieldTelefonoProveedor().setText("");
        ventanaInicio.getFieldCorreoProveedor().setText("");
    }

    public static String[] getEncabezadoProveedores() {
        return encabezadoProveedores;
    }

    public static boolean revisarFieldsProveedores(VentanaInicio ventanaInicio){

        return true;
    }

    public static Proveedor crearProveedor(VentanaInicio ventanaInicio){

        return null;
    }

    public static boolean revisarIDProveedor(VentanaInicio ventanaInicio){

        return true;
    }

}