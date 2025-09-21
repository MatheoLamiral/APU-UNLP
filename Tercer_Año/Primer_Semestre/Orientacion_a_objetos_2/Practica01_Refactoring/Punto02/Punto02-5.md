```Java
    public class Supermercado {
        //...

        public void notificarPedido(long nroPedido, Cliente cliente) {
            String notificacion = MessageFormat.format("Estimado cliente, se le informa que hemos recibido su pedido con número {0}, el cual será enviado a la dirección {1}", new Object[] { nroPedido, cliente.getDireccionFormateada() });

            // lo imprimimos en pantalla, podría ser un mail, SMS, etc..
            System.out.println(notificacion);
        }
    }

    public class Cliente {
        //...

        public String getDireccionFormateada() {
            return this.direccion.getLocalidad() + ", " +
                   this.direccion.getCalle() + ", " +
                   this.direccion.getNumero() + "," +
                   this.direccion.getDepartamento()
            ; 
        }
    }

    public class Direccion {
        public String localidad;
        public String calle;
        public String numero;
        public String departamento;
    }

```
### Bad smell: Atributos publicos en la clase `Direccion`
1. Hago privados los atributos 
```Java
    public class Direccion {
        private String localidad;
        private String calle;
        private  String numero;
        private String departamento;
    }
  ```
### Bad smell: Feature envy en el método `getDireccionFormateada()` de la clase `Cliente`
1. Aplico **Move method**
   1. Creo el método `formatearDireccion()` en la clase `Direccion`
   2. Copio el comportamiento de `getDireccionFormateada()` a `formatearDireccion()`
   3. Reemplazo el código de `getDireccionFormateada()` por un llamado a `this.direccion.formatearDireccion()`
```Java
    public class Cliente {
        //...

        public String getDireccionFormateada() {
            return this.direccion.formatearDireccion();
        }
    }

    public class Direccion {
        private String localidad;
        private String calle;
        private String numero;
        private String departamento;

        public String formatearDireccion() {
            return this.localidad + ", " +
                    this.calle + ", " +
                    this.numero + "," +
                    this.departamento;
        }
    }
  ``` 
### Bad smell: Long method en el método `notificarPedido()` de la clase `Supermercado`
1. Aplico **Extract method**
   1. Creo el método `formatearNotificacion(long nroPedido, Cliente cliente)`
   2. Copio el comportamiento de `notificarPedido()` a `formatearNotificacion()`
   3. Reemplazo el código de `notificarPedido()` por un llamado a `this.formatearNotificacion(nroPedido, cliente)`
```Java
    public class Supermercado {
        //...

        public void notificarPedido(long nroPedido, Cliente cliente) {
            String notificacion = this.formatearNotificacion(nroPedido, cliente);
            System.out.println(notificacion);
        }

        private String formatearNotificacion(long nroPedido, Cliente cliente) {
            return MessageFormat.format("Estimado cliente, se le informa que hemos recibido su pedido con número {0}, el cual será enviado a la dirección {1}", new Object[] { nroPedido, cliente.getDireccionFormateada() });
        }
    }
```
### Bad smell: Temporary field en el método `notificarPedido()` de la clase `Supermercado`
1. Elimino la variable `notificacion` realizando el llamado a `formatearNotificacion()` directamente en el `System.out.println()`
```Java
    public class Supermercado {
        //...

        public void notificarPedido(long nroPedido, Cliente cliente) {
            System.out.println(this.formatearNotificacion(nroPedido, cliente));
        }

        private String formatearNotificacion(long nroPedido, Cliente cliente) {
            return MessageFormat.format("Estimado cliente, se le informa que hemos recibido su pedido con número {0}, el cual será enviado a la dirección {1}", new Object[] { nroPedido, cliente.getDireccionFormateada() });
        }
    }
```
### Código final
```Java
    public class Supermercado {
        //...

        public void notificarPedido(long nroPedido, Cliente cliente) {
            System.out.println(this.formatearNotificacion(nroPedido, cliente));
        }

        private String formatearNotificacion(long nroPedido, Cliente cliente) {
            return MessageFormat.format("Estimado cliente, se le informa que hemos recibido su pedido con número {0}, el cual será enviado a la dirección {1}", new Object[] { nroPedido, cliente.getDireccionFormateada() });
        }
    }

    public class Cliente {
        private Direccion direccion;

        public String getDireccionFormateada() {
            return this.direccion.formatearDireccion();
        }
    }

    public class Direccion {
        private String localidad;
        private String calle;
        private String numero;
        private String departamento;

        public String formatearDireccion() {
            return this.localidad + ", " +
                   this.calle + ", " +
                   this.numero + "," +
                   this.departamento;
        }
    }
```