package Practica1.ejercicio9;

public class TestBalanceo {

	private static boolean validar(String string) {
		 Stack<Character> stack = new Stack<Character>();
		 char[] vChar = string.toCharArray();
		 if(vChar.length%2 != 0) {
			 return false;
		 }
		 else {
		 for (int i = 0; i < vChar.length; i++) {
			 char car = vChar[i];
			 if (car == '(') {
				 stack.push(car);
			 }
			 else if(car=='[') {
				 stack.push(car);
			 }
			 else if(car=='{') {
				 stack.push(car);
			 }
			 else if (stack.isEmpty())
				 return false;
			 else
				 stack.pop();
		 }
		 return stack.isEmpty();
		 }
	}
	
	public static void main(String[] args) {
		System.out.println(validar("{[]()}"));
		System.out.println(validar("{[}([)}"));
	}
}
