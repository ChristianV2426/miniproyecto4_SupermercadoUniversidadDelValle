package co.edu.univalle.controlador;

import co.edu.univalle.vista.VentanaInicio;

public class ControladorVenta {
    
    private static String[] labelVenta = {"ID de la venta ", "Fecha de la venta ", "Cédula del cliente ", "Nombre y apellido ", "Costo ", "Lista de productos vendidos "};
    String encabezadoVenta[] = {"ID de la venta", "Fecha de la venta", "Cédula del cliente", "Nombre y apellido", "Costo"};
    
    public static void pintar(VentanaInicio ventanaInicio) {
        ventanaInicio.getLabelTexto()[0].setText(labelVenta[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdVenta());

        ventanaInicio.getLabelTexto()[1].setText(labelVenta[1]);
        ventanaInicio.getContenedorTexto()[1].add(ventanaInicio.getFieldFechaVenta());
        
        ventanaInicio.getLabelTexto()[2].setText(labelVenta[2]);
        ventanaInicio.getContenedorTexto()[2].add(ventanaInicio.getFieldCedulaClienteVenta());

        ventanaInicio.getLabelTexto()[3].setText(labelVenta[3]);
        ventanaInicio.getContenedorTexto()[3].add(ventanaInicio.getFieldNombresClienteVenta());

        ventanaInicio.getLabelTexto()[4].setText(labelVenta[4]);
        ventanaInicio.getContenedorTexto()[4].add(ventanaInicio.getFieldCostoVenta());

        ventanaInicio.getLabelTexto()[5].setText(labelVenta[5]);
        ventanaInicio.getContenedorTexto()[5].add(ventanaInicio.getbuttonListaProductosVenta());

    }


    public static void limpiar(VentanaInicio ventanaInicio) {
        ventanaInicio.getFieldIdVenta().setText("");
        ventanaInicio.getFieldFechaVenta().setText("");
        ventanaInicio.getFieldCedulaClienteVenta().setText("");
        ventanaInicio.getFieldNombresClienteVenta().setText("");
        ventanaInicio.getFieldCostoVenta().setText("");
    }
}
