package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(
    name = "RESENA",
    uniqueConstraints = @UniqueConstraint(name = "UQ_RESENA_AUTOR_VIAJE", columnNames = {"ID_VIAJE","ID_AUTOR"})
)
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESENA_GEN")
    @SequenceGenerator(name = "SEQ_RESENA_GEN", sequenceName = "SEQ_RESENA", allocationSize = 1)
    @Column(name = "ID_RESENA")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_VIAJE", nullable = false)
    private Viaje viaje;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_AUTOR", nullable = false)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_OBJETIVO", nullable = false)
    private Usuario objetivo;

    @DecimalMin("0.0")
    @DecimalMax("5.0")
    @Column(name = "CALIFICACION", precision = 2, scale = 1, nullable = false)
    private BigDecimal calificacion;

    @Column(name = "COMENTARIO", length = 500, nullable = false)
    private String comentario;

    public Resena() {}

    public Resena(Viaje viaje, Usuario autor, Usuario objetivo,
                  BigDecimal calificacion, String comentario) {
        this.viaje = viaje;
        this.autor = autor;
        this.objetivo = objetivo;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public Long getId() { return id; }
    public Viaje getViaje() { return viaje; }
    public Usuario getAutor() { return autor; }
    public Usuario getObjetivo() { return objetivo; }
    public BigDecimal getCalificacion() { return calificacion; }
    public String getComentario() { return comentario; }

    public void setId(Long id) { this.id = id; }
    public void setViaje(Viaje viaje) { this.viaje = viaje; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    public void setObjetivo(Usuario objetivo) { this.objetivo = objetivo; }
    public void setCalificacion(BigDecimal calificacion) { this.calificacion = calificacion; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
