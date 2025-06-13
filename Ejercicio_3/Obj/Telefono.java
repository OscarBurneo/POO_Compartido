package Ejercicio_3.Obj;
public class Telefono extends Producto {

    private String numeroSim = "";

    public Telefono(String nombre, double precio, String nSim) {
        super(nombre, precio);
        this.numeroSim = nSim;
    }

    public void mostrarProducto() {
        super.mostrarProducto();
        System.out.println("Número de la Sim: " + this.numeroSim);
        System.out.println("Precio Final: " + super.precioFinal);
    }

}
