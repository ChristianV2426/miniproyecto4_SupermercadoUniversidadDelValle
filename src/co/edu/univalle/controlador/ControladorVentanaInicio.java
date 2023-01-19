/*
    Archivo: ControladorVentanaInicio.java
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
    CLASE: ControladorVentanaInicio

    INTENCIÓN: Esta es la clase controladora principal del programa.
    Desde este controlador se conecta la capa vista con las capas de la lógica del negocio y de la persistencia.
    Además, esta clase direcciona los controladores específicos de cada categoría, según los eventos originados desde la interfaz gráfica del programa. 
    
    RELACIONES:
    -Contiene una VentanaInicio, que es la ventana con la que interactua el usuario.
    -Contiene un Supermercado, que es la clase que conoce y administra todos los elementos del sistema (Productos, Clientes, Proveedores, Ventas y Compras).
*/

package co.edu.univalle.controlador;

import co.edu.univalle.modelo.*;
import co.edu.univalle.vista.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.time.*;
import java.awt.*;


public class ControladorVentanaInicio {
    // Atributos:
    private VentanaListar ventanaListados = new VentanaListar(); 
    private String tipoCategoria;
    private VentanaInicio ventanaInicio;
    private JTable tablaDatos = new JTable();
    private JScrollPane pane = new JScrollPane();
    private JLabel labelFormatoFecha = new JLabel("AAAA-MM-DD");
    String rutaArchivoBinario = "./src/co/edu/univalle/archivos/supermercado.bin";
    private Supermercado supermercado = null;
    private Integer serialProducto;
    private Integer serialVenta;
    private Integer serialCompra;
    
    // Constructor
    public ControladorVentanaInicio(VentanaInicio ventanaInicio){
        labelFormatoFecha.setFont(new Font("Arial", Font.PLAIN, 11));
        labelFormatoFecha.setForeground(Color.gray);
        this.ventanaInicio = ventanaInicio;

        this.supermercado = ManejadorArchivos.leerArchivoBinario(rutaArchivoBinario);
        if (supermercado == null)
            supermercado = new Supermercado("Supermercado - Universidad del Valle");

        serialProducto = supermercado.getSerialProducto();
        serialVenta = supermercado.getSerialVenta();
        serialCompra = supermercado.getSerialCompra(); 

        // Listener para close.
        ventanaInicio.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                supermercado.setSerialProducto(serialProducto);
                supermercado.setSerialVenta(serialVenta);
                supermercado.setSerialCompra(serialCompra);
                ManejadorArchivos.guardarEnArchivoBinario(supermercado, rutaArchivoBinario);
                System.exit(0); 
            }
        });

        // Mostrar Pantalla Inicial.
        ventanaInicio.setVisible(true);
        this.ventanaInicio.addListener(new CalculateListener());
        ventanaListados.addListener(new CalculateListener());

        tipoCategoria = (String)ventanaInicio.getDropCategorias().getSelectedItem();
        pintarFormulario(tipoCategoria);
    }

    class CalculateListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento) {
            if (evento.getActionCommand().equalsIgnoreCase("Agregar")){
                if(tipoCategoria == "Productos"){
                    if(ControladorProductos.revisarFieldsProductos(ventanaInicio)){
                        Producto nuevoProducto = ControladorProductos.crearProducto(ventanaInicio);

                    }

                } else if(tipoCategoria == "Clientes"){
                    if(ControladorClientes.revisarFieldsClientes(ventanaInicio)){
                        Cliente nuevoCliente = ControladorClientes.crearCliente(ventanaInicio);

                    }

                } else if(tipoCategoria == "Proveedores"){
                    if(ControladorProveedores.revisarFieldsProveedores(ventanaInicio)){
                        Proveedor nuevoProveedor = ControladorProveedores.crearProveedor(ventanaInicio);

                    }

                } else if(tipoCategoria == "Ventas (a clientes)"){
                    if(ControladorVentas.revisarFieldsVentas(ventanaInicio)){
                        Venta nuevaVenta = ControladorVentas.crearVenta(ventanaInicio);

                    }

                } else if (tipoCategoria == "Compras (a proveedores)"){
                    if(ControladorCompras.revisarFieldsCompras(ventanaInicio)){
                        Compra nuevaCompra = ControladorCompras.crearCompra(ventanaInicio);

                    }
                }
                pintarFormulario(tipoCategoria);

            } else if (evento.getActionCommand().equalsIgnoreCase("limpiar")){
                limpiarFormulario(tipoCategoria);
    
            } else if (evento.getActionCommand().equalsIgnoreCase("Editar")){
                if(tipoCategoria == "Productos"){
                    if(ControladorProductos.revisarFieldsProductos(ventanaInicio)){
                        Producto nuevoProducto = ControladorProductos.crearProducto(ventanaInicio);

                    }
                    
                } else if(tipoCategoria == "Clientes"){
                    if(ControladorClientes.revisarFieldsClientes(ventanaInicio)){
                        Cliente nuevoCliente = ControladorClientes.crearCliente(ventanaInicio);

                    }

                } else if(tipoCategoria == "Proveedores"){
                    if(ControladorProveedores.revisarFieldsProveedores(ventanaInicio)){
                        Proveedor nuevoProveedor = ControladorProveedores.crearProveedor(ventanaInicio);

                    }

                } else if(tipoCategoria == "Ventas (a clientes)"){
                    if(ControladorVentas.revisarFieldsVentas(ventanaInicio)){
                        Venta nuevaVenta = ControladorVentas.crearVenta(ventanaInicio);

                    }

                } else if (tipoCategoria == "Compras (a proveedores)"){
                    if(ControladorCompras.revisarFieldsCompras(ventanaInicio)){
                        Compra nuevaCompra = ControladorCompras.crearCompra(ventanaInicio);

                    }

                }
                pintarFormulario(tipoCategoria);
                
            } else if (evento.getActionCommand().equalsIgnoreCase("Eliminar")){
                if(tipoCategoria == "Productos"){
                    if(ControladorProductos.revisarIDProducto(ventanaInicio)){

                    }

                } else if(tipoCategoria == "Clientes"){
                    if(ControladorClientes.revisarIDCliente(ventanaInicio)){

                    }

                } else if(tipoCategoria == "Proveedores"){
                    if(ControladorProveedores.revisarIDProveedor(ventanaInicio)){

                    }

                } else if(tipoCategoria == "Ventas (a clientes)"){
                    if(ControladorVentas.revisarIDVenta(ventanaInicio)){

                    }

                } else if (tipoCategoria == "Compras (a proveedores)"){
                    if(ControladorCompras.revisarFieldsCompras(ventanaInicio)){

                    }
                    
                }
                pintarFormulario(tipoCategoria);
                
            } else if (evento.getActionCommand().equalsIgnoreCase("Exportar")){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int seleccion = fileChooser.showOpenDialog(ventanaInicio);

                if(seleccion != JFileChooser.CANCEL_OPTION){
                    String ruta = fileChooser.getSelectedFile().getAbsolutePath();
                    if(ManejadorArchivos.guardarEnArchivoTextoPlano(supermercado, ruta)){
                        JOptionPane.showMessageDialog(null,"¡El estado de la aplicación se guardó correctamente con archivo de texto plano!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"¡Hubo un problema al guardar el estado de la aplicación en un archivo de texto plano!" +
                        "\nAsegurese de haber seleccionado una carpeta de destino correcta. \nSi considera que este es un error, por favor póngase en contacto con el administrador del sistema.", "Operación fallida", JOptionPane.ERROR_MESSAGE);
                    }
                }

            } else if (evento.getSource() == ventanaInicio.getDropCategorias()){
                tipoCategoria = (String)ventanaInicio.getDropCategorias().getSelectedItem();
                pintarFormulario(tipoCategoria);
                limpiarFormulario(tipoCategoria);

            } else if (evento.getActionCommand().equalsIgnoreCase("Listar")){
                pane = ventanaListados.getPane();
                tablaDatos = ventanaListados.getTablaDatos();
                pane.removeAll();
                tablaDatos.removeAll();
                pane = new JScrollPane(ventanaListados.getTablaDatos());
                ventanaListados.setPane(pane);
                ventanaListados.mostrarListado(tipoCategoria);
                ventanaListados.setTablaDatos(tablaDatos);
            } 
        }
    }

    public void limpiarFormulario(String tipoCategoria){
        if(tipoCategoria == "Productos"){
            ControladorProductos.limpiar(ventanaInicio);

        } else if (tipoCategoria == "Clientes") {
            ControladorClientes.limpiar(ventanaInicio);

        } else if (tipoCategoria == "Proveedores") {
            ControladorProveedores.limpiar(ventanaInicio);

        } else if (tipoCategoria == "Ventas (a clientes)") {
            ControladorVentas.limpiar(ventanaInicio);

        } else if (tipoCategoria == "Compras (a proveedores)") {
            ControladorCompras.limpiar(ventanaInicio);
        } 
    }

    public void pintarFormulario(String tipoCategoria){
        tablaDatos = ventanaInicio.getTablaDatos();
        pane = ventanaInicio.getPane();
        
        for(int limpiador = 0; limpiador < 15; limpiador++){
            ventanaInicio.getContenedorTexto()[limpiador].removeAll();
            ventanaInicio.getLabelTexto()[limpiador].setText("");
            ventanaInicio.getContenedorTexto()[limpiador].add(ventanaInicio.getLabelTexto()[limpiador]);
        }

        ventanaInicio.getPanelInferior().removeAll();
        ventanaInicio.getPanelInferior().removeAll();

        pane.removeAll();
        tablaDatos.removeAll();
        
        if(tipoCategoria == "Productos"){
            ControladorProductos.pintar(ventanaInicio);
            String[][] datosProductos = supermercado.getProductos().getListables();
            tablaDatos = new JTable(asignarModelo(datosProductos, ControladorProductos.getEncabezadoProductos()));
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        } else if (tipoCategoria == "Clientes") {
            ControladorClientes.pintar(ventanaInicio);
            String[][] datosClientes = supermercado.getClientes().getListables();
            tablaDatos = new JTable(asignarModelo(datosClientes, ControladorClientes.getEncabezadoClientes()));
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        } else if (tipoCategoria == "Proveedores") {
            ControladorProveedores.pintar(ventanaInicio);
            String[][] datosProveedores = supermercado.getProveedores().getListables();
            tablaDatos = new JTable(asignarModelo(datosProveedores, ControladorProveedores.getEncabezadoProveedores()));
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        } else if (tipoCategoria == "Ventas (a clientes)") {
            ControladorVentas.pintar(ventanaInicio);
            String[][] datosVentas = supermercado.getVentas().getListables();
            tablaDatos = new JTable(asignarModelo(datosVentas, ControladorVentas.getEncabezadoVenta()));
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            ventanaInicio.getContenedorTexto()[1].add(labelFormatoFecha);

        } else if (tipoCategoria == "Compras (a proveedores)") {
            ControladorCompras.pintar(ventanaInicio);
            String[][] datosCompras = supermercado.getCompras().getListables();
            tablaDatos = new JTable(asignarModelo(datosCompras, ControladorCompras.getEncabezadoCompra()));
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
            ventanaInicio.getContenedorTexto()[1].add(labelFormatoFecha);
        } 
        
        // Se actualiza la tabla:
        tablaDatos.addMouseListener(new CalculateMouseListener());
        ventanaInicio.setTablaDatos(tablaDatos);
        pane = new JScrollPane(ventanaInicio.getTablaDatos());
        ventanaInicio.setPane(pane);
    }

    // Asigna los valores de la tabla a los respectivos fields.
    class CalculateMouseListener implements MouseInputListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            DefaultTableModel modeloTabla = (DefaultTableModel)ventanaInicio.getTablaDatos().getModel();

            if(tipoCategoria == "Productos"){


            } else if (tipoCategoria == "Clientes") {


            } else if (tipoCategoria == "Proveedores") {


            } else if (tipoCategoria == "Ventas (a clientes)") {


            } else if (tipoCategoria == "Compras (a proveedores)") {

            } 
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }
    
        @Override
        public void mouseReleased(MouseEvent e) {
        }
    
        @Override
        public void mouseEntered(MouseEvent e) {
        }
    
        @Override
        public void mouseExited(MouseEvent e) {
        }
    
        @Override
        public void mouseDragged(MouseEvent e) {
        }
    
        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }

    private TableModel asignarModelo(String[][] datos, String[] encabezado) {
        TableModel model = new DefaultTableModel(datos, encabezado)
        {
            public boolean isCellEditable(int row, int column)
            {
                return false; //Esta línea se asegura de que no se editen las celdas de la tabla
            }
        };

        return model;
    }

}
