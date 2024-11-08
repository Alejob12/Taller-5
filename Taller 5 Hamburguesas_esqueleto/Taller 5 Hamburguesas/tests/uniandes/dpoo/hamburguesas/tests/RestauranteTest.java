package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.mundo.*;
import uniandes.dpoo.hamburguesas.excepciones.*;

public class RestauranteTest {
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        restaurante = new Restaurante();
    }

    @AfterEach
    void tearDown() {
        restaurante = null;
    }

    @Test
    void testIniciarPedidoSinPedidoEnCurso() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Juan", "Calle 123");
        assertNotNull(restaurante.getPedidoEnCurso(), "El pedido en curso no debería ser null.");
    }

    @Test
    void testIniciarPedidoConPedidoEnCurso() {
        try {
            restaurante.iniciarPedido("Juan", "Calle 123");
            restaurante.iniciarPedido("Pedro", "Calle 456");
            fail("Se esperaba una excepción YaHayUnPedidoEnCursoException.");
        } catch (YaHayUnPedidoEnCursoException e) {
            assertEquals("Juan", e.getNombreCliente(), "El cliente que tenía el pedido en curso no es el esperado.");
        }
    }

    @Test
    void testCerrarYGuardarPedido() {
        try {
            restaurante.iniciarPedido("Juan", "Calle 123");
            restaurante.cerrarYGuardarPedido();
            assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso debería ser null después de cerrarlo.");
        } catch (Exception e) {
            fail("No se esperaba una excepción.");
        }
    }

    @Test
    void testCerrarYGuardarPedidoSinPedidoEnCurso() {
        try {
            restaurante.cerrarYGuardarPedido();
            fail("Se esperaba una excepción NoHayPedidoEnCursoException.");
        } catch (NoHayPedidoEnCursoException e) {
        } catch (IOException e) {
            fail("No se esperaba una IOException.");
        }
    }

    @Test
    void testCargarInformacionRestaurante() {
        try {
            File archivoIngredientes = new File("ingredientes.txt");
            File archivoMenu = new File("menu.txt");
            File archivoCombos = new File("combos.txt");

            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
            
            assertFalse(restaurante.getIngredientes().isEmpty(), "La lista de ingredientes no debería estar vacía.");
            assertFalse(restaurante.getMenuBase().isEmpty(), "El menú base no debería estar vacío.");
            assertFalse(restaurante.getMenuCombos().isEmpty(), "El menú de combos no debería estar vacío.");
        } catch (Exception e) {
            fail("No se esperaba una excepción al cargar la información del restaurante.");
        }
    }

    @Test
    void testCargarInformacionRestauranteConIngredienteRepetido() {
        try {
            File archivoIngredientes = new File("ingredientes_repetidos.txt");
            File archivoMenu = new File("menu.txt");
            File archivoCombos = new File("combos.txt");

            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
            fail("Se esperaba una excepción IngredienteRepetidoException.");
        } catch (IngredienteRepetidoException e) {
        } catch (Exception e) {
            fail("Se esperaba una excepción IngredienteRepetidoException.");
        }
    }

    @Test
    void testCargarMenuConProductoRepetido() {
        try {
            File archivoIngredientes = new File("ingredientes.txt");
            File archivoMenu = new File("menu_repetido.txt");
            File archivoCombos = new File("combos.txt");

            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
            fail("Se esperaba una excepción ProductoRepetidoException.");
        } catch (ProductoRepetidoException e) {
        } catch (Exception e) {
            fail("Se esperaba una excepción ProductoRepetidoException.");
        }
    }

    @Test
    void testCargarCombosConProductoFaltante() {
        try {
            File archivoIngredientes = new File("ingredientes.txt");
            File archivoMenu = new File("menu.txt");
            File archivoCombos = new File("combos_producto_faltante.txt");

            restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);
            fail("Se esperaba una excepción ProductoFaltanteException.");
        } catch (ProductoFaltanteException e) {
        } catch (Exception e) {
            fail("Se esperaba una excepción ProductoFaltanteException.");
        }
    }

    @Test
    void testGetPedidos() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException {
        restaurante.iniciarPedido("Juan", "Calle 123");
        restaurante.cerrarYGuardarPedido();
        ArrayList<Pedido> pedidos = restaurante.getPedidos();
        assertEquals(1, pedidos.size(), "El tamaño de la lista de pedidos no es el esperado.");
    }
}
