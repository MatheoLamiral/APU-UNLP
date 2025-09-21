```Java
public class EmpleadoTemporario {
    public String nombre;
    public String apellido;
    public double sueldoBasico = 0;
    public double horasTrabajadas = 0;
    public int cantidadHijos = 0;
    // ......
    
    public double sueldo() {
        return this.sueldoBasico 
            + (this.horasTrabajadas * 500) 
            + (this.cantidadHijos * 1000) 
            - (this.sueldoBasico * 0.13);
    }
}


public class EmpleadoPlanta {
    public String nombre;
    public String apellido;
    public double sueldoBasico = 0;
    public int cantidadHijos = 0;
    // ......
    
    public double sueldo() {
        return this.sueldoBasico 
            + (this.cantidadHijos * 2000)
            - (this.sueldoBasico * 0.13);
    }
}

public class EmpleadoPasante {
    public String nombre;
    public String apellido;
    public double sueldoBasico = 0;
    // ......
    
    public double sueldo() {
        return this.sueldoBasico - (this.sueldoBasico * 0.13);
    }
}

```
### Bad smell: Duplicate code, los atributos `nombre`, `apellido` y `sueldoBasico`
1. Aplico **Extract Super Class**
    1. Creo la clase abstracta `Empleado`
    2. hago que `EmpleadoTemporario`, `EmpleadoPlanta` y `EmpleadoPasante` extiendan de `Empleado`
    3. Aplico **Pull up field** para `nombre`, `apellido` y `sueldoBasico`
### Bas semll: Duplicate code en el método `double sueldo()` de las clases `EmpleadoTemporario`, `EmpleadoPlanta` y `EmpleadoPasante`
2. Aplico **Form template method**
    1. Aplico un **Extract method** del comportamiento unico en las subclases 
       1. En la clase `EmpleadoTemporario` extraigo `(this.horasTrabajadas * 500) + (this.cantidadHijos * 1000)` en el método `double sueldo()` creando el método `double calcularAdicionalHorasEHijos()`
       2. En la clase `EmpleadoPlanta` extraigo `(this.cantidadHijos * 2000)` en el método `double sueldo()` creando el método `double calcularAdicionalHijos()`
       3. En la clase `EmpleadoPasante` creo el método `double calcularAdicional()`, que retorna 0
    2. Aplico **Rename method** sobre los métodos creados en el paso anterior para que tengan la misma firma
       1. cmabio el nombre de `calcularAdicionalHorasEHijos()` a `calcularAdicional()`
       2. cambio el nombre de `calcularAdicionalHijos()` a `calcularAdicional()`
    3. Reemplazo en el método `double sueldo()` de las subclases el cálculo del adicional por un llamado a `calcularAdicional()`
    4. Aplico **Pull up method** de  `sueldo()` a la clase `Empleado`
    5. Defino el método abstracto `double calcularAdicional()` en la clase `Empleado`
```Java
    public abstract class  Empleado {
        public String nombre;
        public String apellido;
        public double sueldoBasico = 0;

        abstract double calcularAdicional();

        public double sueldo(){
            return this.sueldoBasico + this.calcularAdicioinal() - (this.sueldoBasico * 0.13);
        }

    }
    public class EmpleadoTemporario extends Empleado{
        public double horasTrabajadas = 0;
        public int cantidadHijos = 0;
        // ......

        public double calcularAdicional() {
            return (this.horasTrabajadas * 500) + (this.cantidadHijos * 1000) 
        }
    }   


    public class EmpleadoPlanta extends Empleado{
        public int cantidadHijos = 0;
        // ......
        
        public double calcularAdicional() {
            return (this.cantidadHijos * 2000)
        }
    }

    public class EmpleadoPasante extends Empleado{
        // ......
        
        public double calcularAdicional() {
            return 0;
        }
    }
  ```
### Bad smell: Magic number en `double sueldo()` de la clase `Empleado` y `double calcularAdicional()` de las clases `EmpleadoTemporario` y `EmpleadoPlanta`
 1. convierto los "magic numers" en constantes (`porcentajeDescuento` en `Empleado`, `bonoHorasTrabajadas` en `EmpleadoTemporario`, `bonoCantidadHijos` en `EmpleadoPlanta` y `EmpleadoTemporario`) y reemplazo los valores de las operaciones por llamadas a las constantes 
```Java
    public abstract class  Empleado {
        public String nombre;
        public String apellido;
        public double sueldoBasico = 0;
        private final double porcentajeDescuento = 0.13;

        abstract double calcularAdicional();

        public double sueldo(){
            return this.sueldoBasico + this.calcularAdicioinal() - (this.sueldoBasico * this.porcentajeDescuento);
        }

    }
    public class EmpleadoTemporario extends Empleado{
        public double horasTrabajadas = 0;
        public int cantidadHijos = 0;
        private final double bonoHorasTrabajadas = 500;
        private final double bonoCantidadHijos = 1000;
        // ......

        public double calcularAdicional() {
            return (this.horasTrabajadas * this.bonoHorasTrabajadas) + (this.cantidadHijos * this.bonoCantidadHijos);
        }
    }   


    public class EmpleadoPlanta extends Empleado{
        public int cantidadHijos = 0;
        private final double bonoCantidadHijos = 2000;
        // ......
        
        public double calcularAdicional() {
            return (this.cantidadHijos * this.bonoCantidadHijos);
        }
    }

    public class EmpleadoPasante extends Empleado{
        // ......
        
        public double calcularAdicional() {
            return 0;
        }
    }
  ```
## Extensión con null object
