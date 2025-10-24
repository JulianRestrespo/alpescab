package uniandes.edu.co.alpescab.DTO;

import java.math.BigDecimal;

public class PuntoGeograficoDTO {
    private Long id;
    private BigDecimal latitud;
    private BigDecimal longitud;
    private CiudadDTO ciudad; 

    public PuntoGeograficoDTO() {}

    public PuntoGeograficoDTO(Long id, BigDecimal latitud, BigDecimal longitud, CiudadDTO ciudad) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ciudad = ciudad;
    }

    public Long getId() { return id; }
    public BigDecimal getLatitud() { return latitud; }
    public BigDecimal getLongitud() { return longitud; }
    public CiudadDTO getCiudad() { return ciudad; }

    public void setId(Long id) { this.id = id; }
    public void setLatitud(BigDecimal latitud) { this.latitud = latitud; }
    public void setLongitud(BigDecimal longitud) { this.longitud = longitud; }
    public void setCiudad(CiudadDTO ciudad) { this.ciudad = ciudad; }
}
