package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "MERCANCIA")
public class Mercancia {

    @Id
    @Column(name = "ID_SERVICIO")
    private Long id; 

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "ID_SERVICIO", nullable = false)
    private Servicio servicio;

    @Column(name = "DESCRIPCION_PRODUCTO", nullable = false, length = 200)
    private String descripcionProducto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_DESTINO", nullable = false)
    private Destino destino;

    public Mercancia() {}

    public Mercancia(Servicio servicio, String descripcionProducto, Destino destino) {
        this.servicio = servicio;
        this.descripcionProducto = descripcionProducto;
        this.destino = destino;
    }

    public Long getId() { return id; }
    public Servicio getServicio() { return servicio; }
    public String getDescripcionProducto() { return descripcionProducto; }
    public Destino getDestino() { return destino; }

    public void setId(Long id) { this.id = id; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
    public void setDescripcionProducto(String descripcionProducto) { this.descripcionProducto = descripcionProducto; }
    public void setDestino(Destino destino) { this.destino = destino; }
}
