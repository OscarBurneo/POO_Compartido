package Ejercicio_1.Obj;

public class Ave extends Animal {
    public String behavior;

    public Ave(String nombre, int edad, String Alim, String behavior) {
        super(nombre, edad, Alim);
        this.behavior = behavior;
    }
}
