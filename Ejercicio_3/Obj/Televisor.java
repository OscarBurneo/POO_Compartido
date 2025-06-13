package Ejercicio_3.Obj;

public class Televisor extends Producto {

    private double tamaño = 0.0;

    public Televisor(String nombre, double precio, double tamaño) {
        super(nombre, precio);
        this.tamaño = tamaño;
    }

    public void mostrarProducto() {
        super.mostrarProducto();
        System.out.println("Tamaño: " + this.tamaño + " pulgadas");
        System.out.println("Precio Final: " + super.precioFinal);
    }

}