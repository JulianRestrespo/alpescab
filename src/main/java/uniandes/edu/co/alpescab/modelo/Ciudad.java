package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "CIUDAD")
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ciudad")
    @SequenceGenerator(name = "seq_ciudad", sequenceName = "SEQ_CIUDAD", allocationSize = 1)
    @Column(name = "ID_CIUDAD")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    public Ciudad() { }

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public String getNombre() { return nombre; }

    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
