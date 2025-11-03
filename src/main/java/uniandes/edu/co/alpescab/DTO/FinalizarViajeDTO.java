
package uniandes.edu.co.alpescab.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FinalizarViajeDTO {

  @NotNull
  private Long idConductor;     

  @NotNull
  private String horaFin;       

  @NotNull
  @Min(1)                       
  private Integer distanciaKm;  

  
  private boolean cerrarServicio = true;

  // getters & setters
    public Long getIdConductor() {
        return idConductor;
    }
    public void setIdConductor(Long idConductor) {
        this.idConductor = idConductor;
    }
    public String getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    public Integer getDistanciaKm() {
        return distanciaKm;
    }
    public void setDistanciaKm(Integer distanciaKm) {
        this.distanciaKm = distanciaKm;
    }
    public boolean isCerrarServicio() {
        return cerrarServicio;
    }
    public void setCerrarServicio(boolean cerrarServicio) {
        this.cerrarServicio = cerrarServicio;
    }
}
