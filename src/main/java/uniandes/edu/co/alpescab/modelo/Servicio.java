package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "SERVICIO")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SERVICIO_GEN")
    @SequenceGenerator(name = "SEQ_SERVICIO_GEN", sequenceName = "SEQ_SERVICIO", allocationSize = 1)
    @Column(name = "ID_SERVICIO")
    private Long id;

    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado;

    @Column(name = "TIPO_SERVICIO", nullable = false, length = 15)
    private String tipoServicio;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_USUARIO", nullable = false)
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "ID_ORIGEN", referencedColumnName = "ID_PUNTO", nullable = false)
    private PuntoGeografico origen;

    public Servicio() { }

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
