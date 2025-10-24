package uniandes.edu.co.alpescab.DTO;

public class ServicioDTO {
    private Long idServicio;
    private String tipoServicio;
    private String estado;

    private UsuarioDTO cliente;                // datos básicos del cliente
    private PuntoGeograficoDTO origen;         // punto de origen

    public ServicioDTO() {}

    public ServicioDTO(
        Long idServicio,
        String tipoServicio,
        String estado,
        UsuarioDTO cliente,
        PuntoGeograficoDTO origen
    ) {
        this.idServicio = idServicio;
        this.tipoServicio = tipoServicio;
        this.estado = estado;
        this.cliente = cliente;
        this.origen = origen;
    }

    public Long getIdServicio() { return idServicio; }
    public String getTipoServicio() { return tipoServicio; }
    public String getEstado() { return estado; }
    public UsuarioDTO getCliente() { return cliente; }
    public PuntoGeograficoDTO getOrigen() { return origen; }

    public void setIdServicio(Long idServicio) { this.idServicio = idServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setCliente(UsuarioDTO cliente) { this.cliente = cliente; }
    public void setOrigen(PuntoGeograficoDTO origen) { this.origen = origen; }
}
