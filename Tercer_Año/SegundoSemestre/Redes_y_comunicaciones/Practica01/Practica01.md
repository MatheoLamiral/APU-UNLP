# Práctica 1: Introducción

### Ejercicio 1: ¿Qué es una red? ¿Cuál es el principal objetivo para construir una red?

Una red es un grupo de computadoras o dispositivos interconectados, con el objetivo de compartir recursos, como información, servicios, etc. El conjunto de computadoras, software de red, medios y dispositivos de interconexión forma un sistema de comunicación, como por ejemplo, red universitara o internet.

### Ejercicio 2: ¿Qué es Internet? Describa los principales componentes que permiten su funcionamiento.

Internet es una red de redes de computadoras, descentralizada, pública, que ejecutan el conjunto abierto de protocolos TCP/IP. Sin embargo, teniendo en cuenta la cantidad de dispositivos que actualmente se conectan a internet, el concepto de red de computadoras está algo desactualizado. 
- Principales componentes que definen su funcionamiento:
  - Todos estos dispositivos se denominan **hotsts** o **sistemas ternimales**
  - Los sistemas terminales se conectan entre sí mediante una red de **enlaces de comunicaciones** y dispositivos de **conmutación de paquetes**.
  - Cuando un sistema terminal tiene que enviar datos a otro sistema terminal, el emisor segmenta los datos y añade bits de cabecera a cada segmento. Los paquetes de información resultantes, conocidos como **paquetes**, se envían entonces a través de la red hasta el sistema terminal receptor, donde vuelven a ser ensamblados para obtener los datos originales.
  - Un **conmutador de paquetes** toma el paquete que llega de uno de sus enlaces de comunicaciones de entrada y lo reenvía a uno de sus enlaces de comunicaciones de salida. Los dos tipos más utilizados actualmente en internets son los routers (normalmente se usan en el núcleo de la red) y los switches de la capa de enlace (normalmente se usan en la capa de acceso)
  - La secuencia de enlaces de comunicaciones y conmutadores de paquetes que atraviesa un paquete desde el sistema terminal emisor hasta el sistema terminal receptor se conoce como **ruta** a través de la red
  - Los sistemas terminales acceden a Internet a través de los **ISP** (Internet Service Provider, Proveedor de servicios de Internet). Cada ISP es en sí mismo una red de conmutadores de paquetes y enlaces de comunicaciones. Los ISP proporcionan una amplia variedad de tipos de acceso a red a los sistemas terminales. Como internet es todo lo que conecta a los sistemas terminales entre sí, los ISP que proporcionan el acceso a los sistemas terminales también tienen que estar interconectados entre ellos. Estos ISP de nivel inferior se interconectan a través de los ISP de nivel superior nacionales e internacionales
  - Los sistemas terminales, los conmutadores de paquetes y otros dispositivos de Internet ejecutan **protocolos** que controlan el envío y la recepción de la información dentro de Internet. El protocolo **TCP** (Transmission Control Protocol, Protocolo de control de transmi- sión) y el protocolo **IP** (Internet Protocol, Protocolo de Internet) son dos de los protocolos más importantes de internet. Los principales protocolos de internet se conocen colectivamente como **protocolos TCP/IP**
  - Debido a la importancia de los protocolos en Internet, es importante que todo el mundo esté de acuerdo en qué hacen todos y cada uno de ellos, siendo aquí donde entran en juego los **estándares**. Los estándares de Internet son desarrollados por el **IETF** (Internet Engineering Task Force). Los documentos asociados a estos estándares IETF se conocen como documentos **RFC** (Request For Comments, Solicitud de comentarios)

### Ejercicio 3: ¿Qué son las RFCs?

Las RFCs (Request For Comments) son documentos que describen especificaciones, protocolos, procedimientos, buenas prácticas o estándares relacionados con el funcionamiento de internet y redes de computadoras. Cada RFC está numerada de forma única y no cambia una vez publicada, si se actualiza, se crea una nueva con otro número. Algunas RFC son simplemente informativas, otras experimentales, y solo algunas se convierten en estándares oficiales de Internet.

### Ejercicio 4: ¿Qué es un protocolo?

Un **protocolo** es el conjunto de conductas y normas a conocer, respetar y cumplir no sólo en el medio oficial ya establecido, sino también en el medio social, laboral, etc. Un protocolo define el formato, el orden de los mensajes intercambiados y las acciones que se llevan a cabo en la transmisión y/o recepción de un mensaje u otro evento. Un **protocolo de red** es el conjunto de reglas que especifican el intercambio de datos u órdenes durante la comunicación entre las entidades que forman parte de una red; permiten la comunicación y están implementados en los componentes. Por ejemplo los protocolos TCP/IP son los estándares en torno a los cuales se desarrolló Internet, de modo que la credibilidad del modelo TCP/IP se debe en gran parte a sus protocolos

### Ejercicio 5: ¿Por qué dos máquinas de distintos sistemas operativos pueden formar parte de una misma red?

dos máquinas con diferentes sistemas operativos pueden formar parte de la misma red y comunicarse entre sí gracias a que la red está organizada en capas (como aplicación, transporte, red, enlace y física)y cada capa tiene protocolos estandarizados (como TCP/IP) que definen cómo se comunican los sistemas. Esto significa que, sin importar el sistema operativo o el hardware subyacente, mientras sigan los mismos protocolos, pueden interactuar. Los protocolos de red también incluyen mecanismos para describir los tipos de datos de forma independiente de la máquina o del sistema operativo, resolviendo diferencias como el orden de bytes (big-endian/little-endian) al transmitir información. En esencia, la red actúa como un lenguaje común que todos los sistemas pueden "hablar" y "entender", independientemente de su "idioma nativo" (sistema operativo o hardware)

### Ejercicio 6: ¿Cuáles son las 2 categorías en las que pueden clasificarse a los sistemas finales o End Systems? Dé un ejemplo del rol de cada uno en alguna aplicación distribuida que corra sobre internet 

Los sistemas terminales o hosts se pueden clasificar principalmente en dos categorías en el contexto del software de red, **clientes** y **servidores**

- **Clientes**: Un programa cliente es aquel que se ejecuta en un sistema terminal y solicita y recibe un servicio de un programa servidor que se ejecuta en otro sistema terminal. En el contexto de una sesión de comunicación, el cliente es el proceso que inicia la comunicación, es decir, el que contacta inicialmente al otro proceso al principio de la sesión
  - Por ejemplo, en la aplicación Web, un navegador (como Firefox o Chrome) que se ejecuta en el host del usuario es el proceso cliente. Este programa inicia la comunicación enviando solicitudes HTTP a un servidor web para obtener documentos, imágenes o videos.

- **Servidores**: Un programa servidor es aquel que se ejecuta en un sistema terminal y proporciona un servicio a un programa cliente. Es el proceso que espera ser contactado para comenzar la sesión de comunicación. Los servidores suelen ser equipos más potentes que almacenan y distribuyen páginas web, flujos de vídeo o correo electrónico
  - Por ejemplo, en la Web, un servidor web (por ejemplo, un servidor Apache) que se ejecuta en un host servidor está siempre activo y tiene una dirección IP fija y conocida. Su función es servir las solicitudes HTTP que recibe de los navegadores cliente, enviándoles los objetos solicitados

### Ejercicio 7: ¿Cuál es la diferencia entre una red conmutada de paquetes de una red conmutada de circuitos?

La principal diferencia entre una red conmutada de paquetes y una red conmutada de circuitos es que, en las redes conmutadas de circuitos los recursos necearios a lo largo de una ruta (como buferes y velocidad de transmisión del enlace) se reservan durante toda la comunicación entre los sistemas trerminales, lo que establece una conexión de buena fe o "circuito", donde el ancho de banda pra la conexión emisor-receptor esta garantizado y es constante, incluso durtante periodos de inactividad, lo que puede ser ineficiente. En cambio, en las redes conmutadas de paquetes, los recursos no estan reservados, los mensajes de una sesión utilizan los recursos bajo petición, compartiendo la capacidad de transmisión del enlace paquete paquete solo entre usuarios que tienen paquetes para transmitir, lo que puede llegar a generar retardos si un enlace está congestionado debido a que otros paquetes se transmiten al mismo tiempo. Este último enfoque se conoce como multiplexación estadística.

### Ejercicio 8: Analice qué tipo de red es una red de telefonía y qué tipo de red es Internet.
Las redes telefónicas son ejemplos de redes de conmutación de circuitos, cuando una persona desea enviar información a otra a través de una red telefónica, antes de que el emisor pueda transmitir la información, la red debe establecer una conexión entre el emisor y el receptor.  
Internet utiliza la técnica de de conmutación de paquetes. Cuando un host desea enviar un paquete a otro host a través de Internet, al igual que con la conmutación de circuitos, el paquete se transmite a través de una serie de enlaces de comunicaciones. Pero con la técnica de conmutación de paquetes, el paquete se envía a la red sin haber reservado ancho de banda. Si uno de los enlaces está congestionado porque otros paquetes tienen que ser transmitidos a través de él al mismo tiempo, entonces nuestro paquete tendrá que esperar en un buffer en el lado del emisor del enlace de transmisión y, por tanto, sufrirá un retardo. Internet realiza el máximo esfuerzo para suministrar los paquetes a tiempo, pero no existe ninguna garantía.

### Ejercicio 9: Describa brevemente las distintas alternativas que conoce para acceder a Internet en su hogar.

Existen diversas alternativas para acceder a Internet desde el hogar:

- **DSL** (Digital Subscriber Line): Utiliza la línea telefónica de hilo de cobre existente y permite la transmisión simultánea de datos y llamadas telefónicas. Es una conexión "siempre activa"
- **HFC** (Hybrid Fiber Coaxial) o Acceso por Cable: Se basa en la infraestructura de televisión por cable. Requiere módems por cable y es un medio de difusión compartido, lo que significa que el ancho de banda se distribuye entre los usuarios de un mismo nodo
- **WiFi** (Redes LAN Inalámbricas 802.11): Es una tecnología inalámbrica que permite a los dispositivos conectarse a Internet a través de un punto de acceso (AP)
- **Acceso Celular a Internet** (3G y 4G): Utiliza la infraestructura de telefonía móvil. Las redes 3G ofrecen acceso inalámbrico a Internet con velocidades superiores a 1 Mbps y soportan tanto voz como datos. Tecnologías como HSDPA/HSUPA, consideradas "3G mejorado" o "pre-4G", pueden alcanzar hasta 14 Mbps

### Ejercicio 10: ¿Qué ventajas tiene una implementación basada en capas o niveles?

El modelo en capas o Layering consiste en dividir la complejidad en componentes reusables, lo cual tiene varias ventajas:

- Reduce la complejidad en componentes más pequeños 
- Las capas de abajo ocultan la complejidad a las de arriba, generando abstracción
- Las capas de arriba utilizan servicios de las de abajo, mediante interfaces similares a APIs
- Los cambios en una capa no deberían afectar a las demás si la interfaz se mantiene.
- Facilita el desarrollo y la evolución de los componentes de red asegurando interoperabilidad.
- Facilita aprendizaje, diseñoo y administración de las redes.

### Ejercicio 11: ¿Cómo se llama la PDU de cada una de las siguientes capas: Aplicación, Transporte, Red y Enlace?

- **Capa de aplicación**: La PDU de la capa de aplicación se denomina **datos**
- **Capa de transporte**: Los paquetes de la capa de transporte se conocen como **segmentos**
- **Capa de red**: Los paquetes de la capa de red se denominan **paquete**
- **Capa de enlace**: Las unidades de datos intercambiadas por un portocolo de la capa de enlace se conocen como **tramas** (frames)

### Ejercicio 12: ¿Qué es la encapsulación? Si una capa realiza la encapsulación de datos, ¿qué capa del nodo receptor realizará el proceso inverso?

La **encapsulación** consiste en que cada capa define su propio PDU(Protocol Data Unit). Es el proceso mediante el cual, cuando un mensaje se envía desde la capa de aplicación hacia la red, cada capa del protocolo añade información de control en forma de cabeceras. Esa información se usa para que la capa correspondiente del receptor pueda interpretar correctamente los datos.
Si una capa realiza la encapsulación de los datos en el emisor, la misma capa en el nodo receptor realiza el proceso inverso, conocido como **desencapsulación**, para interpretar la información y entregar los datos a la capa superior.

### Ejercicio 13: Describa cuáles son las funciones de cada una de las capas del stack TCP/IP o protocolo de Internet.
El stack TCP/IP, también conocido como la pila de protocolos de Internet, está compuesto por cinco capas principales:

- **Capa física**: Se encarga de mover los bits individuales dentro de la trama de un nodo a otro, es la capa más baja de la pila
- **Capa de enlace**: Se encarga de trasladar las tramas completas de un elemento de la red al elemento de red adyacente. Recibe los datagramas de la capa de red, los encapsula en tramas y los envía a través de un único enlace hasta el siguiente nodo
- **Capa de red**: Es responsable de trasladar los paquetes de la capa de red, llamados datagramas, de un host a otro a través de la red. Proporcionan comunicación lógica entre hosts
- **Capa de transporte**: Transporta los mensajes de la capa de aplicación entre los puntos terminales de la aplicación, es decir, proporciona una comunicación lógica entre procesos de aplicación que se ejecutan en hosts diferentes
- **Capa de aplicación**: Es la capa donde residen las aplicaciones de red y sus protocolos. Permite a los programas de software en diferentes sistemas terminales intercambiar datos
  
### Ejercicio 14: Compare el modelo OSI con la implementación TCP/IP.
- Similitudes
  - Ambos se dividen en capas.
  - Ambos tienen capas de aplicación, aunque incluyen servicios distintos.
  - Ambos tienen capas de transporte similares.
  - Ambos tienen capa de red similar pero con distinto nombre.
  - Se supone que la tecnología es de conmutación de paquetes (no de conmutación de circuitos).
- Diferencias
  - TCP/IP combina las funciones de la capa de presentación y de sesión en la capa de aplicación.
  - TCP/IP combina la capas de enlace de datos y la capa física del modelo OSI en una sola capa.
  - TCP/IP más simple porque tiene menos capas.
  - Los protocolos TCP/IP son los estándares en torno a los cuales se desarrolló Internet, de modo que la credibilidad del modelo TCP/IP se debe en gran parte a sus protocolos.
  - El modelo OSI es un modelo “más” de referencia, teórico, aunque hay implementaciones.