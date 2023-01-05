/*
    Archivo: AfiliadoDao.java
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
    Interfaz: AfiliadoDao

    INTENCIÓN: Esta es la interfaz que implementará la clase AfiliadoDaoImpl. 

    RELACIONES:
    -Hereda la interfaz DaoGeneral.
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.util.*;

public interface AfiliadoDao extends DaoGeneral<Afiliado>{
    public abstract List<Afiliado> obtenerAfiliadosConCita();
}
