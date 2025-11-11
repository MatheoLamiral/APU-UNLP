# Práctica 4 - Correo electrónico

### Ejercicio 1

El protocolo principal de la capa de aplicación utilizado para la transferencia de correo electrónico, tanto entre el cliente y su servidor como entre servidores, es el Protocolo simple de transferencia de correo (**SMTP**, Simple Mail Transfer Protocol). El cliente utiliza comandos SMTP para insertar el mensaje en su servidor de correo. Entre Servidores se utiliza SMTP (o ESMTP), los servidores se comunican entre sí utilizando conexiones persistentes y un diálogo interactivo de solicitud/respuesta. SMTP se basa en un servicio de transferencia de datos fiable, como el que ofrece TCP, para asegurar la entrega del mensaje. Es importante notar que, aunque SMTP maneja el envío (es un protocolo push), la sesión puede requerir o no autenticación, y puede trabajar de forma segura utilizando SSL/TLS

### Ejercicio 2

- Los protocolos que se utilizan para la recepción de mails son:
  - **POP3 (Post Office Protocol - versión 3)**, es un protocolo de acceso a correo extremadamente simple, el cliente (MUA) abre una conexión TCP en el puerto 110 al servidor de correo y puede operar de forma segura sobre SSL/TLS. Una sesión POP3 pasa por tres fases, autorización (enviar nombre/contraseña en texto legible), transacción (recuperar mensajes) y actualización (borrar mensajes marcados, que ocurre al salir). El servidor POP3 es esencialmente sin estado entre sesiones. El agente de usuario se configura generalmente para "descargar y borrar" o para "descargar y mantener (guardar)". En el modo "descargar y borrar", los mensajes se eliminan del buzón después de la recuperación (durante la fase de actualización). Una de las mayores limitaciones es que, si un usuario utiliza el modo "descargar y borrar" y luego intenta acceder a su correo desde una computadora diferente, los mensajes recuperados anteriormente no estarán disponibles, ya que fueron eliminados del servidor
  - **IMAP (Internet Mail Access Protocol)**, es un protocolo de acceso que ofrece mucha más funcionalidad que POP3. Utiliza el puerto 143 y también puede ejecutarse de forma segura sobre SSL/TLS. A diferencia de POP3, un servidor IMAP mantiene información sobre el estado a lo largo de las sesiones, como los nombres de las carpetas y los mensajes asociados a cada una. Permite a los usuarios crear y manipular carpetas en el servidor, moviendo mensajes entre ellas (los mensajes se asocian inicialmente a la carpeta INBOX). Los mensajes permanecen en el servidor hasta que el usuario los borra explícitamente. Proporciona comandos para realizar búsquedas en carpetas remotas. Dispone de comandos que permiten al agente de usuario obtener solo partes componentes de los mensajes, como únicamente la cabecera o una parte de un mensaje MIME de varias partes. Esto es útil en conexiones con ancho de banda pequeño para evitar descargar archivos adjuntos grandes (audio o vídeo)
  - **Correo electrónico web (Webmail)**, el acceso al correo electrónico mediante interfaces web (como Hotmail, Yahoo o Gmail) utiliza una arquitectura diferente para la interacción entre el cliente y el servidor. El agente de usuario es un navegador web corriente y la comunicación entre el buzón remoto y el navegador del usuario se realiza a través de HTTP en lugar de POP3 o IMAP. Aunque el usuario acceda a través de HTTP, el servidor de correo sigue utilizando SMTP para enviar y recibir mensajes de otros servidores de correo. No utiliza protocolos especializados de acceso a correo (POP3/IMAP) para la interacción cliente-servidor, sino que se basa en el protocolo HTTP

### Ejercicio 3

- Inciso b
  - i
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
  - ii
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
- Inciso c
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

Sí, es posible que el Agente de Envío de Correo (MSA) escuche en un puerto TCP diferente a los convencionales. De hecho, existe una recomendación específica de la IETF para esta función. El protocolo SMTP tradicionalmente utiliza el puerto servidor 25. Aunque el MSA habitualmente usaba el puerto 25, se recomienda usar el puerto 587 (submission), según el RFC-6409 (anteriormente RFC-4409). Si un desarrollador crea una nueva aplicación de red, debe asignarle un número de puerto. Es técnicamente posible usar un puerto no estándar (fuera del rango de puertos bien conocidos 0-1023), siempre que el lado del servidor de la aplicación y el lado del cliente usen el mismo número de puerto.
- Implicancias de usar un puerto TCP diferente
  - **Separación de Roles**: El MSA es el agente que pre-procesa el mensaje recibido desde el MUA. La distinción de puertos facilita la separación de roles, donde el puerto 25 es típicamente usado para la transferencia de MTA a MTA, mientras que puertos como el 465 y 587 (junto con el 25) se asocian con la comunicación MUA-MTA que requiere autenticación (auth)
  - **Configuración del Cliente (MUA a MSA)**: La combinación de la dirección IP del host junto con el número de puerto de la aplicación identifica el proceso servidor. Si el MSA usa un puerto distinto, el cliente (MUA) debe configurarse para comunicarse con ese puerto específico

### Ejercicio 9

Sí, es posible que un Agente de Transporte de Correo (MTA) escuche en un puerto TCP diferente a los convencionales. El protocolo utilizado para esta transferencia de servidor a servidor es SMTP (Simple Mail Transfer Protocol), y el puerto convencional o estándar para el servicio SMTP es el puerto TCP 25. Es técnicamente posible configurar un MTA o cualquier servidor de aplicación para escuchar en un puerto TCP diferente al asignado.
- Las implicancias de usar un puerto TCP no convencional son:
  - **Interoperabilidad (MTA a MTA)**: Si un MTA se configura para usar un puerto distinto al 25 para la comunicación con otros servidores MTA, la transferencia de correo estándar fallará. Cuando un cliente SMTP (ejecutándose en el servidor de correo emisor) intenta enviar un mensaje, establece una conexión TCP con el puerto 25 del servidor SMTP de destino. Si el servidor de destino no está escuchando en el puerto 25, la conexión predeterminada fallará, ya que los servidores de correo intermedios normalmente no utilizan otros puertos para transferir correo. Cuando un cliente SMTP (ejecutándose en el servidor de correo emisor) intenta enviar un mensaje, establece una conexión TCP con el puerto 25 del servidor SMTP de destino. Si el servidor de destino no está escuchando en el puerto 25, la conexión predeterminada fallará, ya que los servidores de correo intermedios normalmente no utilizan otros puertos para transferir correo. Los protocolos definidos en un RFC (como SMTP) deben usar el número de puerto asociado a ese protocolo para garantizar la interoperabilidad. Si un desarrollador crea una aplicación de red propietaria (que no sigue un RFC), puede usar un número de puerto diferente, pero el código no interoperará con aplicaciones desarrolladas independientemente

### Ejercicio 10

  - Inciso a 
    - Hacer visible el dominio `redes2024.com.ar` en Internet significa que los servidores DNS de la jerarquía global, especialmente los servidores de Dominio de Nivel Superior (TLD, Top-Level Domain), deben saber qué servidores son los responsables de responder a las consultas para el dominio `redes2024.com.ar`
      - La entidad registradora es la responsable de ingresar esta información en los servidores TLD correspondientes al dominio `.com.ar`
      - La información que el registrador inserta en el sistema DNS global (los servidores TLD) consiste en dos tipos de registros de recursos (RR)
        - **Registros NS**: indican cuales son los servidores de nombres autoritativos para el dominio. En este caso `(redes2024.com.ar, ns1.redes2024.com.ar, NS)` y `(redes2024.com.ar, ns2.redes2024.com.ar, NS)` 
        - **Registros de tipo A**: proporcionan las direcciones IP correspondientes a los nombres de los servidores DNS. En este caso `(ns1.redes2024.com.ar, 203.0.113.65, A)` y `(ns2.redes2024.com.ar, 203.0.113.66, A)` 
    - Una vez que estos registros son insertados en el sistema DNS, cuando un host en Internet intente resolver el nombre de cualquier servicio de su dominio, la consulta comenzará en los servidores raíz y será delegada a los servidores TLD, quienes finalmente dirigirán la consulta a sus servidores autoritativos (`ns1` o `ns2`) para obtener la dirección IP final
  - Inciso b 
    | Nombre del registro          | Tipo de registro | Prioridad | TTL    | Valor del registro                                                                                     |
    |------------------------------|------------------|-----------|--------|--------------------------------------------------------------------------------------------------------|
    | @ o redes2024.com.ar         | SOA              | --        | 604800 | ns1.redes2024.com.ar root.ns1.redes2024.com.ar (Serial, Refresh, Retry, Expiry, Neg Cache TTL)        |
    | @ o redes2024.com.ar         | NS               | --        | 604800 | ns1.redes2024.com.ar                                                                                  |
    | @ o redes2024.com.ar         | NS               | --        | 604800 | ns2.redes2024.com.ar                                                                                  |
    | ns1.redes2024.com.ar                          | A                | --        | 604800 | 203.0.113.65                                                                                          |
    | ns2.redes2024.com.ar                          | A                | --        | 604800 | 203.0.113.66                                                                                          |
    | mail.redes2024.com.ar                         | A                | --        | 604800 | 203.0.113.111                                                                                         |
    | correo.redes2024.com.ar                       | A                | --        | 604800 | 203.0.113.8                                                                                           |
    | @ o redes2024.com.ar         | MX               | 10        | 604800 | mail.redes2024.com.ar                                                                                 |
    | webmail.redes2024.com.ar                      | CNAME            | --        | 604800 | correo.redes2024.com.ar                                                                               |

    - **SOA (Start Of Authority)**: es obligatorio para definir la zona de autoridad. Especifica el servidor de nombres primario (ns1.redes2024.com.ar), una dirección de correo para el administrador (aquí representada como root.ns1.redes2024.com.ar) y varios parámetros de temporización cruciales para la transferencia de zona y el caching (como el Número de Serie, Refresh, Retry, Expiry y TTL de caché negativa)
    - **NS (Name Server)**: indican qué servidores son los autoritativos para la zona `redes2024.com.ar.` Estos son los servidores que contienen la base de datos completa de la zona. Se requiere un registro NS para el servidor primario (`ns1`) y otro para el servidor secundario (`ns2`)
    - **A (Adress)**: proporcionan la correspondencia estándar de nombre de host a dirección IP (IPv4). Es crucial tener registros A para:
      - Servidores DNS (ns1, ns2): Es la información que permite a otros servidores encontrar sus servidores autoritativos. Cuando un servidor DNS no es autoritativo para un nombre de host, contendrá un registro A que proporciona la dirección IP del servidor DNS referenciado en el registro NS
      - Servidor de Correo (mail): Necesario para que el registro MX pueda apuntar a una dirección IP real
      - Servidor Web/Webmail (correo): Necesario para que la entrada webmail (CNAME) resuelva finalmente a una dirección IP
    - **MX (Mail Exchanger)**: es fundamental para el servicio de correo, ya que especifica qué host (servidor de correo) es responsable de aceptar mensajes para el dominio. Se aplica al dominio base (redes2024.com.ar). El campo de Prioridad (o preferencia) es un número entero. Si existieran múltiples servidores de correo, el MTA de envío intentaría contactar primero al servidor con el valor de prioridad más bajo (más preferido). En este caso usamos la prioridad de 10 ya que es una elección convencional utilizada para indicar que este es el principal y único destino del correo para el dominio `redes2024.com.ar.`. El valor de un registro MX debe ser el nombre canónico (el nombre de host) del servidor de correo, no la dirección IP directamente
    - **CNAME (Canonical Name)**: se utiliza para crear un alias más fácil de recordar para un nombre de host canónico (real). El usuario accederá al servicio de webmail a través de https://webmail.redes2024.com.ar. Por lo tanto, webmail actúa como un alias que apunta al nombre canónico del servidor web, que en este caso es correo.redes2024.com.ar
    - **NOTA**: `@` es una abreviatura o convención que representa el nombre del dominio de origen o la raíz de la zona que se está configurando
- Inciso c
  - No es necesario que el servidor DNS acepte consultas recursivas. Cuando el servidor autoritativo (`ns1` o `ns2`) recibe una consulta (que es una consulta iterativa proveniente de un servidor DNS local externo), simplemente devuelve la respuesta que ya posee en su base de datos de Registros de Recursos (RR). Por lo tanto, el servidor autoritativo no necesita realizar el trabajo de recursión. De hecho, los servidores fundamentales de la jerarquía (como los servidores DNS raíz) no deberían permitir consultas recursivas para prevenir implicancias en la seguridad y el rendimiento
- Inciso d
  - **Servidor DNS primario (ns1) y secundario (ns2)**: para atender consultas de resolución de nombres (iterativas) para el dominio `redes2024.com.ar`. El servidor secundario `ns2` debe sincronizarse periódicamente con el primario (`ns1`) para obtener actualizaciónes de la zona (Transferencia de zona)
  - **Servidor de correo electrónico**
    - **SMTP**: para recepción de correo (MTA a MTA) de otros servidores de Internet
    - **SMTP/ESMTP**: para envío de correo (MUA a MTA/MSA), recibiendo mensajes de usuarios internos para su envío (rol del MSA normalmente incorporado en el MTA)
    - **POP3**: pera permitir a los usuarios descargar mensajes de su buzón
    - **IMAP**: para brindar una alternativa a POP3, permitiendo a los usuarios gestionar carpetas y manipular mensajes en el servidor
  - **Servidor WEB**: para proporcionar acceso al webmail a través de la URL https://webmail.redes2024.com.ar.
    - **HTTPs**: combinación de HTTP y SSL/TLS, para permitir la transferencia segura de documentos web para que los usuarios puedan gestionar sus correos de forma segura
- Inciso e  
  | Servidor                     | Protocolo de Transporte | Número de Puerto |
  |------------------------------|--------------------------|------------------|
  | **DNS Primario (ns1)**       | UDP/TCP                      | 53(DNS)               | 
  | **DNS Secundario (ns2)**     | UDP/TCP                      | 53(DNS)               | 
  | **Servidor de Correo (mail)**| TCP                      | 25(SMTP)               | 
  | **Servidor de Correo (mail)**| TCP                      | 110(POP3)              | 
  | **Servidor de Correo (mail)**| TCP                      | 143(IMAP)              | 
  | **Servidor WEB/Webmail (correo)** | TCP                 | 80(HTTP)               | 
  | **Servidor WEB/Webmail (correo)** | TCP                 | 443(HTTPS)             |
- Inciso f
  - El webmail, en este escenario, actúa como el Agente de Usuario de Correo (MUA, Mail User Agent) o cliente, que se comunica con el servidor de correo para dos funciones principales: 
    - Recibir o acceder al correo
      - **IMAP**: permite que webmail lea, gestione y manipule los correos en el servidor
      - **POP3**: permite que webmail recupere (descargue) los mensjaes del buzón del usuario
      - Para un servicio de webmail moderno, se utilizaría IMAP debido a su mayor flexibilidad para gestionar correos remotamente, carpetas y estados, lo cual es fundamental para una interfaz web
    - Enviar correos
      - **SMTP**: permite que **webmail** (cliente) inserte (push) el mensaje en el servidor de correo, en este caso **mail**, que funciona como un MSA/MTA
  - Dado que el acceso de los usuarios al webmail se realiza a través de HTTPS (https://webmail.redes2024.com.ar), es una práctica estándar que la conexión interna entre el servidor web (webmail) y el servidor de correo también utilice SSL/TLS para cifrar datos.
  - En resumen, la conexión se realizaría a través de una conexión TCP subyacente, utilizando los protocolos de la capa de aplicación IMAP/POP3 (para acceder al buzón) y SMTP (para enviar mensajes), y podrían estar asegurados mediante SSL/TLS
- Inciso g
  - La forma de asegurar que cualquier MTA reconozca como válidos los correos electrónicos provenientes del dominio `redes2024.com.ar` únicamente si llegan desde la dirección `203.0.113.111 ` es mediante la implementación de un registro **SPF (Sender Policy Framework)** en el DNS. **SPF** es un mecanismo diseñado para contrarrestar el correo basura (spam) al permitir a los propietarios de dominios especificar qué servidores de correo están autorizados para enviar mensajes en nombre de ese dominio. La configuración de SPF se realiza añadiendo un registro de recurso (RR) de tipo TXT (Textual) a la zona redes2024.com.ar en su servidor DNS primario (`ns1 - 203.0.113.65`). Los registros TXT almacenan información descriptiva para el dominio. El contenido de este registro TXT debe declarar explícitamente que la única dirección IP autorizada para enviar correo para este dominio es la de su servidor de correo, 203.0.113.111, por ejemplo, `v=spf1 ip4:203.0.113.111 -all` (donde -all indica que cualquier correo proveniente de una IP diferente debe ser rechazado). Cuando un servidor MTA receptor (el servidor de correo del destinatario) recibe un mensaje de correo electrónico que afirma ser de usuario@redes2024.com.ar, el servidor receptor (que actúa como servidor SMTP) realiza una consulta DNS buscando el registro SPF (TXT) asociado con el dominio redes2024.com.ar. Si la dirección IP de origen de la conexión SMTP coincide con la lista de direcciones autorizadas en el registro SPF, el MTA receptor considera el correo como legítimo, sino, el receptor determinará que el correo no es válido según su política de SPF.
  - Esto garantizaría la autenticidad de los correos enviados desde webmail. El servidor de webmail funciona como una MUA, cuando el usuario envía un correo a través de webmail, el MUA establece una conexión y utiliza el protocolo SMTP para enviar el mensaje a su propio servidor de correo, que en este caso es mail (203.0.113.111), y este es el que toma el menssaje y, actuando como cliente SMTP, se encarga dde transferír el correo al servidor del destinatario (MTA-MTA). Y dado que el MTA saliente es el servidor de correo 203.0.113.111, esta es la dirección IP de origen que cualquier MTA externo verá cuando reciba el mensaje. Como la validación SPF se hace sobre la IP del servidor que esta realizando la inyección de los mensajes en la red, los mensajes enviados desde webmail serán considerados válidos y auténticos por los servidores receptores. La IP interna de webmail, no es visible para la verificación SPF de los MTAs externos
- Inciso h
  - La característica **SMTP**, que indirectamente afecta el manejo de mensajes por parte de **IMAP** y **POP**, es su restricción de diseño a utilizar únicamente caracteres **ASCII de 7 bits** para la transferencia de mensajes. Esta característica es la que obliga a codificar datos binarios (como imágenes, ejecutables, o archivos multimedia) en un formato que cumpla con los estándares ASCII de 7 bits, utilizando métodos como **Base64**
- Inciso i
  - Si, Este acto se conoce como suplantación de identidad (spoofing) y es una indicación de una estafa o un ataque malicioso
  - La posibilidad de enviar un correo con una dirección de remitente falsa se debe al diseño de SMTP, que históricamente no exigía una autenticación estricta de la identidad del remitente durante la fase de transporte. El protocolo de correo define dos niveles de identificación para el remitente: el Envelope (envoltorio) y el Header (encabezado). El protocolo SMTP permite que un cliente especifique la dirección de correo electrónico del emisor (la persona que generó el mensaje) a través del comando MAIL FROM:. El encabezado del mensaje también debe contener una línea From:. Estas líneas de encabezado son diferentes de los comandos SMTP utilizados en la negociación del protocolo. Debido a que el sistema no realiza una verificación rigurosa de que la entidad que envía el mensaje es realmente la propietaria de la dirección que declara, es posible declarar una dirección falsa en la línea From: del encabezado que ve el destinatario. Esta capacidad de suplantación puede ser utilizada por atacantes para llevar a cabo un ataque con el fin de obtener información, distribuír software mailicoso, o realizar estafas. Para prevenir estas estafas aparecen mecanismos de seguridad robustos como SSL/TLS para la autenticación de los datos.
- Inciso j
  - Sí, esta manipulación del destinatario visible en el cliente de correo es posible debido a cómo está estructurado el protocolo SMTP y su distinción entre la información de transporte y la de visualización. La arquitectura del correo electrónico utiliza dos tipos principales de información de direccionamiento que se manejan por separado durante el proceso de envío
    - **Sobre (Envelope)**: Esta información es utilizada por los Agentes de Transporte de Correo (MTA) para determinar la ruta y el buzón final donde se debe depositar el mensaje. Los comandos SMTP que gestionan el sobre son `MAIL FROM:` (remitente de la ruta) y `RCPT TO:` (destinatario de la ruta)
    - **Encabezado (Header):** Esta es la meta-información del mensaje que el usuario final ve en su cliente de correo (Agente de Usuario de Correo o MUA). Incluye campos como `From:`, `Subject:`, y `To:`
  - El correo podría no llegar a la ruta que dice en el `To:` del **encabezado (Header)** ya que el enrutamiento y la entrega final son determinados por la dirección especificada en el `RCPT TO:` del **sobre (Envelope)**. Los Agentes de Transporte de Correo (MTA) que mueven el mensaje a través de Internet (MTA-MTA) y el Agente de Entrega de Correo (MDA) que lo deposita en el buzón, solo utilizan el **sobre (Envelope)** para guiar el datagrama. El campo `To:` dentro del **encabezado (Header)** es simplemente meta-información leída por el agente de usuario del receptor, y es ignorada por la infraestructura de enrutamiento del correo. Esta capacidad de suplantación puede violar principios fundamentales como la integridad de los mensajes o la autenticación del punto terminal ( receptor no puede verificar la legitimidad completa del mensaje o del remitente).
  - Un atacante puede usar la suplantación de la identidad del destinatario para confundir al receptor, hacerle creer que el mensaje era confidencial y fue mal dirigido, o iniciar un ataque de interposición (man-in-the-middle) o de reproducción. Tales vulnerabilidades son comunes en Internet y los atacantes las aprovechan para propagar software malicioso (malware) o realizar estafas.
- Inciso k
  - El MUA usará el protocolo SMTP para enviar un correo con remitente redes@info.unlp.edu.ar, conectándose con su propio servidor de correo local MSA/MTA para introducir el mensaje de correo en el servidor.
  - Para que el MUA envíe el correo, necesita la siguiente información:
    - **Identidad del servidor de correo local**: El MUA necesita conocer el nombre del servidor MSA/MTA local al que debe enviar el mensaje.
    - **Información del servidor de correo (Dirección IP)**: El MUA utiliza DNS para traducir el nombre del host del servidor de correo en su dirección IP correspondiente. Esto implica:
      - El MUA invoca a sui cliente DNS, pasándole el nombre de host del servidor
      - El cliente DNS envía una consulta a su servidor DNS local
      - El cliente DNS recibe una respuesta que incluye la dirección IP del servidor
      - El MUA debe autenticarse ante su servidor de correo (generalmente usuario y contraseña)
      - Durante la fase de negociación el MUA debe especificar la dirección de correo del emisor (en este caso redes@info.unlp.edu.ar) mediante el comando `MAIL FROM:` y la direción del destinatario mediante `RCPT TO:`
      - El MUA agrega los campos del encabezado (`Message-ID`, `To:`, `From:`, `Date:`, `Subject:`, etc.) y el cuerpo del mensaje
- Inciso l
  - Cuando un servidor de correo electrónico externo intenta entregar un mensaje destinado a un buzón en `redes2024.com.ar`, intentará establecer una conexión TCP con su servidor de correo. Si el servidor mail está en proceso de reinicio o está inoperativo, el cliente SMTP (el servidor emisor) determinará que el servidor de destino "no está operativo". Si el servidor de destino (su servidor mail) está fuera de servicio, el servidor de correo emisor retendrá el mensaje y lo conservará en una cola de mensajes salientes (Mail Queue). El mensaje no se deja en un servidor de correo intermedio, sino que es el servidor del emisor quien lo conserva. El servidor emisor intentará enviar el mensaje más tarde. Típicamente, estos reintentos de envío se realizan de forma periódica cada un cierto lapso de tiempo. Si, después de varios días, el servidor de destino sigue sin estar disponible y no se ha podido entregar el mensaje, el servidor emisor finalmente eliminará el mensaje de su cola y notificará al emisor (el usuario original) mediante un correo electrónico que la entrega falló
- Inciso m
  - Agregamos un registro MX correspondiente al servidor de mail de la nube. Al agregar este nuevo registro, tenemos que configurar su prioridad. Así, cuando un MTA externo desee enviar un correo, primero intentará establecer una conexión TCP con el servidor de prioridad mas alta (valor más bajo) y si este no está operativo, intentará con el siguiente más prioritario, y así sucesivamente, proporcionando tolerancia a fallos.

### Ejercicio 11
- Inciso a
  - i
    - La repsuesta a EHLO fue:
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
  - ii
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
  - iii
    - EL message-id del correo es `<20251010191206.005029@debian>`. Este valor lo asigna el cliente de correo (Swaks en este caso) automáticamente al enviar el mensaje. Normalmente se construye combinando la fecha/hora + un número de proceso + el nombre del host local (@debian). El Message-ID es único para cada mensaje y sirve para identificarlo de manera inequívoca en las comunicaciones de correo electrónico.
  - iv
    - El software utilizado como servidor de correo electrónico es Postfix, un MTA de código abierto, incluído en muchas distribuciones Linux. Postfix es conocido por su seguridad, rendimiento y facilidad de configuración. En este caso, Postfix está configurado para aceptar conexiones SMTP en el puerto 25 y manejar el envío y recepción de correos electrónicos.
  - v
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
- Inciso b
  - El contenido del mail no puede leerse en la captura porque la sesión SMTP se negoció y se protegió con TLS, por lo que el cuerpo y el adjunto viajan cifrados. Además, el adjunto está codificado en Base64 dentro del MIME, así que incluso si la sesión no estuviera cifrada el adjunto no sería legible como texto plano.
- Inciso c
  - El registro SPF del dominio info.unlp.edu.ar especifica una gran cantidad de servidores autorizados para enviar correos en nombre del dominio. Esto ocurre porque la Universidad Nacional de La Plata utiliza una infraestructura de correo distribuida, con múltiples servidores y servicios especializados. Cada dependencia o sistema (por ejemplo, bibliotecas, cátedras, plataformas Moodle, listas de correo, o servidores de extensión) puede generar correos legítimos utilizando el dominio info.unlp.edu.ar. Por ese motivo, todos esos servidores se incluyen en el registro SPF, de modo que los mensajes enviados desde cualquiera de ellos no sean marcados como spam y puedan ser verificados como legítimos por los destinatarios.
  - `v=spf1` indica la version del estándar SPF
  - `mx` autoriza a los servidores definidos conmo MX del dominio
  - `a:servidor.info.unlp.edu.ar` autoriza a ese servidor específico a enviar correos en nombre de `info.unlp.edu.ar`
- Inciso d
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
- Inciso a
  - Es posible, definiendo un puerto diferente para cada servicio, por ejemplo el mail, mail1 podría estar escuchando en el puerto 25(SMTP) y el web, www podría estar escuchando en el puerto 80(HTTP) o 443(HTTPS)
- Inciso b
  - El MUA consultará por el registro A de sus servidor de correo  
- Inciso c
  - Una vez que el mail fue recibido por smtp-5, este hará la consulta DNS recursiva para obtener el registro MX   y A de `example.com`, usando como servidor recursivo a `8.8.8.8`.
- Inciso d
  - Se requiere una consulta DNS adicional de tipo `A`(IPv4) o `AAAA`(IPv6) al servidor recursivo 8.8.8.8, para traducir el nombre del servidor de correo con mayor prioridad destino a su dirección IP antes de poder enviar el mail
- Inciso e
  - El servidor `ns1` no es recursivo, por lo tanto, no puede resolver la consulta de los MX de `example.com`
- Inciso f
  - F, cada protocolo se encarga de desencapsular su propia capa, hasta llegar a la aplicación destino
  - F, cada capa procesa la información de su propia capa
  - V
  - F, cada protcolo define su propia froma de comunicarse y no conocen la del resto
  - F, para eso estan los protocolos, para evitar esto  
- Inciso g
  - No es posible, ya que el servidor `ns1` no tiene habilitada la recursión
- Inciso h
  - Debera consultar por el registro A de su servidor de correo, es decir la IP de mail1
- Inciso i
  - Para el envío, el protocolo involucrados seran SMTP, en el puerto 25, y se utilizara TCP, ya que  queremos asegurarnos que la entrega del mensaje sea correcta. Para la recepción, el protocolo involucrado será POP3, en el puerto 110, o IMAP en el puerto 143, y también se utilizará TCP, ya que queremos asegurarnos que la descarga del mensaje sea correcta.