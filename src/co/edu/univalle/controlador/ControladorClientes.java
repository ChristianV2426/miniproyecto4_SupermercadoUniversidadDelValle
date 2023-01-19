/*
    Archivo: ControladorClientes.java
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
    CLASE: ControladorClientes

    INTENCIÓN: Esta es la clase controladora de la categoría clientes.
    Esta clase se encarga orientar los eventos relacionados con los clientes del supermercado.
    
    RELACIONES:
*/

package co.edu.univalle.controlador;

import co.edu.univalle.vista.*;
import co.edu.univalle.modelo.*;
import javax.swing.*;


public class ControladorClientes {
    private static String[] labelClientes = {"Cédula", "Nombre del cliente", "Correo", "Teléfono"};
    private static String[] encabezadoClientes = {"Cédula", "Nombre del cliente", "Correo", "Teléfono", "Transacciones registradas"};
    
    public static void pintar(VentanaInicio ventanaInicio) {
        ventanaInicio.getLabelTexto()[0].setText(labelClientes[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdClientes());

        ventanaInicio.getLabelTexto()[1].setText(labelClientes[1]);
        ventanaInicio.getContenedorTexto()[1].add(ventanaInicio.getFieldNombresClientes());

        ventanaInicio.getLabelTexto()[2].setText(labelClientes[2]);
        ventanaInicio.getContenedorTexto()[2].add(ventanaInicio.getFieldCorreoClientes());

        ventanaInicio.getLabelTexto()[3].setText(labelClientes[3]);
        ventanaInicio.getContenedorTexto()[3].add(ventanaInicio.getFieldTelefonoClientes());

    }

    public static void limpiar(VentanaInicio ventanaInicio) {

        ventanaInicio.getFieldIdClientes().setText("");
        ventanaInicio.getFieldIdClientes().setEditable(true);
        ventanaInicio.getFieldNombresClientes().setText("");
        ventanaInicio.getFieldTelefonoClientes().setText("");
        ventanaInicio.getFieldCorreoClientes().setText("");
    }

    public static String[] getEncabezadoClientes() {
        return encabezadoClientes;
    }

    public static boolean revisarFieldsClientes(VentanaInicio ventanaInicio){

        return true;
    }

    public static Cliente crearCliente(VentanaInicio ventanaInicio){

        return null;
    }

    public static boolean revisarIDCliente(VentanaInicio ventanaInicio){

        return true;
    }
    
}
