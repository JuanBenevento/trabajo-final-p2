package tp_Final.Modelos;

import tp_Final.Exepciones.*;
import tp_Final.Modelos.*;
import tp_Final.Servicios.*;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {
    private Gestor gestor = new Gestor();
    private Scanner scanner = new Scanner(System.in);
    private List<Comprador> compradores = new ArrayList<>();
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<Vehiculo> vehiculos = new ArrayList<>();
    private List<Venta> ventas = new ArrayList<>();

    public Menu() {
        this.compradores = gestor.leerCompradores("compradores.json");
        this.vendedores = gestor.leerVendedores("vendedores.json");
        this.vehiculos = gestor.leerVehiculos("vehiculos.json");
        this.ventas = gestor.leerVentas("ventas.json");
    }

    public void mostrarMenu() throws IOException, ParseException, InterruptedException, ElementoNoEncontradoExcepcion {
        int opcion;

        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Compradores");
            System.out.println("2. Vehículos");
            System.out.println("3. Ventas");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> menuCompradores();
                case 2 -> menuVehiculos();
                case 3 -> menuVentas();
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida, intenta de nuevo.");
            }
        } while (opcion != 4);
    }



    public void menuCompradores() throws InterruptedException, ElementoNoEncontradoExcepcion, IOException {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        int opcion = 0;
        do {
            System.out.println("\n--- Menú Compradores ---");
            System.out.println("1. Agregar Comprador");
            System.out.println("2. Listar Compradores");
            System.out.println("3. Editar Compradores");
            System.out.println("4. Eliminar Comprador");
            System.out.println("5. Volver");
            System.out.print("Elija una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarComprador();
                    break;
                case 2:
                    listarCompradores();
                    break;
                case 3:
                    editarCompradorPorDni();
                    break;
                case 4:
                    eliminarCompradorPorDni();
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 5);
    }

    private int buscarPersonaPorDni() {
        int contador =0;
        String dni = null;
        while(dni == null) {
            System.out.println("Ingrese el DNI del comprador a editar: ");
            dni = scanner.nextLine();
            if (dni.length() > 8 || dni.length() < 8) {
                System.out.println("El dni debe tener 8 números válidos");
                dni = null;
            } else if (!dni.matches("1234567890")) {
                System.out.println("El dni debe tener 8 números válidos");
                dni = null;
            }
            for (Comprador comprador : compradores){
                contador ++;
                if(comprador.getDni() == dni){
                    return contador -1;
                }
            }


        }

        return contador-1;
    }

    public void editarCompradorPorDni(){
        int posicion = buscarPersonaPorDni();
        int opcion = 0;

        if (posicion > 0) {
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Email");
            System.out.println("4. Monto Disponible");
            System.out.println("5. Volver");
            opcion = scanner.nextInt();
            scanner.nextLine();

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


    }
    public void menuVehiculos() throws IOException, InterruptedException, ElementoNoEncontradoExcepcion {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        int opcion = 0;
        do {
            System.out.println("\n--- Menú Vehículos ---");
            System.out.println("1. Agregar Vehículo");
            System.out.println("2. Listar Vehículos");
            System.out.println("3. Editar Vehículos");
            System.out.println("4. Eliminar Vehiculo");
            System.out.println("5. Mostrar Vehículos según presupuesto");
            System.out.println("6. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agregarVehiculo();
                    break;
                case 2:
                    listarVehiculos();
                    break;
                case 3:
                    editarVehiculoPorId();
                    break;
                case 4:
                    eliminarVehiculoPorId();
                    break;
                case 5:
                    listaSegunPresupuesto();
                case 6:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 6);
    }


    public void editarVehiculoPorId() throws IOException {
        int posicion = buscarVehiculoPorId();
        if (posicion == -1) {
            System.out.println("Vehículo no encontrado.");
            return;
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
            scanner.nextLine();

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


        gestor.guardarVehiculo(vehiculos);
        System.out.println("Cambios guardados exitosamente.");
    }


    public int buscarVehiculoPorId(){
        int contador = 0;
        System.out.println("Ingrese el ID del vehiculo: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Vehiculo vehiculo : vehiculos){
            contador ++;
            if(vehiculo.getId() == id){
                return contador -1;
            }
        }
        return 0;
    }

    public void menuVentas() throws IOException, ParseException, InterruptedException, ElementoNoEncontradoExcepcion {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        int opcion = 0;

        do {
            System.out.println("\n--- Menú Ventas ---");
            System.out.println("1. Listar Vendedores");
            System.out.println("2. Editar Vendedores");
            System.out.println("3. Agregar Vendedor");
            System.out.println("4. Eliminar Vendedor");
            System.out.println("5. Agregar Venta");
            System.out.println("6. Listar Ventas");
            System.out.println("7. Editar Ventas");
            System.out.println("8. Eliminar Venta");
            System.out.println("9. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    listarVendedores();
                    break;
                case 2:
                    editarVendedorPorDni();
                    break;
                case 3:
                    agregarVendedor();
                    break;
                case 4:
                    eliminarVendedorPorDni();
                    break;
                case 5:
                    agregarVenta();
                    break;
                case 6:
                    listarVentas();
                    break;
                case 7:
                    editarVentaPorId();
                    break;
                case 8:
                    eliminarVentaPorId();
                    break;
                case 9:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 9);
    }


    public int buscarVentaPorId(){
        int contador =0;
        System.out.println("Ingrese el id de la venta: ");
        int id = scanner.nextInt();
        scanner.nextLine();

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
            return;
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
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el DNI del comprador: ");
                    String dniComprador = scanner.nextLine();
                    Comprador comprador = gestor.obtenerCompradorPorDni(dniComprador);
                    if(comprador != null){
                        ventas.get(posicion).setComprador(comprador);
                        System.out.println("Comprador actualizado correctamente.");
                    }else{
                        System.out.println("No se encontró el comprador");
                        break;
                    }

                }
                case 2 -> {
                    System.out.print("Ingrese el DNI del vendedor: ");
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
                    Integer idVehiculo = scanner.nextInt();
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


        gestor.guardarVenta(ventas);
        System.out.println("Cambios guardados exitosamente.");
    }

    public int buscarVendedorPorDni(){
        int contador =0;
        String dni = null;
        while(dni == null) {
            System.out.println("Ingrese el DNI del vendedor a editar: ");
             dni = scanner.nextLine();
            if (dni.length() > 8 || dni.length() < 8) {
                System.out.println("El dni debe tener 8 números válidos");
            }
            if (!(dni.matches("^\\d{8}$"))) {
                System.out.println("El dni debe tener 8 números válidos");
            }
            for (Vendedor vendedor : vendedores) {
                contador++;
                if (vendedor.getDni() == dni) {
                    return contador - 1;
                }
            }


        }
        return 0;
    }

    public void editarVendedorPorDni() throws IOException {
        int posicion = buscarVendedorPorDni();

        if (posicion == -1) {
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
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    vendedores.get(posicion).setNombre(nuevoNombre);
                    System.out.println("Nombre actualizado correctamente.");
                    break;
                case 2:
                    System.out.print("Ingrese el nuevo apellido: ");
                    String nuevoApellido = scanner.nextLine();
                    vendedores.get(posicion).setApellido(nuevoApellido);
                    System.out.println("Apellido actualizado correctamente.");
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo email: ");
                    String nuevoEmail = scanner.nextLine();
                    vendedores.get(posicion).setEmail(nuevoEmail);
                    System.out.println("Email actualizado correctamente.");
                    break;
                case 4:
                    System.out.print("Ingrese el nuevo salario base: ");
                    double nuevoSalario = scanner.nextDouble();
                    vendedores.get(posicion).setSalarioBase(nuevoSalario);
                    System.out.println("Salario base actualizado correctamente.");
                    break;
                case 5:
                    System.out.print("Ingrese la nueva comisión: ");
                    double nuevaComision = scanner.nextDouble();
                    vendedores.get(posicion).setComision(nuevaComision);
                    System.out.println("Comisión actualizada correctamente.");
                    break;
                case 6:  System.out.println("Volviendo al menú anterior...");
                default: System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        } while (opcion != 6);

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
        if(dni.length() < 8 || dni.length() > 8){
            System.out.println("DNI invalido.");
        }
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

        comprador = gestor.obtenerCompradorPorDni(dniComprador);


        if (comprador == null) {
            System.out.println("Comprador no encontrado. Ingrese los datos del comprador:");
            agregarComprador();
        }
        venta.setComprador(gestor.obtenerCompradorPorDni(dniComprador));

        System.out.println("Datos del Vendedor:");
        System.out.print("Ingrese el DNI del Vendedor: ");
        vendedor = gestor.obtenerVendedorPorDni(scanner.nextLine());
        if(vendedor == null){
            System.out.println("Vendedor no encontrado. Ingrese los datos del vendedor:");
            agregarVendedor();
            vendedor = vendedores.getLast();
        }
        venta.setVendedor(vendedor);
        System.out.println("Datos del Vehículo:");
        System.out.print("Ingrese el id del Vehiculo: ");
        Integer idVehiculo = scanner.nextInt();
        vehiculo = gestor.obtenerVehiculoPorId(idVehiculo);
        if(vehiculo == null){
            System.out.println("Vehiculo no encontrado. Ingrese los datos:");
            agregarVehiculo();
            vehiculo = vehiculos.getLast();
        }else{
            vehiculo.setStock((vehiculo.getStock() - 1));
        }
        venta.setVehiculo(vehiculo);

        System.out.print("Monto: ");
        double monto = scanner.nextDouble();
        venta.setMonto(monto);
        scanner.nextLine();



        venta.setFechaVenta(new Date());
        venta.setId((ventas.size() + 1));
        this.ventas.add(venta);
        gestor.guardarVenta(ventas);
        System.out.println("Venta registrada correctamente.");
    }

    private void listaSegunPresupuesto() throws InterruptedException {
        System.out.println("Ingrese el presupuesto: ");
        Double presupuesto = scanner.nextDouble();
        gestor.mostrarAutosSegunPresupuesto(presupuesto);
        Thread.sleep(5000);
    }

    public void eliminarCompradorPorDni() throws ElementoNoEncontradoExcepcion, IOException {
        System.out.print("Ingrese el DNI del comprador a eliminar: ");
        String dni = scanner.nextLine();

        Comprador comprador = compradores.stream()
                .filter(c -> c.getDni().equals(dni))
                .findFirst()
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion("No se encontró un comprador con el DNI: " + dni));

        compradores.remove(comprador);
        gestor.guardarComprador(compradores);
        System.out.println("Comprador eliminado correctamente.");
    }

    public void eliminarVendedorPorDni() throws ElementoNoEncontradoExcepcion, IOException {
        System.out.print("Ingrese el DNI del vendedor a eliminar: ");
        String dni = scanner.nextLine();

        Vendedor vendedor = vendedores.stream()
                .filter(v -> v.getDni().equals(dni))
                .findFirst()
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion("No se encontró un vendedor con el DNI: " + dni));

        vendedores.remove(vendedor);
        gestor.guardarVendedor(vendedores);
        System.out.println("Vendedor eliminado correctamente.");
    }

    public void eliminarVehiculoPorId() throws ElementoNoEncontradoExcepcion, IOException {
        System.out.print("Ingrese el ID del vehículo a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Vehiculo vehiculo = vehiculos.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion("No se encontró un vehículo con el ID: " + id));

        vehiculos.remove(vehiculo);
        gestor.guardarVehiculo(vehiculos);
        System.out.println("Vehículo eliminado correctamente.");
    }

    public void eliminarVentaPorId() throws ElementoNoEncontradoExcepcion, IOException {
        System.out.print("Ingrese el ID de la venta a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Venta venta = ventas.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ElementoNoEncontradoExcepcion("No se encontró una venta con el ID: " + id));

        ventas.remove(venta);
        gestor.guardarVenta(ventas);
        System.out.println("Venta eliminada correctamente.");
    }


}
