
package Final_2021;

public class ConcursoDeBaile {
    private Pareja [] parejas;
    private int dimF;

    public ConcursoDeBaile(int n) {
        this.parejas = new Pareja [n];
        for(int i=0;i<n;i++){
            this.parejas[i]=null;
        }
        this.dimF=n;
    }
    
    public void agregarPareja(Pareja p){
        int i=0; //el inciso especifica que se suponga que hay lugar en el arreglo, por ende no verifico
        while(this.parejas[i]!= null){
            i++;
        }
        this.parejas[i]=p;
    }
    
    public Pareja mayorDiferenciaEdad(){
        Pareja maxPareja=null;
        int maxDif=-1;
        for(int i=0;i<this.dimF;i++){
            if(this.parejas[i].diferenciaEdad()>maxDif){
                maxDif=this.parejas[i].diferenciaEdad();
                maxPareja=this.parejas[i];
            }
        }
        return maxPareja;
    }

    //getters and setters
    public int getDimF() {
        return dimF;
    }
    
    
}
