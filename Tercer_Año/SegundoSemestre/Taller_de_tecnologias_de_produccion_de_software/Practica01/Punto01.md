# Punto 1

### Inciso a: ¿Qué es una URL y cómo se compone?

Una **URL** (Uniform Resource Location) es un tipo de URI (Uniform Resource Identifier) que se utiliza para referenciar
recursos u objetos específicos en la web.
Se compone de:

1. **Protocolo**: le indica al servidor que protocolo de comunicación será utilizado (http/https)
2. **Servidor**: nombre único del servidor al que se hace la petición. Este nombre mapea con  una única dirección IP y se podría  especificar la dirección IP en vez del nombre
3. **Puerto**: esta parte de la url es opcional. Cada servidor soporta  muchos puertos. El puerto  por defecto para servidores web, es 80.
4. **Path**: camino de la ubicación del recurso buscado en el servidor.
5. **Recurso**: el nombre del recurso que se está pidiendo. Podría ser un HTML, una imagen, un servlet, etc. Si no se especifica nada, el servidor buscará un index.htm

### Inciso b: ¿Qué componentes de la URL son opcionales?

Los componentes opcionales de una URL son:
- Puerto: Si no se especifica, se utiliza el puerto por defecto del protocolo (80 para HTTP, 443 para HTTPS).
- Recurso: Si no se especifica, el servidor generalmente busca un archivo predeterminado como `index.html` o `index.php`.

### Inciso c: ¿Cómo se especifica en el encabezado HTTP el recurso al que se quiere acceder?

En el encabezado HTTP, el recurso al que se quiere acceder se especifica a través de su URL (Uniform Resource Locator). Cada recurso en la web tiene su propia y única dirección en el formato URL, que es uno de los elementos clave de un requerimiento HTTP

### Inciso d: ¿Cómo se diferencian los mensajes de requerimiento HTTP de los mensajes de respuesta?

Un mensaje de Solicitud HTTP (Request Message) tiene como primera líena la **línea de Solicitud** (Request Line): 
- Es la primera línea y consta de tres campos:
  - Campo de Método: Indica la acción que el cliente desea realizar sobre el recurso identificado por el URL, como GET, POST, HEAD, PUT, y DELETE 
  - Campo URL: Especifica la URL (Uniform Resource Locator) del objeto solicitado 
    - Campo de Versión HTTP: Indica la versión del protocolo HTTP utilizada por el cliente (por ejemplo, HTTP/1.1)
    
Un mensaje de Respuesta HTTP (Response Message) tiene como primera linea la **línea de Estado** (Status Line):
- Es la primera línea y también tiene tres campos:
  - Campo de Versión HTTP: Indica la versión del protocolo HTTP utilizada por el servidor 
  - Campo de Código de Estado: Un código numérico de tres dígitos que informa el resultado de la solicitud (por ejemplo, 200, 301, 400, 404, 304, etc.)
  - Mensaje Explicativo del Estado (Reason Phrase): Una breve descripción textual asociada al código de estado(por ejemplo, "OK", "Not Found", etc.)

### Inciso e: ¿Cómo sabe el servidor qué navegador está utilizando el visitante?

Lo sabe mediante el header `User-Agent` que es parte del encabezado HTTP en la solicitud del cliente. Este header proporciona información sobre el navegador, su versión, el sistema operativo y otros detalles relevantes. El servidor puede utilizar esta información para adaptar la respuesta según las capacidades y características del navegador del visitante

### Inciso f: ¿Cómo se envían los parámetros en los mensajes con método POST?

Los parámetros se envían en el cuerpo (body) del requerimiento HTTP. A diferencia del método GET, donde los datos aparecen en la URL y tienen limitaciones en la cantidad de caracteres, con POST no hay un límite de tamaño para los datos, ya que estos viajan en el cuerpo del mensaje

### Inciso g: ¿Qué son las Cookies? ¿Cómo se envían las Cookies a través de HTTP?

Las cookies son un mecanismo que permite a las aplicaciones web mantener un "estado" en el servidor, a pesar de que el protocolo HTTP no tiene estado (stateless). Son utilizadas ampliamente para rastrear usuarios y personalizar el contenido.  
En la primera interacción, cuando un usuario accede a un sitio web, el servidor crea una identificación única para ese usuario. Esta identificación se incluye en una cabecera `Set-cookie`, dentro del mensaje de respuesta HTTP que el servidor envía al navegador del cliente. El navegador guarda esta información en su archivo de cookies. En interacciones posteriores, cada vez que el usuario realiza una nueva solicitud a ese mismo sitio web, el navegador busca en su archivo de cookies, extrae la identificación asociada y la incluye en una cabecera `Cookie`, en el mensaje de solicitud HTTP. De esta manera, el servidor puede identificar al usuario y rastrear su actividad a lo largo de su navegación por el sitio, creando una capa de sesión sobre el protocolo HTTP sin estado. Sin embargo, esto también genera preocupaciones sobre la privacidad del usuario

### Inciso h: ¿Cuál es la diferencia entre HTTP y HTTPS? ¿Cuáles son los puertos por default (por defecto) de los mismos?

La principal diferencia entre HTTP y HTTPS radica en la seguridad y la confidencialidad de los datos. Mientras que HTTP envía los mensajes en texto ASCII normal, lo que significa que los datos viajan sin cifrar y pueden ser interceptados y leídos por terceros, HTTPS incorpora servicios de seguridad al protocolo HTTP utilizando TLS (Transport Layer Security), una versión modificada de SSL (Secure Sockets Layer). Esta capa de seguridad en HTTPS cifra los datos antes de que viajen por Internet, garantizando la confidencialidad. Además del cifrado, HTTPS también proporciona integridad de los datos y autenticación en el punto terminal, asegurando que el mensaje no ha sido alterado y que el emisor y el receptor son quienes dicen ser.  
- Los puertos por defecto son:  
  - HTTP: Puerto 80. 
  - HTTPS: Puerto 443.