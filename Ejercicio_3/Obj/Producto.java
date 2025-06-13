package Ejercicio_3.Obj;

public class Producto {

    private String nombre;
    private double precio;
    private double iva = 0.15;
    public double precioFinal;

    public Producto() {
        this.nombre = "";
        this.precio = 0.0;
        this.precioFinal = 0.0;
    }

    public Producto(String n, double p) {
        this.nombre = n;
        this.precio = p;
    }

    public void calcularPrecioFinal() {
        double precioFinal = precio + (precio*iva);
        this.precioFinal = precioFinal;
    }

    public void mostrarProducto() {
        System.out.println("============================================");
        System.out.println("Producto: " + this.nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Iva: " + iva);
    }
    
}