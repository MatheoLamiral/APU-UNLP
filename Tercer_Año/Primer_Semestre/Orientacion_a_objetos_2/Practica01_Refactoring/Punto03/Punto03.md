```Java
    public class Document {
        List<String> words;
    
        public long characterCount() {
            long count = this.words.stream().mapToLong(w -> w.length()).sum();
            return count;
        }

        public long calculateAvg() {
            long avgLength = this.words.stream().mapToLong(w -> w.length()).sum() / this.words.size();
            return avgLength;
        }
        
        // Resto del código que no importa
    }

```
# Bad smell duplicate code
- ```Java
        public long characterCount() {
            long count = this.words.stream().mapToLong(w -> w.length()).sum();
            return count;
        }

        public long calculateAvg() {
            long avgLength = this.words.stream().mapToLong(w -> w.length()).sum() / this.words.size();
            return avgLength;
        }
  ```
### Reemplazo código duplicado por llamada al método
- ```Java
    public class Document {
        List<String> words;
    
        public long characterCount() {
            long count = this.words.stream().mapToLong(w -> w.length()).sum();
            return count;
        }

        public long calculateAvg() {
            long avgLength = this.characterCount() / this.words.size();
            return avgLength;
        }
        
        // Resto del código que no importa
    }
  ```

# Problemas al calcular las estadísticas 
## Bug: si la lista está vacía divide por 0
- ```Java
    long avgLength = this.characterCount() / this.words.size();
  ```
### Verifico anteriormente que la lista no este vacía
- ```Java
    public class Document {
        List<String> words;
    
        public long characterCount() {
            long count = this.words.stream().mapToLong(w -> w.length()).sum();
            return count;
        }

        public long calculateAvg() {
            long avgLength = (this.words.size() != 0) ? (this.characterCount() / this.words.size()) : 0;
            return avgLength;
        }
        
        // Resto del código que no importa
    }
  ```
## Esto no es un bad smell, ni se considera refactoring. Al momento de realizar refactoring no consideramos errores de cálculos y cosas del estilo 