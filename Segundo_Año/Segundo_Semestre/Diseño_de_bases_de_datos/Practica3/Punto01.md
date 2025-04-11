1. Listar nombre, apellido, DNI, teléfono y dirección de clientes con DNI superior a 22222222.  
   - <i>π<sub>dni, nombre, apellido, teléfono, dirección</sub></i>(<i>σ<sub>(dni > 22222222)</sub></i>(cliente))
2. Listar nombre, apellido, DNI, teléfono y dirección de clientes con DNI superior a 22222222 y que tengan
facturas cuyo total no supere los $100000.  
   - <i>π<sub>dni, nombre, apellido, teléfono, dirección</sub></i>(<i>σ<sub>(Cliente.dni > 22222222)^(Factura.total <= 100000)</sub></i>(cliente |x| factura))
1. Listar nombre, apellido, DNI, teléfono y dirección de clientes que realizaron compras durante 2020.
   - <i>π<sub>nombre, apellido, DNI, teléfono, dirección</sub></i>(<i>σ<sub>(Factura.fecha >= '01/01/2020')^(Factura.fecha <= '31/12/2020')</sub></i>(Cliente |x| Factura))
2. Listar nombre, apellido, DNI, teléfono y dirección de clientes que no realizaron compras durante 2020.
   - <i>π<sub>nombre, apellido, DNI, teléfono, dirección</sub></i>(<i>σ<sub>(Factura.fecha < '01/01/2020')v(Factura.fecha > '31/12/2020')</sub></i>(Cliente |x| Factura))
3. Listar nombre, apellido, DNI, teléfono y dirección de clientes que solo tengan compras durante 2020.
   - Durante2020 ⇐ <i>π<sub>nombre, apellido, DNI, teléfono, dirección</sub></i>(<i>σ<sub>(Factura.fecha >= '01/01/2020')^(Factura.fecha <= '31/12/2020')</sub></i>(Cliente |x| Factura))
   - Fuera2020 ⇐ <i>π<sub>nombre, apellido, DNI, teléfono, dirección</sub></i>(<i>σ<sub>(Factura.fecha < '01/01/2020')v(Factura.fecha > '31/12/2020')</sub></i>(Cliente |x| Factura))
   - Solo2020 ⇐ Durante2020 - Fuera2020
4. Listar nombre, descripción, precio y stock de productos no vendidos.
   - ProductosVendidos ⇐ <i>π<sub>idProducto, nombre, descripción, precio, stock</sub></i>(Producto |x| Detalle)
   - ProductosNoVendidos ⇐ <i>π<sub>nombre, descripción, precio y stock</sub></i>(Productos - ProductosVendidos)
5. Listar  nombre, apellido, DNI, teléfono y dirección de clientes que no compraron el producto con nombre
‘ProductoX’ durante 2020.
   - Compraron ⇐ (<i>σ<sub>(Producto.nombreP = 'ProductoX')^((Factura.fecha >= '01/01/2020')^(Factura.fecha <= '31/12/2020'))</sub></i>(Cliente |x| Factura |x| Detalle |x| Producto))
   - noCompraron2020 ⇐ <i>π<sub>nombre, apellido, DNI, teléfono, dirección</sub></i>(Compraron - (Cliente |x| Factura |x| Detalle |x| Producto))
1. Listar  nombre, apellido, DNI, teléfono y dirección de clientes que compraron el producto con nombre
‘Producto A’’ y no compraron el producto con nombre ‘Producto B’.
   - CompraronA ⇐ (<i>σ<sub>(Producto.nombreP = 'Producto A')</sub></i>(Cliente |x| Factura |x| Detalle |x| Producto))
   - CompraronB ⇐ (<i>σ<sub>(Producto.nombreP = 'Producto B')</sub></i>(Cliente |x| Factura |x| Detalle |x| Producto))
   - CompraronAYNoB ⇐ <i>π<sub>nombre, apellido, DNI, teléfono, dirección</sub></i>(CompraronA - CompraronB)
1. Listar nroTicket, total, fecha, hora y DNI del cliente, de aquellas  facturas donde se haya comprado el
producto ‘Producto C’.
   - <i>π<sub>Factura.nroTicket, Factura.total, Factura.fecha, Factura.hora, Cliente.DNI </sub></i>(<i>σ<sub>(Producto.nombre = 'Producto C')</sub></i>(Cliente |x| Factura |x| Detalle |x| Producto))
1.  Agregar un producto con id de producto 1000, nombre “Producto Z”, descripción “mi producto”, precio
$10000 y stock 1000. Se supone que el idProducto 1000 no existe
   - Producto ⇐ Producto U {(1000, 'Producto Z', 'mi producto', 10000, 1000)}