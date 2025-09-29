package uniandes.edu.co.alpescab.modelo;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ResenaPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ID_VIAJE", referencedColumnName = "id")
    private Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "ID_AUTOR", referencedColumnName = "id")
    private Usuario autor;

    public ResenaPK() {;}

    public ResenaPK(Viaje viaje, Usuario autor) {
        this.viaje = viaje;
        this.autor = autor;
    }

    public Viaje getViaje() { return viaje; }
    public Usuario getAutor() { return autor; }

    public void setViaje(Viaje viaje) { this.viaje = viaje; }
    public void setAutor(Usuario autor) { this.autor = autor; }
}
