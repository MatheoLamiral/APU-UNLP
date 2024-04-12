
package final_2022;

public class Bienes {
    private String desc;
    private int cant;
    private double costo;

    public Bienes(String desc, int cant, double costo) {
        this.setDesc(desc);
        this.setCant(cant);
        this.setCosto(costo);
    }

    public double calcularCosto(){
        return this.getCant()*this.getCosto();
    }
    
    
    //getters and setters
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
    
}
