/*
    Archivo: Proveedor.java
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
    CLASE: Proveedor

    INTENCIÓN: Esta clase modela los proveedores que surten los productos al Supermercado.
    Los proveedores son terceros a los que el Supermercado le compra productos. 
    Aunque la mayor parte de los atributos y métodos de los proveedores están definidos en la clase padre Tercero.java,
    se define una clase exclusiva para los proveedores pensando en la extensibilidad del programa (futuros nuevos atributos/funcionalidades de los proveedores que se necesiten a medida que el sistema crezca).
    Además la clase padre es una clase abstracta.

    RELACIONES:
    -Es un tercero por lo tanto contiene un HashMap de transacciones realizadas. También por herencia implementa las interfaces Listable y Serializable.
*/

package co.edu.univalle.modelo;

public class Proveedor extends Tercero{

    public Proveedor(Integer documento, String nombre, String correoElectronico, String telefono) {
        super(documento, nombre, correoElectronico, telefono);
    }
}
