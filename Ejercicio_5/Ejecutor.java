package Ejercicio_5;
import Ejercicio_5.Obj.Estandar;
import Ejercicio_5.Obj.Familiar;
import Ejercicio_5.Obj.Habitacion;
import Ejercicio_5.Obj.Suit;

public class Ejecutor {
    
    public static void main(String[] args) {
        
        Familiar familiar = new Familiar(123, 12, 3);
        Estandar estandar = new Estandar(121, 14, true);
        Suit suit = new Suit(145, 18);

        Habitacion habitaciones[] = {familiar, estandar, suit};

        for(Habitacion habitacion : habitaciones) {
            habitacion.mostrarInformacion();
        }

    }

}
