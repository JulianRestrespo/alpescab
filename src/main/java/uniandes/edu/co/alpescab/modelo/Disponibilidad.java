package uniandes.edu.co.alpescab.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "DISPONIBILIDAD")
public class Disponibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_disponibilidad")
    @SequenceGenerator(name = "seq_disponibilidad", sequenceName = "SEQ_DISPONIBILIDAD", allocationSize = 1)
    @Column(name = "ID_DISPONIBILIDAD")
    private Long id;

    @Column(name = "DIA_SEMANA", nullable = false, length = 10)
    private String diaSemana;

    @Column(name = "HORA_INICIO", nullable = false, length = 5)
    private String horaInicio;

    @Column(name = "HORA_FIN", nullable = false, length = 5)
    private String horaFin;

    @Column(name = "TIPO_SERVICIO", nullable = false, length = 15)
    private String tipoServicio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CONDUCTOR", nullable = false)
    private Usuario conductor;

    @Column(name = "PLACA_VEHICULO", nullable = false, length = 10)
    private String placaVehiculo;

    public Disponibilidad() {}

    public Disponibilidad(String diaSemana, String horaInicio, String horaFin,
                          String tipoServicio, Usuario conductor, String placaVehiculo) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tipoServicio = tipoServicio;
        this.conductor = conductor;
        this.placaVehiculo = placaVehiculo;
    }

    public Long getId() { return id; }
    public String getDiaSemana() { return diaSemana; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
    public String getTipoServicio() { return tipoServicio; }
    public Usuario getConductor() { return conductor; }
    public String getPlacaVehiculo() { return placaVehiculo; }

    public void setId(Long id) { this.id = id; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    public void setConductor(Usuario conductor) { this.conductor = conductor; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }
}
