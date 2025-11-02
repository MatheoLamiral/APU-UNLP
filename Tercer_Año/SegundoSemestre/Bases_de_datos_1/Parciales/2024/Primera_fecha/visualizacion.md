# Visualización de datos

- Para la primera visualización, utilizaría el **gráfico de barras**, ya que nos permite comparar los valores numéricos entre categorías (categorías de productos en este caso), observar las variaciones a lo largo del tiempo y ver cual es la categoría más vendida.
  - Las tablas relevantes para representar el análisis son:
    - **Item_Venta** (<ins>#id_venta</ins>, id_producto, cant)
    - **Producto** (<ins>#id_producto</ins>, nombre_producto, desc_producto ,precio_unit, categoria)
- Para la segunda visualización, utilizaría el **gráfico de líneas**, ya que es ideal para mostrar los cambios en los datos en un período de tiempo, facilitando la identificación de tendencias. Se podrá ver el aumento, disminución o estabilidad de las ventas totales a lo largo de los meses. En el eje X se colocarán los meses y en el eje Y el monto de las ventas registradas en la sucursal 10 cada mes.
  - Las tablas relevantes para representar el análisis son:
    - **Venta** (<ins>#id_venta</ins>, fecha_venta, total_venta)
    - **Sucursal** (<ins>#id_sucursal</ins>, ubicación, cant_empleados)