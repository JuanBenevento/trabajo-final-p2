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

    public void mostrarMenu() throws IOException, ParseException {
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



    public void menuCompradores() {
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
    public void menuVehiculos() throws IOException {
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
        int opcion = 0;
        System.out.println("Seleccion la opcion a editar: ");
        System.out.println("1. Modelo");
        System.out.println("2. Marca");
        System.out.println("3. Precio");
        System.out.println("4. Stock");
        System.out.println("5. Volver");
        opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion){
            case 1: vehiculos.get(posicion).setModelo(scanner.nextLine());
            case 2:vehiculos.get(posicion).setMarca(scanner.nextLine());
            case 3:vehiculos.get(posicion).setPrecio(scanner.nextDouble());
            case 4:vehiculos.get(posicion).setStock(scanner.nextInt());
            case 5:    break;
        }

        gestor.guardarVehiculo(vehiculos);

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

    public void menuVentas() throws IOException, ParseException {
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

    public void editarVentaPorId() throws ParseException {
        int posicion = buscarVentaPorId();
        int opcion = 0;
        System.out.println("Elija una opcion para editar");
        System.out.println("1. Comprador");
        System.out.println("2. Vendedor");
        System.out.println("3. Fecha");
        System.out.println("4. Vehiculo");
        System.out.println("5. Monto");
        System.out.println("6. Volver");
        opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion){
            case 1:
                System.out.println("Ingrese el dni del nuevo comprador: ");
                ventas.get(buscarVentaPorId()).setComprador(gestor.obtenerCompradorPorDni(scanner.nextLine()));
            case 2:
                System.out.println("Ingrese el dni del nuevo vendedor: ");
                ventas.get(buscarVentaPorId()).setVendedor(gestor.obtenerVendedorPorDni(scanner.nextLine()));
            case 3:
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String inputFecha  = scanner.nextLine();
                Date nuevaFecha = sdf.parse(inputFecha);
                ventas.get(buscarVentaPorId()).setFechaVenta(nuevaFecha);
            case 4:
                System.out.println("Ingrese el id del nuevo vehiculo");
                ventas.get(buscarVentaPorId()).setVehiculo(gestor.obtenerVehiculoPorId(String.valueOf(scanner.nextInt())));
            case 5:
                System.out.println("Ingrese el nuevo monto: ");
                ventas.get(buscarVentaPorId()).setMonto(scanner.nextDouble());
            case 6: break;
        }

        gestor.guardarVenta(ventas);

    }
    public int buscarVendedorPorDni(){
        int contador =0;
        System.out.println("Ingrese el DNI del vendedor a editar: ");
        String dni = scanner.nextLine();
        if(dni.length() != 8){
            System.out.println("El dni debe tener 8 números válidos");
        }else if(!dni.matches("1234567890")){
            System.out.println("El dni debe tener 8 números válidos");
        }

        for (Vendedor vendedor : vendedores){
            contador ++;
            if(vendedor.getDni() == dni){
                return contador -1;
            }
        }

        return contador-1;
    }

    public void editarVendedorPorDni(){
        int posicion = buscarVendedorPorDni();
        int opcion = 0;
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Email");
        System.out.println("4. Salario base");
        System.out.println("5. Comision");
        System.out.println("6. Volver");
        opcion =scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion){
            case 1:
                vendedores.get(posicion).setNombre(scanner.nextLine());
                break;
            case 2:
                vendedores.get(posicion).setApellido(scanner.nextLine());
                break;
            case 3:
                vendedores.get(posicion).setEmail(scanner.nextLine());
                break;
            case 4:
                vendedores.get(posicion).setSalarioBase(scanner.nextDouble());
                break;
            case 5: vendedores.get(posicion).setComision(scanner.nextDouble());
                break;
            case 6: break;
        }
        gestor.guardarVendedor(vendedores);

    }

    private void listarVehiculos() {
        System.out.println("--- Listar Vehiculos");
        for(Vehiculo vehiculo : vehiculos){
            System.out.println(vehiculo.toString());
        }
    }

    private void listarVendedores() {
        System.out.println("--- Listar Vendedores");
        for (Vendedor vendedor : vendedores){
            System.out.println(vendedor.toString());
        }
    }

    private void listarCompradores() {
        System.out.println("--- Listar Compradores");
        for (Comprador comprador : compradores){
            System.out.println(comprador.toString());
        }
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

    private void listaSegunPresupuesto(){
        System.out.println("Ingrese el presupuesto: ");
        Double presupuesto = scanner.nextDouble();
        gestor.mostrarAutosSegunPresupuesto(presupuesto);
    }

}
