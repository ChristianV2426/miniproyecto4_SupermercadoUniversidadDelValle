/*
    Archivo: VentanaInicio.java
    Fundamentos de Programación Orientada a Eventos - 750014C Grupo 01
    Proyecto 3 - Servicio de Salud
    Autores: 
    Juan Camilo Narvaez Tascon - juan.narvaez.tascon@correounivalle.edu.co - 2140112-3743
    Christian David Vargas Gutiérrez - vargas.christian@correounivalle.edu.co - 2179172-3743
    Profesor:
    Ing. M.Sc. Luis Yovany Romo Portilla
    Licencia: GNU-GPL
 */

/**
    CLASE: VentanaInicio
    INTENCIÓN: --
    RELACIONES:
    -Es una Ventana.
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
    private JLabel tituloProyecto = new JLabel("Súper Mercado");
    private JLabel subtituloProyecto = new JLabel("Universidad del Valle");
    
    private JPanel contenedorCategoria = new JPanel();
    private JLabel labelCategoria = new JLabel("Categoría");
    private String[] opcionesCategoria = {"Afiliados", "Medicos", "Servicios medicos", "Consultorios", "Citas"};
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
    private JButton botonImportar = new JButton("Importar");

    // Citas:
    private JTextField fieldIdCitas = new JTextField(4);
    private JTextField fieldAfiliadoCitas = new JTextField(12);
    private JTextField fieldFechaCitas = new JTextField(12);
    private JTextField fieldHoraCitas = new JTextField(12);
    
    // Consultorios:
    private JTextField fieldIdConsultorio = new JTextField(4);
    private JTextField fieldNumeroConsultorio = new JTextField(12);
    private JTextField fieldServicioConsultorio = new JTextField(12);

    // Servicios médicos:
    private JTextField fieldIdServicioMedico = new JTextField(4);
    private JTextField fieldNombreServicio = new JTextField(12);
    private JTextField areaDescripcionServicio = new JTextField(20);
    
    // Médicos:
    private JTextField fieldNombreMedico = new JTextField(12);
    private JTextField fieldApellidoMedico = new JTextField(12);
    
    private JTextField fieldNumeroDocumentoMedico = new JTextField(12);
    private JTextField fieldFechaNacimientoMedico = new JTextField(12);
    private JTextField fieldEdadMedico = new JTextField(2);
    
    private JTextField fieldUniversidadMedico = new JTextField(12);
    private JTextField fieldTelefonoMedico = new JTextField(12);
    private JTextField fieldDisponibilidadMedico = new JTextField(12);

    // Afiliados:
    private JTextField fieldNombreAfiliado = new JTextField(12);
    private JTextField fieldApellidoAfiliado = new JTextField(12);
    private JTextField fieldLugarNacimientoAfiliado = new JTextField(12);
    
    private JTextField fieldNumeroDocumentoAfiliado = new JTextField(12);
    private JTextField fieldFechaNacimientoAfiliado = new JTextField(12);
    
    private JTextField fieldEdadAfiliado = new JTextField(2);
    
    private JTextField fieldDireccionAfiliado = new JTextField(12);
    private JTextField fieldCiudadResidenciaAfiliado = new JTextField(12);
    private JTextField fieldTelefonoAfiliado = new JTextField(12);
    
    private JTextField fieldEpsAfiliado = new JTextField(12);

    // Constructor:
    public VentanaInicio() {
        // Configuración componentes:
        for(int contadorTextosPanelFormulario = 0; contadorTextosPanelFormulario < 15; contadorTextosPanelFormulario++) {
            contenedorTexto[contadorTextosPanelFormulario].setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            contenedorTexto[contadorTextosPanelFormulario].add(labelTexto[contadorTextosPanelFormulario]);
            contenedorTexto[contadorTextosPanelFormulario].setBackground(Color.WHITE); // Coloreado
            panelInferiorIzquierdo.add(contenedorTexto[contadorTextosPanelFormulario]);
        }

        fieldIdCitas.setEditable(false);
        fieldEdadMedico.setEditable(false);
        fieldEdadAfiliado.setEditable(false);
        tituloProyecto.setFont(new Font("Arial", Font.PLAIN, 24));
        tituloProyecto.setForeground(Color.WHITE);
        tituloProyecto.setHorizontalAlignment(JLabel.CENTER);
        subtituloProyecto.setFont(new Font("Arial", Font.PLAIN, 18));
        subtituloProyecto.setForeground(Color.WHITE);
        subtituloProyecto.setHorizontalAlignment(JLabel.CENTER);
        labelCategoria.setForeground(Color.WHITE);

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
        panelPieDerecho.add(botonImportar);

        panelPieDerecho.setVisible(false);

        // Coloreados  
        cabeceraTitulo.setBackground(new Color(41, 126, 255));
        contenedorCategoria.setBackground(new Color(41, 126, 255));
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
        
    }
    
    // Métodos
    public void addListener(ActionListener listenControles){
        dropCategorias.addActionListener(listenControles);
        botonAgregar.addActionListener(listenControles);
        botonLimpiar.addActionListener(listenControles);
        botonEditar.addActionListener(listenControles);
        botonEliminar.addActionListener(listenControles);
        botonExportar.addActionListener(listenControles);
    }
    
    public void setTablaDatos(JTable tablaDatos){
        this.tablaDatos = tablaDatos;
    }
    
    public JTable getTablaDatos(){
        return tablaDatos;
    }

    public JTextField getFieldAfiliadosCitas(){
        return fieldAfiliadoCitas;
    }

    public JTextField getFieldLugarNacimientoAfiliado(){
        return fieldLugarNacimientoAfiliado;
    }

    public JTextField getFieldFechaCitas(){
        return fieldFechaCitas;
    }

    public JTextField getFieldHoraCitas(){
        return fieldHoraCitas;
    }

    public JTextField getFieldNumeroConsultorio(){
        return fieldNumeroConsultorio;
    }

    public JTextField getFieldServicioConsultorio(){
        return fieldServicioConsultorio;
    }

    public JTextField getFieldIdConsultorio(){
        return fieldIdConsultorio;
    }

    public JTextField getFieldIdCitas(){
        return fieldIdCitas;
    }

    public JTextField getFieldIdServicioMedico(){
        return fieldIdServicioMedico;
    }

    public JTextField getFieldNombreServicio(){
        return fieldNombreServicio;
    }

    public JTextField getFieldNombreMedico(){
        return fieldNombreMedico;
    }

    public JTextField getFieldApellidoMedico(){
        return fieldApellidoMedico;
    }


    public JTextField getFieldNumeroDocumentoMedico(){
        return fieldNumeroDocumentoMedico;
    }

    public JTextField getFieldFechaNacimientoMedico(){
        return fieldFechaNacimientoMedico;
    }

    public JTextField getFieldEdadMedico(){
        return fieldEdadMedico;
    }

    public JTextField getFieldNombreAfiliado(){
        return fieldNombreAfiliado;
    }
    
    public JTextField getFieldApellidoAfiliado(){
        return fieldApellidoAfiliado;
    }

    public JTextField getFieldNumeroDocumentoAfiliado(){
        return fieldNumeroDocumentoAfiliado;
    }

    public JTextField getFieldFechaNacimientoAfiliado(){
        return fieldFechaNacimientoAfiliado;
    }

    public JTextField getFieldEdadAfiliado(){
        return fieldEdadAfiliado;
    }

    public JTextField getAreaDescripcionServicio(){
        return areaDescripcionServicio;
    }


    public JComboBox<String> getDropCategorias(){
        return dropCategorias;
    }


    public JTextField getFieldUniversidadMedico(){
        return fieldUniversidadMedico;
    }

    public JTextField getFieldTelefonoMedico(){
        return fieldTelefonoMedico;
    }

    public JTextField getFieldDisponibilidadMedico(){
        return fieldDisponibilidadMedico;
    }

    public JTextField getFieldDireccionAfiliado(){
        return fieldDireccionAfiliado;
    }

    public JTextField getFieldCiudadResidenciaAfiliado(){
        return fieldCiudadResidenciaAfiliado;
    }

    public JTextField getFieldTelefonoAfiliado(){
        return fieldTelefonoAfiliado;
    }

    public JTextField getFieldEpsAfiliado(){
        return fieldEpsAfiliado;
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

    public JButton getBotonImportar(){
        return botonImportar;
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