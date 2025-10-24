package uniandes.edu.co.alpescab.DTO;

import java.math.BigDecimal;

public class PuntoGeograficoCreateDTO {

    private BigDecimal latitud;
    private BigDecimal longitud;
    private Long idCiudad;

    public PuntoGeograficoCreateDTO() { }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }
}
