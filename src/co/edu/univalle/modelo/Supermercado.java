/*
    Archivo: Supermercado.java
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
    CLASE: Supermercado

    INTENCIÓN: Esta clase modela el Supermercado.
    En este programa en particular, el Supermercado es quien administra los elementos del sistema: los productos, los clientes, los proveedores, las ventas y las compras.

    RELACIONES:
    -Contiene 5 objetos de tipo DAO, uno por cada tipo de objeto del sistema (producto, cliente, proveedor, venta, compra).
    -Implementa la interfaz Serializable para permitir la persistencia del sistema en archivos de tipo binario.
*/

package co.edu.univalle.modelo;

import co.edu.univalle.dao.*;
import java.io.Serializable;

public class Supermercado implements Serializable {
    public static final long serialVersionUID = 1L;
    public String nombreSupermercado;
    private ProductoDao productos;
    private ClienteDao clientes;
    private ProveedorDao proveedores;
    private VentaDao ventas;
    private CompraDao compras;
    private Integer serialProducto = 1000;
    private Integer serialVenta = 200000;
    private Integer serialCompra = 300000;

    public Supermercado(String nombreSupermercado) {
        this.nombreSupermercado = nombreSupermercado;
        this.productos = new ProductoDaoImpl();
        this.clientes = new ClienteDaoImpl();
        this.proveedores = new ProveedorDaoImpl();
        this.ventas = new VentaDaoImpl();
        this.compras = new CompraDaoImpl();
    }

    public String getNombreSupermercado() {
        return nombreSupermercado;
    }

    public void setNombreSupermercado(String nombreSupermercado) {
        this.nombreSupermercado = nombreSupermercado;
    }

    public ProductoDao getProductos() {
        return productos;
    }

    public void setProductos(ProductoDao productos) {
        this.productos = productos;
    }

    public ClienteDao getClientes() {
        return clientes;
    }

    public void setClientes(ClienteDao clientes) {
        this.clientes = clientes;
    }

    public ProveedorDao getProveedores() {
        return proveedores;
    }

    public void setProveedores(ProveedorDao proveedores) {
        this.proveedores = proveedores;
    }

    public VentaDao getVentas() {
        return ventas;
    }

    public void setVentas(VentaDao ventas) {
        this.ventas = ventas;
    }

    public CompraDao getCompras() {
        return compras;
    }

    public void setCompras(CompraDao compras) {
        this.compras = compras;
    }

    public Integer getSerialProducto() {
        return serialProducto;
    }

    public void setSerialProducto(Integer serialProducto) {
        this.serialProducto = serialProducto;
    }

    public void sumarSerialProducto(){
        serialProducto++;
    }

    public Integer getSerialVenta() {
        return serialVenta;
    }

    public void setSerialVenta(Integer serialVenta) {
        this.serialVenta = serialVenta;
    }

    public void sumarSerialVenta(){
        serialVenta++;
    }

    public Integer getSerialCompra() {
        return serialCompra;
    }

    public void setSerialCompra(Integer serialCompra) {
        this.serialCompra = serialCompra;
    }

    public void sumarSerialCompra(){
        serialCompra++;
    }
    
}
