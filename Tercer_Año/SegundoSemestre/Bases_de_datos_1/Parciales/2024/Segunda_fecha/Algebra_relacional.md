# Álgebra relacional

- Mayoristas_vendieron_categorias ⇐ π<sub>#mayorista, #categoria</sub>(Venta |X| Producto)
- Categorias_ofrecidas_mayoristas ⇐ π<sub>#categoria</sub>(PrecioMayorista |X| Producto)
- Mayoristas_vendieron_categorias % Categorias_ofrecidas_mayoristas

### Explicación

- Primero obtengo las categorías que vendió cada mayorista
- Después obtengo las categorías ofrecidas por cada mayorista
- Finalmente, me quedo con los mayoristas que vendieron productos de todas las categorías ofrecidas. No hace falta la proyección ya que con la división se cancela el #categoria dejando solo el #mayorista