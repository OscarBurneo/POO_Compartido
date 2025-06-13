package Ejercicio_4.Obj;

public class CuentaInversion extends Cuenta {
    
    private int rendimiento;

    public CuentaInversion(String numeroCuenta, double saldo, int rendimiento) {
        super(numeroCuenta, saldo);
        this.rendimiento = rendimiento;
    }

    public void mostrarCuenta() {
        super.mostrarCuenta();
        System.out.println("Tipo de Cuenta: Ahorros");
        System.out.println("Número de Cuenta: " + super.numeroCuenta);
        System.out.println("Saldo de la Cuenta: " + super.saldo);
        System.out.println("La cuenta tiene un rendimiento del: " + this.rendimiento + "%");
    }

}
