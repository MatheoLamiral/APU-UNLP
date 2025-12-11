# Práctica 4 - Correo electrónico

### Ejercicio 1

El protocolo principal de la capa de aplicación utilizado para la transferencia de correo electrónico, tanto entre el cliente y su servidor como entre servidores, es el Protocolo simple de transferencia de correo (**SMTP**, Simple Mail Transfer Protocol). El cliente utiliza comandos SMTP para insertar el mensaje en su servidor de correo. Entre Servidores se utiliza SMTP (o ESMTP), los servidores se comunican entre sí utilizando conexiones persistentes y un diálogo interactivo de solicitud/respuesta. SMTP se basa en un servicio de transferencia de datos fiable, como el que ofrece TCP, para asegurar la entrega del mensaje. Es importante notar que, aunque SMTP maneja el envío (es un protocolo push), la sesión puede requerir o no autenticación, y puede trabajar de forma segura utilizando SSL/TLS

### Ejercicio 2

- Los protocolos que se utilizan para la recepción de mails son:
  - **POP3 (Post Office Protocol - versión 3)**, es un protocolo de acceso a correo extremadamente simple, el cliente (MUA) abre una conexión TCP en el puerto 110 al servidor de correo y puede operar de forma segura sobre SSL/TLS. Una sesión POP3 pasa por tres fases, autorización (enviar nombre/contraseña en texto legible), transacción (recuperar mensajes) y actualización (borrar mensajes marcados, que ocurre al salir). El servidor POP3 es esencialmente sin estado entre sesiones. El agente de usuario se configura generalmente para "descargar y borrar" o para "descargar y mantener (guardar)". En el modo "descargar y borrar", los mensajes se eliminan del buzón después de la recuperación (durante la fase de actualización). Una de las mayores limitaciones es que, si un usuario utiliza el modo "descargar y borrar" y luego intenta acceder a su correo desde una computadora diferente, los mensajes recuperados anteriormente no estarán disponibles, ya que fueron eliminados del servidor
  - **IMAP (Internet Mail Access Protocol)**, es un protocolo de acceso que ofrece mucha más funcionalidad que POP3. Utiliza el puerto 143 y también puede ejecutarse de forma segura sobre SSL/TLS. A diferencia de POP3, un servidor IMAP mantiene información sobre el estado a lo largo de las sesiones, como los nombres de las carpetas y los mensajes asociados a cada una. Permite a los usuarios crear y manipular carpetas en el servidor, moviendo mensajes entre ellas (los mensajes se asocian inicialmente a la carpeta INBOX). Los mensajes permanecen en el servidor hasta que el usuario los borra explícitamente. Proporciona comandos para realizar búsquedas en carpetas remotas. Dispone de comandos que permiten al agente de usuario obtener solo partes componentes de los mensajes, como únicamente la cabecera o una parte de un mensaje MIME de varias partes. Esto es útil en conexiones con ancho de banda pequeño para evitar descargar archivos adjuntos grandes (audio o vídeo)
  - **Correo electrónico web (Webmail)**, el acceso al correo electrónico mediante interfaces web (como Hotmail, Yahoo o Gmail) utiliza una arquitectura diferente para la interacción entre el cliente y el servidor. El agente de usuario es un navegador web corriente y la comunicación entre el buzón remoto y el navegador del usuario se realiza a través de HTTP en lugar de POP3 o IMAP. Aunque el usuario acceda a través de HTTP, el servidor de correo sigue utilizando SMTP para enviar y recibir mensajes de otros servidores de correo. No utiliza protocolos especializados de acceso a correo (POP3/IMAP) para la interacción cliente-servidor, sino que se basa en el protocolo HTTP

### Ejercicio 3

#### Inciso b
  - **i**
    La captura muestra luego de ver el TCP stream:
    ```
      * 5 EXISTS
      * 4 RECENT
      DONE
      179 OK Idle completed (90.100 + 90.047 + 90.099 secs).
      180 noop
      180 OK NOOP completed (0.001 + 0.000 secs).
      181 UID fetch 7:* (FLAGS)
      * 5 FETCH (UID 7 FLAGS (\Recent))
      181 OK Fetch completed (0.001 + 0.000 secs).
      182 UID fetch 7 (UID RFC822.SIZE FLAGS BODY.PEEK[HEADER.FIELDS (From To Cc Bcc Subject Date Message-ID Priority X-Priority References Newsgroups In-Reply-To Content-Type Reply-To)])
      * 5 FETCH (UID 7 RFC822.SIZE 802 FLAGS (\Recent) BODY[HEADER.FIELDS (FROM TO CC BCC SUBJECT DATE MESSAGE-ID PRIORITY X-PRIORITY REFERENCES NEWSGROUPS IN-REPLY-TO CONTENT-TYPE REPLY-TO)] {285}
      Message-ID: <e0000698-3def-25b5-703d-4eaa24ab2050@redes.unlp.edu.ar>
      Date: Thu, 2 Oct 2025 09:24:48 -0300
      To: alumnoimap@redes.unlp.edu.ar
      From: alumnopop <alumnopop@redes.unlp.edu.ar>
      Subject: MAIL para captura Wireshark
      Content-Type: text/plain; charset=UTF-8; format=flowed

      )
      182 OK Fetch completed (0.002 + 0.000 + 0.001 secs).
      183 UID fetch 7 (UID RFC822.SIZE BODY.PEEK[])
      * 5 FETCH (UID 7 RFC822.SIZE 802 BODY[] {802}
      Return-Path: <alumnopop@redes.unlp.edu.ar>
      X-Original-To: alumnoimap@redes.unlp.edu.ar
      Delivered-To: alumnoimap@redes.unlp.edu.ar
      Received: from [172.28.0.1] (unknown [172.28.0.1])
        by mail.redes.unlp.edu.ar (Postfix) with ESMTPS id 80DC62423CF
        for <alumnoimap@redes.unlp.edu.ar>; Thu,  2 Oct 2025 12:24:53 +0000 (UTC)
      Message-ID: <e0000698-3def-25b5-703d-4eaa24ab2050@redes.unlp.edu.ar>
      Date: Thu, 2 Oct 2025 09:24:48 -0300
      MIME-Version: 1.0
      User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:91.0) Gecko/20100101
      Thunderbird/91.12.0
      Content-Language: en-US
      To: alumnoimap@redes.unlp.edu.ar
      From: alumnopop <alumnopop@redes.unlp.edu.ar>
      Subject: MAIL para captura Wireshark
      Content-Type: text/plain; charset=UTF-8; format=flowed
      Content-Transfer-Encoding: 7bit

      Cuerpo del mensaje!

      )
      183 OK Fetch completed (0.001 + 0.000 secs).
      184 UID fetch 7 (UID BODY.PEEK[HEADER.FIELDS (Content-Type Content-Transfer-Encoding)] BODY.PEEK[TEXT]<0.2048>)
      * 5 FETCH (UID 7 BODY[HEADER.FIELDS (CONTENT-TYPE CONTENT-TRANSFER-ENCODING)] {91}
      Content-Type: text/plain; charset=UTF-8; format=flowed
      Content-Transfer-Encoding: 7bit

      BODY[TEXT]<0> {23}
      Cuerpo del mensaje!

      )
      184 OK Fetch completed (0.002 + 0.000 + 0.001 secs).
      185 IDLE
      + idling
    ```
  - **ii**
    - Ejemplo
    ```wireshark
      220 mail.redes.unlp.edu.ar ESMTP Postfix (Lihuen-4.01/GNU)
      EHLO [172.28.0.1]
      250-mail.redes.unlp.edu.ar
      250-PIPELINING
      250-SIZE 10240000
      250-VRFY
      250-ETRN
      250-STARTTLS
      250-ENHANCEDSTATUSCODES
      250-8BITMIME
      250-DSN
      250 CHUNKING
      MAIL FROM:<alumnopop@redes.unlp.edu.ar> BODY=8BITMIME SIZE=453
      250 2.1.0 Ok
      RCPT TO:<alumnoimap@redes.unlp.edu.ar>
      250 2.1.5 Ok
      DATA
      354 End data with <CR><LF>.<CR><LF>
      Message-ID: <62af4b3a-a06d-ec3c-4949-cd7c3046a656@redes.unlp.edu.ar>
      Date: Thu, 2 Oct 2025 09:38:31 -0300
      MIME-Version: 1.0
      User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:91.0) Gecko/20100101
      Thunderbird/91.12.0
      Content-Language: en-US
      To: alumnoimap@redes.unlp.edu.ar
      From: alumnopop <alumnopop@redes.unlp.edu.ar>
      Subject: MAIL para captura
      Content-Type: text/plain; charset=UTF-8; format=flowed
      Content-Transfer-Encoding: 7bit

      Cuerpo

      .
      250 2.0.0 Ok: queued as 5E32D2423BB
      QUIT
      221 2.0.0 Bye
    ```
    - El servidor avisa que se realizó la conexión con EHLO [172.28.0.1]
    - Luego muestra todas las opciones de comandos que el servidor entiende (250-...)
    - Luego el cliente dice que quiere mandar un mail
    - Luego a quien quiere mandarlo
    - Luego los datos 
    - El servidor dice que debe terminar con "."
    - El cliente cierra la conexión 

#### Inciso c

- Se utiliza para el envío de caracteres no imprimibles de un archivo binario como puede ser, por ejemplo, una imagen 
- luego de obtener el fuente mediante el TCP stream de la captura de wireshark, guardamos el fuente en un archivo `imagen.b64`, luego lo decodificamos i lo guardamos en un archivo `imagen.png` con el siguiente comando:
  ```bash
  base64 -d imagen.b64 > imagen.png
  ```

### Ejercicio 4

- El TCP stream de la primera captura fue:
  ```
    +OK Dovecot ready.
    CAPA
    +OK
    CAPA
    TOP
    UIDL
    RESP-CODES
    PIPELINING
    AUTH-RESP-CODE
    STLS
    USER
    SASL PLAIN
    .
    AUTH PLAIN
    + 
    AGFsdW1ub3BvcABhbHVtbm9wb3BwYXNz
    +OK Logged in.
    STAT
    +OK 3 2621
    LIST
    +OK 3 messages:
    1 1021
    2 782
    3 818
    .
    UIDL
    +OK
    1 0000000356eaa394
    2 0000000456eaa394
    3 0000000556eaa394
    .
    RETR 3
    +OK 818 octets
    Return-Path: <alumnoimap@redes.unlp.edu.ar>
    X-Original-To: alumnopop@redes.unlp.edu.ar
    Delivered-To: alumnopop@redes.unlp.edu.ar
    Received: from [172.28.0.1] (unknown [172.28.0.1])
      by mail.redes.unlp.edu.ar (Postfix) with ESMTP id AD00224235F
      for <alumnopop@redes.unlp.edu.ar>; Wed,  8 Oct 2025 22:20:34 +0000 (UTC)
    Message-ID: <2e9d88b3-579b-885e-e794-2195f3330e28@redes.unlp.edu.ar>
    Date: Wed, 8 Oct 2025 19:20:29 -0300
    MIME-Version: 1.0
    User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:91.0) Gecko/20100101
    Thunderbird/91.12.0
    Content-Language: en-US
    To: alumnopop <alumnopop@redes.unlp.edu.ar>
    From: alumnoimap <alumnoimap@redes.unlp.edu.ar>
    Subject: Mensaje captura punto 4
    Content-Type: text/plain; charset=UTF-8; format=flowed
    Content-Transfer-Encoding: 7bit

    este es el cuerpo del mensaje

    .
    QUIT
    +OK Logging out.
  ```
  - El servidor POP3 (Dovecot en este caso) indica que está listo para recibir comandos con el mensaje `+OK Dovecot ready.`
  - Con `CAPA` el cliente pregunta qué capacidades soporta el servidor
  - El servidor responde con los comando que soporta
    - `+OK`: Indica que el comando fue exitoso
    - `CAPA`: Permite al cliente solicitar las capacidades del servidor
    - `TOP`: Permite obtener encabezados y primeras líneas de un mensaje
    - `UIDL`: Proporciona un identificador único para cada mensaje
    - `RESP-CODES`: Indica que el servidor puede enviar códigos de respuesta extendidos
    - `PIPELINING`: Permite enviar múltiples comandos sin esperar respuestas de cada uno
    - `AUTH-RESP-CODE`: Indica que el servidor puede enviar códigos de respuesta durante la autenticación
    - `STLS`: Indica que el servidor soporta la extensión de seguridad TLS
    - `USER`: Indica que el servidor soporta el comando USER para autenticación
    - `SASL PLAIN`: Indica que el servidor soporta autenticación SAS
  - Luego continua el intercambio de mensajes entre cliente y servidor (los comandos que ejecuta el cliente que no fueron listados en por el CAPA son parte del estándar POP3)

### Ejercicio 5

- El TCP stream de la primera captura fue:
  ```
    * OK Still here
    DONE
    78 OK Idle completed (120.124 + 120.123 + 120.123 secs).
    79 check
    79 OK Check completed (0.001 + 0.000 secs).
    80 UID fetch 10:* (FLAGS)
    * 7 FETCH (UID 9 FLAGS (\Seen))
    80 OK Fetch completed (0.001 + 0.000 secs).
    81 IDLE
    + idling
    * 8 EXISTS
    * 1 RECENT
    DONE
    81 OK Idle completed (3.678 + 3.664 + 3.677 secs).
    82 noop
    82 OK NOOP completed (0.001 + 0.000 secs).
    83 UID fetch 10:* (FLAGS)
    * 8 FETCH (UID 10 FLAGS (\Recent))
    83 OK Fetch completed (0.001 + 0.000 secs).
    84 UID fetch 10 (UID RFC822.SIZE FLAGS BODY.PEEK[HEADER.FIELDS (From To Cc Bcc Subject Date Message-ID Priority X-Priority References Newsgroups In-Reply-To Content-Type Reply-To)])
    * 8 FETCH (UID 10 RFC822.SIZE 812 FLAGS (\Recent) BODY[HEADER.FIELDS (FROM TO CC BCC SUBJECT DATE MESSAGE-ID PRIORITY X-PRIORITY REFERENCES NEWSGROUPS IN-REPLY-TO CONTENT-TYPE REPLY-TO)] {286}
    Message-ID: <35d2f0f6-4ddc-4747-dddb-08a5116195f7@redes.unlp.edu.ar>
    Date: Wed, 8 Oct 2025 19:37:22 -0300
    To: alumnoimap@redes.unlp.edu.ar
    From: alumnopop <alumnopop@redes.unlp.edu.ar>
    Subject: mensaje para captura punto 5
    Content-Type: text/plain; charset=UTF-8; format=flowed

    )
    84 OK Fetch completed (0.009 + 0.000 + 0.008 secs).
    85 UID fetch 10 (UID RFC822.SIZE BODY.PEEK[])
    * 8 FETCH (UID 10 RFC822.SIZE 812 BODY[] {812}
    Return-Path: <alumnopop@redes.unlp.edu.ar>
    X-Original-To: alumnoimap@redes.unlp.edu.ar
    Delivered-To: alumnoimap@redes.unlp.edu.ar
    Received: from [172.28.0.1] (unknown [172.28.0.1])
      by mail.redes.unlp.edu.ar (Postfix) with ESMTP id F35ED242360
      for <alumnoimap@redes.unlp.edu.ar>; Wed,  8 Oct 2025 22:37:27 +0000 (UTC)
    Message-ID: <35d2f0f6-4ddc-4747-dddb-08a5116195f7@redes.unlp.edu.ar>
    Date: Wed, 8 Oct 2025 19:37:22 -0300
    MIME-Version: 1.0
    User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:91.0) Gecko/20100101
    Thunderbird/91.12.0
    Content-Language: en-US
    To: alumnoimap@redes.unlp.edu.ar
    From: alumnopop <alumnopop@redes.unlp.edu.ar>
    Subject: mensaje para captura punto 5
    Content-Type: text/plain; charset=UTF-8; format=flowed
    Content-Transfer-Encoding: 7bit

    este es el cuerpo del mensaje

    )
    85 OK Fetch completed (0.001 + 0.000 secs).
    86 UID fetch 10 (UID BODY.PEEK[HEADER.FIELDS (Content-Type Content-Transfer-Encoding)] BODY.PEEK[TEXT]<0.2048>)
    * 8 FETCH (UID 10 BODY[HEADER.FIELDS (CONTENT-TYPE CONTENT-TRANSFER-ENCODING)] {91}
    Content-Type: text/plain; charset=UTF-8; format=flowed
    Content-Transfer-Encoding: 7bit

    BODY[TEXT]<0> {33}
    este es el cuerpo del mensaje

    )
    86 OK Fetch completed (0.002 + 0.000 + 0.001 secs).
    87 IDLE
    + idling
    DONE
    87 OK Idle completed (3.976 + 3.976 + 3.975 secs).
    88 noop
    88 OK NOOP completed (0.001 + 0.000 secs).
    89 UID fetch 11:* (FLAGS)
    * 8 FETCH (UID 10 FLAGS (\Recent))
    89 OK Fetch completed (0.001 + 0.000 secs).
    90 IDLE
    + idling
    DONE
    90 OK Idle completed (2.585 + 2.584 + 2.584 secs).
    91 uid store 10 +Flags (\Seen)
    * 8 FETCH (UID 10 FLAGS (\Seen \Recent))
    91 OK Store completed (0.001 + 0.000 secs).
    92 IDLE
    + idling
  ``` 
  - El servidor IMAP indica que está listo para aceptar comandos con el mensaje `* OK Still here`
  - El comando `IDLE` pone al cliente en modo espera para recibir notificaciones de nuevos mensajes o cambios en el buzón
    - `EXISTS`: Indica el número total de mensajes en el buzón
    - `RECENT`: Indica el número de mensajes nuevos desde la última vez que el cliente consultó el buzón
  - `DONE` finaliza el modo IDLE y permite al cliente enviar nuevos comandos
  - `Check` asegura que todos los cambios locales en la carpeta han sido registrados en el servidor
  - `UID fetch` solicita información de mensajes usando sus identificadores únicos (UID) en lugar de un número secuencial
    - `\Seen`: Marca que el mensaje ha sido leído
    - `\Recent`: Marca que el mensaje es nuevo
  - Luego se pasan parámetros al comando `UID fetch` para obtener diferentes partes del mensaje, como encabezados y cuerpo, y descargar el contenido del mensaje, entre otros

### Ejercicio 6

- En el buzón de entrada de alumnopop, se ven todos los mensajes recibidos, marcados como no leídos ya que se leyeron localmente desde la cuenta "redes" y no de la cuenta "root", por esto mismo, la carpeta POP desaparece, ya que fue creada localmente por la cuenta redes y POP no mantiene el estado de los mensajes y las carpetas en el servidor. En cambio en el buzón de entrada de alumnoimap, se ven todos los mensajes recibidos marcados como leídos, y la carpeta IMAP sigue existiendo ya IMAP mantiene el estado de los mensajes y las carpetas en el servidor.
- En base a lo observado, es mejor utilizar IMAP o POP dependiendo de la utilidad que se le vaya a dar y las capacidades del servidor de mail. En caso de POP resultaría beneficioso si se desea acceder a los mensajes desde un solo dispositivo y no se requiere mantener el estado de los mensajes en el servidor, o en el caso de que el servidor tenga recursos muy limitados. Por otro lado, en el caso de IMAP, puede resultar beneficioso, en el caso de que se necesite tener disponible desde cualquier dispositivo los mismos mensajes, carpetas y otras configuraciones.
- El protocolo que utiliza mas recursos del servidor es IMAP, ya que la constante sincronización entre el cliente y el servidor para el mantenimiento del estado de los mensajes, carpetas y configuraciones, resulta mucho más costoso en comparación con POP, que se comunicara con el servidor a demanda del cliente y no mantiene una conexión constante.
  
### Ejercicio 7

El Agente de Usuario de Correo (MUA) envía su mensaje a su servidor de correo, donde es procesado por el Agente de Envío de Correo (MSA), el cual está habitualmente integrado en el MTA local. El MUA utiliza SMTP (o ESMTP) para comunicarse con este servidor de correo saliente. El MUA se conecta a un único servidor local (su MSA/MTA), una vez establecida la conexión TCP con el MSA/MTA local, el cliente puede repetir el proceso de envío a través de la misma conexión TCP si tiene que enviar otros mensajes al servidor. Para cada mensaje adicional, el cliente inicia el proceso con un nuevo comando `MAIL FROM:` y ejecuta el comando `QUIT` solo después de que todos los mensajes hayan sido enviados. Por ende, **independientemente si la comunicación es para múltiples destinatarios del mismo dominio o diferentes dominios, siempre se podrá enviar más de un correo en una conexión TCP entre MUA y MSA**. En cuanto a la comunicación entre Agentes de Transporte de Correos (MTA), depende de si los destinatarios son del mismo dominio o uno diferente, ya que, asumiendo que los domininios se almacenan en un mismo servidor, **el MTA que quiere enviar los mensajes, deberá abrir una conexión por cada servidor receptor, es decir los mensajes destinados a un mismo dominio podrán viajar sobre la misma conexión, mientras que los mensajes destinados a dominios diferentes viajarán en conexiones TCP diferentes**

### Ejercicio 8

Sí, es posible que el Agente de Envío de Correo (MSA) escuche en un puerto TCP diferente a los convencionales, pero esto implica que los  Agentes de Usuario de Correo (MUA) del dominio y la infraestructura de red (como firewalls y NAT) deberán configurarse correctamente para permitir el tráfico en ese puerto no estándar. Además, podría generar problemas de interoperabilidad y soporte ya que la mayoría de las aplicaciones y servicios están diseñados para operar en los puertos estándar de SMTP. 

### Ejercicio 9

Sí, es posible que un Agente de Transporte de Correo (MTA) escuche en un puerto TCP diferente a los convencionales. Esto no es aconsejable debido a las implicaciones en la interoperabilidad, entrega de correos y la compatibilidad de otros servicios. EL puerto 25 es un estándar universal para la entrega de correos entre servidores, y cambiarlo puede romper la comunicació con otros MTAs, causando fallos en la entrega de correos a nivel global

### Ejercicio 10

#### Inciso a 

- Hacer visible el dominio `redes2024.com.ar` en Internet significa que los servidores DNS de la jerarquía global, especialmente los servidores de Dominio de Nivel Superior (TLD, Top-Level Domain), deben saber qué servidores son los responsables de responder a las consultas para el dominio `redes2024.com.ar`. Por ende, deberíamos agregar los dos registros NS correspondientes a los servidores autoritativos `ns1.redes2024.com.ar` y `ns2.redes2024.com.ar` y el A correspondiente a cada uno de los dos `203.0.113.65` y `203.0.113.66` respectivamente.

#### Inciso b 

- `redes2024.com.ar IN SOA ns1.redes2024.com.ar. root.ns1.redes2024.com.ar. ( 2024101001 ; Serial 7200 ; Refresh 3600 ; Retry 1209600 ; Expiry 604800)`
- `redes2024.com.ar 86400 IN NS ns1.redes2024.com.ar.`
- `redes2024.com.ar 86400 IN NS ns2.redes2024.com.ar.`
- `ns1.redes2024.com.ar 86400 IN A 203.0.113.65`
- `ns2.redes2024.com.ar 86400 IN A 203.0.113.66`
- `redes2024.com.ar 86400 IN MX 10 mail.redes2024.com.ar.`
- `mail.redes2024.com.ar 86400 IN A 203.0.113.111`
- `correo.redes2024.com.ar 86400 IN A 203.0.113.8`
- `webmail.redes2024.com.ar 86400 IN CNAME correo.redes2024.com.ar`

>[!note]
>**SOA (Start Of Authority)**: es obligatorio para definir la zona de autoridad. Especifica el servidor de nombres primario (`ns1.redes2024.com.ar`), una dirección de correo para el administrador (en este caso `root.ns1.redes2024.com.ar`) y varios parámetros de temporización cruciales para la transferencia de zona y el caching (como el Número de Serie, Refresh, Retry y Expiry)  
> **CNAME (Canonical Name)**: se utiliza para crear un alias más fácil de recordar para un nombre de host canónico (real). El usuario accederá al servicio de webmail a través de https://webmail.redes2024.com.ar. Por lo tanto, webmail actúa como un alias que apunta al nombre canónico del servidor web, que en este caso es `correo.redes2024.com.ar`

#### Inciso c

- No es necesario que el servidor DNS acepte consultas recursivas. Cuando el servidor autoritativo (`ns1` o `ns2`) recibe una consulta (que es una consulta iterativa proveniente de un servidor DNS local externo), simplemente devuelve la respuesta que ya posee en su base de datos de Registros de Recursos (RR). Por lo tanto, el servidor autoritativo no necesita realizar el trabajo de recursión. De hecho, es recomendable que los servidores DNS autoritativos no permitan consultas recursivas por:
- **Funcionalidad**
  - La funcionalidad del servidor DNS autoritativo es responder con los registros DNS correspondientes al dominio del cuál es autoritativo cuando se le consulte directamente, no realizar consultas recursivas en nombre de otros clientes
- **Rendimiento**
  - Aceptar consultas recursivas consume más recursos del servidor DNS, ya que implica que este debe resolver dominios externos, lo que aumenta la carga de trabajo innecesariamente para un servidor cuya principal función es reesponder a consultas sobre su propia zona

Tener en cuenta que podría darse el caso de que el servidor DNS además de ser autoritativo, sea el servidor DNS local de la zona, por lo que en ese caso si debería aceptar consultas recursivas para los clientes internos de la red (NO para los externos).

>[!important]
> Recordar que se puede configurar en un servidor DNS de quienes aceptar consultas recursivas y de quienes no, por ejemplo, permitir consultas recursivas solo para clientes internos de la red y no para clientes externos
  
#### Inciso d

- **Servidor DNS primario (ns1) y secundario (ns2)**
- **Servidor de correo electrónico**
  - **SMTP**: para recepción y envío de correo (MTA a MTA) de otros servidores de Internet
  - **IMAP**: para permitir a los usuarios gestionar carpetas, manipular mensajes en el servidor y mantener el estado en diferentes dispositivos 
- **Servidor WEB**: para proporcionar acceso al webmail a través de la URL https://webmail.redes2024.com.ar.
  - **HTTPs**: combinación de HTTP y SSL/TLS, para permitir la transferencia segura de documentos web para que los usuarios puedan gestionar sus correos de forma segura

#### Inciso e  

| Servidor                     | Protocolo de Transporte | Número de Puerto |
|------------------------------|--------------------------|------------------|
| **DNS Primario (ns1)**       | UDP/TCP                      | 53(DNS)               | 
| **DNS Secundario (ns2)**     | UDP/TCP                      | 53(DNS)               | 
| **Servidor de Correo (mail)**| TCP                      | 25(SMTP)               | 
| **Servidor de Correo (mail)**| TCP                      | 110(POP3)              | 
| **Servidor de Correo (mail)**| TCP                      | 143(IMAP)              | 
| **Servidor WEB/Webmail (correo)** | TCP                 | 80(HTTP)               | 
| **Servidor WEB/Webmail (correo)** | TCP                 | 443(HTTPS)             |

#### Inciso f

- El webmail, en este escenario, actúa como el Agente de Usuario de Correo (MUA, Mail User Agent) o cliente, que se comunica con el servidor de correo para dos funciones principales: 
  - **Recibir o acceder al correo**
    - **IMAP**: permite que webmail lea, gestione y manipule los correos en el servidor
    - **POP3**: permite que webmail recupere (descargue) los mensjaes del buzón del usuario
    - Para un servicio de webmail moderno, se utilizaría IMAP debido a su mayor flexibilidad para gestionar correos remotamente, carpetas y estados, lo cual es fundamental para una interfaz web
  - **Enviar correos**
    - **SMTP**: permite que **webmail** (cliente) inserte (push) el mensaje en el servidor de correo, en este caso **mail**, que funciona como un MSA/MTA
- Dado que el acceso de los usuarios al webmail se realiza a través de HTTPS (https://webmail.redes2024.com.ar), es una práctica estándar que la conexión interna entre el servidor web (webmail) y el servidor de correo también utilice SSL/TLS para cifrar datos.
- En resumen, la conexión se realizaría a través de una conexión TCP subyacente, utilizando los protocolos de la capa de aplicación IMAP/POP3 (para acceder al buzón) y SMTP (para enviar mensajes), y podrían estar asegurados mediante SSL/TLS

#### Inciso g

- La forma de asegurar que cualquier MTA reconozca como válidos los correos electrónicos provenientes del dominio `redes2024.com.ar` únicamente si llegan desde la dirección `203.0.113.111 ` es mediante la implementación de un registro **SPF (Sender Policy Framework)** en el DNS. **SPF** es un mecanismo diseñado para **contrarrestar el correo basura** (spam) al permitir a los **propietarios de dominios especificar qué servidores de correo están autorizados para enviar mensajes en nombre de ese dominio**. La configuración de SPF se realiza **añadiendo** un **registro de recurso** (RR) de tipo **TXT** (Textual) a la zona redes2024.com.ar en su servidor DNS primario (`ns1 - 203.0.113.65`). Los registros TXT almacenan información descriptiva para el dominio. El contenido de este registro TXT debe declarar explícitamente que la única dirección IP autorizada para enviar correo para este dominio es la de su servidor de correo, 203.0.113.111, por ejemplo, `v=spf1 ip4:203.0.113.111 -all`. Cuando un servidor **MTA receptor** (el servidor de correo del destinatario) recibe un mensaje de correo electrónico que afirma ser de `usuario@redes2024.com.ar`, el servidor receptor (que actúa como servidor SMTP) **realiza una consulta DNS buscando el registro SPF (TXT) asociado con el dominio redes2024.com.ar**. Si la dirección IP de origen de la conexión SMTP coincide con la lista de direcciones autorizadas en el registro SPF, el MTA receptor considera el correo como legítimo, sino, el receptor determinará que el correo no es válido según su política de SPF.
- El servidor de webmail no debería verse afectado por esta nueva configuración, siempre y cuando los correos enviados desde webmail pasen por el servidor de correo que tiene la IP autorizada (`203.0.113.111`)

>[!note]
> `v=spf1 ip4:203.0.113.111 -all`
> - `v=spf1`: Indica la versión del SPF que se está utilizando, en este caso la versión 1
> - `ip4:203.0.113.111`: Especifica que la dirección IPv4 203.0.113.111 está autorizada para enviar correos en nombre del dominio
> - `-all`: Indica que cualquier otro servidor no listado en el registro SPF no está autorizado para enviar correos en nombre del dominio. El prefijo "-" significa que los correos de servidores no autorizados deben ser rechazados

#### Inciso h

- La característica **SMTP**, que indirectamente afecta el manejo de mensajes por parte de **IMAP** y **POP**, es su restricción de diseño a utilizar únicamente caracteres **ASCII de 7 bits** para la **transferencia de mensajes**. Esta característica es la que **obliga a codificar datos binarios** (como imágenes, ejecutables, o archivos multimedia) en un formato que cumpla con los estándares ASCII de 7 bits, utilizando métodos como **Base64**

#### Inciso i y j 

- Sí, esta manipulación del destinatario y remitente visible en el cliente de correo es posible debido a cómo está estructurado el protocolo SMTP y su distinción entre la información de transporte y la de visualización. La arquitectura del correo electrónico utiliza dos tipos principales de información de direccionamiento que se manejan por separado durante el proceso de envío
  - **Sobre (Envelope)**: Esta información es utilizada por los Agentes de Transporte de Correo (MTA) para determinar la ruta y el buzón final donde se debe depositar el mensaje. Los comandos SMTP que gestionan el sobre son `MAIL FROM:` (remitente de la ruta) y `RCPT TO:` (destinatario de la ruta)
  - **Encabezado (Header):** Esta es la meta-información del mensaje que el usuario final ve en su cliente de correo (Agente de Usuario de Correo o MUA). Incluye campos como `From:`, `Subject:`, y `To:`
- El correo podría no llegar a la ruta que dice en el `To:` del **encabezado (Header)** ya que el enrutamiento y la entrega final son determinados por la dirección especificada en el `RCPT TO:` del **sobre (Envelope)**, como también podría no provenir de la ruta que dice el `From:`, ya que la verdadera ruta se encuentra en el `MAIL FROM:`. Los Agentes de Transporte de Correo (MTA) que mueven el mensaje a través de Internet (MTA-MTA) y el Agente de Entrega de Correo (MDA) que lo deposita en el buzón, solo utilizan el **sobre (Envelope)** para guiar el datagrama. Los campos `To:` o `From:` dentro del **encabezado (Header)** son simplemente meta-información leída por el agente de usuario del receptor, y es ignorada por la infraestructura de enrutamiento del correo. 
- Esta capacidad de suplantación del campo `To:` o `From:` es frecuentemente una indicación de estafa, como email spoofing o phishing, aunque en el caso del campo `To:` también puede deberse al uso legítimo de Copia Oculta (BCC), que permite enviar un mensaje a un destinatario sin que su dirección aparezca visible para los demás receptores del mensaje.

#### Inciso k

- Para enviar el correo con remitente `redes@info.unlp.edu.ar`, el MUA utilizará el protocolo SMTP con el fin de comunicarse con el MTA local del dominio `info.unlp.edu.ar`. Para lograr esta conexión, el cliente necesita conocer la dirección IP del servidor, información que obtiene realizando una consulta DNS para resolver el nombre de dominio del servidor SMTP a su correspondiente registro A (IPv4) o AAAA (IPv6). Cabe mencionar que si el acceso se realizara mediante una interfaz de Webmail, el usuario interactuaría inicialmente mediante HTTP/HTTPS, siendo luego el servidor web quien se encargue del envío vía SMTP.

#### Inciso l

- Los correos que intenten ingresar durante el reinicio **no se perderán**. El MTA del remitente detectará que el servidor de destino no responde y almacenará los mensajes en una **cola de reintentos**. El protocolo SMTP establece que el remitente debe volver a intentar el envío periódicamente. **Una vez que el servidor finalice el reinicio** y esté operativo, **recibirá los correos acumulados** en el siguiente intento de conexión de los remitentes.

#### Inciso m

- Agregamos un registro MX correspondiente al servidor de mail de la nube. Al agregar este nuevo registro, tenemos que configurar su prioridad. Así, cuando un MTA externo desee enviar un correo, primero intentará establecer una conexión TCP con el servidor de prioridad mas alta (valor más bajo) y si este no está operativo, intentará con el otro de menos prioridad. Seguiría asi sucesivamente a medida que se agreguen más servidores de correo.

### Ejercicio 11

#### Inciso a

- **i**
  - La respuesta a EHLO fue:
    ```bash
       -> EHLO debian
       <-  250-mail.redes.unlp.edu.ar
       <-  250-PIPELINING
       <-  250-SIZE 10240000
       <-  250-VRFY
       <-  250-ETRN
       <-  250-STARTTLS
       <-  250-ENHANCEDSTATUSCODES
       <-  250-8BITMIME
       <-  250-DSN
       <-  250 CHUNKING
    ```
  - Cada línea describe una capacidad (extensión ESMTP) del servidor de correo.
  - `PIPELING`: Permite que el cliente envíe varios comandos SMTP seguidos sin esperar una respuesta inmediata por cada uno, reduciendo la latencia de la sesión. Mejora el rendimiento del envío de múltiples correos o adjuntos.
  - `STARTRLS`: Indica que el servidor soporta cifrado TLS (Transport Layer Security). Permite que la comunicación SMTP pase de texto plano a una conexión segura (cifrada) después del comando STARTTLS. Se usa para proteger credenciales y el contenido del correo frente a escuchas en red.
- **ii**
  - Las cabeceras de la salida fueron:
    ```bash
        -> Date: Fri, 10 Oct 2025 19:12:06 -0300
        -> To: alumnoimap@redes.unlp.edu.ar
        -> From: redesycomunicaciones@redes.unlp.edu.ar
        -> Subject: SMTP-Practica4
        -> Message-Id: <20251010191206.005029@debian>
        -> X-Mailer: swaks v20201014.0 jetmore.org/john/code/swaks/
        -> MIME-Version: 1.0
        -> Content-Type: multipart/mixed; boundary="----=_MIME_BOUNDARY_000_5029"
        -> 
        -> ------=_MIME_BOUNDARY_000_5029
        -> Content-Type: text/plain
        -> 
        -> Esto es una prueba del protocolo SMTP
        -> ------=_MIME_BOUNDARY_000_5029
        -> Content-Type: application/octet-stream; name="Practica4-Mail.pdf"
        -> Content-Description: Practica4-Mail.pdf
        -> Content-Disposition: attachment; filename="Practica4-Mail.pdf"
        -> Content-Transfer-Encoding: BASE64
    ```
  - De ellas, Swaks agrega automáticamente
    - `Date`: Fecha y hora en que se envió el correo
    - `Message-ID`: Identificador único del mensaje
    - `X-Mailer`: Información sobre la herramienta utilizada para enviar el correo (Swaks en este caso)
    - `MIME-Version`: Versión del estándar MIME utilizado
    - `Content-Type`: Tipo de contenido del mensaje (aquí multipart/mixed indicando que hay múltiples partes, como texto y adjuntos)
    - `Content-Transfer-Encoding`: Método de codificación del contenido (aquí BASE64 para el adjunto)
  - Las demás cabeceras (`To`, `From`, `Subject`) fueron especificadas manualmente en el comando Swaks (`--to`, `--from`, `--header`)
- **iii**
  - EL message-id del correo es `<20251010191206.005029@debian>`. Este valor lo asigna el cliente de correo (Swaks en este caso) automáticamente al enviar el mensaje. Normalmente se construye combinando la fecha/hora + un número de proceso + el nombre del host local (@debian). El Message-ID es único para cada mensaje y sirve para identificarlo de manera inequívoca en las comunicaciones de correo electrónico.
- **iv**
  - El software utilizado como servidor de correo electrónico es Postfix, un MTA de código abierto, incluído en muchas distribuciones Linux. Postfix es conocido por su seguridad, rendimiento y facilidad de configuración. En este caso, Postfix está configurado para aceptar conexiones SMTP en el puerto 25 y manejar el envío y recepción de correos electrónicos.
- **v**
  - Salida del comando swaks
    ```bash
    === Trying mail.redes.unlp.edu.ar:25...
    === Connected to mail.redes.unlp.edu.ar.
    <-  220 mail.redes.unlp.edu.ar ESMTP Postfix (Lihuen-4.01/GNU)
    -> EHLO debian
    <-  250-mail.redes.unlp.edu.ar
    <-  250-PIPELINING
    <-  250-SIZE 10240000
    <-  250-VRFY
    <-  250-ETRN
    <-  250-STARTTLS
    <-  250-ENHANCEDSTATUSCODES
    <-  250-8BITMIME
    <-  250-DSN
    <-  250 CHUNKING
    -> MAIL FROM:<redesycomunicaciones@redes.unlp.edu.ar>
    <-  250 2.1.0 Ok
    -> RCPT TO:<alumnoimap@redes.unlp.edu.ar>
    <-  250 2.1.5 Ok
    -> DATA
    <-  354 End data with <CR><LF>.<CR><LF>
    -> Date: Fri, 10 Oct 2025 19:12:06 -0300
    -> To: alumnoimap@redes.unlp.edu.ar
    -> From: redesycomunicaciones@redes.unlp.edu.ar
    -> Subject: SMTP-Practica4
    -> Message-Id: <20251010191206.005029@debian>
    -> X-Mailer: swaks v20201014.0 jetmore.org/john/code/swaks/
    -> MIME-Version: 1.0
    -> Content-Type: multipart/mixed; boundary="----=_MIME_BOUNDARY_000_5029"
    -> 
    -> ------=_MIME_BOUNDARY_000_5029
    -> Content-Type: text/plain
    -> 
    -> Esto es una prueba del protocolo SMTP
    -> ------=_MIME_BOUNDARY_000_5029
    -> Content-Type: application/octet-stream; name="Practica4-Mail.pdf"
    -> Content-Description: Practica4-Mail.pdf
    -> Content-Disposition: attachment; filename="Practica4-Mail.pdf"
    -> Content-Transfer-Encoding: BASE64
    ->
    ... (contenido del archivo PDF en base64) ...
    ->
    -> ------=_MIME_BOUNDARY_000_5029--
    -> 
    -> 
    -> .
    <-  250 2.0.0 Ok: queued as F32F3242356
    -> QUIT
    <-  221 2.0.0 Bye
    === Connection closed with remote host.
    ```
  - Fuentes del correo
    ```bash
    Return-Path: <redesycomunicaciones@redes.unlp.edu.ar>
    X-Original-To: alumnoimap@redes.unlp.edu.ar
    Delivered-To: alumnoimap@redes.unlp.edu.ar
    Received: from debian (unknown [172.28.0.1])
      by mail.redes.unlp.edu.ar (Postfix) with ESMTP id F32F3242356
      for <alumnoimap@redes.unlp.edu.ar>; Fri, 10 Oct 2025 22:12:11 +0000 (UTC)
    Date: Fri, 10 Oct 2025 19:12:06 -0300
    To: alumnoimap@redes.unlp.edu.ar
    From: redesycomunicaciones@redes.unlp.edu.ar
    Subject: SMTP-Practica4
    Message-Id: <20251010191206.005029@debian>
    X-Mailer: swaks v20201014.0 jetmore.org/john/code/swaks/
    MIME-Version: 1.0
    Content-Type: multipart/mixed; boundary="----=_MIME_BOUNDARY_000_5029"

    ------=_MIME_BOUNDARY_000_5029
    Content-Type: text/plain

    Esto es una prueba del protocolo SMTP
    ------=_MIME_BOUNDARY_000_5029
    Content-Type: application/octet-stream; name="Practica4-Mail.pdf"
    Content-Description: Practica4-Mail.pdf
    Content-Disposition: attachment; filename="Practica4-Mail.pdf"
    Content-Transfer-Encoding: BASE64

    ... (contenido del archivo PDF en base64) ...

    ------=_MIME_BOUNDARY_000_5029--
    ```

#### Inciso b

- El contenido del mail no puede leerse en la captura porque la sesión SMTP se negoció y se protegió con TLS, por lo que el cuerpo y el adjunto viajan cifrados. Además, el adjunto está codificado en Base64 dentro del MIME, así que incluso si la sesión no estuviera cifrada el adjunto no sería legible como texto plano.

#### Inciso c

- El registro SPF del dominio `info.unlp.edu.ar` especifica una gran cantidad de servidores autorizados para enviar correos en nombre del dominio. Esto ocurre porque la Universidad Nacional de La Plata utiliza una infraestructura de correo distribuida, con múltiples servidores y servicios especializados. Cada dependencia o sistema (por ejemplo, bibliotecas, cátedras, plataforma Moodle, listas de correo, o servidores de extensión) puede generar correos legítimos utilizando el dominio `info.unlp.edu.ar`. Por ese motivo, todos esos servidores se incluyen en el registro SPF, de modo que los mensajes enviados desde cualquiera de ellos no sean marcados como spam y puedan ser verificados como legítimos por los destinatarios.
- `v=spf1` indica la version del estándar SPF, 1 en este caso
- `mx` autoriza a los servidores definidos conmo MX del dominio
- `a:servidor.info.unlp.edu.ar` autoriza a ese servidor específico a enviar correos en nombre de `info.unlp.edu.ar`

#### Inciso d

- Los bloques de red autorizados para enviar mails del dominio `outlook.com` son:
  - Directamente `157.55.9.128/25`
    - el rango `/25` incluye las direcciones desde `157.55.9.128` hasta `157.55.9.255`
  - Indirectamente 
    - `spf-a.outlook.com`
    - `spf-b.outlook.com`
    - `spf2.outlook.com`
    - `_spf-ssg-b.microsoft.com`
    - `_spf-ssg-c.microsoft.com`
- `~all` marca como "no autorizados" (softail) a los servidores que no estén listados. los correos que no provienen de servidores autorizados no deben confiarse, pero tampoco rechazarse automáticamente. Sirve para detectar fuentes no autorizadas sin cortar el tráfico legítimo accidentalmente.
  
### Ejercicio 12

#### Inciso a

- Es posible, definiendo un puerto diferente para cada servicio, por ejemplo el servicio de mail, `mail1` podría estar escuchando en el puerto 25(SMTP) y el web, `www` podría estar escuchando en el puerto 80(HTTP) o 443(HTTPS)

#### Inciso b

- El MUA consultará por el registro A de su servidor de correo `smtp-5`

#### Inciso c

- Una vez que el mail fue recibido por smtp-5, este hará la consulta DNS recursiva para obtener el registro MX y A del dominio receptor `example.com`, usando como servidor recursivo a `8.8.8.8`.

#### Inciso d

- Se requiere una consulta DNS adicional de tipo `A` para resolver la IP del servidor de correo, que tenga mas prioridad de los devueltos en la primera consulta MX

#### Inciso e

- El servidor `ns1` no es recursivo, por lo tanto, no puede resolver la consulta de los MX de `example.com`

#### Inciso f

- **Falso**, cada protocolo se encarga de desencapsular su propia capa, hasta llegar a la aplicación destino. El MSA se tiene que encargar de analizar los datos de la cabecera para saber si hay algún dato faltante
- **Falso**, cada capa es responsable de procesar y encapsular los datos antes de pasarlos a la capa inferior, por lo que no son analizados por las capas inferiores
- **Verdadero**, en la capa de aplicación protocolos como HTTP, SMTP o FTP, agregan su propia cabecera con información relevante para ese protocolo en particular. Estas cabeceras contienen detalles como dirección de destino, tipo de contenido y otras informaciones especificas del protocolo que ayudan a la correcta interpretación y manejo de los datos por parte del receptor.
- **Falso**, cada protocolo tiene su propia estructura de cabeceras y formato de datos, no necesariamente deberían estar diseñados para funcionar juntos de manera directa
- **Falso**, el protocolo HTTP brinda la abstracción necesaria para que los clientes puedan acceder al servidor HTTP independientemente del sistema operativo subyacente
 
#### Inciso g

- No es posible, ya que el servidor `ns1` no tiene habilitada la recursión, por lo tanto, no podrá resolver consultas para dominios que no sean los que administra directamente

#### Inciso h

- Deberá consultar por el registro `A` de su servidor de correo `mail1`, para poder resolver la IP del servidor SMTP al cual conectarse

#### Inciso i

- Para el envío, el protocolo involucrados seran SMTP, en el puerto 25, y se utilizara TCP, ya que  queremos asegurarnos que la entrega del mensaje sea correcta. Para la recepción, el protocolo involucrado será POP3, en el puerto 110, o IMAP en el puerto 143, y también se utilizará TCP, ya que queremos asegurarnos que la descarga del mensaje sea correcta.
