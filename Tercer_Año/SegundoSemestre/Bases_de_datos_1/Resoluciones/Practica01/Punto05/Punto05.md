**A.**  La relación **brinda** tiene los atributos correspondientes y su clave está bien definida  
- **Verdadero**, la relación tiene los atributos correspondientes y la clave está bien definida ya que cuando hay una cardinalidad N a N, la clave de la relación son los identificadores de las entidades

**B.**  La relación **tiene** tiene los atributos correspondientes y su clave está bien definida
- **Flaso**, la relación tiene los atributos correspondientes, pero su clave no esta bién definida, ya que al ser una relación 1 a 1, no es necesario que todos los atributos formen la clave, con uno de ellos basta  

**C.**  La relación **dicta** tiene los atributos correspondientes y su clave está bien definida
- **Flaso**, la relación tiene los atributos correspondientes, pero su clave no esta bién definida, ya que la clave la debería formar el atributo `idic` y no `cuil` ya que la relación es (1,1) de InfoCurso hacia Profesor   
      
**D.**  La relación **tiene** no debería existir y los identificadores de la agregación deberían estar en **InfoCurso**.  
- **Falso**, la relación `tiene` debe existir ya que todas las relaciones y entidades del modelo ER deben pasar al relacioinal, exceptuando los casos de jerarquías  
  
**E.**  La relación **dicta** no debería existir y los atributos de **Profesor** deberían estar en
**InfoCurso**.  
- **Falso**, la relación `dicta` debe existir ya que todas las relaciones y entidades del modelo ER deben pasar al relacioinal, exceptuando los casos de jerarquías