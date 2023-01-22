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
import java.math.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ControladorProductos {
    private static String[] labelProductos = {"ID del producto ", "Nombre del producto ", "Categoría ", "Cantidad en Stock ", "Precio de venta $ "};
    private static String[] opcionesCategoriaProducto = {"Seleccionar", "Categoría 1", "Categoría 2", "Categoría 3"};
    private static JComboBox<String> dropCategoriaProducto = new JComboBox<>(opcionesCategoriaProducto);
    private static String encabezadoProductos[] = {"ID del producto", "Nombre producto", "Categoría", "Cantidad en Stock", "Precio de venta"};
    
    
    public static void pintar(VentanaInicio ventanaInicio, Integer serialProducto) {
        ventanaInicio.getLabelTexto()[0].setText(labelProductos[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdProductos());
        ventanaInicio.getFieldIdProductos().setText(String.valueOf(serialProducto));

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

    public static void limpiar(VentanaInicio ventanaInicio, Integer serialProducto) {
        // ventanaInicio.getContenedorTexto()[2].setVisible(false); // Se hace para solucionar bugs de swing
        ventanaInicio.getFieldIdProductos().setText(String.valueOf(serialProducto));
        ventanaInicio.getFieldNombresProductos().setText("");
        dropCategoriaProducto.setSelectedItem("Seleccionar");
        ventanaInicio.getFieldCantidadProductos().setText("");
        ventanaInicio.getFieldPrecioProductos().setText("");
    }

    public static String[] getEncabezadoProductos() {
        return encabezadoProductos;
    }

    public static boolean revisarFieldsProductos(VentanaInicio ventanaInicio){
        String nombreProducto = ventanaInicio.getFieldNombresProductos().getText();
        if(nombreProducto.isEmpty() || nombreProducto.isBlank() || nombreProducto == null){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un nombre de producto válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String categoria = dropCategoriaProducto.getSelectedItem().toString();
        if(categoria.equals("Seleccionar")){
            JOptionPane.showMessageDialog(null,"Por favor seleccione una categoría de producto.", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;    
        }

        String stringPrecioDeVenta = ventanaInicio.getFieldPrecioProductos().getText();
        try{
            BigDecimal precioVenta = BigDecimal.valueOf(Double.valueOf(stringPrecioDeVenta));
            if(precioVenta.compareTo(BigDecimal.ZERO) <= 0)
                throw new NumberFormatException(); 

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null,"Por favor ingrese un precio de venta válido. El precio debe escribirse sin puntos ni espacios, solo números mayores a cero.\nEjemplo: 45000 (cuarenta y cinco mil pesos)", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static Producto crearProducto(VentanaInicio ventanaInicio){
        String stringIDProducto = ventanaInicio.getFieldIdProductos().getText();
        Integer idProducto = Integer.valueOf(stringIDProducto);
        String nombreProducto = ventanaInicio.getFieldNombresProductos().getText();
        String categoria = dropCategoriaProducto.getSelectedItem().toString();
        String stringPrecioDeVenta = ventanaInicio.getFieldPrecioProductos().getText();
        BigDecimal precioVenta = BigDecimal.valueOf(Double.valueOf(stringPrecioDeVenta));

        Producto producto = new Producto(idProducto, nombreProducto, categoria, precioVenta);

        return producto;
    }

    public static void asignarTabla(DefaultTableModel modeloTabla, VentanaInicio ventanaInicio) {
        String tablaIdProductos = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
        String tablaNombreProductos = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
        String tablaCategoriaProductos = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
        String tablaCantidadProductos = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 3).toString();
        String tablaPrecioProductos = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 4).toString();

        ventanaInicio.getFieldIdProductos().setText(tablaIdProductos);
        ventanaInicio.getFieldNombresProductos().setText(tablaNombreProductos);
        dropCategoriaProducto.setSelectedItem(tablaCategoriaProductos);
        ventanaInicio.getFieldCantidadProductos().setText(tablaCantidadProductos);
        ventanaInicio.getFieldPrecioProductos().setText(tablaPrecioProductos);
    }
}
