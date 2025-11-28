# Ejercicio 10

**DISPOSITIVOS** (marca_id, descripMarca, modelo_id, descripModelo, equipo_tipo_id, descripEquipoTipo, nombreEmpresa, cuit, direcciónEmpresa, usuario_id, apyn, direcciónUsuario, cuil, plan_id, descripPlan, importe, equipo_id, imei, fec_alta, fec_baja, observaciones, línea_id, fec_alta_linea, fec_baja_linea)

## Dependencias funcionales

DF1: `equipo_id -> equipo_tipo_id,modelo_id, imei, fec_alta, fec_baja, observaciones`  
DF2: `imei -> equipo_id, equipo_tipo_id, modelo_id, fec_alta, fec_baja, observaciones`  
DF3: `marca_id -> descripMarca`  
DF4: `modelo_id -> descripModelo, marca_id`  
DF5: `plan_id -> cuit, descripPlan, importe`  
DF6: `equipo_tipo_id -> descripEquipoTipo`  
DF7: `cuit -> nombreEmpresa, direcciónEmpresa`  
DF8: `usuario_id -> apyn, direcciónUsuario, cuil`  
DF9: `cuil -> usuario_id, apyn, direcciónUsuario`  
DF10: `línea_id -> plan_id, fec_alta_linea, fec_baja_linea, equipo_id, usuario_id`  
DF11: `línea_id -> plan_id, fec_alta_linea, fec_baja_linea, equipo_id, cuil`  
DF12: `línea_id -> plan_id, fec_alta_linea, fec_baja_linea, imei, usuario_id`  
DF13: `línea_id -> plan_id, fec_alta_linea, fec_baja_linea, imei, cuil`  

**CC: {línea_id}**

## Análisis de BCNF

DISPOSITIVOS no está en BCNF ya que existe al menos una DF no trivial, por ej DF3, cuyo determinante (marca_id) no es superclave del esquema

- Por lo tanto, particiono por DF3:
  - **D1)** **(marca_id**, descripMarca)
  - **D2)** (**línea_id**, marca_id, modelo_id, equipo_tipo_id, descripModelo, descripEquipoTipo, nombreEmpresa, cuit, direcciónEmpresa, usuario_id, apyn, direcciónUsuario, cuil, plan_id, descripPlan, importe, equipo_id, imei, fec_alta, fec_baja, observaciones, fec_alta_linea, fec_baja_linea)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que D1 ∩ D2 es {marca_id}, que es clave en D1. 
  - No se pierden DFs dado que por validacion simple, DF3 vale en D1 y DF1, DF2, DF4, DF5, DF6, DF7, DF8, DF9, DF10, DF11, DF12, DF13 valen en D2

- **D1** está en BCNF ya que solo vale la DF3 donde su determinante {marca_id} es superclave del esquema.
- **D2** no está en BCNF porque existe al menos una DF no trivial, por ej DF4, cuyo determinante (modelo_id) no es superclave del esquema

- Por lo tanto, particiono por DF4:
  - **D3)** (**modelo_id**, descripModelo, marca_id)
  - **D4)** (**línea_id**, modelo_id, equipo_tipo_id, descripEquipoTipo, nombreEmpresa, cuit, direcciónEmpresa, usuario_id, apyn, direcciónUsuario, cuil, plan_id, descripPlan, importe, equipo_id, imei, fec_alta, fec_baja, observaciones, fec_alta_linea, fec_baja_linea)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que D3 ∩ D4 es {modelo_id}, que es clave en D3. 
  - No se pierden DFs dado que por validacion simple, DF4 vale en D3 y DF1, DF2, DF5, DF6, DF7, DF8, DF9, DF10, DF11, DF12, DF13 valen en D4

- **D3** está en BCNF ya que solo vale la DF4 donde su determinante {modelo_id} es superclave del esquema.
- **D4** no está en BCNF porque existe al menos una DF no trivial, por ej DF6, cuyo determinante (equipo_tipo_id) no es superclave del esquema

- Por lo tanto, particiono por DF6:
  - **D5)** (**equipo_tipo_id**, descripEquipoTipo)
  - **D6)** (**línea_id**, modelo_id, equipo_tipo_id, nombreEmpresa, cuit, direcciónEmpresa, usuario_id, apyn, direcciónUsuario, cuil, plan_id, descripPlan, importe, equipo_id, imei, fec_alta, fec_baja, observaciones, fec_alta_linea, fec_baja_linea)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que D5 ∩ D6 es {equipo_tipo_id}, que es clave en D5. 
  - No se pierden DFs dado que por validacion simple, DF6 vale en D5 y DF1, DF2, DF5, DF7, DF8, DF9, DF10, DF11, DF12, DF13 valen en D6
  
- **D5** está en BCNF ya que solo vale la DF6 donde su determinante {equipo_tipo_id} es superclave del esquema.
- **D6** no está en BCNF porque existe al menos una DF no trivial, por ej DF7, cuyo determinante (cuit) no es superclave del esquema

- Por lo tanto, particiono por DF7:
  - **D7)** (**cuit**, nombreEmpresa, direcciónEmpresa)
  - **D8)** (**línea_id**, modelo_id, equipo_tipo_id, cuit, usuario_id, apyn, direcciónUsuario, cuil, plan_id, descripPlan, importe, equipo_id, imei, fec_alta, fec_baja, observaciones, fec_alta_linea, fec_baja_linea)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que D7 ∩ D8 es {cuit}, que es clave en D7. 
  - No se pierden DFs dado que por validacion simple, DF7 vale en D7 y DF1, DF2, DF5, DF8, DF9, DF10, DF11, DF12, DF13 valen en D8

- **D7** está en BCNF ya que solo vale la DF7 donde su determinante {cuit} es superclave del esquema.
- **D8** no está en BCNF porque existe al menos una DF no trivial, por ej DF5, cuyo determinante (plan_id) no es superclave del esquema

- Por lo tanto, particiono por DF5:
  - **D9)** (**plan_id**, cuit, descripPlan, importe)
  - **D10)** (**línea_id**, modelo_id, equipo_tipo_id, usuario_id, apyn, direcciónUsuario, cuil, plan_id, equipo_id, imei, fec_alta, fec_baja, observaciones, fec_alta_linea, fec_baja_linea)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que D9 ∩ D10 es {plan_id}, que es clave en D9. 
  - No se pierden DFs dado que por validacion simple, DF5 vale en D9 y DF1, DF2, DF8, DF9, DF10, DF11, DF12, DF13 valen en D10

- **D9** está en BCNF ya que solo vale la DF5 donde su determinante {plan_id} es superclave del esquema.
- **D10** no está en BCNF porque existe al menos una DF no trivial, por ej DF1, cuyo determinante (equipo_id) no es superclave del esquema

- Por lo tanto, particiono por DF1:
  - **D11)** (**equipo_id**, equipo_tipo_id, modelo_id, imei, fec_alta, fec_baja, observaciones)
  - **D12)** (**línea_id**, usuario_id, apyn, direcciónUsuario, cuil, plan_id, equipo_id, fec_alta_linea, fec_baja_linea)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que D11 ∩ D12 es {equipo_id}, que es clave en D11. 
  - No se pierden DFs dado que por validacion simple, DF1 y DF2 valen en D11, ya que son equivalentes y DF8, DF9, DF10, DF11, DF12, DF13 valen en D12. Notar que DF12 y DF13 no se pierden ya que `equipo_id` existe en D12 y debido a la equivalencia entre DF1 y DF2, `imei` puede ser determinado a partir de `equipo_id`, por lo que DF12 y DF13 siguen valiendo en D12.

- **D11** está en BCNF ya que solo valen la DF1 y DF2 cuyos determinantes {equipo_id} y {imei} son superclave del esquema.
- **D12** no está en BCNF porque existe al menos una DF no trivial, por ej DF8, cuyo determinante (usuario_id) no es superclave del esquema

- Por lo tanto, particiono por DF8:
  - **D13)** (**usuario_id**, apyn, direcciónUsuario, cuil)
  - **D14)** (**línea_id**, usuario_id, plan_id, equipo_id, fec_alta_linea, fec_baja_linea)
- Análisis de pérdida de información y DFs:
  - No se pierde información ya que D13 ∩ D14 es {usuario_id}, que es clave en D13. 
  - No se pierden DFs dado que por validacion simple, DF8 y DF9 valen en D13, ya que son equivalentes, y DF10, DF11, DF12, DF13 valen en D14. Notar que DF11 y DF13 no se pierden ya que `usuario_id` existe en D14 y debido a la equivalencia entre DF8 y DF9, `cuil` puede ser determinado a partir de `usuario_id`, por lo que DF11 y DF13 siguen valiendo en D14.

- **D13** está en BCNF ya que solo valen la DF8 y DF9 cuyos determinantes {usuario_id} y {cuil} son superclave del esquema.
- **D14** está en BCNF ya que solo valen por las equivalencias antes explicadas la DF10, DF11, DF12 y DF13 cuyos determinantes {línea_id} son superclave del esquema.

### Esquemas en BCNF

- **D1)** (**marca_id**, descripMarca)
- **D3)** (**modelo_id**, descripModelo, marca_id)
- **D5)** (**equipo_tipo_id**, descripEquipoTipo)
- **D7)** (**cuit**, nombreEmpresa, direcciónEmpresa)
- **D9)** (**plan_id**, cuit, descripPlan, importe)
- **D11)** (**equipo_id**, equipo_tipo_id, modelo_id, imei, fec_alta, fec_baja, observaciones)
- **D13)** (**usuario_id**, apyn, direcciónUsuario, cuil)
- **D14)** (**línea_id**, usuario_id, plan_id, equipo_id, fec_alta_linea, fec_baja_linea)

**Clave Primaria: {línea_id}**

## Dependencias multivaluadas a partir de D14

No existen DMs en el esquema D14

## Análisis de 4FN

Como no existen dependencias multivaluadas en D14, el esquema se encuentra en 4FN.

### Esquemas en 4FN

- **D1)** (**marca_id**, descripMarca)
- **D3)** (**modelo_id**, descripModelo, marca_id)
- **D5)** (**equipo_tipo_id**, descripEquipoTipo)
- **D7)** (**cuit**, nombreEmpresa, direcciónEmpresa)
- **D9)** (**plan_id**, cuit, descripPlan, importe)
- **D11)** (**equipo_id**, equipo_tipo_id, modelo_id, imei, fec_alta, fec_baja, observaciones)
- **D13)** (**usuario_id**, apyn, direcciónUsuario, cuil)
- **D14)** (**línea_id**, usuario_id, plan_id, equipo_id, fec_alta_linea, fec_baja_linea)

**Clave Primaria: {línea_id}**

### Esquemas en 4FN y que no son proyecciones de otros

No hay esquemas que sean proyecciones de otros, por lo tanto todos los esquemas listados en 4FN cumplen esta condición.