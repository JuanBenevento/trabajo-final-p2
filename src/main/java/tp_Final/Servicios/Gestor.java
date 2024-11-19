package tp_Final.Servicios;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tp_Final.Modelos.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gestor implements iGestionable {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void guardarVehiculo(List<Vehiculo> vehiculos) throws IOException {
        String nombreArchivo = "vehiculos.json";

        try {
            objectMapper.writeValue(new File(nombreArchivo), vehiculos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void guardarComprador(List<Comprador> compradores) {
        String nombreArchivo = "compradores.json";

        try {
            objectMapper.writeValue(new File(nombreArchivo), compradores);
        } catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void guardarVendedor(List<Vendedor> vendedores) {
        String nombreArchivo = "vendedores.json";

        try{
            objectMapper.writeValue(new File(nombreArchivo), vendedores);
        } catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void guardarVenta(List<Venta> ventas) {
        String nombreArchivo = "ventas.json";

        try{
            objectMapper.writeValue(new File(nombreArchivo), ventas);
        } catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public List<Vendedor> leerVendedores(String nombreArchivo) {
        List<Vendedor> vendedores = new ArrayList<>();
        try {
            vendedores =  objectMapper.readValue(new File(nombreArchivo), new TypeReference<List<Vendedor>>() {});
            System.out.println("Datos leídos desde el archivo: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return vendedores;
    }

    public List<Comprador> leerCompradores(String nombreArchivo) {
        List<Comprador> compradores = new ArrayList<>();
        try {
            compradores =  objectMapper.readValue(new File(nombreArchivo), new TypeReference<List<Comprador>>(){});
            System.out.println("Datos leídos desde el archivo: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return compradores;
    }

    public List<Vehiculo> leerVehiculos(String nombreArchivo) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try {
            vehiculos = objectMapper.readValue(new File(nombreArchivo), new TypeReference<List<Vehiculo>>() {});
            System.out.println("Datos leídos desde el archivo: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return vehiculos;
    }

    public List<Venta> leerVentas(String nombreArchivo) {
        List<Venta> ventas = new ArrayList<>();
        try {
            ventas = objectMapper.readValue(new File(nombreArchivo), new TypeReference<List<Venta>>() {});
            System.out.println("Datos leídos desde el archivo: " + nombreArchivo);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return ventas;
    }

    public Comprador obtenerCompradorPorDni(String dni) {
        List<Comprador> compradores = leerCompradores("compradores.json");
        for(Comprador comprador : compradores){
            if(comprador.getDni().equals(dni)){
                return comprador;
            }
        }
        return null;
    }


    public Vendedor obtenerVendedorPorDni(String dni){
        List<Vendedor> vendedores = leerVendedores("vendedores.json");
        for(Vendedor vendedor : vendedores){
            if(vendedor.getDni().equals(dni)){
                return vendedor;
            }
        }
        return null;
    }

   public Vehiculo obtenerVehiculoPorId(Integer id){
       List<Vehiculo> vehiculos = leerVehiculos("vehiculos.json");
       for(Vehiculo vehiculo : vehiculos){
           if(vehiculo.getId().equals(id)){
               return vehiculo;
           }
       }
       return null;
   }

   public void eliminarVehiculo(int id){
       List<Vehiculo> vehiculos = leerVehiculos("vehiculos.json");
       int contador = 0;
       for(Vehiculo vehiculo : vehiculos){
           contador ++;
           if(vehiculo.getId().equals(id)){
               contador--;
               break;

           }
       }
       if(contador>0){
           vehiculos.remove(contador);
       }
   }
    public void eliminarVendedor(String dni){
        List<Vendedor> vendedores = leerVendedores("vendedores.json");
        int contador = 0;
        for(Vendedor vendedor : vendedores){
            contador ++;
            if(vendedor.getDni().equals(dni)){
                contador--;
                break;

            }
        }
        if(contador>0){
            vendedores.remove(contador);
        }
    }
    public void eliminarComprador(String dni){
        List<Comprador> compradores = leerCompradores("compradores.json");
        int contador = 0;
        for(Comprador comprador : compradores){
            contador ++;
            if(comprador.getDni().equals(dni)){
                contador--;
                break;

            }
        }
        if(contador>0){
            compradores.remove(contador);
        }
    }
    public void eliminarVenta(Integer id){
        List<Venta> ventas = leerVentas("ventas.json");
        int contador = 0;
        for(Venta venta : ventas){
            contador ++;
            if(venta.getId().equals(id)){
                contador--;
                break;

            }
        }
        if(contador>0){
            ventas.remove(contador);
        }
    }

   public void mostrarAutosSegunPresupuesto(Double presupuesto){
        List<Vehiculo> listaVehiculos = leerVehiculos("vehiculos.json");
        for(Vehiculo vehiculo : listaVehiculos){
            if(vehiculo.getPrecio() <=presupuesto){
                System.out.println(vehiculo.toString());
            }
        }

   }


}

