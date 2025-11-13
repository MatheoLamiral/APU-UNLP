# Práctica 2 - Capa de aplicación HTTP

### Ejercicio 1

La función de la capa de aplicación es proporcionar servicios de red directamente a las aplicaciones del usuario final como navegadores web, aplicaciones FTP, etc. Es la capa más cercana al usuario en el modelo TCP/IP

### Ejercicio 2

Cuando dos procesos se ejecutan en **sistemas terminales diferentes**, se comunican intercambiando mensajes a través de la red. Esta comunicación se gestiona principalmente en la capa de aplicación de la pila de protocolos, y se debe tener en cuenta:
- **Mensajes y protocolos de aplicación**: Un protocolo de la capa de aplicación define cómo los procesos intercambian mensajes, reglas de sintáxis, semántica de los campos y demás.
- **Sockets**: Un proceso envía y recibe mensajes de la red a través de una interfaz de software llamada socket
- **Servicios de la capa de transporte**: La capa de transporte es la responsable de llevar los mensajes desde el socket del proceso emisor hasta el socket del proceso receptor (ej: TCP y UDP).
- **Direccionamiento de procesos**: Para que un proceso emisor identifique al proceso receptor en otra máquina, necesita dos elementos de información:
  - La **dirección** IP del host de destino
  - Un número de **puerto de destino**, que identifica al proceso específico que se ejecuta en ese host

Cuando los procesos se ejecutan en el **mismo sistema terminal**, pueden comunicarse entre sí mediante lo que se conoce como **comunicación entre procesos**, aplicando las reglas gobernadas por el sistema operativo del sistema terminal

### Ejercicio 3

un programa **cliente** que inicia la comunicación y solicita servicios, y un programa **servidor** que espera ser contactado y proporciona servicios en respuesta a las solicitudes. Los servidores, a menudo hosts potentes y siempre activos con direcciones IP fijas, se encargan de las solicitudes de numerosos clientes. La interacción ocurre mediante el intercambio de mensajes a través de la red, utilizando sockets como interfaz de software.  
Un ejemplo de este modelo en la vida cotidiana puede ser un cine, en el que nosostros como clientes solicitamos una pelicula pagando la entrada, y el cine como servidor nos responde con la película. De la mano con este ejemplo un sistema que implementa este modelo es Netflix, en el que, al igual que en un cine, solicitamos una pelicula o serie y el servidor nos responde brindándonos las mismas.  
Además del modelo Cliente-Servidor, una arquitectura alternativa para aplicaciones de red puede ser el modelo **P2P** (Peer-to-Peer), en este modelo, no existe una dependencia exclusiva de servidores siempre activos, sino que la comunicación se da directamente entre los hosts (peers) de los usuarios, los cuales pueden realizar tanto funciones de cliente como de servidor

### Ejercicio 4

El Agente de usuario o **User agent** es la interfaz del usuario con las aplicaciones de red y gestiona la comunicación a través de la red. La línea de cabecera User-agent: especifica el agente de usuario, es decir, el tipo de navegador que está haciendo la solicitud al servidor. Esta línea de cabecera resulta útil porque el servidor puede enviar versiones diferentes del mismo objeto a los distintos tipos de agentes de usuario

### Ejercicio 5

HTML es un lenguaje de marcado que se utiliza para definir la estructura y el contenido de las páginas web, mientras que HTTP es el protocolo de la capa de aplicación de la Web que define cómo los clientes web (navegadores) solicitan páginas y objetos web a los servidores web, y cómo estos servidores transfieren esos objetos a los clientes. Un archivo HTML es un tipo de objeto o recurso que HTTP puede transferir y HTTP es el mecanismo que permite que el archivo HTML, junto con sus imágenes, videos y otros objetos, viaje desde el servidor hasta el navegador del usuario. En resumen, HTML define qué es una página web y cómo está estructurada, mientras que HTTP define cómo se solicita y se entrega esa página web y sus componentes a través de internet.

### Ejercicio 6

### Inciso a

La información de la capa de aplicación que nos indica si un mensaje es de requerimiento o de respuesta para HTTP se encuentra en la primera línea del mensaje. Los mensajes HTTP se estructuran de forma diferente para solicitudes y respuestas
- Un mensaje de **Solicitud HTTP** (Request Message) tiene: 
  - **Línea de Solicitud (Request Line)**: Es la primera línea y consta de tres campos:
    - **Campo de Método**: Indica la acción que el cliente desea realizar sobre el recurso identificado por el URL, como GET, POST, HEAD, PUT, y DELETE
    - **Campo URL**: Especifica la URL (Uniform Resource Locator) del objeto solicitado
    - **Campo de Versión HTTP**: Indica la versión del protocolo HTTP utilizada por el cliente (por ejemplo, HTTP/1.1)
  - **Líneas de Cabecera (Header Lines)**: Proporcionan información adicional sobre la solicitud, el cliente o laspreferencias de contenido
    - **Línea en blanco**: Separa las cabeceras del cuerpo de la entidad
    - **Cuerpo de Entidad (Entity Body)**: Es opcional y se utiliza para enviar datos al servidor, especialmente con métodos como POST. Para el método GET, este campo generalmente está vacío
- Un mensaje de **Respuesta HTTP** (Response Message) tiene:
  - **Línea de Estado (Status Line)**: Es la primera línea y también tiene tres campos:
    - **Campo de Versión HTTP**: Indica la versión del protocolo HTTP utilizada por el servidor
    - **Campo de Código de Estado**: Un código numérico de tres dígitos que informa el resultado de la solicitud (por ejemplo, 200, 301, 400, 404, 304, etc.)
    - **Mensaje Explicativo del Estado (Reason Phrase)**: Una breve descripción textual asociada al código de estado(por ejemplo, "OK", "Not Found", etc.)
  - **Líneas de Cabecera (Header Lines)**: Proporcionan metadatos sobre la respuesta, el servidor o el objeto que se está enviando
  - **Línea en blanco**: Separa las cabeceras del cuerpo de la entidad
  - **Cuerpo de Entidad (Entity Body)**: Contiene el objeto solicitado (ej., archivo HTML, imagen). Puede estar vacío en ciertos casos, como en una respuesta "304 Not Modified" para optimizar el ancho de banda.  

Las **cabeceras** (headers), proporcionan informacion adicional tanto en las solicitudes como en las respuestas, detallando aspectos específicos de la comunicación o del contenido.

### Inciso b

Una **cabecera** de petición esta compuesta por su clave(nombre) (no sensible a las mayusculas) seguido de dos puntos ":", y a continuación su valor (sin saltos de línea).

### Inciso c

```HTTP
    GET /index.html HTTP/1.1
    Host: www.misitio.com
    Connection: close
    User-agent: curl/7.74.0
```

Deben enviarse las siguientes cabeceras:
1. **Host**, ya que las cachés proxy web necesitan la información proporcionada por la línea de cabecera del host
2. **Connection**, el navegador está diciendo al servidor que no desea molestarse en trabajar con conexiones persistentes
3. **Agente de usuario**

### Ejercicio 7

**curl** es una herramienta para tansferir datos desde o hacia un servidor, usando uno de los protocolos soportados (DICT, FILE, FTP, HTTP, HTTPS, etc.). El comando esta diseñado para trabajar sin la interacción del usuario
- **-I** (o --head): (HTTP, FTP, FILE) obtiene solo las cabeceras. Los servidores HTTP cuentan con el comando HEAD, que se utiliza para obtener únicamente la cabecera de un documento. Cuando se usa en un archivo FTP o FILE, curl muestra solamente el tamaño del archivo y la fecha de su última modificación.
- **-H** (o --header): agrega un encabezado adicional a la solicitud HTTP enviada al servidor. Se puede especificar cualquier número de encabezados extra. Si se añade un encabezado personalizado con el mismo nombre que uno de los internos usados por curl, el encabezado personalizado reemplazará al interno.
- **-X** ( o --request): especifica un método de solicitud customizado para usar en la comunicación con el servidor HTTP. El método especificado será utilizado en lugar del predetermindao (por default, es GET).
- **-s** (o --silent): activa el modo silencioso/quieto en curl. No muestra el medidor de progreso y los mensajes de error, haciendo que curl no emita mensajes. Los datos solicitados aún se muestran (por ejemplo, en la terminal/stdout) a menos que se redirijan.

### Ejercicio 

### Inciso a

Se realiza un solo requerimiento, que al no especificar ninguno, es GET por default, y recibimos el código HTML de la página, y si redirigimos la salida a un archivo, al clickearlo abre la página en el navegador (sin estilos).

### Inciso b
### Inciso c
### Inciso d

### Ejercicio 9

a- Ambos comandos muestran las cabeceras, la diferencia es que en la primera opción, el comando redirige la salida (cuerpo HTML) a dev/null ("basurero"), quedando solo las cabeceras, lo cual es más ineficiente que la opción 2, que al usar el parámetro -I, ya trae directamente solo las cabeceras. 
b- Si se quita la redirección en el primer comando, se va a mostrar el cuerpo del HTML. Esta redirección no es necesaria en el segundo comando ya que el parámetro -I trae solo las cabeceras directamente
c- En el requerimiento viajaron 3 cabeceras (Host, User-Agent y Accept) y en la respuesta viajaron 7 (Date, Server, Last-Modified, ETAG, Accept-Ranges, Content-Length y Content-Type)

### Ejercicio 10

La cabecera **Date** forma parte de la respuesta HTTP del servidor que indica la fecha y la hora en la que el mismo generó la respuesta. 

### Ejercicio 11

HTTP/1.0 no usa conexiones persistentes por defecto (en parches si), el cliente sabe que recibió todo el objeto porque la conexión se cerró. En cambio en HTTP/1.1 existen conexiones persistentes y hay 2 mecanimsos para saber si se recibió el objeto completo, Content-Length que indica el tamaño del objeto o chunked tranfer encoding que consiste en que si no se conoce el tamaño, se envia el objeto en fragmentos (chunks) y un bloque de tamaño 0 indica el final.

### Ejerecicio 12

Los distintos tipos de código de respuesta son, 2XX de éxito/información, 3XX de redirección, 4XX de errores del cliente y 5XX de errores del servidor. Por ejemplo:
- 200 OK
- 301 Moved Permanently
- 404 Not Found
- 505 HTTP Version Not Supported

### Ejercicio 13

a- La primera línea (Línea de estado) brinda la versión de HTTP, el código de estado y el mensaje explicativo del código

b- la respuesta muestra 7 (Date, Server, Last-Modified, ETAG, Accept-Ranges, Content-Length y Content-Type)

c-

d- Fue exitoso, ya que el servidor respondió con `HTTP/1.1 200 OK`

e- la respuesta del servidor muestra: `Last-Modified: Sun, 19 Mar 2023 19:04:46 GMT`

f- Lo hice ejecutando `curl -v -z "04 Sep 2025"  www.redes.unlp.edu.ar`, no obtuve resultado, la terminal no mostro nada, a menos que agreguemos el parámtro `-v`, en ese caso, el resultado que obtuve fue:

```bash
  GET / HTTP/1.1
  Host: www.redes.unlp.edu.ar
  User-Agent: curl/7.74.0
  Accept: */*
  If-Modified-Since: Thu, 04 Sep 2025 00:00:00 GMT

  Mark bundle as not supporting multiuse
  HTTP/1.1 304 Not Modified
  Date: Thu, 04 Sep 2025 16:40:04 GMT
  Server: Apache/2.4.56 (Unix)
  Last-Modified: Sun, 19 Mar 2023 19:04:46 GMT
  ETag: "1322-5f7457bd64f80"
  Accept-Ranges: bytes
```

Sirve para evitar descargar datos innecesarios si el recurso no cambió, ahorrando ancho de banda y tiempo. Es útil en cachés, proxys y navegadores, donde se quiere tener contenido actualizado sin estar bajándolo siempre. Y el código `304 Not Modified` le indica al cliente que puede seguir usando la copia local.

### Ejercicio 14

1. `curl www.redes.unlp.edu.ar/restringido/index.php`
2. `curl www.redes.unlp.edu.ar/obtener-usuario.php`
3. `curl -H "Usuario-Redes: obtener" www.redes.unlp.edu.ar obtener-usuario.php`
4. `echo -n "redes:RYC" | base64`
   1. Devuelve `cmVkZXM6UllD`
5. `curl -v -H "Authorization: Basic cmVkZXM6UllD" www.redes unlp.edu.ar`

### Ejercicio 15

a. Respuesta:
```bash
GET /http/HTTP-1.1/ HTTP/1.0
User-Agent: curl/7.38.0
Host: www.redes.unlp.edu.ar
Accept: */*



```

b. Responde mostrando una página HTML de título "Protocolo HTTP: versiones"

c. Se puede pegar varias veces el mismo contenido sin tener que ejecutar el comando telnet nuevamente ya que ahora se está utilizando el protocolo HTTP 1.1

### Ejercicio 16

**a.** `telnet` se utiliza para establecer una conexión TCP con un servidor en un host remoto. Por ejemplo, al ejecutar telnet www.redes.unlp.edu.ar 80, telnet establece la conexión TCP, y proporcionará una sesión interactiva directamente con el servidor web. Esto significa que podremos escribir manualmente comandos HTTP (como GET, HEAD, POST, etc.) y el servidor los responderá directamente con los mensajes HTTP correspondientes. Cada caracter que escribamos en el cliente telnet será enviado al host remoto, y el host remoto usualmente le "hará eco" de vuelta a nuestra pantalla (Conversation History). Este método es comúnmente utilizado para entender y depurar protocolos de la capa de aplicación

**b.** se utilizó el método GET, primero para solicitar el recurso extras/prueba-http-1-0.txt y luego /extras/prueba-http-1-1.tx

**c.** en el primer caso cuando el host retornó la respuesta, la conexión se cerró, ya que en nuestra solicitud especificamos el protocolo HTTP 1.0, que por defecto no utiliza conexiones persistentes, y en el segundo caso, la conexión se mantuvo, permitiendo seguir mandando solicitudes al servidor, ya que especificamos el protocolo HTTP 1.1 en nuestra solicitud, que utiliza conexiones persistentes por defecto

**d.** El más eficiente es el de HTTP 1.1 ya que no se va cerrar la conexión cada vez que solicite un recurso a diferencia de HTTP 1.0 por defecto. Aunque, en HTTP 1.1 el pipeline puede generar HOLB (Head-of-Line Blocking), ya que las respuestas del servidor deben enviarse en el mismo orden en que se recibieron las solicitudas, y si una solicitud temprana o un objeto grande tarda en porcesarse, bloquea la entrega de las respuestas de las solicitudes posteriores, incluso si estas ya están listas. Para mitigar el HOLB, los navegadores modernos a menudo abren varias conexiones TCP paralelas con el mismo servidor. Aunque esto puede reducir el tiempo total de carga, un número excesivo de conexiones puede sobrecargar el servidor, consumir recursos de red de manera ineficiente y potencialmente eludir el control de congestión de TCP.

### Ejercicio 17
El método GET envía los valores ingresados en el formulario dentro de la URL, mientras que el método POST envía los valores en el cuerpo de la petición, los cuales pueden visualizarse en Wireshark bajo el apartado HTML Form URL Encoded.

- Diferencias en el navegador
  - GET
    - Los parámetros se muestran en la barra de direcciones del navegador.
    - Al recargar la página (F5), el navegador puede volver a enviar la misma solicitud sin pedir confirmación.
    - Es menos seguro, ya que los datos quedan visibles en el historial, caché y URL.
  - POST
    - Los parámetros no aparecen en la barra de direcciones.
    - Al recargar la página, el navegador pide confirmación para reenviar el formulario, porque implica volver a mandar datos al servidor.
    - Es más apropiado para el envío de datos sensibles o formularios largos.

### Ejercicio 18

Las cabeceras `Set-Cookie` y `Cookie` actúan como una "capa de sesión" sobre HTTP, transformando un protocolo "stateless" en uno que puede simular un estado, habilitando así funcionalidades complejas en aplicaciones web. Las cookies permiten que los sitios web identifiquen a los usuarios y mantengan información de estado (como inicios de sesión o carritos de compra) a lo largo de múltiples solicitudes y sesiones, algo que HTTP por sí solo no puede hacer debido a su diseño sin estado
1. El servidor envía una cabecera `Set-Cookie` al cliente, asignándole un identificador único
2. El navegador del cliente almacena esta información en un archivo de cookies
3. En solicitudes posteriores al mismo sitio, el navegador incluye una cabecera `Cookie` con dicho identificador, permitiendo al servidor reconocer al usuario y su actividad 
  
Esta funcionalidad, si bien útil, también ha generado preocupaciones de privacidad debido al seguimiento de usuarios

### Ejercicio 19

La diferencia fundamental entre un protocolo binario y uno basado en texto radica en cómo se codifican los datos en los mensajes que se intercambian.
Un **protocolo basado en texto** utiliza caracteres legibles por humanos, como ASCII normal, para construir sus mensajes. Esto significa que una persona con conocimientos informáticos puede leer e interpretar fácilmente los mensajes sin necesidad de herramientas especiales de decodificación. Si bien son fáciles de depurar y comprender, pueden ser menos eficientes en términos de tamaño de mensaje, ya que los datos binarios (como imágenes o archivos multimedia) a menudo necesitan ser codificados a ASCII antes de la transmisión, lo que añade sobrecarga.  
Un **protocolo binario** codifica sus mensajes directamente en formato binario, lo que significa que no son directamente legibles por humanos sin una decodificación. La codificación binaria suele ser más eficiente en términos de espacio, ya que no necesita convertir datos binarios a una representación textual y puede transmitir la información de manera más compacta
- **HTTP/1.0**: Es un protocolo basado en texto
- **HTTP/1.1**: Es un protocolo basado en texto
- **HTTP/2**: Es un protocolo binario. Una de sus principales diferencias con HTTP/1.1 es que es un protocolo binario en lugar de textual (ASCII)

### Ejercicio 20