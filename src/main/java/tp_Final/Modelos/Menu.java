package tp_Final.Modelos;

import tp_Final.Modelos.*;
import tp_Final.Servicios.Gestor;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {
    private Gestor gestor = new Gestor();
    private Scanner scanner = new Scanner(System.in);
    List<Comprador> compradores = new ArrayList<>();
    List<Vendedor> vendedores = new ArrayList<>();
    List<Vehiculo> vehiculos = new ArrayList<>();
    List<Venta> ventas = new ArrayList<>();

    public Menu() {
        this.compradores = gestor.leerCompradores("compradores.json");
        this.vendedores = gestor.leerVendedores("vendedores.json");
        this.vehiculos = gestor.leerVehiculos("vehiculos.json");
        this.ventas = gestor.leerVentas("ventas.json");
    }

    public void mostrarMenu() throws IOException, ParseException, InterruptedException {
        int opcion;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Compradores");
            System.out.println("2. Vehículos");
            System.out.println("3. Ventas");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> menuCompradores();
                case 2 -> menuVehiculos();
                case 3 -> menuVentas();
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (opcion != 4); // Repite el menú hasta que el usuario elija "Salir"
    }



    public void menuCompradores() throws InterruptedException {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        int opcion = 0;
        do {
            System.out.println("\n--- Menú Compradores ---");
            System.out.println("1. Agregar Comprador");
            System.out.println("2. Listar Compradores");
            System.out.println("3. Editar Compradores");
            System.out.println("4. Volver");
            System.out.print("Elija una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer de entrada

            switch (opcion) {
                case 1:
                    agregarComprador();
                    break; // Salir del switch después de ejecutar la acción
                case 2:
                    listarCompradores();
                    break;
                case 3:
                    editarCompradorPorDni();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break; // Salir del switch, y luego del bucle en el do-while
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 4); // Repite el menú hasta que el usuario elija la opción 4
    }

    private int buscarPersonaPorDni() {
        int contador =0;
        System.out.println("Ingrese el DNI del comprador a editar: ");
        String dni = scanner.nextLine();
        if(dni.length() != 8){
            System.out.println("El dni debe tener 8 números válidos");
        }else if(!dni.matches("1234567890")){
            System.out.println("El dni debe tener 8 números válidos");
        }

        for (Comprador comprador : compradores){
            contador ++;
            if(comprador.getDni() == dni){
                return contador -1;
            }
        }

        return contador-1;
    }

    public void editarCompradorPorDni(){
        int posicion = buscarPersonaPorDni();
        int opcion = 0;


        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Email");
        System.out.println("4. Monto Disponible");
        System.out.println("5. Volver");
        opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion){
            case 1:
                String nombre = scanner.nextLine();
                compradores.get(posicion).setNombre(nombre);
            case 2:
                String apellido = scanner.nextLine();
                compradores.get(posicion).setApellido(apellido);
            case 3:
                String email = scanner.nextLine();
                compradores.get(posicion).setEmail(email);
            case 4:
                double montoDisponible = scanner.nextDouble();
                compradores.get(posicion).setMontoDisponible(montoDisponible);
            case 5: break;
        }

        gestor.guardarComprador(compradores);

    }
    public void menuVehiculos() throws IOException, InterruptedException {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        int opcion = 0;
        do {
            System.out.println("\n--- Menú Vehículos ---");
            System.out.println("1. Agregar Vehículo");
            System.out.println("2. Listar Vehículos");
            System.out.println("3. Editar Vehículos");
            System.out.println("4. Mostrar Vehículos según presupuesto");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    agregarVehiculo();
                    break; // Salir del switch
                case 2:
                    listarVehiculos();
                    break; // Salir del switch
                case 3:
                    editarVehiculoPorId();
                    break; // Salir del switch
                case 4:
                    listaSegunPresupuesto();
                    break; // Salir del switch
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break; // Salir del switch y del bucle en el `do-while`
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 5); // Repite el menú hasta que el usuario elija "Volver"
    }


    public void editarVehiculoPorId() throws IOException {
        int posicion = buscarVehiculoPorId();
        if (posicion == -1) { // Validar si el vehículo existe
            System.out.println("Vehículo no encontrado.");
            return; // Salir si no se encuentra el vehículo
        }

        int opcion;
        do {
            System.out.println("\n--- Editar Vehículo ---");
            System.out.println("1. Editar Modelo");
            System.out.println("2. Editar Marca");
            System.out.println("3. Editar Precio");
            System.out.println("4. Editar Stock");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el nuevo modelo: ");
                    String nuevoModelo = scanner.nextLine();
                    vehiculos.get(posicion).setModelo(nuevoModelo);
                    System.out.println("Modelo actualizado correctamente.");
                }
                case 2 -> {
                    System.out.print("Ingrese la nueva marca: ");
                    String nuevaMarca = scanner.nextLine();
                    vehiculos.get(posicion).setMarca(nuevaMarca);
                    System.out.println("Marca actualizada correctamente.");
                }
                case 3 -> {
                    System.out.print("Ingrese el nuevo precio: ");
                    double nuevoPrecio = scanner.nextDouble();
                    vehiculos.get(posicion).setPrecio(nuevoPrecio);
                    System.out.println("Precio actualizado correctamente.");
                }
                case 4 -> {
                    System.out.print("Ingrese el nuevo stock: ");
                    int nuevoStock = scanner.nextInt();
                    vehiculos.get(posicion).setStock(nuevoStock);
                    System.out.println("Stock actualizado correctamente.");
                }
                case 5 -> System.out.println("Volviendo al menú anterior...");
                default -> System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 5);

        // Guardar los cambios realizados
        gestor.guardarVehiculo(vehiculos);
        System.out.println("Cambios guardados exitosamente.");
    }


    public int buscarVehiculoPorId(){
        int contador = 0;
        System.out.println("Ingrese el ID del vehiculo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        for (Vehiculo vehiculo : vehiculos){
            contador ++;
            if(vehiculo.getId() == id){
                return contador -1;
            }
        }
        return 0;
    }

    public void menuVentas() throws IOException, ParseException, InterruptedException {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        int opcion = 0;

        do {
            System.out.println("\n--- Menú Ventas ---");
            System.out.println("1. Listar Vendedores");
            System.out.println("2. Editar Vendedores");
            System.out.println("3. Agregar Vendedor");
            System.out.println("4. Agregar Venta");
            System.out.println("5. Listar Ventas");
            System.out.println("6. Editar Ventas");
            System.out.println("7. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    listarVendedores();
                    break; // Salir del switch
                case 2:
                    editarVendedorPorDni();
                    break; // Salir del switch
                case 3:
                    agregarVendedor();
                    break; // Salir del switch
                case 4:
                    agregarVenta();
                    break; // Salir del switch
                case 5:
                    listarVentas();
                    break; // Salir del switch
                case 6:
                    editarVentaPorId();
                    break; // Salir del switch
                case 7:
                    System.out.println("Volviendo al menú principal...");
                    break; // Salir del switch y luego del bucle
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 7); // Repite el menú hasta que el usuario elija "Volver"
    }


    public int buscarVentaPorId(){
        int contador =0;
        System.out.println("Ingrese el id de la venta: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        for(Venta venta : ventas){
            contador ++;
            if(venta.getId().equals(id)){
                return contador -1;
            }
        }
        return 0;
    }

    public void editarVentaPorId() throws ParseException, IOException {
        int posicion = buscarVentaPorId();
        if (posicion == -1) {
            System.out.println("Venta no encontrada.");
            return; // Salir si no se encuentra la venta
        }

        int opcion;
        do {
            System.out.println("\n--- Editar Venta ---");
            System.out.println("1. Editar Comprador");
            System.out.println("2. Editar Vendedor");
            System.out.println("3. Editar Fecha");
            System.out.println("4. Editar Vehículo");
            System.out.println("5. Editar Monto");
            System.out.println("6. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el DNI del nuevo comprador: ");
                    String dniComprador = scanner.nextLine();
                    ventas.get(posicion).setComprador(gestor.obtenerCompradorPorDni(dniComprador));
                    System.out.println("Comprador actualizado correctamente.");
                }
                case 2 -> {
                    System.out.print("Ingrese el DNI del nuevo vendedor: ");
                    String dniVendedor = scanner.nextLine();
                    ventas.get(posicion).setVendedor(gestor.obtenerVendedorPorDni(dniVendedor));
                    System.out.println("Vendedor actualizado correctamente.");
                }
                case 3 -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    sdf.setLenient(false);
                    System.out.print("Ingrese la nueva fecha (formato yyyy-MM-dd): ");
                    String inputFecha = scanner.nextLine();
                    try {
                        Date nuevaFecha = sdf.parse(inputFecha);
                        ventas.get(posicion).setFechaVenta(nuevaFecha);
                        System.out.println("Fecha actualizada correctamente.");
                    } catch (ParseException e) {
                        System.out.println("Formato de fecha inválido. Intente nuevamente.");
                    }
                }
                case 4 -> {
                    System.out.print("Ingrese el ID del nuevo vehículo: ");
                    String idVehiculo = scanner.nextLine();
                    ventas.get(posicion).setVehiculo(gestor.obtenerVehiculoPorId(idVehiculo));
                    System.out.println("Vehículo actualizado correctamente.");
                }
                case 5 -> {
                    System.out.print("Ingrese el nuevo monto: ");
                    double nuevoMonto = scanner.nextDouble();
                    ventas.get(posicion).setMonto(nuevoMonto);
                    System.out.println("Monto actualizado correctamente.");
                }
                case 6 -> System.out.println("Volviendo al menú anterior...");
                default -> System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 6);

        // Guardar los cambios realizados en las ventas
        gestor.guardarVenta(ventas);
        System.out.println("Cambios guardados exitosamente.");
    }

    public int buscarVendedorPorDni(){
        int contador =0;
        System.out.println("Ingrese el DNI del vendedor a editar: ");
        String dni = scanner.nextLine();
        if(dni.length() != 8){
            System.out.println("El dni debe tener 8 números válidos");
        }else if(!dni.matches("1234567890")){
            System.out.println("El dni debe tener 8 números válidos");
        }else{
            for (Vendedor vendedor : vendedores){
                contador ++;
                if(vendedor.getDni() == dni){
                    return contador -1;
                }
            }

        }
        return 0;
    }

    public void editarVendedorPorDni() throws IOException {
        int posicion = buscarVendedorPorDni();

        if (posicion == -1) { // Validar si el vendedor existe
            System.out.println("Vendedor no encontrado.");
            return;
        }

        int opcion;
        do {
            System.out.println("\n--- Editar Vendedor ---");
            System.out.println("1. Editar Nombre");
            System.out.println("2. Editar Apellido");
            System.out.println("3. Editar Email");
            System.out.println("4. Editar Salario base");
            System.out.println("5. Editar Comisión");
            System.out.println("6. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    vendedores.get(posicion).setNombre(nuevoNombre);
                    System.out.println("Nombre actualizado correctamente.");
                }
                case 2 -> {
                    System.out.print("Ingrese el nuevo apellido: ");
                    String nuevoApellido = scanner.nextLine();
                    vendedores.get(posicion).setApellido(nuevoApellido);
                    System.out.println("Apellido actualizado correctamente.");
                }
                case 3 -> {
                    System.out.print("Ingrese el nuevo email: ");
                    String nuevoEmail = scanner.nextLine();
                    vendedores.get(posicion).setEmail(nuevoEmail);
                    System.out.println("Email actualizado correctamente.");
                }
                case 4 -> {
                    System.out.print("Ingrese el nuevo salario base: ");
                    double nuevoSalario = scanner.nextDouble();
                    vendedores.get(posicion).setSalarioBase(nuevoSalario);
                    System.out.println("Salario base actualizado correctamente.");
                }
                case 5 -> {
                    System.out.print("Ingrese la nueva comisión: ");
                    double nuevaComision = scanner.nextDouble();
                    vendedores.get(posicion).setComision(nuevaComision);
                    System.out.println("Comisión actualizada correctamente.");
                }
                case 6 -> System.out.println("Volviendo al menú anterior...");
                default -> System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 6);

        // Guardar los cambios realizados
        gestor.guardarVendedor(vendedores);
        System.out.println("Cambios guardados exitosamente.");
    }


    private void listarVehiculos() throws InterruptedException {
        System.out.println("--- Listar Vehiculos");
        for(Vehiculo vehiculo : vehiculos){
            System.out.println(vehiculo.toString());
        }
        Thread.sleep(5000);
    }

    private void listarVendedores() throws InterruptedException {
        System.out.println("--- Listar Vendedores");
        for (Vendedor vendedor : vendedores){
            System.out.println(vendedor.toString());
        }
        Thread.sleep(5000);
    }

    private void listarCompradores() throws InterruptedException {
        System.out.println("--- Listar Compradores");
        for (Comprador comprador : compradores){
            System.out.println(comprador.toString());
        }
        Thread.sleep(5000);

    }


    private void listarVentas() {
        System.out.println("--- Listar Ventas");
        System.out.println(this.ventas.toString());
    }

    private void agregarComprador() {
        Comprador comprador = new Comprador();
        System.out.println("\n--- Agregar Comprador ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        comprador.setNombre(nombre);
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        comprador.setApellido(apellido);
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        comprador.setDni(dni);
        scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        comprador.setEmail(email);
        System.out.print("Monto Disponible: ");
        double montoDisponible = scanner.nextDouble();
        comprador.setMontoDisponible(montoDisponible);
        scanner.nextLine();
        this.compradores.add(comprador);
        gestor.guardarComprador(compradores);
    }

    private void agregarVendedor() {
        Vendedor vendedor = new Vendedor();
        System.out.println("\n--- Agregar Vendedor ---");
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        vendedor.setNombre(nombre);
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        vendedor.setApellido(apellido);
        System.out.print("DNI: ");
        vendedor.setDni(scanner.nextLine());
        scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        vendedor.setEmail(email);
        System.out.print("Salario Base: ");
        double salarioBase = scanner.nextDouble();
        vendedor.setSalarioBase(salarioBase);
        scanner.nextLine();
        System.out.print("Comisión por Ventas: ");
        double comision = scanner.nextDouble();
        vendedor.setComisionPorVentas(comision);
        scanner.nextLine();

        this.vendedores.add(vendedor);
        gestor.guardarVendedor(vendedores);
    }

    private void agregarVehiculo() throws IOException {
        System.out.println("\n--- Agregar Vehículo ---");
        Vehiculo vehiculo = new Vehiculo();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        vehiculo.setId(id);
        scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        vehiculo.setModelo(modelo);
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        vehiculo.setMarca(marca);
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        vehiculo.setPrecio(precio);
        scanner.nextLine();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        vehiculo.setStock(stock);
        scanner.nextLine();

        this.vehiculos.add(vehiculo);
        gestor.guardarVehiculo(vehiculos);
    }

    private void agregarVenta() throws IOException {
        System.out.println("\n--- Agregar Venta ---");

        Venta venta = new Venta();
        Comprador comprador = new Comprador();
        Vendedor vendedor = new Vendedor();
        Vehiculo vehiculo = new Vehiculo();
        System.out.print("Ingrese el DNI del Comprador: ");
        String dniComprador = scanner.nextLine();

        // Buscar si el comprador con ese DNI ya existe
        comprador = gestor.obtenerCompradorPorDni(dniComprador);  // Método para buscar comprador por DNI

        // Si el comprador no existe, pedir los datos para crear uno nuevo
        if (comprador == null) {
            System.out.println("Comprador no encontrado. Ingrese los datos del comprador:");
            agregarComprador();// Obtener el último comprador agregado
        }
        comprador.setDni(dniComprador);
        // Solicitar los datos del vendedor
        System.out.println("Datos del Vendedor:");
        System.out.print("Ingrese el DNI del Vendedor: ");
        String dniVendedor = scanner.nextLine();
        vendedor = gestor.obtenerVendedorPorDni(dniVendedor);  // Método para buscar comprador por DNI
        if(vendedor == null){
            System.out.println("Vendedor no encontrado. Ingrese los datos del vendedor:");
            agregarVendedor();
            vendedor = vendedores.getLast();
        }

        // Solicitar los datos del vehículo
        System.out.println("Datos del Vehículo:");
        System.out.print("Ingrese el id del Vehiculo: ");
        String idVehiculo = scanner.nextLine();
        vehiculo = gestor.obtenerVehiculoPorId(idVehiculo);  // Método para buscar comprador por DNI
        if(vehiculo == null){
            System.out.println("Vehiculo no encontrado. Ingrese los datos:");
            agregarVehiculo();
            vehiculo = vehiculos.getLast();
        }else{
            vehiculo.setStock((vehiculo.getStock() - 1));
        }

        // Solicitar el monto de la venta
        System.out.print("Monto: ");
        double monto = scanner.nextDouble();
        venta.setMonto(monto);
        scanner.nextLine();  // Limpiar buffer


        // Crear la venta y agregarla
        venta.setFechaVenta(new Date());
        venta.setId((ventas.size() + 1));
        this.ventas.add(venta);
        gestor.guardarVenta(ventas);  // Guardar la venta

        System.out.println("Venta registrada correctamente.");
    }

    private void listaSegunPresupuesto() throws InterruptedException {
        System.out.println("Ingrese el presupuesto: ");
        Double presupuesto = scanner.nextDouble();
        gestor.mostrarAutosSegunPresupuesto(presupuesto);
        Thread.sleep(5000);
    }

}
