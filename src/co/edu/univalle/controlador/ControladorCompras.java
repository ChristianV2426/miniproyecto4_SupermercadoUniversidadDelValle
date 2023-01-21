/*
    Archivo: ControladorCompras.java
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
    CLASE: ControladorCompras

    INTENCIÓN: Esta es la clase controladora de la categoría compras.
    Esta clase se encarga orientar los eventos relacionados con las compras del supermercado.
    
    RELACIONES:
*/

package co.edu.univalle.controlador;

import javax.swing.table.DefaultTableModel;

import co.edu.univalle.modelo.*;
import co.edu.univalle.vista.*;
import java.time.*;
import javax.swing.*;
import java.math.*;
import java.time.format.DateTimeParseException;

public class ControladorCompras {
    
    private static String[] labelCompra = {"ID de la compra", "Fecha de la compra", "NIT del proveedor", "Nombre del proveedor", "Valor de la factura", "Lista de productos"};
    private static String[] encabezadoCompra = {"ID de la compra", "Fecha de la compra", "NIT del proveedor", "Nombre del proveedor", "Número de productos", "Valor de la factura"};
    
    public static void pintar(VentanaInicio ventanaInicio, Integer serialCompra) {
        ventanaInicio.getLabelTexto()[0].setText(labelCompra[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdCompra());
        ventanaInicio.getFieldIdCompra().setText(String.valueOf(serialCompra));

        ventanaInicio.getLabelTexto()[2].setText(labelCompra[1]);
        ventanaInicio.getContenedorTexto()[2].add(ventanaInicio.getFieldFechaCompra());
        
        ventanaInicio.getLabelTexto()[1].setText(labelCompra[2]);
        ventanaInicio.getContenedorTexto()[1].add(ventanaInicio.getFieldNitProveedorCompra());

        ventanaInicio.getLabelTexto()[3].setText(labelCompra[3]);
        ventanaInicio.getContenedorTexto()[3].add(ventanaInicio.getFieldNombreProveedorCompra());
        
        ventanaInicio.getLabelTexto()[4].setText(labelCompra[5]);
        ventanaInicio.getContenedorTexto()[4].add(ventanaInicio.getbuttonListaProductosCompra());

        ventanaInicio.getLabelTexto()[5].setText(labelCompra[4]);
        ventanaInicio.getContenedorTexto()[5].add(ventanaInicio.getFieldCostoCompra());

    }

    public static void limpiar(VentanaInicio ventanaInicio, Integer serialCompra) {
        ventanaInicio.getFieldIdCompra().setText(String.valueOf(serialCompra));
        ventanaInicio.getFieldFechaCompra().setText("");
        ventanaInicio.getFieldNitProveedorCompra().setText("");
        ventanaInicio.getFieldNombreProveedorCompra().setText("");
        ventanaInicio.getFieldCostoCompra().setText("");
    }

    public static String[] getEncabezadoCompra() {
        return encabezadoCompra;
    }

    public static boolean revisarFieldsCompras(VentanaInicio ventanaInicio){
        String stringFechaCompra = ventanaInicio.getFieldFechaCompra().getText();
        try{
            LocalDate.parse(stringFechaCompra);

        } catch(DateTimeParseException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese una fecha válida, con el formato AAAA-MM-DD.\nEjemplo: 1990-01-01", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String stringIdProveedor = ventanaInicio.getFieldNitProveedorCompra().getText();
        try{
            Integer.valueOf(stringIdProveedor);

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un número de NIT válido, sin puntos ni espacios, solo números.\nEjemplo: 77581411", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }



        String stringCostoTransaccion = ventanaInicio.getFieldCostoCompra().getText();
        try{
            BigDecimal.valueOf(Double.valueOf(stringCostoTransaccion));

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un precio de compra válido. El precio debe escribirse sin puntos ni espacios, solo números.\nEjemplo: 45000 (cuarenta y cinco mil pesos)", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    
    public static Compra crearCompra(VentanaInicio ventanaInicio){
        String stringIdCompra = ventanaInicio.getFieldIdCompra().getText();
        Integer idCompra = Integer.valueOf(stringIdCompra);
        String stringFechaCompra = ventanaInicio.getFieldFechaCompra().getText();
        LocalDate fechaCompra = LocalDate.parse(stringFechaCompra);
        String stringIdProveedor = ventanaInicio.getFieldNitProveedorCompra().getText();
        Integer idProveedor = Integer.valueOf(stringIdProveedor);
        String nombreProveedor = ventanaInicio.getFieldNombreProveedorCompra().getText();

        String stringCostoTransaccion = ventanaInicio.getFieldCostoCompra().getText();
        BigDecimal costoTransaccion = BigDecimal.valueOf(Double.valueOf(stringCostoTransaccion));


        // Compra compra = new Compra(idCompra, fechaCompra, idProveedor, nombreProveedor, null, costoTransaccion, null);
        return null;
    }

    public static boolean revisarIDCompra(VentanaInicio ventanaInicio){

        return true;
    }

    public static void asignarTabla(DefaultTableModel modeloTabla, VentanaInicio ventanaInicio) {
        String tablaIdCompra = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
        String tablaFechaCompra = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
        String tablaNitProveedorCompra = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
        String tablaNombreProveedorCompra = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 3).toString();
        String tablaValorFacturaCompra = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 5).toString();

        ventanaInicio.getFieldIdCompra().setText(tablaIdCompra);
        ventanaInicio.getFieldFechaCompra().setText(tablaFechaCompra);
        ventanaInicio.getFieldNitProveedorCompra().setText(tablaNitProveedorCompra);
        ventanaInicio.getFieldNombreProveedorCompra().setText(tablaNombreProveedorCompra);
        ventanaInicio.getFieldCostoCompra().setText(tablaValorFacturaCompra);
    }
}
