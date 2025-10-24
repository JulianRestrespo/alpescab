package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "DESTINO")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_destino")
    @SequenceGenerator(name = "seq_destino", sequenceName = "SEQ_DESTINO", allocationSize = 1)
    @Column(name = "ID_DESTINO")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PUNTO", nullable = false)
    private PuntoGeografico punto;

    public Destino() { }

    public Destino(String nombre, String descripcion, PuntoGeografico punto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.punto = punto;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public PuntoGeografico getPunto() { return punto; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPunto(PuntoGeografico punto) { this.punto = punto; }
}
