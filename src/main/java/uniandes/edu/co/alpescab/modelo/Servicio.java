package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "SERVICIO")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String estado;        
    private String tipoServicio;  

    @ManyToOne
    private Usuario cliente;

    @ManyToOne
    private PuntoGeografico origen;

    public Servicio() {;}

    public Servicio(String estado, String tipoServicio, Usuario cliente, PuntoGeografico origen) {
        this.estado = estado;
        this.tipoServicio = tipoServicio;
        this.cliente = cliente;
        this.origen = origen;
    }

    public Long getId() { return id; }
    public String getEstado() { return estado; }
    public String getTipoServicio() { return tipoServicio; }
    public Usuario getCliente() { return cliente; }
    public PuntoGeografico getOrigen() { return origen; }

    public void setId(Long id) { this.id = id; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    public void setCliente(Usuario cliente) { this.cliente = cliente; }
    public void setOrigen(PuntoGeografico origen) { this.origen = origen; }
}
