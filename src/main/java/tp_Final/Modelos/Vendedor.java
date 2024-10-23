package tp_Final.Modelos;

public class Vendedor extends Persona{


    private String ventasRealizadas;
    private Double salarioBase;
    private Double comisionPorVentas;

    public Vendedor(Integer idPersona, String nombre, String apellido, Integer dni, String email, String ventasRealizadas, Double salarioBase, Double comisionPorVentas) {
        super(idPersona, nombre, apellido, dni, email);
        this.ventasRealizadas = ventasRealizadas;
        this.salarioBase = salarioBase;
        this.comisionPorVentas = comisionPorVentas;
    }

    public String getVentasRealizadas() {
        return ventasRealizadas;
    }

    public void setVentasRealizadas(String ventasRealizadas) {
        this.ventasRealizadas = ventasRealizadas;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Double getComisionPorVentas() {
        return comisionPorVentas;
    }

    public void setComisionPorVentas(Double comisionPorVentas) {
        this.comisionPorVentas = comisionPorVentas;
    }

    @Override
    public String ObtenerTipo() {
        return "";
    }
}
