package Ejercicio_2.Obj;

public class Persona  {
    public String nombre;
    public int edad;
    public  int dni;
    public String correo;
    
    public Persona(String nombre, int edad, int dni, String correo){
        this.nombre=nombre;
        this.dni=dni;
        this.edad=edad;
        this.correo=correo;
    }
    
    public void MostrarDatos(){
        System.out.println(" Nombre: "+nombre);
        System.out.println(" Edad: "+edad);
        System.out.println(" DNI: "+dni);
        System.out.println(" Correo: "+correo);
    }
    
}

