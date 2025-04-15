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
# Bad smell: Feature envy
- ```Java
    public class Juego {
        // ......
        public void incrementar(Jugador j) {
            j.puntuacion = j.puntuacion + 100;//accede mas a los datos de jugador que a los de juego 
        }

        public void decrementar(Jugador j) {
            j.puntuacion = j.puntuacion - 50;//accede mas a los datos de jugador que a los de juego
        }
    
    }
  ```
### Aplico Extract Method
- ```Java
    public class Juego {
        // ......
        public void incrementar(Jugador j) {
            j.incrementar();
        }

        public void decrementar(Jugador j) {
            j.decrementar();
        }
    
    }

    public class Jugador {
        public String nombre;
        public String apellido;
        public int puntuacion = 0;

        public void incrementar(){
            this.puntuacion = this.puntuacion + 100;
        }

        public void decrementar(){
            this.puntuacion = this.puntuacion - 50;
        }
    }
  ```