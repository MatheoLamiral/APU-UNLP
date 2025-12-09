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

#### Inciso a

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

#### Inciso b

Una **cabecera** de petición esta compuesta por su clave(nombre) (no sensible a las mayusculas) seguido de dos puntos ":", y a continuación su valor (sin saltos de línea).

#### Inciso c

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

### Ejercicio 8

#### Inciso a

Se realiza un solo requerimiento, que al no especificar ninguno, es GET por default, y recibimos el código HTML de la página, y si redirigimos la salida a un archivo, al clickearlo abre la página en el navegador (sin estilos).

#### Inciso b

el atributo `href` (Hypertex Reference) en etiquetas HTML como `link` e `image` se utiliza para especificar la URL del objeto o recurso que el navegador debe obtener de un servidor para completar la visualización o funcionalidad de la página web.  

#### Inciso c

No es suficiente un único requerimiento para visualizar una página web completa que contenga imágenes y otros elementos, como haría un navegador. La solicitud inicial (utilizando `curl` sin parámetros adicionales o un navegador) solo recupera el archivo base HTML de la página. Para que un navegador pueda mostrar la página completa, incluyendo el texto y todos los elementos visuales, deberíamos realizar un requerimiento HTTP por cada recurso adicional que queramos visualizar de la página, como imágenes, hojas de estilo CSS, scripts JavaScript, etc.

#### Inciso d

Para obtener la página que contiene el archivo HTML base, dos CSS, dos archivos Javascript y tres imágenes, se requerirían 8 requerimientos HTTP (1 para la página HTML y 7 para los recursos adicionales).
La diferencia entre usar `curl` y un navegador web radica en que `curl` solo realiza el requerimiento explícito que se le indica (por ejemplo, obtener el archivo HTML), mientras que un navegador web automáticamente analiza el contenido HTML recibido, identifica los recursos adicionales necesarios (como imágenes, CSS, JavaScript) y realiza los requerimientos HTTP adicionales para obtener esos recursos y renderizar la página completa para el usuario.

### Ejercicio 9

#### Inciso a

Ambos comandos muestran las cabeceras, la diferencia es que en la primera opción, el comando redirige la salida (cuerpo HTML) a dev/null ("basurero"), quedando solo las cabeceras, lo cual es más ineficiente que la opción 2, que al usar el parámetro -I, ya trae directamente solo las cabeceras. 

#### Inciso b

Si se quita la redirección en el primer comando, se va a mostrar el cuerpo del HTML. Esta redirección no es necesaria en el segundo comando ya que el parámetro -I trae solo las cabeceras directamente

#### Inciso c

En el requerimiento viajaron 3 cabeceras (Host, User-Agent y Accept) y en la respuesta viajaron 7 (Date, Server, Last-Modified, ETAG, Accept-Ranges, Content-Length y Content-Type)

### Ejercicio 10

La cabecera **Date** forma parte de la respuesta HTTP del servidor que indica la fecha y la hora en la que el mismo generó la respuesta. 

### Ejercicio 11

HTTP/1.0 no usa conexiones persistentes por defecto (en parches si), el cliente sabe que recibió todo el objeto porque la conexión se cerró. En cambio en HTTP/1.1 se usan conexiones persistentes por defecto y hay 2 mecanimsos para saber si se recibió el objeto completo, Content-Length que indica el tamaño del objeto o chunked tranfer encoding que consiste en que si no se conoce el tamaño, se envia el objeto en fragmentos (chunks) y un bloque de tamaño 0 indica el final.

### Ejerecicio 12

|Categoría|Significado|Ejemplo|
|-|-|-|
|2XX|Éxito/Información|200 OK|
|3XX|Redirección|301 Moved Permanently|
|4XX|Errores del cliente|404 Not Found|
|5XX|Errores del servidor|505 HTTP Version Not Supported|

### Ejercicio 13

#### Inciso a

La primera línea (Línea de estado) brinda la versión de HTTP, el código de estado y el mensaje explicativo del código
- `version HTTP/(1.0, 1.1, 2) código(2xx,3xx, etc) Mensaje(OK, Not Found, etc)`

#### Inciso b

la respuesta muestra 7 (Date, Server, Last-Modified, ETAG, Accept-Ranges, Content-Length y Content-Type)

#### Inciso c

Esta sirviendo el servidor Apache/2.4.56(Unix), y se puede observar en la cabecera `Server: Apache/2.4.56 (Unix)`

#### Inciso d

Fue exitoso, ya que el servidor respondió con `200 OK`

#### Inciso e

la respuesta del servidor muestra: `Last-Modified: Sun, 19 Mar 2023 19:04:46 GMT`

#### Inciso f

- `curl -H "If-Modified-Since: Tue, 04 Sep 2025 10:00:00 GMT" www.redes.unlp.edu.ar`. Esto me permite solicitar el recurso solo si ha sido modificado después de la fecha y hora especificadas en la cabecera `If-Modified-Since`.
- No obtuve ningun resultado.
- Sirve para evitar descargar datos innecesarios si el recurso no cambió, ahorrando ancho de banda y tiempo. Es útil en cachés, proxys y navegadores, donde se quiere tener contenido actualizado sin estar bajándolo siempre. Y el código `304 Not Modified` le indica al cliente que puede seguir usando la copia local.

### Ejercicio 14

1. `curl www.redes.unlp.edu.ar/restringido/index.php`
2. `curl www.redes.unlp.edu.ar/obtener-usuario.php`
3. `curl -H "Usuario-Redes: obtener" www.redes.unlp.edu.ar obtener-usuario.php`
4. `echo -n "redes:RYC" | base64`
   1. Devuelve `cmVkZXM6UllD`
5. `curl -v -H "Authorization: Basic cmVkZXM6UllD" www.redes unlp.edu.ar`

### Ejercicio 15

#### Inciso a

```bash
GET /http/HTTP-1.1/ HTTP/1.0
User-Agent: curl/7.38.0
Host: www.redes.unlp.edu.ar
Accept: */*



```

#### Inciso b

Responde mostrando una página HTML de título "Protocolo HTTP: versiones"

```bash
  redes@debian:~$ telnet www.redes.unlp.edu.ar 80
  Trying 172.28.0.50...
  Connected to www.redes.unlp.edu.ar.
  Escape character is '^]'.
  GET /http/HTTP-1.1/ HTTP/1.1
  User-Agent: curl/7.38.0
  Host: www.redes.unlp.edu.ar
  Accept: */*



  HTTP/1.1 200 OK
  Date: Tue, 09 Sep 2025 18:44:11 GMT
  Server: Apache/2.4.56 (Unix)
  Last-Modified: Sun, 19 Mar 2023 19:04:46 GMT
  ETag: "760-5f7457bd64f80"
  Accept-Ranges: bytes
  Content-Length: 1888
  Content-Type: text/html

  <!DOCTYPE html>
  <html lang="en">
    <head>
      <meta charset="utf-8">
      <title>Protocolo HTTP: versiones</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta name="description" content="">
      <meta name="author" content="">

      <!-- Le styles -->
      <link href="../../bootstrap/css/bootstrap.css" rel="stylesheet">
      <link href="../../css/style.css" rel="stylesheet">
      <link href="../../bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

      <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
      <!--[if lt IE 9]>
        <script src="./bootstrap/js/html5shiv.js"></script>
      <![endif]-->
    </head>

    <body>


      <div id="wrap">
          
      <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="navbar-inner">
          <div class="container">
            <a class="brand" href="../../index.html"><i class="icon-home icon-white"></i></a>
            <a class="brand" href="https://catedras.info.unlp.edu.ar" target="_blank">Redes y Comunicaciones</a>
            <a class="brand" href="http://www.info.unlp.edu.ar" target="_blank">Facultad de Inform&aacute;tica</a>
            <a class="brand" href="http://www.unlp.edu.ar" target="_blank">UNLP</a>
          </div>
        </div>
      </div>

      <div class="container">
      <h1>Ejemplo del protocolo HTTP 1.1</h1>
      <p>
          Esta p&aacute;gina se visualiza utilizando HTTP 1.1. Utilizando el capturador de paquetes analice cuantos flujos utiliza el navegador para visualizar la p&aacute;gina con sus im&aacute;genes en contraposici&oacute;n con el protocolo HTTP/1.0.
      </p>
      </p>
      <h2>Imagen de ejemplo</h2>
      <img src="13532-tuxkiller03green.png" width="800px"/>
      </div> 
      
      
      </div>
      <div id="footer">
        <div class="container">
          <p class="muted credit">Redes y Comunicaciones</p>
        </div>
      </div>
    </body>
  </html>
  Connection closed by foreign host.
```

#### Inciso c

Se puede pegar varias veces el mismo contenido sin tener que ejecutar el comando telnet nuevamente ya que ahora se está utilizando el protocolo HTTP 1.1, es decir, que por la conexión persistente que establece HTTP 1.1, se puede seguir enviando solicitudes al servidor sin necesidad de abrir una nueva conexión TCP cada vez.

### Ejercicio 16

#### Inciso a

`telnet` se utiliza para establecer una conexión TCP con un servidor en un host remoto. Por ejemplo, al ejecutar `telnet www.redes.unlp.edu.ar 80`, telnet establece la conexión TCP, y proporcionará una sesión interactiva directamente con el servidor web. Esto significa que podremos escribir manualmente comandos HTTP (como GET, HEAD, POST, etc.) y el servidor los responderá directamente con los mensajes HTTP correspondientes. Cada caracter que escribamos en el cliente telnet será enviado al host remoto, y el host remoto usualmente le "hará eco" de vuelta a nuestra pantalla (Conversation History). Este método es comúnmente utilizado para entender y depurar protocolos de la capa de aplicación

#### Inciso b

se utilizó el método GET, primero para solicitar el recurso extras/prueba-http-1-0.txt y luego /extras/prueba-http-1-1.tx

#### Inciso c

en el primer caso cuando el host retornó la respuesta, la conexión se cerró, ya que en nuestra solicitud especificamos el protocolo HTTP 1.0, que por defecto no utiliza conexiones persistentes, y en el segundo caso, la conexión se mantuvo, permitiendo seguir mandando solicitudes al servidor, ya que especificamos el protocolo HTTP 1.1 en nuestra solicitud, que utiliza conexiones persistentes por defecto

#### Inciso d

El más eficiente es el de HTTP 1.1 ya que no se va cerrar la conexión cada vez que solicite un recurso a diferencia de HTTP 1.0 por defecto. Aunque, en HTTP 1.1 el pipeline puede generar HOLB (Head-of-Line Blocking), ya que las respuestas del servidor deben enviarse en el mismo orden en que se recibieron las solicitudas, y si una solicitud temprana o un objeto grande tarda en porcesarse, bloquea la entrega de las respuestas de las solicitudes posteriores, incluso si estas ya están listas. Para mitigar el HOLB, los navegadores modernos a menudo abren varias conexiones TCP paralelas con el mismo servidor. Aunque esto puede reducir el tiempo total de carga, un número excesivo de conexiones puede sobrecargar el servidor, consumir recursos de red de manera ineficiente y potencialmente eludir el control de congestión de TCP.

### Ejercicio 17

El método **GET** envía los valores ingresados en el formulario dentro de la **URL**, mientras que el método **POST** envía los valores en el **cuerpo** de la petición, los cuales pueden visualizarse en Wireshark bajo el apartado HTML Form URL Encoded.

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
  
Esta funcionalidad, si bien es útil, también ha generado preocupaciones de privacidad debido al seguimiento de usuarios

### Ejercicio 19

La diferencia fundamental entre un protocolo binario y uno basado en texto radica en cómo se codifican los datos en los mensajes que se intercambian.
Un **protocolo basado en texto** utiliza caracteres legibles por humanos, como ASCII normal, para construir sus mensajes. Esto significa que una persona con conocimientos informáticos puede leer e interpretar fácilmente los mensajes sin necesidad de herramientas especiales de decodificación. Si bien son fáciles de depurar y comprender, pueden ser menos eficientes en términos de tamaño de mensaje, ya que los datos binarios (como imágenes o archivos multimedia) a menudo necesitan ser codificados a ASCII antes de la transmisión, lo que añade sobrecarga.  
Un **protocolo binario** codifica sus mensajes directamente en formato binario, lo que significa que no son directamente legibles por humanos sin una decodificación. La codificación binaria suele ser más eficiente en términos de espacio, ya que no necesita convertir datos binarios a una representación textual y puede transmitir la información de manera más compacta
- **HTTP/1.0**: Es un protocolo basado en texto
- **HTTP/1.1**: Es un protocolo basado en texto
- **HTTP/2**: Es un protocolo binario. Esta es una de sus principales diferencias con HTTP/1.1

### Ejercicio 20

#### Inciso a

- En HTTP 1.0, la cabecera `Host:` no es obligatoria, pero puede ser necesaria dependiendo de cómo el servidor web esté configurado. Si la cabecera `Host:` no se incluye en una solicitud HTTP 1.0, el servidor asume que la solicitud estaba destinada al dominio asociado con la dirección IP del servidor
- La principal función de la cabecera `Host:` en HTTP 1.1 es especificar el host en el que reside el objeto solicitado. En HTTP 1.1, esta cabecera es obligatoria, si está ausente, el servidor responderá con un error 400 Bad Request. Es fundamental con respecto al virtual hosting (múltiples servicios o dominios multiplexados sobre una misma dirección IP), ya que sin esta cabecera, el servidor no podría determinar a qué sitio web o servicio se refiere la solicitud cuando múltiples sitios comparten la misma dirección IP.
- En HTTP 2, la cabecera `Host:` sigue siendo relevante y funciona igual, solo que ahora forma parte de los pseudo-headers, específicamente `:authority`. Aunque HTTP/2 es un protocolo binario, mantiene la semántica de las cabeceras de HTTP/1.1 para asegurar la compatibilidad y continuidad en la comunicación entre clientes y servidores. El uso de pseudo-headers en HTTP/2 permite tratar a las cabeceras de una manera más eficiente y optimizada

#### Inciso b

El siguiente requerimiento HTTP 1.1

```HTTP
  GET /index.php HTTP/1.1
  User-Agent: curl/7.54.0
```

No es correcto, ya que falta la cabecera obligatoria `Host:`, por lo que el servidor responderá con un error 400 Bad Request.

#### Inciso c

El siguiente requerimiento HTTP 1.1

```HTTP
  GET /index.php HTTP/1.1
  Host: www.info.unlp.edu.ar
```

Quedaría de la siguiente manera en HTTP 2 si se utiliza HTTPS

```HTTP
  :method: GET
  :path: /index.php
  :scheme: https
  :authority: www.info.unlp.edu.ar
```
- `:method:` corresponde al método HTTP (GET, POST, etc.)
- `:path:` corresponde a la ruta del recurso solicitado
- `:scheme:` indica el esquema de la URL (http o https)
- `:authority:` equivalente a la cabecera Host en HTTP 1.1, especifica el host al que se dirige la solicitud

### Ejercicio parcial 

```HTTP
  curl -X ?? www.redes.unlp.edu.ar/??
  > HEAD /metodos/ HTTP/??
  > Host: www.redes.unlp.edu.ar
  > User-Agent: curl/7.54.0
  < HTTP/?? 200 OK
  < Server: nginx/1.4.6 (Ubuntu)
  < Date: Wed, 31 Jan 2018 22:22:22 GMT
  < Last-Modified: Sat, 20 Jan 2018 13:02:41 GMT
  < Content-Type: text/html; charset=UTF-8
  < Connection: close
```

#### Inciso a

El servidor podría estar utilizanto tanto HTTP/1.1 como HTTP/1.0, ya que en la respuesta se observa la cabecera `Host: www.redes.unlp.edu.ar`, que es obligatoria en HTTP/1.1 y opcional en HTTP/1.0. Pero nos determinamos por HTTP/1.1 ya que en la respuesta se observa la cabecera `Connection:close` (aunla el comportamiento persistente, es decir, cerrar conexión TCP depsues de la respuesta), si fuese HTTP/1.0, esa especificación no sería necesaria, ya que las conexiones no son persistentes por defecto.

#### Inciso b

Se está utilizando el método HEAD, ya que en la respuesta solo se observan las cabeceras y no el cuerpo del mensaje (cuerpo HTML). Este método no retorna el recurso completo, ya que la finalidad del método HEAD es obtener únicamente las cabeceras de la respuesta sin el cuerpo, lo cual es útil para verificar metadatos o la existencia de un recurso sin descargar su contenido completo.

#### Inciso c

El recurso solicitado es www.redes.unlp.edu.ar/metodos/

#### Inciso d

El método funcionó correctamente, ya que en la respuesta se observa el código `200 OK`, lo que indica que la solicitud fue exitosa.

#### Inciso e

Si se hubiese agregado la cabecera `If-Modified-Since: Sat, 20 Jan 2018 13:02:41 GMT` al requerimiento, la respuesta de servidor hubiese sido un código `304 Not Modified`. El navegador hubiese interpretado que el recurso no ha sido modificado desde la última vez que lo solicitó, por lo que podría utilizar la versión almacenada en caché sin necesidad de descargarlo nuevamente, optimizando así el uso del ancho de banda y reduciendo el tiempo de carga.
