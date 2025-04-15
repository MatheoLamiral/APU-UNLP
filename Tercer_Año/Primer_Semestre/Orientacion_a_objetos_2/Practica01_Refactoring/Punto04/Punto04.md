```Java
     public class Pedido {
        private Cliente cliente;
        private List<Producto> productos;
        private String formaPago;

        public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
            if (!"efectivo".equals(formaPago) && !"6 cuotas".equals(formaPago)&& !"12 cuotas".equals(formaPago)) {
                throw new Error("Forma de pago incorrecta");
            }
            this.cliente = cliente;
            this.productos = productos;
            this.formaPago = formaPago;
        }   

        public double getCostoTotal() {
            double costoProductos = 0;
            for (Producto producto : this.productos) {
                costoProductos += producto.getPrecio();
            }
            double extraFormaPago = 0;
            if ("efectivo".equals(this.formaPago)) {
                extraFormaPago = 0;
            } else if ("6 cuotas".equals(this.formaPago)) {
                extraFormaPago = costoProductos * 0.2;
            } else if ("12 cuotas".equals(this.formaPago)) {
                extraFormaPago = costoProductos * 0.5;
            }
            int añosDesdeFechaAlta = Period.between(this.cliente.getFechaAlta(), LocalDate.now()).getYears();
            // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
            if (añosDesdeFechaAlta > 5) {
                return (costoProductos + extraFormaPago) * 0.9;
            }
            return costoProductos + extraFormaPago;
        }
    }  

    public class Cliente {
        private LocalDate fechaAlta;

        public LocalDate getFechaAlta() {
            return this.fechaAlta;
        }
    }
    public class Producto {
        private double precio;

        public double getPrecio() {
            return this.precio;
        }
    }
```
# Bad smell: Rinventando la rueda 
- ```Java
    double costoProductos = 0;
    for (Producto producto : this.productos) {
        costoProductos += producto.getPrecio();
    }
  ``` 
### Aplcio replace loop with pipeline 
- ```Java
    public class Pedido {
        private Cliente cliente;
        private List<Producto> productos;
        private String formaPago;

        public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
            if (!"efectivo".equals(formaPago) && !"6 cuotas".equals(formaPago)&& !"12 cuotas".equals(formaPago)) {
                throw new Error("Forma de pago incorrecta");
            }
            this.cliente = cliente;
            this.productos = productos;
            this.formaPago = formaPago;
        }   

        public double getCostoTotal() {
            double costoProductos = this.productos.Stream().mapToDouble(producto -> producto.getPrecio()).sum();
            double extraFormaPago = 0;
            if ("efectivo".equals(this.formaPago)) {
                extraFormaPago = 0;
            } else if ("6 cuotas".equals(this.formaPago)) {
                extraFormaPago = costoProductos * 0.2;
            } else if ("12 cuotas".equals(this.formaPago)) {
                extraFormaPago = costoProductos * 0.5;
            }
            int añosDesdeFechaAlta = Period.between(this.cliente.getFechaAlta(), LocalDate.now()).getYears();
            // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
            if (añosDesdeFechaAlta > 5) {
                return (costoProductos + extraFormaPago) * 0.9;
            }
            return costoProductos + extraFormaPago;
        }
    }  

    public class Cliente {
        private LocalDate fechaAlta;

        public LocalDate getFechaAlta() {
            return this.fechaAlta;
        }
    }
    public class Producto {
        private double precio;

        public double getPrecio() {
            return this.precio;
        }
    }
  ```
# Bad smell: Switch statement
- ```Java
    if ("efectivo".equals(this.formaPago)) {
        extraFormaPago = 0;
    } else if ("6 cuotas".equals(this.formaPago)) {
        extraFormaPago = costoProductos * 0.2;
    } else if ("12 cuotas".equals(this.formaPago)) {
        extraFormaPago = costoProductos * 0.5;
    }
  ```
### Aplico Replace conditional with polymorphism
- ```Java
    public class Pedido {
        private Cliente cliente;
        private List<Producto> productos;
        private FromaDePago formaPago;

        public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
            if (!"efectivo".equals(formaPago) && !"6 cuotas".equals(formaPago)&& !"12 cuotas".equals(formaPago)) {
                throw new Error("Forma de pago incorrecta");
            }
            this.cliente = cliente;
            this.productos = productos;
            this.formaPago = formaPago;
        }   

        public double getCostoTotal() {
            double costoProductos = this.productos.Stream().mapToDouble(producto -> producto.getPrecio()).sum();
            double extraFormaPago = this.formaPago.calcularExtra(costoProductos);
            int añosDesdeFechaAlta = Period.between(this.cliente.getFechaAlta(), LocalDate.now()).getYears();
            // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
            if (añosDesdeFechaAlta > 5) {
                return (costoProductos + extraFormaPago) * 0.9;
            }
            return costoProductos + extraFormaPago;
        }
    }

    public abstract class FromaDePago {
        
        public double calcularExtra(double costo){
            return this.calcularExtra(costo);
        }
    }

    public abstract class Efectivo extends FromaDePago {
        
        public double calcularExtra(double costo){
            return costo;
        }
    }   

    public abstract class SeisCuotas extends FromaDePago {
        
        public double calcularExtra(double costo){
            return costo * 0.2;
        }
    }

    public abstract class DoceCuotas extends FromaDePago {
        
        public double calcularExtra(double costo){
            return costo * 0.5;
        }
    }

    public class Cliente {
        private LocalDate fechaAlta;

        public LocalDate getFechaAlta() {
            return this.fechaAlta;
        }
    }
    public class Producto {
        private double precio;

        public double getPrecio() {
            return this.precio;
        }
    }
  ```
# Bad smell: Feature envy
- ```Java
    int añosDesdeFechaAlta = Period.between(this.cliente.getFechaAlta(), LocalDate.now()).getYears();
    //deberia ser responsabilida de cliente 
  ```
### Aplico Extract method y Move method 
- ```Java
    public class Pedido {
        private Cliente cliente;
        private List<Producto> productos;
        private FromaDePago formaPago;

        public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
            if (!"efectivo".equals(formaPago) && !"6 cuotas".equals(formaPago)&& !"12 cuotas".equals(formaPago)) {
                throw new Error("Forma de pago incorrecta");
            }
            this.cliente = cliente;
            this.productos = productos;
            this.formaPago = formaPago;
        }   

        public double getCostoTotal() {
            double costoProductos = this.productos.Stream().mapToDouble(producto -> producto.getPrecio()).sum();
            double extraFormaPago = this.formaPago.calcularExtra(costoProductos);
            int añosDesdeFechaAlta = this.cliente.calcularAntiguedad();
            // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
            if (añosDesdeFechaAlta > 5) {
                return (costoProductos + extraFormaPago) * 0.9;
            }
            return costoProductos + extraFormaPago;
        }
    }

    public abstract class FromaDePago {
        
        public double calcularExtra(double costo){
            return this.calcularExtra(costo);
        }
    }

    public abstract class Efectivo extends FromaDePago {
        
        public double calcularExtra(double costo){
            return costo;
        }
    }   

    public abstract class SeisCuotas extends FromaDePago {
        
        public double calcularExtra(double costo){
            return costo * 0.2;
        }
    }

    public abstract class DoceCuotas extends FromaDePago {
        
        public double calcularExtra(double costo){
            return costo * 0.5;
        }
    }

    public class Cliente {
        private LocalDate fechaAlta;

        public int getAntiguedad(){
            return Period.between(this.fechaAlta, LocalDate.now()).getYears()
        }

        public LocalDate getFechaAlta() {
            return this.fechaAlta;
        }
    }
    public class Producto {
        private double precio;

        public double getPrecio() {
            return this.precio;
        }
    }
  ```
# Bad smell: Long method 
- ```Java
    public double getCostoTotal() {
            double costoProductos = this.productos.Stream().mapToDouble(producto -> producto.getPrecio()).sum();
            double extraFormaPago = this.formaPago.calcularExtra(costoProductos);
            int añosDesdeFechaAlta = this.cliente.calcularAntiguedad();
            // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
            if (añosDesdeFechaAlta > 5) {
                return (costoProductos + extraFormaPago) * 0.9;
            }
            return costoProductos + extraFormaPago;
        }
  ```
### Aplico Extract method y Replace temp with query
- ```Java
    public class Pedido {
        private Cliente cliente;
        private List<Producto> productos;
        private FromaDePago formaPago;

        public Pedido(Cliente cliente, List<Producto> productos, String formaPago) {
            if (!"efectivo".equals(formaPago) && !"6 cuotas".equals(formaPago)&& !"12 cuotas".equals(formaPago)) {
                throw new Error("Forma de pago incorrecta");
            }
            this.cliente = cliente;
            this.productos = productos;
            this.formaPago = formaPago;
        }   

        public double getCostoTotal() {
            double costoProductos = this.productos.Stream().mapToDouble(producto -> producto.getPrecio()).sum();
            double extraFormaPago = this.formaPago.calcularExtra(costoProductos);
            return (costoProductos + extraFormaPago) * this.calcularDescuentoAntiguedad();
        }

        protected double calcularDescuentoAntiguedad(){
            int añosDesdeFechaAlta = this.cliente.calcularAntiguedad();
            // Aplicar descuento del 10% si el cliente tiene más de 5 años de antiguedad
            if (añosDesdeFechaAlta > 5) {
                return 0.9;
            }
            return 1;
        }
    }

    public abstract class FromaDePago {
        
        public double calcularExtra(double costo){
            return this.calcularExtra(costo);
        }
    }

    public abstract class Efectivo extends FromaDePago {
        
        public double calcularExtra(double costo){
            return costo;
        }
    }   

    public abstract class SeisCuotas extends FromaDePago {
        
        public double calcularExtra(double costo){
            return costo * 0.2;
        }
    }

    public abstract class DoceCuotas extends FromaDePago {
        
        public double calcularExtra(double costo){
            return costo * 0.5;
        }
    }

    public class Cliente {
        private LocalDate fechaAlta;

        public int getAntiguedad(){
            return Period.between(this.fechaAlta, LocalDate.now()).getYears()
        }

        public LocalDate getFechaAlta() {
            return this.fechaAlta;
        }
    }
    public class Producto {
        private double precio;

        public double getPrecio() {
            return this.precio;
        }
    }
  ```