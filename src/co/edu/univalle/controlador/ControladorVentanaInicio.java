package co.edu.univalle.controlador;

import co.edu.univalle.modelo.*;
import co.edu.univalle.vista.*;
import java.awt.event.*;
import java.time.format.DateTimeParseException;
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
    private JLabel labelFormatoFecha = new JLabel(" AAAA-MM-DD");
    private JLabel labelFormatoHora = new JLabel(" HH:MM (Sistema horario de 24 horas)");

    private JComboBox<String> dropServicioMedicoCitas;    

    // private EntidadSalud entidadSalud = null;
    Integer serialCitas;
    
    // Constructor
    public ControladorVentanaInicio(VentanaInicio ventanaInicio){
        labelFormatoFecha.setFont(new Font("Arial", Font.PLAIN, 11));
        labelFormatoFecha.setForeground(Color.gray);
        labelFormatoHora.setFont(new Font("Arial", Font.PLAIN, 11));
        labelFormatoHora.setForeground(Color.gray);

        this.ventanaInicio = ventanaInicio;
        // this.entidadSalud = ManejadorArchivos.leerArchivoBinario("./src/co/edu/univalle/archivos/entidadSalud.bin");
        // if (entidadSalud == null)
        //     entidadSalud = new EntidadSalud("Servicio de Salud - Universidad del Valle");

        // serialCitas = entidadSalud.getSerialCitas();

        // Listener para close.
        // ventanaInicio.addWindowListener(new java.awt.event.WindowAdapter() {
        //     @Override
        //     public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        //         entidadSalud.setSerialCitas(serialCitas);
        //         ManejadorArchivos.guardarArchivoBinario(entidadSalud, "./src/co/edu/univalle/archivos/entidadSalud.bin");
        //         System.exit(0); 
        //     }
        // });

        // Mostrar Pantalla Inicial.
        ventanaInicio.setVisible(true);
        this.ventanaInicio.addListener(new CalculateListener());
        ventanaListados.addListener(new CalculateListener());

        // Se inicializan los arreglos asociados a los servicion médicos.
        // String[] arregloServiciosMedicos = entidadSalud.getDaoServiciosMedicos().obtenerArreglo();
        // dropServicioMedicoCitas = new JComboBox<>(Arreglo.recalcularArreglo(arregloServiciosMedicos));
        // dropServicioMedicoCitas.addActionListener(new CalculateServicioMedicoCitas());

        tipoCategoria = (String)ventanaInicio.getDropCategorias().getSelectedItem();
        pintarFormulario(tipoCategoria);
    }

    class CalculateListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento) {
            if (evento.getActionCommand().equalsIgnoreCase("Agregar")){
                if(tipoCategoria == "Afiliados"){
                    // agregarAfiliado();
                } else if(tipoCategoria == "Medicos"){
                    // agregarMedico();
                } else if(tipoCategoria == "Servicios medicos"){
                    // agregarServicioMedico();
                } else if(tipoCategoria == "Consultorios"){
                    // agregarConsultorio();
                } else if (tipoCategoria == "Citas"){
                    // agregarCita();
                }
                pintarFormulario(tipoCategoria);

            } else if (evento.getActionCommand().equalsIgnoreCase("limpiar")){
                limpiarFormulario(tipoCategoria);
    
            } else if (evento.getActionCommand().equalsIgnoreCase("Editar")){
                if(tipoCategoria == "Afiliados"){
                    editarAfiliado();
                } else if(tipoCategoria == "Medicos"){
                    editarMedico();
                } else if(tipoCategoria == "Servicios medicos"){
                    // editarServicioMedico();
                } else if(tipoCategoria == "Consultorios"){
                    // editarConsultorio();
                } else if (tipoCategoria == "Citas"){
                    // editarCita();
                }
                pintarFormulario(tipoCategoria);
                
            } else if (evento.getActionCommand().equalsIgnoreCase("Eliminar")){
                System.out.println("FSDFSDF");
                if(tipoCategoria == "Afiliados"){
                    eliminarAfiliado();
                } else if(tipoCategoria == "Medicos"){
                    eliminarMedico();
                } else if(tipoCategoria == "Servicios medicos"){
                    // eliminarServicioMedico();
                } else if(tipoCategoria == "Consultorios"){
                    // eliminarConsultorio();
                } else if (tipoCategoria == "Citas"){
                    // eliminarCita();
                }
                pintarFormulario(tipoCategoria);
                
            } else if (evento.getActionCommand().equalsIgnoreCase("Exportar")){

                // if(tipoCategoria == "Afiliados"){
                //     String ruta = "./src/co/edu/univalle/archivos/registroAfiliados"; //Acá va el JFileChooser
                //     if(ManejadorArchivos.generarArchivoAfiliados(entidadSalud, ruta)){
                //         JOptionPane.showMessageDialog(null,"¡Lista de afiliados generada correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                //     } else {
                //         JOptionPane.showMessageDialog(null,"¡Hubo un problema al generar la lista de afiliados!" +
                //         "\nAsegurese de haber seleccionado una carpeta de destino correcta. \nSi considera que este es un error, por favor pongase en contacto con el administrador del sistema.", "Operación fallida", JOptionPane.ERROR_MESSAGE);
                //     }
                // } else if(tipoCategoria == "Citas"){

                // }

            } else if (evento.getActionCommand().equalsIgnoreCase("Importar")){
                // If para verificar que se seleccionó una ruta valida con el JFileChooser

            } else if (evento.getSource() == ventanaInicio.getDropCategorias()){
                tipoCategoria = (String)ventanaInicio.getDropCategorias().getSelectedItem();
                pintarFormulario(tipoCategoria);
                limpiarFormulario(tipoCategoria);
                ventanaInicio.getFieldIdProductos().setText(String.valueOf(serialCitas));
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

    class CalculateServicioMedicoCitas implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == dropServicioMedicoCitas){
                // if(dropServicioMedicoCitas.getSelectedItem() == null){
                //     JOptionPane.showMessageDialog(null,"Por favor ingrese una especialidad médica válida.\nSi en la lista no se encuentra la especialidad médica que busca, debe crearla desde la categoría \"Servicios médicos\"", "Advertencia", JOptionPane.ERROR_MESSAGE);
                //     return;
                // }

                // String stringIdServicioMedico = dropServicioMedicoCitas.getSelectedItem().toString();

                // if(stringIdServicioMedico.equals("Seleccionar")){
                //     String[] arregloVacio = {""};
                //     dropMedicosCitas = new JComboBox<>(arregloVacio);
                //     dropConsultoriosCitas = new JComboBox<>(arregloVacio);
                //     pintarFormulario("Citas");
                //     return;
                // }

                // Integer idServicioMedico = Integer.valueOf(stringIdServicioMedico.split(" ")[0]);
                // ServicioMedico servicioMedico = entidadSalud.getDaoServiciosMedicos().obtenerElemento(idServicioMedico);

                // String[] arregloMedicos = entidadSalud.getDaoMedicos().arregloMedicosConEspecialidad(servicioMedico);
                // dropMedicosCitas = new JComboBox<>(arregloMedicos);

                // String[] arregloConsultorios = entidadSalud.getDaoConsultorios().arregloConsultoriosConEspecialidad(servicioMedico);
                // dropConsultoriosCitas = new JComboBox<>(arregloConsultorios);

                ventanaInicio.getContenedorTexto()[2].setVisible(false); // Se hace para solucionar bugs de swing
                ventanaInicio.getContenedorTexto()[3].setVisible(false); // Se hace para solucionar bugs de swing
                ventanaInicio.getContenedorTexto()[4].setVisible(false); // Se hace para solucionar bugs de swing

                pintarFormulario("Citas");
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

        } else if (tipoCategoria == "Venta") {
            ControladorVenta.limpiar(ventanaInicio);

        } else if (tipoCategoria == "Compra") {
            ControladorCompra.limpiar(ventanaInicio);
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
            // String[][] datosCitas = Arreglo.convertirAArreglo(entidadSalud.getDaoCitas().obtenerLista());
            // tablaDatos = new JTable(asignarModelo(datosCitas, encabezadoCitas));

        } else if (tipoCategoria == "Clientes") {
            ControladorClientes.pintar(ventanaInicio);
            // String[][] datosConsultorios = Arreglo.convertirAArreglo(entidadSalud.getDaoConsultorios().obtenerLista());
            // tablaDatos = new JTable(asignarModelo(datosConsultorios, encabezadoConsultorios));
            // String[] arregloServiciosMedicos = entidadSalud.getDaoServiciosMedicos().obtenerArreglo();
            // dropServicioMedico = new JComboBox<>(Arreglo.recalcularArreglo(arregloServiciosMedicos));


        } else if (tipoCategoria == "Proveedores") {
            ControladorProveedores.pintar(ventanaInicio);
            // String[][] datosServiciosMedicos = Arreglo.convertirAArreglo(entidadSalud.getDaoServiciosMedicos().obtenerLista());
            // tablaDatos = new JTable(asignarModelo(datosServiciosMedicos, encabezadoServiciosMedicos));


        } else if (tipoCategoria == "Venta") {
            // String[][] datosMedicos = Arreglo.convertirAArreglo(entidadSalud.getDaoMedicos().obtenerLista());
            // tablaDatos = new JTable(asignarModelo(datosMedicos, encabezadoMedicos));
            tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            // String[] arregloServiciosMedicos = entidadSalud.getDaoServiciosMedicos().obtenerArreglo();
            // dropServicioMedico = new JComboBox<>(Arreglo.recalcularArreglo(arregloServiciosMedicos));
            ControladorVenta.pintar(ventanaInicio);
            ventanaInicio.getContenedorTexto()[1].add(labelFormatoFecha);
            

        } else if (tipoCategoria == "Compra") {
            // String[][] datosAfiliados = Arreglo.convertirAArreglo(entidadSalud.getDaoAfiliados().obtenerLista());
            // tablaDatos = new JTable(asignarModelo(datosAfiliados, encabezadoAfiliados));
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            ControladorCompra.pintar(ventanaInicio);
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

            if(tipoCategoria == "Citas"){
                // String tablaIdCitas = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
                // String tablaAfiliadoCitas = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
                // String tablaServicioCitas = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
                // String tablaMedicoCitas = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 3).toString();
                // String tablaConsultorioCitas = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 4).toString();
                // String tablaFechaCitas = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 5).toString();
                // String tablaHoraCitas = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 6).toString();
                
                // ventanaInicio.getFieldIdProductos().setText(tablaIdCitas);
                // dropServicioMedicoCitas.setSelectedItem(tablaServicioCitas);
                // ventanaInicio.getFieldNombresProductos().setText(tablaAfiliadoCitas);
                // dropMedicosCitas.setSelectedItem(tablaMedicoCitas);
                // dropConsultoriosCitas.setSelectedItem(tablaConsultorioCitas);
                // ventanaInicio.getFieldCantidadProductos().setText(tablaFechaCitas);
                // ventanaInicio.getFieldPrecioProductos().setText(tablaHoraCitas);

            } else if (tipoCategoria == "Consultorios") {
                // String tablaIdConsultorio = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
                // String tablaNumeroConsultorio = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
                // String tablaUbicacionConsultorio = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
                // String tablaServicioConsultorio = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 3).toString();

                // ventanaInicio.getFieldIdClientes().setEditable(false);
                // ventanaInicio.getFieldIdClientes().setText(tablaIdConsultorio);
                // ventanaInicio.getFieldNumeroConsultorio().setText(tablaNumeroConsultorio);
                // dropServicioMedico.setSelectedItem(tablaServicioConsultorio);

            } else if (tipoCategoria == "Servicios medicos") {
                // String tablaIdServicio = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
                // String tablaNombreServicio = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
                // String tablaDescripcionServicio = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
                
                // ventanaInicio.getFieldIdServicioMedico().setEditable(false);
                // ventanaInicio.getFieldIdServicioMedico().setText(tablaIdServicio);
                // ventanaInicio.getFieldNombreServicio().setText(tablaNombreServicio);
                // ventanaInicio.getAreaDescripcionServicio().setText(tablaDescripcionServicio);

            } else if (tipoCategoria == "Medicos") {
                // String tablaDocumentoMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
                // String tablaNumeroDocumentoMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
                // String tablaNombreMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
                // String tablaApellidoMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 3).toString();
                // String tablaNacimientoMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 4).toString();
                // String tablaEdadMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 5).toString();
                // String tablaSexoMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 6).toString();
                // String tablaServicioMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 7).toString();
                // String tablaUniversidadMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 8).toString();
                // String tablaTelefonoMedico = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 9).toString();

                // ventanaInicio.getFieldNombreMedico().setText(tablaNombreMedico);
                // ventanaInicio.getFieldApellidoMedico().setText(tablaApellidoMedico);
                // dropTipoDocumetoMedico.setSelectedItem(tablaDocumentoMedico);
                // ventanaInicio.getFieldNumeroDocumentoMedico().setText(tablaNumeroDocumentoMedico);
                // ventanaInicio.getFieldNumeroDocumentoMedico().setEditable(false);
                // ventanaInicio.getFieldFechaNacimientoMedico().setText(tablaNacimientoMedico);
                // ventanaInicio.getFieldEdadMedico().setText(tablaEdadMedico);
                // dropSexoMedico.setSelectedItem(tablaSexoMedico);
                // dropServicioMedico.setSelectedItem(tablaServicioMedico);
                // ventanaInicio.getFieldUniversidadMedico().setText(tablaUniversidadMedico);
                // ventanaInicio.getFieldTelefonoMedico().setText(tablaTelefonoMedico);

            } else if (tipoCategoria == "Afiliados") {
                // String tablaGrupoSanguineoAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 9).toString();
                // String tablaRHAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 10).toString();
                // String tablaDocumentoAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 0).toString();
                // String tablaNumeroDocumentoAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 1).toString();
                // String tablaNombreAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 2).toString();
                // String tablaApellidoAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 3).toString();
                // String tablaNacimientoAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 4).toString();
                // String tablaLugarNacimientoAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 5).toString();
                // String tablaEdadAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 7).toString();
                // String tablaSexoAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 8).toString();
                // String tablaSangreAfiliado = tablaGrupoSanguineoAfiliado + tablaRHAfiliado;
                // String tablaEstadoCivilAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 11).toString();
                // String tablaDireccionAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 12).toString();
                // String tablaCiudadRecidenciaAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 13).toString();
                // String tablaTelefonoAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 14).toString();
                // String tablaRegimenAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 15).toString();
                // String tablaEPSAfiliado = modeloTabla.getValueAt(ventanaInicio.getTablaDatos().getSelectedRow(), 16).toString();

                // ventanaInicio.getFieldIdCompra().setText(tablaNombreAfiliado);
                // ventanaInicio.getFieldFechaCompra().setText(tablaApellidoAfiliado);
                // dropTipoDocumetoAfiliado.setSelectedItem(tablaDocumentoAfiliado);
                // ventanaInicio.getFieldNombreProveedprVenta().setText(tablaNumeroDocumentoAfiliado);
                // ventanaInicio.getFieldNombreProveedprVenta().setEditable(false);
                // ventanaInicio.getFieldCostoCompra().setText(tablaNacimientoAfiliado);
                // ventanaInicio.getFieldNitProveedorVenta().setText(tablaLugarNacimientoAfiliado);
                // ventanaInicio.getbuttonListaProductosCompra().setText(tablaEdadAfiliado);
                // dropSexoAfiliado.setSelectedItem(tablaSexoAfiliado);
                // dropTipoSangreAfiliado.setSelectedItem(tablaSangreAfiliado);
                // dropEstadoCivilAfiliado.setSelectedItem(tablaEstadoCivilAfiliado);
                // ventanaInicio.getFieldCiudadResidenciaAfiliado().setText(tablaCiudadRecidenciaAfiliado);
                // ventanaInicio.getFieldDireccionAfiliado().setText(tablaDireccionAfiliado);
                // ventanaInicio.getFieldTelefonoAfiliado().setText(tablaTelefonoAfiliado);
                // dropRegimenAfiliado.setSelectedItem(tablaRegimenAfiliado);
                // ventanaInicio.getFieldEpsAfiliado().setText(tablaEPSAfiliado);
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

    public void agregarAfiliado(){
        // if(revisarFieldsAfiliado())
        // {
        //     String tipoDeDocumento = dropTipoDocumetoAfiliado.getSelectedItem().toString();
        //     Integer numeroDeDocumento = Integer.valueOf(ventanaInicio.getFieldNombreProveedprVenta().getText());
        //     Afiliado afiliado = crearAfiliado();
        //     if(entidadSalud.getDaoAfiliados().añadir(afiliado)){
        //         JOptionPane.showMessageDialog(null,"¡Afiliado " + tipoDeDocumento + " " + numeroDeDocumento + " agregado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
        //         limpiarFormulario(tipoCategoria);

        //     } else {
        //         JOptionPane.showMessageDialog(null,"El afiliado " + tipoDeDocumento + " " + numeroDeDocumento + " ya se encuentra registrado en el sistema, no lo puede volver a registrar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
        //     }
        // }
    }

    public void editarAfiliado(){
        // if(revisarFieldsAfiliado()){
        //     String tipoDeDocumento = dropTipoDocumetoAfiliado.getSelectedItem().toString();
        //     Integer numeroDeDocumento = Integer.valueOf(ventanaInicio.getFieldNombreProveedprVenta().getText());
        //     Afiliado afiliado = crearAfiliado();
        //     if(entidadSalud.getDaoAfiliados().elementoPresente(numeroDeDocumento) && entidadSalud.getDaoAfiliados().actualizar(numeroDeDocumento, afiliado)){
        //         JOptionPane.showMessageDialog(null,"¡Datos del filiado " + tipoDeDocumento + " " + numeroDeDocumento + " actualizados correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
        //         limpiarFormulario(tipoCategoria);

        //     } else {
        //         JOptionPane.showMessageDialog(null,"El afiliado " + tipoDeDocumento + " " + numeroDeDocumento + " no se encuentra registrado en el sistema, primero debe registrarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
        //     }
        // }
    }

    public void eliminarAfiliado(){
        // String tipoDeDocumento = dropTipoDocumetoAfiliado.getSelectedItem().toString();
        // if (!tipoDeDocumento.equals("T.I") && !tipoDeDocumento.equals("C.C") && !tipoDeDocumento.equals("C.E")){
        //     JOptionPane.showMessageDialog(null,"Por favor ingrese un tipo de documento válido", "Advertencia", JOptionPane.ERROR_MESSAGE);
        //     return;
        // }

        // String stringNumeroDeDocumento = ventanaInicio.getFieldNombreProveedprVenta().getText();
        // Integer numeroDeDocumento;
        // try{
        //     numeroDeDocumento = Integer.valueOf(stringNumeroDeDocumento);
        // } catch (NumberFormatException exception){
        //     JOptionPane.showMessageDialog(null,"Por favor ingrese un número de documento válido, sin puntos ni espacios, solo números.\nEjemplo: 1144000000", "Advertencia", JOptionPane.ERROR_MESSAGE);
        //     return;
        // }

        // if(entidadSalud.getDaoAfiliados().elementoPresente(numeroDeDocumento)){
        //     int continuar = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar al afiliado " + tipoDeDocumento + " " + numeroDeDocumento + "? \nEsta acción no se puede deshacer.", "¿Desea proceder con la eliminación?", JOptionPane.YES_NO_OPTION);
        //     if(continuar == JOptionPane.YES_OPTION && entidadSalud.getDaoAfiliados().eliminar(numeroDeDocumento)){
        //         JOptionPane.showMessageDialog(null,"¡El afiliado " + tipoDeDocumento + " " + numeroDeDocumento + " fue eliminado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
        //         limpiarFormulario(tipoCategoria);
        //     }          
        // } else {
        //     JOptionPane.showMessageDialog(null,"El afiliado " + tipoDeDocumento + " " + numeroDeDocumento + " no se encuentra registrado en el sistema, no puede eliminarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
        // }
    }

    // public Afiliado crearAfiliado(){
        // String tipoDeDocumento = dropTipoDocumetoAfiliado.getSelectedItem().toString();
        // Integer numeroDeDocumento = Integer.valueOf(ventanaInicio.getFieldNombreProveedprVenta().getText());
        // String nombre = ventanaInicio.getFieldIdCompra().getText().toUpperCase();
        // String apellido = ventanaInicio.getFieldFechaCompra().getText().toUpperCase();
        // LocalDate fechaNacimiento = LocalDate.parse(ventanaInicio.getFieldCostoCompra().getText());
        // String ciudadNacimiento = ventanaInicio.getFieldNitProveedorVenta().getText().toUpperCase();
        // String paisNacimiento = "Colombia".toUpperCase();
        // String sexo = dropSexoAfiliado.getSelectedItem().toString();
        // String tipoSangre = dropTipoSangreAfiliado.getSelectedItem().toString();
        // String grupoSanguineo = tipoSangre.substring(0, tipoSangre.length()-1);
        // String rh = tipoSangre.substring(tipoSangre.length()-1);
        // String estadoCivil = dropEstadoCivilAfiliado.getSelectedItem().toString();
        // String direccion = ventanaInicio.getFieldDireccionAfiliado().getText();
        // String ciudadResidencia = ventanaInicio.getFieldCiudadResidenciaAfiliado().getText().toUpperCase();
        // String telefono = ventanaInicio.getFieldTelefonoAfiliado().getText();
        // String regimen = dropRegimenAfiliado.getSelectedItem().toString();
        // String eps = ventanaInicio.getFieldEpsAfiliado().getText().toUpperCase();

        // Afiliado afiliado = new Afiliado(tipoDeDocumento, numeroDeDocumento, nombre, apellido, fechaNacimiento, ciudadNacimiento, paisNacimiento, sexo, grupoSanguineo, rh, estadoCivil, direccion, ciudadResidencia, telefono, regimen, eps);

        // return afiliado;
    // }

    public void agregarMedico(){

        // if(revisarFieldsMedico())
        // {
        //     String tipoDeDocumento = dropTipoDocumetoMedico.getSelectedItem().toString();
        //     Integer numeroDeDocumento = Integer.valueOf(ventanaInicio.getFieldNumeroDocumentoMedico().getText());
        //     Medico medico = crearMedico();
        //     if(entidadSalud.getDaoMedicos().añadir(medico)){
        //         JOptionPane.showMessageDialog(null,"¡Médico " + tipoDeDocumento + " " + numeroDeDocumento + " agregado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
        //         limpiarFormulario(tipoCategoria);

        //     } else {
        //         JOptionPane.showMessageDialog(null,"El médico " + tipoDeDocumento + " " + numeroDeDocumento + " ya se encuentra registrado en el sistema, no lo puede volver a registrar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
        //     }
        // }
    }

    public void editarMedico(){
        // if(revisarFieldsMedico())
        // {
        //     String tipoDeDocumento = dropTipoDocumetoMedico.getSelectedItem().toString();
        //     Integer numeroDeDocumento = Integer.valueOf(ventanaInicio.getFieldNumeroDocumentoMedico().getText());
        //     Medico medico = crearMedico();
        //     if(entidadSalud.getDaoMedicos().elementoPresente(numeroDeDocumento) && entidadSalud.getDaoMedicos().actualizar(numeroDeDocumento, medico)){
        //         JOptionPane.showMessageDialog(null,"¡Datos del médico " + tipoDeDocumento + " " + numeroDeDocumento + " actualizados correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
        //         limpiarFormulario(tipoCategoria);

        //     } else {
        //         JOptionPane.showMessageDialog(null,"El médico " + tipoDeDocumento + " " + numeroDeDocumento + " no se encuentra registrado en el sistema, primero debe registrarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
        //     }
        // }
    }

    public void eliminarMedico(){
        // String tipoDeDocumento = dropTipoDocumetoMedico.getSelectedItem().toString();
        // if (!tipoDeDocumento.equals("C.C") && !tipoDeDocumento.equals("C.E")){
        //     JOptionPane.showMessageDialog(null,"Por favor ingrese un tipo de documento válido", "Advertencia", JOptionPane.ERROR_MESSAGE);
        //     return;
        // }

        // String stringNumeroDeDocumento = ventanaInicio.getFieldNumeroDocumentoMedico().getText();
        // Integer numeroDeDocumento;
        // try{
        //     numeroDeDocumento = Integer.valueOf(stringNumeroDeDocumento);
        // } catch (NumberFormatException exception){
        //     JOptionPane.showMessageDialog(null,"Por favor ingrese un número de documento válido, sin puntos ni espacios, solo números.\nEjemplo: 1144000000", "Advertencia", JOptionPane.ERROR_MESSAGE);
        //     return;
        // }

        // if(entidadSalud.getDaoMedicos().elementoPresente(numeroDeDocumento)){
        //     int continuar = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar al médico " + tipoDeDocumento + " " + numeroDeDocumento + "? \nEsta acción no se puede deshacer.", "¿Desea proceder con la eliminación?", JOptionPane.YES_NO_OPTION);
        //     if(continuar == JOptionPane.YES_OPTION && entidadSalud.getDaoMedicos().eliminar(numeroDeDocumento)){
        //         JOptionPane.showMessageDialog(null,"¡El médico " + tipoDeDocumento + " " + numeroDeDocumento + " fue eliminado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
        //         limpiarFormulario(tipoCategoria);
        //     }
        // } else {
        //     JOptionPane.showMessageDialog(null,"El médico " + tipoDeDocumento + " " + numeroDeDocumento + " no se encuentra registrado en el sistema, no puede eliminarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
        // }
    }

    // public Medico crearMedico(){
    //     String tipoDeDocumento = dropTipoDocumetoMedico.getSelectedItem().toString();
    //     Integer numeroDeDocumento = Integer.valueOf(ventanaInicio.getFieldNumeroDocumentoMedico().getText());
    //     String nombre = ventanaInicio.getFieldNombreMedico().getText().toUpperCase();
    //     String apellido = ventanaInicio.getFieldApellidoMedico().getText().toUpperCase();
    //     LocalDate fechaNacimiento = LocalDate.parse(ventanaInicio.getFieldFechaNacimientoMedico().getText());
    //     String sexo = dropSexoMedico.getSelectedItem().toString();
    //     // ServicioMedico especialidadMedica = encontrarEspecialidadMedica();
    //     String universidad = ventanaInicio.getFieldUniversidadMedico().getText().toUpperCase();
    //     String telefono = ventanaInicio.getFieldTelefonoMedico().getText();
    //     // Medico medico = new Medico(tipoDeDocumento, numeroDeDocumento, nombre, apellido, fechaNacimiento, sexo, especialidadMedica, universidad, telefono);
    //     return medico;
    // }

    // public ServicioMedico encontrarEspecialidadMedica(){
    //     Integer idEspecialidadMedica = Integer.valueOf(dropServicioMedico.getSelectedItem().toString().split(" ")[0]);
    //     // ServicioMedico especialidadMedica = entidadSalud.getDaoServiciosMedicos().obtenerElemento(idEspecialidadMedica);

    //     // return especialidadMedica;
    // }

    // public void agregarServicioMedico(){
    //     if(revisarFieldsServicioMedico()){
    //         Integer idServicio = Integer.valueOf(ventanaInicio.getFieldIdServicioMedico().getText());
    //         String nombreServicio = ventanaInicio.getFieldNombreServicio().getText();
    //         ServicioMedico servicioMedico = crearServicioMedico();
    //         if(entidadSalud.getDaoServiciosMedicos().añadir(servicioMedico)){
    //             JOptionPane.showMessageDialog(null,"¡Servicio médico " + idServicio + " " + nombreServicio + " agregado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
    //             limpiarFormulario(tipoCategoria);

    //         } else {
    //             JOptionPane.showMessageDialog(null,"El Servicio médico " + idServicio + " " + nombreServicio + " ya se encuentra registrado en el sistema, no lo puede volver a registrar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         }
    //     }
    // }

    // public void editarServicioMedico(){
    //     if(revisarFieldsServicioMedico()){
    //         Integer idServicio = Integer.valueOf(ventanaInicio.getFieldIdServicioMedico().getText());
    //         String nombreServicio = ventanaInicio.getFieldNombreServicio().getText();
    //         ServicioMedico servicioMedico = crearServicioMedico();
    //         if(entidadSalud.getDaoServiciosMedicos().elementoPresente(idServicio) && entidadSalud.getDaoServiciosMedicos().actualizar(idServicio, servicioMedico)){
    //             JOptionPane.showMessageDialog(null,"¡Datos del servicio médico " + idServicio + " " + nombreServicio + " actualizados correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
    //             limpiarFormulario(tipoCategoria);

    //         } else {
    //             JOptionPane.showMessageDialog(null,"El Servicio médico " + idServicio + " " + nombreServicio + " no se encuentra registrado en el sistema, primero debe registrarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         }
    //     }
    // }

    // public void eliminarServicioMedico(){
    //     String stringIdServicioMedico = ventanaInicio.getFieldIdServicioMedico().getText();
    //     Integer idServicio;
    //     try{
    //         idServicio = Integer.valueOf(stringIdServicioMedico);
    //     } catch (NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número ID válido, sin puntos ni espacios, solo números.\nEjemplo: 231", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return;
    //     }

    //     if(entidadSalud.getDaoServiciosMedicos().elementoPresente(idServicio)){
    //         int continuar = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar al servicio " + idServicio + "? \nEsta acción no se puede deshacer.", "¿Desea proceder con la eliminación?", JOptionPane.YES_NO_OPTION);
    //         if(continuar == JOptionPane.YES_OPTION && entidadSalud.getDaoServiciosMedicos().eliminar(idServicio)){
    //             JOptionPane.showMessageDialog(null,"¡El servicio médico " + idServicio + " fue eliminado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
    //             limpiarFormulario(tipoCategoria);
    //         }          
    //     } else {
    //         JOptionPane.showMessageDialog(null,"¡El servicio médico " + idServicio + " " + " no se encuentra registrado en el sistema, no puede eliminarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //     }
    // }

    // public ServicioMedico crearServicioMedico(){
    //     Integer idServicio = Integer.valueOf(ventanaInicio.getFieldIdServicioMedico().getText());
    //     String nombreServicio = ventanaInicio.getFieldNombreServicio().getText();
    //     String descripcionServicio = ventanaInicio.getAreaDescripcionServicio().getText();

    //     ServicioMedico servicioMedico = new ServicioMedico(idServicio, nombreServicio, descripcionServicio);
    //     return servicioMedico;
    // }

    // public void agregarConsultorio(){
    //     if(revisarFieldsConsultorio())
    //     {
    //         Integer idConsultorio = Integer.valueOf(ventanaInicio.getFieldIdConsultorio().getText());
    //         Consultorio consultorio = crearConsultorio();
    //         if(entidadSalud.getDaoConsultorios().añadir(consultorio)){
    //             JOptionPane.showMessageDialog(null,"¡Consultorio con id " + idConsultorio + " agregado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
    //             limpiarFormulario(tipoCategoria);
                
    //         } else {
    //             JOptionPane.showMessageDialog(null,"El consultorio con id " + idConsultorio + " ya se encuentra registrado en el sistema, no lo puede volver a registrar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         }
    //     }
    // }

    // public void editarConsultorio(){
    //     if(revisarFieldsConsultorio())
    //     {
    //         Integer idConsultorio = Integer.valueOf(ventanaInicio.getFieldIdConsultorio().getText());
    //         Consultorio consultorio = crearConsultorio();
    //         if(entidadSalud.getDaoConsultorios().elementoPresente(idConsultorio) && entidadSalud.getDaoConsultorios().actualizar(idConsultorio, consultorio)){
    //             JOptionPane.showMessageDialog(null,"¡Datos del consultorio con id " + idConsultorio + " actualizados correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
    //             limpiarFormulario(tipoCategoria);
                
    //         } else {
    //             JOptionPane.showMessageDialog(null,"El consultorio con id " + idConsultorio + " no se encuentra registrado en el sistema, primero debe registrarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         }
    //     }
    // }

    // public void eliminarConsultorio(){
    //     String stringIdConsultorio = ventanaInicio.getFieldIdConsultorio().getText();
    //     Integer idConsultorio;
    //     try{
    //         idConsultorio = Integer.valueOf(stringIdConsultorio);
    //     } catch (NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número ID del consultorio válido, sin puntos ni espacios, solo números. Ejemplo: 5202", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return;
    //     }

    //     if(entidadSalud.getDaoConsultorios().elementoPresente(idConsultorio)){
    //         int continuar = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar al consultorio con id " + idConsultorio + " " + "? \nEsta acción no se puede deshacer.", "¿Desea proceder con la eliminación?", JOptionPane.YES_NO_OPTION);
    //         if(continuar == JOptionPane.YES_OPTION && entidadSalud.getDaoConsultorios().eliminar(idConsultorio)){
    //             JOptionPane.showMessageDialog(null,"¡El consultorio " + idConsultorio + " fue eliminado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
    //             limpiarFormulario(tipoCategoria);
    //         }          
    //     } else {
    //         JOptionPane.showMessageDialog(null,"El consultorio " + idConsultorio + " no se encuentra registrado en el sistema, no puede eliminarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //     }
    // }

    // public Consultorio crearConsultorio(){
    //     Integer idConsultorio = Integer.valueOf(ventanaInicio.getFieldIdConsultorio().getText());
    //     Integer numeroConsultorio = Integer.valueOf(ventanaInicio.getFieldNumeroConsultorio().getText());
    //     String sedeConsultorio = dropUbicacionConsultorios.getSelectedItem().toString();
    //     ServicioMedico especialidadMedica = encontrarEspecialidadMedica();

    //     Consultorio consultorio = new Consultorio(idConsultorio, sedeConsultorio, numeroConsultorio, especialidadMedica);
    //     return consultorio;
    // }

    // public void agregarCita(){
    //     if(revisarFieldsCitas()){
    //         Integer idCita = Integer.valueOf(ventanaInicio.getFieldIdProductos().getText());
    //         Afiliado afiliado = encontrarAfiliado();
    //         Medico medico = encontrarMedico();
    //         Consultorio consultorio = encontrarConsultorio();
    //         Cita cita = crearCita();

    //         if(afiliado.tieneEstaCitaAgendada(cita)){
    //             JOptionPane.showMessageDialog(null, "El afiliado " + afiliado + " ya tiene asignada la cita con Id " + idCita + ".\n¡No le puede agendar de nuevo la misma cita!", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }
            
    //         if(!afiliado.tieneDisponibilidadParaCita(cita.getFechaYHora())){
    //             JOptionPane.showMessageDialog(null,"El afiliado " + afiliado + " ya tiene un cita registrada a la misma hora " + cita.getFechaYHora() + ".", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }

    //         if(medico.tieneEstaCitaAgendada(cita)){
    //             JOptionPane.showMessageDialog(null, "El medico " + medico + " ya tiene asignada la cita con Id " + idCita + ".\n¡No le puede agendar de nuevo la misma cita!", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }

    //         if(!medico.tieneDisponibilidadParaCita(cita.getFechaYHora())){
    //             JOptionPane.showMessageDialog(null,"El medico" + medico + " ya tiene un cita registrada a la misma hora " + cita.getFechaYHora() + ".", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }

    //         if(consultorio.tieneEstaCitaAgendada(cita)){
    //             JOptionPane.showMessageDialog(null, "El consultorio " + consultorio + " ya tiene asignada la cita con Id " + idCita + ".\n¡No le puede agendar de nuevo la misma cita!", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }

    //         if(!consultorio.tieneDisponibilidadParaCita(cita.getFechaYHora())){
    //             JOptionPane.showMessageDialog(null,"El consultorio" + consultorio + " ya tiene un cita registrada a la misma hora " + cita.getFechaYHora() + ".", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //             return;
    //         }

    //         entidadSalud.getDaoCitas().añadir(cita);
    //         entidadSalud.getDaoAfiliados().obtenerElemento(afiliado.getDocumento()).agregarCita(cita);
    //         entidadSalud.getDaoMedicos().obtenerElemento(medico.getDocumento()).agregarCita(cita);
    //         entidadSalud.getDaoConsultorios().obtenerElemento(consultorio.getIdConsultorio()).agregarCita(cita);
    //         serialCitas++;
    //         JOptionPane.showMessageDialog(null,"¡Cita con id " + idCita + " agregada correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
    //         limpiarFormulario(tipoCategoria);
    //     }
    // }

    // public void editarCita(){
    //     if(revisarFieldsCitas()){
    //         Integer idCita = Integer.valueOf(ventanaInicio.getFieldIdProductos().getText());
    //         Afiliado afiliado = encontrarAfiliado();
    //         Medico medico = encontrarMedico();
    //         Consultorio consultorio = encontrarConsultorio();
    //         Cita cita = crearCita();

    //         if(entidadSalud.getDaoCitas().elementoPresente(idCita)){
    //             if(!afiliado.tieneEstaCitaAgendada(cita)){
    //                 JOptionPane.showMessageDialog(null, "El afiliado " + afiliado + " no tiene asignada la cita con ID " + idCita + ".\n¡No le puede modificar una cita que no tiene agendada!", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //                 return;
    //             }

    //             if(!medico.tieneEstaCitaAgendada(cita)){
    //                 JOptionPane.showMessageDialog(null, "El medico " + medico + " no tiene asignada la cita con ID " + idCita + ".\n¡No le puede modificar una cita que no tiene agendada!", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //                 return;
    //             }

    //             if(!consultorio.tieneEstaCitaAgendada(cita)){
    //                 JOptionPane.showMessageDialog(null, "El consultorio " + medico + " no tiene asignada la cita con ID " + idCita + ".\n¡No le puede modificar una cita que no tiene agendada!", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //                 return;
    //             }
                
    //             if(entidadSalud.getDaoCitas().actualizar(idCita, cita)){
    //                 entidadSalud.getDaoAfiliados().obtenerElemento(afiliado.getDocumento()).eliminarCita(cita);
    //                 entidadSalud.getDaoMedicos().obtenerElemento(medico.getDocumento()).eliminarCita(cita);
    //                 entidadSalud.getDaoConsultorios().obtenerElemento(consultorio.getIdConsultorio()).eliminarCita(cita);
    //                 entidadSalud.getDaoAfiliados().obtenerElemento(afiliado.getDocumento()).agregarCita(cita);
    //                 entidadSalud.getDaoMedicos().obtenerElemento(medico.getDocumento()).agregarCita(cita);
    //                 entidadSalud.getDaoConsultorios().obtenerElemento(consultorio.getIdConsultorio()).agregarCita(cita);
    //                 JOptionPane.showMessageDialog(null,"¡Cita con id " + idCita + " modificada correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
    //                 limpiarFormulario(tipoCategoria);
    //             }

    //         } else {
    //             JOptionPane.showMessageDialog(null,"La cita con Id " + idCita + " no se encuentra registrada en el sistema, primero debe registrarla.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         }
    //     }
    // }
    

    // public void eliminarCita(){
    //     String stringIdCita = ventanaInicio.getFieldIdProductos().getText();
    //     Integer idCita;
    //     try{
    //         idCita = Integer.valueOf(stringIdCita);
    //     } catch(NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número ID de cita válido, sin puntos ni espacios, solo números. Ejemplo: 1002", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return;
    //     }

    //     if(entidadSalud.getDaoCitas().elementoPresente(idCita)){
    //         Cita cita = entidadSalud.getDaoCitas().obtenerElemento(idCita);
    //         int continuar = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar la cita con id " + idCita + " " + "? \nEsta acción no se puede deshacer.", "¿Desea proceder con la eliminación?", JOptionPane.YES_NO_OPTION);
    //         if(continuar == JOptionPane.YES_OPTION && entidadSalud.getDaoCitas().eliminar(idCita)){
    //             JOptionPane.showMessageDialog(null,"¡La cita " + idCita + " fue eliminada correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
    //             entidadSalud.getDaoAfiliados().obtenerElemento(cita.getAfiliado().getDocumento()).eliminarCita(cita);
    //             entidadSalud.getDaoMedicos().obtenerElemento(cita.getMedico().getDocumento()).eliminarCita(cita);
    //             entidadSalud.getDaoConsultorios().obtenerElemento(cita.getConsultorio().getIdConsultorio()).eliminarCita(cita);
    //             limpiarFormulario(tipoCategoria);
    //         }          
    //     } else {
    //         JOptionPane.showMessageDialog(null,"La cita " + idCita + " no se encuentra registrada en el sistema, no puede eliminarla.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //     }
    // }

    // public Cita crearCita(){
    //     Integer idCita = Integer.valueOf(ventanaInicio.getFieldIdProductos().getText());
    //     Afiliado afiliado = encontrarAfiliado();
    //     ServicioMedico servicioMedico = encontrarServicioCita();
    //     Medico medico = encontrarMedico();
    //     Consultorio consultorio = encontrarConsultorio();
    //     LocalDate fecha = LocalDate.parse(ventanaInicio.getFieldCantidadProductos().getText());
    //     LocalTime hora = LocalTime.parse(ventanaInicio.getFieldPrecioProductos().getText());
    //     LocalDateTime fechaYHora = LocalDateTime.of(fecha, hora);

    //     Cita cita = new Cita(idCita, afiliado, servicioMedico, medico, consultorio, fechaYHora);

    //     return cita;
    // }

    // public Afiliado encontrarAfiliado(){
    //     Integer idAfiliado = Integer.valueOf(ventanaInicio.getFieldNombresProductos().getText());
    //     Afiliado afiliado = entidadSalud.getDaoAfiliados().obtenerElemento(idAfiliado);

    //     return afiliado;
    // }

    // public ServicioMedico encontrarServicioCita(){
    //     Integer idServicioMedico = Integer.valueOf(dropServicioMedicoCitas.getSelectedItem().toString().split(" ")[0]);
    //     ServicioMedico servicioMedico = entidadSalud.getDaoServiciosMedicos().obtenerElemento(idServicioMedico);

    //     return servicioMedico;
    // }

    // public Medico encontrarMedico(){
    //     Integer idMedico = Integer.valueOf(dropMedicosCitas.getSelectedItem().toString().split(" ")[0]);
    //     Medico medico = entidadSalud.getDaoMedicos().obtenerElemento(idMedico);

    //     return medico;
    // }

    // public Consultorio encontrarConsultorio(){
    //     Integer idConsultorio = Integer.valueOf(dropConsultoriosCitas.getSelectedItem().toString().split(" ")[0]);
    //     Consultorio consultorio = entidadSalud.getDaoConsultorios().obtenerElemento(idConsultorio);

    //     return consultorio;
    // }

    // public boolean revisarFieldsAfiliado(){
    //     String tipoDeDocumento = dropTipoDocumetoAfiliado.getSelectedItem().toString();
    //     if (!tipoDeDocumento.equals("T.I") && !tipoDeDocumento.equals("C.C") && !tipoDeDocumento.equals("C.E")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un tipo de documento válido", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String stringNumeroDeDocumento = ventanaInicio.getFieldNombreProveedprVenta().getText();
    //     try{
    //         Integer numeroDeDocumento = Integer.valueOf(stringNumeroDeDocumento);
    //     } catch (NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número de documento válido, sin puntos ni espacios, solo números.\nEjemplo: 1144000000", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String nombre = ventanaInicio.getFieldIdCompra().getText();
    //     if(nombre.isEmpty() || nombre.isBlank() || nombre == null || !nombre.matches("[a-zA-Z ]+")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un nombre válido.\nEl nombre no debe contener números ni caracteres especiales, únicamente letras.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String apellido = ventanaInicio.getFieldFechaCompra().getText();
    //     if(apellido.isEmpty() || apellido.isBlank() || apellido == null || !apellido.matches("[a-zA-Z ]+")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un apellido válido.\nEl apellido no debe contener números ni caracteres especiales, únicamente letras.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String stringFechaNacimiento = ventanaInicio.getFieldCostoCompra().getText();
    //     try{
    //         LocalDate fechaNacimiento = LocalDate.parse(stringFechaNacimiento);
    //     } catch (DateTimeParseException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una fecha de nacimiento válida, con el formato AAAA-MM-DD.\nEjemplo: 1990-01-01", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String ciudadNacimiento = ventanaInicio.getFieldNitProveedorVenta().getText();
    //     if(ciudadNacimiento.isEmpty() || ciudadNacimiento.isBlank() || ciudadNacimiento == null || !ciudadNacimiento.matches("[a-zA-Z ]+")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un lugar de nacimiento válido para el afiliado.\nEl nombre del lugar no debe contener números ni caracteres especiales, únicamente letras.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String sexo = dropSexoAfiliado.getSelectedItem().toString();
    //     if (!sexo.equals("M") && !sexo.equals("F") && !sexo.equals("Otro")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una opción válida para el sexo del afiliado.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String tipoSangre = dropTipoSangreAfiliado.getSelectedItem().toString();
    //     if (!tipoSangre.equals("A+") && !tipoSangre.equals("A-") && !tipoSangre.equals("B+") && !tipoSangre.equals("B-") && !tipoSangre.equals("AB+") && !tipoSangre.equals("AB-") && !tipoSangre.equals("O+") && !tipoSangre.equals("O-")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una opción válida para el tipo de sangre.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String estadoCivil = dropEstadoCivilAfiliado.getSelectedItem().toString();
    //     if (!estadoCivil.equals("Soltero(a)") && !estadoCivil.equals("Casado(a)") && !estadoCivil.equals("Viudo(a)")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una opción válida para el estado civil.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String direccion = ventanaInicio.getFieldDireccionAfiliado().getText();
    //     if(direccion.isEmpty() || direccion.isBlank() || direccion == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una dirección válida.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String ciudadResidencia = ventanaInicio.getFieldCiudadResidenciaAfiliado().getText();
    //     if(ciudadResidencia.isEmpty() || ciudadResidencia.isEmpty() || ciudadResidencia == null || !ciudadResidencia.matches("[a-zA-Z ]+")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una ciudad de residencia válida.\nEl nombre de la ciudad no debe contener números ni caracteres especiales, únicamente letras.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String telefono = ventanaInicio.getFieldTelefonoAfiliado().getText();
    //     if(telefono.isEmpty() || telefono.isBlank() || telefono == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número de teléfono válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String regimen = dropRegimenAfiliado.getSelectedItem().toString();
    //     if (!regimen.equals("Contributivo") && !regimen.equals("Subsidiado")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un régimen de salud válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
        
    //     String eps = ventanaInicio.getFieldEpsAfiliado().getText();
    //     if(eps.isEmpty() || eps.isBlank() || eps == null || !eps.matches("[a-zA-Z ]+")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una eps válida para el afiliado.\nEl nombre de la eps no debe contener números ni caracteres especiales, únicamente letras.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     return true;
    // }

    // public boolean revisarFieldsMedico(){
    //     String tipoDeDocumento = dropTipoDocumetoMedico.getSelectedItem().toString();
    //     if (!tipoDeDocumento.equals("C.C") && !tipoDeDocumento.equals("C.E")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un tipo de documento válido", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String stringNumeroDeDocumento = ventanaInicio.getFieldNumeroDocumentoMedico().getText();
    //     try{
    //         Integer numeroDeDocumento = Integer.valueOf(stringNumeroDeDocumento);
    //     } catch (NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número de documento válido, sin puntos ni espacios, solo números.\nEjemplo: 1144000000", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String nombre = ventanaInicio.getFieldNombreMedico().getText();
    //     if(nombre.isEmpty() || nombre.isBlank() || nombre == null || !nombre.matches("[a-zA-Z ]+")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un nombre válido.\nEl nombre no debe contener números ni caracteres especiales, únicamente letras.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String apellido = ventanaInicio.getFieldApellidoMedico().getText();
    //     if(apellido.isEmpty() || apellido.isBlank() || apellido == null || !apellido.matches("[a-zA-Z ]+")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un apellido válido.\nEl apellido no debe contener números ni caracteres especiales, únicamente letras.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String stringFechaNacimiento = ventanaInicio.getFieldFechaNacimientoMedico().getText();
    //     try{
    //         LocalDate fechaNacimiento = LocalDate.parse(stringFechaNacimiento);
    //     } catch (DateTimeParseException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una fecha de nacimiento válida, con el formato AAAA-MM-DD.\nEjemplo: 1990-01-01", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String sexo = dropSexoMedico.getSelectedItem().toString();
    //     if (!sexo.equals("M") && !sexo.equals("F") && !sexo.equals("Otro")){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una opción válida para el sexo del médico.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     if(dropServicioMedico.getSelectedItem() == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una especialidad médica válida.\nSi en la lista no se encuentra la especialidad médica que busca, debe crearla desde la categoría \"Servicios médicos\"", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     String especialidadMedica = dropServicioMedico.getSelectedItem().toString();
    //     if(especialidadMedica.isEmpty() || especialidadMedica.isBlank() || especialidadMedica.equals("Seleccionar") || dropServicioMedico.getSelectedItem() == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una especialidad médica válida.\nSi en la lista no se encuentra la especialidad médica que busca, debe crearla desde la categoría \"Servicios médicos\"", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String universidad = ventanaInicio.getFieldUniversidadMedico().getText();
    //     if(universidad.isEmpty() || universidad.isBlank() || universidad == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese el nombre de la Universidad donde estudió el médico.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String telefono = ventanaInicio.getFieldTelefonoMedico().getText();
    //     if(telefono.isEmpty() || telefono.isBlank() || telefono == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número de teléfono válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     return true;
    // }

    // public boolean revisarFieldsServicioMedico(){
    //     String stringIdServicioMedico = ventanaInicio.getFieldIdServicioMedico().getText();
    //     try{
    //         Integer idServicio = Integer.valueOf(stringIdServicioMedico);
    //     } catch (NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número ID válido, sin puntos ni espacios, solo números.\nEjemplo: 231", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String nombreServicio = ventanaInicio.getFieldNombreServicio().getText();
    //     if(nombreServicio.isEmpty() || nombreServicio.isBlank() || nombreServicio == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un nombre de servicio válido.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String descripcionServicio = ventanaInicio.getAreaDescripcionServicio().getText();
    //     if(descripcionServicio.isEmpty() || descripcionServicio.isBlank() || descripcionServicio == null){
    //         JOptionPane.showMessageDialog(null, "Por favor ingrese una descripción del servicio.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     return true;
    // }

    // public boolean revisarFieldsConsultorio(){
    //     String stringIdConsultorio = ventanaInicio.getFieldIdConsultorio().getText();
    //     try{
    //         Integer idConsultorio = Integer.valueOf(stringIdConsultorio);
    //     } catch (NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número ID del consultorio válido, sin puntos ni espacios, solo números.\nEjemplo: 5202", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String stringNumeroConsultorio = ventanaInicio.getFieldNumeroConsultorio().getText();
    //     try{
    //         Integer numeroConsultorio = Integer.valueOf(stringNumeroConsultorio);
    //     } catch (NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número de consultorio válido, sin puntos ni espacios, solo números.\nEjemplo: 202", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String sedeConsultorio = dropUbicacionConsultorios.getSelectedItem().toString();
    //     if (!sedeConsultorio.equals("Norte") && !sedeConsultorio.equals("Sur")){
    //         JOptionPane.showMessageDialog(null,"Por favor seleccione una de las opciones válidas para la sede donde se encuentra el consultorio.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     if(dropServicioMedico.getSelectedItem() == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un servicio médico válido.\nSi en la lista no se encuentra el servicio / especialidad médica que busca, debe crearla desde la categoría \"Servicios médicos\"", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     String especialidadMedica = dropServicioMedico.getSelectedItem().toString();
    //     if(especialidadMedica.isEmpty() || especialidadMedica.isBlank() || especialidadMedica.equals("Seleccionar") || especialidadMedica == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un servicio médico.\nSi en la lista no se encuentra el servicio / especialidad médica que busca, debe crearla desde la categoría \"Servicios médicos\"", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     return true;
    // }

    // public boolean revisarFieldsCitas(){
    //     String stringIdCita = ventanaInicio.getFieldIdProductos().getText();
    //     try{
    //         Integer idCita = Integer.valueOf(stringIdCita);
    //     } catch (NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número ID de cita válido, sin puntos ni espacios, solo números.\nEjemplo: 10001", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String stringDocumentoAfiliado = ventanaInicio.getFieldNombresProductos().getText();
    //     Integer documentoAfiliado;
    //     try{
    //         documentoAfiliado = Integer.valueOf(stringDocumentoAfiliado);
    //     } catch (NumberFormatException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un número de documento del afiliado válido, sin puntos ni espacios, solo números.\nEjemplo: 1144000000", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     if(!entidadSalud.getDaoAfiliados().elementoPresente(documentoAfiliado)){
    //         JOptionPane.showMessageDialog(null,"El afiliado con número de documento " + documentoAfiliado + " no se encuentra registrado en el sistema.\nPor favor verifique el número de documento e inténtelo de nuevo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     if(dropServicioMedicoCitas.getSelectedItem() == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un servicio médico válido.\nSi en la lista no se encuentra el servicio / especialidad médica que busca, debe crearla desde la categoría \"Servicios médicos\"", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     String servicioMedico = dropServicioMedicoCitas.getSelectedItem().toString();
    //     if(servicioMedico.isEmpty() || servicioMedico.isBlank() || servicioMedico.equals("Seleccionar") || servicioMedico == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un servicio médico.\nSi en la lista no se encuentra el servicio / especialidad médica que busca, debe crearla desde la categoría \"Servicios médicos\"", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     if(dropMedicosCitas.getSelectedItem() == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un médico.\nSi en la lista no se muestra ningún médico, entonces no hay médicos con disponibilidad para la especialidad seleccionada.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     String medico = dropMedicosCitas.getSelectedItem().toString();
    //     if(medico.isEmpty() || medico.isBlank() || medico == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un médico.\nSi en la lista no se muestra ningún médico, entonces no hay médicos con disponibilidad para la especialidad seleccionada.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     if(dropConsultoriosCitas.getSelectedItem() == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un consultorio para la cita.\nSi en la lista no se muestra ningún consultorio, entonces no hay consultorios disponibles para la especialidad seleccionada.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     String consultorio = dropConsultoriosCitas.getSelectedItem().toString();
    //     if(consultorio.isEmpty() || consultorio.isBlank() || consultorio == null){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese un consultorio para la cita.\nSi en la lista no se muestra ningún consultorio, entonces no hay consultorios disponibles para la especialidad seleccionada.", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }    

    //     String stringFechaCita = ventanaInicio.getFieldCantidadProductos().getText();
    //     try{
    //         LocalDate fechaCita = LocalDate.parse(stringFechaCita);
    //     } catch (DateTimeParseException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una fecha para la cita válida, con el formato AAAA-MM-DD.\nEjemplo: 2022-12-20", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }

    //     String stringHoraCita = ventanaInicio.getFieldPrecioProductos().getText();
    //     try{
    //         LocalTime horaCita = LocalTime.parse(stringHoraCita);
    //     } catch (DateTimeParseException exception){
    //         JOptionPane.showMessageDialog(null,"Por favor ingrese una hora para la cita, con el formato HH:MM (sistema horario de 24 horas).\nEjemplo: 14:20", "Advertencia", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    //     return true;
    // }

}