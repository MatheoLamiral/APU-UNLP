# Álgebra relacional

- perito_tipo_seguro ⇐ π<sub>#perito, #tipo_seguro</sub> (Peritaje |x| Siniestro |x| Poliza_Tipo_seguro)
- tipos_seguro ⇐ π<sub>#tipo_seguro</sub> (Tipo_seguro)
- π<sub>nombre, apellido</sub>((perito_tipo_seguro % tipos_seguro) |x| Perito)

### Explicación
- Primero obtengo todos los peritos relacionados con algún tipo.
- Después obtengo todos los tipos de seguro.
- Finalmente hago una división entre los peritos relacionados con algún tipo y todos los tipos de seguro, para obtener los peritos que están relacionados con todos los tipos de seguro. Luego hago un producto cartesiano con la tabla Perito para obtener sus nombres y apellidos que necesito proyectar.