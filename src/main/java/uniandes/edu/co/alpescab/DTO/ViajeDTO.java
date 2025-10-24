package uniandes.edu.co.alpescab.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ViajeDTO {
    private Long id;
    private String estado;
    private UsuarioDTO conductor;
    private ServicioDTO servicio;
    private VehiculoDTO vehiculo;
    private String horaInicio;
    private String horaFin;
    private BigDecimal distanciaKm;
    private BigDecimal costoTotal;
    private BigDecimal gananciaConductor;
    private LocalDate fecha;

    public ViajeDTO(
            Long id,
            String estado,
            UsuarioDTO conductor,
            ServicioDTO servicio,
            VehiculoDTO vehiculo,
            String horaInicio,
            String horaFin,
            BigDecimal distanciaKm,
            BigDecimal costoTotal,
            BigDecimal gananciaConductor,
            LocalDate fecha
    ) {
        this.id = id;
        this.estado = estado;
        this.conductor = conductor;
        this.servicio = servicio;
        this.vehiculo = vehiculo;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.distanciaKm = distanciaKm;
        this.costoTotal = costoTotal;
        this.gananciaConductor = gananciaConductor;
        this.fecha = fecha;
    }

    public Long getId() { return id; }
    public String getEstado() { return estado; }
    public UsuarioDTO getConductor() { return conductor; }
    public ServicioDTO getServicio() { return servicio; }
    public VehiculoDTO getVehiculo() { return vehiculo; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
    public BigDecimal getDistanciaKm() { return distanciaKm; }
    public BigDecimal getCostoTotal() { return costoTotal; }
    public BigDecimal getGananciaConductor() { return gananciaConductor; }
    public LocalDate getFecha() { return fecha; }
}

