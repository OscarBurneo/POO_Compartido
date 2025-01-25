import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemFact {
    public static Scanner sc=new Scanner(System.in);
    public static File archive=new File("inventario_libros.txt");
    public static String[] product;

    public static void main(String[] args) {
        byte op;
        boolean val=false;

        System.out.print("Bienvenido al sistema");

        while (!val) {
            try {
                System.out.print("\nEscoja una opcion: \n"+
                                "    1) Realizar una nueva factura \n"+
                                "    2) Mostrar inventario\n"+
                                "    3) Salir del sistema\n"+
                                "Introduzca el dígito de la opcion: ");
                op=sc.nextByte();
                if (op<=0||op>=4) {
                    throw new Exception("**Opcion Invalida**\n"+
                                       "Por favor introduzca alguna de las opciones dadas.\n");
                }
                switch (op) {
                    case 1:
                        
                        break;
                    case 2:
                        mostrarInventario();
                        break;
                    case 3:
                        val=true;
                        break;
                }
            } catch (Exception e) {
                System.err.print("\nError: "+e.getMessage());
            }
            
        }
        System.out.println("\nGracias por usar el sistema. :)");

    }
    public static void mostrarInventario(){
        ArrayList<String>Name=new ArrayList<>();
        ArrayList<Double>Precio=new ArrayList<>();
        ArrayList<Integer>Stock=new ArrayList<>();
    
        try {
            Scanner fl=new Scanner(archive);
            while (fl.hasNextLine()) {
                product=fl.nextLine().split(":");
                if (product[0].length()>22) {
                    product[0]=product[0].substring(0,22);
                }
                Name.add(product[0]);
                Precio.add(Double.parseDouble(product[1]));
                Stock.add(Integer.parseInt(product[2]));
            }
            System.out.println("\nINVENTARIO");
            System.out.println("----------");
            System.out.printf("%-22s %-7s %-6s%n","Nombre","Precio","Stock");
            System.out.println("====================== ======= ======");
            for (int i=0;i<Name.size();i++) {
                System.out.printf("%-22s %,7.2f %6d%n",Name.get(i),Precio.get(i),Stock.get(i));
            }

        } catch (Exception e) {
            System.err.println("Error: "+e.toString());
        }
         
    }
}