package uniandes.edu.co.alpescab.DTO;

import java.math.BigDecimal;

public class ServicioHistoricoDTO {
    private Long idServicio;
    private String tipoServicio;
    private String estado;
    private String ciudadOrigen;
    private String horaInicio;     
    private String horaFin;        
    private BigDecimal distanciaKm;  
    private BigDecimal costoTotal;   
    private String conductor;       

    public ServicioHistoricoDTO() {}

    public ServicioHistoricoDTO(
            Long idServicio,
            String tipoServicio,
            String estado,
            String ciudadOrigen,
            String horaInicio,
            String horaFin,
            BigDecimal distanciaKm,
            BigDecimal costoTotal,
            String conductor
    ) {
        this.idServicio = idServicio;
        this.tipoServicio = tipoServicio;
        this.estado = estado;
        this.ciudadOrigen = ciudadOrigen;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.distanciaKm = distanciaKm;
        this.costoTotal = costoTotal;
        this.conductor = conductor;
    }

    public Long getIdServicio() { return idServicio; }
    public String getTipoServicio() { return tipoServicio; }
    public String getEstado() { return estado; }
    public String getCiudadOrigen() { return ciudadOrigen; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
    public BigDecimal getDistanciaKm() { return distanciaKm; }
    public BigDecimal getCostoTotal() { return costoTotal; }
    public String getConductor() { return conductor; }

    public void setIdServicio(Long idServicio) { this.idServicio = idServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setCiudadOrigen(String ciudadOrigen) { this.ciudadOrigen = ciudadOrigen; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }
    public void setDistanciaKm(BigDecimal distanciaKm) { this.distanciaKm = distanciaKm; }
    public void setCostoTotal(BigDecimal costoTotal) { this.costoTotal = costoTotal; }
    public void setConductor(String conductor) { this.conductor = conductor; }
}

