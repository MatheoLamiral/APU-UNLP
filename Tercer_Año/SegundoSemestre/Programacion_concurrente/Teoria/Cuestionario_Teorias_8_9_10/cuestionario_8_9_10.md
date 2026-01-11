# Resolución del cuestionario teórico de las teorías 8, 9 y 10

### Ejercicio 1

- Los 7 paradigmas de interacción entre procesos vistos en teoría son:
  - **Master/Worker**
    - Implementación distribuida del modelo Bag of Tasks, donde un proceso maestro (Master) coordina y distribuye datos o tareas a múltiples procesos trabajadores (Workers), es decir, implementa la bolsa, y recibe los resultados de estos. Por su parte los trabajadores, tomarán tareas de la "bolsa" y las ejecutan. Este esquema es una variación del modelo Cliente/Servidor
    - La comunicación por mensajes más conveniente es RPC o Rendezvous, ya que son técnicas con un canales bidireccionales, ideales para soluciones Cliente/Servidor, donde el llamador (worker en este caso) se demora hasta que el servicio (tarea/datos) es completado y los resultados son devueltos por el servidor (Master)
    - La arquitectura que mejor se ajusta es la de memoria distribuída, ya que permite distribuír la carga de trabajo entre nodos independientes para lograr un mejor balance de carga.
  - **Heartbeat**
    - Se distribuye la carga entre los workers y cada uno es responsable de actualizar una parte, los nuevos valores dependen de los mantenidos por los workers o sus vecinos inmediatos, por lo que los procesos periódicamente intercambian informació mediante send/receive.
    - La comunicación por mensajes asincrónicos es la más conveniente, ya que aumenta la concurrencia al permitir que los procesos envíen valores y continúen su ejecución sin bloquearse. 
    - La arquitectura que mejor se ajusta es la de memoria distribuída, ya que la topología debe reflejar la interacción local de vencindad requerida por el algoritmo 
  - **Pipeline**
    - Arreglo lineal de procesos filtro que reciben datos de un canal de entrada y entregan resultados en un canal de salida.
    - La comunicación por mensajes asincrónicos es la más conveniente, al permitir que los procesos filtros operen a su propia velocidad (desacoplamiento), maximizando el rendimiento del pipeline.
    - La arquitectura que mejor se ajusta es la de memoria distribuída, ya que ...
  - **Probes y echoes**
    - Es el análogo concurrente del algoritmo de búsqueda en profundidad (DFS). La interacción permite recorrer grafos o árboles y se basa en el envío de mensajes ("Probe") de un nodo al sucesor, y la espera posterior de un mensaje de respuesta ("Echo"). Es útil en casos, como por ejemplo, recorrer rededes donde el número de nodos activos no es fijo 
    - La comunicación por mensajes más conveniente es Rendezvous, ya que garantiza que el probe sea atendido y se genere echo antes de que el proceso continúe, facilitando el recorrido controlado del grafo/árbol
    - La arquitectura que mejor se ajusta es la de memoria distribuída con topología de Árbol o Grafo, ya que el paradígma está ligado al recorrido de estructuras jerárquicas o de grafo
  - **Broadcast**
    - Se utiuliza la primitiva de broadcast para difundir información de manera global y simultánea a múltiples procesos en una arquitectura distribuída 
    - La comunicación por mensajes asincrónicos es la más conveniente, al permitir que el proceso que desea difundir la información puede continuar su ejecución luego de enviár el mensaje, sin esperar a la recepción de los mismos 
    - La arquitectura que mejor se ajusta es la de memoria distribuída con buses o redes completamente conectadas, ya que requiere una red que soporte eficientemente el envío de un mensaje de un solo origen a todos los destinos
  - **Token passing**
    - Se basa en un tipo especial de mensaje, en el que se pasa un "token" que puede usarse para otorgar control o permisos. Es la solución principal para la exclusión mutua distribuída.
    - La comunicación por pasaje de mensajes sincrónico es la más conveniente, ya que, como el token representa un permiso, la comunicación de su transferencia debe ser bloqueante para asegurar que el emisor no continúe hasta que el receptor haya recibido el mensaje
    - La arquitectura que mejor se ajusta es la de memoria distribuída con topología de red de anillo, ya que es fundamental para garantizar la circulación ordenada y la fairness del token entre los procesos 
  - **Servidores replicados** 
    - Se usan múltiples instancias de un servidor para manejar recursos compartidos, lo que puede proporcionar a los clientes la percepción de un único recurso 
    - La comunicación por mensajes más conveniente es RPC o Rendezvous, ya que gestionan intrinsecamente la sincronización y comunicación bidireccional necesaria para solicitar y devolver recursos 
    - La arquitectura que mejor se ajusta es la de memoria distribuída, ya que la replicación implicaría distribuír las instancias del recurso en nodos físicamente separados para obtener escalabilidad, tolerancia a fallos o simular un recurso único

### Ejercicio 2

La "bag" es una estructura de datos compartida que almacena las tareas disponibles para su ejecución. Los procesos Master depositarán tareas en la bolsa y los procesos Workers, accederán a la bolsa para tomar una tarea, ejecutarla y, potencialmente, generar nuevas subtareas que se reintroducen en la bolsa.
- Sus principales ventajas son:
  - Fácil implementación y definición
  - Balanceo de carga natural 
  - Escalabilidad
  - Tolerancia a la heterogeneidad de velocidades o tiempos de ejecución

### Ejercicio 3

### Ejercicio 4

### Ejercicio 5

- Las características de Pthreads son:
  - Modelo de programación en threads (hilos)
    - Un "hilo" es conceptualmente un proceso "liviano", es decir, contiene su propio contador de programa y pila de ejecución, pero no controla contexto "pesado", como las tablas de página 
    - Todos los hilos de un mismo procesos comparten el mismo espacio de direcciones y recursos, lo que significa que la comunicación es implícita entre ellos 
  - Funciones de control y estructura 
    - Proporciona las funciones básicas para la gestión del ciclo de vida de hilos 
    - `pthread_create` crea un nuevo hilo y especificar la funcion que debe ejecutrar el mismo. Es posible asignarle atributos al hilo antes de su creación utilizando un attribute object `pthread_attr_t`
    - Los hilos pueden terminar su propia ejecucíon utilizando `pthread_exit`. `pthread_join` se usa en un hilo padre o invocador para bloquear su ejecución hasta que un hilo especificado termine
    - Primitivas de sincronización de bajo nivel para implementar exclusión mutua y sincronización por condición 

### Ejercicio 6

#### Sincronización por exclusión mutua en Pthreads

La exclusión mutua es la propiedad de seguridad que garantiza que a lo sumo un proceso esté ejecutando en una sección crítica (SC) en un instante dado, previniendo el acceso concurrente a recursos o variables compartidas. En Pthreads, esto se implementa utilizando **Mutex Locks** (bloqueos por exclusión mutua), que son variables del tipo `pthread_mutex_t`. 
- Una variable mutex, puede tiene dos estados, bloqueado (locked) y desbloqueado (unlocked) y toddos los mutex deben inicializarse como desbloqueados. 
- Para entrar a una sección crítica, un thread debe obtener el control del mutex llamando a `pthread_mutex_lock(&mutex)`. Si el mutex ya está bloqueado por otro thread, el invocador es bloqueado. Adicionalmente, Pthreads ofrece `pthread_mutex_trylock`, esta función intenta realizar el bloqueo y retorna inmediatamente informando si tuvo éxito o no, sin bloquear el thread en caso de fallo (retorna EBUSY). Esto se utiliza para reducir el overhead por espera ociosa (busy waiting o spinning)
- Al salir de la sección crítica, el thread debe liberar el mutex llamando a `pthread_mutex_unlock(&mutex)`
- Soporta tres tipos de mutex que se configuran con atributos (`pthtread_mutexattr_t`), Normal, Recursive y Error Check

#### Sincronización por condición en Pthreads

Una variable de condición siempre está asociada a un mutex. El thread debe bloquear el mutex asociado antes de testear el predicado. Si el predicado es falso, el thread llama a `pthread_cond_wait(&cond, &mutex)`. Esta llamada realiza dos acciones cruciales de forma atómica, bloquea la ejecución del thread y libera el mutex para que otro thread potencialmente pueda hacer que la condicion cambie a verdadero. Cuando otro thread (que ha cambiado el estado de las variables compartidas) desea notificar a los threads en espera, utiliza `pthread_cond_signal(&cond)` o `pthread_cond_broadcast(&cond)`. signal despierta a un solo thread de la cola y broadcast despierta a todos los threads que están en espera. El thread despertado debe esperar a readquirir el mutex antes de reanudar la ejecución.

#### Relación entre exclusión mutua y sincronización por condición

 La Exclusión Mutua (Mutex Locks) se ocupa de la seguridad (safety), garantizando que las secciones críticas no se ejecuten al mismo tiempo para evitar interferencias y estados inconsistentes. La Sincronización por Condición (Variables de Condición) se ocupa de la vida (liveness), forzando a un thread a esperar hasta que se cumpla una condición específica para que el programa pueda progresar. Las variables de condición en Pthreads dependen intrínsecamente de los mutex. Para que `pthread_cond_wait` pueda funcionar, debe existir un mutex que se pueda liberar. De lo contrario, ningún otro thread podría acceder para cambiar la condición que permitiría continuar al thread en espera.


#### Simulación de monitores con Pthreads

Un Monitor es un mecanismo de abstracción de datos de alto nivel que encapsula la representación de un recurso compartido (variables permanentes) junto con un conjunto de procedimientos para manipularlo. Los monitores proporcionan Exclusión Mutua implícita (solo un proceso puede ejecutar un procedimiento del monitor a la vez) y Sincronización por Condición explícita (mediante variables de condición). Dado que Pthreads no implementa el concepto de Monitor directamente, su funcionalidad se simula utilizando las primitivas de sincronización disponibles:
- La exclusión mutua implícita del monitor se simula utilizando una variable mutex
- Cada llamada a un procedimiento del monitor debe ser envuelta en un bloque de código que primero bloquea el mutex (`pthread_mutex_lock`) y lo desbloquea (`pthread_mutex_unlock`) antes de retornar
-  Las variables de condición del monitor se simulan con Variables de Condición Pthreads (`pthread_cond_t`), cada una asociada al mutex global del monitor
-  La operación `wait(cv)` del monitor se traduce a `pthread_cond_wait(&cv, &mutex)`, lo que asegura la liberación del mutex mientras el thread espera
-  La operación `signal(cv)` o `signal_all(cv)` del monitor se traduce directamente a `pthread_cond_signal(&cv)` o `pthread_cond_broadcast(&cv)`, respectivamente  
  
Un monitor implementado en Pthreads se comporta como si la ejecución del cuerpo del procedure se dividiera en tres fases gestionadas por el mutex: adquisición del mutex, ejecución del cuerpo del procedure, y liberación del mutex

### Ejericico 7

La comunicación entre procesos que utilizan el paradigma de Pasaje de Mensajes (PM) se basa en las operaciones fundamentales de envío (`send`) y recepción (`receive`)
- Operaciones **Bloqueantes** y **No Bloqueantes**:
  - Esta clasificación depende de si la primitiva de envío (`send`) o recepción (`receive`) devuelve el control al programa antes de que la operación se complete semánticamente
- Uso de **Buffering** y **No Buffering**:
  - Esta clasificación describe cómo la implementación maneja temporalmente los datos transferidos, especialmente en el lado del envío, afectando la sincronización entre el emisor y el receptor. 
  
    |Tipo de Operación|Bloqueante|No Bloqueante|
    |----|----|----|
    |**Con Buffering**|El envío retorna tras la copia al buffer del sistema|El envío inicia la transferencia DMA al buffer y retorna inmediatamente (permite overlap)|
    |**Sin Buffering**|El envío espera hasta que el receive correspondiente ha sido encontrado (implica handshake)|No es común, ya que la responsabilidad de la semántica es alta|

- Relación con PMA y PMS
  - PMA 
    - La primitiva `send` en PMA se considera **no bloqueante**. Los canales asociados se modelan como colas de mensajes ("ilimitadas" o con buffering). El `send` retorna inmediatamente, ya que los mensajes se encolan en el canal y el emisor puede continuar su ejecución. La cola "ilimitada" garantiza que la ejecución de `send` nunca causa demora. La primitiva `receive` sigue siendo bloqueante.
  - PMS
    - La primitiva `send` en PMS es **bloqueante**. La implementación típica no utiliza buffering para el mensaje en el lado del emisor, esperando la recepción del destinatario. El transmisor queda esperando hasta que el mensaje es recibido por el receptor. Esto significa que la cola de mensajes del canal asociado se reduce a un solo mensaje, lo cual limita la concurrencia pero asegura un tamaño acotado de los canales de comunicación

### Ejercicio 8 

