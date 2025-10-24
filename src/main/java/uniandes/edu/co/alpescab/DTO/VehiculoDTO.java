package uniandes.edu.co.alpescab.DTO;

public class VehiculoDTO {
    private Long id;
    private String placaVehiculo;
    private String marca;
    private String modelo;
    private String color;
    private Integer capacidad;
    private String tipo;
    private UsuarioDTO conductor;
    private CiudadDTO ciudadExpedicion;

    public VehiculoDTO(
            Long id,
            String placaVehiculo,
            String marca,
            String modelo,
            String color,
            Integer capacidad,
            String tipo,
            UsuarioDTO conductor,
            CiudadDTO ciudadExpedicion
    ) {
        this.id = id;
        this.placaVehiculo = placaVehiculo;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.capacidad = capacidad;
        this.tipo = tipo;
        this.conductor = conductor;
        this.ciudadExpedicion = ciudadExpedicion;
    }

    public Long getId() { return id; }
    public String getPlacaVehiculo() { return placaVehiculo; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getColor() { return color; }
    public Integer getCapacidad() { return capacidad; }
    public String getTipo() { return tipo; }
    public UsuarioDTO getConductor() { return conductor; }
    public CiudadDTO getCiudadExpedicion() { return ciudadExpedicion; }
}

