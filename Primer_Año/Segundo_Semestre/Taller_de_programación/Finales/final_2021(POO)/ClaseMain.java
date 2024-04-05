
package Final_2021;

import PaqueteLectura.Lector;

public class ClaseMain {

    public static void main(String[] args) {
        // TODO code application logic here
        ConcursoDeBaile c = new ConcursoDeBaile(2);
        Participante part1;
        Participante part2;
        Pareja p;
        String estilo;
        for(int i=0;i<c.getDimF();i++){
            System.out.println("Ingrese los datos del primer participante(DNI,nombre y edad): ");
            part1 = new Participante(Lector.leerInt(),Lector.leerString(),Lector.leerInt());
            System.out.println("Ingrese los datos del segundo participante(DNI,nombre y edad): ");
            part2 = new Participante(Lector.leerInt(),Lector.leerString(),Lector.leerInt());
            System.out.println("Ingrese el estilo de la pareja: ");
            estilo = Lector.leerString();
            p = new Pareja(part1,part2,estilo);
            c.agregarPareja(p);
        }
        System.out.println("La pareja con Mayor diferencia de edad es: ");
        System.out.println(c.mayorDiferenciaEdad().mostrarNombres());
    }
    
}
