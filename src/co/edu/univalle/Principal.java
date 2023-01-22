/*
    Archivo: Principal.java
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
    CLASE: Principal

    INTENCIÓN: Esta es la clase principal, desde donde se inicia el programa. 
    
    RELACIONES:
    -Inicializa una ventaInicio y un ControladorVentanaInicio, que se encargan de arrancar el programa.
*/

package co.edu.univalle;

import co.edu.univalle.controlador.*;
import co.edu.univalle.vista.*;

public class Principal {
    public static void main(String[] args) {
        VentanaInicio ventanaInicio = new VentanaInicio();
        ControladorVentanaInicio actualizarVentanaInicio = new ControladorVentanaInicio(ventanaInicio);
    }
}