package tp_Final.Modelos;

public class Comprador extends Persona {
    private Double montoDisponible;

    public Comprador(Integer idPersona, String nombre, String apellido, Integer dni, String email, Double montoDisponible) {
        super(idPersona, nombre, apellido, dni, email);
        this.montoDisponible = montoDisponible;
    }

    public Double getMontoDisponible() {
        return montoDisponible;
    }

    public void setMontoDisponible(Double montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    @Override
    public String ObtenerTipo() {
        return "";
    }
}
