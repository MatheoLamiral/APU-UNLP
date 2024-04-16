package Practica1.ejercicio5;

public class ArrayProces {
	private int[] vec;
	private int dimF;
	
	public ArrayProces(int dimF) {
		this.dimF= dimF;
		vec = new int[dimF];
		for(int i=0;i<dimF;i++) {//cargo vector que se dispone
			vec[i]=i;
		}
	}
	
	public String procesArrayWithReturn() {
		int min=999;
		int max=-999;
		int total=0;
		int i = 0;
		for(i=0;i<this.dimF;i++) {
			if(vec[i]<min) {
				min=vec[i];
			}
			if (vec[i]>max){
					max=vec[i];
				}
			total += vec[i];
			}
		float prom = total/this.dimF;
		String aux= ("Minimo: "+min+" Maximo: "+max+" Promedio: "+prom);
		return aux;	
	}
	
	public void procesArrayWithParam(Mensaje msj) {
		int min=999;
		int max=-999;
		int total=0;
		int i = 0;
		for(i=0;i<this.dimF;i++) {
			if(vec[i]<min) {
				min=vec[i];
			}
			if (vec[i]>max){
					max=vec[i];
				}
			total += vec[i];
			}
		float prom = total/this.dimF;
		msj.setMax(max);
		msj.setMin(min);
		msj.setProm(prom);
	}
	
	public void procesArrayWithoutReturnAndParam() {
		int min=999;
		int max=-999;
		int total=0;
		int i = 0;
		for(i=0;i<this.dimF;i++) {
			if(vec[i]<min) {
				min=vec[i];
			}
			if (vec[i]>max){
					max=vec[i];
				}
			total += vec[i];
			}
		float prom = total/this.dimF;
		System.out.println ("Minimo: "+min+" Maximo: "+max+" Promedio: "+prom);	
	}
}
