package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RESENA")
public class Resena {

    @EmbeddedId
    private ResenaPK pk;

    @ManyToOne
    @JoinColumn(name = "ID_OBJETIVO", referencedColumnName = "id")
    private Usuario objetivo;

    private BigDecimal calificacion; 
    private String comentario;

    public Resena() {;}

    public Resena(Viaje viaje, Usuario autor, Usuario objetivo,
                  BigDecimal calificacion, String comentario) {
        this.pk = new ResenaPK(viaje, autor);
        this.objetivo = objetivo;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public ResenaPK getPk() { return pk; }
    public Usuario getObjetivo() { return objetivo; }
    public BigDecimal getCalificacion() { return calificacion; }
    public String getComentario() { return comentario; }

    public void setPk(ResenaPK pk) { this.pk = pk; }
    public void setObjetivo(Usuario objetivo) { this.objetivo = objetivo; }
    public void setCalificacion(BigDecimal calificacion) { this.calificacion = calificacion; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
