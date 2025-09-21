```Java
public void imprimirValores() {
	int totalEdades = 0;
	double promedioEdades = 0;
	double totalSalarios = 0;
	
	for (Empleado empleado : personal) {
		totalEdades = totalEdades + empleado.getEdad();
		totalSalarios = totalSalarios + empleado.getSalario();
	}
	promedioEdades = totalEdades / personal.size();
		
	String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);
	
	System.out.println(message);
}
```
### Bad smell: Reinventando la rueda en el loop for  
1. Aplico replace loop with pipeline
```Java
public void imprimirValores() {
	int totalEdades = 0;
	double promedioEdades = 0;
	double totalSalarios = 0;

	double totalEdades = this.personal.stream()
									  .mapToDouble(empleado->empleado.getEdad())
									  .sum();
	double totalSalarios = this.personal.stream()
										.mapToInt(empleado->empleado.getSalario())
										.sum();

	promedioEdades = totalEdades / personal.size();
		
	String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);
	
	System.out.println(message);
}
```
### Bad smell: usa variable temporal promedioEdades y está reinventando la rueda, esta calculando el total de las edades para luego dividirlo por la cantidad de empleados, cuando podría calcular directamente el promedio de las edades
1. Aplico remove field para eliminar la variable `totalEdades`
2. Reemplazo el cálculo de `promedioEdades` por un stream que calcula directamente el promedio 
```Java
public void imprimirValores() {
	double promedioEdades = this.personal.stream()
										 .mapToDouble(empleado->empleado.getEdad())
										 .average()
										 .orElse(0);
	double totalSalarios = this.personal.stream()
										 .mapToInt(empleado->empleado.getSalario())
										 .sum();

	String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);
	
	System.out.println(message);
}
```
### Bad smell: Long method
1. Aplico extract method para el calculo de promedio de edades
   1. creo el método ` double calcularPromedioEdad()`
   2. copio el cálculo de promedio de edades dentro del nuevo método
   3. Reemplazo el cálculo de promedio de edades en `imprimirValores` por una llamada al nuevo método
2.  Aplico extract method para el cálculo de total de salarios
   1. creo el método `double calcularTotalSalarios()`
   2. copio el cálculo de total de salarios dentro del nuevo método
   3. Reemplazo el cálculo de total de salarios en `imprimirValores` por una llamada al nuevo método
```Java
public void imprimirValores() {	
    double promedioEdades = this.calcularPromedioEdad();
	double totalSalarios = this.calcularTotalSalarios();
	String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);

	System.out.println(message);
}

protected double calcularPromedioEdad (){
    return this.personal.stream().mapToDouble(empleado->empleado.getEdad()).average().orElse(0);
}

protected int calcularTotalSalarios(){
    return this.personal.stream().mapToInt(empleado->empleado.getSalario()).sum();
}
```
### Bad smell: Temporary field en las variables `promedioEdades` y `totalSalarios`
1. Aplico remove field para eliminar las variables `promedioEdades` y `totalSalarios`
2. Aplico replace temp with query para reemplazar las variables por llamadas a los métodos `calcularPromedioEdad` y `calcularTotalSalarios` en el seteo de message
```Java
public void imprimirValores() {		
	String message = String.format("El promedio de las edades es %s y el total de salarios es %s", this.calcularPromedioEdad(), this.calcularTotalSalarios());

	System.out.println(message);
}

protected double calcularPromedioEdad (){
    return this.personal.stream().mapToDouble(empleado->empleado.getEdad()).average().orElse(0);
}

protected int calcularTotalSalarios(){
    return this.personal.stream().mapToInt(empleado->empleado.getSalario()).sum();
}
```
### Bad smell: Temporary field en el método imprimirValores
1. Aplico replace temp with query
   1. Elimino la variable `message`
   2. muevo el código de construcción del string directamente al `System.out.println` 
```Java
public void imprimirValores() {
	System.out.println(String.format("El promedio de las edades es %s y el total de salarios es %s", this.calcularPromedioEdad(), this.calcularTotalSalarios()));
}

protected double calcularPromedioEdad (){
    return this.personal.stream().mapToDouble(empleado->empleado.getEdad()).average().orElse(0);
}

protected int calcularTotalSalarios(){
    return this.personal.stream().mapToInt(empleado->empleado.getSalario()).sum();
}
```