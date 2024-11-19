package tp_Final.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class Venta {
    private Integer id;
    private Comprador comprador;
    private Vendedor vendedor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha;

    private Vehiculo vehiculo;
    private Double monto;


    public Venta() {
    }

    public Venta(Integer idVenta, Comprador comprador, Vendedor vendedor, Date fechaVenta, Vehiculo vehiculo, Double monto) {
        this.id = idVenta;
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.fecha = fechaVenta;
        this.vehiculo = vehiculo;
        this.monto = monto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idVenta) {
        this.id = idVenta;
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

    public Date getFechaVenta() {
        return fecha;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fecha = fechaVenta;
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
                "idVenta=" + id + '\'' +
                ", comprador=" + comprador + '\'' +
                ", vendedor=" + vendedor + '\'' +
                ", fechaVenta=" + fecha + '\'' +
                ", vehiculo=" + vehiculo + '\'' +
                ", monto=" + monto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venta venta)) return false;
        return Objects.equals(id, venta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Double calcularDescuento(Double precioVenta, Integer descuento){
        return (precioVenta + (precioVenta * (descuento / 100)));
    }
}
