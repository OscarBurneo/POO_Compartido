package Ejercicio_5.Obj;

public class Suit extends Habitacion {
    
    private boolean jacuzzi;

    public Suit(int n, double p) {
        super(n, p);
        this.jacuzzi = true;
    }

    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("La habitacion si tiene jacuzzi");
    }

}
