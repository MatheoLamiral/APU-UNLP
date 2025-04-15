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
- # Bad smell: Clases con métodos y atributos en común
- ```Java
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
- ### Aplico extract Superclass 
- ```Java
    public abstract class  Empleado {

    }
    public class EmpleadoTemporario extends Empleado{
        private String nombre;
        private String apellido;
        private double sueldoBasico = 0;
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


    public class EmpleadoPlanta extends Empleado{
        private String nombre;
        private String apellido;
        private double sueldoBasico = 0;
        public int cantidadHijos = 0;
        // ......
        
        public double sueldo() {
            return this.sueldoBasico 
                + (this.cantidadHijos * 2000)
                - (this.sueldoBasico * 0.13);
        }
    }

    public class EmpleadoPasante extends Empleado{
        private String nombre;
        private String apellido;
        private double sueldoBasico = 0;
        // ......
        
        public double sueldo() {
            return this.sueldoBasico - (this.sueldoBasico * 0.13);
        }
    }
  ```
- # Bad smell: atributos repetidos 
- ```Java
        private String nombre;
        private String apellido;
        private double sueldoBasico = 0;
  ```
- ### Aplico pull up field
- ```Java
    public abstract class  Empleado {
        private String nombre;
        private String apellido;
        private double sueldoBasico = 0;

    }
    public class EmpleadoTemporario extends Empleado{
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


    public class EmpleadoPlanta extends Empleado{
        public int cantidadHijos = 0;
        // ......
        
        public double sueldo() {
            return this.sueldoBasico 
                + (this.cantidadHijos * 2000)
                - (this.sueldoBasico * 0.13);
        }
    }

    public class EmpleadoPasante extends Empleado{
        // ......
        
        public double sueldo() {
            return this.sueldoBasico - (this.sueldoBasico * 0.13);
        }
    }
  ```
- # Bad smell: métodos repetidos con comportamiento similar 
- ```Java
    public class EmpleadoTemporario extends Empleado{
        // ......
        
        public double sueldo() {
            return this.sueldoBasico 
                + (this.horasTrabajadas * 500) 
                + (this.cantidadHijos * 1000) 
                - (this.sueldoBasico * 0.13);
        }
    }   


    public class EmpleadoPlanta extends Empleado{
        // ......
        public double sueldo() {
            return this.sueldoBasico 
                + (this.cantidadHijos * 2000)
                - (this.sueldoBasico * 0.13);
        }
    }

    public class EmpleadoPasante extends Empleado{
        // ......
        public double sueldo() {
            return this.sueldoBasico - (this.sueldoBasico * 0.13);
        }
    }
  ```
- ### Aplico pull up method
- ```Java
    public abstract class  Empleado {
        private String nombre;
        private String apellido;
        private double sueldoBasico = 0;

        public double sueldo(){
            return this.sueldoBasico + this.extra() - (this.sueldoBasico * 0.13);
        }

    }
    public class EmpleadoTemporario extends Empleado{
        public double horasTrabajadas = 0;
        public int cantidadHijos = 0;
        // ......
        
        public double extra() {
            return (this.horasTrabajadas * 500) + (this.cantidadHijos * 1000) 
        }
    }   


    public class EmpleadoPlanta extends Empleado{
        public int cantidadHijos = 0;
        // ......
        
        public double extra() {
            return (this.cantidadHijos * 2000)
        }
    }

    public class EmpleadoPasante extends Empleado{
        // ......
        
        public double extra() {
            
        }
    }
  ```