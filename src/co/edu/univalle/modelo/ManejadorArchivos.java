/*
    Archivo: ManejadorArchivos.java
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
    CLASE: ManejadorArchivos

    INTENCIÓN: Esta clase se encargará de implementar la lógica referente al manejo de archivos binarios y planos en el programa. 
    
    RELACIONES: Ninguna
*/

package co.edu.univalle.modelo;

import java.io.*;
import java.time.*;

public class ManejadorArchivos {

    public ManejadorArchivos(){
    }

    public static Supermercado leerArchivoBinario(String rutaArchivo){
        ObjectInputStream objInputStream = null;
        Supermercado supermercado = null;

        try{
            objInputStream = new ObjectInputStream(new FileInputStream(rutaArchivo));
            supermercado = (Supermercado) objInputStream.readObject();
            System.out.println("¡Archivo binario leído!");

        } catch(FileNotFoundException exception) {
            System.out.println("El archivo binario aun no ha sido creado. No se preocupe, el archivo se generará automaticamente al cerrar la aplicación");

        } catch(IOException exception){
            System.out.println("Error leyendo el archivo binario.");
            // System.out.println("Error leyendo el archivo binario. " + exception.getMessage());

        } catch(ClassNotFoundException exception) {
            System.out.println("Conflicto entre clases.");
            // System.out.println("Conflicto entre clases." + exception.getMessage());

        } finally {
            if(objInputStream != null)
                try {
                    objInputStream.close();
                } catch (IOException exception) {
                    System.out.println("Error cerrando el archivo objInputStream.");
                    // System.out.println("Error cerrando el archivo objInputStream" + exception.getMessage());
                }
        }
        return supermercado;
    }

    public static boolean guardarEnArchivoBinario(Supermercado supermercado, String rutaArchivo){
        boolean operacionRealizada = false;
        ObjectOutputStream objOutputStream = null;

        try{
            objOutputStream = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
            objOutputStream.writeObject(supermercado);
            System.out.println("¡Archivo binario guardado!");
            operacionRealizada = true;

        } catch(FileNotFoundException exception){ 
            System.out.println("Hay un problema con la ruta donde se guardará el archivo binario. Por favor verificar la ruta.");
            // System.out.println("Hay un problema con la ruta donde se guardará el archivo binario. Por favor verificar la ruta." + exception.getMessage());

        } catch(IOException exception){
            System.out.println("Error guardando el archivo binario.");
            // System.out.println("Error guardando el archivo binario. " + exception.getMessage() + exception.getStackTrace());

        } finally {
            if(objOutputStream != null){
                try {
                    objOutputStream.close();
                } catch (IOException exception) {
                    System.out.println("Error cerrando el archivo objOutputStream.");
                    // System.out.println("Error cerrando el archivo objOutputStream" + exception.getMessage());
                }
            }
        }
        return operacionRealizada;
    }

    public static boolean guardarEnArchivoTextoPlano(Supermercado supermercado, String rutaDirectorio){
        String[][] listaProductos = supermercado.getProductos().getListables();
        String[][] listaClientes = supermercado.getClientes().getListables();
        String[][] listaProveedores = supermercado.getProveedores().getListables();
        String[][] listaVentas = supermercado.getVentas().getListables();
        String [][] listaCompras = supermercado.getCompras().getListables();
        boolean operacionRealizada = false;

        LocalDateTime fechaYHora = LocalDateTime.now();
        String identificador = String.valueOf(
            fechaYHora.getYear() + "-" + fechaYHora.getMonthValue() + "-" +  fechaYHora.getDayOfMonth() + " -- " +
            fechaYHora.getHour() + "h " + fechaYHora.getMinute() + "m " + fechaYHora.getSecond() + "s" );

        PrintWriter escritorDeArchivo = null;
        String pieDePagina = "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        String configuracionImpresion = "%-40s";
        try{
            escritorDeArchivo = new PrintWriter(new FileWriter(rutaDirectorio + "/Copia Seguridad " + identificador + ".txt"));

            escritorDeArchivo.print("Fecha: " + fechaYHora.getYear() + "-" + fechaYHora.getMonthValue() + "-" +  fechaYHora.getDayOfMonth());
            escritorDeArchivo.print("\n");
            escritorDeArchivo.print("\n");

            // Datos de los productos
            escritorDeArchivo.print("------------------------------------------------------------------------------------------------ LISTA DE PRODUCTOS -------------------------------------------------------------------------------------------------\n\n");
            String[] columnasProductos = {"ID producto", "Producto", "Categoría", "Cantidad en Stock", "Precio de venta"};
            for(String columna : columnasProductos)
                escritorDeArchivo.printf(configuracionImpresion, columna);
            
            escritorDeArchivo.print("\n");
            
            if(listaProductos != null){
                for(String[] producto : listaProductos){
                    for(String dato : producto){
                        escritorDeArchivo.printf(configuracionImpresion, dato);
                    }
                    escritorDeArchivo.print("\n");
                }
            }
            escritorDeArchivo.print("\n" + pieDePagina + "\n\n\n");

            // Datos de los clientes
            escritorDeArchivo.print("------------------------------------------------------------------------------------------------ LISTA DE CLIENTES -------------------------------------------------------------------------------------------------\n\n");
            String[] columnasClientes = {"Documento", "Nombre y apellidos", "Correo Electrónico", "Teléfono", "Número de transacciones"};
            for(String columna : columnasClientes)
                escritorDeArchivo.printf(configuracionImpresion, columna);
            
            escritorDeArchivo.print("\n");

            if(listaClientes != null){
                for(String[] cliente : listaClientes){
                    for(String dato : cliente){
                        escritorDeArchivo.printf(configuracionImpresion, dato);
                    }
                    escritorDeArchivo.print("\n");
                }
            }
            escritorDeArchivo.print("\n" + pieDePagina + "\n\n\n");

            // Datos de los proveedores
            escritorDeArchivo.print("------------------------------------------------------------------------------------------------ LISTA DE PROVEEDORES ---------------------------------------------------------------------------------------------\n\n");
            String[] columnasProveedores = {"NIT", "Nombre de la empresa", "Correo Electrónico", "Teléfono", "Número de transacciones"};
            for(String columna : columnasProveedores)
                escritorDeArchivo.printf(configuracionImpresion, columna);
            
            escritorDeArchivo.print("\n");

            if(listaProveedores != null){
                for(String[] proveedor : listaProveedores){
                    for(String dato : proveedor){
                        escritorDeArchivo.printf(configuracionImpresion, dato);
                    }
                    escritorDeArchivo.print("\n");
                }
            }
            escritorDeArchivo.print("\n" + pieDePagina + "\n\n\n");

            // Datos de las ventas
            escritorDeArchivo.print("------------------------------------------------------------------------------------------------ LISTA DE VENTAS -------------------------------------------------------------------------------------------------\n\n");
            String[] columnasVentas = {"ID venta", "Fecha", "Documento del cliente", "Nombre y apellidos", "Número de productos de la venta", "Precio total"};
            for(String columna : columnasVentas)
                escritorDeArchivo.printf(configuracionImpresion, columna);
            
            escritorDeArchivo.print("\n");

            if(listaVentas != null){
                for(String[] venta : listaVentas){
                    for(String dato : venta){
                        escritorDeArchivo.printf(configuracionImpresion, dato);
                    }
                    escritorDeArchivo.print("\n");
                }
            }   
            escritorDeArchivo.print("\n" + pieDePagina + "\n\n\n");

            // Datos de las compras
            escritorDeArchivo.print("------------------------------------------------------------------------------------------------ LISTA DE COMPRAS -------------------------------------------------------------------------------------------------\n\n");
            String[] columnasCompras = {"ID compra", "Fecha", "NIT", "Nombre de la empresa", "Número de productos de la compra", "Precio total"};
            for(String columna : columnasCompras)
                escritorDeArchivo.printf(configuracionImpresion, columna);
            
            escritorDeArchivo.print("\n");

            if(listaCompras != null){
                for(String[] compra : listaCompras){
                    for(String dato : compra){
                        escritorDeArchivo.printf(configuracionImpresion, dato);
                    }
                    escritorDeArchivo.print("\n");
                }
            }
            escritorDeArchivo.print("\n" + pieDePagina + "\n\n\n");

            operacionRealizada = true;

        } catch (IOException exception){
            System.out.println("Error alistando el archivo de texto plano.");
            // System.out.println("Error alistando el archivo de texto plano. " + exception.getMessage());
  
        } finally {
            if(escritorDeArchivo != null){
                try{
                    escritorDeArchivo.close();

                } catch (Exception exception){
                    System.out.println("Error cerrando el archivo plano.");
                    // System.out.println("Error cerrando el archivo plano. " + exception.getMessage());
                }
            }
        }
        return operacionRealizada;
    }
}
