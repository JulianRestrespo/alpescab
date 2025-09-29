package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "DESTINO")
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;
    private String descripcion;

    @ManyToOne
    private PuntoGeografico punto;

    public Destino() {;}

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
