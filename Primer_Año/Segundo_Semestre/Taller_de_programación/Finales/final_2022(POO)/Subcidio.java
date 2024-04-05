
package final_2022;

public abstract class Subcidio {
    private String invest;
    private String planT;
    private int fecha;

    public Subcidio(String invest, String planT, int fecha) {
        this.setFecha(fecha);
        this.setPlanT(planT);
        this.setInvest(invest);
    }
    
     public abstract double montoTotal();

    
    //getters and setters
    public String getInvest() {
        return invest;
    }

    public void setInvest(String invest) {
        this.invest = invest;
    }

    public String getPlanT() {
        return planT;
    }

    public void setPlanT(String planT) {
        this.planT = planT;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }
    
    
}
