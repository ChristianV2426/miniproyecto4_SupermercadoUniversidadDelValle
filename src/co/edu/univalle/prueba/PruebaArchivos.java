package co.edu.univalle.prueba;

import co.edu.univalle.modelo.*;

import java.math.BigDecimal;

// import co.edu.univalle.archivos.*;

public class PruebaArchivos {
    public static void main(String[] args) { 
        Supermercado supermercado = new Supermercado("Supermercado Univalle");
        Producto producto1 = new Producto(Integer.valueOf(100), "Manzanas paquete por 6 unidades", "Frutas y verduras", BigDecimal.valueOf(8800));

        supermercado.getProductos().añadir(producto1);
        ManejadorArchivos.guardarEnArchivoTextoPlano(supermercado, "./src/co/edu/univalle/archivos");
    }
}
