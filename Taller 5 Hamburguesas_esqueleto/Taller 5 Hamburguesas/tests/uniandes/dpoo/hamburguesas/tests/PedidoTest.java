package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest
{
    private Pedido pedido;
    private ProductoMenu producto1;
    private ProductoMenu producto2;

    @BeforeEach
    void setUp()
    {
        pedido = new Pedido("Cliente Prueba", "Calle Falsa 123");
        producto1 = new ProductoMenu("Hamburguesa Sencilla", 15000);
        producto2 = new ProductoMenu("Papas Fritas", 5000);

        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
    }

    @AfterEach
    void tearDown()
    {
        pedido = null;
    }

    @Test
    void testGetIdPedido()
    {
        assertTrue(pedido.getIdPedido() > 0, "El ID del pedido debe ser mayor a 0.");
    }

    @Test
    void testGetPrecioTotalPedido()
    {
        int precioEsperado = (int) ((15000 + 5000) * 1.19); // Incluye el IVA
        assertEquals(precioEsperado, pedido.getPrecioTotalPedido(), "El precio total del pedido no es el esperado.");
    }

    @Test
    void testGenerarTextoFactura()
    {
        String textoFactura = pedido.generarTextoFactura();
        assertTrue(textoFactura.contains("Cliente Prueba"), "El texto de factura debe contener el nombre del cliente.");
        assertTrue(textoFactura.contains("Calle Falsa 123"), "El texto de factura debe contener la dirección del cliente.");
        assertTrue(textoFactura.contains("Total: " + pedido.getPrecioTotalPedido()), "El texto de factura debe contener el total del pedido.");
    }

    @Test
    void testAgregarProducto()
    {
        ProductoMenu producto3 = new ProductoMenu("Bebida", 3000);
        pedido.agregarProducto(producto3);
        assertEquals(3, pedido.getProductos().size(), "El número de productos debería ser 3.");
    }

    @Test
    void testGetPrecioTotalPedidoSinProductos()
    {
        Pedido pedidoVacio = new Pedido("Cliente Vacio", "Calle Vacía");
        assertEquals(0, pedidoVacio.getPrecioTotalPedido(), "El precio total del pedido vacío debe ser 0.");
    }

    @Test
    void testGenerarTextoFacturaSinProductos()
    {
        Pedido pedidoVacio = new Pedido("Cliente Vacio", "Calle Vacía");
        String textoFactura = pedidoVacio.generarTextoFactura();
        assertTrue(textoFactura.contains("Cliente Vacio"), "La factura debe contener el nombre del cliente.");
        assertTrue(textoFactura.contains("Dirección: Calle Vacía"), "La factura debe contener la dirección del cliente.");
        assertTrue(textoFactura.contains("Precio Neto:  0"), "La factura debe mostrar 0 como precio neto.");
        assertTrue(textoFactura.contains("IVA:          0"), "La factura debe mostrar 0 como IVA.");
        assertTrue(textoFactura.contains("Precio Total: 0"), "La factura debe mostrar 0 como precio total.");
    }

    @Test
    void testGuardarFactura() throws FileNotFoundException
    {
        File archivo = new File("factura_prueba.txt");
        pedido.guardarFactura(archivo);
        assertTrue(archivo.exists(), "El archivo de la factura debe ser creado.");
        archivo.delete();
    }
}


