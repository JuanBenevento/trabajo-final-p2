package tp_Final;

import tp_Final.Modelos.Comprador;
import tp_Final.Modelos.Vehiculo;
import tp_Final.Modelos.Vendedor;
import tp_Final.Modelos.Venta;
import tp_Final.Servicios.Gestor;
import tp_Final.Servicios.iGestionable;

import java.io.IOException;
import java.util.Date;

public class App{
    public static void main(String[] args) throws IOException {
        tp_Final.Menu menu = new tp_Final.Menu();
        menu.mostrarMenu();
    }
}