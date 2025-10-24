package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TARJETA")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TARJETA_GEN")
    @SequenceGenerator(name = "SEQ_TARJETA_GEN", sequenceName = "SEQ_TARJETA", allocationSize = 1)
    @Column(name = "ID_TARJETA")
    private Long id;

    @Column(name = "NUMERO", nullable = false, length = 25)
    private String numero;

    @Column(name = "NOMBRE_TARJETA", nullable = false, length = 100)
    private String nombreTarjeta;

    @Column(name = "VENCIMIENTO", nullable = false)
    private LocalDate vencimiento;

    @Column(name = "CVV", nullable = false, length = 4)
    private String cvv;

    @OneToOne
    @JoinColumn(
        name = "ID_USUARIO",
        referencedColumnName = "ID_USUARIO",
        nullable = false,
        unique = true
    )
    private Usuario usuario;

    public Tarjeta() {}

    public Tarjeta(String numero, String nombreTarjeta, LocalDate vencimiento, String cvv, Usuario usuario) {
        this.numero = numero;
        this.nombreTarjeta = nombreTarjeta;
        this.vencimiento = vencimiento;
        this.cvv = cvv;
        this.usuario = usuario;
    }

    public Long getId() { return id; }
    public String getNumero() { return numero; }
    public String getNombreTarjeta() { return nombreTarjeta; }
    public LocalDate getVencimiento() { return vencimiento; }
    public String getCvv() { return cvv; }
    public Usuario getUsuario() { return usuario; }

    public void setId(Long id) { this.id = id; }
    public void setNumero(String numero) { this.numero = numero; }
    public void setNombreTarjeta(String nombreTarjeta) { this.nombreTarjeta = nombreTarjeta; }
    public void setVencimiento(LocalDate vencimiento) { this.vencimiento = vencimiento; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
