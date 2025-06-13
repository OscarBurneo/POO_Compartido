package Ejercicio_1;
import java.util.Scanner;

import Ejercicio_1.Obj.Ave;
import Ejercicio_1.Obj.Mamifero;
import Ejercicio_1.Obj.Reptil;

public class Main {
    String nombre, alim, behavior;
    int edad;
    Ave[] aves = new Ave[3];
    Mamifero[] mamiferos = new Mamifero[3];
    Reptil[] reptiles = new Reptil[3];
    public Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Main Main=new Main();
        int op=0;
        do {
            op=Main.menu();
            Main.sc.nextLine(); 
            switch (op) {
                case 1:
                    for (int i = 0; i < Main.aves.length; i++) {
                        Main.ingresoAve(i);
                    }
                    break;
                case 2:
                    for (int i = 0; i < Main.mamiferos.length; i++) {
                        Main.ingresoMamifero(i);
                    }
                    break;
                case 3:
                    for (int i = 0; i < Main.reptiles.length; i++) {
                        Main.ingresoReptil(i);
                    }
                    break;
                case 4:
                    Main.mostrarAves();
                    break;
                case 5:
                    Main.mostrarMamiferos();
                    break;
                case 6:
                    Main.mostrarReptiles();
                    break;
                case 7:
                    Main.mostrarAnimales();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida, intente de nuevo.");
            }

        } while (op!=0);
    }
    public int menu(){
        System.out.println("\nMenu de opciones:");
        System.out.println("1. Ingresar Aves");
        System.out.println("2. Ingresar Mamiferos");
        System.out.println("3. Ingresar Reptiles");
        System.out.println("4. Mostrar Aves");
        System.out.println("5. Mostrar Mamiferos");
        System.out.println("6. Mostrar Reptiles");
        System.out.println("7. Mostrar Todos los Animales");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
        return sc.nextInt();
    }
    public void ingreso(){
        System.out.println();
        System.out.print("Nombre/Especie:");
        nombre = sc.nextLine();
        System.out.print("Edad: ");
        edad = sc.nextInt();
        sc.nextLine();
        System.out.print("Alimentacion: ");
        alim = sc.nextLine();
        System.out.print("Comportamiento: ");
        behavior = sc.nextLine();
        System.out.println();
    }
    public Ave ingresoAve(int i){
        System.out.println("Ingrese los datos del ave " + (i + 1) + ":");
        ingreso();
        aves[i] = new Ave(nombre, edad, alim, behavior);
        return aves[i];

    }
    public Mamifero ingresoMamifero(int i){
        System.out.println("Ingrese los datos del mamifero " + (i + 1) + ":");
        ingreso();
        mamiferos[i] = new Mamifero(nombre, edad, alim, behavior);
        return mamiferos[i];
    }
    public Reptil ingresoReptil(int i){
        System.out.println("Ingrese los datos del reptil " + (i + 1) + ":");
        ingreso();
        reptiles[i] = new Reptil(nombre, edad, alim, behavior);
        return reptiles[i];
    }
    public void mostrarAves(){
        System.out.println("\nAves:");
        for (int i = 0; i < aves.length; i++) {
            System.out.println();
            System.out.println("Nombre/Especie: " + aves[i].nombre +
                               "\nEdad: " + aves[i].edad +
                               "\nAlimentacion: " + aves[i].Alim +
                               "\nComportamiento: " + aves[i].behavior);
            
        }
    }
    public void mostrarMamiferos(){
        System.out.println("\nMamiferos:");
        for (int i = 0; i < mamiferos.length; i++) {
            System.out.println();
            System.out.println("Nombre/Especie: " + mamiferos[i].nombre +
                               "\nEdad: " + mamiferos[i].edad +
                               "\nAlimentacion: " + mamiferos[i].Alim +
                               "\nComportamiento: " + mamiferos[i].behavior);
            
        }
    }
    public void mostrarReptiles(){
        System.out.println("\nReptiles:");
        for (int i = 0; i < reptiles.length; i++) {
            System.out.println();
            System.out.println("Nombre/Especie: " + reptiles[i].nombre +
                               "\nEdad: " + reptiles[i].edad +
                               "\nAlimentacion: " + reptiles[i].Alim +
                               "\nComportamiento: " + reptiles[i].behavior);
            
        }
    }
    public void mostrarAnimales(){
        mostrarAves();
        mostrarMamiferos();
        mostrarReptiles();
    }
    
}