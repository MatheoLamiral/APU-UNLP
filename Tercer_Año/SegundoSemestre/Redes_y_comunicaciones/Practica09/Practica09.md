# Práctica 9 - Capa de Enlace

### Ejercicio 1

La función fundamental de la Capa de Enlace es la transferencia de paquetes entre nodos adyacentes a través de un único enlace de comunicaciones. Los nodos que ejecutan el protocolo de la capa de enlace incluyen hosts, routers, switches y puntos de acceso WiFi
- Los servicios que ofrece son:
  - **Entramado (Framing)**: encapsula cada datagrama de la capa de red dentro de una trama de la capa de enlace. La trama está compuesta por un campo de datos y una serie de campos de cabecera (header) y cola (trailer).
  - **Control de acceso al medio MAC(Medium Access Control)**: En los enlaces de difusión (broadcast), donde múliples nodos están conectados a un mismo canal compartido, es necesaria la coordinación. El protocolo de acceso múltiple se encarga de coordinar las transmisiones para evitar colisiones. 
  - **Entrega fiable (Reliable Delivery)**: Algunos protocolos de la Capa de Enlace proporcionan un servicio de entrega fiable, el cual garantiza que el datagrama será transportado a través del enlace sin que se produzcan errores. Este servicio suele implementarse mediante reconocimientos (ACKs) y retransmisiones. Esta entrega fiable es nodo a nodo (entre dos elementos adyacentes), a diferencia de la entrega fiable de TCP que es terminal a terminal (entre procesos de los hosts de origen y destino)
  - **Detección y corrección de errores (Error Detection and Correction)**: Se ofrecen servicios para detectar y corregir los errores de bit que se producen al transmitir la trama.  La detección de errores permite al receptor detectar si hay bits erróneos en la trama y  determinar exactamente dónde se produjeron los errores y corregirlos.
  - **Direccionamiento de la capa de enlace (direcciones MAC)**: La capa de enlace utiliza direcciones MAC (también llamadas direcciones LAN o físicas) que se asignan a los adaptadores de red (interfaces) de hosts y routers
  - **Protocolo de resolución de direcciones ARP(Address Resolution Protocol)**: Se sitúa entre la capa de enlace y la capa de red, se utiliza para traducir direcciones IP en direcciones MAC
  - **Control de flujo (Flow Control)**: Este mecanismo evita que un nodo emisor abrume al nodo receptor adyacente con tramas a una velocidad mayor de la que puede procesar, lo que podría causar desbordamiento del búfer y pérdida de tramas.
  - **Transmisión Half-duplex y Full-duplex**: La Capa de Enlace especifica si los nodos pueden transmitir paquetes al mismo tiempo (full-duplex) o si solo puede transmitir un nodo a la vez (semiduplex).

### Ejercicio 2

- La capa de transorte proporciona comunicación lógica entre procesos de aplicación que se ejecutan en host diferentes (**host a host**). La capa de enlace proporciona la transferencia de paquetes entre nodos adyacentes a través de un único enlace de comunicaciones (**nodo a nodo**).
- Las unidades de datos en la capa de transporte se denominan **segmentos** (TCP) o **datagramas** (UDP), mientras que en la capa de enlace se denominan **tramas** (frames).
- La capa de transporte reside únicamente en los sistemas terminales (hosts). Se implementa principalmente por software como parte del sistema operativo. La capa de enlace reside en los host y routers (en las tarjetas de interfaz de red o adaptadores). Es una combinación de hardware y software.
- la Capa de Transporte amplía la comunicación de host-a-host a proceso-a-proceso, y es responsable de la fiabilidad, el control de flujo y la gestión de la congestión de extremo a extremo, especialmente a través del protocolo TCP. Por su parte, la Capa de Enlace se enfoca en la transferencia de paquetes a través de un único salto o enlace, ocupándose del encapsulado en tramas, el acceso físico al medio y la detección de errores

### Ejercicio 3

- ¿Cómo se identifican dos máquinas en una red Ethernet?
  - Dos máquinas (nodos) en una red Ethernet se **identifican mediante sus direcciones de la capa de enlace**. Es importante destacar que estas direcciones se asignan a los adaptadores de red (tarjetas de interfaz de red, NIC), y no al host o router en sí. Cuando un adaptador emisor quiere enviar una trama a un adaptador de destino, inserta la dirección MAC de destino en la trama y la envía a través de la LAN. El adaptador receptor procesará la trama si la dirección MAC de destino coincide con su propia dirección MAC. Si no hay coincidencia, el adaptador descarta la trama sin pasar el datagrama a la capa de red
- ¿Cómo se llaman y qué características poseen estas direcciones?
  - Estas direcciones se conocen comúnmente como **dirección MAC (Medium Access Control)**. También se denominan dirección LAN o dirección física y sus características principales son:
    - **Longitud**: Tienen 6 bytes de longitud (48 bits) en la mayoría de las redes LAN, incluyendo Ethernet
    - **Formato**: Se suelen expresar en notación hexadecimal (ej: AA:AA:AA:BB:BB:BB)
    - **Unicidad y Permanencia**: Están diseñadas para ser permanentes y son teóricamente globalmente únicas, siendo administradas por el IEEE
    - **Función vs. IP**: Son distintas de las direcciones de la Capa de Red (direcciones IP). Una dirección MAC se utiliza para la comunicación nodo a nodo a través de un único enlace, mientras que una dirección IP identifica de forma unívoca a un host en Internet
- ¿Cuál es la dirección de broadcast en la capa de enlace? ¿Qué función cumple?
  - La dirección de difusión (broadcast) en la Capa de Enlace es una dirección especial utilizada en las redes LAN. Es una cadena compuesta por 48 unos (1) consecutivos, en notación hexadecimal, esta dirección es `FF:FF:FF:FF:FF:FF`. La función principal de esta dirección es hacer que todos los demás adaptadores de la LAN reciban y procesen la trama que se envía

### Ejercicio 4

- Dispositivos de capa de enlace y sus diferencias.
  - **Hub (Repetidor)**
    - Regenera y amplifica la señal. Es transparente para todas las terminales conectadas a el o sea cualquier mensaje enviado a cualquier de los dispositivos conectados a el es visibile para todos los demas.
    - Une dominios de colisión, extendiéndolos. Es un dispositivo sin colisiones. Un Hub es un repetidor multipuerto
  - **Switch (Conmutador)**
    - Dispositivo de conmutación de paquetes de la Capa 2 qure reenvía y filtra tramas basándose en direcciones MAC.
    - Elimina las colisiones. Es un dispositivo plug-and-play. Proporciona una mejora significativa en la tasa de transferencia agregada al aislar los enlaces
  - **Router**
    - Reenvía datagramas basándose en direcciones IP de la Capa de Red. Aunque los routers no son intrínsecamente dispositivos de la capa de enlace, implementan la capa de enlace en sus interfaces y son los principales dispositivos de conmutación de paquetes de la capa superior, sirviendo como contraste arquitectónico a los switches de capa 2
    - Procesa hasta la Capa 3. No es plug-and-play (requiere configuración IP).
  - **Bridge (Puente)**
    - Conecta dos segmentos de red, operando en la capa de enlace de datos. Decide si un paquete debe cruzar de un segmento a otro basándose en las direcciones MAC. Un  bridge tradicionalmente tiene solo dos puertos.
- ¿Qué es una colisión?
  - Una colisión ocurre cuando más de dos nodos transmiten tramas al mismo tiempo a través de un canal de difusión compartido. Cuando se produce una colisión, normalmente ninguno de los nodos receptores puede interpretar ninguna de las tramas transmitidas
- ¿Qué dispositivos dividen dominios de broadcast?
  - Los **routers**, ya que son los dispositivos que inherentemente definen los limites de los dominions de difusion.
- ¿Qué dispositivos dividen dominios de colisión?
  - Los switches, un switch elimina las colisiones. Logra esto porque almacena las tramas en buffer y nunca transmite más de una trama a un segmento simultáneamente. Los switches fueron diseñados para no producir colisiones.

### Ejercicio 5

El algoritmo de acceso al medio en Ethernet, CSMA/CD (Carrier Sense Multiple Access with Collision Detection) es necesario en las configuraciones donde varios nodos comparten un mismo canal de comunicación, como en las redes basadas en hubs. Su función principal es resolver el problema del acceso múltiple, detectar y gestionar colisiones, y coordinar las transmisiones entre los dispositivos que utilizan un medio compartido. En cuanto al tipo de servicio, Ethernet no es orientado a la conexión. Proporciona un servicio sin conexión y no confiable a la capa de red, cuando un adaptador quiere enviar un datagrama, simplemente lo encapsula en una trama Ethernet y la transmite, sin establecer previamente ningún tipo de negociación o sesión con el receptor

### Ejercicio 6

El protocolo **ARP (Address Resolution Protocol)** tiene como finalidad obtener la dirección MAC correspondiente a una dirección IP dentro de la misma red local. Esta resolución es necesaria porque, para enviar una trama a través de una LAN, la capa de enlace debe conocer la dirección MAC del siguiente salto (ya sea el host de destino si está en la misma subred, o el router si está en otra). Sin ARP, los dispositivos no podrían entregar tramas correctamente dentro de la red local.

### Ejercicio 7

#### Inciso a

- **i)** Si la estación 1 envía una trama al servidor 1, lo escucharán todos los dispositivos conectados al hub, es decir, Estación 2, 3, 4 y 5, y Servidor 1.
- **ii)** Si la estación 1 envía una trama a la estación 11, lo escucharán todos los dispositivos conectados al primer hub, es decir, Estación 2, 3, 4, 5 y 11, y Servidor 1.
- **iii)** Si la estación 1 envía una trama a la estación 9, lo escucharán todos los dispositivos conectados al  primer hub, además de todos los dispositivos conectados al segundo hub, es decir, Estación 2, 3, 4, 5, 8, 9 y 10, y Servidor 1.
- **iv)** Si la estación 4 le envía una trama a la MAC de broadcast, lo escucharán todos los dispositivos conectados al primer hub y todos los dispositivos conectados al mismo switch que la estación 4, es decir, Estación 1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12 y 13, y Servidor 1.
- **v)** Si la estación 6 envía una trama a la estación 7, lo escuchará solo la estación 7, ya que están conectadas al mismo switch.
- **vi)** Si la estación 6 envía una trama a la estación 10, lo escucharán todos los dispositivos conectados al segundo hub, es decir, Estación 8, 9 y 10.

##### Inciso b

Las colisiones solo pueden producirse en los segmentos de la red donde el medio es compartido, es decir, en los hubs. Ocurren cuando dos o más dispositivos conectados al mismo hub transmiten simultáneamente. En esta red, pueden ocurrir colisiones únicamente en el hub donde están Estaciones 1 a 5 y el Servidor 1, y en el hub donde están Estaciones 8, 9 y 10. En los enlaces con switches no pueden ocurrir colisiones.
- NO hay colisiones en los switches, porque cada enlace entre un host y un switch es full-duplex y dedicado.
- NO hay colisiones entre Switch1 y Switch2, porque ese enlace también es full-duplex.
- NO hay colisiones entre dispositivos conectados directamente a un switch (como Estación 6, 7, 11, 12 o 13).

### Ejercicio 8

#### Inciso a

Hay 5 dominios de colisión en total:
- La conexión de PC A a Switch 2
- La conexión de PC B a Switch 2
- La conexión de PC E a Switch 2
- La conexión de Switch 2 a Switch 1
- La conexión de HUB a Switch 1

>[!note] NOTA
> Las conexiones de PCs a HUBS no cuentan como dominios de colisión ya que todos los nodos conectados al HUB pueden colisionar con los demás.

#### Inciso b

Hay solo 1 dominio de broadcast. Los dominios de broadcast se separan con el uso de routers, solo un dominio de broadcast por red.

#### Inciso c

- A envía una solicitud ARP consultando la MAC de C
  - Switch 1 
    |MAC|Port|
    |---|----|
    |MAC_PC_A  |Port_Switch2_Switch1   |  
  - Switch 2
    |MAC|Port|
    |---|----|
    |MAC_PC_A  |Port_PC_A_Switch2   |
- C responde esta solicitud ARP
  - Switch 1 
    |MAC|Port|
    |---|----|
    |MAC_PC_A  |Port_Switch2_Switch1   |  
    |MAC_PC_C  |Port_Hub_Switch1   |
  - Switch 2
  - |MAC|Port|
    |---|----|
    |MAC_PC_A  |Port_Switch2_Switch1  |
    |MAC_PC_C  |Port_Switch1_Switch2  |
- A envía una solicitud ARP consultando la MAC de B
  - Switch 1 
    |MAC|Port|
    |---|----|
    |MAC_PC_A  |Port_Switch2_Switch1   |  
    |MAC_PC_C  |Port_Hub_Switch1   |
    |MAC_PC_A| Port_Switch2_Switch1   |
  - Switch 2
    |MAC|Port|
    |---|----|
    |MAC_PC_A  |Port_Switch2_Switch1  |
    |MAC_PC_C  |Port_Switch1_Switch2  |
    |MAC_PC_A| Port_PC_A_Switch2   |
- B responde esta solicitud ARP
  - Switch 1 
    |MAC|Port|
    |---|----|
    |MAC_PC_A  |Port_Switch2_Switch1   |  
    |MAC_PC_C  |Port_Hub_Switch1   |
    |MAC_PC_A| Port_Switch2_Switch1   |
  - Switch 2
    |MAC|Port|
    |---|----|
    |MAC_PC_A  |Port_Switch2_Switch1  |
    |MAC_PC_C  |Port_Switch1_Switch2  |
    |MAC_PC_A| Port_PC_A_Switch2   |
    |MAC_PC_B| Port_PC_B_Switch2   |

#### Inciso d

- La PC E hubiese escuchado solamente las ARP Requests (i y iii), ya que son tráfico de broadcast. Las respuestas ARP (unicast) son filtradas por el Switch 2 y entregadas solo al destinatario (PC A). Por otro lado, la PC D hubiese escuchado las ARP Requests y la respuesta de C (paso ii), dado que al estar conectada a un Hub, este repite el tráfico saliente de C hacia sus puertos. Sin embargo, PC D NO escucharía la respuesta de B (paso iv), ya que ese tráfico es unicast local dentro del Switch 2 y nunca viaja hacia el segmento del Hub.

### Ejercicio 9

- Si la PC_A le hace un ping a la PC_C, habría tráfico en todos los dominios de broadcast ya que para que el mensaje llegue desde PC_A hasta PC_C, debe pasar por Router_1, por Router_2, luego por Router_3 y finalmente llegar a PC_C, lo que cubre todos los dominios de broadcast.
  - Primer dominio de broadcast: entre PC_A y Router_1
    - Request
      |Mac Origen|IP Origen|Mac Destino|IP Destino|
      |---|----|----|----|
      |8888:8888:8888|190.26.11.24/24|00:00:00:00:00:00|190.26.11.1/24|
    - Reply
      |Mac Origen|IP Origen|Mac Destino|IP Destino|
      |---|----|----|----|
      |7777:7777:7777|190.26.11.1/24|8888:8888:8888|190.26.11.24/24|
  - Segundo dominio de broadcast: entre Router_1 y Router_2
    - Request
      |Mac Origen|IP Origen|Mac Destino|IP Destino|
      |---|----|----|----|
      |bbbb:bbbb:bbbb|200.100.11.1/30|00:00:00:00:00:00|200.100.11.2/30|
    - Reply
      |Mac Origen|IP Origen|Mac Destino|IP Destino|
      |---|----|----|----|
      |cccc:cccc:cccc|200.100.11.2/30|bbbb:bbbb:bbbb|200.100.11.1/30|
  - Tercer dominio de broadcast: entre Router_2 y Router_3
    - Request  
      |Mac Origen|IP Origen|Mac Destino|IP Destino|
      |---|----|----|----|
      |eeee:eeee:eeee|200.100.11.5/30|00:00:00:00:00:00|200.100.11.6/30|
    - Reply
      |Mac Origen|IP Origen|Mac Destino|IP Destino|
      |---|----|----|----|
      |dddd:dddd:dddd|200.100.11.6/30|eeee:eeee:eeee|200.100.11.5/30|
  - Cuarto dominio de broadcast: entre Router_3 y PC_C
    - Request
      |Mac Origen|IP Origen|Mac Destino|IP Destino|
      |---|----|----|----|
      |4444:4444:4444|190.26.12.1/24|00:00:00:00:00:00|190.26.12.65/24|
    - Reply
      |Mac Origen|IP Origen|Mac Destino|IP Destino|
      |---|----|----|----|
      |5555:5555:5555|190.26.12.65/24|4444:4444:4444|190.26.12.1/24|

- Hay tráfico ICMP en todos los dominios de broadcast, ya que el ping utiliza el protocolo ICMP para enviar mensajes de eco y recibir respuestas de eco. El tráfico ICMP se genera cuando PC_A envía un mensaje de eco (ping) a PC_C y cuando PC_C responde con un mensaje de respuesta de eco. Este tráfico atraviesa todos los routers y dominios de broadcast en el camino desde PC_A hasta PC_C y viceversa.
  - **CAPA 2: Enlace**
    - Primer dominio de broadcast: entre PC_A y Router_1
      |Mac Origen|Mac Destino|
      |---|----|
      |8888:8888:8888|7777:7777:7777|
    - Segundo dominio de broadcast: entre Router_1 y Router_2
      |Mac Origen|Mac Destino|
      |---|----|
      |bbbb:bbbb:bbbb|cccc:cccc:cccc|
    - Tercer dominio de broadcast: entre Router_2 y Router_3
      |Mac Origen|Mac Destino|
      |---|----|
      |eeee:eeee:eeee|dddd:dddd:dddd|
    - Cuarto dominio de broadcast: entre Router_3 y PC_C
      |Mac Origen|Mac Destino|
      |---|----|
      |4444:4444:4444|5555:5555:5555|
  - **CAPA 3: Red**
    - Primer dominio de broadcast: entre PC_A y Router_1
      |IP Origen|IP Destino|
      |----|----|
      |190.26.11.24/24|190.26.11.1/24|
    - Segundo dominio de broadcast: entre Router_1 y Router_2
      |IP Origen|IP Destino|
      |----|----|
      |200.100.11.1/30|200.100.11.2/30|
    - Tercer dominio de broadcast: entre Router_2 y Router_3
      |IP Origen|IP Destino|
      |----|----|
      |200.100.11.5/30|200.100.11.6/30|
    - Cuarto dominio de broadcast: entre Router_3 y PC_C
      |IP Origen|IP Destino|
      |----|----|
      |190.26.12.1/24|190.26.12.65/24|

- Primero ocurre el tráfico ARP para resolver las direcciones MAC correspondientes a cada salto en el camino desde PC_A hasta PC_C. Luego, una vez que las direcciones MAC están resueltas, se genera el tráfico ICMP para el ping entre PC_A y PC_C. El tráfico ARP es necesario para que cada dispositivo pueda enviar tramas correctamente a través de la red, mientras que el tráfico ICMP es el que realmente lleva los mensajes de eco y las respuestas de eco del ping.

### Ejercicio 10

Si la PC A está en una red y se quiere comunicar con la PC B que está en otra red
- PC A se da cuenta de que PC B está en otra red al comparar su propia dirección IP y máscara de subred con la dirección IP de PC B. Si las direcciones no pertenecen a la misma subred, PC A determina que PC B está en una red diferente.
- Si la tabla ARP de PC A está vacía, necesitará la MAC del router que sepa como llegar a la red de PC B. Esta MAC la obtiene enviando una solicitud ARP (ARP Request) en broadcast dentro de su red local.
- En base a lo anterior
  - Trama Ethernet: (**mac_origen**:<u>MAC_PC_A</u> **mac_destino**:<u>FF:FF:FF:FF:FF:FF</u>)
  - Solicitud ARP:   
    (**mac_origen**:<u>MAC_PC_A</u> **ip_origen**:<u>IP_PC_A</u>  
    **mac_destino**:<u>00:00:00:00:00:00</u> **ip_destino**:<u>IP_Router</u>)

- Información de capa 2 y 3 del ICMP ECHO REQUEST que la PC A envía a la PC B cuando ejecuta un ping, en el segmento de LAN de la PC B.
  - CAPA 2: Enlace
    - **mac_origen**:MAC_Router 
    - **mac_destino**:MAC_PC_B
  - CAPA 3: Red
    - **ip_origen**:IP_PC_A 
    - **ip_destino**:IP_PC_B
  - >[!note] NOTA
    > La MAC_Router hace referencia al router final antes de llegar a PC B




