# Visualización de Datos

### Ejercicio 1

- Dimensión
  - Se utiliza para estructurar y organizar los datos en un modelo multidimensional. Las dimensiones se utilizan para identificar cada tupla de los datos en una tabla de hechos. En el modelo multidimensional, existen tablas de dimensión que constan de tuplas de atributos de la dimensión. En un análisis de ventas, las dimensiones podrían incluir el Producto, el Trimestre fiscal o la Región. Las operaciones analíticas típicas, como el Slice and dice (aislar y reagrupar), implican realizar operaciones de proyección en las dimensiones. El pivotaje (rotación) también permite observar el cubo de datos desde una orientación dimensional diferente
- Medida
  - Valor que se registra y se analiza dentro de la estructura definida por las dimensiones. Las medidas son las variables de medida u observación que se asocian con las tablas de hechos (las cuales contienen los datos a analizar). Una tabla de hechos es una agrupación de tuplas, y cada una contiene una o varias variables de medida que se asocian con las tablas de dimensión mediante punteros
- En el contexto de un esquema de estrella, las tablas de dimensión (como Producto o Región) proporcionan el contexto o la estructura organizativa, mientras que la tabla de hechos contiene las medidas que están siendo medidos dentro de ese contexto.

### Ejercicio 2

- **Dato discreto**
  - Se refiere a valores que representan objetos separados e identificables. A menudo están ligados a tipos que tienen un número contable o finito de valores posibles (como los números enteros).
- **Dato continuo**
  - Se refiere a valores que pueden tomar cualquier valor dentro de un rango o que representan una distribución que varía ininterrumpidamente. A menudo están ligados a tipos de datos que permiten fracciones y precisión variable, como los números reales o de coma flotante (float, real, double precision).
- los datos discretos representan entidades separadas (como puntos en un mapa), mientras que los datos continuos representan fenómenos que varían de forma fluida a través de un espacio (como la temperatura o los niveles de contaminación)

### Ejercicio 3

La preparación de datos, conocida también como preprocesamiento, limpieza o normalización, es fundamental porque asegura la calidad, coherencia y estructura de los datos, lo cual es crítico para que puedan ser utilizados de manera eficiente, ética y confiable en cualquier proceso subsiguiente, ya sea el análisis, la toma de decisiones o la alimentación de sistemas avanzados.
- **Garantía de Integridad**: Protege los datos almacenados contra la corrupción, fallos o modificaciones inadecuadas, asegurando la validez y coherencia de la información. Esto previene la toma de decisiones inexactas o erróneas basadas en datos inconsistentes
- **Optimización del Diseño (Normalización)**: Mediante la normalización, se logra minimizar la redundancia y reducir las anomalías (de inserción, borrado y actualización)
- **Soporte a la Tecnología Avanzada**: Es un paso previo crucial para la minería de datos. Además, es esencial para cumplir con los principios FAIR-R, que buscan evitar que los sistemas de IA se basen en datos incompletos o sesgados, lo cual conduce a un mayor rendimiento y aplicaciones más éticas
- **Costo Operativo**: Históricamente, la limpieza de los datos ha sido considerada como el mayor consumidor de trabajo en el proceso de construcción de un almacén de datos

### Ejercicio 4

- Inciso a
  - El gráfico que utilizaría sería un **gráfico de barras**, ya que nos permite comparar los valores numéricos entre categorías (regiones en este caso) y observar las variaciones a lo largo del tiempo.
- Inciso b
  - EL gráfico que utilizaría sería un **gráfico de barras**, ya que nos permitirá comparar por rangos de edad y comparar los valores numéricos uno al lado del otro.
- Inciso c
  - El gráfico que utilizaría sería un **gráfico de dispersión**, ya que es ideal para ver si hay alguna correlación entre dos variables numéricas (en este caso, el precio de los servicios y la calificación de los clientes).
- Inciso d
  - El gráfico que utilizaría sería un **gráfico de torta**, ya que es útil para mostrar como unas pocas variables (2 a 5) se comparan en porcentaje entre ellas y con el total, y en este caso, útil para ver la proporción de préstamos por género del libro (narrativa, poesía, cuento, novela y biografía).
- Inciso e
  - El gráfico que utilizaría sería un **gráfico de líneas**, ya que es ideal para mostrar los cambios en los datos en un período de tiempo, facilitando la identificación de tendencias. Se podrá ver el aumento, disminución o estabilidad de las temperaturas a lo largo de los años. En el eje X se colocarán las fechas y en el eje Y las temperaturas promedio registradas en cada fecha.
  - Las tablas relevantes para representar el análisis son:
    - **TemperaturasPromedio**(<ins>#registroProm</ins>, fecha, valorProm)
- Inciso f
  - El gráfico que utilizaría sería un **gráfico de torta**, ya que es útil para mostrar como unas pocas variables (2 a 5) se comparan en porcentaje entre ellas y con el total, y en este caso, útil para ver la proporción de especies por categoría (Moluscos, Artrópodos, Cnidarios, Peces y Mamíferos marinos).
  - Las tablas relevantes para representar el análisis son:
    - **CategoriaEspecie** (<ins>#cat_especie</ins>, cat_nombre )
    - **EspecieIdentfificada** (<ins>#especie</ins>, #cat_especie, nombre_especie, cantidad)
- Inciso g
  - Para la primera visualización, el gráfico que utilizaría sería un **gráfico de barras**, ya que nos permite comparar los valores numéricos entre categorías (categorías de productos en este caso), observar las variaciones a lo largo del tiempo y ver cual es la categoría más vendida.
    - Las tablas relevantes para representar el análisis son:
      - **Item_Venta** (<ins>#id_venta</ins>, id_producto, cant)
      - **Producto** (<ins>#id_producto</ins>, nombre_producto, desc_producto ,precio_unit, categoria)
  - Para la segunda visualización, el gráfico que utilizaría sería un **gráfico de líneas**, ya que es ideal para mostrar los cambios en los datos en un período de tiempo, facilitando la identificación de tendencias. Se podrá ver el aumento, disminución o estabilidad de las ventas totales a lo largo de los meses. En el eje X se colocarán los meses y en el eje Y el monto de las ventas registradas en la sucursal 10 cada mes.
    - Las tablas relevantes para representar el análisis son:
      - **Venta** (<ins>#id_venta</ins>, fecha_venta, total_venta)
      - **Sucursal** (<ins>#id_sucursal</ins>, ubicación, cant_empleados)