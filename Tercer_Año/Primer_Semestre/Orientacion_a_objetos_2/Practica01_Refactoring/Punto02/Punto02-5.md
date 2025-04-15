```Java
    public class Supermercado {
        //...

        public void notificarPedido(long nroPedido, Cliente cliente) {
            String notificacion = MessageFormat.format(“Estimado cliente, se le informa que hemos recibido su pedido con número {0}, el cual será enviado a la dirección {1}”, new Object[] { nroPedido, cliente.getDireccionFormateada() });

            // lo imprimimos en pantalla, podría ser un mail, SMS, etc..
            System.out.println(notificacion);
        }
    }

    public class Cliente {
        //...

        public String getDireccionFormateada() {
            return this.direccion.getLocalidad() + “, ” +
                   this.direccion.getCalle() + “, ” +
                   this.direccion.getNumero() + “, ” +
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
# Bad smell: Atributos publicos
- ```Java
    public class Direccion {
        public String localidad;
        public String calle;
        public String numero;
        public String departamento;
    }
  ```
### Hago privados los atributos 
- ```Java
    public class Supermercado {
        //...

        public void notificarPedido(long nroPedido, Cliente cliente) {
            String notificacion = MessageFormat.format(“Estimado cliente, se le informa que hemos recibido su pedido con número {0}, el cual será enviado a la dirección {1}”, new Object[] { nroPedido, cliente.getDireccionFormateada() });

            // lo imprimimos en pantalla, podría ser un mail, SMS, etc..
            System.out.println(notificacion);
        }
    }

    public class Cliente {
        //...

        public String getDireccionFormateada() {
            return this.direccion.getLocalidad() + “, ” +
                   this.direccion.getCalle() + “, ” +
                   this.direccion.getNumero() + “, ” +
                   this.direccion.getDepartamento()
            ; 
        }
    }

    public class Direccion {
        Private String localidad;
        Private String calle;
        Private String numero;
        Private String departamento;
    }
  ```
# Bad smell: Feature envy
- ```Java

    public class Cliente {
        //...

        public String getDireccionFormateada() { //debería ser responsabilidad de la dirección formatearse
            return this.direccion.getLocalidad() + “, ” +
                   this.direccion.getCalle() + “, ” +
                   this.direccion.getNumero() + “, ” +
                   this.direccion.getDepartamento()
            ; 
        }
    }
  ```
### Aplico Move method
- ```Java
    public class Supermercado {
        //...

        public void notificarPedido(long nroPedido, Cliente cliente) {
            String notificacion = MessageFormat.format(“Estimado cliente, se le informa que hemos recibido su pedido con número {0}, el cual será enviado a la dirección {1}”, new Object[] { nroPedido, cliente.getDireccionFormateada() });

            // lo imprimimos en pantalla, podría ser un mail, SMS, etc..
            System.out.println(notificacion);
        }
    }

    public class Cliente {
        //...

        public String getDireccionFormateada() {
            return this.direccion.formatearDireccion();
            ; 
        }
    }

    public class Direccion {
        Private String localidad;
        Private String calle;
        Private String numero;
        Private String departamento;

        public String formatearDireccion() {
            return this.localidad + “, ” +
                   this.calle + “, ” +
                   this.numero + “, ” +
                   this.departamento
            ; 
        }
    }
  ``` 