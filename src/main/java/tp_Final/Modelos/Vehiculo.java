package tp_Final.Modelos;

public class Vehiculo {
    private Integer id;
    private String modelo;
    private String marca;
    private Double precio;
    private Integer stock;

    public Vehiculo(Integer id, String modelo, String marca, Double precio, Integer stock) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public Vehiculo() {
    }

    public Vehiculo(Vehiculo vehiculo){
        this.id = vehiculo.id;
        this.modelo = vehiculo.modelo;
        this.marca = vehiculo.marca;
        this.precio = vehiculo.precio;
        this.stock = vehiculo.stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
