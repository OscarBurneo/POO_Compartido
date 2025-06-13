package Ejercicio_4.Obj;

public class CuentaCorriente extends Cuenta {
    
    private double sobregiro;

    public CuentaCorriente(String numeroCuenta, double saldo, double sobregiro) {
        super(numeroCuenta, saldo);
        this.sobregiro = sobregiro;
    }

    public void mostrarCuenta() {
        super.mostrarCuenta();
        System.out.println("Tipo de Cuenta: Ahorros");
        System.out.println("Número de Cuenta: " + super.numeroCuenta);
        System.out.println("Saldo de la Cuenta: " + super.saldo);
        System.out.println("Esta cuenta permite un sobregiro maximo de: " + this.sobregiro);
    }

}
