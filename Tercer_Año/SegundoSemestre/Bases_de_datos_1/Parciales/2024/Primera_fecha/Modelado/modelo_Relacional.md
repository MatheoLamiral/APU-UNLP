# Modelo Relacional

**Poliza**(<ins>#poliza</ins>, fecha_inicio, fecha_fin, estado)  
**PolizaVehicular**(<ins>#poliza</ins>, #patente, deducible, cobertura_daños)  
**PolizaHogar**(<ins>#poliza</ins>, direccion, cobertura)  
**EsDueño**(<ins>#poliza</ins>, CUIL/CUIT)  
**Titular**(<ins>CUIL/CUIT</ins>, nombre, direccion, telefono)  
**Corresponde**(<ins>#patente</ins>, <ins>#siniestro</ins>)  
**Siniestro**(<ins>#siniestro</ins>, fecha_denuncia, descripcion)  
**Posee**(<ins>#siniestro</ins>, <ins>#especialidad</ins>, #reparacion)  
**Reparacion**(<ins>#reparacion</ins>, fecha_aprobacion, descripcion)  
**Asociado**(<ins>#reparacion</ins>, id_taller)  
**Taller**(<ins>id_taller</ins>, nombre)  
**Tiene**(<ins>id_taller</ins>, <ins>#especialidad</ins>)  
**Especialidad**(<ins>#especialidad</ins>, nombre)  
**Asigna**(<ins>#siniestro</ins>, #especialidad)
**Dispone**(<ins>id_garantia</ins>,id_taller, #especialidad)  
**Garantia**(<ins>id_garantia</ins>, detalles, fecha_inicio, fecha_fin)  

