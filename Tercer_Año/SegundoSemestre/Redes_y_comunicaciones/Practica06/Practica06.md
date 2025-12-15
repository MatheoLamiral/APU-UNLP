# Práctica 6 - Capa de transporte, parte 2

### Ejercicio 1

- Los puertos por defecto son:
  - **web(HTTP)**: 80
  - **SSH**: 22
  - **DNS**: 53
  - **Web seguro(HTTPS)**: 443
  - **POP3**: 110
  - **IMAP**: 143
  - **SMTP**: 25
- La asociación de estos nombres de servicios con sus números de puerto y el protocolo se define localmente en un archivo de texto llamado "services".
  - En Linux, la ubicación estándar es `/etc/services`.
  - En Windows, la ubicación estándar es `C:\Windows\System32\drivers\etc\services`.

### Ejercicio 2

El **multicast** es un método de transmisión de datos en redes donde **un solo paquete se envía a múltiples destinatarios interesados simultáneamente**. A diferencia de unicast (un solo emisor a un solo receptor) y broadcast (un emisor a todos los dispositivos de una red), multicast permite que **la información se entregue a los dispositivos que han expresado interés en recibirla**. El multicast es fundamental para aplicaciones que requieren la entrega de paquetes a un grupo de receptores, como la transferencia masiva de datos, flujos multimedia continuos (audio, video, videoconferencias, etc.), juegos en línea, entre otros.
- El multicast **opera principalmente sobre UDP**. Al ser un protocolo sin conexión y sin control de flujo, UDP permite que el emisor envíe los datagramas una sola vez a la red sin tener que preocuparse por quiénes o cuántos están escuchando, ni mantener un estado por cada receptor, además de que no hay retardo de establecimiento de conexión.
- Es **Inviable adaptar TCP para multicast** por las siguientes razones
  - **Orientado a conexión (Estado)**: TCP requiere mantener un estado (números de secuencia, buffers, ventanas) sincronizado con cada receptor. En un grupo multicast dinámico y grande, esto es imposible de gestionar para el emisor.
  - **Implosión de ACKs**: Si TCP se usara en multicast, cada receptor enviaría un acuse de recibo (ACK) por cada segmento. Si hay miles de receptores, el emisor se saturaría procesando miles de ACKs simultáneos, colapsando la comunicación.
  - **Control de flujo y congestión**: TCP proporciona confiabilidad, control de flujo y detección de congestión. En un entorno multicast, diferentes receptores pueden tener diferentes capacidades de red y velocidades de procesamiento. Si un destinatario tiene problemas de red, retrasaría la transmisión para todos los demás, afectando la eficiencia general.
  - **Garantía de entrega**: TCP implica la necesidad de confirmar la recepción de paquetes. Si varios receptores tuvieran diferentes latencias o pérdidas de paquetes, coordinar retransmisiones y confirmaciones sería extremadamente complejo y poco práctico. 

### Ejercicio 3

**FTP (File Transfer Protocol)** es un protocolo de la capa de aplicación diseñado para la **transferencia de archivos entre sistemas terminales** que se **ejecuta por encima de TCP**. Es un protocolo único en la capa de aplicación, ya que **utiliza dos conexiones TCP paralelas para transferir un archivo**, una para el control (puerto 21) y otra para la transferencia de datos. Debido a esta separación, se dice que FTP envía su información de control fuera de banda (**Out-Of-Band**).
- **Conexión de control**
  - se **establece al iniciar la sesión**. Se usa para **enviar comandos y respuestas en ASCII de 7 bits** (usuario, pass, `LIST`, `RETR`, etc.). Esta conexión es **persistente durante toda la sesión**.
- **Conexión de datos**
  - Se utiliza **exclusivamente para enviar el archivo**. Es **no persistente**, se abre una **nueva conexión de datos por cada archivo** transferido y **se cierra al terminar**.

#### Modo activo 

- El cliente envía el comando `PORT` especificando su IP y un puerto. El **servidor inicia la conexión de datos** desde su puerto 20 hacia el puerto especificado por el cliente.

>[!important]
> Esto suele fallar con firewalls/NAT del lado del cliente, ya que bloquean la conexión entrante iniciada por el servidor
>[!note]
> Un firewall es un sistema de seguridad para redes que actúa como una barrera entre una red interna confiable y redes externas no confiables, como Internet.

#### Modo pasivo

- El cliente envía el comando `PASV`. El servidor responde con una IP y un puerto aleatorio (ej. 5000) donde está escuchando. El **cliente inicia la conexión de datos** hacia ese puerto.

>[!note]
> Es el modo estándar hoy en día ("Firewall Friendly"), ya que ambas conexiones (Control y Datos) son iniciadas por el cliente (salientes), y los firewalls del lado del cliente permiten este tráfico sin problemas.

#### Diferencias con otros protocolos de la capa de aplicación

- Las diferencias de FTP con otros protocolos de la capa de aplicación son:
  - **Fuera de banda (Out-Of-Band)**: A diferencia de muchos otros protocolos, FTP usa dos canales separados, uno para la conexión de control y otro para la transferencia de datos. Mientras que la mayoría, envían los comandos de control (cabeceras) y los datos en la misma conexión TCP (en banda)
  - **Con estado (Stateful)**: A diferencia de muchos otros protocolos, FTP **mantiene el estado del usuario (directorio actual, autenticación)** a través de la conexión de control.
  
### Ejercicio 4

La numeración de los ACK que el host B envía al host A en la figura, suponiendo Selective Repeat, es:
- primer ACK = 0
- segundo ACK = 1
- tercer ACK = 3
- cuarto ACK = 4
- quinto ACK = 5
- sexto ACK = 2

>[!important]
> A diferencia de **Go-Back-N (GBN)**, donde un error obliga a reenviar toda la ventana de paquetes, **Selective Repeat** solo reenvía los paquetes específicos que se perdieron o llegaron dañados. El receptor acepta y almacena en búfer (buffer) los paquetes que llegan fuera de orden (siempre que estén dentro de la ventana de recepción). Esto evita tener que descartar paquetes que llegaron bien solo porque faltaba uno anterior.

### Ejercicio 5

La reestricción que existe sobre el tamaño de las ventanas en el protocolo Selective Repeat es que el tamaño de la ventana tiene que ser menor o igual que la mitad del tamaño del espacio de números de secuencia (W <= M/2). Esta limitación es crucial para evitar que el receptor confunda un paquete retransmitido antiguo con un paquete nuevo. Debido a que los número de secuencia son finitos y se reutilizan, el receptor podría confundir dos escenarios idénticos que tienen consecuencias de procesamiento muy diferentes.

### Ejercicio 6

Podemos completar los campos que estan "borroneados" interpretando los diferentes valores (`seq`, `ack`, etc.), ya que se está realizando un trhee-way handshake para establecer una conexión TCP entre `172.20.1.1` y `172.20.1.100`.
La resolución completa y los campos completados está en el archivo [Ejercicio6.pdf](Extras/Ejercicio06.pdf).

>[!important]
> Tener en cuenta que el campo `seq` de un host se corresponde con el campo `ack` del otro host, y viceversa. Además, de que la sequencia de pasos del three-way handshake es `SYN`, `SYN ACK`, `ACK`.

### Ejercicio 7

La resolución del ejercicio está en el archivo [Ejercicio7.pdf](Extras/Ejercicio07.pdf).

### Ejercicio 8

El **RTT (Round Trip Time / Tiempo de ida y vuelta)** es el **tiempo que transcurre desde que se envía un segmento hasta que se recibe su confirmación** (`ACK`). Conceptualmente, es la suma de los tiempos de transmisión, propagación, y el tiempo de procesamiento en el receptor
- El RTT se calcula de la siguiente manera:
    - **Fórmula del Sample RTT (muestra)**: `SampleRTT = Tiempo de recepción del ACK - Tiempo de envío`
    - Dado que el **RTT fluctúa**, **TCP calcula un promedio** ponderado exponencial (EstimatedRTT) basado en estas muestras para **definir** su temporizador de retransmisión (**RTO**).
- Opción TCP `Timestamp`:
  - Es una **extensión del protocolo negociada en el handshake** (`SYN`) que agrega dos campos de 32 bits en la cabecera para mejorar la precisión.
  - Campos **TSval** y **TSecr**:
    - **TSval (Timestamp Value)**: El emisor copia el valor de su reloj local en este campo cuando envía un segmento.
    - **TSecr (Timestamp Echo Reply)**: El receptor, al enviar un `ACK`, copia el valor TSval que recibió en el segmento de datos y lo devuelve en este campo.

>[!note]
> El emisor simplemente calcula `RTT = Hora Actual - TSecr`. Esto permite obtener mediciones de RTT precisas en cada segmento (no solo uno por ventana) y resuelve la ambigüedad en las retransmisiones, además de proteger contra números de secuencia repetidos (PAWS) en redes de alta velocidad.

### Ejercicio 9

>[!important]
> Los `seq` que se ven son los que pone wireshark para facilitar la lectura, si queremos ver los números de secuencia reales, debemos fijarnos en el campo `Sequence Number (raw)` del encabezado TCP. Si se quisiera ver siempre el número de secuencia real, se puede configurar en `Edit -> Preferences -> Protocols -> TCP` y desmarcar la opción `Relative sequence numbers`.

#### Inciso a 

En total hay 6 intentos de conexión TCP (SYN). Los detalles de la captura de wireshark son están en el archivo [Ejercicio09-a.png](Extras/Ejercicio09-a.png).

>[!note]
> Para poder ver en wireshark solo los intentos de conexión TCP, se puede utilizar el filtro `tcp.flags.syn == 1 && tcp.flags.ack == 0`, que muestra solo los segmentos con el flag SYN activado y el flag ACK desactivado.

#### Inciso b

|(IP:Port) Origen|(IP:Port) Destino|
|---|---|
|10.0.2.10:46907|10.0.4.10:5001|
|10.0.2.10:45670|10.0.4.10:7002|
|10.0.2.10:45671|10.0.4.10:7002|
|10.0.2.10:46910|10.0.4.10:5001|
|10.0.2.10:54424|10.0.4.10:9000|
|10.0.2.10:54425|10.0.4.10:9000|

>[!note]
> Podemos observarlo al clicker sobre cada fila del wireshark y ver los campos `Source Port` y `Destination Port` en la seccion de TCP.

#### Inciso c

De las 6 conexiones TCP, 4 fueron exitosas y 2 fallidas. Las exitosas se identifican porque tienen los flags `SYN-ACK`, y las fallidas porque tienen los flags `RST-ACK`.

>[!note]
> Las conexiones exitosas estan en el archivo [Ejercicio09-c-exitosas.png](Extras/Ejercicio09-c-exitosas.png) y las fallidas en el archivo [Ejercicio09-c-fallidas.png](Extras/Ejercicio09-c-fallidas.png).

#### Inciso d

- **i)**
  - La conexión es iniciada por (IP:port) `10.0.2.10:46907`
- **ii)**
  - El servidor es `10.0.4.10:5001` y el cliente es `10.0.2.10:46907`
- **iii)**
  - El three-way handshake se ve en los segmentos 3, 4 y 5
- **iv)**
  - Los ISNs que se intercambian son:
    - **Segmento 3 (SYN)**: ISN cliente = 0 (2218428254)
    - **Segmento 4 (SYN-ACK)**: ISN servidor = 0 (1292618479)
    - **Segmento 5 (ACK)**: ISN cliente = 1 (2218428255)
  >[!important]
  > El **ISN (Initial Sequence Number)** es el primer número de secuencia que un cliente o servidor utiliza al iniciar una conexión TCP. Luego de la negociación inicial, ambos lados incrementan sus números de secuencia a medida que envían datos. 

- **v)**
  - Se negoció un MSS de 1460 bytes, que se puede observar en el campo `MSS` en los segmentos 3 y 4 del three-way handshake
- **vi)**
  - B (`10.0.4.10:5001`) transfiere 27k bytes hacia A (`10.0.2.10:46907`), y que A transfiere 822k hacia B, por ende el host que envió mayor cantidad de datos es A
  
  >[!note]
  > Podemos ver la cantidad de bytes transferidos en wireshark en la sección `Statistics -> Conversations -> TCP`.

>[!note]
> La captura del handsake donde se ven los datos usados está en el archivo [Ejercicio09-d.png](Extras/Ejercicio09-d.png)

#### Inciso e

El primer segmento de datos es el 6, origen `10.0.2.10:46907` y destino `10.0.4.10:5001`, tiempo 0.151747000
- **i)**
  - El campo `Len` tiene un valor de 24, indicando que lleva 24 bytes de datos
- **ii)**
  - Es confirmado en el segmento 7, tiempo desde el primer frame 0.151846000 y número de sequencia seq=1
- **iii)**
  - La confirmación confirma 24 bytes, y espera a partir del byte 25

#### Inciso f

El cierre de conexión lo inicia el cliente `10.0.2.10:46907` y puede observarse en el segmento 958, utilizando los flags `FIN-PSH-ACK`.

>[!note]
> los datos restantes del cierre de conexión pueden verse en el archivo [Ejercicio09-f.png](Extras/Ejercicio09-f.png). Además, para vel el cierre de conexión en wireshark se usó el filtro `tcp.flags.fin == 1`.
  
### Ejercicio 10


#### Inciso a

El **control de flujo** esta **siempre activado y es controlado por el receptor**, gestionando la variable `rwnd` (Receive Window), y es **disparado cuando el campo `Window Size` es 0**. Cuando el **receptor tiene poco espacio en su buffer**, **reduce** el valor anunciado en el **campo `Window Size`** de los segmentos TCP que envía al emisor. El emisor **está obligado a respetar este límite**, asegurando que `LastByteSent - LastByteAcked <= rwnd`

#### Inciso b

Su objetivo principal es **prevenir que el emisor sature o deborde el buffer de recepción del receptor al enviar demasiados datos demasiado rápido**. Proporciona un servicio de adaptación de velocidades (speed matching), asegurando que la velocidad de envío del emisor no supere la capacidad de la aplicación receptora para leer y procesar los datos del buffer.

#### Inciso c

El **control de flujo está activo durante todo el tiempo de vida de la conexión TCP**, ya que la ventana de recepción es una variable dinámica que se ajusta continuamente. 
- Si bien el mecanismo siempre está activo, las **restricciones de transmisión que impone pueden activarse o desactivarse de la siguiente manera**
  - **Ventana cerrada**: Si la aplicación es lenta y el buffer se llena, el receptor anuncia `rwnd = 0`, es decir, `window size = 0`. El emisor detiene el envío de datos útiles.
  - **Desactivación**: Cuando la aplicación libera espacio, el receptor envía un ACK con un nuevo valor de ventana positivo.
- Si la **ventana es cero**, el **emisor** inicia un temporizador de persistencia y **envía periódicamente segmentos de prueba de 1 byte**, llamados **Zero Window Probes**. Esto **obliga** al **receptor a responder con el estado actual de su ventana**, **evitando un bloqueo mutuo** (deadlock) en caso de que el ACK de apertura de ventana se haya perdido.

### Ejercicio 11

#### Inciso a

El mecanismo de **control de congestión** es **activado y controlado por el emisor**, **limitando** su **tasa de transmisión basándose en la congestión que percibe de la red**. Esta limitación se logra mediante una variable dinámica llamada ventana de congestión (`CongWin` o `cwnd`). 
>[!note]
> La tasa de transmisión es aproximadamente igual a `VentanaCongestion/RTT`
- El emisor **deduce que existe congestión** en la ruta cuando se produce un **evento de pérdida**. Los **dos posibles disparadores de un evento de pérdida son**:
  - **Timeout**: Esto indica que el TTL asociado a un segmento se venció, indicando que los **paquetes** (o sus ACKs) **no llegaron**, probablemente debido a que los buffers de los routers se desbordaron y se descartaron datagramas.
  - **Triple ACK duplicado**: Esto sucede cuando el emisor recibe el cuarto ACK para el mismo segmento. Indica que se ha perdido un segmento, pero los paquetes posteriores si lograron llegar.

#### Inciso b

El **control de congestión resuelve** el problema de **sobrecarga de la red**. Este mecanismo busca prevenir el colapso, regular el flujo y proporcionar equidad. 

> [!important]
> Es crucial diferenciar el control de congestión del control de flujo, ya que este último solo se enfoca en que el emisor no sature el buffer del receptor, mientras que el control de congestión toma en cuenta el estado de la red.

#### Inciso c

| Característica | Slow Start (SS) | Congestion Avoidance (CA) |
| --- | --- | --- |
| **Objetivo** | Descubrir rápidamente la capacidad de la red | Mantener el flujo estable y evitar congestión |
| **Inicio** | Al iniciar la conexión o tras un Timeout (cwnd = 1 MSS)  | Cuando cwnd ≥ ssthresh (Umbral) |
| **Crecimiento `cwnd`** | Exponencial, se duplica cada RTT (incrementa cwnd en 1 MSS por cada ACK recibido).  |Lineal. Aumenta 1 MSS por cada RTT (incrementa MSS * (MSS/cwnd) por cada ACK) |
| **Finalización** | Al alcanzar el ssthresh (pasa a CA) o detectar pérdida | Al detectar pérdida (Timeout o Triple ACK) |

### Ejercicio 12

#### Inciso a

- En la captura se observan 9 comunicaciones UDP.

|(IP:Port) Origen|(IP:Port) Destino|
|---|---| 
|1.1.1.1:9045|10.0.2.10:9004|
|10.0.2.10:9000|10.0.3.10:13|
|10.0.2.10:9004|10.0.3.10:13|
|10.0.2.10:9004|10.0.3.10:4555|
|10.0.2.10:53300|10.0.4.10:9045|
|10.0.2.10:59053|10.0.4.10:8003|
|10.0.2.10:8003|10.0.4.10:8003|
|10.0.3.10:9045|10.0.2.10:9004|
|10.0.30.10:8003|10.0.2.10:0|

>[!note]
> Para ver las comunicaciones UDP en wireshark `statistics -> Conversations -> UDP`

#### Inciso b

Las comunicaciones UDP fallidas devolverán un mensaje ICMP indicando el motivo del error (puerto inalcanzable, host inalcanzable o tiempo excedido). Las exitosas, en cambio, no devolverán ningún mensaje, ya que UDP carece de mecanismos de confirmación (ACK).

>[!important]
> La ausencia de un mensaje ICMP de error no garantiza que la comunicación haya sido exitosa. El paquete podría haber sido descartado silenciosamente por un firewall o un router intermedio sin generar notificación.

#### Inciso c

Aunque UDP no realiza un establecimiento de conexión (handshake) como TCP, **soporta y utiliza el modelo cliente/servidor**. La diferencia fundamental radica en la gestión del estado, en UDP el servidor **no mantiene un "estado de conexión"** dedicado ni exclusivo **para cada cliente**. Simplemente escucha en un puerto conocido, **procesa los datagramas de solicitud que llegan y envía datagramas de respuesta, sin garantizar la continuidad de una sesión**.

#### Inciso d

Las aplicaciones o servicios que suelen utilizar UDP son:
- **DNS (Domain Name System)**: Generalmente consiste en una sola solicitud y una sola respuesta. Establecer una conexión TCP (Handshake) duplicaría o triplicaría el tiempo necesario para resolver una dirección web
- **Aplicaciones multimedia en tiempo real (VoIP, videoconferencias)**: Si se pierde un paquete de voz o video, es preferible que se pierda a detener la transmisión para retransmitirlo
- **Streaming multimedia**: Servicios como la transmisión de video o audio en tiempo real prefieren UDP porque permite una entrega continua y rápida, aunque no garantice la entrega de todos los paquetes.
- **Juegos en línea**: Importa, por ejemplo, la posición actual del jugador. Recibir un paquete retransmitido con la posición de hace 2 segundos es inútil y obsoleto.
- **Protocolos de enrutamiento (como RIP)**: Envían mensajes pequeños y periódicos, por lo que la sobrecarga de TCP es innecesaria.
- **QUIC (HTTP/3)**: usa UDP como base para implementar su propio control de fiabilidad y congestión a nivel de aplicación, evitando la lentitud del handshake de TCP.

Estas aplicaciones comparten algunos requerimientos y características, que las hacen más adecuadas para UDP:
- **Tolerancia a la pérdida de datos (Loss-tolerant)**: Pueden funcionar aceptablemente aunque se pierdan algunos paquetes
- **Sensibilidad al Retardo (Delay-sensitive)**: Requieren una baja latencia. No pueden permitirse el tiempo extra que implica el establecimiento de conexión (Handshake) o las retransmisiones automáticas de TCP.
- **Necesidad de Ancho de Banda Mínimo**: Algunas aplicaciones multimedia requieren enviar datos a una tasa constante, UDP permite enviar a la velocidad que la aplicación desee, sin ser frenado por un mecanismo de control de congestión estricto (aunque esto puede saturar la red).
- **Control Fino a Nivel de Aplicación**: La aplicación quiere decidir exactamente qué se envía y cuándo, sin que el sistema operativo "tome decisiones" (como hace TCP al agrupar datos en segmentos o retrasar envíos).

#### Inciso e

UDP **realiza un control de errores mínimo**. Su única herramienta es el **Checksum** (Suma de comprobación), que sirve para detectar alteraciones en los bits del segmento. Si detecta un error mediante el Checksum, su comportamiento estándar es realizar un **descarte silencioso** del paquete, lo **elimina sin notificar al emisor** y **sin solicitar retransmisión**. Por lo tanto, UDP **ofrece detección de errores, pero no recuperación de errores**.

>[!important]
> - Al no tener mecanismo de retransmisión, si un paquete se pierde o llega corrupto, se pierde para siempre a menos que la aplicación decida reintentarlo por su cuenta.
> - UDP no detecta si faltan paquetes o si llegaron desordenados.

#### Inciso f

A diferencia de TCP, en UDP el puerto origen puede ser cualquiera, incluso el puerto 0, si no se necesita una respuesta. Puede ser simplemente de envío, sin interés en recibir datos de vuelta, por lo que no es necesario especificar un puerto de origen válido

#### Inciso g

- La dirección IP que envía el primer datagrama es `10.0.2.10` en el puerto `59053`
- Desde A(`10.0.2.10`) hacia B(`10.0.4.10`) se envían 139 bytes, y desde B hacia A se envían 96 bytes 

### Ejercicio 13

Suponiendo los siguientes comandos desde un host con IP `10.100.25.90`.

#### Inciso a 

- `hping3 -p 3306 –udp 10.100.25.135`
  - Devolverá un mensaje **ICMP port unreachable**, ya que el puerto 3306 está escuchando TCP y no UDP.

#### Inciso b 

- `hping3 -S -p 25 10.100.25.135`
  - Devolverá un segmento TCP `RST-ACK`. Esto ocurre porque, aunque el puerto 25 está activo, no hay ningún proceso escuchando en la IP `10.100.25.135`. 

  >[!important]
  > Tener en cuenta que para que una conexión resulte exitosa los cuatro elementos de la cuadrupla (IP origen, puerto origen, IP destino, puerto destino) deben coincidir con una conexión activa en el host destino.

#### Inciso c 

- `hping3 -S -p 22 10.100.25.135`
  - Devolverá un segmento TCP `SYN-ACK`, ya que el puerto 22 está abierto y está escuchando conexiones en todas las interfaces (`*:22`), incluyendo la IP `10.100.25.135`.
  
#### Inciso d 

- `hping3 -S -p 110 10.100.25.135`
  - Devolverá un segmento TCP `RST-ACK`, ya que no hay ningún proceso escuchando en el puerto 110

#### Cantidad de conexiones establecidas

La cantidad de conexiones distintas que estan establecidas es 3
  >[!note]
  > Tener en cuenta que en la salida se visualizan 5 filas con el estado `ESTAB`, pero dos de ellas se intercala el host local con el remoto, por lo que en realidad son 3 conexiones distintas y no 5.

