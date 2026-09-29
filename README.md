# Taller 5 — Sistema de pedidos de un restaurante de hamburguesas (Java)

Modelo orientado a objetos de un restaurante de hamburguesas, hecho para el curso de Diseño y Programación Orientada a Objetos (Universidad de los Andes). Carga el menú desde archivos, arma pedidos con productos, ajustes y combos, calcula el IVA y guarda cada factura en un archivo. Incluye 30 pruebas unitarias con JUnit 5.

## Qué hace

- **Carga del restaurante** desde `data/`: ingredientes, menú base y combos, validando repetidos (`IngredienteRepetidoException`, `ProductoRepetidoException`) y productos inexistentes en un combo (`ProductoFaltanteException`).
- **Pedidos:** un solo pedido en curso a la vez (`YaHayUnPedidoEnCursoException`); al cerrarlo se guarda la factura en `facturas/` y queda en el historial.
- **Productos:** producto del menú, producto ajustado (con ingredientes adicionales que suben el precio) y combo con descuento sobre la suma de sus productos.
- **Factura:** detalle por producto, precio neto, IVA y total.

## Diseño

```
mundo/         Restaurante, Pedido, Producto (interfaz), ProductoMenu, ProductoAjustado, Combo, Ingrediente
excepciones/   Excepciones del dominio, todas hijas de HamburguesaException
```

`Producto` es una interfaz con `getNombre`, `getPrecio` y `generarTextoFactura`; el pedido trata igual a un producto simple, uno ajustado o un combo (polimorfismo).

## Pruebas

En `tests/` hay pruebas de cada clase del mundo y del restaurante, incluyendo los casos de error, con archivos de prueba (`*_repetidos.txt`, `combos_producto_faltante.txt`) en `data/`.

Para ejecutarlas se puede importar la carpeta como proyecto de Eclipse (ya trae `.classpath` con JUnit 5), o por consola con el lanzador de JUnit:

```bash
cd "Taller 5 Hamburguesas_esqueleto/Taller 5 Hamburguesas"
mkdir -p bin
javac -cp junit-platform-console-standalone.jar -d bin $(find src tests -name '*.java')
java -jar junit-platform-console-standalone.jar -cp bin --scan-classpath
```

(`junit-platform-console-standalone.jar` se descarga de Maven Central.)

## Autor

**Alejandro Bernal** — Ingeniería de Sistemas e Industrial, Universidad de los Andes.
