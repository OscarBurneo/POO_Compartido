package Ejercicio_2.Obj;

public class Docente extends Persona{
    String Materia;
    public Docente(String nombre, int edad, int dni, String correo, String Materia) {
        super(nombre, edad, dni, correo);
        this.Materia = Materia;
    }
    public void MostrarDatos() {
        System.out.println("\nDocente: ");
        super.MostrarDatos();
        System.out.println(" Materia que imparte: " + Materia);
    }
}
