package tp_Final;

import tp_Final.Modelos.*;
import tp_Final.Servicios.Gestor;

import java.io.IOException;
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

    public void mostrarMenu() throws IOException {
        int opcion;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar Comprador");
            System.out.println("2. Listar Compradores");
            System.out.println("3. Agregar Vehículo");
            System.out.println("4. Listar Vehiculos");
            System.out.println("5. Mostrar Vehiculos según presupuesto");
            System.out.println("6. Agregar Vendedor");
            System.out.println("7. Listar Vendedores");
            System.out.println("8. Agregar Venta");
            System.out.println(("9. Listar Ventas"));
            System.out.println("10. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> agregarComprador();
                case 2 -> listarCompradores();
                case 3 -> agregarVehiculo();
                case 4 -> listarVehiculos();
                case 5 -> listaSegunPresupuesto();
                case 6 -> agregarVendedor();
                case 7 -> listarVendedores();
                case 8 -> agregarVenta();
                case 9 -> listarVentas();
                case 10 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (opcion != 9);
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
        int dni = scanner.nextInt();
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
        int dni = scanner.nextInt();
        vendedor.setDni(dni);
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
        comprador.setDni(Integer.valueOf(dniComprador));
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
