package tp_Final.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;
@JsonIgnoreProperties({"tipoPersona"})
public class Vendedor extends Persona {
    private String tipoPersona;
    private Double salarioBase;
    private Double comision;


    public Vendedor() {
    }

    public Vendedor(Persona persona, Double comisionPorVentas) {
        super(persona);
        this.salarioBase = salarioBase;
        this.comision = comisionPorVentas;
        this.tipoPersona = "Vendedores";
    }


    public void setComision(Double comision) {
        this.comision = comision;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Double getComisionPorVentas() {
        return comision;
    }

    public void setComisionPorVentas(Double comisionPorVentas) {
        this.comision = comisionPorVentas;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                super.toString() +
                "tipoPersona='" + tipoPersona + '\'' +
                ", salarioBase=" + salarioBase +
                ", comision=" + comision +
                '}';
    }
}
