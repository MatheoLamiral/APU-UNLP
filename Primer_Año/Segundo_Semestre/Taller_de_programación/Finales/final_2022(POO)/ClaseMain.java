
package final_2022;

import PaqueteLectura.Lector;

public class ClaseMain {

    public static void main(String[] args) {
        // TODO code application logic here
        
        SubcidioEstadia subE;
        SubcidioBienes subB;
        System.out.println("Ingrese los datos del subcidio de estadía.\n"
                            + "Lugar de destino.\n"
                            + "Costo del pasaje.\n"
                            + "Cantidad de dias.\n"
                            + "Monto del hotel.\n"
                            + "Nombre del investigador.\n"
                            + "Plan de trabajo.\n"
                            + "Fecha.\n");
        
        subE = new SubcidioEstadia(Lector.leerString(),Lector.leerDouble(),
                                   Lector.leerInt(),Lector.leerDouble(),Lector.leerString(),
                                   Lector.leerString(),Lector.leerInt());
        
        System.out.println("Ingrese los datos del subcidio de bienes.\n"
                            + "Cantidad de bienes.\n"
                            + "Nombre del investigador.\n"
                            + "Plan de trabajo.\n"
                            + "Fecha.\n");
        int cantBienes;
        subB = new SubcidioBienes(cantBienes=Lector.leerInt(),Lector.leerString(),
                                  Lector.leerString(),Lector.leerInt());
        Bienes b;
        for(int j=0;j<cantBienes;j++){
            System.out.println("Ingrese los datos del bien a cargar.\n"
                               + "Descripcion, cantidad y costo por unidad");
            b = new Bienes(Lector.leerString(),Lector.leerInt(),Lector.leerDouble());
            subB.agregarBien(b);
        }
        
        System.out.println(subE.toString());
        System.out.println(subB.toString());
        
    }
    
}
