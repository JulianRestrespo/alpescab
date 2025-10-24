package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "PUNTO_GEOGRAFICO")
public class PuntoGeografico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PUNTO_GEN")
    @SequenceGenerator(name = "SEQ_PUNTO_GEN", sequenceName = "SEQ_PUNTO", allocationSize = 1)
    @Column(name = "ID_PUNTO")
    private Long id;

    @Column(name = "LATITUD", precision = 9, scale = 6, nullable = false)
    private BigDecimal latitud;

    @Column(name = "LONGITUD", precision = 9, scale = 6, nullable = false)
    private BigDecimal longitud;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CIUDAD", nullable = false)
    @JsonProperty("ciudad")
    private Ciudad ciudad;

    public PuntoGeografico() {}

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
    public void setCiudad(Ciudad ciudad) {
    this.ciudad = ciudad;
}

}
