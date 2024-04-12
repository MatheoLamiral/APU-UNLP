
package final_2022;

public class SubcidioEstadia extends Subcidio {
    private String destino;
    private double pasaje;
    private int dias;
    private double montoH;

    public SubcidioEstadia(String destino, double pasaje, int dias, double montoH,String invest, String planTrabajo,int fecha) {
        super(invest,planTrabajo,fecha);
        this.setDestino(destino);
        this.setPasaje(pasaje);
        this.setDias(dias);
        this.setMontoH(montoH);
    }

    public double montoTotal(){
        return this.getPasaje()+(this.getDias()*this.getMontoH());
    }

    @Override
    public String toString() {
        return "Nombre del investigador: "+this.getInvest()+"\n"+
               " Plan de trabajo: "+this.getPlanT()+"\n"+
               " Fecha de solicitud: "+this.getFecha()+"\n"+
               " Monto total: "+this.montoTotal()+"\n"+
               " Lugar de destino: "+this.getDestino()+"\n"+
               " Días de estadía: "+this.getDias();
    }
    
    
    //getters and setters
    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPasaje() {
        return pasaje;
    }

    public void setPasaje(double pasaje) {
        this.pasaje = pasaje;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public double getMontoH() {
        return montoH;
    }

    public void setMontoH(double montoH) {
        this.montoH = montoH;
    }
    
    
}
