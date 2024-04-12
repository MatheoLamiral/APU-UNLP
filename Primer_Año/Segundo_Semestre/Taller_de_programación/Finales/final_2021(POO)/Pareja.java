
package Final_2021;

public class Pareja {
    private Participante part1;
    private Participante part2;
    private String estilo;

    public Pareja(Participante unPart1, Participante unPart2, String unEstilo) {
        this.setPart1(unPart1);
        this.setPart2(unPart2);
        this.setEstilo(unEstilo);
    }
    
    public int diferenciaEdad(){
        if(this.getPart1().getEdad()>this.getPart2().getEdad()){
            return this.getPart1().getEdad() - this.getPart2().getEdad();
        }
        else{
            return this.getPart2().getEdad() - this.getPart1().getEdad();
        }
    }
    
    public String mostrarNombres(){
        return "Participante 1: "+this.part1.mostrarNombre()+"\n"+
               "Participante 2: "+this.part2.mostrarNombre();
    }
    
    //getters and setters
    public Participante getPart1() {
        return part1;
    }

    public void setPart1(Participante part1) {
        this.part1 = part1;
    }

    public Participante getPart2() {
        return part2;
    }

    public void setPart2(Participante part2) {
        this.part2 = part2;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }
    
    
}
