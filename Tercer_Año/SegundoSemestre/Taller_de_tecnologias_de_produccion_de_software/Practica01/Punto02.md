# Punto 2

### Inciso a: ¿Cómo se categorizan los tipos de respuestas HTTP según su código de estado?

Los distintos tipos de respuesta HTTP se categorizan segun su estado en:
- 2XX de éxito/información
- 3XX de redirección
- 4XX de errores del cliente
- 5XX de errores del servidor.

### Inciso b: Complete la siguiente tabla indicando el significado de los códigos de estado

| Código | Descripción                 |
|--------|-----------------------------|
| 200    | OK                          |
| 201    | Created                     |
| 204    | No Content                  |
| 206    | Partial Content             |
| 301    | Moved Permanently           |
| 302    | Found                       |
| 304    | Not Modified                |
| 401    | Unauthorized                |
| 403    | Forbidden                   |
| 404    | Not Found                   |
| 409    | Conflict                    |
| 500    | Internal Server Error       |

### Inciso c: En el browser, escriba la URL: http://www.eldia.com, ¿cuáles de estos códigos aparecen?

Pude acceder al sitio sin problemas desde el navegador, lo que significa que el servidor responde correctamente a mi solicitud. Esto sugiere que el código HTTP que devuelve es un 200 OK, ya que ese es el mensaje estándar cuando una página web carga exitosamente. Sin embargo, si entramos a la consola de desarrollador, en la pestaña de red, podemos observar que ademas de los códigos 200 y 204, hay un código 502, Bad Gateway (Puerta de enlace incorrecta), que se da cuando un servidor que actúa como puente o proxy recibe una respuesta inválida o ningún dato de otro servidor al que intentaba conectarse para entregar la página 

### Inciso d: Verifique como se redirecciona http://www.guarani-informatica.unlp.edu.ar a una conexión segura. ¿En qué campo de la respuesta se indica a donde redireccionar?

Se redirecciona de la siguiente manera:

- 302 Found 
  - El navegador pidió http://www.guarani-informatica.unlp.edu.ar/. El servidor respondió con un 302, es decir, una redirección temporal. En los Response Headers de esa entrada el campo Location apunta a la siguiente URL https://www.guarani-informatica.unlp.edu.ar/. 
- 301 Moved Permanently 
  - Luego, al seguir esa redirección, el servidor contestó con un 301, indicando que la URL debe usarse siempre en HTTPS. De nuevo, el campo Location en los encabezados es el que le indica al navegador hacia dónde ir. 
- 200 OK 
  - Finalmente, la petición llega a https://autogestion.guarani.unlp.edu.ar/. El servidor responde con 200 OK, lo que significa que la página cargó exitosamente.

El campo de respuesta donde se indica la redirección es `Location`, que aparece en los encabezados de respuesta HTTP (Response Headers). Este campo especifica la URL a la que el cliente debe redirigirse.