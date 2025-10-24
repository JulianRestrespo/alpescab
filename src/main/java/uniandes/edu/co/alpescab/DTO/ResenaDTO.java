package uniandes.edu.co.alpescab.DTO;

import java.math.BigDecimal;

public class ResenaDTO {

    private Long id;
    private Long idViaje;

    private Long idAutor;
    private String nombreAutor;

    private Long idObjetivo;
    private String nombreObjetivo;

    private BigDecimal calificacion;
    private String comentario;

    public ResenaDTO(
        Long id,
        Long idViaje,
        Long idAutor,
        String nombreAutor,
        Long idObjetivo,
        String nombreObjetivo,
        BigDecimal calificacion,
        String comentario
    ) {
        this.id = id;
        this.idViaje = idViaje;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.idObjetivo = idObjetivo;
        this.nombreObjetivo = nombreObjetivo;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public Long getId() { return id; }
    public Long getIdViaje() { return idViaje; }
    public Long getIdAutor() { return idAutor; }
    public String getNombreAutor() { return nombreAutor; }
    public Long getIdObjetivo() { return idObjetivo; }
    public String getNombreObjetivo() { return nombreObjetivo; }
    public BigDecimal getCalificacion() { return calificacion; }
    public String getComentario() { return comentario; }
}
