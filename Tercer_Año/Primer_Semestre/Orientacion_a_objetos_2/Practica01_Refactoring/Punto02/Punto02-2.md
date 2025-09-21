```Java
    public class Juego {
        // ......
        public void incrementar(Jugador j) {
            j.puntuacion = j.puntuacion + 100;
        }

        public void decrementar(Jugador j) {
            j.puntuacion = j.puntuacion - 50;
        }
    
    }

    public class Jugador {
        public String nombre;
        public String apellido;
        public int puntuacion = 0;
    }
```
### Bad smell: Feature envy en el método `incrementar` y `decrementar` de la clase `Juego`
1. Aplico **Move method** del comportamiento de `incrementar()` a la clase `Jugador`
   2. Creo el método `incrementarPuntaje()` en la clase `Jugador` 
   3. copio el comportamiento de `incrementar()` a `incrementarPuntaje()`
   4. Reemplazo el código de `incrementar()` por un llamado a `j.incrementarPuntaje()`
2. Aplico **Move method** del comportamiento de `decrementar()` a la clase `Jugador`
   2. Creo el método `decrementarPuntaje()` en la clase `Jugador` 
   3. copio el comportamiento de `decrementar()` a `decrementarPuntaje()`
   4. Reemplazo el código de `decrementar()` por un llamado a `j.decrementarPuntaje()`

```Java
    public class Juego {
        // ......
        public void incrementar(Jugador j) {
            j.incrementarPuntaje();
        }

        public void decrementar(Jugador j) {
            j.decrementarPuntaje();
        }
    
    }

    public class Jugador {
        public String nombre;
        public String apellido;
        public int puntuacion = 0;

        public void incrementarPuntaje(){
            this.puntuacion = this.puntuacion + 100;
        }

        public void decrementarPuntaje(){
            this.puntuacion = this.puntuacion - 50;
        }
    }
  ```
### Bad smell: Magic number en el método `incrementarPuntaje()` y `decrementarPuntaje()`
1. Convierto los "magic numers" en constantes (`incrementoPuntaje`, `decrementoPuntaje`) y reemplazo los valores de las operaciones por llamadas a las constantes 

```Java
    public class Juego {
        // ......
        public void incrementar(Jugador j) {
            j.incrementarPuntaje();
        }

        public void decrementar(Jugador j) {
            j.decrementarPuntaje();
        }
    
    }

    public class Jugador {
        public String nombre;
        public String apellido;
        public int puntuacion = 0;
        private final int incrementoPuntaje = 100;
        private final int decrementoPuntaje = 50;

        public void incrementarPuntaje(){
            this.puntuacion = this.puntuacion + this.incrementoPuntaje;
        }

        public void decrementarPuntaje(){
            this.puntuacion = this.puntuacion - this.decrementoPuntaje;
        }
    }
```