package Practica1.ejercicio5;

public class ClaseMain {

	public static void main(String[] args) {
		
		//INCISO A
		ArrayProces vec = new ArrayProces(5);
		System.out.println(vec.procesArrayWithReturn());
		
		//INCISO B
		Mensaje msj= new Mensaje();
		vec.procesArrayWithParam(msj);
		System.out.println(msj);
		
		//INCISO C
		vec.procesArrayWithoutReturnAndParam();
	}

}
