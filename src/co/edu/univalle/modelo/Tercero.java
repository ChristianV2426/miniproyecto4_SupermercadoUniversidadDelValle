/*
    Archivo: Tercero.java
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
    CLASE: Tercero

    INTENCIÓN: Esta clase modela los terceros con los que el Supermercado tiene relaciones comerciales.
    Un tercero podrá ser un cliente que está interesado en comprarle productos al Supermercado, o un proveedor al que el Supermercado le compra productos.
    La idea es que las clases Cliente.java y Proovedor.java hereden de esta clase. 

    RELACIONES:
    -Contiene un HashMap donde guarda la lista de transacciones realizadas (estas transacciones podrán ser ventas o compras, según sea el caso).
    -Implementa la interfaz Listable, pues los datos de los terceros se desplegarán en la tabla dispuesta para ello en la interfaz gráfica del programa.
    -Implementa la interfaz Serializable para permitir la persistencia del sistema en archivos de tipo binario.
*/

package co.edu.univalle.modelo;

import java.io.Serializable;
import java.util.*;

public abstract class Tercero implements Listable, Serializable{
    public static final long serialVersionUID = 1L;
    private static final int totalDatos = 5;
    private Integer identificacion;
    private String nombre;
    private String correoElectronico;
    private String telefono;
    private HashMap<Integer, Transaccion> transaccionesRealizadas;
    
    public Tercero(Integer identificacion, String nombre, String correoElectronico, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.transaccionesRealizadas = new HashMap<>();
    }

    public Integer getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public HashMap<Integer, Transaccion> getTransaccionesRealizadas() {
        return transaccionesRealizadas;
    }

    public boolean agregarTransaccion(Transaccion transaccion){
        if (transaccionesRealizadas.containsKey(transaccion.getIdentificacion()))
            return false;
        
        transaccionesRealizadas.put(transaccion.getIdentificacion(), transaccion);
        return true;
    }

    public boolean eliminarTransaccion(Transaccion transaccion){
        if (!transaccionesRealizadas.containsKey(transaccion.getIdentificacion()))
            return false;
        
        transaccionesRealizadas.remove(transaccion.getIdentificacion());
        return true;
    }

    public int getNumeroTransaccionesRealizadas(){
        return transaccionesRealizadas.size();
    }

    public int getTotalDatos(){
        return totalDatos;
    }

    public String getDato(int idDato){
        switch(idDato){
            case 0:
                return String.valueOf(identificacion);
            case 1: 
                return String.valueOf(nombre);
            case 2:
                return String.valueOf(correoElectronico);
            case 3:
                return String.valueOf(telefono);
            case 4:
                return String.valueOf(transaccionesRealizadas.size());
            default:
                return "";
        }
    }
    
    @Override
    public String toString(){
        String cadena = String.valueOf(identificacion) + " " + String.valueOf(nombre);
        return cadena;
    }
}
