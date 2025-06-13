package Ejercicio_2.Obj;

public class Administrativo extends Persona {
    String departamento;
    public Administrativo(String nombre, int edad, int dni, String correo, String departamento) {
        super(nombre, edad, dni, correo);
        this.departamento = departamento;
    }
    public void MostrarDatos() {
        System.out.println("\nPersonal Administrativo: ");
        super.MostrarDatos();
        System.out.println(" Departamento al que corresponde: " + departamento);
    }

}
