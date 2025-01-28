import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemFact {
    public static Scanner sc = new Scanner(System.in);
    public static File archive = new File("../inventario_libros.txt");
    public static File archivoVentas = new File("../ventas_libros.txt");
    public static String[] product;
    public static ArrayList<ArrayList<String[]>> ventas = new ArrayList<>();
    public static ArrayList<String> Name = new ArrayList<>();
    public static ArrayList<Double> Precio = new ArrayList<>();
    public static ArrayList<Integer> Stock = new ArrayList<>();

    public static void main(String[] args) {
        byte op;
        boolean val = false;

        System.out.print("Bienvenido al sistema");

        while (!val) {
            try {
                System.out.print("\nEscoja una opcion: \n" +
                        "    1) Realizar una nueva factura \n" +
                        "    2) Mostrar inventario\n" +
                        "    3) Salir del sistema\n" +
                        "\nIntroduzca el dígito de la opcion: ");
                op = sc.nextByte();
                if (op <= 0 || op >= 4) {
                    throw new Exception("**Opcion Invalida**\n" +
                            "Por favor introduzca alguna de las opciones dadas.\n");
                }
                switch (op) {
                    case 1:
                        nuevaVenta();
                        break;
                    case 2:
                        mostrarInventario();
                        break;
                    case 3:
                        val = true;
                        break;
                }
            } catch (Exception e) {
                System.err.print("\nError: " + e.getMessage());
            }

        }
        System.out.println("\nGracias por usar el sistema. :)");

    }

    public static void mostrarInventario() {

        Name = new ArrayList<>();
        Precio = new ArrayList<>();
        Stock = new ArrayList<>();

        try {
            Scanner fl = new Scanner(archive);
            while (fl.hasNextLine()) {
                product = fl.nextLine().split(":");
                if (product[0].length() > 22) {
                    product[0] = product[0].substring(0, 22);
                }
                Name.add(product[0]);
                Precio.add(Double.parseDouble(product[1]));
                Stock.add(Integer.parseInt(product[2]));
            }

            System.out.println("\nPRODUCTOS");
            System.out.println("----------");
            System.out.printf("%-5s %-22s %-7s %-6s%n", "#", "Nombre", "Precio", "Stock");
            System.out.println("====  ====================== ======= ======");

            for (int i = 0; i < Name.size(); i++) {
                System.out.printf("%-5d %-22s %,7.2f %6d%n", (i + 1), Name.get(i), Precio.get(i), Stock.get(i));
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.toString());
        }

    }

    public static void nuevaVenta() {

        String nombre, producto, fechaActual, nombreMes, cedula;
        int opcion, stock, dia, mes, year, dias, opcionNumero, cantidadProductos;
        double precio;
        boolean diaCorrecto = false, condicion = true;

        try {
            sc.nextLine();

            System.out.print("\nIngrese su nombre completo a continuación: ");
            nombre = sc.nextLine().trim();
            if (nombre.isEmpty()) {
                throw new Exception("No se ingresó ningún nombre.");
            }

            System.out.print("\nIngrese su cedula a continuación: ");
            cedula = sc.nextLine().trim();
            if (cedula.isEmpty()) {
                throw new Exception("No se ingresó ninguna cedula.");
            }
            if (cedula.length() < 10 || cedula.length() > 10) {
                throw new Exception("El número de cedula ingresado no tiene un formato valido");
            }

            System.out.print("\nIngrese el año actual: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new Exception("El formato del año es incorrecto");
            }
            year = sc.nextInt();
            sc.nextLine();

            MostrarMeses();

            System.out.print("\nDigite el número del mes que quiere escoger: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new Exception("Formato de la opción ingresada incorrecto.");
            }
            mes = sc.nextInt();
            sc.nextLine();
            if (mes < 1 || mes > 12) {
                throw new Exception("Opción ingresada fuera de límites (1 - 12).");
            }

            System.out.print("\nIngrese el día de la fecha actual: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                throw new Exception("El día ingresado no es válido.");
            }
            dia = sc.nextInt();
            sc.nextLine();

            if (dia < 1 || dia > 31) {
                throw new Exception("El día ingresado no es válido: " + dia);
            }

            switch (mes) {
                case 1, 3, 5, 7, 8, 10, 12 -> dias = 31;
                case 4, 6, 9, 11 -> dias = 30;
                case 2 -> dias = 28;
                default -> throw new Exception("Mes digitado no válido: " + mes);
            }
            diaCorrecto = DiasComprobar(dias, dia);
            if (!diaCorrecto) {
                throw new Exception("El día " + dia + " para el mes " + mes + " no es correcto");
            }

            String[] meses = {
                    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
            };
            nombreMes = meses[mes - 1];

            fechaActual = dia + " / " + nombreMes + " / " + year;

            while (condicion) {

                System.out.println("\nSeleccione de la lista qué producto desea comprar: ");
                mostrarInventario();

                System.out.print("\nDigite el número del producto que desea seleccionar: ");
                if (!sc.hasNextInt()) {
                    sc.nextLine();
                    throw new Exception("Formato de la opción ingresada incorrecto.");
                }
                opcion = sc.nextInt();
                sc.nextLine();
                if (opcion < 1 || opcion > 10) {
                    throw new Exception("Opción ingresada fuera de límites (1 - 10).");
                }

                producto = Name.get(opcion - 1);
                precio = Precio.get(opcion - 1);

                System.out.print("\n¿Cuantos " + producto + " decea comprar?: ");

                if (!sc.hasNextInt()) {
                    throw new Exception("El formato del número ingresado es incorrecto");
                }

                cantidadProductos = sc.nextInt();

                try (FileWriter fw = new FileWriter(archivoVentas, true);
                        PrintWriter archivoWriter = new PrintWriter(fw)) {

                    archivoWriter.println(cedula + ", " + nombre + ", " + producto + ", " + precio);
                    System.out.println("Venta registrada");

                    editarArchivo(producto, cantidadProductos);

                    System.out.println("¿Decea comprar otro producto? ");
                    System.out.println("    1) Si");
                    System.out.println("    2) No");
                    System.out.print("\nDigite el número de la opcion deceada: ");

                    if (!sc.hasNextInt()) {
                        throw new Exception("El formato que ingreso en la opción es incorrecto");
                    }

                    opcionNumero = sc.nextInt();

                    if (opcionNumero < 1 || opcionNumero > 2) {
                        throw new Exception("El número ingresado no esta dentro de los limites(1 - 2)");
                    }

                    condicion = (opcionNumero == 1) ? true : false;

                }

            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void MostrarMeses() {
        String[] meses = {
                "1. Enero", "2. Febrero", "3. Marzo", "4. Abril", "5. Mayo", "6. Junio",
                "7. Julio", "8. Agosto", "9. Septiembre", "10. Octubre", "11. Noviembre", "12. Diciembre"
        };

        System.out.println("Lista de meses del año:");
        for (String mes : meses) {
            System.out.println(mes);
        }
    }

    public static Boolean DiasComprobar(int dias, int dia) {

        if (dia >= 1 && dia <= dias) {
            return true;
        } else {
            return false;
        }

    }

    public static void editarArchivo(String nombre, int cantidadProductos) {

        int c = 0;
        int stock;
        String producto = "";
        Double precio = 0.0;

        for (String Nam : Name) {

            if (Nam == nombre) {
                stock = Stock.get(c);
                stock -= cantidadProductos;
                Stock.set(c, stock);
            }

            c++;

        }

        try (FileWriter fw = new FileWriter(archive);
                PrintWriter archivoWriter = new PrintWriter(fw)) {

            for (int i = 0; i < Name.size(); i++) {
                archivoWriter.println(Name.get(i) + ":" + Precio.get(i) + ":" + Stock.get(i));
            }

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }

    }

}
