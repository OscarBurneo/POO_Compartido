package Ejercicio_4;
import Ejercicio_4.Obj.Cuenta;
import Ejercicio_4.Obj.CuentaAhorro;
import Ejercicio_4.Obj.CuentaCorriente;
import Ejercicio_4.Obj.CuentaInversion;

public class Ejecutor {

    public static void main(String[] args) {

        CuentaAhorro cuentaAhorro = new CuentaAhorro("114578512185161", 300);
        CuentaCorriente cuentaCorriente = new CuentaCorriente("114578512185547", 532, 300);
        CuentaInversion cuentaInversion = new CuentaInversion("114578512185754", 988.17, 30);

        Cuenta cuentas[] = {cuentaAhorro, cuentaCorriente, cuentaInversion};
        
        for(Cuenta cuenta : cuentas) {
            cuenta.mostrarCuenta();
        }
    }
}
