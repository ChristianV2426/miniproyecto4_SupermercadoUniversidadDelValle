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
import javax.swing.table.*;


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

        ventanaInicio.getFieldIdClientes().setEditable(true);
        ventanaInicio.getFieldNombresClientes().setText("");
        ventanaInicio.getFieldTelefonoClientes().setText("");
        ventanaInicio.getFieldCorreoClientes().setText("");
    }

    public static String[] getEncabezadoClientes() {
        return encabezadoClientes;
    }

    public static boolean revisarFieldsClientes(VentanaInicio ventanaInicio){
        if(!revisarIDCliente(ventanaInicio))
            return false;

        String nombreCliente = ventanaInicio.getFieldNombresClientes().getText();
        if(nombreCliente.isEmpty() || nombreCliente.isBlank() || nombreCliente == null){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un nombre de cliente válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String correoCliente = ventanaInicio.getFieldCorreoClientes().getText();
        if(correoCliente.isEmpty() || correoCliente.isBlank() || correoCliente == null){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un correo electrónico válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String telefonoCliente = ventanaInicio.getFieldTelefonoClientes().getText();
        if(telefonoCliente.isEmpty() || telefonoCliente.isBlank() || telefonoCliente == null){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un teléfono válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }  

        return true;
    }

    public static Cliente crearCliente(VentanaInicio ventanaInicio){
        String stringIdCliente = ventanaInicio.getFieldIdClientes().getText();
        Integer idCliente = Integer.valueOf(stringIdCliente);
        String nombreCliente = ventanaInicio.getFieldNombresClientes().getText();
        String correoCliente = ventanaInicio.getFieldCorreoClientes().getText();
        String telefonoCliente = ventanaInicio.getFieldTelefonoClientes().getText();

        Cliente cliente = new Cliente(idCliente, nombreCliente, correoCliente, telefonoCliente);

        return cliente;
    }

    public static boolean revisarIDCliente(VentanaInicio ventanaInicio){
        String stringIdCliente = ventanaInicio.getFieldIdClientes().getText();
        try{
            Integer idCliente = Integer.valueOf(stringIdCliente);
            if(idCliente <= 0)
                throw new NumberFormatException();

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un número de documento válido, sin puntos ni espacios, solo números.\nEjemplo: 1144000000", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    public static void asignarTabla(DefaultTableModel modeloTabla, VentanaInicio ventanaInicio) {
        String tablaCedulaCliente = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
        String tablaNombreCliente = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
        String tablaCorreoCliente = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
        String tablaTelefonoCliente = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 3).toString();

        ventanaInicio.getFieldIdClientes().setText(tablaCedulaCliente);
        ventanaInicio.getFieldIdClientes().setEditable(false);
        ventanaInicio.getFieldNombresClientes().setText(tablaNombreCliente);
        ventanaInicio.getFieldCorreoClientes().setText(tablaCorreoCliente);
        ventanaInicio.getFieldTelefonoClientes().setText(tablaTelefonoCliente);
    }
}
