package Practica1.ejercicio3;

public class Test {

	public static void main(String[] args) {
		
		Estudiante [] vecE = new Estudiante [2];
		Profesor [] vecP = new Profesor [3];
		
		for (int i=0;i<2;i++) {
			vecE[i]=new Estudiante();
			vecE[i].setNom("nom est "+ i);
			vecE[i].setApe("ape est "+ i);
			vecE[i].setComision(i);
			vecE[i].setEmail("nomape"+ i +"@gmail.com");
			vecE[i].setDirec(i);
		}
		
		for(int j=0;j<3;j++) {
			vecP[j]=new Profesor();
			vecP[j].setNom("nom prof "+ j);
			vecP[j].setApe("ape prof "+ j);
			vecP[j].setCatedra("catedra "+ j);
			vecP[j].setFacultad("facultad prof"+ j);
			vecP[j].setEmail("nomape"+ j +"@gmail.com");
		}
		
		for(int i=0;i<2;i++) {
			System.out.println(vecE[i].tusDatos());
		}
		for(int j=0;j<3;j++) {
			System.out.println(vecP[j].tusDatos());
		}
	}

}
