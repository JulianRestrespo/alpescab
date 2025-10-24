package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "VEHICULO")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VEHICULO_GEN")
    @SequenceGenerator(name = "SEQ_VEHICULO_GEN", sequenceName = "SEQ_VEHICULO", allocationSize = 1)
    @Column(name = "ID_VEHICULO")
    private Long id;

    @Column(name = "PLACA_VEHICULO", nullable = false, length = 10, unique = true)
    private String placaVehiculo;

    @Column(name = "TIPO", nullable = false, length = 20)
    private String tipo; // DB tiene CHECK ('carro','camioneta','motocicleta','van')

    @Column(name = "MARCA", nullable = false, length = 50)
    private String marca;

    @Column(name = "MODELO", nullable = false, length = 50)
    private String modelo;

    @Column(name = "COLOR", nullable = false, length = 30)
    private String color;

    @Column(name = "CAPACIDAD", nullable = false)
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(name = "CIUDAD_EXPEDICION", referencedColumnName = "ID_CIUDAD")
    private Ciudad ciudadExpedicion;

    @ManyToOne
    @JoinColumn(name = "ID_CONDUCTOR", referencedColumnName = "ID_USUARIO", nullable = false)
    private Usuario conductor;

    public Vehiculo() {}

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
