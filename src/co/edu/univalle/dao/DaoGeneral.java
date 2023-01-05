/*
    Archivo: DaoGeneral.java
    Fundamentos de Programación Orientada a Eventos - 750014C Grupo 01
    Proyecto 3 - Servicio de Salud Universidad del Valle

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

import java.util.*;

public interface DaoGeneral<T> {
    public abstract boolean añadir(T t);
    public abstract boolean elementoPresente(Integer id);
    public abstract T obtenerElemento(Integer id);
    public abstract List<T> obtenerLista();
    public abstract boolean actualizar(Integer id, T t);
    public abstract boolean eliminar(Integer id);
}
