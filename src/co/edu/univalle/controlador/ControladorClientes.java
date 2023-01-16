package co.edu.univalle.controlador;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import co.edu.univalle.vista.VentanaInicio;

public class ControladorClientes {
    private static String[] labelClientes = {"Cédula ", "Nombre y apellido ", "Correo ", "Teléfono "};
    String encabezadoClientes[] = {"Cédula", "Nombre completo", "Correo", "Teléfono", "Compras registradas"};
    
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
}
