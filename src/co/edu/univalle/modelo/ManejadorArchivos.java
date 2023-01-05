/*
    Archivo: ManejadorArchivos.java
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
    CLASE: ManejadorArchivos

    INTENCIÓN: Esta clase se encargará de implementar la lógica referente al manejo de archivos binarios y planos en el programa. 
    
    RELACIONES: Ninguna
*/

package co.edu.univalle.modelo;

import co.edu.univalle.dao.*;
import java.io.*;
import java.time.*;
import java.util.*;

public class ManejadorArchivos {

    public ManejadorArchivos(){
    }

    /**
     * Restaura el estado del programa, usando los datos guardados en el archivo binario cuya ruta se pasa como argumento del método.
     * Retorna un objeto de tipo EntidadSalud con los datos leídos en archivo.
     * Si el archivo binario pasado como argumento no existe o está vacío, se devuelve un objeto null.
     */
    public static EntidadSalud leerArchivoBinario(String rutaArchivo){
        ObjectInputStream objInputStream = null;
        EntidadSalud entidadSalud = null;

        try{
            objInputStream = new ObjectInputStream(new FileInputStream(rutaArchivo));
            entidadSalud = (EntidadSalud) objInputStream.readObject();
            System.out.println("¡Archivo binario leído!");

        } catch(FileNotFoundException exception) {
            System.out.println("El archivo binario aun no ha sido creado. ");

        } catch(IOException exception){
            System.out.println("Error leyendo el archivo binario. " + exception.getMessage());

        } catch(ClassNotFoundException exception) {
            System.out.println("Conflicto entre clases. " + exception.getMessage());

        } finally {
            if(objInputStream != null)
                try {
                    objInputStream.close();
                } catch (IOException exception) {
                    System.out.println("Error cerrando el archivo objInputStream" + exception.getMessage());
                }
        }
        return entidadSalud;
    }
    
    /**
     * Guarda el estado del programa, utilizando los datos del objeto de tipo EntidadSalud pasado como primer argumento del método.
     * La función guarda el estado del programa en el archvio cuya ruta se pasa como segundo argumento del método.
     * Retorna true si se pudo guardar el estado del programa en el archivo binario. De lo contrario, retorna false y no se pudo completar la operación. 
     */
    public static boolean guardarArchivoBinario(EntidadSalud entidadSalud, String rutaArchivo){
        boolean operacionRealizada = false;
        ObjectOutputStream objOutputStream = null;

        try{
            objOutputStream = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            objOutputStream.writeObject(entidadSalud);
            System.out.println("¡Archivo binario guardado!");
            operacionRealizada = true;

        } catch(FileNotFoundException exception){ 
            System.out.println("Archivo binario no encontrado." + exception.getMessage());

        } catch(IOException exception){
            System.out.println("Error guardando el archivo binario. " + exception.getMessage() + exception.getStackTrace());

        } finally{
            if(objOutputStream != null){
                try {
                    objOutputStream.close();
                } catch (IOException exception) {
                    System.out.println("Error cerrando el archivo objOutputStream" + exception.getMessage());
                }
            }
        }
        return operacionRealizada;
    }

    /**
     * Guarda los datos de los afiliados registrados en el sistema en un archivo plano separado por punto y coma. El archivo plano es guardado en la ruta que se pasa como argumento del método.
     * Retorna true si la operación se realizó con éxito.
     * Retorna false si no hay empleados registrados en el sistema, o si se encuentra alguna novedad durante el proceso que impide la generación del archivo de texto plano. 
     */
    public static boolean generarArchivoAfiliados(EntidadSalud entidadSalud, String rutaDirectorio){
        AfiliadoDao daoAfiliados = entidadSalud.getDaoAfiliados();
        List<Afiliado> listaAfiliados = daoAfiliados.obtenerLista();
        boolean operacionRealizada = false;

        if(listaAfiliados.isEmpty()){
            System.out.println("No hay afiliados registrados en el sistema. No se puede generar la lista de afiliados en texto plano");
            return operacionRealizada;
        }

        int numeroFilas = listaAfiliados.size();
        int numeroColumnas = listaAfiliados.get(0).getTotalDatos();
        LocalDateTime fechaYHora = LocalDateTime.now();
        String identificador = String.valueOf(
            fechaYHora.getYear() + "-" + fechaYHora.getMonthValue() + "-" +  fechaYHora.getDayOfMonth() + " -- " +
            fechaYHora.getHour() + "h " + fechaYHora.getMinute() + "m " + fechaYHora.getSecond() + "s");
        PrintWriter escritorDeArchivo = null;

        try{
            escritorDeArchivo = new PrintWriter(
                new FileWriter(rutaDirectorio + "/Afiliados " + identificador + ".txt"));

        } catch (IOException exception){
            System.out.println("Error alistando el archivo de texto plano. " + exception.getMessage());
            return false;
        }

        for(int fila=0; fila < numeroFilas; fila++){
            for(int columna=0; columna < numeroColumnas; columna++){
                escritorDeArchivo.print(listaAfiliados.get(fila).getDato(columna) + ";");
            }
            escritorDeArchivo.print("\n");
        }

        if(escritorDeArchivo != null){
            try{
                escritorDeArchivo.close();
                operacionRealizada = true;

            } catch (Exception exception){
                System.out.println("Error cerrando el archivo plano. " + exception.getMessage());
            }
        }
        return operacionRealizada;
    }
    
    /**
     * Realiza un backup de las citas registradas en el programa. Recibe como argumentos el objeto de tipo EntidadSalud que lleva el control del sistema de citas
     * y la ruta del directorio donde se guardará el backup en formato de archivo binario. 
     * Retorna true si logra realizar el backup. De lo contrario, retorna false. 
     */
    public static boolean generarBackupCitas(EntidadSalud entidadSalud, String rutaDirectorio){
        CitaDao daoCitas = entidadSalud.getDaoCitas();
        boolean operacionRealizada = false;

        if(daoCitas.obtenerLista().isEmpty()){
            System.out.println("No hay citas registradas en el sistema. No se puede generar backup de citas");
            return operacionRealizada;
        }

        FileOutputStream backup = null;
        ObjectOutputStream objOutputStream = null;
        LocalDateTime fechaYHora = LocalDateTime.now();
        String identificador = String.valueOf(
            fechaYHora.getYear() + "-" + fechaYHora.getMonthValue() + "-" +  fechaYHora.getDayOfMonth() + " -- " +
            fechaYHora.getHour() + "h " + fechaYHora.getMinute() + "m " + fechaYHora.getSecond() + "s");

        try{
            backup = new FileOutputStream(rutaDirectorio + "/Backup citas " + identificador + ".bin");
            objOutputStream = new ObjectOutputStream(backup);
            objOutputStream.writeObject(daoCitas);
            System.out.println("¡Backup guardado!");
            operacionRealizada = true;

        } catch(FileNotFoundException exception){ 
            System.out.println("Directorio no encontrado." + exception.getMessage());

        } catch(IOException exception){
            System.out.println("Error al realizar el backup. " + exception.getMessage() + exception.getStackTrace());

        } finally{
            if(objOutputStream != null){
                try {
                    objOutputStream.close();
                } catch (IOException exception) {
                    System.out.println("Error cerrando el archivo objOutputStream" + exception.getMessage());
                }
            }
        }
        return operacionRealizada;
    }

    /**
     * Restaura la información de las citas del sistema, devolviendo un objeto de tipo CitaDao extraído del archivo binario cuya ruta se pasa como argumento del método.
     * Se espera que posteriormente este objeto retornado sea incorporado al objeto tipo EntidadSalud que lleve el control del sistema de citas.
     * En caso de no poder leer el archivo binario, retorna null.
     */
    public static CitaDao restaurarCitasDesdeBackup(String rutaArchivo){
        ObjectInputStream objInputStream = null;
        CitaDao daoCitasBackup = null;

        try{
            objInputStream = new ObjectInputStream(new FileInputStream(rutaArchivo));
            daoCitasBackup = (CitaDaoImpl) objInputStream.readObject();
            System.out.println("¡Backup correctamente cargado!");

        } catch(FileNotFoundException exception) {
            System.out.println("El archivo backup no existe. Por favor ingrese una ruta del archivo valida. " + exception.getMessage());

        } catch(IOException exception){
            System.out.println("Error leyendo el archivo backup. " + exception.getMessage());

        } catch(ClassNotFoundException exception) {
            System.out.println("Conflicto entre clases. " + exception.getMessage());

        } finally {
            if(objInputStream != null)
                try {
                    objInputStream.close();
                } catch (IOException exception) {
                    System.out.println("Error cerrando el archivo objInputStream" + exception.getMessage());
                }
        }
        return daoCitasBackup;
    }
}
