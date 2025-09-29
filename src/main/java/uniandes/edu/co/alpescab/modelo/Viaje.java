package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "VIAJE")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Usuario conductor;

    @ManyToOne
    private Vehiculo vehiculo;

    private String horaInicio;     
    private String horaFin;         
    private BigDecimal distanciaKm;  
    private BigDecimal costoTotal;   

    @ManyToOne
    private Servicio servicio;       

    private BigDecimal gananciaConductor;  
    private LocalDate fecha;              

    public Viaje() {;}

    public Viaje(Usuario conductor, Vehiculo vehiculo, String horaInicio, String horaFin,
                 BigDecimal distanciaKm, BigDecimal costoTotal, Servicio servicio,
                 BigDecimal gananciaConductor, LocalDate fecha) {
        this.conductor = conductor;
        this.vehiculo = vehiculo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.distanciaKm = distanciaKm;
        this.costoTotal = costoTotal;
        this.servicio = servicio;
        this.gananciaConductor = gananciaConductor;
        this.fecha = fecha;
    }

    public Long getId() { return id; }
    public Usuario getConductor() { return conductor; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
    public BigDecimal getDistanciaKm() { return distanciaKm; }
    public BigDecimal getCostoTotal() { return costoTotal; }
    public Servicio getServicio() { return servicio; }
    public BigDecimal getGananciaConductor() { return gananciaConductor; }
    public LocalDate getFecha() { return fecha; }

    public void setId(Long id) { this.id = id; }
    public void setConductor(Usuario conductor) { this.conductor = conductor; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
    public void setDistanciaKm(BigDecimal distanciaKm) { this.distanciaKm = distanciaKm; }
    public void setCostoTotal(BigDecimal costoTotal) { this.costoTotal = costoTotal; }
    public void setServicio(Servicio servicio) { this.servicio = servicio; }
    public void setGananciaConductor(BigDecimal gananciaConductor) { this.gananciaConductor = gananciaConductor; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
}
