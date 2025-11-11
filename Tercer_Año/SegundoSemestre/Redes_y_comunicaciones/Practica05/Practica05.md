# Práctica 5 - Capa de transporte

### Ejercicio 1

Proporciona una comunicación lógica entre procesos de aplicación que se ejecutan en hosts diferentes. Desde la perspectiva de la aplicación, es como si los hosts estuvieran conectados directamente, sin que los procesos deban preocuparse por los detalles de la infraestructura física subyacente

### Ejercicio 2

- **Estructura del segmento UDP**:
    - **Puerto de origen (Source Port)**: identificador utilizado en la multiplexación/demultiplexación, y forma parte de la dirección de retorno si el host receptor desea enviar un segmento de vuelta
    - **Puerto de destino (Destination Port)**: es examinado por la capa de transporte del host receptor para dirigir (demultiplexar) los datos al socket apropiado, lo que permite la entrega de datos a la aplicación correcta
    - **Longitud (Length)**: especifica la longitud total del segmento UDP en bytes, incluyendo la cabecera
    - **Suma de comprobación (CheckSum)**: proporciona un mecanismo de detección de errores. El emisor calcula el complemento a 1 de la suma de todas las palabras de 16 bits del segmento y lo almacena en este campo. Si el receptor detecta un error, puede descartar el segmento. Este cálculo es opcional en UDP sobre IPv4 (UDP4)
    - **Datos de la aplicación (Data)**: contiene el mensaje o la carga útil de datos de la capa de aplicación
- **Estructura del segmento TCP**:
  - **Puerto de origen (Source Port number 16-bit)**: campo de 16 bits utilizados para la multiplexación/demultiplexación de datos a y desde las aplicaciones
  - **Puerto de destino (Destination Port number 16-bit)**: campo de 16 bits utilizados para la multiplexación/demultiplexación de datos a y desde las aplicaciones
  - **Numero de secuencia (Sequence number 32-bit)**: campo de 32 bits que indica el número del primer byte de datos del segmento dentro del flujo de bytes
  - **Número de reconocimiento (Acknowledgment number 32-bit)**: Campo de 32 bits que indica el número de secuencia del siguiente byte esperado por el receptor de la otra parte
  - **Longitud de la cabecera (Header Length 4-bit)**: campo de 4 bits que indica la longitud de la cabecera TCP
  - **Reservado (Reserved 6-bit)**: campo de 6 bits reservado para uso futuro y debe establecerse en cero
    - **ACK**: puesto en 1 si el campo de número de reconocimiento es válido
    - **URG**: indica que hay datos urgentes en el segmento
    - **PSH**: indica que se deben pasar los datos a la capa superior (aplicación) de forma inmediata
    - **RST, SYN, FIN**: bits utilizados para el establecimiento (SYN), reinicio (RST) y cierre (FIN) de la conexión TCP
  - **Tamaño de ventana (Window size 16-bit)**: se utiliza para el control de flujo e indica el número de bytes que el receptor está dispuesto a aceptar, garantizando que el emisor no sature el buffer de recepción del receptor
  - **Suma de comprobación (TCP Checksum 16-bit)**: campo de 16 bits que proporciona un mecanismo de detección de errores, a diferencia de UDP, es obligatorio en TCP
  - **Puntero de datos urgentes (Urgent Pointer 16-bit)**: se utiliza junto con el flag URG para indicar la posición donde finalizan los datos urgentes
  - **Opciones (Options (if any))**: campo de longitud variable que puede incluir, por ejemplo, la negociación del tamaño máximo de segmento
  - **Datos de la aplicación (Data (if any))**: campo de longitud variable que contiene un fragmento de datos de la aplicación, cuyo tamaño máximo está limitado por el MSS (Maximum Segment Size) negociado durante el establecimiento de la conexión

### Ejercicio 3

- **Identificación del proceso**: el host destino utiliza el número de puerto de destino en el segmento para dirigir los datos al socket correcto, que es la interfaz de software para el proceso de aplicación
- **Dirección de retorno**: el número de puerto de origen proporciona parte de la dirección de retorno, permitiendo al receptor enviar segmentos de respuesta al proceso correcto en el host emisor
- **Identificación de conexiones por protocolo**:
  - **UDP (Demultiplexación sin conexión)**: un socket UDP se identifica únicamente por la dirección IP de destino y el número de puerto de destino. Dos segmentos UDP con diferentes IPs o puertos de origen, pero el mismo destino, se dirigirán al mismo socket
  - **TCP (Demultiplexación orientada a conexión)**: un socket TCP se identifica por una cuádrupla de cuatro elementos:
    - (IP origen, puerto origen, IP destino, puerto destino)
    - El servidor utiliza estos cuatro valores para manejar múltiples conexiones simultáneas dirigidas al mismo puerto conocido (como el 80 para HTTP)

### Ejercicio 4

- **Confiabilidad**: 
  - **TCP**: Proporciona un servicio de flujo de bytes fiable y en orden, garantiza que los datos se entragan sin errores, sin pérdidas, sin duplicados y en el orden correcto. Implementa mecanismos de transferencia de datos fiable, incluyendo números de secuencia, mensajes de reconcoimiendo (ACK) y temporizadores, además, de el cálculo cheksum para la detección de errores es obligatorio
  - **UDP**: es un servicio de mejor esfuerzo, los segmentos pueden perderse corromperse o ser entregados fuera de orden a la aplicación. Solo proporciona un mecanismo de detección de errores a través de la suma de comprobación checksum, pero este es opcional sobre IPv4. UDP no hace nada para recuperarse de un error, simplemente descarta el segmento dañado
- **Multiplexación**: 
  - Ambos protocolos proporiconan la funcions de multiplexación/demultiplexación de procesos. Esta es una de las principales responsabilidades de la capa de transporte, utilizando los campos de número de puerto de origen y destino 
- **Orientación a conexión**:
  - **TCP**: requiere un saludo de tres vías (three-way handshake: SYN, SYN-ACK, ACK) antes del intercambio de datos para poder comenzar. Mantiene información de estado de la conexión en los sistemas terminales.
  - **UDP**: no requiere establecimiento de conexión, lo cual evita el retardo asociado a este proceso. No mantiene información del estado de la conexión
- **Controles de congestión**:
  - **TCP**: Utiliza un control terminal a terminal (end-to-end), ya que la capa IP subyacente no proporciona realimentación explícita sobre la congestión. Limita la velocidad de transmisión usando la Ventana de Congestión (CongWin o cwnd). El algoritmo de control de congestión utiliza las fases de arranque lento (slow start) y evitación de la congestión (congestion avoidance). TCP utiliza el mecanismo de Crecimiento Aditivo/Decremento Multiplicativo (AIMD)
  - **UDP**: No implementa control de congestión. Puede enviar datos a la velocidad que le parezca (el emisor puede disparar todo lo rápido que quiera). La falta de control de congestión en UDP puede dar lugar a que las fuentes no reguladas saturen la red (colapso de congestión) y reduzcan dramáticamente la tasa de transferencia de las sesiones TCP que sí cooperan
- **Utilización de puertos**:
  - **TCP**: Un socket TCP se identifica por una cuádrupla de cuatro elementos: (Dirección IP de origen, Puerto de origen, Dirección IP de destino, Puerto de destino), y el receptor usa los cuatro valores para dirigir el segmento al socket correcto. Esto permite que un servidor web (que usa el puerto 80) soporte múltiples conexiones simultáneas provenientes de diferentes clientes en el mismo puerto de destino, ya que cada conexión es distinguida por su cuádrupla única
  - **UDP**: Un socket UDP se identifica por una tupla que consta de la dirección IP de destino y el número de puerto de destino. Si dos segmentos UDP tienen diferente IP de origen o puerto de origen, pero el mismo destino (IP/Puerto), ambos se dirigen al mismo socket

### Ejercicio 5

Se utiliza el termino datagrama para referirse a la Unidad de Datos de Protocolo (PDU) en el contexto de UDP, que está orientado a paquetes o datagramas. Es importante notar que el termino datagrama para la PDU de UDP genera ambiguedad terminológica, ya que datagrama es el término reservado para la PDU de la capa de red (IP), que es la capa responsable de trasladar los paquetes de la capa de red, conocidos como datagramas, de un host a otro. No obstante, cuando se hace referencia a UDP, su naturaleza orientada a datagramas implica que su PDU sea comúnmente llamada datagrama

### Ejercicio 6

El saludo de tres vías (three-way handshake) es el mecanismo que utiliza TCP para establecer la conexión antes de un intercambio de datos. 
- **Paso 1: Cliente envía sincronización**
  - El cliente inicia la conexión enviando un segmento TCP especial conocido como segmento SYN (synchronize), que no contiene datos de la capa de aplicación. Uno de los bits indicadores de la cabecera del segmento, el bit SYN, se pone en 1, por esta razón este segmento especial se denomina segmento SYN. Además, el cliente selecciona un número de secuencia inicial aleatorio (cliente_nsi) y lo coloca en el campo de número de secuencia del segmento. Este segmento se encapsula dentro de un datagrama IP y se envía al servidor
- **Paso 2: Servidor envía sincronización y reconocimiento**
  - Al recibir el segmento SYN (suponiendo que llega), el servidor asigna buffers y variables TCP a la conexión. Luego, el servidor responde con un segmento de conexión concedida, conocido como SYNACK (synchronize-acknowledge). Este segmento de conexión concedida tampoco contiene datos de la capa de aplicación. Sin embargo, contiene tres fragmentos de información importantes de la cabecera del segmento. El primero, el bit SYN se pone en 1. El segundo, el campo reconocimiento de la cabecera del segmento TCP se hace igual a cliente_nsi+1. Por último, el servidor elige su propio número de secuencia inicial (servidor_nsi) y almacena este valor en el campo número de secuencia de la cabe-cera del segmento TCP. Este segmento de conexión concedida está diciendo, en efecto, “He recibido tu paquete SYN para iniciar una conexión con tu número de secuencia inicial, cliente_nsi. Estoy de acuerdo con establecer esta conexión. Mi número de secuencia inicial es servidor_nsi”.
- **Paso 3: Cliente envía reconocimiento**
  - Al recibir el segmento SYNACK, el cliente también asigna buffers y variables a la conexión. El host cliente envía entonces al servidor otro segmento. Este último segmento confirma el segmento de conexión concedida del servidor (el cliente hace esto almacenando el valor servidor_nsi+1 en el campo de reconocimiento de la cabecera del segmento TCP). El bit SYN se pone en cero, ya que la conexión está establecida. Esta tercera etapa del saludo de tres vías puede transportar datos del cliente al servidor dentro de la carga útil del segmento.

UDP no tiene un saludo de tres vías (three-way handshake), es un protocolo de transporte ligero simple que proporciona unos servicios mínimos y no está orientado a la conexión, por lo que no tiene lugar un procedimiento de negociación antes de que los dos procesos comiencen a comunicarse. UDP proporciona un servicio de transferencia de datos no fiable, es decir, cuando un proceso envía un mensaje a un socket UDP, el protocolo UDP no ofrece ninguna garantía de que el mensaje vaya a llegar al proceso receptor. Además, los mensajes que sí llegan al proceso receptor pueden hacerlo de manera desordenada

### Ejercicio 7

El ISN (Initial Sequence Number) es el valor con el que se inicia la numeración de bytes para el flujo de datos en una conexión TCP. TCP está diseñado para ver los datos como un flujo de bytes ordenado y no estructurado (stream of bytes), y el número de secuencia de un segmento es el número del primer byte del segmento dentro de dicho flujo. Ambos lados de una conexión TCP eligen aleatoriamente un número de secuencia inicial. Esto se hace con el fin de minimizar la posibilidad de que un segmento que todavía está presente en la red a causa de una conexión anterior que ya ha terminado entre dos hosts pueda ser confundido con un segmento válido de una conexión posterior entre esos dos mismos hosts.
- Relación con el saludo de tres vías (three-way handshake):
  - El ISN es un componente fundamental del saludo de tres vías de TCP, ya que este procedimiento es el encargado de inicializar las variables TCP, incluyendo los números de secuencia. El saludo de tres vías establece una conexión full-duplex (bidireccional) segura entre el cliente y el servidor, y cada parte debe seleccionar y comunicar su propio ISN para su flujo de datos particular

### Ejercicio 8

El MSS (Maximum Segment Size) define la máxima cantidad de datos de la capa de aplicación que puede ser colocada en un único segmento TCP. Se refiere solo a la carga útil de datos (bytes de la aplicación) y no incluye las cabeceras del segmento TCP o del datagrama IP que lo transporta.
La negociación del MSS entre el cliente y el servidor se lleva a cabo durante el establecimiento de la conexión TCP como parte del saludo de tres vías (three-way handshake), mediante el intercambio de información en el campo de Opciones de longitud variable dentro de los segmento iniciales. Cuando el cliente inicia la conexión enviando el segmento SYN, en este segmento, el cliente propone el valor del MSS que está dispuesto a aceptar para los segmentos que reciba del servidor. El servidor, al recibir el SYN, evalúa el MSS propuesto por el cliente y lo compara con el MTU (Maximum Transmission Unit) de la ruta disponible, y entonces responde con el segmento SYNACK, en el cual puede proponer su propio MSS para los segmentos que el enviará. El valor final del MSS utilizado para la conexión será el mínimo acordado por ambas partes, generalmente seleccionado para ser el MTU más pequeño de la ruta.
La importancia de acordar un MSS adecuado es evitar la fragmentación de datagramas, lo que contribuye a la transmisión de datos eficiente.

### Ejercicio 9
#### Utilizando `ss`:
- Listar las comunicaciones TCP establecidas
  ```bash
    redes@debian:~$ ss -t -s
    Total: 478
    TCP:   35 (estab 3, closed 25, orphaned 0, timewait 0)

    Transport Total     IP        IPv6
    RAW	  1         0         1        
    UDP	  7         5         2        
    TCP	  10        6         4        
    INET	  18        11        7        
    FRAG	  0         0         0        

    State  Recv-Q   Send-Q     Local Address:Port      Peer Address:Port   Process  
    ESTAB  0        0              10.0.2.15:47624      18.65.48.61:https           
    ESTAB  0        0             172.28.0.1:40764      172.28.0.90:imap2           
    ESTAB  0        0             172.28.0.1:40754      172.28.0.90:imap2 
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ ss -t -s -p
      Total: 479
      TCP:   34 (estab 2, closed 25, orphaned 0, timewait 0)

      Transport Total     IP        IPv6
      RAW	  1         0         1        
      UDP	  7         5         2        
      TCP	  9         5         4        
      INET	  17        10        7        
      FRAG	  0         0         0        

      State    Recv-Q    Send-Q       Local Address:Port        Peer Address:Port     Process                                                                         
      ESTAB    0         0               172.28.0.1:40764        172.28.0.90:imap2     users:(("thunderbird",pid=2756,fd=94))                                         
      ESTAB    0         0               172.28.0.1:40754        172.28.0.90:imap2     users:(("thunderbird",pid=2756,fd=93))
    ```
- Inciso b: listar las comunicaciones UDP establecidas
  ```bash
    redes@debian:~$ ss -u -s
    Total: 479
    TCP:   35 (estab 2, closed 26, orphaned 0, timewait 1)

    Transport Total     IP        IPv6
    RAW	  1         0         1        
    UDP	  7         5         2        
    TCP	  9         5         4        
    INET	  17        10        7        
    FRAG	  0         0         0        

    Recv-Q   Send-Q        Local Address:Port       Peer Address:Port     Process   
    0        0          10.0.2.15%enp0s3:bootpc         10.0.2.2:bootps 
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo ss -u -s -p
      Total: 485
      TCP:   35 (estab 2, closed 26, orphaned 0, timewait 1)

      Transport Total     IP        IPv6
      RAW	  1         0         1        
      UDP	  7         5         2        
      TCP	  9         5         4        
      INET	  17        10        7        
      FRAG	  0         0         0        

      Recv-Q Send-Q    Local Address:Port   Peer Address:Port   Process 
      0      0      10.0.2.15%enp0s3:bootpc     10.0.2.2:bootps  users:(("NetworkManager",pid=521,fd=23))
    ```
- Inciso c: obtener sólo los servicios TCP que están esperando comunicaciones
  ```bash
    redes@debian:~$ ss -t -l
    State   Recv-Q  Send-Q        Local Address:Port    Peer Address:Port  Process  
    LISTEN  0       128               127.0.0.1:ipp          0.0.0.0:*              
    LISTEN  0       5                 127.0.0.1:4038         0.0.0.0:*              
    LISTEN  0       128                 0.0.0.0:ssh          0.0.0.0:*              
    LISTEN  0       128                   [::1]:ipp             [::]:*              
    LISTEN  0       4096                  [::1]:50051           [::]:*              
    LISTEN  0       4096     [::ffff:127.0.0.1]:50051              *:*              
    LISTEN  0       128                    [::]:ssh             [::]:* 
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo ss -t -l -p
      State  Recv-Q Send-Q      Local Address:Port    Peer Address:Port Process                                                           
      LISTEN 0      128             127.0.0.1:ipp          0.0.0.0:*     users:(("cupsd",pid=606,fd=7))                                   
      LISTEN 0      5               127.0.0.1:4038         0.0.0.0:*     users:(("core-daemon",pid=605,fd=5))                             
      LISTEN 0      128               0.0.0.0:ssh          0.0.0.0:*     users:(("sshd",pid=632,fd=3))                                    
      LISTEN 0      128                 [::1]:ipp             [::]:*     users:(("cupsd",pid=606,fd=6))                                   
      LISTEN 0      4096                [::1]:50051           [::]:*     users:(("core-daemon",pid=605,fd=9))                             
      LISTEN 0      4096   [::ffff:127.0.0.1]:50051              *:*     users:(("core-daemon",pid=605,fd=10))                            
      LISTEN 0      128                  [::]:ssh             [::]:*     users:(("sshd",pid=632,fd=4))
    ```
- Inciso d: obtener sólo los servicios UDP que están esperando comunicaciones
  ```bash
    redes@debian:~$ ss -u -l
    State   Recv-Q   Send-Q     Local Address:Port      Peer Address:Port  Process  
    UNCONN  0        0                0.0.0.0:631            0.0.0.0:*              
    UNCONN  0        0                0.0.0.0:59119          0.0.0.0:*              
    UNCONN  0        0              127.0.0.1:4038           0.0.0.0:*              
    UNCONN  0        0                0.0.0.0:mdns           0.0.0.0:*              
    UNCONN  0        0                   [::]:48990             [::]:*              
    UNCONN  0        0                   [::]:mdns              [::]:*  
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo ss -u -l -p
      State       Recv-Q      Send-Q           Local Address:Port            Peer Address:Port     Process                                                                                      
      UNCONN      0           0                      0.0.0.0:631                  0.0.0.0:*         users:(("cups-browsed",pid=635,fd=7))                                                       
      UNCONN      0           0                      0.0.0.0:59119                0.0.0.0:*         users:(("avahi-daemon",pid=518,fd=14))                                                      
      UNCONN      0           0                    127.0.0.1:4038                 0.0.0.0:*         users:(("core-daemon",pid=605,fd=6))                                                        
      UNCONN      0           0                      0.0.0.0:mdns                 0.0.0.0:*         users:(("avahi-daemon",pid=518,fd=12))                                                      
      UNCONN      0           0                         [::]:48990                   [::]:*         users:(("avahi-daemon",pid=518,fd=15))                                                      
      UNCONN      0           0                         [::]:mdns                    [::]:*         users:(("avahi-daemon",pid=518,fd=13)) 
    ```
  - Notar que para ver los procesos asociados a las conexiones UDP es necesario usar `sudo`, ya que algunos procesos del sistema requieren privilegios elevados para ser visualizados y en algunas conexiones TCP también  
#### Utilizando `netstat`:
- Listar las comunicaciones TCP establecidas
  ```bash
    redes@debian:~$ netstat -a -t | grep ESTABLISHED
    tcp        0      0 172.28.0.1:40764        172.28.0.90:imap2       ESTABLISHED
  ```
  - NOTA: si no utilizamos `grep` para filtrar las conexiones establecidas, la salida es muy larga, ya que `netstat` muestra todas las conexiones activas, tanto establecidas como en otros estados (LISTEN, TIME_WAIT, etc.)
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo netstat -a -t -p | grep ESTABLISHED
      tcp        0      0 172.28.0.1:40764        172.28.0.90:imap2       ESTABLISHED 2756/thunderbird 
    ```
- Listar las comunicaciones UDP establecidas
  ```bash
    redes@debian:~$ netstat -a -u | grep ESTABLISHED
    udp        0      0 10.0.2.15:bootpc        10.0.2.2:bootps         ESTABLISHED
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo netstat -a -u -p | grep ESTABLISHED
      udp        0      0 10.0.2.15:bootpc        10.0.2.2:bootps         ESTABLISHED 521/NetworkManager 
    ```
- Obtener sólo los servicios TCP que están esperando comunicaciones
  ```bash
    redes@debian:~$ netstat -t -l
    Active Internet connections (only servers)
    Proto Recv-Q Send-Q Local Address           Foreign Address         State      
    tcp        0      0 localhost:ipp           0.0.0.0:*               LISTEN     
    tcp        0      0 localhost:4038          0.0.0.0:*               LISTEN     
    tcp        0      0 0.0.0.0:ssh             0.0.0.0:*               LISTEN     
    tcp6       0      0 localhost:ipp           [::]:*                  LISTEN     
    tcp6       0      0 localhost:50051         [::]:*                  LISTEN     
    tcp6       0      0 127.0.0.1:50051         [::]:*                  LISTEN     
    tcp6       0      0 [::]:ssh                [::]:*                  LISTEN 
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo netstat -t -l -p
      Active Internet connections (only servers)
      Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
      tcp        0      0 localhost:ipp           0.0.0.0:*               LISTEN      606/cupsd           
      tcp        0      0 localhost:4038          0.0.0.0:*               LISTEN      605/python          
      tcp        0      0 0.0.0.0:ssh             0.0.0.0:*               LISTEN      632/sshd: /usr/sbin 
      tcp6       0      0 localhost:ipp           [::]:*                  LISTEN      606/cupsd           
      tcp6       0      0 localhost:50051         [::]:*                  LISTEN      605/python          
      tcp6       0      0 127.0.0.1:50051         [::]:*                  LISTEN      605/python          
      tcp6       0      0 [::]:ssh                [::]:*                  LISTEN      632/sshd: /usr/sbin 
    ```
- Obtener sólo los servicios UDP que están esperando comunicaciones
  ```bash
    redes@debian:~$ netstat -u -l 
    Active Internet connections (only servers)
    Proto Recv-Q Send-Q Local Address           Foreign Address         State      
    udp        0      0 0.0.0.0:631             0.0.0.0:*                          
    udp        0      0 0.0.0.0:59119           0.0.0.0:*                          
    udp        0      0 localhost:4038          0.0.0.0:*                          
    udp        0      0 0.0.0.0:mdns            0.0.0.0:*                          
    udp6       0      0 [::]:48990              [::]:*                             
    udp6       0      0 [::]:mdns               [::]:* 
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo netstat -u -l -p
      Active Internet connections (only servers)
      Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
      udp        0      0 0.0.0.0:631             0.0.0.0:*                           635/cups-browsed    
      udp        0      0 0.0.0.0:59119           0.0.0.0:*                           518/avahi-daemon: r 
      udp        0      0 localhost:4038          0.0.0.0:*                           605/python          
      udp        0      0 0.0.0.0:mdns            0.0.0.0:*                           518/avahi-daemon: r 
      udp6       0      0 [::]:48990              [::]:*                              518/avahi-daemon: r 
      udp6       0      0 [::]:mdns               [::]:*                              518/avahi-daemon: r 
    ```

### Ejercicio 10

Si un host recibe un segmento TCP cuyo número de puerto de destino no se corresponde con ninguno de los sockets activos que están esperando una conexión, o si no hay un proceso en estado LISTEN en ese puerto, el host de destino enviará un segmento especial de reinicio. Este segmento de reinicio tendrá el bit indicador RST (Reset) puesto en 1. Al enviar un segmento RST, el host está comunicando al emisor: "No tengo un socket para ese segmento. Por favor, no reenvies el segmento"
- Inciso a
  ```bash
    redes@debian:~$ sudo hping3 -S -p 22 -c 5  127.0.0.1
    HPING 127.0.0.1 (lo 127.0.0.1): S set, 40 headers + 0 data bytes
    len=44 ip=127.0.0.1 ttl=64 DF id=0 sport=22 flags=SA seq=0 win=65495 rtt=8.1 ms
    len=44 ip=127.0.0.1 ttl=64 DF id=0 sport=22 flags=SA seq=1 win=65495 rtt=7.6 ms
    len=44 ip=127.0.0.1 ttl=64 DF id=0 sport=22 flags=SA seq=2 win=65495 rtt=5.8 ms
    len=44 ip=127.0.0.1 ttl=64 DF id=0 sport=22 flags=SA seq=3 win=65495 rtt=4.3 ms
    len=44 ip=127.0.0.1 ttl=64 DF id=0 sport=22 flags=SA seq=4 win=65495 rtt=12.6 ms

    --- 127.0.0.1 hping statistic ---
    5 packets transmitted, 5 packets received, 0% packet loss
    round-trip min/avg/max = 4.3/7.7/12.6 ms
  ```
  - `-S`: establece el bit SYN en el segmento TCP
  - `-p 22`: especifica el puerto de destino (22 es el puerto SSH, que está abierto en este caso)
  - `-c 5`: envía 5 paquetes (para no quedarnos enviando paquetes indefinidamente)
  - `127.0.0.1`: es la dirección IP de destino (en este caso, el localhost)
- Inciso b
  ```bash
    redes@debian:~$ sudo hping3 -S -p 40 -c 5  127.0.0.1
    HPING 127.0.0.1 (lo 127.0.0.1): S set, 40 headers + 0 data bytes
    len=40 ip=127.0.0.1 ttl=64 DF id=0 sport=40 flags=RA seq=0 win=0 rtt=7.6 ms
    len=40 ip=127.0.0.1 ttl=64 DF id=0 sport=40 flags=RA seq=1 win=0 rtt=2.7 ms
    len=40 ip=127.0.0.1 ttl=64 DF id=0 sport=40 flags=RA seq=2 win=0 rtt=9.4 ms
    len=40 ip=127.0.0.1 ttl=64 DF id=0 sport=40 flags=RA seq=3 win=0 rtt=9.7 ms
    len=40 ip=127.0.0.1 ttl=64 DF id=0 sport=40 flags=RA seq=4 win=0 rtt=3.7 ms

    --- 127.0.0.1 hping statistic ---
    5 packets transmitted, 5 packets received, 0% packet loss
    round-trip min/avg/max = 2.7/6.6/9.7 ms
  ```
- Inciso c
  - La diferencia radica en que en el primer caso, el puerto 22 está abierto y escuchando conexiones, por lo que el host responde con un segmento SYN-ACK (flags=SA) indicando que está dispuesto a establecer una conexión. En el segundo caso, el puerto 40 no está abierto ni tiene ningún proceso escuchando, por lo que el host responde con un segmento RST-ACK (flags=RA), indicando que no hay un socket disponible para manejar la conexión solicitada.
  - Esto podemos verificarlo con `ss` y agregando el parámetro `-n` para que resuelva los puertos numéricos y no intente traducirlos a nombres de servicios
    ```bash
      redes@debian:~$ sudo ss -t -l -p -n
      State      Recv-Q     Send-Q              Local Address:Port           Peer Address:Port     Process                                                                                      
      LISTEN     0          128                     127.0.0.1:631                 0.0.0.0:*         users:(("cupsd",pid=606,fd=7))                                                              
      LISTEN     0          5                       127.0.0.1:4038                0.0.0.0:*         users:(("core-daemon",pid=605,fd=5))                                                        
      LISTEN     0          128                       0.0.0.0:22                  0.0.0.0:*         users:(("sshd",pid=632,fd=3))                                                               
      LISTEN     0          128                         [::1]:631                    [::]:*         users:(("cupsd",pid=606,fd=6))                                                              
      LISTEN     0          4096                        [::1]:50051                  [::]:*         users:(("core-daemon",pid=605,fd=9))                                                        
      LISTEN     0          4096           [::ffff:127.0.0.1]:50051                     *:*         users:(("core-daemon",pid=605,fd=10))                                                       
      LISTEN     0          128                          [::]:22                     [::]:*         users:(("sshd",pid=632,fd=4)) 
    ```
  - Acá podemos ver que el puerto 22 (SSH) está en estado LISTEN, mientras que el puerto 40 no aparece en la lista, confirmando que no hay ningún proceso escuchando en ese puerto.

### Ejercicio 11
Si un host recibe un datagrama UDP en un puerto de destino para el cual no hay un socket UDP activo (es decir, no hay un proceso esperando), el host no puede entregar el datagrama a la capa de aplicación. En esta situación, el host envía un datagrama ICMP especial de vuelta al origen. Este mensaje ICMP es de "puerto de destino inalcanzable" (Destination Port Unreachable). Este mensaje es transportado como carga útil dentro de un datagrama IP. UDP se basa en ICMP (capa de red) para notificar la imposibilidad de entrega a nivel de proceso, a diferencia de TCP que utiliza el bit RST de su propia capa de transporte para el rechazo
- Inciso a
  ```bash
    redes@debian:~$ sudo hping3 -2 -p 5353 127.0.0.1
    HPING 127.0.0.1 (lo 127.0.0.1): udp mode set, 28 headers + 0 data bytes
    ^C
    --- 127.0.0.1 hping statistic ---
    3 packets transmitted, 0 packets received, 100% packet loss
    round-trip min/avg/max = 0.0/0.0/0.0 ms
  ```
- Inciso b
  ```bash
    redes@debian:~$ sudo hping3 -2 -p 40 -c 5 127.0.0.1
    HPING 127.0.0.1 (lo 127.0.0.1): udp mode set, 28 headers + 0 data bytes
    ICMP Port Unreachable from ip=127.0.0.1 name=localhost 
    status=0 port=2713 seq=0
    ICMP Port Unreachable from ip=127.0.0.1 name=localhost 
    status=0 port=2714 seq=1
    ICMP Port Unreachable from ip=127.0.0.1 name=localhost 
    status=0 port=2715 seq=2
    ICMP Port Unreachable from ip=127.0.0.1 name=localhost 
    status=0 port=2716 seq=3
    ICMP Port Unreachable from ip=127.0.0.1 name=localhost 
    status=0 port=2717 seq=4

    --- 127.0.0.1 hping statistic ---
    5 packets transmitted, 5 packets received, 0% packet loss
    round-trip min/avg/max = 1.8/3.5/4.7 ms
  ```
- Inciso c
  ```bash
    redes@debian:~$ sudo ss -u -l -p -n
    State       Recv-Q      Send-Q           Local Address:Port            Peer Address:Port     Process                                                                                      
    UNCONN      0           0                      0.0.0.0:631                  0.0.0.0:*         users:(("cups-browsed",pid=635,fd=7))                                                       
    UNCONN      0           0                      0.0.0.0:59119                0.0.0.0:*         users:(("avahi-daemon",pid=518,fd=14))                                                      
    UNCONN      0           0                    127.0.0.1:4038                 0.0.0.0:*         users:(("core-daemon",pid=605,fd=6))                                                        
    UNCONN      0           0                      0.0.0.0:5353                 0.0.0.0:*         users:(("avahi-daemon",pid=518,fd=12))                                                      
    UNCONN      0           0                         [::]:48990                   [::]:*         users:(("avahi-daemon",pid=518,fd=15))                                                      
    UNCONN      0           0                         [::]:5353                    [::]:*         users:(("avahi-daemon",pid=518,fd=13))  
  ```
  El puerto 5353 UDP sí está en uso por el proceso avahi-daemon, que implementa el protocolo mDNS (Multicast DNS). Este servicio no responde directamente a paquetes unicast enviados a 127.0.0.1, ya que trabaja por multicast (224.0.0.251). Por eso, hping3 no recibe respuesta, pero el paquete no es rechazado, simplemente es ignorado (UDP no establece conexión). El datagrama llega a un puerto donde sí hay un proceso escuchando (avahi-daemon), pero como no es un paquete multicast válido, no genera respuesta. En cambio, el puerto 40 UDP no está en uso (no hay ningún proceso en ese puerto). Cuando llega un datagrama UDP a un puerto sin proceso escuchando, el sistema operativo responde con un mensaje ICMP "Port Unreachable". `hping3` interpreta ese mensaje ICMP y muestra una respuesta

### Ejercicio 12

- **CLOSED**
  - Representa que no existe ningún estado de conexión. El socket aún no ha sido abierto o ya ha sido cerrado.
  - Es un estado ficticio, ya que representa el estado donde no hay un TCB (Transmission Control Block, estructura de datos que el sistema operativo usa para rastrear y gestionar cada conexión TCP) asignado, por lo tanto, no hay conexión. 
- **LISTEN**
  - Un socket está a la espera de conexión entrante (servicio escuchando). Sólo aplica en el lado servidor que acepta conexiones (por ejemplo, sshd).
- **SYN-SENT**
  - Estado intermedio del cliente cuando envía un SYN para iniciar la conexión y espera respuesta (SYN+ACK). El cliente está “intentando conectarse”.
- **SYN-RECEIVED**
  - Estado que ocurre en el lado servidor cuando recibe un SYN, envía un SYN+ACK, y espera el ACK final del cliente. También puede darse en el cliente si hay una conexión simultánea (ambos inician al mismo tiempo).
- **ESTABLISHED**
  - La conexión ya fue completamente establecida (saludo de tres vías completado). Los datos pueden fluir en ambas direcciones.
- **FIN-WAIT-1**
  - Un extremo ha comenzado el cierre enviando un FIN, y espera un ACK o un FIN del otro lado.
- **FIN-WAIT-2**
  - Tras recibir el ACK del otro extremo luego de enviar FIN, espera el FIN del otro lado.
- **CLOSE-WAIT**
  - Estado que representa la espera de un pedido de cierre de conexión del lado del usuario local, después de haber recibido un FIN del otro extremo. 
- **CLOSING**
  - Estado que representa la espera de un ACK del lado del TCP remoto, luego de enviar un FIN
- **LAST-ACK**
  - El que ya envió su FIN espera el ACK final después de haber recibido y atendido el FIN del otro.
- **TIME-WAIT**
  - Representa el tiempo suficiente que debe pasar para asegurar que el TCP remoto recibió el ACK final de cierre. Sólo un lado entra en TIME-WAIT típicamente.

### Ejercicio 13
- Inciso a
  - Se encuentran establecidas las siguientes 9 conexiones:
    ```bash
      ...
      tcp ESTAB 0 0 163.10.5.222:59736 64.233.163.120:443 users:(("x-www-browser",pid=1079,fd=51))
      ...
      tcp ESTAB 0 0 163.10.5.222:59737 64.233.163.120:443 users:(("x-www-browser",pid=1079,fd=55))
      tcp ESTAB 0 0 163.10.5.222:33583 200.115.89.15:443 users:(("x-www-browser",pid=1079,fd=53))
      tcp ESTAB 0 0 163.10.5.222:45293 64.233.190.99:443 users:(("x-www-browser",pid=1079,fd=59))
      ...
      tcp ESTAB 0 0 127.0.0.1:22 127.0.0.1:41220 users:(("sshd",pid=1418,fd=3),("sshd",pid=1416,fd=3))
      tcp ESTAB 0 0 163.10.5.222:52952 64.233.190.94:443 users:(("x-www-browser",pid=1079,fd=29))
      ...
      tcp ESTAB 0 0 163.10.5.222:52960 64.233.190.94:443 users:(("x-www-browser",pid=1079,fd=67))
      tcp ESTAB 0 0 163.10.5.222:50521 200.115.89.57:443 users:(("x-www-browser",pid=1079,fd=69))
      ...
      tcp ESTAB 0 0 127.0.0.1:41220 127.0.0.1:22 users:(("ssh",pid=1415,fd=3))
      ...
    ```
- Inciso b
  - Se encuentran abiertos a la espera de posibles nuevas conexiones los siguientes 5 puertos:
    ```bash
      tcp LISTEN 0 128 *:22 *:* users:(("sshd",pid=468,fd=29))
      tcp LISTEN 0 128 *:80 *:* users:(("apache2",pid=991,fd=95))
      udp LISTEN 0 128 163.10.5.222:53 *:* users:(("named",pid=452,fd=10))
      ...
      tcp LISTEN 0 128 *:25 *:* users:(("postfix",pid=627,fd=3))
      ...
      udp LISTEN 0 128 127.0.0.1:53 *:* users:(("named",pid=452,fd=9))
    ```
- Inciso c
  - El cliente y el servidor de las comunicaciones HTTPS (puerto 443) no residen en la misma máquina, ya que vemos que para todas las conexiones establecidas en el puerto 443, la IP local es diferente a la IP remota.
- Inciso d
  - EL cliente y el servidor de las comunicaciones SSH (puerto 22) residen en la misma máquina, ya que vemos que para la conexión establecida en el puerto 22, la IP local es igual a la IP remota (127.0.0.1).
- Inciso e
  |Proceso|Rol|
  |-------|---|
  |sshd|Servidor|
  |apache2|Servidor|
  |named|Servidor|
  |x-www-browser|Cliente|
  |ssh|Cliente|
  |postfix|Servidor|
- Inciso f
  - Las conexiones que tuvieron el cierre iniciado por el host local son:
    - `tcp TIME-WAIT 0 0 163.10.5.222:36676 54.149.207.17:443 users:(("x-www-browser",pid=1079,fd=3))`, ya que esta en estado TIME-WAIT, que indica que el cierre fue iniciado por el host local.
  - Las conexiones que tuvieron el cierre iniciado por el host remoto son:
    - `tcp CLOSE-WAIT 0 0 163.10.5.222:41654 200.115.89.30:443 users:(("x-www-browser",pid=1079,fd=50))`, ya que esta en estado CLOSE-WAIT, que indica que el cierre fue iniciado por el host remoto.
- Inciso g
  - Las conexiones que aún están pendientes por establecerse son:
    - `tcp SYN-SENT 0 0 163.10.5.222:52132 43.232.2.2:9500 users:(("x-www-browser",pid=1079,fd=70))`, ya que esta en estado SYN-SENT, que indica que el host local envió un SYN y está esperando el SYN-ACK del host remoto.

### Ejercicio 14
- Inciso a 
  - Los segmentos que llegaron son:
    - SYN del cliente al servidor (se puede observaar en SYN-SENT del cliente y SYN-RECV del servidor)
  - Los segmentos que no llegaron o están perdidos en la red son:
    - SYN-ACK del servidor no llegó al cliente (por eso el cliente sigue en SYN-SENT esperando el SYN-ACK)
- Inciso b
  - El cliente se esta intentando conectar al protocolo de la capa de aplicación POP3 (el puerto 110 es el estándar de POP3 para correo electrónico), y al protocolo de la capa de transporte TCP (se puede observar por tcp SYN-SENT).
- Inciso c
  - El segmento perdido tendría que haber seteado las flags SYN en 1 y ACK en 1 (SYN-ACK), ya que es la respuesta del servidor al SYN del cliente. 

### Ejercicio 15