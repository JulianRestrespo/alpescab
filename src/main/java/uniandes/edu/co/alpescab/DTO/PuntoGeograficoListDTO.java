package uniandes.edu.co.alpescab.DTO;

import java.math.BigDecimal;

public class PuntoGeograficoListDTO {
    private Long id;
    private BigDecimal latitud;
    private BigDecimal longitud;
    private Long idCiudad;
    private String nombreCiudad;

    public PuntoGeograficoListDTO(Long id,
                                  BigDecimal latitud,
                                  BigDecimal longitud,
                                  Long idCiudad,
                                  String nombreCiudad) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
    }

    public Long getId() { return id; }
    public BigDecimal getLatitud() { return latitud; }
    public BigDecimal getLongitud() { return longitud; }
    public Long getIdCiudad() { return idCiudad; }
    public String getNombreCiudad() { return nombreCiudad; }
}

