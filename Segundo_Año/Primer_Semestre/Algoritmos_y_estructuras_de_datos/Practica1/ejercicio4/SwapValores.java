package Practica1.ejercicio4;

public class SwapValores {

		public static void swap1 (int x, int y) {
			if (x < y) {
				int tmp = x ;
				x = y ;
				y = tmp;
			}
		}
		
		public static void swap2 (Integer x, Integer y) {
			if (x < y) {
				int tmp = x ;
				x = y ;
				y = tmp;
			}
		}
		
		public static void main(String[] args) {
			
			int a = 1, b = 2;
			Integer c = 3, d = 4;
			swap1(a,b);//a=2; b=1
			swap2(c,d);//c=4; d=3
			System.out.println("a=" + a + " b=" + b) ;//a=1; b=2
			System.out.println("c=" + c + " d=" + d) ;//c=3; d=4

	}

}
