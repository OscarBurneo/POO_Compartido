package Ejercicio_4.Obj;

public class CuentaAhorro extends Cuenta {
    
    private double tasaInterez;

    public CuentaAhorro(String n, double s) {
        super(n, s);
        this.tasaInterez = 0.5;
    }

    public void mostrarCuenta() {
        super.mostrarCuenta();
        System.out.println("Tipo de Cuenta: Ahorros");
        System.out.println("Número de Cuenta: " + super.numeroCuenta);
        System.out.println("Saldo de la Cuenta: " + super.saldo);
        System.out.println("Tasa de Interez: " + this.tasaInterez);
    }

}
