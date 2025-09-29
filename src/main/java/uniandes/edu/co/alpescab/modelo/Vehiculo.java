package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "VEHICULO")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String placaVehiculo;
    private String tipo;
    private String marca;
    private String modelo;
    private String color;
    private Integer capacidad;

    @ManyToOne
    private Ciudad ciudadExpedicion;

    @ManyToOne
    private Usuario conductor;

    public Vehiculo() {;}

    public Vehiculo(String placaVehiculo, String tipo, String marca, String modelo, String color,
                    Integer capacidad, Ciudad ciudadExpedicion, Usuario conductor) {
        this.placaVehiculo = placaVehiculo;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.capacidad = capacidad;
        this.ciudadExpedicion = ciudadExpedicion;
        this.conductor = conductor;
    }

    public Long getId() { return id; }
    public String getPlacaVehiculo() { return placaVehiculo; }
    public String getTipo() { return tipo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
    public Integer getCapacidad() { return capacidad; }
    public Ciudad getCiudadExpedicion() { return ciudadExpedicion; }
    public Usuario getConductor() { return conductor; }

    public void setId(Long id) { this.id = id; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setColor(String color) { this.color = color; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    public void setCiudadExpedicion(Ciudad ciudadExpedicion) { this.ciudadExpedicion = ciudadExpedicion; }
    public void setConductor(Usuario conductor) { this.conductor = conductor; }
}
