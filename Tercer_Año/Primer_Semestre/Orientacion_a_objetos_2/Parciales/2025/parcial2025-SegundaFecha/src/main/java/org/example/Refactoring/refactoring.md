# Segunda fecha 2025

## Bad Smells
- **Long mehtod** (21-38)
- **Duplicate code** (23 y 34)(24 y 37)(25 y 35)
- **Switch statement** (22-37)

## Refactoring
1. Aplico **Extract method** (23 y 34) y creo el método `int calcularKmRecorridos()` en la clase `Renta`
   1. Reemplazo la línea 23 y 34 por `this.calcularKmRecorridos()`
   2. Elimino variable temporal `kilometrosRecorridos` 
2. Aplico **Extract method** (24 y 37) y creo el método `int calcularPrecioPorDia()` en la clase `Renta`
   1. Reemplazo la línea 24 y 37 por `this.calcularPrecioPorDia()`
   2. Elimino variable temporal `precio`
3. APlico **Extract method** (25 y 35) y creo el método `int calcularPrecioPorKm()` en la clase `Renta`
   1. Reemplazo la línea 25 y 35 por `this.calcularPrecioPorKm()`
   2. Reemplazo en él la referencia a la variable `kilometrosRecorridos` por `this.calcularKmRecorridos()`
4. Aplico **Extract method** (26-31) creando el método `double aplicarDescuentoAntiguedad(double precio)`
   1. Reemplazo las líneas 26-31 por `return this.aplicarDescuentoAntiguedad(double precio)`
5. Aplico **Replace conditional logic with strategy** (22-37)
   1. Creo la jerarquía de clases necesaria
      1. Creo la clase abstracta `RentaStrategy`
      2. Creo las clases `Basico`, `Plus` y `KilometrajeLibre` que extienden de `RentaStrategy`
   2. Creo la v.i `estrategia` de tipo `RentaStrategy` en la clase`Renta` y un método `setEstrategia(RentaStrategy estrategia)`
   3. Aplico **Move method** (22-37) creando el método `double calcularTotal(Renta contexto, String tipo)` en la clase `RentaStrategy`
      1. Reemplazo las líneas 22-37 por `return this.estrategia.calcularTotal()`
   4. Aplico **Replace conditional with polymorphism**
      1. Creo el método `double calcularTotal(Renta contexto)` en las subclases de `RentaStrategy`
      2. muevo el código de las líneas 23-31 al método `calcularTotal(Renta contexto)` de la clase `Basico`
      3. muevo el código de las líneas 34-35 al método `calcularTotal(Renta contexto)` de la clase `Plus`
      4. muevo el código de la línea 37 al método `calcularTotal(Renta contexto)` de la clase `KilometrajeLibre`
      5. Hago que el método `calcularTotal(Renta contexto, String tipo)` de la clase `RentaStrategy` sea abstracto y ya no reciba `String tipo` como parámetro
      6. Convierto la clase `RentaStrategy` en una interfaz y elimino el modificador `abstract` del método `calcularTotal(Renta contexto)`
6. Elimino la v.i `String tipoRenta` de la clase `Renta` y el método `String setTipoRenta(Strin tipoRenta)`
7. Modifico el constructor de la clase `Renta` para que asigna a la v.i `estrategia` una nueva instancia de `Basico` por defecto.
8. En la clase `RentaTest` cambio `renta.setTipoRenta("KILOMETRAJE_LIBRE")` por `renta.setEstrategia(new KilometrajeLibre())`

## Código final

```java
    public class Renta {
        private Vehiculo vehiculo;
        private Cliente cliente;
        private int diasRenta;
        private RentaStrategy estrategia;
        private int KilometrajeInicial;

        public Renta(Vehiculo vehiculo, Cliente cliente, int diasRenta, int kilometrajeInicial) {
            this.vehiculo = vehiculo;
            this.cliente = cliente;
            this.diasRenta = diasRenta;
            this.KilometrajeInicial = kilometrajeInicial;
            this.estrategia = new Basico();
            
        }

        public void setEstrategia(RentaStrategy estrategia) {
            this.estrategia = estrategia;
        }

        public double calcularTotal() {
            return this.estrategia.calcularTotal(this);
        }

        public int calcularKmRecorridos() {
            return this.vehiculo.getKilometraje() - this.KilometrajeInicial;
        }
        
        public int calcularPrecioPorDia() {
            return this.diasRenta + this.vehiculo.getPrecioPorDia();
        }
        
        public int calcularPrecioPorKm() {
            return this.calcularKmRecorridos() * this.vehiculo.getPrecioPorKm();
        }
        
        public double aplicarDescuentoAntiguedad(double precio) {
            double adicional = 1;
            if (this.Vehiculo.getAntiguedad() > 5) {
                adicional = 0.85;
            }
            return precio * adicional;
        }
    }
    
    public interface RentaStrategy {
        public abstract double calcularTotal(Renta contexto);
    }
    
    public class Basico implements RentaStrategy {
        @Override
        public double calcularTotal(Renta contexto) {
            return contexto.aplicarDescuentoAntiguedad(contexto.calcularPrecioPorDia()+contexto.calcularPrecioPorKm());
        }
    }
    
    public class Plus implements RentaStrategy {
        @Override
        public double calcularTotal(Renta contexto) {
            return contexto.calcularPrecioPorKm();
        }
    }
    
    public class KilometrajeLibre implements RentaStrategy {
        @Override
        public double calcularTotal(Renta contexto) {
            return contexto.calcularPrecioPorDia();
        }
    }
    
    public class RentaTest {
        //...
       renta.setEstrategia(new KilometrajeLibre());
       //...
    }
    
    public class Vehiculo {
        //...
    }
```