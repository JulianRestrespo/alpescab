package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "TRANSPORTE_PASAJEROS")
public class TransportePasajeros {

    // La PK es ID_SERVICIO (compartida con Servicio)
    @Id
    @Column(name = "ID_SERVICIO")
    private Long id;

    @OneToOne(optional = false)
    @MapsId                               // comparte la PK con Servicio
    @JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID_SERVICIO")
    private Servicio servicio;

    @Column(name = "NIVEL", nullable = false, length = 20)
    private String nivel;                 // 'Estandar' | 'Comfort' | 'Large'

    @Column(name = "NUMERO_PASAJEROS", nullable = false)
    private Integer numeroPasajeros;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_DESTINO", referencedColumnName = "ID_DESTINO")
    private Destino destino;

    public TransportePasajeros() {}

    public TransportePasajeros(Servicio servicio, String nivel, Integer numeroPasajeros, Destino destino) {
        this.servicio = servicio;
        this.nivel = nivel;
        this.numeroPasajeros = numeroPasajeros;
        this.destino = destino;
    }

    public Long getId() { return id; }
    public Servicio getServicio() { return servicio; }
    public String getNivel() { return nivel; }
    public Integer getNumeroPasajeros() { return numeroPasajeros; }
    public Destino getDestino() { return destino; }

    public void setId(Long id) { this.id = id; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public void setNumeroPasajeros(Integer numeroPasajeros) { this.numeroPasajeros = numeroPasajeros; }
    public void setDestino(Destino destino) { this.destino = destino; }
}
