/*
    Archivo: ControladorVentas.java
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
    CLASE: ControladorVentas

    INTENCIÓN: Esta es la clase controladora de la categoría ventas
    Esta clase se encarga orientar los eventos relacionados con las ventas del supermercado.
    
    RELACIONES:
*/

package co.edu.univalle.controlador;

import javax.swing.table.DefaultTableModel;

import co.edu.univalle.modelo.*;
import co.edu.univalle.vista.*;
import javax.swing.*;
import java.math.*;
import java.time.*;
import java.util.*;
import java.time.format.DateTimeParseException;

public class ControladorVentas {
    
    private static String[] labelVenta = {"ID de la venta", "Fecha de la venta", "Cédula del cliente", "Nombre del cliente", "Valor de la factura", "Lista de productos"};
    private static String encabezadoVenta[] = {"ID de la venta", "Fecha de la venta", "Cédula del cliente", "Nombre del cliente", "Número de productos", "Valor de la factura"};
    
    public static void pintar(VentanaInicio ventanaInicio, Integer serialVenta) {
        ventanaInicio.getLabelTexto()[0].setText(labelVenta[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdVenta());
        ventanaInicio.getFieldIdVenta().setText(String.valueOf(serialVenta));

        ventanaInicio.getLabelTexto()[3].setText(labelVenta[1]);
        ventanaInicio.getContenedorTexto()[3].add(ventanaInicio.getFieldFechaVenta());
        ventanaInicio.getFieldFechaVenta().setText(String.valueOf(LocalDate.now()));
        
        ventanaInicio.getLabelTexto()[1].setText(labelVenta[2]);
        ventanaInicio.getContenedorTexto()[1].add(ventanaInicio.getFieldCedulaClienteVenta());

        ventanaInicio.getLabelTexto()[2].setText(labelVenta[3]);
        ventanaInicio.getContenedorTexto()[2].add(ventanaInicio.getFieldNombresClienteVenta());
        
        ventanaInicio.getLabelTexto()[4].setText(labelVenta[5]);
        ventanaInicio.getContenedorTexto()[4].add(ventanaInicio.getbuttonListaProductosVenta());

        ventanaInicio.getLabelTexto()[5].setText(labelVenta[4]);
        ventanaInicio.getContenedorTexto()[5].add(ventanaInicio.getFieldCostoVenta());

    }

    public static void limpiar(VentanaInicio ventanaInicio, Integer serialVenta) {
        ventanaInicio.getFieldIdVenta().setText(String.valueOf(serialVenta));
        ventanaInicio.getFieldFechaVenta().setText("");
        ventanaInicio.getFieldCedulaClienteVenta().setText("");
        ventanaInicio.getFieldNombresClienteVenta().setText("");
        ventanaInicio.getFieldCostoVenta().setText("");
    }


    public static String[] getEncabezadoVenta() {
        return encabezadoVenta;
    }
    
    public static boolean revisarFieldsVentas(VentanaInicio ventanaInicio){
        String stringFechaVenta = ventanaInicio.getFieldFechaVenta().getText();
        try{
            LocalDate.parse(stringFechaVenta);

        } catch(DateTimeParseException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese una fecha válida, con el formato AAAA-MM-DD.\nEjemplo: 1990-01-01", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String stringIdCliente = ventanaInicio.getFieldCedulaClienteVenta().getText();
        try{
            Integer.valueOf(stringIdCliente);

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un número de documento válido, sin puntos ni espacios, solo números.\nEjemplo: 1144000000", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String nombreCliente = ventanaInicio.getFieldNombresClienteVenta().getText();
        if(nombreCliente.isEmpty() || nombreCliente.isBlank()){
            JOptionPane.showMessageDialog(null,"El número de documento ingresado no corresponde a ningún cliente registrado en el sistema.\nPor favor verfique que ingresó un número de documento válido, o registre al cliente desde la categoría \"Clientes\".", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String stringCostoTransaccion = ventanaInicio.getFieldCostoVenta().getText();
        try{
            BigDecimal costoTransaccion = BigDecimal.valueOf(Double.valueOf(stringCostoTransaccion));
            if(costoTransaccion == BigDecimal.ZERO)
                throw new NumberFormatException();

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"El valor de la factura debe ser un número diferente de cero. Debe agregar al menos un producto a la lista de productos de la venta.\nAgregue una lista de productos, haciendo clic sobre el botón \"Listar\".", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static Venta crearVenta(VentanaInicio ventanaInicio, HashMap<Integer, Integer> listaProductos){
        String stringIdVenta = ventanaInicio.getFieldIdVenta().getText();
        Integer idVenta = Integer.valueOf(stringIdVenta);
        String stringFechaVenta = ventanaInicio.getFieldFechaVenta().getText();
        LocalDate fechaVenta = LocalDate.parse(stringFechaVenta);
        String stringIdCliente = ventanaInicio.getFieldCedulaClienteVenta().getText();
        Integer idCliente = Integer.valueOf(stringIdCliente);
        String nombreCliente = ventanaInicio.getFieldNombresClienteVenta().getText();
        String stringCostoTransaccion = ventanaInicio.getFieldCostoVenta().getText();
        BigDecimal costoTransaccion = BigDecimal.valueOf(Double.valueOf(stringCostoTransaccion));

        Venta venta = new Venta(idVenta, fechaVenta, idCliente, nombreCliente, listaProductos, costoTransaccion);

        return venta;
    }


    public static void asignarTabla(DefaultTableModel modeloTabla, VentanaInicio ventanaInicio) {
        String tablaIdVenta = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
        String tablaFechaVenta = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
        String tablaCedulaClienteVenta = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
        String tablaNombreClienteVenta = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 3).toString();
        String tablaValorFacturaVenta = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 5).toString();

        ventanaInicio.getFieldIdVenta().setText(tablaIdVenta);
        ventanaInicio.getFieldFechaVenta().setText(tablaFechaVenta);
        ventanaInicio.getFieldCedulaClienteVenta().setText(tablaCedulaClienteVenta);
        ventanaInicio.getFieldNombresClienteVenta().setText(tablaNombreClienteVenta);
        ventanaInicio.getFieldCostoVenta().setText(tablaValorFacturaVenta);
    }
}
