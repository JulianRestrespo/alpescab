package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "VIAJE")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_VIAJE_GEN")
    @SequenceGenerator(name = "SEQ_VIAJE_GEN", sequenceName = "SEQ_VIAJE", allocationSize = 1)
    @Column(name = "ID_VIAJE")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CONDUCTOR", referencedColumnName = "ID_USUARIO", nullable = false)
    private Usuario conductor;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO", referencedColumnName = "ID_VEHICULO", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "HORA_INICIO", nullable = false, length = 5)
    private String horaInicio; // formato "HH:MM"

    @Column(name = "HORA_FIN", nullable = false, length = 5)
    private String horaFin;    // formato "HH:MM"

    @Column(name = "DISTANCIA_KM", nullable = false, precision = 9, scale = 3)
    private BigDecimal distanciaKm;

    @Column(name = "COSTO_TOTAL", nullable = false, precision = 12, scale = 2)
    private BigDecimal costoTotal;

    // En la tabla VIAJE, ID_SERVICIO es UNIQUE y FK -> SERVICIO(ID_SERVICIO),
    // por lo que la relación es 1:1 desde VIAJE hacia SERVICIO.
    @OneToOne
    @JoinColumn(name = "ID_SERVICIO", referencedColumnName = "ID_SERVICIO", nullable = false, unique = true)
    private Servicio servicio;

    @Column(name = "GANANCIA_CONDUCTOR", nullable = false, precision = 12, scale = 2)
    private BigDecimal gananciaConductor;

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    public Viaje() {}

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
