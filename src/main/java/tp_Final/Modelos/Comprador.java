package tp_Final.Modelos;

import java.util.Objects;

public class Comprador extends Persona {
    private String tipoPersona;
    private Double montoDisponible;


    public Comprador() {
    }

    public Comprador(Persona persona, Double montoDisponible) {
        super(persona);
        this.montoDisponible = montoDisponible;
        this.tipoPersona = "Comprador";
    }

    public Double getMontoDisponible() {
        return montoDisponible;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public void setMontoDisponible(Double montoDisponible) {
        this.montoDisponible = montoDisponible;
    }

    @Override
    public String toString() {
        return "Comprador{" +
                super.toString()+
                "tipoPersona='" + tipoPersona + '\'' +
                ", montoDisponible=" + montoDisponible +
                '}';
    }


}
