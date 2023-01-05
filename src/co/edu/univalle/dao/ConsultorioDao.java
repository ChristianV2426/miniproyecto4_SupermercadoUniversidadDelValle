/*
    Archivo: ConsultorioDao.java
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
    Interfaz: ConsultorioDao

    INTENCIÓN: Esta es la interfaz que implementará la clase ConsultorioDaoImpl. 

    RELACIONES:
    -Hereda la interfaz DaoGeneral.
*/

package co.edu.univalle.dao;

import co.edu.univalle.modelo.*;
import java.util.*;

public interface ConsultorioDao  extends DaoGeneral<Consultorio>{
    public abstract List<Consultorio> obtenerConsultoriosConCita();
    public abstract List<Consultorio> obtenerConsultoriosConEspecialidad(ServicioMedico servicioMedico);
    public abstract String[] arregloConsultoriosConEspecialidad(ServicioMedico servicioMedico);
}
