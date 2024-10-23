package tp_Final.Modelos;

public class Venta {
    private Integer id_venta;
    private Comprador comprador;
    private Vendedor vendedor;
    private Date fecha_venta;
    private Vehiculo vehiculo;
    private Double monto;

    public Venta(Integer id_venta, Comprador comprador, Vendedor vendedor, Date fecha_venta, Vehiculo vehiculo, Double monto) {
        this.id_venta = id_venta;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.fecha_venta = fecha_venta;
        this.vehiculo = vehiculo;
        this.monto = monto;
    }

    public Venta() {
    }

    public Integer getId_venta() {
        return id_venta;
    }

    public void setId_venta(Integer id_venta) {
        this.id_venta = id_venta;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id_venta=" + id_venta +
                ", comprador=" + comprador +
                ", vendedor=" + vendedor +
                ", fecha_venta=" + fecha_venta +
                ", vehiculo=" + vehiculo +
                ", monto=" + monto +
                '}';
    }
}
