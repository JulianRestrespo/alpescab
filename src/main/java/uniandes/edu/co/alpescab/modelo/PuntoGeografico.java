package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PUNTO_GEOGRAFICO")
public class PuntoGeografico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal latitud;
    private BigDecimal longitud;

    @ManyToOne
    private Ciudad ciudad;

    public PuntoGeografico() {;}

    public PuntoGeografico(BigDecimal latitud, BigDecimal longitud, Ciudad ciudad) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.ciudad = ciudad;
    }

    public Long getId() { return id; }
    public BigDecimal getLatitud() { return latitud; }
    public BigDecimal getLongitud() { return longitud; }
    public Ciudad getCiudad() { return ciudad; }

    public void setId(Long id) { this.id = id; }
    public void setLatitud(BigDecimal latitud) { this.latitud = latitud; }
    public void setLongitud(BigDecimal longitud) { this.longitud = longitud; }
    public void setCiudad(Ciudad ciudad) { this.ciudad = ciudad; }
}
