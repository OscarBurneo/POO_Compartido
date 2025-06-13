package Ejercicio_2;

import Ejercicio_2.Obj.Administrativo;
import Ejercicio_2.Obj.Docente;
import Ejercicio_2.Obj.Estudiante;

public class Main {
    Estudiante estudiante = new Estudiante("Juan Perez", 20, 12345678, "jp","Ing. Sistemas", 3.5f);
    Docente docente = new Docente("Maria Lopez", 35, 87654321, "ml", "Matematicas");
    Administrativo administrativo = new Administrativo("Carlos Gomez", 40, 11223344, "cg", "Recursos Humanos");

    public static void main(String[] args) {
        Main main = new Main();
        main.estudiante.MostrarDatos();
        System.out.println();
        main.docente.MostrarDatos();
        System.out.println();
        main.administrativo.MostrarDatos();
        System.out.println();
    }
}
