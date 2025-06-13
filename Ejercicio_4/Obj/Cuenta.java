package Ejercicio_4.Obj;

public class Cuenta {
    
    public String numeroCuenta;
    public double saldo;

    public Cuenta() {
        this.numeroCuenta = "";
        this.saldo = 0.0;
    }

    public Cuenta(String n, double s) {
        this.numeroCuenta = n;
        this.saldo = s;
    }

    public void mostrarCuenta() {
        System.out.println("===============================");
    }

}
