package co.edu.univalle.controlador;

import co.edu.univalle.vista.VentanaInicio;

public class ControladorCompra {
    
    private static String[] labelCompra = {"ID de la compra ", "Fecha de la compra ", "NIT del proveedor ", "Nombre del proveedor ", "Costo de la compra ", "Lista de productos comprador "};

    
    public static void pintar(VentanaInicio ventanaInicio) {
        ventanaInicio.getLabelTexto()[0].setText(labelCompra[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdCompra());

        ventanaInicio.getLabelTexto()[1].setText(labelCompra[1]);
        ventanaInicio.getContenedorTexto()[1].add(ventanaInicio.getFieldFechaCompra());
        
        ventanaInicio.getLabelTexto()[2].setText(labelCompra[2]);
        ventanaInicio.getContenedorTexto()[2].add(ventanaInicio.getFieldNitProveedorCompra());

        ventanaInicio.getLabelTexto()[3].setText(labelCompra[3]);
        ventanaInicio.getContenedorTexto()[3].add(ventanaInicio.getFieldNombreProveedorCompra());

        ventanaInicio.getLabelTexto()[4].setText(labelCompra[4]);
        ventanaInicio.getContenedorTexto()[4].add(ventanaInicio.getFieldCostoCompra());

        ventanaInicio.getLabelTexto()[5].setText(labelCompra[5]);
        ventanaInicio.getContenedorTexto()[5].add(ventanaInicio.getbuttonListaProductosCompra());

    }


    public static void limpiar(VentanaInicio ventanaInicio) {
        ventanaInicio.getFieldIdCompra().setText("");
        ventanaInicio.getFieldFechaCompra().setText("");
        ventanaInicio.getFieldNitProveedorCompra().setText("");
        ventanaInicio.getFieldNombreProveedorCompra().setText("");
        ventanaInicio.getFieldCostoCompra().setText("");
    }
}
