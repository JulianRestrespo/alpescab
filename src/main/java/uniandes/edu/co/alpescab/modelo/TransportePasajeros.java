package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "TRANSPORTE_PASAJEROS")
public class TransportePasajeros {

    @Id
    private Long id;  

    @OneToOne
    private Servicio servicio;

    private String nivel;           
    private Integer numeroPasajeros;

    @ManyToOne
    private Destino destino;

    public TransportePasajeros() {;}

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
