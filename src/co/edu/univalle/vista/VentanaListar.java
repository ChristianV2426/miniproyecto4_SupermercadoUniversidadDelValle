/*
    Archivo: VentanaListar.java
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
    CLASE: VentanaListar

    INTENCIÓN: Esta es una ventana auxiliar que se utilizará al momento de agregar los productos de una venta (a un cliente)
    o de una compra (a un proveedor).

    RELACIONES:
    -Hereda de la clase Ventana, y por lo tanto es un JFrame.
 */

package co.edu.univalle.vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VentanaListar extends Ventana {
    // Atributos:
    private JTable tablaDatos = new JTable(); // Se pasa (datos, encabezados).
    // private JTableHeader encabezadoTabla = tablaDatos.getTableHeader();
    private JScrollPane pane = new JScrollPane();

    private JPanel contenedorCategoria = new JPanel();
    
    // Labels:
    private JLabel[] labelTexto = {new JLabel(""), new JLabel(""), new JLabel(""), new JLabel(""), new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel("")};
    private JPanel[] contenedorTexto = {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};

    private JPanel panelInferiorIzquierdo = new JPanel();
    private JPanel panelInferiorDerecho = new JPanel();
    private JPanel panelPieIzquierdo = new JPanel();
    private JPanel panelPieDerecho = new JPanel();
    private JButton botonAgregar = new JButton("Agregar");
    private JButton botonLimpiar = new JButton("Limpiar");
    private JButton botonEliminar = new JButton("Eliminar");

    // Productos:
    private JTextField fieldId = new JTextField(4);
    private JTextField fieldNombre = new JTextField(12);
    private JTextField fieldCantidad = new JTextField(6);
    private JTextField fieldCosto = new JTextField(6);
    
    // Constructor:
    public VentanaListar() {
        setSize(1000,400);
        // setResizable(true);

        // Configuración componentes:
        for(int contadorTextosPanelFormulario = 0; contadorTextosPanelFormulario < 15; contadorTextosPanelFormulario++) {
            contenedorTexto[contadorTextosPanelFormulario].setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            contenedorTexto[contadorTextosPanelFormulario].add(labelTexto[contadorTextosPanelFormulario]);
            contenedorTexto[contadorTextosPanelFormulario].setBackground(Color.WHITE); // Coloreado
            panelInferiorIzquierdo.add(contenedorTexto[contadorTextosPanelFormulario]);
        }

        // Configuración de páneles:
        northPanel.setLayout(new GridLayout(2, 1));
        centerPanel.setLayout(new GridLayout(1, 2, 10, 0));
        panelInferiorIzquierdo.setLayout(new GridLayout(15, 1, 0, 5));
        southPanel.setLayout(new GridLayout(1, 2)); 

        panelPieIzquierdo.add(botonAgregar);
        panelPieIzquierdo.add(botonLimpiar);
        panelPieIzquierdo.add(botonEliminar);

        panelPieDerecho.setVisible(false);

        fieldNombre.setEditable(false);

        // Coloreados  
        contenedorCategoria.setBackground(new Color(41, 126, 255));
        panelInferiorIzquierdo.setBackground(Color.WHITE);
        // panelInferiorDerecho.setBackground(Color.CYAN);
        // southPanel.setBackground(Color.PINK);
        // panelPieIzquierdo.setBackground(Color.PINK);
        // panelPieDerecho.setBackground(Color.ORANGE);

        // Añadidos a Ventana.java
        centerPanel.add(panelInferiorIzquierdo);
        centerPanel.add(panelInferiorDerecho);
        southPanel.add(panelPieIzquierdo);
        southPanel.add(panelPieDerecho);   
    }

    public void addListener(ActionListener listenControles){
        botonAgregar.addActionListener(listenControles);
        botonLimpiar.addActionListener(listenControles);
        botonEliminar.addActionListener(listenControles);
    }

    public void mostrarListado(String categoria){
        labelTexto[0].setText("Id ");
        contenedorTexto[0].add(fieldId);
        labelTexto[1].setText("Nombre ");
        contenedorTexto[1].add(fieldNombre);
        labelTexto[2].setText("Cantidad ");
        contenedorTexto[2].add(fieldCantidad);
        if(categoria == "Ventas (a clientes)"){
            setTitle("Supermercado Universidad del Valle - Listado de productos vendidos");

        } else if(categoria == "Compras (a proveedores)"){
            setTitle("Supermercado Universidad del Valle - Listado de productos comprados");
            labelTexto[3].setText("Costo ");
            contenedorTexto[3].add(fieldCosto);
        }

        setVisible(true);
    }

    public void addFocusListener(FocusListener listenControles){
        fieldNombre.addFocusListener(listenControles);
    }

    public void setTablaDatos(JTable tablaDatos){
        this.tablaDatos = tablaDatos;
    }
    
    public JTextField getId(){
        return fieldId;
    }

    public JTextField getNombre(){
        return fieldNombre;
    }

    public JTextField getCosto(){
        return fieldCosto;
    }

    public JTextField getCantidad(){
        return fieldCantidad;
    }

    public JTable getTablaDatos(){
        return tablaDatos;
    }

    public JScrollPane getPane(){
        return pane;
    }

    public void setPane(JScrollPane pane){
        this.pane = pane;
        panelInferiorDerecho.add(pane);
    }

    public JPanel[] getContenedorTexto(){
        return contenedorTexto;
    }  

    public JLabel[] getLabelTexto(){
        return labelTexto;
    }

    public JPanel getPanelInferior(){
        return panelInferiorDerecho;
    }
}
