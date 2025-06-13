package Ejercicio_1.Obj;

public class Mamifero extends Animal {
    public String behavior;

    public Mamifero(String nombre, int edad, String Alim, String behavior) {
        super(nombre, edad, Alim);
        this.behavior = behavior;
    }
    
    
}
