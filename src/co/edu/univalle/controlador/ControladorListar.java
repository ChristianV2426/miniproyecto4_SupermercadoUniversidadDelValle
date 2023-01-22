/*
    Archivo: ControladorListar.java
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

    INTENCIÓN: Esta es la clase controladora de la ventana auxiliar (VentanaListar).
    Esta clase se encarga orientar los eventos relacionados con la lista de productos involucrados en una transacción (bien sea venta o compra).
    
    RELACIONES:
*/

package co.edu.univalle.controlador;

import co.edu.univalle.modelo.*;
import co.edu.univalle.vista.*;
import java.util.*;
import java.math.*;
import javax.swing.*;
import java.text.*;
import javax.swing.table.DefaultTableModel;


public class ControladorListar {
    private static String encabezadoListaVenta[] = {"ID del producto", "Nombre del producto", "Cantidad"};
    private static String encabezadoListaCompra[] = {"ID del producto", "Nombre del producto", "Cantidad", "Costo"};
    
    public static void limpiar(VentanaListar ventanaListar, String categoria) {

        if(categoria == "Ventas (a clientes)"){
            ventanaListar.getId().setText("");
            ventanaListar.getCantidad().setText("");


        } else if(categoria == "Compras (a proveedores)"){
            ventanaListar.getId().setText("");
            ventanaListar.getCantidad().setText("");
            ventanaListar.getCosto().setText("");
        }
    }

    public static String[] getEncabezadoListaVenta() {
        return encabezadoListaVenta;
    }

    public static String[] getEncabezadoListaCompra() {
        return encabezadoListaCompra;
    }

    public static boolean revisarFieldsProductoEnVenta(VentanaListar ventanaListados){
        String stringIdProducto = ventanaListados.getId().getText();
        try{
            Integer.valueOf(stringIdProducto);

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un número de identificación de producto válido, sin puntos ni espacios, solo números.\nEjemplo: 1250", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String stringCantidadProducto = ventanaListados.getCantidad().getText();
        try{
            Integer cantidadProducto = Integer.valueOf(stringCantidadProducto);
            if(cantidadProducto <= 0)
                throw new NumberFormatException();

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un número de productos válido. ¡Solo números enteros mayores a cero!\nEjemplo: 5", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static boolean revisarFieldsProductoEnCompra(VentanaListar ventanaListados){
        if(!revisarFieldsProductoEnVenta(ventanaListados))
            return false;

        String stringCostoProducto = ventanaListados.getCosto().getText();
        try{
            BigDecimal costoProducto = BigDecimal.valueOf(Double.valueOf(stringCostoProducto));
            if(costoProducto.compareTo(BigDecimal.ZERO) <= 0)
                throw new NumberFormatException();

        } catch (NumberFormatException exception){
            JOptionPane.showMessageDialog(null,"Por favor ingrese un costo de compra válido. El costo debe escribirse sin puntos ni espacios, solo números mayores a cero.\nEjemplo: 35000 (treinta y cinco mil pesos)", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public static String calcularCostoVenta(Supermercado supermercado, HashMap<Integer, Integer> listaProductos){
        BigDecimal valorFactura = BigDecimal.ZERO;
        for(Map.Entry<Integer, Integer> pareja : listaProductos.entrySet()){
            BigDecimal cantidad = BigDecimal.valueOf(Double.valueOf(pareja.getValue()));
            valorFactura = valorFactura.add(supermercado.getProductos().getElemento(pareja.getKey()).getPrecioVenta().multiply(cantidad));
        }
        DecimalFormatSymbols puntoDecimal = new DecimalFormatSymbols();
        puntoDecimal.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("###.00", puntoDecimal);
        return String.valueOf(formato.format(valorFactura));
    }

    public static String calcularCostoCompra(Supermercado supermercado, HashMap<Integer, Integer> listaProductos, HashMap<Integer, BigDecimal> listaPrecios){
        BigDecimal valorFactura = BigDecimal.ZERO;
        for(Map.Entry<Integer, Integer> parejaCantidad : listaProductos.entrySet()){
            BigDecimal cantidad = BigDecimal.valueOf(Double.valueOf(parejaCantidad.getValue()));
            BigDecimal costo = listaPrecios.get(parejaCantidad.getKey());
            valorFactura = valorFactura.add(costo.multiply(cantidad));
        }
        DecimalFormatSymbols puntoDecimal = new DecimalFormatSymbols();
        puntoDecimal.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("###.00", puntoDecimal);
        return String.valueOf(formato.format(valorFactura));
    }

    public static void asignarTabla(DefaultTableModel modeloTabla, VentanaListar ventanaListar, String categoria) {

        if(categoria == "Ventas (a clientes)"){
            String tablaIdVentasListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 0).toString();
            String tablaNombreListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 1).toString();
            String tablaCantidadListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 2).toString();

            ventanaListar.getId().setText(tablaIdVentasListado);
            ventanaListar.getNombre().setText(tablaNombreListado);
            ventanaListar.getCantidad().setText(tablaCantidadListado);
            
        } else if(categoria == "Compras (a proveedores)"){
            String tablaIdVentasListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 0).toString();
            String tablaNombreListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 1).toString();
            String tablaCantidadListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 2).toString();
            String tablaCostoListado = modeloTabla.getValueAt(ventanaListar.getTablaDatos().getSelectedRow(), 3).toString();

            ventanaListar.getId().setText(tablaIdVentasListado);
            ventanaListar.getNombre().setText(tablaNombreListado);
            ventanaListar.getCantidad().setText(tablaCantidadListado);
            ventanaListar.getCosto().setText(tablaCostoListado);
        }
    }
}