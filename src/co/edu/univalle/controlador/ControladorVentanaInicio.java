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

import co.edu.univalle.dao.*;
import co.edu.univalle.modelo.*;
import co.edu.univalle.vista.*;
import java.awt.event.*;
import java.math.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
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
    private HashMap<Integer, Integer> listaProductosVenta = new HashMap<>();
    private HashMap<Integer, Integer> listaProductosCompra = new HashMap<>();
    private HashMap<Integer, BigDecimal> listaPreciosCompra = new HashMap<>();
    
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
        this.ventanaInicio.addFocusListener(new CalculateFocus());
        ventanaListados.addListener(new CalculateListener());
        ventanaListados.addFocusListener(new CalculateFocus());
        tipoCategoria = (String)ventanaInicio.getDropCategorias().getSelectedItem();
        pintarFormulario(tipoCategoria);
    }

    class CalculateFocus implements FocusListener{

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(tipoCategoria == "Ventas (a clientes)" && !ventanaListados.isActive()){
                String stringIdCliente = ventanaInicio.getFieldCedulaClienteVenta().getText();
                Integer idCliente;
                try{
                    idCliente = Integer.valueOf(stringIdCliente);

                } catch (NumberFormatException exception){
                    return;
                }

                if(supermercado.getClientes().elementoPresente(idCliente)){
                    Cliente cliente = supermercado.getClientes().getElemento(idCliente);
                    ventanaInicio.getFieldNombresClienteVenta().setText(cliente.getNombre());

                } else {
                    ventanaInicio.getFieldNombresClienteVenta().setText("");
                }

            } else if(tipoCategoria == "Compras (a proveedores)" && !ventanaListados.isActive()){
                String stringIdProveedor = ventanaInicio.getFieldNitProveedorCompra().getText();
                Integer idProveedor;
                try{
                    idProveedor = Integer.valueOf(stringIdProveedor);

                } catch (NumberFormatException exception){
                    return;
                }
                if(supermercado.getProveedores().elementoPresente(idProveedor)){
                    Proveedor proveedor = supermercado.getProveedores().getElemento(idProveedor);
                    ventanaInicio.getFieldNombreProveedorCompra().setText(proveedor.getNombre());

                } else {
                    ventanaInicio.getFieldNombreProveedorCompra().setText("");
                }

            } else if(ventanaListados.isActive()){
                String stringIdProducto = ventanaListados.getId().getText();
                Integer idProducto;
                try{
                    idProducto = Integer.valueOf(stringIdProducto);
                } catch (NumberFormatException exception){
                    return;
                }
                if(supermercado.getProductos().elementoPresente(idProducto)){
                    Producto producto = supermercado.getProductos().getElemento(idProducto);
                    ventanaListados.getNombre().setText(producto.getNombreProducto());

                } else {
                    ventanaListados.getNombre().setText("");
                }

            } 
        }
    }

    class CalculateListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evento) {
            if (evento.getActionCommand().equalsIgnoreCase("Agregar") && !ventanaListados.isActive()){
                if(tipoCategoria == "Productos"){
                    if(ControladorProductos.revisarFieldsProductos(ventanaInicio)){
                        Producto nuevoProducto = ControladorProductos.crearProducto(ventanaInicio);
                        Integer idProducto = nuevoProducto.getIdentificacion();
                        String nombrePrdoucto = nuevoProducto.getNombreProducto();
                        if(supermercado.getProductos().añadir(nuevoProducto)){
                            JOptionPane.showMessageDialog(null,"¡El producto " + idProducto + " " + nombrePrdoucto + " agregado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                            serialProducto++;
                            limpiarFormulario(tipoCategoria);

                        } else {
                            JOptionPane.showMessageDialog(null,"El producto " + idProducto + " " + nombrePrdoucto + " ya se encuentra registrado en el sistema, no lo puede volver a registrar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if(tipoCategoria == "Clientes"){
                    if(ControladorClientes.revisarFieldsClientes(ventanaInicio)){
                        Cliente nuevoCliente = ControladorClientes.crearCliente(ventanaInicio);
                        Integer idCliente = nuevoCliente.getIdentificacion();
                        String nombreCliente = nuevoCliente.getNombre();
                        if(supermercado.getClientes().añadir(nuevoCliente)){
                            JOptionPane.showMessageDialog(null,"¡El cliente " + idCliente + " " + nombreCliente + " agregado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                            limpiarFormulario(tipoCategoria);

                        } else {
                            JOptionPane.showMessageDialog(null,"El cliente " + idCliente + " " + nombreCliente + " ya se encuentra registrado en el sistema, no lo puede volver a registrar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if(tipoCategoria == "Proveedores"){
                    if(ControladorProveedores.revisarFieldsProveedores(ventanaInicio)){
                        Proveedor nuevoProveedor = ControladorProveedores.crearProveedor(ventanaInicio);
                        Integer idProveedor = nuevoProveedor.getIdentificacion();
                        String nombreProveedor = nuevoProveedor.getNombre();
                        if(supermercado.getProveedores().añadir(nuevoProveedor)){
                            JOptionPane.showMessageDialog(null,"¡El proveedor " + idProveedor + " " + nombreProveedor + " agregado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                            limpiarFormulario(tipoCategoria);

                        } else {
                            JOptionPane.showMessageDialog(null,"¡El proveedor " + idProveedor + " " + nombreProveedor + " ya se encuentra registrado en el sistema, no lo puede volver a registrar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if(tipoCategoria == "Ventas (a clientes)"){
                    if(ControladorVentas.revisarFieldsVentas(ventanaInicio)){
                        Integer idCliente = Integer.valueOf(ventanaInicio.getFieldCedulaClienteVenta().getText());
                            if(!listaProductosVenta.isEmpty()){
                                Venta nuevaVenta = ControladorVentas.crearVenta(ventanaInicio, listaProductosVenta);
                                Integer idVenta = nuevaVenta.getIdentificacion();
                                if(supermercado.getVentas().añadir(nuevaVenta)){
                                    listaProductosVenta = new HashMap<>();
                                    supermercado.getClientes().getElemento(idCliente).agregarTransaccion(nuevaVenta);
                                    reducirStock(nuevaVenta);
                                    serialVenta++;
                                    JOptionPane.showMessageDialog(null,"¡La venta " + idVenta + " " + " fue agregada correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                                    limpiarFormulario(tipoCategoria);

                                } else {
                                    JOptionPane.showMessageDialog(null,"¡La venta " + idVenta + " ya se encuentra registrada en el sistema, no lo puede volver a registrar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null,"Debe agregar al menos un producto a la lista de productos de la venta.\nAgregue una lista de productos, haciendo clic sobre el botón \"Listar\".", "Advertencia", JOptionPane.ERROR_MESSAGE);
                            }
                    }

                } else if (tipoCategoria == "Compras (a proveedores)"){
                    if(ControladorCompras.revisarFieldsCompras(ventanaInicio)){
                        Integer idProveedor = Integer.valueOf(ventanaInicio.getFieldNitProveedorCompra().getText());
                        if(!listaPreciosCompra.isEmpty()){
                            Compra nuevaCompra = ControladorCompras.crearCompra(ventanaInicio, listaProductosCompra, listaPreciosCompra);
                            Integer idCompra = nuevaCompra.getIdentificacion();
                            if(supermercado.getCompras().añadir(nuevaCompra)){
                                listaProductosCompra = new HashMap<>();
                                listaPreciosCompra = new HashMap<>();
                                supermercado.getProveedores().getElemento(idProveedor).agregarTransaccion(nuevaCompra);
                                aumentarStock(nuevaCompra);
                                serialCompra++;
                                JOptionPane.showMessageDialog(null,"¡La compra " + idCompra + " " + " fue agregada correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                                limpiarFormulario(tipoCategoria);

                            } else {
                                JOptionPane.showMessageDialog(null,"¡La comora " + idCompra + " ya se encuentra registrada en el sistema, no la puede volver a registrar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,"Debe agregar al menos un producto a la lista de productos de la compra.\nAgregue una lista de productos, haciendo clic sobre el botón \"Listar\".", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }


                    }
                }
                pintarFormulario(tipoCategoria);

            } else if (evento.getActionCommand().equalsIgnoreCase("Agregar") && ventanaListados.isActive()){
                if(tipoCategoria == "Ventas (a clientes)"){
                    if(ControladorListar.revisarFieldsProductoEnVenta(ventanaListados)){
                        Integer idProducto = Integer.valueOf(ventanaListados.getId().getText());
                        Integer cantidadProducto = Integer.valueOf(ventanaListados.getCantidad().getText());
                        if(supermercado.getProductos().elementoPresente(idProducto)){
                            Producto producto = supermercado.getProductos().getElemento(idProducto);
                            if(producto.getCantidadStock() >= cantidadProducto){
                                if(!listaProductosVenta.containsKey(idProducto)){
                                    listaProductosVenta.put(idProducto, cantidadProducto);
                                    ventanaInicio.getFieldCostoVenta().setText(ControladorListar.calcularCostoVenta(supermercado, listaProductosVenta));
                                    JOptionPane.showMessageDialog(null,"¡El producto " + idProducto + " " + producto.getNombreProducto() + " agregado correctamente a la lista de productos!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                                    pintarListado(tipoCategoria);

                                } else {
                                    JOptionPane.showMessageDialog(null,"¡El producto " + idProducto + producto.getNombreProducto() + " ya fue agregado a la lista de productos de la venta.\nSi desea cambiar la cantidad del producto en la transacción, debe hacerlo con el botón editar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                                }

                            } else {
                                JOptionPane.showMessageDialog(null,"¡El producto " + idProducto + " " + producto.getNombreProducto() + " No tiene Stock suficiente!\nStock actual: " + producto.getCantidadStock() + ".\nCantidad ingresada: " + cantidadProducto + ".", "Advertencia", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null,"¡El producto " + idProducto + " no es un producto que se venda en el supermercado.\nPor favor verifique el ID del producto.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }
        
                } else if(tipoCategoria == "Compras (a proveedores)"){
                    if(ControladorListar.revisarFieldsProductoEnCompra(ventanaListados)){
                        Integer idProducto = Integer.valueOf(ventanaListados.getId().getText());
                        Integer cantidadProducto = Integer.valueOf(ventanaListados.getCantidad().getText());
                        BigDecimal costoProducto = BigDecimal.valueOf(Double.valueOf(ventanaListados.getCosto().getText()));
                        if(supermercado.getProductos().elementoPresente(idProducto)){
                            Producto producto = supermercado.getProductos().getElemento(idProducto);
                            if(!listaPreciosCompra.containsKey(idProducto)){
                                listaProductosCompra.put(idProducto, cantidadProducto);
                                listaPreciosCompra.put(idProducto, costoProducto);
                                ventanaInicio.getFieldCostoCompra().setText(ControladorListar.calcularCostoCompra(supermercado, listaProductosCompra, listaPreciosCompra));
                                JOptionPane.showMessageDialog(null,"¡El producto " + idProducto + " " + producto.getNombreProducto() + " agregado correctamente a la lista de productos!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                                pintarListado(tipoCategoria);

                            } else {
                                JOptionPane.showMessageDialog(null,"¡El producto " + idProducto + producto.getNombreProducto() + " ya fue agregado a la lista de productos de la compra.\nSi desea cambiar la cantidad del producto en la transacción, debe hacerlo con el botón editar.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null,"¡El producto " + idProducto + " no es un producto registrado.\nPor favor verifique el ID del producto, o registrelo desde la categoría \"Productos\".", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            
            } else if (evento.getActionCommand().equalsIgnoreCase("limpiar") && !ventanaListados.isActive()){
                limpiarFormulario(tipoCategoria);
    
            }else if (evento.getActionCommand().equalsIgnoreCase("limpiar") && ventanaListados.isActive()){
                ControladorListar.limpiar(ventanaListados, tipoCategoria);

            } else if (evento.getActionCommand().equalsIgnoreCase("Editar") && !ventanaListados.isActive()){
                if(tipoCategoria == "Productos"){
                    if(ControladorProductos.revisarFieldsProductos(ventanaInicio)){
                        Producto nuevoProducto = ControladorProductos.crearProducto(ventanaInicio);
                        Integer idProducto = nuevoProducto.getIdentificacion();
                        String nombrePrdoucto = nuevoProducto.getNombreProducto();
                        if(supermercado.getProductos().elementoPresente(idProducto) && supermercado.getProductos().actualizar(idProducto, nuevoProducto)){
                            JOptionPane.showMessageDialog(null,"¡Datos del producto " + idProducto + " " + nombrePrdoucto + " actualizados correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                            pintarFormulario(tipoCategoria);

                        } else {
                            JOptionPane.showMessageDialog(null,"El producto " + idProducto + " " + nombrePrdoucto + " no se encuentra registrado en el sistema, primero debe registrarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                } else if(tipoCategoria == "Clientes"){
                    if(ControladorClientes.revisarFieldsClientes(ventanaInicio)){
                        Cliente nuevoCliente = ControladorClientes.crearCliente(ventanaInicio);
                        Integer idCliente = nuevoCliente.getIdentificacion();
                        String nombreCliente = nuevoCliente.getNombre();
                        if(supermercado.getClientes().elementoPresente(idCliente) && supermercado.getClientes().actualizar(idCliente, nuevoCliente)){
                            JOptionPane.showMessageDialog(null,"¡Datos del cliente " + idCliente + " " + nombreCliente + " actualizados correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                            pintarFormulario(tipoCategoria);

                        } else {
                            JOptionPane.showMessageDialog(null,"El cliente " + idCliente + " " + nombreCliente + " no se encuentra registrado en el sistema, primero debe registrarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if(tipoCategoria == "Proveedores"){
                    if(ControladorProveedores.revisarFieldsProveedores(ventanaInicio)){
                        Proveedor nuevoProveedor = ControladorProveedores.crearProveedor(ventanaInicio);
                        Integer idProveedor = nuevoProveedor.getIdentificacion();
                        String nombreProveedor = nuevoProveedor.getNombre();
                        if(supermercado.getProveedores().elementoPresente(idProveedor) && supermercado.getProveedores().actualizar(idProveedor, nuevoProveedor)){
                            JOptionPane.showMessageDialog(null,"¡Datos del proveedor " + idProveedor + " " + nombreProveedor + " actualizados correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                            pintarFormulario(tipoCategoria);

                        } else {
                            JOptionPane.showMessageDialog(null,"El proveedor " + idProveedor + " " + nombreProveedor + " no se encuentra registrado en el sistema, primero debe registrarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if(tipoCategoria == "Ventas (a clientes)"){
                    if(ControladorVentas.revisarFieldsVentas(ventanaInicio)){
                        Venta nuevaVenta = ControladorVentas.crearVenta(ventanaInicio, listaProductosVenta);

                    }

                } else if (tipoCategoria == "Compras (a proveedores)"){
                    if(ControladorCompras.revisarFieldsCompras(ventanaInicio)){
                        Compra nuevaCompra = ControladorCompras.crearCompra(ventanaInicio, listaProductosCompra, listaPreciosCompra);

                    }

                }
                
            } else if (evento.getActionCommand().equalsIgnoreCase("Editar") && ventanaListados.isActive()) {
                if(tipoCategoria == "Ventas (a clientes)"){
                    // if(ControladorListar.revisarFieldsProductoEnVenta(ventanaListados))


                } else if(tipoCategoria == "Compras (a proveedores)"){



                }

            } else if (evento.getActionCommand().equalsIgnoreCase("Eliminar") && !ventanaListados.isActive()){
                if(tipoCategoria == "Productos"){
                    String stringIDProducto = ventanaInicio.getFieldIdProductos().getText();
                    Integer idProducto = Integer.valueOf(stringIDProducto);
                    if(supermercado.getProductos().elementoPresente(idProducto)){
                        String nombreProducto = supermercado.getProductos().getElemento(idProducto).getNombreProducto();
                        int continuar = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar el producto " + idProducto + " " + nombreProducto + "? \nEsta acción no se puede deshacer.", "¿Desea proceder con la eliminación?", JOptionPane.YES_NO_OPTION);
                        if(continuar == JOptionPane.YES_OPTION && supermercado.getProductos().eliminar(idProducto)){
                            JOptionPane.showMessageDialog(null,"¡El producto " + idProducto + " " + nombreProducto + " fue eliminado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                            pintarFormulario(tipoCategoria);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null,"El producto " + idProducto + " no se encuentra registrado en el sistema, no puede eliminarla.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    }

                } else if(tipoCategoria == "Clientes"){
                    if(ControladorClientes.revisarIDCliente(ventanaInicio)){
                        String stringIdCliente = ventanaInicio.getFieldIdClientes().getText();
                        Integer idCliente = Integer.valueOf(stringIdCliente);
                        if(supermercado.getClientes().elementoPresente(idCliente)){
                            int numeroTransaccionesRealizadas = supermercado.getClientes().getElemento(idCliente).getNumeroTransaccionesRealizadas();
                            if(numeroTransaccionesRealizadas == 0){
                                int continuar = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar al cliente " + idCliente + "? \nEsta acción no se puede deshacer.", "¿Desea proceder con la eliminación?", JOptionPane.YES_NO_OPTION);
                                if(continuar == JOptionPane.YES_OPTION && supermercado.getClientes().eliminar(idCliente)){
                                    JOptionPane.showMessageDialog(null,"¡El cliente " + idCliente + " fue eliminado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                                    pintarFormulario(tipoCategoria);
                                }
                                         
                            } else {
                                JOptionPane.showMessageDialog(null,"¡No puede eliminar al cliente " + idCliente + " " + " ya que el cliente tiene\n" + numeroTransaccionesRealizadas + " transacciones registradas en el sistema.", "Advertencia", JOptionPane.ERROR_MESSAGE); 
                            }

                        } else {
                            JOptionPane.showMessageDialog(null,"¡El cliente " + idCliente + " " + " no se encuentra registrado en el sistema, no puede eliminarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if(tipoCategoria == "Proveedores"){
                    if(ControladorProveedores.revisarIDProveedor(ventanaInicio)){
                        String stringIdProveedor = ventanaInicio.getFieldIdProveedor().getText();
                        Integer idProveedor = Integer.valueOf(stringIdProveedor);
                        if(supermercado.getProveedores().elementoPresente(idProveedor)){
                            int numeroTransaccionesRealizadas = supermercado.getProveedores().getElemento(idProveedor).getNumeroTransaccionesRealizadas();
                            if(numeroTransaccionesRealizadas == 0){
                                int continuar = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar al proveedor " + idProveedor + "? \nEsta acción no se puede deshacer.", "¿Desea proceder con la eliminación?", JOptionPane.YES_NO_OPTION);
                                if(continuar == JOptionPane.YES_OPTION && supermercado.getProveedores().eliminar(idProveedor)){
                                    JOptionPane.showMessageDialog(null,"¡El proveedor " + idProveedor + " fue eliminado correctamente!", "Operación realizada con éxito", JOptionPane.INFORMATION_MESSAGE);
                                    pintarFormulario(tipoCategoria);
                                }
                                            
                            } else {
                                JOptionPane.showMessageDialog(null,"¡No puede eliminar al proveedor " + idProveedor + " " + " ya que el proveedor tiene\n" + numeroTransaccionesRealizadas + " transacciones registradas en el sistema.", "Advertencia", JOptionPane.ERROR_MESSAGE); 
                            }

                        } else {
                            JOptionPane.showMessageDialog(null,"¡El proveedor " + idProveedor + " " + " no se encuentra registrado en el sistema, no puede eliminarlo.", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else if(tipoCategoria == "Ventas (a clientes)"){


                } else if (tipoCategoria == "Compras (a proveedores)"){

                }
                
            } else if (evento.getActionCommand().equalsIgnoreCase("Eliminar") && ventanaListados.isActive()){
                if(tipoCategoria == "Ventas (a clientes)"){



                } else if(tipoCategoria == "Compras (a proveedores)"){



                }


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
                limpiarFormulario(tipoCategoria);
                pintarFormulario(tipoCategoria);


            } else if (evento.getActionCommand().equalsIgnoreCase("Listar")){
                pintarListado(tipoCategoria);
            }
        }
    }

    public void limpiarFormulario(String tipoCategoria){
        if(tipoCategoria == "Productos"){
            ControladorProductos.limpiar(ventanaInicio, serialProducto);

        } else if (tipoCategoria == "Clientes") {
            ControladorClientes.limpiar(ventanaInicio);

        } else if (tipoCategoria == "Proveedores") {
            ControladorProveedores.limpiar(ventanaInicio);

        } else if (tipoCategoria == "Ventas (a clientes)") {
            ControladorVentas.limpiar(ventanaInicio, serialVenta);
            listaProductosVenta = new HashMap<>();

        } else if (tipoCategoria == "Compras (a proveedores)") {
            ControladorCompras.limpiar(ventanaInicio, serialCompra);
            listaProductosCompra = new HashMap<>();
            listaPreciosCompra = new HashMap<>();
        } 
    }

    public void pintarListado(String tipoCategoria){
        ControladorListar.limpiar(ventanaListados, tipoCategoria);
        tablaDatos = ventanaListados.getTablaDatos();
        pane = ventanaListados.getPane();
        
        for(int limpiador = 0; limpiador < 15; limpiador++){
            ventanaListados.getContenedorTexto()[limpiador].removeAll();
            ventanaListados.getLabelTexto()[limpiador].setText("");
            ventanaListados.getContenedorTexto()[limpiador].add(ventanaListados.getLabelTexto()[limpiador]);
        }

        ventanaListados.getPanelInferior().removeAll();
        ventanaListados.getPanelInferior().removeAll();

        pane.removeAll();
        tablaDatos.removeAll();

        if(tipoCategoria == "Ventas (a clientes)"){
            String[][] datosListadosVentas = Arreglo.getArregloProductosVenta(supermercado, listaProductosVenta);
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tablaDatos = new JTable(ControladorVentanaInicio.asignarModelo(datosListadosVentas, ControladorListar.getEncabezadoListaVenta()));

        } else if (tipoCategoria == "Compras (a proveedores)") {
            String[][] datosListadoCompras = Arreglo.getArregloProductosCompra(supermercado, listaProductosCompra, listaPreciosCompra);
            tablaDatos = new JTable(asignarModelo(datosListadoCompras, ControladorListar.getEncabezadoListaCompra()));
            // ControladorClientes.pintar(ventanaInicio);
        }

        ventanaListados.setTablaDatos(tablaDatos);
        pane = new JScrollPane(ventanaListados.getTablaDatos());
        ventanaListados.setPane(pane);

        tablaDatos.addMouseListener(new CalculateMouseListener());
        ventanaListados.mostrarListado(tipoCategoria); // Correspondiente a pintar
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

            String[][] datosProductos = supermercado.getProductos().getListables();
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tablaDatos = new JTable(ControladorVentanaInicio.asignarModelo(datosProductos, ControladorProductos.getEncabezadoProductos()));
            
            ventanaInicio.setTablaDatos(tablaDatos);
            pane = new JScrollPane(ventanaInicio.getTablaDatos());
            ventanaInicio.setPane(pane);
            
            ControladorProductos.pintar(ventanaInicio, serialProducto);

        } else if (tipoCategoria == "Clientes") {
            String[][] datosClientes = supermercado.getClientes().getListables();
            tablaDatos = new JTable(asignarModelo(datosClientes, ControladorClientes.getEncabezadoClientes()));
            
            ventanaInicio.setTablaDatos(tablaDatos);
            pane = new JScrollPane(ventanaInicio.getTablaDatos());
            ventanaInicio.setPane(pane);
            
            ControladorClientes.pintar(ventanaInicio);
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        } else if (tipoCategoria == "Proveedores") {
            String[][] datosProveedores = supermercado.getProveedores().getListables();
            tablaDatos = new JTable(asignarModelo(datosProveedores, ControladorProveedores.getEncabezadoProveedores()));
            // tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
            ventanaInicio.setTablaDatos(tablaDatos);
            pane = new JScrollPane(ventanaInicio.getTablaDatos());
            ventanaInicio.setPane(pane);
            
            ControladorProveedores.pintar(ventanaInicio);

        } else if (tipoCategoria == "Ventas (a clientes)") {
            String[][] datosVentas = supermercado.getVentas().getListables();
            tablaDatos = new JTable(asignarModelo(datosVentas, ControladorVentas.getEncabezadoVenta()));
            tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
            ventanaInicio.setTablaDatos(tablaDatos);
            pane = new JScrollPane(ventanaInicio.getTablaDatos());
            ventanaInicio.setPane(pane);
            
            ControladorVentas.pintar(ventanaInicio, serialVenta);
            ventanaInicio.getContenedorTexto()[3].add(labelFormatoFecha);


        } else if (tipoCategoria == "Compras (a proveedores)") {
            String[][] datosCompras = supermercado.getCompras().getListables();
            tablaDatos = new JTable(asignarModelo(datosCompras, ControladorCompras.getEncabezadoCompra()));
            tablaDatos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            
            ventanaInicio.setTablaDatos(tablaDatos);
            pane = new JScrollPane(ventanaInicio.getTablaDatos());
            ventanaInicio.setPane(pane);

            ControladorCompras.pintar(ventanaInicio, serialCompra);
            ventanaInicio.getContenedorTexto()[3].add(labelFormatoFecha);
        } 
        
        // Se actualiza la tabla:
        tablaDatos.addMouseListener(new CalculateMouseListener());
    }

    // Asigna los valores de la tabla a los respectivos fields.
    class CalculateMouseListener implements MouseInputListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            DefaultTableModel modeloTabla = (DefaultTableModel)ventanaInicio.getTablaDatos().getModel();
            DefaultTableModel modeloTablaListado = (DefaultTableModel)ventanaListados.getTablaDatos().getModel();

            if(tipoCategoria == "Productos"){
                ControladorProductos.asignarTabla(modeloTabla, ventanaInicio);

            } else if (tipoCategoria == "Clientes") {
                ControladorClientes.asignarTabla(modeloTabla, ventanaInicio);

            } else if (tipoCategoria == "Proveedores") {
                ControladorProveedores.asignarTabla(modeloTabla, ventanaInicio);

            } else if (tipoCategoria == "Ventas (a clientes)" && !ventanaListados.isActive()) {
                ControladorVentas.asignarTabla(modeloTabla, ventanaInicio);
                String stringIdVenta = ventanaInicio.getFieldIdVenta().getText();
                Integer idVenta = Integer.valueOf(stringIdVenta);
                listaProductosVenta = supermercado.getVentas().getElemento(idVenta).getListaProductos();

            } else if (tipoCategoria == "Compras (a proveedores)" && !ventanaListados.isActive()) {
                ControladorCompras.asignarTabla(modeloTabla, ventanaInicio);
                String stringIdCompra = ventanaInicio.getFieldIdCompra().getText();
                Integer idCompra = Integer.valueOf(stringIdCompra);
                listaProductosCompra = supermercado.getCompras().getElemento(idCompra).getListaProductos();
                listaPreciosCompra = supermercado.getCompras().getElemento(idCompra).getListaPrecios();

            } else if ((tipoCategoria == "Ventas (a clientes)" && ventanaListados.isActive()) || (tipoCategoria == "Compras (a proveedores)" && ventanaListados.isActive())) {
                ControladorListar.asignarTabla(modeloTablaListado, ventanaListados, tipoCategoria);
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

    public void reducirStock(Venta nuevaVenta){
        HashMap<Integer, Integer> productosVenta = nuevaVenta.getListaProductos();
        for(Map.Entry<Integer, Integer> pareja : productosVenta.entrySet())
            supermercado.getProductos().getElemento(pareja.getKey()).reducirStock(pareja.getValue());
    }

    public void aumentarStock(Compra nuevaCompra){
        HashMap<Integer, Integer> productosCompra = nuevaCompra.getListaProductos();
        for(Map.Entry<Integer, Integer> pareja : productosCompra.entrySet())
            supermercado.getProductos().getElemento(pareja.getKey()).aumentarStock(pareja.getValue());
    }

    public static TableModel asignarModelo(String[][] datos, String[] encabezado) {
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
