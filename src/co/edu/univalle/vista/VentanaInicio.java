/*
    Archivo: VentanaInicio.java
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
    CLASE: VentanaInicio

    INTENCIÓN: Esta es la ventana principal del programa. Acá se definen los componentes de la interfaz gráfica que pasarán a ser controlados
    desde la capa de control del programa.
    
    RELACIONES:
    -Hereda de la clase Ventana, y por lo tanto es un JFrame.
 */

 package co.edu.univalle.vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import co.edu.univalle.controlador.*;
 
 public class VentanaInicio extends Ventana{
    // Atributos:
    private JTable tablaDatos = new JTable(); // Se pasa (datos, encabezados).
    // private JTableHeader encabezadoTabla = tablaDatos.getTableHeader();
    private JScrollPane pane = new JScrollPane();

    private JPanel cabeceraTitulo = new JPanel();
    private JLabel tituloProyecto = new JLabel("Supermercado");
    private JLabel subtituloProyecto = new JLabel("Universidad del Valle");
    
    private JPanel contenedorCategoria = new JPanel();
    private JLabel labelCategoria = new JLabel("Categoría");
    private String[] opcionesCategoria = {"Productos", "Clientes", "Proveedores", "Ventas (a clientes)", "Compras (a proveedores)"};
    private JComboBox<String> dropCategorias = new JComboBox<>(opcionesCategoria);

    // Labels:
    private JLabel[] labelTexto = {new JLabel(""), new JLabel(""), new JLabel(""), new JLabel(""), new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel("")};
    private JPanel[] contenedorTexto = {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};

    private JPanel panelInferiorIzquierdo = new JPanel();
    private JPanel panelInferiorDerecho = new JPanel();
    private JPanel panelPieIzquierdo = new JPanel();
    private JPanel panelPieDerecho = new JPanel();
    private JButton botonAgregar = new JButton("Agregar");
    private JButton botonLimpiar = new JButton("Limpiar");
    private JButton botonEditar = new JButton("Editar");
    private JButton botonEliminar = new JButton("Eliminar");
    private JButton botonExportar = new JButton("Exportar");

    // Productos:
    private JTextField fieldIdProductos = new JTextField(6);
    private JTextField fieldNombresProductos = new JTextField(12);
    private JTextField fieldCantidadProductos = new JTextField(12);
    private JTextField fieldPrecioProductos = new JTextField(12);
    
    // Clientes:
    private JTextField fieldIdClientes = new JTextField(12);
    private JTextField fieldNombresClientes = new JTextField(12);
    private JTextField fieldCorreoClientes = new JTextField(12);
    private JTextField fieldTelefonoClientes = new JTextField(12);

    // Proveedores:
    private JTextField fieldIdProveedor = new JTextField(12);
    private JTextField fieldNombreProveedor = new JTextField(12);
    private JTextField fieldCorreoProveedor = new JTextField(12);
    private JTextField fieldTelefonoProveedor = new JTextField(12);

    // Venta:
    private JTextField fieldIdVenta = new JTextField(6);
    private JTextField fieldFechaVenta = new JTextField(12);
    private JTextField fieldCedulaClienteVenta = new JTextField(12);
    private JTextField fieldNombresClienteVenta = new JTextField(12);
    private JTextField fieldCostoVenta = new JTextField(6);
    private JButton buttonListaProductosVenta = new JButton("Listar");

    // Compra:
    private JTextField fieldIdCompra = new JTextField(6);
    private JTextField fieldFechaCompra = new JTextField(12);
    private JTextField fieldNitProveedorCompra = new JTextField(12);
    private JTextField fieldNombreProveedorCompra = new JTextField(12);
    private JTextField fieldCostoCompra = new JTextField(6);
    private JButton buttonListaProductosCompra = new JButton("Listar");

    // Constructor:
    public VentanaInicio() {
        // Configuración componentes:
        for(int contadorTextosPanelFormulario = 0; contadorTextosPanelFormulario < 15; contadorTextosPanelFormulario++) {
            contenedorTexto[contadorTextosPanelFormulario].setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            contenedorTexto[contadorTextosPanelFormulario].add(labelTexto[contadorTextosPanelFormulario]);
            contenedorTexto[contadorTextosPanelFormulario].setBackground(Color.WHITE); // Coloreado
            panelInferiorIzquierdo.add(contenedorTexto[contadorTextosPanelFormulario]);
        }

        tituloProyecto.setFont(new Font("Arial", Font.PLAIN, 24));
        tituloProyecto.setForeground(Color.WHITE);
        tituloProyecto.setHorizontalAlignment(JLabel.CENTER);
        subtituloProyecto.setFont(new Font("Arial", Font.PLAIN, 18));
        subtituloProyecto.setForeground(Color.WHITE);
        subtituloProyecto.setHorizontalAlignment(JLabel.CENTER);
        labelCategoria.setForeground(Color.WHITE);

        fieldCostoCompra.setEditable(false);
        fieldCostoVenta.setEditable(false);

        fieldIdProductos.setEditable(false);
        fieldIdVenta.setEditable(false);
        fieldIdCompra.setEditable(false);

        fieldNombreProveedorCompra.setEditable(false);
        fieldNombresClienteVenta.setEditable(false);

        // Configuración de páneles:
        northPanel.setLayout(new GridLayout(2, 1));
        centerPanel.setLayout(new GridLayout(1, 2, 10, 0));
        panelInferiorIzquierdo.setLayout(new GridLayout(15, 1, 0, 5));
        southPanel.setLayout(new GridLayout(1, 2)); 

        cabeceraTitulo.setLayout(new GridLayout(2, 1));
        cabeceraTitulo.add(tituloProyecto);
        cabeceraTitulo.add(subtituloProyecto);

        contenedorCategoria.add(labelCategoria);
        contenedorCategoria.add(dropCategorias);
        
        panelPieIzquierdo.add(botonAgregar);
        panelPieIzquierdo.add(botonLimpiar);
        panelPieIzquierdo.add(botonEditar);
        panelPieIzquierdo.add(botonEliminar);
        panelPieIzquierdo.add(botonEditar);

        panelPieDerecho.add(botonExportar);

        // Coloreados  
        cabeceraTitulo.setBackground(new Color(0,153,51));
        contenedorCategoria.setBackground(new Color(0,153,51));
        panelInferiorIzquierdo.setBackground(Color.WHITE);
        // panelInferiorDerecho.setBackground(Color.CYAN);
        // southPanel.setBackground(Color.PINK);
        // panelPieIzquierdo.setBackground(Color.PINK);
        // panelPieDerecho.setBackground(Color.ORANGE);

        // Añadidos a Ventana.java
        northPanel.add(cabeceraTitulo);
        northPanel.add(contenedorCategoria);
        centerPanel.add(panelInferiorIzquierdo);
        centerPanel.add(panelInferiorDerecho);
        southPanel.add(panelPieIzquierdo);
        southPanel.add(panelPieDerecho);  
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    // Métodos
    public void addListener(ActionListener listenControles){
        dropCategorias.addActionListener(listenControles);
        botonAgregar.addActionListener(listenControles);
        botonLimpiar.addActionListener(listenControles);
        botonEditar.addActionListener(listenControles);
        botonEliminar.addActionListener(listenControles);
        botonExportar.addActionListener(listenControles);
        buttonListaProductosCompra.addActionListener(listenControles);
        buttonListaProductosVenta.addActionListener(listenControles);
    }
    
    public void setTablaDatos(JTable tablaDatos){
        this.tablaDatos = tablaDatos;
    }
    
    public JTable getTablaDatos(){
        return tablaDatos;
    }
    
    // Productos
    public JTextField getFieldNombresProductos(){
        return fieldNombresProductos;
    }

    public JTextField getFieldCantidadProductos(){
        return fieldCantidadProductos;
    }

    public JTextField getFieldPrecioProductos(){
        return fieldPrecioProductos;
    }

    public JTextField getFieldIdProductos(){
        return fieldIdProductos;
    }

    // Clientes
    public JTextField getFieldIdClientes(){
        return fieldIdClientes;
    }

    public JTextField getFieldNombresClientes(){
        return fieldNombresClientes;
    }

    public JTextField getFieldCorreoClientes(){
        return fieldCorreoClientes;
    }

    public JTextField getFieldTelefonoClientes(){
        return fieldTelefonoClientes;
    }

    // Proveedores
    public JTextField getFieldIdProveedor(){
        return fieldIdProveedor;
    }

    public JTextField getFieldNombreProveedor(){
        return fieldNombreProveedor;
    }

    public JTextField getFieldCorreoProveedor(){
        return fieldCorreoProveedor;
    }

    public JTextField getFieldTelefonoProveedor(){
        return fieldTelefonoProveedor;
    }

    // Venta
    public JTextField getFieldIdVenta(){
        return fieldIdVenta;
    }

    public JTextField getFieldFechaVenta(){
        return fieldFechaVenta;
    }


    public JTextField getFieldCedulaClienteVenta(){
        return fieldCedulaClienteVenta;
    }

    public JTextField getFieldNombresClienteVenta(){
        return fieldNombresClienteVenta;
    }

    public JTextField getFieldCostoVenta(){
        return fieldCostoVenta;
    }

    // Compra
    public JTextField getFieldIdCompra(){
        return fieldIdCompra;
    }
    
    public JTextField getFieldFechaCompra(){
        return fieldFechaCompra;
    }

    public JTextField getFieldNitProveedorCompra(){
        return fieldNitProveedorCompra;
    }

    public JTextField getFieldNombreProveedorCompra(){
        return fieldNombreProveedorCompra;
    }

    public JTextField getFieldCostoCompra(){
        return fieldCostoCompra;
    }

    public JButton getbuttonListaProductosCompra(){
        return buttonListaProductosCompra;
    }

    public JComboBox<String> getDropCategorias(){
        return dropCategorias;
    }


    public JButton getbuttonListaProductosVenta(){
        return buttonListaProductosVenta;
    }

    public JLabel[] getLabelTexto(){
        return labelTexto;
    }


    public JScrollPane getPane(){
        return pane;
    }

    public JButton getBotonExportar(){
        return botonExportar;
    }

    public void setPane(JScrollPane pane){
        this.pane = pane;
        panelInferiorDerecho.add(pane);
    }

    public JPanel getPanelInferior(){
        return panelInferiorDerecho;
    }

    public JPanel getPanelPieInferior(){
        return panelPieDerecho;
    }

    public JPanel[] getContenedorTexto(){
        return contenedorTexto;
    }  
 }