package tp_Final;

import tp_Final.Modelos.*;
import tp_Final.Servicios.Gestor;
import tp_Final.Servicios.iGestionable;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class App{
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}