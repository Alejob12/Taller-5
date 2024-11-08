package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.util.ArrayList;

public class ComboTest {
    private Combo combo;
    private ProductoMenu producto1;
    private ProductoMenu producto2;
    private ArrayList<ProductoMenu> items;

    @BeforeEach
    void setUp() {
        producto1 = new ProductoMenu("Hamburguesa Sencilla", 15000);
        producto2 = new ProductoMenu("Papas Fritas", 5000);
        
        items = new ArrayList<>();
        items.add(producto1);
        items.add(producto2);
        
        combo = new Combo("Combo Sencillo", 0.1, items);
    }

    @AfterEach
    void tearDown() {
        combo = null;
    }

    @Test
    void testGetNombre() {
        assertEquals("Combo Sencillo", combo.getNombre(), "El nombre del combo no es el esperado.");
    }

    @Test
    void testGetPrecio() {
        int precioEsperado = (int) ((15000 + 5000) * (1 - 0.1));
        assertEquals(precioEsperado, combo.getPrecio(), "El precio del combo no es el esperado.");
    }

    @Test
    void testGenerarTextoFactura() {
        String textoFactura = combo.generarTextoFactura();
        assertTrue(textoFactura.contains("Combo Sencillo"), "El texto de factura no contiene el nombre esperado.");
        assertTrue(textoFactura.contains("Descuento: 0.1"), "El texto de factura no contiene el descuento esperado.");
        assertTrue(textoFactura.contains(String.valueOf(combo.getPrecio())), "El texto de factura no contiene el precio esperado.");
    }

    @Test
    void testGetPrecioSinDescuento() {
        Combo comboSinDescuento = new Combo("Combo Sin Descuento", 0.0, new ArrayList<>(items));
        int precioEsperado = 15000 + 5000;
        assertEquals(precioEsperado, comboSinDescuento.getPrecio(), "El precio sin descuento no es el esperado.");
    }

    @Test
    void testGetPrecioConDescuentoTotal() {
        Combo comboConDescuentoTotal = new Combo("Combo Descuento Total", 1.0, new ArrayList<>(items));
        int precioEsperado = 0;
        assertEquals(precioEsperado, comboConDescuentoTotal.getPrecio(), "El precio con descuento total no es el esperado.");
    }
}


