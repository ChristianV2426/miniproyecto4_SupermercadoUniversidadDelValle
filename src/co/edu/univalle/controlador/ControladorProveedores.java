package co.edu.univalle.controlador;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import co.edu.univalle.vista.VentanaInicio;

public class ControladorProveedores {
    private static String[] labelProveedores = {"NIT ", "Nombre ", "Correo ", "Teléfono "};
    String encabezadoProveedores[] = {"NIT", "Nombre completo", "Correo", "Teléfono", "Compras registradas"};
    
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
}