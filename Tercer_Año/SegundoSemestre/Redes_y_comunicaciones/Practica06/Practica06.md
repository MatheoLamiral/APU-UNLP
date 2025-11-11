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

El **multicast** es un servicio de la capa de red que permite que **un paquete enviado desde un único nodo de origen sea entregado a un subconjunto de los restantes nodos de la red**. Implica la transferencia de datos desde un emisor a muchos receptores en una única operación. El multicast o multidifusión es fundamental para aplicaciones que requieren la entrega de paquetes a un grupo de receptores, como la transferencia masiva de datos, flujos multimedia continuos (audio, video, videoconferencias, etc.), juegos en línea, entre otros.
- El multicast **opera principalmente sobre UDP**, ya que permite enviar datos a una velocidad constante sin que esta sea regulada por la red, lo cual es vital para las transacciones en tiempo real, además de que no hay retardo de establecimiento de conexión.
- El multicast n**o es compatible con TCP**, ya que TCP es inherentemente una conexión punto a punto, es decir, un único emisor y un único receptor. Además, los mecanismos que definen a TCP, como la transferencia de datos fiable y el control de congestión, introducen retardos que son inaceptables para las aplicaciones interactivas en tiempo real que generalmente utilizan multicast.

### Ejercicio 3

**FTP (File Transfer Protocol)** es un protocolo de la capa de aplicación diseñado para la **transferencia de archivos entre sistemas terminales**. Al igual que HTTP, se ejecuta por encima de TCP. 
- Sin embargo, FTP es un protocolo único en la capa de aplicación porque utiliza dos conexiones TCP paralelas para transferir un archivo.
  - Conexión de control: se establece cuando el usuario inicia una sesión FTP con el host remoto. Utiliza el puerto 21 de TCP y se emplea para enviar información de control entre el cliente y el servidor, como identificación del usuario, contraseñas, comandos, etc. La conexión permanece abierta durante toda la sesión del usuario y los comandos y respuestas se envían en formato ASCII de 7 bits.
  - Conexión de datos: se utiliza exclusivamente para envíar el archivo. Cuando el servidor recibe un comando para realizar una transferencia de archivo a través de la conexión de control, el lado del servidor inicia una conexión de datos TCP con el lado del cliente. FTP envía exactamente un archivo a través de la conexión de datos y luego cierra la conexión, por lo tantom las conexiones de datos no son persistentes, si el usuario deseara transferir otro archivo, se abre una nueva conexión de datos 
  - A diferencia de muchos otros protocolos web, FTP es un protocolo con memoria del estado (stateful). A lo largo de la sesión, el servidor FTP tiene que mantener un estado del usuario. Específicamente, debe asociar la conexión de control con una cuenta de usuario específica y estar al tanto del directorio actual del usuario cuando este se mueve por el árbol del directorio remoto
  - Debido a que la información de control se envía a través de una conexión separada a los datos, se dice que FTP envía su información de control fuera de banda (out of band)
- **Modo Activo**
  - En el modo activo, el cliente le dice al servidor que puerto utilizará para la conexión de datos y luego espera a que el servidor inicie la conexión.
  - Este modo es problemático para los firewalls (sistema de seguridad para redes que actúa como una barrera entre una red interna confiable y redes externas no confiables) y NAT modernos. El firewall del cliente ve la conexión entrante del servidor como no solicitada y la bloquea por seguridad.
- **Modo Pasivo**
  - En el modo pasivo, el cliente le pregunta al servidor qué puerto utilizará para la conexión de datos y luego el cliente inicia la conexión.
  - Este modo es más compatible con los firewalls porque ambas conexiones (control y datos) son iniciadas por el cliente (son salientes). Los firewalls del lado del cliente permiten este tráfico sin problemas. Por esta razón, el modo pasivo es el más utilizado.
- **Diferencia entre FTP y otros protocolos de aplicación**:
  - La principal diferencia de FTP con los otros protocolos es que FTP utiliza un canal "fuera de banda" (out-of-band) para los datos. En FTP, los comandos (control) viajan por un canal (puerto 21) y los datos viajan por un canal completamente separado (un puerto variable). En HTTP, HTTPS, SSH, SMTP, etc., todo viaja por un único canal. Esta arquitectura de doble canal de FTP es la que lo hace más complejo y la razón por la que necesita los modos activo y pasivo para negociar el canal de datos, algo que los otros protocolos no necesitan hacer.
  
### Ejercicio 4

La numeración de los ACK que el host B envía al host A en la figura es:
- primer ACK = 0
- segundo ACK = 1
- tercer ACK = 3
- cuarto ACK = 4
- quinto ACK = 5
- sexto ACK = 2

### Ejercicio 5

La reestricción que existe sobre el tamaño de las ventanas en el protocolo Selective Repeat es que el tamaño de la ventana tiene que ser menor o igual que la mitad del tamaño del espacio de números de secuencia (W <= M/2). Esta limitación es crucial para evitar que el receptor confunda un paquete retransmitido antiguo con un paquete nuevo. Debido a que los número de secuencia son finitos y se reutilizan, el receptor podría confundir dos escenarios idénticos que tienen consecuencias de procesamiento muy diferentes.

### Ejercicio 6

### Ejercicio 7

### Ejercicio 8

El **RTT (Round Trip Time/Tiempo de ida y vuelta)** es la cantidad de tiempo que transcurre desde que se envía un paquete pequeño o un segmento desde un extremo (cliente) hasta que se recibe su correspondiente paquete de reconocimiento (ACK) de vuelta al origen. Es una medida fundamental del rendimiento de la red, ya que incluye todos los retardos acumulados en el camino.
- El RTT se calcula de la siguiente manera:
  - TCP estima el RTT mediante un valor de promedio que se ajusta dinámicamente, ya que el RTT de la red es variable
  - El RTT_muestra es el tiempo medido entre el envío de un segmento y la recepción de su ACK correspondiente. No se calcula para los segmentos retransmitidos, ya que sería imposible determinar a cuál de las transmisiones corresponde el ACK recibido.
  - Debido a la fluctuación del RTT_muestra, TCP mantiene un valor promedio, denominado RTT_estimado, que se actualiza cada vez que se recibe un nuevo ACK utilizando la siguiente fórmula ponderada:
    - RTT_estimado = (1 - α) * RTT_estimado + α * RTT_muestra
- Opción TCP Timestamp
  - Es una característica opcional que puede utilizarse para mejorar la estimación del RTT. La capacidad de utilizar esta opción se negocia durante el establecimiento de la conexión TCP en el primer segmento SYN. Cuando se utiliza, la opción Timestamp relaja la necesidad de mantener un temporizador por cada segmento para estimar RTT.
  - Campos TSval y TSecr
    - Cuando la opción Timestamp está activa, se incluyen dos campos de 32 bits en la cabecera de los segmentos TCP (dentro del campo opciones)
    - **TSval (Timestamp Value)**: Es la marca de tiempo local del emisor del segmento. Cuando un host envía un segmento, copia el valor de su reloj local en este campo.
    - **TSecr (Timestamp Echo Reply)**: Es el eco de la última marca de tiempo recibida del otro extremo. El emisor lo utiliza para copiar el TSval del segmento más reciente que ha recibido del receptor.

### Ejercicio 9

- Inciso a
  - Hay 6 intentos de conexión TCP
- Inciso b
  - La fuente (IP-port) es `10.0.2.10:46907` y el destino (IP-port) es `10.0.4.10:5001`
- Inciso c
  - De las 6 conexiones TCP, 4 fueron exitosas. Las dos que fueron fallidas se diferencian de las que no, en que el destino responde al SYN de la fuente con un RST(Reset) ACK en lugar de un SYN ACK. EL flag de RST se encuentra activado indicando que la conexión fue rechazada.
- Inciso d
  - i
    - La conexión es iniciada por (IP:port) `10.0.2.10:46907`
  - ii
    - El servidor es `10.0.4.10:5001` y el cliente es `10.0.2.10:46907`
  - iii
    - El 3-way handshake se ve en los segmentos 3, 4 y 5 (3 SYN, 4 SYN ACK, 5 ACK)
  - iv
    - Los ISNs que se intercambian son 0 (2218428254) en el segmento 3 (`10.0.2.10:46907` con el SYN), 0 (1292618479) en el segmento 4 (`10.0.4.10:5001` con el SYN ACK)
    - El ISN es el primer número de secuencia que un cliente o servidor utiliza al iniciar una conexión TCP. Luego de la negociación inicial, ambos lados incrementan sus números de secuencia a medida que envían datos.
  - v
    - Se negoció un MSS de 1460 bytes
  - vi
  - en wireshark statistic -> conversations -> TCP, vemos que B (`10.0.4.10:5001`) transfiere 27k bytes hacia A (`10.0.2.10:46907`), y que A transfiere 822k hacia B, por ende el host que envió mayor cantidad de datos es A
- Inciso e
  - El primer segmento de datos es el 6, origen `10.0.2.10:46907` y destino `10.0.4.10:5001`, tiempo 0.151747000 y seq=1 (2218428255)
  - i
    - El campo len es Len=24, indicando que lleva 24 bytes de datos
  - ii
    - Es confirmado en el segmento 7, tiempo desde el primer frame 0.151846000 y número de sequencia seq=1(1292618480), con el ACK=25 indicando que recibio 24 bytes y espera a partir del 25
  - iii
    - La confirmación confirma 25 bytes con ACK=25
- Inciso f
  - El cierre de conexión lo inicia el cliente `10.0.2.10:46907` utilizando los flags FIN, PUSH y ACK en la fila 958, tiempo 75.090117000 y número de secuencia seq=786289(2219214543). Luego el servidor responde con un FIN, ACK en la fila 959, tiempo 75.091640000 y número de secuencia seq=1(1292618480). Finalmente el cliente responde con un ACK en la fila 960, tiempo 75.247378000 y número de secuencia seq=786458 (2219214712).
  
### Ejercicio 10

- Inciso a
  - El control de flujo es activado y controlado por el receptor. Se realiza de extremo a extremo (End-to-End) en cada conexión TCP. Durante el establecimiento de la conexión, se define un buffer de recepción(Rx Buffer) en el lado del receptor. El receptor mantiene una variable dinámica llamada ventana de recepción (RcvWindow), que representa la cantidad de espacio libre disponible en ese buffer de recepción. La RcvWindow se calcula restando a la capacidad todal del buffer los bytes que han llegado al buffer pero que la apliacaión aún no ha leído. El receptor anuncia este valor de la ventana en el campo correspondiente del encabezado de cada segmento que envía de vuelta al transmisor. El emisor debe monitorear constantemente este valor y limitar la cantidad de datos sin confirmar que tiene en vuelo (sin ACK) a ese tamaño de ventana anunciado, asegurándose de que `UltimoByteEnviado – UltimoByteReconocido ≤ VentanaRecepcion`
- Inciso b
  - El control de flujo resuelve el problema de sobrecarga del receptor por parte del emisor. Su objetivo principal es prevenir que el emisor sature o deborde el buffer de recepción del receptor al enviar demasiados datos demasiado rápido. Proporciona un servicio de adaptación o equilibrio de velocidades. Esto asegura que la velocidad a la que el emisor está transmitiendo se alinee con la velocidad a la que la aplicación receptora está consumiendo (leyendo) los datos almacenados en su buffer.
- Inciso c
  - El control de flujo está activo durante todo el tiempo de vida de la conexión TCP, ya que la ventana de recepción des una variable dinámica que se ajusta continuamente. Si bien el mecanismo siempre está activo, las restricciones de transmisión que impone pueden activarse o desactivarse de la siguiente manera
    - **Ventana cerrada**: Si la aplicación receptora es lenta y el buffer se llena, el receptor anuncia una ventana igual a 0. El emisor queda bloqueado (se le prohibe enviar más datos de aplicación), limitando el flujo a 0.
    - **Ventana abierta**: La restriccion se levanta cuando la aplicacion receptora lee los datos dle buffer. Al liberar espacio, el receptor calcula una nueva ventana mayor que 0. El receptor envía un segmento de ACK anunciando el nuevo valor positivo de la ventana. El emisor recibe este ACK, actualiza su ventana deslizante, y puede reanudar la transmisión de nuevos datos 
  - Si la ventana es cero y el receptor no tiene datos propios que enviar, el emisor está obligado por la especificación TCP a continuar enviando segmentos de 1 byte de datos periódicamente. Esto se hace para forzar al receptor a enviar un ACK que anuncie el nuevo valor de la ventana tan pronto como haya espacio disponible, evitando así un bloqueo permanente de la conexión

### Ejercicio 11

- Inciso a
  - El mecanismo de control de congestión es activado y gestionado por el emisor (Tx) de la conexión TCP. El emisor limita su tasa de transmisión basándose en la congestión que percibe de la red. Esta limitación se logra mediante una variable dinámica llamada ventana de congestión (CongWin o cwnd). La tasa de transmisión es aproximadamente igual a `VentanadCongestion/RTT`
  - El emisor TCP deduce que existe congestión en al ruta cuando se produce un evento de pérdida. Los dos posibles disparadores de un evento de pérdida son:
    - Timeout: Esto indica una congestión severa porque los paquetes (o sus ACKs) no llegaron, probablemente debido a que los buffers de los routers se desbordaron y se descartaron datagramas.
    - Triple ACK duplicado: Esto sucede cuando el emisor recibe el cuarto ACK para el mismo segmento. Indica que se ha perdido un segmento, pero los paquetes posteriores si lograron llegar y se está en presencia de una congestión menos crítica, ya que la red es capaz de transportar algunos segmentos.
- Inciso b
  - El control de congestión resuelve el problema de que el tráfico enviado sature o colapse la capacidad de la red. Este mecanismo busca prevenir el colapso, regular el flujo y proporcionar equidad. Es crucial diferenciar el control de congestión del control de flujo, ya que este último solo se enfoca en que el emisor no sature el buffer del receptor, mientras que el control de congestión toma en cuenta el estado de la red.
- Inciso c
  | Característica | Slow Start (SS) | Congestion Avoidance (CA) |
  | --- | --- | --- |
  | **Objetivo** | Aumentar la tasa rápidamente al inicio de la conexión | Tentar el ancho de banda disponible con cautela |
  | **Inicio** | Cuando la conexión comienza, CongWin se fija en 1 MSS  | Cuando CongWin alcanza o supera el valor del Umbral (ssthresh) |
  | **Tipo de Crecimiento** | Exponencialmente rápido  | Linealmente (Aditivo), conocido como AIMD (Additive-Increase, Multiplicative-Decrease)  |
  | **Mecanismo de Incremento** | CongWin aumenta en 1 MSS por cada ACK recibido . Esto resulta en duplicar CongWin cada RTT | CongWin aumenta aproximadamente en 1 MSS por cada RTT . Esto se logra incrementando CongWin en MSS x (MSS/CongWin) por cada ACK recibido |
  | **Reacción a Timeout (Pérdida Severa)** | El Umbral se fija en CongWin/2 y CongWin se fija en 1 MSS, reiniciando el proceso de SS | El Umbral se fija en CongWin/2 y CongWin se fija en 1 MSS, reiniciando el proceso de SS |
  | **Reacción a 3 DUP ACKs (Reno)** | Termina SS y pasa al estado de Recuperación Rápida (Fast Recovery), sin pasar por CA | CongWin se reduce a la mitad (CongWin = Umbral + 3) y continúa creciendo linealmente (si no hay otro evento de pérdida) |

### Ejercicio 12

### Ejercicio 13

- Suponiendo los siguientes comandos desde un host con IP 10.100.25.90.
  - Inciso a
    - `hping3 -p 3306 –udp 10.100.25.135`
  - Inciso b
    - `hping3 -S -p 25 10.100.25.135`
  - Inciso c
    - `hping3 -S -p 22 10.100.25.135`
  - Inciso d
    - `hping3 -S -p 110 10.100.25.135`
- La cantidad de conexiones distintas que estan establecidas es ...

