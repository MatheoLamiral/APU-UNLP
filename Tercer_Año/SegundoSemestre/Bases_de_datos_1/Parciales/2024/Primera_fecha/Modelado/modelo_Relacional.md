# Modelo Relacional

**Poliza**(<u>#poliza</u>, fecha_inicio, fecha_fin, estado)  
**PolizaVehicular**(<u>#poliza</u>, #patente, deducible, cobertura_daños)  
**PolizaHogar**(<u>#poliza</u>, direccion, cobertura)  
**EsDueño**(<u>#poliza</u>, CUIL/CUIT)  
**Titular**(<u>CUIL/CUIT</u>, nombre, direccion, telefono)  
**Corresponde**(<u>#patente</u>, <u>#siniestro</u>)  
**Siniestro**(<u>#siniestro</u>, fecha_denuncia, descripcion)  
**Posee**(<u>#siniestro</u>, <u>#especialidad</u>, #reparacion)  
**Reparacion**(<u>#reparacion</u>, fecha_aprobacion, descripcion)  
**Asociado**(<u>#reparacion</u>, id_taller)  
**Taller**(<u>id_taller</u>, nombre)  
**Tiene**(<u>id_taller</u>, <u>#especialidad</u>)  
**Especialidad**(<u>#especialidad</u>, nombre)  
**Asigna**(<u>#siniestro</u>, #especialidad)
**Dispone**(<u>id_garantia</u>,id_taller, #especialidad)  
**Garantia**(<u>id_garantia</u>, detalles, fecha_inicio, fecha_fin)  

