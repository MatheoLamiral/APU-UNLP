
package Final_2021;

public class Participante {
    private int dni;
    private String nombre;
    private int edad;

    public Participante(int unDni,String unNombre,int unaEdad) {
        this.setDni(unDni);
        this.setNombre(unNombre);
        this.setEdad(unaEdad);
    }

    public String mostrarNombre(){
        return "Nombre: "+this.getNombre();
    }
    
    //getters and setters
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    
    
}
