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
### Bad smell: Switch statement
```Java
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
1. Aplico **Replace conditional with strategy**
   1. Creo la jerarquía de clases necesaria para representar los tipos de suscripción.
      1. Creo la clase abstracta `Subscripcion`.
      2. Creo las subclases `Basico`, `Familia`, `Plus` y `Premium` que extienden de `Subscripcion`.
   2. Aplico **move method** del método `calcularCostoPelicula(Pelicula pelicula)` de la clase `Usuario` a la clase `Subscripcion`.
      1. creo una v.i en la clase `usuario` de tipo `Subscripcion` llamada `tipoSubscripcion`
      1. creo el método `calcularCostoPelicula(Pelicula pelicula, String tipo)` en la clase `Subscripcion`
      2. copio el comportamiento del método `calcularCostoPelicula(Pelicula pelicula)` de la clase `Usuario` en el nuevo método de la clase `Subscripcion`
      3. Reemplazo el condicional de la clase `Usuario` por un llamado al método `this.tipoSubscripcion.calcularCostoPelicula(Pelicula pelicula, String tipo)` de la clase `Subscripcion`
   2. Creo un método `setTipoSubscripcion(Subscripcion unTipo)` en la clase `Usuario` que seteará la variable `tipoSubscripcion` 
   3. Aplico **Replace conditional with polymorphism** 
      1. Por cada subclase de `Subscripcion`
         1. Creo el método `calcularCostoPelicula(Pelicula pelicula)`
         2. copio en el método el comportamiento de la rama `if` correspondiente (lo que corresonde a `if (tipoSubscripcion=="Basico")` irá en la clase `Basico`, lo que corresponde a `if (tipoSubscripcion== "Familia")` irá en la clase `Familia`, etc.)
         3. Borro la condición y el código de la rama correspondiente en la superclase
      2. Hago que el método `calcularCostoPelicula(Pelicula pelicula, String tipo)` de la clase `Subscripcion` sea abstracto.
   4. Elimino el parametro `String tipo` del método `calcularCostoPelicula(Pelicula pelicula)` de la clase `Subscripcion`
   5. Elimino la v.i `String tipoSubscripcion` de la clase `Usuario` y el método `setTipoSubscripcion(String unTipo)` de la clase `Usuario`



-------
   1. Creo la jerarquía de clases necesaria para representar los tipos de suscripción.
      1. Creo la clase abstracta `Subscripcion`.
      2. Creo las subclases `Basico`, `Familia`, `Plus` y `Premium` que extienden de `Subscripcion`.
   2. En la clase `Usuario` cambio el tipode la variable `tipoSubscripcion` de `String` a `Subscripcion`.
   3. Por cada subclase de `Subscripcion`
      1. creo el método `calcularCostoPelicula(Pelicula pelicula)` 
      2. copio en el método el comportamiento de la rama `if` correspondiente, haciendo que se retorne el cálculo
      3. Borro el código del `if` en la clase `Usuario`
   4. Asigno a la variable `costo` el llamado a `this.subscripcion.calcularCostoPelicula(pelicula)`
   5. Defino el método `calcularCostoPelicula(Pelicula pelicula)` como abstracto en la clase `Subscripcion`.
2. Modifico el método `setTipoSubscripcion(String unTipo)` de la clase `Usuario` para que reciba un objeto de tipo `Subscripcion` en lugar de un `String`.
```Java 
    public class Usuario {
        Subscripcion tipoSubscripcion;
        // ...

        public void setTipoSubscripcion(Subscripcion unTipo) {
            this.tipoSubscripcion = unTipo;
        }
        
        public double calcularCostoPelicula(Pelicula pelicula) {
            costo = this.tipoSubscripcion.calcularCostoPelicula(pelicula);
            return costo;
        }
    }

    public abstract class Subscripcion {
        public abstract double calcularCostoPelicula(Pelicula pelicula);
    }

    public class Basico extends Subscripcion{
        public double calcularCostoPelicula(Pelicula pelicula){
            return pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno();
        }
    }

    public class Familia extends Subscripcion{
        public double calcularCostoPelicula(Pelicula pelicula){
            return (pelicula.getCosto() + pelicula.calcularCargoExtraPorEstreno()) * 0.90;
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
  ```
### Bad smell: Duplicate code y comportamiento similar en los métodos `calcularCostoPelicula` de las subclases de `Subscripcion`
1. Aplico **Form template method**
   1. Aplico un **Extract method** del comportamiento único en las subclases, creando el método `double calcularAdicional(Pelicula pelicula, double costo)` en cada subclase.
      1. En la clase `Basico` extraigo `pelicula.calcularCargoExtraPorEstreno()` y lo copio en el método `calcularAdicional(Pelicula pelicula, double costo)`
      2. En la clase `Familia` extraigo `pelicula.calcularCargoExtraPorEstreno()) * 0.90` y lo copio en el método `calcularAdicional(Pelicula pelicula, double costo)`
      3. En la clase `Plus` el método `double calcularAdicional(Pelicula pelicula, double costo)`retorna 0
      4. En la clase `Premium` creo el método `double calcularAdicional(Pelicula pelicula, double costo)`, que retorna `costo * 0.75` 
   2. Modifico el método `calcularCostoPelicula(Pelicula pelicula)` de cada subclase para que llame a `calcularAdicional(pelicula, plicula.getCosto())` y retorne el resultado.
   3. Aplico **Pull up method** del método `calcularCostoPelicula(Pelicula pelicula)` a la clase `Subscripcion`.
   4. Elimino el método abstracto `calcularCostoPelicula(Pelicula pelicula)` anterior que teniamos.
   4. Defino el método abstracto `double calcularAdicional(Pelicula pelicula, double costo)` en la clase `Subscripcion`.
```Java
    public abstract class Subscripcion {

        public abstract double calcularAdicional(Pelicula pelicula, double costo);
        
        public double calcularCostoPelicula(Pelicula pelicula){
            return this.calcularAdicional(pelicula, pelicula.getCosto());
        }
    }

    public class Basico extends Subscripcion{
    
        public double calcularAdicional(Pelicula pelicula, double costo){
            return costo + pelicula.calcularCargoExtraPorEstreno();
        }
    }

    public class Familia extends Subscripcion{
    
        public double calcularAdicional(Pelicula pelicula, double costo){
            return (costo + pelicula.calcularCargoExtraPorEstreno()) * 0.90;
        }
        
    }

    public class Plus extends Subscripcion{
    
        public double calcularAdicional(Pelicula pelicula, double costo){
            return costo;
        }
        
    }

    public class Premium extends Subscripcion{
    
        public double calcularAdicional(Pelicula pelicula, double costo){
            return costo * 0.75;
        }
        
    }
  ```
### Bad smell: Temporary field en el método `calcularCostoPelicula(Pelicula pelicula)` de la clase `Usuario`
1. Elimino la variable `costo` retornando directamente `this.tipoSubscripcion.calcularCostoPelicula(pelicula)`
```Java
    public class Usuario {
        Subscripcion tipoSubscripcion;
        // ...

        public void setTipoSubscripcion(Subscripcion unTipo) {
            this.tipoSubscripcion = unTipo;
        }
        
        public double calcularCostoPelicula(Pelicula pelicula) {
            return this.tipoSubscripcion.calcularCostoPelicula(pelicula);
        }
    }
```
### Bad smell: magic number en los métodos `calcularAdicional` en las clases `Familia` y `Premium`, y `calcularCargoExtraPorEstreno()` de la clase `Pelicula`
1. Convierto los magic numers en constantes (`porcentajeFamilia`, `porcentajePremium`, `diasCargoExtra`, `cargoExtra`) y reemplazo los valores de las operaciones por llamadas a las constantes 
```Java
    public class Pelicula {
        LocalDate fechaEstreno;
        private final int diasCargoExtra = 30;
        private final double cargoExtra = 300;
        // ...

        public double calcularCargoExtraPorEstreno(){
            return (ChronoUnit.DAYS.between(this.fechaEstreno, LocalDate.now()) ) > this.diasCargoExtra ? 0 : this.cargoExtra;
        }
    }

    public class Familia extends Subscripcion{
        private final double porcentajeFamilia = 0.90;

        public double calcularAdicional(Pelicula pelicula, double costo){
            return (costo + pelicula.calcularCargoExtraPorEstreno()) * this.porcentajeFamilia;
        }
    }

    public class Premium extends Subscripcion{
        private final double porcentajePremium = 0.75;

        public double calcularAdicional(Pelicula pelicula, double costo){
            return costo * this.porcentajePremium;
        }
    }
```
### Bad smell: variables publicas en la clase `Usuario` y `Pelicula`
1. Cambio las variables `tipoSubscripcion` de la clase `Usuario` y `fechaEstreno` de la clase `Pelicula` a privadas.
```Java
    public class Usuario {
        private Subscripcion tipoSubscripcion;
        // ...
    }

    public class Pelicula {
        private LocalDate fechaEstreno;
        // ...
    }
```
### Bad smell: commentarios innecesarios en el método `calcularCargoExtraPorEstreno()` de la clase `Pelicula`
1. Elimino el comentario que describe el comportamiento del método `calcularCargoExtraPorEstreno()`, ya que el nombre del método es suficientemente descriptivo.
```Java
    public class Pelicula {
        private LocalDate fechaEstreno;
        private final int diasCargoExtra = 30;
        private final double cargoExtra = 300;

        public double calcularCargoExtraPorEstreno(){
            return (ChronoUnit.DAYS.between(this.fechaEstreno, LocalDate.now()) ) > this.diasCargoExtra ? 0 : this.cargoExtra;
        }
    }
```
### Código final
```Java
    public class Usuario {
        private Subscripcion tipoSubscripcion;
        // ...

        public void setTipoSubscripcion(Subscripcion unTipo) {
            this.tipoSubscripcion = unTipo;
        }
        
        public double calcularCostoPelicula(Pelicula pelicula) {
            return this.tipoSubscripcion.calcularCostoPelicula(pelicula);
        }
    }

    public abstract class Subscripcion {

        public abstract double calcularAdicional(Pelicula pelicula, double costo);
        
        public double calcularCostoPelicula(Pelicula pelicula){
            return this.calcularAdicional(pelicula, pelicula.getCosto());
        }
    }

    public class Basico extends Subscripcion{
    
        public double calcularAdicional(Pelicula pelicula, double costo){
            return costo + pelicula.calcularCargoExtraPorEstreno();
        }
    }

    public class Familia extends Subscripcion{
        private final double porcentajeFamilia = 0.90;

        public double calcularAdicional(Pelicula pelicula, double costo){
            return (costo + pelicula.calcularCargoExtraPorEstreno()) * this.porcentajeFamilia;
        }
    }

    public class Plus extends Subscripcion{
    
        public double calcularAdicional(Pelicula pelicula, double costo){
            return costo;
        }
        
    }

    public class Premium extends Subscripcion{
        private final double porcentajePremium = 0.75;

        public double calcularAdicional(Pelicula pelicula, double costo){
            return costo * this.porcentajePremium;
        }
    }

    public class Pelicula {
        private LocalDate fechaEstreno;
        private final int diasCargoExtra = 30;
        private final double cargoExtra = 300;

        public double getCosto() {
            return this.costo;
        }
        
        public double calcularCargoExtraPorEstreno(){
            return (ChronoUnit.DAYS.between(this.fechaEstreno, LocalDate.now()) ) > this.diasCargoExtra ? 0 : this.cargoExtra;
        }
    }
```

