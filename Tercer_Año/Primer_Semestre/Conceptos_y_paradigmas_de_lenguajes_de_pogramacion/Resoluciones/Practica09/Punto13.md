# Ejercicio 13

```Java
    public class ExcepcionUno extends Exception {
        public ExcepcionUno(){
            super(); // constructor por defecto de Exception
        }
        public ExcepcionUno( String cadena ){
            super( cadena ); // constructor param. de Exception
        }
    }
    public class ExcepcionDos extends Exception {
        public ExcepcionDos(){
            super(); // constructor por defecto de Exception
        }
        public ExcepcionDos( String cadena ){
            super( cadena ); // constructor param. de Exception
        }
    }
    public class ExcepcionTres extends Exception {
        public ExcepcionTres(){
            super(); // constructor por defecto de Exception
        }
        public ExcepcionTres( String cadena ){
            super( cadena ); // constructor param. de Exception
        }
    }
    public class Lanzadora {
        public void lanzaSiNegativo(int param) throws ExcepcionUno {
            if (param < 0)
                throw new ExcepcionUno("Numero negativo");
        }
        public void lanzaSimayor100(int param) throws ExcepcionDos {
            if (param >100 and param<125)
                throw new ExcepcionDos("Numero mayor100");
        }
        public void lanzaSimayor125(int param) throws ExcepcionTres {
            if (param >= 125)
                throw new ExcepcionTres("Numero mayor125");
        }
    }
    import java.io.FileInputStream;
    import java.io.IOException;
    public class Excepciones {
        public static void main(String[] args) {
        // Para leer un fichero
            Lanzadora lanza = new Lanzadora();
            FileInputStream entrada = null;
            int leo;
            try {
                entrada = new FileInputStream("fich.txt");
                while ((leo = entrada.read()) != -1){
                    if (leo < 0)
                        lanza.lanzaSiNegativo(leo);
                    else if (leo > 100)
                        lanza.lanzaSimayor100(leo);
                }
                entrada.close();
                System.out.println("Todo fue bien");
            }
            catch (ExcepcionUno e) { // Personalizada
                System.out.println("Excepcion: " + e.getMessage());
            }
            catch (ExcepcionDos e) { // Personalizada
                System.out.println("Excepcion: " + e.getMessage());
            }
            catch (IOException e) { // Estándar
                System.out.println("Excepcion: " + e.getMessage());
            }
            finally {
                if (entrada != null)
                    try {
                        entrada.close(); // Siempre queda cerrado
                    }
                    catch (Exception e) {
                        System.out.println("Excepcion: " + e.getMessage());
                    }
                System.out.println("Fichero cerrado.");
            }
        }   
    }
``` 

## Caminos de ejecución