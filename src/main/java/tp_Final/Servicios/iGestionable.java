package tp_Final.Servicios;

import tp_Final.Modelos.Comprador;
import tp_Final.Modelos.Vehiculo;
import tp_Final.Modelos.Vendedor;
import tp_Final.Modelos.Venta;

import java.io.IOException;
import java.util.List;

public interface iGestionable {
    public void guardarVehiculo(List<Vehiculo> vehiculos) throws IOException;
    public void guardarComprador(List<Comprador> compradors);
    public void guardarVendedor(List<Vendedor> vendedors);
    public void guardarVenta(List<Venta> ventas);
    public List<Vendedor> leerVendedores(String nombreArchivo);
    public List<Comprador> leerCompradores(String nombreArchivo);
    public List<Vehiculo> leerVehiculos(String nombreArchivo);
    public List<Venta> leerVentas(String nombreArchivo);
}