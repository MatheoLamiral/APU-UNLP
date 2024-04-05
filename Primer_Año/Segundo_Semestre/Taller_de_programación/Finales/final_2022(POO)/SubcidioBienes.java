
package final_2022;

public class SubcidioBienes extends Subcidio{
    private Bienes [] bienes;
    private int dimF;
    private int dimL;

    public SubcidioBienes(int n,String invest, String planTrabajo,int fecha) {
        super(invest,planTrabajo,fecha);
        this.bienes = new Bienes [n];
        this.dimF = n;
        this.dimL = 0;
        //Java inicializa por defecto los arreglos en null
    }
    
    public void agregarBien(Bienes b){
        if((this.dimL<=this.dimF)&&(this.bienes[dimL]==null)){
            this.bienes[dimL] = b;
            System.out.println("cargo");
            this.dimL++;
        }
    }
    
    public double montoTotal(){
        double total=0;
        for(int i=0;i<this.dimL;i++){
            total += this.bienes[i].calcularCosto();
        }
        return total;
    }
    
    public String toString() {
        return "Nombre del investigador: "+this.getInvest()+"\n"+
               " Plan de trabajo: "+this.getPlanT()+"\n"+
               " Fecha de solicitud: "+this.getFecha()+"\n"+
               " Monto total: "+this.montoTotal();
    }
}
