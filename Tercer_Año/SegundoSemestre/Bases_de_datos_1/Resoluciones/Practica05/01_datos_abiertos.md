# Datos Abiertos

### Ejercicio 1

- Data.gov (Estados Unidos): [https://www.data.gov/](https://www.data.gov/)
  - Tipo de datos disponibles:
    - Economía (empleo, comercio, impuestos, gasto público)
    - Medio ambiente (calidad del aire, clima, energía renovable)
    - Salud (estadísticas de hospitales, enfermedades, seguros médicos)
    - Educación (rendimiento escolar, financiamiento educativo)
- Datos.gob.ar (Argentina): [https://datos.gob.ar/](https://datos.gob.ar/)
  - Tipo de datos disponibles:
    - Transporte (tránsito, rutas, infraestructura vial)
    - Población (censos, estadísticas demográficas)
    - Gobierno (presupuesto, contrataciones públicas, transparencia)
    - Salud y educación (indicadores nacionales y provinciales)
- Kaggle Datasets: [https://www.kaggle.com/datasets](https://www.kaggle.com/datasets)
  - Tipo de datos disponibles:
    - Ciencia de datos (imágenes, texto, datos tabulares para machine learning)
    - Finanzas y economía (datos de mercado, criptomonedas, análisis financiero)
    - Deportes, entretenimiento y redes sociales (estadísticas deportivas, análisis de redes sociales)
    - Datos experimentales o académicos (resultados de investigaciones, datos de ensayos clínicos)
    - Datos experimentales o académicos

### Ejercicio 2

Un dato público se define por su origen o custodia; es cualquier dato generado en el ámbito gubernamental o que se encuentra bajo su guarda. Los datos abiertos se definen por sus condiciones de uso; son aquellos datos, de origen público o no, a los que cualquier persona puede acceder, usar y compartir libremente. Para ser abiertos, deben cumplir la condición de que solo se necesita atribuir su origen y compartirse con la misma licencia con la que fueron publicados. Que un dato sea público es como si fuera un libro que está en una biblioteca, todos saben que existe, pero podría estar encadenado, escrito en un idioma indescifrable o solo disponible en papel (formatos no reutilizables). En cambio, un dato abierto es como un libro que está disponible en formato digital universalmente legible, con permiso explícito para copiarlo, adaptarlo y compartirlo con otros, siempre que se cite al autor (licencia libre)
- Ejemplo Dato Público
  - Textos legislativos, ordenanzas, reglamentos o normativas individuales
    - Estos son datos generados en el ámbito gubernamental. Sin embargo, si estos documentos solo se publican en formatos que no permiten su reutilización por un sistema informático (como un PDF no procesable), no se consideran datos abiertos
- Ejemplo Dato Abierto
  - wikidata
    - base de conocimiento secundaria libre, colaborativa, multilingüe y abierta. Su contenido está publicado bajo una licencia libre (como la Creative Commons Dedicación al Dominio Público 1.0) que permite la reutilización de los datos en diversos escenarios, incluso con fines comerciales, sin necesidad de pedir permiso

### Ejercicio 3

-  **Open Data Commons Public Domain Dedication and License (PDDL)**
   -  Es la licencia más permisiva, ya que permite difundir, reutilizar y adaptar los datos sin restricciones. Esto implica una dedicación al dominio público
-  **Open Data Commons Attribution License**
   -  El requisito esencial de esta licencia es la atribución. Exige la referencia a la autoría o fuente de los datos para la reutilización de la información
-  **Open Data Commons Open Database License (ODbL)**
   -  Esta licencia combina la exigencia de atribución con el concepto de "Compartir Igual" (Share Alike). Permite la reutilización de los datos siempre que se reconozca la autoría de la información original y se mantenga la misma licencia en las obras derivadas. Existe una condición adicional: de las obras derivadas se puede restringir su uso si, además, se distribuye una versión sin dichas restricciones
-  La diferencia clave entre estas tres licencias radica en el nivel de exigencia para el reutilizador, PDDL no requiere nada, Attribution exige solo reconocer la fuente, y ODbL exige reconocer la fuente y compartir las obras derivadas bajo la misma licencia

### Ejercicio 4

- **Procesamiento analítico en línea (OLAP)**
  - implica el examen de datos para buscar patrones o tendencias y generalmente utiliza consultas altamente complejas con una o más agregaciones. Las consultas de OLAP a menudo se denominan consultas de soporte a la decisión (decision-support queries). La funcionalidad típica de un almacén de datos (diseñado para OLAP) incluye:
    -  Roll-up: Resumir los datos mediante una generalización ascendente
    -  Drill-down: Dar a conocer los niveles de detalle ascendentes (complemento de Roll-up)
    -  Slice and Dice: Operaciones de proyección en las dimensiones
    -  Pivotaje: Tabulación cruzada o rotación
-  **Análisis espacial y geográfico (GIS)**
   -  Cuando los datos son geográficos o espaciales, se pueden utilizar Sistemas de Información Geográfica (GIS). Las consultas en GIS localizan objetos con características espaciales y el análisis espacial debe basarse en la topología entre objetos, lo que permite analizar relaciones de adyacencia, proximidad y conectividad
-  **Análisis de minería de datos**
   -  Extracción o descubrimiento de nueva información en términos de patrones o reglas a partir de grandes cantidades de datos. Sus objetivos incluyen:
      -  Clasificación: Aprendizaje de un modelo que describe diferentes clases de datos (por ejemplo, clasificar clientes como de bajo, medio o alto riesgo)
      -  Agrupamiento (Clustering): Segmentar elementos "similares" en conjuntos
      -  Reglas de Asociación y Patrones Secuenciales: Descubrir secuencias de acciones o eventos
-  **Análisis temporal (Series de tiempo)**
   -  Implica la búsqueda de patrones en series temporales, que son secuencias de datos tomados a intervalos regulares. Este análisis es una funcionalidad extendida de la gestión de datos temporales

### Ejercicio 5

- Condiciones de la licencia creative commons:
  - **Atribución:** Se debe dar crédito al autor original de la obra.
  - **No Comercial:** No se puede utilizar la obra con fines comerciales.
  - **Sin Obras Derivadas:** No se pueden hacer modificaciones o adaptaciones de la obra original.
  - **Compartir Igual:** Si se modifica la obra, la nueva obra debe tener la misma licencia que la original.

### Ejercicio 6

Tener datos IA-ready significa que estos datos cumplen una serie de requisitos técnicos, estructurales y de calidad que optimizan su aprovechamiento por parte de los algoritmos de inteligencia artificial. Esto incluye múltiples aspectos como:
- completitud de los datos
- ausencia de errores e inconsistencias
- uso de formatos adecuados, metadatos y estructuras homogéneas
- proporcionar el contexto necesario para poder verificar que estén alineados con el uso que la IA les dará.

### Ejercicio 7

Los principios FAIR-R son una extensión de los principios FAIR originales, y su principal enfoque es garantizar que los datos, especialmente aquellos utilizados en el ámbito científico y de la gobernanza, sean aptos para alimentar modelos de Inteligencia Artificial (IA) de manera efectiva y segura.Los principios originales FAIR (promovidos en el ámbito de la ciencia desde 2016) guían la publicación de datos para que sean Fáciles de Encontrar (Findable), Accesibles (Accessible), Interoperables (Interoperable) y Reutilizables (Reutilizable). La iniciativa FAIR-R no sustituye a los principios FAIR, sino que los complementa. Su objetivo general es evitar que los sistemas de IA se construyan sobre datos incompletos, sesgados o sin control ético.
- Para asegurar que los datos puedan alimentar los modelos de IA de manera efectiva y segura, la implementación de FAIR-R introduce nuevos requisitos que van más allá de los principios originales:
  -  **Etiquetado exhaustivo**: Se requiere un etiquetado completo de los datos
  -  **Documentación completa del origen de los datos**: Es necesaria una documentación detallada sobre la procedencia de la información
  -  **Homogeneidad de estándares y metadatos**: Se exige el uso de estándares uniformes y metadatos consistentes
  -  **Cobertura suficiente que evite sesgos**: El conjunto de datos debe tener un alcance adecuado para prevenir la introducción de sesgos o exclusiones
  -  **Licencias que regulen claramente su uso en IA**: Deben existir licencias específicas que determinen cómo pueden ser utilizados los datos en aplicaciones de Inteligencia Artificial