/*
    Archivo: Producto.java
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
    CLASE: Producto

    INTENCIÓN: Esta clase modela los productos del Supermercado.
    Define los atributos y métodos propios relacionados con los productos.

    RELACIONES:
    -Implementa la interfaz Listable, pues los datos de los objetos de tipo producto se desplegarán en la tabla dispuesta para ello en la interfaz gráfica del programa.
    -Implementa la interfaz Serializable para permitir la persistencia del sistema en archivos de tipo binario.
*/

package co.edu.univalle.modelo;

import java.io.Serializable;
import java.math.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Producto implements Listable, Serializable {
    public static final long serialVersionUID = 1L;
    private static final int totalDatos = 5;
    private Integer identificacion;
    private String nombreProducto;
    private String categoriaProducto;
    private int cantidadStock;
    private BigDecimal precioVenta;
    
    public Producto(Integer identificacion, String nombreProducto, String categoriaProducto, BigDecimal precioVenta) {
        this.identificacion = identificacion;
        this.nombreProducto = nombreProducto;
        this.categoriaProducto = categoriaProducto;
        this.cantidadStock = 0;  // Cuando recién se crea un producto desde la categoría PRODUCTOS, el stock es cero. Hay que agregar stock comprándole producto a los proovedores.
        this.precioVenta = precioVenta;
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public void reducirStock(Integer cantidadAReducir){
        cantidadStock -= cantidadAReducir;
    }

    public void aumentarStock(Integer cantidadAAumentar){
        cantidadStock += cantidadAAumentar;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getTotalDatos(){
        return totalDatos;
    }

    public String getDato(int idDato){
        switch(idDato){
            case 0: 
                return String.valueOf(identificacion);
            case 1:
                return String.valueOf(nombreProducto);
            case 2: 
                return String.valueOf(categoriaProducto);
            case 3: 
                return String.valueOf(cantidadStock);
            case 4:
                DecimalFormatSymbols puntoDecimal = new DecimalFormatSymbols();
                puntoDecimal.setDecimalSeparator('.');
                DecimalFormat formato = new DecimalFormat("###.00", puntoDecimal);
                return String.valueOf(formato.format(precioVenta));
            default:
                return "";
        }
    }
    
    @Override
    public String toString(){
        String cadena = String.valueOf(identificacion) + " " + String.valueOf(nombreProducto);
        return cadena;
    }
}
