/*
    Archivo: Listable.java
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
    Interfaz: Listable

    INTENCIÓN: Esta interfaz define los métodos que deben implementar todas las clases susceptibles a ser desplegadas 
    en la tabla de datos de la interfaz gráfica del programa. 

    RELACIONES: ninguna
*/

package co.edu.univalle.modelo;

public interface Listable{
    public abstract Integer getIdentificacion();
    public abstract int getTotalDatos();
    public abstract String getDato(int idDato);
}
