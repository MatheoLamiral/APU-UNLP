# Práctica 5 - Capa de transporte

### Ejercicio 1

La función principal de la capa de transporte es proporcionar **comunicación lógica entre procesos de aplicación en hosts diferentes**. Se encarga de la **segmentación** y **reensamblado** de los datos en **PDUs** (Protocol Data Units) llamados **segmentos** en TCP o **datagramas** en UDP. Además, realiza la **multiplexación** y **demultiplexación** mediante puertos para asegurar que la información llegue a la aplicación correcta.

### Ejercicio 2

#### Estructura del segmento UDP

- **Puerto de origen (Source Port, 16 bits) y destino (Destination Port, 16 bits)**: Permiten la multiplexación y demultiplexación de datos, dirigiendo los datos a la aplicación correcta
- **Longitud (Length, 16 bits)**: indica la longitud total del segmento UDP, incluyendo encabezados y datos, en bytes
- **Suma de comprobación (Checksum, 16 bits)**: Mecanimso de detección de errores (complemento a 1). En UDP sobre IPv4 es opcional, mientras que en UDP sobre IPv6 es obligatorio

#### Estructura del segmento TCP

- **Puertos Origen (Source Port, 16 bits) y destino (Destination Port, 16 bits)**: Igual que en UDP, permiten la multiplexación y demultiplexación de datos para identificar las aplicaciones
- **Número de secuencia (Sequence Number, 32 bits)**: Indica el número del primer byte de datos en el segmento
- **Número de reconocimiento (Acknowledgment Number, 32 bits)**: Indica el siguiente byte que el receptor espera recibir (confirmación acumulativa)
- **Longitud de la cabecera (Header Length, 4 bits)**: Indica el tamaño del encabezado en palabras de 32 bits
- **Reservado (Reserved, 6 bits)**: Bits en 0 reservados para uso futuro
- **Flags (6 bits de control)**:
  - `SYN/FIN`: inciar/finalizar conexión. Ambos incrementan el número de secuencia en 1
  - `ACK`: valida el campo de número de reconocimiento
  - `RST`: reinicio forzado de la conexión
  - `PSH/URG`: Empuje de datos/Datos urgentes
  - `ECE/CWR`: control de congestión explícito
- **Tamaño de ventana (Window Size, 16 bits)**: Indica cuántos bytes está dispuesto a aceptar el receptor (control de flujo)
- **Suma de comprobación (Checksum, 16 bits)**: Detección de errores (complemento a 1). Obligatorio en TCP para integridad de datos.
- **Puntero de datos urgentes (Urgent Pointer, 16 bits)**: Indica la posición del último byte urgente si el flag URG está activo
- **Opciones (Options, variable)**: Permite funcionalidades adicionales como la negociación del tamaño máximo de segmento (MSS, Maximum Segment Size)

### Ejercicio 3

Los puertos son esenciales en el modelo TCP/IP, ya que permiten la multiplexación y demultiplexación de datos. Esto posibilita la comunicación simultánea entre múltiples aplicaciones en un mismo host, actuando como puntos finales lógicos de comunicación. Cada puerto se asocia con un proceso específico, permitiendo que el sistema operativo dirija los segmentos entrantes a la aplicación correcta.

> [!note]
> En sí, un puerto es un número de 16 bits comprendido en el rango de 0 a 65535. Los puertos del 0 al 1023 son puertos bien conocidos (well-known ports) y están reservados para servicios estándar (como HTTP en el puerto 80, HTTPS en el puerto 443, FTP en el puerto 21, etc.), los puertos del 1024 al 49151 son puertos asignados a aplicaciones de usuario específicas, y los del 49152 al 65535 son puertos dinámicos o privados. Al desarrollar una nueva applicación, es necesario asignarle un número de puerto que no esté ya en uso por otro servicio o aplicación

> [!important]
> Un **socket TCP se identifica** por una **cuádrupla** de cuatro elementos: **(Dirección IP de origen, Puerto de origen, Dirección IP de destino, Puerto de destino)**, y el receptor usa los cuatro valores para dirigir el segmento al socket correcto. Esto permite, por ejemplo, que un servidor web (que usa el puerto 80) soporte múltiples conexiones simultáneas provenientes de diferentes clientes en el mismo puerto de destino, ya que cada conexión es distinguida por su cuádrupla única

### Ejercicio 4

#### Confiabilidad 

- **TCP**: es un protocolo confiable, garantiza que los datos se entregan sin errores, sin pérdidas, sin duplicados y en el orden correcto. Implementa mecanismos de transferencia de datos fiable, incluyendo números de secuencia, mensajes de reconocimiento (ACK) y temporizadores, además del cálculo checksum para la detección de errores.
- **UDP**: es un servicio de mejor esfuerzo, los segmentos pueden perderse, corromperse o ser entregados fuera de orden a la aplicación. Solo proporciona un mecanismo de detección de errores a través de la suma de comprobación checksum, pero este es opcional sobre IPv4 (Obligatorio en IPv6). UDP no hace nada para recuperarse de un error, simplemente descarta el segmento dañado

#### Multiplexación 

- Ambos protocolos proporcionan la función de multiplexación/demultiplexación de procesos. Esta es una de las principales responsabilidades de la capa de transporte, permitiendo que múltiples aplicaciones en un mismo host puedan comunicarse simultáneamente a través de la red utilizando puertos para identificar cada aplicación

#### Orientación a conexión

- **TCP**: requiere un saludo de tres vías (three-way handshake) antes del intercambio de datos para poder comenzar, lo que genera más overhead que en UDP. Además, mantiene información de estado de la conexión en los sistemas terminales.
- **UDP**: no requiere establecimiento de conexión, lo cual evita el retardo asociado a este proceso y no mantiene información del estado de la conexión

#### Controles de congestión

- **TCP**: Implementa el mecanismo para el control de congestión, que permite que las aplicaciones no saturen la capacidad de la red y tiene como objetivo controlar el tráfico (cantidad de datos que un emisor inyecta en la red) para evitar la congestión, y por ende, la pérdida de paquetes o retransmisiones de los mismos. Esto se logra ajustando dinámicamente el tamaño de la ventana de envío (ventana de congestión) según el estado de la red
- **UDP**: No implementa control de congestión. Puede enviar datos a la velocidad que le parezca (el emisor puede disparar todo lo rápido que quiera). La falta de control de congestión en UDP puede dar lugar a que los emisores saturen la red (colapso de congestión) y reduzcan dramáticamente la tasa de transferencia de las sesiones TCP que sí cooperan

#### Utilización de puertos

- **TCP**: Un **socket TCP** se identifica por una **cuádrupla** que consta de **(Dirección IP de origen, Puerto de origen, Dirección IP de destino, Puerto de destino)**, y el receptor usa los cuatro valores para dirigir el segmento al socket correcto. Esto permite que, por ejemplo, un servidor web (que usa el puerto 80) soporte múltiples conexiones simultáneas provenientes de diferentes clientes en el mismo puerto de destino, ya que cada conexión es distinguida por su cuádrupla única
- **UDP**: Un **socket UDP** se identifica por una **tupla** que consta de **(dirección IP de destino, Puerto de destino)**. Si dos segmentos UDP tienen diferente IP de origen o puerto de origen, pero el mismo destino (IP/Puerto), ambos se dirigen al mismo socket

### Ejercicio 5

Se utiliza el termino **datagrama** para referirse a la **Unidad de Datos de Protocolo (PDU) en el contexto de UDP**, que está orientado a paquetes o datagramas. 

>[!note]
> Es importante notar que el termino datagrama para la PDU de UDP genera ambiguedad terminológica, ya que datagrama es el término reservado para la PDU de la capa de red (IP). No obstante, cuando se hace referencia a UDP, su naturaleza orientada a datagramas implica que su PDU sea comúnmente llamada datagrama

### Ejercicio 6

El saludo de tres vías (three-way handshake) es el mecanismo que utiliza TCP para establecer la conexión antes de un intercambio de datos. 
1. **SYN**: El cliente elige un número de secuencia inicial aleatorio (client_isn) y envía un segmento con el bit SYN=1 al servidor. Este segmento no contiene datos de capa de aplicación.
2. **SYN-ACK**: El servidor recibe el SYN, asigna buffers y variables a la conexión, y responde con un segmento con los bits SYN=1 y ACK=1. El servidor confirma el número del cliente (ACK = client_isn + 1) y elige su propio número de secuencia inicial (server_isn). Tampoco contiene datos de aplicación.
3. **ACK**: El cliente recibe el SYNACK, asigna sus propios buffers y variables, y envía un segmento de confirmación con el bit ACK=1 (donde ACK = server_isn + 1). Este segmento sí puede transportar datos de la capa de aplicación hacia el servidor.

**UDP** es un protocolo no orientado a conexión. **No realiza ningún tipo de negociación previa** (handshake) ni mantiene estado de la conexión. El emisor simplemente encapsula los datos y los envía a la dirección destino sin saber si el receptor está listo o si existe, lo que permite que sea más rápido y ligero, pero sin garantías.

### Ejercicio 7

El **ISN (Initial Sequence Number)** es un número de 32 bits generado aleatoriamente que asigna el emisor (tanto el cliente como el servidor) al primer byte de datos de una nueva conexión TCP. El intercambio de los ISN es el objetivo principal del saludo de tres vías, de hecho, el handshake sirve para "sincronizar" (SYN) estos números. Al finalizar el saludo de tres vías, ambos extremos conocen el ISN del otro y saben qué número de secuencia esperar en el primer segmento de datos real.

>[!note]
> Se genera aleatoriamente por seguridad (evitar predicción de secuencias) y para no confundir paquetes de conexiones anteriores.

### Ejercicio 8

El **MSS (Maximum Segment Size)** define la **máxima cantidad de datos** de la **capa de aplicación** que se **puede enviar en un segmento TCP**. Se refiere **solo a la carga útil de datos** (bytes de la aplicación) y **no incluye las cabeceras** del segmento TCP o del datagrama IP que lo transporta.
La negociación del MSS entre el cliente y el servidor se lleva a cabo durante el establecimiento de la conexión TCP como parte del saludo de tres vías (three-way handshake), utilizando el campo de longitud variable `Options` dentro de la cabecera del segmento TCP. Cada extremo revisa el tamaño de frame que puede enviar (MTU), luego se lo comunica en el envío del SYN al otro host, y finalmente el **valor final del MSS** utilizado para la conexión será el **mínimo acordado por ambas partes**, generalmente seleccionado para ser el MTU más pequeño de la ruta.
La importancia de acordar un MSS adecuado es evitar la fragmentación de datagramas, lo que contribuye a la transmisión de datos eficiente.

>[!note]
> Generalmente, el MSS se calcula restando el tamaño de los encabezados al MTU de la red: MSS = MTU - sizeof(TCP Header) - sizeof(IP Header)

### Ejercicio 9

**Utilizando `ss`**

#### Inciso a

- Comunicaciones TCP establecidas
  ```bash
    redes@debian:~$ ss -t 
    State                Recv-Q                Send-Q                               Local Address:Port                                   Peer Address:Port                 Process                
    ESTAB                0                     0                                        10.0.2.15:35426                                 142.251.129.3:http                                        
    ESTAB                0                     0                                        10.0.2.15:54198                                151.101.217.91:https                                       
    ESTAB                0                     0                                        10.0.2.15:47460                                34.160.144.191:https                                       
    ESTAB                0                     0                                        10.0.2.15:35418                                 142.251.129.3:http                                        
    ESTAB                0                     0                                        10.0.2.15:35446                                 142.251.129.3:http                                        
    ESTAB                0                     0                                        10.0.2.15:35458                                 142.251.129.3:http                                        
    ESTAB                0                     0                                        10.0.2.15:35440                                 142.251.129.3:http                                        
    ESTAB                0                     0                                        10.0.2.15:45776                                 34.107.243.93:https 
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo ss -t -p
      State            Recv-Q            Send-Q                       Local Address:Port                          Peer Address:Port            Process                                              
      ESTAB            0                 0                                10.0.2.15:35426                        142.251.129.3:http             users:(("firefox-esr",pid=27088,fd=45))             
      ESTAB            0                 0                                10.0.2.15:54198                       151.101.217.91:https            users:(("firefox-esr",pid=27088,fd=111))            
      ESTAB            0                 0                                10.0.2.15:47460                       34.160.144.191:https            users:(("firefox-esr",pid=27088,fd=118))            
      ESTAB            0                 0                                10.0.2.15:35418                        142.251.129.3:http             users:(("firefox-esr",pid=27088,fd=42))             
      ESTAB            0                 0                                10.0.2.15:35446                        142.251.129.3:http             users:(("firefox-esr",pid=27088,fd=126))            
      ESTAB            0                 0                                10.0.2.15:35458                        142.251.129.3:http             users:(("firefox-esr",pid=27088,fd=124))            
      ESTAB            0                 0                                10.0.2.15:35440                        142.251.129.3:http             users:(("firefox-esr",pid=27088,fd=103))            
      ESTAB            0                 0                                10.0.2.15:45776                        34.107.243.93:https            users:(("firefox-esr",pid=27088,fd=46)) 
    ```

#### Inciso b

- Comunicaciones UDP establecidas
  ```bash
    redes@debian:~$ ss -u
    Recv-Q                   Send-Q                                        Local Address:Port                                       Peer Address:Port                    Process                  
    0                        0                                          10.0.2.15%enp0s3:bootpc                                         10.0.2.2:bootps     
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo ss -u -p
      Recv-Q                   Send-Q                                        Local Address:Port                                       Peer Address:Port                    Process                  
      0                        0                                          10.0.2.15%enp0s3:bootpc                                         10.0.2.2:bootps                   users:(("NetworkManager",pid=521,fd=23))
    ```

#### Inciso c

- Sólo los servicios TCP que están esperando comunicaciones
  ```bash
    redes@debian:~$ ss -t -l
    State                 Recv-Q                Send-Q                                    Local Address:Port                                Peer Address:Port               Process               
    LISTEN                0                     128                                             0.0.0.0:ssh                                      0.0.0.0:*                                        
    LISTEN                0                     128                                           127.0.0.1:ipp                                      0.0.0.0:*                                        
    LISTEN                0                     5                                             127.0.0.1:4038                                     0.0.0.0:*                                        
    LISTEN                0                     128                                                [::]:ssh                                         [::]:*                                        
    LISTEN                0                     128                                               [::1]:ipp                                         [::]:*                                        
    LISTEN                0                     4096                                              [::1]:50051                                       [::]:*                                        
    LISTEN                0                     4096                                 [::ffff:127.0.0.1]:50051                                          *:*  
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo ss -t -l -p
      State             Recv-Q            Send-Q                            Local Address:Port                        Peer Address:Port            Process                                          
      LISTEN            0                 128                                     0.0.0.0:ssh                              0.0.0.0:*                users:(("sshd",pid=666,fd=3))                   
      LISTEN            0                 128                                   127.0.0.1:ipp                              0.0.0.0:*                users:(("cupsd",pid=609,fd=7))                  
      LISTEN            0                 5                                     127.0.0.1:4038                             0.0.0.0:*                users:(("core-daemon",pid=608,fd=5))            
      LISTEN            0                 128                                        [::]:ssh                                 [::]:*                users:(("sshd",pid=666,fd=4))                   
      LISTEN            0                 128                                       [::1]:ipp                                 [::]:*                users:(("cupsd",pid=609,fd=6))                  
      LISTEN            0                 4096                                      [::1]:50051                               [::]:*                users:(("core-daemon",pid=608,fd=9))            
      LISTEN            0                 4096                         [::ffff:127.0.0.1]:50051                                  *:*                users:(("core-daemon",pid=608,fd=10))
    ```

#### Inciso d

- Sólo los servicios UDP que están esperando comunicaciones
  ```bash
    redes@debian:~$ ss -u -l 
    State                 Recv-Q                Send-Q                                Local Address:Port                                  Peer Address:Port                Process                
    UNCONN                0                     0                                           0.0.0.0:39436                                      0.0.0.0:*                                          
    UNCONN                0                     0                                           0.0.0.0:631                                        0.0.0.0:*                                          
    UNCONN                0                     0                                         127.0.0.1:4038                                       0.0.0.0:*                                          
    UNCONN                0                     0                                           0.0.0.0:mdns                                       0.0.0.0:*                                          
    UNCONN                0                     0                                              [::]:37939                                         [::]:*                                          
    UNCONN                0                     0                                              [::]:mdns                                          [::]:* 
  ```
  - visualizando el proceso del sistema asociado a la conexión
    ```bash
      redes@debian:~$ sudo ss -u -l -p
      State             Recv-Q            Send-Q                        Local Address:Port                          Peer Address:Port            Process                                            
      UNCONN            0                 0                                   0.0.0.0:39436                              0.0.0.0:*                users:(("avahi-daemon",pid=517,fd=14))            
      UNCONN            0                 0                                   0.0.0.0:631                                0.0.0.0:*                users:(("cups-browsed",pid=665,fd=7))             
      UNCONN            0                 0                                 127.0.0.1:4038                               0.0.0.0:*                users:(("core-daemon",pid=608,fd=6))              
      UNCONN            0                 0                                   0.0.0.0:mdns                               0.0.0.0:*                users:(("avahi-daemon",pid=517,fd=12))            
      UNCONN            0                 0                                      [::]:37939                                 [::]:*                users:(("avahi-daemon",pid=517,fd=15))            
      UNCONN            0                 0                                      [::]:mdns                                  [::]:*                users:(("avahi-daemon",pid=517,fd=13))  
    ```

> [!note] 
> Para ver los procesos asociados a las conexiones UDP/TCP es necesario usar `sudo`, ya que, por razones de seguridad, `ss` no muestra información de procesos que no pertenecen al usuario que ejecuta el comando

#### Inciso e

**Utilizando `netstat`**

- Comunicaciones TCP establecidas
  ```bash
    redes@debian:~$ netstat -t -a | grep ESTABLISHED
    tcp        0      0 10.0.2.15:32908         eze03s15-in-f10.1:https ESTABLISHED
    tcp        0      0 10.0.2.15:45828         lcezea-ad-in-f3.1e:http ESTABLISHED
    tcp        0      0 10.0.2.15:60250         lcezea-ad-in-f3.1:https ESTABLISHED
    tcp        0      0 10.0.2.15:45818         lcezea-ad-in-f3.1e:http ESTABLISHED
    tcp        0      0 10.0.2.15:45810         lcezea-ad-in-f3.1e:http ESTABLISHED
    tcp        0      0 10.0.2.15:45776         93.243.107.34.bc.:https ESTABLISHED
  ```
- Comunicaciones UDP establecidas
  ```bash
    redes@debian:~$ netstat -u -a | grep ESTABLISHED
    udp        0      0 10.0.2.15:bootpc        10.0.2.2:bootps         ESTABLISHED
  ```
- Sólo los servicios TCP que están esperando comunicaciones
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
- Sólo los servicios UDP que están esperando comunicaciones
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

>[!note]
> Si no utilizamos `grep` para filtrar las conexiones establecidas, la salida es muy larga, ya que `netstat` muestra todas las conexiones activas, tanto establecidas como en otros estados (LISTEN, TIME_WAIT, etc.)

### Ejercicio 10

Si un host recibe un segmento TCP cuyo número de puerto de destino **no se corresponde con ninguno de los sockets activos que están esperando una conexión**, o si **no hay un proceso en estado LISTEN en ese puerto**, el host de destino enviará un **segmento especial de reinicio**. Este segmento de reinicio tendrá el bit **indicador RST (Reset) puesto en 1**. Al enviar un segmento RST, el host está comunicando al emisor "No tengo un proceso escuchando en ese puerto, no puedo aceptar tu conexión".

> [!note]
> - `-S`: establece el bit SYN en el segmento TCP
> - `-p 22`: especifica el puerto de destino (22 es el puerto SSH, que está abierto en este caso)
> - `-c 5`: envía 5 paquetes (para no quedarnos enviando paquetes indefinidamente)
> - `127.0.0.1`: es la dirección IP de destino (en este caso, el localhost)

#### Inciso a

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

#### Inciso b

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

#### Inciso c

- La diferencia radica en que en el primer caso, para el puerto 22, se retornan los flags **SA (SYN-ACK)** indicando que el **puerto está abierto y escuchando conexiones**, mientras que en el segundo caso, para el puerto 40, se retornan los flags **RA (RST-ACK)**, indicando que **no hay ningún proceso escuchando en ese puerto**, por lo que **no se puede establecer una conexión**
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
- Acá podemos ver que el puerto 22 (SSH) está en estado LISTEN, mientras que el puerto 40 no aparece en la lista, confirmando que no hay ningún proceso escuchando en ese puerto, y tampoco se encuentra abierto.

### Ejercicio 11

Si un host recibe un **datagrama UDP** en un puerto de destino para el cual no hay un socket UDP activo, es decir, **no hay un proceso en estado de LISTEN**, el host no puede entregar el datagrama a la capa de aplicación. En esta situación, el **host envía un datagrama ICMP especial de vuelta al origen**. Este mensaje ICMP es de **"puerto de destino inalcanzable" (Destination Port Unreachable)**. Este mensaje es **transportado como carga útil dentro de un datagrama IP**. **UDP se basa en ICMP (capa de red) para notificar la imposibilidad de entrega** a nivel de proceso, a diferencia de TCP que utiliza el bit RST de su propia capa de transporte para el rechazo

#### Inciso a

```bash
  redes@debian:~$ sudo hping3 -2 -p 5353 127.0.0.1
  HPING 127.0.0.1 (lo 127.0.0.1): udp mode set, 28 headers + 0 data bytes
  ^C
  --- 127.0.0.1 hping statistic ---
  3 packets transmitted, 0 packets received, 100% packet loss
  round-trip min/avg/max = 0.0/0.0/0.0 ms
```

#### Inciso b

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
#### Inciso c

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
El puerto **5353 UDP sí está en uso por el proceso avahi-daemon**, que implementa el protocolo mDNS (Multicast DNS). Este servicio **no responde directamente a paquetes unicast enviados a 127.0.0.1, ya que trabaja por multicast (224.0.0.251)**. Por eso, **hping3 no recibe respuesta, pero el paquete no es rechazado, simplemente es ignorado (UDP no establece conexión)**. El datagrama llega a un puerto donde sí hay un proceso escuchando (avahi-daemon), pero como no es un paquete multicast válido, no genera respuesta. En cambio, el **puerto 40 UDP no está en uso (no hay ningún proceso en ese puerto)**. Cuando llega un datagrama UDP a un puerto sin proceso escuchando, el **sistema operativo responde con un mensaje ICMP "Port Unreachable"**. `hping3` interpreta ese mensaje ICMP y muestra una respuesta


>[!important]
> **TCP** usa **RST (Capa 4, transporte)** mientras que **UDP** depende de **ICMP (Capa 3, red)** para notificar puertos inalcanzables

### Ejercicio 12

#### Fases de establecimiento

- **CLOSED**
  - Representa que no existe ningún estado de conexión. El socket aún no ha sido abierto o ya ha sido cerrado.
- **LISTEN**
  - Un socket está a la espera de conexión entrante (servicio escuchando). Sólo aplica en el lado servidor que acepta conexiones (por ejemplo, sshd).
- **SYN-SENT**
  - Estado intermedio del cliente cuando envía un SYN para iniciar la conexión y espera respuesta (SYN+ACK). El cliente está “intentando conectarse”.
- **SYN-RECEIVED**
  - Estado que ocurre en el lado servidor cuando recibe un SYN, envía un SYN+ACK, y espera el ACK final del cliente. También puede darse en el cliente si hay una conexión simultánea (ambos inician al mismo tiempo).
- **ESTABLISHED**
  - La conexión ya fue completamente establecida (saludo de tres vías completado). Los datos pueden fluir en ambas direcciones.

#### Fases de cierre

- **FIN-WAIT-1**
  - Un extremo ha comenzado el cierre enviando un FIN, y espera un ACK o un FIN del otro lado.
- **FIN-WAIT-2**
  - Tras recibir el ACK del otro extremo luego de enviar FIN, espera el FIN del otro lado.
- **CLOSE-WAIT**
  - Estado que representa la espera de un pedido de cierre de conexión del lado del usuario local, después de haber recibido un FIN del otro extremo. El sistema operativo está esperando a la aplicación local.
- **CLOSING**
  - Estado que representa la espera de un ACK del lado del TCP remoto, luego de enviar un FIN. Solo ocurre en un cierre simultáneo (ambos lados envían FIN al mismo tiempo exacto), cruzándose los mensajes en la red.
- **LAST-ACK**
  - El que ya envió su FIN espera el ACK final después de haber recibido y atendido el FIN del otro.
- **TIME-WAIT**
  - Representa el tiempo suficiente que debe pasar para asegurar que el TCP remoto recibió el ACK final de cierre. Sólo un lado entra en TIME-WAIT típicamente. Espera 2MSL (Maximum Segment Lifetime) para asegurar que el ACK final llegó al otro lado y para permitir que expiren segmentos viejos duplicados en la red antes de reusar la tupla (IP/Puerto).

### Ejercicio 13

#### Inciso a

- Se encuentran establecidas 8 conexiones, ya que se puede observar que están en estado `ESTAB` 9 conexiones, pero una de ellas, en particular, la que involucra a ssh, aparece 2 veces por la comuniación entre ambos extremos.

#### Inciso b

- Se encuentran abiertos a la espera de posibles nuevas conexiones 4 puertos, ya que se puede observar que están en estado `LISTEN` 5 puertos, pero el puerto 53 aparece dos veces, una con la IP `163.10.5.222` y otra con la IP localhost `127.0.0.1`.

#### Inciso c

- El cliente y el servidor de las comunicaciones HTTPS (puerto 443) no residen en la misma máquina, ya que sus direcciones IP son diferentes. Si estuvieran en la misma máquina, deberían tener la misma IP.

#### Inciso d

- EL cliente y el servidor de las comunicaciones SSH (puerto 22) residen en la misma máquina, ya que comparten la misma dirección IP (`127.0.0.1`).

#### Inciso e

|Puerto|Protocolo|Proceso - PID|Rol|
|------|---------|-------------|---|
|22|TCP|sshd - 468|Servidor|
|80|TCP|apache2 - 991|Servidor|
|53|UDP|named - 452|Servidor|
|443|TCP|x-www-browser - 1079|Cliente|
|443|TCP|x-www-browser - 1079|Cliente|
|443|TCP|x-www-browser - 1079|Cliente|
|443|TCP|x-www-browser - 1079|Cliente|
|443|TCP|x-www-browser - 1079|Cliente|
|25|TCP|postfix - 627|Servidor|
|22|TCP|sshd - 1418, sshd - 1416|Servidor|
|443|TCP|x-www-browser - 1079|Cliente|
|443|TCP|x-www-browser - 1079|Cliente|
|443|TCP|x-www-browser - 1079|Cliente|
|443|TCP|x-www-browser - 1079|Cliente|
|9500|TCP|x-www-browser - 1079|Cliente|
|22|TCP|ssh - 1415|Cliente|
|53|UDP|named - 452|Servidor|

#### Inciso f

- Las conexiones que estén en estado `TIME-WAIT` son aquellas en las que el cierre fue iniciado por el host local, y las que están en `CLOSE-WAIT` son aquellas en las que el cierre fue iniciado por el host remoto.

#### Inciso g

- Solo hay una conexión pendiente de establecerse, ya que solo vemos un socket en estado `SYN-SENT`.

### Ejercicio 14

#### Inciso a 

El cliente envió un segmento `SYN` al servidor y este fue recibido ya que el servidor está en estado `SYN-RECV`. El servidor, al alcanzar este estado, recibe el `SYN` del cliente y responde con un segmento `SYN-ACK`. Sin embargo, el cliente no recibió este segmento de respuesta, ya que permanece en estado `SYN-SENT`, esperando el `SYN-ACK` del servidor para completar el saludo de tres vías. Entonces, podemos afirmar que el segmento `SYN` que el cliente envío al servidor llegó, pero el segmento `SYN-ACK` no llegó al cliente, por lo que podría haberse perdido.

#### Inciso b

El cliente está queriendo conectarse al protocolo de aplicación **POP3**, ya que envía el segmento `SYN` al **puerto 110** del servidor, que es el **puerto estándar para POP3**. A su vez, se está queriendo conectar **usando TCP** como protocolo de transporte, ya que el segmento `SYN` es característico de TCP.

#### Inciso c

El segmento perdido tendría que haber seteado las flags `SYN` en 1 y `ACK` en 1 (SYN-ACK), ya que es la respuesta del servidor al `SYN` del cliente. 