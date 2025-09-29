package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "ENTREGA_COMIDA")
public class EntregaComida {

    @Id
    private Long id;  

    @OneToOne
    private Servicio servicio;

    private String restaurante;

    @ManyToOne
    private PuntoGeografico puntoEntrega;

    public EntregaComida() {;}

    public EntregaComida(Servicio servicio, String restaurante, PuntoGeografico puntoEntrega) {
        this.servicio = servicio;
        this.restaurante = restaurante;
        this.puntoEntrega = puntoEntrega;
    }

    public Long getId() { return id; }
    public Servicio getServicio() { return servicio; }
    public String getRestaurante() { return restaurante; }
    public PuntoGeografico getPuntoEntrega() { return puntoEntrega; }

    public void setId(Long id) { this.id = id; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
    public void setRestaurante(String restaurante) { this.restaurante = restaurante; }
    public void setPuntoEntrega(PuntoGeografico puntoEntrega) { this.puntoEntrega = puntoEntrega; }
}
