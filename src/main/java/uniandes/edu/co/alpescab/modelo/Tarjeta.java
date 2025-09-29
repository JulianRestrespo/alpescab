package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TARJETA")
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numero;
    private String nombreTarjeta;
    private LocalDate vencimiento;
    private String cvv;

    @OneToOne
    private Usuario usuario;

    public Tarjeta() {;}

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
