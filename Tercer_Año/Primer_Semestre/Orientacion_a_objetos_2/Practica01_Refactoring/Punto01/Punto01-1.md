```Java
    /** 
    * Retorna el límite de crédito del cliente
    */
    public double lmtCrdt() {...

    /** 
    * Retorna el monto facturado al cliente desde la fecha f1 a la fecha f2
    */
    protected double mtFcE(LocalDate f1, LocalDate f2) {...

    /** 
    * Retorna el monto cobrado al cliente desde la fecha f1 a la fecha f2
    */
    private double mtCbE(LocalDate f1, LocalDate f2) {...
```
- Bad smell: nombres de métodos poco descriptivos 
- ```Java
     public double lmtCrdt() {...

     protected double mtFcE(LocalDate f1, LocalDate f2) {...

     private double mtCbE(LocalDate f1, LocalDate f2) {...
  ```
- Aplico nombres más descriptivos 
- ```Java
    /** 
    * Retorna el límite de crédito del cliente
    */
    public double calcularLimiteCredito() {...

    /** 
    * Retorna el monto facturado al cliente desde la fecha f1 a la fecha f2
    */
    protected double calcularMontoFacturadoEntreFechas(LocalDate f1, LocalDate f2) {...

    /** 
    * Retorna el monto cobrado al cliente desde la fecha f1 a la fecha f2
    */
    private double calcularMontoCobradoEntreFechas(LocalDate f1, LocalDate f2) {...
  ```