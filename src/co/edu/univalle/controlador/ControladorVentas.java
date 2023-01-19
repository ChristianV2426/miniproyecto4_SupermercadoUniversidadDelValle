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

import co.edu.univalle.modelo.*;
import co.edu.univalle.vista.*;
import javax.swing.*;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeParseException;

public class ControladorVentas {
    
    private static String[] labelVenta = {"ID de la venta ", "Fecha de la venta ", "Cédula del cliente ", "Nombre del cliente ", "Valor de la factura ", "Lista de productos "};
    private static String encabezadoVenta[] = {"ID de la venta", "Fecha de la venta", "Cédula del cliente", "Nombre del cliente", "Número de productos", "Valor de la factura"};
    
    public static void pintar(VentanaInicio ventanaInicio) {
        ventanaInicio.getLabelTexto()[0].setText(labelVenta[0]);
        ventanaInicio.getContenedorTexto()[0].add(ventanaInicio.getFieldIdVenta());

        ventanaInicio.getLabelTexto()[1].setText(labelVenta[1]);
        ventanaInicio.getContenedorTexto()[1].add(ventanaInicio.getFieldFechaVenta());
        
        ventanaInicio.getLabelTexto()[2].setText(labelVenta[2]);
        ventanaInicio.getContenedorTexto()[2].add(ventanaInicio.getFieldCedulaClienteVenta());

        ventanaInicio.getLabelTexto()[3].setText(labelVenta[3]);
        ventanaInicio.getContenedorTexto()[3].add(ventanaInicio.getFieldNombresClienteVenta());
        
        ventanaInicio.getLabelTexto()[4].setText(labelVenta[5]);
        ventanaInicio.getContenedorTexto()[4].add(ventanaInicio.getbuttonListaProductosVenta());

        ventanaInicio.getLabelTexto()[5].setText(labelVenta[4]);
        ventanaInicio.getContenedorTexto()[5].add(ventanaInicio.getFieldCostoVenta());

    }

    public static void limpiar(VentanaInicio ventanaInicio) {
        ventanaInicio.getFieldIdVenta().setText("");
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



        String stringCostoTransaccion = ventanaInicio.getFieldCostoVenta().getText();
        try{
            BigDecimal.valueOf(Double.valueOf(stringCostoTransaccion));

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un precio de venta válido. El precio debe escribirse sin puntos ni espacios, solo números.\nEjemplo: 45000 (cuarenta y cinco mil pesos)", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static Venta crearVenta(VentanaInicio ventanaInicio){
        String stringIdVenta = ventanaInicio.getFieldIdVenta().getText();
        Integer idVenta = Integer.valueOf(stringIdVenta);
        String stringFechaVenta = ventanaInicio.getFieldFechaVenta().getText();
        LocalDate fechaVenta = LocalDate.parse(stringFechaVenta);
        String stringIdCliente = ventanaInicio.getFieldCedulaClienteVenta().getText();
        Integer idCliente = Integer.valueOf(stringIdCliente);
        String nombreCliente = ventanaInicio.getFieldNombresClienteVenta().getText();

        String stringCostoTransaccion = ventanaInicio.getFieldCostoVenta().getText();
        BigDecimal costoTransaccion = BigDecimal.valueOf(Double.valueOf(stringCostoTransaccion));

        // Venta venta = new Venta(idVenta, fechaVenta, idCliente, nombreCliente, null, costoTransaccion)

        return null;
    }


}
