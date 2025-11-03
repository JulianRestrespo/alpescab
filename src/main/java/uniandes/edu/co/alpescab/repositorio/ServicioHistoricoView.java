package uniandes.edu.co.alpescab.repositorio;

import java.math.BigDecimal;


public interface ServicioHistoricoView {
    Long getIdServicio();
    String getTipoServicio();
    String getEstado();

    String getCiudadOrigen();

    String getHoraInicio();
    String getHoraFin();
    BigDecimal getDistanciaKm();
    BigDecimal getCostoTotal();

    String getConductor();
}

