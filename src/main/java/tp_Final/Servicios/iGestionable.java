package tp_Final.Servicios;

public interface iGestionable {
         void agregar(Object T);
        void eliminar(Object T);
        void imprimir(Object T);
        void guardarEnArchivo(String nombreArchivo);
        void leerDesdeArchivo(String nombreArchivo);
}