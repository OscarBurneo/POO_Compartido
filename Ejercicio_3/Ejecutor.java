package Ejercicio_3;

import Ejercicio_3.Obj.Laptop;
import Ejercicio_3.Obj.Producto;
import Ejercicio_3.Obj.Telefono;
import Ejercicio_3.Obj.Televisor;

public class Ejecutor {

    public static void main(String[] args) {

        Televisor televisor = new Televisor("Global", 1200.00, 50);
        Telefono telefono = new Telefono("Redmi 14 pro", 300, "1457854987456");
        Laptop laptop = new Laptop("Asus TUF GAMING A15", 1200, 8);

        Producto lisProductos[] = {televisor, telefono, laptop};

        for(Producto producto : lisProductos) {
            producto.calcularPrecioFinal();
            producto.mostrarProducto();
        }

    }

}
