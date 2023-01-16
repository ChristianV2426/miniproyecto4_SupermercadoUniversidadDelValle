/*
    Archivo: Principal.java
    Fundamentos de Programación Orientada a Eventos - 750014C Grupo 01
    Proyecto 3 - Servicio de Salud
    Autores: 
    Juan Camilo Narvaez Tascon - juan.narvaez.tascon@correounivalle.edu.co - 2140112-3743
    Christian David Vargas Gutiérrez - vargas.christian@correounivalle.edu.co - 2179172-3743
    Profesor:
    Ing. M.Sc. Luis Yovany Romo Portilla
    Licencia: GNU-GPL
*/

/**
    HISTORIA: --
*/

package co.edu.univalle.vista;

import co.edu.univalle.controlador.ControladorVentanaInicio;

public class Principal {
    public static void main(String[] args) {
        VentanaInicio ventanaInicio = new VentanaInicio();
        ControladorVentanaInicio actualizarVentanaInicio = new ControladorVentanaInicio(ventanaInicio);
    }
}