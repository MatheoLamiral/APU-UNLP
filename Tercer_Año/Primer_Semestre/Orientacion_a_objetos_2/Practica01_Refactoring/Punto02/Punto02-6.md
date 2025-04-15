```Java
    public class Usuario {
        String tipoSubscripcion;
        // ...

        public void setTipoSubscripcion(String unTipo) {
            this.tipoSubscripcion = unTipo;
        }
        
        public double calcularCostoPelicula(Pelicula pelicula) {
            double costo = 0;
            if (tipoSubscripcion=="Basico") {
                costo = pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno();
            }
            else if (tipoSubscripcion== "Familia") {
                costo = (pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno()) * 0.90;
            }
            else if (tipoSubscripcion=="Plus") {
                costo = pelicula.getCosto();
            }
            else if (tipoSubscripcion=="Premium") {
                costo = pelicula.getCosto() * 0.75;
            }
            return costo;
        }
    }

    public class Pelicula {
        LocalDate fechaEstreno;
        // ...

        public double getCosto() {
            return this.costo;
        }
        
        public double calcularCargoExtraPorEstreno(){
            // Si la Película se estrenó 30 días antes de la fecha actual, retorna un cargo de 0$, caso contrario, retorna un cargo extra de 300$
            return (ChronoUnit.DAYS.between(this.fechaEstreno, LocalDate.now()) ) > 30 ? 0 : 300;
        }
    }
```
# Bad smell: Switch statement
- ```Java
    public double calcularCostoPelicula(Pelicula pelicula) {
            double costo = 0;
            if (tipoSubscripcion=="Basico") {
                costo = pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno();
            }
            else if (tipoSubscripcion== "Familia") {
                costo = (pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno()) * 0.90;
            }
            else if (tipoSubscripcion=="Plus") {
                costo = pelicula.getCosto();
            }
            else if (tipoSubscripcion=="Premium") {
                costo = pelicula.getCosto() * 0.75;
            }
            return costo;
    }
  ```
### Aplico Replace type code with subclasses
- ```Java 
    public class Usuario {
        Subscripcion tipoSubscripcion;
        // ...

        public void setTipoSubscripcion(String unTipo) //pasa a ser incorrecto
            this.tipoSubscripcion = unTipo;
        }
        
        public double calcularCostoPelicula(Pelicula pelicula) {
            double costo = 0;
            if (tipoSubscripcion=="Basico") {//pasa a ser incorecto
                costo = pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno();
            }
            else if (tipoSubscripcion== "Familia") {
                costo = (pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno()) * 0.90;
            }
            else if (tipoSubscripcion=="Plus") {
                costo = pelicula.getCosto();
            }
            else if (tipoSubscripcion=="Premium") {
                costo = pelicula.getCosto() * 0.75;
            }
            return costo;
        }
    }

    public abstract class Subscripcion {

    }

    public class Basico extends Subscripcion{

    }

    public class Familia extends Subscripcion{
        
    }

    public class Plus extends Subscripcion{
        
    }

    public class Premium extends Subscripcion{
        
    }

    public class Pelicula {
        LocalDate fechaEstreno;
        // ...

        public double getCosto() {
            return this.costo;
        }
        
        public double calcularCargoExtraPorEstreno(){
            // Si la Película se estrenó 30 días antes de la fecha actual, retorna un cargo de 0$, caso contrario, retorna un cargo extra de 300$
            return (ChronoUnit.DAYS.between(this.fechaEstreno, LocalDate.now()) ) > 30 ? 0 : 300;
        }
    }
  ```
### Aplcio Replace conditional with polymorphism
- ```Java 
    public class Usuario {
        Subscripcion subscripcion;
        // ...
        
        public double calcularCostoPelicula(Pelicula pelicula) {
            this.subscripcion.calcularCostoPelicula(pelicula);
        }
    }

    public abstract class Subscripcion {

        public double calcularCostoPelicula(Pelicula pelicula){
            return this.calcularCostoPelicula(pelicula);
        }

    }

    public class Basico extends Subscripcion{

        public double calcularCostoPelicula(Pelicula pelicula){
            return pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno();
        }
    }

    public class Familia extends Subscripcion{

        public double calcularCostoPelicula(Pelicula pelicula){
            return (pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno()) * 0.90;;
        }
    }

    public class Plus extends Subscripcion{

        public double calcularCostoPelicula(Pelicula pelicula){
            return pelicula.getCosto();
        }
    }

    public class Premium extends Subscripcion{

        public double calcularCostoPelicula(Pelicula pelicula){
            return pelicula.getCosto() * 0.75;
        }

    }

    public class Pelicula {
        LocalDate fechaEstreno;
        // ...

        public double getCosto() {
            return this.costo;
        }
        
        public double calcularCargoExtraPorEstreno(){
            // Si la Película se estrenó 30 días antes de la fecha actual, retorna un cargo de 0$, caso contrario, retorna un cargo extra de 300$
            return (ChronoUnit.DAYS.between(this.fechaEstreno, LocalDate.now()) ) > 30 ? 0 : 300;
        }
    }
  ```
