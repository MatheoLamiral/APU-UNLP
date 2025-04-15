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
- Bad smell: Reinventando la rueda
  - ```java
        for (Empleado empleado : personal) {
		    totalEdades = totalEdades + empleado.getEdad();
		    totalSalarios = totalSalarios + empleado.getSalario();
	    }
    ``` 
  - Aplico replace loop with pipeline
```Java
	public void imprimirValores() {
		int totalEdades = 0;

		double promedioEdades = this.personal.stream().mapToDouble(empleado->empleado.getEdad()).average().orElse(0);
		double totalSalarios = this.personal.stream().mapToInt(empleado->empleado.getSalario()).sum();

		promedioEdades = totalEdades / personal.size();
			
		String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);
		
		System.out.println(message);
	}
	```
- Bad smell: variables y cálculos redundantes
  - totalEdades y promedioEdades
  - elimino las variables y cálculos redundantes
```Java
public void imprimirValores() {
	double promedioEdades = this.personal.stream().mapToDouble(empleado->empleado.getEdad()).average().orElse(0);
	double totalSalarios = this.personal.stream().mapToInt(empleado->empleado.getSalario()).sum();
		
	String message = String.format("El promedio de las edades es %s y el total de salarios es %s", promedioEdades, totalSalarios);
	
	System.out.println(message);
}
```
- Bad smell: método largo
  - double promedioEdades = this.calcularPromedioEdad() y double totalSalarios = this.calcularTotalSalarios()
  - extract method
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
- Bad smell: Variables temporales redundantes
  - double promedioEdades = this.calcularPromedioEdad() y double totalSalarios = this.calcularTotalSalarios()
  - aplico replace temp with query
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