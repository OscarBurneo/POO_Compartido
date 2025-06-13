package Ejercicio_2.Obj;

public class Estudiante extends Persona {
    String carrera;
    float promedio;

    public Estudiante(String nombre, int edad, int dni, String correo, String carrera, float promedio) {
        super(nombre, edad, dni, correo);
        this.carrera = carrera;
        this.promedio = promedio;
    }
    public void MostrarDatos() {
        System.out.println("\nEstudiante: ");
        super.MostrarDatos();
        System.out.println(" Carrera: " + carrera);
        System.out.println(" Promedio: " + promedio);
    }
    
}
