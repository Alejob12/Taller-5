package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

public class ProductoAjustadoTest {

    private ProductoAjustado productoAjustado;
    private ProductoMenu productoBase;
    private Ingrediente ingredienteExtra;

    @BeforeEach
    void setUp() {
        productoBase = new ProductoMenu("Hamburguesa Especial", 20000);
        productoAjustado = new ProductoAjustado(productoBase);
        ingredienteExtra = new Ingrediente("Queso Extra", 2000);
    }

    @AfterEach
    void tearDown() {
        productoAjustado = null;
    }

    @Test
    void testGetNombre() {
        assertEquals("Hamburguesa Especial", productoAjustado.getNombre(), "El nombre del producto ajustado no es el esperado.");
    }

    @Test
    void testGetPrecio() {
        productoAjustado.agregarIngrediente(ingredienteExtra);
        assertEquals(22000, productoAjustado.getPrecio(), "El precio del producto ajustado no es el esperado.");
    }

    @Test
    void testGenerarTextoFactura() {
        productoAjustado.agregarIngrediente(ingredienteExtra);
        String textoFactura = productoAjustado.generarTextoFactura();
        assertTrue(textoFactura.contains("Hamburguesa Especial"), "El texto de factura debe contener el nombre del producto base.");
        assertTrue(textoFactura.contains("    +Queso Extra"), "El texto de factura debe contener el ingrediente adicional.");
        assertTrue(textoFactura.contains("22000"), "El texto de factura debe mostrar el precio total.");
    }

    @Test
    void testGenerarTextoFacturaSinIngredientes() {
        ProductoAjustado productoSinIngredientes = new ProductoAjustado(productoBase);
        String textoFactura = productoSinIngredientes.generarTextoFactura();
        assertTrue(textoFactura.contains("Hamburguesa Especial"), "La factura debe contener el nombre del producto base.");
        assertFalse(textoFactura.contains("+"), "La factura no debe contener ingredientes adicionales.");
    }
}


