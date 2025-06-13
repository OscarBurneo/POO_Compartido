package Ejercicio_3.Obj;

public class Laptop extends Producto {
    
    private int memoriaRam = 0;

    public Laptop(String nombre, double precio, int memoriaRam) {
        super(nombre, precio);
        this.memoriaRam = memoriaRam;
    }

    public void mostrarProducto() {
        super.mostrarProducto();
        System.out.println("Cantidad de Memoria Ram: " + this.memoriaRam + " GB");
        System.out.println("Precio Final: " + super.precioFinal);
    }

}
