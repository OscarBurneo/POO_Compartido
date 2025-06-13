package Ejercicio_5.Obj;

public class Familiar extends Habitacion {
    
    private int capacidadNiños;

    public Familiar(int n, double p, int c) {
        super(n, p);
        this.capacidadNiños = c;
    }

    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Capacidad para niños: " + this.capacidadNiños);
    }



}
