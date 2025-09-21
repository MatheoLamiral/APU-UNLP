# Ejercicio 11

Un espacio de nombres es un mecanismo que permite organizar y agrupar clases, funciones o variables para evitar conflictos de nombres y facilitar la modularidad del código.

En Java el manejo de espacios de nombres se realiza mediante paquetes (`package`).
- Se declara al inicio de un archivo con `package nombre_del_paquete;`.
- Para usar clases de otros paquetes, se utiliza `import`.
Ejemplo:
```Java	
    package com.ejemplo.util;
    import java.util.List;
```
- Características en otros lenguajes:
  - PHP:
    - Usa la palabra clave `namespace` para definir espacios de nombres y evitar colisiones entre clases o funciones con el mismo nombre.
    - `namespace MiProyecto\Util`;
  - Python:
    - Los espacios de nombres se gestionan mediante módulos y paquetes. Cada archivo .py es un módulo y las carpetas con `__init__.py` son paquetes.
    - Se usan las instrucciones `import` y `from ... import ...` para acceder a ellos.
- Características importantes:
  - Permiten organizar el código y evitar conflictos de nombres.
  - Facilitan la reutilización y el mantenimiento.
  - Mejoran la legibilidad y estructura de los proyectos grandes.