/*
    Archivo: DaoGeneral.java
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
    Interfaz: DaoGeneral

    INTENCIÓN: Esta interfaz declara los métodos básicos de los DAO del sistema, utilizando datos genéricos. 

    RELACIONES: ninguna
*/

package co.edu.univalle.dao;

public interface DaoGeneral<T> {
    public abstract boolean añadir(T t);
    public abstract boolean elementoPresente(Integer identificacion);
    public abstract T getElemento(Integer identificacion);
    public abstract String[][] getListables();
    public abstract boolean actualizar(Integer identificacion, T t);
    public abstract boolean eliminar(Integer identificacion);
}
