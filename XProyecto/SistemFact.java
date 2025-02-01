import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class SistemFact {
    public static Scanner sc = new Scanner(System.in);
    public static File archive = new File("../inventario_libros.txt");
    public static File archivoVentas = new File("../ventas_libros.txt");
    public static String[] product;
    public static ArrayList<String> Name = new ArrayList<>();
    public static ArrayList<Double> Precio = new ArrayList<>();
    public static ArrayList<Integer> Stock = new ArrayList<>();
    public static float iva = 0.12f;

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

        Name.clear();
        Precio.clear();
        Stock.clear();

        if (!archive.exists()) {
            System.err.println("ERROR: El archivo de productos no existe.");
            return;
        }

        try (Scanner fl = new Scanner(archive)) {
            while (fl.hasNextLine()) {
                String linea = fl.nextLine();
                String[] product = linea.split(":");

                if (product.length < 3) {
                    System.err.println("Línea ignorada por formato incorrecto: " + linea);
                    continue;
                }

                String nombre = product[0].trim();
                double precio;
                int stock;

                try {
                    precio = Double.parseDouble(product[1].trim());
                    stock = Integer.parseInt(product[2].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error de formato en línea: " + linea);
                    continue;
                }

                if (nombre.length() > 22) {
                    nombre = nombre.substring(0, 22);
                }

                Name.add(nombre);
                Precio.add(precio);
                Stock.add(stock);
            }

            System.out.println("\nPRODUCTOS");
            System.out.println("----------");
            System.out.printf("%-5s %-22s %-7s %-6s%n", "#", "Nombre", "Precio", "Stock");
            System.out.println("====  ====================== ======= ======");

            for (int i = 0; i < Name.size(); i++) {
                System.out.printf("%-5d %-22s %,7.2f %6d%n", (i + 1), Name.get(i), Precio.get(i), Stock.get(i));
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void nuevaVenta() {
        String nombre, producto, cedula;
        int opcion, cantidadProductos, descuento = 0, descuentoOpcion, contadorLineas;
        float precioTotal;
        double precio;
        boolean condicion = true;
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.forLanguageTag("es-ES"));
        String fechaFormateada = fechaActual.format(formato);

        try {
            sc.nextLine();

            System.out.print("\nIngrese su nombre completo: ");
            nombre = sc.nextLine().trim();
            if (nombre.isEmpty())
                throw new Exception("No se ingresó ningún nombre.");

            System.out.print("\nIngrese su cédula: ");
            cedula = sc.nextLine().trim();
            if (cedula.length() != 10)
                throw new Exception("El número de cédula ingresado no tiene un formato válido.");

            while (condicion) {
                System.out.println("\nSeleccione un producto:");
                mostrarInventario();

                System.out.print("\nDigite el número del producto: ");
                String input = sc.nextLine();
                try {
                    opcion = Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    throw new Exception("Formato de la opción ingresada incorrecto.");
                }

                if (opcion < 1 || opcion > Name.size()) {
                    throw new Exception("Opción ingresada fuera de límites (1 - " + Name.size() + ").");
                }

                producto = Name.get(opcion - 1);
                precio = Precio.get(opcion - 1);

                System.out.print("\n¿Cuántos " + producto + " desea comprar?: ");
                if (!sc.hasNextInt())
                    throw new Exception("El formato del número ingresado es incorrecto.");
                cantidadProductos = sc.nextInt();
                sc.nextLine();

                if (!archivoVentas.exists()) {
                    archivoVentas.createNewFile();
                }

                contadorLineas = ContarLineasArchivo();

                try (FileWriter fw = new FileWriter(archivoVentas, true);
                        PrintWriter archivoWriter = new PrintWriter(fw)) {

                    archivoWriter.println((contadorLineas + 1) + ": " + cedula + ": " + nombre + ": " + producto + ": "
                            + precio
                            + ": " + fechaActual
                            + ": " + cantidadProductos + ": " + 0);

                    System.out.println("=======================");
                    System.out.println("  Venta registrada");
                    System.out.println("=======================");

                    editarArchivo(producto, cantidadProductos);

                    System.out.println("\n¿Desea comprar otro producto?");
                    System.out.println("    1) Sí");
                    System.out.println("    2) No");
                    System.out.print("Opción: ");

                    String opcionInput = sc.nextLine();
                    try {
                        int opcionNumero = Integer.parseInt(opcionInput);
                        if (opcionNumero < 1 || opcionNumero > 2)
                            throw new Exception("Opción fuera de límites (1 - 2)");
                        condicion = (opcionNumero == 1);
                    } catch (NumberFormatException ex) {
                        throw new Exception("Formato de la opción ingresada incorrecto.");
                    }
                }
            }

            System.out.println("==============================================");
            System.out.println("               Descuento");
            System.out.println("==============================================");

            System.out.println("¿Existe descuento?");
            System.out.println("    1) Sí");
            System.out.println("    2) No");
            System.out.print("Opción: ");

            String descuentoInput = sc.nextLine();
            try {
                descuentoOpcion = Integer.parseInt(descuentoInput);
                if (descuentoOpcion < 1 || descuentoOpcion > 2) {
                    throw new Exception("Opción fuera de límites (1 - 2)");
                }
            } catch (NumberFormatException ex) {
                throw new Exception("Formato de la opción ingresada incorrecto.");
            }

            if (descuentoOpcion == 1) {
                System.out.print("\nDigite el porcentaje de descuento: ");
                String descuentoStr = sc.nextLine();
                try {
                    descuento = Integer.parseInt(descuentoStr);
                    if (descuento < 0 || descuento >= 100) {
                        throw new Exception("El descuento debe estar entre 0% y 99%.");
                    }
                } catch (NumberFormatException ex) {
                    throw new Exception("Formato del descuento ingresado incorrecto.");
                }
            }

            precioTotal = CalculoPrecio(descuento, cedula, fechaFormateada);
            MostrarFactura(precioTotal, cedula, fechaFormateada, nombre);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
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

    public static float CalculoPrecio(int descuento, String cedula, String fechaActual) {

        float total = 0, sumaPrecio = 0, precio, totalMasIva = 0;
        String[] producto;
        int cantidad;

        ArrayList<String> carrito = new ArrayList<>();

        try {

            carrito = LeerVentas(cedula, fechaActual);

            for (String item : carrito) {

                producto = item.split(":");
                precio = Float.parseFloat(producto[4]);
                cantidad = Integer.parseInt(producto[6]);
                sumaPrecio += (precio * cantidad);

            }

            total = sumaPrecio - (sumaPrecio * (descuento / 100));
            totalMasIva = total + (total * iva);

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        return totalMasIva;

    }

    public static ArrayList<String> LeerVentas(String cedula, String fecha) {

        ArrayList<String> ventas = new ArrayList<>();

        File archivoVentas = new File("../ventas_libros.txt");

        if (!archivoVentas.exists()) {
            System.err.println("ERROR: El archivo de ventas no existe.");
            return ventas;
        }

        try (Scanner fl = new Scanner(archivoVentas)) {
            while (fl.hasNextLine()) {
                String linea = fl.nextLine().trim();
                String[] product = linea.split(":");

                if (product.length < 7) {
                    System.err.println("ERROR: Línea mal formada: " + linea);
                    continue;
                }

                String cedulaArray = product[1].trim();
                String fechaArray = product[5].trim();

                if (cedulaArray.equals(cedula.trim()) && fechaArray.equals(fecha.trim())) {
                    try {

                        ventas.add(product[0].trim() + ":" + product[1].trim() + ":" + product[2].trim() + ":"
                                + product[3].trim() + ":" + product[4].trim() + ":"
                                + fechaArray.trim() + ":" + product[6].trim());

                    } catch (NumberFormatException e) {
                        System.err.println("ERROR: Formato de número incorrecto en línea: " + linea);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR al leer ventas: " + e.getMessage());
        }

        return ventas;
    }

    public static ArrayList<String> LeerVentasAll() {

        ArrayList<String> ventas = new ArrayList<>();

        File archivoVentas = new File("../ventas_libros.txt");

        if (!archivoVentas.exists()) {
            System.err.println("ERROR: El archivo de ventas no existe.");
            return ventas;
        }

        try (Scanner fl = new Scanner(archivoVentas)) {
            while (fl.hasNextLine()) {
                String linea = fl.nextLine().trim();
                String[] product = linea.split(":");

                if (product.length < 7) {
                    System.err.println("ERROR: Línea mal formada: " + linea);
                    continue;
                }

                String cedulaArray = product[1].trim();
                String fechaArray = product[5].trim();

                try {

                    ventas.add(product[0].trim() + ":" + product[1].trim() + ":" + product[2].trim() + ":"
                            + product[3].trim() + ":" + product[4].trim() + ":"
                            + fechaArray.trim() + ":" + product[6].trim());

                } catch (NumberFormatException e) {
                    System.err.println("ERROR: Formato de número incorrecto en línea: " + linea);
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR al leer ventas: " + e.getMessage());
        }

        return ventas;
    }

    public static void CambiarVentas(String cedula, String fecha, ArrayList<String> ventas) {

        ArrayList<String> ventasAll = LeerVentasAll();

        try (FileWriter fw = new FileWriter(archivoVentas);
                PrintWriter archivoWriter = new PrintWriter(fw)) {

            for (String venta : ventasAll) {
                String linea = venta;
                String[] producto = linea.split(":");

                if (producto.length >= 6) {
                    if (cedula.trim().equals(producto[1].trim()) && fecha.trim().equals(producto[5].trim())) {
                        archivoWriter.println(producto[0].trim() + ":" + producto[1].trim() + ":" + producto[2].trim()
                                + ":"
                                + producto[3].trim() + ":" + producto[4].trim() + ":" + producto[5].trim() + ":"
                                + producto[6].trim() + ":" + 1);
                    } else {
                        archivoWriter.println(producto[0].trim() + ":" + producto[1].trim() + ":" + producto[2].trim()
                                + ":"
                                + producto[3].trim() + ":" + producto[4].trim() + ":" + producto[5].trim() + ":"
                                + producto[6].trim() + ":" + 0);
                    }
                } else {
                    System.err.println("Error: Formato de venta incorrecto: " + linea);
                }
            }

        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void MostrarFactura(float precioTotal, String cedula, String fecha, String nombre) {
        ArrayList<String> ventas = LeerVentas(cedula, fecha);

        if (ventas == null || ventas.isEmpty()) {
            System.out.println("No se encontraron ventas para la cédula y fecha especificadas.");
            return;
        }

        System.out.println();
        System.out.println("==============================================");
        System.out.println("                FACTURA                       ");
        System.out.println("           --- LIBRO LAB ---                   ");
        System.out.println("==============================================");
        System.out.printf("R.U.C.: %-35s\n", "1790112233001");
        System.out.printf("FACTURA NO.: %-30s\n", "002-001-123456789");
        System.out.printf("AUT. SRI: %-31s\n", "1234567890");
        System.out.println("----------------------------------------------");
        System.out.println("Dirección Matriz: Páez N22-53 y Ramírez Dávalos");
        System.out.println("Dirección Sucursal: García Moreno y Sucre");
        System.out.println("----------------------------------------------");
        System.out.printf("Sr(es): %-30s\n", nombre);
        System.out.printf("R.U.C./C.I.: %-30s\n", cedula);
        System.out.printf("Fecha Emisión: %-25s\n", fecha);
        System.out.printf("Guía de Remisión: %-22s\n", "001-001-123456789");
        System.out.println("----------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-10s\n", "Cant", "Producto", "P. Unitario", "V. Total");

        float precioSub = 0;

        for (String venta : ventas) {
            String[] linea = venta.split(":");

            int numeroFactura = Integer.parseInt(linea[0]);
            String cedulaUsuario = linea[1].trim();
            String nombreUsuario = linea[2].trim();
            String nombreProducto = linea[3].trim();
            float precio = Float.parseFloat(linea[4].trim());
            String fechaFactura = linea[5].trim();
            int cantidad = Integer.parseInt(linea[6]);

            System.out.printf("%-5d %-20s %-10.2f %-10.2f\n", cantidad, nombreProducto, precio, precio * cantidad);
        }

        System.out.println("----------------------------------------------");
        System.out.printf("%-25s: %.2f\n", "Efectivo", precioTotal);
        System.out.println("----------------------------------------------");
        System.out.printf("Subtotal 12%% IVA: %20.2f\n", precioSub * 0.12);
        System.out.printf("Subtotal sin impuestos: %14.2f\n", precioSub);

        CambiarVentas(cedula, fecha, ventas);
    }

    public static int ContarLineasArchivo() {

        int contador = 0;

        File archivoVentas = new File("../ventas_libros.txt");

        if (!archivoVentas.exists()) {
            System.err.println("ERROR: El archivo de ventas no existe.");
            return contador;
        }

        if (archivoVentas.length() == 0) {
            return contador;
        }

        try (Scanner fl = new Scanner(archivoVentas)) {
            while (fl.hasNextLine()) {
                fl.nextLine();
                contador++;
            }
        } catch (Exception e) {
            System.err.println("ERROR al leer lineas: " + e.getMessage());
        }

        return contador;

    }

}
