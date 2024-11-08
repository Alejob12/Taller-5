package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
    private ProductoMenu productoMenu;

    @BeforeEach
    void setUp() {
        productoMenu = new ProductoMenu("Hamburguesa Sencilla", 15000);
    }

    @AfterEach
    void tearDown() {
        productoMenu = null;
    }

    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa Sencilla", productoMenu.getNombre(), "El nombre del producto no es el esperado.");
    }

    @Test
    void testGetPrecio() {
        assertEquals(15000, productoMenu.getPrecio(), "El precio del producto no es el esperado.");
    }

    @Test
    void testGenerarTextoFactura() {
        String textoFactura = productoMenu.generarTextoFactura();
        assertTrue(textoFactura.contains("Hamburguesa Sencilla"), "El texto de factura debe contener el nombre del producto.");
        assertTrue(textoFactura.contains("15000"), "El texto de factura debe mostrar el precio.");
        assertTrue(textoFactura.contains("\n"), "El texto de factura debe contener un salto de línea.");
    }
}
