package Ejercicio_5.Obj;

public class Estandar extends Habitacion {
    
    private boolean vista;

    public Estandar(int n, double p, boolean v) {
        super(n, p);
        this.vista = v;
    }

    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println((vista) ? "La habitacion si tiene vista" : "La habitacion no tiene vista");
    }

}
