```Java
    public class Producto {
        private String nombre;
        private double precio;
        
        public double getPrecio() {
            return this.precio;
        }
    }   

    public class ItemCarrito {
        private Producto producto;
        private int cantidad;
            
        public Producto getProducto() {
            return this.producto;
        }
        
        public int getCantidad() {
            return this.cantidad;
        }

    }

    public class Carrito {
        private List<ItemCarrito> items;
        
        public double total() {
            return this.items.stream().mapToDouble(item -> item.getProducto().getPrecio() * item.getCantidad()).sum();
        }
    }

```
### Bad smell: Feature envy en `item.getProducto().getPrecio() * item.getCantidad()`
1. Aplico **Extract method**
   1. Creo el método `calcularTotalItem(ItemCarrito item)` en la clase `Carrito`
   2. Copio el comportamiento de `item.getProducto().getPrecio() * item.getCantidad()` a `calcularTotalItem(ItemCarrito item)`
   3. Reemplazo el código del stream `item -> item.getProducto().getPrecio() * item.getCantidad()` por  `item -> calcularTotalItem(item)`
2. Aplico **Move method** 
   1. Creo el método `calcularTotalItem()` en la clase `ItemCarrito`
   2. Copio el comportamiento de `item.getProducto().getPrecio() * item.getCantidad()` a `calcularTotalItem()`
   3. Reemplazo la referencia a `item` por `this` en `calcularTotalItem()`
   4. Reemplazo el código del stream `item -> calcularTotalItem(item)` por `item -> item.calcularTotalItem()`
   5. Elimino el método `calcularTotalItem(ItemCarrito item)` de la clase `Carrito`
```Java 
    public class Producto {
        private String nombre;
        private double precio;
        
        public double getPrecio() {
            return this.precio;
        }
    }   

    public class ItemCarrito {
        private Producto producto;
        private int cantidad;
            
        public double calcularTotalItem(){
            return this.getProducto().getPrecio() * this.getCantidad();
        }

        public Producto getProducto() {
            return this.producto;
        }
        
        public int getCantidad() {
            return this.cantidad;
        }

    }

    public class Carrito {
        private List<ItemCarrito> items;
        
        public double total() {
            return this.items.stream().mapToDouble(item -> item.calcularTotalItem()).sum();
        }
    }
  ```
