package uniandes.edu.co.alpescab.DTO;

public class SolicitarServicioRequestDTO {

    private Long idCliente;
    private Long idOrigen;
    private String tipoServicio;   // "pasajeros" | "comida" | "mercancias"


    private Long idDestino;
    private String nivel;          // Estandar / Confort / Large
    private Integer numeroPasajeros;

    private String restaurante;
    private Long idPuntoEntrega;

    private String horaInicio;


    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdOrigen() {
        return idOrigen;
    }

    public void setIdOrigen(Long idOrigen) {
        this.idOrigen = idOrigen;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public Long getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Long idDestino) {
        this.idDestino = idDestino;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Integer getNumeroPasajeros() {
        return numeroPasajeros;
    }

    public void setNumeroPasajeros(Integer numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public Long getIdPuntoEntrega() {
        return idPuntoEntrega;
    }

    public void setIdPuntoEntrega(Long idPuntoEntrega) {
        this.idPuntoEntrega = idPuntoEntrega;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }
}
