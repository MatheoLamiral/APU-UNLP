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
# Bad smell: Feature envy
- ```Java
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
  ```
### Aplico Extract method y Move method
- ```Java 
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
