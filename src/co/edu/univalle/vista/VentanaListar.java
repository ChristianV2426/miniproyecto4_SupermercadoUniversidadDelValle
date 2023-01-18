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
    private JLabel[] labelTexto = {new JLabel("Id "), new JLabel("Cantidad "), new JLabel(""), new JLabel(""), new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel(""),new JLabel("")};
    private JPanel[] contenedorTexto = {new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};

    private JPanel panelInferiorIzquierdo = new JPanel();
    private JPanel panelInferiorDerecho = new JPanel();
    private JPanel panelPieIzquierdo = new JPanel();
    private JPanel panelPieDerecho = new JPanel();
    private JButton botonAgregar = new JButton("Agregar");
    private JButton botonLimpiar = new JButton("Limpiar");
    private JButton botonEditar = new JButton("Editar");
    private JButton botonEliminar = new JButton("Eliminar");

    // Productos:
    private JTextField fieldId = new JTextField(4);
    private JTextField fieldCantidad = new JTextField(6);
    private JTextField fieldCosto = new JTextField(6);
    
    // Constructor:
    public VentanaListar() {
        setSize(800,400);

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
        panelPieIzquierdo.add(botonEditar);
        panelPieIzquierdo.add(botonEliminar);
        panelPieIzquierdo.add(botonEditar);

        panelPieDerecho.setVisible(false);

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
        botonEditar.addActionListener(listenControles);
        botonEliminar.addActionListener(listenControles);
    }

    public void mostrarListado(String categoria){
        contenedorTexto[0].add(fieldId);
        contenedorTexto[1].add(fieldCantidad);
        if(categoria == "Venta"){
            setTitle("Servicio de Salud - Listado de productos vendidos");

        } else if(categoria == "Compra"){
            setTitle("Servicio de Salud - Listado de productos comprados");
            labelTexto[2].setText("Costo ");
            contenedorTexto[2].add(fieldCosto);
        }

        setVisible(true);
    }

    public void setTablaDatos(JTable tablaDatos){
        this.tablaDatos = tablaDatos;
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
}
