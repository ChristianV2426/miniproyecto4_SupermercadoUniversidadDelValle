/*
    Archivo: ServicioMedicoDao.java
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
    Interfaz: ServicioMedicoDao

    INTENCIÓN: Esta es la interfaz que implementará la clase ServicioMedicoDaoImpl. 

    RELACIONES:
    -Hereda la interfaz DaoGeneral.
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;

public interface ServicioMedicoDao extends DaoGeneral<ServicioMedico>{
    public abstract String[] obtenerArreglo();
}
