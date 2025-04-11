1. Listar nombre, descripción, fecha de inicio y fecha de fin de proyectos ya finalizados que no fueron terminados antes de la fecha de fin estimada.
   - <i>π<sub>nombre, descripción, fechaInicioP, fechaFinP</sub>(<i>σ<sub>(fechaFinP > fechaFinEstimada)</sub>(Proyecto)</i>)</i>
2. Listar DNI, nombre, apellido, teléfono, dirección y fecha de ingreso de empleados que no hayan sido responsables de proyectos.
   - Responsables ⇐ <i>π<sub>DNI, nombre, apellido, teléfono, dirección, fechaIngreso</sub>(<i>σ<sub>(Proyecto.DNIResponsable = Empleado.DNI)</sub>(Proyecto x Empleado))</i></i>
   - Empleado - Responsables
3. Listar DNI, nombre, apellido, teléfono y dirección de todos los empleados que trabajan en el proyecto con nombre ‘Proyecto X’. No es necesario informar responsable y líderes.
   - <i>π<sub>DNI, nombre, apellido, teléfono, dirección</sub>(<i>σ<sub>(Empleado.DNI = Empleado_Equipo.DNI)^(Empleado_Equipo.codEquipo = Equipo.codEquipo)^((Equipo.codEquipo = Proyecto.equipoBackend)v(Equipo.codEquipo = Proyecto.equipoFrontend))</sub>(Empleado x Empleado_Equipo x Equipo x Proyecto))</i></i>
4. Listar nombre de equipo y datos personales de líderes de equipos que no tengan empleados asignados y trabajen con tecnología ‘Java’.
   - TieneEmpleados ⇐ (Equipo |x| Equipo_Empleado)
   - NoTieneEmpleados ⇐ Equipo - TieneEmpleados
   - <i>π<sub>nombreE, DNI, nombre, apellido, teléfono, dirección, fechaIngreso</sub>(<i>σ<sub>(Equipo.descripcionTecnologias = 'Java')</sub>(NoTieneEmpleados x Empleado)</i>)</i>
5. Modificar nombre, apellido y dirección del empleado con DNI: 40568965 con los datos que desee.
   - δ nombre, apellido, dirección ⇐ pepe, gonzalez, calle 1 esquina 1 (<i>σ<sub>(DNI = 40568965)</sub>(Empleado)</i>)
6. Listar DNI, nombre, apellido, teléfono y dirección de empleados que son responsables de proyectos pero no han sido líderes de equipo.
   - Responsables ⇐ <i>π<sub>DNI, nombre, apellido, teléfono, dirección</sub>(<i>σ<sub>(Empleado.DNI = Proyecto.DNIResponsable)</sub>(Empleado x Proyecto)</i>)</i>
   - Lideres ⇐ <i>π<sub>DNI, nombre, apellido, teléfono, dirección</sub>(<i>σ<sub>(Empleado.DNI = Equipo.DNILider)</sub>(Empleado x Equipo)</i>)</i>
   - SoloResponsables ⇐ Responsables - Lideres
7. Listar nombre de equipo y descripción de tecnologías de equipos que hayan sido asignados como equipos frontend y backend.
   - <i>π<sub>nombreE, descripciónTecnologías</sub>(<i>σ<sub>(Equipo.codEquipo = Proyecto.equipoFrontend)^(Equipo.codEquipo = Proyecto.equipoBackend)</sub>(Equipo x Proyecto)</i>)</i>
8. Listar nombre, descripción, fecha de inicio, nombre y apellido de responsables de proyectos a finalizar durante 2019.
   - <i>π<sub>nombreE, descripción, fechaInicioP, nombre, apellido</sub>(<i>σ<sub>(fechaFinEstimada = '2019')^(DNI = DNIResponsable)</sub>(Proyecto x Empleado)</i>)</i>
9.  Listar nombre de equipo, descripción de tecnología y la información personal del líder, de equipos que no estén asignados a ningún proyecto aun
    - Asignados ⇐ <i>π<sub>codEquipo, nombreE, descripciónTecnologías, DNILider</sub>(<i>σ<sub>(Equipo.codEquipo = equipoBackend)v(Equipo.codEquipo = equipoFrontend)</sub>(Equipo x Proyecto)</i>)</i>
    - NoAsignados ⇐ Equipo - Asignados 
    - <i>π<sub>nombreE, descripciónTecnologías, nombre, apellido, teléfono, dirección, fechaIngreso</sub>(<i>σ<sub>(DNILider = DNI)</sub>(NoAsignados x Equipo)</i>)</i>