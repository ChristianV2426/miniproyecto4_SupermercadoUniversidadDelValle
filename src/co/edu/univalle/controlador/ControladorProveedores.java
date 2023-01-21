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
import javax.swing.table.DefaultTableModel;


public class ControladorProveedores {
    private static String[] labelProveedores = {"NIT", "Nombre del proveedor", "Correo", "Teléfono"};
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
        ventanaInicio.getFieldIdProveedor().setEditable(true);
        ventanaInicio.getFieldNombreProveedor().setText("");
        ventanaInicio.getFieldTelefonoProveedor().setText("");
        ventanaInicio.getFieldCorreoProveedor().setText("");
    }

    public static String[] getEncabezadoProveedores() {
        return encabezadoProveedores;
    }

    public static boolean revisarFieldsProveedores(VentanaInicio ventanaInicio){
        if(!revisarIDProveedor(ventanaInicio))
            return false;

        String nombreProveedor = ventanaInicio.getFieldNombreProveedor().getText();
        if(nombreProveedor.isEmpty() || nombreProveedor.isBlank() || nombreProveedor == null){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un nombre de proveedor válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String correoProveedor = ventanaInicio.getFieldCorreoProveedor().getText();
        if(correoProveedor.isEmpty() || correoProveedor.isBlank() || correoProveedor == null){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un correo electrónico válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String telefonoProveedor = ventanaInicio.getFieldTelefonoProveedor().getText();
        if(telefonoProveedor.isEmpty() || telefonoProveedor.isBlank() || telefonoProveedor == null){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un teléfono válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }  

        return true;
    }

    public static Proveedor crearProveedor(VentanaInicio ventanaInicio){
        String stringIdProveedor = ventanaInicio.getFieldIdProveedor().getText();
        Integer idProveedor = Integer.valueOf(stringIdProveedor);
        String nombreProveedor = ventanaInicio.getFieldNombreProveedor().getText();
        String correoProveedor = ventanaInicio.getFieldCorreoProveedor().getText();
        String telefonoProveedor = ventanaInicio.getFieldTelefonoProveedor().getText();

        Proveedor proveedor = new Proveedor(idProveedor, nombreProveedor, correoProveedor, telefonoProveedor);

        return proveedor;
    }

    public static boolean revisarIDProveedor(VentanaInicio ventanaInicio){
        String stringIdProveedor = ventanaInicio.getFieldIdProveedor().getText();
        try{
            Integer.valueOf(stringIdProveedor);

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un número de NIT válido, sin puntos ni espacios, solo números.\nEjemplo: 77581411", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static void asignarTabla(DefaultTableModel modeloTabla, VentanaInicio ventanaInicio) {
        String tablaNitProveedor = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
        String tablaNombreProveedor = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
        String tablaCorreoProveedor = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
        String tablaTelefonoProveedor = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 3).toString();

        ventanaInicio.getFieldIdProveedor().setText(tablaNitProveedor);
        ventanaInicio.getFieldIdProveedor().setEditable(false);
        ventanaInicio.getFieldNombreProveedor().setText(tablaNombreProveedor);
        ventanaInicio.getFieldCorreoProveedor().setText(tablaCorreoProveedor);
        ventanaInicio.getFieldTelefonoProveedor().setText(tablaTelefonoProveedor);
    }

}