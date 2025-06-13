package Ejercicio_5.Obj;
public class Habitacion {
    
    public int numeroHabitacion;
    public double precioPNoche;

    public Habitacion(int n, double p) {
        this.numeroHabitacion = n;
        this.precioPNoche = p;
    }

    public void mostrarInformacion() {
        System.out.println("=========================================");
        System.out.println("Número de la habitacion: " + this.numeroHabitacion);
        System.out.println("Precio por noche: " + this.precioPNoche);
    }

}
