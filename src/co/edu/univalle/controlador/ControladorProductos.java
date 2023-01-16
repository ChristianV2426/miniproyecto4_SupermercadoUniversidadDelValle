package co.edu.univalle.controlador;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import co.edu.univalle.vista.VentanaInicio;

public class ControladorProductos {
    private static String[] labelProductos = {"Id cita ", "Nombre ", "Categoría ", "Cantidad ", "Precio "};
    private static String[] opcionesCategoriaProducto = {"Seleccionar", "Categoría 1", "Categoría 2", "Categoría 3"};
    private static JComboBox<String> dropCategoriaProducto = new JComboBox<>(opcionesCategoriaProducto);
    String encabezadoProductos[] = {"Id producto", "Nombre producto","Categoría","Cantidad","Precio"};
    
    
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
}
